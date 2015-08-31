/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes;

import java.util.List;

import muni.expedientes.tables.ABM_Table;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.model.DefaultOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.presentacion.utiles.MgrDropDown;

public abstract class AbmNodoExpediente extends ABM_Table {

	private Label labelHitos = new Label();
	private Listbox lbHitos = new Listbox();
	private DefaultOptionsList lbHitosDefaultOptions = new DefaultOptionsList();

	private MgrDropDown mgrDropDown = new MgrDropDown();

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			lbHitosDefaultOptions.setOptions(getOptions(getListaDelCommunication()));
		}
	}

	public Label getLabelHitos() {
		return labelHitos;
	}

	public void setLabelHitos(Label labelHitos) {
		this.labelHitos = labelHitos;
	}

	public Listbox getLbHitos() {
		return lbHitos;
	}

	public void setLbHitos(Listbox lbHitos) {
		this.lbHitos = lbHitos;
	}

	public DefaultOptionsList getLbHitosDefaultOptions() {
		return lbHitosDefaultOptions;
	}

	public void setLbHitosDefaultOptions(DefaultOptionsList lbHitosDefaultOptions) {
		this.lbHitosDefaultOptions = lbHitosDefaultOptions;
	}

	@SuppressWarnings("rawtypes")
	private Option[] getOptions(List lista) {
		return mgrDropDown.armarArrayOptionsObjectsList(lista.toArray(), "");
	}

	protected void mostrartHitos(NodoExpediente pNodoExpediente) {
		lbHitosDefaultOptions.setOptions(getOptions(pNodoExpediente.listarHitos()));
		setListaDelCommunication(pNodoExpediente.listarHitos());
	}

	@SuppressWarnings("rawtypes")
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaHitos();
	}

	@SuppressWarnings("rawtypes")
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaHitos(lista);
	}

}