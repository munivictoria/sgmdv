package com.trascender.saic.business.ejb;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import org.jboss.ejb3.annotation.TransactionTimeout;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.business.interfaces.BusinessRegistroGeograficoLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoPlanObraLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessPeriodoLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoAlicuotaLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.refinanciacion.DocumentoRef;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.business.interfaces.BusinessEstadoCuentaContribuyenteLocal;
import com.trascender.saic.business.interfaces.BusinessImpresionLocal;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.Reliquidacion;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;
import com.trascender.saic.reporte.dataSource.CuotaRefinanciacionAgrupadaDS;
import com.trascender.saic.reporte.dataSource.CuotaRefinanciacionDS;
import com.trascender.saic.reporte.dataSource.ImprimirDDJJSHPSDS;
import com.trascender.saic.reporte.dataSource.ImprimirDDJJSHPSPadreDS;
import com.trascender.saic.reporte.dataSource.LiquidacionAgrupadaDS;
import com.trascender.saic.reporte.dataSource.LiquidacionAutomotorDS;
import com.trascender.saic.reporte.dataSource.LiquidacionDS;
import com.trascender.saic.reporte.dataSource.LiquidacionOspDS;
import com.trascender.saic.reporte.dataSource.LiquidacionPfoDS;
import com.trascender.saic.reporte.dataSource.LiquidacionShpsDS;
import com.trascender.saic.reporte.dataSource.LiquidacionTasaAgrupadaDS;
import com.trascender.saic.reporte.dataSource.ReconocimientoDeudaDS;
import com.trascender.saic.reporte.dataSource.ReliquidacionDS;

@Stateless(name="BusinessImpresionLocal")
@TransactionTimeout(86400)
public class BusinessImpresionBean implements BusinessImpresionLocal {

	@EJB
	private BusinessRegistroParcelarioLocal businessRegistroParcelarioLocal;
	@EJB
	private BusinessPersonaLocal businessPersonaLocal;
	@EJB
	private BusinessRegistroGeograficoLocal businessRegistroGeograficoLocal;
	@EJB
	private BusinessTipoAlicuotaLocal businessTipoAlicuotaLocal;
	@EJB
	private BusinessZonificacionLocal businessZonificacionLocal;
	@EJB
	private BusinessDocumentoPlanObraLocal businessDocumentoPlanObraLocal;
	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;
	@EJB
	private BusinessObligacionLocal businessObligacionLocal;
	@EJB
	private BusinessEstadoCuentaContribuyenteLocal businessEstadoCuentaContribuyenteLocal;
	@EJB
	private BusinessMunicipalidadLocal businessMunicipalidadLocal;
	@EJB
	private BusinessLiquidacionTasaLocal businessLiquidacionTasaLocal; 
	@EJB
	private BusinessParametroLocal businessParametro;

	@EJB
	private BusinessPeriodoLocal businessPeriodo;

	@PersistenceContext(name="Vipians")
	private EntityManager entityManager;

	public Zonificacion zonificacion;

	private Image parametroLogoMuni;
	private String parametroTitulo;
	private String parametroSubtitulo;
	private InputStream logoInputStream;

	//Metodo para obtener la zonificacion y todos los reportes puedan accederla
	private Zonificacion getZonificacion() throws Exception{
		if(zonificacion == null){
			try{
				this.zonificacion =  this.businessZonificacionLocal.getZonificacionPorId(2l);
			}
			catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		}
		return zonificacion;
	}

	class AuxIndice{
		public Persona persona;
		public Parcela parcela;
		public Calendar fechaInicioPeriodo;

		public AuxIndice(Parcela pParcela, Persona pPersona,Calendar pFechaInicioPeriodo) throws Exception{
			this.parcela = pParcela;
			this.persona = pPersona;
			this.fechaInicioPeriodo = pFechaInicioPeriodo;
		}	

		@Override
		public boolean equals(Object obj) {
			if (obj==this) return true;
			if (obj==null) return false;
			if (!(obj instanceof AuxIndice)) return false;
			AuxIndice locAuxIndice = (AuxIndice)obj;

			return (locAuxIndice.persona.equals(this.persona))
					&&
					(locAuxIndice.parcela.equals(this.parcela))
					&&
					(locAuxIndice.fechaInicioPeriodo.equals(this.fechaInicioPeriodo));
		}

		@Override
		public int hashCode() {
			return persona.hashCode()+parcela.hashCode()+this.fechaInicioPeriodo.hashCode();
		}

		@Override
		public String toString() {
			DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
			return this.parcela+"  "+this.persona+"  "+locDateFormat.format(this.fechaInicioPeriodo.getTime());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4153863207952162281L;

	public BusinessImpresionBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx)
			throws EJBException,
			RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	private String getFechaYHoraString() {
		Date locDate = Calendar.getInstance().getTime();

		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		DateFormat locTimeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);

		String locFechaConFormato = (locDateFormat.format(locDate).replaceAll("/", "-"));

		String locFormatoHora = locTimeFormat.format(locDate).replaceAll(":","-");
		String locFormatoHoraFinal = locFormatoHora.substring(0, 2)+"h-";
		locFormatoHoraFinal += locFormatoHora.substring(3, 5)+"m-";
		locFormatoHoraFinal += locFormatoHora.substring(6, 8)+"s";

		return (locFechaConFormato+" ["+locFormatoHoraFinal+"]");
	}


	@SuppressWarnings("unchecked")
	private List<Long> getListaSinReliquidacionesTGIVencidas(Periodo pPeriodo, 
			List<Long> pListaIdObligacionesIgualFormaPago) {
		//Recupera los id de las ReliquidacionesTGI no canceladas para ese periodo
		List<Long> locListaIdsReliquidacionesTGI = Criterio.getInstance(this.entityManager, LiquidacionTasa.class)
				.crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.setProyeccion(Proyeccion.PROP("locObligacion.idObligacion"))
				.setDistinct(true)
				.crearAlias("locObligacion.documentoEspecializado","locDocEsp")
				.add(Restriccion.NULO("registroCancelacion"))
				.add(Restriccion.DISTINTO("locObligacion.estado",Obligacion.Estado.ANULADO))
				.add(Restriccion.IGUAL("tipoDeuda",RegistroDeuda.TipoDeuda.RELIQUIDACION))
				.add(Restriccion.IGUAL("estado",RegistroDeuda.EstadoRegistroDeuda.VENCIDA))
				.add(Restriccion.IGUAL("numeroCuota", 1))
				.add(Restriccion.IGUAL("locDocEsp.class", "TGI"))
				.add(Restriccion.IGUAL("periodo", pPeriodo))		
				.list();

		for(Long cadaId : locListaIdsReliquidacionesTGI){
			if(pListaIdObligacionesIgualFormaPago.contains(cadaId)){
				pListaIdObligacionesIgualFormaPago.remove(cadaId);
			}
		}
		return pListaIdObligacionesIgualFormaPago;
	}

	@SuppressWarnings("unchecked")
	private Persona getPersonaPorId(Long pIdPersona,Class pTipoPersona) throws Exception{
		return this.businessPersonaLocal.getPersonaPorId(pIdPersona,pTipoPersona);
	}

	private Periodo getPeriodoPorId(long pIdPeriodo) {
		Periodo locPeriodo = (Periodo)Criterio.getInstance(this.entityManager, Periodo.class)
				.add(Restriccion.IGUAL("idPeriodo",pIdPeriodo))
				.uniqueResult();
		return locPeriodo;
	}

