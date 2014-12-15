package com.trascender.saic.reporte.dataSource;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.Util;
import com.trascender.saic.recurso.persistent.Reliquidacion;

public class ReliquidacionDS extends LiquidacionDS{
	private Reliquidacion locReliquidacion;
	
	public ReliquidacionDS(Reliquidacion pReliquidacion, Zonificacion pZonificacion, String pTitulo, String pSubtitulo) {
		super(pReliquidacion.getLiquidacionTasa(), pZonificacion,pTitulo, pSubtitulo);
		locReliquidacion = pReliquidacion;
		this.armarParametros();
	}
	
	private void armarParametros(){
		mapaParametros.put("PAR_TITULO", "RELIQUIDACION " + Util.getFormatIfNull(this.liquidacionTasa.getTipoTasa().getTipoObligacion().toString().toUpperCase()));
		Persona locPersona = locReliquidacion.getLiquidacionTasa().getPersona();
		mapaParametros.put("PAR_CONTRIBUYENTE", Util.getFormatIfNull(locPersona.getDenominacion()));
		if(locPersona instanceof PersonaFisica){
			mapaParametros.put("PAR_DOCUMENTO", Util.getFormatIfNull(((PersonaFisica)locPersona).getTipoDocumento() + ": "
					+ ((PersonaFisica)locPersona).getNumeroDocumento()));
			}else{
			mapaParametros.put("PAR_DOCUMENTO", Util.getFormatIfNull(null));
		}
		
		mapaParametros.put("PAR_FORMULA_LIQUIDACION", Util.getFormatIfNull(locReliquidacion.getLiquidacionTasa().getStringPeriodoLiquidado()));
	}
	
	public String getNombreReporte() {
		return "Reporte_Reliquidacion.jasper";
	}
}
