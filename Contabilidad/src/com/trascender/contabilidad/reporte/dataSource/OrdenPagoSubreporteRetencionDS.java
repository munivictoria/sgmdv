package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoSubreporteRetencionDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaRetencion> listaLineas;
	
	public OrdenPagoSubreporteRetencionDS(List<LineaRetencion> plistaLinaRetencion) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaRetencion>();

		for (LineaRetencion cadaLinea : plistaLinaRetencion) {
			listaLineas.add(cadaLinea);
		}
	}
		
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;

		if(listaLineas.size() > 0){
			if("F_RETENCION".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getComprobanteRetencion().toString());
			}else if ("F_IMPORTE_MINIMO".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getParametroRetencion().getImporteMinimo());
			}else if ("F_PORCENTAJE".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getParametroRetencion().getPorcentaje());
			}else if ("F_ALICUOTA".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getParametroRetencion().getAlicuota());
			}else if ("F_IMPORTE_CADA_RETENCION".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getComprobanteRetencion().getImporte());
			}
		}
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < listaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return null;
	}

}