	private List<LiquidacionTasa> ordenarListaLiquidaciones(List<LiquidacionTasa> pListado) {
		LiquidacionTasa[] locListado = new LiquidacionTasa[pListado.size()];
		pListado.toArray(locListado);

		java.util.Arrays.sort(locListado, new  Comparator<LiquidacionTasa>(){
			@Override
			public int compare(LiquidacionTasa o1, LiquidacionTasa o2) {
				Domicilio dom1 =  o1.getDocGeneradorDeuda()
						.getObligacion().getDocumentoEspecializado().getDomicilio();
				Domicilio dom2 = o2.getDocGeneradorDeuda()
						.getObligacion().getDocumentoEspecializado().getDomicilio();
				if (dom1.getCalle() == null || dom2.getCalle() == null) {
					System.out.println("stop");
				}
				int resultado = dom1.getCalle().compareTo(dom2.getCalle());
				if(resultado == 0){
					try{
						Integer locNumeroO1 = Integer.valueOf(dom1.getNumero());
						Integer locNumeroO2 = Integer.valueOf(dom2.getNumero());
						resultado = locNumeroO1.compareTo(locNumeroO2);
					}
					catch(Exception e){
						//Significa q no pudo castear un numero a Integer
						resultado = dom1.getNumero().compareTo(dom2.getNumero());
					}
				}
				return resultado;
			}
		}
				);

		pListado = new ArrayList<LiquidacionTasa>();
		for(LiquidacionTasa cadaLiquidacionTasa : locListado){
			pListado.add(cadaLiquidacionTasa);
		}
		//		System.gc();
		return pListado;
	}

	@Override
	public void setEntityManager(EntityManager pEntityManager) {
		if(this.entityManager != null) this.entityManager =pEntityManager;
	}

	@Override
	public JasperPrint getReporteOSP(
			com.trascender.framework.recurso.persistent.Persona pPersona,
			com.trascender.catastro.recurso.persistent.Cuadra pCuadra,
			com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicioOSP,
			java.lang.Boolean pServicioMedido,
			com.trascender.catastro.recurso.persistent.Calle pCalle,
			CuotaLiquidacion pCuota,
			EstadoRegistroDeuda pEstadoRegistroDeuda, String pCuentaSubcuenta)
					throws Exception {
		List<LiquidacionTasa> listaLiquidacionesOSP = this.businessLiquidacionTasaLocal
				.findListaLiquidacionesOSP(null).getListaResultados();
		List<LiquidacionDS> locListaDataSources = new ArrayList<LiquidacionDS>();
		for (LiquidacionTasa cadaLiquidacion : listaLiquidacionesOSP) {
			entityManager.merge(cadaLiquidacion);
			DocumentoOSP locDocumentoOSP = (DocumentoOSP) cadaLiquidacion
					.getDocGeneradorDeuda().getObligacion()
					.getDocumentoEspecializado();
			cadaLiquidacion.getDocGeneradorDeuda().getObligacion()
			.getDocumentoEspecializado().getDomicilio().toString();
			cadaLiquidacion.getDocGeneradorDeuda().getObligacion()
			.getDocumentoEspecializado().getParcela()
			.getDomicilioParcelario().toString();
			if (!locDocumentoOSP.getListaRegistrosValuados().isEmpty()) {
				locDocumentoOSP.getListaRegistrosValuados().toString();
				for (RegistroValuado cadaRegistroValuado : locDocumentoOSP
						.getListaRegistrosValuados()) {
					cadaRegistroValuado.toString();
					if (cadaRegistroValuado.getRegistroValuadoAnterior() != null) {
						cadaRegistroValuado.getRegistroValuadoAnterior()
						.toString();
					}
				}
			}
//			locListaDataSources.add(new LiquidacionOspDS(cadaLiquidacion, null, this.getTituloReporte(), this.getSubtituloReporte()));
		}
		LiquidacionAgrupadaDS locLiquidacionAgrupadaDS = new LiquidacionAgrupadaDS(
				locListaDataSources, getLogoMunicipalidad());
		JasperPrint jasperPrint = getJasperPrint(locLiquidacionAgrupadaDS);
		return jasperPrint;
	}

	@Override
	public JasperPrint getReportePFO(
			com.trascender.framework.recurso.persistent.Persona pPersona,
			com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
			com.trascender.catastro.recurso.persistent.Cuadra pCuadra,
			com.trascender.catastro.recurso.persistent.Calle pCalle,
			CuotaLiquidacion pCuota,
			EstadoRegistroDeuda pEstadoRegistroDeuda, TipoDeuda pTipoDeuda,
			Integer pCuotaDesde, Integer pCuotaHasta) throws Exception {
		List<LiquidacionTasa> listaLiquidacionesPFO = this.businessLiquidacionTasaLocal
				.findListaLiquidacionesPFO(null).getListaResultados();
		return this.getReportePFO(listaLiquidacionesPFO);
	}

