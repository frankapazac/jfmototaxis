package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInspectorDao;
import com.munichosica.myapp.dto.MotInspector;
import com.munichosica.myapp.exceptions.MotInspectorDaoException;
import com.munichosica.myapp.util.FileUtil;

public class MotInspectorDaoImpl implements MotInspectorDao{

	@Override
	public void procesar(MotInspector dto) throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INSPECTOR;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(1, dto.getInscodigoI());
			stmt.setLong(2, dto.getPersona().getPercodigoD());
			stmt.setString(3, dto.getPersona().getPerdniV());
			stmt.setString(4, dto.getPersona().getPernombresV());
			stmt.setString(5, dto.getPersona().getPerpaternoV());
			stmt.setString(6, dto.getPersona().getPermaternoV());
			stmt.setString(7, dto.getPersona().getPernacimientoF());
			stmt.setString(8, dto.getPersona().getPerestadocivilC());
			stmt.setString(9, dto.getPersona().getPermovilclaV());
			stmt.setString(10, dto.getPersona().getPermovilmovV());
			stmt.setString(11, dto.getPersona().getPermovilnexV());
			stmt.setString(12, dto.getPersona().getPerteleffijoV());
			stmt.setString(13, dto.getPersona().getPeremailV());
			stmt.setString(14, dto.getPersona().getPerdomicilioV());
			stmt.setString(15, dto.getPersona().getPerubidistV());
			stmt.setString(16, dto.getPersona().getPerubdptoV());
			stmt.setString(17, dto.getPersona().getPerubprovV());
			stmt.setString(18, dto.getPersona().getPersexoC());
			stmt.execute();
			Integer codigo=stmt.getInt(1);
			if(codigo!=null){
				dto.setInscodigoI(codigo);
			}
		} catch (SQLException e) {
			throw new MotInspectorDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotInspector> findByCriterio(String criterio, String texto)
			throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInspector> list=null;
		try {
			list=new ArrayList<MotInspector>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INSPECTORESPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				MotInspector inspector=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					inspector=new MotInspector();
					inspector.setInscodigoI(rs.getInt("INSCODIGO"));
					inspector.setInsestadoC(rs.getString("ESTADO"));
					inspector.getPersona().setPercodigoD(rs.getLong("CODIGO"));
					inspector.getPersona().setPerdniV(rs.getString("DNI"));
					inspector.getPersona().setPernombresV(rs.getString("NOMBRES"));
					inspector.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					inspector.getPersona().setPermaternoV(rs.getString("MATERNO"));
					inspector.getPersona().setPernacimientoF(rs.getString("NACIMIENTO"));
					inspector.getPersona().setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					inspector.getPersona().setPermovilclaV(rs.getString("MOVILCLA"));
					inspector.getPersona().setPermovilmovV(rs.getString("MOVILMOV"));
					inspector.getPersona().setPermovilnexV(rs.getString("MOVILNEX"));
					inspector.getPersona().setPerteleffijoV(rs.getString("TELEFFIJO"));
					inspector.getPersona().setPeremailV(rs.getString("EMAIL"));
					inspector.getPersona().setPerdomicilioV(rs.getString("DOMICILIO"));
					inspector.getPersona().setPerubidistV(rs.getString("DISTRITO"));
					inspector.getPersona().setPerubdptoV(rs.getString("DEPARTAMENTO"));
					inspector.getPersona().setPerubprovV(rs.getString("PROVINCIA"));
					list.add(inspector);
				}
			}
		} catch (SQLException e) {
			throw new MotInspectorDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

	@Override
	public MotInspector findByPrimaryKey(int codigo) throws MotInspectorDaoException{
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotInspector inspector=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INSPECTOR;1(?)}");
			stmt.setInt(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					System.out.println("INGRESOOOOO");
					inspector=new MotInspector();
					inspector.setInscodigoI(rs.getInt("INSCODIGO"));
					inspector.setInsestadoC(rs.getString("ESTADO"));
					inspector.getPersona().setPercodigoD(rs.getLong("CODIGO"));
					inspector.getPersona().setPerdniV(rs.getString("DNI"));
					inspector.getPersona().setPernombresV(rs.getString("NOMBRES"));
					inspector.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					inspector.getPersona().setPermaternoV(rs.getString("MATERNO"));
					inspector.getPersona().setPernacimientoF(rs.getString("NACIMIENTO"));
					inspector.getPersona().setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					inspector.getPersona().setPermovilclaV(rs.getString("MOVILCLA"));
					inspector.getPersona().setPermovilmovV(rs.getString("MOVILMOV"));
					inspector.getPersona().setPermovilnexV(rs.getString("MOVILNEX"));
					inspector.getPersona().setPerteleffijoV(rs.getString("TELEFFIJO"));
					inspector.getPersona().setPeremailV(rs.getString("EMAIL"));
					inspector.getPersona().setPerdomicilioV(rs.getString("DOMICILIO"));
					inspector.getPersona().setPersexoC(rs.getString("SEXO"));
					inspector.getPersona().setPerubidistV(rs.getString("DISTRITO"));
					inspector.getPersona().setPerubdptoV(rs.getString("DEPARTAMENTO"));
					inspector.getPersona().setPerubprovV(rs.getString("PROVINCIA"));
					inspector.getPersona().setPerubdptonombreV(rs.getString("DEPARTAMENTONOMBRE"));
					inspector.getPersona().setPerubidistnombreV(rs.getString("DISTRITONOMBRE"));
					inspector.getPersona().setPerubprovnombreV(rs.getString("PROVINCIANOMBRE"));
					inspector.getFoto().setAdjarchivoB(rs.getBytes("FOTO")!=null?FileUtil.deCompress(rs.getBytes("FOTO")):null);
					inspector.getFoto().setAdjnombreV(rs.getString("NOMBRE_FOTO"));
				}
			}
		} catch (Exception e) {
			throw new MotInspectorDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return inspector;
	}

	@Override
	public void delete(int codigo) throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_DEL_INSPECTOR;1(?)}");
			stmt.setInt(1, codigo);
			stmt.execute();
		} catch (SQLException e) {
			throw new MotInspectorDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotInspector> findAllInspectores() throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotInspector> list=new ArrayList<MotInspector>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLINSPECTOR;1}");
			
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotInspector inspector= null;
				while(rs.next()){
					inspector=new MotInspector();
					inspector.setInscodigoI(rs.getInt("INSCODIGO"));
					inspector.getPersona().setPernombresV(rs.getString("NOMBRES"));
					inspector.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					inspector.getPersona().setPermaternoV(rs.getString("MATERNO"));
					list.add(inspector);
				}
			}
		} catch (SQLException ex) {
			throw new MotInspectorDaoException( "Exception: " + ex.getMessage(), ex );
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

	@Override
	public List<MotInspector> findAll() throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInspector> list=null;
		try {
			list=new ArrayList<MotInspector>();
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLINSPECTORES;1}");
			boolean results=stmt.execute();
			if(results){
				MotInspector inspector=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					inspector=new MotInspector();
					inspector.setInscodigoI(rs.getInt("CODIGO"));
					inspector.getPersona().setPernombresV(rs.getString("NOMBRES"));
					inspector.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					inspector.getPersona().setPermaternoV(rs.getString("MATERNO"));
					list.add(inspector);
				}
			}
		} catch (SQLException e) {
			throw new MotInspectorDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}
	
	@Override
	public List<MotInspector> findByNotInCodInspector(int codigo) throws MotInspectorDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotInspector> list=new ArrayList<MotInspector>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALL_INSPECTOR_NOTIN_INSPECTOR;1(?)}");
			stmt.setInt(1, codigo);
			
			boolean results = stmt.execute();			
			if(results){
				rs=stmt.getResultSet();
				MotInspector inspector= null;
				while(rs.next()){
					inspector=new MotInspector();
					inspector.setInscodigoI(rs.getInt("INSCODIGO"));
					inspector.getPersona().setPernombresV(rs.getString("NOMBRES"));
					inspector.getPersona().setPerpaternoV(rs.getString("PATERNO"));
					inspector.getPersona().setPermaternoV(rs.getString("MATERNO"));
					list.add(inspector);
				}
			}
		} catch (SQLException ex) {
			throw new MotInspectorDaoException( "Exception: " + ex.getMessage(), ex );
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}
}
