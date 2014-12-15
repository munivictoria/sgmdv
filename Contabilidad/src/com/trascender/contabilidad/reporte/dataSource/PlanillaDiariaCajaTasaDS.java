package com.trascender.contabilidad.reporte.dataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class PlanillaDiariaCajaTasaDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<TicketCaja> listaLineas;
	
	private int linea = -1;
	private List<HashMap<String, Object>> lineas = new ArrayList<HashMap<String, Object>>();
	private Double importeTotal = new Double(0);
	private Integer cantidadTickets;
	private Map<TipoObligacion, Double> mapaImportePorTasa = new HashMap<TipoObligacion, Double>();
	private Map<TipoObligacion, Integer> mapaCantidadPorTasa = new HashMap<TipoObligacion, Integer>();
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public PlanillaDiariaCajaTasaDS(List<TicketCaja> plistaTicketCaja) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<TicketCaja>();
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NUM_CAJA", listaLineas.get(lineaActual).getCaja().getNumero());
		parametros.put("PAR_CAJERO", listaLineas.get(lineaActual).getUsuario().getUser());
		parametros.put("PAR_PERSONA", listaLineas.get(lineaActual).getUsuario().getNombrePersonaFisica());
		parametros.put("PAR_CANT_TOTAL_TICKET", listaLineas.size());
		parametros.put("PAR_IMPORTE_TOTAL", listaLineas.get(lineaActual).getImporteTotal());
		
		Pagable locDeuda = null;
		for (TicketCaja cadaTicketCaja : plistaTicketCaja){
			locDeuda = ((DetalleTicketCaja)cadaTicketCaja.getDetalles()).getDeuda();
			if (locDeuda instanceof LiquidacionTasa){
				listaLineas.add(cadaTicketCaja);
			} 
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;	
			
		cantidadTickets = listaLineas.size();
		for (TicketCaja locTicketCaja : listaLineas){
			importeTotal = importeTotal + locTicketCaja.getImporteTotal();
			for (DetalleTicketCaja locDetalle : locTicketCaja.getDetalles()){
				LiquidacionTasa locLiquidacion = (LiquidacionTasa) locDetalle.getDeuda();
				this.addImportePorTasa(locLiquidacion);
				Double importeInicial = locLiquidacion.getValor() + locLiquidacion.getInteres() + locLiquidacion.getRecargo() + locLiquidacion.getMontoMultas();
				HashMap<String, Object> locLineaLiquidacion = new HashMap<String, Object>();
				
				if("F_TIPO_OBLIGACION".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getTipoTasa().getTipoObligacion().toString());
				}else if ("F_NRO_TICKET".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getNumero());
				}else if ("F_FECHA_DEUDA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getFechaEmision());
				}else if ("F_FECHA_CANCELACION".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getFechaCancelacion());
				}else if ("F_PERIODO".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getCuotaLiquidacion());
				}else if ("F_CUENTA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(getCuenta(locDetalle, importeInicial));
				}else if ("F_MPORTE_TASA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getValor());
				}else if ("F_INTERES_TASA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getInteres());
				}else if ("F_RECARGO_TASA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(locLiquidacion.getRecargo());
				}else if ("F_CANTIDAD_TICKET_TASA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(mapaCantidadPorTasa.get(locLiquidacion.getTipoTasa().getTipoObligacion()));
				}else if ("F_IMPORTE_TOTAL_TASA".equals(arg0.getName())){
					locValor = Util.getFormatIfNull(mapaImportePorTasa.get(locLiquidacion.getTipoTasa().getTipoObligacion()));
				}
			}
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
		return "Reporte_Planilla_Diaria_Caja_Tasa.jasper";
	}
	
	private void addImportePorTasa(LiquidacionTasa pLiquidacion){
		TipoObligacion tipoObligacion = pLiquidacion.getTipoTasa().getTipoObligacion();
		
		Double importe = mapaImportePorTasa.get(tipoObligacion);
		if (importe == null){
			importe = pLiquidacion.getMonto();
		} else {
			importe = importe + pLiquidacion.getMonto();
		}
		mapaImportePorTasa.put(pLiquidacion.getTipoTasa().getTipoObligacion(), importe);
		
		Integer cantidad = mapaCantidadPorTasa.get(tipoObligacion);
		if (cantidad == null){
			cantidad = 1;
		} else {
			cantidad = cantidad + 1;
		}
		mapaCantidadPorTasa.put(tipoObligacion, cantidad);
	}
	
	private String getCuenta(DetalleTicketCaja detalle, Double monto){
		for (MovimientoCajaIngreso locMovimiento : detalle.getMovimientosCajaIngreso()){
			if (locMovimiento.getImporte().equals(monto)){
				return locMovimiento.getCuenta().getNombre();
			}
		}
		return null;
	}

}
