package com.trascender.contabilidad.gui.abmTipoBalance.abm;
import java.awt.Color;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.util.Constantes;

public class ConsultarTipoBalanceView extends ABMTipoBalanceView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public ConsultarTipoBalanceView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}

    private void init() {
    	this.setColorFondo();
    	this.setTituloVentana();
    	this.setDescripcionVentana();
   	    this.setTextoBtnCerrar(); 
	}
    
    @Override
    public void setColorFondo() {
    	Color color = Constantes.COLOR_CONSULTAR;
    	this.getPnlCabecera().setBackground(color);
    	this.getPnlPie().setBackground(color);
    }
    
    @Override
    public void setTituloVentana() {
    	String locTitulo = MessagesContabilidad.getString("ConsultarTipoBalance.titulo");
    	this.setTitle(locTitulo);
    	this.getPnlCabecera().getLblTitulo().setText(locTitulo);
    }
    
    @Override
    public void setDescripcionVentana() {
    	this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ConsultarTipoBalance.descripcion"));
    }
    
    @Override
    public void setTextoBtnAceptar() {
    }

    public void setTextoBtnCerrar() {
    	this.getPnlPie().getBtnCancelar().setText(
				MessagesContabilidad.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCancelar().setMnemonic(
				MessagesContabilidad.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCancelar().setToolTipText(
				MessagesContabilidad.getString("Application.btnCerrarToolTip"));
    }
    
    public void setImagenCabecera() {
	}

}
