package com.munichosica.myapp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.munichosica.myapp.dao.MotEmpresaDao;
import com.munichosica.myapp.dto.MotEmpPropietario;
import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;

public class MotEmpresaDaoImpl implements MotEmpresaDao {

	@Override
	public MotEmpresa findByEmpresa(Long codigo)throws MotEmpresaDaoException {
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpresa empresa = new MotEmpresa();
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_TRA_GET_DATOS_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);
									
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				MotUbigeo distrito = null;
				MotUbigeo provincia = null;
				MotUbigeo departamento = null;
				while(rs.next()){
					empresa.setEmpcodigoD(rs.getLong("Codigo"));
					empresa.setEmpdireccionV(rs.getString("Direccion"));
					distrito = new MotUbigeo();
					provincia = new MotUbigeo();
					departamento = new MotUbigeo();
					departamento.setNombubigeo(rs.getString("Departamento"));
					provincia.setNombubigeo(rs.getString("Provincia"));
					distrito.setNombubigeo(rs.getString("Distrito"));
					empresa.setDepartamento(departamento);
					empresa.setDistrito(distrito);
					empresa.setProvincia(provincia);
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmptelefono1V(rs.getString("Telefono1"));
					empresa.setEmptelefono2V(rs.getString("Telefono2"));
					empresa.setEmpcelularclaV(rs.getString("Claro"));
					empresa.setEmpcelularmovV(rs.getString("Movistar"));
					empresa.setEmpcelularnexV(rs.getString("Nextel"));
					empresa.setEmpmailV(rs.getString("Email"));
					empresa.setEmppagwebV(rs.getString("PagWeb"));
				}
			}
			
		} catch (Exception ex) {
			throw new MotEmpresaDaoException( "Exception: " + ex.getMessage(),ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return empresa;
	}

	@Override
	public void update(MotEmpresa dto) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_DATOS_EMPRESA;1(?,?,?,?,?,?,?,?)}");
			stmt.setLong(1, dto.getEmpcodigoD());
			
			stmt.setString(2, dto.getEmptelefono1V());
			stmt.setString(3, dto.getEmptelefono2V());
			stmt.setString(4, dto.getEmpcelularclaV());
			stmt.setString(5, dto.getEmpcelularmovV());
			stmt.setString(6, dto.getEmpcelularnexV());
			stmt.setString(7, dto.getEmpmailV());
			stmt.setString(8, dto.getEmppagwebV());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public void insert(MotEmpresa empresa) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_EMPRESA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			if(empresa.getEmpcodigoD()!=null)
				stmt.setLong(1, empresa.getEmpcodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			stmt.setLong(2, empresa.getZona().getZoncodigo_I());
			stmt.setString(3, empresa.getEmprazonsocialV());
			stmt.setString(4, empresa.getEmpdireccionV());
			stmt.setString(5, empresa.getEmprucV());
			stmt.setString(6, empresa.getEmptelefono1V());
			stmt.setString(7, empresa.getEmptelefono2V());
			stmt.setString(8, empresa.getEmpcelularmovV());
			stmt.setString(9, empresa.getEmpcelularclaV());
			stmt.setString(10, empresa.getEmpcelularnexV());
			stmt.setString(11, empresa.getEmpmailV());
			stmt.setString(12, empresa.getEmppagwebV());
			stmt.setString(13, empresa.getDistrito().getIdubigeo());
			stmt.setString(14, empresa.getProvincia().getIdubigeo());
			stmt.setString(15, empresa.getDepartamento().getIdubigeo());
			stmt.setString(16, empresa.getEmpfechainioperacionF());
			stmt.setString(17, empresa.getEmpfechaceseoperacionF());
			stmt.setString(18, empresa.getEmpresolucionV());
			stmt.setInt(19, empresa.getEmpunidadesautorizadasI());
			stmt.setString(20, empresa.getEmpescriturapublicaV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				empresa.setEmpcodigoD(codigo);
			}
			
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public MotEmpresa get(Long codigo) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpresa empresa=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMPRESA;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					empresa=new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("CODIGO"));
					empresa.getZona().setZoncodigo_I(rs.getLong("ZONCODIGO"));
					empresa.setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmpdireccionV(rs.getString("DIRECCION"));
					empresa.getDepartamento().setIdubigeo(rs.getString("DEPARTAMENTO"));
					empresa.getDepartamento().setNombubigeo(rs.getString("DEPARTAMENTONOMBRE"));
					empresa.getProvincia().setIdubigeo(rs.getString("PROVINCIA"));
					empresa.getProvincia().setNombubigeo(rs.getString("PROVINCIANOMBRE"));
					empresa.getDistrito().setIdubigeo(rs.getString("DISTRITO"));
					empresa.getDistrito().setNombubigeo(rs.getString("DISTRITONOMBRE"));
					empresa.setEmptelefono1V(rs.getString("TELEFONO1"));
					empresa.setEmptelefono2V(rs.getString("TELEFONO2"));
					empresa.setEmpresolucionV(rs.getString("RESOLUCION"));
					empresa.setEmppagwebV(rs.getString("PAGWEB"));
					empresa.setEmpmailV(rs.getString("CORREO"));
					empresa.setEmpcelularmovV(rs.getString("MOVISTAR"));
					empresa.setEmpcelularclaV(rs.getString("CLARO"));
					empresa.setEmpcelularnexV(rs.getString("NEXTEL"));
					empresa.setEmpfechainioperacionF(rs.getString("INICIO"));
					empresa.setEmpfechaceseoperacionF(rs.getString("CESE"));
					empresa.setEmpunidadesautorizadasI(rs.getInt("UNIDADES"));
					empresa.setEmpescriturapublicaV(rs.getString("ESCRITURA"));
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return empresa;
	}

	@Override
	public void delete(Long codigo) throws MotEmpresaDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(MotEmpresa empresa) throws MotEmpresaDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MotEmpresa> listar(String criterio, String texto)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<MotEmpresa> list=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMPRESASPORCRITERIO;1(?,?)}");
			stmt.setString(1, criterio);
			stmt.setString(2, texto);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotEmpresa>();
				rs=stmt.getResultSet();
				MotEmpresa empresa=null;
				while(rs.next()){
					empresa=new MotEmpresa();
					empresa.setEmpcodigoD(rs.getLong("CODIGO"));
					empresa.getZona().setZonnombre_V(rs.getString("ZONA"));
					empresa.setEmprazonsocialV(rs.getString("RAZONSOCIAL"));
					empresa.setEmpdireccionV(rs.getString("DIRECCION"));
					empresa.setEmprucV(rs.getString("RUC"));
					empresa.setEmptelefono1V(rs.getString("TELEFONO1"));
					empresa.setEmptelefono2V(rs.getString("TELEFONO2"));
					empresa.setEmpcelularmovV(rs.getString("MOVISTAR"));
					empresa.setEmpcelularclaV(rs.getString("CLARO"));
					empresa.setEmpcelularnexV(rs.getString("NEXTEL"));
					empresa.setEmpmailV(rs.getString("EMAIL"));
					empresa.setEmpfechainioperacionF(rs.getString("INICIO"));
					empresa.setEmpfechaceseoperacionF(rs.getString("CESE"));
					empresa.setEmpunidadesautorizadasI(rs.getInt("UNIDADES"));
					empresa.setEmpescriturapublicaV(rs.getString("ESCRITURA"));
					empresa.setEmpestadoC(rs.getString("ESTADO"));
					list.add(empresa);
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public List<MotEmpPropietario> listarPropietarios(Long empCodigoD, String criterio, String texto,
			String estado) throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<MotEmpPropietario> list=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMPR_PROPIETARIO;1(?,?,?,?)}");
			stmt.setLong(1, empCodigoD);
			stmt.setString(2, criterio);
			stmt.setString(3, texto);
			stmt.setString(4, estado);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotEmpPropietario>();
				rs=stmt.getResultSet();
				MotEmpPropietario empresaPropietario=null;
				while(rs.next()){
					empresaPropietario=new MotEmpPropietario();
					empresaPropietario.setEprcodigoD(rs.getLong("EPRCODIGO"));
					empresaPropietario.getEmpresa().setEmpcodigoD(rs.getLong("EMPCODIGO"));
					empresaPropietario.getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
					empresaPropietario.getPersona().setPernombresV(rs.getString("PROPIETARIO"));
					empresaPropietario.getPersona().setPerdniV(rs.getString("DNI"));
					empresaPropietario.setEprfechainicioF(rs.getString("INICIO"));
					empresaPropietario.setEprfechafinF(rs.getString("FIN"));
					empresaPropietario.setEprestadoC(rs.getString("ESTADO"));
					list.add(empresaPropietario);
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public void insertarEmpPropietario(MotEmpPropietario propietario)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_EMP_PROPIETARIO;1(?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			if(propietario.getEprcodigoD() !=null)
				stmt.setLong(1, propietario.getEprcodigoD());
			else stmt.setNull(1, Types.DECIMAL);
			stmt.setLong(2, propietario.getEmpresa().getEmpcodigoD());
			stmt.setLong(3, propietario.getPersona().getPercodigoD());
			stmt.setString(4, propietario.getEprfechainicioF());
			stmt.setString(5, propietario.getEprfechafinF());
			stmt.setString(6, propietario.getEprestadoC());
			stmt.setString(7, propietario.getEprobservacionesV());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				propietario.setEprcodigoD(codigo);
			}
			
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public MotEmpPropietario findByEmpresaPropietario(Long codigo)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpPropietario empresaPropietario = null;
		
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMP_PROPIETARIO_ID;1(?)}");
			stmt.setLong(1, codigo);
									
			boolean results = stmt.execute();
			if(results){
				rs = stmt.getResultSet();
				while(rs.next()){
					empresaPropietario = new MotEmpPropietario();
					empresaPropietario.setEprcodigoD(rs.getLong("CODIGO"));
					empresaPropietario.getEmpresa().setEmpcodigoD(rs.getLong("EMPCODIGO"));
					empresaPropietario.setEprfechainicioF(rs.getString("FECHAINICIO"));
					empresaPropietario.setEprfechafinF(rs.getString("FECHAFIN"));
					empresaPropietario.setEprestadoC(rs.getString("ESTADO"));
					empresaPropietario.getArchivo().setAdjcodigoD(rs.getLong("ADJCODIGO"));
					empresaPropietario.setEprobservacionesV(rs.getString("OBSERVACIONES"));
					empresaPropietario.getPersona().setPercodigoD(rs.getLong("PERCODIGO"));
				}
			}
			
		} catch (Exception ex) {
			throw new MotEmpresaDaoException( "Exception: " + ex.getMessage(),ex);
		}
		finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		
		return empresaPropietario;
	}

	@Override
	public List<MotEmpPropietario> listarPropietariosDDL(Long empcodigoD)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<MotEmpPropietario> list=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMP_PROPIETARIO_DDL;1(?)}");
			stmt.setLong(1, empcodigoD);
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotEmpPropietario>();
				rs=stmt.getResultSet();
				MotEmpPropietario empresaPropietario=null;
				while(rs.next()){
					empresaPropietario=new MotEmpPropietario();
					empresaPropietario.setEprcodigoD(rs.getLong("CODIGO"));
					empresaPropietario.getPersona().setPernombresV(rs.getString("NOMBRES"));;
					list.add(empresaPropietario);
				}
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return list;
	}

	@Override
	public boolean ExisteRepresentante(Long empcodigoD) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		boolean estado=false;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_GET_REPRESENTANTES_COUNT;1(?)}");
			stmt.setLong(1, empcodigoD);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					int cantidad=rs.getInt(1);
					if(cantidad>0) estado=true;
				}
			}
		} catch (SQLException e) {
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return estado;
	}

	@Override
	public MotEmpRepresentante obtenerRepresentanteEmpresa(Long empcodigoD)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		MotEmpRepresentante representante=null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_GET_EMP_REPRESENTANTE_IDEMP;1(?)}");
			stmt.setLong(1, empcodigoD);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				while(rs.next()){
					representante=new MotEmpRepresentante();
					representante.setRepcodigoI(rs.getLong("CODIGO"));
					representante.getEmpProp().setEprcodigoD(rs.getLong("EPRCODIGO"));
					representante.getEmpresa().setEmpcodigoD(rs.getLong("EMPCODIGO	"));
					representante.setRepdescripcionV(rs.getString("DESCRIPCION"));
					representante.setRepfechainicioF(rs.getString("FECHA_INICIO"));
					representante.setRepfechaceseF(rs.getString("CESE"));
					representante.setRepobservaciones(rs.getString("OBSERVACION"));
					representante.setRepestadoC(rs.getString("ESTADO"));
				}
			}
		} catch (SQLException e) {
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return representante;
	}

	@Override
	public void insertarRepresentante(MotEmpRepresentante representante)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_INS_EMP_REPRESENTANTE;1(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, representante.getRepcodigoI());
			stmt.setLong(2, representante.getEmpProp().getEprcodigoD());
			stmt.setLong(3, representante.getEmpresa().getEmpcodigoD());
			stmt.setString(4, representante.getRepdescripcionV());
			stmt.setString(5, representante.getRepfechainicioF());
			stmt.execute();
			Long codigo=stmt.getLong(1);
			if(codigo!=null){
				representante.setRepcodigoI(codigo);
			}
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	@Override
	public void actualizarRepresentante(MotEmpRepresentante representante)
			throws MotEmpresaDaoException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ResourceManager.getConnection();
			stmt = conn.prepareCall("{call SP_MOT_UPD_EMP_REPRESENTANTE;1(?,?,?)}");
			stmt.setLong(1, representante.getRepcodigoI());
			stmt.setString(2, representante.getRepobservaciones());
			stmt.setString(3, representante.getRepfechaceseF());
			stmt.execute();
		} catch (SQLException e) {
			throw new MotEmpresaDaoException(e.getMessage(),e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}
	
}
