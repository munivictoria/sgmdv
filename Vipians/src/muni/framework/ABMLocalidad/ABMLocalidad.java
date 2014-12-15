/*
 * ABMLocalidad.java
 *
 * Created on 5 de octubre de 2006, 10:21
 * Copyright Trascender
 */
package muni.framework.ABMLocalidad;

import java.util.Set;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMLocalidad extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Set<String> locListaProvincias = getApplicationBean1().getMapaProvincia().keySet();

		Option[] opProvincias = new Option[locListaProvincias.size() + 1];
		int i = 0;
		opProvincias[i++] = new Option("", "");
		for (String cadaProvincia : locListaProvincias) {
			opProvincias[i++] = new Option(cadaProvincia, cadaProvincia);
		}
		this.ddProvinciaOptions.setOptions(opProvincias);
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfCodigoPostal = new TextField();

	public TextField getTfCodigoPostal() {
		return tfCodigoPostal;
	}

	public void setTfCodigoPostal(TextField tf) {
		this.tfCodigoPostal = tf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	// Atributos propios de la pagina.
	// CAMBIAR: Vincular a campos ocultos.
	private Long idPagina = null;
	private Long idSubSesion = null;

	@Override
	public Long getIdPagina() {
		return idPagina;
	}

	@Override
	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
	}

	@Override
	public Long getIdSubSesion() {
		return idSubSesion;
	}

	@Override
	public void setIdSubSesion(Long idSubSesion) {
		this.idSubSesion = idSubSesion;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private DropDown ddProvincia = new DropDown();
	private SingleSelectOptionsList ddProvinciaOptions = new SingleSelectOptionsList();

	public DropDown getDdProvincia() {
		return ddProvincia;
	}

	public void setDdProvincia(DropDown ddProvincia) {
		this.ddProvincia = ddProvincia;
	}

	public SingleSelectOptionsList getDdProvinciaOptions() {
		return ddProvinciaOptions;
	}

	public void setDdProvinciaOptions(SingleSelectOptionsList ddProvinciaOptions) {
		this.ddProvinciaOptions = ddProvinciaOptions;
	}

	private Button btnSeleccionarProvincia = new Button();

	public Button getBtnSeleccionarProvincia() {
		return btnSeleccionarProvincia;
	}

	public void setBtnSeleccionarProvincia(Button b) {
		this.btnSeleccionarProvincia = b;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMLocalidad() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Localidad());

		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Localidad localidad = this.obtenerObjetoDelElementoPila(0, Localidad.class);

		this.getTfNombre().setText(localidad.getNombre());
		this.getTfCodigoPostal().setText(localidad.getCodigoPostal());
		if (localidad.getProvincia() != null && localidad.getProvincia().getIdProvincia() != -1) {
			this.getDdProvincia().setSelected(localidad.getProvincia().getNombre());
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Localidad localidad = this.obtenerObjetoDelElementoPila(0, Localidad.class);

		localidad.setNombre(this.getTextFieldValue(getTfNombre()));
		localidad.setCodigoPostal(this.getTextFieldValue(getTfCodigoPostal()));
		localidad.setProvincia(this.getDDObjectValue(getDdProvincia(), getApplicationBean1().getMapaProvincia()));

		this.getElementoPila().getObjetos().set(0, localidad);
	}

	public String btnSeleccionarProvincia_action() {
		return navegarParaSeleccionar("AdminProvincia");
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMLocalidad";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Provincia) {
			if (pObject != null) {
				Provincia provincia = (Provincia) pObject;
				Localidad localidad = this.obtenerObjetoDelElementoPila(0, Localidad.class);
				localidad.setProvincia(provincia);

				this.getElementoPila().getObjetos().set(0, localidad);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Localidad localidad = (Localidad) pObject;


		this.getElementoPila().getObjetos().set(0, localidad);
	}

	@Override
	public long getSerialVersionUID() {
		return Localidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMLocalidad$ABMLocalidad}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Localidad locLocalidad = this.obtenerObjetoDelElementoPila(0, Localidad.class);
		this.getTablaLogs().getLdpLogs().setList(locLocalidad.getListaLogsAuditoria());
	}
}