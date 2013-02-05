package com.munichosica.myapp.jdbc;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.munichosica.myapp.dao.MotParaderoDao;
import com.munichosica.myapp.dto.MotEmpParadero;
import com.munichosica.myapp.dto.MotParadero;
import com.munichosica.myapp.dto.MotZona;
import com.munichosica.myapp.exceptions.MotParaderoDaoException;

public class MotParaderoDaoImpl implements MotParaderoDao {
	
	//protected static final Logger logger = Logger.getLogger( MotParaderoDao.class );
	
	@Override
	public void insert(MotParadero dto) throws MotParaderoDaoException {
		
		
	}

	@Override
	public void update(MotParadero dto) throws MotParaderoDaoException {
		
		
	}

	@Override
	public void delete(MotParadero dto) throws MotParaderoDaoException {
		
		
	}

	@Override
	public MotParadero findByPrimaryKey(Long codigo)
			throws MotParaderoDaoException {
		
		return null;
	}

	@Override
	public List<MotParadero> findByCriterio(String criterio, String texto,
			Long parcodigoI) throws MotParaderoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotParadero> list = new ArrayList<MotParadero>();
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_PARADEROSPORCRITERIO;1(?, ?, ?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, parcodigoI);
			
			boolean results = stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotParadero paradero= null;
				MotZona zona=null;
				while(rs.next()){
					paradero=new  MotParadero();
					paradero.setParcodigoI(rs.getInt("Epacodigo"));
					paradero.setParnombreV(rs.getString("Paradero"));
					paradero.setParubicacionV(rs.getString("Ubicación"));
					paradero.setParestadoC(rs.getString("ESTADO"));
					zona=new MotZona();
					zona.setZonnombre_V(rs.getString("Zona Administrativa"));
					paradero.setZona(zona);
					//paradero.setParzonaadmI(rs.getLong("Zona Administrativa"));
					list.add(paradero);
				}
			}
		} catch (Exception ex) {
			throw new MotParaderoDaoException( "Exception: " + ex.getMessage(),ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
		}
		
		return list;
		
	}

	@Override
	public List<MotParadero> findZonaByEmpresa(Long empcodigoD) throws MotParaderoDaoException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotParadero> list = new ArrayList<MotParadero>();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_LISTA_PARADEROS_XEMPRESA_ACTIVOS;1(?)}");
			stmt.setLong(1, empcodigoD);
			
			boolean results = stmt.execute();
			if(results){
			rs=stmt.getResultSet();
			MotParadero paradero = null;
				while(rs.next()){
					paradero = new MotParadero();
					paradero.setParcodigoI(rs.getInt("Código"));
					paradero.setParnombreV(rs.getString("Paradero"));
					list.add(paradero);
				}
			}
			
		} catch (SQLException ex) {
			throw new MotParaderoDaoException(ex.getMessage());
		} finally{
			
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
		}
		
		return list;
	}

	
	
	@Override
	public MotParadero findByParadero(int codigo) throws MotParaderoDaoException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		MotParadero paradero= new MotParadero();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_DETALLE_PARADERO;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
			rs=stmt.getResultSet();
			MotZona zona = null;
				while(rs.next()){
					paradero = new MotParadero();
					paradero.setParcodigoI(rs.getInt("CODIGO"));
					paradero.setParubicacionV(rs.getString("Ubicación"));
					zona = new MotZona();
					zona.setZonnombre_V(rs.getString("Zona"));
					paradero.setZona(zona);
				}
			}
			
		} catch (SQLException ex) {
			throw new MotParaderoDaoException(ex.getMessage());
		} finally{
			
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
		}
		
		return paradero;
	}
}
