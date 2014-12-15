package com.trascender.caja.gui.cobro.abm.pagoTicketEfectivo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.cobro.PagoTicketEfectivoABMModel;
import com.trascender.contabilidad.recurso.persistent.PagoTicketEfectivo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMPagoTicketEfectivo extends ABMController<PagoTicketEfectivo>{
	
	public abstract ABMPagoTicketEfectivoView getView();
	public abstract PagoTicketEfectivoABMModel getABMModel();
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFechas = new ArrayList<String>();
		List<JLabel> lblFechas = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfMonto().getText());
		lblNulos.add(this.getView().getLblMonto());
		
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
	protected void init() {
		super.init();
		this.getView().setAbmModel(getABMModel());
	}

	@Override
	protected void actualizarABMModel() {
		PagoTicketEfectivoABMModel model = this.getABMModel();
		
		model.setComentario(Conversor.getNullSiVacio(this.getView().getTfComentario().getText()));
		model.setMonto(Conversor.getDouble(this.getView().getTfMonto().getText()));
		
		model.fireActualizarDatos();
	}

	@Override
	protected void actualizarView() {
		PagoTicketEfectivoABMModel model = this.getABMModel();
		this.getView().getTfComentario().setText(Conversor.getVacioSiNull(model.getComentario()));
		this.getView().getTfMonto().setText(Conversor.getVacioSiNull(model.getMonto()));
	}


}
