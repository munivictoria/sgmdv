package com.trascender.caja.gui.abmMoneda.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.caja.gui.recursos.RecursosCaja;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class EliminarMonedaView extends ABMMonedaView {

	private static final long serialVersionUID = 8240803090245934424L;
	
	public EliminarMonedaView(JDialog owner) {
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
		Color color = Constantes.COLOR_ELIMINAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString("EliminarMoneda.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesCaja.getString("Application.btnEliminarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesCaja.getString("Application.btnEliminarTextoMnemonic").charAt(0));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString("EliminarMoneda.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosCaja.IMG_CAB_ELIMINAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}



