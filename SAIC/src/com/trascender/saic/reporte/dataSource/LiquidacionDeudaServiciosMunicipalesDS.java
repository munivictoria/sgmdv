package com.trascender.saic.reporte.dataSource;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

public class LiquidacionDeudaServiciosMunicipalesDS extends TrascenderDataSource {

	protected DocHabilitanteEspecializado docHabilitanteEspecializado;
	protected Map<String, Object> mapaParametros;

	public LiquidacionDeudaServiciosMunicipalesDS(DocHabilitanteEspecializado pDocHabilitanteEspecializado){
		this.docHabilitanteEspecializado = pDocHabilitanteEspecializado;
		setearParametros();
	}

	private void setearParametros(){
		mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("PAR_IMAGEN", null);
		mapaParametros.put("PAR_TITULO", null);
		mapaParametros.put("PAR_SUBTITULO", null);

		//Persona
		Persona locPersona =  docHabilitanteEspecializado.getObligacion().getPersona();

		mapaParametros.put("PAR_CUENTA", Util.getFormatIfNull(docHabilitanteEspecializado.getParcela().getNroCuenta().toString()));
		mapaParametros.put("PAR_CONTRIBUYENTE", Util.getFormatIfNull(locPersona.toString()));
		mapaParametros.put("PAR_DOMICILIO_POSTAL", Util.getFormatIfNull(docHabilitanteEspecializado.getDomicilio().toString()));
		mapaParametros.put("PAR_DOMICILIO_PARCELARIO", Util.getFormatIfNull(docHabilitanteEspecializado.getParcela().getDomicilioParcelario().toString()));
		//		mapaParametros.put("PAR_DEUDA", Util.getFormatIfNull(new Date()));
		mapaParametros.put("PAR_PARTIDA", Util.getFormatIfNull(docHabilitanteEspecializado.getParcela().getNroPartidaProvincial()).toString());
		//		mapaParametros.put("PAR_TGI", Util.getFormatIfNull())

	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
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
		return "Reporte_Liquidacion_Deuda_Servicios_Municipales.jasper";
	}

}
