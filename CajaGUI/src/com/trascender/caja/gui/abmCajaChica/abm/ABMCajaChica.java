package com.trascender.caja.gui.abmCajaChica.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.abmCajaChica.CajaChicaABMModel;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMCajaChica extends ABMController<CajaChica> {

	public abstract ABMCajaChicaView getView();
	public abstract CajaChicaABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	@Override
	public void actualizarABMModel() {
		CajaChicaABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setImporteReposicion(Conversor.getDouble(this.getView().getTfImporteReposicion().getValue()));
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfImporteReposicion().setValue(this.getAbmModel().getImporteReposicion());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfImporteReposicion().getValue());
		lblNulos.add(this.getView().getLblImporteReposicion());
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attDecimales.add(this.getView().getTfImporteReposicion().getValue());
		lblDecimales.add(this.getView().getLblImporteReposicion());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
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
