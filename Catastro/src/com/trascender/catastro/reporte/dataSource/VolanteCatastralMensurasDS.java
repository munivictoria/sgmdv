package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class VolanteCatastralMensurasDS implements JRDataSource{

	private int linea_Actual = -1;
	private ArrayList <PlanoMensura>listaLineas = new ArrayList<PlanoMensura>();
	private Map <String, Object>parametros;
	
	public VolanteCatastralMensurasDS(List <PlanoMensura> listaPlanoMensura){
		for (PlanoMensura cadaMensura : listaPlanoMensura){
			listaLineas.add(cadaMensura);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = null;
		
		if("F_NRO_MENSURA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getPlano());
		}else if ("F_FECHA_INSCRIPCION".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getFechaInscripcion();
		}
		
		return locValor;
	}

	public boolean next() throws JRException {
		return ++linea_Actual < listaLineas.size();
	}

}
