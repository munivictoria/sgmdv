/*
 * ABMConsumoBasico.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpOSP.ABMConsumoBasico;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMConsumoBasico extends ABMPageBean {

	private TextField tfConsumoInicial = new TextField();

	public TextField getTfConsumoInicial() {
		return tfConsumoInicial;
	}

	public void setTfConsumoInicial(TextField tf) {
		this.tfConsumoInicial = tf;
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

	private TextField tfConsumoPorExcedente = new TextField();

	public TextField getTfConsumoPorExcedente() {
		return tfConsumoPorExcedente;
	}

	public void setTfConsumoPorExcedente(TextField tf) {
		this.tfConsumoPorExcedente = tf;
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

	private TextField tfSuperficieMejorasMinimo = new TextField();

	public TextField getTfSuperficieMejorasMinimo() {
		return tfSuperficieMejorasMinimo;
	}

	public void setTfSuperficieMejorasMinimo(TextField tf) {
		this.tfSuperficieMejorasMinimo = tf;
	}

	private TextField tfSuperficieMejorasMaximo = new TextField();

	public TextField getTfSuperficieMejorasMaximo() {
		return tfSuperficieMejorasMaximo;
	}

	public void setTfSuperficieMejorasMaximo(TextField tf) {
		this.tfSuperficieMejorasMaximo = tf;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMConsumoBasico() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ConsumoBasico());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		ConsumoBasico consumoBasico = (ConsumoBasico) this.obtenerObjetoDelElementoPila(0, ConsumoBasico.class);

		Object consumoPorExcedente = this.getTfConsumoPorExcedente().getText();
		Object superficieMejorasMinimo = this.getTfSuperficieMejorasMinimo().getText();
		Object superficieMejorasMaximo = this.getTfSuperficieMejorasMaximo().getText();

		consumoBasico.setConsumoInicial(getTextFieldValueDouble(this.getTfConsumoInicial()));
		consumoBasico.setConsumoPorExcedente(getTextFieldValueDouble(this.getTfConsumoPorExcedente()));
		consumoBasico.setSuperficieMejorasMinimo(getTextFieldValueDouble(this.getTfSuperficieMejorasMinimo()));
		consumoBasico.setSuperficieMejorasMaximo(getTextFieldValueDouble(this.getTfSuperficieMejorasMaximo()));

		this.getElementoPila().getObjetos().set(0, consumoBasico);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		ConsumoBasico consumoBasico = null;

		consumoBasico = (ConsumoBasico) this.obtenerObjetoDelElementoPila(0, ConsumoBasico.class);

		if(consumoBasico.getConsumoInicial() != null)
			this.getTfConsumoInicial().setText(Conversor.getStringDeDouble(consumoBasico.getConsumoInicial()));
		if(consumoBasico.getConsumoPorExcedente() != null)
			this.getTfConsumoPorExcedente().setText(Conversor.getStringDeDouble(consumoBasico.getConsumoPorExcedente()));
		if(consumoBasico.getSuperficieMejorasMinimo() != null)
			this.getTfSuperficieMejorasMinimo().setText(Conversor.getStringDeDouble(consumoBasico.getSuperficieMejorasMinimo()));
		if(consumoBasico.getSuperficieMejorasMaximo() != null)
			this.getTfSuperficieMejorasMaximo().setText(Conversor.getStringDeDouble(consumoBasico.getSuperficieMejorasMaximo()));

	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMConsumoBasico";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ConsumoBasico consumoBasico = (ConsumoBasico) pObject;
		this.getElementoPila().getObjetos().set(0, consumoBasico);
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConsumoBasico.serialVersionUID;
	}
}