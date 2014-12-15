package com.trascender.contabilidad.gui.abmRetencion.abm;

import java.awt.Color;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class EliminarRetencionView extends ABMRetencionView {

	private static final long serialVersionUID = 6093617126006507097L;

	public EliminarRetencionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_ELIMINAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("EliminarRetencion.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
//		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnEliminar"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("Application.btnEliminarMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnEliminarToolTip"));
	}
	
	public void setTextoBtnCerrar() {
//    	this.getPnlPie().getBtnCancelar().setText(
//				MessagesContabilidad.getString("Application.btnCerrar"));
//		this.getPnlPie().getBtnCancelar().setMnemonic(
//				MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
//		this.getPnlPie().getBtnCancelar().setToolTipText(
//				MessagesContabilidad.getString("Application.btnCerrarToolTip"));
    }

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("EliminarRetencion.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

}
