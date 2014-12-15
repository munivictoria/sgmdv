package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarTipoCuentaView extends ABMTipoCuentaView {
	
	private static final long serialVersionUID = 4276342570537995172L;
	
	public ConsultarTipoCuentaView (JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public ConsultarTipoCuentaView (JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	private void init() {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnCancelar();
		this.setTituloVentana();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarTipoCuenta.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
	}
	
	public void setTextoBtnCancelar() {
	    this.getPnlPie().getBtnCancelar().setText(
					MessagesContabilidad.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCancelar().setMnemonic(
					MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCancelar().setToolTipText(
					MessagesContabilidad.getString("Application.btnCerrarToolTip"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString("ConsultarTipoCuenta.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	
	public void setImagenCabecera() {
	}

}
