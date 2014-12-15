package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Planta.Edificacion;
import com.trascender.catastro.recurso.persistent.TipoPlanta;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.util.VencimientoComparator;

public abstract class LiquidacionDS extends TrascenderDataSource{

	protected LiquidacionTasa liquidacionTasa;
	protected Zonificacion zonificacion;
	protected Map<String, Object> mapaParametros;
	protected String numeroClientePagoFacil;

	public LiquidacionDS(LiquidacionTasa pLiquidacionTasa, Zonificacion pZonificacion, String pTitulo, String pSubtitulo){
		this.liquidacionTasa = pLiquidacionTasa;
		this.zonificacion = pZonificacion;
		this.numeroClientePagoFacil = this.getCodigoPagoFacil();
		this.setearParametros(pTitulo, pSubtitulo);
	}

	private void setearParametros(String pTitulo, String pSubtitulo){
		mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("PAR_IMAGEN", null);
		mapaParametros.put("PAR_TITULO", pTitulo);
		mapaParametros.put("PAR_SUBTITULO", pSubtitulo);
		//Persona
		DocHabilitanteEspecializado locDocHabilitanteEspecializado = this.liquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
		Persona locPersona = locDocHabilitanteEspecializado.getObligacion().getPersona();
		mapaParametros.put("PAR_CUIM", Util.getFormatIfNull(locPersona.getCuim()));
		mapaParametros.put("PAR_PERSONA", Util.getFormatIfNull(locPersona.getDenominacion()));
		if (locPersona instanceof PersonaFisica){
			PersonaFisica locPersonaFisica = (PersonaFisica) locPersona;
			if (locPersonaFisica.getNumeroDocumento() != null){
				mapaParametros.put("PAR_DNI", locPersonaFisica.getTipoDocumento() + " " + locPersonaFisica.getNumeroDocumento());
			}
		} else {
			mapaParametros.put("PAR_DNI", "");
		}

		//Domicilio
		Domicilio domicilioPostal = locDocHabilitanteEspecializado.getDomicilio();
		String locStringDomicilio = domicilioPostal.toString();
		mapaParametros.put("PAR_CALLE", locStringDomicilio);

		mapaParametros.put("PAR_COD_POSTAL", Util.getFormatIfNull(domicilioPostal.getLocalidad().getCodigoPostal()));
		mapaParametros.put("PAR_LOCALIDAD", Util.getFormatIfNull(domicilioPostal.getLocalidad().getNombre()));
		mapaParametros.put("PAR_PROVINCIA", Util.getFormatIfNull(domicilioPostal.getLocalidad().getProvincia().getNombre()));

		Parcela locParcela = locDocHabilitanteEspecializado.getParcela();
		if (locParcela != null){
			//Datos catastrales			
			//mapaParametros.put("PAR_NRO_REGISTRO", locParcela.getNroRegistro());
			if(locParcela.getDomicilioParcelario().getCalle() != null){
				String locCalle = locParcela.getDomicilioParcelario().getCalle();
				String locNumero = locParcela.getDomicilioParcelario().getNumero();
				mapaParametros.put("PAR_DOM_PARCELARIO", locCalle + " " + locNumero);
			}else{
				mapaParametros.put("PAR_DOM_PARCELARIO", " ");
			}
			mapaParametros.put("PAR_NRO_PART_PROV", Util.getFormatIfNull(locParcela.getNroPartidaProvincial()));

			//En el reporte de birt no se mostraba el Plano de mensura
			mapaParametros.put("PAR_NRO_PLANO_MENS", Util.getFormatIfNull(null));
			mapaParametros.put("PAR_USO_DRM", "No");
			mapaParametros.put("PAR_MANZANA", Util.getFormatIfNull(locParcela.getManzana().getNombre()));
			mapaParametros.put("PAR_PARCELA", Util.getFormatIfNull(locParcela.getNomenclaturaCatastral().getNroParcela()));
			if(locParcela.getMetrosFrente() != null){
				mapaParametros.put("PAR_METROS_FRENTE", Util.getFormatIfNull(locParcela.getMetrosFrente().toString()));
			}else{
				mapaParametros.put("PAR_METROS_FRENTE", "---");
			}

			mapaParametros.put("PAR_BALDIO", Util.getFormatIfNull(locParcela.getPlanta().getTipoEdificacion().equals(Edificacion.BALDIO)?"Si":"No"));
			mapaParametros.put("PAR_RADIO_CENTRICO", Util.getFormatIfNull(locParcela.getPlanta().getTipoPlanta().equals(TipoPlanta.Tipo.URBANA)?"Si":"No"));

			//Datos parcelarios
			mapaParametros.put("PAR_ZONA", this.getZona(locParcela));
			mapaParametros.put("PAR_SUP_TERRENO", locParcela.getSuperficie());
			mapaParametros.put("PAR_SUP_MEJORAS", locParcela.getSuperficieMejoras());
			mapaParametros.put("PAR_AVALUO_TERRENO", locParcela.getAvaluoTerreno());
			mapaParametros.put("PAR_AVALUO_MEJORAS", locParcela.getAvaluoPorMejoras());
		}
		mapaParametros.put("PAR_TOTAL", liquidacionTasa.getMonto());
		mapaParametros.put("PAR_CODIGOBARRA_LIQUIDACION", "1"+this.formatString(18, String.valueOf(this.liquidacionTasa.getIdRegistroDeuda())));
		if(numeroClientePagoFacil != null)
		{
			mapaParametros.put("PAR_TITULO_PAGOFACIL", "PAGO FÁCIL:");
			mapaParametros.put("PAR_CODIGOBARRA_LIQUIDACION_PF", this.liquidacionTasa.getCodigoBarrasPagoFacil());
		}else{
			mapaParametros.put("PAR_TITULO_PAGOFACIL", "");
			mapaParametros.put("PAR_CODIGOBARRA_LIQUIDACION_PF", null);
		}

		mapaParametros.put("PAR_MODIFICADOR_DS", new DetalleLiquidacionDS(liquidacionTasa));
		mapaParametros.put("PAR_VENCIMIENTOS_DS", new VencimientosDS(liquidacionTasa));
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		return null;
	}

