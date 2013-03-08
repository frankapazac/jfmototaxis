package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotOperativoDao;
import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;

public class MotOperativoDaoImpl implements MotOperativoDao{
	
	protected static final Logger logger = Logger.getLogger( MotOperativoDaoImpl.class );
	
	@Override
	public void insert(MotOperativo dto) throws MotOperativoDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_OPERATIVO;1 (?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(2,dto.getOpecodigoD());
			stmt.setString(3,dto.getOpetituloV());
			stmt.setString(4,dto.getOpedescripcionV());
			stmt.setString(5, dto.getOpelugarV());
			stmt.setString(6, dto.getOpereferencia());
			stmt.setString(7, dto.getOpefecha());
			stmt.setString(8, dto.getOpehora());
			stmt.setLong(9, dto.getZona().getZoncodigo_I());
			stmt.setLong(10, dto.getInspector().getInscodigoI());
			
			stmt.execute();

			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setOpecodigoD(codigo);
			}
			System.out.println(codigo);
								
		} catch (SQLException e) {
			throw new MotOperativoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}
	
	@Override
	public MotOperativo findByIdOperativo(Long codigo) throws MotOperativoDaoException {
		
		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotOperativo operativo = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_MOT_OPERATIVOBYCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					operativo = new MotOperativo();
					operativo.setOpecodigoD(rs.getLong("CodOperativo"));
					operativo.setOpetituloV(rs.getString("Nombre Operativo"));
					operativo.setOpedescripcionV(rs.getString("Descripcion"));
					operativo.setOpelugarV(rs.getString("Lugar"));
					operativo.setOpereferencia(rs.getString("Referencia"));
					operativo.getZona().setZonnombre_V(rs.getString("Zona"));
					operativo.getZona().setZoncodigo_I(rs.getLong("CodZona"));
					operativo.setOpefecha(rs.getString("Fecha"));
					operativo.setOpehora(rs.getString("Hora"));
					operativo.getInspector().getPersona().setPernombresV(rs.getString("Nombre"));
					operativo.getInspector().getPersona().setPerpaternoV(rs.getString("Paterno"));
					operativo.getInspector().getPersona().setPermaternoV(rs.getString("Materno"));
					operativo.getInspector().setInscodigoI(rs.getInt("CodInspector"));					
				}
			}
		} catch (Exception ex) {
			logger.error("Exception :" + ex.getMessage(),ex);
			throw new MotOperativoDaoException(" Exception: " + ex.getMessage(),ex);
			
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return operativo;
	}

	@Override
	public void delete(MotOperativo dto) throws MotOperativoDaoException {

		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_ESTADO_MOT_OPERATIVO;1 (?)}");
			stmt.setLong(1,dto.getOpecodigoD());
			stmt.execute();		
								
		} catch (SQLException e) {
			throw new MotOperativoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

}
