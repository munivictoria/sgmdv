package com.trascender.gui.framework.abmStandard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.gui.framework.recursos.Messages;

public abstract class ABMController<T> {
	
	private ABMErrores abmErrores;
	public ABMErrores getAbmErrores() {
		return this.abmErrores;
	}
	
	protected abstract ABMView getView();
	protected abstract TAbstractABMModel getAbmModel();
	
	protected boolean operacionRealizada = false;
	
	public boolean isOperacionRealizada() {
		return operacionRealizada;
	}
	public void setOperacionRealizada(boolean operacionRealizada) {
		this.operacionRealizada = operacionRealizada;
	}
	
	protected void init() {
		this.abmErrores =  new ABMErrores(this.getView());
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		
	}
	
	private void setListeners() {
		this.setBtnCancelarListener();
	}
	
	public void open() {
		this.getView().setVisible(true);
	}
	
	public void close() {
		this.getView().dispose();
	}
	
	protected abstract void actualizarABMModel();
	
	protected abstract void actualizarView();
	
	public boolean validarDatos() {
		return true;
	}
	
	public void mostrarErroresValidacion(List<String> listaErrores) {
		if (Messages.getBoolean("Application.mostrarPantallaErroresABM")) {
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
	}
	
	/**
	 * Set del listener del botón Cancelar.
	 * Sobrescribir si se quiere hacer algo diferente de un <code>dispose</code>.
	 * @param view
	 */
	public void setBtnCancelarListener() {
		this.getView().getPnlPie().getBtnCancelar().addActionListener(new BtnCancelarListener(this.getView()));
	}
	
}

/**
 * Listener por defecto de los botones Cancelar de los ABM que solo hace el <code>dispose</code> de la ventana. 
 *
 */
class BtnCancelarListener implements ActionListener {
	private ABMView view;
	public BtnCancelarListener(ABMView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		this.view.dispose();
	}
}