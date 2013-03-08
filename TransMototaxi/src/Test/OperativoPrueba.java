package Test;

import com.munichosica.myapp.dto.MotOperativo;
import com.munichosica.myapp.exceptions.MotOperativoDaoException;
import com.munichosica.myapp.factory.MotOperativoDaoFactory;

public class OperativoPrueba {
	
	public static void main(String[] args) {
		
		try {
			System.out.println("entro");
			MotOperativo operativo = MotOperativoDaoFactory.create().findByIdOperativo((long)1);
			System.out.println("entroaaaa");
			 System.out.println(operativo.getOpecodigoD() + " " +
					 operativo.getOpetituloV()+ " " +
					 operativo.getOpedescripcionV()+ " " +
					 operativo.getZona().getZonnombre_V() + " " +
					 operativo.getOpelugarV() + " " +
					 operativo.getOpereferencia() + " " +
					 operativo.getOpefecha() + " " +
					 operativo.getOpehora() + " " +
					 operativo.getInspector().getPersona().getPernombresV() + " " +
					 operativo.getInspector().getPersona().getPerpaternoV() + " " +
					 operativo.getInspector().getPersona().getPermaternoV()
			);
		} catch (MotOperativoDaoException e) {
			e.printStackTrace();
		}
		
		

	}
	
}
