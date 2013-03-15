package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotAdjuntarArchivoDao;
import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;

public class MotAdjuntarArchivoDaoImpl implements MotAdjuntarArchivoDao{

	@Override
	public void insert(MotAdjuntarArchivo adjuntarArchivo)
			throws MotAdjuntarArchivoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_ADJUNTARARCHIVO;1(?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, adjuntarArchivo.getAdjcodigoD());
			stmt.setString(2,adjuntarArchivo.getAdjnumeroV());
			stmt.setString(3,adjuntarArchivo.getAdjfechaemisionF());
			stmt.setString(4,adjuntarArchivo.getAdjfechacaducidadF());
			stmt.setString(5,adjuntarArchivo.getAdjnombreV());
			stmt.setBytes(6,adjuntarArchivo.getAdjarchivoB());
			stmt.setString(7,adjuntarArchivo.getAdjextensionV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				adjuntarArchivo.setAdjcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotAdjuntarArchivoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotAdjuntarArchivo> findArchivosByAsociado(Long codigo)
			throws MotAdjuntarArchivoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotAdjuntarArchivo> list=new ArrayList<MotAdjuntarArchivo>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_ADJUNTARARCHIVOBYASOCIADO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				MotAdjuntarArchivo archivo=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					archivo=new MotAdjuntarArchivo();
					archivo.setAdjcodigoD(rs.getLong("CODIGO"));
					archivo.setAdjnumeroV(rs.getString("NUMERO"));
					archivo.setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					archivo.setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					archivo.setAdjnombreV(rs.getString("NOMBRE"));
					list.add(archivo);
				}
			}
		} catch (SQLException e) {
			throw new MotAdjuntarArchivoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

	

}
