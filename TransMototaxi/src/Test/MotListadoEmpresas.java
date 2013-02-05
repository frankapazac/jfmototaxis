package Test;

import com.munichosica.myapp.dto.MotEmpRepresentante;
import com.munichosica.myapp.exceptions.MotEmpRepresentanteDaoException;
import com.munichosica.myapp.factory.MotEmpRepresentanteDaoFactory;

public class MotListadoEmpresas {

	public static void main(String[] args) {
		
		try {
			MotEmpRepresentante representante = MotEmpRepresentanteDaoFactory.create().findByEmpresa((long)1);
				System.out.println(
						representante.getEmpProp().getEmpPropietario().getPersona().getPernombresV()
						
						//MotEmpProp.empPropietario.empPropietario.persona.PernombresV
				+ " "+  representante.getEmpProp().getEmpPropietario().getPersona().getPerpaternoV()
				+ " "+  representante.getEmpProp().getEmpPropietario().getPersona().getPermaternoV()
				+ " "+  representante.getEmpProp().getEmpresa().getEmpcodigoD()
				+ " "+  representante.getEmpProp().getEmpresa().getEmprucV()
				+ " "+  representante.getEmpProp().getEmpresa().getEmpdireccionV()+ " " +
		
						representante.getEmpProp().getEmpresa().getEmptelefono1V() + " " +
						representante.getEmpProp().getEmpresa().getEmptelefono2V() + " " +
						representante.getEmpProp().getEmpresa().getEmpcelularmovV() + " " +
						representante.getEmpProp().getEmpresa().getEmpcelularclaV() + " " +
						representante.getEmpProp().getEmpresa().getEmpcelularnexV() + " " +
						representante.getEmpProp().getEmpresa().getEmpmailV() + " "+
						representante.getEmpProp().getEmpresa().getEmppagwebV()+ " "+
						representante.getEmpProp().getEmpresa().getDepartamento().getNombubigeo()+" "+
						representante.getEmpProp().getEmpresa().getProvincia().getNombubigeo()+" "+
						representante.getEmpProp().getEmpresa().getDistrito().getNombubigeo()
				);
		} catch (MotEmpRepresentanteDaoException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
