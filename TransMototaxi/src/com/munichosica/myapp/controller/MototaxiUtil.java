package com.munichosica.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.dto.MotUnidadEmpresa;

public class MototaxiUtil {
	protected MotEmpadronamiento empadronamiento;
	protected MotUnidadEmpresa unidadEmpresa;
	protected List<MotUnidDocumento> documentos;
	
	public MototaxiUtil() {
		unidadEmpresa=new MotUnidadEmpresa();
		documentos=new ArrayList<MotUnidDocumento>();
	}

	public MotUnidadEmpresa getUnidadEmpresa() {
		return unidadEmpresa;
	}

	public void setUnidadEmpresa(MotUnidadEmpresa unidadEmpresa) {
		this.unidadEmpresa = unidadEmpresa;
	}

	public List<MotUnidDocumento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<MotUnidDocumento> documentos) {
		this.documentos = documentos;
	}

	public MotEmpadronamiento getEmpadronamiento() {
		return empadronamiento;
	}

	public void setEmpadronamiento(MotEmpadronamiento empadronamiento) {
		this.empadronamiento = empadronamiento;
	}
}
