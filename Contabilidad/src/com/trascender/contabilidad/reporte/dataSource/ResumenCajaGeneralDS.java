package com.trascender.contabilidad.reporte.dataSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.PagoTicket;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCompensacion;
import com.trascender.contabilidad.recurso.persistent.PagoTicketDeposito;
import com.trascender.contabilidad.recurso.persistent.PagoTicketEfectivo;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.THashMap;

/**
 * Para ser usado por cualquier reporte de caja (cobros de ticket)
 * @author fer
 *
 */
public abstract class ResumenCajaGeneralDS implements JRDataSource, Serializable{
	private static final long serialVersionUID = 8175347145720283834L;

	protected List<TicketCaja> listaTicketsCaja;
	
	protected TiposPagoDS tipoPagoDS;
	protected CanceladosDS canceladosDS;
	protected Map <String, Object>parametros;
	protected THashMap<Cuenta> totalImputaciones;
	
	public ResumenCajaGeneralDS(List<TicketCaja> pListaTickets) {
		listaTicketsCaja = new ArrayList<TicketCaja>(pListaTickets.size());
		parametros = new HashMap<String, Object>();
		totalImputaciones = new THashMap<Cuenta>();
		List<TicketCaja> locListaTicketsCancelados = new ArrayList<TicketCaja>();
		THashMap<String> mapaTiposPago = new THashMap<String>();
		
		for (TicketCaja cadaTicket : pListaTickets) {
			//Si es Cancelado no lo proceso, solo lo agrego
			if (!cadaTicket.getEstado().equals(TicketCaja.Estado.ACTIVO)){
				locListaTicketsCancelados.add(cadaTicket);
				continue;
			}
			
			for (PagoTicket cadaPago : cadaTicket.getListaPagosTicket()){
				mapaTiposPago.add(getNombreTipoPago(cadaPago), cadaPago.getMonto());
			}
			
			for (DetalleTicketCaja cadaDetalle : cadaTicket.getDetalles()) {
				for (MovimientoCajaIngreso cadaMovimiento : cadaDetalle.getMovimientosCajaIngreso()) {
					totalImputaciones.add(cadaMovimiento.getCuenta(), cadaMovimiento.getImporte());
				}
			}
			
			listaTicketsCaja.add(cadaTicket);
		}
		
		this.tipoPagoDS = new TiposPagoDS(mapaTiposPago);
		this.canceladosDS = new CanceladosDS(locListaTicketsCancelados);
		List<MovimientoCajaIngreso> listaTotalImputaciones = new ArrayList<MovimientoCajaIngreso>();
		for (Cuenta cadaCuenta : totalImputaciones.keySet()) {
			MovimientoCajaIngreso locImputacion = new MovimientoCajaIngreso();
			locImputacion.setCuenta(cadaCuenta);
			locImputacion.setImporte(totalImputaciones.get(cadaCuenta));
			listaTotalImputaciones.add(locImputacion);
		}
		
		parametros.put("PAR_LISTA_IMPUTACIONES", listaTotalImputaciones);
		parametros.put("TIPOS_PAGOS_DS", tipoPagoDS);
		parametros.put("CANCELADOS_DS", canceladosDS);
	}
	
	private String getNombreTipoPago(PagoTicket pPagoTicket){
		if (pPagoTicket instanceof PagoTicketEfectivo) {
			return "EFECTIVO";
		}
		if (pPagoTicket instanceof PagoTicketCheque) {
			return "CHEQUE";
		}
		if (pPagoTicket instanceof PagoTicketCompensacion) {
			return "COMPENSACION";
		}
		if (pPagoTicket instanceof PagoTicketDeposito) {
			return "DEPOSITO";
		}
		return "";
	}
	
	public abstract String getNombreReporte();
	
	public TiposPagoDS getTipoPagoDS() {
		return tipoPagoDS;
	}
	
	public CanceladosDS getCanceladosDS() {
		return canceladosDS;
	}
	
	class CanceladosDS implements JRDataSource, Serializable {

		private static final long serialVersionUID = 7405351023172707970L;
		private int lineaActual = -1;
		private List<TicketCaja> listaTicketsCaja;
		
		public CanceladosDS(List<TicketCaja> pListaCancelados){
			this.listaTicketsCaja = pListaCancelados;
		}

		@Override
		public Object getFieldValue(JRField field) throws JRException {
			if (field.getName().equals("F_NUMERO_TICKET")){
				return listaTicketsCaja.get(lineaActual).getNumero();
			}
			if (field.getName().equals("F_IMPORTE_TICKET")){
				return listaTicketsCaja.get(lineaActual).getImporteTotal();
			}
			if (field.getName().equals("F_FECHA_CANCELACION")){
				return listaTicketsCaja.get(lineaActual).getDetalles().iterator().next().getFechaCancelacion();
			}
			return "";
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaTicketsCaja.size();
		}
		
	}
	
	class TiposPagoDS implements JRDataSource, Serializable{
		private static final long serialVersionUID = 2868139162971633844L;
		
		private List<String> listaLlaves;
		private int lineaActual = -1;
		private THashMap<String> mapa;
		
		public TiposPagoDS(THashMap<String> pMapa){
			this.listaLlaves = new ArrayList<String>(pMapa.keySet());
			Collections.sort(listaLlaves);
			this.mapa = pMapa;
		}

		@Override
		public Object getFieldValue(JRField field) throws JRException {
			if (field.getName().equals("F_NOMBRE_TIPO_PAGO")){
				return listaLlaves.get(lineaActual);
			}
			if (field.getName().equals("F_MONTO_TIPO_PAGO")){
				return mapa.get(listaLlaves.get(lineaActual));
			}
			return null;
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaLlaves.size();
		}
		
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

}
