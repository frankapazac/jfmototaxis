package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotActaConformidadDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotBoletaInternamientoDaoException;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.factory.MotActaConformidadDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotBoletaInternamientoDaoFactory;
import com.munichosica.myapp.factory.MotInteInventarioDaoFactory;
import com.munichosica.myapp.factory.MotInternamientoDaoFactory;
import com.munichosica.myapp.factory.MotPapeletaDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.ReportsDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Internamientos")
public class InternamientoController {
	
	protected static final Logger logger=Logger.getLogger(InternamientoController.class);

	@RequestMapping(value="BuscarPorPapeleta.htm", method=RequestMethod.POST)
	public @ResponseBody MotPapeleta buscarPorPapeleta(String papnumero){
		MotPapeleta papeleta=null;
		try {
			papeleta = MotPapeletaDaoFactory.create().findInternamientoByPapeleta(papnumero);
		} catch (MotPapeletaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return papeleta;
	}
	
	@RequestMapping(value="BuscarUnidadPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa BuscarUnidadPorCodigo(Long codigo){
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			unidadEmpresa=MotUnidadEmpresaDaoFactory.create().findPmoCodigo(codigo);
		} catch (MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return unidadEmpresa;
	}
	
	@RequestMapping(value="Insertar.htm", method=RequestMethod.POST)
	public @ResponseBody String insertar(HttpServletRequest request, @RequestBody MotInternamiento internamiento){
		logger.info("Ingreso a Internamientos/Insertar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotBoletaInternamientoDaoFactory.create().procesar(internamiento.getBoletaInternamiento());
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_BOLETA_INTERNAMIENTO", internamiento.getBoletaInternamiento().getBincodigoD(),"SP_MOT_INS_BOLETAINTERNAMIENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			MotInternamientoDaoFactory.create().procesar(internamiento);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_INTERNAMIENTO", internamiento.getIntcodigoD(),"SP_MOT_INS_INTERNAMIENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			for(MotInteInventario inventario:internamiento.getInventarios()){
				inventario.setInternamiento(internamiento);
				MotInteInventarioDaoFactory.create().insertar(inventario);
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_INTE_INVENTARIO", inventario.getBivcodigoD(),"SP_MOT_INS_INTE_INVENTARIO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			}
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException | MotBoletaInternamientoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return String.valueOf(internamiento.getIntcodigoD());
	}
	
	@RequestMapping(value="Modificar.htm", method=RequestMethod.POST)
	public @ResponseBody String modificar(HttpServletRequest request,@RequestBody MotInternamiento internamiento){
		logger.info("Ingreso a Internamientos/Modificar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotBoletaInternamientoDaoFactory.create().procesar(internamiento.getBoletaInternamiento());
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_BOLETA_INTERNAMIENTO", internamiento.getBoletaInternamiento().getBincodigoD(),"SP_MOT_INS_BOLETAINTERNAMIENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			MotInternamientoDaoFactory.create().procesar(internamiento);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_INTERNAMIENTO", internamiento.getIntcodigoD(),"SP_MOT_INS_INTERNAMIENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			for(MotInteInventario inventario:internamiento.getInventarios()){
				inventario.setInternamiento(internamiento);
				MotInteInventarioDaoFactory.create().modificar(inventario);
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_INTE_INVENTARIO", inventario.getInternamiento().getIntcodigoD(),"SP_MOT_UPD_INTE_INVENTARIO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
				
			}
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException | MotBoletaInternamientoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return String.valueOf(internamiento.getIntcodigoD());
	}
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotInternamiento> listar(String criterio, String texto){
		List<MotInternamiento> list=null;
		try {
			list=MotInternamientoDaoFactory.create().listarPorCriterio(criterio,texto);
		} catch (MotInternamientoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.POST)
	public @ResponseBody MotInternamiento obtener(Long codigo){
		MotInternamiento internamiento=null;
		try {
			internamiento=MotInternamientoDaoFactory.create().get(codigo);
			internamiento.setInventarios(MotInteInventarioDaoFactory.create().findByInternamiento(codigo));
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return internamiento;
	}
	
	@RequestMapping(value="ObtenerPropietario.htm", method=RequestMethod.GET)
	public @ResponseBody MotInternamiento obtenerPropietario(Long codigo){
		logger.info("ObtenerPropietario.htm Codigo: "+codigo);
		MotInternamiento internamiento=null;
		try {
			internamiento=MotInternamientoDaoFactory.create().getPropietarioInternamiento(codigo);
			internamiento.setPersonas(MotActaConformidadDaoFactory.create().obtenerPropietarioConductorByInternamiento(codigo));
		} catch (MotInternamientoDaoException | MotActaConformidadDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return internamiento;
	}
	

	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(Long codigo){
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("@CODIGO", codigo);
		mav=new ModelAndView("reportInternamiento", parameters);
		return mav;
	}
}
