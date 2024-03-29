package com.munichosica.myapp.dto;

public class MotEmpresa {
	protected Long empcodigoD;
	protected MotZona zona;
	protected String emprazonsocialV;
	protected String empdireccionV;
	protected String emprucV;
	protected String emptelefono1V;
	protected String emptelefono2V;
	protected String empcelularmovV;
	protected String empcelularclaV;
	protected String empcelularnexV;
	protected String empmailV;
	protected String emppagwebV;
	protected String empfechainioperacionF;
	protected String empfechaceseoperacionF;
	protected String empresolucionV;
	protected Integer empunidadesautorizadasI;
	protected String empescriturapublicaV;
	protected String empestadoC;
	
	protected MotUbigeo distrito;
	protected MotUbigeo departamento;
	protected MotUbigeo provincia;
	protected String foto;
	protected String logo;
	protected String banner;
	
	public MotEmpresa() {
		zona=new MotZona();
		distrito=new MotUbigeo();
		departamento=new MotUbigeo();
		provincia=new MotUbigeo();
	}
	
	public void setEmpestadoC(String empestadoC) {
		this.empestadoC = empestadoC;
	}
	public String getEmpestadoC() {
		return empestadoC;
	}
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getEmpcelularmovV() {
		return empcelularmovV;
	}
	public void setEmpcelularmovV(String empcelularmovV) {
		this.empcelularmovV = empcelularmovV;
	}
	public MotUbigeo getDistrito() {
		return distrito;
	}
	public void setDistrito(MotUbigeo distrito) {
		this.distrito = distrito;
	}
	public MotUbigeo getDepartamento() {
		return departamento;
	}
	public void setDepartamento(MotUbigeo departamento) {
		this.departamento = departamento;
	}
	public MotUbigeo getProvincia() {
		return provincia;
	}
	public void setProvincia(MotUbigeo provincia) {
		this.provincia = provincia;
	}

	public Long getEmpcodigoD() {
		return empcodigoD;
	}
	public void setEmpcodigoD(Long empcodigoD) {
		this.empcodigoD = empcodigoD;
	}
	public void setZona(MotZona zona) {
		this.zona = zona;
	}
	public MotZona getZona() {
		return zona;
	}
	public String getEmprazonsocialV() {
		return emprazonsocialV;
	}
	public void setEmprazonsocialV(String emprazonsocialV) {
		this.emprazonsocialV = emprazonsocialV;
	}
	public String getEmpdireccionV() {
		return empdireccionV;
	}
	public void setEmpdireccionV(String empdireccionV) {
		this.empdireccionV = empdireccionV;
	}
	public String getEmprucV() {
		return emprucV;
	}
	public void setEmprucV(String emprucV) {
		this.emprucV = emprucV;
	}
	public String getEmptelefono1V() {
		return emptelefono1V;
	}
	public void setEmptelefono1V(String emptelefono1v) {
		emptelefono1V = emptelefono1v;
	}
	public String getEmptelefono2V() {
		return emptelefono2V;
	}
	public void setEmptelefono2V(String emptelefono2v) {
		emptelefono2V = emptelefono2v;
	}
	public String getEmpcelularclaV() {
		return empcelularclaV;
	}
	public void setEmpcelularclaV(String empcelularclaV) {
		this.empcelularclaV = empcelularclaV;
	}
	public String getEmpcelularnexV() {
		return empcelularnexV;
	}
	public String getEmpfechainioperacionF() {
		return empfechainioperacionF;
	}

	public void setEmpcelularnexV(String empcelularnexV) {
		this.empcelularnexV = empcelularnexV;
	}
	public String getEmpmailV() {
		return empmailV;
	}
	public void setEmpmailV(String empmailV) {
		this.empmailV = empmailV;
	}
	public String getEmppagwebV() {
		return emppagwebV;
	}
	public void setEmppagwebV(String emppagwebV) {
		this.emppagwebV = emppagwebV;
	}	
	public String getEmpfechaceseoperacionF() {
		return empfechaceseoperacionF;
	}

	public void setEmpfechaceseoperacionF(String empfechaceseoperacionF) {
		this.empfechaceseoperacionF = empfechaceseoperacionF;
	}

	public void setEmpfechainioperacionF(String empfechainioperacionF) {
		this.empfechainioperacionF = empfechainioperacionF;
	}

	public String getEmpresolucionV() {
		return empresolucionV;
	}
	public void setEmpresolucionV(String empresolucionV) {
		this.empresolucionV = empresolucionV;
	}
	public Integer getEmpunidadesautorizadasI() {
		return empunidadesautorizadasI;
	}
	public void setEmpunidadesautorizadasI(Integer empunidadesautorizadasI) {
		this.empunidadesautorizadasI = empunidadesautorizadasI;
	}
	public String getEmpescriturapublicaV() {
		return empescriturapublicaV;
	}
	public void setEmpescriturapublicaV(String empescriturapublicaV) {
		this.empescriturapublicaV = empescriturapublicaV;
	}
}
