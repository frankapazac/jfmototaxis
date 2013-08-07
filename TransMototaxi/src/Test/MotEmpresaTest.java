package Test;

import com.munichosica.myapp.dto.MotEmpresa;
import com.munichosica.myapp.exceptions.MotEmpresaDaoException;
import com.munichosica.myapp.factory.MotEmpresaDaoFactory;

public class MotEmpresaTest {
	public static void main(String[] args) {
		MotEmpresa empresa;
		try {
			empresa = MotEmpresaDaoFactory.create().get(2l);
			System.out.println(empresa.getEmprazonsocialV());
		} catch (MotEmpresaDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
