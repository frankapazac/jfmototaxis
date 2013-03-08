package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.dto.MotTipoMedida;

public class MotTipoMedidasList {

	private List<MotTipoMedida> tipoMedidas = null;
	private MotInfraccion infraccion;
	
	public MotTipoMedidasList() {
		tipoMedidas=new ArrayList<MotTipoMedida>();
	}
	
	public List<MotTipoMedida> getTipoMedidas() {
		return tipoMedidas;
	}

	public void setTipoMedidas(List<MotTipoMedida> tipoMedidas) {
		this.tipoMedidas = tipoMedidas;
	}

	public MotInfraccion getInfraccion() {
		return infraccion;
	}

	public void setInfraccion(MotInfraccion infraccion) {
		this.infraccion = infraccion;
	}


}
