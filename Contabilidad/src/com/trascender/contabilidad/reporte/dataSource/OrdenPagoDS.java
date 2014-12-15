package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private OrdenPago locOrdenPago;
	
	public OrdenPagoDS(OrdenPago pOrdenPago) throws Exception{
		parametros = new HashMap<String, Object> ();
		
		locOrdenPago = pOrdenPago;
		Proveedor locProveedor = locOrdenPago.getProveedor();
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_ORDEN_P", locOrdenPago.getNumero());
		parametros.put("PAR_FECHA_EMISION_PAGO", locOrdenPago.getFechaEmision());
		parametros.put("PAR_FECHA_PAGO", locOrdenPago.getFechaPago());
		parametros.put("PAR_RAZON_SOCIAL_PROV", locProveedor.toString());
		parametros.put("PAR_CUIT_PROV", locProveedor.getProveedorLocal().getCuim());		
		parametros.put("PAR_CALLE_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getCalle()));
		parametros.put("PAR_NUMERO_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getNumero()));
		parametros.put("PAR_PISO_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getPiso()));
		parametros.put("PAR_DPTO_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getDepartamento()));
		parametros.put("PAR_COD_POSTAL_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getCodigoPostal()));
		parametros.put("PAR_LOCALIDAD_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getLocalidad().getNombre()));
		parametros.put("PAR_PROVINCIA_DOM", Util.getFormatIfNull(locProveedor.getDomicilio().getLocalidad().getProvincia().getNombre()));
		parametros.put("PAR_TELEFONO_PROV", Util.getFormatIfNull(locProveedor.getTelefono()));
		
		double locTotalCheque = 0d;
		double locTotalDebito = 0d;
		List<Cheque> locListaCheque = new ArrayList<Cheque>();
		List<Debito> locListaDebito = new ArrayList<Debito>();
		for (MovimientoBancario cadaMovimiento : locOrdenPago.getMovimientosBancarios()){
			if (cadaMovimiento instanceof Cheque){
				locListaCheque.add((Cheque) cadaMovimiento);
				locTotalCheque = locTotalCheque + cadaMovimiento.getImporte();
			} else if (cadaMovimiento instanceof Debito) {
				locListaDebito.add((Debito) cadaMovimiento);
				locTotalDebito = locTotalDebito + cadaMovimiento.getImporte();
			}
		}
		
		if(locListaCheque != null){
			OrdenPagoSubreporteChequeDS locSubreporteChequeDS = new OrdenPagoSubreporteChequeDS(locListaCheque);
			parametros.put("PAR_CHEQUE_DS", locSubreporteChequeDS);
		}
		if(locListaDebito != null){
			OrdenPagoSubreporteDebitoDS locSubreporteDebitoDS = new OrdenPagoSubreporteDebitoDS(locListaDebito);
			parametros.put("PAR_DEBITO_DS", locSubreporteDebitoDS);
		}
		if(locOrdenPago.getLineasOrdenPago() != null){
			OrdenPagoSubreporteCompraDS locSubreporteCompraDS = new OrdenPagoSubreporteCompraDS(new ArrayList(locOrdenPago.getLineasOrdenPago()));
			parametros.put("PAR_COMPRA_DS", locSubreporteCompraDS);
		}
		if(locOrdenPago.getComprobanteRetencion() != null)
		{
			OrdenPagoSubreporteRetencionDS locSubreporteRetencionDS = new OrdenPagoSubreporteRetencionDS(new ArrayList(locOrdenPago.getComprobanteRetencion().getLineaRetencion()));
			parametros.put("PAR_RETENCION_DS", locSubreporteRetencionDS);
		}
		parametros.put("PAR_SUMA_DEBITO", locTotalCheque);
		parametros.put("PAR_SUMA_CHEQUE", locTotalDebito);
		parametros.put("PAR_SUMA_COMPRA", this.montoTotalLista(locOrdenPago, "compra"));
		parametros.put("PAR_SUMA_RETENCION", this.montoTotalLista(locOrdenPago, "retencion"));
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
		return "Reporte_Orden_Pago.jasper";
	}
	
	private double montoTotalLista(OrdenPago pOrdenDePago, String tipo){
		double montoTotal = 0d;
		
		if(tipo.equals("retencion")){
			if(pOrdenDePago.getComprobanteRetencion() != null){
				for(LineaRetencion cadaLinea: pOrdenDePago.getComprobanteRetencion().getLineaRetencion()){
					montoTotal += cadaLinea.getImporte();
				}
			}
		}else
		{
			if(tipo.equals("compra")){
				if(pOrdenDePago.getLineasOrdenPago() != null){
					for(LineaOrdenPago cadaLinea: pOrdenDePago.getLineasOrdenPago()){
						montoTotal += cadaLinea.getImporte();
					}
				}
			}
		}
		
		return montoTotal;
	}

}
