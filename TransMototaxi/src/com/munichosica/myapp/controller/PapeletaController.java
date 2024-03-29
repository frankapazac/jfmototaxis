package com.munichosica.myapp.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.dto.MotPolicia;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;
import com.munichosica.myapp.exceptions.MotPoliciaDaoException;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotCondDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotConductorDaoFactory;
import com.munichosica.myapp.factory.MotInfrMedidaDaoFactory;
import com.munichosica.myapp.factory.MotInfraccionDaoFactory;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;
import com.munichosica.myapp.factory.MotPapeletaDaoFactory;
import com.munichosica.myapp.factory.MotPoliciaDaoFactory;
import com.munichosica.myapp.factory.MotUnidConductorDaoFactory;
import com.munichosica.myapp.factory.MotUnidDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotUnidadEmpresaDaoFactory;
import com.munichosica.myapp.factory.ReportsDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.IpUtils;

@Controller
@RequestMapping("/Papeletas")
public class PapeletaController {
	
	private Logger logger=Logger.getLogger(PapeletaController.class);

	@RequestMapping(value="BuscarPorConductor.htm", method=RequestMethod.GET)
	public @ResponseBody MotConductor buscarConductor(@RequestParam("codigo") Long codigo){
		MotConductor conductor=null;
		return conductor;
	}
	
	@RequestMapping(value="MensajesConductor.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotCondDocumento> mensajesConductor(Long codigo){
		List<MotCondDocumento> documentos=null;
		try {
			documentos = MotCondDocumentoDaoFactory.create().findMensajesIdConductor(codigo);
		} catch (MotCondDocumentoDaoException e) {
			logger.error(e.getMessage(),e );
		}
		return documentos;
	}
	
