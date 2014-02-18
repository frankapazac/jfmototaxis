package com.munichosica.myapp.jdbc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import com.munichosica.myapp.dao.MotEmpDocumentoDao;
import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;
import com.munichosica.myapp.util.FileUtil;

public class MotEmpDocumentoDaoImpl implements MotEmpDocumentoDao {

	@Override
	public void insert(MotEmpDocumento dto) throws MotEmpDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_INS_MOT_EMP_DOCUMENTO;1(?,?,?)}");
			stmt.setLong(1,dto.getAdjuntarArchivo().getAdjcodigoD());
			stmt.setInt(2,dto.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(3,dto.getEmpresa().getEmpcodigoD());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

	}

	@Override
	public List<MotEmpDocumento> findImagesByEmpresa(Long empcodigoD)
			throws MotEmpDocumentoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<MotEmpDocumento> list=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_FOTOSEMPRESA;1(?)}");
			stmt.setLong(1,empcodigoD);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotEmpDocumento>();
				MotEmpDocumento documento=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					documento=new MotEmpDocumento();
					documento.getTipoDocumento().setMtdcodigoI(rs.getInt("TDOCODIGO"));
					documento.getTipoDocumento().setMtdnombreV(rs.getString("TDONOMBRE"));
					documento.getAdjuntarArchivo().setAdjarchivoB(rs.getBytes("IMAGEN")!=null?FileUtil.deCompress(rs.getBytes("IMAGEN")):null);
					documento.getAdjuntarArchivo().setAdjnombreV(rs.getString("NOMBRE"));
					list.add(documento);
				}
			}
		} catch (SQLException | IOException | DataFormatException e) {
			throw new MotEmpDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public MotEmpDocumento findImageByEmpresa(Long empcodigoD)
			throws MotEmpDocumentoDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpDocumento documento=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_FOTOEMPRESA;1(?)}");
			stmt.setLong(1,empcodigoD);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					documento=new MotEmpDocumento();
					documento.getTipoDocumento().setMtdcodigoI(rs.getInt("TDOCODIGO"));
					documento.getTipoDocumento().setMtdnombreV(rs.getString("TDONOMBRE"));
					documento.getAdjuntarArchivo().setAdjarchivoB(rs.getBytes("IMAGEN")!=null?FileUtil.deCompress(rs.getBytes("IMAGEN")):null);
					documento.getAdjuntarArchivo().setAdjnombreV(rs.getString("NOMBRE"));
				}
			}
		} catch (SQLException | IOException | DataFormatException e) {
			throw new MotEmpDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return documento;
	}

	
	
}
