/*
 * ABMDeclaracionJurada.java
 *
 * Created on 2 de noviembre de 2006, 14:35
 * Copyright Trascender
 */
package muni.catastro.ABMDeclaracionJurada;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
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
public class ABMDeclaracionJurada extends ABMPageBean {

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

	private TextField tfFechaInscripcion = new TextField();

	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public void setTfFechaInscripcion(TextField tfCodigo) {
		this.tfFechaInscripcion = tfCodigo;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDeclaracionJurada() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DeclaracionJurada());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Obtener los valores de los campos y
		// asignarlos a los atributos de los objetos de la pagina
		DeclaracionJurada declaracion = (DeclaracionJurada) this.obtenerObjetoDelElementoPila(0, DeclaracionJurada.class);

		Object codigo = this.getTfCodigo().getText();
		Object fecha = this.getTfFechaInscripcion().getText();

		if (codigo != null && codigo != "")
			declaracion.setNumero(codigo.toString());
		else
			declaracion.setNumero(null);
		if (fecha != null && fecha != "")
			declaracion.setFechaInscripcion(Conversor.getFechaCortaDeString(fecha.toString()));
		else
			declaracion.setFechaInscripcion(null);

		this.getElementoPila().getObjetos().set(0, declaracion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
		DeclaracionJurada declaracion = (DeclaracionJurada) this.obtenerObjetoDelElementoPila(0, DeclaracionJurada.class);

		if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
			// CAMBIAR:
			declaracion = (DeclaracionJurada) this.obtenerObjetoDelElementoPila(0, DeclaracionJurada.class);
		}

		this.getTfCodigo().setText(declaracion.getNumero());
		this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(declaracion.getFechaInscripcion()));
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDeclaracionJurada";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		DeclaracionJurada declaracionJurada = null;
		if(pObject instanceof DeclaracionJurada) {
			if(pObject != null) {
				declaracionJurada = (DeclaracionJurada) pObject;
				this.getElementoPila().getObjetos().set(0, declaracionJurada);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DeclaracionJurada declaracion = (DeclaracionJurada) pObject;

		this.getElementoPila().getObjetos().set(0, declaracion);
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada}";
	}

	@Override
	public long getSerialVersionUID() {
		return DeclaracionJurada.serialVersionUID;
	}
}