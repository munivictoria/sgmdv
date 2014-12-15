package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoSubreporteChequeDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <Cheque> listaLineas;
	
	public OrdenPagoSubreporteChequeDS(List<Cheque> plistaCheque) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<Cheque>();

		for (Cheque cadaLinea : plistaCheque) {
			listaLineas.add(cadaLinea);
		}
	}
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		
		Object locValor = null;
		if(listaLineas.size() > 0){
			if("F_CHEQUE_NUMERO".equals(arg0.getName())){
				locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getNumeroCheque());
			}else if ("F_CHEQUE_IMPORTE".equals(arg0.getName())){
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
