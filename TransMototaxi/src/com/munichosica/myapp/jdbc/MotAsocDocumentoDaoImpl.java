package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotAsocDocumentoDao;
import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;

public class MotAsocDocumentoDaoImpl implements MotAsocDocumentoDao{

	@Override
	public void insert(MotAsocDocumento asocDocumento)
			throws MotAsocDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_ASOCDOCUMENTO;1(?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(2, asocDocumento.getAsociado().getAsocodigoD());
			stmt.setInt(3, asocDocumento.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(4, asocDocumento.getArchivo().getAdjcodigoD());
			stmt.execute();
			
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				asocDocumento.setAdocodigoD(codigo);
			}
		
		} catch (SQLException e) {
			throw new MotAsocDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
	}

	@Override
	public List<MotAsocDocumento> findByIdAsociado(Long codigo)
			throws MotAsocDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotAsocDocumento> list=new ArrayList<MotAsocDocumento>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTASOCDOCUMENTOBYASOCIADO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				MotAsocDocumento documento=null;
				MotTipoDocumento tipoDocumento=null;
				MotAdjuntarArchivo archivo=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					documento=new MotAsocDocumento();
					tipoDocumento=new MotTipoDocumento();
					archivo=new MotAdjuntarArchivo();
					documento.setAdocodigoD(rs.getLong("ADOCODIGO"));
					tipoDocumento.setMtdcodigoI(rs.getInt("TDOCODIGO"));
					archivo.setAdjcodigoD(rs.getLong("CODIGO"));
					archivo.setAdjnumeroV(rs.getString("NUMERO"));
					archivo.setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					archivo.setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					archivo.setAdjnombreV(rs.getString("NOMBRE"));
					documento.setTipoDocumento(tipoDocumento);
					documento.setArchivo(archivo);
					list.add(documento);
				}
			}
		} catch (SQLException e) {
			throw new MotAsocDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
