package com.trascender.contabilidad.recurso.transients;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.reporte.dataSource.ResumenCajaGeneralDS;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;

public class ResumenActualCajaDataSource extends ResumenCajaGeneralDS{
	private static final long serialVersionUID = 5578570120563068456L;
	private int linea = -1;
	private List<HashMap<String, Object>> lineas = new ArrayList<HashMap<String, Object>>();
	private Double importeTotal = new Double(0);
	private Integer cantidadTickets;
	private Map<TipoObligacion, Double> mapaImportePorTasa = new HashMap<TipoObligacion, Double>();
	private Map<TipoObligacion, Integer> mapaCantidadPorTasa = new HashMap<TipoObligacion, Integer>();
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private List<MovimientoCajaIngreso> listaMovimientoCajaIngreso;
	
	public ResumenActualCajaDataSource(List<TicketCaja> pListaTicketsCaja, BusinessCajaLocal pBusinessCajaLocal){
		super(pListaTicketsCaja);
		List<Pagable> locListaLiquidacionTasa = new ArrayList<Pagable>();
		cantidadTickets = pListaTicketsCaja.size();
		Double interesTasa = null;
		Double adicionalBaldio = null;
		Double obraNueva = null;
		Double fondoBomberos = null;
		Double fondoPromocion = null;
		for (TicketCaja cadaTicketCaja : pListaTicketsCaja){
			if (!cadaTicketCaja.getEstado().equals(TicketCaja.Estado.ACTIVO)){
				continue;
			}
			//Datos comunes a todos los detalles.
			importeTotal = importeTotal + cadaTicketCaja.getImporteTotal();
			
			DetalleTicketCaja detalleEjemplo = cadaTicketCaja.getDetalles().iterator().next();
			LiquidacionTasa liquidacionEjemplo = (LiquidacionTasa) detalleEjemplo.getDeuda();
			HashMap<String, Object> locLineaLiquidacion = new HashMap<String, Object>();
			String numeroTicket = cadaTicketCaja.getNumero().toString();
			if (cadaTicketCaja.tieneDetallesConDeudaReatachada()) {
				numeroTicket = "*"+numeroTicket;
			}
			locLineaLiquidacion.put("NRO_TICKET", numeroTicket);
			locLineaLiquidacion.put("TICKET_IMPORTE", cadaTicketCaja.getImporteTotal());
			locLineaLiquidacion.put("CUIT", detalleEjemplo.getDeuda().getPersona().getCuim());
			locLineaLiquidacion.put("NOMBRE", detalleEjemplo.getDeuda().getPersona().getDenominacion());
			locLineaLiquidacion.put("TIPO_OBLIGACION", getTipoObligacionProcesada(
					liquidacionEjemplo.getTipoTasa().getTipoObligacion()));
			if (liquidacionEjemplo.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado()
					instanceof DocumentoSHPS){
				DocumentoSHPS locDocumento = (DocumentoSHPS) liquidacionEjemplo.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				locLineaLiquidacion.put("NRO_INSCRIPCION", locDocumento.getNumeroInscripcion());
			} else {
				locLineaLiquidacion.put("NRO_INSCRIPCION", 
						liquidacionEjemplo.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado()
						.getParcela().getNomenclaturaCatastral().getNroParcela());
			}
			//Detalle por detalle
			interesTasa = 0D;
			adicionalBaldio = 0D;
			fondoBomberos = 0D;
			fondoPromocion = 0D;
			obraNueva = 0D;
			
			for (DetalleTicketCaja cadaDetalle : cadaTicketCaja.getDetalles()){
				LiquidacionTasa cadaLiquidacion = (LiquidacionTasa) cadaDetalle.getDeuda();
				locListaLiquidacionTasa.add(cadaLiquidacion);
				Double importeTasa = cadaLiquidacion.getValorTasaConDescuentos() 
						+ cadaLiquidacion.getSumaRecargosManuales();
				int i = getIndiceSegunDeuda(cadaLiquidacion);
				locLineaLiquidacion.put("F_IMPORTE_TASA_"+i, importeTasa);
				locLineaLiquidacion.put("F_NOMBRE_TASA_"+i, cadaLiquidacion
						.getTipoTasa().getTipoObligacion().getNombre());
				
				interesTasa += cadaLiquidacion.getInteres();
				adicionalBaldio += getValorModificadorPorNombre("F_MOD_ADIC_BALDIO", cadaLiquidacion);
				obraNueva +=  getValorModificadorPorNombre("F_MOD_FONDO_OBRA", cadaLiquidacion);
				fondoPromocion += getValorModificadorPorNombre("F_MOD_FONDO_MUN", cadaLiquidacion);
				fondoBomberos += getValorModificadorPorNombre("F_MOD_FONDO_BOMBEROS", cadaLiquidacion);
			}
			locLineaLiquidacion.put("INTERES_TASA",interesTasa);
			locLineaLiquidacion.put("IMPORTE_INTERES", interesTasa);
			locLineaLiquidacion.put("F_MOD_ADIC_BALDIO",adicionalBaldio);
			locLineaLiquidacion.put("F_MOD_FONDO_OBRA",obraNueva);
			locLineaLiquidacion.put("F_MOD_FONDO_MUN",fondoPromocion);
			locLineaLiquidacion.put("F_MOD_FONDO_BOMBEROS", fondoBomberos);
			lineas.add(locLineaLiquidacion);
		}
		Collections.sort(lineas, new Comparator<HashMap<String, Object>>() {

			@Override
			public int compare(HashMap<String, Object> o1,
					HashMap<String, Object> o2) {
				
				String tipo1 = (String) o1.get("TIPO_OBLIGACION");
				if (tipo1.equalsIgnoreCase("Tickets Cancelados")){
					return 1;
				}
				String tipo2 = (String) o2.get("TIPO_OBLIGACION");
				return tipo1.compareTo(tipo2);
			}
			
		});
		try {
			listaMovimientoCajaIngreso = pBusinessCajaLocal.getListaMovimientosCaja(locListaLiquidacionTasa, false);
			Collections.sort(listaMovimientoCajaIngreso, new Comparator<MovimientoCajaIngreso>() {

				@Override
				public int compare(MovimientoCajaIngreso o1,
						MovimientoCajaIngreso o2) {
				return	o1.getCuenta().getNombre().compareTo(o2.getCuenta().getNombre());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		parametros.put("PAR_LISTA_MOV_CAJA_INGRESO", listaMovimientoCajaIngreso);
	}
	
	private String getTipoObligacionProcesada(TipoObligacion pTipoObligacion){
		if (pTipoObligacion.getNombre().equals("OYSP")
				|| pTipoObligacion.getNombre().equals("TGI")) {
			return "Servicios Municipales";
		}
		return pTipoObligacion.getNombre();
	}
	
	private Double getValorModificadorPorNombre(String pNombre, LiquidacionTasa pLiquidacion){
		for (ModificadorLiquidacion cadaModificador : pLiquidacion.getListaModificadoresLiquidacion()){
			if (pNombre.equals("F_MOD_ADIC_BALDIO") && 
					cadaModificador.getNombre().equals("Adicional Baldío")){
				return cadaModificador.getValorModificador();
			} else if (pNombre.equals("F_MOD_FONDO_OBRA") 
					&& cadaModificador.getNombre().equals("Fondo Obra Nueva - Ord.2475 art 34")){
				return cadaModificador.getValorModificador();
			} else if (pNombre.equals("F_MOD_FONDO_MUN")
					&& (cadaModificador.getNombre().equals("Fondo Municipal - Ord.2475 art 238")
							|| cadaModificador.getNombre().equals("Fondo Municipal de Promoción"))){
				return cadaModificador.getValorModificador();
			} else if (pNombre.equals("F_MOD_FONDO_BOMBEROS")
					&& cadaModificador.getNombre().equals("Fondo Bomberos")){
				return cadaModificador.getValorModificador();
			}
		}
		return 0D;
	}
	
	private Double getImporteTotalModificadores(LiquidacionTasa pLiquidacionTasa){
		Double locTotalModificadores = new Double(0);
		for (ModificadorLiquidacion cadaModificador : pLiquidacionTasa.getListaModificadoresLiquidacion()){
			locTotalModificadores += cadaModificador.getValorModificador();
		}
		return locTotalModificadores;
	}
	
	public Double getImporteTotal(){
		return importeTotal;
	}
	
	public Integer getCantidadTickets(){
		return cantidadTickets;
	}
	
	private int getIndiceSegunDeuda(LiquidacionTasa pLiquidacion){
		if (pLiquidacion.getTipoTasa().getTipoObligacion().getNombre().equals("OYSP")){
			return 1;
		}
		return 0;
	}
	
	private List<LiquidacionTasa> getLiquidacionesOrdenadosTasa(TicketCaja pTicketCaja){
		List<LiquidacionTasa> locListaLiquidaciones = new ArrayList<LiquidacionTasa>();
		for (DetalleTicketCaja cadaDetalle : pTicketCaja.getDetalles()){
			locListaLiquidaciones.add((LiquidacionTasa) cadaDetalle.getDeuda());
		}
		Collections.sort(locListaLiquidaciones, new Comparator<LiquidacionTasa>() {
			@Override
			public int compare(LiquidacionTasa o1, LiquidacionTasa o2) {
				return o1.getTipoTasa().getTipoObligacion().getNombre().compareTo(
						o2.getTipoTasa().getTipoObligacion().getNombre());
			}
		});
		return locListaLiquidaciones;
	}
	
	private String getCuenta(DetalleTicketCaja detalle, Double monto){
		for (MovimientoCajaIngreso locMovimiento : detalle.getMovimientosCajaIngreso()){
			if (locMovimiento.getImporte().equals(monto)){
				return locMovimiento.getCuenta().getNombre();
			}
		}
		return null;
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
	
	public void imprimir(){
		for (Map<String, Object> cadaMapa : this.lineas){
			System.out.print("OBLIGACION: " + cadaMapa.get("TIPO_OBLIGACION") + " ");
			System.out.print("CUIT: " + cadaMapa.get("CUIT") + " ");
			System.out.print("NOMBRE: " + cadaMapa.get("NOMBRE") + " ");
			System.out.print(cadaMapa.get("F_IMPORTE_TASA_0") + ": " 
					+ cadaMapa.get("F_NOMBRE_TASA_0") + " ");
			System.out.print(cadaMapa.get("F_IMPORTE_TASA_1") + ": " 
					+ cadaMapa.get("F_NOMBRE_TASA_1") + " ");
			System.out.print("INTERES: " + cadaMapa.get("INTERES_TASA") + " ");
			System.out.print("FMP: " + cadaMapa.get("F_MOD_FONDO_MUN") + " ");
			System.out.print("FON: " + cadaMapa.get("F_MOD_FONDO_OBRA") + " ");
			System.out.print("FBV: " + cadaMapa.get("F_MOD_FONDO_BOMBEROS") + " ");
			System.out.print("BALDIO: " + cadaMapa.get("F_MOD_ADIC_BALDIO") + " ");
			System.out.print("CUENTA/INSCRIPCION: " + cadaMapa.get("NRO_INSCRIPCION") + " ");
			System.out.print("IMPORTE_TICKET: " + cadaMapa.get("TICKET_IMPORTE") + " ");
			System.out.println("FIN LINEA");
		}
		System.out.println();
		for (Map<String, Object> locLinea : lineas){
			for (String locString : locLinea.keySet()){
				System.out.print(locLinea.get(locString) + "\t");
			}
			System.out.println();
		}
		System.out.println("IMPORTE POR TASA");
		for (TipoObligacion tipo : mapaImportePorTasa.keySet()){
			System.out.println(tipo+ ": " + mapaImportePorTasa.get(tipo));
		}
		System.out.println("IMPORTE TOTAL");
		System.out.println(importeTotal);
	}

	public Object getFieldValue(JRField field) throws JRException {
		return lineas.get(linea).get(field.getName());
	}

	public boolean next() throws JRException {
		return ++linea < lineas.size();
	}
	
	public List<MovimientoCajaIngreso> getListaMovimientoCajaIngreso() {
		return listaMovimientoCajaIngreso;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Planilla_Diaria_Caja_Tasa.jasper";
	}


}
