package com.trascender.contabilidad.reporte.dataSource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.helpers.FileUtils;
import org.apache.velocity.texen.util.FileUtil;
import org.jboss.util.file.Files;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.THashMap;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.interfaces.Pagable;

public class PlanillaDiariaCajaIngresoVarioDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private Map<ConceptoIngresoVario, SubreporteDataSource> filas = new HashMap<ConceptoIngresoVario, PlanillaDiariaCajaIngresoVarioDS.SubreporteDataSource>();
	private JasperReport jasperReport;
	private List<ConceptoIngresoVario> listaLlaves;
	private THashMap<Cuenta> totalImputaciones;
	
	public PlanillaDiariaCajaIngresoVarioDS(List<TicketCaja> plistaTicketCaja) throws Exception{
		parametros = new HashMap<String, Object> ();
		totalImputaciones = new THashMap<Cuenta>();
		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		File fileReporte = new File(rutaReportes + "Reporte_Planilla_Diaria_Caja_Ingresos_Varios.jasper");
		
		jasperReport = (JasperReport) JRLoader.loadObject(fileReporte);
		jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		
		Pagable locDeuda = null;
		for (TicketCaja cadaTicketCaja : plistaTicketCaja){
			locDeuda = cadaTicketCaja.getDetalles().iterator().next().getDeuda();
			IngresoVario locIngreso = (IngresoVario) locDeuda;
			SubreporteDataSource ds = filas.get(locIngreso.getConceptoIngresoVario());
			if (ds == null) {
				ds = new SubreporteDataSource(locIngreso.getConceptoIngresoVario());
				filas.put(locIngreso.getConceptoIngresoVario(), ds);
			}
			ds.addIngreso(locIngreso, cadaTicketCaja);
		}
		listaLlaves = new ArrayList<ConceptoIngresoVario>(filas.keySet());
		
		List<ImputacionIngresoVario> listaTotalImputaciones = new ArrayList<ImputacionIngresoVario>();
		for (Cuenta cadaCuenta : totalImputaciones.keySet()) {
			ImputacionIngresoVario locImputacion = new ImputacionIngresoVario();
			locImputacion.setCuenta(cadaCuenta);
			locImputacion.setMonto(totalImputaciones.get(cadaCuenta));
			listaTotalImputaciones.add(locImputacion);
		}
		parametros.put("PAR_LISTA_IMPUTACIONES", listaTotalImputaciones);
	}
	
	public Object getFieldValue(JRField field) throws JRException {
		ConceptoIngresoVario llave = listaLlaves.get(lineaActual);
		if (field.getName().equals("CONCEPTO_DS")) return filas.get(llave);
		if (field.getName().equals("SUBREPORTE")) return jasperReport;
		if (field.getName().equals("CONCEPTO")) return llave.getNombre();
 		return null;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < filas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "ReportePlanillaDiariaCajaIngresosVariosMaestro.jasper";
	}
	
	class SubreporteDataSource implements JRDataSource {

		private List<Map<String, Object>> filas = new ArrayList<Map<String,Object>>();
		private ConceptoIngresoVario concepto;
		private int lineaActual = -1;
		
		public SubreporteDataSource(ConceptoIngresoVario concepto) {
			this.concepto = concepto;
		}

		public void addIngreso(IngresoVario locIngreso, TicketCaja cadaTicketCaja) {
			for (ImputacionIngresoVario cadaImputacion : locIngreso.getListaImputacionIngresos()) {
				Map<String, Object> locMapa = new HashMap<String, Object>();
				locMapa.put("F_CONCEPTO", locIngreso.getConceptoIngresoVario().getNombre());
				locMapa.put("F_CUENTA", cadaImputacion.getCuenta().getNombre());
				locMapa.put("F_INGRESO", locIngreso.getNumero());
				locMapa.put("F_MONTO_IMPUTACION", cadaImputacion.getMonto());
				locMapa.put("F_NRO_TICKET", cadaTicketCaja.getNumero());
				
				String cuit = cadaTicketCaja.getStringPersona();
				int pos = cuit.lastIndexOf("[");
				String nombre = cuit.substring(0, pos-1);
				cuit = cuit.substring(pos +1, cuit.length() -1);
				locMapa.put("F_PERSONA", nombre);
				locMapa.put("F_CUIT", cuit);
				
				totalImputaciones.add(cadaImputacion.getCuenta(), cadaImputacion.getMonto());
				
				filas.add(locMapa);
			}
		}

		@Override
		public Object getFieldValue(JRField arg0) throws JRException {
			return filas.get(lineaActual).get(arg0.getName());
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < filas.size();
		}
		
	}

}
