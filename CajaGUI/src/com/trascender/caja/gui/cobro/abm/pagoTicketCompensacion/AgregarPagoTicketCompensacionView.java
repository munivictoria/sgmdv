package com.trascender.caja.gui.cobro.abm.pagoTicketCompensacion;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AgregarPagoTicketCompensacionView extends ABMPagoTicketCompensacionView{

	public AgregarPagoTicketCompensacionView(JDialog owner) {
		super(owner);
	}
	
	public AgregarPagoTicketCompensacionView(JFrame owner) {
		super(owner);
	}

	@Override
	public void setTituloVentana() {
//		String locTitulo = MessagesCaja.getString("AgregarAsientoContable.titulo");
		String locTitulo = "Agregar Pago Ticket Compensación";
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		
	}

}
