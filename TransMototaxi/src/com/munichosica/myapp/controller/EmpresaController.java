package com.munichosica.myapp.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.Usuempr;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotEmpParaderoDaoException;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotEmpDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotEmpresaDaoFactory;
import com.munichosica.myapp.jdbc.ResourceManager;

@Controller
@RequestMapping("/Configuracion")
public class EmpresaController{

	
	protected final Logger logger= Logger.getLogger(EmpresaController.class);
	
	public @ResponseBody MotEmpresa obtener(@RequestParam("codigo") long codigo){
		
		logger.info("Ingreso a listado de Empresas/Obtener.htm");
		MotEmpresa empresa = null;
		
		try {
			empresa=MotEmpresaDaoFactory.create().findByEmpresa(codigo);
			logger.info("MotEmpresaDaoFactory.create().findByEmpresa(codigo);Completed");
		} catch (MotEmpresaDaoException e) {
			e.printStackTrace();
		}
		return empresa;
	}
	
	@RequestMapping(value="Actualizar.htm" , method=RequestMethod.POST)
	public String actualizar(HttpServletRequest request ,MotEmpresa empresa){
		try {
			HttpSession session=request.getSession(true);
			EmpresaDocumentoSession documentoSession=(EmpresaDocumentoSession) session.getAttribute("DOCUMENTOS_EMPRESA");
			Usuempr usuempr=(Usuempr) session.getAttribute("USUARIO");
			logger.info("Ingreso a Configuracion/Actualizar.htm");
			MotEmpresaDaoFactory.create().update(empresa);
			logger.info("MotEmpresaDaoFactory.create().update(empresa); Completed codigo: "+empresa.getEmpcodigoD());
			if(documentoSession!=null){
				if(documentoSession.getList()!=null&&documentoSession.getList().size()>0){
					for(MotEmpDocumento documento: documentoSession.getList()){
						documento.setEmpresa(usuempr.getEmpresa());
						MotAdjuntarArchivoDaoFactory.create().insert(documento.getAdjuntarArchivo());
						MotEmpDocumentoDaoFactory.create().insert(documento);
					}
				}
			}
		} catch (MotEmpresaDaoException | MotAdjuntarArchivoDaoException | MotEmpDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		
		return "Success";
	}

}
