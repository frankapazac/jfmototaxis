package com.munichosica.myapp.controller;


import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotCondDocumento;

public class DocumentoConductorSession {
	
	private List<MotCondDocumento> list;

	public DocumentoConductorSession() {
		list=new ArrayList<MotCondDocumento>();
	}
	
	public void setList(List<MotCondDocumento> list) {
		this.list = list;
	}
	
	public List<MotCondDocumento> getList() {
		return list;
	}

	public void add(MotCondDocumento documento) {
		for(MotCondDocumento doc:getList()){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				getList().remove(doc);
				break;
			}
		}
		list.add(documento);
	}
}
