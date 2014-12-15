package com.trascender.saic.recurso.persistent;

/**
 * 
 * @author Mariano Lusardi
 * 
 */
public class Condonacion extends RegistroCancelacion {

	/**
	 * 
	 */
	public static final long serialVersionUID = -775215747366196369L;
	
	private String causa;
	
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	
}
