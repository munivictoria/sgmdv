package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;

public class ModificarAsociacionCuentaLineaFacturaView extends ABMAsociacionCuentaLineaFacturaView {

	private static final long serialVersionUID = -7400694394006233398L;

	public ModificarAsociacionCuentaLineaFacturaView(JDialog owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ModificarAsociacionCuentaLineaFactura.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(Messages.getString("Application.btnModificarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(Messages.getString("Application.btnModificarTextoMnemonic").charAt(0));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ModificarAsociacionCuentaLineaFactura.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
}
