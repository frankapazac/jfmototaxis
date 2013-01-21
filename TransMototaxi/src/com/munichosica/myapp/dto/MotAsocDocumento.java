package com.munichosica.myapp.dto;

public class MotAsocDocumento {
	protected Long adocodigoD;
	protected MotEmprAsociado asociado;
	protected MotTipoDocumento tipoDocumento;
	protected MotAdjuntarArchivo archivo;
	public Long getAdocodigoD() {
		return adocodigoD;
	}
	public void setAdocodigoD(Long adocodigoD) {
		this.adocodigoD = adocodigoD;
	}
	public MotEmprAsociado getAsociado() {
		return asociado;
	}
	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
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
	
}
