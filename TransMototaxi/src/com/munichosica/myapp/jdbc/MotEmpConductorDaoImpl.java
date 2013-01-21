package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotEmpConductorDao;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;

public class MotEmpConductorDaoImpl implements MotEmpConductorDao  {

	@Override
	public void insert(MotEmpConductor dto)
			throws MotEmpConductorDaoException {		
	}

	@Override
	public void update(MotEmpConductor dto)
			throws MotEmpConductorDaoException {
	}

	@Override
	public void delete(MotEmpConductor dto)
			throws MotEmpConductorDaoException {	
	}

	@Override
	public MotEmpConductor findByPrimaryKey(Long codigo)
			throws MotEmpConductorDaoException {
		return null;
	}

	@Override
	public List<MotEmpConductor> findByCriterio(String criterio,
			String texto, Long empcodigoD) throws MotEmpConductorDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotEmpConductor> list = new ArrayList<MotEmpConductor>();
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_LISTADO_CONDUCTORPORRITERIO;1(?,?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, empcodigoD);
			boolean results = stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotPersona persona = null;
				MotConductor conductor = null;
				MotEmpConductor empresaconductor = null; //???
				
				while(rs.next()){
					
					persona = new MotPersona();
					persona.setPernombresV(rs.getString("Nombres"));
					persona.setPerpaternoV(rs.getString("Paterno"));
					persona.setPermaternoV(rs.getString("Materno"));
					persona.setPerdniV(rs.getString("DNI"));
					
					conductor = new MotConductor();
					conductor.setConcodigoD(rs.getLong("CONDCODIGO"));
					conductor.setPersona(persona);
					
					empresaconductor = new MotEmpConductor();
					empresaconductor.setMotosasignadasI(rs.getInt("Motos Asignadas"));
					empresaconductor.setEcofechainicioF(rs.getString("Fecha Inicio"));
					empresaconductor.setEcofechaceseF(rs.getString("Fecha Cese"));
					empresaconductor.setConductor(conductor);
					list.add(empresaconductor);
				}
			}
			
		} catch (SQLException ex) {
			throw new MotEmpConductorDaoException(ex.getMessage());
		} finally{
			
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

}
