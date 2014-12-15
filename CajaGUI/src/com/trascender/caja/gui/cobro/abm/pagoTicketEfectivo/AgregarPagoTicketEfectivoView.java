package com.trascender.caja.gui.cobro.abm.pagoTicketEfectivo;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AgregarPagoTicketEfectivoView extends ABMPagoTicketEfectivoView{

	public AgregarPagoTicketEfectivoView(JDialog owner) {
		super(owner);
	}
	
	public AgregarPagoTicketEfectivoView(JFrame owner) {
		super(owner);
	}

	@Override
	public void setTituloVentana() {
//		String locTitulo = MessagesCaja.getString("AgregarAsientoContable.titulo");
		String locTitulo = "Agregar Pago Ticket Efectivo";
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		
	}

}
