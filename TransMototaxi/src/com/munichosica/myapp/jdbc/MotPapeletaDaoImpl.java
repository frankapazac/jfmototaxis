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
		System.out.println("INGRESO ACA");
		Connection conn=null;
		CallableStatement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_INS_PAPELETA;1(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.DECIMAL);
			stmt.setLong(1, papeleta.getPapcodigoD());
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

	@Override
	public List<MotPapeleta> findByPmoCodigo(Long codigo) throws MotPapeletaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		List<MotPapeleta> list=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PAPELETA_X_PMOCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			
			boolean results=stmt.execute();
			if(results){
				list=new ArrayList<MotPapeleta>();
				rs=stmt.getResultSet();
				MotPapeleta papeleta=null;
				while(rs.next()){
					papeleta=new MotPapeleta();
					papeleta.setPapcodigoD(rs.getLong("PAPCODIGO_D"));
					papeleta.setPapfechainfraccionF(rs.getString("FECHA"));
					papeleta.setPaphorainfraccionF(rs.getString("HORA"));
					papeleta.getInfrMedida().getInfraccion().setInfcodigoV(rs.getString("CODIGO"));
					papeleta.getInfrMedida().getInfraccion().setInftipoC(rs.getString("TIPO"));
					papeleta.setPapnumeroV(rs.getString("N° PAPELETA"));
					papeleta.getConductor().getPersona().setPernombresV(rs.getString("NOMBRES"));
					papeleta.getConductor().getPersona().setPermaternoV(rs.getString("PATERNO"));
					papeleta.getConductor().getPersona().setPerpaternoV(rs.getString("MATERNO"));
					papeleta.getInspector().getPersona().setPernombresV(rs.getString("NOMBREINS"));
					papeleta.getInspector().getPersona().setPermaternoV(rs.getString("PATERNOINS"));
					papeleta.getInspector().getPersona().setPerpaternoV(rs.getString("MATERNOINS"));
					papeleta.setPapestadoC(rs.getString("ESTADO"));			
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
	
	
	@Override
	public MotPapeleta findByCodigo(Long codigo) throws MotPapeletaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotPapeleta papeleta=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_FINDPAPELETABYCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				papeleta=new MotPapeleta();
				rs=stmt.getResultSet();
				if(rs.next()){
					papeleta.setPapcodigoD(rs.getLong("PAPCODIGO"));
					papeleta.setPapnumeroV(rs.getString("NUMERO"));
					papeleta.setPapfechainfraccionF(rs.getString("FECHINFRACCION"));
					papeleta.getConductor().setConcodigoD(rs.getLong("CONCODIGO"));
					papeleta.getConductor().getPersona().setPerdniV(rs.getString("CONDNI"));
					papeleta.getConductor().getPersona().setPerpaternoV(rs.getString("CONPATERNO"));
					papeleta.getConductor().getPersona().setPermaternoV(rs.getString("CONMATERNO"));
					papeleta.getConductor().getPersona().setPernombresV(rs.getString("CONNOMBRES"));
					papeleta.getConductor().getArchivo().setAdjnumeroV(rs.getString("CONLICENCIA"));
					papeleta.getConductor().getArchivo().setAdjestadoV(rs.getString("ESTADO"));
					papeleta.getConductor().getArchivo().setAdjfechaemisionF(rs.getString("CONLICEMISION"));
					papeleta.getConductor().getArchivo().setAdjfechacaducidadF(rs.getString("CONLICCADUCIDAD"));
					papeleta.getPropUnidadEmpresa().setPmocodigoD(rs.getLong("PMOCODIGO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUneplacanroV(rs.getString("UNIPLACANRO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUneannoC(rs.getString("UNIANNOM"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getMarca().setMarnombreV(rs.getString("UNIMARCA"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getModelo().setModnombre_V(rs.getString("UNIMODELO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUnecolorV(rs.getString("UNICOLOR"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getArchivo().setAdjnumeroV(rs.getString("UNITARJPROPNRO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getArchivo().setAdjestadoV(rs.getString("ESTTARJUNID"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getArchivo().setAdjfechaemisionF(rs.getString("UNITARJCEMISION"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getArchivo().setAdjfechacaducidadF(rs.getString("UNITARJCADUCIDAD"));
					papeleta.getPropUnidadEmpresa().getAsociado().setAsorazonsocialV(rs.getString("ASORAZONSOCIAL"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerdniV(rs.getString("ASODNI"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerpaternoV(rs.getString("ASOPATERNO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPermaternoV(rs.getString("ASOMATERNO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("ASONOMBRES"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerdomicilioV(rs.getString("ASODOMICILIO"));
					papeleta.getInspector().setInscodigoI(rs.getInt("INSCODIGO"));
					papeleta.getInspector().getPersona().setPerdniV(rs.getString("INSDNI"));
					papeleta.getInspector().getPersona().setPernombresV(rs.getString("INSNOMBRES"));
					papeleta.getInspector().getPersona().setPerpaternoV(rs.getString("INSPATERNO"));
					papeleta.getInspector().getPersona().setPermaternoV(rs.getString("INSMATERNO"));
					papeleta.setPapinfrdireccionV(rs.getString("PAPDIRECCION"));
					papeleta.setPapinfrreferenciaV(rs.getString("PAPREFERENCIA"));
					papeleta.setPapobservinspectorV(rs.getString("PAPOBSINSPECTOR"));
					papeleta.getPolicia().setPolcarnetidentV(rs.getString("POLCARNETNRO"));
					papeleta.getPolicia().setPolcodigoI(rs.getInt("POLCODIGO"));
					papeleta.getPolicia().setPolpaternoV(rs.getString("POLPATERNO"));
					papeleta.getPolicia().setPolmaternoV(rs.getString("POLMATERNO"));
					papeleta.getPolicia().setPolnombresV(rs.getString("POLNOMBRE"));
					papeleta.setPappropietarioC(rs.getString("ESPROPCOND"));
					papeleta.getInfrMedida().getInfraccion().setInfcodigoD(rs.getLong("INFCODIGO"));
					papeleta.getInfrMedida().getInfraccion().setInfcodigoV(rs.getString("INFCODIGOV"));
					papeleta.getInfrMedida().setImecodigoI(rs.getInt("IMECODIGO"));
					papeleta.getInfrMedida().getInfraccion().setInfinfraccionV(rs.getString("INFRACCION"));
					papeleta.getInfrMedida().getTipoMedida().setTmedescripcionV(rs.getString("INFSANCION"));
					papeleta.setPapobservinfraccionV(rs.getString("INFOBSERV"));
				}
			}
		} catch (SQLException e) {
			throw new MotPapeletaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return papeleta;
	}

	@Override
	public MotPapeleta findInternamientoByPapeleta(String numero)
			throws MotPapeletaDaoException {
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		MotPapeleta papeleta=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_INTERNAMIENTOBYPAPELETA;1(?)}");
			stmt.setString(1, numero);
			boolean results=stmt.execute();
			if(results){
				rs=stmt.getResultSet();
				if(rs.next()){
					papeleta=new MotPapeleta();
					papeleta.getConductor().setConcodigoD(rs.getLong("CONDCODIGO"));
					papeleta.getConductor().getPersona().setPerdniV(rs.getString("CONDDNI"));
					papeleta.getConductor().getPersona().setPerpaternoV(rs.getString("CONDPATERNO"));
					papeleta.getConductor().getPersona().setPermaternoV(rs.getString("CONDMATERNO"));
					papeleta.getConductor().getPersona().setPernombresV(rs.getString("CONDNOMBRES"));
					papeleta.getConductor().getPersona().setPerdomicilioV(rs.getString("CONDDIRECCION"));
					papeleta.getConductor().getPersona().setPerteleffijoV(rs.getString("CONDTELEFONO"));
					papeleta.getConductor().getPersona().setPermovilmovV(rs.getString("CONDCELULAR"));
					papeleta.getConductor().getArchivo().setAdjnumeroV(rs.getString("CONDNROLICENCIA"));
					papeleta.getConductor().getArchivo().setAdjfechaemisionF(rs.getString("CONDLICEMISION"));
					papeleta.getConductor().getArchivo().setAdjfechacaducidadF(rs.getString("CONDLICCADUCIDAD"));
					papeleta.getConductor().getArchivo().setAdjestadoV(rs.getString("CONDLICESTADO"));
					papeleta.getPropUnidadEmpresa().setPmocodigoD(rs.getLong("PMOCODIGO"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUneplacanroV(rs.getString("UNIDPLACA"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().getMarca().setMarnombreV(rs.getString("UNIDMARCA"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUnenromotorV(rs.getString("UNIDMOTOR"));
					papeleta.getPropUnidadEmpresa().getUnidadempresa().setUnecolorV(rs.getString("UNIDCOLOR"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerdniV(rs.getString("PROPDNI"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerpaternoV(rs.getString("PROPPATERNO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPermaternoV(rs.getString("PROPMATERNO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPernombresV(rs.getString("PROPNOMBRES"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerdomicilioV(rs.getString("PROPDIRECCION"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPerteleffijoV(rs.getString("PROPTELEFONO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getPersona().setPermovilmovV(rs.getString("PROPCELULAR"));
					papeleta.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmprazonsocialV(rs.getString("EMPRESA"));
					papeleta.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmpdireccionV(rs.getString("EMPDIRECCION"));
					papeleta.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmptelefono1V(rs.getString("EMPTELEFONO"));
					papeleta.getPropUnidadEmpresa().getAsociado().getEmpresa().setEmpcelularmovV(rs.getString("EMPCELULAR"));
				}
			}
		} catch (SQLException e) {
			throw new MotPapeletaDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return papeleta;
	}

}
