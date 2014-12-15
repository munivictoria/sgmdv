package com.trascender.expedientes.business.interfaces;

import java.util.Date;

import javax.ejb.Local;

import com.trascender.expedientes.recurso.persistent.Plazo;

@Local
public interface BusinessPlazo {

	
	public final static String JNDI_NAME = "BusinessExpedientes";
	
	public void inicializarPlazoExpediente(Date fechaDesde, Plazo pPlazo);
	
	public void actualizarPlazoExpediente(Plazo pPlazo);
	
	
}
