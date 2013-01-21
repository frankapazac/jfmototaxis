package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotEmpadronamientoDao;
import com.munichosica.myapp.dto.MotEmpadronamiento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotEmpadronamientoDaoException;


public class MotEmpadronamientoDaoImpl implements MotEmpadronamientoDao {

	protected static final Logger logger = Logger.getLogger(MotEmpadronamientoDaoImpl.class);
	
	@Override
	public void insert(MotEmpadronamiento dto)
			throws MotEmpadronamientoDaoException {	
	}

	@Override
	public void update(MotEmpadronamiento dto)
			throws MotEmpadronamientoDaoException {
	
	}

	@Override
	public void delete(MotEmpadronamiento dto)
			throws MotEmpadronamientoDaoException {	
	}

	@Override
	public MotEmpadronamiento findByPrimaryKey(Long codigo)
			throws MotEmpadronamientoDaoException {
		return null;
	}

	@Override
	public List<MotEmpadronamiento> findByCriterio(String criterio,
			String texto, Long empcodigo_D)
			throws MotEmpadronamientoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		
		List<MotEmpadronamiento> list = new ArrayList<MotEmpadronamiento>();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_MOTOTAXISPORCRITERIO;1(?,?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, empcodigo_D);
			
			
			boolean results = stmt.execute();
			if(results){
				
				rs=stmt.getResultSet();
				MotEmpadronamiento empadronamiento = null;
				MotEmprAsociado asociado = null;
				MotUnidadEmpresa mototaxi = null;
				MotPersona persona = null;
				MotModelo modelo = null;

				
				while(rs.next()){
					
					persona = new MotPersona();
					persona.setPernombresV(rs.getString("Nombres"));
					persona.setPerpaternoV(rs.getString("Paterno"));
					persona.setPermaternoV(rs.getString("Materno"));
					persona.setPerdniV(rs.getString("DNI"));
					asociado = new MotEmprAsociado();
					asociado.setPersona(persona);
					
					mototaxi = new MotUnidadEmpresa();
					mototaxi.setUnecodigo_D(rs.getBigDecimal("Código"));
					mototaxi.setUneplacanro_V(rs.getString("N° Placa"));
					mototaxi.setUnemarca_V(rs.getString("Marca"));
					mototaxi.setUneanno_C(rs.getString("Año"));
					mototaxi.setUnecolor_V(rs.getString("Color"));
					
					modelo = new MotModelo();//aqui jalamos el nombre modelo de la tabla modelo
					modelo.setModnombre_V(rs.getString("Modelo"));
					mototaxi.setModelo(modelo);
					
					empadronamiento= new MotEmpadronamiento();
					empadronamiento.setEmpfechaceses_F(rs.getString("Fecha Cese"));
					empadronamiento.setEmpfechainicio_F(rs.getString("Fecha Inicio"));
					
					empadronamiento.setAsociado(asociado);
					empadronamiento.setMototaxi(mototaxi);
					list.add(empadronamiento);
				}
			}
			
		} catch (SQLException ex) {
			logger.error("Exception : " + ex.getMessage(), ex);
			throw new MotEmpadronamientoDaoException( "Exception: " + ex.getMessage(), ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	
		return list;
	}
	
	
	
}
