package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;

public interface MotEmpadronamientoDao {
	public void insert(MotEmpadronamiento dto) throws MotEmpadronamientoDaoException;
	public void update(MotEmpadronamiento dto) throws MotEmpadronamientoDaoException;
	public void delete(MotEmpadronamiento dto) throws MotEmpadronamientoDaoException;
	public MotEmpadronamiento findByPrimaryKey(Long codigo) throws MotEmpadronamientoDaoException;
	public List<MotEmpadronamiento> findByCriterio(String criterio,String texto, Long empcodigo_D)throws MotEmpadronamientoDaoException;
	public MotEmpadronamiento findByUnidad(Long codigo) throws MotEmpadronamientoDaoException;
	public MotEmpadronamiento findByPropietarioEmp(Long codigo)throws MotEmpadronamientoDaoException;
	public MotEmpadronamiento findByPapeleta(Long codigo)throws MotEmpadronamientoDaoException;
}
