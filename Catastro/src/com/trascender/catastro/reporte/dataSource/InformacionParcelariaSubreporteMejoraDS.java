package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class InformacionParcelariaSubreporteMejoraDS extends TrascenderDataSource{
	private int linea_Actual = -1;
	private ArrayList <RegistroMejora>listaRegistroMejora = new ArrayList<RegistroMejora>();
	private Map <String, Object>parametros;
	
	public InformacionParcelariaSubreporteMejoraDS(List<RegistroMejora> pListaMejoras){
		listaRegistroMejora = new ArrayList<RegistroMejora>(pListaMejoras);
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = new Object();
		
		if("F_ESTADO_MEJORA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getRegistroMejora().getEstadoMejora().toString());
		}else if("F_ANIO_MEJORA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getRegistroMejora().getAnioConstruccion());
		}else if("F_SUP_MEJORA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getRegistroMejora().getSuperficie());
		}else if("F_ACTIVO_MEJORA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getRegistroMejora().isActivo()? "Si":"No");
		}
		
		return locValor;
	}

	private RegistroMejora getRegistroMejora(){
		return listaRegistroMejora.get(linea_Actual);
	}
	
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++linea_Actual < listaRegistroMejora.size();
	}

	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Parcela_Subreporte_Mejora.jasper";
	}

}
