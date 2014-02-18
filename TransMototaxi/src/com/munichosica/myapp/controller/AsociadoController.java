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
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;
import com.munichosica.myapp.factory.MotAdjuntarArchivoDaoFactory;
import com.munichosica.myapp.factory.MotAsocDocumentoDaoFactory;
import com.munichosica.myapp.factory.MotAuditoriaDaoFactory;
import com.munichosica.myapp.factory.MotEmprAsociadoDaoFactory;
import com.munichosica.myapp.factory.MotPropUnidadEmpresaDaoFactory;
import com.munichosica.myapp.util.FileUtil;
import com.munichosica.myapp.util.IpUtils;
import com.munichosica.myapp.util.UTFEncodingUtil;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;

@Controller
@RequestMapping("/Asociados")
public class AsociadoController {
	
	protected static final Logger logger = Logger.getLogger( AsociadoController.class );
	
	@RequestMapping(value="BuscarNroPlaca.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotPropUnidadEmpresa> buscarNroPlaca(String placa){
		logger.info("Ingreso a BuscarNroPlaca.htm PLACA="+placa);
		List<MotPropUnidadEmpresa> unidadEmpresa=null;
		try {
			unidadEmpresa = MotPropUnidadEmpresaDaoFactory.create().buscarExisteNroPlaca(placa);
		} catch (MotPropUnidadEmpresaDaoException e) {
			logger.error(e.getMessage(),e);
		}
		return unidadEmpresa;
	}
	
	@RequestMapping(value="GuardarCese.htm", method=RequestMethod.POST)
	public @ResponseBody String guardarCese(HttpServletRequest request,MotEmprAsociado asociado){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "ERROR";
		}
		
