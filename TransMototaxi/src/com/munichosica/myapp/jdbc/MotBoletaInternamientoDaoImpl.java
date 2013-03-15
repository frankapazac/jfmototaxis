package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.MotBoletaInternamientoDao;
import com.munichosica.myapp.dto.MotBoletaInternamiento;
import com.munichosica.myapp.exceptions.MotBoletaInternamientoDaoException;

public class MotBoletaInternamientoDaoImpl implements MotBoletaInternamientoDao{

	@Override
	public void procesar(MotBoletaInternamiento boletaInternamiento)
			throws MotBoletaInternamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_BOLETAINTERNAMIENTO;1(?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.registerOutParameter(2, Types.VARCHAR);
			if(boletaInternamiento.getBincodigoD()!=null)
				stmt.setLong(1, boletaInternamiento.getBincodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			if(boletaInternamiento.getBinnumeroV()!=null)
				stmt.setString(2, boletaInternamiento.getBinnumeroV());
			else stmt.setNull(2, Types.VARCHAR);
			stmt.setString(3, boletaInternamiento.getBinmotivoV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			String numero=stmt.getString(2);
			if(codigo!=null){
				boletaInternamiento.setBincodigoD(codigo);
				if(numero!=null){
					boletaInternamiento.setBinnumeroV(numero);
				}
			}
		} catch (SQLException e) {
			throw new MotBoletaInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

}
