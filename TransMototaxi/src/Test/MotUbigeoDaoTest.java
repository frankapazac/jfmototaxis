package Test;

import java.util.List;

import com.munichosica.myapp.dto.MotUbigeo;
import com.munichosica.myapp.exceptions.MotUbigeoDaoException;
import com.munichosica.myapp.factory.MotUbigeoDaoFactory;
import com.munichosica.myapp.jdbc.MotUbigeoDaoImpl;

public class MotUbigeoDaoTest {
	public static void main(String[] args) {
		try {
			List<MotUbigeo> list=MotUbigeoDaoFactory.create().findAllDepartamentos();
			for(MotUbigeo ubigeo:list){
				System.out.println(ubigeo.getIdubigeo()+".- "+ubigeo.getNombubigeo());
			}
		} catch (MotUbigeoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
