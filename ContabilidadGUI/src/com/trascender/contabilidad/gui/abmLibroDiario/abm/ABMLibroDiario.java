package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.util.Conversor;

public abstract class ABMLibroDiario extends ABMController<LibroDiario> {
	
	public abstract ABMLibroDiarioView getView();
	public abstract LibroDiarioABMModel getAbmModel(); 
	
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
		this.getAbmModel().setCodigoAutorizacion(Conversor.getNullSiVacio(this.getView().getTfCodigoAutorizacion().getText()));
		this.getAbmModel().setNumero(Conversor.getNullSiVacio(this.getView().getTfNumero().getText()));
		this.getAbmModel().setCantidadFolios(Conversor.getInteger(this.getView().getTfCantidadFolios().getText()));
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfCodigoAutorizacion().setText(Conversor.getVacioSiNull(this.getAbmModel().getCodigoAutorizacion()));
		this.getView().getTfNumero().setText(Conversor.getVacioSiNull(this.getAbmModel().getNumero()));
		this.getView().getTfCantidadFolios().setText(this.getAbmModel().getCantidadFolios().toString());
	}

}
