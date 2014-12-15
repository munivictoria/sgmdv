package com.trascender.contabilidad.gui.abmBalance.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarBalanceView extends ABMBalanceView {

	private static final long serialVersionUID = 2093508176966711493L;
	
	public ConsultarBalanceView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ConsultarBalanceView(JFrame owner) {
		super(owner);
		this.init();
	}

	private void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarBalance.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
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
		String locTitulo = MessagesContabilidad.getString("ConsultarBalance.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
}
