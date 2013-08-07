package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotEmpresaDao;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;

public class MotEmpresaDaoImpl implements MotEmpresaDao {

	@Override
	public MotEmpresa findByEmpresa(Long codigo)throws MotEmpresaDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpresa empresa = new MotEmpresa();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_GET_DATOS_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);
									
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				MotUbigeo distrito = null;
				MotUbigeo provincia = null;
				MotUbigeo departamento = null;
				while(rs.next()){
					empresa.setEmpcodigoD(rs.getLong("Codigo"));
					empresa.setEmpdireccionV(rs.getString("Direccion"));
					distrito = new MotUbigeo();
					provincia = new MotUbigeo();
					departamento = new MotUbigeo();
					departamento.setNombubigeo(rs.getString("Departamento"));
					provincia.setNombubigeo(rs.getString("Provincia"));
					distrito.setNombubigeo(rs.getString("Distrito"));
					empresa.setDepartamento(departamento);
					empresa.setDistrito(distrito);
					empresa.setProvincia(provincia);
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmptelefono1V(rs.getString("Telefono1"));
					empresa.setEmptelefono2V(rs.getString("Telefono2"));
					empresa.setEmpcelularclaV(rs.getString("Claro"));
					empresa.setEmpcelularmovV(rs.getString("Movistar"));
					empresa.setEmpcelularnexV(rs.getString("Nextel"));
					empresa.setEmpmailV(rs.getString("Email"));
					empresa.setEmppagwebV(rs.getString("PagWeb"));
				}
			}
			
		} catch (Exception ex) {
			throw new MotEmpresaDaoException( "Exception: " + ex.getMessage(),ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return empresa;
	}

	@Override
	public void update(MotEmpresa dto) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		System.out.println("hols");
		try {
			System.out.println(dto.getEmpcodigoD());
			System.out.println(dto.getEmppagwebV());
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_DATOS_EMPRESA;1(?,?,?,?,?,?,?,?)}");
			stmt.setLong(1, dto.getEmpcodigoD());
			
			stmt.setString(2, dto.getEmptelefono1V());
			stmt.setString(3, dto.getEmptelefono2V());
			stmt.setString(4, dto.getEmpcelularclaV());
			stmt.setString(5, dto.getEmpcelularmovV());
			stmt.setString(6, dto.getEmpcelularnexV());
			stmt.setString(7, dto.getEmpmailV());
			stmt.setString(8, dto.getEmppagwebV());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public void insert(MotEmpresa empresa) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_EMPRESA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			if(empresa.getEmpcodigoD()!=null)
				stmt.setLong(1, empresa.getEmpcodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			stmt.setLong(2, empresa.getZona().getZoncodigo_I());
			stmt.setString(3, empresa.getEmprazonsocialV());
			stmt.setString(4, empresa.getEmpdireccionV());
			stmt.setString(5, empresa.getEmprucV());
			stmt.setString(6, empresa.getEmptelefono1V());
			stmt.setString(7, empresa.getEmptelefono2V());
			stmt.setString(8, empresa.getEmpcelularmovV());
			stmt.setString(9, empresa.getEmpcelularclaV());
			stmt.setString(10, empresa.getEmpcelularnexV());
			stmt.setString(11, empresa.getEmpmailV());
			stmt.setString(12, empresa.getEmppagwebV());
			stmt.setString(13, empresa.getDistrito().getIdubigeo());
			stmt.setString(14, empresa.getProvincia().getIdubigeo());
			stmt.setString(15, empresa.getDepartamento().getIdubigeo());
			stmt.setString(16, empresa.getEmpfechainioperacionF());
			stmt.setString(17, empresa.getEmpfechaceseoperacionF());
			stmt.setString(18, empresa.getEmpresolucionV());
			stmt.setInt(19, empresa.getEmpunidadesautorizadasI());
			stmt.setString(20, empresa.getEmpescriturapublicaV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				empresa.setEmpcodigoD(codigo);
			}
			
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public MotEmpresa get(Long codigo) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpresa empresa=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					empresa=new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("CODIGO"));
					empresa.getZona().setZoncodigo_I(rs.getLong("ZONCODIGO"));
					empresa.setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmpdireccionV(rs.getString("DIRECCION"));
					empresa.getDepartamento().setIdubigeo(rs.getString("DEPARTAMENTO"));
					empresa.getDepartamento().setNombubigeo(rs.getString("DEPARTAMENTONOMBRE"));
					empresa.getProvincia().setIdubigeo(rs.getString("PROVINCIA"));
					empresa.getProvincia().setNombubigeo(rs.getString("PROVINCIANOMBRE"));
					empresa.getDistrito().setIdubigeo(rs.getString("DISTRITO"));
					empresa.getDistrito().setNombubigeo(rs.getString("DISTRITONOMBRE"));
					empresa.setEmptelefono1V(rs.getString("TELEFONO1"));
					empresa.setEmptelefono2V(rs.getString("TELEFONO2"));
					empresa.setEmpresolucionV(rs.getString("RESOLUCION"));
					empresa.setEmppagwebV(rs.getString("PAGWEB"));
					empresa.setEmpmailV(rs.getString("CORREO"));
					empresa.setEmpcelularmovV(rs.getString("MOVISTAR"));
					empresa.setEmpcelularclaV(rs.getString("CLARO"));
					empresa.setEmpcelularnexV(rs.getString("NEXTEL"));
					empresa.setEmpfechainioperacionF(rs.getString("INICIO"));
					empresa.setEmpfechaceseoperacionF(rs.getString("CESE"));
					empresa.setEmpunidadesautorizadasI(rs.getInt("UNIDADES"));
					empresa.setEmpescriturapublicaV(rs.getString("ESCRITURA"));
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return empresa;
	}

	@Override
	public void delete(Long codigo) throws MotEmpresaDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(MotEmpresa empresa) throws MotEmpresaDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MotEmpresa> listar(String criterio, String texto)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<MotEmpresa> list=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMPRESASPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotEmpresa>();
				rs=stmt.getResultSet();
				MotEmpresa empresa=null;
				while(rs.next()){
					empresa=new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("CODIGO"));
					empresa.getZona().setZonnombre_V(rs.getString("ZONA"));
					empresa.setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					empresa.setEmpdireccionV(rs.getString("DIRECCION"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmptelefono1V(rs.getString("TELEFONO1"));
					empresa.setEmptelefono2V(rs.getString("TELEFONO2"));
					empresa.setEmpcelularmovV(rs.getString("MOVISTAR"));
					empresa.setEmpcelularclaV(rs.getString("CLARO"));
					empresa.setEmpcelularnexV(rs.getString("NEXTEL"));
					empresa.setEmpmailV(rs.getString("EMAIL"));
					empresa.setEmpfechainioperacionF(rs.getString("INICIO"));
					empresa.setEmpfechaceseoperacionF(rs.getString("CESE"));
					empresa.setEmpunidadesautorizadasI(rs.getInt("UNIDADES"));
					empresa.setEmpescriturapublicaV(rs.getString("ESCRITURA"));
					empresa.setEmpestadoC(rs.getString("ESTADO"));
					list.add(empresa);
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	
	
}
