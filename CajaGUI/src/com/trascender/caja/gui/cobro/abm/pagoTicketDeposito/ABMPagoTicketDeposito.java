package com.trascender.caja.gui.cobro.abm.pagoTicketDeposito;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.cobro.PagoTicketDepositoABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketDeposito;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMPagoTicketDeposito extends ABMController<PagoTicketDeposito>{
	
	public abstract ABMPagoTicketDepositoView getView();
	public abstract PagoTicketDepositoABMModel getABMModel();
	
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
		PagoTicketDepositoABMModel model = this.getABMModel();
		
		model.setComentario(Conversor.getNullSiVacio(this.getView().getTfComentario().getText()));
		model.setMonto(Conversor.getDouble(this.getView().getTfMonto().getText()));
		model.setNumeroTransaccion(Conversor.getNullSiVacio(this.getView().getTfNumero().getText()));
		
		model.fireActualizarDatos();
	}

	@Override
	protected void actualizarView() {
		PagoTicketDepositoABMModel model = this.getABMModel();
		this.getView().getTfComentario().setText(Conversor.getVacioSiNull(model.getComentario()));
		this.getView().getTfMonto().setText(Conversor.getVacioSiNull(model.getMonto()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(model.getNumeroTransaccion()));
	}


}
