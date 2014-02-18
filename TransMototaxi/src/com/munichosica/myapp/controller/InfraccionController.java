package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotInfrMedidaDaoFactory;
import com.munichosica.myapp.factory.MotInfraccionDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.util.IpUtils;
import com.munichosica.myapp.util.UserSecurity;

@Controller
@RequestMapping("/Infracciones")
public class InfraccionController {

	protected static final Logger logger = Logger.getLogger( InfraccionController.class );
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotInfraccion> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a infraccionesss/Listar.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		
		List<MotInfraccion> list=null;
		try {
			list=MotInfraccionDaoFactory.create().findByCriterio(criterio,texto);
			logger.info("MotOperFiscalizadorDaoFactory.create().findByCriterio(criterio, texto); Complete");
		} catch (MotInfraccionDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody String procesar
	(HttpServletRequest request , @RequestBody MotTipoMedidasList tipoMedidasList){	
		logger.info("Ingreso a GuardarInfracciones/Procesar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		
		try {
			MotInfraccionDaoFactory.create().procesar(tipoMedidasList.getInfraccion());
			System.out.println("MOT_INFRACCION");
			System.out.println(IpUtils.getIpFromRequest(request));
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_INFRACCION",tipoMedidasList.getInfraccion().getInfcodigoD(),"SP_MOT_INS_AUDITORIA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			for(MotTipoMedida tipoMedida:tipoMedidasList.getTipoMedidas()){	
				MotInfrMedida infraMedida = new MotInfrMedida();
				infraMedida.setTipoMedida(tipoMedida);
				infraMedida.setInfraccion(tipoMedidasList.getInfraccion());
				MotInfrMedidaDaoFactory.create().insert(infraMedida);
				
			}
		} catch (MotInfrMedidaDaoException | MotInfraccionDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	/*
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody String procesar (HttpServletRequest request , MotInfraccion infraccion){
		
		logger.info("Ingreso a GuardarInfraccion/Procesar.htm");	
		
		try {
			MotInfraccionDaoFactory.create().procesar(infraccion);
		} catch (MotInfraccionDaoException e) {
			logger.error(e.getMessage());
		}
			
		return "Success";
	}*/
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotInfraccion obtener (@RequestParam("codigo") Long codigo){
		
		logger.info("Ingreso a ObtenerInfraccion/Obtener.htm");	
		MotInfraccion infraccion = null;
		try {

			infraccion=MotInfraccionDaoFactory.create().findByIdInfraccion(codigo);
			System.out.println("salio obtener infraccion");
		} catch (MotInfraccionDaoException e) {
			logger.error(e.getMessage());
		}
			
		return infraccion;
	}
	
	@RequestMapping(value="Eliminar.htm", method=RequestMethod.GET)
	public @ResponseBody String eliminar (HttpServletRequest request,@RequestParam("codigo") Long codigo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			logger.info("Ingreso a EliminarInfraccion/Eliminar.htm");
			MotInfraccion infraccion = new MotInfraccion();	
			infraccion.setInfcodigoD(codigo);
			MotInfraccionDaoFactory.create().delete(infraccion);
			logger.info("MotInfraccionDaoFactory.create().delete(infraccion); Complete");
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_INFRACCION",codigo,"SP_MOT_UPD_ESTADO_INFRACCION",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotInfraccionDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
			
		return "Success";
	}
	
	@RequestMapping(value="ActualizarEstadoInFMedida.htm", method=RequestMethod.POST)
	public String actualizaEstado(Integer codMedida, Long codInfraccion, String lado){

		System.out.println(codMedida+" "+codInfraccion+" "+lado);
		try {
			logger.info("ActualizarEstadoInFMedida/ActualizarEstadoInFMedida.htm");
			MotInfrMedida infrMedida = new MotInfrMedida();
			
			infrMedida.getTipoMedida().setTmecodigoI(codMedida);
			infrMedida.getInfraccion().setInfcodigoD(codInfraccion);
			infrMedida.getTipoMedida().setLado(lado);
			MotInfrMedidaDaoFactory.create().actualizaEstado(infrMedida);
			
			logger.info("MotInfrMedidaDaoFactory.create().actualizaEstado(infrMedida); Complete");
		} catch (MotInfrMedidaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="ObtenerMedidasxInfraccion.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotInfrMedida> ObtenerMedidasxInfraccion(HttpServletRequest request,
			@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a ObtenerMedidasxInfraccion/ObtenerMedidasxInfraccion.htm");

		List<MotInfrMedida> listarMedidasInfraccion = null;
		try {
			listarMedidasInfraccion=MotInfrMedidaDaoFactory.create().findMedidasxInfraccion(codigo);
			logger.info("MotInfrMedidaDaoFactory.create().findMedidasxInfraccion(codigo);Completed");
		} catch (MotInfrMedidaDaoException e) {
			logger.error(e.getMessage());
		}
		return listarMedidasInfraccion;
	}
}
