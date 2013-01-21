package Test;

import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.dto.MotPersona;
import com.munichosica.myapp.exceptions.MotEmprAsociadoDaoException;
import com.munichosica.myapp.factory.MotEmprAsociadoDaoFactory;

public class MotEmprAsociadoTest {
	public static void main(String[] args) {
		Insertar();
	}

	private static void Insertar() {
		MotEmprAsociado asociado=new MotEmprAsociado();
		MotPersona persona=new MotPersona();
		persona.setPercodigoD(4L);
		asociado.setAsocodigoD(4L);
		persona.setPerdniV("98765432");
		persona.setPernombresV("Erik");
		persona.setPerpaternoV("Bujaico");
		persona.setPermaternoV("Cuñao");
		//persona.setPernacimientoF("11/07/1986");
		persona.setPerestadocivilC("V");
		persona.setPermovilclaV("98765432");
		persona.setPermovilmovV("98765432");
		persona.setPermovilnexV("98765432");
		persona.setPerteleffijoV("98765432");
		persona.setPeremailV("98765432");
		persona.setPerdomicilioV("98765432");
		persona.setPerubdptoV("98765432");
		persona.setPerubidistV("98765432");
		persona.setPerubprovV("98765432");
		asociado.setAsorucV("45343");
		asociado.setAsorazonsocialV("rAZON SOCIAL");
		MotEmpresa empresa=new MotEmpresa();
		empresa.setEmpcodigoD(0L);
		asociado.setAsoestadoC("P");
		asociado.setEmpresa(empresa);
		asociado.setPersona(persona);
		try {
			MotEmprAsociadoDaoFactory.create().procesar(asociado);
			System.out.println("CODIGO NUEVO: "+asociado.getAsocodigoD());
		} catch (MotEmprAsociadoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
