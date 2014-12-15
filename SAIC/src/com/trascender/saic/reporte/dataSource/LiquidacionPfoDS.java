package com.trascender.saic.reporte.dataSource;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class LiquidacionPfoDS extends LiquidacionDS{

	public LiquidacionPfoDS(LiquidacionTasa pLiquidacionTasa, Zonificacion pZonificacion, String pTitulo, String pSubtitulo) {
		super(pLiquidacionTasa, pZonificacion,pTitulo, pSubtitulo);
		this.armarParametros();
	}
	
	private void armarParametros(){
		DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) liquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
		mapaParametros.put("PAR_PERIODO", "Período: " + liquidacionTasa.getCuotaLiquidacion().toString());
		mapaParametros.put("PAR_CUOTA", "Cuota: " + liquidacionTasa.getNumeroCuota()+"/"+locDocumentoPlanObra.getPlanCuentaObra().getCantidadCuotas().toString());
		mapaParametros.put("PAR_NRO_OBRA", locDocumentoPlanObra.getObra().getNumeroObra());
		mapaParametros.put("PAR_COSTO_METRO", locDocumentoPlanObra.getObra().getValorPorMetro());
		mapaParametros.put("PAR_OBRA", locDocumentoPlanObra.getObra().getDescripcion());
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Planes_Finalizacion_Obras.jasper";
	}

}
