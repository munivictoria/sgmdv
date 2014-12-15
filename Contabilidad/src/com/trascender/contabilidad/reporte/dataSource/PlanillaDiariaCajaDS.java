package com.trascender.contabilidad.reporte.dataSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;

public class PlanillaDiariaCajaDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<TicketCaja> listaLineas;
	private JasperReport reportePlanillaDiariaCaja;
	
	public PlanillaDiariaCajaDS(List<TicketCaja> plistaTicketCaja) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<TicketCaja>();
		
		URL urlSubreportePlanillaCajaDiaria = this.getClass().getResource("/com/trascender/catastro/reporte/compilado/Reporte_Planilla_Diaria_Caja.jasper");
		reportePlanillaDiariaCaja = (JasperReport) JRLoader.loadObject(urlSubreportePlanillaCajaDiaria);
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NUMERO_CAJA", listaLineas.get(lineaActual).getCaja().getNumero());
		parametros.put("PAR_CAJA", listaLineas.get(lineaActual).getUsuario().toString());
		parametros.put("PAR_USUARIO", listaLineas.get(lineaActual).getUsuario().getUser());
		
		for (TicketCaja cadaLinea : plistaTicketCaja) {
			listaLineas.add(cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if ("F_TICKET_DATASPURCE".equals(arg0.getName())){
			PlanillaDiariaCajaSubreporteDS locSubreportePlanillaDiariaCajaDS = null;
			try {
				locSubreportePlanillaDiariaCajaDS = new PlanillaDiariaCajaSubreporteDS(listaLineas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			locValor = locSubreportePlanillaDiariaCajaDS;
		}else if ("F_TICKET_SUBREPORT".equals(arg0.getName())){
			locValor = reportePlanillaDiariaCaja;
		}				
		
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < 1;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Planilla_Caja.jasper";
	}
}
