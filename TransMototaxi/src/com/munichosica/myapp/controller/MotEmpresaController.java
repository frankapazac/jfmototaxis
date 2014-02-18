package com.munichosica.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotEmpPropietario;
import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.UsuEmp;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;
import com.munichosica.myapp.exceptions.MotPersonaDaoException;
import com.munichosica.myapp.exceptions.UsuarioDaoException;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotPersonaDaoFactory;
import com.munichosica.myapp.factory.UsuEmpDaoFactory;
import com.munichosica.myapp.factory.UsuarioDaoFactory;
import com.munichosica.myapp.util.IpUtils;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;

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
	
	@RequestMapping(value="InsertarRepresentante.htm",method=RequestMethod.POST)
	public @ResponseBody MotEmpRepresentante insertarRepresentante(HttpServletRequest request ,MotEmpRepresentante representante){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotEmpresaDaoFactory.create().insertarRepresentante(representante);
			representante.setEmpProp(MotEmpresaDaoFactory.create().findByEmpresaPropietario(representante.getEmpProp().getEprcodigoD()));
			representante.getEmpProp().setPersona(MotPersonaDaoFactory.create().obtener(representante.getEmpProp().getPersona().getPercodigoD()));
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMP_REPRESENTANTE", representante.getRepcodigoI(),"SP_MOT_INS_EMP_REPRESENTANTE",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotEmpresaDaoException | MotPersonaDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return representante;
	}
	
	@RequestMapping(value="ModificarRepresentante.htm",method=RequestMethod.POST)
	public @ResponseBody String modificarRepresentante(HttpServletRequest request, MotEmpRepresentante representante){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "ERROR";
		}
		try {
			MotEmpresaDaoFactory.create().actualizarRepresentante(representante);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMP_REPRESENTANTE", representante.getRepcodigoI(),"SP_MOT_UPD_EMP_REPRESENTANTE",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotEmpresaDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping(value="ListarPropietarios.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpPropietario> listarPropietarios(Long empCodigoD,String criterio, String texto, String estado){
		logger.info("Ingreso a ListarPropietarios.htm codigo="+empCodigoD+" criterio="+criterio+"; texto="+texto+"; estado="+estado);
		List<MotEmpPropietario> list=null;
		try {
			list = MotEmpresaDaoFactory.create().listarPropietarios(empCodigoD, criterio.trim(), texto.trim(), estado.trim());
		} catch (MotEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	@RequestMapping(value="ListarPropietariosDDL.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotEmpPropietario> listarPropietariosDDL(Long empcodigoD) throws Exception{
		logger.info("Ingreso a ListarPropietariosDDL.htm");
		List<MotEmpPropietario> lista = null;
		try {
			boolean estado=MotEmpresaDaoFactory.create().ExisteRepresentante(empcodigoD);
			logger.info("MotEmpresaDaoFactory.create().ExisteRepresentante(empcodigoD) ESTADO = "+estado);
			if(estado){
				throw new Exception("Ya existe un representante. Debe de cesar al representante antes de continuar.");
			}
			lista = MotEmpresaDaoFactory.create().listarPropietariosDDL(empcodigoD);
		} catch (MotEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return lista;
	}
	
	@RequestMapping(value="ObtenerRepresentante.htm", method=RequestMethod.GET)
	public @ResponseBody MotEmpRepresentante obtenerRepresentante(Long empcodigoD){
		logger.info("Ingreso a ObtenerRepresentante.htm CODIGO = "+empcodigoD);
		MotEmpRepresentante representante=null;
		try {
			representante = MotEmpresaDaoFactory.create().obtenerRepresentanteEmpresa(empcodigoD);
			if(representante!=null){
				representante.setEmpProp(MotEmpresaDaoFactory.create().findByEmpresaPropietario(representante.getEmpProp().getEprcodigoD()));
				representante.getEmpProp().setPersona(MotPersonaDaoFactory.create().obtener(
						representante.getEmpProp().getPersona().getPercodigoD()));
			}
		} catch (MotEmpresaDaoException | MotPersonaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return representante;
	}
	
	@RequestMapping(value="InsertarPropietario.htm", method=RequestMethod.POST)
	public @ResponseBody String insertarPropietario(HttpServletRequest request, MotEmpPropietario propietario){
		logger.info("Ingreso a InsertarPropietario.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotPersonaDaoFactory.create().insertar(propietario.getPersona());
			MotEmpresaDaoFactory.create().insertarEmpPropietario(propietario);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMP_PROPIETARIO",propietario.getEprcodigoD() ,"SP_MOT_INS_EMP_PROPIETARIO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotPersonaDaoException | MotEmpresaDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return String.valueOf(propietario.getEprcodigoD());
	}
	
	@RequestMapping(value="ObtenerPropietario.htm", method=RequestMethod.GET)
	public @ResponseBody MotEmpPropietario obtenerPropietario(Long codigo){
		MotEmpPropietario empPropietario=null;
		try {
			empPropietario=MotEmpresaDaoFactory.create().findByEmpresaPropietario(codigo);
			logger.info("MotEmpresaDaoFactory.create().findByEmpresaPropietario(codigo); CODIGO = "+codigo);
			empPropietario.setEmpresa(MotEmpresaDaoFactory.create().findByEmpresa(
					empPropietario.getEmpresa().getEmpcodigoD()));
			logger.info("MotEmpresaDaoFactory.create().findByEmpresa(empPropietario.getEmpresa().getEmpcodigoD()) EMPCODIGO = "+empPropietario.getEmpresa().getEmpcodigoD());
			empPropietario.setPersona(MotPersonaDaoFactory.create().obtener(
					empPropietario.getPersona().getPercodigoD()));
			logger.info("MotPersonaDaoFactory.create().obtener(empPropietario.getPersona().getPercodigoD()) PERCODIGO = "+empPropietario.getPersona().getPercodigoD());
		} catch (MotEmpresaDaoException | MotPersonaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return empPropietario;
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public @ResponseBody String procesar(HttpServletRequest request, MotEmpresa empresa){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotEmpresaDaoFactory.create().insert(empresa);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMPRESA",empresa.getEmpcodigoD() ,"SP_MOT_INS_EMPRESA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotEmpresaDaoException | MotAuditoriaDaoException e) {
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
		HttpSession session=request.getSession(true);
		Usuario usuario1=(Usuario) session.getAttribute("USUARIO");
		if(usuario1==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		try {
			MotEmpresa empresa=MotEmpresaDaoFactory.create().get(usuario.getEmpresa().getEmpcodigoD());
			usuario.setEmpresa(empresa);
			//if(usuario.getPersona().getPercodigoD()<1){
				MotPersonaDaoFactory.create().insertar(usuario.getPersona());
				logger.info("MotPersonaDaoFactory.create().insertar(usuario.getPersona()); Codigo="+usuario.getPersona().getPercodigoD());
			//}
			usuario.getRol().setRolcodigoI(3);
			UsuarioDaoFactory.create().insertar(usuario);
			MotAuditoriaDaoFactory.create().Insert(
					"USUARIO", Long.parseLong(usuario.getUsucodigoI().toString()),"SP_MOT_INS_USUARIO",usuario1.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("UsuarioDaoFactory.create().insertar(usuario); Codigo="+usuario.getUsucodigoI());
			UsuEmp usuEmp=new UsuEmp();
			usuEmp.setEmpresa(empresa);
			usuEmp.setUsuario(usuario);
			UsuEmpDaoFactory.create().insertar(usuEmp);
			MotAuditoriaDaoFactory.create().Insert(
					"USU_EMPR", Long.parseLong(String.valueOf(usuEmp.getUsecodigoI())),"SP_MOT_INS_USU_EMPR",usuario1.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("UsuEmpDaoFactory.create().insertar(usuEmp); Codigo="+usuEmp.getUsecodigoI());
		} catch (MotPersonaDaoException | UsuarioDaoException | MotEmpresaDaoException | NumberFormatException | MotAuditoriaDaoException e) {
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
