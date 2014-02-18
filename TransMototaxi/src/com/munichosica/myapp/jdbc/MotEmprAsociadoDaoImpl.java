package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotEmprAsociadoDao;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.util.FileUtil;

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
			stmt.execute();
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
				while(rs.next()){
					asociado=new MotEmprAsociado();
					asociado.setAsocodigoD(rs.getLong("ASOCODIGO"));
					asociado.setAsorazonsocialV(rs.getString("RAZONSOCIAL"));
					asociado.setAsorucV(rs.getString("RUC"));
					asociado.getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
					asociado.getPersona().setPerdniV(rs.getString("DNI"));
					asociado.getPersona().setPernombresV(rs.getString("NOMBRES"));
					asociado.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					asociado.getPersona().setPermaternoV(rs.getString("MATERNO"));
					asociado.getPersona().setPernacimientoF(rs.getString("NACIMIENTO"));
					asociado.getPersona().setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					asociado.getPersona().setPermovilclaV(rs.getString("CLARO"));
					asociado.getPersona().setPermovilmovV(rs.getString("MOVISTAR"));
					asociado.getPersona().setPermovilnexV(rs.getString("NEXTEL"));
					asociado.getPersona().setPerteleffijoV(rs.getString("FIJO"));
					asociado.getPersona().setPeremailV(rs.getString("EMAIL"));
					asociado.getPersona().setPerdomicilioV(rs.getString("DOMICILIO"));
					asociado.getPersona().setPerubdptoV(rs.getString("DEPARTAMENTO"));
					asociado.getPersona().setPerubdptonombreV(rs.getString("DEPARTAMENTONOMBRE"));
					asociado.getPersona().setPerubprovV(rs.getString("PROVINCIA"));
					asociado.getPersona().setPerubprovnombreV(rs.getString("PROVINCIANOMBRE"));
					asociado.getPersona().setPerubidistV(rs.getString("DISTRITO"));
					asociado.getPersona().setPerubidistnombreV(rs.getString("DISTRITONOMBRE"));
					asociado.getPersona().setPersexoC(rs.getString("SEXO"));
					asociado.getFoto().setAdjnombreV(rs.getString("ARCHIVONOMBRE"));
					asociado.getFoto().setAdjarchivoB(rs.getBytes("ARCHIVO")!=null?FileUtil.deCompress(rs.getBytes("ARCHIVO")):null);
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
                	asociado.setAsocodigoD(rs.getLong("ASOCODIGO"));
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

	@Override
	public List<MotEmprAsociado> ExisteAsociadoPorDNI(String dni) throws MotEmprAsociadoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotEmprAsociado> list=new ArrayList<MotEmprAsociado>();
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall( "{call SP_MOT_GET_EMP_ASOCIADOS_EXISTE_DNI;1(?)}" );
			stmt.setString(1, dni);
			
			boolean results=stmt.execute();
            if(results){
                rs=stmt.getResultSet();
                MotEmprAsociado asociado=null;
                while(rs.next()){
                	asociado=new MotEmprAsociado();
                	asociado.getEmpresa().setEmprazonsocialV(rs.getString("RAZON_SOCIAL"));
                	asociado.getEmpresa().setEmpcelularmovV(rs.getString("MOVISTAR"));
                	asociado.getEmpresa().setEmpcelularclaV(rs.getString("CLARO"));
                	asociado.getEmpresa().setEmpcelularnexV(rs.getString("NEXTEL"));
                	asociado.getEmpresa().setEmptelefono1V(rs.getString("TELEFONO"));
                	asociado.setAsoestadoC(rs.getString("ESTADO"));
                	asociado.setAsofechainicioF(rs.getString("INICIO"));
                	asociado.setAsofechaceseF(rs.getString("CESE"));
                	asociado.setAsoobservacionV(rs.getString("OBSERVACION"));
                	asociado.getEmpresa().setEmpestadoC(rs.getString("ACTIVO"));
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
	public MotEmprAsociado obtenerAsociadoCesar(Long codigo)
			throws MotEmprAsociadoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		MotEmprAsociado asociado=null;
		try{
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_GET_EMP_ASOCIADO_CESE;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					asociado=new MotEmprAsociado();
					asociado.getPersona().setPernombresV(rs.getString("NOMBRES"));
					asociado.setAsoobservacionV(rs.getString("OBSERVACION"));
					asociado.setAsofechaceseF(rs.getString("CESE"));
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
	public void guardarCese(MotEmprAsociado dto)
			throws MotEmprAsociadoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall( "{call SP_UPD_EMP_ASOCIADO_CESE;1(?,?,?)}" );
			stmt.setLong(1, dto.getAsocodigoD());
			stmt.setString(2,dto.getAsofechaceseF());
			stmt.setString(3, dto.getAsoobservacionV());
			stmt.execute();
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

}
