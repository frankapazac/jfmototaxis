package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.dto.UsuEmp;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.exceptions.UsuarioDaoException;
import com.munichosica.myapp.factory.MotEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotPersonaDaoFactory;
import com.munichosica.myapp.factory.ReportsDaoFactory;
import com.munichosica.myapp.factory.UsuEmpDaoFactory;
import com.munichosica.myapp.factory.UsuarioDaoFactory;

@Controller
@RequestMapping("/Empresas")
public class MotEmpresaController {
	protected final Logger logger= Logger.getLogger(MotEmpresaController.class);

	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpresa> listar(String criterio, String texto){
		List<MotEmpresa> list=null;
		try {
			list = MotEmpresaDaoFactory.create().listar(criterio, texto);
		} catch (MotEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody String procesar(MotEmpresa empresa){
		try {
			MotEmpresaDaoFactory.create().insert(empresa);
		} catch (MotEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return String.valueOf(empresa.getEmpcodigoD());
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotEmpresa obtener(@RequestParam("codigo") Long codigo){
		MotEmpresa empresa=null;
		try {
			empresa=MotEmpresaDaoFactory.create().get(codigo);
		} catch (MotEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return empresa;
	}

	@RequestMapping(value="ObtenerUsuario.htm", method=RequestMethod.GET)
	public @ResponseBody Usuario obtenerUsuario(@RequestParam("codigo") Long codigo){
		logger.info("Ingreso a Empresas/ObtenerUsuario.htm");
		Usuario usuario=null;
		try {
			usuario=UsuarioDaoFactory.create().obtenerUsuarioEmpresa(codigo);
			logger.info("usuario=UsuarioDaoFactory.create().obtenerUsuarioEmpresa(codigo); Completed");
		} catch (UsuarioDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return usuario;
	}
	
	@RequestMapping(value="InsertarUsuario.htm", method=RequestMethod.POST)
	public @ResponseBody String insertarUsuario(HttpServletRequest request,Usuario usuario){
		logger.info("Ingreso a Empresas/InsertarUsuario.htm");
		try {
			MotEmpresa empresa=MotEmpresaDaoFactory.create().get(usuario.getEmpresa().getEmpcodigoD());
			usuario.setEmpresa(empresa);
			//if(usuario.getPersona().getPercodigoD()<1){
				MotPersonaDaoFactory.create().insertar(usuario.getPersona());
			//}
			usuario.getRol().setRolcodigoI(3);
			UsuarioDaoFactory.create().insertar(usuario);
			UsuEmp usuEmp=new UsuEmp();
			usuEmp.setEmpresa(empresa);
			usuEmp.setUsuario(usuario);
			UsuEmpDaoFactory.create().insertar(usuEmp);
		} catch (MotPersonaDaoException | UsuarioDaoException | MotEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return String.valueOf(usuario.getUsucodigoI());
	}
	
	@RequestMapping(value="ImprimirSobreUsuario.htm", method=RequestMethod.GET)
	public ModelAndView imprimirSobreUsuario(Long codigo){
		ModelAndView mav=null;
		logger.info("Ingreso a ImprimirSobreUsuario.htm codigo:"+codigo);
		try {
			Map<String, Object> parameters=new HashMap<String, Object>();
			Usuario usuario=UsuarioDaoFactory.create().obtenerUsuarioEmpresa(codigo);
			logger.info("Usuario : " + usuario.getUsuusuarioV());
			parameters.put("USUARIO", usuario);
			logger.info("usuario=UsuarioDaoFactory.create().obtenerUsuarioEmpresa(codigo); Completed");

			mav=new ModelAndView("reportSobreUsuario", parameters);
		} catch (UsuarioDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return mav;
	}
	
	@RequestMapping(value="ImprimirEmpresasUsuarios.htm", method=RequestMethod.GET)
	public ModelAndView imprimirEmpresasUsuarios(){	
		return new ModelAndView("reportEmpresasUsuarios");
	}
	
	/*@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
	public ModelAndView descargarPdf(Long codigo){
		ModelAndView mav=null;
		System.out.println("Ingreso a Papeletas/ImprimirPdf.htm Codigo:"+codigo);
		Map<String, Object> parameters= new HashMap<String, Object>();
		RepPapeleta papeleta=null;
		try {
			papeleta = ReportsDaoFactory.create().reportePapeleta(codigo);
			parameters.put("papeleta", papeleta);
			mav=new ModelAndView("reportPapeleta", parameters);
		} catch (ReportsDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return mav;
	}*/
}
