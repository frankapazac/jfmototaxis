package com.munichosica.myapp.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.util.FileUtil;

@Controller
@RequestMapping("/EmpresaDocumento")
public class EmpresaDocumentoController {
	
	@RequestMapping(value="Agregar.htm", method=RequestMethod.POST, headers="content-type=multipart/form-data")
	public String agregarDocumento(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		EmpresaDocumentoSession documentoSession=(EmpresaDocumentoSession) session.getAttribute("DOCUMENTOS_EMPRESA");
		if(documentoSession==null){
			documentoSession=new EmpresaDocumentoSession();
			session.setAttribute("DOCUMENTOS_EMPRESA", documentoSession);
		}
		MotEmpDocumento documento=new MotEmpDocumento();
		MotTipoDocumento tipoDocumento=new MotTipoDocumento();
		MotAdjuntarArchivo archivo=new MotAdjuntarArchivo();
		try {
			for(Part part:request.getParts()){
				//System.out.println("NOMBRE: "+part.getName());
				//System.out.println("FILENAME: "+FileUtil.getFilename(part));
				InputStream inputStream=request.getPart(part.getName()).getInputStream();
				int i=inputStream.available();
				byte[] bs=new byte[i];
				inputStream.read(bs);
					switch (part.getName()) {
						case "txtFotoCodigo":tipoDocumento.setMtdcodigoI(Integer.parseInt(new String(bs,"UTF8")));
							break;
						case "flFoto":
							String filename=FileUtil.getFilename(part);
							archivo.setAdjcodigoD(0L);
							archivo.setAdjnombreV(filename);
							archivo.setAdjarchivoB(FileUtil.compress(bs));
							archivo.setAdjextensionV(FileUtil.getExtension(filename));
							break;
					}
				}
				documento.setAdjuntarArchivo(archivo);
				documento.setTipoDocumento(tipoDocumento);
				documentoSession.add(documento);
				//System.out.println("Documentos subidos en total: "+documentoSession.getList().size());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return "Success";
	}
}
