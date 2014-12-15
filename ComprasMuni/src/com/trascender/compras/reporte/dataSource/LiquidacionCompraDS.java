package com.trascender.compras.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra.LineaLiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class LiquidacionCompraDS extends TrascenderDataSource{

	private int lineaActual = -1;
	private FacturaProveedor facturaProveedor;
	private OrdenCompra ordenCompra;
	private ArrayList <LineaLiquidacionCompra> listaLineasLiquidacionCompra = new ArrayList <LineaLiquidacionCompra>();
	private Map<String, Object> parametros = new HashMap<String, Object> ();
	private Double total = 0.00; 

	
	public LiquidacionCompraDS (LiquidacionCompra liquidacionCompra){
		
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("P_NRO_LIQUIDACION",liquidacionCompra.getNumero());
		parametros.put("PAR_FACTURA",liquidacionCompra.getStringListaFacturas());
		
		for (Factura cadaFactura : liquidacionCompra.getListaFacturas()){
			if (cadaFactura.getProveedor() != null){
			parametros.put("PAR_CODIGO", cadaFactura.getProveedor().getCodigo());
			parametros.put("PAR_PROVEEDOR", cadaFactura.getProveedor().toString());
			parametros.put("PAR_DOMICILIO",cadaFactura.getProveedor().getDomicilio().toString());
			parametros.put("PAR_CIUDAD",cadaFactura.getProveedor().getDomicilio().getLocalidad().getNombre().toString());
			}
			if (cadaFactura instanceof FacturaProveedor){
				facturaProveedor = (FacturaProveedor) cadaFactura;
				
				for (PagoOrdenCompra cadaPagoOrden : facturaProveedor.getListaPagosOrdenCompra()){
					ordenCompra = cadaPagoOrden.getOrdenCompra();
					
					for (LineaOrdenCompra cadaLineaOrdenCompra : ordenCompra.getListaLineaOrdenCompra()){
						
						for (LineaSolicitudSuministro cadaLineaSolicitudSuministro : cadaLineaOrdenCompra.getLineaContratacion().getListaLineasSolicitudSuministro()){
							parametros.put("PAR_SOLICITANTE", cadaLineaSolicitudSuministro.getArea().toString());
							parametros.put("PAR_PED_MATERIALES", cadaLineaSolicitudSuministro.getNumeroSolicitud().toString());
						}
					}
				}
				
			}
		}
			
			liquidacionCompra.recrearListaLineasLiquidacionCompra();
		for (LineaLiquidacionCompra cadaLineaLiquidacionCompra :liquidacionCompra.getListaLineasLiquidacionCompra()){
			listaLineasLiquidacionCompra.add(cadaLineaLiquidacionCompra);
			total = total + cadaLineaLiquidacionCompra.getMontoTotal();
		}
		parametros.put("PAR_TOTAL", total);
	}
	
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValue = new Object();		
		
		if ("F_ITEM".equals(arg0.getName())){
			locValue = Util.getFormatIfNull(lineaActual +1);
		}
		else if("F_CANT".equals(arg0.getName())){
			if (listaLineasLiquidacionCompra.get(lineaActual).getCantidad() != null){
			locValue = Util.getFormatIfNull(listaLineasLiquidacionCompra.get(lineaActual).getCantidad().toString());
			}else{
				locValue = "----";
			}
		} 
		else if("F_UNIT".equals(arg0.getName())){
			if (listaLineasLiquidacionCompra.get(lineaActual).getUnidadMedida() != null){
			locValue = Util.getFormatIfNull(listaLineasLiquidacionCompra.get(lineaActual).getUnidadMedida().getDescripcion());
			}else{
				locValue = "----";
			}
		}
		else if("F_DESCRIPCION".equals(arg0.getName())){
			if (listaLineasLiquidacionCompra.get(lineaActual).getNombre() != null){
			locValue = Util.getFormatIfNull(listaLineasLiquidacionCompra.get(lineaActual).getNombre());
			}
			else{
				locValue = "----";
			}
		}
		else if("F_P_UNITARIO".equals(arg0.getName())){
			if (listaLineasLiquidacionCompra.get(lineaActual).getMontoUnitario() != null){
				locValue = Util.getFormatIfNull(listaLineasLiquidacionCompra.get(lineaActual).getMontoUnitario());	
			}else{
			locValue = "----";
			}
		}
		else if("F_IMPORTE".equals(arg0.getName())){
			if (listaLineasLiquidacionCompra.get(lineaActual).getMontoTotal() != null){
				locValue = Util.getFormatIfNull(listaLineasLiquidacionCompra.get(lineaActual).getMontoTotal());	
			}else{
			locValue = "----";
			}
		}
	
		return locValue;
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaLineasLiquidacionCompra.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Liquidacion_Compra.jasper";
	}

}
