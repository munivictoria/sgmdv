package com.trascender.contabilidad.reporte.dataSource;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.framework.util.TrascenderDataSource;

public class ChequeDS extends TrascenderDataSource {

	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private Cheque locCheque;
	
	public ChequeDS(Cheque pCheque) throws Exception{
		parametros = new HashMap<String, Object> ();
		locCheque = pCheque;
		parametros.put("PAR_IMPORTE", locCheque.getImporte());
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(locCheque.getFechaEmision());
		parametros.put("PAR_DIA_EMISION", locCalendar.get(Calendar.DAY_OF_YEAR));
		parametros.put("PAR_MES_EMISION", locCalendar.get(Calendar.MONTH));
		parametros.put("PAR_ANIO_EMISION", locCalendar.get(Calendar.YEAR));
		parametros.put("PAR_RAZON_SOCIAL", locCheque.getCuentaBancaria().getTitularCuentaBancaria().toString());
		parametros.put("PAR_NUMERO_EN_LETRAS", locCheque.getNumeroCheque());
	}
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < 1;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Cheque.jasper";
	}

}
