package com.munichosica.myapp.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.Usuempr;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotAsocDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotEmprAsociadoDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.UTFEncodingUtil;

@Controller
@RequestMapping("/Asociados")
public class AsociadoController {
	
	protected static final Logger logger = Logger.getLogger( AsociadoController.class );
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmprAsociado> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Asociados/Listar.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		List<MotEmprAsociado> list=null;
		try {
			list=MotEmprAsociadoDaoFactory.create().findByCriterio(criterio, 
					texto, usuempr.getEmpresa().getEmpcodigoD());
			logger.info("MotEmprAsociadoDaoFactory.create().findByCriterio(criterio, texto, codigo); Complete");
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Procesar.htm",method=RequestMethod.POST)
	public @ResponseBody MotEmprAsociado procesar(HttpServletRequest request, MotEmprAsociado asociado,Model model){
		logger.info("Ingreso a Asociados/Procesar.htm");
		try {
			HttpSession session=request.getSession(true);
			Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
			DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
			/*if(documentos==null||documentos.getList().size()<3){
				throw new MotEmprAsociadoDaoException("Aun no se agregaron documentos");
			}*/
			
			asociado.setEmpresa(usuempr.getEmpresa());
			UTFEncodingUtil.decodeObjectUTF(asociado);
			UTFEncodingUtil.decodeObjectUTF(asociado.getPersona());
			MotEmprAsociadoDaoFactory.create().procesar(asociado);
			logger.info("MotEmprAsociadoDaoFactory.create().procesar(asociado); Complete codigo: "+asociado.getAsocodigoD());
			if(documentos!=null){
				if(documentos.getList()!=null && documentos.getList().size()>0){
					for(MotAsocDocumento docum:documentos.getList()){
						docum.setAsociado(asociado);
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getArchivo());
						MotAsocDocumentoDaoFactory.create().insert(docum);
						logger.info("MotAsocDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getAdocodigoD());
					}
				}
			}
		} catch (MotEmprAsociadoDaoException | MotAdjuntarArchivoDaoException | MotAsocDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return asociado;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody AsociadoUtil obtener(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		//NO OLVIDAR LIMPIAR LA SESSION DOCUMENTOS
		logger.info("Ingreso a Asociados/Obtener.htm");
		HttpSession session=request.getSession(true);
		DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
		if(documentos!=null){
			documentos.getList().clear();
			logger.info("documentos.getList().clear(); Session DOCUMENTOS limpiada completamente.");
		}
		
		AsociadoUtil asociadoUtil=null;
		try {
			asociadoUtil=new AsociadoUtil();
			MotEmprAsociado asociado=MotEmprAsociadoDaoFactory.create().findByPrimaryKey(codigo);
			List<MotAsocDocumento> listDocumentos=MotAsocDocumentoDaoFactory.create().findByIdAsociado(codigo);
			asociadoUtil.setAsociado(asociado);
			asociadoUtil.setListDocumentos(listDocumentos);
			logger.info("MotEmprAsociadoDaoFactory.create().findByPrimaryKey(codigo); Complete");
		} catch (MotEmprAsociadoDaoException | MotAsocDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return asociadoUtil;
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a Asociados/Eliminar.htm");
		try {
			MotEmprAsociadoDaoFactory.create().delete(codigo);
			logger.info("MotEmprAsociadoDaoFactory.create().delete(codigo); Complete");
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Documento.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public String agregarDocumento(HttpServletRequest request){
		logger.info("Ingreso a Asociados/Documento.htm");
		HttpSession session=request.getSession(true);
		DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
		if(documentos==null){
			documentos=new DocumentoSession();
			session.setAttribute("DOCUMENTOS", documentos);
			logger.info("Se creo session.setAttribute('DOCUMENTOS', documentos);");
		}
		try {
			MotAsocDocumento documento=new MotAsocDocumento();
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
