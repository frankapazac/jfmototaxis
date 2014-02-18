package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.MotAuditoria;
import com.munichosica.myapp.exceptions.MotAuditoriaDaoException;

public interface MotAuditoriaDao {
	public void Insert(MotAuditoria auditoria) throws MotAuditoriaDaoException;
	Long Insert(String tabla, Long codigo, String procedimiento,
			String usuario, String ip) throws MotAuditoriaDaoException;
}
