package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;

@Controller
@RequestMapping("/Documentos")
public class MotCondDocumentoController {

	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(HttpServletRequest request,String FECHA1 ,String FECHA2){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		ModelAndView mav=null;
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("FECHA1", FECHA1);
		parameters.put("FECHA2", FECHA2);
		parameters.put("EMPCODIGO_D", usuario.getEmpresa().getEmpcodigoD());

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
