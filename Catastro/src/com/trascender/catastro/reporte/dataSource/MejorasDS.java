package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class MejorasDS extends TrascenderDataSource {

	private int linea_Actual = -1;
	private ArrayList <RegistroMejora>listaLineas = new ArrayList<RegistroMejora>();
	private Map <String, Object>parametros;
	
	public MejorasDS(Set <RegistroMejora> listaMejoras)
	{
		for(RegistroMejora cadaMejora : listaMejoras){
			listaLineas.add(cadaMejora);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		
		Object locValor = new Object();
		
			if("F_CATEGORIA".equals(arg0.getName())){
				locValor = listaLineas.get(linea_Actual).getCategoria().getCodigo();
			}else if("F_SUPERFICIE".equals(arg0.getName())){
				locValor = listaLineas.get(linea_Actual).getSuperficie();
			}else if("F_AÑO_CONSTRUCCION".equals(arg0.getName())){
				locValor = listaLineas.get(linea_Actual).getAnioConstruccion();
			}else if("F_ESTADO_MEJORAS".equals(arg0.getName())){
				locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getEstadoMejora().toString());
			}

		return locValor;
	}


	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++linea_Actual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Padron_Catastral_Mejoras_Subreporte.jasper";
	}

}
