package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.munichosica.myapp.dao.MotInfraccionDao;
import com.munichosica.myapp.dto.MotInfraccion;
import com.munichosica.myapp.exceptions.MotInfraccionDaoException;

public class MotInfraccionDaoImpl implements MotInfraccionDao {

	protected static final Logger logger = Logger.getLogger( MotInfraccionDaoImpl.class );
	
	@Override
	public List<MotInfraccion> findByCriterio(String criterio, String texto)
			throws MotInfraccionDaoException {
		System.out.println("entro a daoimpl");
		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotInfraccion> list = new ArrayList<MotInfraccion>();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{CALL SP_MOT_GET_INFRACCIONBYCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				MotInfraccion infraccion = null;
				while(rs.next()){
					infraccion = new MotInfraccion();
					infraccion.setInfcodigoD(rs.getLong("CODIGOINF"));
					infraccion.setInfcodigoV(rs.getString("CODIGOINFV"));
					infraccion.setInfinfraccionV(rs.getString("DESCRIPCIÓN"));
					infraccion.setInftipoC(rs.getString("TIPO INFRACCION"));
					infraccion.setInftipoPersonaC(rs.getString("TIPO PERSONA"));
					infraccion.setInfnrouitI(rs.getInt("N° UIT"));
					infraccion.setInfestadoC(rs.getString("ESTADO"));
					list.add(infraccion);
				}
			}
		} catch (Exception ex) {
			logger.error("Exception :" + ex.getMessage(),ex);
			throw new MotInfraccionDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}

		return list;
	}
	
	@Override
	public void procesar(MotInfraccion dto) throws MotInfraccionDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		System.out.println("ENTRO A INSERTAR O ACTUALIZAR INFRACCION");
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_INFRACCION;1 (?,?,?,?,?,?,?)}");
			//stmt.setLong(1, dto.getInfcodigoD());
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setString(2,dto.getInfcodigoV());
			stmt.setString(3,dto.getInfinfraccionV());
			stmt.setString(4,dto.getInftipoC());
			stmt.setString(5, dto.getInftipoPersonaC());
			stmt.setString(6, dto.getInfmedidasAccV());
			stmt.setInt(7, dto.getInfnrouitI());
			
			
			stmt.execute();
			
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setInfcodigoD(codigo);
			}
			//System.out.println("CODIGO INFRACCION : " + codigo);
			System.out.println(codigo);
			System.out.println(dto.getInfcodigoV());
			System.out.println(dto.getInfinfraccionV());
			System.out.println(dto.getInftipoC());
			System.out.println(dto.getInftipoPersonaC());
			System.out.println(dto.getInfmedidasAccV());
			System.out.println(dto.getInfnrouitI());
								
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}

	@Override
	public MotInfraccion findByIdInfraccion(Long codigo) throws MotInfraccionDaoException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotInfraccion infraccion= null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_INFRACCION_BY_ID;1 (?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				if(rs.next()){
				infraccion = new MotInfraccion();
				infraccion.setInfcodigoD(rs.getLong("CODIGOINF"));
				infraccion.setInfcodigoV(rs.getString("CODIGOINFV"));
				infraccion.setInfinfraccionV(rs.getString("DESCRIPCIÓN"));
				infraccion.setInftipoC(rs.getString("TIPO INFRACCION").trim());
				infraccion.setInftipoPersonaC(rs.getString("INFTITPOPERSONA_C"));
				infraccion.setInfmedidasAccV(rs.getString("INFTITPOPERSONA_C"));
				infraccion.setInfnrouitI(rs.getInt("N° UIT"));
				}
			}
								
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
		return infraccion;
	}

	@Override
	public void  delete (MotInfraccion dto) throws MotInfraccionDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_ESTADO_INFRACCION;1 (?)}");
			stmt.setLong(1, dto.getInfcodigoD());			
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}		
	}
	
	@Override
	public List<MotInfraccion> findAll() throws MotInfraccionDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInfraccion> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLCODINFRACCION;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInfraccion>();
				MotInfraccion infraccion=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					infraccion=new MotInfraccion();
					infraccion.setInfcodigoD(rs.getLong("CODIGO"));
					infraccion.setInfcodigoV(rs.getString("INFCODIGO"));
					list.add(infraccion);
				}
			}
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotInfraccion> findByEstado(String estado)
			throws MotInfraccionDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInfraccion> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDINFRACCIONBYESTADO;1(?)}");
			stmt.setString(1, estado);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInfraccion>();
				MotInfraccion infraccion=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					infraccion=new MotInfraccion();
					infraccion.setInfcodigoD(rs.getLong("CODIGO"));
					infraccion.setInfcodigoV(rs.getString("INFCODIGO"));
					list.add(infraccion);
				}
			}
		} catch (SQLException e) {
			throw new MotInfraccionDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
