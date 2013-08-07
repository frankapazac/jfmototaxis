package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;
import com.munichosica.myapp.factory.MotEmpadronamientoDaoFactory;
import com.munichosica.myapp.util.UserSecurity;

public class AdjuntarArchivoController {
	
	protected final Logger logger=Logger.getLogger(AdjuntarArchivoController.class);
	
	
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
}
