package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MotInternamiento {
	protected Long intcodigoD;
	protected MotActaConformidad actaConformidad;
	protected MotConductor conductor;
	protected MotBoletaInternamiento boletaInternamiento;
	protected MotPapeleta papeleta;
	protected MotPropUnidadEmpresa propUnidadEmpresa;
	protected String intfechaingresoF;
	protected String intfechasalidaF;
	protected List<MotInteInventario> inventarios;
	protected List<MotPersona> personas;
	
	public MotInternamiento() {
		actaConformidad=new MotActaConformidad();
		conductor=new MotConductor();
		boletaInternamiento=new MotBoletaInternamiento();
		papeleta=new MotPapeleta();
		propUnidadEmpresa=new MotPropUnidadEmpresa();
		inventarios=new ArrayList<MotInteInventario>();
	}
	
	public List<MotPersona> getPersonas() {
		return personas;
	}
	
	public void setPersonas(List<MotPersona> personas) {
		this.personas = personas;
	}
	
	public void setInventarios(List<MotInteInventario> inventarios) {
		this.inventarios = inventarios;
	}
	public List<MotInteInventario> getInventarios() {
		return inventarios;
	}
	public Long getIntcodigoD() {
		return intcodigoD;
	}
	public void setIntcodigoD(Long intcodigoD) {
		this.intcodigoD = intcodigoD;
	}
	public MotActaConformidad getActaConformidad() {
		return actaConformidad;
	}
	public void setActaConformidad(MotActaConformidad actaConformidad) {
		this.actaConformidad = actaConformidad;
	}
	public MotConductor getConductor() {
		return conductor;
	}
	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}
	public MotBoletaInternamiento getBoletaInternamiento() {
		return boletaInternamiento;
	}
	public void setBoletaInternamiento(MotBoletaInternamiento boletaInternamiento) {
		this.boletaInternamiento = boletaInternamiento;
	}
	public MotPapeleta getPapeleta() {
		return papeleta;
	}
	public void setPapeleta(MotPapeleta papeleta) {
		this.papeleta = papeleta;
	}
	public MotPropUnidadEmpresa getPropUnidadEmpresa() {
		return propUnidadEmpresa;
	}
	public void setPropUnidadEmpresa(MotPropUnidadEmpresa propUnidadEmpresa) {
		this.propUnidadEmpresa = propUnidadEmpresa;
	}
	public String getIntfechaingresoF() {
		return intfechaingresoF;
	}
	public void setIntfechaingresoF(String intfechaingresoF) {
		this.intfechaingresoF = intfechaingresoF;
	}
	public String getIntfechasalidaF() {
		return intfechasalidaF;
	}
	public void setIntfechasalidaF(String intfechasalidaF) {
		this.intfechasalidaF = intfechasalidaF;
	}
	
	
}