	private JasperPrint getReportePFO(List<LiquidacionTasa> listaLiquidacionesPFO) throws Exception {
		List<LiquidacionDS> locListaDataSources = new ArrayList<LiquidacionDS>();
		for (LiquidacionTasa cadaLiquidacion : listaLiquidacionesPFO) {
			locListaDataSources.add(new LiquidacionPfoDS(cadaLiquidacion, null, this.getTituloReporte(), this.getSubtituloReporte()));
		}
		LiquidacionAgrupadaDS locLiquidacionAgrupadaDS = new LiquidacionAgrupadaDS(
				locListaDataSources, this.getLogoMunicipalidad());
		JasperPrint jasperPrint = getJasperPrint(locLiquidacionAgrupadaDS);
		return jasperPrint;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JasperPrint getReporteSHPS(FiltroLiquidacionSHPS filtro, Usuario pUsuario)
					throws Exception {
		filtro.setCantidadFilasTotales(null);
		filtro.setCantidadPorPagina(null);
		List<LiquidacionTasa> locListaLiquidacionesTasa = this.businessLiquidacionTasaLocal
				.findListaLiquidacionesSHPS(filtro).getListaResultados();
		return getReporteSHPS(locListaLiquidacionesTasa, pUsuario);
	}

	@Override
	public JasperPrint getReporteSHPS(List<LiquidacionTasa> pListaLiquidaciones, Usuario pUsuario) throws Exception{
		pListaLiquidaciones = this.ordenarListaLiquidaciones(pListaLiquidaciones);
		List<LiquidacionDS> locListaDataSource = new ArrayList<LiquidacionDS>();
		for (LiquidacionTasa cadaLiquidacion : pListaLiquidaciones) {
			locListaDataSource.add(new LiquidacionShpsDS(cadaLiquidacion, null, this.getTituloReporte(), 
					this.getSubtituloReporte(), businessRegistroParcelarioLocal, pUsuario));
		}
		LiquidacionAgrupadaDS locLiquidacionAgrupadaDS = new LiquidacionAgrupadaDS(
				locListaDataSource, this.getLogoMunicipalidad());
		URL urlReporte = this.getClass().getResource(
				"/com/trascender/saic/reporte/compilado/"
						+ locLiquidacionAgrupadaDS.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(urlReporte);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
				locLiquidacionAgrupadaDS.getMapaParametros(),
				locLiquidacionAgrupadaDS);
		return jasperPrint;
	}
	
	@Override
	public JasperPrint getReporteDDJJSHPS(List<Obligacion> pListaObligaciones, CuotaLiquidacion pCuota) throws Exception{
		List<Long> listaIdsObligacion = new ArrayList<Long>();
		for (Obligacion cadaObligacion : pListaObligaciones) {
			listaIdsObligacion.add(cadaObligacion.getIdObligacion());
		}
		
		Criterio locCriterio = Criterio.getInstance(entityManager, Obligacion.class)
				.add(Restriccion.EN("idObligacion", listaIdsObligacion))
				.crearFetchAlias("persona", "locPersona")
				.crearFetchAlias("documentoEspecializado", "locDocumento")
				.crearFetchAlias("locDocumento.domicilio", "locDomicilio")
				.crearFetchAlias("locDomicilio.localidad", "locLocalidad")
				.crearFetchAlias("locDomicilio.relacionCalle", "locCalle1");
		
		List<Obligacion> locListaOblitacionesEncontradas = locCriterio.list();
		List<ImprimirDDJJSHPSDS> locListaDataSources = new ArrayList<ImprimirDDJJSHPSDS>();
		for (Obligacion cadaObligacion : locListaOblitacionesEncontradas) {
			locListaDataSources.add(new ImprimirDDJJSHPSDS(cadaObligacion, pCuota));
			locListaDataSources.add(new ImprimirDDJJSHPSDS(cadaObligacion, pCuota));
		}
	
		ImprimirDDJJSHPSPadreDS locImprimirDDJJSHPSPadreDS = new ImprimirDDJJSHPSPadreDS(locListaDataSources);
		String direccionReporte = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		File locFile = new File(direccionReporte + locImprimirDDJJSHPSPadreDS.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(locFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
				locImprimirDDJJSHPSPadreDS.getMapaParametros(),
				locImprimirDDJJSHPSPadreDS);
		return jasperPrint;
	}


	@Override
	public JasperPrint getReporteTGI(CuotaLiquidacion pCuota, Persona pPersona,
			Parcela pParcela, Periodicidad pPeriodicidad, Integer pNumeroCuota,
			EstadoRegistroDeuda pEstadoRegistroDeuda, boolean pIgualFormaPago,
			TipoDeuda pTipoDeuda) throws Exception {
		System.out.println("Parametros: ");
		System.out.println("Periodo: " + pCuota);
		System.out.println("Persona: " + pPersona);
		System.out.println("Parcela: " + pParcela);
		System.out.println("Periodicidad: " + pPeriodicidad);
		System.out.println("Numero Cuota: " + pNumeroCuota);
		System.out.println("Estado Registro Deuda: " + pEstadoRegistroDeuda);
		System.out.println("Igual forma de pago: " + pIgualFormaPago);
		System.out.println("Tipo Deuda: " + pTipoDeuda);

		if ((pPersona == null) && (pCuota == null) && (pParcela == null)) {
			throw new SaicException(34);
		}

		try {

			List<Long> locListaIdObligacionesIgualFormaPago = new ArrayList<Long>();
			Criterio locCriterio = null;

			//			Periodo locPeriodoAnterior = businessPeriodo.getCuotaAnterior(pCuota);
			Periodo locPeriodoAnterior = null;

			if (pIgualFormaPago == true) {
				locCriterio = Criterio.getInstance(entityManager, LiquidacionTasa.class)
						.crearAlias("docGeneradorDeuda", "locDocGenDeuda")
						.crearAlias("locDocGenDeuda.obligacion",
								"locObligacion")
								.setDistinct(true)
								.setProyeccion(Proyeccion.PROP("locObligacion.idObligacion"))
								.crearAlias("locObligacion.documentoEspecializado",
										"locDocEsp")
										.crearAlias("tipoTasa", "locTipoTasa")
										.crearAlias("periodo", "locPeriodo")
										.add(Restriccion.NULO("registroCancelacion"))
										.add(Restriccion.DISTINTO("locObligacion.estado",
												Obligacion.Estado.ANULADO))
												.add(Restriccion.IGUAL("numeroCuota", 1))
												.add(Restriccion.JPQL("TYPE(locDocEsp) = DocumentoTGI"));

				if (locPeriodoAnterior != null) {
					locCriterio.add(Restriccion.IGUAL("periodo",
							locPeriodoAnterior));
				}

				if (pPeriodicidad != null) {
					locCriterio.add(Restriccion.IGUAL(
							"locTipoTasa.periodicidadCuotas", pPeriodicidad));
				}

				if (pPersona != null) {
					locCriterio.add(Restriccion.IGUAL("locObligacion.persona",
							pPersona));
				}

				if (pParcela != null) {
					locCriterio.add(Restriccion.IGUAL("locDocEsp.parcela",
							pParcela));
				}
				// Si fue cancelada de la misma forma su estado debe ser pagada,
				// si esto no se agrega puede traer reliquidaciones y
				// refinanciaciones
				locCriterio.add(Restriccion.IGUAL("estado",
						RegistroDeuda.EstadoRegistroDeuda.PAGADA));

				locListaIdObligacionesIgualFormaPago = locCriterio.list();

				locListaIdObligacionesIgualFormaPago = this
						.getListaSinReliquidacionesTGIVencidas(
								locPeriodoAnterior,
								locListaIdObligacionesIgualFormaPago);
			}

			Criterio crit = Criterio.getInstance(entityManager, LiquidacionTasa.class)
					.crearAlias("docGeneradorDeuda", "locDocGenDeuda")
					.crearAlias("locDocGenDeuda.obligacion", "locObligacion")
					.crearAlias("locObligacion.documentoEspecializado",	"locDocEsp")
					.crearAlias("tipoTasa", "locTipoTasa")
					.crearAlias("periodo", "locPeriodo")
					.add(Restriccion.JPQL("TYPE(locDocEsp) = DocumentoTGI"))
					.add(Orden.ASC("periodo"))
					.add(Orden.ASC("locObligacion.persona"));

			if (pCuota != null) {
				if (pCuota.getPeriodicidad().equals(Periodicidad.ANUAL)) {
					crit.add(Restriccion.IGUAL("locPeriodo.fechaInicio",
							pCuota.getPeriodo().getFechaInicio()));
				} else {
					crit.add(Restriccion.IGUAL("cuotaLiquidacion", pCuota));
				}
			}

			if (pPeriodicidad != null) {
				crit.add(Restriccion.IGUAL("locTipoTasa.periodicidadCuotas",
						pPeriodicidad));
			}

			if (pNumeroCuota != null) {
				crit.add(Restriccion.IGUAL("numeroCuota", pNumeroCuota));
			}

			if (pPersona != null) {
				crit.add(Restriccion.IGUAL("locObligacion.persona", pPersona));
			}

			if (pParcela != null) {
				crit.add(Restriccion.IGUAL("locDocEsp.parcela", pParcela));
			}

			if (pTipoDeuda != null) {
				crit.add(Restriccion.IGUAL("tipoDeuda", pTipoDeuda));
			}

			if (pEstadoRegistroDeuda != null) {
				/*
				 * si el estado es vigente o vencida traigo aquellas que tengan
				 * cualquiera de los 2 estados en la bd,esto es asi porque el
				 * estado vigente o vencida se calcula dinamicamente, por lo
				 * tanto debo traer aquellas quetengan cualquiera de estos 2
				 * estados ya que mas adelante se compara si el estado es el
				 * correcto.Si no pusiera esta condicion estaria trayendo con
				 * cualquier estado, de esta forma acorto la busqueda
				 */
				if (pEstadoRegistroDeuda
						.equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)
						|| pEstadoRegistroDeuda
						.equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)) {
					crit.add(Restriccion.OR(Restriccion.IGUAL("estado",
							RegistroDeuda.EstadoRegistroDeuda.VIGENTE),
							Restriccion.IGUAL("estado",
									RegistroDeuda.EstadoRegistroDeuda.VENCIDA)));
				} else {
					crit.add(Restriccion.IGUAL("estado", pEstadoRegistroDeuda));
				}
			}

			List locListadoLiquidacionesTasa = crit.list();
			Map<AuxIndice, List<LiquidacionTasa>> locListaLiquidacionesTasasOrdenadas = new HashMap<AuxIndice, List<LiquidacionTasa>>();
			for (Object locObjeto : locListadoLiquidacionesTasa) {

				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) locObjeto;
				locLiquidacionTasa.getPersona().toString();
				locLiquidacionTasa.getPersona().getDomicilio().toString();
				locLiquidacionTasa.getPersona().getDomicilioPostal().toString();
				boolean locAgregar = true;

				if ((pIgualFormaPago && locListaIdObligacionesIgualFormaPago
						.isEmpty())
						|| (!locListaIdObligacionesIgualFormaPago.isEmpty() && !locListaIdObligacionesIgualFormaPago
								.contains(locLiquidacionTasa
										.getDocGeneradorDeuda().getObligacion()
										.getIdObligacion()))) {
					locAgregar = false;
				}

				if (pEstadoRegistroDeuda != null
						&& !pEstadoRegistroDeuda
						.equals(RegistroDeuda.EstadoRegistroDeuda.PAGADA)) {
					if (!locLiquidacionTasa.getEstado().equals(
							pEstadoRegistroDeuda)) {
						locAgregar = false;
					}
				}

				if (locAgregar) {
					// Indice
					Persona locPersona = locLiquidacionTasa
							.getDocGeneradorDeuda().getObligacion()
							.getPersona();
					Calendar fechaInicioPeriodo = locLiquidacionTasa
							.getCuotaLiquidacion().getPeriodo().getFechaInicio();
					Parcela locParcela = locLiquidacionTasa
							.getDocGeneradorDeuda().getObligacion()
							.getDocumentoEspecializado().getParcela();

					AuxIndice indice = new AuxIndice(locParcela, locPersona,
							fechaInicioPeriodo);

					List<LiquidacionTasa> locListaLiquidaciones = locListaLiquidacionesTasasOrdenadas
							.get(indice);
					locListaLiquidaciones = locListaLiquidacionesTasasOrdenadas
							.get(indice);

					if (locListaLiquidaciones == null) {
						locListaLiquidaciones = new ArrayList<LiquidacionTasa>();
						locListaLiquidaciones.add(locLiquidacionTasa);
						locListaLiquidacionesTasasOrdenadas.put(indice,
								locListaLiquidaciones);
					} else {
						if (!locListaLiquidaciones.contains(locLiquidacionTasa)) {
							// HARDCODEADO LA VALIDACIÓN QUE TE TRAIGA LA
							// PRIMERA CUOTA DE ANUAL:MENSUAL
							if (locLiquidacionTasa.getCuotaLiquidacion()
									.getPeriodicidad()
									.equals(Periodicidad.ANUAL)) {
								// if
								// (locTasa.getTipoTasa().getPeriodicidadCuotas().equals(Periodicidad.MENSUAL)){
								//								if (locLiquidacionTasa.getTipoTasa()
								//										.getPeriodicidadCuotas()
								//										.equals(Periodicidad.MENSUAL)) {
								//									boolean esPrimeraMensual = true;
								//									// Filtrar solo la primera
								//									LiquidacionTasa locEliminarDeLista = null;
								//									for (LiquidacionTasa locCadaLiquidacion : locListaLiquidaciones) {
								//										Tasa locTasaGuardada = (Tasa) locCadaLiquidacion
								//												.getDocGeneradorDeuda();
								//										if ((locTasaGuardada.getTipoTasa()
								//												.getPeriodicidadCuotas()
								//												.equals(Periodicidad.MENSUAL))
								//												&& (locTasaGuardada
								//														.getTipoTasa()
								//														.getPeriodicidad()
								//														.equals(Periodicidad.ANUAL))) {
								//											if (locLiquidacionTasa
								//													.getNumeroCuota() < locCadaLiquidacion
								//													.getNumeroCuota()) {
								//												locEliminarDeLista = locCadaLiquidacion;
								//											} else {
								//												esPrimeraMensual = false;
								//											}
								//										}
								//									}
								//									// Siempre va a haber 1 y solo 1 en la lista
								//									// de periodicidad ANUAL:MENSUAL
								//									if (locEliminarDeLista != null) {
								//										locListaLiquidaciones
								//												.remove(locEliminarDeLista);
								//										locListaLiquidaciones
								//												.add(locLiquidacionTasa);
								//									} else {
								//										if (esPrimeraMensual) {
								//											locListaLiquidaciones
								//													.add(locLiquidacionTasa);
								//										}
								//									}

								//								} else {
								locListaLiquidaciones
								.add(locLiquidacionTasa);
								//								}
							} else {
								locListaLiquidaciones.add(locLiquidacionTasa);
							}
						}
					}
				}
			}

			/*
			 * Tengo que terminar de leer el listado de las liquidaciones y
			 * asignarle a cada 1 un reporte (hay que convertir el list a un
			 * array primitivo) después mandarlo al reporte y obtener la lista
			 * completa
			 */
			List<LiquidacionDS> locListaDataSource = new ArrayList<LiquidacionDS>();

			for (AuxIndice locIndice : locListaLiquidacionesTasasOrdenadas
					.keySet()) {
				List<LiquidacionTasa> locListaLiquidacionesTasa = locListaLiquidacionesTasasOrdenadas
						.get(locIndice);
//				LiquidacionTgiDS locDS = new LiquidacionTgiDS(
//						locListaLiquidacionesTasa, null, this.getTituloReporte(), this.getSubtituloReporte());
//				locListaDataSource.add(locDS);
			}
			LiquidacionAgrupadaDS locLiquidacionAgrupadaDS = new LiquidacionAgrupadaDS(
					locListaDataSource,this.getLogoMunicipalidad());

			this.ordenarListaReportes(locLiquidacionAgrupadaDS);
			this.ordenarPorLocalidad(locLiquidacionAgrupadaDS);

			URL urlReporte = this.getClass().getResource(
					"/com/trascender/saic/reporte/compilado/"
							+ locLiquidacionAgrupadaDS.getNombreReporte());
			JasperReport reporte = (JasperReport) JRLoader
					.loadObject(urlReporte);
			reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					locLiquidacionAgrupadaDS.getMapaParametros(),
					locLiquidacionAgrupadaDS);
			return jasperPrint;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ordena por calle y numero
	 * 
	 * @param pLiquidacionAgrupadaDS
	 */
	private void ordenarListaReportes(
			LiquidacionAgrupadaDS pLiquidacionAgrupadaDS) {
		List<LiquidacionDS> locListado = pLiquidacionAgrupadaDS
				.getListaLiquidacionesDS();

		java.util.Collections.sort(locListado, new Comparator<LiquidacionDS>() {
			@Override
			public int compare(LiquidacionDS o1, LiquidacionDS o2) {
				int resultadoDomicilio = o1
						.getLiquidacionTasa()
						.getDocGeneradorDeuda()
						.getObligacion()
						.getDocumentoEspecializado()
						.getDomicilio()
						.getCalle()
						.compareTo(
								o2.getLiquidacionTasa().getDocGeneradorDeuda()
								.getObligacion()
								.getDocumentoEspecializado()
								.getDomicilio().getCalle());
				int resultadoCodPostal = o1
						.getLiquidacionTasa()
						.getDocGeneradorDeuda()
						.getObligacion()
						.getDocumentoEspecializado()
						.getDomicilio()
						.getLocalidad()
						.getCodigoPostal()
						.compareTo(
								o2.getLiquidacionTasa().getDocGeneradorDeuda()
								.getObligacion()
								.getDocumentoEspecializado()
								.getDomicilio().getLocalidad()
								.getCodigoPostal());

				if (resultadoDomicilio == 0) {
					try {
						Integer locNumeroO1 = Integer.valueOf(o1
								.getLiquidacionTasa().getDocGeneradorDeuda()
								.getObligacion().getDocumentoEspecializado()
								.getDomicilio().getNumero());
						Integer locNumeroO2 = Integer.valueOf(o2
								.getLiquidacionTasa().getDocGeneradorDeuda()
								.getObligacion().getDocumentoEspecializado()
								.getDomicilio().getNumero());
						resultadoDomicilio = locNumeroO1.compareTo(locNumeroO2);
					} catch (Exception e) {
						// Significa q no pudo castear un numero a Integer
						resultadoDomicilio = o1
								.getLiquidacionTasa()
								.getDocGeneradorDeuda()
								.getObligacion()
								.getDocumentoEspecializado()
								.getDomicilio()
								.getNumero()
								.compareTo(
										o2.getLiquidacionTasa()
										.getDocGeneradorDeuda()
										.getObligacion()
										.getDocumentoEspecializado()
										.getDomicilio().getNumero());
					}
				}
				return resultadoCodPostal + resultadoDomicilio;
			}
		});
		System.gc();
	}

