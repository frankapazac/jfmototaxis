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
import com.munichosica.myapp.factory.MotInspectorDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;
import com.munichosica.myapp.util.UTFEncodingUtil;

@Controller
@RequestMapping("/Operativos")
public class OperativoController {

	protected static final Logger logger = Logger.getLogger( OperativoController.class );
	
	@RequestMapping(value="ListaInspectoresNotIn.htm",method=RequestMethod.POST)
	public @ResponseBody List<MotInspector> listarInspectoresNotIn(HttpServletRequest request,@RequestParam("codigo") int codigo){
		logger.info("Ingreso a Operativos/ListaInspectoresNotIn.htm");
		HttpSession session=request.getSession(true);
		List<MotInspector> listarInspectoresNotIn = null;
		try {
			listarInspectoresNotIn = MotInspectorDaoFactory.create().findByNotInCodInspector(codigo);
			logger.info("MotInspectorDaoImpl.create().findByNotInCodInspector(codigo); Complete");
		} catch (MotInspectorDaoException e) {
			e.printStackTrace();
		}
		return listarInspectoresNotIn;
	}
	
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
	
	/*
	@RequestMapping(value="ListaInspectores.htm",method=RequestMethod.POST)
	public @ResponseBody String listaOperativos(@RequestBody MotInspectorList inspectorList){
		System.out.println("ENTRO A ListaInspectores.htm");
		System.out.println(inspectorList.getOperativo().getOpetituloV());
		for(MotInspector inspector:inspectorList.getInspectores()){
			System.out.println("*  "+inspector.getInscodigoI());
		}
		return "EXITOSO";
	}*/
	
	/*
	 	public @ResponseBody List<MotOperFiscalizador> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto)
	 * */

	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody String procesar 
	(HttpServletRequest request , @RequestBody MotInspectorList inspectorList){
		
		logger.info("Ingreso a GuardarOperativo/Procesar.htm");
		
		HttpSession session= request.getSession(true);
		Rol rol = (Rol) session.getAttribute("ROL");
		
		try {
			MotOperativoDaoFactory.create().insert(inspectorList.getOperativo());
			for(MotInspector inspector:inspectorList.getInspectores()){
				MotOperFiscalizador operFiscalizador=new MotOperFiscalizador();
				operFiscalizador.setOperativo(inspectorList.getOperativo());
				operFiscalizador.setFiscalizador(inspector);
				MotOperFiscalizadorDaoFactory.create().insert(operFiscalizador);
			}
			System.out.println("actualizo correctamente el detalle operativo fiscalizador");
			
		} catch (MotOperativoDaoException | MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage());
		}
			
		return "Success";
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
	

	
	@RequestMapping(value="ObtenerInspector.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotOperFiscalizador> obtenerInspector(HttpServletRequest request,@RequestParam("codigo") int codigo){
		logger.info("Ingreso a obtenerInspector/ObtenerInspector.htm");

		//MotInspector inspector= null;
		List<MotOperFiscalizador> listarInspectoresxOperativo = null;
		try {
			listarInspectoresxOperativo=MotOperFiscalizadorDaoFactory.create().findInspectorporOperativo(codigo);
			logger.info("MotOperFiscalizadorDaoFactory.create().findInspectorporOperativo(codigo);Completed");
		} catch (MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage());
		}
		return listarInspectoresxOperativo;
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
	
	@RequestMapping(value="ActualizarEstadoIns.htm", method=RequestMethod.POST)
	public String actualizaEstado(Long codigo,Integer codins, String lado){

		System.out.println(codigo+" "+codins+" "+lado);
		try {
			logger.info("actualizaEstado/ActualizarEstadoIns.htm");
			MotOperFiscalizador operFiscalizador= new MotOperFiscalizador(); 
			operFiscalizador.getOperativo().setOpecodigoD(codigo);
			operFiscalizador.getFiscalizador().setInscodigoI(codins);
			operFiscalizador.getFiscalizador().setLado(lado);
			MotOperFiscalizadorDaoFactory.create().actualizaEstado(operFiscalizador);
			logger.info("MotOperFiscalizadorDaoFactory.create().actualizaEstado(operFiscalizador); Complete");
		} catch (MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
		
}
