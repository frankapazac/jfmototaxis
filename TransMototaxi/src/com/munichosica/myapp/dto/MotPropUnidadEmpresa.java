package com.munichosica.myapp.dto;

public class MotPropUnidadEmpresa {
	
	protected Long pmocodigoD;
	protected MotEmprAsociado asociado;
	protected MotUnidadEmpresa unidadempresa;
	protected String pmoestadoC;
	
	public MotPropUnidadEmpresa() {
		asociado=new MotEmprAsociado();
		unidadempresa=new MotUnidadEmpresa();
	}
	
	public String getPmoestadoC() {
		return pmoestadoC;
	}
	public void setPmoestadoC(String pmoestadoC) {
		this.pmoestadoC = pmoestadoC;
	}
	public Long getPmocodigoD() {
		return pmocodigoD;
	}
	public void setPmocodigoD(Long pmocodigoD) {
		this.pmocodigoD = pmocodigoD;
	}
	public MotEmprAsociado getAsociado() {
		return asociado;
	}
	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
	}
	public MotUnidadEmpresa getUnidadempresa() {
		return unidadempresa;
	}
	public void setUnidadempresa(MotUnidadEmpresa unidadempresa) {
		this.unidadempresa = unidadempresa;
	}

	

}
