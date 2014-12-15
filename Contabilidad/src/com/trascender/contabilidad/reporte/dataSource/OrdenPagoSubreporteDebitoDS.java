package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoSubreporteDebitoDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <Debito> listaLineas;
	
	public OrdenPagoSubreporteDebitoDS(List<Debito> plistaDebito) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<Debito>();

		for (Debito cadaLinea : plistaDebito) {
			listaLineas.add(cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if(listaLineas.size() >0){
			if("F_CUENTA_BAN_COMPLETA".equals(arg0.getName())){
				locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getCuentaBancaria().toString());
			}else if ("F_DEBITO_IMPORTE".equals(arg0.getName())){
				locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getImporte());
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
