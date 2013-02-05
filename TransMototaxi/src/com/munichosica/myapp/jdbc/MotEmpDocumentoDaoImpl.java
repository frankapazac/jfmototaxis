package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.munichosica.myapp.dao.MotEmpDocumentoDao;
import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;

public class MotEmpDocumentoDaoImpl implements MotEmpDocumentoDao {

	@Override
	public void insert(MotEmpDocumento dto) throws MotEmpDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_INS_MOT_EMP_DOCUMENTO;1(?,?,?)}");
			stmt.setLong(1,dto.getAdjuntarArchivo().getAdjcodigoD());
			stmt.setInt(2,dto.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(3,dto.getEmpresa().getEmpcodigoD());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

	}

	
	
}