	private void ordenarPorLocalidad(
			LiquidacionAgrupadaDS pLiquidacionAgrupadaDS) throws Exception {
		HashMap<String, List<LiquidacionDS>> locListaReportesOrdenados = new HashMap<String, List<LiquidacionDS>>();


		Pais locPais = this.businessMunicipalidadLocal.findPais(new FiltroPais("Argentina")).getListaResultados().get(0);
		List<Localidad> locListaLocalidades = this.businessMunicipalidadLocal.findLocalidad(new FiltroLocalidad(null,null,null,locPais)).getListaResultados();

		// setelo las claves del map asi voy agregando los reportes ordenados en
		// cada lista de acuerdo al codigo postal
		for (Localidad cadaLocalidad : locListaLocalidades) {
			locListaReportesOrdenados.put(cadaLocalidad.getCodigoPostal(),
					new ArrayList<LiquidacionDS>());
		}

		for (LiquidacionDS cadaLiquidacionDS : pLiquidacionAgrupadaDS
				.getListaLiquidacionesDS()) {
			String locCodigoPostal = cadaLiquidacionDS.getLiquidacionTasa()
					.getDocGeneradorDeuda().getObligacion()
					.getDocumentoEspecializado().getDomicilio().getLocalidad()
					.getCodigoPostal();
			locListaReportesOrdenados.get(locCodigoPostal).add(
					cadaLiquidacionDS);
		}

		pLiquidacionAgrupadaDS.getListaLiquidacionesDS().clear();
		for (String cadaCodigoPostal : locListaReportesOrdenados.keySet()) {
			pLiquidacionAgrupadaDS.getListaLiquidacionesDS().addAll(
					locListaReportesOrdenados.get(cadaCodigoPostal));
		}
	}

