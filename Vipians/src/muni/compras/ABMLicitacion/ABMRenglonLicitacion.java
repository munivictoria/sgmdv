/*
 * AgregarRenovacionLibretaSanitaria.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */

package muni.compras.ABMLicitacion;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMRenglonLicitacion extends ABMPageBean {

	protected Button btnSeleccionarUnidadMedida = new Button();
	protected HtmlAjaxCommandButton btnLimpiarUnidadMedida = new HtmlAjaxCommandButton();
	protected Label label1 = new Label();
	protected TextField tfCantidad = new TextField();
	protected Label label2 = new Label();
	protected TextField tfNombre = new TextField();
	protected TextArea taDescripcion = new TextArea();
	protected Label label3 = new Label();
	protected Label label5 = new Label();
	protected TextField tfUnidadMedida = new TextField();
	protected StaticText staticText1 = new StaticText();
	protected StaticText staticText2 = new StaticText();

	public Button getBtnSeleccionarUnidadMedida() {
		return btnSeleccionarUnidadMedida;
	}

	public void setBtnSeleccionarUnidadMedida(Button btnSeleccionarUnidadMedida) {
		this.btnSeleccionarUnidadMedida = btnSeleccionarUnidadMedida;
	}

	public HtmlAjaxCommandButton getBtnLimpiarUnidadMedida() {
		return btnLimpiarUnidadMedida;
	}

	public void setBtnLimpiarUnidadMedida(HtmlAjaxCommandButton btnLimpiarUnidadMedida) {
		this.btnLimpiarUnidadMedida = btnLimpiarUnidadMedida;
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label label5) {
		this.label5 = label5;
	}

	public TextField getTfUnidadMedida() {
		return tfUnidadMedida;
	}

	public void setTfUnidadMedida(TextField tfUnidadMedida) {
		this.tfUnidadMedida = tfUnidadMedida;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	public ABMRenglonLicitacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new LineaContratacion());
		ep.getObjetos().add(ind++, new Unidad());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		LineaContratacion lineaContratacion = (LineaContratacion) this.obtenerObjetoDelElementoPila(ind++, LineaContratacion.class);
		Unidad unidad = (Unidad) this.obtenerObjetoDelElementoPila(ind++, Unidad.class);

		lineaContratacion.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
		lineaContratacion.setCantidad(this.getTextFieldValueDouble(getTfCantidad()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, lineaContratacion);
		this.getElementoPila().getObjetos().set(ind++, unidad);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		LineaContratacion lineaContratacion = (LineaContratacion) this.obtenerObjetoDelElementoPila(ind++, LineaContratacion.class);
		Unidad unidad = (Unidad) this.obtenerObjetoDelElementoPila(ind++, Unidad.class);

		this.getTaDescripcion().setText(lineaContratacion.getDescripcion());
		this.getTfUnidadMedida().setText(unidad.toString());
		this.getTfCantidad().setText(Conversor.getStringDeDouble(lineaContratacion.getCantidad()));
	}

	public String btnSeleccionarUnidadMedida_action() {
		return navegarParaSeleccionar("AdminUnidad");
	}

	public String btnLimpiarUnidadMedida_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, Unidad.class, this.getTfUnidadMedida());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRenglonLicitacion";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Unidad) {
			Unidad unidad = (Unidad) pObject;
			this.getElementoPila().getObjetos().set(1, unidad);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMLicitacion$ABMRenglonLicitacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return LineaContratacion.serialVersionUID;
	}
}