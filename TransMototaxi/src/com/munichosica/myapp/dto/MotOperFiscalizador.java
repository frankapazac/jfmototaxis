package com.munichosica.myapp.dto;

public class MotOperFiscalizador {
	
	protected Long oficodigoD;
	protected MotOperativo operativo;
	protected MotInspector fiscalizador;
	
	
	public MotOperFiscalizador()
	{
		operativo = new MotOperativo();
		fiscalizador = new MotInspector();
	}
	
	public Long getOficodigoD() {
		return oficodigoD;
	}

	public void setOficodigoD(Long oficodigoD) {
		this.oficodigoD = oficodigoD;
	}

	public MotOperativo getOperativo() {
		return operativo;
	}

	public void setOperativo(MotOperativo operativo) {
		this.operativo = operativo;
	}

	public MotInspector getFiscalizador() {
		return fiscalizador;
	}

	public void setFiscalizador(MotInspector fiscalizador) {
		this.fiscalizador = fiscalizador;
	}



}
