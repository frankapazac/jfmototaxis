package com.munichosica.myapp.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotEmpDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotEmpresaDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.IpUtils;

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
			Usuario usuario=(Usuario) session.getAttribute("USUARIO");
			if(usuario==null){
				SecurityContextHolder.getContext().setAuthentication(null);
				return "Error";
			}
			DocumentoEmpresaSession documentos=(DocumentoEmpresaSession) session.getAttribute("FOTO_EMPRESA");
			logger.info("Ingreso a Configuracion/Actualizar.htm");
			MotEmpresaDaoFactory.create().update(empresa);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMPRESA", empresa.getEmpcodigoD(),"SP_MOT_UPD_DATOS_EMPRESA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			logger.info("MotEmpresaDaoFactory.create().update(empresa); Completed codigo: "+empresa.getEmpcodigoD());
			if(documentos!=null){
				if(documentos.getList()!=null&&documentos.getList().size()>0){
					for(MotEmpDocumento documento: documentos.getList()){
						documento.setEmpresa(usuario.getEmpresa());
						MotAdjuntarArchivoDaoFactory.create().insert(documento.getAdjuntarArchivo());
						MotEmpDocumentoDaoFactory.create().insert(documento);
						MotAuditoriaDaoFactory.create().Insert(
								"MOT_EMP_DOCUMENTO", documento.getAdjuntarArchivo().getAdjcodigoD(),"SP_INS_MOT_EMP_DOCUMENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
					}
				}
			}
		} catch (MotEmpresaDaoException | MotAdjuntarArchivoDaoException | MotEmpDocumentoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Foto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarFoto(HttpServletRequest request){
		logger.info("Ingreso a Configuracion/Foto.htm");
		HttpSession session=request.getSession(true);
		DocumentoEmpresaSession documentos=(DocumentoEmpresaSession) session.getAttribute("FOTO_EMPRESA");
		if(documentos==null){
			documentos=new DocumentoEmpresaSession();
			session.setAttribute("FOTO_EMPRESA", documentos);
			logger.info("Se creo session.setAttribute('FOTO_EMPRESA', documentos);");
		}
		String nombreArchivo="";
		try {
			MotEmpDocumento documento=new MotEmpDocumento();
			for(Part part:request.getParts()){
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				if(part.getName().equals("flFoto")){
					String filename=FileUtil.getFilename(part);
					documento.getAdjuntarArchivo().setAdjcodigoD(0L);
					documento.getAdjuntarArchivo().setAdjnombreV(filename);
					documento.getAdjuntarArchivo().setAdjarchivoB(FileUtil.compress(bs));
					documento.getAdjuntarArchivo().setAdjextensionV(FileUtil.getExtension(filename));
					nombreArchivo=FileUtil.createTempFile(request, documento.getAdjuntarArchivo().getAdjnombreV(), bs);
				}else if(part.getName().equals("txtFotoCodigo")){
					documento.getTipoDocumento().setMtdcodigoI(Integer.parseInt(new String(bs,"UTF8")));
				}
			}
			nombreArchivo=documento.getTipoDocumento().getMtdcodigoI()+"|"+nombreArchivo;
			documentos.add(documento);
			logger.info("Fotos empresa subidos: "+documentos.getList().size());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return nombreArchivo;
	}
}
