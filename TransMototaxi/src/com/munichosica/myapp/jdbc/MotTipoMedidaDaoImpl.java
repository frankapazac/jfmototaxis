package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munichosica.myapp.dao.MotTipoMedidaDao;
import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotTipoMedidaDaoException;

public class MotTipoMedidaDaoImpl implements MotTipoMedidaDao {
	
	protected static final Logger logger = Logger.getLogger( MotOperativoDaoImpl.class );
	
	@Override
	public List<MotTipoMedida> findAllTipoMedida() throws MotTipoMedidaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotTipoMedida> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_TIPO_MEDIDA;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotTipoMedida>();
				MotTipoMedida TipoMedida=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					TipoMedida=new MotTipoMedida();
					TipoMedida.setTmecodigoI(rs.getInt("CODIGO"));
					TipoMedida.setTmedescripcionV(rs.getString("DESCRIPCION"));
					list.add(TipoMedida);
				}
			}
		} catch (SQLException e) {
			throw new MotTipoMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotTipoMedida> findByCriterio(String criterio, String texto)
			throws MotTipoMedidaDaoException {

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotTipoMedida> list=new ArrayList<MotTipoMedida>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_TIPO_MEDIDA_BY_CRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotTipoMedida medida=null;
				while(rs.next()){
					medida=new MotTipoMedida();
					medida.setTmecodigoI(rs.getInt("CODIGO"));
					medida.setTmedescripcionV(rs.getString("DESCRIPCION"));
					medida.setTmeestadoC(rs.getString("ESTADO"));
					list.add(medida);
				}
			}
		} catch (SQLException e) {
			throw new MotTipoMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
		
	}
	
	
	@Override
	public MotTipoMedida findByIdTipoMed (Long codigo) throws MotTipoMedidaDaoException {

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotTipoMedida medida = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_TIPO_MEDIDA_BY_ID;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					
					medida = new MotTipoMedida();
					medida.setTmecodigoI(rs.getInt("CODIGO"));
					medida.setTmedescripcionV(rs.getString("DESCRIPCION"));
					medida.setTmeestadoC(rs.getString("ESTADO"));
				}
			}
		} catch (Exception ex) {
			throw new MotTipoMedidaDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return medida;
	}
	
	
	@Override
	public void insert(MotTipoMedida dto) throws MotTipoMedidaDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_TIPO_MEDIDA;1 (?,?,?)}");
			stmt.setInt(1,dto.getTmecodigoI());
			stmt.setString(2,dto.getTmedescripcionV());
			stmt.setString(3,dto.getTmeestadoC());
			stmt.execute();
								
		} catch (SQLException e) {
			throw new MotTipoMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}
	
	@Override
	public void delete(Integer codigo) throws MotTipoMedidaDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_TIPO_MEDIDA;1 (?)}");
			stmt.setInt(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotTipoMedidaDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

	
}
