package com.trascender.saic.reporte.dataSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.Tasa;

public class LiquidacionTgiDS extends LiquidacionDS{
	
	private List<LiquidacionTasa> listaLiquidacionesTasa;
	
	public LiquidacionTgiDS(List<LiquidacionTasa> pListaLiquidacionTasa, Zonificacion pZonificacion, String pTitulo, String pSubtitulo){
		super(pListaLiquidacionTasa.get(0), pZonificacion,pTitulo, pSubtitulo);
		this.listaLiquidacionesTasa = pListaLiquidacionTasa;
		this.ordenarLiquidaciones();
		this.armarParametros();
	}
	
	private void ordenarLiquidaciones(){
		Collections.sort(this.listaLiquidacionesTasa,new Comparator<LiquidacionTasa>(){
			@Override
			public int compare(LiquidacionTasa o1, LiquidacionTasa o2) {
				Tasa locTasa = (Tasa)o1.getDocGeneradorDeuda();
				Tasa locTasa2 = (Tasa)o2.getDocGeneradorDeuda();
				int retorno = locTasa.getTipoTasa().getPeriodicidadCuotas().compareTo(locTasa2.getTipoTasa().getPeriodicidadCuotas());
				return retorno;
			}
		});
	}
	
	private void armarParametros(){
		for (int i = 1 ; i < listaLiquidacionesTasa.size() + 1 ; i++){
			LiquidacionTasa cadaLiquidacion = listaLiquidacionesTasa.get(i - 1);
			mapaParametros.put("PAR_TIPO_PAGO_" + i, this.getTipoPago(cadaLiquidacion));
			mapaParametros.put("PAR_CODIGOBARRA_LIQUIDACION_" + i, "1"+Util.formatString(18, String.valueOf(cadaLiquidacion.getIdRegistroDeuda())));
			mapaParametros.put("PAR_TOTAL_" + i, cadaLiquidacion.getMonto());
			mapaParametros.put("PAR_VENCIMIENTOS_DS_" + i, new VencimientosDS(cadaLiquidacion));
			mapaParametros.put("PAR_DETALLES_DS_" + i, new DetalleLiquidacionDS(cadaLiquidacion));
			if (numeroClientePagoFacil != null){
				mapaParametros.put("PAR_CODIGOBARRA_LIQUIDACION_PF_" + i, cadaLiquidacion.getCodigoBarrasPagoFacil());
			}
		}
	}
	
	private String getTipoPago(LiquidacionTasa pLiquidacion){
		String locTipoPago = "";
		Tasa locTasa = (Tasa) pLiquidacion.getDocGeneradorDeuda();
		Periodicidad locPeriodicidadCuotas = locTasa.getTipoTasa().getPeriodicidadCuotas();
		Periodicidad locPeriodicidadLiquidacion = locTasa.getTipoTasa().getPeriodicidad();
		if (locPeriodicidadLiquidacion.equals(Periodicidad.ANUAL)){
			if (locPeriodicidadCuotas.equals(Periodicidad.ANUAL)){
				locTipoPago = "PAGO ANUAL: "+pLiquidacion.getCuotaLiquidacion().toString();
			} else if (locPeriodicidadCuotas.equals(Periodicidad.MENSUAL)){
				locTipoPago = "PAGO EN TERCIOS: Mes " + pLiquidacion.getNumeroCuota()+ "/"+locTasa.getTipoTasa().getCantidadCuotas();
			}
		} else if (locPeriodicidadLiquidacion.equals(Periodicidad.BIMESTRAL)){
			locTipoPago = "PAGO BIMESTRAL: Bimestre " + pLiquidacion.getCuotaLiquidacion().getNumero()+"/6";
		}
		return locTipoPago;
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		return null;
	}
	
	@Override
	public boolean next() throws JRException {
		return false;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Tasa_General_Inmobiliaria.jasper";
	}

}
