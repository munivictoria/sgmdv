package com.trascender.saic.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

@Local
public interface BusinessThreaderLocal {
	
	public void imprimirLiquidacionesEnServidor(
			List<LiquidacionTasaAgrupada> pListaLiquidaciones, byte[] pLogo, 
			String pTitulo, String pSubtitulo, BusinessZonificacionLocal pBusinessZonificacion);

}
