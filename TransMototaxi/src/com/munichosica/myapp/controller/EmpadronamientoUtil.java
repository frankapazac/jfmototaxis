package com.munichosica.myapp.controller;

import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;

public class EmpadronamientoUtil {
	private MotEmpadronamiento empadronamiento;
	private MotPropUnidadEmpresa propUnidadEmpresa;
	
	public EmpadronamientoUtil() {
		empadronamiento=new MotEmpadronamiento();
		propUnidadEmpresa=new MotPropUnidadEmpresa();
		empadronamiento.setUnidadEmpresa(propUnidadEmpresa.getUnidadempresa());
	}
	
	public MotEmpadronamiento getEmpadronamiento() {
		return empadronamiento;
	}
	public void setEmpadronamiento(MotEmpadronamiento empadronamiento) {
		this.empadronamiento = empadronamiento;
	}
	public MotPropUnidadEmpresa getPropUnidadEmpresa() {
		return propUnidadEmpresa;
	}
	public void setPropUnidadEmpresa(MotPropUnidadEmpresa propUnidadEmpresa) {
		this.propUnidadEmpresa = propUnidadEmpresa;
	}
}
