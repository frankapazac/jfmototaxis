package com.munichosica.myapp.dto;

import org.springframework.context.annotation.Scope;

public class Usuario {
	private int usucodigoI;
	private String usuusuarioV;
	private String usurolV;
	private MotEmpresa empresa;
	
	public int getUsucodigoI() {
		return usucodigoI;
	}
	public void setUsucodigoI(int usucodigoI) {
		this.usucodigoI = usucodigoI;
	}
	public String getUsuusuarioV() {
		return usuusuarioV;
	}
	public void setUsuusuarioV(String usuusuarioV) {
		this.usuusuarioV = usuusuarioV;
	}
	public String getUsurolV() {
		return usurolV;
	}
	public void setUsurolV(String usurolV) {
		this.usurolV = usurolV;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	
}
