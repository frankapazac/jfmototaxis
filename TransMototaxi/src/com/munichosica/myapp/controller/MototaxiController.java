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

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.factory.MotEmpadronamientoDaoFactory;
import com.munichosica.myapp.factory.MotUnidDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.util.FileUtil;

@Controller
@RequestMapping("/Mototaxi")
public class MototaxiController {
protected final Logger logger=Logger.getLogger(MototaxiController.class);
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmpadronamiento> listar(@RequestParam("criterio") String criterio,
				@RequestParam("texto") String texto, HttpServletRequest request){
		logger.info("Ingreso a Mototaxi/Listar.htm");
		HttpSession session=request.getSession(true);
		Rol rol=(Rol) session.getAttribute("ROL");
		List<MotEmpadronamiento> list=null;
		try {
			list=MotEmpadronamientoDaoFactory.create().findByCriterio(criterio, texto,
					rol.getUsuario().getEmpresa().getEmpcodigoD());
			logger.info("MotParaderoDaoFactory.create().findByCriterio(criterio, texto,(long) 1);Completed");
		} catch (MotEmpadronamientoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MototaxiUtil obtener(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		HttpSession session=request.getSession(true);
		MotUnidDocumentoSession documentoSession=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS");
		MotUnidDocumentoSession documentoFotoSession=(MotUnidDocumentoSession) session.getAttribute("UNIDAD_DOCUMENTOS_FOTO");
		if(documentoSession!=null){
			documentoSession.getList().clear();
			logger.info("documentoSession.getList().clear(); Session DOCUMENTOS_UNIDAD limpiada completamente.");
		}
		if(documentoFotoSession!=null){
			documentoFotoSession.getList().clear();
			logger.info("documentoFotoSession.getList().clear(); Session DOCUMENTOS_UNIDAD_FOTO limpiada completamente.");
		}
		MototaxiUtil mototaxi=null;
		try {
			mototaxi=new MototaxiUtil();
			mototaxi.setUnidadEmpresa(MotUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo));
			mototaxi.setDocumentos(MotUnidDocumentoDaoFactory.create().findDocumentosByIdUnidad(codigo));
			mototaxi.setFotos(MotUnidDocumentoDaoFactory.create().findFotosByIdUnidad(codigo));
			for(MotUnidDocumento unidDoc:mototaxi.getFotos()){
				String nombreArchivo=FileUtil.createTempFile(request, 
						unidDoc.getAdjuntarArchivo().getAdjnombreV(), unidDoc.getAdjuntarArchivo().getAdjarchivoB());
				unidDoc.getAdjuntarArchivo().setAdjnombreV(nombreArchivo);
			}
			mototaxi.setEmpadronamiento(MotEmpadronamientoDaoFactory.create().findByUnidad(codigo));
		} catch (MotUnidadEmpresaDaoException | MotUnidDocumentoDaoException | MotEmpadronamientoDaoException e) {
			logger.info(e.getMessage());
		}
		return mototaxi;
	}
}
