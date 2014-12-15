package com.trascender.caja.gui.principal;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.principal.MainView;

public class MainCajaView extends MainView{

	private static final long serialVersionUID = -927467928737754701L;
	
	private MenuAdministracionCaja menuAdministracionCaja;
	private MenuAdministracionCobro menuAdministracionCobro;
	private MenuAyuda menuAyuda;
	
	public MainCajaView() {
		super();
		
		this.menuAdministracionCaja = new MenuAdministracionCaja(this);
		this.getMbMain().add(this.menuAdministracionCaja);
		
		this.menuAdministracionCobro = new MenuAdministracionCobro(this);
		this.getMbMain().add(this.menuAdministracionCobro);
		
		this.menuAyuda = new MenuAyuda(this);
		this.getMbMain().add(this.menuAyuda);
		
		super.setTitle(MessagesCaja.getString("Application.titulo"));
		
		this.setExtendedState(MainCajaView.MAXIMIZED_BOTH);
	}

	public MenuAdministracionCaja getMenuAdministracionCaja() {
		return menuAdministracionCaja;
	}

	public void setMenuAdministracionCaja(
			MenuAdministracionCaja menuAdministracionCaja) {
		this.menuAdministracionCaja = menuAdministracionCaja;
	}

	public MenuAdministracionCobro getMenuAdministracionCobro() {
		return menuAdministracionCobro;
	}

	public void setMenuAdministracionCobro(
			MenuAdministracionCobro menuAdministracionCobro) {
		this.menuAdministracionCobro = menuAdministracionCobro;
	}

}
