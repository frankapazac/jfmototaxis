package com.munichosica.myapp.dto;

public class MotOperativo {
	
	protected long opecodigoD;
	protected String opetituloV;
	protected String opedescripcionV;
	protected String opelugarV;
	protected String opereferencia;
	protected String opefecha;
	protected String opehora;
	protected String opehorafin;
	protected MotZona zona;
	protected MotInspector inspector;
	protected String estado;
	protected String opeobservacion;
	
	
	public String getOpeobservacion() {
		return opeobservacion;
	}

	public void setOpeobservacion(String opeobservacion) {
		this.opeobservacion = opeobservacion;
	}

	public MotOperativo(){
		zona = new MotZona();
		inspector = new MotInspector();
	}
	
	public String getOpehorafin() {
		return opehorafin;
	}

	public void setOpehorafin(String opehorafin) {
		this.opehorafin = opehorafin;
	}
	
	public long getOpecodigoD() {
		return opecodigoD;
	}
	public void setOpecodigoD(long opecodigoD) {
		this.opecodigoD = opecodigoD;
	}
	public String getOpetituloV() {
		return opetituloV;
	}
	public void setOpetituloV(String opetituloV) {
		this.opetituloV = opetituloV;
	}
	public String getOpedescripcionV() {
		return opedescripcionV;
	}
	public void setOpedescripcionV(String opedescripcionV) {
		this.opedescripcionV = opedescripcionV;
	}
	public String getOpelugarV() {
		return opelugarV;
	}
	public void setOpelugarV(String opelugarV) {
		this.opelugarV = opelugarV;
	}
	public String getOpereferencia() {
		return opereferencia;
	}
	public void setOpereferencia(String opereferencia) {
		this.opereferencia = opereferencia;
	}
	public MotZona getZona() {
		return zona;
	}
	public void setZona(MotZona zona) {
		this.zona = zona;
	}
	public MotInspector getInspector() {
		return inspector;
	}
	public void setInspector(MotInspector inspector) {
		this.inspector = inspector;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getOpefecha() {
		return opefecha;
	}

	public void setOpefecha(String opefecha) {
		this.opefecha = opefecha;
	}

	public String getOpehora() {
		return opehora;
	}

	public void setOpehora(String opehora) {
		this.opehora = opehora;
	}
}
