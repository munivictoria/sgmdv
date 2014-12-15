package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.abm;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaOrdenCompra.AsociacionCuentaLineaOrdenCompraABMModel;
import com.trascender.contabilidad.gui.abmLineaOrdenCompra.LineaOrdenCompraTableModel;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.util.Conversor;

public abstract class ABMAsociacionCuentaLineaOrdenCompra extends ABMController<LineaOrdenCompra> {

	public abstract ABMAsociacionCuentaLineaOrdenCompraView getView();
	public abstract AsociacionCuentaLineaOrdenCompraABMModel getAbmModel();
	public abstract LineaOrdenCompraTableModel getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.setCommonProperties();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	private void setListeners() {
		
	}
	
	private void setCommonProperties() {
		this.getView().getPnlBtnTabla().getBtnModificar().setVisible(false);
		this.getView().getPnlBtnTabla().getBtnEliminar().setVisible(false);
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().setVisible(false);
	}
	
	@Override
	protected void actualizarABMModel() {
		this.getAbmModel().setListaLineaOrdenCompra(this.getTableModel().getListaObjetos());
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfOrdenCompra().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM()));
		this.getTableModel().addRows(this.getAbmModel().getListaLineaOrdenCompra());
		//setListaObjetos(this.getAbmModel().getListaLineaOrdenCompra());
		
	}
	
	public LineaOrdenCompra getSelectedRow() {
		LineaOrdenCompra objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModel().getRow(selectedRow);
			}
		}
		return objetoSeleccionado; 
	}

}
