package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.omg.PortableInterceptor.INACTIVE;

import com.munichosica.myapp.dao.MotInternamientoDao;
import com.munichosica.myapp.dto.MotInternamiento;
import com.munichosica.myapp.exceptions.MotInternamientoDaoException;

public class MotInternamientoDaoImpl implements MotInternamientoDao{

	@Override
	public void procesar(MotInternamiento internamiento)
			throws MotInternamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_INTERNAMIENTO;1(?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			if(internamiento.getIntcodigoD()!=null&&internamiento.getIntcodigoD()>0)
				stmt.setLong(1, internamiento.getIntcodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			stmt.setLong(2, internamiento.getConductor().getConcodigoD());
			if(internamiento.getActaConformidad().getAcocodigoD()!=null&&internamiento.getActaConformidad().getAcocodigoD()>0)
				stmt.setLong(3, internamiento.getActaConformidad().getAcocodigoD());
			else stmt.setNull(3, Types.DECIMAL);
			if(internamiento.getBoletaInternamiento().getBincodigoD()!=null&&internamiento.getBoletaInternamiento().getBincodigoD()>0)
				stmt.setLong(4, internamiento.getBoletaInternamiento().getBincodigoD());
			else stmt.setNull(4, Types.DECIMAL);
			if(internamiento.getPapeleta().getPapcodigoD()!=null&&internamiento.getPapeleta().getPapcodigoD()>0)
				stmt.setLong(5, internamiento.getPapeleta().getPapcodigoD());
			else stmt.setNull(5, Types.DECIMAL);
			stmt.setLong(6, internamiento.getPropUnidadEmpresa().getPmocodigoD());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				internamiento.setIntcodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public List<MotInternamiento> listarPorCriterio(String criterio,
			String texto) throws MotInternamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotInternamiento> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INTERNAMIENTOPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				list=new ArrayList<MotInternamiento>();
				MotInternamiento internamiento=null;
				while(rs.next()){
					internamiento=new MotInternamiento();
					internamiento.setIntcodigoD(rs.getLong("CODIGO"));
					internamiento.setIntfechaingresoF(rs.getString("INGRESO"));
					internamiento.getPapeleta().setPapnumeroV(rs.getString("PAPELETA"));
					internamiento.getPropUnidadEmpresa().getUnidadempresa().setUneplacanroV(rs.getString("PLACA"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("PROPIETARIO"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPerdniV(rs.getString("PROP_DNI"));
					internamiento.getConductor().getPersona().setPernombresV(rs.getString("CONDUCTOR"));
					internamiento.getConductor().getPersona().setPerdniV(rs.getString("COND_DNI"));
					internamiento.getPapeleta().setPapcodigoD(rs.getLong("PAPCODIGO"));
					internamiento.getBoletaInternamiento().setBincodigoD(rs.getLong("BOLETA_INTERNAMIENTO"));
					internamiento.getActaConformidad().setAcocodigoD(rs.getLong("ACTA_CONFORMIDAD"));
					internamiento.getBoletaInternamiento().setBinnumeroV(rs.getString("BOL_NUMERO"));
					list.add(internamiento);
				}
			}
		} catch (SQLException e) {
			throw new MotInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public MotInternamiento get(Long codigo)
			throws MotInternamientoDaoException {
		System.out.println(codigo);
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotInternamiento internamiento=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INTERNAMIENTO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					internamiento=new MotInternamiento();
					internamiento.setIntcodigoD(rs.getLong("INTCODIGO"));
					internamiento.getPapeleta().setPapcodigoD(rs.getLong("PAPCODIGO"));
					internamiento.getPapeleta().setPapnumeroV(rs.getString("PAPNUMERO"));
					internamiento.getBoletaInternamiento().setBincodigoD(rs.getLong("BINCODIGO"));
					internamiento.getBoletaInternamiento().setBinmotivoV(rs.getString("MOTIVO"));
					internamiento.setIntfechaingresoF(rs.getString("INGRESO"));
					internamiento.setIntfechasalidaF(rs.getString("INGRESO_HORA"));
					internamiento.getConductor().setConcodigoD(rs.getLong("COND_CODIGO"));
					internamiento.getConductor().getPersona().setPerdniV(rs.getString("COND_DNI"));
					internamiento.getConductor().getPersona().setPernombresV(rs.getString("COND_NOMBRES"));
					internamiento.getConductor().getPersona().setPerdomicilioV(rs.getString("COND_DIRECCION"));
					internamiento.getConductor().getPersona().setPerteleffijoV(rs.getString("COND_TELEFONO"));
					internamiento.getConductor().getPersona().setPermovilmovV(rs.getString("COND_MOVIL"));
					internamiento.getConductor().getArchivo().setAdjnumeroV(rs.getString("LIC_NUMERO"));
					internamiento.getConductor().getArchivo().setAdjfechaemisionF(rs.getString("LIC_EMISION"));
					internamiento.getConductor().getArchivo().setAdjfechacaducidadF(rs.getString("LIC_CADUCIDAD"));
					internamiento.getConductor().getArchivo().setAdjestadoV(rs.getString("ESTADO"));
					internamiento.getPropUnidadEmpresa().setPmocodigoD(rs.getLong("PROP_CODIGO"));
					internamiento.getPropUnidadEmpresa().getUnidadempresa().setUneplacanroV(rs.getString("UNID_PLACA"));
					internamiento.getPropUnidadEmpresa().getUnidadempresa().getMarca().setMarnombreV("UNID_MARCA");
					internamiento.getPropUnidadEmpresa().getUnidadempresa().setUnenromotorV(rs.getString("UNID_MOTOR"));
					internamiento.getPropUnidadEmpresa().getUnidadempresa().setUnecolorV(rs.getString("UNID_COLOR"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPerdniV(rs.getString("PROP_DNI"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("PROP_NOMBRES"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPerdomicilioV(rs.getString("PROP_DOMICILIO"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPerteleffijoV(rs.getString("PROP_TELEFONO"));
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPermovilmovV(rs.getString("PROP_MOVIL"));
					internamiento.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmprazonsocialV(rs.getString("EMPR_RAZONSOCIAL"));
					internamiento.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmpdireccionV(rs.getString("EMPR_DIRECCION"));
					internamiento.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmptelefono1V(rs.getString("EMPR_TELEFONO"));
					internamiento.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmpcelularmovV(rs.getString("EMPR_CELULAR"));
				}
			}
		} catch (SQLException e) {
			throw new MotInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return internamiento;
	}
	
	@Override
	public MotInternamiento getPropietarioInternamiento(Long codigo)
			throws MotInternamientoDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotInternamiento internamiento=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PROPIETARIO_INTERNAMIENTO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					internamiento=new MotInternamiento();
					internamiento.setIntcodigoD(rs.getLong("INTCODIGO"));	
					internamiento.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("PROP_NOMBRES"));
					internamiento.getActaConformidad().setAcodescripcionV(rs.getString("DESCRIPCION"));
				}
			}
		} catch (SQLException e) {
			throw new MotInternamientoDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return internamiento;
	}
	
}
