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
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotTipoMedidaDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/TipoMedida")
public class MotTipoMedidaController {

	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotTipoMedida> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		
		List<MotTipoMedida> list=null;
		try {
			list=MotTipoMedidaDaoFactory.create().findByCriterio(criterio,texto);
		} catch (MotTipoMedidaDaoException e) {
		
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotTipoMedida obtener(@RequestParam("codigo") Long codigo){

		MotTipoMedida medida = null;
		
		try {
			medida=MotTipoMedidaDaoFactory.create().findByIdTipoMed(codigo);
		} catch (MotTipoMedidaDaoException e) {
			
		}
		return medida;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotTipoMedida medida){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotTipoMedidaDaoFactory.create().insert(medida);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_TIPO_MEDIDA", Long.parseLong(String.valueOf(medida.getTmecodigoI())),"SP_MOT_INS_TIPO_MEDIDA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotTipoMedidaDaoException | NumberFormatException | MotAuditoriaDaoException e) {

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
			MotTipoMedida medida=new MotTipoMedida();
			medida.setTmecodigoI(codigo);
			MotTipoMedidaDaoFactory.create().delete(codigo);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_TIPO_MEDIDA", Long.parseLong(String.valueOf(codigo)),"SP_MOT_DEL_TIPO_MEDIDA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotTipoMedidaDaoException | NumberFormatException | MotAuditoriaDaoException e) {

		}
		return "Success";
	}
}
