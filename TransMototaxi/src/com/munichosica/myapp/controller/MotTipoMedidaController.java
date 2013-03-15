package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.factory.MotTipoMedidaDaoFactory;

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
		try {
			MotTipoMedidaDaoFactory.create().insert(medida);
		} catch (MotTipoMedidaDaoException e) {

		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Integer codigo){
		try {
			MotTipoMedida medida=new MotTipoMedida();
			medida.setTmecodigoI(codigo);
			MotTipoMedidaDaoFactory.create().delete(codigo);
		} catch (MotTipoMedidaDaoException e) {

		}
		return "Success";
	}
}
