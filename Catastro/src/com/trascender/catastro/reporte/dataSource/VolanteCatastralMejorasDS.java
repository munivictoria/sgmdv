package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.RegistroMejora;

public class VolanteCatastralMejorasDS implements JRDataSource{
	private int linea_Actual = -1;
	private ArrayList <RegistroMejora>listaLineas = new ArrayList<RegistroMejora>();
	
	public VolanteCatastralMejorasDS(Set <RegistroMejora> mejoras){
		for (RegistroMejora cadaMejora : mejoras){
			listaLineas.add(cadaMejora);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = new Object();
		
		if("F_ESTADO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getEstadoMejora().toString();
		}else if("F_SUPERFICIE".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getSuperficie();
		}else if("F_ANIO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getAnioConstruccion();
		}
		
		return locValor;
	}

	public boolean next() throws JRException {
		return ++linea_Actual < listaLineas.size();
	}

}
