package com.munichosica.myapp.dto;

public class MotParadero {
	protected Integer parcodigoI;
	protected String parnombreV;
	protected String parubicacionV;
	protected MotZona zona;
	protected String parestadoC;
	
	
	
	public Integer getParcodigoI() {
		return parcodigoI;
	}
	public void setParcodigoI(Integer parcodigoI) {
		this.parcodigoI = parcodigoI;
	}
	public String getParnombreV() {
		return parnombreV;
	}
	public void setParnombreV(String parnombreV) {
		this.parnombreV = parnombreV;
	}
	public String getParubicacionV() {
		return parubicacionV;
	}
	public void setParubicacionV(String parubicacionV) {
		this.parubicacionV = parubicacionV;
	}
	public void setZona(MotZona zona) {
		this.zona = zona;
	}
	public MotZona getZona() {
		return zona;
	}
	public String getParestadoC() {
		return parestadoC;
	}
	public void setParestadoC(String parestadoC) {
		this.parestadoC = parestadoC;
	}
	
	
}
