package com.trascender.caja.gui.cobro;

import javax.swing.JDialog;

import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

public class DetalleCobroRegistroDeuda extends ABMController<RegistroDeuda>{

	private final DetalleCobroRegistroDeudaABMModel model;
	private final DetalleCobroView view;

	public  DetalleCobroRegistroDeuda(JDialog owner) throws Exception{
		this.view = new DetalleCobroView(owner);
		this.model = new DetalleCobroRegistroDeudaABMModel();
		this.init();

	}

	/**
	 * Inicializa el modelo, los eventos y la vista 
	 */
	@Override
	protected void init() {
		super.init();
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.setEditable(false);
	}

	protected void setEditable(Boolean bandera) {
		this.getView().getTfCodigoBarras().setEditable(bandera);
		this.getView().getTfConcepto().setEditable(bandera);
		this.getView().getTfFechaVencimiento().setEditable(bandera);
		this.getView().getTfTasa().setEditable(bandera);
		this.getView().getTfEstado().setEditable(bandera);
		this.getView().getTfMontoExento().setEditable(bandera);
		this.getView().getTfInteres().setEditable(bandera);
		this.getView().getTfMulta().setEditable(bandera);
		this.getView().getTfRecargo().setEditable(bandera);
		this.getView().getTfTotal().setEditable(bandera);
	}

	@Override
	public DetalleCobroView getView() {
		return view;
	}

	@Override
	protected DetalleCobroRegistroDeudaABMModel getAbmModel() {
		return this.model;
	}

	@Override
	protected void actualizarABMModel() {

	}

	@Override
	protected void actualizarView() {
		System.out.println("****************************");
		System.out.println("getFechaCancelacion--> " + this.getAbmModel().getObjetoABM().getFechaCancelacion());
		System.out.println("getIdRegistroDeuda--> " + this.getAbmModel().getObjetoABM().getIdRegistroDeuda());
		System.out.println("getNombre--> " + this.getAbmModel().getObjetoABM().getNombre());
		System.out.println("getStringPeriodoLiquidado--> " + this.getAbmModel().getObjetoABM().getStringPeriodoLiquidado());
		System.out.println("getStringPeriodoLiquidadogetDocGeneradorDeuda--> " + this.getAbmModel().getObjetoABM().getDocGeneradorDeuda());
		System.out.println("getEstado--> " + this.getAbmModel().getObjetoABM().getEstado());
		System.out.println("getEstadoAnterior-->" + this.getAbmModel().getObjetoABM().getEstadoAnterior());
		System.out.println("getFechaEmision-->" + this.getAbmModel().getObjetoABM().getFechaEmision());
		System.out.println("getFechaVencimiento-->" + this.getAbmModel().getObjetoABM().getFechaVencimiento());
		System.out.println("getInteres-->" + this.getAbmModel().getObjetoABM().getInteres());
		System.out.println("getMonto-->" + this.getAbmModel().getObjetoABM().getMonto());
		System.out.println("getMontoExencion-->" + this.getAbmModel().getObjetoABM().getMontoExencion());
		System.out.println("getMultas-->" + this.getAbmModel().getObjetoABM().getMultas());
		System.out.println("getNumeroRegistroDeuda-->" + this.getAbmModel().getObjetoABM().getNumeroRegistroDeuda());
		//		System.out.println("getPeriodo-->" + this.getAbmModel().getObjetoABM().getPeriodo());
		//		System.out.println("getPersona-->" + this.getAbmModel().getObjetoABM().getPersona());
		System.out.println("getRecargo-->" + this.getAbmModel().getObjetoABM().getRecargo());
		System.out.println("getRegistroCancelacion-->" + this.getAbmModel().getObjetoABM().getRegistroCancelacion());
		System.out.println("getRegistroExencionRegistroDeuda--> " + this.getAbmModel().getObjetoABM().getRegistroExencionRegistroDeuda());
		System.out.println("getTipoDeuda--> " + this.getAbmModel().getObjetoABM().getTipoDeuda());
		System.out.println("-->" + this.getAbmModel().getObjetoABM().getFechaCancelacion());


		this.getView().getTfCodigoBarras().setText(this.getAbmModel().getObjetoABM().getId().toString());
		this.getView().getTfConcepto().setText(this.getAbmModel().getObjetoABM().getNombre().toString());
		this.getView().getTfFechaVencimiento().setValue(Conversor.getString(this.getAbmModel().getObjetoABM().getFechaVencimiento()));

		if (this.getAbmModel().getObjetoABM() instanceof LiquidacionTasa){
			this.getView().getTfTasa().setValue(((LiquidacionTasa)this.getAbmModel().getObjetoABM()).getValorTotal());
		}else this.getView().getTfTasa().setValue(new Double(0D));

		this.getView().getTfEstado().setText(this.getAbmModel().getObjetoABM().getEstado().toString());
		this.getView().getTfMontoExento().setValue(this.getAbmModel().getObjetoABM().getMontoExencion());
		this.getView().getTfInteres().setValue(this.getAbmModel().getObjetoABM().getInteres());
		this.getView().getTfMulta().setValue(this.getAbmModel().getObjetoABM().getMultas());
		this.getView().getTfRecargo().setValue(this.getAbmModel().getObjetoABM().getRecargo());
		this.getView().getTfTotal().setValue(this.getAbmModel().getObjetoABM().getMonto());
	}


}
