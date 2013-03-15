package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotTipoDocumentoDao;
import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;

public class MotTipoDocumentoDaoImpl implements MotTipoDocumentoDao{

	@Override
	public List<MotTipoDocumento> findByTable(String table)
			throws MotTipoDocumentoDaoException {

		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotTipoDocumento> list=new ArrayList<MotTipoDocumento>();;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_TIPODOCUMENTO;1(?)}");
			stmt.setString(1, table);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotTipoDocumento tipoDocumento=null;
				while(rs.next()){
					tipoDocumento=new MotTipoDocumento();
					tipoDocumento.setMtdcodigoI(rs.getInt("CODIGO"));
					tipoDocumento.setMtdnombreV(rs.getString("NOMBRE"));
					list.add(tipoDocumento);
				}
			}
		} catch (SQLException e) {
			throw new MotTipoDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return list;
	}

	@Override
	public List<MotTipoDocumento> findByCriterio(String criterio, String texto)
			throws MotTipoDocumentoDaoException {
		
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		List<MotTipoDocumento> list=new ArrayList<MotTipoDocumento>();
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_TIPO_DOCUMENTOPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotTipoDocumento tipoDocumento=null;
				while(rs.next()){
					tipoDocumento=new MotTipoDocumento();
					tipoDocumento.setMtdcodigoI(rs.getInt("CODIGO"));
					tipoDocumento.setMtdnombreV(rs.getString("NOMBRE"));
					tipoDocumento.setMtdtablaC(rs.getString("TABLA"));
					tipoDocumento.setMtdEstadoC(rs.getString("ESTADO"));
					list.add(tipoDocumento);
				}
			}
		} catch (SQLException e) {
			throw new MotTipoDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return list;
	}

	@Override
	public MotTipoDocumento findByIdTipoDoc(Long codigo)
			throws MotOperativoDaoException {

		Connection conn =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotTipoDocumento tipoDocumento = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_TIPODOCUMENTOBYID;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					
					tipoDocumento = new MotTipoDocumento();
					tipoDocumento.setMtdcodigoI(rs.getInt("CODIGO"));
					tipoDocumento.setMtdnombreV(rs.getString("NOMBRE"));
					tipoDocumento.setMtdtablaC(rs.getString("TABLA"));
					tipoDocumento.setMtdEstadoC(rs.getString("ESTADO"));
				}
			}
		} catch (Exception ex) {
			throw new MotOperativoDaoException(" Exception: " + ex.getMessage(),ex);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}

		return tipoDocumento;
		
	
	}

	
	@Override
	public void insert(MotTipoDocumento dto) throws MotTipoDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_TIPO_DOCUMENTO;1 (?,?,?,?)}");
			stmt.setInt(1,dto.getMtdcodigoI());
			stmt.setString(2,dto.getMtdnombreV());
			stmt.setString(3,dto.getMtdtablaC());
			stmt.setString(4, dto.getMtdEstadoC());
			stmt.execute();
			
			System.out.println(dto.getMtdcodigoI());
			System.out.println(dto.getMtdnombreV());
			System.out.println(dto.getMtdtablaC());
			System.out.println(dto.getMtdEstadoC());
								
		} catch (SQLException e) {
			throw new MotTipoDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	}

	@Override
	public void delete(Integer codigo) throws MotTipoDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_DEL_TIPO_DOCUMENTO;1 (?)}");
			stmt.setInt(1,codigo);
			stmt.execute();

		} catch (SQLException e) {
			throw new MotTipoDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
		
	}
}
