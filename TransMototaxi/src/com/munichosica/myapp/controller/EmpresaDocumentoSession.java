package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotEmpDocumento;

public class EmpresaDocumentoSession {
	private List<MotEmpDocumento> list;
	
	public EmpresaDocumentoSession() {
		list=new ArrayList<MotEmpDocumento>();
	}
	
	public List<MotEmpDocumento> getList() {
		return list;
	}
	
	public void add(MotEmpDocumento documento){
		for(MotEmpDocumento doc:list){
			if(doc.getTipoDocumento().getMtdcodigoI()==documento.getTipoDocumento().getMtdcodigoI()){
				list.remove(doc);
				break;
			}
		}
		list.add(documento);
	}
	
	public void remove(MotEmpDocumento documento){
		list.remove(documento);
	}
}
