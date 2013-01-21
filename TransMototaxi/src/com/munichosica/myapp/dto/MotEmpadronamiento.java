package com.munichosica.myapp.dto;

import java.math.BigDecimal;

public class MotEmpadronamiento {

	protected BigDecimal epocodigo_D;
	protected BigDecimal empcodigo_D;
	protected BigDecimal unecodigo_D;
	protected String empfechainicio_F;
	protected String empfechaceses_F;
	protected String empvigencia_C;
	protected String empobservaciones_V;
	
	protected MotEmprAsociado asociado;
	protected MotUnidadEmpresa mototaxi;
	
	public MotEmprAsociado getAsociado() {
		return asociado;
	}
	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
	}
	public MotUnidadEmpresa getMototaxi() {
		return mototaxi;
	}
	public void setMototaxi(MotUnidadEmpresa mototaxi) {
		this.mototaxi = mototaxi;
	}
	public BigDecimal getEpocodigo_D() {
		return epocodigo_D;
	}
	public void setEpocodigo_D(BigDecimal epocodigo_D) {
		this.epocodigo_D = epocodigo_D;
	}
	public BigDecimal getEmpcodigo_D() {
		return empcodigo_D;
	}
	public void setEmpcodigo_D(BigDecimal empcodigo_D) {
		this.empcodigo_D = empcodigo_D;
	}
	public BigDecimal getUnecodigo_D() {
		return unecodigo_D;
	}
	public void setUnecodigo_D(BigDecimal unecodigo_D) {
		this.unecodigo_D = unecodigo_D;
	}
	public String getEmpvigencia_C() {
		return empvigencia_C;
	}
	public void setEmpvigencia_C(String empvigencia_C) {
		this.empvigencia_C = empvigencia_C;
	}
	public String getEmpobservaciones_V() {
		return empobservaciones_V;
	}
	public void setEmpobservaciones_V(String empobservaciones_V) {
		this.empobservaciones_V = empobservaciones_V;
	}
	
	public String getEmpfechainicio_F() {
		return empfechainicio_F;
	}
	public void setEmpfechainicio_F(String empfechainicio_F) {
		this.empfechainicio_F = empfechainicio_F;
	}
	public String getEmpfechaceses_F() {
		return empfechaceses_F;
	}
	public void setEmpfechaceses_F(String empfechaceses_F) {
		this.empfechaceses_F = empfechaceses_F;
	}
	
}
