/*
 * ABMDiaFeriado.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.framework.ABMDiaFeriado;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.DiaFeriado;
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
public class ABMDiaFeriado extends ABMPageBean {

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

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfFecha = new TextField();

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tf) {
		this.tfFecha = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
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
	public ABMDiaFeriado() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DiaFeriado());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		DiaFeriado diaFeriado = (DiaFeriado) this.obtenerObjetoDelElementoPila(0, DiaFeriado.class);

		diaFeriado.setNombre(this.getTextFieldValue(getTfNombre()));
		diaFeriado.setFecha(this.getTextFieldValueDate(getTfFecha()));

		this.getElementoPila().getObjetos().set(0, diaFeriado);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		int ind = 0;
		DiaFeriado diaFeriado = (DiaFeriado) this.obtenerObjetoDelElementoPila(ind++, DiaFeriado.class);

		this.getTfNombre().setText(diaFeriado.getNombre());
		if (diaFeriado.getFecha() != null) {
			this.getTfFecha().setText(Conversor.getStringDeFechaCorta(diaFeriado.getFecha()));
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDiaFeriado";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DiaFeriado diaFeriado = (DiaFeriado) pObject;

		this.getElementoPila().getObjetos().set(0, diaFeriado);
	}
	
	@Override
	public long getSerialVersionUID() {
		return DiaFeriado.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMDiaFeriado$ABMDiaFeriado}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DiaFeriado locDiaFeriado = this.obtenerObjetoDelElementoPila(0, DiaFeriado.class);
		this.getTablaLogs().getLdpLogs().setList(locDiaFeriado.getListaLogsAuditoria());
	}
}