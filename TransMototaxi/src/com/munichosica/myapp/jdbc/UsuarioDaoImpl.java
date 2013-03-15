package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.munichosica.myapp.dao.UsuarioDao;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.exceptions.UsuarioDaoException;

public class UsuarioDaoImpl implements UsuarioDao {

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

	
	
}
