package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.Color;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarChequeView extends ABMChequeView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5932738900013745549L;

	public ConsultarChequeView(JDialog owner) {
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
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
		
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarCheque.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ConsultarCheque.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
	}
}
