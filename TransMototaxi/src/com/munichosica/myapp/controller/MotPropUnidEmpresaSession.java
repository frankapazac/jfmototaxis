package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.exceptions.MotPropUnidadEmpresaDaoException;
import com.munichosica.myapp.factory.MotPropUnidadEmpresaDaoFactory;

public class MotPropUnidEmpresaSession {
	private List<MotPropUnidadEmpresa> list;
	private MotConductor conductor;
	
	public MotPropUnidEmpresaSession() {
		list=new ArrayList<MotPropUnidadEmpresa>();
		conductor=new MotConductor();
	}
	
	public List<MotPropUnidadEmpresa> getList() {
		return list;
	}
	
	public void add(Long codigo) throws MotPropUnidadEmpresaDaoException{
		for(MotPropUnidadEmpresa pue:list){
			System.out.println("PUECODIGO: "+pue.getPmocodigoD());
			if(pue.getPmocodigoD()==codigo){
				list.remove(pue);
				break;
			}
		}
		MotPropUnidadEmpresa dto=MotPropUnidadEmpresaDaoFactory.create().findByPrimaryKey(codigo);
		list.add(dto);
	}
	
	public void remove(Long codigo){
		for(MotPropUnidadEmpresa pue:list){
			if(pue.getPmocodigoD()==codigo){
				list.remove(pue);
				break;
			}
		}
	}

	public MotConductor getConductor() {
		return conductor;
	}

	public void setConductor(MotConductor conductor) {
		this.conductor = conductor;
	}

	public void setList(List<MotPropUnidadEmpresa> list) {
		this.list=list;
	}
}
