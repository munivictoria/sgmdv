/*
 * ModificarValorBasicoMejora.java
 *
 * Created on 27 de octubre de 2006, 15:59
 * Copyright Trascender
 */
package muni.catastro.ABMValorBasicoMejora;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMValorBasicoMejora extends ABMPageBean {

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private ValorBasicoMejora valorBasicoABM = null;

	public ValorBasicoMejora getValorBasicoABM() {
		return valorBasicoABM;
	}

	public void setValorBasicoABM(ValorBasicoMejora valorBasicoABM) {
		this.valorBasicoABM = valorBasicoABM;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfCategoria = new TextField();

	public TextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(TextField tf) {
		this.tfCategoria = tf;
	}

	private Button btnSeleccionarCategoria = new Button();

	public Button getBtnSeleccionarCategoria() {
		return btnSeleccionarCategoria;
	}

	public void setBtnSeleccionarCategoria(Button b) {
		this.btnSeleccionarCategoria = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfAnioVigencia = new TextField();

	public TextField getTfAnioVigencia() {
		return tfAnioVigencia;
	}

	public void setTfAnioVigencia(TextField tf) {
		this.tfAnioVigencia = tf;
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
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMValorBasicoMejora() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, this.getValorBasicoABM());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		ValorBasicoMejora valor = (ValorBasicoMejora) this.obtenerObjetoDelElementoPila(0, ValorBasicoMejora.class);

		valor.setAnioVigente(this.getTextFieldValueInteger(getTfAnioVigencia()));
		valor.setValor(this.getTextFieldValueDouble(getTfValor()));

		this.getElementoPila().getObjetos().set(0, valor);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ValorBasicoMejora valor = (ValorBasicoMejora) this.obtenerObjetoDelElementoPila(0, ValorBasicoMejora.class);

		if (valor.getAnioVigente() != null) {
			this.getTfAnioVigencia().setText(valor.getAnioVigente().toString());
		}
		if (valor.getCategoria() != null) {
			this.getTfCategoria().setText(valor.getCategoria().toString());
		}
		if (valor.getValor() != null) {
			this.getTfValor().setText(Conversor.getStringDeDouble(valor.getValor()));
		}
	}

	public String btnSeleccionarCategoria_action() {
		return navegarParaSeleccionar("AdminCategoria");
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMValorBasicoMejora";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Categoria) {
			Categoria categoria = (Categoria) pObject;
			ValorBasicoMejora valor = (ValorBasicoMejora) this.obtenerObjetoDelElementoPila(0, ValorBasicoMejora.class);
			valor.setCategoria(categoria);

			this.getElementoPila().getObjetos().set(0, valor);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ValorBasicoMejora valor = (ValorBasicoMejora) pObject;

		this.getElementoPila().getObjetos().set(0, valor);
	}
	
	@Override
	public long getSerialVersionUID() {
		return ValorBasicoMejora.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		ValorBasicoMejora locValorBasicoMejora = this.obtenerObjetoDelElementoPila(0, ValorBasicoMejora.class);
		this.getTablaLogs().getLdpLogs().setList(locValorBasicoMejora.getListaLogsAuditoria());
	}
}