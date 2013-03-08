package Test;

import java.util.List;

import com.munichosica.myapp.dto.MotOperFiscalizador;
import com.munichosica.myapp.exceptions.MotOperFiscalizadorDaoException;
import com.munichosica.myapp.factory.MotOperFiscalizadorDaoFactory;

public class MotObtenerOperativo {

	public static void main(String[] args) {
		
		List<MotOperFiscalizador> lista;
		try {
			lista = MotOperFiscalizadorDaoFactory.create().findByCriterio("OPETITULO_V", "");
			for(MotOperFiscalizador operativoFiscalizador:lista){
				System.out.println(operativoFiscalizador.getOperativo().getOpecodigoD() + " " +
						operativoFiscalizador.getOperativo().getOpetituloV()+ " " +
						operativoFiscalizador.getOperativo().getOpedescripcionV()+ " " +
						operativoFiscalizador.getOperativo().getOpereferencia()+ " " +
						operativoFiscalizador.getOperativo().getZona().getZonnombre_V() + " " +
						operativoFiscalizador.getOperativo().getOpelugarV() + " " +
						operativoFiscalizador.getOperativo().getOpefecha() + " " +
						operativoFiscalizador.getOperativo().getOpehora() + " " +
						operativoFiscalizador.getOperativo().getInspector().getPersona().getPernombresV() + " " +
						operativoFiscalizador.getOperativo().getInspector().getPersona().getPerpaternoV() + " " +
						operativoFiscalizador.getOperativo().getInspector().getPersona().getPermaternoV() + " " +
						operativoFiscalizador.getOperativo().getEstado()
						);
			}
		} catch (MotOperFiscalizadorDaoException e) {
			// 
			e.printStackTrace();
		}
	}
	
}
