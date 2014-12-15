package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class InformacionParcelariaSubreporteZonaDS extends TrascenderDataSource{
	private int linea_Actual = -1;
	private ArrayList <Zona>listaZonificacion = new ArrayList<Zona>();
	private Map <String, Object>parametros;
	PlanoMensura pm = new PlanoMensura();
	
	public InformacionParcelariaSubreporteZonaDS(List<Zona> pListaZonificacionParcela) throws Exception{
		listaZonificacion = new ArrayList<Zona>(pListaZonificacionParcela);
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = new Object();
		
		if("F_ZONIFICACION_NOMBRE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getZonificacion().getNombre()); 
		}else if("F_ZONA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getZonificacion().getZonificacion().getNombre());
		}else if("F_ESTADO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getZonificacion().getEstado().toString());
		}else if("F_PRIORIDAD".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getZonificacion().getPrioridad());
		}

		return locValor;
	}

	private Zona getZonificacion(){
		return listaZonificacion.get(linea_Actual);
	}
	
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++linea_Actual < listaZonificacion.size();
	}

	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Parcela_Subreporte_Zona.jasper";
	}

}
