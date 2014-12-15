package com.trascender.gui.framework.model;

import javax.swing.DefaultComboBoxModel;

public class TDefaultComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 5513255615940373091L;

	/**
	 * Constructor por defecto. 
	 * Es igual que invocar a TDefaultComboBoxModel(Object[] objetos, boolean conElementoVacio) con conElementoVacio = true.
	 * @param objetos
	 */
	public TDefaultComboBoxModel(Object[] objetos) {
		this.addElements(objetos, true);
	}
	
	/**
	 * Constructor para permitir quitar el primer elemento vacío de la lista.
	 * @param objetos
	 * @param conElementoVacio
	 */
	public TDefaultComboBoxModel(Object[] objetos, boolean conElementoVacio) {
		this.addElements(objetos, conElementoVacio);
	}
	
	private void addElements(Object[] objetos, boolean conElementoVacio) {
		Object[] listaCompleta = null;
		if (conElementoVacio) {
			listaCompleta = new Object[objetos.length+1];
			listaCompleta[0] = null;
			for (int i = 0; i < objetos.length; i++) {
				listaCompleta[i+1] = objetos[i];
			}
		}
		else {
			listaCompleta = objetos;
		}
		
		for (int i = 0; i < listaCompleta.length; i++) {
			this.addElement(listaCompleta[i]);
		}
	}
	
}
