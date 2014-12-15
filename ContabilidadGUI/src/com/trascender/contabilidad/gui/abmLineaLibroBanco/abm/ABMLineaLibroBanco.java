package com.trascender.contabilidad.gui.abmLineaLibroBanco.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmLineaLibroBanco.LineaLibroBancoABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco.Tipo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMLineaLibroBanco extends ABMController<LineaLibroBanco> {
	
	public abstract LineaLibroBancoABMModel getAbmModel();
	public abstract ABMLineaLibroBancoView getView();
	
	public ABMLineaLibroBanco(){
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(LineaLibroBanco.Tipo.values()));
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setFechaGeneracion(Conversor.getDate(this.getView().getTfFechaGeneracion().getText()));
		this.getAbmModel().setImporte(Conversor.getDouble(this.getView().getTfImporte().getValue()));
		
		this.getAbmModel().setConciliado(this.getView().getChkConciliado().isSelected());
		this.getAbmModel().setObservaciones(this.getView().getTaObservaciones().getText());
		
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) this.getAbmModel().setTipo((Tipo) locTipo);
		else this.getAbmModel().setTipo(null);
		
		this.getAbmModel().setObservaciones(Conversor.getNullSiVacio(this.getView().getTaObservaciones().getText()));
		
		this.getAbmModel().fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfFechaGeneracion().setValue(Conversor.getString(this.getAbmModel().getFechaGeneracion()));
		this.getView().getTfImporte().setValue(this.getAbmModel().getImporte());
		this.getView().getChkConciliado().setSelected(this.getAbmModel().isConciliado());
		this.getView().getCbTipo().setSelectedItem(this.getAbmModel().getTipo());
		this.getView().getTaObservaciones().setText(Conversor.getVacioSiNull(this.getAbmModel().getObservaciones()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		List<Float> attNumero = new ArrayList<Float>();
		List<JLabel> lblNumero = new ArrayList<JLabel>();
		
		attFecha.add(this.getView().getTfFechaGeneracion().getText());
		lblFecha.add(this.getView().getLblFechaGeneracion());
		
		attNulos.add(this.getView().getTfFechaGeneracion().getText());
		lblNulos.add(this.getView().getLblFechaGeneracion());
		
		attNulos.add(this.getView().getTfImporte().getValue());
		lblNulos.add(this.getView().getLblImporte());
		
		attNulos.add(this.getView().getCbTipo().getSelectedItem());
		lblNulos.add(this.getView().getLblTipo());
		
		attNumero.add(Conversor.getFloat(this.getView().getTfImporte().getValue().toString()));
		lblNumero.add(this.getView().getLblImporte());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarSiEsCero(attNumero, lblNumero));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaGeneracion().getText(), this.getView().getLblFechaGeneracion()));
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
