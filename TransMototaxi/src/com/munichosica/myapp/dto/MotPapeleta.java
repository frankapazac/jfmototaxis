package com.munichosica.myapp.dto;

public class MotPapeleta {
	protected Long papcodigoD;
	protected MotPolicia policia;
	protected MotAdjuntarArchivo archivo;
	protected MotInfrMedida infrMedida;
	protected MotInspector inspector;
	protected MotPropUnidadEmpresa propUnidadEmpresa;
	protected MotConductor conductor;
	protected String papnumeroV;
	protected String papfechainfraccionF;
	protected String paphorainfraccionF;
	protected String papinfrdireccionV;
	protected String papinfrreferenciaV;
	protected String pappropietarioC;
	protected int papnrocopiasI;
	protected String papobservinfraccionV;
	protected String papobservinspectorV;
	protected String papestadoC;
	protected Float monto;
	

	public MotPapeleta() {
		policia=new MotPolicia();
		archivo=new MotAdjuntarArchivo();
		infrMedida=new MotInfrMedida();
		inspector=new MotInspector();
		propUnidadEmpresa=new MotPropUnidadEmpresa();
		conductor=new MotConductor();
	}
	
	public Float getMonto() {
		return monto;
	}
	
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	public Long getPapcodigoD() {
		return papcodigoD;
	}
	public void setPapcodigoD(Long papcodigoD) {
		this.papcodigoD = papcodigoD;
	}
	public void setPapinfrreferenciaV(String papinfrreferenciaV) {
		this.papinfrreferenciaV = papinfrreferenciaV;
	}
	public String getPapinfrreferenciaV() {
		return papinfrreferenciaV;
	}
	public MotPolicia getPolicia() {
		return policia;
	}
	public void setPolicia(MotPolicia policia) {
		this.policia = policia;
	}
	public MotAdjuntarArchivo getArchivo() {
		return archivo;
	}
	public void setArchivo(MotAdjuntarArchivo archivo) {
		this.archivo = archivo;
	}
	public MotInfrMedida getInfrMedida() {
		return infrMedida;
	}
	public void setInfrMedida(MotInfrMedida infrMedida) {
		this.infrMedida = infrMedida;
	}
	public MotInspector getInspector() {
		return inspector;
	}
	public void setInspector(MotInspector inspector) {
		this.inspector = inspector;
	}
	public MotPropUnidadEmpresa getPropUnidadEmpresa() {
		return propUnidadEmpresa;
	}
	public void setPropUnidadEmpresa(MotPropUnidadEmpresa propUnidadEmpresa) {
		this.propUnidadEmpresa = propUnidadEmpresa;
	}
	public MotConductor getConductor() {
		return conductor;
	}
	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}
	public String getPapnumeroV() {
		return papnumeroV;
	}
	public void setPapnumeroV(String papnumeroV) {
		this.papnumeroV = papnumeroV;
	}
	public String getPapfechainfraccionF() {
		return papfechainfraccionF;
	}
	public void setPapfechainfraccionF(String papfechainfraccionF) {
		this.papfechainfraccionF = papfechainfraccionF;
	}
	public String getPaphorainfraccionF() {
		return paphorainfraccionF;
	}
	public void setPaphorainfraccionF(String paphorainfraccionF) {
		this.paphorainfraccionF = paphorainfraccionF;
	}
	public String getPapinfrdireccionV() {
		return papinfrdireccionV;
	}
	public void setPapinfrdireccionV(String papinfrdireccionV) {
		this.papinfrdireccionV = papinfrdireccionV;
	}
	public String getPappropietarioC() {
		return pappropietarioC;
	}
	public void setPappropietarioC(String pappropietarioC) {
		this.pappropietarioC = pappropietarioC;
	}
	public int getPapnrocopiasI() {
		return papnrocopiasI;
	}
	public void setPapnrocopiasI(int papnrocopiasI) {
		this.papnrocopiasI = papnrocopiasI;
	}
	public String getPapobservinfraccionV() {
		return papobservinfraccionV;
	}
	public void setPapobservinfraccionV(String papobservinfraccionV) {
		this.papobservinfraccionV = papobservinfraccionV;
	}
	public String getPapobservinspectorV() {
		return papobservinspectorV;
	}
	public void setPapobservinspectorV(String papobservinspectorV) {
		this.papobservinspectorV = papobservinspectorV;
	}
	public String getPapestadoC() {
		return papestadoC;
	}
	public void setPapestadoC(String papestadoC) {
		this.papestadoC = papestadoC;
	}
}
