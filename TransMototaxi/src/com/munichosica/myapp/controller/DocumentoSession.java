package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotAsocDocumento;

public class DocumentoSession {
	private List<MotAsocDocumento> list;
	
	public DocumentoSession() {
		list=new ArrayList<MotAsocDocumento>();
	}
	
	public List<MotAsocDocumento> getList() {
		return list;
	}
	
	public void add(MotAsocDocumento documento){
		System.out.println("ENTRO");
		for(MotAsocDocumento doc:getList()){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				getList().remove(doc);
				break;
			}
		}
		list.add(documento);
	}
	
	public void remove(MotAsocDocumento documento){
		list.remove(documento);
	}
}
