/*
 * ABMTipoCalle.java
 *
 * Created on 18 de octubre de 2006, 12:39
 * Copyright Trascender SRL
 */
package muni.catastro.ABMTipoCalle;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.TipoCalle;
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
public class ABMTipoCalle extends ABMPageBean {

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

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMTipoCalle() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new TipoCalle());

		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoCalle tipoCalle = (TipoCalle) this.obtenerObjetoDelElementoPila(0, TipoCalle.class);

		tipoCalle.setNombre(this.getTextFieldValue(getTfNombre()));
		tipoCalle.setDescripcion(this.getTextAreaValue(getTaDescripcion()));

		this.getElementoPila().getObjetos().set(0, tipoCalle);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		TipoCalle tipoCalle = (TipoCalle) this.obtenerObjetoDelElementoPila(0, TipoCalle.class);

		this.getTfNombre().setText(tipoCalle.getNombre());
		this.getTaDescripcion().setText(tipoCalle.getDescripcion());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoCalle";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoCalle tipoCalle = (TipoCalle) pObject;
		this.getElementoPila().getObjetos().set(0, tipoCalle);
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoCalle.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMTipoCalle$ABMTipoCalle}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoCalle locTipoCalle = this.obtenerObjetoDelElementoPila(0, TipoCalle.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoCalle.getListaLogsAuditoria());
	}
}