package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarReporteContableView extends ABMReporteContableView{

	private static final long serialVersionUID = -4827381766348845752L;
	
	public ConsultarReporteContableView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ConsultarReporteContableView(JFrame owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarReporteContable.descripcion"));
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
		String locTitulo = MessagesContabilidad.getString("ConsultarReporteContable.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
}
