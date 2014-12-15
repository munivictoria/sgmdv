package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.caja.gui.recursos.RecursosCaja;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class ModificarConceptoMovimientoCajaChicaView extends ABMConceptoMovimientoCajaChicaView {

	private static final long serialVersionUID = 1821157584200486071L;
	
	public ModificarConceptoMovimientoCajaChicaView(JDialog owner) {
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString("ModificarConceptoMovimientoCajaChica.descripcion"));		
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesCaja.getString("Application.btnModificarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesCaja.getString("Application.btnModificarTextoMnemonic").charAt(0));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesCaja.getString("ModificarConceptoMovimientoCajaChica.titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosCaja.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
}