package com.trascender.saic.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

public class ImprimirDDJJSHPSDS extends TrascenderDataSource  {
	

	private int lineaActual = -1;
	private List <AsocRegAlicuota> listaLineas;
	private Map<String, Object> parametros = new HashMap<String, Object> ();
	
	public ImprimirDDJJSHPSDS (Obligacion pObligaciones, CuotaLiquidacion pCuota)
	{
		listaLineas = pObligaciones.getDocumentoEspecializado().getListaAsocRegAlicuota();
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NOMBRE",  pObligaciones.getPersona().getDenominacion());
		parametros.put("PAR_DOMICILIO_COMERCIAL", pObligaciones.getDocumentoEspecializado().getDomicilio().getDomicilioCompleto());
		parametros.put("PAR_CUIT_CUIL_DNI", pObligaciones.getPersona().getCuim());

		parametros.put("PAR_ANIO", pCuota.getPeriodo().getCalendario().getAnio().toString());
		parametros.put("PAR_PERIODO", pCuota.getPeriodo().getNumero().toString());
		parametros.put("PAR_NRO_CUENTA",pObligaciones.getNumeroInscripcion());

	}	

	public Object getFieldValue(JRField field) throws JRException 
	{
		Object valor = null;
		
		 if (field.getName().equals("F_RUBROS"))
		{
			valor = listaLineas.get(lineaActual).getRegistroAlicuota().getCodigo() ;
		}else if (field.getName().equals("F_RAMO_EXPLORADO")){
			valor = listaLineas.get(lineaActual).getRegistroAlicuota().getNombre() ;
		}else if (field.getName().equals("F_ALICUOTA")){
			valor = listaLineas.get(lineaActual).getRegistroAlicuota().getValor().toString();
		}
		 
		return valor;
	}
	
	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "SubReporte_DDJJSHPS.jasper";
	}

}
