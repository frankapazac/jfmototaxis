package com.munichosica.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;
import com.munichosica.myapp.factory.MotPersonaDaoFactory;

@Controller
@RequestMapping("/Persona")
public class MotPersonaController {

	@RequestMapping(value="ObtenerPorDni.htm", method=RequestMethod.GET)
	public @ResponseBody MotPersona obtenerPorDni(@RequestParam("dni") String dni){
		MotPersona persona=null;
		try {
			persona=MotPersonaDaoFactory.create().obtenerPorDni(dni);
		} catch (MotPersonaDaoException e) {
			e.printStackTrace();
		}
		return persona;
	}
}
