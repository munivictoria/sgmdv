package com.trascender.contabilidad.gui.abmOrdenPago.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class EliminarOrdenPagoView extends ABMOrdenPagoView {

	private static final long serialVersionUID = -3124588390570580376L;

	public EliminarOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}

	public EliminarOrdenPagoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setImagenCabecera();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_ELIMINAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("EliminarOrdenPago.descripcion"));
		
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnEliminarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("Application.btnEliminarTextoMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnEliminarTextoToolTip"));
	}

	
	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("EliminarOrdenPago.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_ELIMINAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
}
