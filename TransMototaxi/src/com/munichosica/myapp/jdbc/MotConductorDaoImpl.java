package com.munichosica.myapp.jdbc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotConductorDao;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.util.FileUtil;


public class MotConductorDaoImpl implements MotConductorDao {
		
	protected static final Logger logger = Logger.getLogger( MotEmprAsociadoDaoImpl.class );
	
	@Override
	public void insert(MotConductor dto) throws MotConductorDaoException {
			
	}

	@Override
	public void update(MotConductor dto) throws MotConductorDaoException {
	}

	@Override
	public void delete(MotConductor dto) throws MotConductorDaoException {
	}

	@Override
	public MotConductor findByPrimaryKey(Long codigo)throws MotConductorDaoException {
		return null;
	}

	@Override
	public List<MotConductor> findByCriterio(String criterio, String texto,Long concodigoD) throws MotConductorDaoException {
		return null;
	}

	@Override
	public List<MotConductor> findAll() throws MotConductorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotConductor> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{ call SP_MOT_GET_FINDALLCONDUCTOR;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotConductor>();
				rs=stmt.getResultSet();
				MotConductor conductor=null;
				while(rs.next()){
					conductor=new MotConductor();
					conductor.setConcodigoD(rs.getLong("CODIGO"));
					conductor.getPersona().setPernombresV(rs.getString("NOMBRES"));
					conductor.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					conductor.getPersona().setPermaternoV(rs.getString("MATERNO"));
					list.add(conductor);
				}
			}
		} catch (SQLException e) {
			throw new MotConductorDaoException(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public MotConductor findByDNI(String dni) throws MotConductorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotConductor conductor=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{ call SP_MOT_GET_CONDUCTORBYDNI;1(?)}");
			stmt.setString(1, dni);	
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					conductor=new MotConductor();
					conductor.setConcodigoD(rs.getLong("CONCODIGO"));
					conductor.getPersona().setPernombresV(rs.getString("NOMBRES"));
					conductor.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					conductor.getPersona().setPermaternoV(rs.getString("MATERNO"));
					conductor.getPersona().setPerdomicilioV(rs.getString("DIRECCION"));
					conductor.getPersona().setPerteleffijoV(rs.getString("TELEFONO"));
					conductor.getPersona().setPermovilmovV(rs.getString("CELULAR"));
					conductor.getPersona().setPerdniV(rs.getString("DNI"));
					conductor.getArchivo().setAdjnumeroV(rs.getString("LIC_NUMERO"));
					conductor.getArchivo().setAdjfechaemisionF(rs.getString("LIC_EMISION"));
					conductor.getArchivo().setAdjfechacaducidadF(rs.getString("LIC_CADUCIDAD"));
					conductor.getArchivo().setAdjestadoV(rs.getString("ESTADO"));
					conductor.setMensaje(rs.getString("MENSAJE"));
				}
			}
		} catch (SQLException e) {
			throw new MotConductorDaoException(e.getMessage(), e);
		}
		return conductor;
	}

	@Override
	public MotConductor findByCodigo(String codigo)
			throws MotConductorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotConductor conductor=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{ call SP_MOT_GET_CONDUCTORBYCODIGO;1(?)}");
			stmt.setString(1, codigo);	
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					conductor=new MotConductor();
					conductor.setConcodigoD(rs.getLong("CONCODIGO"));
					conductor.getPersona().setPernombresV(rs.getString("NOMBRES"));
					conductor.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					conductor.getPersona().setPermaternoV(rs.getString("MATERNO"));
					conductor.getPersona().setPerdomicilioV(rs.getString("DIRECCION"));
					conductor.getPersona().setPerteleffijoV(rs.getString("TELEFONO"));
					conductor.getPersona().setPermovilmovV(rs.getString("CELULAR"));
					conductor.getPersona().setPerdniV(rs.getString("DNI"));
					conductor.getArchivo().setAdjnumeroV(rs.getString("LIC_NUMERO"));
					conductor.getArchivo().setAdjfechaemisionF(rs.getString("LIC_EMISION"));
					conductor.getArchivo().setAdjfechacaducidadF(rs.getString("LIC_CADUCIDAD"));
					conductor.getArchivo().setAdjestadoV(rs.getString("ESTADO"));
					conductor.setMensaje(rs.getString("MENSAJE"));
				}
			}
		} catch (SQLException e) {
			throw new MotConductorDaoException(e.getMessage(), e);
		}
		return conductor;
	}

	@Override
	public MotConductor findByPmoCodigo(Long pmocodigo)
			throws MotConductorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotConductor conductor=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{ call SP_MOT_GET_FINDCONDUCTORBYPMOCODIGO;1(?)}");
			stmt.setLong(1, pmocodigo);	
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					conductor=new MotConductor();
					conductor.getArchivo().setAdjarchivoB(rs.getBytes("FOTO")!=null?FileUtil.deCompress(rs.getBytes("FOTO")):null);
					conductor.getArchivo().setAdjnombreV(rs.getString("FOTO_NOMBRE"));
				}
			}
		} catch (SQLException | IOException | DataFormatException e) {
			throw new MotConductorDaoException(e.getMessage(), e);
		}
		return conductor;
	}
	
	

	
}
