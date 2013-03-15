package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.factory.MotTipoDocumentoDaoFactory;

@Controller
@RequestMapping("/TipoDocumento")
public class MotTipoDocumentoController {
	
	protected static final Logger logger = Logger.getLogger(MotTipoDocumentoController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotTipoDocumento> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Operativos/Listar.htm");
		
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		
		List<MotTipoDocumento> list=null;
		try {
			list=MotTipoDocumentoDaoFactory.create().findByCriterio(criterio,texto);
			logger.info("MotOperFiscalizadorDaoFactory.create().findByCriterio(criterio, texto); Complete");
		} catch (MotTipoDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotTipoDocumento obtener(@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a Operativo/Obtener.htm");
		MotTipoDocumento tipoDocumento = null;
		
		try {
			tipoDocumento=MotTipoDocumentoDaoFactory.create().findByIdTipoDoc(codigo);
			logger.info("MotTipoDocumentoDaoFactory.create().findById(1);Completed");
		} catch (MotOperativoDaoException e) {
			logger.error(e.getMessage());
		}
		return tipoDocumento;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotTipoDocumento tipoDocumento){
		try {
			MotTipoDocumentoDaoFactory.create().insert(tipoDocumento);
		} catch (MotTipoDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Integer codigo){
		try {
			MotTipoDocumento tipoDoc=new MotTipoDocumento();
			tipoDoc.setMtdcodigoI(codigo);
			MotTipoDocumentoDaoFactory.create().delete(codigo);
		} catch (MotTipoDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
}
