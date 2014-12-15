
package muni.framework.ABMReporteJasper;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMReporteJasper extends ABMPageBean {

	private Label lblNombre = new Label();
	private TextField tfNombre = new TextField();
	private UploadedFile file = null;
	private Label lblNombreArchivo = new Label();
	private Upload upload = new Upload();
	private Hyperlink hyperLinkBajarArchivo = new Hyperlink();
	private Label lblEliminarArchivo = new Label();
	private Label lblAgregarArchivo = new Label();
	private TextField tfNombreArchivo = new TextField();

	public TextField getTfNombreArchivo() {
		return tfNombreArchivo;
	}

	public void setTfNombreArchivo(TextField tfNombreArchivo) {
		this.tfNombreArchivo = tfNombreArchivo;
	}

	public Label getLblAgregarArchivo() {
		return lblAgregarArchivo;
	}

	public void setLblAgregarArchivo(Label lblAgregarArchivo) {
		this.lblAgregarArchivo = lblAgregarArchivo;
	}

	public Label getLblEliminarArchivo() {
		return lblEliminarArchivo;
	}

	public void setLblEliminarArchivo(Label lblEliminarArchivo) {
		this.lblEliminarArchivo = lblEliminarArchivo;
	}

	public Hyperlink getHyperLinkBajarArchivo() {
		return hyperLinkBajarArchivo;
	}

	public void setHyperLinkBajarArchivo(Hyperlink hyperLinkBajarArchivo) {
		this.hyperLinkBajarArchivo = hyperLinkBajarArchivo;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

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
		ReportesJasper reporteJasper = (ReportesJasper) this.obtenerObjetoDelElementoPila(0);

		try {
			reporteJasper.setNombre(this.getTextFieldValue(this.getTfNombre()));

			reporteJasper.setNombreArchivo(file.getOriginalName());
			reporteJasper.setValor(file.getBytes());
			this.getElementoPila().getObjetos().set(0, reporteJasper);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ReportesJasper reporteJasper = this.obtenerObjetoDelElementoPila(0, ReportesJasper.class);

		if(reporteJasper.getIdReporte() != -1) {
			getTfNombre().setText(reporteJasper.getNombre());
			getTfNombreArchivo().setText(reporteJasper.getNombreArchivo().toString());
		}

	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMReporteJasper";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ReportesJasper());
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
		ReportesJasper locReporteJasper = (ReportesJasper) pObject;
		this.getElementoPila().getObjetos().set(0, locReporteJasper);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		if(pObject instanceof ReportesJasper) {
			ReportesJasper reporte = (ReportesJasper) pObject;
			this.getElementoPila().getObjetos().set(0, reporte);
		}
	}

	public void procesarDescargarReporteJaper(ActionEvent evento) {
		try {
			ReportesJasper reporteJasper = (ReportesJasper) this.obtenerObjetoDelElementoPila(0, ReportesJasper.class);
			// guardo en memoria los datos para el servlet
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bytesDocumentoDescargable", reporteJasper.getValor());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivoOriginal", reporteJasper.getNombreArchivo());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMReporteJasper$ABMReporteJasper}";
	}

	@Override
	public long getSerialVersionUID() {
		return ReportesJasper.serialVersionUID;
	}
}