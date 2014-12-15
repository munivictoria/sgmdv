package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionABMModel;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMParametroRetencion extends ABMController<ParametroRetencion> {
	
	public abstract ParametroRetencionABMModel getAbmModel();
	public abstract ABMParametroRetencionView getView();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	private void setListeners() {
		
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfAlicuota().setText(Conversor.getString(this.getAbmModel().getAlicuota()));
		this.getView().getTfPorcentaje().setText(Conversor.getString(this.getAbmModel().getPorcentaje()));
		this.getView().getTfImporteMinimo().setValue(this.getAbmModel().getImporteMinimo());
		this.getView().getChDeducirIVA().setSelected(this.getAbmModel().isDeducirIVA());
	}
	
	@Override
	protected void actualizarABMModel() {
		ParametroRetencionABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setAlicuota(Conversor.getDouble(this.getView().getTfAlicuota().getText()));
		locModel.setDeducirIVA(this.getView().getChDeducirIVA().isSelected());
		locModel.setImporteMinimo(Conversor.getDouble(this.getView().getTfImporteMinimo().getValue()));
		locModel.setPorcentaje(Conversor.getDouble(this.getView().getTfPorcentaje().getText()));
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attDecimales = new ArrayList<Object>();
		List<JLabel> lblDecimales = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
		List<Object> attEnteros = new ArrayList<Object>();
		List<JLabel> lblEnteros = new ArrayList<JLabel>();
		
		attEnteros.add(this.getView().getTfAlicuota().getText());
		lblEnteros.add(this.getView().getLblAlicuota());
		
		attEnteros.add(this.getView().getTfPorcentaje().getText());
		lblEnteros.add(this.getView().getLblPorcentaje());
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfAlicuota().getText());
		lblNulos.add(this.getView().getLblAlicuota());
		
		attNulos.add(this.getView().getTfImporteMinimo().getText());
		lblNulos.add(this.getView().getLblImporteMinimo());
		
		attNulos.add(this.getView().getTfPorcentaje().getText());
		lblNulos.add(this.getView().getLblPorcentaje());
		
		attDecimales.add(this.getView().getTfImporteMinimo().getValue());
		lblDecimales.add(this.getView().getLblImporteMinimo());
		
		attPositivo.add(this.getView().getTfImporteMinimo().getValue().toString());
		lblPositivo.add(this.getView().getLblImporteMinimo());	
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarEnteros(attEnteros, lblEnteros));
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarDecimales(attDecimales, lblDecimales));
			listaErrores.addAll(Validador.validarPositivos(attPositivo, lblPositivo));
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


