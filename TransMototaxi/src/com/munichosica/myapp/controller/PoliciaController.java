package com.munichosica.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotPolicia;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotPoliciaDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotPoliciaDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Policia")
public class PoliciaController {

	protected final Logger logger= Logger.getLogger(PoliciaController.class);
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody MotPolicia procesar(HttpServletRequest request ,MotPolicia policia){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			logger.info("Ingreso a Policia/Procesar.htm");
			MotPoliciaDaoFactory.create().procesar(policia);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_POLICIA", Long.parseLong(policia.getPolcodigoI().toString()),"SP_MOT_INS_POLICIA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotPoliciaDaoException | NumberFormatException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return policia;
	}
}
