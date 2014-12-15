package com.trascender.gui.framework.principal;


public abstract class Main {
	
	public abstract MainView getView();
	
	public void open() {
		this.getView().setVisible(true);
	}

	protected void init() {
		
	}
	
}