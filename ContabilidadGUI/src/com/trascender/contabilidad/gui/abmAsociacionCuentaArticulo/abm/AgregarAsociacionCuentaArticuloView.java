package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class AgregarAsociacionCuentaArticuloView extends ABMAsociacionCuentaArticuloView{
	

	private static final long serialVersionUID = 1725218711953953814L;
	
	public AgregarAsociacionCuentaArticuloView(JDialog dialog) {
		super(dialog);
		this.init();
	}
	
	public AgregarAsociacionCuentaArticuloView(JFrame frame){
		super(frame);
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
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("AgregarAsociacionCuentaArticulo.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AgregarAsociacionCuentaArticulo.descripcion"));
		
	}

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlPie().setBackground(color);
		this.getPnlCabecera().setBackground(color);
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar(("Application.btnAgregarTextoMnemonic")));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnAgregarTextoToolTip"));
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

}
