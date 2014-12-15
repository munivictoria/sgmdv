/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMCodigoCiiu;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author ubuntero
 */
public class ABMCodigoCiiu extends ABMPageBean {

	protected Label lblCodigo = new Label();
	protected Label lblDescripcion = new Label();
	protected TextField tfCodigo = new TextField();
	protected TextField tfDescripcion = new TextField();
	protected ObjectListDataProvider ldpCodigosCiiu = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCodigosCiiu() {
		return ldpCodigosCiiu;
	}

	public void setLdpCodigosCiiu(ObjectListDataProvider ldpCodigosCiiu) {
		this.ldpCodigosCiiu = ldpCodigosCiiu;
	}

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public TextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(TextField tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		CodigoCiiu codigoCiiu = (CodigoCiiu) this.obtenerObjetoDelElementoPila(0, CodigoCiiu.class);

		codigoCiiu.setCodigo(this.getTextFieldValue(getTfCodigo()));
		codigoCiiu.setDescripcion(this.getTextFieldValue(getTfDescripcion()));

		this.getElementoPila().getObjetos().set(0, codigoCiiu);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		CodigoCiiu codigoCiiu = (CodigoCiiu) this.obtenerObjetoDelElementoPila(ind++, CodigoCiiu.class);

		this.getTfCodigo().setText(codigoCiiu.getCodigo());
		this.getTfDescripcion().setText(codigoCiiu.getDescripcion());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCodigoCiiu";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new CodigoCiiu());

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof CodigoCiiu) {
			if(pObject != null) {
				CodigoCiiu codigoCiiu = (CodigoCiiu) pObject;
				this.getElementoPila().getObjetos().set(0, codigoCiiu);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		CodigoCiiu codigoCiiu = null;
		try {
			codigoCiiu = (CodigoCiiu) pObject;
		} catch(Exception e) {
			log(this.getCasoNavegacion() + "_Guardar Error:", e);
			e.printStackTrace();
		}
		this.getElementoPila().getObjetos().set(0, codigoCiiu);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMCodigoCiiu$ABMCodigoCiiu}";
	}

	@Override
	public long getSerialVersionUID() {
		return CodigoCiiu.serialVersionUID;
	}
}