		try {
			MotEmprAsociadoDaoFactory.create().guardarCese(asociado);
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMPR_ASOCIADO",asociado.getAsocodigoD(),"SP_UPD_EMP_ASOCIADO_CESE",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
		} catch (MotEmprAsociadoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage(),e);
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping(value="ExisteAsociadoPorDNI.htm", method=RequestMethod.GET)
	public @ResponseBody List<MotEmprAsociado> existeAsociadoPorDNI(String dni){
		logger.info("Ingreso a ExisteAsociadoPorDNI.htm CODIGO="+dni);
		List<MotEmprAsociado> lista=null;
		try {
			lista = MotEmprAsociadoDaoFactory.create().ExisteAsociadoPorDNI(dni);
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		
		return lista;
	}
	
	@RequestMapping(value="ImprimirTodosAsociadosActivos.htm", method=RequestMethod.GET)
	public ModelAndView imprimirTodosAsociadosActivos(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		ModelAndView mav=null;
		try{
			Map<String, Object> parameters=new HashMap<String, Object>();
			parameters.put("EMPCODIGO", usuario.getEmpresa().getEmpcodigoD());
			mav=new ModelAndView("reportAfiliados", parameters);
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
		}
		return mav;
	}
	
	@RequestMapping(value="Listar.htm", method=RequestMethod.POST)
	public @ResponseBody List<MotEmprAsociado> listar(HttpServletRequest request,
			@RequestParam("criterio") String criterio, @RequestParam("texto") String texto){
		logger.info("Ingreso a Asociados/Listar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		List<MotEmprAsociado> list=null;
		try {
			list=MotEmprAsociadoDaoFactory.create().findByCriterio(criterio, 
					texto, usuario.getEmpresa().getEmpcodigoD());
			logger.info("MotEmprAsociadoDaoFactory.create().findByCriterio(criterio, texto, codigo); Complete");
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="Procesar.htm",method=RequestMethod.POST)
	public @ResponseBody MotEmprAsociado procesar(HttpServletRequest request, MotEmprAsociado asociado){
		logger.info("Ingreso a Asociados/Procesar.htm");
		try {
			HttpSession session=request.getSession(true);
			Usuario usuario=(Usuario) session.getAttribute("USUARIO");
			if(usuario==null){
				SecurityContextHolder.getContext().setAuthentication(null);
				return null;
			}
			DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
			DocumentoSession foto=(DocumentoSession) session.getAttribute("FOTO");
			/*if(documentos==null||documentos.getList().size()<3){
				throw new MotEmprAsociadoDaoException("Aun no se agregaron documentos");
			}*/
			
			asociado.setEmpresa(usuario.getEmpresa());
			UTFEncodingUtil.decodeObjectUTF(asociado);
			UTFEncodingUtil.decodeObjectUTF(asociado.getPersona());
			MotEmprAsociadoDaoFactory.create().procesar(asociado);
			logger.info("MotEmprAsociadoDaoFactory.create().procesar(asociado); Complete codigo: "+asociado.getAsocodigoD());
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMPR_ASOCIADO",asociado.getAsocodigoD(),"SP_MOT_INS_EMPRASOCIADO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
			if(documentos!=null){
				if(documentos.getList()!=null && documentos.getList().size()>0){
					for(MotAsocDocumento docum:documentos.getList()){
						docum.setAsociado(asociado);
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getArchivo());
						MotAsocDocumentoDaoFactory.create().insert(docum);
						logger.info("MotAsocDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getAdocodigoD());
						MotAuditoriaDaoFactory.create().Insert(
								"MOT_ASOC_DOCUMENTO", docum.getAdocodigoD(),"SP_MOT_INS_ASOCDOCUMENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
					}
				}
			}
			if(foto!=null){
				if(foto.getList()!=null && foto.getList().size()>0){
					for(MotAsocDocumento docum:foto.getList()){
						docum.setAsociado(asociado);
						MotAdjuntarArchivoDaoFactory.create().insert(docum.getArchivo());
						MotAsocDocumentoDaoFactory.create().insert(docum);
						logger.info("MotAsocDocumentoDaoFactory.create().insert(docum); Complete codigo: "+docum.getAdocodigoD());
						MotAuditoriaDaoFactory.create().Insert(
								"MOT_ASOC_DOCUMENTO", docum.getAdocodigoD(),"SP_MOT_INS_ASOCDOCUMENTO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));
					}
				}
			}
		} catch (MotEmprAsociadoDaoException | MotAdjuntarArchivoDaoException | MotAsocDocumentoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return asociado;
	}
	
	@RequestMapping(value="ObtenerCese.htm", method=RequestMethod.GET)
	public @ResponseBody MotEmprAsociado obtener(Long codigo){
		MotEmprAsociado asociado=null;
		try {
			asociado = MotEmprAsociadoDaoFactory.create().obtenerAsociadoCesar(codigo);
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return asociado;
	}
	
	@RequestMapping(value="Obtener.htm", method=RequestMethod.GET)
	public @ResponseBody AsociadoUtil obtener(HttpServletRequest request,@RequestParam("codigo") Long codigo){
		//NO OLVIDAR LIMPIAR LA SESSION DOCUMENTOS
		logger.info("Ingreso a Asociados/Obtener.htm");
		HttpSession session=request.getSession(true);
		DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
		DocumentoSession foto=(DocumentoSession) session.getAttribute("FOTO");
		if(documentos!=null){
			documentos.getList().clear();
			logger.info("documentos.getList().clear(); Session DOCUMENTOS limpiada completamente.");
		}
		if(foto!=null){
			foto.getList().clear();
			logger.info("documentos.getList().clear(); Session FOTO limpiada completamente.");
		}
		AsociadoUtil asociadoUtil=null;
		try {
			asociadoUtil=new AsociadoUtil();
			MotEmprAsociado asociado=MotEmprAsociadoDaoFactory.create().findByPrimaryKey(codigo);
			List<MotAsocDocumento> listDocumentos=MotAsocDocumentoDaoFactory.create().findByIdAsociado(codigo);
			asociadoUtil.setAsociado(asociado);
			asociadoUtil.setListDocumentos(listDocumentos);
			logger.info("MotEmprAsociadoDaoFactory.create().findByPrimaryKey(codigo); Complete");
			String nombreArchivo=null;
			System.out.println(asociado);
			System.out.println(asociado.getFoto());
			if(asociado.getFoto().getAdjarchivoB()!=null){
				nombreArchivo=FileUtil.createTempFile(request, asociado.getFoto().getAdjnombreV(),asociado.getFoto().getAdjarchivoB());
				asociado.getFoto().setAdjnombreV(nombreArchivo);
			}
		} catch (MotEmprAsociadoDaoException | MotAsocDocumentoDaoException e) {
			logger.error(e.getMessage());
		}
		return asociadoUtil;
	}
	
	@RequestMapping(value="Eliminar.htm",method=RequestMethod.GET)
	public String eliminar(HttpServletRequest request, @RequestParam("codigo") Long codigo){
		logger.info("Ingreso a Asociados/Eliminar.htm");
		HttpSession session=request.getSession(true);
		Usuario usuario=(Usuario) session.getAttribute("USUARIO");
		if(usuario==null){
			SecurityContextHolder.getContext().setAuthentication(null);
			return "Error";
		}
		try {
			MotEmprAsociadoDaoFactory.create().delete(codigo);
			logger.info("MotEmprAsociadoDaoFactory.create().delete(codigo); Complete");
			MotAuditoriaDaoFactory.create().Insert(
					"MOT_EMPR_ASOCIADO", codigo,"SP_MOT_DEL_EMPRASOCIADO",usuario.getUsuusuarioV(),IpUtils.getIpFromRequest(request));

		} catch (MotEmprAsociadoDaoException | MotAuditoriaDaoException e) {
			logger.error(e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping(value="Foto.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public @ResponseBody String agregarFoto(HttpServletRequest request){
		logger.info("Ingreso a Asociados/Foto.htm");
		HttpSession session=request.getSession(true);
		DocumentoSession documentos=(DocumentoSession) session.getAttribute("FOTO");
		if(documentos==null){
			documentos=new DocumentoSession();
			session.setAttribute("FOTO", documentos);
			logger.info("Se creo session.setAttribute('DOCUMENTOS', documentos);");
		}
		String nombreArchivo="";
		try {
			for(Part part:request.getParts()){
				MotAsocDocumento documento=new MotAsocDocumento();
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				if(part.getName().equals("fileFotoAsociado")){
					String filename=FileUtil.getFilename(part);
					documento.getArchivo().setAdjcodigoD(0L);
					documento.getArchivo().setAdjnombreV(filename);
					documento.getArchivo().setAdjarchivoB(FileUtil.compress(bs));
					documento.getArchivo().setAdjextensionV(FileUtil.getExtension(filename));
					nombreArchivo=FileUtil.createTempFile(request, documento.getArchivo().getAdjnombreV(), bs);
				}
				documento.getTipoDocumento().setMtdcodigoI(16);
				documentos.add(documento);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return nombreArchivo;
	}
	
	@RequestMapping(value="Documento.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public String agregarDocumento(HttpServletRequest request){
		logger.info("Ingreso a Asociados/Documento.htm");
		HttpSession session=request.getSession(true);
		DocumentoSession documentos=(DocumentoSession) session.getAttribute("DOCUMENTOS");
		if(documentos==null){
			documentos=new DocumentoSession();
			session.setAttribute("DOCUMENTOS", documentos);
			logger.info("Se creo session.setAttribute('DOCUMENTOS', documentos);");
		}
		try {
			MotAsocDocumento documento=new MotAsocDocumento();
			MotTipoDocumento tipoDocumento=new MotTipoDocumento();
			MotAdjuntarArchivo archivo=new MotAdjuntarArchivo();
			for(Part part:request.getParts()){
				//System.out.println("NOMBRE: "+part.getName());
				//System.out.println("FILENAME: "+getFilename(part));
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
				switch(part.getName()){
					case "txtCodDocumento"://System.out.println("txtCodDocumento");
						tipoDocumento.setMtdcodigoI(Integer.parseInt(new String(bs,"UTF8")));
						break;
					case "txtCodArchivo"://System.out.println("txtCodDocumento");
						archivo.setAdjcodigoD(Long.parseLong(new String(bs,"UTF8")));
						break;
					case "txtNumDocumento"://System.out.println("txtNumDocumento");
						archivo.setAdjnumeroV(new String(bs,"UTF8"));
						break;
					case "txtFechaEmision"://System.out.println("txtFechaEmision");
						archivo.setAdjfechaemisionF(new String(bs,"UTF8"));
						break;
					case "txtFechaCaducidad"://System.out.println("txtFechaCaducidad");
						archivo.setAdjfechacaducidadF(new String(bs,"UTF8"));
						break;
					case "fileDocumento"://System.out.println("fileDocumento");
						String filename=FileUtil.getFilename(part);
						archivo.setAdjnombreV(filename);
						archivo.setAdjarchivoB(FileUtil.compress(bs));
						archivo.setAdjextensionV(FileUtil.getExtension(filename));
						break;
				}
			}
			documento.setTipoDocumento(tipoDocumento);
			documento.setArchivo(archivo);
			documentos.add(documento);
			logger.info("Documentos subidos en total: "+documentos.getList().size());
		} catch (Exception e) {
			logger.error("ERROR: "+e.getMessage());
		}
		return "Success";
	}
	
	//public ModelAndView imprimir(@RequestParam("codigo") Long codigo){
	@RequestMapping(value="Imprimir.htm", method=RequestMethod.GET)
	public ModelAndView imprimir(Long codigo){
		ModelAndView mav=null;
		try {
			logger.info("Ingreso a Imprimir.htm Codigo="+codigo);
			Map<String, Object> parameter=new HashMap<String, Object>();
			MotEmprAsociado asociado=MotEmprAsociadoDaoFactory.create().findByPrimaryKey(codigo);
			parameter.put("Afiliado", asociado);
			mav=new ModelAndView("reportAfiliado",parameter);
		} catch (MotEmprAsociadoDaoException e) {
			logger.error(e.getMessage(), e);
		}
		return mav;
	}
	
}
