package com.munichosica.myapp.dao;

import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.exceptions.ReportsDaoException;

public interface ReportsDao {
	public RepPapeleta reportePapeleta(Long codigo) throws ReportsDaoException;
}
