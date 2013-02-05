package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotEmprAsociadoDao;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;

public class MotEmprAsociadoDaoImpl implements MotEmprAsociadoDao{
	
	protected static final Logger logger = Logger.getLogger( MotEmprAsociadoDaoImpl.class );

	@Override
	public void procesar(MotEmprAsociado dto) throws MotEmprAsociadoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall( "{call SP_MOT_INS_EMPRASOCIADO;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
			stmt.registerOutParameter(1,Types.DECIMAL);
			stmt.setLong(1, dto.getAsocodigoD());
			stmt.setString(2,dto.getAsorucV());
			stmt.setString(3, dto.getAsorazonsocialV());
			stmt.setLong(4, dto.getPersona().getPercodigoD());
			stmt.setString(5, dto.getPersona().getPerdniV());
			stmt.setString(6, dto.getPersona().getPernombresV());
			stmt.setString(7, dto.getPersona().getPerpaternoV());
			stmt.setString(8, dto.getPersona().getPermaternoV());
			stmt.setString(9, dto.getPersona().getPernacimientoF());
			stmt.setString(10, dto.getPersona().getPerestadocivilC());
			stmt.setString(11, dto.getPersona().getPermovilclaV());
			stmt.setString(12, dto.getPersona().getPermovilmovV());
			stmt.setString(13, dto.getPersona().getPermovilnexV());
			stmt.setString(14, dto.getPersona().getPerteleffijoV());
			stmt.setString(15, dto.getPersona().getPeremailV());
			stmt.setString(16, dto.getPersona().getPerdomicilioV());
			stmt.setString(17, dto.getPersona().getPerubidistV());
			stmt.setString(18, dto.getPersona().getPerubdptoV());
			stmt.setString(19, dto.getPersona().getPerubprovV());
			stmt.setString(20, dto.getPersona().getPersexoC());
			stmt.setLong(21, dto.getEmpresa().getEmpcodigoD());
			stmt.execute();
			
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setAsocodigoD(codigo);
			}
		}
		catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotEmprAsociadoDaoException( "Exception: " + ex.getMessage(), ex );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
	@Override
	public void delete(Long codigo) throws MotEmprAsociadoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_DEL_EMPRASOCIADO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
		} 
		catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotEmprAsociadoDaoException( "Exception: " + ex.getMessage(), ex );
		}
		finally {
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public MotEmprAsociado findByPrimaryKey(Long codigo)
			throws MotEmprAsociadoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		MotEmprAsociado asociado=null;
		try{
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_EMPRASOCIADO;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotPersona persona=null;
				while(rs.next()){
					asociado=new MotEmprAsociado();
					asociado.setAsocodigoD(rs.getLong("ASOCODIGO"));
					asociado.setAsorazonsocialV(rs.getString("RAZONSOCIAL"));
					asociado.setAsorucV(rs.getString("RUC"));
					persona=new MotPersona();
					persona.setPercodigoD(rs.getLong("PERCODIGO"));
					persona.setPerdniV(rs.getString("DNI"));
					persona.setPernombresV(rs.getString("NOMBRES"));
					persona.setPerpaternoV(rs.getString("PATERNO"));
					persona.setPermaternoV(rs.getString("MATERNO"));
					persona.setPernacimientoF(rs.getString("NACIMIENTO"));
					persona.setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					persona.setPermovilclaV(rs.getString("CLARO"));
					persona.setPermovilmovV(rs.getString("MOVISTAR"));
					persona.setPermovilnexV(rs.getString("NEXTEL"));
					persona.setPerteleffijoV(rs.getString("FIJO"));
					persona.setPeremailV(rs.getString("EMAIL"));
					persona.setPerdomicilioV(rs.getString("DOMICILIO"));
					persona.setPerubdptoV(rs.getString("DEPARTAMENTO"));
					persona.setPerubdptonombreV(rs.getString("DEPARTAMENTONOMBRE"));
					persona.setPerubprovV(rs.getString("PROVINCIA"));
					persona.setPerubprovnombreV(rs.getString("PROVINCIANOMBRE"));
					persona.setPerubidistV(rs.getString("DISTRITO"));
					persona.setPerubidistnombreV(rs.getString("DISTRITONOMBRE"));
					persona.setPersexoC(rs.getString("SEXO"));
					asociado.setPersona(persona);
				}
			}
		}
		catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotEmprAsociadoDaoException( "Exception: " + ex.getMessage(), ex );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return asociado;
	}

	@Override
	public List<MotEmprAsociado> findByCriterio(String criterio, String texto,
			Long empcodigoD) throws MotEmprAsociadoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotEmprAsociado> list=new ArrayList<MotEmprAsociado>();
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall( "{call SP_MOT_GET_ASOCIADOSPORCRITERIOANDEMPR;1(?, ?, ?)}" );
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			stmt.setLong(3, empcodigoD);
			
			boolean results=stmt.execute();
            if(results){
                rs=stmt.getResultSet();
                MotEmprAsociado asociado=null;
                MotPersona persona=null;
                while(rs.next()){
                	persona=new MotPersona();
                	persona.setPercodigoD(rs.getLong("CODIGO"));
                	persona.setPerdniV(rs.getString("DNI"));
                	persona.setPernombresV(rs.getString("NOMBRES"));
                	persona.setPerpaternoV(rs.getString("PATERNO"));
                	persona.setPermaternoV(rs.getString("MATERNO"));
                	persona.setPernacimientoF(rs.getString("NACIMIENTO"));
                	persona.setPerestadocivilC(rs.getString("ESTADOCIVIL"));
                	persona.setPermovilclaV(rs.getString("MOVILCLA"));
                	persona.setPermovilmovV(rs.getString("MOVILMOV"));
                	persona.setPermovilnexV(rs.getString("MOVILNEX"));
                	persona.setPerteleffijoV(rs.getString("TELEFFIJO"));
                	persona.setPeremailV(rs.getString("EMAIL"));
                	persona.setPerdomicilioV(rs.getString("DOMICILIO"));
                	persona.setPerubidistV(rs.getString("DISTRITO"));
                	persona.setPerubprovV(rs.getString("PROVINCIA"));
                	persona.setPerubdptoV(rs.getString("DEPARTAMENTO"));
                	asociado=new MotEmprAsociado();
                	asociado.setAsocodigoD(rs.getLong("CODIGO"));
                	asociado.setAsorazonsocialV(rs.getString("RAZONSOCIAL"));
                	asociado.setAsorucV(rs.getString("RUC"));
                	asociado.setAsoestadoC(rs.getString("ESTADO"));
                	asociado.setPersona(persona);
                	list.add(asociado);
                }
            }
		}
		catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotEmprAsociadoDaoException( "Exception: " + ex.getMessage(), ex );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotEmprAsociado> findByCriterio(String criterio, String texto)
			throws MotEmprAsociadoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotEmprAsociado> list=new ArrayList<MotEmprAsociado>();
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall( "{call SP_MOT_GET_ASOCIADOSPORCRITERIO;1(?, ?)}" );
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			
			boolean results=stmt.execute();
            if(results){
                rs=stmt.getResultSet();
                MotEmprAsociado asociado=null;
                MotPersona persona=null;
                while(rs.next()){
                	persona=new MotPersona();
                	persona.setPercodigoD(rs.getLong("CODIGO"));
                	persona.setPerdniV(rs.getString("DNI"));
                	persona.setPernombresV(rs.getString("NOMBRES"));
                	persona.setPerpaternoV(rs.getString("PATERNO"));
                	persona.setPermaternoV(rs.getString("MATERNO"));
                	persona.setPernacimientoF(rs.getString("NACIMIENTO"));
                	persona.setPerestadocivilC(rs.getString("ESTADOCIVIL"));
                	persona.setPermovilclaV(rs.getString("MOVILCLA"));
                	persona.setPermovilmovV(rs.getString("MOVILMOV"));
                	persona.setPermovilnexV(rs.getString("MOVILNEX"));
                	persona.setPerteleffijoV(rs.getString("TELEFFIJO"));
                	persona.setPeremailV(rs.getString("EMAIL"));
                	persona.setPerdomicilioV(rs.getString("DOMICILIO"));
                	persona.setPerubidistV(rs.getString("DISTRITO"));
                	persona.setPerubprovV(rs.getString("PROVINCIA"));
                	persona.setPerubdptoV(rs.getString("DEPARTAMENTO"));
                	asociado=new MotEmprAsociado();
                	asociado.setAsocodigoD(rs.getLong("CODIGO"));
                	asociado.setAsorazonsocialV(rs.getString("RAZONSOCIAL"));
                	asociado.setAsorucV(rs.getString("RUC"));
                	asociado.setAsoestadoC(rs.getString("ESTADO"));
                	asociado.setPersona(persona);
                	list.add(asociado);
                }
            }
		}
		catch (Exception ex) {
			logger.error( "Exception: " + ex.getMessage(), ex );
			throw new MotEmprAsociadoDaoException( "Exception: " + ex.getMessage(), ex );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
