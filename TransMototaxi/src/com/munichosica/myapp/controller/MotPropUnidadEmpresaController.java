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

import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;
import com.munichosica.myapp.factory.MotPropUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotUnidConductorDaoFactory;

@Controller
@RequestMapping("/PropUnidadEmpresa")
public class MotPropUnidadEmpresaController {
	
	Logger logger=Logger.getLogger(MotPropUnidadEmpresaController.class);
	
	@RequestMapping(value="BuscarPorAsociado.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotPropUnidadEmpresa> lista(@RequestParam("criterio") String criterio,
			@RequestParam("texto") String texto, @RequestParam("codigo") Long codigo){
		List<MotPropUnidadEmpresa> list=null;
		logger.info("Ingreso a PropUnidadEmpresa/BuscarPorAsociado.htm criterio:"+criterio+" texto:"+texto+" codigo:"+codigo);
		try {
			list=MotPropUnidadEmpresaDaoFactory.create().findByAsociado(criterio, texto, codigo);
		} catch (MotPropUnidadEmpresaDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Agregar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotPropUnidadEmpresa> agregar(HttpServletRequest request, Long codigo){
		HttpSession session=request.getSession(true);
		MotPropUnidEmpresaSession puemp=(MotPropUnidEmpresaSession) 
								session.getAttribute("PROP_UNID_EMPRESA");
		try {
			if(puemp==null){
				throw new MotPropUnidadEmpresaDaoException("Session conductor no creada");
			}
			puemp.add(codigo);
			System.out.println("Lista agregar: "+puemp.getList().size());
		} catch (MotPropUnidadEmpresaDaoException e) {
			logger.error(e.getMessage());
		}
		return puemp.getList();
	}
	
	@RequestMapping(value="Eliminar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotPropUnidadEmpresa> eliminar(HttpServletRequest request, Long codigo){
		HttpSession session=request.getSession(true);
		MotPropUnidEmpresaSession puemp=(MotPropUnidEmpresaSession) 
								session.getAttribute("PROP_UNID_EMPRESA");
		puemp.remove(codigo);
		System.out.println("Lista eliminar: "+puemp.getList().size());
		return puemp.getList();
	}
	
	@RequestMapping(value="Asignar.htm", method=RequestMethod.POST)
	public String asignar(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		MotPropUnidEmpresaSession puemp=(MotPropUnidEmpresaSession) 
				session.getAttribute("PROP_UNID_EMPRESA");
		try {
			for(MotPropUnidadEmpresa propUnidadEmpresa:puemp.getList()){
				MotUnidConductor motUnidConductor=new MotUnidConductor();
				motUnidConductor.setPropietariomoto(propUnidadEmpresa);
				motUnidConductor.setConductor(puemp.getConductor());
				MotUnidConductorDaoFactory.create().asignar(motUnidConductor);	
			}
		} catch (MotUnidConductorDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
}
