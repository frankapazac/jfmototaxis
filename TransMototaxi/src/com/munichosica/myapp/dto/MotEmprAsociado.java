package com.munichosica.myapp.dto;

import java.util.List;

/**
 * @author FAPAZA
 *
 */
public class MotEmprAsociado {
	protected Long asocodigoD;
	protected MotPersona persona;
	protected String asorucV;
	protected String asorazonsocialV;
	protected MotEmpresa empresa;
	protected String asoestadoC;
	protected MotAdjuntarArchivo foto;
	
	public MotEmprAsociado() {
		persona=new MotPersona();
		foto=new MotAdjuntarArchivo();
	}
	public void setFoto(MotAdjuntarArchivo foto) {
		this.foto = foto;
	}
	public MotAdjuntarArchivo getFoto() {
		return foto;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public Long getAsocodigoD() {
		return asocodigoD;
	}
	public void setAsocodigoD(Long asocodigoD) {
		this.asocodigoD = asocodigoD;
	}
	public MotPersona getPersona() {
		return persona;
	}
	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}
	public String getAsorucV() {
		return asorucV;
	}
	public void setAsorucV(String asorucV) {
		this.asorucV = asorucV;
	}
	public String getAsorazonsocialV() {
		return asorazonsocialV;
	}
	public void setAsorazonsocialV(String asorazonsocialV) {
		this.asorazonsocialV = asorazonsocialV;
	}
	public String getAsoestadoC() {
		return asoestadoC;
	}
	public void setAsoestadoC(String asoestadoC) {
		this.asoestadoC = asoestadoC;
	}
}
