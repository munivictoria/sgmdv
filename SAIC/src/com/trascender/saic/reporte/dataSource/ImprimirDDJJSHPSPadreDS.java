package com.trascender.saic.reporte.dataSource;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderDataSource;

public class ImprimirDDJJSHPSPadreDS extends TrascenderDataSource{

	private List<ImprimirDDJJSHPSDS> listaObligaciones;
	private int lineaActual = -1;
	Map<String,Object> mapaParametros = new HashMap<String, Object>();
	
	public ImprimirDDJJSHPSPadreDS (List<ImprimirDDJJSHPSDS> pListaDataSource){
		this.listaObligaciones = pListaDataSource;
		
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("F_DATASOURCE")){
			valor = listaObligaciones.get(lineaActual);
		} else if (field.getName().equals("F_SUBREPORTE")){
			File locFile = new File(SecurityMgr.getInstance().getMunicipalidad().getRutaReportes() + listaObligaciones.get(lineaActual).getNombreReporte());
			JasperReport jr = (JasperReport) JRLoader.loadObject(locFile);
			jr.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			valor = jr;
		}else if (field.getName().equals("MAPA_PARAMETROS")){
			valor = listaObligaciones.get(lineaActual).getMapaParametros();
		}
		return valor;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaObligaciones.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	@Override
	public String getNombreReporte() {
		
		return "Reporte_DDJJSHPS.jasper";
	}

}
