package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotUnidDocumentoDao;
import com.munichosica.myapp.dto.MotUnidDocumento;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotUnidDocumentoDaoException;

public class MotUnidDocumentoDaoImpl implements MotUnidDocumentoDao{

	@Override
	public void insert(MotUnidDocumento dto)
			throws MotUnidDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_UNDDOCUMENTO;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setInt(2, dto.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(3, dto.getAdjuntarArchivo().getAdjcodigoD());
			stmt.setLong(4, dto.getUnidadEmpresa().getAsociado().getAsocodigoD());
			stmt.setLong(5, dto.getUnidadEmpresa().getUnidadempresa().getUnecodigoD());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setPtdcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotUnidDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotUnidDocumento> findDocumentosByIdUnidad(Long codigo)
			throws MotUnidDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotUnidDocumento> list=null;
		try {
			list=new ArrayList<MotUnidDocumento>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDADDOCUMENTO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				MotUnidDocumento documento=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					documento=new MotUnidDocumento();
					documento.getTipoDocumento().setMtdcodigoI(rs.getInt("TDOCODIGO"));
					documento.setPtdcodigoD(rs.getLong("UDOCODIGO"));
					documento.getAdjuntarArchivo().setAdjcodigoD(rs.getLong("ADJCODIGO"));
					documento.getAdjuntarArchivo().setAdjnombreV(rs.getString("NOMBRE"));
					documento.getAdjuntarArchivo().setAdjfechaemisionF(rs.getString("EMISION"));
					documento.getAdjuntarArchivo().setAdjfechacaducidadF(rs.getString("CADUCIDAD"));
					documento.getAdjuntarArchivo().setAdjnumeroV(rs.getString("NUMERO"));
					list.add(documento);
				}
			}
		} catch (SQLException e) {
			throw new MotUnidDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
