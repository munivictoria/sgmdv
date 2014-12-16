package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class ReconocimientoDeudaDS extends TrascenderDataSource{
	
	private DocumentoRefinanciacion documentoRefinanciacion;
	private Map<String, Object> mapaParametros = new HashMap<String, Object>();
	
	public ReconocimientoDeudaDS(DocumentoRefinanciacion pDocumentoRefinanciacion){
		this.documentoRefinanciacion = pDocumentoRefinanciacion;
		this.armarParametros();
	}
	
	private void armarParametros(){
		mapaParametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		mapaParametros.put("PAR_TITULO", this.getTituloReporte());
		mapaParametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		mapaParametros.put("PAR_FACILIDADES_PAGO", this.documentoRefinanciacion.getStringNombreRefinanciacion());
		mapaParametros.put("PAR_NRO_REFINANCIACION", this.documentoRefinanciacion.getNumeroRefinanciacion());
		mapaParametros.put("PAR_DIGESTO", this.documentoRefinanciacion.getRegCancelacionPorRefinanciacion().getDigestoMunicipal().toString());
		mapaParametros.put("PAR_CONTRIBUYENTE", this.documentoRefinanciacion.getObligacion().getPersona().toString());
		mapaParametros.put("PAR_INMUEBLES", this.documentoRefinanciacion.getStringInmuebles());
		mapaParametros.put("PAR_COMERCIOS", Util.getFormatIfNull(this.documentoRefinanciacion.getStringComercios()));
		mapaParametros.put("PAR_CANTIDAD_CUOTAS", this.documentoRefinanciacion.getCantidadCuotas());
		mapaParametros.put("PAR_MONTO_CUOTAS", this.documentoRefinanciacion.getMontoMinimoPorCuota());
		mapaParametros.put("PAR_FECHA_REFINANCIACION", this.documentoRefinanciacion.getObligacion().getDocumentoEspecializado().getFechaCreacion());
		mapaParametros.put("PAR_MES_AÑO_INICIO", this.documentoRefinanciacion.getStringMesAnioInicio());
		mapaParametros.put("PAR_MODIFICADORES_DS", new DetalleLiquidacionDS(this.documentoRefinanciacion));
		mapaParametros.put("PAR_SUBTOTAL", new DetalleLiquidacionDS().getSubTotal());
		mapaParametros.put("PAR_SALDO_FAVOR", new DetalleLiquidacionDS().getSaldoFavor());
		mapaParametros.put("PAR_TOTAL_ADEUDADO", new DetalleLiquidacionDS().getTotalDeuda());
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return mapaParametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Reconocimiento_Deudas.jasper";
	}
	
	public static class DetalleLiquidacionDS implements JRDataSource {
		
		private DocumentoRefinanciacion documentoRefinanciacion;
		private HashMap<String,Double> listaMontosDeudas = new HashMap<String, Double>();
		private List<String> listaOrdenAniosRef = new ArrayList<String>();
		private int lineaActual = -1;
		private List<String> listaLlaves = new LinkedList<String>();
		private List<Object> listaValores = new LinkedList<Object>();
		
		private static double locSubtotal;
		private static double locSaldoFavor;
		
		public double getSubTotal(){
			return locSubtotal;
		}
		public double getSaldoFavor(){
			return locSaldoFavor;
		}
		
		
		public DetalleLiquidacionDS(){
			
		}
		
		public DetalleLiquidacionDS(DocumentoRefinanciacion pDocumentoRefinanciacion){
			this.documentoRefinanciacion = pDocumentoRefinanciacion;
			
			this.listaMontosDeudas.put("Tasa General Inmobiliaria", 0d);
			this.listaMontosDeudas.put("Obras y Servicios Públicos", 0d);
			this.listaMontosDeudas.put("Planes de Financiación de Obras", 0d);
			this.listaMontosDeudas.put("Tasa de Salud, Higiene, Profilaxis y Seguridad", 0d);

			for(RegistroDeuda cadaRegistroDeuda : this.documentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
				cadaRegistroDeuda.toString();
				if(cadaRegistroDeuda.getTipoDeuda().equals(TipoDeuda.REFINANCIACION)){
					Calendar locCalendar = Calendar.getInstance();
					locCalendar.setTime(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getFechaCreacion());
					Integer locAnio = locCalendar.get(Calendar.YEAR);
					this.listaOrdenAniosRef.add(locAnio.toString());
					if(this.listaMontosDeudas.containsKey(locAnio)){
						this.listaMontosDeudas.put(locAnio.toString(), this.listaMontosDeudas.get(locAnio)+cadaRegistroDeuda.getMonto());
					}
					else{
						this.listaMontosDeudas.put(locAnio.toString(), cadaRegistroDeuda.getMonto());
					}
				}
				else{
					String locTipoDocumento = "";
					if(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoTGI){
						locTipoDocumento = "Tasa General Inmobiliaria";
					}
					else if(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoOSP){
						locTipoDocumento = "Obras y Servicios Públicos";
					}
					else if(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra){
						locTipoDocumento = "Planes de Financiación de Obras";
					}
					else if(cadaRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoSHPS){
						locTipoDocumento = "Tasa de Salud, Higiene, Profilaxis y Seguridad";
					}
					
					if(locTipoDocumento != null && !locTipoDocumento.trim().isEmpty()){
						if(this.listaMontosDeudas.containsKey(locTipoDocumento)){
							this.listaMontosDeudas.put(locTipoDocumento, this.listaMontosDeudas.get(locTipoDocumento)+cadaRegistroDeuda.getMonto());
						}
						else{
							this.listaMontosDeudas.put(locTipoDocumento, cadaRegistroDeuda.getMonto());
						}
					}
				}
				
			}
			String[] locListaOrdenAniosRef = new String[this.listaOrdenAniosRef.size()];
			this.listaOrdenAniosRef.toArray(locListaOrdenAniosRef);
			this.ordenarArrayAniosRef(locListaOrdenAniosRef);
			
			for (Entry cadaLinea :listaMontosDeudas.entrySet())
			{
				listaLlaves.add((String)cadaLinea.getKey());
				listaValores.add(cadaLinea.getValue());
			}
			
			locSubtotal = this.getSubtotalDeudas();
			
			if(this.documentoRefinanciacion.getSaldoAFavor() != null){
				locSaldoFavor = this.documentoRefinanciacion.getSaldoAFavor();
			}
		}

		@Override
		public Object getFieldValue(JRField field) throws JRException {
			Object valor = null;
			if (field.getName().equals("F_MODIFICADOR1")){
				valor = listaLlaves.get(lineaActual);
			} else if (field.getName().equals("F_MONTO_MODIFICADOR1")){
				valor = listaValores.get(lineaActual);
			}
			return valor;
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaLlaves.size();
		}
		
		private void ordenarArrayAniosRef(String[] pListaOrdenAniosRef) {
			java.util.Arrays.sort(pListaOrdenAniosRef, new  Comparator<String>(){
				public int compare(String o1, String o2) {
					int resultado=o1.compareTo(o2);
					return resultado;

				}
			}
			);
		}
		
		public double getSubtotalDeudas(){
			for(int i = 0; i < listaValores.size(); i++){
				double valor = Double.parseDouble(listaValores.get(i).toString());
				locSubtotal = locSubtotal + valor;
			}
			return locSubtotal;
		}
		
		public double getSaldoAFavor(){
			if(this.documentoRefinanciacion.getSaldoAFavor()!=null){
				locSaldoFavor = this.documentoRefinanciacion.getSaldoAFavor();
			}
			return locSaldoFavor;
		}
		
		public double getTotalDeuda(){		
			return getSubTotal() - getSaldoFavor();
		}
	}

}
