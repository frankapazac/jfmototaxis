package com.munichosica.myapp.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.dto.MotInspDocumento;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotCondDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotConductorDaoFactory;
import com.munichosica.myapp.factory.MotEmpConductorDaoFactory;
import com.munichosica.myapp.factory.MotInspDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotPersonaDaoFactory;
import com.munichosica.myapp.factory.MotUnidConductorDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.IpUtils;
import com.munichosica.myapp.util.UTFEncodingUtil;

@Controller
@RequestMapping("/Conductores")
public class ConductorController {

protected final Logger logger=Logger.getLogger(ConductorController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpConductor> listar
	(HttpServletRequest request,@RequestParam("criterio") String criterio,@RequestParam("texto") String texto){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		logger.info("Ingreso a Conductores/Listar.htm");
		List<MotEmpConductor> list = null;
		
		try {
			list = MotEmpConductorDaoFactory.create().findByCriterio(criterio, texto, usuario.getEmpresa().getEmpcodigoD());
			logger.info("MotEmpConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1);Completed");
		} catch (MotEmpConductorDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="ListarMotosAsignadas.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotUnidConductor> listarMotosAsignadas
	(HttpServletRequest request,@RequestParam("criterio") String criterio,
			@RequestParam("texto") String texto,@RequestParam("codCondu") Long conductor){
		logger.info("Ingreso a Conductores/ListarMotosAsignadas.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		MotPropUnidEmpresaSession puemp=(MotPropUnidEmpresaSession) 
				session.getAttribute("PROP_UNID_EMPRESA");
		if(puemp==null){
			puemp=new MotPropUnidEmpresaSession();
			session.setAttribute("PROP_UNID_EMPRESA", puemp);
			logger.info("Se creo session.setAttribute('PROP_UNID_EMPRESA', puem);");
		}else{
			if(puemp.getList()!=null&&puemp.getList().size()>0){
				puemp.getList().clear();
				logger.info("Se limpio la lista session.setAttribute('PROP_UNID_EMPRESA', puem);");
			}
		}
		puemp.getConductor().setConcodigoD(conductor);
		List<MotUnidConductor> list = null;
		try {
			/*System.out.println("Criterio "+criterio);
			System.out.println("texto "+texto);
			System.out.println("usuario.getEmpresa().getEmpcodigoD() "+usuario.getEmpresa().getEmpcodigoD());
			System.out.println("conductor "+conductor);*/
			list = MotUnidConductorDaoFactory.create().findByCriterio(criterio, texto, usuario.getEmpresa().getEmpcodigoD(), conductor);
			logger.info("MotUnidConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1);Completed");
		} catch (MotUnidConductorDaoException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
			
		return list;
	}
	
	@RequestMapping(value="CesarConductor.htm", method=RequestMethod.POST)
	public String CesarConductor(HttpServletRequest request ,MotUnidConductor unidadConductor){
		logger.info("Ingreso a Conductores/CesarConductor.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotUnidConductorDaoFactory.create().insert(unidadConductor);
			logger.info("MotUnidConductorDaoFactory.create().insert(unidadConductor);Completed");
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_UNID_CONDUCTOR", unidadConductor.getUcocodigoD(),"SP_MOT_INS_CESE_CONDUCTOR_MOTOTAXI",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotUnidConductorDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody MotEmpConductor procesar 
	(HttpServletRequest request , MotEmpConductor conductor,Model model){
		
		logger.info("Ingreso a GuardarConductor/Procesar.htm");
		
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		DocumentoConductorSession documentos = (DocumentoConductorSession)
				session.getAttribute("DOC_CONDUCTOR");
		DocumentoConductorSession foto=(DocumentoConductorSession) session.getAttribute("FOTO_CONDUCTOR");
		conductor.setEmpresa((usuario.getEmpresa()));
		UTFEncodingUtil.decodeObjectUTF(conductor);
		UTFEncodingUtil.decodeObjectUTF(conductor.getConductor().getPersona());
		try {
			MotEmpConductorDaoFactory.create().procesar(conductor);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_CONDUCTOR", conductor.getEcocodigoD(),"SP_MOT_INS_PERSONA_CONDUCTOR",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			if(documentos!=null){
				if(documentos.getList()!=null && documentos.getList().size()>0){
					for(MotCondDocumento docum:documentos.getList()){
						docum.setConductor(conductor.getConductor());
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getAdjuntarArchivo());
						MotCondDocumentoDaoFactory.create().insert(docum);
						MotAuditoriaDaoFactory.create().Insert(
								"MOT_COND_DOCUMENTO", docum.getCdocodigoD(),"SP_MOT_INS_COND_DOCUMENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
					}
				}
			}
			
			if(foto!=null){
				if(foto.getList()!=null && foto.getList().size()>0){
					for(MotCondDocumento docum:foto.getList()){
						docum.setConductor(conductor.getConductor());
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getAdjuntarArchivo());
						MotCondDocumentoDaoFactory.create().insert(docum);
						MotAuditoriaDaoFactory.create().Insert(
								"MOT_COND_DOCUMENTO", docum.getCdocodigoD(),"SP_MOT_INS_COND_DOCUMENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
						logger.info("MotCondDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getCdocodigoD());
					}
				}
			}
			
		} catch (MotEmpConductorDaoException | MotAdjuntarArchivoDaoException | MotCondDocumentoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
			
		return conductor;
		
	}
	
	@RequestMapping(value="Foto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarFoto(HttpServletRequest request){
		logger.info("Ingreso a Conductores/Foto.htm");
		HttpSession session=request.getSession(true);
		DocumentoConductorSession documentos=(DocumentoConductorSession) session.getAttribute("FOTO_CONDUCTOR");
		if(documentos==null){
			documentos=new DocumentoConductorSession();
			session.setAttribute("FOTO_CONDUCTOR", documentos);
			logger.info("Se creo session.setAttribute('FOTO_CONDUCTOR', documentos);");
		}
		String nombreArchivo="";
		try {
			for(Part part:request.getParts()){
				MotCondDocumento documento=new MotCondDocumento();
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				if(part.getName().equals("fileFotoConductor")){
					String filename=FileUtil.getFilename(part);
					documento.getAdjuntarArchivo().setAdjcodigoD(0L);
					documento.getAdjuntarArchivo().setAdjnombreV(filename);
					documento.getAdjuntarArchivo().setAdjarchivoB(FileUtil.compress(bs));
					documento.getAdjuntarArchivo().setAdjextensionV(FileUtil.getExtension(filename));
					nombreArchivo=FileUtil.createTempFile(request, documento.getAdjuntarArchivo().getAdjnombreV(), bs);
				}
				documento.getTipoDocumento().setMtdcodigoI(21);
				documentos.add(documento);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return nombreArchivo;
	}
	
	@RequestMapping(value="Documento.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarDocumento(HttpServletRequest request){
		logger.info("Ingreso a Condcutores/Documento.htm");
		HttpSession session=request.getSession(true);
		DocumentoConductorSession documentos=(DocumentoConductorSession) session.getAttribute("DOC_CONDUCTOR");
		if(documentos==null){
			documentos=new DocumentoConductorSession();
			session.setAttribute("DOC_CONDUCTOR", documentos);
			logger.info("Se creo session.setAttribute('DOC_CONDUCTOR', documentos);");
		}
		try {
			MotCondDocumento documento=new MotCondDocumento();
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
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotCondDocumento obtener(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		MotCondDocumento conductor=null;
		HttpSession session=request.getSession(true);
		DocumentoConductorSession documentos=(DocumentoConductorSession) session.getAttribute("DOC_CONDUCTOR");
		if(documentos!=null){
			documentos.getList().clear();
			logger.info("documentos.getList().clear(); Session DOC_INSPECTOR limpiada completamente.");
		}
		try {System.out.println("CODIGO: "+codigo);
			conductor = MotCondDocumentoDaoFactory.create().findByPrimaryKey(codigo);
			logger.info("MotCondDocumentoDaoFactory.create().findByPrimaryKey(codigo); Completed");
			
			List<MotCondDocumento> listDocumentos=MotCondDocumentoDaoFactory.create().findByIdConductor(codigo);
			conductor.getConductor().setDocumentos(listDocumentos);
			
			String nombreArchivo=null;
			if(conductor.getConductor().getFoto()!=null&&conductor.getConductor().getFoto().getAdjarchivoB()!=null){
				nombreArchivo=FileUtil.createTempFile(request, conductor.getConductor().getFoto().getAdjnombreV(),conductor.getConductor().getFoto().getAdjarchivoB());
				conductor.getConductor().getFoto().setAdjnombreV(nombreArchivo);
			}
		} catch (MotCondDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return conductor;
	}
	
	@RequestMapping(value="Insertar.htm", method=RequestMethod.POST)
	public @ResponseBody MotConductor insertar(HttpServletRequest request, MotConductor conductor) {
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotPersonaDaoFactory.create().insertar(conductor.getPersona());
			MotConductorDaoFactory.create().insert(conductor);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_CONDUCTOR", conductor.getConcodigoD(),"SP_MOT_INS_CONDUCTOR",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotPersonaDaoException | MotConductorDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return conductor;
	}
		
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			logger.info("Eliminar Conductor/Eliminar.htm");
			MotCondDocumento conductor=new MotCondDocumento();
			conductor.getConductor().setConcodigoD(codigo);
			
			MotCondDocumentoDaoFactory.create().delete(conductor);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_CONDUCTOR", conductor.getCdocodigoD(),"SP_UPD_ESTADO_MOT_CONDUCTOR",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("MotConductorDaoFactory.create().delete(paradero); Complete");
		} catch (MotCondDocumentoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}	
	
}
