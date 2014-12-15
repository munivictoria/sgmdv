package com.trascender.compras.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class SolicitudSuministroDS extends TrascenderDataSource {
	
	private int lineaActual = -1;
	private SolicitudSuministro solicitudSuministro;
	private Usuario usuario;
	private List<LineaSolicitudSuministro> listaLineas;
	private Map<String, Object> mapaParametros;
	
	public SolicitudSuministroDS(SolicitudSuministro pSolicitud){
		solicitudSuministro = pSolicitud;
		usuario = solicitudSuministro.getUsuario();
		listaLineas = new ArrayList<LineaSolicitudSuministro>(pSolicitud.getListaLineaSolSuministro());
		this.armarParametros();
	}
	
	private void armarParametros(){
		this.mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		mapaParametros.put("PAR_TITULO", this.getTituloReporte());
		mapaParametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		mapaParametros.put("PAR_AREA_SOLICITUD", solicitudSuministro.getArea().toString());
		mapaParametros.put("PAR_USUARIO", usuario.getNombrePersonaFisica() + " " + "(" + usuario.getUser() + ")");
		mapaParametros.put("PAR_DESCRIPCION", Util.getFormatIfNull(solicitudSuministro.getDescripcion()));
		mapaParametros.put("PAR_NUMERO", solicitudSuministro.getNumero().toString());
		if (solicitudSuministro.getEstado() != null){
		mapaParametros.put("PAR_ESTADO", solicitudSuministro.getStringEstado());
		}else{
			mapaParametros.put("PAR_ESTADO", "-------");
		}
		mapaParametros.put("PAR_EMISION", solicitudSuministro.getFechaEmision());
		mapaParametros.put("PAR_CANTIDAD_PRODUCTO", solicitudSuministro.getListaLineaSolSuministro().size());

	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
	
		 if (field.getName().equals("F_PRODUCTO_SOLICITUD"))
		{
			valor = Util.getFormatIfNull(getLineaSolicitudActual().getBien().getNombre());
		} else if (field.getName().equals("F_CANTIDAD_SOLICITUD"))
		{
			valor = Util.getFormatIfNull(getLineaSolicitudActual().getCantidad());
		}else if (field.getName().equals("F_UNIDAD"))
		{
			valor= Util.getFormatIfNull(getLineaSolicitudActual().getBien().getUnidad().toString());
		}
		return valor;
	}
	
	private LineaSolicitudSuministro getLineaSolicitudActual(){
		return listaLineas.get(lineaActual);
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	public String getNombreReporte() {
		return "Reporte_Solicitud_Suministro.jasper";
	}

}
