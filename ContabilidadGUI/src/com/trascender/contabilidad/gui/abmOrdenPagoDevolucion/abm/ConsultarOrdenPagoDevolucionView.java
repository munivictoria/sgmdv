package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarOrdenPagoDevolucionView extends ABMOrdenPagoDevolucionView {

	private static final long serialVersionUID = 5145525343152833690L;

	public ConsultarOrdenPagoDevolucionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ConsultarOrdenPagoDevolucionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTextoBtnCerrar();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarOrdenPagoDevolucion.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
//		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("ConsultarOrdenPagoDevolucion.btnAceptar"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("ConsultarOrdenPagoDevolucion.btnAceptarMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("ConsultarOrdenPagoDevolucion.btnAceptarTextoToolTip"));
	}
	
	public void setTextoBtnCerrar() {
    	this.getPnlPie().getBtnCancelar().setText(
				MessagesContabilidad.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCancelar().setMnemonic(
				MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCancelar().setToolTipText(
				MessagesContabilidad.getString("Application.btnCerrarToolTip"));
    }

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ConsultarOrdenPagoDevolucion.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

}
