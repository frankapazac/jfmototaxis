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
	protected String asofechainicioF;
	protected String asofechaceseF;
	protected String asoobservacionV;
	protected MotAdjuntarArchivo foto;
	
	public MotEmprAsociado() {
		persona=new MotPersona();
		foto=new MotAdjuntarArchivo();
		empresa=new MotEmpresa();
	}
	
	
	public String getAsofechainicioF() {
		return asofechainicioF;
	}


	public void setAsofechainicioF(String asofechainicioF) {
		this.asofechainicioF = asofechainicioF;
	}


	public String getAsofechaceseF() {
		return asofechaceseF;
	}


	public void setAsofechaceseF(String asofechaceseF) {
		this.asofechaceseF = asofechaceseF;
	}


	public String getAsoobservacionV() {
		return asoobservacionV;
	}


	public void setAsoobservacionV(String asoobservacionV) {
		this.asoobservacionV = asoobservacionV;
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
