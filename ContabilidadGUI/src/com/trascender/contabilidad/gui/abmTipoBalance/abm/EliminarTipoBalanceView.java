package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;

public class EliminarTipoBalanceView extends ABMTipoBalanceView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public EliminarTipoBalanceView(JDialog pDialog) {
		super(pDialog);
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
    	Color color = Constantes.COLOR_ELIMINAR;
    	this.getPnlCabecera().setBackground(color);
    	this.getPnlPie().setBackground(color);
    }
    
    @Override
    public void setTituloVentana() {
    	String locTitulo = MessagesContabilidad.getString("EliminarTipoBalance.titulo");
    	this.setTitle(locTitulo);
    	this.getPnlCabecera().getLblTitulo().setText(locTitulo);
    }
    
    @Override
    public void setDescripcionVentana() {
    	this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("EliminarTipoBalance.descripcion"));
    }
    
    @Override
    public void setTextoBtnAceptar() {
    	this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnEliminarTexto"));
    	this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("Application.btnEliminarTextoMnemonic"));
    	this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnEliminarTextToolTip"));
    }
    
    public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_ELIMINAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

}
