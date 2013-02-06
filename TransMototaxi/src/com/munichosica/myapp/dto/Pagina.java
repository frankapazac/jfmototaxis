package com.munichosica.myapp.dto;

public class Pagina {
	protected int pagcodigoI;
	protected String pagnombreV;
	protected String pagurlV;
	protected byte[] pagimagenB;
	protected Rol rol;
	
	public Pagina() {
		rol=new Rol();
	}
	
	public int getPagcodigoI() {
		return pagcodigoI;
	}
	public void setPagcodigoI(int pagcodigoI) {
		this.pagcodigoI = pagcodigoI;
	}
	public String getPagnombreV() {
		return pagnombreV;
	}
	public void setPagnombreV(String pagnombreV) {
		this.pagnombreV = pagnombreV;
	}
	public String getPagurlV() {
		return pagurlV;
	}
	public void setPagurlV(String pagurlV) {
		this.pagurlV = pagurlV;
	}
	public byte[] getPagimagenB() {
		return pagimagenB;
	}
	public void setPagimagenB(byte[] pagimagenB) {
		this.pagimagenB = pagimagenB;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
