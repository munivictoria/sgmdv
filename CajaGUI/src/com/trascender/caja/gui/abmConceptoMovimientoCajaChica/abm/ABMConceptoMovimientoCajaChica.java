package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.ConceptoMovimientoCajaChicaABMModel;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica.Tipo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMConceptoMovimientoCajaChica extends ABMController<ConceptoMovimientoCajaChica> {
	
	public abstract ABMConceptoMovimientoCajaChicaView getView();
	public abstract ConceptoMovimientoCajaChicaABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(Tipo.values()));
	}
	
	@Override
	public void actualizarABMModel() {
		ConceptoMovimientoCajaChicaABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setDescipcion(Conversor.getNullSiVacio(this.getView().getTfDescripcion().getText()));
		
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) locModel.setTipo((Tipo)locTipo); 
		else locModel.setTipo(null);
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfDescripcion().setText(Conversor.getVacioSiNull(this.getAbmModel().getDescipcion()));
		this.getView().getCbTipo().setSelectedItem(this.getAbmModel().getTipo());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getCbTipo().getSelectedItem());
		lblNulos.add(this.getView().getLblTipo());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
}
