package com.trascender.compras.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.MultiMap;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;


public class OrdenCompraDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private ArrayList <LineaOrdenCompra> listaLineas = new ArrayList <LineaOrdenCompra>();
	private Set <String> setNroSolicitud = new HashSet <String>();
	private Set <String> setAreaSolicitud = new HashSet <String>();
	private Map<String, Object> parametros = new HashMap<String, Object> ();
	private String cadenaCadaArea="";
	private String cadenaNroSolicitud="";
//	private Map<Area, LineaImputacion> mapaImputaciones = new HashMap<Area, OrdenCompraDS.LineaImputacion>();
	private MultiMap<Area, CuentaRfr, LineaImputacion> mapaImputaciones = new MultiMap<Area, CuentaRfr, OrdenCompraDS.LineaImputacion>();
	
	public OrdenCompraDS(OrdenCompra orden){
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());		
		parametros.put("PAR_NUMERO_COMPRA", orden.getNumero());
		parametros.put("PAR_DESCRIPCION_COMPRA", Util.getFormatIfNull(orden.getDescripcion()));
		parametros.put("PAR_EMISION_COMPRA", orden.getFechaEmision());
		parametros.put("PAR_RAZON_PROVEEDOR", Util.getFormatIfNull(orden.getProveedor().toString()));	
		parametros.put("PAR_DOMICILIO", Util.getFormatIfNull(orden.getProveedor().getDomicilio().toString()));
		parametros.put("PAR_LOCALIDAD", Util.getFormatIfNull(orden.getProveedor().getDomicilio().getLocalidad().toString()));
		parametros.put("PAR_TELEFONO", Util.getFormatIfNull(orden.getProveedor().getTelefono()));
		parametros.put("PAR_CUIT", Util.getFormatIfNull(orden.getProveedor().getCuit()));
		
		Contratacion locContratacion= orden.getListaLineaOrdenCompra().iterator().next().getLineaContratacion().getContratacion();
		parametros.put("PAR_EXPEDIENTE", locContratacion.getNumeroExpediente());
		parametros.put("PAR_LICITACION", locContratacion.getTipo().toString());
		parametros.put("PAR_NRO_ORDENANZA", locContratacion.getDigestoMunicipal() == null ? null : locContratacion.getDigestoMunicipal().getNumero());
		if (orden.getProveedor().getProveedorLocal() instanceof PersonaJuridica) {
			parametros.put("PAR_ING_BRUTOS", ((PersonaJuridica) orden.getProveedor().getProveedorLocal()).getNumeroIngresosBrutos());
		} else {
			parametros.put("PAR_ING_BRUTOS", "----------");
		}
		parametros.put("PAR_FECHA_ENTREGA", locContratacion.getFechaEntrega());
	

