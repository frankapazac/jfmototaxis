package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotUnidConductorDao;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotModelo;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.dto.MotPropUnidadEmpresa;
import com.munichosica.myapp.dto.MotUnidConductor;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotUnidConductorDaoException;

public class MotUnidConductorDaoImpl implements MotUnidConductorDao {
	
	protected static final Logger logger = Logger.getLogger(MotUnidConductorDaoImpl.class);
	
	@Override
	public List<MotUnidConductor> findByCriterio(String criterio, String texto,
			Long empcodigo_D,Long condcodigo) throws MotUnidConductorDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotUnidConductor> list = new ArrayList<MotUnidConductor>();
		
		try {
			
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_PROPIETARIOVEHICULOXCRITERIO;1(?,?,?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, empcodigo_D);
			stmt.setLong(4, condcodigo);
			
			
			boolean results = stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				
				MotPersona persona = null;
				MotConductor conductor = null;
				
				MotUnidadEmpresa mototaxi = null;
				MotModelo modelo = null;
				MotPropUnidadEmpresa propietariomoto = null;
				MotUnidConductor unidadconductor = null;
				
				while(rs.next()){
					persona = new MotPersona();
					persona.setPernombresV(rs.getString("Nombres"));
					persona.setPerpaternoV(rs.getString("Paterno"));
					persona.setPermaternoV(rs.getString("Materno"));
					persona.setPerdniV(rs.getString("DNI"));
					conductor = new MotConductor();
					conductor.setPersona(persona);
					
					mototaxi = new MotUnidadEmpresa();
					mototaxi.setUneplacanro_V(rs.getString("N° Placa"));
					mototaxi.setUnemarca_V(rs.getString("Marca"));
					mototaxi.setUneanno_C(rs.getString("Año"));
					mototaxi.setUnecolor_V(rs.getString("Color"));
					modelo = new MotModelo();
					modelo.setModnombre_V(rs.getString("Modelo"));
					mototaxi.setModelo(modelo);
					
					propietariomoto = new MotPropUnidadEmpresa();
					propietariomoto.setMototaxi(mototaxi);
										
					unidadconductor = new MotUnidConductor();
					unidadconductor.setUcocodigoD(rs.getLong("codUndConductor"));
					unidadconductor.setUcofechainicioF(rs.getString("Fec. Inicio"));
					unidadconductor.setUcofechacese(rs.getString("Fec.Cese"));
					unidadconductor.setConductor(conductor);
					unidadconductor.setPropietariomoto(propietariomoto);
					list.add(unidadconductor);								
				}
			}
			
		} catch (SQLException ex) {
			
			logger.error("Exception : " + ex.getMessage(), ex);
			throw new MotUnidConductorDaoException( "Exception: " + ex.getMessage(), ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
	}

	@Override
	public void insert(MotUnidConductor dto) 
			throws MotUnidConductorDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_CESE_CONDUCTOR_MOTOTAXI;1(?,?,?)}");
			stmt.setLong(1,dto.getUcocodigoD());
			stmt.setString(2, dto.getUcofechacese());
			stmt.setString(3, dto.getUcoobservacionesV());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotUnidConductorDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
}
