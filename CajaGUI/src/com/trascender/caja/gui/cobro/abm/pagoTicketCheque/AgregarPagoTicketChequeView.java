package com.trascender.caja.gui.cobro.abm.pagoTicketCheque;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AgregarPagoTicketChequeView extends ABMPagoTicketChequeView{

	public AgregarPagoTicketChequeView(JDialog owner) {
		super(owner);
	}
	
	public AgregarPagoTicketChequeView(JFrame owner) {
		super(owner);
	}

	@Override
	public void setTituloVentana() {
//		String locTitulo = MessagesCaja.getString("AgregarAsientoContable.titulo");
		String locTitulo = "Agregar Pago Ticket Cheque";
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		
	}

}
