package com.munichosica.myapp.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.munichosica.myapp.dto.Pagina;
import com.munichosica.myapp.dto.Rol;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.jdbc.ResourceManager;

public class UserSecurity {
	SecurityContext securityContext=null;
	Authentication authentication=null;
	public UserSecurity() {
		securityContext=SecurityContextHolder.getContext();
		authentication=securityContext.getAuthentication();
	}
	
	/*private Usuario getUser(){
		Usuario usuario=null;
		if(authentication!=null&&authentication.isAuthenticated()){
			usuario=new Usuario();
			User user=(User) authentication.getPrincipal();
		System.out.println(user);
			System.out.println(user.getAuthorities());
			System.out.println(user.getAuthorities().getClass());
			System.out.println(user.getAuthorities().toString());
			try {
				usuario=getUsuarioByUser(user.getUsername());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return usuario;
	}*/
	
	private Usuario getUsuarioByUser(String user) throws SQLException{
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		Usuario usuario=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_USERDETAIL;1(?)}");
			stmt.setString(1, user);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					usuario=new Usuario();
					usuario.setUsucodigoI(rs.getInt("CODUSUARIO"));
					usuario.setUsuusuarioV(rs.getString("USUARIO"));
					usuario.getEmpresa().setEmpcodigoD(rs.getLong("CODEMPRESA"));
					usuario.getEmpresa().setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					usuario.getEmpresa().setEmpdireccionV(rs.getString("DIRECCION"));
					usuario.getEmpresa().setEmprucV(rs.getString("RUC"));
					usuario.getEmpresa().setEmppagwebV(rs.getString("WEB"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
		
		return usuario;
	}

	public Rol getRol() {
		Rol rol=null;
		try {
			if(authentication!=null&&authentication.isAuthenticated()){
				rol=new Rol();
				User user=(User) authentication.getPrincipal();
				rol.setUsuario(getUsuarioByUser(user.getUsername()));
				rol.setRolnombreV(user.getAuthorities().toString().replace("[", "").replace("]",""));
				rol.setPaginas(paginasByRol(rol.getRolnombreV()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return rol;
	}

	private List<Pagina> paginasByRol(String rolnombreV) {
		List<Pagina> list=null;
		Connection conn=null;
		CallableStatement stmt;
		ResultSet rs=null;
		try {
			list=new ArrayList<Pagina>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PAGINA;1(?)}");
			stmt.setString(1, rolnombreV);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				Pagina pagina=null;
				while(rs.next()){
					pagina=new Pagina();
					pagina.setPagcodigoI(rs.getInt("CODIGO"));
					pagina.setPagnombreV(rs.getString("NOMBRE"));
					pagina.setPagurlV(rs.getString("URL"));
					pagina.setPagimagenB(rs.getBytes("IMAGEN"));
					list.add(pagina);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
