package Test;

import com.munichosica.myapp.dto.MotAdjuntarArchivo;
import com.munichosica.myapp.dto.MotAsocDocumento;
import com.munichosica.myapp.dto.MotEmprAsociado;
import com.munichosica.myapp.dto.MotTipoDocumento;
import com.munichosica.myapp.exceptions.MotAsocDocumentoDaoException;
import com.munichosica.myapp.factory.MotAsocDocumentoDaoFactory;

public class MotAsocDocumentoTest {
	public static void main(String[] args) {
		MotAsocDocumento documento=new MotAsocDocumento();
		MotEmprAsociado asociado=new MotEmprAsociado();
		MotTipoDocumento tipoDocumento=new MotTipoDocumento();
		MotAdjuntarArchivo archivo=new MotAdjuntarArchivo();
		asociado.setAsocodigoD(1L);
		tipoDocumento.setMtdcodigoI(1);
		archivo.setAdjcodigoD(1L);
		documento.setArchivo(archivo);
		documento.setAsociado(asociado);
		documento.setTipoDocumento(tipoDocumento);
		try {
			MotAsocDocumentoDaoFactory.create().insert(documento);
			System.out.println("CODIGO: "+documento.getAdocodigoD());
		} catch (MotAsocDocumentoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
