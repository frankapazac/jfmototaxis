package com.munichosica.myapp.jdbc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import com.munichosica.myapp.dao.MotCondDocumentoDao;
import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotCondDocumento;
import com.munichosica.myapp.dto.MotConductor;
import com.munichosica.myapp.exceptions.MotAdjuntarArchivoDaoException;
import com.munichosica.myapp.exceptions.MotCondDocumentoDaoException;
import com.munichosica.myapp.exceptions.MotConductorDaoException;
import com.munichosica.myapp.exceptions.MotEmpParaderoDaoException;
import com.munichosica.myapp.util.FileUtil;

public class MotCondDocumentoDaoImpl implements MotCondDocumentoDao {
	
	@Override
	public void insert(MotCondDocumento dto) throws MotCondDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_COND_DOCUMENTO;1 (?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(2, dto.getAdjuntarArchivo().getAdjcodigoD());
			stmt.setInt(3, dto.getTipoDocumento().getMtdcodigoI());
			stmt.setLong(4, dto.getConductor().getConcodigoD());
			stmt.execute();
			
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				dto.setCdocodigoD(codigo);
			}
					
		} catch (SQLException e) {
			
			throw new MotCondDocumentoDaoException(e.getMessage(), e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
			ResourceManager.close(rs);
		}
	
	}

	@Override
	public MotCondDocumento findByPrimaryKey(Long codigo) throws MotCondDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		
		MotCondDocumento conductor = null;
		
		try {

			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_CONDUCTOR_X_CODIGO;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					conductor = new MotCondDocumento();
					conductor.getConductor().setConcodigoD(rs.getLong("CONDCODIGO"));
					conductor.getConductor().getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
					conductor.getConductor().getPersona().setPerdniV(rs.getString("DNI"));
					conductor.getConductor().getPersona().setPernombresV(rs.getString("NOMBRES"));
					conductor.getConductor().getPersona().setPerpaternoV(rs.getString("PATERNO"));
					conductor.getConductor().getPersona().setPermaternoV(rs.getString("MATERNO"));
					conductor.getConductor().getPersona().setPernacimientoF(rs.getString("NACIMIENTO"));
					conductor.getConductor().getPersona().setPerestadocivilC(rs.getString("ESTADOCIVIL"));
					conductor.getConductor().getPersona().setPermovilclaV(rs.getString("CLARO"));
					conductor.getConductor().getPersona().setPermovilmovV(rs.getString("MOVISTAR"));
					conductor.getConductor().getPersona().setPermovilnexV(rs.getString("NEXTEL"));
					conductor.getConductor().getPersona().setPerteleffijoV(rs.getString("FIJO"));
					conductor.getConductor().getPersona().setPeremailV(rs.getString("EMAIL"));
					conductor.getConductor().getPersona().setPerdomicilioV(rs.getString("DOMICILIO"));
					conductor.getConductor().getPersona().setPerubdptoV(rs.getString("DEPARTAMENTO"));
					conductor.getConductor().getPersona().setPerubdptonombreV(rs.getString("DEPARTAMENTONOMBRE"));
					conductor.getConductor().getPersona().setPerubprovV(rs.getString("PROVINCIA"));
					conductor.getConductor().getPersona().setPerubprovnombreV(rs.getString("PROVINCIANOMBRE"));
					conductor.getConductor().getPersona().setPerubidistV(rs.getString("DISTRITO"));
					conductor.getConductor().getPersona().setPerubidistnombreV(rs.getString("DISTRITONOMBRE"));
					conductor.getConductor().getPersona().setPersexoC(rs.getString("SEXO"));
					conductor.getEmpresaConductor().setEcofechainicioF(rs.getString("FECHA INICIO"));
					conductor.getConductor().getFoto().setAdjnombreV(rs.getString("ARCHIVONOMBRE"));
					conductor.getConductor().getFoto().setAdjarchivoB(rs.getBytes("ARCHIVO")!=null?FileUtil.deCompress(rs.getBytes("ARCHIVO")):null);
				}
			}
		}
		catch (Exception ex) {
		
			throw new MotCondDocumentoDaoException( "Exception: " + ex.getMessage(), ex );
		}
			finally {
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				ResourceManager.close(conn);
		}
			return conductor;
	}
	
	
	@Override
	public List<MotCondDocumento> findByIdConductor(Long codigo)
			throws MotCondDocumentoDaoException {
		
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotCondDocumento> list=null;
		try {
			conn=ResourceManager.getConnection();
			list=new ArrayList<MotCondDocumento>();
			stmt=conn.prepareCall("{call SP_MOT_GET_MOTINSPDOCUMENTOBYCONDUCTOR;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotCondDocumento documento=null;
				while(rs.next()){
					documento=new MotCondDocumento();
					documento.setCdocodigoD(rs.getLong("CDOCODIGO"));
					documento.getTipoDocumento().setMtdcodigoI(rs.getInt("TDOCODIGO"));
					documento.getAdjuntarArchivo().setAdjcodigoD(rs.getLong("CODIGO"));
					documento.getAdjuntarArchivo().setAdjnumeroV(rs.getString("NUMERO"));
					documento.getAdjuntarArchivo().setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					documento.getAdjuntarArchivo().setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					documento.getAdjuntarArchivo().setAdjnombreV(rs.getString("NOMBRE"));
					list.add(documento);
				}
			}
		} catch (SQLException e) {
			throw new MotCondDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}
	
	@Override
	public void delete(MotCondDocumento dto) throws MotCondDocumentoDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_UPD_ESTADO_MOT_CONDUCTOR;1(?)}");
			stmt.setLong(1,  dto.getConductor().getConcodigoD());
			System.out.println(dto.getConductor().getConcodigoD());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotCondDocumentoDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
	}
	
	@Override
	public MotCondDocumento findByFechas(String fecha1, String fecha2)throws MotCondDocumentoDaoException {
		
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotCondDocumento condDocumento=null;
		
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_RPT_PAPELETA_CONDUCTOR_FECHAS;1(?,?)}");
			stmt.setString(1, fecha1);
			stmt.setString(2, fecha2);
			boolean results=stmt.execute();
			
			/*if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					conductor=new MotConductor();
					conductor.getArchivo().setAdjarchivoB(rs.getBytes("FOTO")!=null?FileUtil.deCompress(rs.getBytes("FOTO")):null);
					conductor.getArchivo().setAdjnombreV(rs.getString("FOTO_NOMBRE"));
				}
			}*/
			
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					condDocumento=new MotCondDocumento();
					condDocumento.getEmpresaConductor().getConductor().getPersona().setPerdniV(rs.getString("DNI"));
					condDocumento.getAdjuntarArchivo().setAdjnumeroV(rs.getString("LICENCIA"));
					condDocumento.getEmpresaConductor().getConductor().getPersona().setPernombresV(rs.getString("NOMBRES"));
					condDocumento.getEmpresaConductor().getConductor().getPersona().setPerpaternoV(rs.getString("PATERNO"));
					condDocumento.getEmpresaConductor().getConductor().getPersona().setPermaternoV(rs.getString("MATERNO"));
					condDocumento.getEmpresaConductor().getEmpresa().setEmprazonsocialV(rs.getString("EMPRESA"));
					condDocumento.getPapeleta().getInfrMedida().getInfraccion().setInfcodigoV(rs.getString("INFRACCION"));
					condDocumento.getPapeleta().setPapfechainfraccionF(rs.getString("FECHA"));	
					condDocumento.getPapeleta().setPapestadoC(rs.getString("ESTADO"));
				}	
			}
		} catch (SQLException  e) {
			throw new MotCondDocumentoDaoException(e.getMessage(),e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return condDocumento;
	}

	@Override
	public List<MotCondDocumento> findMensajesIdConductor(Long codigo)
			throws MotCondDocumentoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotCondDocumento> list=null;
		try {
			conn=ResourceManager.getConnection();
			list=new ArrayList<MotCondDocumento>();
			stmt=conn.prepareCall("{call SP_MOT_GET_PAPELETA_MENSAJES_CONDUCTOR;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				MotCondDocumento documento=null;
				while(rs.next()){
					documento=new MotCondDocumento();
					documento.getTipoDocumento().setMtdnombreV(rs.getString("NOMBRE"));
					documento.getAdjuntarArchivo().setAdjnumeroV(rs.getString("NUMERO"));
					documento.getAdjuntarArchivo().setAdjfechaemisionF(rs.getString("EMISION"));
					documento.getAdjuntarArchivo().setAdjfechacaducidadF(rs.getString("CADUCIDAD"));
					documento.getAdjuntarArchivo().setAdjestadoV(rs.getString("ESTADO"));
					list.add(documento);
				}
			}
		} catch (SQLException e) {
			throw new MotCondDocumentoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}
	
	
}
