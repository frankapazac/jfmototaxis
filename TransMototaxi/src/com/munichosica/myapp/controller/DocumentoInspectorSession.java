package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotInspDocumento;

public class DocumentoInspectorSession {
	private List<MotInspDocumento> list;
	
	public DocumentoInspectorSession() {
		list=new ArrayList<MotInspDocumento>();
	}
	
	public List<MotInspDocumento> getList() {
		return list;
	}
	
	public void add(MotInspDocumento documento){
		System.out.println("ENTRO");
		for(MotInspDocumento doc:getList()){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				getList().remove(doc);
				break;
			}
		}
		list.add(documento);
	}
	
	public void remove(MotInspDocumento documento){
		list.remove(documento);
	}
}
