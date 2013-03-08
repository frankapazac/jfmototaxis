package com.munichosica.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/MaestroTranporte")
public class MaestroTranporteController {
	
	@RequestMapping(value="MaestroInfraccion.htm",method=RequestMethod.GET)
	public String maestroInfraccion(){
		System.out.println("Ingreso a MaestroInfraccion.htm");
		return "mantInfracciones";
	}
}
