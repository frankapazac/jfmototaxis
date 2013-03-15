package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.exceptions.MotModeloDaoException;
import com.munichosica.myapp.factory.MotModeloDaoFactory;

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
		try {
			MotModeloDaoFactory.create().insert(modelo);
		} catch (MotModeloDaoException e) {

		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Integer codigo){
		try {
			MotModelo modelo=new MotModelo();
			modelo.setModcodigo_D(codigo);
			MotModeloDaoFactory.create().delete(codigo);
		} catch (MotModeloDaoException e) {

		}
		return "Success";
	}
}
