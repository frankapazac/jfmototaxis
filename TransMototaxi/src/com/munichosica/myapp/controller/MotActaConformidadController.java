package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotActaConformidad;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotActaConformidadDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.factory.MotActaConformidadDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/ActaConformidad")
public class MotActaConformidadController {
	Logger logger=Logger.getLogger(MotActaConformidadController.class);
	
	@RequestMapping(value="Insertar.htm", method=RequestMethod.POST)
	public @ResponseBody String insertar(HttpServletRequest request,MotActaConformidad actaConformidad){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "ERROR";
		}
		try {
			MotActaConformidadDaoFactory.create().insertar(actaConformidad);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_ACTA_CONFORMIDAD", actaConformidad.getAcocodigoD(),"SP_MOT_INS_ACTA_CONFORMIDAD",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotActaConformidadDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView imprimir(Long intCodigo, Long perCodigo){
		ModelAndView mav=null;
		try{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("PERCODIGO_D", perCodigo);
			map.put("INTCODIGO_D", intCodigo);
			mav=new ModelAndView("reportActaConformidad", map);
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
		}
		return mav;
	}
}
