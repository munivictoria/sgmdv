package com.trascender.saic.reporte.dataSource;

import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.framework.util.TrascenderDataSource;

public class CuotaRefinanciacionAgrupadaDS extends TrascenderDataSource{
	
	List<CuotaRefinanciacionDS> listaCuotasRefinanciacionDS;
	private int lineaActual = -1;
	
	public CuotaRefinanciacionAgrupadaDS(List<CuotaRefinanciacionDS> pListaDS){
		this.listaCuotasRefinanciacionDS = pListaDS;
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("DATASOURCE")){
			valor = listaCuotasRefinanciacionDS.get(lineaActual);
		} else if (field.getName().equals("SUBREPORTE")){
			URL urlSubreporte = this.getClass().getResource("/com/trascender/saic/reporte/compilado/"+listaCuotasRefinanciacionDS.get(lineaActual).getNombreReporte());
			JasperReport jr = (JasperReport) JRLoader.loadObject(urlSubreporte);
			jr.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			valor = jr;
		}else if (field.getName().equals("MAPA_PARAMETROS")){
			valor = listaCuotasRefinanciacionDS.get(lineaActual).getMapaParametros();
		}
		return valor;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaCuotasRefinanciacionDS.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return null;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Cuota_Refinanciacion_Agrupada.jasper";
	}

}
