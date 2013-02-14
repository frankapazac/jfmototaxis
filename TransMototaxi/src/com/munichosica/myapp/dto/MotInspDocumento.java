package com.munichosica.myapp.dto;

public class MotInspDocumento {
	protected Long idocodigoD;
	protected MotInspector inspector;
	protected MotTipoDocumento tipoDocumento;
	protected MotAdjuntarArchivo archivo;
	protected String idoestadoC;
	
	public MotInspDocumento() {
		inspector=new MotInspector();
		tipoDocumento=new MotTipoDocumento();
		archivo=new MotAdjuntarArchivo();
	}
	
	public Long getIdocodigoD() {
		return idocodigoD;
	}
	public void setIdocodigoD(Long idocodigoD) {
		this.idocodigoD = idocodigoD;
	}
	public MotInspector getInspector() {
		return inspector;
	}
	public void setInspector(MotInspector inspector) {
		this.inspector = inspector;
	}
	public MotTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(MotTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public MotAdjuntarArchivo getArchivo() {
		return archivo;
	}
	public void setArchivo(MotAdjuntarArchivo archivo) {
		this.archivo = archivo;
	}
	public String getIdoestadoC() {
		return idoestadoC;
	}
	public void setIdoestadoC(String idoestadoC) {
		this.idoestadoC = idoestadoC;
	}
}
