package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class VolanteCatastralZonificacionDS implements JRDataSource{
	private int linea_Actual = -1;
	private ArrayList <Zona>listaLineas = new ArrayList<Zona>();
	
	public VolanteCatastralZonificacionDS(List<Zona> zona)	{ 
	if (zona != null) {
		for (Zona cadaZona : zona){
				listaLineas.add(cadaZona);
			}
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = new Object();
		
		if("F_ZONIFICACION_NOMBRE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getNombre());
		}else if("F_ZONA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getIdZona());
		}else if("F_ESTADO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getEstado());
		}
		else if("F_PRIORIDAD".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getPrioridad());
		}
		return locValor;
	}

	public boolean next() throws JRException {
		return ++linea_Actual < listaLineas.size();
	}

}
