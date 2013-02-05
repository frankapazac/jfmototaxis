package Test;

import com.lowagie.text.Document;
import com.munichosica.myapp.dto.MotEmpDocumento;
import com.munichosica.myapp.exceptions.MotEmpDocumentoDaoException;
import com.munichosica.myapp.factory.MotEmpDocumentoDaoFactory;

public class MotEmpDocumentoInserta {

	
	public static void main(String[] args) {
		
		MotEmpDocumento empDocumento = new MotEmpDocumento();
		empDocumento.setAdjcodigoD(1L);
		empDocumento.setMtdcodigoI(1);
		empDocumento.setEmpcodigoD(1L);
		
		try {
			MotEmpDocumentoDaoFactory.create().insert(empDocumento);
		} catch (MotEmpDocumentoDaoException e) {
			e.printStackTrace();
		}

		
	}
	
	
	
}
