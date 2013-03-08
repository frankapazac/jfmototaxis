package com.munichosica.myapp.dto;

public class MotInteInventario {
	protected Long bivcodigoD;
	protected MotInternamiento internamiento;
	protected MotInteInventarioTipo inventarioTipo;
	protected String bivestadoC;
	protected int bivcantidadI;
	
	public MotInteInventario() {
		internamiento=new MotInternamiento();
		inventarioTipo=new MotInteInventarioTipo();
	}
	
	public Long getBivcodigoD() {
		return bivcodigoD;
	}
	public void setBivcodigoD(Long bivcodigoD) {
		this.bivcodigoD = bivcodigoD;
	}
	public MotInternamiento getInternamiento() {
		return internamiento;
	}
	public void setInternamiento(MotInternamiento internamiento) {
		this.internamiento = internamiento;
	}
	public MotInteInventarioTipo getInventarioTipo() {
		return inventarioTipo;
	}

	public void setInventarioTipo(MotInteInventarioTipo inventarioTipo) {
		this.inventarioTipo = inventarioTipo;
	}

	public String getBivestadoC() {
		return bivestadoC;
	}
	public void setBivestadoC(String bivestadoC) {
		this.bivestadoC = bivestadoC;
	}
	public void setBivcantidadI(int bivcantidadI) {
		this.bivcantidadI = bivcantidadI;
	}
	public int getBivcantidadI() {
		return bivcantidadI;
	}
}
