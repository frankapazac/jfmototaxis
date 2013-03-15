package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotInteInventario;
import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.exceptions.MotBoletaInternamientoDaoException;
import com.munichosica.myapp.exceptions.MotInteInventarioDaoException;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.factory.MotBoletaInternamientoDaoFactory;
import com.munichosica.myapp.factory.MotInteInventarioDaoFactory;
import com.munichosica.myapp.factory.MotInternamientoDaoFactory;
import com.munichosica.myapp.factory.MotPapeletaDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.ReportsDaoFactory;

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
	
	@RequestMapping(value="BuscarUnidadPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa BuscarUnidadPorCodigo(Long codigo){
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			unidadEmpresa=MotUnidadEmpresaDaoFactory.create().findPmoCodigo(codigo);
		} catch (MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return unidadEmpresa;
	}
	
	@RequestMapping(value="Insertar.htm", method=RequestMethod.POST)
	public @ResponseBody String insertar(@RequestBody MotInternamiento internamiento){
		logger.info("Ingreso a Internamientos/Insertar.htm");
		try {
			MotBoletaInternamientoDaoFactory.create().procesar(internamiento.getBoletaInternamiento());
			MotInternamientoDaoFactory.create().procesar(internamiento);
			for(MotInteInventario inventario:internamiento.getInventarios()){
				inventario.setInternamiento(internamiento);
				MotInteInventarioDaoFactory.create().insertar(inventario);
			}
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException | MotBoletaInternamientoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return String.valueOf(internamiento.getIntcodigoD());
	}
	
	@RequestMapping(value="Modificar.htm", method=RequestMethod.POST)
	public @ResponseBody String modificar(@RequestBody MotInternamiento internamiento){
		logger.info("Ingreso a Internamientos/Modificar.htm");
		try {
			MotBoletaInternamientoDaoFactory.create().procesar(internamiento.getBoletaInternamiento());
			MotInternamientoDaoFactory.create().procesar(internamiento);
			for(MotInteInventario inventario:internamiento.getInventarios()){
				inventario.setInternamiento(internamiento);
				MotInteInventarioDaoFactory.create().modificar(inventario);
			}
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException | MotBoletaInternamientoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return String.valueOf(internamiento.getIntcodigoD());
	}
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotInternamiento> listar(String criterio, String texto){
		List<MotInternamiento> list=null;
		try {
			list=MotInternamientoDaoFactory.create().listarPorCriterio(criterio,texto);
		} catch (MotInternamientoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.POST)
	public @ResponseBody MotInternamiento obtener(Long codigo){
		MotInternamiento internamiento=null;
		try {
			internamiento=MotInternamientoDaoFactory.create().get(codigo);
			internamiento.setInventarios(MotInteInventarioDaoFactory.create().findByInternamiento(codigo));
		} catch (MotInternamientoDaoException | MotInteInventarioDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return internamiento;
	}
	

	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(Long codigo){
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("@CODIGO", codigo);
		mav=new ModelAndView("reportInternamiento", parameters);
		return mav;
	}
}
