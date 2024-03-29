package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotInspectorDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Operativos")
public class OperativoController {

	protected static final Logger logger = Logger.getLogger( OperativoController.class );
	
	@RequestMapping(value="ListaInspectoresNotIn.htm",method=RequestMethod.POST)
	public @ResponseBody List<MotInspector> listarInspectoresNotIn(@RequestParam("codigo") int codigo){
		logger.info("Ingreso a Operativos/ListaInspectoresNotIn.htm");
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
	public @ResponseBody List<MotOperFiscalizador> listar(@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Operativos/Listar.htm");
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
	(HttpServletRequest request ,@RequestBody MotInspectorList inspectorList){
		logger.info("Ingreso a GuardarOperativo/Procesar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
			
		try {
			MotOperativoDaoFactory.create().insert(inspectorList.getOperativo());
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_OPERATIVO", inspectorList.getOperativo().getOpecodigoD(),"SP_MOT_INS_OPERATIVO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			for(MotInspector inspector:inspectorList.getInspectores()){
				MotOperFiscalizador operFiscalizador=new MotOperFiscalizador();
				operFiscalizador.setOperativo(inspectorList.getOperativo());
				operFiscalizador.setFiscalizador(inspector);
				MotOperFiscalizadorDaoFactory.create().insert(operFiscalizador);
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_OPER_INSPECTOR", operFiscalizador.getOperativo().getOpecodigoD(),"SP_MOT_INS_OPER_INSPECTOR",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			}
			System.out.println("actualizo correctamente el detalle operativo fiscalizador");
			
		} catch (MotOperativoDaoException | MotOperFiscalizadorDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
			
		return String.valueOf(inspectorList.getOperativo().getOpecodigoD());
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
	public String eliminar(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		logger.info("Eliminar Operativo/Eliminar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotOperativo operativo=new MotOperativo();
			operativo.setOpecodigoD(codigo);
			MotOperativoDaoFactory.create().delete(operativo);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_OPERATIVO", operativo.getOpecodigoD(),"SP_MOT_UPD_ESTADO_MOT_OPERATIVO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("MotOperativoDaoFactory.create().delete(paradero); Complete");
		} catch (MotOperativoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="ActualizarEstadoIns.htm", method=RequestMethod.POST)
	public String actualizaEstado(HttpServletRequest request,Long codigo,Integer codins, String lado){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			logger.info("actualizaEstado/ActualizarEstadoIns.htm");
			MotOperFiscalizador operFiscalizador= new MotOperFiscalizador(); 
			operFiscalizador.getOperativo().setOpecodigoD(codigo);
			operFiscalizador.getFiscalizador().setInscodigoI(codins);
			operFiscalizador.getFiscalizador().setLado(lado);
			MotOperFiscalizadorDaoFactory.create().actualizaEstado(operFiscalizador);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_OPER_INSPECTOR", operFiscalizador.getOperativo().getOpecodigoD(),"SP_MOT_ACTUALIZA_ESTADO_INSP_OPERATIVO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("MotOperFiscalizadorDaoFactory.create().actualizaEstado(operFiscalizador); Complete");
		} catch (MotOperFiscalizadorDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="ImprimirInfDiarioActa.htm", method=RequestMethod.GET)
	public ModelAndView ImprimirMotosconBajas(HttpServletRequest request,String FECHAINI ,String HORAINI,String HORAFIN,
			String OPECODIGO,String INDOBSERVACION_V,String VISTAPREVIA){
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		ModelAndView mav=null;
		
		Map<String, Object> parameters= new HashMap<String, Object>();
			parameters.put("FECHAINI", FECHAINI);
			parameters.put("HORAINI", HORAINI);
			parameters.put("HORAFIN", HORAFIN);
			parameters.put("OPECODIGO", OPECODIGO);
			parameters.put("VISTAPREVIA", VISTAPREVIA);
			parameters.put("INDOBSERVACION_V", INDOBSERVACION_V);
			mav=new ModelAndView("reportInformeInfDiarioActa", parameters);
			
		return mav;
	}
	
	
	@RequestMapping(value="ListarOperaticoxFecha.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotOperativo> listarOperativoxFecha(HttpServletRequest request,
			@RequestParam("criterio") String fecha){
		List<MotOperativo> list=null;
		try {
			list=MotOperativoDaoFactory.create().findByFecha(fecha);
			logger.info("MotOperFiscalizadorDaoFactory.create().findByCriterio(criterio, texto); Complete");
		} catch (MotOperativoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
		
	@RequestMapping(value="Actualizar.htm", method=RequestMethod.POST)
	public  String actualizar (HttpServletRequest request, MotOperativo operativo){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotOperativoDaoFactory.create().updateObs(operativo);
			MotAuditoriaDaoFactory.create().Insert(
				"MOT_OPERATIVO", operativo.getOpecodigoD(),"SP_MOT_UPD_OBSERVACIONES_OPERATIVOS",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotOperativoDaoException | MotAuditoriaDaoException e) {
			e.printStackTrace();
		}			

	return "Success";
	}
		

	
}
