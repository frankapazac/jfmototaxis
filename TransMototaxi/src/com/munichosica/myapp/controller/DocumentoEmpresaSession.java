package com.munichosica.myapp.controller;


import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotEmpDocumento;

public class DocumentoEmpresaSession {
	
	private List<MotEmpDocumento> list;

	public DocumentoEmpresaSession() {
		list=new ArrayList<MotEmpDocumento>();
	}
	
	public void setList(List<MotEmpDocumento> list) {
		this.list = list;
	}
	
	public List<MotEmpDocumento> getList() {
		return list;
	}

	public void add(MotEmpDocumento documento) {
		for(MotEmpDocumento doc:getList()){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				getList().remove(doc);
				break;
			}
		}
		list.add(documento);
	}
}
