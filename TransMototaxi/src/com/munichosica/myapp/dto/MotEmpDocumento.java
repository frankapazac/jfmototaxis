package com.munichosica.myapp.dto;

public class MotEmpDocumento {
	
	protected Long edocodigoD;
	protected Long adjcodigoD;
	protected Integer mtdcodigoI;
	protected Long empcodigoD;	
	
	protected MotEmpresa empresa;
	protected MotTipoDocumento tipoDocumento;
	protected MotAdjuntarArchivo adjuntarArchivo;
	
	public MotEmpDocumento() {
		empresa=new MotEmpresa();
		tipoDocumento=new MotTipoDocumento();
		adjuntarArchivo=new MotAdjuntarArchivo();
	}
	
	public Long getEdocodigoD() {
		return edocodigoD;
	}
	public void setEdocodigoD(Long edocodigoD) {
		this.edocodigoD = edocodigoD;
	}
	public Long getAdjcodigoD() {
		return adjcodigoD;
	}
	public void setAdjcodigoD(Long adjcodigoD) {
		this.adjcodigoD = adjcodigoD;
	}
	public Integer getMtdcodigoI() {
		return mtdcodigoI;
	}
	public void setMtdcodigoI(Integer mtdcodigoI) {
		this.mtdcodigoI = mtdcodigoI;
	}
	public Long getEmpcodigoD() {
		return empcodigoD;
	}
	public void setEmpcodigoD(Long empcodigoD) {
		this.empcodigoD = empcodigoD;
	}
	public MotEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(MotEmpresa empresa) {
		this.empresa = empresa;
	}
	public MotTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(MotTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public MotAdjuntarArchivo getAdjuntarArchivo() {
		return adjuntarArchivo;
	}
	public void setAdjuntarArchivo(MotAdjuntarArchivo adjuntarArchivo) {
		this.adjuntarArchivo = adjuntarArchivo;
	}

}
