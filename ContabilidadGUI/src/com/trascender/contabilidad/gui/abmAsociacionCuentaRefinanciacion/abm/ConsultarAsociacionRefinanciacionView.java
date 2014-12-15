package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.awt.Color;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarAsociacionRefinanciacionView extends ABMAsociacionRefinanciacionView {
	
	private static final long serialVersionUID = 8148196398332499029L;

	public ConsultarAsociacionRefinanciacionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTextoBtnCerrar();
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarAsociacionRefinanciacion.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
	}
	
	public void setTextoBtnCerrar() {
    }

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ConsultarAsociacionRefinanciacion.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
}