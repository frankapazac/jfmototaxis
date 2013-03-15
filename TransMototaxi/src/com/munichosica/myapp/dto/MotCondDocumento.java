package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MotCondDocumento {
	
	protected Long cdocodigoD;	
	protected String cdocategoriaV;
	protected String cdoclaseV;
	
	protected MotAdjuntarArchivo adjuntarArchivo;

	protected MotConductor conductor;
	protected MotTipoDocumento tipoDocumento;
	protected MotEmpConductor empresaConductor;
	protected MotPapeleta papeleta;
	
	public MotCondDocumento() {
		adjuntarArchivo=new MotAdjuntarArchivo();
		conductor=new MotConductor();
		tipoDocumento=new MotTipoDocumento();
		empresaConductor = new MotEmpConductor();
	}
	
	public MotPapeleta getPapeleta() {
		return papeleta;
	}

	public void setPapeleta(MotPapeleta papeleta) {
		this.papeleta = papeleta;
	}
	
	public Long getCdocodigoD() {
		return cdocodigoD;
	}
	public void setCdocodigoD(Long cdocodigoD) {
		this.cdocodigoD = cdocodigoD;
	}
	public String getCdocategoriaV() {
		return cdocategoriaV;
	}
	public void setCdocategoriaV(String cdocategoriaV) {
		this.cdocategoriaV = cdocategoriaV;
	}
	public String getCdoclaseV() {
		return cdoclaseV;
	}
	public void setCdoclaseV(String cdoclaseV) {
		this.cdoclaseV = cdoclaseV;
	}
	public MotAdjuntarArchivo getAdjuntarArchivo() {
		return adjuntarArchivo;
	}
	public void setAdjuntarArchivo(MotAdjuntarArchivo adjuntarArchivo) {
		this.adjuntarArchivo = adjuntarArchivo;
	}
	public MotConductor getConductor() {
		return conductor;
	}
	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}
	public MotTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(MotTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public MotEmpConductor getEmpresaConductor() {
		return empresaConductor;
	}
	
	public void setEmpresaConductor(MotEmpConductor empresaConductor) {
		this.empresaConductor = empresaConductor;
	}
	
}
