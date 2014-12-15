package com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarPlanDeCuentaView extends ABMPlanDeCuentaConsultarView {
	
	private static final long serialVersionUID = -1016779314497407559L;
	
	private final String NOMBRE_RECURSO = "ConsultarPlanDeCuenta";
	
	public ConsultarPlanDeCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ConsultarPlanDeCuentaView(JFrame owner) {
		super(owner);
		this.init();
	} 
	
	private void init(){
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTituloVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}
		
	public void setImagenCabecera() {
//		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_ELIMINAR);
//		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}
	
}
