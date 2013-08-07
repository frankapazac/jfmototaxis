package com.munichosica.myapp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;

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
	
	public Usuario getUsuarioByUser(HttpServletRequest request){
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		Usuario usuario=null;
		try {
			if(authentication!=null&&authentication.isAuthenticated()){
				usuario=new Usuario();
				
				User user=(User) authentication.getPrincipal();
				usuario.getRol().setRolnombreV(user.getAuthorities().toString().replace("[", "").replace("]",""));
				usuario.getRol().setPaginas(paginasByRol(usuario.getRol().getRolnombreV()));
				
				conn=ResourceManager.getConnection();
				stmt=conn.prepareCall("{call SP_MOT_GET_USERDETAIL;1(?)}");
				stmt.setString(1, user.getUsername());
				boolean results=stmt.execute();
				if(results){
					rs=stmt.getResultSet();
					if(rs.next()){						
						usuario.setUsucodigoI(rs.getInt("CODUSUARIO"));
						usuario.setUsuusuarioV(rs.getString("USUARIO"));
						usuario.getEmpresa().setEmpcodigoD(rs.getLong("CODEMPRESA"));
						usuario.getEmpresa().setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
						usuario.getEmpresa().setEmpdireccionV(rs.getString("DIRECCION"));
						usuario.getEmpresa().setEmprucV(rs.getString("RUC"));
						usuario.getEmpresa().setEmppagwebV(rs.getString("WEB"));
						
						//usuario.getEmpresa().getFoto().setAdjarchivoB(rs.getBytes("FOTO_ARCHIVO"));
						String nombreBanner="images/bannerMunicipalidad.png",nombreLogo="images/no_disponible.jpg",nombreFoto="images/no_disponible.jpg";
						if(rs.getBytes("FOTO_ARCHIVO")!=null){
							nombreFoto="temp/"+FileUtil.createTempFile(request, rs.getString("FOTO_NOMB"), 
								FileUtil.deCompress(rs.getBytes("FOTO_ARCHIVO")));
						}
						usuario.getEmpresa().setFoto(nombreFoto);
						if(rs.getBytes("LOGO_ARCHIVO")!=null){
							nombreLogo="temp/"+FileUtil.createTempFile(request, rs.getString("LOGO_NOMB"), 
								FileUtil.deCompress(rs.getBytes("LOGO_ARCHIVO")));
						}
						usuario.getEmpresa().setLogo(nombreLogo);
						if(rs.getBytes("BANER_ARCHIVO")!=null){
							nombreBanner="temp/"+FileUtil.createTempFile(request, rs.getString("BANER_NOMB"), 
								FileUtil.deCompress(rs.getBytes("BANER_ARCHIVO")));
						}
						usuario.getEmpresa().setBanner(nombreBanner);
					}
				}
			}
		} catch (SQLException | IOException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuario;
	}

	/*public Rol getRol(HttpServletRequest request) {
		Rol rol=null;
		try {
			if(authentication!=null&&authentication.isAuthenticated()){
				rol=new Rol();
				User user=(User) authentication.getPrincipal();
				rol.setUsuario(getUsuarioByUser(request,user.getUsername()));
				rol.setRolnombreV(user.getAuthorities().toString().replace("[", "").replace("]",""));
				rol.setPaginas(paginasByRol(rol.getRolnombreV()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		return rol;
	}*/

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
