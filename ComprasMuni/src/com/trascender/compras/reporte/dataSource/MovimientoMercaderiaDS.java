package com.trascender.compras.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class MovimientoMercaderiaDS extends TrascenderDataSource{

	private int lineaActual = -1;
	private ArrayList < LineaMovimientoMercaderia> listaLineas = new ArrayList <LineaMovimientoMercaderia>();
	
	private Map<String, Object> parametros = new HashMap<String, Object> ();
	
	
	public MovimientoMercaderiaDS(MovimientoDeMercaderia movimientoMercaderia){
	
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", "Movimiento de Mercaderia");
		parametros.put("PAR_USUARIO", movimientoMercaderia.getUsuario().getNombrePersonaFisica() + " " +"("+movimientoMercaderia.getUsuario().toString()+")" );
		parametros.put("PAR_DEPOSITO", movimientoMercaderia.getDeposito().toString());
		parametros.put("PAR_DEPOSITO_DESTINO", movimientoMercaderia.getDepositoDestino()  == null ? "--------" : movimientoMercaderia.getDepositoDestino().toString());
		parametros.put("PAR_MOTIVO", movimientoMercaderia.getMotivo());
		parametros.put("PAR_FECHA_EMISION", movimientoMercaderia.getFechaDate() == null ? "--------" : movimientoMercaderia.getFechaDate());
		
		for (LineaMovimientoMercaderia cadaLineaMovimientoMercaderia : movimientoMercaderia.getListaLineasMovimientoMercaderia()){
			listaLineas.add(cadaLineaMovimientoMercaderia);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValue = new Object();		
	
		if("F_NUMERO".equals(arg0.getName())){
			if (listaLineas.get(lineaActual).getLineaSolicitudSuministro().getNumeroSolicitud().toString() != null){
			locValue = "Solicitud: " + Util.getFormatIfNull(listaLineas.get(lineaActual).getLineaSolicitudSuministro().getNumeroSolicitud().toString());
			}else{
				locValue = "Orden de compra: " + Util.getFormatIfNull(listaLineas.get(lineaActual).getLineaOrdenCompra().getOrdenCompra().getNumero().toString());
			}
		}
		
		if("F_NOMBRE_PRODUCTO".equals(arg0.getName())){
			locValue = Util.getFormatIfNull(listaLineas.get(lineaActual).getBien().getNombre());
		}
		else if("F_CANTIDAD".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getCantidad().toString();
		}
		else if("F_UNIDAD_MEDIDA".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getBien().getUnidad().toString();
		}
		return locValue;
		
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "Reporte_Movimiento_Mercaderia.jasper";
	}

}
