
package muni.habilitaciones.grpAutomotor.ABMValuacionAcara;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMProcesarArchivoAcara extends ABMPageBean {

	private UploadedFile file = null;
	private Label lblNombreArchivo = new Label();
	private Upload upload = new Upload();

	public Label getLblNombreArchivo() {
		return lblNombreArchivo;
	}

	public void setLblNombreArchivo(Label lblNombreArchivo) {
		this.lblNombreArchivo = lblNombreArchivo;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		this.getElementoPila().getObjetos().set(0, file);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMProcesarArchivoValuacionAcara";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, null);
		return ep;
	}

	public void procesarArchivo(ValueChangeEvent event) throws AbortProcessingException {
		Object value = event.getNewValue();
		if(value != null && value instanceof UploadedFile) {
			file = (UploadedFile) value;
		}

	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}