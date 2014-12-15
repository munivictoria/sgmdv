package com.trascender.caja.gui.cobro.abm.pagoTicketCheque;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.cobro.PagoTicketCompensacionABMModel;
import com.trascender.caja.gui.cobro.PagoTicketChequeABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCompensacion;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMPagoTicketCheque extends ABMController<PagoTicketCheque>{
	
	public abstract ABMPagoTicketChequeView getView();
	public abstract PagoTicketChequeABMModel getABMModel();
	
	@Override
	protected void init() {
		super.init();
		this.getView().setAbmModel(getABMModel());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfMonto().getText());
		lblNulos.add(this.getView().getLblMonto());
		
		attNulos.add(this.getView().getTfNumero().getText());
		lblNulos.add(this.getView().getLblNumero());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFechas, lblFechas));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}

	@Override
	protected void actualizarABMModel() {
		PagoTicketChequeABMModel model = this.getABMModel();
		
		model.setComentario(Conversor.getNullSiVacio(this.getView().getTfComentario().getText()));
		model.setMonto(Conversor.getDouble(this.getView().getTfMonto().getText()));
		model.setNumero(Conversor.getNullSiVacio(this.getView().getTfNumero().getText()));
		
		model.fireActualizarDatos();
	}

	@Override
	protected void actualizarView() {
		PagoTicketChequeABMModel model = this.getABMModel();
		this.getView().getTfComentario().setText(Conversor.getVacioSiNull(model.getComentario()));
		this.getView().getTfMonto().setText(Conversor.getVacioSiNull(model.getMonto()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(model.getNumero()));
	}


}
