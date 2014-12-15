package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class MayorDS extends TrascenderDataSource {
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaMayor> listaLineas;
	private Mayor locMayor;
	
	public MayorDS(Mayor pMayor) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaMayor>();
		locMayor = pMayor;
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_CUENTA_NOMBRE", Util.getFormatIfNull(locMayor.getCuenta().getNombre()));
		parametros.put("PAR_COD_IMPUTACION", Util.getFormatIfNull(locMayor.getCuenta().getCodigoImputacionCompleto()));
		parametros.put("PAR_TIPO_MAYOR", Util.getFormatIfNull(locMayor.getTipo().toString()));
		
		for (LineaMayor cadaLinea : pMayor.getListaLineaMayor()) {
			listaLineas.add(cadaLinea);
		}
	}
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		
		Object locValor = null;
		
		if("F_FECHA_LINEA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getFechaGeneracion());
		}else if ("F_NUMERO_FOLIO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getFolioLibroDiario().getNumero());
		}else if ("F_IMPORTE_DEBE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporteHaber());
		}else if ("IMPORTE_HABER".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporteDebe());
		}else if ("F_SALDO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getSaldo());
		}
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Mayor.jasper";
	}

}
