package Test;

import com.munichosica.myapp.dto.Usuario;
import com.munichosica.myapp.exceptions.UsuarioDaoException;
import com.munichosica.myapp.factory.UsuarioDaoFactory;

public class UsuarioPrueba {

	public static void main(String[] args) {
		

		Usuario usu = new Usuario();
		usu.setUsuusuarioV("FRANK");
		usu.setPass("322");
		usu.setNewPass("321");
		
		try {
			UsuarioDaoFactory.create().update(usu);
		} catch (UsuarioDaoException e) {
			e.printStackTrace();
		}
		
	}
	
}
