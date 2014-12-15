package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class AgregarLineaOrdenPagoView extends ABMLineaOrdenPagoView {

	private static final long serialVersionUID = 5513255615940373091L;

	public AgregarLineaOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}

	public AgregarLineaOrdenPagoView(JDialog owner) {
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
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AgregarLineaOrdenPago.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString("AgregarLineaOrdenPago.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}	
	
}
