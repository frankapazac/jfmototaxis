package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotOficinaRegistral;
import com.munichosica.myapp.exceptions.MotOficinaRegistralDaoException;

public interface MotOficinaRegistralDao {
	public List<MotOficinaRegistral> findAll() throws MotOficinaRegistralDaoException;
}
