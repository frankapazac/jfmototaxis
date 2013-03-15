package com.munichosica.myapp.dao;

import java.util.List;

import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;

public interface MotTipoMedidaDao {

	public List<MotTipoMedida> findAllTipoMedida() throws MotTipoMedidaDaoException;
	public List<MotTipoMedida> findByCriterio(String criterio,String texto) throws MotTipoMedidaDaoException;
	public MotTipoMedida findByIdTipoMed(Long codigo) throws MotTipoMedidaDaoException;
	public void insert(MotTipoMedida medida) throws MotTipoMedidaDaoException;
	public void delete(Integer codigo) throws MotTipoMedidaDaoException;
}
