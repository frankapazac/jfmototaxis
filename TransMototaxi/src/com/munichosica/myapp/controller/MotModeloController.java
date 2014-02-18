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

import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotModeloDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Modelo")
public class MotModeloController {
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotModelo> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		
		List<MotModelo> list=null;
		try {
			list=MotModeloDaoFactory.create().findByCriterio(criterio, texto);
		} catch (MotModeloDaoException e) {
		
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotModelo obtener(@RequestParam("codigo") Long codigo){

		MotModelo modelo = null;
		
		try {
			System.out.println("entro");
			modelo=MotModeloDaoFactory.create().findByIdModelo(codigo);
			System.out.println("salio");
		} catch (MotModeloDaoException e) {
			
		}
		return modelo;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotModelo modelo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotModeloDaoFactory.create().insert(modelo);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_MODELO", Long.parseLong(String.valueOf(modelo.getModcodigo_D())),"SP_MOT_INS_MOT_MODELO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotModeloDaoException | NumberFormatException | MotAuditoriaDaoException e) {

		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(HttpServletRequest request,@RequestParam("codigo") Integer codigo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotModelo modelo=new MotModelo();
			modelo.setModcodigo_D(codigo);
			MotModeloDaoFactory.create().delete(codigo);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_MODELO", Long.parseLong(String.valueOf(codigo)),"SP_MOT_DEL_MODELO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotModeloDaoException | NumberFormatException | MotAuditoriaDaoException e) {

		}
		return "Success";
	}
}
