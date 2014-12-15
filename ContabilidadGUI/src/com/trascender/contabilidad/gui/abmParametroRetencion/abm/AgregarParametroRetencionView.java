package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class AgregarParametroRetencionView extends ABMParametroRetencionView {

	private static final long serialVersionUID = -8542906580700826672L;

	public AgregarParametroRetencionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AgregarParametroRetencionView(JFrame owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AgregarParametroRetencion.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar(("Application.btnAgregarTextoMnemonic")));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnAgregarTextoToolTip"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("AgregarParametroRetencion.titulo");
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
		this.setTitle(locTitulo);
	}		
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

}
