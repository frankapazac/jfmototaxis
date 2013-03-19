package com.munichosica.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.util.DateUtil;

@Controller
@RequestMapping("/Util")
public class UtilController {
	
	@RequestMapping(value="obtenerFecha.htm", method=RequestMethod.GET)
	public @ResponseBody String obtenerFecha(){
		return DateUtil.getFechaActual();
	}
	
	@RequestMapping(value="obtenerFechaHora.htm", method=RequestMethod.GET)
	public @ResponseBody String obtenerFechaHora(){
		return DateUtil.getFechaHoraActual();
	}
	
	@RequestMapping(value="obtenerHora.htm", method=RequestMethod.GET)
	public @ResponseBody String obtenerHora(){
		return DateUtil.getHoraActual();
	}
}
