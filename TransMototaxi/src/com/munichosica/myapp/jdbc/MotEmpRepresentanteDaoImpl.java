package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.munichosica.myapp.dao.MotEmpRepresentanteDao;
import com.munichosica.myapp.dto.MotEmpProp;
import com.munichosica.myapp.dto.MotEmpPropietario;
import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotEmpRepresentanteDaoException;

public class MotEmpRepresentanteDaoImpl implements MotEmpRepresentanteDao {

	@Override
	public MotEmpRepresentante findByEmpresa(long codigo)
			throws MotEmpRepresentanteDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		MotEmpRepresentante empRepresentate = null;
		MotUbigeo distrito = null;
		MotUbigeo provincia = null;
		MotUbigeo departamento = null;
		MotPersona persona = null;
		MotEmpPropietario empPropietario = null;
		MotEmpProp empProp = null;
		MotEmpresa empresa = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_GET_DATOS_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);	
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					persona = new MotPersona();
					persona.setPernombresV(rs.getString("Nombres"));
					persona.setPerpaternoV(rs.getString("Paterno"));
					persona.setPermaternoV(rs.getString("Materno"));
					
					empPropietario = new MotEmpPropietario();
					empPropietario.setPersona(persona);
					
					empresa = new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("Codigo"));
					empresa.setEmpdireccionV(rs.getString("Direccion"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmptelefono1V(rs.getString("Telefono1"));
					empresa.setEmptelefono2V(rs.getString("Telefono2"));
					empresa.setEmpcelularclaV(rs.getString("Claro"));
					empresa.setEmpcelularmovV(rs.getString("Movistar"));
					empresa.setEmpcelularnexV(rs.getString("Nextel"));
					empresa.setEmpmailV(rs.getString("Email"));
					empresa.setEmppagwebV(rs.getString("PagWeb"));
					
					distrito = new MotUbigeo();
					provincia = new MotUbigeo();
					departamento = new MotUbigeo();
					departamento.setNombubigeo(rs.getString("Departamento"));
					provincia.setNombubigeo(rs.getString("Provincia"));
					distrito.setNombubigeo(rs.getString("Distrito"));
					empresa.setDepartamento(departamento);
					empresa.setDistrito(distrito);
					empresa.setProvincia(provincia);
					
					empProp = new MotEmpProp();
					empProp.setEmpPropietario(empPropietario);
					empProp.setEmpresa(empresa);

					empRepresentate = new MotEmpRepresentante();
					empRepresentate.setEmpProp(empProp);

				}
			}
			
		} catch (Exception ex) {
			throw new MotEmpRepresentanteDaoException( "Exception: " + ex.getMessage(),ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return empRepresentate;

	}
	

	

}
