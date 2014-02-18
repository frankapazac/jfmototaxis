package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.munichosica.myapp.dao.MotEmpRepresentanteDao;
import com.munichosica.myapp.dto.MotEmpPropietario;
import com.munichosica.myapp.dto.MotEmpPropietarioDelete;
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
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_GET_DATOS_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);	
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					empRepresentate=new MotEmpRepresentante();
					empRepresentate.getEmpresa().setEmpcodigoD(rs.getLong("Codigo"));
					empRepresentate.getEmpresa().setEmpdireccionV(rs.getString("Direccion"));
					empRepresentate.getEmpresa().setEmprucV(rs.getString("RUC"));
					empRepresentate.getEmpresa().setEmptelefono1V(rs.getString("Telefono1"));
					empRepresentate.getEmpresa().setEmptelefono2V(rs.getString("Telefono2"));
					empRepresentate.getEmpresa().setEmpcelularclaV(rs.getString("Claro"));
					empRepresentate.getEmpresa().setEmpcelularmovV(rs.getString("Movistar"));
					empRepresentate.getEmpresa().setEmpcelularnexV(rs.getString("Nextel"));
					empRepresentate.getEmpresa().setEmpmailV(rs.getString("Email"));
					empRepresentate.getEmpresa().setEmppagwebV(rs.getString("PagWeb"));
					empRepresentate.getEmpresa().getDepartamento().setNombubigeo(rs.getString("Departamento"));
					empRepresentate.getEmpresa().getProvincia().setNombubigeo(rs.getString("Provincia"));
					empRepresentate.getEmpresa().getDistrito().setNombubigeo(rs.getString("Distrito"));
					empRepresentate.getEmpProp().getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
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
