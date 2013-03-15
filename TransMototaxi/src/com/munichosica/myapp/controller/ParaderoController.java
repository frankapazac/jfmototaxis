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

import com.munichosica.myapp.dto.MotEmpParadero;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotEmpParaderoDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.factory.MotEmpParaderoDaoFactory;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;

@Controller
@RequestMapping("/Paradero")
public class ParaderoController {

		protected final Logger logger=Logger.getLogger(ParaderoController.class);
		
		@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
		public @ResponseBody List<MotParadero> listar(HttpServletRequest request,
				@RequestParam("criterio") String criterio,@RequestParam("texto") String texto){
			logger.info("Ingreso a Mototaxi/Listar.htm");
			List<MotParadero> list=null;
			try {
				HttpSession session=request.getSession(true);
				Rol rol=(Rol) session.getAttribute("ROL");
				list=MotParaderoDaoFactory.create().findByCriterio(criterio, texto,
						rol.getUsuario().getEmpresa().getEmpcodigoD());
				logger.info("MotParaderoDaoFactory.create().findByCriterio(criterio, texto,(long) 1);Completed");
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return list;
		}
		
		@RequestMapping(value="Obtener.htm", method=RequestMethod.POST)
		public @ResponseBody MotParadero obtener(@RequestParam("codigo") int codigo){
			logger.info("Ingreso a Mototaxi/Obtener.htm");
			MotParadero paradero= null;
			
			try {
				paradero=MotParaderoDaoFactory.create().findByParadero(codigo);
				logger.info("MotParaderoDaoFactory.create().findByParadero(1);Completed");
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return paradero;
		}
		
		@RequestMapping(value="Agregar.htm", method=RequestMethod.POST)
		public String agregar(HttpServletRequest request,MotEmpParadero paradero){
			try {
				HttpSession session=request.getSession(true);
				Rol rol=(Rol) session.getAttribute("ROL");
				logger.info("Ingreso a Paraderos/Agregar.htm");
				MotEmpresa empresa=new MotEmpresa();
				empresa.setEmpcodigoD(rol.getUsuario().getEmpresa().getEmpcodigoD());//cambiar
				paradero.setEmpresa(empresa);
				MotEmpParaderoDaoFactory.create().insert(paradero);
				logger.info("MotEmpParaderoDaoFactory.create().insert(paradero); Complete");
			} catch (MotEmpParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return "Success";
		}	
		
		@RequestMapping(value="Eliminar.htm",method=RequestMethod.POST)
		public String eliminar(@RequestParam("codigo") Long codigo){
			
			try {
				logger.info("Eliminar Paraderos/Eliminar.htm");
				MotEmpParadero paradero=new MotEmpParadero();
				paradero.setEpacodigo_I(codigo);
				MotEmpParaderoDaoFactory.create().delete(paradero);
				logger.info("MotEmpParaderoDaoFactory.create().delete(paradero); Complete");
			} catch (MotEmpParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return "Success";
		}
		
		//ESTO ES PARADERO PARA TRANSPORTES
		
		@RequestMapping(value="ListarParaderoTrans.htm", method=RequestMethod.POST)
		public @ResponseBody List<MotParadero> listarParaderoTrans(HttpServletRequest request,@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
			
			logger.info("Ingreso a Mototaxi/Listar.htm");
			List<MotParadero> list=null;
			try {
				list=MotParaderoDaoFactory.create().findByCriterioTrans(criterio, texto);
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return list;
		}
		
		@RequestMapping(value="ProcesarParadero.htm", method=RequestMethod.POST)
		public String agregar(HttpServletRequest request,MotParadero paradero){
			try {
				MotParaderoDaoFactory.create().insert(paradero);
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return "Success";
		}	
		
		@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
		public @ResponseBody MotParadero obtener(@RequestParam("codigo") Long codigo){
			MotParadero paradero = null;
			try {
				paradero= MotParaderoDaoFactory.create().findByPrimaryKey(codigo);
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return paradero;
		}
		
		@RequestMapping(value="EliminarParadero.htm",method=RequestMethod.GET)
		public String eliminar(@RequestParam("codigo") Integer codigo){
			
			try {
				MotParadero paradero=new MotParadero();
				paradero.setParcodigoI(codigo);
				System.out.println(paradero);
				MotParaderoDaoFactory.create().delete(paradero);
			} catch (MotParaderoDaoException e) {
				logger.error(e.getMessage());
			}
			return "Success";
		}
		
}
