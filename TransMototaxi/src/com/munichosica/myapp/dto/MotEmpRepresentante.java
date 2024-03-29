package com.munichosica.myapp.dto;

public class MotEmpRepresentante {
	protected Long repcodigoI;
	protected MotEmpPropietario empProp;
	protected MotEmpresa empresa;
	protected String repdescripcionV;
	protected String repfechainicioF;
	protected String repfechaceseF;
	protected String repobservaciones;
	protected String repestadoC;
	
	public String getRepestadoC() {
		return repestadoC;
	}

	public void setRepestadoC(String repestadoC) {
		this.repestadoC = repestadoC;
	}

	public MotEmpRepresentante() {
		empProp=new MotEmpPropietario();
		empresa=new MotEmpresa();
	}
	
	public Long getRepcodigoI() {
		return repcodigoI;
	}
	public void setRepcodigoI(Long repcodigoI) {
		this.repcodigoI = repcodigoI;
	}
	public MotEmpPropietario getEmpProp() {
		return empProp;
	}
	public void setEmpProp(MotEmpPropietario empProp) {
		this.empProp = empProp;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public String getRepdescripcionV() {
		return repdescripcionV;
	}
	public void setRepdescripcionV(String repdescripcionV) {
		this.repdescripcionV = repdescripcionV;
	}
	public String getRepfechainicioF() {
		return repfechainicioF;
	}
	public void setRepfechainicioF(String repfechainicioF) {
		this.repfechainicioF = repfechainicioF;
	}
	public String getRepfechaceseF() {
		return repfechaceseF;
	}
	public void setRepfechaceseF(String repfechaceseF) {
		this.repfechaceseF = repfechaceseF;
	}
	public String getRepobservaciones() {
		return repobservaciones;
	}
	public void setRepobservaciones(String repobservaciones) {
		this.repobservaciones = repobservaciones;
	}
	
}
