package com.munichosica.myapp.dto;

public class MotEmpPropietario {

	protected Long procodigoD;
	protected Long percodigoD;
	protected MotPersona persona;
	
	public MotEmpPropietario() {
		persona=new MotPersona();
	}
	
	public Long getProcodigoD() {
		return procodigoD;
	}
	public void setProcodigoD(Long procodigoD) {
		this.procodigoD = procodigoD;
	}
	public Long getPercodigoD() {
		return percodigoD;
	}
	public void setPercodigoD(Long percodigoD) {
		this.percodigoD = percodigoD;
	}
	public MotPersona getPersona() {
		return persona;
	}
	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}
	
}
