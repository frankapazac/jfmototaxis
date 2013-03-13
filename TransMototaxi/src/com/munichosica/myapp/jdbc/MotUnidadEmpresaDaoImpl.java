package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotUnidadEmpresaDao;
import com.munichosica.myapp.dto.MotUnidadEmpresa;
import com.munichosica.myapp.exceptions.MotUnidadEmpresaDaoException;

public class MotUnidadEmpresaDaoImpl implements MotUnidadEmpresaDao {

	@Override
	public void insert(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_UNIDADEMPRESA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			if(dto.getUnecodigoD()!=null)
				stmt.setLong(1, dto.getUnecodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			stmt.setString(2, dto.getUneplacanroV());
			stmt.setInt(3, dto.getOficina().getOficodigoI());
			stmt.setString(4, dto.getUnenropadronV());
			stmt.setString(5, dto.getUnenropartidaregistralV());
			stmt.setString(6, dto.getUnetituloV());
			stmt.setString(7, dto.getUneclaseV());
			stmt.setInt(8, dto.getMarca().getMarcodigoI());
			stmt.setLong(9, dto.getModelo().getModcodigo_D());
			stmt.setString(10, dto.getUneannoC());
			stmt.setString(11, dto.getUnecolorV());
			stmt.setString(12, dto.getUnecombustibleC());
			stmt.setString(13, dto.getUnecarroceriaC());
			stmt.setString(14, dto.getUnenroseriechasisV());
			stmt.setString(15, dto.getUnenromotorV());
			stmt.setString(16, dto.getUnenroidentificacionV());
			stmt.setInt(17, dto.getUnenroruedasI());
			stmt.setInt(18, dto.getUnenroasientosI());
			stmt.setInt(19, dto.getUnenropasajerosI());
			stmt.setBigDecimal(20, dto.getUnecargautilD());
			stmt.setBigDecimal(21, dto.getUnelongitudD());
			stmt.setBigDecimal(22, dto.getUneanchoD());
			stmt.setBigDecimal(23, dto.getUnealtoD());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			System.out.println("UNIDDAD CODIGO: "+codigo);
			if(codigo!=null){
				dto.setUnecodigoD(codigo);
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public void update(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {	
	}

	@Override
	public void delete(MotUnidadEmpresa dto)
			throws MotUnidadEmpresaDaoException {
	}

	@Override
	public MotUnidadEmpresa findByPrimaryKey(Long codigo)
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotUnidadEmpresa unidad=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDADEMPRESA;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					unidad=new MotUnidadEmpresa();
					unidad.setUnecodigoD(rs.getLong("CODIGO"));
					unidad.setUneplacanroV(rs.getString("PLACA"));
					unidad.getOficina().setOficodigoI(rs.getInt("OFICINA"));
					unidad.setUnenropadronV(rs.getString("PADRON"));
					unidad.setUnenropartidaregistralV(rs.getString("PARTIDAREGISTRAL"));
					unidad.setUnetituloV(rs.getString("TITULO"));
					unidad.setUneclaseV(rs.getString("CLASE"));
					unidad.getMarca().setMarcodigoI(rs.getInt("MARCA"));
					unidad.getModelo().setModcodigo_D(rs.getInt("MODELO"));
					unidad.setUneannoC(rs.getString("ANNO"));
					unidad.setUnecolorV(rs.getString("COLOR"));
					unidad.setUnecombustibleC(rs.getString("COMBUSTIBLE"));
					unidad.setUnecarroceriaC(rs.getString("CARROCERIA"));
					unidad.setUnenroseriechasisV(rs.getString("CHASIS"));
					unidad.setUnenromotorV(rs.getString("MOTOR"));
					unidad.setUnenroidentificacionV(rs.getString("NIV"));
					unidad.setUnenroruedasI(rs.getInt("RUEDAS"));
					unidad.setUnenroasientosI(rs.getInt("ASIENTOS"));
					unidad.setUnenropasajerosI(rs.getInt("PASAJEROS"));
					unidad.setUnecargautilD(rs.getBigDecimal("CARGAUTIL"));
					unidad.setUnelongitudD(rs.getBigDecimal("LONGITUD"));
					unidad.setUneanchoD(rs.getBigDecimal("ANCHO"));
					unidad.setUnealtoD(rs.getBigDecimal("ALTO"));
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(), e);
		}
		return unidad;
	}

	@Override
	public List<MotUnidadEmpresa> findByCriterio(String criterio, String texto,
			Long modcodigo_D) throws MotUnidadEmpresaDaoException {
		return null;
	}

	@Override
	public List<MotUnidadEmpresa> findAllPlacas()
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotUnidadEmpresa> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDALLPLACAS;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotUnidadEmpresa>();
				MotUnidadEmpresa unidad=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					unidad=new MotUnidadEmpresa();
					unidad.setUnecodigoD(rs.getLong("CODIGO"));
					unidad.setUneplacanroV(rs.getString("PLACA"));
					list.add(unidad);
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public List<MotUnidadEmpresa> findAllPlacasByAsociado()
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotUnidadEmpresa> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDPLACAS_BYPROPIETARIO;1}");
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotUnidadEmpresa>();
				MotUnidadEmpresa unidad=null;
				rs=stmt.getResultSet();
				while(rs.next()){
					unidad=new MotUnidadEmpresa();
					unidad.setUnecodigoD(rs.getLong("PMOCODIGO"));
					unidad.setUneplacanroV(rs.getString("PLACA"));
					list.add(unidad);
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public MotUnidadEmpresa findByPlaca(String placa)
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDEMPRESABYPLACA;1(?)}");
			stmt.setString(1, placa);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					unidadEmpresa=new MotUnidadEmpresa();
					unidadEmpresa.setUneplacanroV(rs.getString("PLACA"));
					unidadEmpresa.getMarca().setMarnombreV(rs.getString("MARCA"));
					unidadEmpresa.getModelo().setModnombre_V(rs.getString("MODELO"));
					unidadEmpresa.setUneannoC(rs.getString("ANNO"));
					unidadEmpresa.getArchivo().setAdjnumeroV(rs.getString("TARJPROPNUMERO"));
					unidadEmpresa.getArchivo().setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					unidadEmpresa.getArchivo().setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					unidadEmpresa.setUnecolorV(rs.getString("COLOR"));
					unidadEmpresa.getArchivo().setAdjestadoV(rs.getString("ESTADO"));
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(),e);
		}
		return unidadEmpresa;
	}

	@Override
	public MotUnidadEmpresa findByCodigo(Long codigo)
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDEMPRESABYCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					unidadEmpresa=new MotUnidadEmpresa();
					unidadEmpresa.setUneplacanroV(rs.getString("PLACA"));
					unidadEmpresa.getMarca().setMarnombreV(rs.getString("MARCA"));
					unidadEmpresa.getModelo().setModnombre_V(rs.getString("MODELO"));
					unidadEmpresa.setUnenromotorV(rs.getString("MOTOR"));
					unidadEmpresa.setUneannoC(rs.getString("ANNO"));
					unidadEmpresa.getArchivo().setAdjnumeroV(rs.getString("TARJPROPNUMERO"));
					unidadEmpresa.getArchivo().setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					unidadEmpresa.getArchivo().setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					unidadEmpresa.setUnecolorV(rs.getString("COLOR"));
					
					unidadEmpresa.getAsociado().setAsorazonsocialV(rs.getString("ASORAZONSOCIAL"));
					unidadEmpresa.getAsociado().setAsocodigoD(rs.getLong("ASOCODIGO"));
					unidadEmpresa.getAsociado().getPersona().setPernombresV(rs.getString("ASONOMBRES"));
					unidadEmpresa.getAsociado().getPersona().setPerpaternoV(rs.getString("ASOPATERNO"));
					unidadEmpresa.getAsociado().getPersona().setPermaternoV(rs.getString("ASOMATERNO"));
					unidadEmpresa.getAsociado().getPersona().setPerdniV(rs.getString("ASODNI"));
					unidadEmpresa.getAsociado().getPersona().setPerdomicilioV(rs.getString("ASODOMICILIO"));
					unidadEmpresa.getAsociado().getPersona().setPerteleffijoV(rs.getString("ASOTELEFONO"));
					unidadEmpresa.getAsociado().getPersona().setPermovilmovV(rs.getString("ASOCELULAR"));

					unidadEmpresa.getAsociado().getEmpresa().setEmprazonsocialV(rs.getString("EMPRESA"));
					unidadEmpresa.getAsociado().getEmpresa().setEmpdireccionV(rs.getString("EMPDIRECCION"));
					unidadEmpresa.getAsociado().getEmpresa().setEmptelefono1V(rs.getString("EMPTELEFONO"));
					unidadEmpresa.getAsociado().getEmpresa().setEmpcelularmovV(rs.getString("EMPCELULAR"));
					
					unidadEmpresa.getArchivo().setAdjestadoV(rs.getString("ESTADO"));
					unidadEmpresa.setMensaje(rs.getString("MENSAJE"));
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(),e);
		}
		return unidadEmpresa;
	}
	
	@Override
	public MotUnidadEmpresa findPmoCodigo(Long codigo)
			throws MotUnidadEmpresaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotUnidadEmpresa unidadEmpresa=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_UNIDEMPRESABYPMOCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					unidadEmpresa=new MotUnidadEmpresa();
					unidadEmpresa.setUneplacanroV(rs.getString("PLACA"));
					unidadEmpresa.getMarca().setMarnombreV(rs.getString("MARCA"));
					//unidadEmpresa.getModelo().setModnombre_V(rs.getString("MODELO"));
					unidadEmpresa.setUnenromotorV(rs.getString("MOTOR"));
					unidadEmpresa.setUneannoC(rs.getString("ANNO"));
					unidadEmpresa.getArchivo().setAdjnumeroV(rs.getString("TARJPROPNUMERO"));
					unidadEmpresa.getArchivo().setAdjfechaemisionF(rs.getString("FECHAEMISION"));
					unidadEmpresa.getArchivo().setAdjfechacaducidadF(rs.getString("FECHACADUCIDAD"));
					unidadEmpresa.setUnecolorV(rs.getString("COLOR"));
					
					unidadEmpresa.getAsociado().setAsorazonsocialV(rs.getString("ASORAZONSOCIAL"));
					unidadEmpresa.getAsociado().setAsocodigoD(rs.getLong("ASOCODIGO"));
					unidadEmpresa.getAsociado().getPersona().setPernombresV(rs.getString("ASONOMBRES"));
					unidadEmpresa.getAsociado().getPersona().setPerpaternoV(rs.getString("ASOPATERNO"));
					unidadEmpresa.getAsociado().getPersona().setPermaternoV(rs.getString("ASOMATERNO"));
					unidadEmpresa.getAsociado().getPersona().setPerdniV(rs.getString("ASODNI"));
					unidadEmpresa.getAsociado().getPersona().setPerdomicilioV(rs.getString("ASODOMICILIO"));
					unidadEmpresa.getAsociado().getPersona().setPerteleffijoV(rs.getString("ASOTELEFONO"));
					unidadEmpresa.getAsociado().getPersona().setPermovilmovV(rs.getString("ASOCELULAR"));

					unidadEmpresa.getAsociado().getEmpresa().setEmprazonsocialV(rs.getString("EMPRESA"));
					unidadEmpresa.getAsociado().getEmpresa().setEmpdireccionV(rs.getString("EMPDIRECCION"));
					unidadEmpresa.getAsociado().getEmpresa().setEmptelefono1V(rs.getString("EMPTELEFONO"));
					unidadEmpresa.getAsociado().getEmpresa().setEmpcelularmovV(rs.getString("EMPCELULAR"));
					
					unidadEmpresa.getArchivo().setAdjestadoV(rs.getString("ESTADO"));
				}
			}
		} catch (SQLException e) {
			throw new MotUnidadEmpresaDaoException(e.getMessage(),e);
		}
		return unidadEmpresa;
	}
}
