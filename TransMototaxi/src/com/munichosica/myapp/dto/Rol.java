package com.munichosica.myapp.dto;

import java.util.ArrayList;
import java.util.List;

public class Rol {
	protected int rolcodigoI;
	protected Usuario usuario;
	protected String rolnombreV;
	protected List<Pagina> paginas;
	
	public Rol() {
		usuario=new Usuario();
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getRolnombreV() {
		return rolnombreV;
	}
	public void setRolnombreV(String rolnombreV) {
		this.rolnombreV = rolnombreV;
	}
	
}
