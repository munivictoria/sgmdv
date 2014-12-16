package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.Tasa;
import com.trascender.saic.recurso.transients.HistorialPagosTasas;
import com.trascender.saic.recurso.transients.LineaHistorialPago;

public class ServiciosPublicosDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LiquidacionTasa> locListaLiquidacionesTasa;
	private BusinessLiquidacionTasaLocal businessLiquidacionTasa;
	
	public ServiciosPublicosDS(List<LiquidacionTasa> pListaLiquidacion, BusinessLiquidacionTasaLocal pBusinessLiquidacion){
		businessLiquidacionTasa = pBusinessLiquidacion;
		parametros = new HashMap<String, Object> ();
		locListaLiquidacionesTasa = pListaLiquidacion;
		this.armarParametros();
	}
	
	private void armarParametros(){
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_ENCABEZADO", "SERVICIOS MUNICIPALES");
		
		for (int i = 1 ; i < locListaLiquidacionesTasa.size() + 1 ; i++){
			LiquidacionTasa cadaLiquidacion = locListaLiquidacionesTasa.get(i - 1);
			
			parametros.put("PAR_PERIODO", Util.getFormatIfNull(cadaLiquidacion.getCuotaLiquidacion().toString()));
			DocHabilitanteEspecializado locDocHabilitanteEspecializado = cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			Persona locPersona = locDocHabilitanteEspecializado.getObligacion().getPersona();
			RegistroValuado locRegistroValuado = null;
			DocumentoOSP locDocumentoOSP = null;
			DocumentoTGI locDocumentoTGI = null;
			if(locDocHabilitanteEspecializado instanceof DocumentoOSP){
				locDocumentoOSP = (DocumentoOSP) cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				//Se sabe que existe solo un registro valuado para la tasa OSP
				locRegistroValuado= locDocumentoOSP.getRegistroValuado(cadaLiquidacion.getCuotaLiquidacion()).get(0);
			}else if(locDocHabilitanteEspecializado instanceof DocumentoTGI){
				locDocumentoTGI = (DocumentoTGI) cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			}
			
			parametros.put("PAR_NOMBRE", Util.getFormatIfNull(locPersona.getDenominacion()));
			
			Domicilio domicilioPostal = locDocHabilitanteEspecializado.getDomicilio();
			System.out.println(domicilioPostal);
			
			String locStringDomicilio = ((domicilioPostal.getCalle()!=null)?domicilioPostal.getCalle():"")+" "+((domicilioPostal.getNumero()!=null)?domicilioPostal.getNumero():"");
			parametros.put("PAR_DOMICILIO", Util.getFormatIfNull(locStringDomicilio));
			
			String locCodigoP = domicilioPostal.getCodigoPostal();
			Localidad locLocal = domicilioPostal.getLocalidad();
			Provincia locProv = domicilioPostal.getLocalidad().getProvincia();
			String par_datos_localidad = "(" + locCodigoP + ") " + locLocal.getNombre() + " - " + locProv.getNombre();
			parametros.put("PAR_DATOS_LOCALIDAD", par_datos_localidad);
		
			parametros.put("PAR_CUIT", Util.getFormatIfNull(locPersona.getCuim()));
			parametros.put("PAR_CUENTA", Util.getFormatIfNull(null));
			parametros.put("PAR_PERIODO_ANTICIPO", Util.getFormatIfNull(cadaLiquidacion.getCuotaLiquidacion().getNombre()));
			parametros.put("PAR_AÑO", Util.getFormatIfNull(cadaLiquidacion.getCuotaLiquidacion().getAnio()));
			
			Parcela locParcela = locDocHabilitanteEspecializado.getParcela();
			if (locParcela != null){
				//Datos catastrales			
				if(locParcela.getDomicilioParcelario().getCalle() != null){
					String locCalle = locParcela.getDomicilioParcelario().getCalle();
					String locNumero = locParcela.getDomicilioParcelario().getNumero();
					parametros.put("PAR_DOM_PARCELARIO", Util.getFormatIfNull(locCalle) + " " + Util.getFormatIfNull(locNumero));
				}else{
					parametros.put("PAR_DOM_PARCELARIO", " ");
				}
				parametros.put("PAR_REGISTRO_TGI" , Util.getFormatIfNull(locParcela.getNomenclaturaCatastral().getNroParcela()));
				Manzana locManzana = locParcela.getManzana();
				if(locManzana != null){
					parametros.put("PAR_MANZANA", Util.getFormatIfNull(locParcela.getManzana().getNroManzana()));
				}else{
					parametros.put("PAR_MANZANA", "---");
				}
				parametros.put("PAR_SECCION", Util.getFormatIfNull(locParcela.getNomenclaturaCatastral().getSeccion()));
				parametros.put("PAR_PARTIDA", Util.getFormatIfNull(locParcela.getNroPartidaProvincial()));
				Zona locZona = locParcela.getListaAsociacionParcela().get(0).getZona();
				if(locZona != null){
					parametros.put("PAR_ZONA", Util.getFormatIfNull(locZona.getNombre()));
				}else{
					parametros.put("PAR_ZONA", "---");
				}
				parametros.put("PAR_FRENTE", locParcela.getMetrosFrente() != null ? locParcela.getMetrosFrente() : 0d);
				parametros.put("PAR_SUPERFICIE_TERRENO", locParcela.getSuperficie() != null ? locParcela.getSuperficie() : 0d);
				parametros.put("PAR_SUPERFICIE_MEJORAS", locParcela.getSuperficieMejoras() != null ? locParcela.getSuperficieMejoras() : 0d);
				parametros.put("PAR_AVALUO_TERRENO", locParcela.getAvaluoTerreno() != null ? locParcela.getAvaluoTerreno() : 0d);
				parametros.put("PAR_AVALUO_MEJORAS", locParcela.getAvaluoPorMejoras() != null ? locParcela.getAvaluoPorMejoras() : 0d);
				
				//Datos del documento (OSP / TGI)
				if(locDocumentoOSP != null){
					parametros.put("PAR_CUENTA_OSM", locDocumentoOSP.getNumeroCuenta());
					RegAlicuota locRegistroAlicuota = null;//locDocumentoOSP.getRegistroAlicuota();
					if(locRegistroAlicuota != null){
//						parametros.put("PAR_CODIGO_SERVICIO", Util.getFormatIfNull(locDocumentoOSP.getRegistroAlicuota().getCodigo()));
//						parametros.put("PAR_TIPO_SERVICIO_OSM", Util.getFormatIfNull(locDocumentoOSP.getRegistroAlicuota().getNombre()));
						parametros.put("PAR_NUM_MEDIDOR", Util.getFormatIfNull(locDocumentoOSP.getCodigoMedidor()));
					}else{
						parametros.put("PAR_CODIGO_SERVICIO", Util.getFormatIfNull(null));
						parametros.put("PAR_TIPO_SERVICIO_OSM", Util.getFormatIfNull(null));
						parametros.put("PAR_NUM_MEDIDOR", Util.getFormatIfNull(null));
					}
				}else if(locDocumentoTGI != null){
					parametros.put("PAR_CUENTA_OSM", 0);
					//Esto es codigo muerto, TGI no tiene registro alicuota.
//					RegAlicuota locRegistroAlicuota = locDocumentoTGI.getRegistroAlicuota();
//					if(locRegistroAlicuota != null){
//						parametros.put("PAR_CODIGO_SERVICIO", Util.getFormatIfNull(locDocumentoTGI.getRegistroAlicuota().getCodigo()));
//						parametros.put("PAR_TIPO_SERVICIO_OSM", Util.getFormatIfNull(locDocumentoTGI.getRegistroAlicuota().getNombre()));
//					}else{
						parametros.put("PAR_CODIGO_SERVICIO", Util.getFormatIfNull(null));
						parametros.put("PAR_TIPO_SERVICIO_OSM", Util.getFormatIfNull(null));
//					}
					parametros.put("PAR_NUM_MEDIDOR", "---");
				}
				
				parametros.put("PAR_PARCELA", Util.getFormatIfNull(locParcela.getNomenclaturaCatastral().getNroParcela()));
				if(locRegistroValuado != null){
					parametros.put("PAR_LECTURA_ANTERIOR", locRegistroValuado.getRegistroValuadoAnterior().getLectura());
					parametros.put("PAR_LECTURA_ACTUAL", locRegistroValuado.getLectura());
					parametros.put("PAR_CONSUMO", locRegistroValuado.getMontoImponible());
				}else{
					parametros.put("PAR_LECTURA_ANTERIOR", 0d);
					parametros.put("PAR_LECTURA_ACTUAL", 0d);
					parametros.put("PAR_CONSUMO", 0d);
				}
				parametros.put("PAR_TRAMO", Util.getFormatIfNull(null));
			}
			
			parametros.put("PAR_VENCIMIENTO", Util.getFormatIfNull(cadaLiquidacion.getFechaVencimiento()));
			parametros.put("PAR_TOTAL", Util.getFormatIfNull(cadaLiquidacion.getMonto()));
			parametros.put("PAR_CODIGOBARRA", "1"+ Util.formatString(18, String.valueOf(cadaLiquidacion.getIdRegistroDeuda())));
			
			Obligacion locObligacion = cadaLiquidacion.getDocGeneradorDeuda().getObligacion();
			parametros.put("PAR_MODIFICADOR_PAGOS_DS", new InformacionDePagosDS(businessLiquidacionTasa.getHistorialPagos(locObligacion, 7)));
			
			parametros.put("PAR_MODIFICADOR_PERIODOS_DS", new InformacionDePeriodosDS(cadaLiquidacion));
			parametros.put("PAR_MODIFICADOR_PERIODOS_CAJA_DS", new InformacionDePeriodosDS(cadaLiquidacion));
			parametros.put("PAR_MODIFICADOR_PERIODOS_CONTADURIA_DS", new InformacionDePeriodosDS(cadaLiquidacion));
		}
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
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_TasaUnificada.jasper";
	}

	// DataSource para las tablas del reporte
	public class InformacionDePagosDS implements JRDataSource{
		
		List<LineaHistorialPago> listaHistorialPago;
		
		int lineaActual = -1;
		
		public InformacionDePagosDS(HistorialPagosTasas pHistorial){
			if(pHistorial != null){
				this.listaHistorialPago = new ArrayList<LineaHistorialPago>();
				this.listaHistorialPago.addAll(pHistorial.getListaHitorialPagoAnual());
			}
		}
		
		@Override
		public Object getFieldValue(JRField field) throws JRException {
			Object valor = null;
			if (field.getName().equals("F_AÑO")){
				valor = Util.getFormatIfNull(listaHistorialPago.get(lineaActual).getAnio());
			}
			Character[] listaCaracteres = listaHistorialPago.get(lineaActual).getListaEstadoPagos();
			
			if (field.getName().equals("F_MES_1")){
				if(listaCaracteres[0] != null)
					valor = Util.getFormatIfNull(listaCaracteres[0]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_2")){
				if(listaCaracteres[1] != null)
					valor = Util.getFormatIfNull(listaCaracteres[1]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_3")){
				if(listaCaracteres[2] != null)
					valor = Util.getFormatIfNull(listaCaracteres[2]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_4")){
				if(listaCaracteres[3] != null)
					valor = Util.getFormatIfNull(listaCaracteres[3]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_5")){
				if(listaCaracteres[4] != null)
					valor = Util.getFormatIfNull(listaCaracteres[4]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_6")){
				if(listaCaracteres[5] != null)
					valor = Util.getFormatIfNull(listaCaracteres[5]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_7")){
				if(listaCaracteres[6] != null)
					valor = Util.getFormatIfNull(listaCaracteres[6]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_8")){
				if(listaCaracteres[7] != null)
					valor = Util.getFormatIfNull(listaCaracteres[7]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_9")){
				if(listaCaracteres[8] != null)
					valor = Util.getFormatIfNull(listaCaracteres[8]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_10")){
				if(listaCaracteres[9] != null)
					valor = Util.getFormatIfNull(listaCaracteres[9]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_11")){
				if(listaCaracteres[10] != null)
					valor = Util.getFormatIfNull(listaCaracteres[10]).toString();
				else
					valor = "·";
			}
			if (field.getName().equals("F_MES_12")){
				if(listaCaracteres[11] != null)
					valor = Util.getFormatIfNull(listaCaracteres[11]).toString();
				else
					valor = "·";
			}
				
			return valor;
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaHistorialPago.size();
		}
	}
	
	public class InformacionDePeriodosDS implements JRDataSource {
		
		List<String> listaDetalles = new ArrayList<String>();
		List<Double> listaImportes = new ArrayList<Double>();
		int lineaActual = -1;
		
		public InformacionDePeriodosDS(LiquidacionTasa pLiquidacion){
			
			List<ModificadorLiquidacion> locListaModificadoresLiquidacion=new ArrayList<ModificadorLiquidacion>();
			List<ModificadorLiquidacion> locListaModificadoresSobreSubTotal = new ArrayList<ModificadorLiquidacion>();

			Double total = 0d;

			for (ModificadorLiquidacion locModificadorLiquidacion:pLiquidacion.getListaModificadoresLiquidacion()){
				if (locModificadorLiquidacion.isSobreSaldoNeto()){
					locListaModificadoresLiquidacion.add(locModificadorLiquidacion);
				} else {
					locListaModificadoresSobreSubTotal.add(locModificadorLiquidacion);
				}
			}
			
			//Valor de la tasa		
			this.listaDetalles.add("Tasa "+pLiquidacion.getCuotaLiquidacion().getPeriodicidad().getNombrePeriodo());
			this.listaImportes.add(pLiquidacion.getValor());
			
			//Modificadores sobre la tasa
			for (ModificadorLiquidacion locModificadorLiquidacion: locListaModificadoresLiquidacion){
				this.listaDetalles.add(locModificadorLiquidacion.getNombre());
				this.listaImportes.add(locModificadorLiquidacion.getValorModificador());
			}
			
			//Sub total
			this.listaDetalles.add("SubTotal");
			this.listaImportes.add(pLiquidacion.getValor() + pLiquidacion.getValorModificadoresSobreSaldoNeto());
			
			//Modificadores sobre el subtotal
			for (ModificadorLiquidacion locModificadorLiquidacion: locListaModificadoresSobreSubTotal){
				this.listaDetalles.add(locModificadorLiquidacion.getNombre());
				this.listaImportes.add(locModificadorLiquidacion.getValorModificador());
			}
			
			//Exención
			if (pLiquidacion.getRegistroExencionRegistroDeuda() != null && pLiquidacion.getRegistroExencionRegistroDeuda().getExencionRegistroDeuda().getEstado().equals(Exencion.Estado.VIGENTE)) {
				this.listaDetalles.add(((pLiquidacion.getRegistroExencionRegistroDeuda()!=null)?pLiquidacion.getRegistroExencionRegistroDeuda().getExencionRegistroDeuda().getNombre():"Exención"));
				//multiplico por -1 ya que quiero q se muestre con valor negativo en el reporte
				this.listaImportes.add(-1*pLiquidacion.getMontoExencion());
			}
			
			// Interés y Recargo
			if (!pLiquidacion.getInteres().equals(0D)) {
				this.listaDetalles.add("Intereses");
				this.listaImportes.add(pLiquidacion.getInteres());
			}
			
			if (!pLiquidacion.getRecargo().equals(0D)) {
				this.listaDetalles.add("Recargos");
				this.listaImportes.add(pLiquidacion.getRecargo());
			}
		}

		@Override
		public Object getFieldValue(JRField field) throws JRException {
			Object valor = null;
			if (field.getName().equals("F_MODIFICADOR1")){
				valor = listaDetalles.get(lineaActual);
			} else if (field.getName().equals("F_MONTO_MODIFICADOR1")){
				valor = listaImportes.get(lineaActual);
			}
			return valor;
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaDetalles.size();
		}
	}
}
