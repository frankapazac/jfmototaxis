package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.MotInternamientoDao;
import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;

public class MotInternamientoDaoImpl implements MotInternamientoDao{

	@Override
	public void procesar(MotInternamiento internamiento)
			throws MotInternamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INTERNAMIENTO;1(?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, internamiento.getIntcodigoD());
			stmt.setLong(2, internamiento.getConductor().getConcodigoD());
			stmt.setLong(3, internamiento.getActaConformidad().getAcocodigoD());
			stmt.setLong(4, internamiento.getBoletaInternamiento().getBincodigoD());
			stmt.setLong(5, internamiento.getPapeleta().getPapcodigoD());
			stmt.setLong(6, internamiento.getPropUnidadEmpresa().getPmocodigoD());
			stmt.setString(7, internamiento.getIntfechaingresoF());
			stmt.setString(8, internamiento.getIntfechasalidaF());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				internamiento.setIntcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
}
