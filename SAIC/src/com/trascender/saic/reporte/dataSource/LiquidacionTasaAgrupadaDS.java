package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRImageRenderer;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTGILocal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

public class LiquidacionTasaAgrupadaDS extends TrascenderDataSource{

	private final Map<String, Object> mapaParametros = new HashMap<String, Object>();
	private BusinessZonificacionLocal businessZonificacionLocal;
	private List<Map<Long, String>> listaMapaParcelasZonas;
	private final List<Map<String, Object>> mapasOpciones = new LinkedList<Map<String,Object>>();
	private Map<String, Object> mapaActual;
	private Iterator<Map<String, Object>> iteradorActual;
	
	public LiquidacionTasaAgrupadaDS(List<LiquidacionTasaAgrupada> pListaLiquidacionesAgrupadas,
			byte[] pLogo, String pTitulo, String pSubtitulo, 
			BusinessZonificacionLocal pBusinessZonificacion, Usuario pUsuario){

		mapaParametros.put("PAR_USUARIO", pUsuario.getUser());
		mapaParametros.put("P_LOGO", JRImageRenderer.getInstance(pLogo));
		mapaParametros.put("P_TITULO_REPORTE",pTitulo);
		mapaParametros.put("P_SUBTITULO_REPORTE", pSubtitulo);
		//Si es mas de una liquidacion, inicializo el mapa, para levantar las zonas mas rapido
		if (pListaLiquidacionesAgrupadas.size() > 5) {
			this.listaMapaParcelasZonas = pBusinessZonificacion.getMapaParcelaNombreZona(null);
			System.out.println("mapa levantado");
		} else {
			//si no, dejo la refencia al business para obtener solo una zona.
			businessZonificacionLocal = pBusinessZonificacion;
		}
		generarMapa(pListaLiquidacionesAgrupadas);
	}

