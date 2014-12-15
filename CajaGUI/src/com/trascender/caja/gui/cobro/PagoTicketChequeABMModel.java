package com.trascender.caja.gui.cobro;

import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class PagoTicketChequeABMModel extends TAbstractABMModel<PagoTicketCheque> {

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
	
	public void setNumero(String pNumero){
		this.objetoABM.setNumero(pNumero);
	}
	
	public String getNumero(){
		return this.objetoABM.getNumero();
	}

}
