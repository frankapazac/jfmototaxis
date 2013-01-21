package com.munichosica.myapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;
import com.munichosica.myapp.factory.MotEmpConductorDaoFactory;
import com.munichosica.myapp.factory.MotUnidConductorDaoFactory;

@Controller
@RequestMapping("/Conductores")
public class ConductorController {

	protected final Logger logger=Logger.getLogger(ConductorController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpConductor> listar
	(@RequestParam("criterio") String criterio,@RequestParam("texto") String texto){
		
		logger.info("Ingreso a Conductores/Listar.htm");
		List<MotEmpConductor> list = null;
		
		try {
			list = MotEmpConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1);
			logger.info("MotEmpConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1);Completed");
		} catch (MotEmpConductorDaoException e) {
			logger.error(e.getMessage());
		}
		return list;

	}
	
	@RequestMapping(value="ListarMotosAsignadas.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotUnidConductor> listarMotosAsignadas
	(@RequestParam("criterio") String criterio,@RequestParam("texto") String texto,@RequestParam("codCondu") Long conductor){
		
		logger.info("Ingreso a Conductores/ListarMotosAsignadas.htm");
		List<MotUnidConductor> list = null;
		
		try {
			list = MotUnidConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1, conductor);
			logger.info("MotUnidConductorDaoFactory.create().findByCriterio(criterio, texto, (long) 1);Completed");
		} catch (MotUnidConductorDaoException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
			
		return list;
	}
	
	@RequestMapping(value="CesarConductor.htm", method=RequestMethod.POST)
	public String CesarConductor(MotUnidConductor unidadConductor){
		logger.info("Ingreso a Conductores/CesarConductor.htm");
		System.out.println("CODIGO: "+unidadConductor.getUcocodigoD());
		try {
			MotUnidConductorDaoFactory.create().insert(unidadConductor);
			logger.info("MotUnidConductorDaoFactory.create().insert(unidadConductor);Completed");
		} catch (MotUnidConductorDaoException e) {
			logger.error(e.getMessage());
		}
		
		return "Success";
	}
	
	
	
	
}
