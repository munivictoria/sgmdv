package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;

public class VolanteCatastralPropietariosDS implements JRDataSource{
	
	private int linea_Actual = -1;
	private ArrayList <Persona>listaLineas = new ArrayList<Persona>();
	
	public VolanteCatastralPropietariosDS(List<Persona> persona)	
	{ 
	if (persona != null) {
		for (Persona cadaPersona : persona){
				listaLineas.add(cadaPersona);
			}
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = new Object();
		
		if("F_PROPIETARIOS".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getDenominacion());
		}else if("F_ZONA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getCuim());
		}else if("F_ESTADO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getDomicilio());
		}
		return locValor;
	}

	public boolean next() throws JRException {
		return ++linea_Actual < listaLineas.size();
	}

}
