package com.trascender.caja.gui.cobro;

import com.trascender.contabilidad.recurso.persistent.PagoTicketCompensacion;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class PagoTicketCompensacionABMModel extends TAbstractABMModel<PagoTicketCompensacion> {

	@Override
	public void agregar() throws Exception {
	}

	@Override
	public void modificar() throws Exception {
	}

	@Override
	public void eliminar() throws Exception {
	}
	
	public void setMonto(Double pMonto){
		this.objetoABM.setMonto(pMonto);
	}
	
	public Double getMonto(){
		return this.objetoABM.getMonto();
	}
	
	public void setComentario(String pComentario){
		this.objetoABM.setComentario(pComentario);
	}
	
	public String getComentario(){
		return this.objetoABM.getComentario();
	}

}
