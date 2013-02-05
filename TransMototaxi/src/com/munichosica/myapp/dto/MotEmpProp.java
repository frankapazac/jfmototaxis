package com.munichosica.myapp.dto;

public class MotEmpProp {

	protected Long eprcodigoD;
	protected MotEmpPropietario empPropietario;
	protected MotEmpresa empresa;
	protected String eprfechainicioF;
	protected String eprfechafinF;
	protected String eprestadoC;
	protected byte[] eprdocumentoB;
	protected String extension;
	protected String eprobservacionesV;
		
	public Long getEprcodigoD() {
		return eprcodigoD;
	}
	public void setEprcodigoD(Long eprcodigoD) {
		this.eprcodigoD = eprcodigoD;
	}
	public MotEmpPropietario getEmpPropietario() {
		return empPropietario;
	}
	public void setEmpPropietario(MotEmpPropietario empPropietario) {
		this.empPropietario = empPropietario;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public String getEprfechainicioF() {
		return eprfechainicioF;
	}
	public void setEprfechainicioF(String eprfechainicioF) {
		this.eprfechainicioF = eprfechainicioF;
	}
	public String getEprfechafinF() {
		return eprfechafinF;
	}
	public void setEprfechafinF(String eprfechafinF) {
		this.eprfechafinF = eprfechafinF;
	}
	public String getEprestadoC() {
		return eprestadoC;
	}
	public void setEprestadoC(String eprestadoC) {
		this.eprestadoC = eprestadoC;
	}
	public byte[] getEprdocumentoB() {
		return eprdocumentoB;
	}
	public void setEprdocumentoB(byte[] eprdocumentoB) {
		this.eprdocumentoB = eprdocumentoB;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getEprobservacionesV() {
		return eprobservacionesV;
	}
	public void setEprobservacionesV(String eprobservacionesV) {
		this.eprobservacionesV = eprobservacionesV;
	}
	
}
