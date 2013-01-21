package com.munichosica.myapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;
import com.munichosica.myapp.factory.MotEmpadronamientoDaoFactory;

@Controller
@RequestMapping("/Mototaxi")
public class MototaxiController {
	protected final Logger logger=Logger.getLogger(MototaxiController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpadronamiento> listar(@RequestParam("criterio") String criterio,
											@RequestParam("texto") String texto){
		logger.info("Ingreso a Mototaxi/Listar.htm");
		List<MotEmpadronamiento> list=null;
		try {
			list=MotEmpadronamientoDaoFactory.create().findByCriterio(criterio, texto,(long) 1);
			logger.info("MotParaderoDaoFactory.create().findByCriterio(criterio, texto,(long) 1);Completed");
		} catch (MotEmpadronamientoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
