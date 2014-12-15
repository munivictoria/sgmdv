package com.trascender.contabilidad.gui.abmBanco.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;

public class EliminarBancoView extends ABMBancoView {

	public EliminarBancoView(JDialog owner) {
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

	private static final long serialVersionUID = 5513255615940373091L;

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_ELIMINAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("EliminarBanco.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(Messages.getString("Application.btnEliminarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(Messages.getString("Application.btnEliminarTextoMnemonic").charAt(0));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("EliminarBanco.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_ELIMINAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}
