package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotTipoDocumentoDao;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

public class MotTipoDocumentoDaoImpl implements MotTipoDocumentoDao{

	@Override
	public List<MotTipoDocumento> findByTable(String table)
			throws MotTipoDocumentoDaoException {

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotTipoDocumento> list=new ArrayList<MotTipoDocumento>();;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_TIPODOCUMENTO;1(?)}");
			stmt.setString(1, table);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotTipoDocumento tipoDocumento=null;
				while(rs.next()){
					tipoDocumento=new MotTipoDocumento();
					tipoDocumento.setMtdcodigoI(rs.getInt("CODIGO"));
					tipoDocumento.setMtdnombreV(rs.getString("NOMBRE"));
					list.add(tipoDocumento);
				}
			}
		} catch (SQLException e) {
			throw new MotTipoDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

}
