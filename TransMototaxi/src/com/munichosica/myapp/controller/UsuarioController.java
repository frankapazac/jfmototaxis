package com.munichosica.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.munichosica.myapp.dto.Usuario;

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
	public String salir(Model model){
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
}
