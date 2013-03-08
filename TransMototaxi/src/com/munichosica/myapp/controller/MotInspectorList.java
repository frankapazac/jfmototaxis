package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.dto.MotOperativo;

public class MotInspectorList{
	private List<MotInspector> inspectores=null;
	private MotOperativo operativo;
	
	public MotInspectorList() {
		inspectores=new ArrayList<MotInspector>();
	}
	
	public void setOperativo(MotOperativo operativo) {
		this.operativo = operativo;
	}
	public MotOperativo getOperativo() {
		return operativo;
	}
	
	public void setInspectores(List<MotInspector> inspectores) {
		this.inspectores = inspectores;
	}
	public List<MotInspector> getInspectores() {
		return inspectores;
	}
}
