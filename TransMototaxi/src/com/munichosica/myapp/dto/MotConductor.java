package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MotConductor {
	protected Long concodigoD;
	protected Long percodigoD;
	protected MotPersona persona;
	protected List<MotCondDocumento> documentos;
	protected MotAdjuntarArchivo archivo;
	
	public MotConductor() {
		persona=new MotPersona();
		documentos=new ArrayList<MotCondDocumento>();
		archivo=new MotAdjuntarArchivo();
	}
	
	public List<MotCondDocumento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<MotCondDocumento> documentos) {
		this.documentos = documentos;
	}

	public MotAdjuntarArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(MotAdjuntarArchivo archivo) {
		this.archivo = archivo;
	}

	public Long getConcodigoD() {
		return concodigoD;
	}
	public void setConcodigoD(Long concodigoD) {
		this.concodigoD = concodigoD;
	}
	public Long getPercodigoD() {
		return percodigoD;
	}
	public void setPercodigoD(Long percodigoD) {
		this.percodigoD = percodigoD;
	}
	public MotPersona getPersona() {
		return persona;
	}
	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}
	


}
