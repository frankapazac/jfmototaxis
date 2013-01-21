package com.munichosica.myapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;
import com.munichosica.myapp.factory.MotUbigeoDaoFactory;

@Controller
@RequestMapping("/Ubigeo")
public class UbigeoController {
	
	Logger logger=Logger.getLogger(UbigeoController.class);

	@RequestMapping(value="Provincia.htm",method=RequestMethod.POST)
	public @ResponseBody List<MotUbigeo> BuscarProvincias(@RequestParam("idubigeo") String idubigeo){
		logger.info("Entro a Ubigeo/Provincia.htm");
		List<MotUbigeo> list=null;
		try {
			list = MotUbigeoDaoFactory.create().findProvinciaByPrimaryKey(idubigeo);
		} catch (MotUbigeoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Distrito.htm",method=RequestMethod.POST)
	public @ResponseBody List<MotUbigeo> BuscarDistritos(@RequestParam("idubigeo") String idubigeo){
		logger.info("Entro a Ubigeo/Distrito.htm");
		List<MotUbigeo> list=null;
		try {
			list = MotUbigeoDaoFactory.create().findDistritoByPrimaryKey(idubigeo);
		} catch (MotUbigeoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
