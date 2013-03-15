package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.exceptions.MotMarcaDaoException;
import com.munichosica.myapp.factory.MotMarcaDaoFactory;

@Controller
@RequestMapping("/Marca")
public class MotMarcaController {

		@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
		public @ResponseBody List<MotMarca> listar(HttpServletRequest request,
				@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
			
			List<MotMarca> list=null;
			try {
				list=MotMarcaDaoFactory.create().findByCriterio(criterio, texto);
			} catch (MotMarcaDaoException e) {
			
			}
			return list;
		}
		
		@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
		public @ResponseBody MotMarca obtener(@RequestParam("codigo") Long codigo){

			MotMarca marca = null;
			
			try {
				marca=MotMarcaDaoFactory.create().findByIdMarca(codigo);
			} catch (MotMarcaDaoException e) {
				
			}
			return marca;
		}
		
		@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
		public String agregar(HttpServletRequest request,MotMarca marca){
			try {
				MotMarcaDaoFactory.create().insert(marca);
			} catch (MotMarcaDaoException e) {

			}
			return "Success";
		}
		
		@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
		public String eliminar(@RequestParam("codigo") Integer codigo){
			try {
				MotMarca marca=new MotMarca();
				marca.setMarcodigoI(codigo);
				MotMarcaDaoFactory.create().delete(codigo);
			} catch (MotMarcaDaoException e) {

			}
			return "Success";
		}
}
