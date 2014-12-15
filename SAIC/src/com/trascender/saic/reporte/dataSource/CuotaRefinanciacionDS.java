package com.trascender.saic.reporte.dataSource;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class CuotaRefinanciacionDS extends TrascenderDataSource{
	
	private DocumentoRefinanciacion documentoRefinanciacion;
	private CuotaRefinanciacion cuotaRefinanciacion;
	
	private Map<String, Object> mapaParametros = new HashMap<String, Object>();
	
	public CuotaRefinanciacionDS(CuotaRefinanciacion pCuotaRefinanciacion, DocumentoRefinanciacion pDocumentoRefinanciacion){
		this.documentoRefinanciacion = pDocumentoRefinanciacion;
		this.cuotaRefinanciacion = pCuotaRefinanciacion;
		this.armarParametros();
	}
	
	private void armarParametros(){
		this.mapaParametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		this.mapaParametros.put("PAR_TITULO", this.getTituloReporte());
		this.mapaParametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		this.mapaParametros.put("PAR_TASA", this.documentoRefinanciacion.getStringNombreRefinanciacion());
		this.mapaParametros.put("PAR_APELLIDO_NOMBRE", " "+this.documentoRefinanciacion.getObligacion().getPersona().toString());
		this.mapaParametros.put("PAR_INMUEBLES", this.documentoRefinanciacion.getStringInmuebles());
		this.mapaParametros.put("PAR_COMERCIOS", this.documentoRefinanciacion.getStringComercios());
		this.mapaParametros.put("PAR_NRO_REFINANCIACION", this.documentoRefinanciacion.getNumeroRefinanciacion());
		this.mapaParametros.put("PAR_NRO_CUOTA", this.cuotaRefinanciacion.getNumeroCuota());
		this.mapaParametros.put("PAR_MONTO_CUOTA", this.cuotaRefinanciacion.getValor());
		this.mapaParametros.put("PAR_CANTIDAD_CUOTAS", this.documentoRefinanciacion.getCantidadCuotas());
		this.mapaParametros.put("PAR_FECHA_VENCIMIENTO", this.cuotaRefinanciacion.getFechaVencimiento());
		this.mapaParametros.put("PAR_CODIGO_BARRAS", "1"+com.trascender.framework.util.Util.formatString(18, String.valueOf(this.cuotaRefinanciacion.getIdRegistroDeuda())));
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		return null;
	}

	@Override
	public boolean next() throws JRException {
		return false;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Cuotas_Refinanciacion.jasper";
	}
	
	public CuotaRefinanciacion getCuotaRefinanciacion(){
		return this.cuotaRefinanciacion;
	}
	
	

}
