/*
 * ABMProvincia.java
 *
 * Created on 4 de octubre de 2006, 09:17
 * Copyright Trascender
 */
package muni.framework.ABMProvincia;

import java.util.Set;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Pais;
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
public class ABMProvincia extends ABMPageBean {

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

	private TextField tfAbreviatura = new TextField();

	public TextField getTfAbreviatura() {
		return tfAbreviatura;
	}

	public void setTfAbreviatura(TextField tf) {
		this.tfAbreviatura = tf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	@Override
	protected void _init() throws Exception {
		Set<String> locListaPaises = getApplicationBean1().getMapaPais().keySet();

		Option[] opPaises = new Option[locListaPaises.size() + 1];
		int i = 0;
		opPaises[i++] = new Option("", "");
		for (String cadaPais : locListaPaises) {
			opPaises[i++] = new Option(cadaPais, cadaPais);
		}
		this.ddPaisOptions.setOptions(opPaises);
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private DropDown ddPais = new DropDown();
	private SingleSelectOptionsList ddPaisOptions = new SingleSelectOptionsList();

	public DropDown getDdPais() {
		return ddPais;
	}

	public void setDdPais(DropDown ddPais) {
		this.ddPais = ddPais;
	}

	public SingleSelectOptionsList getDdPaisOptions() {
		return ddPaisOptions;
	}

	public void setDdPaisOptions(SingleSelectOptionsList ddPaisOptions) {
		this.ddPaisOptions = ddPaisOptions;
	}

	private Button btnSeleccionarPais = new Button();

	public Button getBtnSeleccionarPais() {
		return btnSeleccionarPais;
	}

	public void setBtnSeleccionarPais(Button b) {
		this.btnSeleccionarPais = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tf) {
		this.tfCodigo = tf;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMProvincia() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Provincia());

		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Provincia provincia = this.obtenerObjetoDelElementoPila(0, Provincia.class);

		this.getTfNombre().setText(provincia.getNombre());
		this.getTfAbreviatura().setText(provincia.getAbreviatura());
		this.getTfCodigo().setText(provincia.getCodigo());
		if (provincia.getPais() != null && provincia.getPais().getIdPais() != -1) {
			this.getDdPais().setSelected(provincia.getPais().toString());
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Provincia provincia = this.obtenerObjetoDelElementoPila(0, Provincia.class);

		provincia.setNombre(this.getTextFieldValue(getTfNombre()));
		provincia.setAbreviatura(this.getTextFieldValue(getTfAbreviatura()));
		provincia.setCodigo(this.getTextFieldValue(getTfCodigo()));

		provincia.setPais(this.getDDObjectValue(getDdPais(), getApplicationBean1().getMapaPais()));

		this.getElementoPila().getObjetos().set(0, provincia);
	}

	public String btnSeleccionarPais_action() {
		return navegarParaSeleccionar("AdminPais");
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMProvincia";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Pais) {
			if (pObject != null) {
				Pais pais = (Pais) pObject;
				Provincia provincia = this.obtenerObjetoDelElementoPila(0, Provincia.class);
				provincia.setPais(pais);

				this.getElementoPila().getObjetos().set(0, provincia);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Provincia provincia = (Provincia) pObject;
		Pais pais = provincia.getPais();

		this.getElementoPila().getObjetos().set(0, provincia);
	}

	@Override
	public long getSerialVersionUID() {
		return Provincia.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMProvincia$ABMProvincia}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Provincia locProvincia = this.obtenerObjetoDelElementoPila(0, Provincia.class);
		this.getTablaLogs().getLdpLogs().setList(locProvincia.getListaLogsAuditoria());
	}
}
