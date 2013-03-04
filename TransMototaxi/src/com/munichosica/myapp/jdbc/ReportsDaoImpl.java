package com.munichosica.myapp.jdbc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import com.munichosica.myapp.dao.ReportsDao;
import com.munichosica.myapp.dto.RepPapeleta;
import com.munichosica.myapp.exceptions.ReportsDaoException;
import com.munichosica.myapp.util.FileUtil;

public class ReportsDaoImpl implements ReportsDao{

	@Override
	public RepPapeleta reportePapeleta(Long codigo) throws ReportsDaoException {
		RepPapeleta papeleta=null;
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.prepareCall("{call SP_MOT_GET_PAPELETABYCODIGO;1(?)}");
			stmt.setLong(1, codigo);
			boolean results=stmt.execute();
			if(results){
				papeleta=new RepPapeleta();
				rs=stmt.getResultSet();
				if(rs.next()){
					papeleta.setPapcodigo(codigo);
					papeleta.setPapnumero(rs.getString("PAPNUMERO"));
					papeleta.setCondnombres(rs.getString("CONDNOMBRES"));
					papeleta.setCondpaterno(rs.getString("CONDPATERNO"));
					papeleta.setCondmaterno(rs.getString("CONDMATERNO"));
					papeleta.setConddni(rs.getString("CONDDNI"));
					papeleta.setConddomicilio(rs.getString("CONDDOMICILIO"));
					papeleta.setConddistrito(rs.getString("CONDDISTRITO"));
					papeleta.setConddepartamento(rs.getString("CONDDEPARTAMENTO"));
					papeleta.setCondprovincia(rs.getString("CONDPROVINCIA"));
					papeleta.setLicenumero(rs.getString("LICENUMERO"));
					papeleta.setLiceemision(rs.getString("LICEEMISION"));
					papeleta.setLicecaducidad(rs.getString("LICECADUCIDAD"));
					papeleta.setFechinfraccion(rs.getString("FECHINFRACCION"));
					papeleta.setUnidplaca(rs.getString("UNIDPLACA"));
					papeleta.setUnidanno(rs.getString("UNIDANNO"));
					papeleta.setUnidcolor(rs.getString("UNIDCOLOR"));
					papeleta.setUnidmarca(rs.getString("UNIDMARCA"));
					papeleta.setUnidmodelo(rs.getString("UNIDMODELO"));
					papeleta.setUnidtipo(rs.getString("UNIDTIPO"));
					papeleta.setUnidtarjpropiedad(rs.getString("UNIDTARJPROPIEDAD"));
					papeleta.setUnidtarjpropiedademision(rs.getString("UNIDTARJPROPEMISION"));
					papeleta.setUnidtarjpropiedadcaducidad(rs.getString("UNIDTARJPROPCADUCIDAD"));
					papeleta.setAsocrazonsocial(rs.getString("ASOCRAZONSOCIAL"));
					papeleta.setAsocnombres(rs.getString("ASOCNOMBRES"));
					papeleta.setAsocpaterno(rs.getString("ASOCPATERNO"));
					papeleta.setAsocmaterno(rs.getString("ASOCMATERNO"));
					papeleta.setAsocdni(rs.getString("ASOCDNI"));
					papeleta.setAsocdomicilio(rs.getString("ASOCDOMICILIO"));
					papeleta.setAsocdistrito(rs.getString("ASOCDISTRITO"));
					papeleta.setAsocdepartamento(rs.getString("ASOCDEPARTAMENTO"));
					papeleta.setAsocprovincia(rs.getString("ASOCPROVINCIA"));
					papeleta.setEspropconductor(rs.getString("ESPROPCOND"));
					papeleta.setNoespropconductor(rs.getString("NOESPROPCOND"));
					papeleta.setInspnombres(rs.getString("INSPNOMBRES"));
					papeleta.setInsppaterno(rs.getString("INSPPATERNO"));
					papeleta.setInspmaterno(rs.getString("INSPMATERNO"));
					papeleta.setInspdni(rs.getString("INSPDNI"));
					papeleta.setInfrcodigo(rs.getString("INFRCODIGO"));
					papeleta.setInfraccion(rs.getString("INFRACCION"));
					papeleta.setMedida(rs.getString("MEDIDA"));
					papeleta.setInfrdireccion(rs.getString("INFRDIRECCION"));
					papeleta.setInfrreferencia(rs.getString("INFRREFERENCIA"));
					papeleta.setObseinfraccion(rs.getString("OBSEINFRACCION"));
					papeleta.setObseinspector(rs.getString("OBSEINSPECTOR"));
					papeleta.setFilenombre(rs.getString("FILENOMBRE"));
					papeleta.setFilefoto(rs.getBytes("FILEFOTO")!=null?FileUtil.deCompress(rs.getBytes("FILEFOTO")):null);
				}
			}
		} catch (SQLException | IOException | DataFormatException e) {
			throw new ReportsDaoException(e.getMessage(), e);
		} finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return papeleta;
	}

}
