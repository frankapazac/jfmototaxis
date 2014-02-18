package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotConductorDaoFactory;
import com.munichosica.myapp.factory.MotEmpadronamientoDaoFactory;
import com.munichosica.myapp.factory.MotEmprAsociadoDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotPropUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotUnidDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.ReportsDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Mototaxi")
public class MototaxiController {
protected final Logger logger=Logger.getLogger(MototaxiController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpadronamiento> listar(@RequestParam("criterio") String criterio,
				@RequestParam("texto") String texto, HttpServletRequest request){
		logger.info("Ingreso a Mototaxi/Listar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		List<MotEmpadronamiento> list=null;
		try {
			list=MotEmpadronamientoDaoFactory.create().findByCriterio(criterio, texto,
					usuario.getEmpresa().getEmpcodigoD());
			logger.info("MotParaderoDaoFactory.create().findByCriterio(criterio, texto,(long) 1);Completed");
		} catch (MotEmpadronamientoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="GuardarCeseMoto.htm", method=RequestMethod.POST)
	public @ResponseBody String guardarCeseMoto(HttpServletRequest request,MotPropUnidadEmpresa unidadEmpresa){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "ERROR";
		}
		try {
			MotPropUnidadEmpresaDaoFactory.create().cesarMototaxi(unidadEmpresa);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_PROP_UNIEMPRESA", unidadEmpresa.getUnidadempresa().getUnecodigoD(),"SP_MOT_UPD_PROP_UNIEMPRESA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotPropUnidadEmpresaDaoException | MotAuditoriaDaoException e) {
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping(value="ObtenerCeseMoto.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa obtener(Long codigo){
		MotUnidadEmpresa unidadEmpresa = null;
		try {
			unidadEmpresa = MotUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo);
		} catch (MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return unidadEmpresa;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MototaxiUtil obtener(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a @RequestMapping(value=Obtener.htm, method=RequestMethod.GET) codigo:"+codigo);
		HttpSession session=request.getSession(true);
		MotUnidDocumentoSession documentoSession=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS");
		MotUnidDocumentoSession documentoFotoSession=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS_FOTO");
		if(documentoSession!=null){
			documentoSession.getList().clear();
			logger.info("documentoSession.getList().clear(); Session DOCUMENTOS_UNIDAD limpiada completamente.");
		}
		if(documentoFotoSession!=null){
			documentoFotoSession.getList().clear();
			logger.info("documentoFotoSession.getList().clear(); Session DOCUMENTOS_UNIDAD_FOTO limpiada completamente.");
		}
		MototaxiUtil mototaxi=null;
		try {
			mototaxi=new MototaxiUtil();
			mototaxi.setUnidadEmpresa(MotUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo));
			logger.info("mototaxi.setUnidadEmpresa(MotUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo));");
			mototaxi.setDocumentos(MotUnidDocumentoDaoFactory.create().findDocumentosByIdUnidad(codigo));
			logger.info("mototaxi.setDocumentos(MotUnidDocumentoDaoFactory.create().findDocumentosByIdUnidad(codigo));");
			mototaxi.setFotos(MotUnidDocumentoDaoFactory.create().findFotosByIdUnidad(codigo));
			logger.info("mototaxi.setFotos(MotUnidDocumentoDaoFactory.create().findFotosByIdUnidad(codigo));");
			for(MotUnidDocumento unidDoc:mototaxi.getFotos()){
				String nombreArchivo=FileUtil.createTempFile(request, 
						unidDoc.getAdjuntarArchivo().getAdjnombreV(), unidDoc.getAdjuntarArchivo().getAdjarchivoB());
				unidDoc.getAdjuntarArchivo().setAdjnombreV(nombreArchivo);
			}
			mototaxi.setEmpadronamiento(MotEmpadronamientoDaoFactory.create().findByUnidad(codigo));
			logger.info("mototaxi.setEmpadronamiento(MotEmpadronamientoDaoFactory.create().findByUnidad(codigo));");
		} catch (MotUnidadEmpresaDaoException | MotUnidDocumentoDaoException | MotEmpadronamientoDaoException e) {
			logger.error(e.getMessage());
		}
		return mototaxi;
	}
	
	@RequestMapping(value="ObtenerUnidad.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa obtenerUnidad(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			unidadEmpresa=MotUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo);
			unidadEmpresa.setAsociado(MotEmprAsociadoDaoFactory.create().findByPrimaryKey(unidadEmpresa.getAsociado().getAsocodigoD()));
		} catch (MotUnidadEmpresaDaoException | MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage());
		}
		return unidadEmpresa;
	}
	
	@RequestMapping(value="ObtenerInforme.htm", method=RequestMethod.GET)
	public @ResponseBody MotEmpadronamiento obtenerInforme(HttpServletRequest request,@RequestParam("codigo") Long codigo){	
		logger.info("Ingreso a obtenerInforme/ObtenerInforme.htm");

		MotEmpadronamiento empadronamiento = null;
		try {
			
			empadronamiento = MotEmpadronamientoDaoFactory.create().findByPropietarioEmp(codigo);
			
		} catch (MotEmpadronamientoDaoException e) {
			logger.info(e.getMessage());
		}
		return empadronamiento;
	}
	
	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(Long codigo){
		ModelAndView mav=null;
		System.out.println("Ingreso a Mototaxis/ImprimirPdf.htm Codigo:"+codigo);
		Map<String, Object> parameters= new HashMap<String, Object>();
		MotConductor conductor=null;
		try {
			conductor=MotConductorDaoFactory.create().findByPmoCodigo(codigo);
			parameters.put("CODIGO", codigo);
			parameters.put("conductor", conductor);
			mav=new ModelAndView("reportInformeMototaxi", parameters);
		} catch (MotConductorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="ImprimirMotosconAltas.htm", method=RequestMethod.GET)
	public ModelAndView ImprimirMotosconAltas(HttpServletRequest request,String FECHA1 ,String FECHA2){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("FECHA1", FECHA1);
		parameters.put("FECHA2", FECHA2);
		parameters.put("EMPCODIGO_D", usuario.getEmpresa().getEmpcodigoD());
			mav=new ModelAndView("reportInformeMotosAlta", parameters);
			
		return mav;
	}

	@RequestMapping(value="ImprimirMotosconBajas.htm", method=RequestMethod.GET)
	public ModelAndView ImprimirMotosconBajas(HttpServletRequest request,String FECHA1 ,String FECHA2){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
			parameters.put("FECHA1", FECHA1);
			parameters.put("FECHA2", FECHA2);
			parameters.put("EMPCODIGO_D", usuario.getEmpresa().getEmpcodigoD());
			mav=new ModelAndView("reportInformeMotosBaja", parameters);
			
		return mav;
	}
	
	
	

	
	

}
