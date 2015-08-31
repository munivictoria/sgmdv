/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMExpediente;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMExtension extends ABMPageBean {

	Label lblnombre = new Label();
	Label lblCantidadDias = new Label();
	Label lblComentario = new Label();

	TextField tfCantidadDias = new TextField();
	TextArea taComentario = new TextArea();

	public Label getLblnombre() {
		return lblnombre;
	}

	public void setLblnombre(Label lblnombre) {
		this.lblnombre = lblnombre;
	}

	public Label getLblCantidadDias() {
		return lblCantidadDias;
	}

	public void setLblCantidadDias(Label lblCantidadDias) {
		this.lblCantidadDias = lblCantidadDias;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public TextField getTfCantidadDias() {
		return tfCantidadDias;
	}

	public void setTfCantidadDias(TextField tfCantidadDias) {
		this.tfCantidadDias = tfCantidadDias;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, null); // NODO EXPEDIENTE
		ep.getObjetos().add(ind++, null); // DIAS
		ep.getObjetos().add(ind++, null); // COMENTARIO

		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		NodoExpediente locNodoExpediente = (NodoExpediente) this.obtenerObjetoDelElementoPila(1);
		Integer dias = this.getTextFieldValueInteger(this.getTfCantidadDias());
		String comentario = this.getTextAreaValue(this.getTaComentario());

		this.getElementoPila().getObjetos().set(1, locNodoExpediente);
		this.getElementoPila().getObjetos().set(2, dias);
		this.getElementoPila().getObjetos().set(3, comentario);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		NodoExpediente locNodoExpediente = (NodoExpediente) this.obtenerObjetoDelElementoPila(1);
		if(locNodoExpediente != null) {
			this.getLblnombre().setText(locNodoExpediente.toString());
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		NodoExpediente nodoExpediente = (NodoExpediente) pObject;
		this.getElementoPila().getObjetos().set(1, nodoExpediente);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMExtension";
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMExtension$ABMExtension}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
	
}