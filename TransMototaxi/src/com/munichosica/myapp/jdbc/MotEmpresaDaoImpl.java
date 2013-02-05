package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	
	
}
