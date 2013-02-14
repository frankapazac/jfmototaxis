package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MotInspector {
	protected int inscodigoI;
	protected String insestadoC;
	protected MotPersona persona;
	protected MotAdjuntarArchivo foto;
	protected List<MotInspDocumento> documentos;
	
	public MotInspector(){
		persona=new MotPersona();
		foto=new MotAdjuntarArchivo();
		documentos=new ArrayList<MotInspDocumento>();
	}

	public void setFoto(MotAdjuntarArchivo foto) {
		this.foto = foto;
	}
	public MotAdjuntarArchivo getFoto() {
		return foto;
	}
	public List<MotInspDocumento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<MotInspDocumento> documentos) {
		this.documentos = documentos;
	}
	public int getInscodigoI() {
		return inscodigoI;
	}
	public void setInscodigoI(int inscodigoI) {
		this.inscodigoI = inscodigoI;
	}
	public String getInsestadoC() {
		return insestadoC;
	}
	public void setInsestadoC(String insestadoC) {
		this.insestadoC = insestadoC;
	}
	public MotPersona getPersona() {
		return persona;
	}
	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}
}
