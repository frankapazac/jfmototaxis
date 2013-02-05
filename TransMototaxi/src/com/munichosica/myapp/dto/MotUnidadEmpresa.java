package com.munichosica.myapp.dto;

import java.math.BigDecimal;

public class MotUnidadEmpresa {
	protected Long unecodigoD;
	protected MotModelo modelo;
	protected String uneplacanroV;
	protected String unenroseriechasisV;
	protected String unenromotorV;
	protected String unecarroceriaC;
	protected String  unecombustibleC;
	protected int unenroasientosI;
	protected String unenroidentificacionV;
	protected String uneannoC;
	protected BigDecimal unelongitudD;
	protected BigDecimal unealtoD;
	protected BigDecimal uneanchoD;
	protected int unenropasajerosI;
	protected BigDecimal unecargautilD;
	protected String unecolorV;
	protected MotOficinaRegistral oficina;
	protected String unenropadronV;
	protected String unenropartidaregistralV;
	protected String unetituloV;
	protected int unenroruedasI;
	protected MotMarca marca;
	protected String uneclaseV;
	protected String uneestadoC;
	
	public MotUnidadEmpresa() {
		modelo=new MotModelo();
		oficina=new MotOficinaRegistral();
		marca=new MotMarca();
	}
	
	public BigDecimal getUnelongitudD() {
		return unelongitudD;
	}

	public void setUnelongitudD(BigDecimal unelongitudD) {
		this.unelongitudD = unelongitudD;
	}

	public BigDecimal getUnealtoD() {
		return unealtoD;
	}

	public void setUnealtoD(BigDecimal unealtoD) {
		this.unealtoD = unealtoD;
	}

	public BigDecimal getUneanchoD() {
		return uneanchoD;
	}

	public void setUneanchoD(BigDecimal uneanchoD) {
		this.uneanchoD = uneanchoD;
	}

	public BigDecimal getUnecargautilD() {
		return unecargautilD;
	}

	public void setUnecargautilD(BigDecimal unecargautilD) {
		this.unecargautilD = unecargautilD;
	}

	public Long getUnecodigoD() {
		return unecodigoD;
	}
	public void setUnecodigoD(Long unecodigoD) {
		this.unecodigoD = unecodigoD;
	}
	public MotModelo getModelo() {
		return modelo;
	}
	public void setModelo(MotModelo modelo) {
		this.modelo = modelo;
	}
	public String getUneplacanroV() {
		return uneplacanroV;
	}
	public void setUneplacanroV(String uneplacanroV) {
		this.uneplacanroV = uneplacanroV;
	}
	public String getUnenroseriechasisV() {
		return unenroseriechasisV;
	}
	public void setUnenroseriechasisV(String unenroseriechasisV) {
		this.unenroseriechasisV = unenroseriechasisV;
	}
	public String getUnenromotorV() {
		return unenromotorV;
	}
	public void setUnenromotorV(String unenromotorV) {
		this.unenromotorV = unenromotorV;
	}
	public String getUnecarroceriaC() {
		return unecarroceriaC;
	}
	public void setUnecarroceriaC(String unecarroceriaC) {
		this.unecarroceriaC = unecarroceriaC;
	}
	public String getUnecombustibleC() {
		return unecombustibleC;
	}
	public void setUnecombustibleC(String unecombustibleC) {
		this.unecombustibleC = unecombustibleC;
	}
	public int getUnenroasientosI() {
		return unenroasientosI;
	}
	public void setUnenroasientosI(int unenroasientosI) {
		this.unenroasientosI = unenroasientosI;
	}
	public String getUnenroidentificacionV() {
		return unenroidentificacionV;
	}
	public void setUnenroidentificacionV(String unenroidentificacionV) {
		this.unenroidentificacionV = unenroidentificacionV;
	}
	public String getUneannoC() {
		return uneannoC;
	}
	public void setUneannoC(String uneannoC) {
		this.uneannoC = uneannoC;
	}
	public int getUnenropasajerosI() {
		return unenropasajerosI;
	}
	public void setUnenropasajerosI(int unenropasajerosI) {
		this.unenropasajerosI = unenropasajerosI;
	}
	public String getUnecolorV() {
		return unecolorV;
	}
	public void setUnecolorV(String unecolorV) {
		this.unecolorV = unecolorV;
	}
	public MotOficinaRegistral getOficina() {
		return oficina;
	}
	public void setOficina(MotOficinaRegistral oficina) {
		this.oficina = oficina;
	}
	public String getUnenropadronV() {
		return unenropadronV;
	}
	public void setUnenropadronV(String unenropadronV) {
		this.unenropadronV = unenropadronV;
	}
	public String getUnenropartidaregistralV() {
		return unenropartidaregistralV;
	}
	public void setUnenropartidaregistralV(String unenropartidaregistralV) {
		this.unenropartidaregistralV = unenropartidaregistralV;
	}
	public String getUnetituloV() {
		return unetituloV;
	}
	public void setUnetituloV(String unetituloV) {
		this.unetituloV = unetituloV;
	}
	public int getUnenroruedasI() {
		return unenroruedasI;
	}
	public void setUnenroruedasI(int unenroruedasI) {
		this.unenroruedasI = unenroruedasI;
	}
	public MotMarca getMarca() {
		return marca;
	}
	public void setMarca(MotMarca marca) {
		this.marca = marca;
	}
	public String getUneclaseV() {
		return uneclaseV;
	}
	public void setUneclaseV(String uneclaseV) {
		this.uneclaseV = uneclaseV;
	}
	public String getUneestadoC() {
		return uneestadoC;
	}
	public void setUneestadoC(String uneestadoC) {
		this.uneestadoC = uneestadoC;
	}
}
