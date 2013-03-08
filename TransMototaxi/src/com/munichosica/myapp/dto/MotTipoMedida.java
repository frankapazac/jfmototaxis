package com.munichosica.myapp.dto;

public class MotTipoMedida {
	protected int tmecodigoI;
	protected String tmedescripcionV;
	protected String tmeestadoC;
	protected String Lado;
	
	
	public String getLado() {
		return Lado;
	}
	public void setLado(String lado) {
		Lado = lado;
	}

	
	public int getTmecodigoI() {
		return tmecodigoI;
	}
	public void setTmecodigoI(int tmecodigoI) {
		this.tmecodigoI = tmecodigoI;
	}
	public String getTmedescripcionV() {
		return tmedescripcionV;
	}
	public void setTmedescripcionV(String tmedescripcionV) {
		this.tmedescripcionV = tmedescripcionV;
	}
	public String getTmeestadoC() {
		return tmeestadoC;
	}
	public void setTmeestadoC(String tmeestadoC) {
		this.tmeestadoC = tmeestadoC;
	}
	
}
