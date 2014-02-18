package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.exceptions.MotZonaDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotTipoMedidaDaoFactory;
import com.munichosica.myapp.factory.MotZonaDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Zona")
public class MotZonaController {
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotZona> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		
		List<MotZona> list=null;
		try {
			list=MotZonaDaoFactory.create().findByCriterio(criterio,texto);
		} catch (MotZonaDaoException e) {
		
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotZona obtener(@RequestParam("codigo") Long codigo){

		MotZona zona = null;
		
		try {
			zona=MotZonaDaoFactory.create().findByPrimaryKey(codigo);
		} catch (MotZonaDaoException e) {
			
		}
		return zona;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotZona zona){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotZonaDaoFactory.create().insert(zona);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_ZONA", zona.getZoncodigo_I(),"SP_MOT_INS_ZONA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotZonaDaoException | MotAuditoriaDaoException e) {

		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotZona zona=new MotZona();
			zona.setZoncodigo_I(codigo);
			MotZonaDaoFactory.create().delete(codigo);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_ZONA", codigo,"SP_MOT_DEL_MOT_ZONA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotZonaDaoException | MotAuditoriaDaoException e) {

		}
		return "Success";
	}
	
}
