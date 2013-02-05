package com.munichosica.myapp.dto;

public class MotUnidDocumento {
	protected Long ptdcodigoD;
	protected MotTipoDocumento tipoDocumento;
	protected MotAdjuntarArchivo adjuntarArchivo;
	protected MotPropUnidadEmpresa unidadEmpresa;
	
	public MotUnidDocumento() {
		tipoDocumento=new MotTipoDocumento();
		adjuntarArchivo=new MotAdjuntarArchivo();
		unidadEmpresa=new MotPropUnidadEmpresa();
	}
	
	public Long getPtdcodigoD() {
		return ptdcodigoD;
	}
	public void setPtdcodigoD(Long ptdcodigoD) {
		this.ptdcodigoD = ptdcodigoD;
	}
	public MotTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(MotTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public MotAdjuntarArchivo getAdjuntarArchivo() {
		return adjuntarArchivo;
	}
	public void setAdjuntarArchivo(MotAdjuntarArchivo adjuntarArchivo) {
		this.adjuntarArchivo = adjuntarArchivo;
	}
	public MotPropUnidadEmpresa getUnidadEmpresa() {
		return unidadEmpresa;
	}
	public void setUnidadEmpresa(MotPropUnidadEmpresa unidadEmpresa) {
		this.unidadEmpresa = unidadEmpresa;
	}
}
