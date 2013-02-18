package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotEmpConductorDao;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.dto.MotEmpConductor;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotEmpConductorDaoException;

public class MotEmpConductorDaoImpl implements MotEmpConductorDao  {
	
	protected static final Logger logger = Logger.getLogger( MotEmprAsociadoDaoImpl.class );
	
	@Override
	public void procesar(MotEmpConductor dto)throws MotEmpConductorDaoException {	
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_PERSONA_CONDUCTOR;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1,Types.DECIMAL);
			stmt.setLong(1, dto.getConductor().getConcodigoD());
			stmt.setLong(2, dto.getConductor().getPersona().getPercodigoD());
			stmt.setString(3, dto.getConductor().getPersona().getPerdniV());
			stmt.setString(4, dto.getConductor().getPersona().getPernombresV());
			stmt.setString(5, dto.getConductor().getPersona().getPerpaternoV());
			stmt.setString(6, dto.getConductor().getPersona().getPermaternoV());
			stmt.setString(7, dto.getConductor().getPersona().getPernacimientoF());
			stmt.setString(8, dto.getConductor().getPersona().getPerestadocivilC());
			stmt.setString(9, dto.getConductor().getPersona().getPermovilclaV());
			stmt.setString(10, dto.getConductor().getPersona().getPermovilmovV());
			stmt.setString(11, dto.getConductor().getPersona().getPermovilnexV());
			stmt.setString(12, dto.getConductor().getPersona().getPerteleffijoV());
			stmt.setString(13, dto.getConductor().getPersona().getPeremailV());
			stmt.setString(14, dto.getConductor().getPersona().getPerdomicilioV());
			stmt.setString(15, dto.getConductor().getPersona().getPerubidistV());
			stmt.setString(16, dto.getConductor().getPersona().getPerubdptoV());
			stmt.setString(17, dto.getConductor().getPersona().getPerubprovV());
			stmt.setString(18, dto.getConductor().getPersona().getPersexoC());
			stmt.setLong(19, dto.getEmpresa().getEmpcodigoD());
			stmt.setString(20, dto.getEcofechainicioF());
			stmt.execute();
			
			Long codigo= stmt.getLong(1);
			if(codigo != null){
				dto.getConductor().setConcodigoD(codigo);
			}
					
		} catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex);
			throw new MotEmpConductorDaoException( "Exception: " + ex.getMessage(), ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
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
