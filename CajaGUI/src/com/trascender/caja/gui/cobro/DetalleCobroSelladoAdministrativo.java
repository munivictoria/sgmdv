package com.trascender.caja.gui.cobro;

import javax.swing.JDialog;

import com.trascender.contabilidad.recurso.persistent.SelladoAdministrativo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.util.Conversor;

public class DetalleCobroSelladoAdministrativo extends ABMController<SelladoAdministrativo> {

	private DetalleCobroSelladoAdminABMModel model;
	private DetalleCobroSelladoAdministrativoView view;
	
	public DetalleCobroSelladoAdministrativo(JDialog owner)throws Exception {
		this.view = new DetalleCobroSelladoAdministrativoView(owner);
		this.model = new DetalleCobroSelladoAdminABMModel();
		this.init();
	}
	
	protected void init() {
		super.init();
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.setEditable(false);
	}
	
	protected void setEditable(Boolean bandera) {
		this.getView().getTfCodigoBarras().setEditable(bandera);
		this.getView().getTfConcepto().setEditable(bandera);
		this.getView().getTfFechaEmision().setEditable(bandera);
		this.getView().getTfEstado().setEditable(bandera);
		this.getView().getTfMonto().setEditable(bandera);
		this.getView().getTaObservaciones().setEditable(bandera);
	}

	
	@Override
	protected void actualizarABMModel() {
		
	}

	@Override
	protected void actualizarView() {
		System.out.println("****************************");
		System.out.println("getIdSelladoAdministrativo--> " + this.getAbmModel().getObjetoABM().getIdSelladoAdministrativo());
		System.out.println("getNombre--> " + this.getAbmModel().getObjetoABM().getNombre());
		System.out.println("getObservaciones--> " + this.getAbmModel().getObjetoABM().getObservaciones());
		System.out.println("getConceptoSelladoAdministrativo--> " + this.getAbmModel().getObjetoABM().getConceptoSelladoAdministrativo());
		System.out.println("getEstado--> " + this.getAbmModel().getObjetoABM().getEstado());
		System.out.println("getFechaEmision--> " + this.getAbmModel().getObjetoABM().getFechaEmision());
		System.out.println("getMonto--> " + this.getAbmModel().getObjetoABM().getMonto());
		System.out.println("getNumero--> " + this.getAbmModel().getObjetoABM().getNumero());
		System.out.println("getRegistroCancelacion--> " + this.getAbmModel().getObjetoABM().getRegistroCancelacion());
		
		this.getView().getTfCodigoBarras().setText(this.getAbmModel().getObjetoABM().getId().toString());
		this.getView().getTfConcepto().setText(this.getAbmModel().getObjetoABM().getNombre().toString());
		this.getView().getTfFechaEmision().setValue(Conversor.getString(this.getAbmModel().getObjetoABM().getFechaEmision()));
		this.getView().getTfEstado().setText(this.getAbmModel().getObjetoABM().getEstado().toString());
		this.getView().getTfMonto().setValue(this.getAbmModel().getObjetoABM().getMonto());
		this.getView().getTaObservaciones().setText(this.getAbmModel().getObjetoABM().getObservaciones());
	}

	@Override
	protected DetalleCobroSelladoAdminABMModel getAbmModel() {
		return this.model;
	}

	@Override
	protected DetalleCobroSelladoAdministrativoView getView() {
		return this.view;
	}

}
