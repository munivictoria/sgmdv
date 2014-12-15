package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class LibroDiarioDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private LibroDiario locLibroDiario;
	private List<LineaAsientoContable> listaLineas;
	
	public LibroDiarioDS(LibroDiario plibroDiario) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaAsientoContable>();
		locLibroDiario = plibroDiario;
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_COD_AUTORIZACION", locLibroDiario.getCodigoAutorizacion());
		parametros.put("PAR_NUMERO", locLibroDiario.getNumero());
		parametros.put("PAR_CANT_FOLIOS", locLibroDiario.getFoliosLibroDiario().size());
		
		for (FolioLibroDiario cadaLinea : plibroDiario.getFoliosLibroDiario()) {
			for(AsientoContable cadaAsiento: cadaLinea.getAsientosContables()){
				listaLineas.addAll(new ArrayList<LineaAsientoContable>(cadaAsiento.getLineasAsientoContable()));
			}
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = null;
		
			if(listaLineas != null && listaLineas.size() > 0){
				if("F_FECHA_ASIENTO".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getAsientoContable().getFecha());
				}else if ("F_CUENTA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCuenta().toString());
				}else if ("F_NUMERO_ASIENTO".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getAsientoContable().getNumeroAsiento());
				}else if ("F_IMPORTE_DEBE".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporteDebe());
				}else if ("F_IMPORTE_HABER".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporteHaber());
				}else if ("F_CONCEPTO".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getAsientoContable().getObservaciones());
			}
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
		return "Reporte_Libro_Diario.jasper";
	}

}
