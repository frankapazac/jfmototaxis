package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotEmprAsociado;

public class AsociadoUtil {
	protected MotEmprAsociado asociado;
	protected List<MotAsocDocumento> listDocumentos;
	
	public AsociadoUtil() {
		asociado=new MotEmprAsociado();
		listDocumentos=new ArrayList<MotAsocDocumento>();
	}
	
	public MotEmprAsociado getAsociado() {
		return asociado;
	}
	public void setAsociado(MotEmprAsociado asociado) {
		this.asociado = asociado;
	}
	public List<MotAsocDocumento> getListDocumentos() {
		return listDocumentos;
	}
	public void setListDocumentos(List<MotAsocDocumento> listDocumentos) {
		this.listDocumentos = listDocumentos;
	}
	
}
