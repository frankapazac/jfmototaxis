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
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotEmpadronamientoDaoFactory;
import com.munichosica.myapp.factory.MotPropUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotUnidDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.util.FileUtil;

@Controller
@RequestMapping("/UnidadEmpresa")
public class MotUnidadEmpresaController {
	
	Logger logger=Logger.getLogger(MotUnidadEmpresaController.class);
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody EmpadronamientoUtil procesar(HttpServletRequest request,
			EmpadronamientoUtil empadronamientoUtil){
		logger.info("Ingreso a /UnidadEmpresa/Procesar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		MotUnidDocumentoSession documentosUnidad=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS");
		MotUnidDocumentoSession documentosFoto=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS_FOTO");
		try {
			empadronamientoUtil.getEmpadronamiento().setEmpresa(usuario.getEmpresa());
			MotUnidadEmpresaDaoFactory.create().insert(
					empadronamientoUtil.getPropUnidadEmpresa().getUnidadempresa());
			logger.info("MotUnidadEmpresaDaoFactory.create().insert(empadronamientoUtil.getPropUnidadEmpresa().getUnidadempresa());Completed codigo:"+
							empadronamientoUtil.getPropUnidadEmpresa().getUnidadempresa().getUnecodigoD());
			MotEmpadronamientoDaoFactory.create().insert(empadronamientoUtil.getEmpadronamiento());
			logger.info("MotEmpadronamientoDaoFactory.create().insert(empadronamientoUtil.getEmpadronamiento()); Completed codigo:"+
							empadronamientoUtil.getEmpadronamiento().getEpocodigoD());
			MotPropUnidadEmpresaDaoFactory.create().insert(empadronamientoUtil.getPropUnidadEmpresa());
			logger.info("MotPropUnidadEmpresaDaoFactory.create().insert(empadronamientoUtil.getPropUnidadEmpresa());Completed codigo:"+
							empadronamientoUtil.getPropUnidadEmpresa().getUnidadempresa().getUnecodigoD());
			if(documentosUnidad!=null){
				if(documentosUnidad.getList()!=null&&documentosUnidad.getList().size()>0){
					for(MotUnidDocumento documento:documentosUnidad.getList()){
						documento.setUnidadEmpresa(empadronamientoUtil.getPropUnidadEmpresa());
						MotAdjuntarArchivoDaoFactory.create().insert(documento.getAdjuntarArchivo());
						MotUnidDocumentoDaoFactory.create().insert(documento);
						logger.info("MotUnidDocumentoDaoFactory.create().insert(documento);Complete codigo: "+documento.getPtdcodigoD());
					}
				}
			}
			if(documentosFoto!=null){
				if(documentosFoto.getList()!=null&&documentosFoto.getList().size()>0){
					for(MotUnidDocumento documento:documentosFoto.getList()){
						documento.setUnidadEmpresa(empadronamientoUtil.getPropUnidadEmpresa());
						MotAdjuntarArchivoDaoFactory.create().insert(documento.getAdjuntarArchivo());
						MotUnidDocumentoDaoFactory.create().insert(documento);
						logger.info("MotUnidDocumentoDaoFactory.create().insert(documento);Complete codigo: "+documento.getPtdcodigoD());
					}
				}
			}
			
		} catch (MotUnidadEmpresaDaoException | MotEmpadronamientoDaoException | MotPropUnidadEmpresaDaoException | MotAdjuntarArchivoDaoException | MotUnidDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return empadronamientoUtil;
	}
	
	@RequestMapping(value="ListarPorAsociado.htm",method=RequestMethod.GET)
	public @ResponseBody List<MotPropUnidadEmpresa> listarPorAsociados(Long codigo){
		List<MotPropUnidadEmpresa> list=null;
		try {
			list=MotPropUnidadEmpresaDaoFactory.create().findByAsociado(codigo);
		} catch (MotPropUnidadEmpresaDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Documento.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public String agregarDocumento(HttpServletRequest request){
		logger.info("Ingreso a UnidadEmpresa/Documento.htm");
		HttpSession session=request.getSession(true);
		MotUnidDocumentoSession documentos=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS");
		if(documentos==null){
			documentos=new MotUnidDocumentoSession();
			session.setAttribute("UNIDAD_DOCUMENTOS", documentos);
			logger.info("Se creo la session.setAttribute('UNIDAD_DOCUMENTOS', documentos);");
		}
		try {
			MotUnidDocumento documento=new MotUnidDocumento();
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
			documento.setAdjuntarArchivo(archivo);
			documentos.add(documento);
			logger.info("Documentos subidos en total: "+documentos.getList().size());
		} catch (Exception e) {
			logger.error("ERROR: "+e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="DocumentoFoto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarDocumentoFoto(HttpServletRequest request){
		logger.info("Ingreso a UnidadEmpresa/DocumentoFoto.htm");
		HttpSession session=request.getSession(true);
		MotUnidDocumentoSession documentos=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS_FOTO");
		if(documentos==null){
			documentos=new MotUnidDocumentoSession();
			session.setAttribute("UNIDAD_DOCUMENTOS_FOTO", documentos);
			logger.info("Se creo la session.setAttribute('UNIDAD_DOCUMENTOS_FOTO', documentos);");
		}
		String nombreArchivo="";
		try {
			MotUnidDocumento documento=new MotUnidDocumento();
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
					case "fileDocumento"://System.out.println("fileDocumento");
						String filename=FileUtil.getFilename(part);
						archivo.setAdjnombreV(filename);
						archivo.setAdjarchivoB(FileUtil.compress(bs));
						archivo.setAdjextensionV(FileUtil.getExtension(filename));
						nombreArchivo=FileUtil.createTempFile(request, archivo.getAdjnombreV(), bs);
						break;
				}
			}

			nombreArchivo=tipoDocumento.getMtdcodigoI()+"|"+nombreArchivo;
			
			documento.setTipoDocumento(tipoDocumento);
			documento.setAdjuntarArchivo(archivo);
			documentos.add(documento);
			logger.info("Documentos subidos en total: "+documentos.getList().size());
		} catch (Exception e) {
			logger.error("ERROR: "+e.getMessage());
		}
		return nombreArchivo;
	}
}
