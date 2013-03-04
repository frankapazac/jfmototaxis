package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotInfrMedidaDao;
import com.munichosica.myapp.dto.MotInfrMedida;
import com.munichosica.myapp.exceptions.MotInfrMedidaDaoException;

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

}