	private void generarMapa(List<LiquidacionTasaAgrupada> pListaLiquidacion){
		for (LiquidacionTasaAgrupada cadaLiquidacion : pListaLiquidacion){
			Map<String, Object> locMapa = new HashMap<String, Object>();
			LiquidacionTasa primerLiquidacion = cadaLiquidacion.getListaLiquidacionesTasa().get(0);
			DocHabilitanteEspecializado locDocumento = cadaLiquidacion.getDocHabilitanteEspecializado(DocumentoTGI.class);
			if (locDocumento == null){
				locDocumento = cadaLiquidacion.getDocHabilitanteEspecializado(DocumentoOSP.class);
			}
			if (locDocumento == null) {
				locDocumento = cadaLiquidacion.getDocHabilitanteEspecializado(DocumentoArrendamiento.class);
			}
			String nombreContribuyente = "";
			if (primerLiquidacion.getDocGeneradorDeuda().getObligacion().getPersona().getCuim().equals("99-99999999-9")) {
				//Tenemos que recuperar el valor del atri dinamico "Apellido y nom de referencia".
				if (locDocumento instanceof DocumentoOSP) {
					//Si el Documento es OSP, debemos buscar la obligacion TGI asociada a la parcela.
					Obligacion obligacionTGI = getObligacionTGIAsociado(locDocumento.getParcela());
					nombreContribuyente = getValorDinamicoDeDocumento(obligacionTGI.getDocumentoEspecializado(), "APELLIDO_Y_NOMBRE_DE_REFERENCIA").toString();
				} else {
					nombreContribuyente = getValorDinamicoDeDocumento(locDocumento, "APELLIDO_Y_NOMBRE_DE_REFERENCIA").toString();
				}
			}else {
				nombreContribuyente = primerLiquidacion
						.getDocGeneradorDeuda().getObligacion().getPersona().getDenominacion();
			}
					TituloPropiedad locTitulo = locDocumento.getParcela().getTituloPropiedad();
					if (locTitulo.getListaRegistrosPropietarios().size() == 2){
//						nombreContribuyente = locTitulo.getListaRegistrosPropietarios().get(0).getPersona().getDenominacion();
						String nombreSegundoPropietario = locTitulo.getListaRegistrosPropietarios().get(1).getPersona().getDenominacion();
						if(nombreSegundoPropietario.equals(nombreContribuyente)){
							nombreSegundoPropietario = locTitulo.getListaRegistrosPropietarios().get(0).getPersona().getDenominacion();
						}
						
						nombreContribuyente = nombreContribuyente + " - " 
								+ nombreSegundoPropietario;
					} else if (locTitulo.getListaRegistrosPropietarios().size() > 2) {
						nombreContribuyente = nombreContribuyente
								+ " y otros.";
					}
					locMapa.put("F_NOMBRE_CONTRIBUYENTE", nombreContribuyente);
					CuotaLiquidacion locPeriodo = primerLiquidacion.getCuotaLiquidacion();
					String periodicidad = locPeriodo.getPeriodo().getNombre();

					locMapa.put("F_PERIODICIDAD", periodicidad);
					locMapa.put("F_CUIT_CONTRIBUYENTE",
							primerLiquidacion
							.getDocGeneradorDeuda().getObligacion().getPersona().getCuim());
					locMapa.put("F_DOMICILIO_POSTAL",
							locDocumento.getDomicilio().toString());
					locMapa.put("F_NUMERO_CUENTA",
							locDocumento.getParcela().getNroParcela());
					locMapa.put("F_NUMERO_PERIODO",
							primerLiquidacion.getCuotaLiquidacion().getNumero());
					locMapa.put("F_ANIO_PERIODO",
							primerLiquidacion.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio());
					locMapa.put("F_NOMBRE_PERIODO", primerLiquidacion.getCuotaLiquidacion().getPeriodo().getNombre());
					locMapa.put("F_DOMICILIO_PARCELARIO",
							locDocumento.getParcela().getDomicilioParcelario().toString());
					locMapa.put("F_MANZANA",
							locDocumento.getParcela().getManzana().getNombre());
					locMapa.put("F_NRO_PARTIDA",
							locDocumento.getParcela().getNroPartidaProvincial());
					locMapa.put("F_ZONA", getZona(cadaLiquidacion));
					locMapa.put("F_SUPERFICIE_TERRENO",
							locDocumento.getParcela().getSuperficie());
					locMapa.put("F_AVALUO_TERRENOS",
							locDocumento.getParcela().getAvaluoTerreno());
					locMapa.put("F_AVALUO_MEJORAS",
							locDocumento.getParcela().getAvaluoPorMejoras());
					boolean tieneInteres = cadaLiquidacion.getInteres() != null
							&& cadaLiquidacion.getInteres() > 0d;
							if (tieneInteres){
								locMapa.put("F_TEXTO_PAGO", "Pago fuera de término");
							} else {
								String textoPago;
								if (cadaLiquidacion
										.getModificadorPorNombre("Desc. Buen Pagador") != null){
									textoPago = "No existen liquidaciones pendientes de pago \n- Período no prescripto -";
								} else {
									textoPago = "Hay deudas por períodos impagos";
								}
								locMapa.put("F_TEXTO_PAGO", textoPago);
							}

							locMapa.put("F_AVALUO_MEJORAS",
									locDocumento.getParcela().getAvaluoPorMejoras());
							DocumentoOSP locDocumentoOSP = cadaLiquidacion.getDocHabilitanteEspecializado(DocumentoOSP.class);
							locMapa.put("F_NRO_MEDIDOR",
									locDocumentoOSP != null ? locDocumentoOSP.getCodigoMedidor() : "");
							locMapa.put("F_MODIFICADOR_DS",
									new DetalleLiquidacionDS(cadaLiquidacion));
							locMapa.put("F_VENCIMIENTO",
									cadaLiquidacion.getFechaVencimientoMayor());
							locMapa.put("F_TOTAL",
									cadaLiquidacion.getMonto());
							locMapa.put("F_LECTURA_ANTERIOR", new Double(0));
							locMapa.put("F_LECTURA_ACTUAL", new Double(0));
							locMapa.put("F_CONSUMO", new Double(0));
							locMapa.put("F_TRAMO", null);
							locMapa.put("F_CODIGO_BARRA", cadaLiquidacion.getCodigoBarraCodificado());

							//La guardo por si la necesito en otro lugar
							locMapa.put("liquidacionTasaAgrupada", cadaLiquidacion);

							//Para usar luego la lista
							locDocumento.getListaAtributosDinamicos().toString();
							if (locDocumentoOSP != null){
								locDocumentoOSP.getListaAtributosDinamicos().toString();
							}
							mapasOpciones.add(locMapa);
		}
		this.iteradorActual = mapasOpciones.iterator();
	}


	private String getZona(LiquidacionTasaAgrupada cadaLiquidacion){
		String retorno = "";
		Parcela locParcela = cadaLiquidacion
				.getListaLiquidacionesTasa().get(0).getDocGeneradorDeuda()
				.getObligacion().getDocumentoEspecializado().getParcela();
		if (businessZonificacionLocal != null) {
			try{
				List<Zona> locListaZona = this.businessZonificacionLocal.getZonasFromParcela(locParcela);
				retorno = locListaZona.get(0).getNombre();
			} catch (Exception e){
			}
		}else {
			for (Map<Long, String> cadaMapa : listaMapaParcelasZonas){
				retorno = cadaMapa.get(locParcela.getIdParcela());
				if (retorno != null) break;
			}
		}
		return retorno;
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (field.getName().startsWith("F_ATDI")){
			//Atributos dinámicos.
			String locNombre = field.getName().replace("F_ATDI_", "");
			LiquidacionTasaAgrupada locLiquidacionAgrupada = (LiquidacionTasaAgrupada) this.mapaActual.get("liquidacionTasaAgrupada");
			for (LiquidacionTasa cadaLiquidacion : 
				locLiquidacionAgrupada.getListaLiquidacionesTasa()){
				DocHabilitanteEspecializado locDoc = cadaLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
				Object valor = getValorDinamicoDeDocumento(locDoc, locNombre);
				if (valor != null) return valor;
			}
		}
		return mapaActual.get(field.getName());
	}
	
