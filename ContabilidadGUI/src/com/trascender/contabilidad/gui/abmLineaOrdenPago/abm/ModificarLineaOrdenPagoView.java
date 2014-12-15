package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class ModificarLineaOrdenPagoView extends ABMLineaOrdenPagoView {

	
	private static final long serialVersionUID = 5513255615940373091L;

	public ModificarLineaOrdenPagoView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ModificarLineaOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.setImagenCabecera();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setColorFondo();
		this.setTextoBtnAceptar();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_MODIFICAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ModificarLineaOrdenPago.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString("ModificarLineaOrdenPago.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}
