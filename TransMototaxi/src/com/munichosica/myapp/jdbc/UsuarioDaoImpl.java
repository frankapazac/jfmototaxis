package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.UsuarioDao;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public class UsuarioDaoImpl implements UsuarioDao {


	@Override
	public void insertar(Usuario usuario) throws UsuarioDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_USUARIO;1(?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(1, usuario.getUsucodigoI());
			stmt.setString(2, usuario.getUsuusuarioV());
			stmt.setString(3, usuario.getPass());
			stmt.setLong(4, usuario.getEmpresa().getZona().getZoncodigo_I());
			stmt.setLong(5, usuario.getPersona().getPercodigoD());
			stmt.setInt(6, usuario.getRol().getRolcodigoI());
			stmt.execute();
			Integer codigo=stmt.getInt(1);
			if(codigo!=null){
				usuario.setUsucodigoI(codigo);
			}
		} catch (SQLException e) {
			throw new UsuarioDaoException(e.getMessage(), e);
		}	finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}		
	}
	
	@Override
	public void update(Usuario dto) throws UsuarioDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_CLAVE_USUARIO;1(?,?,?)}");
			stmt.setString(1,  dto.getUsuusuarioV());
			stmt.setString(2, dto.getPass());
			stmt.setString(3, dto.getNewPass());
			stmt.execute();
		} catch (SQLException e) {
			throw new UsuarioDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}	
	}

	@Override
	public Usuario obtenerUsuarioEmpresa(Long codigo)
			throws UsuarioDaoException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Usuario usuario=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareStatement("{call SP_MOT_GET_USUARIOFROMEMPRESA;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					usuario=new Usuario();
					usuario.setUsucodigoI(rs.getInt("USUCODIGO"));
					usuario.setUsuusuarioV(rs.getString("USUARIO"));
					usuario.setPass(rs.getString("CLAVE"));
					usuario.getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
					usuario.getPersona().setPerdniV(rs.getString("DNI"));
					usuario.getPersona().setPernombresV((rs.getString("NOMBRES")));
					usuario.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					usuario.getPersona().setPermaternoV(rs.getString("MATERNO"));
					usuario.getPersona().setPernacimientoF(rs.getString("NACIMIENTO"));
					usuario.getPersona().setPersexoC(rs.getString("SEXO"));
					usuario.getPersona().setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					usuario.getPersona().setPermovilclaV(rs.getString("CLARO"));
					usuario.getPersona().setPermovilmovV(rs.getString("MOVISTAR"));
					usuario.getPersona().setPermovilnexV(rs.getString("NEXTEL"));
					usuario.getPersona().setPerteleffijoV(rs.getString("TELEFONOFIJO"));
					usuario.getPersona().setPeremailV(rs.getString("EMAIL"));
					usuario.getPersona().setPerdomicilioV(rs.getString("DOMICILIO"));
					usuario.getPersona().setPerubdptoV(rs.getString("DEPARTAMENTO"));
					usuario.getPersona().setPerubprovV(rs.getString("PROVINCIA"));
					usuario.getPersona().setPerubprovnombreV(rs.getString("PROV_NOMBRE"));
					usuario.getPersona().setPerubidistV(rs.getString("DISTRITO"));
					usuario.getPersona().setPerubidistnombreV(rs.getString("DIST_NOMBRE"));
				}
			}
		} catch (SQLException e) {
			throw new UsuarioDaoException(e.getMessage(), e);
		}
		return usuario;
	}
	
}