	@RequestMapping(value="MensajesUnidad.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotUnidDocumento> mensajesUnidad(String placa){
		List<MotUnidDocumento> documentos=null;
		try {
			documentos = MotUnidDocumentoDaoFactory.create().findMensajesPlaca(placa);
		} catch (MotUnidDocumentoDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return documentos;
	}
	
	@RequestMapping(value="BuscarConductorPorDNI.htm", method=RequestMethod.GET)
	public @ResponseBody MotConductor buscarConductorPorDNI(@RequestParam("dni") String dni){
		MotConductor conductor=null;
		try {
			conductor=MotConductorDaoFactory.create().findByDNI(dni);
		} catch (MotConductorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return conductor;
	}

	@RequestMapping(value="BuscarConductorPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotConductor buscarConductorPorCodigo(@RequestParam("codigo") String codigo){
		MotConductor conductor=null;
		try {
			conductor=MotConductorDaoFactory.create().findByCodigo(codigo);
		} catch (MotConductorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return conductor;
	}
	
	@RequestMapping(value="BuscarInspectorPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotOperFiscalizador buscarInspectorPorCodigo(@RequestParam("codigo") int codigo){
		MotOperFiscalizador operFiscalizador=null;
		try {
			operFiscalizador=MotOperFiscalizadorDaoFactory.create().findByCodigoInspector(codigo);
		} catch (MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return operFiscalizador;
	}
	
	@RequestMapping(value="BuscarUnidadPorPlaca.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa buscarUnidadPorPlaca(@RequestParam("placa") String placa){
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			unidadEmpresa=MotUnidadEmpresaDaoFactory.create().findPmoCodigoByPlaca_Papeleta(placa);
		} catch (MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return unidadEmpresa;
	}
	
	/*@RequestMapping(value="BuscarUnidadPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotUnidadEmpresa buscarUnidadPorPlaca(@RequestParam("codigo") Long codigo){
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			unidadEmpresa=MotUnidadEmpresaDaoFactory.create().findPmoCodigo(codigo);
		} catch (MotUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return unidadEmpresa;
	}*/

	@RequestMapping(value="BuscarInspectorPorDni.htm", method=RequestMethod.GET)
	public @ResponseBody MotOperFiscalizador buscarInspectorPorDni(@RequestParam("dni") String dni){
		MotOperFiscalizador operFiscalizador=null;
		try {
			operFiscalizador=MotOperFiscalizadorDaoFactory.create().findByDniInspector(dni);
		} catch (MotOperFiscalizadorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return operFiscalizador;
	}
	
	@RequestMapping(value="BuscarTipoMedidaPorInfraccion.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotInfrMedida> buscarTipoMedidaPorInfraccion(@RequestParam("codigo") Long codigo){
		List<MotInfrMedida> list=null;
		try {
			list=MotInfrMedidaDaoFactory.create().findByInfraccion(codigo);
		} catch (MotInfrMedidaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="BuscarPoliciaPorCarnet.htm", method=RequestMethod.GET)
	public @ResponseBody MotPolicia buscarPoliciaPorCarnet(@RequestParam("carnet") String carnet){
		MotPolicia policia=null;
		try {
			policia=MotPoliciaDaoFactory.create().findByCarnet(carnet);
		} catch (MotPoliciaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return policia;
	}
	
	@RequestMapping(value="BuscarPoliciaPorCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody MotPolicia buscarPoliciaPorCodigo(@RequestParam("codigo") int codigo){
		MotPolicia policia=null;
		try {
			policia=MotPoliciaDaoFactory.create().findByCodigo(codigo);
		} catch (MotPoliciaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return policia;
	}
	
	@RequestMapping(value="BuscarInfraccionPorEstado.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotInfraccion> buscarInfraccionPorEstado(@RequestParam("estado") String estado){
		List<MotInfraccion> list=null;
		try {
			list=MotInfraccionDaoFactory.create().findByEstado(estado);
		} catch (MotInfraccionDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="InsertarPapeleta.htm", method=RequestMethod.POST)
	public @ResponseBody MotPapeleta insertarPapeleta(HttpServletRequest request,MotPapeleta papeleta){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
		MotAdjuntarArchivo archivo=(MotAdjuntarArchivo) session.getAttribute("FOTO_PAPELETA");
		try {
			logger.info("Ingreso a insertarPapeleta.htm");
			papeleta.getArchivo().setAdjcodigoD(0L);
			if(archivo!=null){
				papeleta.setArchivo(archivo);
				MotAdjuntarArchivoDaoFactory.create().insert(papeleta.getArchivo());
				MotAuditoriaDaoFactory.create().Insert(
						"MOT_ADJUNTAR_ARCHIVO", papeleta.getArchivo().getAdjcodigoD(),"SP_MOT_INS_ADJUNTARARCHIVO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			}
			MotPapeletaDaoFactory.create().insert(papeleta);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_PAPELETA", papeleta.getPapcodigoD(),"SP_MOT_INS_PAPELETA",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotPapeletaDaoException | MotAdjuntarArchivoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return papeleta;
	}
	
	@RequestMapping(value="Foto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarFoto(HttpServletRequest request){
		logger.info("Ingreso a Papeletas/Foto.htm");
		HttpSession session=request.getSession(true);
		MotAdjuntarArchivo archivo=(MotAdjuntarArchivo) session.getAttribute("FOTO_PAPELETA");
		if(archivo==null){
			archivo=new MotAdjuntarArchivo();
			session.setAttribute("FOTO_PAPELETA", archivo);
			logger.info("Se creo session.setAttribute('FOTO_PAPELETA', archivo);");
		}
		String nombreArchivo="";
		try {
			for(Part part:request.getParts()){
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				if(part.getName().equals("fileFoto")){
					String filename=FileUtil.getFilename(part);
					archivo.setAdjcodigoD(0L);
					archivo.setAdjnombreV(filename);
					archivo.setAdjarchivoB(FileUtil.compress(bs));
					archivo.setAdjextensionV(FileUtil.getExtension(filename));
					nombreArchivo=FileUtil.createTempFile(request, archivo.getAdjnombreV(), bs);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return nombreArchivo;
	}
	
	@RequestMapping(value="ImprimirPdf.htm", method=RequestMethod.GET)
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
	}
	
	@RequestMapping(value="BuscarPorCriterio.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotPapeleta> buscarPorCriterio(String criterio, String texto){
		List<MotPapeleta> list=null;
		try {
			list = MotPapeletaDaoFactory.create().findByCriterio(criterio, texto);
		} catch (MotPapeletaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="ListaPapeletaxCodigo.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotPapeleta> ListaPapeletaxCodigo(Long codigo){
		List<MotPapeleta> list=null;
		try {
			list = MotPapeletaDaoFactory.create().findByPmoCodigo(codigo);
		} catch (MotPapeletaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="ListaConductorxVehiculo.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotUnidConductor> ListaConductorxVehiculo(Long codigo){
		List<MotUnidConductor> list=null;
		try {
			list = MotUnidConductorDaoFactory.create().findByVehiculo(codigo);
		} catch (MotUnidConductorDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody MotPapeleta obtener(Long codigo){
		MotPapeleta papeleta=null;
		try {
			papeleta = MotPapeletaDaoFactory.create().findByCodigo(codigo);
		} catch (MotPapeletaDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return papeleta;
	}
}