	private Object getValorDinamicoDeDocumento(DocHabilitanteEspecializado pDoc, String pNombreAtributo) {
		for (AtributoDinamico<?> cadaAtributo : pDoc.getListaAtributosDinamicos()){
			if (cadaAtributo.getNombre().replace(" ", "_").toUpperCase().equals(pNombreAtributo)){
				return cadaAtributo.getValorString();
			}
		}
		return null;
	}
	
	private Obligacion getObligacionTGIAsociado(Parcela pParcela) {
		Obligacion locObligacion = null;
		try {
			BusinessDocumentoTGILocal locBean = 
					(BusinessDocumentoTGILocal) new InitialContext().lookup(BusinessDocumentoTGILocal.JNDI_NAME);
			FiltroObligacionTGI locFiltro = new FiltroObligacionTGI();
			locFiltro.setParcela(pParcela);
			locFiltro = locBean.findListaObligacionesTGI(locFiltro);
			if (!locFiltro.getListaResultados().isEmpty()) {
				locObligacion = locFiltro.getListaResultados().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locObligacion;
	}

	@Override
	public boolean next() throws JRException {
		boolean retorno = iteradorActual.hasNext();
		if (retorno){
			this.mapaActual = iteradorActual.next();
		}
		return retorno;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_tasas_unificadas.jasper";
	}

	public class DetalleLiquidacionDS implements JRDataSource {

		List<String> listaDetalles = new ArrayList<String>();
		List<Double> listaImportes = new ArrayList<Double>();
		int lineaActual = -1;

		public DetalleLiquidacionDS(LiquidacionTasaAgrupada pLiquidacion){

			List<ModificadorLiquidacion> locListaModificadoresLiquidacion=new ArrayList<ModificadorLiquidacion>();
			List<ModificadorLiquidacion> locListaModificadoresSobreSubTotal = new ArrayList<ModificadorLiquidacion>();

			for (ModificadorLiquidacion locModificadorLiquidacion:pLiquidacion.getListaModificadoresLiquidacion()){
				if (locModificadorLiquidacion.isSobreSaldoNeto()){
					locListaModificadoresLiquidacion.add(locModificadorLiquidacion);
				} else {
					locListaModificadoresSobreSubTotal.add(locModificadorLiquidacion);
				}
			}
			
			Collections.sort(pLiquidacion.getListaLiquidacionesTasa(), new Comparator<LiquidacionTasa>() {
				@Override
				public int compare(LiquidacionTasa o1, LiquidacionTasa o2) {
					return o1.getTipoTasa().getTipoObligacion().getNombre()
							.compareTo(o2.getTipoTasa().getTipoObligacion().getNombre());
				}
			});

			//Valor de la tasa		
			for (LiquidacionTasa cadaLiquidacion : pLiquidacion.getListaLiquidacionesTasa()){
				if (cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre().equals("TGI")){
					this.listaDetalles.add("Tasa General");
				} else if (cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre().equals("OYSP")){
					this.listaDetalles.add("Servicios Sanitarios");
				} else if (cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre().equals("ARRENDAMIENTO")) {
					this.listaDetalles.add("Arrendamiento");
				}
				this.listaImportes.add(cadaLiquidacion.getValor());
			}

			//Modificadores sobre la tasa
			for (ModificadorLiquidacion cadaModificadorLiquidacion : locListaModificadoresLiquidacion){
				this.listaDetalles.add(cadaModificadorLiquidacion.getNombre());
				this.listaImportes.add(cadaModificadorLiquidacion.getValorModificador());
			}

			//Sub total
			//			this.listaDetalles.add("SubTotal");
			//			this.listaImportes.add(pLiquidacion.getValor() + pLiquidacion.getValorModificadoresSobreSaldoNeto());

			//Modificadores sobre el subtotal
			for (ModificadorLiquidacion cadaModificadorLiquidacion: locListaModificadoresSobreSubTotal){
				this.listaDetalles.add(cadaModificadorLiquidacion.getNombre());
				this.listaImportes.add(cadaModificadorLiquidacion.getValorModificador());
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
