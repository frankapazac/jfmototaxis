package com.munichosica.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;
import com.munichosica.myapp.exceptions.UsuarioDaoException;
import com.munichosica.myapp.factory.MotTipoMedidaDaoFactory;
import com.munichosica.myapp.factory.UsuarioDaoFactory;
import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

@Controller
@RequestMapping("/")
public class UsuarioController{
	
	@RequestMapping(value="Ingresar.htm", method=RequestMethod.GET)
	public String ingresar(Model model){
		System.out.println("INGRESO A REGISTRARSE");
		return "login";
	}
	
	@RequestMapping(value="FalloLogin.htm", method=RequestMethod.GET)
	public String falloLogin(Usuario usuario,Model model){
		model.addAttribute("error", usuario.getUsuusuarioV()+"Usted no pudo registrarse intentelo nuevamente");
		return "login";
	}

	@RequestMapping(value="Salir.htm", method=RequestMethod.GET)
	public String salir(HttpServletRequest request,Model model){
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		if(rol!=null) rol=null;
		return "login";	
	}
	
	@RequestMapping(value="Panel.htm", method=RequestMethod.GET)
	public String panel(Model model){
		System.out.println("ENTRO A BIENVENIDO");
		return "tilesBienvenido";
	}
	
	@RequestMapping(value="403.htm", method=RequestMethod.GET)
	public String error403(Model model){
		return "403";
	}
	
	@RequestMapping(value="Procesar.htm", method=RequestMethod.POST)
	public String agregar(HttpServletRequest request,MotTipoMedida medida){
		try {
			MotTipoMedidaDaoFactory.create().insert(medida);
		} catch (MotTipoMedidaDaoException e) {

		}
		return "Success";
	}
	
	@RequestMapping(value="Actualizar.htm",method=RequestMethod.POST)
	public String cambiarPass(HttpServletRequest request,Usuario usuario){
		
		System.out.println("ENTRO A USUARIO FEO");
		
		try {
			UsuarioDaoFactory.create().update(usuario);
		} catch (UsuarioDaoException e) {
			e.printStackTrace();
		}
			
		return "Success";
	}
}
