package com.munichosica.myapp.dto;

public class MotEmpadronamiento {
	protected Long epocodigoD;
	protected MotEmpresa empresa;
	protected MotUnidadEmpresa unidadEmpresa;
	protected String empfechainicioF;
	protected String empfechaceseF;
	protected String empvigenciaC;
	protected String empobservacionesV;
	protected String empestadoC;
	
	protected MotEmprAsociado asociado;
	
	public MotEmpadronamiento() {
		empresa=new MotEmpresa();
		unidadEmpresa=new MotUnidadEmpresa();
		
		asociado=new MotEmprAsociado();
	}
	
	public MotEmprAsociado getAsociado() {
		return asociado;
	}

	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
	}

	public String getEmpestadoC() {
		return empestadoC;
	}
	public void setEmpestadoC(String empestadoC) {
		this.empestadoC = empestadoC;
	}
	public Long getEpocodigoD() {
		return epocodigoD;
	}
	public void setEpocodigoD(Long epocodigoD) {
		this.epocodigoD = epocodigoD;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public MotUnidadEmpresa getUnidadEmpresa() {
		return unidadEmpresa;
	}
	public void setUnidadEmpresa(MotUnidadEmpresa unidadEmpresa) {
		this.unidadEmpresa = unidadEmpresa;
	}
	public String getEmpfechainicioF() {
		return empfechainicioF;
	}
	public void setEmpfechainicioF(String empfechainicioF) {
		this.empfechainicioF = empfechainicioF;
	}
	public String getEmpfechaceseF() {
		return empfechaceseF;
	}
	public void setEmpfechaceseF(String empfechaceseF) {
		this.empfechaceseF = empfechaceseF;
	}
	public String getEmpvigenciaC() {
		return empvigenciaC;
	}
	public void setEmpvigenciaC(String empvigenciaC) {
		this.empvigenciaC = empvigenciaC;
	}
	public String getEmpobservacionesV() {
		return empobservacionesV;
	}
	public void setEmpobservacionesV(String empobservacionesV) {
		this.empobservacionesV = empobservacionesV;
	}
	
}
