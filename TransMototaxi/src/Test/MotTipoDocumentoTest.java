package Test;

import java.util.List;

import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotTipoDocumentoDaoException;
import com.munichosica.myapp.factory.MotTipoDocumentoDaoFactory;

public class MotTipoDocumentoTest {
	public static void main(String[] args) {
		List<MotTipoDocumento> list;
		try {
			list = MotTipoDocumentoDaoFactory.create().findByTable("ADO");
			for(MotTipoDocumento tipoDocumento:list){
				System.out.println(tipoDocumento.getMtdcodigoI()+": "+tipoDocumento.getMtdnombreV());
			}
		} catch (MotTipoDocumentoDaoException e) {
			e.printStackTrace();
		}
	}
}
