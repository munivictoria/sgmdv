package com.trascender.saic.recurso.transients.pagoFacil;

import java.util.Date;

public class RegistroDetallePF {

	
	private DetalleRegistroPF detalleTransaccion = new DetalleRegistroPF() ;
	private CodigoBarraTransaccionPF codigoBarrasTransaccion = new CodigoBarraTransaccionPF();
	private DetalleInstrumentoPF detalleInstrumento = new DetalleInstrumentoPF();
	
	public DetalleRegistroPF getDetalleTransaccion() {
		return detalleTransaccion;
	}
	public void setDetalleTransaccion(DetalleRegistroPF detalleTransaccion) {
		this.detalleTransaccion = detalleTransaccion;
	}
	public CodigoBarraTransaccionPF getCodigoBarrasTransaccion() {
		return codigoBarrasTransaccion;
	}
	public void setCodigoBarrasTransaccion(
			CodigoBarraTransaccionPF codigoBarrasTransaccion) {
		this.codigoBarrasTransaccion = codigoBarrasTransaccion;
	}
	public DetalleInstrumentoPF getDetalleInstrumento() {
		return detalleInstrumento;
	}
	public void setDetalleInstrumento(DetalleInstrumentoPF detalleInstrumento) {
		this.detalleInstrumento = detalleInstrumento;
	}
	
	
	
	
	
	

}
