package com.trascender.expedientes.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderDataSource;

public class AltasExpedientesDS extends TrascenderDataSource {
	
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<Map<String, Object>> filas = new ArrayList<Map<String,Object>>();
	
	public AltasExpedientesDS (Expediente pExpediente, Usuario pUsuario){
		parametros = new HashMap<String, Object> ();
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_EXPEDIENTE", pExpediente);
		parametros.put("PAR_USUARIO", pUsuario.getNombrePersonaFisica().toString());
		parametros.put("PAR_CARPETA_REPORTES", SecurityMgr.getInstance().getMunicipalidad().getRutaReportes());
		
		List<Documento> locListaDocumentos = new ArrayList<Documento>();
		NodoExpediente primeraFase = pExpediente.getListaFasesOrdenada().get(0);
		//Si la primera fase tiene Tramites
		if (primeraFase.getListaNodosExpedientes() != null && !primeraFase.getListaNodosExpedientes().isEmpty()) {
			//Recorro todos los Tramites de la primera fase y pongo todos los documentos.
			for (NodoExpediente cadaNodoTramite : primeraFase.getListaNodosExpedientes()) {
				Tramite locTramite = (Tramite) cadaNodoTramite;
				locListaDocumentos.addAll(locTramite.getListaDocumentos());
			}
		}
		parametros.put("PAR_LISTA_DOCUMENTOS", locListaDocumentos);
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		return filas.get(lineaActual).get(arg0.getName());
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < filas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Alta_Expediente.jasper";
	}


}
