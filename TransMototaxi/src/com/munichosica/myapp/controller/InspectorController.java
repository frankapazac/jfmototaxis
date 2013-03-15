package com.munichosica.myapp.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotInspDocumento;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotInspDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotInspDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotInspectorDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.UTFEncodingUtil;

@Controller
@RequestMapping("/Inspectores")
public class InspectorController {
	
	protected static final Logger logger=Logger.getLogger(InspectorController.class);
	
	@RequestMapping(value="Procesar.htm",method=RequestMethod.POST)
	public @ResponseBody MotInspector procesar(HttpServletRequest request,MotInspector inspector){
		HttpSession session=request.getSession(true);
		DocumentoInspectorSession documentos=(DocumentoInspectorSession) session.getAttribute("DOC_INSPECTOR");
		DocumentoInspectorSession foto=(DocumentoInspectorSession) session.getAttribute("FOTO_INSPECTOR");
		try {
			UTFEncodingUtil.decodeObjectUTF(inspector.getPersona());
			MotInspectorDaoFactory.create().procesar(inspector);
			logger.info("MotInspectorDaoFactory.create().procesar(inspector); Completed codigo: "+inspector.getInscodigoI());
			
			if(documentos!=null){
				if(documentos.getList()!=null && documentos.getList().size()>0){
					for(MotInspDocumento docum:documentos.getList()){
						docum.setInspector(inspector);
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getArchivo());
						MotInspDocumentoDaoFactory.create().insert(docum);
						logger.info("MotInspDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getIdocodigoD());
					}
				}
			}
			
			if(foto!=null){
				if(foto.getList()!=null && foto.getList().size()>0){
					for(MotInspDocumento docum:foto.getList()){
						docum.setInspector(inspector);
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getArchivo());
						MotInspDocumentoDaoFactory.create().insert(docum);
						logger.info("MotAsocDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getIdocodigoD());
					}
				}
			}
		} catch (MotInspectorDaoException | MotAdjuntarArchivoDaoException | MotInspDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return inspector;
	}
	
	@RequestMapping(value="Listar.htm",method=RequestMethod.POST)
	public @ResponseBody List<MotInspector> listar(
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Inspectores/Listar.htm");
		List<MotInspector> list=null;
		try {
			list = MotInspectorDaoFactory.create().findByCriterio(criterio, texto);
			logger.info("MotInspectorDaoFactory.create().findByCriterio(criterio, texto); Completed");
		} catch (MotInspectorDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(int codigo){
		try {
			MotInspectorDaoFactory.create().delete(codigo);
			logger.info("MotInspectorDaoFactory.create().delete(codigo);");
		} catch (MotInspectorDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotInspector obtener(HttpServletRequest request,@RequestParam("codigo") int codigo){
		MotInspector inspector=null;
		HttpSession session=request.getSession(true);
		DocumentoInspectorSession foto=(DocumentoInspectorSession) session.getAttribute("FOTO_INSPECTOR");
		DocumentoInspectorSession documentos=(DocumentoInspectorSession) session.getAttribute("DOC_INSPECTOR");
		if(documentos!=null){
			documentos.getList().clear();
			logger.info("documentos.getList().clear(); Session DOC_INSPECTOR limpiada completamente.");
		}
		if(foto!=null){
			foto.getList().clear();
			logger.info("documentos.getList().clear(); Session FOTO_INSPECTOR limpiada completamente.");
		}
		try {
			inspector = MotInspectorDaoFactory.create().findByPrimaryKey(codigo);
			logger.info("MotInspectorDaoFactory.create().findByPrimaryKey(codigo); Completed");
			
			List<MotInspDocumento> listDocumentos=MotInspDocumentoDaoFactory.create().findByIdInspector(codigo);
			if(listDocumentos!=null)
			inspector.setDocumentos(listDocumentos);
			
			String nombreArchivo=null;
			if(inspector.getFoto()!=null){
				if(inspector.getFoto().getAdjarchivoB()!=null){
					nombreArchivo=FileUtil.createTempFile(request, inspector.getFoto().getAdjnombreV(),inspector.getFoto().getAdjarchivoB());
					inspector.getFoto().setAdjnombreV(nombreArchivo);
				}
			}
		} catch (MotInspectorDaoException | MotInspDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return inspector;
	}
	
	@RequestMapping(value="Foto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarFoto(HttpServletRequest request){
		logger.info("Ingreso a Asociados/Foto.htm");
		HttpSession session=request.getSession(true);
		DocumentoInspectorSession documentos=(DocumentoInspectorSession) session.getAttribute("FOTO_INSPECTOR");
		if(documentos==null){
			documentos=new DocumentoInspectorSession();
			session.setAttribute("FOTO_INSPECTOR", documentos);
			logger.info("Se creo session.setAttribute('FOTO_INSPECTOR', documentos);");
		}
		String nombreArchivo="";
		try {
			for(Part part:request.getParts()){
				MotInspDocumento documento=new MotInspDocumento();
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				if(part.getName().equals("fileFotoInspector")){
					String filename=FileUtil.getFilename(part);
					documento.getArchivo().setAdjcodigoD(0L);
					documento.getArchivo().setAdjnombreV(filename);
					documento.getArchivo().setAdjarchivoB(FileUtil.compress(bs));
					documento.getArchivo().setAdjextensionV(FileUtil.getExtension(filename));
					nombreArchivo=FileUtil.createTempFile(request, documento.getArchivo().getAdjnombreV(), bs);
				}
				documento.getTipoDocumento().setMtdcodigoI(20);
				documentos.add(documento);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return nombreArchivo;
	}

	@RequestMapping(value="Documento.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarDocumento(HttpServletRequest request){
		logger.info("Ingreso a Inspectores/Documento.htm");
		HttpSession session=request.getSession(true);
		DocumentoInspectorSession documentos=(DocumentoInspectorSession) session.getAttribute("DOC_INSPECTOR");
		if(documentos==null){
			documentos=new DocumentoInspectorSession();
			session.setAttribute("DOC_INSPECTOR", documentos);
			logger.info("Se creo session.setAttribute('DOC_INSPECTOR', documentos);");
		}
		try {
			MotInspDocumento documento=new MotInspDocumento();
			MotTipoDocumento tipoDocumento=new MotTipoDocumento();
			MotAdjuntarArchivo archivo=new MotAdjuntarArchivo();
			for(Part part:request.getParts()){
				//System.out.println("NOMBRE: "+part.getName());
				//System.out.println("FILENAME: "+getFilename(part));
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				switch(part.getName()){
					case "txtCodDocumento"://System.out.println("txtCodDocumento");
						tipoDocumento.setMtdcodigoI(Integer.parseInt(new String(bs,"UTF8")));
						break;
					case "txtCodArchivo"://System.out.println("txtCodDocumento");
						archivo.setAdjcodigoD(Long.parseLong(new String(bs,"UTF8")));
						break;
					case "txtNumDocumento"://System.out.println("txtNumDocumento");
						archivo.setAdjnumeroV(new String(bs,"UTF8"));
						break;
					case "txtFechaEmision"://System.out.println("txtFechaEmision");
						archivo.setAdjfechaemisionF(new String(bs,"UTF8"));
						break;
					case "txtFechaCaducidad"://System.out.println("txtFechaCaducidad");
						archivo.setAdjfechacaducidadF(new String(bs,"UTF8"));
						break;
					case "fileDocumento"://System.out.println("fileDocumento");
						String filename=FileUtil.getFilename(part);
						archivo.setAdjnombreV(filename);
						archivo.setAdjarchivoB(FileUtil.compress(bs));
						archivo.setAdjextensionV(FileUtil.getExtension(filename));
						break;
				}
			}
			documento.setTipoDocumento(tipoDocumento);
			documento.setArchivo(archivo);
			documentos.add(documento);
			logger.info("Documentos subidos en total: "+documentos.getList().size());
		} catch (Exception e) {
			logger.error("ERROR: "+e.getMessage());
		}
		return "Success";
	}
}
