package com.munichosica.myapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;
import com.munichosica.myapp.factory.MotPapeletaDaoFactory;

@Controller
@RequestMapping("/Internamientos")
public class InternamientoController {
	
	protected static final Logger logger=Logger.getLogger(InternamientoController.class);
	
	@RequestMapping(value="BuscarPorPapeleta.htm", method=RequestMethod.POST)
	public @ResponseBody MotPapeleta buscarPorPapeleta(String papnumero){
		MotPapeleta papeleta=null;
		try {
			papeleta = MotPapeletaDaoFactory.create().findInternamientoByPapeleta(papnumero);
		} catch (MotPapeletaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return papeleta;
	}
}
