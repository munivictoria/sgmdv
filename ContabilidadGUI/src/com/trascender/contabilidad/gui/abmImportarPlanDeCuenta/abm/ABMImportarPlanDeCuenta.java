package com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.ImportarPlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar.PlanDeCuentaTableModelConsultar;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMImportarPlanDeCuenta extends ABMController<PlanDeCuenta> {
	
	public abstract ABMImportarPlanDeCuentaView getView();
	public abstract ImportarPlanDeCuentaABMModel getAbmModel();
	public abstract PlanDeCuentaTableModelConsultar getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	@Override
	public void actualizarABMModel() {
		this.getAbmModel().setDescripcion(Conversor.getNullSiVacio(this.getView().getTfDescripcion().getText()));		
		this.getAbmModel().setFechaAlta(Conversor.getDate(this.getView().getTfFechaAlta().getText()));

		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	protected void actualizarView() {
		this.getView().getTfFechaAlta().setValue(Conversor.getString(this.getAbmModel().getFechaAlta()));
		this.getView().getTfDescripcion().setText(Conversor.getVacioSiNull(this.getAbmModel().getDescripcion()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfDescripcion().getText());
		lblNulos.add(this.getView().getLblDescripcion());
		
		attNulos.add(this.getView().getTfFechaAlta().getText());
		lblNulos.add(this.getView().getLblFechaAlta());
		
		attFechas.add(this.getView().getTfFechaAlta().getText());
		lblFechas.add(this.getView().getLblFechaAlta());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
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
