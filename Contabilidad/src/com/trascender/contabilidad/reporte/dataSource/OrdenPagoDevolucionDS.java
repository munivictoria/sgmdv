package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoDevolucionDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private OrdenPagoDevolucion locOrdenPagoDev;
//	private JasperReport reporteOrdenPagoDevolucionCheque;
//	private JasperReport reporteOrdenPagoDevolucionDebito;
//	private JasperReport reporteOrdenPagoDevolucion;
	
	public OrdenPagoDevolucionDS(OrdenPagoDevolucion pOrdenPagoDev) throws Exception{
		parametros = new HashMap<String, Object> ();
		locOrdenPagoDev = pOrdenPagoDev;
		
//		URL urlSubreporteOrdenPagoDevolucionCheque = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/Reporte_Orden_Pago_Devolucion_Cheque_Subreporte.jasper");
//		reporteOrdenPagoDevolucionCheque = (JasperReport) JRLoader.loadObject(urlSubreporteOrdenPagoDevolucionCheque);
//		URL urlSubreporteOrdenPagoDevolucionDebito = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/Reporte_Orden_Pago_Devolucion_Debito_Subreporte.jasper");
//		reporteOrdenPagoDevolucionDebito = (JasperReport) JRLoader.loadObject(urlSubreporteOrdenPagoDevolucionDebito);
//		URL urlSubreporteOrdenPagoDevolucion = this.getClass().getResource("/com/trascender/contabilidad/reporte/compilado/Reporte_Orden_Pago_Devolucion_Subreporte.jasper");
//		reporteOrdenPagoDevolucion = (JasperReport) JRLoader.loadObject(urlSubreporteOrdenPagoDevolucion);
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_ORDEN_PAGO", locOrdenPagoDev.getNumero());
		parametros.put("PAR_EMISION", locOrdenPagoDev.getFechaEmision());
		parametros.put("PAR_PAGO", locOrdenPagoDev.getFechaPago());	
		parametros.put("PAR_CONTRIBUYENTE", Util.getFormatIfNull(locOrdenPagoDev.getPersona().toString()));
		parametros.put("PAR_CUIM", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getCuim()));		
		parametros.put("PAR_CALLE", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getCalle()));
		parametros.put("PAR_NUMERO", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getNumero()));
		parametros.put("PAR_PISO", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getPiso()));
		parametros.put("PAR_DPTO", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getDepartamento()));
		parametros.put("PAR_COD_POSTAL", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getCodigoPostal()));
		parametros.put("PAR_LOCALIDAD", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getLocalidad().getNombre()));
		parametros.put("PAR_PROVINCIA", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getDomicilio().getLocalidad().getProvincia().getNombre()));
		parametros.put("PAR_TELEFONO", Util.getFormatIfNull(locOrdenPagoDev.getPersona().getTelefono()));
		
		List<Cheque> locListaCheque = new ArrayList<Cheque>();
		List<Debito> locListaDebito = new ArrayList<Debito>();
		for (MovimientoBancario cadaMovimiento : locOrdenPagoDev.getMovimientosBancarios()){
			if (cadaMovimiento instanceof Cheque){
				locListaCheque.add((Cheque) cadaMovimiento);
			} else if (cadaMovimiento instanceof Debito) {
				locListaDebito.add((Debito) cadaMovimiento);
			}
		}
		
		OrdenPagoDevolucionSubreporteChequeDS locSubreporteDevolucionChequeDS = new OrdenPagoDevolucionSubreporteChequeDS(locListaCheque);
		parametros.put("PAR_CHEQUE_DS", locSubreporteDevolucionChequeDS);
		OrdenPagoDevolucionSubreporteDebitoDS locSubreporteDevolucionDebitoDS = new OrdenPagoDevolucionSubreporteDebitoDS(locListaDebito);
		parametros.put("PAR_DEBITO_DS", locSubreporteDevolucionDebitoDS);
		OrdenPagoDevolucionSubreporteDevolucionDS locSubreporteDevolucionDS = new OrdenPagoDevolucionSubreporteDevolucionDS(new ArrayList(locOrdenPagoDev.getLineaOrdenPagoDev()));
		parametros.put("PAR_DEVOLUCION_DS", locSubreporteDevolucionDebitoDS);
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		return null;
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
		return "Reporte_Orden_Pago_Devolucion.jasper";
	}

}
