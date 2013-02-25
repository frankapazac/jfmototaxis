package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.lookup.MostSpecificExceptionMethodBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotCondDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotEmpConductorDaoFactory;
import com.munichosica.myapp.factory.MotEmprAsociadoDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;
import com.munichosica.myapp.util.UTFEncodingUtil;

@Controller
@RequestMapping("/Operativos")
public class OperativoController {

	protected static final Logger logger = Logger.getLogger( OperativoController.class );
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotOperFiscalizador> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Operativos/Listar.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		List<MotOperFiscalizador> list=null;
		try {
			list=MotOperFiscalizadorDaoFactory.create().findByCriterio(criterio,texto);
			logger.info("MotOperFiscalizadorDaoFactory.create().findByCriterio(criterio, texto); Complete");
		} catch (MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="ListaInspectores.htm",method=RequestMethod.POST)
	public @ResponseBody String listaOperativos(@RequestBody MotInspectorList inspectores){
		System.out.println("ENTRO A ListaInspectores.htm");
		for(MotInspector inspector:inspectores){
			System.out.println("*  "+inspector.getInscodigoI());
		}
		return "EXITOSO";
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody MotOperativo procesar 
	(HttpServletRequest request , MotOperativo operativo,Model model){
		
		logger.info("Ingreso a GuardarOperativo/Procesar.htm");
		
		HttpSession session= request.getSession(true);
		Rol rol = (Rol) session.getAttribute("ROL");
		
		try {
			MotOperativoDaoFactory.create().insert(operativo);
		} catch (MotOperativoDaoException e) {
			logger.error(e.getMessage());
		}
			
		return operativo;
	}
	
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotOperativo obtener(@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a Operativo/Obtener.htm");
		MotOperativo operativo= null;
		
		try {
			operativo=MotOperativoDaoFactory.create().findByIdOperativo(codigo);
			logger.info("MotOperativoDaoFactory.create().findByParadero(1);Completed");
		} catch (MotOperativoDaoException e) {
			logger.error(e.getMessage());
		}
		return operativo;
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(@RequestParam("codigo") Long codigo){
		try {
			logger.info("Eliminar Operativo/Eliminar.htm");
			MotOperativo operativo=new MotOperativo();
			operativo.setOpecodigoD(codigo);
			MotOperativoDaoFactory.create().delete(operativo);
			logger.info("MotOperativoDaoFactory.create().delete(paradero); Complete");
		} catch (MotOperativoDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}	
}