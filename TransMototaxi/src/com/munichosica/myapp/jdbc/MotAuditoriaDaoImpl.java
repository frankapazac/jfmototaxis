package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.munichosica.myapp.dao.MotAuditoriaDao;
import com.munichosica.myapp.dto.MotAuditoria;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;

public class MotAuditoriaDaoImpl implements MotAuditoriaDao{

	@Override
	public void Insert(MotAuditoria auditoria) throws MotAuditoriaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_AUDITORIA;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setLong(1, auditoria.getAudcodigoD());
			stmt.setString(2, auditoria.getTabla());
			stmt.setLong(3, auditoria.getCodigo());
			stmt.setString(4, auditoria.getUsuario());
			stmt.setString(5, auditoria.getIp());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				auditoria.setAudcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotAuditoriaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
	@Override
	public Long Insert(String tabla, Long codigo, String procedimiento, String usuario, String ip) throws MotAuditoriaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_AUDITORIA;1(?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setLong(1, 0);
			stmt.setString(2, tabla);
			stmt.setLong(3, codigo);
			stmt.setString(4, procedimiento);
			stmt.setString(5, usuario);
			stmt.setString(6, ip);
			stmt.execute();
			Long id=stmt.getLong(1);
			return id;
		} catch (SQLException e) {
			throw new MotAuditoriaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
}
