package com.munichosica.myapp.dto;

public class MotPropUnidadEmpresa {
	
	protected Long pmocodigoD;
	protected Long unecodigoD;
	protected Long procodigoD;
	
	protected MotUnidadEmpresa mototaxi;
	protected MotEmprAsociado asociado;
	
	public Long getPmocodigoD() {
		return pmocodigoD;
	}
	public void setPmocodigoD(Long pmocodigoD) {
		this.pmocodigoD = pmocodigoD;
	}
	public Long getUnecodigoD() {
		return unecodigoD;
	}
	public void setUnecodigoD(Long unecodigoD) {
		this.unecodigoD = unecodigoD;
	}
	public Long getProcodigoD() {
		return procodigoD;
	}
	public void setProcodigoD(Long procodigoD) {
		this.procodigoD = procodigoD;
	}
	public MotUnidadEmpresa getMototaxi() {
		return mototaxi;
	}
	public void setMototaxi(MotUnidadEmpresa mototaxi) {
		this.mototaxi = mototaxi;
	}
	public MotEmprAsociado getAsociado() {
		return asociado;
	}
	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
	}

	

}
