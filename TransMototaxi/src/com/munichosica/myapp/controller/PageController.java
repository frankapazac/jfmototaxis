package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.dto.Usuempr;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;
import com.munichosica.myapp.factory.MotTipoDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUbigeoDaoFactory;
import com.munichosica.myapp.util.UserSecurity;

@Controller
@RequestMapping("/")
public class PageController {
	
	Logger logger=Logger.getLogger(PageController.class);
	
	@RequestMapping(value="Inicio.htm",method=RequestMethod.GET)
	public String inicio(HttpServletRequest request){
		logger.info("Ingreso a Inicio.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		return "tilesBienvenido";
	}

	@RequestMapping(value="Asociados.htm",method=RequestMethod.GET)
	public String asociados(HttpServletRequest request, Model model){
		logger.info("Ingreso a Asociados.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		
		List<MotUbigeo> departamentos=null;
		List<MotTipoDocumento> documentos=null;
		try {
			departamentos = MotUbigeoDaoFactory.create().findAllDepartamentos();
			documentos=MotTipoDocumentoDaoFactory.create().findByTable("ADO");
			model.addAttribute("departamentos", departamentos);
			model.addAttribute("documentos", documentos);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "tilesAsociados";
	}

	@RequestMapping(value="Conductores.htm",method=RequestMethod.GET)
	public String conductores(HttpServletRequest request){
		logger.info("Ingreso a Conductores.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		return "tilesConductores";
	}

	@RequestMapping(value="Mototaxis.htm",method=RequestMethod.GET)
	public String mototaxis(HttpServletRequest request){
		logger.info("Ingreso a Mototaxis.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		return "tilesMototaxis";
	}

	@RequestMapping(value="Paraderos.htm",method=RequestMethod.GET)
	public String paraderos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Paraderos.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		try {
			List<MotParadero> lista=MotParaderoDaoFactory.create().findZonaByEmpresa(
					usuempr.getEmpresa().getEmpcodigoD());
			model.addAttribute("paraderos",lista);
		} catch (MotParaderoDaoException e) {
			logger.error(e.getMessage());
		}
		return "tilesParaderos";
	}

	@RequestMapping(value="Documentacion.htm",method=RequestMethod.GET)
	public String documentacion(HttpServletRequest request){
		logger.info("Ingreso a Documentacion.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		return "tilesDocumentacion";
	}

	@RequestMapping(value="Configuracion.htm",method=RequestMethod.GET)
	public String configuracion(HttpServletRequest request){
		logger.info("Ingreso a Configuracion.htm");
		HttpSession session=request.getSession(true);
		Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
		if(usuempr==null){
			System.out.println("INICIO");
			usuempr=new UserSecurity().getUser();
			session.setAttribute("USUARIO", usuempr);
		}
		return "tilesConfiguracion";
	}
}
