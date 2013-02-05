package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotOficinaRegistralDao;
import com.munichosica.myapp.dto.MotOficinaRegistral;
import com.munichosica.myapp.exceptions.MotOficinaRegistralDaoException;

public class MotOficinaRegistralDaoImpl implements MotOficinaRegistralDao{

	@Override
	public List<MotOficinaRegistral> findAll()
			throws MotOficinaRegistralDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotOficinaRegistral> list=new ArrayList<MotOficinaRegistral>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_OFICINAREGISTRAL;1}");
			boolean results=stmt.execute();
			if(results){
				MotOficinaRegistral oficinaRegistral=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					oficinaRegistral=new MotOficinaRegistral();
					oficinaRegistral.setOficodigoI(rs.getInt("CODIGO"));
					oficinaRegistral.setOfinombreV(rs.getString("NOMBRE"));
					list.add(oficinaRegistral);
				}
			}
		} catch (SQLException e) {
			throw new MotOficinaRegistralDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
