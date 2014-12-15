package com.trascender.contabilidad.gui.abmLineaLibroBanco.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class ModificarLineaLibroBancoView extends ABMLineaLibroBancoView {

	private static final long serialVersionUID = 3506710689281570249L;

	public ModificarLineaLibroBancoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTituloVentana();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_MODIFICAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ModificarLineaSubdiarioCaja.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString("ModificarLineaLibroBanco.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}
