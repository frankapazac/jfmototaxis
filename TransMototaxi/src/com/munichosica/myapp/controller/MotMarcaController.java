package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotMarcaDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Marca")
public class MotMarcaController {
		
		private Logger logger=Logger.getLogger(MotMarcaController.class);
		
		@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
		public @ResponseBody List<MotMarca> listar(HttpServletRequest request,
				@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
			List<MotMarca> list=null;
			try {
				list=MotMarcaDaoFactory.create().findByCriterio(criterio, texto);
			} catch (MotMarcaDaoException e) {
				logger.error(e.getMessage(), e);
			}
			return list;
		}
		
		@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
		public @ResponseBody MotMarca obtener(@RequestParam("codigo") Long codigo){
			MotMarca marca = null;
			try {
				marca=MotMarcaDaoFactory.create().findByIdMarca(codigo);
			} catch (MotMarcaDaoException e) {
				logger.error(e.getMessage(), e);
			}
			return marca;
		}
		
		@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
		public String agregar(HttpServletRequest request,MotMarca marca){
			HttpSession session=request.getSession(true);
			Usuario usuario=(Usuario) session.getAttribute("USUARIO");
			if(usuario==null){
				SecurityContextHolder.getContext().setAuthentication(null);
				return "Error";
			}
			try {
				MotMarcaDaoFactory.create().insert(marca);
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_MARCA", Long.parseLong(String.valueOf(marca.getMarcodigoI())),"SP_MOT_INS_MOT_MARCA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			} catch (MotMarcaDaoException | NumberFormatException | MotAuditoriaDaoException e) {
				logger.error(e.getMessage(), e);
				return "Error";
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
				MotMarca marca=new MotMarca();
				marca.setMarcodigoI(codigo);
				MotMarcaDaoFactory.create().delete(codigo);
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_MARCA", Long.parseLong(String.valueOf(codigo)),"SP_MOT_DEL_MARCA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			} catch (MotMarcaDaoException | NumberFormatException | MotAuditoriaDaoException e) {
				logger.error(e.getMessage(), e);
			}
			return "Success";
		}
}
