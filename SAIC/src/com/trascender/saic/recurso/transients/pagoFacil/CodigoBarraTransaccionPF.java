package com.trascender.saic.recurso.transients.pagoFacil;

public class CodigoBarraTransaccionPF {

	public static final Integer recordCode = 6;
	
	private String barCode = "";
	private Character typeCode;
	
	public String getBarCode() {
		return barCode;
	}
	
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public Character getTypeCode() {
		return typeCode;
	}
	
	public void setTypeCode(Character typeCode) {
		this.typeCode = typeCode;
	}
	
}
