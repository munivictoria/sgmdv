package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class InformacionParcelariaSubreporteMensuraDS extends TrascenderDataSource{
	private int linea_Actual = -1;
	private ArrayList <PlanoMensura>listaMensuras = new ArrayList<PlanoMensura>();
	private Map <String, Object>parametros;

	public InformacionParcelariaSubreporteMensuraDS(List<PlanoMensura> pListaMensuraParcela){
		listaMensuras = new ArrayList<PlanoMensura>(pListaMensuraParcela);
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = new Object();
		if("F_FECHA_INSCRIPCION".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getMensura().getFechaInscripcion()); 
		}else if("F_NUMERO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(getMensura().getPlano());
		}

		return locValor;
	}

	private PlanoMensura getMensura(){
		return listaMensuras.get(linea_Actual);
	}
	
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++linea_Actual < listaMensuras.size();
	}

	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Parcela_Subreporte_Mensura.jasper";
	}

}
