package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.Color;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarMayorView extends ABMMayorView {
	private static final long serialVersionUID = 6534282402644367951L;
	
	public ConsultarMayorView(JDialog owner) {
		super(owner);
		this.init();
	}

	private void init() {
		this.setColorFondo();
		this.setDescripcionVentana();
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarMayor.descripcion"));
	}


	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ConsultarMayor.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setTextoBtnAceptar() {
		// TODO Auto-generated method stub
		
	}
}
