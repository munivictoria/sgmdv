/*
 * ModificarRegAlicuotaSHPS.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpSHPS.ABMRegAlicuotaSHPS;

import java.util.ArrayList;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota.TipoRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMRegAlicuotaSHPS extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoRegAlicuota.values(), "cap");
		ddTipoDefaultOptions.setOptions(op);
	}

	private Label lblCodigoCiiu = new Label();
	private TextField tfCodigoCiiu = new TextField();
	private Button btnSeleccionarCodigoCiiu = new Button();

	public Button getBtnSeleccionarCodigoCiiu() {
		return btnSeleccionarCodigoCiiu;
	}

	public void setBtnSeleccionarCodigoCiiu(Button btnSeleccionarCodigoCiiu) {
		this.btnSeleccionarCodigoCiiu = btnSeleccionarCodigoCiiu;
	}

	public Label getLblCodigoCiiu() {
		return lblCodigoCiiu;
	}

	public void setLblCodigoCiiu(Label lblCodigoCiiu) {
		this.lblCodigoCiiu = lblCodigoCiiu;
	}

	public TextField getTfCodigoCiiu() {
		return tfCodigoCiiu;
	}

	public void setTfCodigoCiiu(TextField tfCodigoCiiu) {
		this.tfCodigoCiiu = tfCodigoCiiu;
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

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private TextArea taNombre = new TextArea();

	public TextArea getTaNombre() {
		return taNombre;
	}

	public void setTaNombre(TextArea ta) {
		this.taNombre = ta;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfValor = new TextField();

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tf) {
		this.tfValor = tf;
	}

	private TextField tfMinimo = new TextField();

	public TextField getTfMinimo() {
		return tfMinimo;
	}

	public void setTfMinimo(TextField tf) {
		this.tfMinimo = tf;
	}

	private DropDown ddTipo = new DropDown();

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown dd) {
		this.ddTipo = dd;
	}

	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoDefaultOptions = ssol;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMRegAlicuotaSHPS() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		ep.getObjetos().add(ind++, new Rubro());
		ep.getObjetos().add(ind++, new ArrayList());// AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		Rubro regAlicuota = (Rubro) this.obtenerObjetoDelElementoPila(ind++, Rubro.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		regAlicuota.setDescripcion(getTextAreaValue(this.getTaNombre()));
		regAlicuota.setValor(getTextFieldValueDouble(this.getTfValor()));
		regAlicuota.setMinimo(getTextFieldValueDouble(this.getTfMinimo()));
		regAlicuota.setTipoRegAlicuota(getDDEnumValue(this.getDdTipo(), TipoRegAlicuota.class));

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);

		// NOTA: seteo el tipo de obligacion como SHPS
		// regAlicuota.setTipoObligacion(PlantillaObligacion.TipoObligacion.SHPS);

		regAlicuota.setListaAtributosDinamicos(atributosDinamicos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, regAlicuota);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	public String btnSeleccionarCodigoCiiu_action() {
		return navegarParaSeleccionar("AdminCodigoCiiu");
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		Rubro regAlicuota = null;
		ArrayList atributosDinamicos = null;

		int ind = 0;
		regAlicuota = (Rubro) this.obtenerObjetoDelElementoPila(ind++, Rubro.class);
		try {
			atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
					.getAtributosPorRecurso(Rubro.serialVersionUID, regAlicuota.getListaAtributosDinamicos(), null);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		} catch(TrascenderException ex) {
			ex.printStackTrace();
		}
		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.getTaNombre().setText(regAlicuota.getDescripcion());

		if(regAlicuota.getValor() != null) {
			this.getTfValor().setText(Conversor.getStringDeDouble(regAlicuota.getValor()));
		}
		if(regAlicuota.getMinimo() != null) {
			this.getTfMinimo().setText(Conversor.getStringDeDouble(regAlicuota.getMinimo()));
		}

		if(regAlicuota.getTipoRegAlicuota() != null) {
			ddTipo.setSelected(Util.getEnumNameFromString(regAlicuota.getTipoRegAlicuota().toString()));
			ddTipoDefaultOptions.setSelectedValue(Util.getEnumNameFromString(regAlicuota.getTipoRegAlicuota().toString()));
		}
		if(regAlicuota.getCodigoCiiu() != null) {
			tfCodigoCiiu.setText(regAlicuota.getCodigoCiiu().toString());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRegAlicuotaSHPS";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof CodigoCiiu) {
			Rubro regAlicuota = (Rubro) this.obtenerObjetoDelElementoPila(0, Rubro.class);
			regAlicuota.setCodigoCiiu((CodigoCiiu) pObject);
			this.getElementoPila().getObjetos().set(0, regAlicuota);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Rubro regAlicuota = (Rubro) pObject;
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, regAlicuota);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Rubro locRubro = this.obtenerObjetoDelElementoPila(0, Rubro.class);
		this.getTablaLogs().getLdpLogs().setList(locRubro.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Rubro.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS}";
	}
}
