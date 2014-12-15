/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMTipoBien;

import java.util.List;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author danilo
 */
public class ABMTipoBien extends ABMPageBean {

	Label lblNombre = new Label();
	Label lblDescripcion = new Label();
	private Label lbCodigoImputacion = new Label();
	TextField tfNombre = new TextField();
	private TextField tfCodigoImputacion = new TextField();

	public Label getLbCodigoImputacion() {
		return lbCodigoImputacion;
	}

	public void setLbCodigoImputacion(Label lbCodigoImputacion) {
		this.lbCodigoImputacion = lbCodigoImputacion;
	}

	public TextField getTfCodigoImputacion() {
		return tfCodigoImputacion;
	}

	public void setTfCodigoImputacion(TextField tfCodigoImputacion) {
		this.tfCodigoImputacion = tfCodigoImputacion;
	}

	TextArea taDescripcion = new TextArea();

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	protected PanelGroup gpFinalizarComo = new PanelGroup();

	public PanelGroup getGpFinalizarComo() {
		return gpFinalizarComo;
	}

	public void setGpFinalizarComo(PanelGroup gpFinalizarComo) {
		this.gpFinalizarComo = gpFinalizarComo;
	}

	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda

		return null;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoBien tipoBien = (TipoBien) this.obtenerObjetoDelElementoPila(0, TipoBien.class);
		
		tipoBien.setNombre(this.getTextFieldValue(getTfNombre()));
		tipoBien.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
		tipoBien.setCodigoImputacion(this.getTextFieldValue(getTfCodigoImputacion()));

		this.getElementoPila().getObjetos().set(0, tipoBien);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		TipoBien tipoBien = (TipoBien) this.obtenerObjetoDelElementoPila(ind++, TipoBien.class);;

		if (tipoBien != null && tipoBien.getIdTipoBien() != -1) {
			this.getTfNombre().setText(tipoBien.getNombre());
			this.getTaDescripcion().setText(tipoBien.getDescripcion());
			this.getTfCodigoImputacion().setText(tipoBien.getCodigoImputacion());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoBien";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new TipoBien()); // 0

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoBien tipoBien = null;
		if (pObject instanceof TipoBien) {
			tipoBien = (TipoBien) pObject;
			this.getElementoPila().getObjetos().set(0, tipoBien);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return TipoBien.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMTipoBien$ABMTipoBien}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoBien locTipoBien = this.obtenerObjetoDelElementoPila(0, TipoBien.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoBien.getListaLogsAuditoria());
	}
}