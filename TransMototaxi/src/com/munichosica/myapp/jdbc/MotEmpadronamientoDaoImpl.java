package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	public void insert(MotEmpadronamiento dto) throws MotEmpadronamientoDaoException{
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_EMPADRONAMIENTO;1(?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, dto.getEpocodigoD());
			stmt.setLong(2,dto.getEmpresa().getEmpcodigoD());
			stmt.setLong(3, dto.getUnidadEmpresa().getUnecodigoD());
			stmt.setString(4, dto.getEmpfechainicioF());
			stmt.setString(5, dto.getEmpfechaceseF());
			stmt.setString(6, dto.getEmpobservacionesV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setEpocodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotEmpadronamientoDaoException(e.getMessage() ,e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
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
				while(rs.next()){
					empadronamiento=new MotEmpadronamiento();
					empadronamiento.getAsociado().getPersona().setPernombresV(rs.getString("Nombres"));
					empadronamiento.getAsociado().getPersona().setPerpaternoV(rs.getString("Paterno"));
					empadronamiento.getAsociado().getPersona().setPermaternoV(rs.getString("Materno"));
					empadronamiento.getAsociado().getPersona().setPerdniV(rs.getString("DNI"));
					empadronamiento.getAsociado().getPersona().setPernombresV(rs.getString("Nombres"));
					empadronamiento.getUnidadEmpresa().setUnecodigoD(rs.getLong("C�digo"));
					empadronamiento.getUnidadEmpresa().setUneplacanroV(rs.getString("N� Placa"));
					empadronamiento.getUnidadEmpresa().setUneannoC(rs.getString("A�o"));
					empadronamiento.getUnidadEmpresa().setUnecolorV(rs.getString("Color"));
					empadronamiento.getUnidadEmpresa().getMarca().setMarnombreV(rs.getString("Marca"));
					empadronamiento.getUnidadEmpresa().getModelo().setModnombre_V(rs.getString("Modelo"));
					empadronamiento.setEmpfechaceseF(rs.getString("Fecha Cese"));
					empadronamiento.setEmpfechainicioF(rs.getString("Fecha Inicio"));
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

	@Override
	public MotEmpadronamiento findByUnidad(Long codigo)
			throws MotEmpadronamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotEmpadronamiento dto=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_EMPADRONAMIENTOBYUNIDAD;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					dto=new MotEmpadronamiento();
					dto.setEpocodigoD(rs.getLong("CODIGO"));
					dto.setEmpfechainicioF(rs.getString("INICIO"));
					dto.setEmpfechaceseF(rs.getString("CESE"));
				}
			}
		} catch (SQLException e) {
			throw new MotEmpadronamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return dto;
	}
}
