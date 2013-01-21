package com.munichosica.myapp.dto;

public class MotEmpParadero {
		
	protected Long epacodigo_I;
	protected MotParadero paradero;
	protected MotEmpresa empresa;
	
	public Long getEpacodigo_I() {
		return epacodigo_I;
	}
	public void setEpacodigo_I(Long epacodigo_I) {
		this.epacodigo_I = epacodigo_I;
	}
	public MotParadero getParadero() {
		return paradero;
	}
	public void setParadero(MotParadero paradero) {
		this.paradero = paradero;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	
}
