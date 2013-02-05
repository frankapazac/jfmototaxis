package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
				MotUnidConductor unidadconductor = null;
				while(rs.next()){
					unidadconductor=new MotUnidConductor();
					unidadconductor.getConductor().getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
					unidadconductor.getConductor().getPersona().setPernombresV(rs.getString("NOMBRES"));
					unidadconductor.getConductor().getPersona().setPerpaternoV(rs.getString("PATERNO"));
					unidadconductor.getConductor().getPersona().setPermaternoV(rs.getString("MATERNO"));
					unidadconductor.getConductor().getPersona().setPerdniV(rs.getString("NOMBRES"));
					unidadconductor.getPropietariomoto().getUnidadempresa().setUneplacanroV(rs.getString("PLACANRO"));
					unidadconductor.getPropietariomoto().getUnidadempresa().getMarca().setMarnombreV(rs.getString("MARCA"));
					unidadconductor.getPropietariomoto().getUnidadempresa().getModelo().setModnombre_V(rs.getString("MODELO"));
					unidadconductor.getPropietariomoto().getUnidadempresa().setUneannoC(rs.getString("ANNO"));
					unidadconductor.getPropietariomoto().getUnidadempresa().setUnecolorV(rs.getString("COLOR"));
					unidadconductor.setUcofechainicioF(rs.getString("FECINICIO"));
					unidadconductor.setUcofechacese(rs.getString("FECCESE"));
					unidadconductor.setUcocodigoD(rs.getLong("UCOCODIGO"));
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

	@Override
	public List<MotUnidConductor> findByConductor(Long condcodigo)
			throws MotUnidConductorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotUnidConductor> list=null;
		try {
			list=new ArrayList<MotUnidConductor>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTUNIDCONDUCTOR_BY_CONDUCTOR;1(?)}");
			stmt.setLong(1, condcodigo);
			boolean results=stmt.execute();
			if(results){
				MotUnidConductor motUnidConductor=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					motUnidConductor=new MotUnidConductor();
					motUnidConductor.setUcocodigoD(rs.getLong("UCOCODIGO"));
					motUnidConductor.getPropietariomoto().getAsociado().setAsocodigoD(rs.getLong("ASOCODIGO"));
					motUnidConductor.getPropietariomoto().setPmocodigoD(rs.getLong("PMOCODIGO"));
					motUnidConductor.getPropietariomoto().getAsociado().getPersona().setPernombresV(rs.getString("NOMBRES"));
					motUnidConductor.getPropietariomoto().getAsociado().getPersona().setPerpaternoV(rs.getString("PATERNO"));
					motUnidConductor.getPropietariomoto().getAsociado().getPersona().setPermaternoV(rs.getString("MATERNO"));
					motUnidConductor.getPropietariomoto().getAsociado().getPersona().setPerdniV(rs.getString("DNI"));
					motUnidConductor.getPropietariomoto().getUnidadempresa().setUneplacanroV(rs.getString(""));
					motUnidConductor.getPropietariomoto().getUnidadempresa().getMarca().setMarnombreV(rs.getString(""));
					motUnidConductor.getPropietariomoto().getUnidadempresa().getModelo().setModnombre_V(rs.getString(""));
					motUnidConductor.getPropietariomoto().getUnidadempresa().setUneannoC(rs.getString(""));
					motUnidConductor.getPropietariomoto().getUnidadempresa().setUnecolorV(rs.getString(""));
					motUnidConductor.setUcofechainicioF(rs.getString("FINICIO"));
					motUnidConductor.setUcofechacese(rs.getString("FCESE"));
					list.add(motUnidConductor);
				}
			}
		} catch (SQLException e) {
			throw new MotUnidConductorDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public void asignar(MotUnidConductor dto)
			throws MotUnidConductorDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_MOTUNIDCONDUCTOR;1(?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(2, dto.getPropietariomoto().getPmocodigoD());
			stmt.setLong(3, dto.getConductor().getConcodigoD());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setUcocodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotUnidConductorDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
}
