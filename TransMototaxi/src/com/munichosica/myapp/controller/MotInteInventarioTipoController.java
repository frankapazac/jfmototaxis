package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.factory.MotInteInventarioTipoDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;

@Controller
@RequestMapping("/Inventario2")
public class MotInteInventarioTipoController {
	
	protected static final Logger logger = Logger.getLogger(MotInteInventarioTipoController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotInteInventarioTipo> listar(HttpServletRequest request,
			String tipo,String criterio,String texto,Integer codigo){
			
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		
		List<MotInteInventarioTipo> list=null;
		try {
			list= MotInteInventarioTipoDaoFactory.create().findByCriterio(tipo, criterio, texto, codigo);
		} catch (MotInteInventarioTipoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.POST)
	public @ResponseBody MotInteInventarioTipo obtener(@RequestParam("codigo") Integer codigo){
	
		MotInteInventarioTipo inventario= null;
		
		try {
			inventario=MotInteInventarioTipoDaoFactory.create().findByid("BP", "", "", codigo);
		} catch (MotInteInventarioTipoDaoException e) {
			logger.error(e.getMessage());
		}
		return inventario;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotInteInventarioTipo inventario){
		try {
			MotInteInventarioTipoDaoFactory.create().insert(inventario);
		} catch (MotInteInventarioTipoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Integer codigo){
		try {
			//MotInteInventarioTipo inventario = new MotInteInventarioTipo();
			//inventario.setBitcodigoI(codigo);
			MotInteInventarioTipoDaoFactory.create().delete(codigo);
		} catch (MotInteInventarioTipoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
}
