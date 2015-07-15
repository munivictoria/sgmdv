package com.trascender.contabilidad.gui.principal;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.principal.MainView;

public class MainContabilidadView extends MainView {
	
	private static final long serialVersionUID = 3715632547420348160L;
	
	private MenuAdministracionContable menuAdministracionContable;
	private MenuAdministracionBancaria menuAdministracionBancaria;
	private MenuAdministracionTesoreria menuAdministracionTesoreria;
	//private MenuPruebas menuPruebas;
	private MenuAyuda menuAyuda;
	
	public MainContabilidadView() {
		super();
		
		this.menuAdministracionContable = new MenuAdministracionContable(this);
		this.getMbMain().add(this.menuAdministracionContable);
		
		this.menuAdministracionBancaria = new MenuAdministracionBancaria(this);
		this.getMbMain().add(this.menuAdministracionBancaria);
		
		this.menuAdministracionTesoreria = new MenuAdministracionTesoreria(this);
		this.getMbMain().add(this.menuAdministracionTesoreria);
		
		this.menuAyuda = new MenuAyuda(this);
		this.getMbMain().add(this.menuAyuda);
		
		super.setTitle(MessagesContabilidad.getString("Application.titulo"));
	}
	
	public MenuAdministracionContable getMenuAdministracionContable() {
		return menuAdministracionContable;
	}
	
	public MenuAdministracionBancaria getMenuAdministracionBancaria() {
		return menuAdministracionBancaria;
	}
	
	public MenuAyuda getMenuAyuda() {
		return menuAyuda;
	}

	public MenuAdministracionTesoreria getMenuAdministracionTesoreria() {
		return menuAdministracionTesoreria;
	}

}
