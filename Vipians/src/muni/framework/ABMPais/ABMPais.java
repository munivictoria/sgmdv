/*
 * ABMPais.java
 *
 * Created on 21 de septiembre de 2006, 14:24
 * Copyright Trascender
 */
package muni.framework.ABMPais;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Pais;
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
public class ABMPais extends ABMPageBean {

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

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMPais() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Pais());

		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Pais pais = (Pais) this.obtenerObjetoDelElementoPila(0, Pais.class);

		this.getTfNombre().setText(pais.getNombre());
		this.getTfAbreviatura().setText(pais.getAbreviatura());
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Pais pais = (Pais) this.obtenerObjetoDelElementoPila(0, Pais.class);

		pais.setNombre(this.getTextFieldValue(getTfNombre()));
		pais.setAbreviatura(this.getTextFieldValue(getTfAbreviatura()));

		this.getElementoPila().getObjetos().set(0, pais);
	}

	@Override
	protected String getNombrePagina() {
		return "Pais";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPais";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Pais pais = (Pais) pObject;

		this.getElementoPila().getObjetos().set(0, pais);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Pais.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPais$ABMPais}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Pais locPais = this.obtenerObjetoDelElementoPila(0, Pais.class);
		this.getTablaLogs().getLdpLogs().setList(locPais.getListaLogsAuditoria());
	}
}