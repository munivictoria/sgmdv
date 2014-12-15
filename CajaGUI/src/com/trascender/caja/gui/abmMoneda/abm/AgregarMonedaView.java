package com.trascender.caja.gui.abmMoneda.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.caja.gui.recursos.RecursosCaja;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class AgregarMonedaView extends ABMMonedaView {

	private static final long serialVersionUID = 4122667310564871874L;
	
	public AgregarMonedaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AgregarMonedaView(JFrame owner) {
		super(owner);
		this.init();
	}

	private void init() {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
	}

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlPie().setBackground(color);
		this.getPnlCabecera().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString("AgregarMoneda.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesCaja.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesCaja.getChar(("Application.btnAgregarTextoMnemonic")));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesCaja.getString("Application.btnAgregarTextoToolTip"));		
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString("AgregarMoneda.titulo");
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
		this.setTitle(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosCaja.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

}