package com.trascender.habilitaciones.recurso.persistent.enums;

public enum TipoParametroInteres {
	IMPORTE_INTERES;
	
	@Override
	public String toString() {
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}
	
	public String getNombreVariable(){
		return com.trascender.framework.util.Util.getEnumNameFromString(this.toString());
	}
}
