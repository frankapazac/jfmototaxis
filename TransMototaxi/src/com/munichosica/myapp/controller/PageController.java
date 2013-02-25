package com.munichosica.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.dto.MotOficinaRegistral;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotEmpRepresentanteDaoException;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;
import com.munichosica.myapp.exceptions.MotZonaDaoException;
import com.munichosica.myapp.factory.MotEmpRepresentanteDaoFactory;
import com.munichosica.myapp.factory.MotInspectorDaoFactory;
import com.munichosica.myapp.factory.MotMarcaDaoFactory;
import com.munichosica.myapp.factory.MotModeloDaoFactory;
import com.munichosica.myapp.factory.MotOficinaRegistralDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;
import com.munichosica.myapp.factory.MotTipoDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUbigeoDaoFactory;
import com.munichosica.myapp.factory.MotZonaDaoFactory;
import com.munichosica.myapp.util.UserSecurity;

@Controller
@RequestMapping("/")
public class PageController {
	
	Logger logger=Logger.getLogger(PageController.class);
	
	@RequestMapping(value="Inicio.htm",method=RequestMethod.GET)
	public String inicio(HttpServletRequest request,Model model){
		logger.info("Ingreso a Inicio.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		model.addAttribute("usuario",rol.getUsuario());
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesBienvenido";
	}

	@RequestMapping(value="Asociados.htm",method=RequestMethod.GET)
	public String asociados(HttpServletRequest request, Model model){
		logger.info("Ingreso a Asociados.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		
		List<MotUbigeo> departamentos=null;
		List<MotTipoDocumento> documentos=null;
		List<MotModelo> modelos=null;
		List<MotMarca> marcas=null;
		List<MotOficinaRegistral> oficinas=null;
		List<MotTipoDocumento> documentosUnidad=null;
		List<MotTipoDocumento> documentosUnidadFotos=null;
		try {
			departamentos = MotUbigeoDaoFactory.create().findAllDepartamentos();
			documentos=MotTipoDocumentoDaoFactory.create().findByTable("ADO");
			documentosUnidad=MotTipoDocumentoDaoFactory.create().findByTable("UDO");
			documentosUnidadFotos=MotTipoDocumentoDaoFactory.create().findByTable("UDF");
			modelos=MotModeloDaoFactory.create().findAll();
			marcas=MotMarcaDaoFactory.create().findAll();
			oficinas=MotOficinaRegistralDaoFactory.create().findAll();
			model.addAttribute("departamentos", departamentos);
			model.addAttribute("documentos", documentos);
			model.addAttribute("documentosUnidad", documentosUnidad);
			model.addAttribute("documentosUnidadFotos", documentosUnidadFotos);
			model.addAttribute("modelos",modelos);
			model.addAttribute("marcas",marcas);
			model.addAttribute("oficinas", oficinas);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesAsociados";
	}

	@RequestMapping(value="Conductores.htm",method=RequestMethod.GET)
	public String conductores(HttpServletRequest request,Model model){
		logger.info("Ingreso a Conductores.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		model.addAttribute("paginas",rol.getPaginas());
		/*para el mantenimiendo de conductores*/
		List<MotUbigeo> departamentos=null;
		List<MotTipoDocumento> documentos=null;
		try {
			departamentos = MotUbigeoDaoFactory.create().findAllDepartamentos();
			documentos=MotTipoDocumentoDaoFactory.create().findByTable("CON");
			model.addAttribute("departamentos", departamentos);
			model.addAttribute("documentos", documentos);
		} catch (MotUbigeoDaoException | MotTipoDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return "tilesConductores";
	}

	@RequestMapping(value="Mototaxis.htm",method=RequestMethod.GET)
	public String mototaxis(HttpServletRequest request,Model model){
		logger.info("Ingreso a Mototaxis.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesMototaxis";
	}

	@RequestMapping(value="Paraderos.htm",method=RequestMethod.GET)
	public String paraderos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Paraderos.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		try {
			List<MotParadero> lista=MotParaderoDaoFactory.create().findZonaByEmpresa(
					rol.getUsuario().getEmpresa().getEmpcodigoD());
			model.addAttribute("paraderos",lista);
		} catch (MotParaderoDaoException e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesParaderos";
	}

	@RequestMapping(value="Documentacion.htm",method=RequestMethod.GET)
	public String documentacion(HttpServletRequest request,Model model){
		logger.info("Ingreso a Documentacion.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesDocumentacion";
	}

	@RequestMapping(value="Configuracion.htm",method=RequestMethod.GET)
	public String configuracion(HttpServletRequest request,Model model){
		logger.info("Ingreso a Configuracion.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		try {
			MotEmpRepresentante emprepresentante=null;
			emprepresentante = MotEmpRepresentanteDaoFactory.create().findByEmpresa(
					rol.getUsuario().getEmpresa().getEmpcodigoD());
			List<MotTipoDocumento> fotos = MotTipoDocumentoDaoFactory.create().findByTable("EMP");
			model.addAttribute("emprepresentante", emprepresentante);//para enviar datos a esta pagina
			model.addAttribute("fotos", fotos);
		} catch (MotEmpRepresentanteDaoException | MotTipoDocumentoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesConfiguracion";
	}
	
	@RequestMapping(value="Inspectores.htm", method=RequestMethod.GET)
	public String inspectores(HttpServletRequest request, Model model){
		logger.info("Ingreso a Inspectores.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		
		List<MotUbigeo> departamentos=null;
		List<MotTipoDocumento> documentos=null;
		try {
			departamentos = MotUbigeoDaoFactory.create().findAllDepartamentos();
			documentos=MotTipoDocumentoDaoFactory.create().findByTable("INS");
			model.addAttribute("departamentos", departamentos);
			model.addAttribute("documentos", documentos);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesInspectores";
	}
	
	@RequestMapping(value="Operativos.htm",method=RequestMethod.GET)
	public String operativos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Operativos.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol==null){
			System.out.println("INICIO");
			rol=new UserSecurity().getRol();
			session.setAttribute("ROL", rol);
		}
		try {
			List<MotInspector> inspectores= MotInspectorDaoFactory.create().findAll();
			model.addAttribute("inspectores", inspectores);
			List<MotZona> zona = MotZonaDaoFactory.create().findAll();
			model.addAttribute("zona", zona);
		} catch (MotInspectorDaoException | MotZonaDaoException e) {
			e.printStackTrace();
		}
		model.addAttribute("paginas",rol.getPaginas());
		return "tilesOperativos";
	}
}


