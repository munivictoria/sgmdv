package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarLibroBancoView extends ABMLibroBancoView {

	private static final long serialVersionUID = -1655655294891848834L;

	public ConsultarLibroBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ConsultarLibroBancoView(JDialog owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarLibroBanco.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
//		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnImprimir"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("Application.btnImprimirMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnImprimirToolTip"));
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
		String locTitulo = MessagesContabilidad.getString("ConsultarLibroBanco.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
}
