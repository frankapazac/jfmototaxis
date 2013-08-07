package com.munichosica.myapp.dto;

public class Usuario {
	private Integer usucodigoI;
	private String usuusuarioV;
	private Rol rol;
	private MotPersona persona;
	private String pass;
	private String newPass;
	private MotEmpresa empresa;
    private MotZona zona;

	public MotZona getZona() {
		return zona;
	}
	public void setZona(MotZona zona) {
		this.zona = zona;
	}
	
	public Usuario() {
		empresa=new MotEmpresa();
		zona=new MotZona();
		rol=new Rol();
		persona=new MotPersona();
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public String getUsuusuarioV() {
		return usuusuarioV;
	}
	public void setUsuusuarioV(String usuusuarioV) {
		this.usuusuarioV = usuusuarioV;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public MotPersona getPersona() {
		return persona;
	}
	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}
	public Integer getUsucodigoI() {
		return usucodigoI;
	}
	public void setUsucodigoI(Integer usucodigoI) {
		this.usucodigoI = usucodigoI;
	}
	
}
