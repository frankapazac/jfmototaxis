package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class Rol {
	protected int rolcodigoI;
	protected String rolnombreV;
	protected List<Pagina> paginas;
	
	public Rol() {
		paginas=new ArrayList<Pagina>();
	}
	
	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public int getRolcodigoI() {
		return rolcodigoI;
	}
	public void setRolcodigoI(int rolcodigoI) {
		this.rolcodigoI = rolcodigoI;
	}
	public String getRolnombreV() {
		return rolnombreV;
	}
	public void setRolnombreV(String rolnombreV) {
		this.rolnombreV = rolnombreV;
	}
	
}
