package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;

public class ModificarPlanDeCuentaView extends ABMPlanDeCuentaView {

	private static final long serialVersionUID = 323863729334937865L;

	public ModificarPlanDeCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ModificarPlanDeCuentaView(JFrame owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ModificarPlanDeCuenta.descripcion"));		
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(Messages.getString("Application.btnModificarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(Messages.getString("Application.btnModificarTextoMnemonic").charAt(0));		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString("ModificarPlanDeCuenta.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

}
