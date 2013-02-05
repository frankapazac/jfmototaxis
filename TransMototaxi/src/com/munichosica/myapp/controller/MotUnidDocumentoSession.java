package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotUnidDocumento;

public class MotUnidDocumentoSession {
	private List<MotUnidDocumento> list;
	
	public MotUnidDocumentoSession() {
		list=new ArrayList<MotUnidDocumento>();
	}
	
	public List<MotUnidDocumento> getList() {
		return list;
	}
	
	public void add(MotUnidDocumento documento){
		System.out.println("Agrego nuevo documento");
		for(MotUnidDocumento doc:getList()){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				getList().remove(doc);
				break;
			}
		}
		list.add(documento);
	}
	
	public void remove(MotUnidDocumento documento){
		list.remove(documento);
	}
}
