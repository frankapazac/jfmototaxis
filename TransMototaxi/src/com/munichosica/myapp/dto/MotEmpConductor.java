package com.munichosica.myapp.dto;

public class MotEmpConductor {
	protected Long ecocodigoD;
	protected String ecofechainicioF;
	protected String ecofechaceseF;
	protected Integer motosasignadasI; 
		
	protected MotConductor conductor;
	protected MotEmpresa empresa;
	
	public MotEmpConductor() {
		conductor=new MotConductor();
		empresa=new MotEmpresa();
	}
	
	public Long getEcocodigoD() {
		return ecocodigoD;
	}
	public void setEcocodigoD(Long ecocodigoD) {
		this.ecocodigoD = ecocodigoD;
	}
	public String getEcofechainicioF() {
		return ecofechainicioF;
	}
	public void setEcofechainicioF(String ecofechainicioF) {
		this.ecofechainicioF = ecofechainicioF;
	}
	public String getEcofechaceseF() {
		return ecofechaceseF;
	}
	public void setEcofechaceseF(String ecofechaceseF) {
		this.ecofechaceseF = ecofechaceseF;
	}
	public Integer getMotosasignadasI() {
		return motosasignadasI;
	}
	public void setMotosasignadasI(Integer motosasignadasI) {
		this.motosasignadasI = motosasignadasI;
	}
	public MotConductor getConductor() {
		return conductor;
	}
	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
}
