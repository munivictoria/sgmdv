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

public class AgregarPlanDeCuentaView extends ABMPlanDeCuentaView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public AgregarPlanDeCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}

	public AgregarPlanDeCuentaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTituloVentana();
	}	
	
	@Override
	public void setColorFondo(){
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}
	
	@Override
	public void setDescripcionVentana(){
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AgregarPlanDeCuenta.descripcion"));
	}
	
	@Override
	public void setTextoBtnAceptar(){
		this.getPnlPie().getBtnAceptar().setText(Messages.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(Messages.getString("Application.btnAgregarTextoMnemonic").charAt(0));
	}
	
	@Override
	public void setTituloVentana(){
		String titulo = MessagesContabilidad.getString("AgregarPlanDeCuenta.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}
