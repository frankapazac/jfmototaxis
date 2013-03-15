package com.munichosica.myapp.dto;

public class MotTipoDocumento {
	protected int mtdcodigoI;
	protected String mtdnombreV;
	protected String mtdtablaC;
	protected String mtdEstadoC;
	protected String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getMtdcodigoI() {
		return mtdcodigoI;
	}
	public void setMtdcodigoI(int mtdcodigoI) {
		this.mtdcodigoI = mtdcodigoI;
	}
	public String getMtdnombreV() {
		return mtdnombreV;
	}
	public void setMtdnombreV(String mtdnombreV) {
		this.mtdnombreV = mtdnombreV;
	}
	public String getMtdtablaC() {
		return mtdtablaC;
	}
	public void setMtdtablaC(String mtdtablaC) {
		this.mtdtablaC = mtdtablaC;
	}
	public String getMtdEstadoC() {
		return mtdEstadoC;
	}
	public void setMtdEstadoC(String mtdEstadoC) {
		this.mtdEstadoC = mtdEstadoC;
	}
}
