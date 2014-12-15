package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

/**
 * Reporte diario de lo cobrado por una caja.
 * @author fer
 *
 */
public class ReporteCajaDinamico {
	
	private Map<String, Object> mapaParametros;
	private DynamicReportBuilder drbPrincipal;
	
	private Style styleColumnas;
	
	public ReporteCajaDinamico(Caja pCaja, List<TicketCaja> pListaTickets){
		this.init();
		this.procesarListaTickets(pListaTickets);
	}
	
	private void init(){
		drbPrincipal = new FastReportBuilder();
		drbPrincipal.setWhenNoDataType(DJConstants.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL);
		mapaParametros = new HashMap<String, Object>();
		//Style comunes a todas las columnas
		styleColumnas = new Style();
		styleColumnas.setFont(new Font(8, "Arial", false));
        styleColumnas.setHorizontalAlign(HorizontalAlign.CENTER);
	}
	
	private void procesarListaTickets(List<TicketCaja> pListaTickets) {
		Map<String, List<TicketCaja>> locMapa = this.separarTicketsPorTipoObligacion(pListaTickets);
		List<String> listaLlaves = new ArrayList<String>(locMapa.keySet());
		Collections.sort(listaLlaves);
		for (String cadaLlave : listaLlaves) {
			List<TicketCaja> locLista = locMapa.get(cadaLlave);
			DynamicReportBuilder drbSubreporte = armarModeloSubreporte(locLista);
			JRDataSource dsSubreporte = armarDataSourceSubreporte(locLista);
			DynamicReport dr = drbSubreporte.build();
			drbPrincipal.addConcatenatedReport(dr, new ClassicLayoutManager(), "datasource"+cadaLlave, DJConstants.SUBREPORT_PARAMETER_MAP_ORIGIN_PARAMETER,
	        		DJConstants.DATA_SOURCE_TYPE_JRDATASOURCE, false);
			mapaParametros.put("datasource"+cadaLlave, dsSubreporte);
		}
	}
	
	private Map<String, List<TicketCaja>> separarTicketsPorTipoObligacion(List<TicketCaja> pListaTickets){
		Map<String, List<TicketCaja>> locMapa = new HashMap<String, List<TicketCaja>>();
		for (TicketCaja cadaTicket : pListaTickets){
			String locNombreObligacion = getTipoObligacion(cadaTicket);
			List<TicketCaja> locLista = locMapa.get(locNombreObligacion);
			if (locLista == null) {
				locLista = new ArrayList<TicketCaja>();
			}
			locLista.add(cadaTicket);
			locMapa.put(locNombreObligacion, locLista);
		}
		return locMapa;
	}
	
	private String getTipoObligacion(TicketCaja pTicket){
		LiquidacionTasa unaLiquidacion = (LiquidacionTasa) pTicket.getDetalles().iterator().next().getDeuda();
		TipoObligacion locTipoObligacion = unaLiquidacion.getTipoTasa().getTipoObligacion();
		if (locTipoObligacion.getNombre().equals("OYSP")
				|| locTipoObligacion.getNombre().equals("TGI")) {
			return "Servicios Municipales";
		}
		return locTipoObligacion.getNombre();
	}
	
	private JRDataSource armarDataSourceSubreporte(List<TicketCaja> pListaTickets){
		JRDataSource ds = new LiquidacionDS(pListaTickets);
		return ds;
	}
	
	private DynamicReportBuilder armarModeloSubreporte(List<TicketCaja> pListaTickets){
		//Columnas comunes a todas las tasas
		FastReportBuilder drb = new FastReportBuilder();
		drb.addColumn(getColumnaNumeroTicket());
		drb.addColumn(getColumnaCuit());
		drb.addColumn(getColumnaNombrePersona());
		drb.addColumn(getColumnaNumeroCuenta());
		//1 o mas tasas
		//Intereses
		//Todos los modificadores.
		//Total del ticket.
		return drb;
	}
	
	private AbstractColumn getColumnaNumeroCuenta(){
		return ColumnBuilder.getNew()
			.setColumnProperty("NUMERO_CUENTA", String.class.getName())
			.setTitle("Nº Insc/Cuenta")
			.setStyle(styleColumnas)
			.setWidth(new Integer(40))
			.build();
	}
	
	private AbstractColumn getColumnaNombrePersona(){
		return ColumnBuilder.getNew()
			.setColumnProperty("NOMBRE_PERSONA", String.class.getName())
			.setTitle("Nombre")
			.setStyle(styleColumnas)
			.setWidth(new Integer(100))
			.build();
	}
	
	private AbstractColumn getColumnaCuit(){
		return ColumnBuilder.getNew()
			.setColumnProperty("CUIT", String.class.getName())
			.setTitle("CUIT")
			.setStyle(styleColumnas)
			.setWidth(new Integer(75))
			.build();
	}

	private AbstractColumn getColumnaNumeroTicket(){
		return ColumnBuilder.getNew()
			.setColumnProperty("NRO_TICKET", Integer.class.getName())
			.setTitle("Ticket")
			.setStyle(styleColumnas)
			.setWidth(new Integer(40))
			.build();
	}
	
	public JasperPrint getJasperPrint() throws JRException{
		DynamicReport dr = drbPrincipal.build();
		return DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), mapaParametros);
	}
	
	class LiquidacionDS implements JRDataSource{
		
		List<Map<String, Object>> listaMapaDatos;
		int i = -1;
		
		public LiquidacionDS(List<TicketCaja> pListaTicketCaja){
			listaMapaDatos = new ArrayList<Map<String,Object>>();
			for (TicketCaja cadaTicket : pListaTicketCaja){
				Map<String, Object> mapaDatos = new HashMap<String, Object>();
				mapaDatos.put("NRO_TICKET", cadaTicket.getNumero());
				mapaDatos.put("CUIT", "20-34014173-4");
				mapaDatos.put("NOMBRE_PERSONA", "Fernando Gareis");
				mapaDatos.put("NUMERO_CUENTA", "5353");
				listaMapaDatos.add(mapaDatos);
			}
		}
		
		@Override
		public Object getFieldValue(JRField field) throws JRException {
			Map<String, Object> locMapa = listaMapaDatos.get(i);
			return locMapa.get(field.getName());
		}

		@Override
		public boolean next() throws JRException {
			return ++i < listaMapaDatos.size();
		}
	}
	

}
