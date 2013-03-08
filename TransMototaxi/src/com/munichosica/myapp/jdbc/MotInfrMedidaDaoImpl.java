package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInfrMedidaDao;
import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;

public class MotInfrMedidaDaoImpl implements MotInfrMedidaDao{

	@Override
	public List<MotInfrMedida> findByInfraccion(Long infcodigo)
			throws MotInfrMedidaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInfrMedida> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDTIPOMEDIDABYINFRACCION;1(?)}");
			stmt.setLong(1, infcodigo);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotInfrMedida>();
				rs=stmt.getResultSet();
				MotInfrMedida medida=null;
				while(rs.next()){
					medida=new MotInfrMedida();
					medida.setImecodigoI(rs.getInt("CODIGO"));
					medida.getTipoMedida().setTmedescripcionV(rs.getString("TIPOMEDIDA"));
					medida.getInfraccion().setInfinfraccionV(rs.getString("DESCRIPCION"));
					list.add(medida);
				}
			}
		} catch (SQLException e) {
			throw new MotInfrMedidaDaoException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public void insert(MotInfrMedida dto) throws MotInfrMedidaDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_INFRA_MEDIDAS;1 (?,?)}");
			stmt.setLong(1,dto.getTipoMedida().getTmecodigoI());
			stmt.setLong(2, dto.getInfraccion().getInfcodigoD());	
			System.out.println("codigo tmedida " + dto.getTipoMedida().getTmecodigoI());
			System.out.println("codigo infraccion " + dto.getInfraccion().getInfcodigoD());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotInfrMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}
	
	@Override
	public void actualizaEstado(MotInfrMedida dto) throws MotInfrMedidaDaoException{
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_ACTUALIZA_ESTADO_INFR_MEDIDA;1 (?,?,?)}");
			stmt.setInt(1,dto.getTipoMedida().getTmecodigoI());
			stmt.setLong(2, dto.getInfraccion().getInfcodigoD());	
			stmt.setString(3,dto.getTipoMedida().getLado());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotInfrMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}
	
	@Override
	public List<MotInfrMedida> findMedidasxInfraccion (Long codigo) throws MotInfrMedidaDaoException{
		
		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		List<MotInfrMedida> listMedidasxInfraccion = new ArrayList<MotInfrMedida>();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{CALL SP_MOT_GET_MEDIDAS_X_INFRACCIONES;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				MotInfrMedida infraccionMedida= null;
				while(rs.next()){
					infraccionMedida = new MotInfrMedida();
					infraccionMedida.getTipoMedida().setTmecodigoI(rs.getInt("TMECODIGO_I"));
					infraccionMedida.getTipoMedida().setTmedescripcionV(rs.getString("TMEDESCRIPCION_V"));
					listMedidasxInfraccion.add(infraccionMedida);					
				}
			}
		} catch (Exception ex) {
			throw new MotInfrMedidaDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
		}

		return listMedidasxInfraccion;
	}


}
