package com.munichosica.myapp.dto;

public class MotUnidConductor {
	
	
	protected Long ucocodigoD;
	protected Long pmocodigoD;
	protected Long condcodigoD;
	protected String ucofechainicioF;
	protected String ucofechacese;
	protected String ucoobservacionesV;
	protected String ucoestadoC;
	
	protected MotPropUnidadEmpresa propietariomoto;
	protected MotConductor conductor;
	

	public Long getUcocodigoD() {
		return ucocodigoD;
	}
	public void setUcocodigoD(Long ucocodigoD) {
		this.ucocodigoD = ucocodigoD;
	}
	public Long getPmocodigoD() {
		return pmocodigoD;
	}
	public void setPmocodigoD(Long pmocodigoD) {
		this.pmocodigoD = pmocodigoD;
	}
	public Long getCondcodigoD() {
		return condcodigoD;
	}
	public void setCondcodigoD(Long condcodigoD) {
		this.condcodigoD = condcodigoD;
	}
	public String getUcofechainicioF() {
		return ucofechainicioF;
	}
	public void setUcofechainicioF(String ucofechainicioF) {
		this.ucofechainicioF = ucofechainicioF;
	}
	public String getUcofechacese() {
		return ucofechacese;
	}
	public void setUcofechacese(String ucofechacese) {
		this.ucofechacese = ucofechacese;
	}
	public String getUcoobservacionesV() {
		return ucoobservacionesV;
	}
	public void setUcoobservacionesV(String ucoobservacionesV) {
		this.ucoobservacionesV = ucoobservacionesV;
	}
	public String getUcoestadoC() {
		return ucoestadoC;
	}
	public void setUcoestadoC(String ucoestadoC) {
		this.ucoestadoC = ucoestadoC;
	}
	public MotPropUnidadEmpresa getPropietariomoto() {
		return propietariomoto;
	}
	public void setPropietariomoto(MotPropUnidadEmpresa propietariomoto) {
		this.propietariomoto = propietariomoto;
	}
	public MotConductor getConductor() {
		return conductor;
	}
	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}


}
