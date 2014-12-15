
package muni.expedientes.ABMExpediente;

import muni.expedientes.AbmNodoExpediente;
import muni.expedientes.tables.TableDocumentos;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMDocumento extends AbmNodoExpediente {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();

	private TextField tfUbicacion = new TextField();
	private Label lblUbicacion = new Label();

	private TextField tfNroRegistro = new TextField();
	private Label lblNroRegistro = new Label();

	private TextField tfFecha = new TextField();
	private Label lblFecha = new Label();

	private Label lblObservacion = new Label();
	private TextArea taObservacion = new TextArea();

	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	private TableDocumentos.WDocumento wDocumento;

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfUbicacion() {
		return tfUbicacion;
	}

	public void setTfUbicacion(TextField tfUbicacion) {
		this.tfUbicacion = tfUbicacion;
	}

	public Label getLblUbicacion() {
		return lblUbicacion;
	}

	public void setLblUbicacion(Label lblUbicacion) {
		this.lblUbicacion = lblUbicacion;
	}

	public TextField getTfNroRegistro() {
		return tfNroRegistro;
	}

	public void setTfNroRegistro(TextField tfNroRegistro) {
		this.tfNroRegistro = tfNroRegistro;
	}

	public Label getLblNroRegistro() {
		return lblNroRegistro;
	}

	public void setLblNroRegistro(Label lblNroRegistro) {
		this.lblNroRegistro = lblNroRegistro;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	public Label getLblObservacion() {
		return lblObservacion;
	}

	public void setLblObservacion(Label lblObservacion) {
		this.lblObservacion = lblObservacion;
	}

	public TextArea getTaObservacion() {
		return taObservacion;
	}

	public void setTaObservacion(TextArea taObservacion) {
		this.taObservacion = taObservacion;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Documento.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);
	}

	@Override
	public void getElementosPila() {
		int ind = 0;
		wDocumento = obtenerObjetoDelElementoPila(ind++, TableDocumentos.WDocumento.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setElementosPila() {
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, wDocumento);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		getElementosPila();
		Documento locDocumento = wDocumento.documento;
		locDocumento.setFecha(getTextFieldValueDate(tfFecha));
		locDocumento.setLocalizacion(getTextFieldValue(tfUbicacion));
		locDocumento.setNroRegistro(getTextFieldValueInteger(tfNroRegistro) != null ? getTextFieldValueInteger(tfNroRegistro).longValue() : null);
		locDocumento.setObservacion(getTextAreaValue(taObservacion));

		Object estadoSelected = this.getDdEstado().getSelected();
		Documento.Estado estado;
		if(estadoSelected != null && estadoSelected.toString().length() > 0) {
			estado = Documento.Estado.valueOf(estadoSelected.toString());
		} else {
			estado = null;
		}
		locDocumento.setEstado(estado);

		setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		getElementosPila();
		Documento locDocumento = wDocumento.documento;

		this.getTfNombre().setText(locDocumento.getPlantilla());
		this.getTfNroRegistro().setText(locDocumento.getNroRegistro());
		this.getTfFecha().setText(locDocumento.getFecha());
		this.getTaObservacion().setText(locDocumento.getObservacion());
		this.getTfUbicacion().setText(locDocumento.getLocalizacion());
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locDocumento.getEstado())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locDocumento.getEstado())));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, wDocumento);
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		getElementosPila();
		// if (pObject == null) {
		// // wDocumento = new TableDocumentos().new WDocumento(new Documento(),
		// null);
		// } else {
		wDocumento = (TableDocumentos.WDocumento) pObject;
		// }
		setElementosPila();
	}

	@Override
	public String btnGuardar_action() {
		getElementosPila();
		getRequestBean1().setObjetoSeleccion(wDocumento);
		return super.btnGuardar_action();
	}

	@Override
	protected String getNombrePagina() {
		return "Documento";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocumento";
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMExpediente$ABMDocumento}";
	}

	@Override
	public long getSerialVersionUID() {
		return Documento.serialVersionUID;
	}
}