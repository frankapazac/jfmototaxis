package com.munichosica.myapp.dto;

public class MotConductor {
	protected Long concodigoD;
	protected Long percodigoD;
	protected MotPersona persona;
	
	public MotConductor() {
		persona=new MotPersona();
	}
	
	public Long getConcodigoD() {
		return concodigoD;
	}
	public void setConcodigoD(Long concodigoD) {
		this.concodigoD = concodigoD;
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
