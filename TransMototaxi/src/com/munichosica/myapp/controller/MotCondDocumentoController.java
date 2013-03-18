package com.munichosica.myapp.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.factory.MotCondDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotConductorDaoFactory;
import com.munichosica.myapp.jdbc.ResourceManager;
import com.munichosica.myapp.util.FileUtil;

@Controller
@RequestMapping("/Documentos")
public class MotCondDocumentoController {

	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(HttpServletRequest request,String FECHA1 ,String FECHA2){
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("FECHA1", FECHA1);
		parameters.put("FECHA2", FECHA2);
		parameters.put("EMPCODIGO_D", rol.getUsuario().getEmpresa().getEmpcodigoD());

		mav=new ModelAndView("reportInformePapeletasxFechas", parameters);
			
		return mav;
	}

	@RequestMapping(value="ImprimirCondAltaRotacionPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(){
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
			mav=new ModelAndView("reportInformeConductorAltaRotacion", parameters);
			
		return mav;
	}

	
	
	
	
}
