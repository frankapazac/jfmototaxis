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
import com.munichosica.myapp.dto.MotTipoMedida;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
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
	
	
}
