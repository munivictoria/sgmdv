/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMEstadoSolicitudSuministro;

import java.util.List;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author danilo
 */
public class ABMEstadoSolicitudSuministro extends ABMPageBean {

	Label lblNombre = new Label();
	Label lblDescripcion = new Label();
	Label lblEstadoFinal = new Label();
	Label lblUsadoEnContratacion = new Label();
	Label lblEstadoInicial = new Label();
	Label lblEsModificable = new Label();

	TextField tfNombre = new TextField();

	TextArea taDescripcion = new TextArea();

	Checkbox cbUsadoEnContratacion = new Checkbox();
	Checkbox cbEstadoInicial = new Checkbox();
	Checkbox cbEsModificable = new Checkbox();
	Checkbox cbEstadoFinal = new Checkbox();
	Checkbox cbUsable = new Checkbox();

	public Checkbox getCbUsable() {
		return cbUsable;
	}

	public void setCbUsable(Checkbox cbUsable) {
		this.cbUsable = cbUsable;
	}

	public Checkbox getCbEstadoFinal() {
		return cbEstadoFinal;
	}

	public void setCbEstadoFinal(Checkbox cbEstadoFinal) {
		this.cbEstadoFinal = cbEstadoFinal;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public Label getLblEsModificable() {
		return lblEsModificable;
	}

	public void setLblEsModificable(Label lblEsModificable) {
		this.lblEsModificable = lblEsModificable;
	}

	public Label getLblEstadoFinal() {
		return lblEstadoFinal;
	}

	public void setLblEstadoFinal(Label lblEstadoFinal) {
		this.lblEstadoFinal = lblEstadoFinal;
	}

	public Label getLblEstadoInicial() {
		return lblEstadoInicial;
	}

	public void setLblEstadoInicial(Label lblEstadoInicial) {
		this.lblEstadoInicial = lblEstadoInicial;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblUsadoEnContratacion() {
		return lblUsadoEnContratacion;
	}

	public void setLblUsadoEnContratacion(Label lblUsadoEnContratacion) {
		this.lblUsadoEnContratacion = lblUsadoEnContratacion;
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

	public Checkbox getCbEsModificable() {
		return cbEsModificable;
	}

	public void setCbEsModificable(Checkbox cbEsModificable) {
		this.cbEsModificable = cbEsModificable;
	}

	public Checkbox getCbEstadoInicial() {
		return cbEstadoInicial;
	}

	public void setCbEstadoInicial(Checkbox cbEstadoInicial) {
		this.cbEstadoInicial = cbEstadoInicial;
	}

	public Checkbox getCbUsadoEnContratacion() {
		return cbUsadoEnContratacion;
	}

	public void setCbUsadoEnContratacion(Checkbox cbUsadoEnContratacion) {
		this.cbUsadoEnContratacion = cbUsadoEnContratacion;
	}

	protected PanelGroup gpFinalizarComo = new PanelGroup();

	public PanelGroup getGpFinalizarComo() {
		return gpFinalizarComo;
	}

	public void setGpFinalizarComo(PanelGroup gpFinalizarComo) {
		this.gpFinalizarComo = gpFinalizarComo;
	}

	protected List getListaDelCommunication() {
		return null;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		EstadoSolicitudSuministro estadoSolicitudSuministro = this.obtenerObjetoDelElementoPila(0,
				EstadoSolicitudSuministro.class);

		estadoSolicitudSuministro.setNombre(this.getTextFieldValue(getTfNombre()));
		estadoSolicitudSuministro.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
		estadoSolicitudSuministro.setEstadoFinal(getCbEstadoFinal().isChecked());
		estadoSolicitudSuministro.setUsadoEnContratacion(getCbUsadoEnContratacion().isChecked());
		estadoSolicitudSuministro.setEstadoInicial(getCbEstadoInicial().isChecked());
		estadoSolicitudSuministro.setModificable(getCbEsModificable().isChecked());
		estadoSolicitudSuministro.setUsadoEnMovimientos(getCbUsable().isChecked());

		this.getElementoPila().getObjetos().set(0, estadoSolicitudSuministro);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		EstadoSolicitudSuministro estadoSolicitudSuministro = this.obtenerObjetoDelElementoPila(ind++,
				EstadoSolicitudSuministro.class);

		if (estadoSolicitudSuministro != null && estadoSolicitudSuministro.getIdEstadoSolicitudSuministro() != -1) {
			this.getTaDescripcion().setText(estadoSolicitudSuministro.getDescripcion());
			this.getTfNombre().setText(estadoSolicitudSuministro.getNombre());

			this.getCbEstadoFinal().setSelected(estadoSolicitudSuministro.isEstadoFinal());
			this.getCbEsModificable().setSelected(estadoSolicitudSuministro.isModificable());
			this.getCbEstadoInicial().setSelected(estadoSolicitudSuministro.isEstadoInicial());
			this.getCbUsadoEnContratacion().setSelected(estadoSolicitudSuministro.isUsadoEnContratacion());
			this.getCbUsable().setSelected(estadoSolicitudSuministro.isUsadoEnMovimientos());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMEstadoSolicitudSuministro";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new EstadoSolicitudSuministro()); // 0

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
		if (pObject instanceof EstadoSolicitudSuministro) {
			EstadoSolicitudSuministro estadoSolicitudSuministro = (EstadoSolicitudSuministro) pObject;
			this.getElementoPila().getObjetos().set(0, estadoSolicitudSuministro);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return EstadoSolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		EstadoSolicitudSuministro locEstadoSolicitudSuministro = this.obtenerObjetoDelElementoPila(0, EstadoSolicitudSuministro.class);
		this.getTablaLogs().getLdpLogs().setList(locEstadoSolicitudSuministro.getListaLogsAuditoria());
	}
}