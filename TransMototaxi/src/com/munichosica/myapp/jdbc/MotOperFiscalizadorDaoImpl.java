package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotOperFiscalizadorDao;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;

public class MotOperFiscalizadorDaoImpl implements MotOperFiscalizadorDao  {
	
	protected static final Logger logger = Logger.getLogger( MotEmprAsociadoDaoImpl.class );

	@Override
	public List<MotOperFiscalizador> findByCriterio(String criterio,
			String texto) throws MotOperFiscalizadorDaoException {
		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotOperFiscalizador> list = new ArrayList<MotOperFiscalizador>();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{CALL SP_GET_LISTADO_OPERATIVO_BYCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				MotOperFiscalizador operativoFiscalizador = null;
				while(rs.next()){
					operativoFiscalizador = new MotOperFiscalizador();
					operativoFiscalizador.getOperativo().setOpecodigoD(rs.getLong("CodOperativo"));
					operativoFiscalizador.getOperativo().setOpetituloV(rs.getString("Nombre Operativo"));
					operativoFiscalizador.getOperativo().getZona().setZonnombre_V(rs.getString("Zona"));
					operativoFiscalizador.getOperativo().setOpelugarV(rs.getString("Lugar"));
					operativoFiscalizador.getOperativo().setOpefecha(rs.getString("Fecha"));
					operativoFiscalizador.getOperativo().setOpehora(rs.getString("Hora"));
					operativoFiscalizador.getOperativo().getInspector().getPersona().setPernombresV(rs.getString("Nombre"));
					operativoFiscalizador.getOperativo().getInspector().getPersona().setPerpaternoV(rs.getString("Paterno"));
					operativoFiscalizador.getOperativo().getInspector().getPersona().setPermaternoV(rs.getString("Materno"));
					operativoFiscalizador.getOperativo().setEstado(rs.getString("Estado"));
					list.add(operativoFiscalizador);					
				}
			}
		} catch (Exception ex) {
			logger.error("Exception :" + ex.getMessage(),ex);
			throw new MotOperFiscalizadorDaoException(" Exception: " + ex.getMessage(),ex);
			
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
		}

		return list;
	}

	@Override
	public List<MotOperFiscalizador> findByIdOperativo(Long codigo)
			throws MotOperFiscalizadorDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MotOperFiscalizador dto)
			throws MotOperFiscalizadorDaoException {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void insert(MotOperFiscalizador dto) throws MotOperFiscalizadorDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_OPER_INSPECTOR;1 (?,?)}");
			System.out.println("entro a call sp_oper_inspector");
			stmt.setLong(1,dto.getOperativo().getOpecodigoD());
			stmt.setInt(2, dto.getFiscalizador().getInscodigoI());	
			stmt.execute();
			System.out.println("salio a call sp_oper_inspector");
								
		} catch (SQLException e) {
			throw new MotOperFiscalizadorDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}*/

}
