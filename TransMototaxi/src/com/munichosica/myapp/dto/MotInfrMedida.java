package com.munichosica.myapp.dto;

public class MotInfrMedida {
	protected int imecodigoI;
	protected MotInfraccion infraccion;
	protected MotTipoMedida tipoMedida;
	
	public MotInfrMedida() {
		infraccion=new MotInfraccion();
		tipoMedida=new MotTipoMedida();
	}
	
	public int getImecodigoI() {
		return imecodigoI;
	}
	public void setImecodigoI(int imecodigoI) {
		this.imecodigoI = imecodigoI;
	}
	public MotInfraccion getInfraccion() {
		return infraccion;
	}
	public void setInfraccion(MotInfraccion infraccion) {
		this.infraccion = infraccion;
	}
	public MotTipoMedida getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(MotTipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
	
	
}
