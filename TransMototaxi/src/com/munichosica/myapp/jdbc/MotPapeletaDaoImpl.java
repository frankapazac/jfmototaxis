package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotPapeletaDao;
import com.munichosica.myapp.dto.MotPapeleta;
import com.munichosica.myapp.exceptions.MotPapeletaDaoException;

public class MotPapeletaDaoImpl implements MotPapeletaDao{

	@Override
	public void insert(MotPapeleta papeleta) throws MotPapeletaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_PAPELETA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setInt(2, papeleta.getPolicia().getPolcodigoI());
			stmt.setLong(3, papeleta.getArchivo().getAdjcodigoD());
			stmt.setInt(4, papeleta.getInfrMedida().getImecodigoI());
			stmt.setInt(5, papeleta.getInspector().getInscodigoI());
			stmt.setLong(6, papeleta.getPropUnidadEmpresa().getPmocodigoD());
			stmt.setLong(7, papeleta.getConductor().getConcodigoD());
			stmt.setString(8, papeleta.getPapfechainfraccionF());
			stmt.setString(9, papeleta.getPaphorainfraccionF());
			stmt.setString(10, papeleta.getPapinfrdireccionV());
			stmt.setString(11, papeleta.getPapinfrreferenciaV());
			stmt.setString(12, papeleta.getPappropietarioC());
			stmt.setString(13, papeleta.getPapobservinfraccionV());
			stmt.setString(14, papeleta.getPapobservinspectorV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				papeleta.setPapcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotPapeletaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
	}

	@Override
	public List<MotPapeleta> findByCriterio(String criterio, String texto)
			throws MotPapeletaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPapeleta> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PAPELETAPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotPapeleta>();
				rs=stmt.getResultSet();
				MotPapeleta papeleta=null;
				while(rs.next()){
					papeleta=new MotPapeleta();
					papeleta.setPapcodigoD(rs.getLong("PAPCODIGO"));
					papeleta.getConductor().setConcodigoD(rs.getLong("CONDCODIGO"));
					papeleta.getPropUnidadEmpresa().setPmocodigoD(rs.getLong("PMOCODIGO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUneplacanroV(rs.getString("PMOPLACANRO"));
					papeleta.getConductor().getPersona().setPernombresV(rs.getString("CONDNOMBCOMP"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("PMONOMBCOMP"));
					papeleta.getConductor().getArchivo().setAdjnumeroV(rs.getString("PMOLICENCIANRO"));
					papeleta.setPapfechainfraccionF(rs.getString("FECHINFRACCION"));
					list.add(papeleta);
				}
			}
		} catch (SQLException e) {
			throw new MotPapeletaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

}
