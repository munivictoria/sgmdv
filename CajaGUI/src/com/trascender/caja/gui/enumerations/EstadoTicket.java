package com.trascender.caja.gui.enumerations;

public enum EstadoTicket {
	TODOS, COBROS, ANULACIONES;
	@Override
	public String toString(){
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}
}
