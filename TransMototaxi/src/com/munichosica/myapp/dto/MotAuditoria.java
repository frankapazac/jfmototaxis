package com.munichosica.myapp.dto;

public class MotAuditoria {
	protected Long audcodigoD;
	protected String tabla;
	protected String tipo;
	protected Long codigo;
	protected String procedimiento;
	protected String usuario;
	protected String fecha;
	protected String ip;
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}
	public String getProcedimiento() {
		return procedimiento;
	}
	public Long getAudcodigoD() {
		return audcodigoD;
	}
	public void setAudcodigoD(Long audcodigoD) {
		this.audcodigoD = audcodigoD;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