//		parametros.put("PAR_DESCUENTOS", "-----");
//		parametros.put("PAR_TIPO_COMPRA", );
//		parametros.put("PAR_FECHA_ENTREGA", );
		
		
		for (LineaOrdenCompra cadaLineaOrdenCompra : orden.getListaLineaOrdenCompra()){
			listaLineas.add(cadaLineaOrdenCompra);
			for (LineaSolicitudSuministro cadaLineaSolicitud : cadaLineaOrdenCompra.getLineaContratacion().getListaLineasSolicitudSuministro()){
				setNroSolicitud.add(cadaLineaSolicitud.getNumeroSolicitud().toString());
				setAreaSolicitud.add(cadaLineaSolicitud.getArea().toString());
			}
			procesarImputaciones(cadaLineaOrdenCompra);
		}
		
		for (String cadaNroSolicitud : setNroSolicitud){
				cadenaNroSolicitud += " - " + cadaNroSolicitud;
		}
		cadenaNroSolicitud = cadenaNroSolicitud.substring(3, cadenaNroSolicitud.length());
		parametros.put("PAR_NRO_PEDIDO", cadenaNroSolicitud);
		
		for (String cadaArea : setAreaSolicitud){
				cadenaCadaArea += "," + cadaArea;
		}
		cadenaCadaArea = cadenaCadaArea.substring(1, cadenaCadaArea.length());
		parametros.put("PAR_AREA", cadenaCadaArea);
		
		List<LineaImputacion> locListaImputaciones = 
				new ArrayList<OrdenCompraDS.LineaImputacion>(this.mapaImputaciones.values());
		ImputacionesDS locImputacionesDS = new ImputacionesDS(locListaImputaciones);
		parametros.put("PAR_IMPUTACIONES_DS", locImputacionesDS);
	}
	
	private void procesarImputaciones(LineaOrdenCompra pLinea){
		for (LineaSolicitudSuministro cadaLineaSS : pLinea.getLineaContratacion().getListaLineasSolicitudSuministro()){
			Area area = cadaLineaSS.getSolicitudSuministro().getArea();
			CuentaRfr cuenta = cadaLineaSS.getCuenta();
			if (cuenta == null) continue;
			LineaImputacion locLineaImputacion = this.mapaImputaciones.get(area, cuenta);
			if (locLineaImputacion == null) {
				locLineaImputacion = new LineaImputacion();
				locLineaImputacion.setCuenta(cadaLineaSS.getCuenta());
				locLineaImputacion.setArea(cadaLineaSS.getSolicitudSuministro().getArea());
			}
			Double valorParaEstaLinea = pLinea.getMontoUnitario() * cadaLineaSS.getCantidad();
			locLineaImputacion.setValor(locLineaImputacion.getValor() + valorParaEstaLinea);
			this.mapaImputaciones.put(area, cuenta, locLineaImputacion);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValue = new Object();		
	
		if("F_NOMBRE_PEDIDO".equals(arg0.getName())){
			locValue = Util.getFormatIfNull(listaLineas.get(lineaActual).getBien().getNombre());
		}
		else if("F_VALOR_SOLICITUD".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getMontoTotal();
		}
		else if("F_CANTIDAD".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getCantidad().toString();
		}
		else if("F_UNIDAD_MEDIDA".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getBien().getUnidad().toString();
		}
		else if("F_DETALLE".equals(arg0.getName())){
			if (listaLineas.get(lineaActual).getBien().getDescripcion() != null){
			locValue =  listaLineas.get(lineaActual).getBien().getDescripcion();
			}else{
				locValue = "-----------";
			}
		}
		else if("F_PRECIO_UNITARIO".equals(arg0.getName())){
			locValue =  listaLineas.get(lineaActual).getMontoUnitario().toString();
		}
		else if("F_IVA".equals(arg0.getName())){
			locValue = "---------";
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
		return "Reporte_Orden_de_Compra.jasper";
	}

	
	class LineaImputacion {
		private Area area;
		private CuentaRfr cuenta;
		private Double valor = new Double(0);
		public Area getArea() {
			return area;
		}
		public void setArea(Area area) {
			this.area = area;
		}
		public CuentaRfr getCuenta() {
			return cuenta;
		}
		public void setCuenta(CuentaRfr cuenta) {
			this.cuenta = cuenta;
		}
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
	}
	
	class ImputacionesDS implements JRDataSource {
		
		private List<LineaImputacion> lineaImputaciones;
		private int lineaActual = -1;
		
		public ImputacionesDS(List<LineaImputacion> pLista){
			lineaImputaciones = pLista;
		}
		
		public Object getFieldValue(JRField field) throws JRException {
			LineaImputacion locLinea = this.lineaImputaciones.get(lineaActual);
			if (field.getName().equals("F_CODIGO_IMPUTACION")) {return locLinea.getCuenta().getCodigoImputacionCompleto();}
			if (field.getName().equals("F_NOMBRE_CUENTA")){return locLinea.getCuenta().getNombre();}
			if (field.getName().equals("F_SECRETARIA")){return locLinea.getArea().getSecretaria().getNombre();}
			if (field.getName().equals("F_AREA")){return locLinea.getArea().getNombre();}
			if (field.getName().equals("F_VALOR")){return locLinea.getValor();}
			return null;
		}

		public boolean next() throws JRException {
			return ++this.lineaActual < lineaImputaciones.size();
		}
		
	}


}
