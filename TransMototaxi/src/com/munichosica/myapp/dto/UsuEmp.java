package com.munichosica.myapp.dto;

public class UsuEmp {
	private int usecodigoI;
	public int getUsecodigoI() {
		return usecodigoI;
	}
	public void setUsecodigoI(int usecodigoI) {
		this.usecodigoI = usecodigoI;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	private Usuario usuario;
	private MotEmpresa empresa;
}
