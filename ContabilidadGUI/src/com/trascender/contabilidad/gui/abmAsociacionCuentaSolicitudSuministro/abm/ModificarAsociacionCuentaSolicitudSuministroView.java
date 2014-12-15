package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class ModificarAsociacionCuentaSolicitudSuministroView extends ABMAsociacionCuentaSolicitudSuministroView {

	private static final long serialVersionUID = -5376060403363046740L;
	
	private final String NOMBRE_RECURSO = "ModificarAsociacionCuentaSolicitudSuministro";
	
	public ModificarAsociacionCuentaSolicitudSuministroView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ModificarAsociacionCuentaSolicitudSuministroView(JFrame owner) {
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
		Color locColor = Constantes.COLOR_MODIFICAR;
		this.getPnlCabecera().setBackground(locColor);
		this.getPnlPie().setBackground(locColor);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnModificarTexto"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	

}
