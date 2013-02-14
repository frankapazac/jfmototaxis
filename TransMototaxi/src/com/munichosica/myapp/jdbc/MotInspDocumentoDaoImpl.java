package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInspDocumentoDao;
import com.munichosica.myapp.dto.MotInspDocumento;
import com.munichosica.myapp.exceptions.MotInspDocumentoDaoException;

public class MotInspDocumentoDaoImpl implements MotInspDocumentoDao{

	@Override
	public void insert(MotInspDocumento dto)
			throws MotInspDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INSPDOCUMENTO;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setInt(2, dto.getInspector().getInscodigoI());
			stmt.setInt(3, dto.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(4, dto.getArchivo().getAdjcodigoD());
			stmt.setString(5, dto.getIdoestadoC());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setIdocodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotInspDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotInspDocumento> findByIdInspector(int codigo)
			throws MotInspDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInspDocumento> list=null;
		try {
			conn=ResourceManager.getConnection();
			list=new ArrayList<MotInspDocumento>();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTINSPDOCUMENTOBYINSPECTOR;1(?)}");
			stmt.setInt(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotInspDocumento documento=null;
				while(rs.next()){
					documento=new MotInspDocumento();
					documento.setIdocodigoD(rs.getLong("IDOCODIGO"));
					documento.getTipoDocumento().setMtdcodigoI(rs.getInt("TDOCODIGO"));
					documento.getArchivo().setAdjcodigoD(rs.getLong("CODIGO"));
					documento.getArchivo().setAdjnumeroV(rs.getString("NUMERO"));
					documento.getArchivo().setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					documento.getArchivo().setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					documento.getArchivo().setAdjnombreV(rs.getString("NOMBRE"));
					list.add(documento);
				}
			}
		} catch (SQLException e) {
			throw new MotInspDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
