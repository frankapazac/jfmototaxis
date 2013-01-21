package com.munichosica.myapp.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.dto.Usuempr;
import com.munichosica.myapp.jdbc.ResourceManager;

public class UserSecurity {
	public Usuempr getUser(){
		SecurityContext securityContext=SecurityContextHolder.getContext();
		Authentication authentication=securityContext.getAuthentication();
		Usuempr usuempr=null;
		if(authentication!=null&&authentication.isAuthenticated()){
			usuempr=new Usuempr();
			User user=(User) authentication.getPrincipal();
			try {
				usuempr=getUsuemprByUser(user.getUsername());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return usuempr;
	}
	
	private Usuempr getUsuemprByUser(String user) throws SQLException{
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		Usuempr usuempr=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_USERDETAIL;1(?)}");
			stmt.setString(1, user);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotEmpresa empresa=null;
				Usuario usuario=null;
				if(rs.next()){
					usuempr=new Usuempr();
					usuario=new Usuario();
					usuario.setUsucodigoI(rs.getInt("CODUSUARIO"));
					usuario.setUsuusuarioV(rs.getString("USUARIO"));
					empresa=new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("CODEMPRESA"));
					empresa.setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					empresa.setEmpdireccionV(rs.getString("DIRECCION"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmppagwebV(rs.getString("WEB"));
					usuempr.setEmpresa(empresa);
					usuempr.setUsuario(usuario);
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
		
		return usuempr;
	}
}
