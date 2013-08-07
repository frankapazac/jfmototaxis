package com.munichosica.myapp.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotInteInventarioTipo;
import com.munichosica.myapp.dto.MotMarca;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.dto.MotOficinaRegistral;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.MotPolicia;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotEmpRepresentanteDaoException;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.exceptions.MotInteInventarioTipoDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;
import com.munichosica.myapp.exceptions.MotPoliciaDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.factory.MotConductorDaoFactory;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;
import com.munichosica.myapp.exceptions.MotZonaDaoException;
import com.munichosica.myapp.factory.MotEmpDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotEmpRepresentanteDaoFactory;
import com.munichosica.myapp.factory.MotInfraccionDaoFactory;
import com.munichosica.myapp.factory.MotInspectorDaoFactory;
import com.munichosica.myapp.factory.MotInteInventarioTipoDaoFactory;
import com.munichosica.myapp.factory.MotMarcaDaoFactory;
import com.munichosica.myapp.factory.MotModeloDaoFactory;
import com.munichosica.myapp.factory.MotOficinaRegistralDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;
import com.munichosica.myapp.factory.MotParaderoDaoFactory;
import com.munichosica.myapp.factory.MotPoliciaDaoFactory;
import com.munichosica.myapp.factory.MotTipoDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotTipoMedidaDaoFactory;
import com.munichosica.myapp.factory.MotUbigeoDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.MotZonaDaoFactory;
import com.munichosica.myapp.util.DateUtil;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.UserSecurity;

@Controller
@RequestMapping("/")
public class PageController {
	
	Logger logger=Logger.getLogger(PageController.class);
	