	@Override
	public boolean next() throws JRException {
		return false;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	public String getParteDecimalAsString(Double pValor) {
		int locParteEntera = pValor.intValue();
		pValor -= locParteEntera;
		pValor*=1000;
		return String.valueOf(pValor.intValue());
	}

	protected String formatString(int pTamanio, String pCadena){
		String retorno = "";
		int locTamanio=pCadena.length();
		int agregar = pTamanio - locTamanio;
		if (agregar>0){
			for (int i=0;i<agregar;i++){
				retorno += "0";
			}
		}
		return retorno+pCadena;
	}

	public LiquidacionTasa getLiquidacionTasa(){
		return this.liquidacionTasa;
	}

	private String getCodigoPagoFacil(){
		try{
			Context ctx = new InitialContext();
			BusinessMunicipalidadLocal businessMunicipalidad = (BusinessMunicipalidadLocal) ctx.lookup(BusinessMunicipalidadLocal.JNDI_NAME);
			Municipalidad locMunicipalidad = businessMunicipalidad.getMunicipalidad(new FiltroMunicipalidad()).getListaResultados().get(0);
			String valor = locMunicipalidad.getNumeroClientePagoFacil().toString();
			return valor;
		} catch (Exception e){
			return null;
		}
	}

	private String getZona(Parcela pParcela){
		//		Session locSession = GestorPersistenciaSaic.getInstance().getSession();
		//		Zonificacion locZonificacion = new Zonificacion();
		//		locZonificacion.setIdZonificacion(2l);
		//		List<AsociacionParcela> locLista = locSession.createCriteria(AsociacionParcela.class)
		//			.createAlias("zona", "locZona")
		//			.add(Restrictions.eq("locZona.zonificacion", locZonificacion))
		//			.add(Restrictions.eq("parcela",pParcela))
		//			.list();
		//		if(!locLista.isEmpty()){
		//			return locLista.get(0).getZona().toString();
		//		}
		return "";
	}

	public class DetalleLiquidacionDS implements JRDataSource {

		List<String> listaDetalles = new ArrayList<String>();
		List<Double> listaImportes = new ArrayList<Double>();
		int lineaActual = -1;

		public DetalleLiquidacionDS(LiquidacionTasa pLiquidacion){

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
			this.listaDetalles.add("Tasa " + pLiquidacion.getCuotaLiquidacion().getPeriodo().getNombre());
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

	public class VencimientosDS implements JRDataSource{

		List<Vencimiento> listaVencimientos;

		int lineaActual = -1;

		public VencimientosDS(LiquidacionTasa pLiquidacion){
			this.listaVencimientos = new ArrayList<Vencimiento>();
			this.listaVencimientos.addAll(pLiquidacion.getListaVencimientos());
			//Lo ordeno
			Collections.sort(this.listaVencimientos, new VencimientoComparator());
		}

		@Override
		public Object getFieldValue(JRField field) throws JRException {
			Object valor = null;
			if (field.getName().equals("F_CODIGOBARRA_VENCIMIENTO")){
				valor = Util.getFormatIfNull(getCodigoBarraVencimiento(listaVencimientos.get(lineaActual)));
			}else if (field.getName().equals("F_FECHA_VENCIMIENTO")){
				valor = Util.getFormatIfNull(listaVencimientos.get(lineaActual).getFecha());
			}else if (field.getName().equals("F_MONTO_VENCIMIENTO")){
				valor = Util.getFormatIfNull(listaVencimientos.get(lineaActual).getValor());
			} 

			return valor;
		}

		@Override
		public boolean next() throws JRException {
			return ++lineaActual < listaVencimientos.size();
		}

		private String getCodigoBarraVencimiento(Vencimiento pVencimiento){
			Calendar locCalendar = Calendar.getInstance();
			StringBuilder locStringBuilder;

			locStringBuilder = new StringBuilder();
			locCalendar.setTime(pVencimiento.getFecha());

			locStringBuilder.append(locCalendar.get(Calendar.YEAR));
			//le agrego 1 al mes porque en la clase Calendar el mes empieza de 0
			locStringBuilder.append(formatString(2, Integer.toString(locCalendar.get(Calendar.MONTH)+1)));
			locStringBuilder.append(formatString(2,Integer.toString(locCalendar.get(Calendar.DAY_OF_MONTH))));

			//Parte entera del número
			locStringBuilder.append(formatString(9, Integer.toString(pVencimiento.getValor().intValue())));
			locStringBuilder.append(formatString(3, getParteDecimalAsString(pVencimiento.getValor())));

			return locStringBuilder.toString();
		}

	}
}