	@Override
	public JasperPrint getReporteListadoCuotasRefinanciacion(
			List<CuotaRefinanciacion> pListaCuotasRefinanciacion, Usuario pUsuario) throws Exception {
		try {
			CuotaRefinanciacionDS cuotasDS = new CuotaRefinanciacionDS(pListaCuotasRefinanciacion, this.getTituloReporte(), pUsuario);
			cuotasDS.getMapaParametros().put("PAR_IMAGEN", getLogoMunicipalidad());
			JasperPrint jp = this.getJasperPrint2(cuotasDS);
			return jp;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private JasperPrint getJasperPrint2(TrascenderDataSource pTrascenderDataSource) throws Exception {
		File locFile = new File(SecurityMgr.getInstance().getMunicipalidad().getRutaReportes() + pTrascenderDataSource.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(locFile);
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, pTrascenderDataSource.getMapaParametros(), pTrascenderDataSource);
		
		return jasperPrint;
	}

	@Override
	public JasperPrint getReporteReliquidacion(RegistroDeuda pRegistroDeuda,
			Usuario pUsuario, boolean pImprimirReporteSimple) throws Exception {
		if (pImprimirReporteSimple) {
			// Impresion simple de la reliquidacion.
			Reliquidacion locReliquidacion = (Reliquidacion) Criterio
					.getInstance(entityManager, Reliquidacion.class)
					.crearAlias("liquidacionTasa", "locLiquidacionTasa")
					.add(Restriccion.IGUAL(
							"locLiquidacionTasa.idRegistroDeuda",
							pRegistroDeuda.getIdRegistroDeuda()))
							.uniqueResult();

			if (locReliquidacion != null) {
				locReliquidacion.toString();
				locReliquidacion.getLiquidacionTasa().toString();
				locReliquidacion.getLiquidacionTasa()
				.getDocGeneradorDeuda().toString();
				ReliquidacionDS locReliquidacionDS = new ReliquidacionDS(
						locReliquidacion, null, this.getTituloReporte(), this.getSubtituloReporte());
				JasperPrint jasperPrint = this
						.getJasperPrint(locReliquidacionDS);
				return jasperPrint;
			} else
				return null;
		} else {
			// Impresion completa de la liquidacion reliquidada
			DocHabilitanteEspecializado locDocHabilitanteEspecializado = pRegistroDeuda
					.getDocGeneradorDeuda().getObligacion()
					.getDocumentoEspecializado();
			Parcela locParcela = locDocHabilitanteEspecializado
					.getParcela();

			// Parametros generales de todos los reportes que no sean
			// Documentos Refinanciacion
			CuotaLiquidacion locPeriodo = null;
			Persona locPersona = null;
			EstadoRegistroDeuda locEstadoRegistroDeuda = null;
			TipoDeuda locTipoDeuda = null;
			if (!(locDocHabilitanteEspecializado instanceof DocumentoRef)) {
				if (pRegistroDeuda.getCuotaLiquidacion() != null) {
					locPeriodo = pRegistroDeuda.getCuotaLiquidacion();
				}
				if (pRegistroDeuda.getPersona() != null) {
					locPersona = pRegistroDeuda.getPersona();
				}
				locTipoDeuda = pRegistroDeuda.getTipoDeuda();
			}
			if (locDocHabilitanteEspecializado instanceof DocumentoTGI) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) pRegistroDeuda;
				Periodicidad locPeriodicidad = null;
				Integer locNumeroCuota = null;
				//					if (locLiquidacionTasa.getTipoTasa()
				//							.getPeriodicidadCuotas() != null
				//							&& !locLiquidacionTasa.getTipoTasa()
				//									.getPeriodicidadCuotas()
				//									.equals(Periodicidad.ANUAL)) {
				//						locPeriodicidad = locLiquidacionTasa.getTipoTasa()
				//								.getPeriodicidadCuotas();
				//					}
				//					if (locLiquidacionTasa.getNumeroCuota() != null
				//							&& !locLiquidacionTasa.getTipoTasa()
				//									.getPeriodicidadCuotas()
				//									.equals(Periodicidad.ANUAL)) {
				//						locNumeroCuota = locLiquidacionTasa.getNumeroCuota();
				//					}
				return this.getReporteTGI(locPeriodo, locPersona,
						locParcela, locPeriodicidad, locNumeroCuota,
						locEstadoRegistroDeuda, false, locTipoDeuda);
			} else if (locDocHabilitanteEspecializado instanceof DocumentoPlanObra) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) pRegistroDeuda;
				return this.getReportePFO(locPersona, null, null, null,
						locPeriodo, locEstadoRegistroDeuda, locTipoDeuda,
						locLiquidacionTasa.getNumeroCuota(),
						locLiquidacionTasa.getNumeroCuota());
			} else if (locDocHabilitanteEspecializado instanceof DocumentoSHPS) {
				List<LiquidacionTasa> locListaLiquidacionTasa = new ArrayList<LiquidacionTasa>();
				locListaLiquidacionTasa.add((LiquidacionTasa) entityManager.merge(pRegistroDeuda));
				return getReporteSHPS(locListaLiquidacionTasa, pUsuario);///////////////////////////////////////////
			} else if (locDocHabilitanteEspecializado instanceof DocumentoOSP) {
				DocumentoOSP locDocumentoOSP = (DocumentoOSP) locDocHabilitanteEspecializado;
				String locCuentaSubcuenta = locDocumentoOSP
						.getNumeroCuenta().toString()
						+ "/"
						+ locDocumentoOSP.getNumeroSubCuenta();
				return this.getReporteOSP(locPersona, null, null, null,
						null, locPeriodo, locEstadoRegistroDeuda,
						locCuentaSubcuenta);
			} else if (locDocHabilitanteEspecializado instanceof DocumentoRef) {
				DocumentoRefinanciacion locDocumento = (DocumentoRefinanciacion) pRegistroDeuda
						.getDocGeneradorDeuda();
//				return this
//						.getReporteListadoCuotasRefinanciacion(locDocumento.getListaRegistrosDeuda());
			}
		}
		return null;
	}

	@Override
	public JasperPrint getReporteReconocimientoDeuda(
			DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception {
		try {
			DocumentoRefinanciacion locDocumento = Criterio.getInstance(entityManager, DocumentoRefinanciacion.class)
					.add(Restriccion.IGUAL("idDocGeneradorDeuda", pDocumentoRefinanciacion.getIdDocGeneradorDeuda()))
					.uniqueResult();

			if (locDocumento != null) {
				locDocumento.toString();
				locDocumento.getRegCancelacionPorRefinanciacion()
				.getDigestoMunicipal().toString();
				locDocumento.getObligacion().getPersona().toString();
				locDocumento.getListaRegistrosDeuda().toString();
				List<CuotaRefinanciacionDS> locListaCuotasDS = new ArrayList<CuotaRefinanciacionDS>();
				for (RegistroDeuda cadaRegistroDeuda : locDocumento.getListaRegistrosDeuda()) {
					CuotaRefinanciacion locCuotaRefinanciacion = (CuotaRefinanciacion) cadaRegistroDeuda;
				}
				ReconocimientoDeudaDS locReconocimientoDeudaDS = new ReconocimientoDeudaDS(
						locDocumento);
				JasperPrint jp = this.getJasperPrint(locReconocimientoDeudaDS);
				return jp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}


	private JasperPrint getJasperPrint(
			TrascenderDataSource pTrascenderDataSource) throws Exception {
		URL urlReporte = this.getClass().getResource(
				"/com/trascender/saic/reporte/compilado/"
						+ pTrascenderDataSource.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(urlReporte);
		reporte.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
				pTrascenderDataSource.getMapaParametros(),
				pTrascenderDataSource);
		return jasperPrint;
	}

//	private void ordenarListaCuotas(List<CuotaRefinanciacionDS> pListaCuotasRefinanciacionDS){
//		Collections.sort(pListaCuotasRefinanciacionDS, new Comparator<CuotaRefinanciacionDS>() {
//			@Override
//			public int compare(CuotaRefinanciacionDS o1, CuotaRefinanciacionDS o2) {
//				return o1.getCuotaRefinanciacion().getNumeroCuota().compareTo(o2.getCuotaRefinanciacion().getNumeroCuota());
//			}
//		});
//	}

	private byte[] getLogoMunicipalidad() {
		return SecurityMgr.getInstance().getMunicipalidad().getLogo();
	}

	private String getTituloReporte() {
		if (this.parametroTitulo == null){
			try{
				this.parametroTitulo = SecurityMgr.getInstance().getMunicipalidad().getEncabezadoReportes();
			} catch (Exception e){
				this.parametroTitulo = null;
			}
		}
		return parametroTitulo;
	}

	private String getSubtituloReporte() {
		if (this.parametroSubtitulo == null){
			try{
				this.parametroSubtitulo = SecurityMgr.getInstance().getMunicipalidad().getSubencabezadoReportes();
			} catch (Exception e){
				this.parametroSubtitulo = null;
			}
		}
		return parametroSubtitulo;
	}

	private InputStream getInputStreamLogo(){
		if (this.logoInputStream == null){
			this.logoInputStream = new ByteArrayInputStream(SecurityMgr.getInstance().getMunicipalidad().getLogo());
		}
		return this.logoInputStream;
	}

	@Override
	public JasperPrint getReporteTasaUnificada(	List<LiquidacionTasaRefer> pListaLiquidacionesTasa, List<TipoObligacion> pLista, Usuario pUsuario) throws Exception {
		boolean shps = false;
		for (TipoObligacion cadaTipo : pLista){
			if (cadaTipo.getNombre().equals("SHPS")){
				shps = true;
				break;
			}
		}

		if (shps) {
			List<LiquidacionTasa> locListaLiquidaciones = new ArrayList<LiquidacionTasa>();
			for (LiquidacionTasaRefer cadaLiquidacionRefer : pListaLiquidacionesTasa) {
				LiquidacionTasaAgrupada locLiquidacionAgrupada = new LiquidacionTasaAgrupada();
				for (Long id : cadaLiquidacionRefer.getIdsRegistrosDeuda()){
					LiquidacionTasa cadaLiquidacion = entityManager.find(LiquidacionTasa.class, id);
					locListaLiquidaciones.add(cadaLiquidacion);
				}
			}
			return this.getReporteSHPS(locListaLiquidaciones, pUsuario);
		} else {

			//Junto los ids para usarlos como subselect y levantar todas las liquidaciones juntas.
			List<LiquidacionTasaAgrupada> locListaLiquidacionTasaAgrupada  = new ArrayList<LiquidacionTasaAgrupada>();
			for (LiquidacionTasaRefer cadaLiquidacionRefer : pListaLiquidacionesTasa) {
				LiquidacionTasaAgrupada locLiquidacionAgrupada = new LiquidacionTasaAgrupada();
				for (Long id : cadaLiquidacionRefer.getIdsRegistrosDeuda()){
					LiquidacionTasa cadaLiquidacion = entityManager.find(LiquidacionTasa.class, id);
					locLiquidacionAgrupada.getListaLiquidacionesTasa().add(cadaLiquidacion);
				}
				locListaLiquidacionTasaAgrupada.add(locLiquidacionAgrupada);
			}

			this.ordenarListaLiquidacionesAgrupadas(locListaLiquidacionTasaAgrupada);
			LiquidacionTasaAgrupadaDS locDS = new LiquidacionTasaAgrupadaDS(locListaLiquidacionTasaAgrupada, 
					getLogoMunicipalidad(), getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal, pUsuario);
			JasperPrint locJasperPrint = this.getJasperPrint(locDS);
			return locJasperPrint;
		}
	}

	@Override
	public void imprimirLiquidacionesTasasEnServidor(List<LiquidacionTasa> pListaLiquidacionesTasa, Usuario pUsuario){
		System.out.println("Imprimir en servidor");
		List<LiquidacionTasaAgrupada> locListaLiquidacionTasaAgrupada  = new ArrayList<LiquidacionTasaAgrupada>();
		int i = 0;
		LiquidacionTasaAgrupada locLiquidacionAgrupada = new LiquidacionTasaAgrupada();
		for (LiquidacionTasa cadaLiquidacion : pListaLiquidacionesTasa) {
			if (i++ == 200){
				System.out.println("Mensaje para mantener vivo el ejb (?");
				i = 0;
			}
			
			locLiquidacionAgrupada.getListaLiquidacionesTasa().add(cadaLiquidacion);
		}
		locListaLiquidacionTasaAgrupada.add(locLiquidacionAgrupada);
		
		System.out.println("Ordenando listas..");
		this.ordenarListaLiquidacionesAgrupadas(locListaLiquidacionTasaAgrupada);
		System.out.println("Listas ordenada.");
		//		this.businessThreader.imprimirLiquidacionesEnServidor(locListaLiquidacionTasaAgrupada, getLogoMunicipalidad(),
		//				getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal);

		// ORDEN ASCENDENTE!!
		//
		int tamanioADividir = 1000;

		int total = locListaLiquidacionTasaAgrupada.size();
		// primer pasada!!
		int desde = 0;
		int hasta;
		
		if(tamanioADividir < total) {
			hasta = tamanioADividir;
		}
		else { 
			hasta = total;
		}
		
		while (desde < total) {
			List<LiquidacionTasaAgrupada> locSubLista = locListaLiquidacionTasaAgrupada.subList(desde, hasta);
			LiquidacionTasaAgrupadaDS dataSource = 
					new LiquidacionTasaAgrupadaDS(locSubLista, getLogoMunicipalidad(), getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal, pUsuario);
			System.out.println("Salida para mantener vivo");
			String locFecha = new SimpleDateFormat("dd-MM-aaaa").format(new Date());
			String nombre = "/opt/reportes/"+locFecha+"_Liquidaciones_"+(desde + 1)+"-"+hasta+".pdf";
			try{
				JasperPrint print = getJasperPrint(dataSource);
				JasperExportManager.exportReportToPdfFile(print, nombre);
			} catch (Exception e){
				e.printStackTrace();
			}
			//			Thread locThread = new Thread(new ImpresorAPDF(dataSource, nombre));
			//			locThread.start();
			//			locListaThread.add(locThread);
			desde += tamanioADividir;
			hasta += tamanioADividir;
			if (hasta > total) hasta = total;
		}

		// ORDEN DESCENDENTE!!
		//
		//		int tamañoADividir = 1000;
		//
		//		int hasta = locListaLiquidacionTasaAgrupada.size();
		//		int desde = hasta - tamañoADividir;
		//		while (hasta > 0) {
		//			if (desde < 0) desde = 0;
		//			List<LiquidacionTasaAgrupada> locSubLista = locListaLiquidacionTasaAgrupada.subList(desde, hasta);
		//			LiquidacionTasaAgrupadaDS dataSource = 
		//					new LiquidacionTasaAgrupadaDS(locSubLista, getLogoMunicipalidad(), getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal);
		//			System.out.println("Salida para mantener vivo");
		//			String locFecha = new SimpleDateFormat("dd-MM-aaaa").format(new Date());
		//			String nombre = "/opt/reportes/"+locFecha+"_Liquidaciones_"+(desde + 1)+"-"+hasta+".pdf";
		//			try{
		//				JasperPrint print = getJasperPrint(dataSource);
		//				JasperExportManager.exportReportToPdfFile(print, nombre);
		//			} catch (Exception e){
		//				e.printStackTrace();
		//			}
		//			//			Thread locThread = new Thread(new ImpresorAPDF(dataSource, nombre));
		//			//			locThread.start();
		//			//			locListaThread.add(locThread);
		//			hasta -= tamañoADividir;
		//			desde -= tamañoADividir;
		//		}
	}
	
	@Override
	public void imprimirLiquidacionesEnServidor(List<LiquidacionTasaRefer> pListaLiquidacionesTasa, Usuario pUsuario){
		//Es SHPS
		if (pListaLiquidacionesTasa.get(0).getTipo().contains("SHPS")) {
			System.out.println("SHPS!");
			List<LiquidacionTasa> locListaLiquidaciones = new ArrayList<LiquidacionTasa>();
			for (LiquidacionTasaRefer cadaLiquidacionRefer : pListaLiquidacionesTasa) {
				for (Long id : cadaLiquidacionRefer.getIdsRegistrosDeuda()){
					LiquidacionTasa cadaLiquidacion = entityManager.find(LiquidacionTasa.class, id);
					locListaLiquidaciones.add(cadaLiquidacion);
				}
			}
			try{
				JasperPrint jp = this.getReporteSHPS(locListaLiquidaciones, pUsuario);
				String locFecha = new SimpleDateFormat("dd-MM-aaaa").format(new Date());
				String nombre = "/opt/reportes/"+locFecha+"_Liquidaciones_SHPS.pdf";
				JasperExportManager.exportReportToPdfFile(jp, nombre);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return;
		}

		System.out.println("Imprimir en servidor");
		List<LiquidacionTasaAgrupada> locListaLiquidacionTasaAgrupada  = new ArrayList<LiquidacionTasaAgrupada>();
		int i = 0;
		for (LiquidacionTasaRefer cadaLiquidacionRefer : pListaLiquidacionesTasa) {
			if (i++ == 200){
				System.out.println("Mensaje para mantener vivo el ejb (?");
				i = 0;
			}
			LiquidacionTasaAgrupada locLiquidacionAgrupada = new LiquidacionTasaAgrupada();
			for (Long id : cadaLiquidacionRefer.getIdsRegistrosDeuda()){
				LiquidacionTasa cadaLiquidacion = entityManager.find(LiquidacionTasa.class, id);
				locLiquidacionAgrupada.getListaLiquidacionesTasa().add(cadaLiquidacion);
			}
			locListaLiquidacionTasaAgrupada.add(locLiquidacionAgrupada);
		}
		System.out.println("Ordenando listas..");
		this.ordenarListaLiquidacionesAgrupadas(locListaLiquidacionTasaAgrupada);
		System.out.println("Listas ordenada.");
		//		this.businessThreader.imprimirLiquidacionesEnServidor(locListaLiquidacionTasaAgrupada, getLogoMunicipalidad(),
		//				getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal);

		// ORDEN ASCENDENTE!!
		//
		int tamanioADividir = 1000;

		int total = locListaLiquidacionTasaAgrupada.size();
		// primer pasada!!
		int desde = 0;
		int hasta;
		
		if(tamanioADividir < total) {
			hasta = tamanioADividir;
		}
		else { 
			hasta = total;
		}
		
		while (desde < total) {
			List<LiquidacionTasaAgrupada> locSubLista = locListaLiquidacionTasaAgrupada.subList(desde, hasta);
			LiquidacionTasaAgrupadaDS dataSource = 
					new LiquidacionTasaAgrupadaDS(locSubLista, getLogoMunicipalidad(), getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal, pUsuario);
			System.out.println("Salida para mantener vivo");
			String locFecha = new SimpleDateFormat("dd-MM-aaaa").format(new Date());
			String nombre = "/opt/reportes/"+locFecha+"_Liquidaciones_"+(desde + 1)+"-"+hasta+".pdf";
			try{
				JasperPrint print = getJasperPrint(dataSource);
				JasperExportManager.exportReportToPdfFile(print, nombre);
			} catch (Exception e){
				e.printStackTrace();
			}
			//			Thread locThread = new Thread(new ImpresorAPDF(dataSource, nombre));
			//			locThread.start();
			//			locListaThread.add(locThread);
			desde += tamanioADividir;
			hasta += tamanioADividir;
			if (hasta > total) hasta = total;
		}

		// ORDEN DESCENDENTE!!
		//
		//		int tamañoADividir = 1000;
		//
		//		int hasta = locListaLiquidacionTasaAgrupada.size();
		//		int desde = hasta - tamañoADividir;
		//		while (hasta > 0) {
		//			if (desde < 0) desde = 0;
		//			List<LiquidacionTasaAgrupada> locSubLista = locListaLiquidacionTasaAgrupada.subList(desde, hasta);
		//			LiquidacionTasaAgrupadaDS dataSource = 
		//					new LiquidacionTasaAgrupadaDS(locSubLista, getLogoMunicipalidad(), getTituloReporte(), getSubtituloReporte(), businessZonificacionLocal);
		//			System.out.println("Salida para mantener vivo");
		//			String locFecha = new SimpleDateFormat("dd-MM-aaaa").format(new Date());
		//			String nombre = "/opt/reportes/"+locFecha+"_Liquidaciones_"+(desde + 1)+"-"+hasta+".pdf";
		//			try{
		//				JasperPrint print = getJasperPrint(dataSource);
		//				JasperExportManager.exportReportToPdfFile(print, nombre);
		//			} catch (Exception e){
		//				e.printStackTrace();
		//			}
		//			//			Thread locThread = new Thread(new ImpresorAPDF(dataSource, nombre));
		//			//			locThread.start();
		//			//			locListaThread.add(locThread);
		//			hasta -= tamañoADividir;
		//			desde -= tamañoADividir;
		//		}
	}

	private void ordenarListaLiquidacionesAgrupadas(List<LiquidacionTasaAgrupada> pListado) {
		java.util.Collections.sort(pListado, new  Comparator<LiquidacionTasaAgrupada>(){
			@Override
			public int compare(LiquidacionTasaAgrupada o1, LiquidacionTasaAgrupada o2) {
				Domicilio locDomicilio1;
				try {
					locDomicilio1 = o1.getDocHabilitanteEspecializado(DocumentoTGI.class).getDomicilio();
				} catch (Exception e){
					locDomicilio1 = o1.getDocHabilitanteEspecializado(DocumentoOSP.class).getDomicilio();
				}
				Domicilio locDomicilio2;
				try {
					locDomicilio2 = o2.getDocHabilitanteEspecializado(DocumentoTGI.class).getDomicilio();
				} catch (Exception e){
					locDomicilio2 = o2.getDocHabilitanteEspecializado(DocumentoOSP.class).getDomicilio();
				}


				int resultado = 0;
				try{
					resultado = locDomicilio1
							.getCalle().compareTo(locDomicilio2.getCalle());
				} catch (Exception e){
					//Lo dejamos en 0
				}
				if(resultado == 0){
					try{
						Integer locNumeroO1 = Integer.valueOf(locDomicilio1.getNumero());
						Integer locNumeroO2 = Integer.valueOf(locDomicilio2.getNumero());
						resultado = locNumeroO1.compareTo(locNumeroO2);
					}
					catch(Exception e){
						//Significa q no pudo castear un numero a Integer
						resultado = locDomicilio1.getNumero().compareTo(locDomicilio2.getNumero());
					}
				}
				return resultado;
			}
		}
				);
	}

	@Override
	public JasperPrint getReporteLiquidacionAutomotor(List<LiquidacionTasa> pListaLiquidaciones) throws Exception{
		List<LiquidacionTasa> locLiquidacionTasa = new ArrayList<LiquidacionTasa>();
		for(LiquidacionTasa cadaLiquidacionTasa : pListaLiquidaciones){
			locLiquidacionTasa.add(entityManager.merge(cadaLiquidacionTasa));
		}
		LiquidacionAutomotorDS locLiquidacionAutomotorDS = new LiquidacionAutomotorDS(locLiquidacionTasa, this.getTituloReporte(), this.getSubtituloReporte());
		File fileReporte = new File("/opt/jboss-6.0.0.Final/server/default/deploy/reportes/" + locLiquidacionAutomotorDS.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(fileReporte);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,	locLiquidacionAutomotorDS.getMapaParametros(), locLiquidacionAutomotorDS);
		entityManager.clear();
		return jasperPrint;
	}
	
	@Override
	public JasperPrint getReporteLiquidacionOSP(List<LiquidacionTasa> pListaLiquidaciones) throws Exception{
		List<LiquidacionTasa> locLiquidacionTasa = new ArrayList<LiquidacionTasa>();
		for(LiquidacionTasa cadaLiquidacionTasa : pListaLiquidaciones){
			locLiquidacionTasa.add(entityManager.merge(cadaLiquidacionTasa));
		}
		LiquidacionOspDS locLiquidacionOspDS = new LiquidacionOspDS(locLiquidacionTasa, this.getTituloReporte(), this.getSubtituloReporte());
		File fileReporte = new File("/opt/jboss-6.0.0.Final/server/default/deploy/reportes/" + locLiquidacionOspDS.getNombreReporte());
		JasperReport reporte = (JasperReport) JRLoader.loadObject(fileReporte);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,	locLiquidacionOspDS.getMapaParametros(), locLiquidacionOspDS);
		entityManager.clear();
		return jasperPrint;
	}
	
	
	public Map<String, JasperPrint> getListaObligacionSHPSPorContador(FiltroObligacionSHPS pFiltro, CuotaLiquidacion pCuota, String pTipoZip) throws Exception {
		
		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoSHPS.class)
				.setModoDebug(true)
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pFiltro.getPersona()))
				.add(Restriccion.ILIKE("numeroInscripcion", pFiltro.getNumeroInscripcion()));
		
		if (pFiltro.getPersona() == null) {
			locCriterio.crearFetchAlias("obligacion.persona", "locPersona");
		}
		
		if (pFiltro.getNumeroInscripcion() == null) {
			locCriterio.crearFetchAlias("domicilio", "locDomicilio")
				.crearFetchAlias("locDomicilio.localidad", "locLocalidad")
				.crearFetchAlias("locDomicilio.relacionCalle", "locCalle1");
		}
		
		locCriterio.setModoDebug(true);
		
		
		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaAtributosDinamicos());
		List<DocumentoSHPS> listaDocumentos = locCriterio.list();

		Map<String, List<DocumentoSHPS>> mapaDocumentos = new HashMap<String, List<DocumentoSHPS>>();
		
		if (pTipoZip.equals("UnPDFPorContador")) {
			for (DocumentoSHPS cadaDocumento: listaDocumentos){
				String llave = cadaDocumento.getContador() != null ? cadaDocumento.getContador().toString() : "SinContador";
				List<DocumentoSHPS> locLista = mapaDocumentos.get(llave);
				if (locLista != null) {
					locLista.add(cadaDocumento);
				} else {
					locLista = new ArrayList<DocumentoSHPS>();
					locLista.add(cadaDocumento);
				}
				mapaDocumentos.put(llave, locLista);
			}
		} else if (pTipoZip.equals("UnPDF")) {
			mapaDocumentos.put("GeneralPorObligaciones", listaDocumentos);
		}
		
		Map<String, JasperPrint> mapaReportesDDJJ = new HashMap<String, JasperPrint>();
		
		for (String cadaLlave : mapaDocumentos.keySet()) {
			List<DocumentoSHPS> cadaLista = mapaDocumentos.get(cadaLlave);
			List<ImprimirDDJJSHPSDS> locListaDataSources = new ArrayList<ImprimirDDJJSHPSDS>();
			for (DocumentoSHPS cadaDocumento: cadaLista) {
				locListaDataSources.add(new ImprimirDDJJSHPSDS(cadaDocumento.getObligacion(), pCuota));
				locListaDataSources.add(new ImprimirDDJJSHPSDS(cadaDocumento.getObligacion(), pCuota));
			}
		
			ImprimirDDJJSHPSPadreDS locImprimirDDJJSHPSPadreDS = new ImprimirDDJJSHPSPadreDS(locListaDataSources);
			String direccionReporte = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
			File locFile = new File(direccionReporte + locImprimirDDJJSHPSPadreDS.getNombreReporte());
			JasperReport reporte = (JasperReport) JRLoader.loadObject(locFile);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
					locImprimirDDJJSHPSPadreDS.getMapaParametros(),
					locImprimirDDJJSHPSPadreDS);
			mapaReportesDDJJ.put(cadaLlave, jasperPrint);
		}
		return mapaReportesDDJJ;
	
	}

}


