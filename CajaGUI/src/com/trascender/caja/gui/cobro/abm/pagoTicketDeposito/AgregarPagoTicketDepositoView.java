package com.trascender.caja.gui.cobro.abm.pagoTicketDeposito;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AgregarPagoTicketDepositoView extends ABMPagoTicketDepositoView{

	public AgregarPagoTicketDepositoView(JDialog owner) {
		super(owner);
	}
	
	public AgregarPagoTicketDepositoView(JFrame owner) {
		super(owner);
	}

	@Override
	public void setTituloVentana() {
//		String locTitulo = MessagesCaja.getString("AgregarAsientoContable.titulo");
		String locTitulo = "Agregar Pago Ticket Depósito";
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		
	}

}