	@RequestMapping(value="Inicio.htm",method=RequestMethod.GET)
	public String inicio(HttpServletRequest request,Model model){
		logger.info("Ingreso a Inicio.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesBienvenido";
	}

	@RequestMapping(value="Asociados.htm",method=RequestMethod.GET)
	public String asociados(HttpServletRequest request, Model model){
		logger.info("Ingreso a Asociados.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
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
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesAsociados";
	}

	@RequestMapping(value="Conductores.htm",method=RequestMethod.GET)
	public String conductores(HttpServletRequest request,Model model){
		logger.info("Ingreso a Conductores.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
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
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesConductores";
	}

	@RequestMapping(value="Mototaxis.htm",method=RequestMethod.GET)
	public String mototaxis(HttpServletRequest request,Model model){
		logger.info("Ingreso a Mototaxis.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesMototaxis";
	}

	@RequestMapping(value="Paraderos.htm",method=RequestMethod.GET)
	public String paraderos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Paraderos.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		try {
			List<MotParadero> lista=MotParaderoDaoFactory.create().findZonaByEmpresa(
					usuario.getEmpresa().getEmpcodigoD());
			model.addAttribute("paraderos",lista);
		} catch (MotParaderoDaoException e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesParaderos";
	}

	@RequestMapping(value="Documentacion.htm",method=RequestMethod.GET)
	public String documentacion(HttpServletRequest request,Model model){
		logger.info("Ingreso a Documentacion.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		
		try {
			List<MotOperativo> operativo = MotOperativoDaoFactory.create().findByFecha("28/02/2013"/*DateUtil.getFechaActual()*/);
			model.addAttribute("operativo",operativo);
		} catch (MotOperativoDaoException e) {

		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());

		return "tilesDocumentacion";
	}

	@RequestMapping(value="DocumentacionTrans.htm",method=RequestMethod.GET)
	public String documentacionTrans(HttpServletRequest request,Model model){
		logger.info("Ingreso a DocumentacionTrans.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());

		return "tilesDocumentacionTrans";
	}

	@RequestMapping(value="Configuracion.htm",method=RequestMethod.GET)
	public String configuracion(HttpServletRequest request,Model model){
		logger.info("Ingreso a Configuracion.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		try {
			MotEmpRepresentante emprepresentante=null;
			emprepresentante = MotEmpRepresentanteDaoFactory.create().findByEmpresa(
					usuario.getEmpresa().getEmpcodigoD());
			//List<MotTipoDocumento> fotos = MotTipoDocumentoDaoFactory.create().findByTable("EMP");
			List<MotEmpDocumento> imagenes=MotEmpDocumentoDaoFactory.create().findImagesByEmpresa(usuario.getEmpresa().getEmpcodigoD());
			String nombreArchivo=null;
			for(MotEmpDocumento images:imagenes){
				if(images.getAdjuntarArchivo()!=null&&images.getAdjuntarArchivo().getAdjarchivoB()!=null)
				nombreArchivo="temp/"+FileUtil.createTempFile(request, images.getAdjuntarArchivo().getAdjnombreV(),images.getAdjuntarArchivo().getAdjarchivoB());
				else
				nombreArchivo="images/no_disponible_m.jpg";
				
				images.getAdjuntarArchivo().setAdjnombreV(nombreArchivo);
			}
			model.addAttribute("USUARIO", usuario.getUsuusuarioV());
			model.addAttribute("emprepresentante", emprepresentante);//para enviar datos a esta pagina
			//model.addAttribute("fotos", fotos);
			model.addAttribute("imagenes", imagenes);
			System.out.println(imagenes.size());
		} catch (MotEmpRepresentanteDaoException | MotEmpDocumentoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesConfiguracion";
	}
	
	
	@RequestMapping(value="configuracionTrans.htm",method=RequestMethod.GET)
	public String configuracionTrans(HttpServletRequest request,Model model){
		logger.info("Ingreso a Configuracion.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		try {
			MotEmpRepresentante emprepresentante=null;
			emprepresentante = MotEmpRepresentanteDaoFactory.create().findByEmpresa(
					usuario.getEmpresa().getEmpcodigoD());
			List<MotTipoDocumento> fotos = MotTipoDocumentoDaoFactory.create().findByTable("EMP");
			System.out.println("USUARIOOOOOOOOOOOO "+usuario.getUsuusuarioV());
			model.addAttribute("USUARIO", usuario.getUsuusuarioV());
			model.addAttribute("emprepresentante", emprepresentante);//para enviar datos a esta pagina
			model.addAttribute("fotos", fotos);
		} catch (MotEmpRepresentanteDaoException | MotTipoDocumentoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesConfiguracion2";
	}
	
	@RequestMapping(value="Inspectores.htm", method=RequestMethod.GET)
	public String inspectores(HttpServletRequest request, Model model){
		logger.info("Ingreso a Inspectores.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
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
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesInspectores";
	}
	
	@RequestMapping(value="Papeletas.htm", method=RequestMethod.GET)
	public String infraccion(HttpServletRequest request, Model model){
		logger.info("Ingreso a Papeleta.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		try {
			List<MotConductor> conductores=null;
			List<MotUnidadEmpresa> placas=null;
			List<MotInspector> inspectores=null;
			List<MotInfraccion> infracciones=null;
			List<MotPolicia> policias=null;
			
			conductores = MotConductorDaoFactory.create().findAll();
			placas=MotUnidadEmpresaDaoFactory.create().findAllPlacasByAsociado();
			inspectores=MotInspectorDaoFactory.create().findAll();
			infracciones=MotInfraccionDaoFactory.create().findAll();
			policias=MotPoliciaDaoFactory.create().findAll();
			model.addAttribute("conductores", conductores);
			model.addAttribute("placas", placas);
			model.addAttribute("inspectores", inspectores);
			model.addAttribute("infracciones", infracciones);
			model.addAttribute("policias", policias);
		} catch (MotConductorDaoException | MotUnidadEmpresaDaoException | 
				MotInspectorDaoException | MotInfraccionDaoException | 
				MotPoliciaDaoException e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesPapeleta";
	}

	@RequestMapping(value="Operativos.htm",method=RequestMethod.GET)
	public String operativos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Operativos.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		try {
			List<MotInspector> inspectores= MotInspectorDaoFactory.create().findAll();
			model.addAttribute("inspectores", inspectores);
			List<MotZona> zona = MotZonaDaoFactory.create().findAll();
			model.addAttribute("zona", zona);
		} catch (MotInspectorDaoException | MotZonaDaoException e) {
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesOperativos";
	}
	
	
	@RequestMapping(value="Infraccion.htm",method=RequestMethod.GET)
	public String infracciones(HttpServletRequest request,Model model){
		logger.info("Ingreso a infracciones.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		
		try {
			List<MotTipoMedida> tipoMedida= MotTipoMedidaDaoFactory.create().findAllTipoMedida();
			model.addAttribute("tipoMedida", tipoMedida);
		} catch (MotTipoMedidaDaoException e) {
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesInfracciones";
	}
	
	@RequestMapping(value="Internamientos.htm",method=RequestMethod.GET)
	public String internamientos(HttpServletRequest request,Model model){
		logger.info("Ingreso a Internamiento.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		List<MotInteInventarioTipo> parteExterior=null;
		List<MotInteInventarioTipo> parteInterior=null;
		List<MotInteInventarioTipo> parteMotor=null;
		List<MotConductor> conductores=null;
		List<MotUnidadEmpresa> placas=null;
		try {
			parteExterior=MotInteInventarioTipoDaoFactory.create().findbyTipo("E");
			parteInterior=MotInteInventarioTipoDaoFactory.create().findbyTipo("I");
			parteMotor=MotInteInventarioTipoDaoFactory.create().findbyTipo("M");
			conductores = MotConductorDaoFactory.create().findAll();
			placas=MotUnidadEmpresaDaoFactory.create().findAllPlacasByAsociado();
			model.addAttribute("parteExterior", parteExterior);
			model.addAttribute("parteInterior", parteInterior);
			model.addAttribute("parteMotor", parteMotor);
			model.addAttribute("conductores", conductores);
			model.addAttribute("placas", placas);
		} catch (MotInteInventarioTipoDaoException | MotConductorDaoException | MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesInternamiento";
	}
	
	@RequestMapping(value="MaestroEmpresa.htm",method=RequestMethod.GET)
	public String maestroEmpresa(HttpServletRequest request,Model model){
		logger.info("Ingreso a Internamiento.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
		
		try {
			List<MotTipoMedida> tipoMedida= MotTipoMedidaDaoFactory.create().findAllTipoMedida();
			model.addAttribute("tipoMedida", tipoMedida);
		} catch (MotTipoMedidaDaoException e) {
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesMaestroEmpresa";
	}
	
	@RequestMapping(value="MaestroTransporte.htm",method=RequestMethod.GET)
	public String maestroTransporte(HttpServletRequest request,Model model){
		logger.info("Ingreso a Internamiento.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
	
		try {
			List<MotZona> zona;
			zona = MotZonaDaoFactory.create().findAll();
			model.addAttribute("zona", zona);
		} catch (MotZonaDaoException e) {
			e.printStackTrace();
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesMaestroTransporte";
	}
	
	@RequestMapping(value="Empresas.htm",method=RequestMethod.GET)
	public String empresas(HttpServletRequest request,Model model){
		logger.info("Ingreso a Empresas.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			usuario=new UserSecurity().getUsuarioByUser(request);
			session.setAttribute("USUARIO", usuario);
		}
	
		List<MotZona> zonas=null;
		List<MotUbigeo> departamentos=null;
		try {
			zonas=MotZonaDaoFactory.create().findAll();
			departamentos = MotUbigeoDaoFactory.create().findAllDepartamentos();
			
			model.addAttribute("zonas", zonas);
			model.addAttribute("departamentos",departamentos);
		} catch (MotZonaDaoException | MotUbigeoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("paginas",usuario.getRol().getPaginas());
		return "tilesEmpresas";
	}
	
	
}
