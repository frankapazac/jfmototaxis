package com.munichosica.myapp.dto;

public class MotEmpPropietario {
	protected Long eprcodigoD;
	protected MotEmpresa empresa;
	protected MotPersona persona;
	protected String eprfechainicioF;
	protected String eprfechafinF;
	protected String eprestadoC;
	protected String eprdocumentoV;
	protected String eprextensionV;
	protected MotAdjuntarArchivo archivo;
	protected String eprobservacionesV;
	
	public MotEmpPropietario() {
		empresa=new MotEmpresa();
		persona=new MotPersona();
		archivo=new MotAdjuntarArchivo();
	}
	
	public Long getEprcodigoD() {
		return eprcodigoD;
	}
	public void setEprcodigoD(Long eprcodigoD) {
		this.eprcodigoD = eprcodigoD;
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
	public String getEprobservacionesV() {
		return eprobservacionesV;
	}
	public void setEprobservacionesV(String eprobservacionesV) {
		this.eprobservacionesV = eprobservacionesV;
	}

	public MotPersona getPersona() {
		return persona;
	}

	public void setPersona(MotPersona persona) {
		this.persona = persona;
	}

	public String getEprdocumentoV() {
		return eprdocumentoV;
	}

	public void setEprdocumentoV(String eprdocumentoV) {
		this.eprdocumentoV = eprdocumentoV;
	}

	public String getEprextensionV() {
		return eprextensionV;
	}

	public void setEprextensionV(String eprextensionV) {
		this.eprextensionV = eprextensionV;
	}

	public MotAdjuntarArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(MotAdjuntarArchivo archivo) {
		this.archivo = archivo;
	}
}
