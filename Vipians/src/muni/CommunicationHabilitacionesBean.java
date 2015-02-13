/*
 * CommunicationHabilitacionesBean.java
 *
 * Created on 16 de noviembre de 2006, 14:36
 * Copyright Trascender
 */

package muni;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.filtros.FiltroInspector;
import com.trascender.habilitaciones.recurso.filtros.FiltroLibretaSanitaria;
import com.trascender.habilitaciones.recurso.filtros.FiltroLocalComercial;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.filtros.FiltroObra;
import com.trascender.habilitaciones.recurso.filtros.FiltroParcelaCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaObligacion;
import com.trascender.habilitaciones.recurso.filtros.FiltroRubroSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrilla;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroTransporteVehicular;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Difunto;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.osp.AsocServicioOsp;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;
import com.trascender.habilitaciones.system.interfaces.SystemArrendamiento;
import com.trascender.habilitaciones.system.interfaces.SystemBromatologia;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoAutomotor;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoCementerio;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoOSP;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTGI;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTasaMenor;
import com.trascender.habilitaciones.system.interfaces.SystemExencionObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemInspectores;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemPlanFinanciacionObra;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;
import com.trascender.habilitaciones.system.interfaces.SystemReportesHabilitaciones;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.Tasa;

/**
 * <p>
 * Session scope data bean for your application. Create properties here to represent cached data that should be made available across multiple HTTP requests for
 * an individual user.
 * </p>
 * 
 * <p>
 * An instance of this class will be created for you automatically, the first time your application evaluates a value binding expression or method binding
 * expression that references a managed bean using this class.
 * </p>
 */
public class CommunicationHabilitacionesBean extends AbstractSessionBean {

	Properties props = null;
	Context ctx = null;
	// <editor-fold defaultstate="collapsed"
	// desc="Creator-managed Component Definition">
	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesi�n.
	 * </p>
	 */
	public CommunicationHabilitacionesBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemPlantillaObligaciones = (SystemPlantillaObligaciones) ctx.lookup(SystemPlantillaObligaciones.JNDI_NAME);
			this.remoteSystemDocumentoAutomotor = (SystemDocumentoAutomotor) ctx.lookup(SystemDocumentoAutomotor.JNDI_NAME);
			this.remoteSystemObligacion = (SystemObligacion) ctx.lookup(SystemObligacion.JNDI_NAME);
			this.remoteSystemBromatologia = (SystemBromatologia) ctx.lookup(SystemBromatologia.JNDI_NAME);
			this.remoteSystemAlicuota = (SystemAlicuota) ctx.lookup(SystemAlicuota.JNDI_NAME);
			this.remoteSystemInspectores = (SystemInspectores) ctx.lookup(SystemInspectores.JNDI_NAME);
			this.remoteSystemPlanFinanciacionObra = (SystemPlanFinanciacionObra) ctx.lookup(SystemPlanFinanciacionObra.JNDI_NAME);
			this.remoteSystemDocumentoTGI = (SystemDocumentoTGI) ctx.lookup(SystemDocumentoTGI.JNDI_NAME);
			this.remoteSystemDocumentoOSP = (SystemDocumentoOSP) ctx.lookup(SystemDocumentoOSP.JNDI_NAME);
			this.remoteSystemDocumentoTasaMenor = (SystemDocumentoTasaMenor) ctx.lookup(SystemDocumentoTasaMenor.JNDI_NAME);
			this.remoteSystemExencion = (SystemExencionObligacion) ctx.lookup(SystemExencionObligacion.JNDI_NAME);
			this.remoteSystemTipoTasa = (SystemTipoTasa) ctx.lookup(SystemTipoTasa.JNDI_NAME);
			this.remoteSystemReportesHabilitaciones = (SystemReportesHabilitaciones) ctx.lookup(SystemReportesHabilitaciones.JNDI_NAME);
			this.remoteSystemDocumentoCementerio = (SystemDocumentoCementerio) ctx.lookup(SystemDocumentoCementerio.JNDI_NAME);
			this.remoteSystemArrendamientos = (SystemArrendamiento) ctx.lookup(SystemArrendamiento.JNDI_NAME);

			System.out.println("CommunicationHabilitacionesBean");
			FiltroValuacionAcara locFiltroValuacionAcara = new FiltroValuacionAcara();
			locFiltroValuacionAcara.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaValuacionAcara = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ValuacionAcara.serialVersionUID),
					"#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara}", locFiltroValuacionAcara);

			FiltroPlan locFiltroPlan = new FiltroPlan();
			locFiltroPlan.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPlan = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Plan.serialVersionUID), "#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan}",
					locFiltroPlan);

			FiltroObligacionSHPS locFiltroSHPS = new FiltroObligacionSHPS();
			locFiltroSHPS.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocEspSHPS = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoSHPS.serialVersionUID),
					"#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS}", locFiltroSHPS);

			FiltroObligacionTasaMenor locFiltroTasaMenor = new FiltroObligacionTasaMenor();
			locFiltroTasaMenor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocEspTasaMenor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoTasaMenor.serialVersionUID),
					"#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor}", locFiltroTasaMenor);

			FiltroTipoTasa locFiltroTipoTasa = new FiltroTipoTasa();
			locFiltroTipoTasa.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoTasa = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoTasa.serialVersionUID), "#{habilitaciones$ABMTipoTasa$AdminTipoTasa}",
					locFiltroTipoTasa);

			FiltroRubroSHPS locFiltroRubro = new FiltroRubroSHPS();
			locFiltroRubro.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaRubros = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Rubro.serialVersionUID),
					"#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS}", locFiltroRubro);

			FiltroConsumoBasico locFiltroConsumoBasico = new FiltroConsumoBasico();
			locFiltroConsumoBasico.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaConsumoBasico = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ConsumoBasico.serialVersionUID),
					"#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico}", locFiltroConsumoBasico);

			FiltroPlantillaObligacion locFiltroPlantillaOblicacion = new FiltroPlantillaObligacion();
			locFiltroPlantillaOblicacion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPlantillaObligacion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PlantillaObligacion.serialVersionUID),
					"#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion}", locFiltroPlantillaOblicacion);
			FiltroObligacionOSP locFiltroObligacionOsp = new FiltroObligacionOSP();
			locFiltroObligacionOsp.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocEspOSP = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoOSP.serialVersionUID),
					"#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP}", locFiltroObligacionOsp);

			FiltroTransporteVehicular locFiltroTransporteVehicular = new FiltroTransporteVehicular();
			locFiltroTransporteVehicular.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTransporteVehicular = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TransporteVehicular.serialVersionUID),
					"#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular}", locFiltroTransporteVehicular);
			FiltroLibretaSanitaria locFiltroLibretaSanitaria = new FiltroLibretaSanitaria();
			locFiltroLibretaSanitaria.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLibretaSanitaria = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LibretaSanitaria.serialVersionUID),
					"#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria}", locFiltroLibretaSanitaria);
			FiltroObra locFiltroObra = new FiltroObra();
			locFiltroObra.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaObra = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Obra.serialVersionUID), "#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra}",
					locFiltroObra);
			FiltroVehiculo locFiltroVehiculo = new FiltroVehiculo();
			locFiltroVehiculo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaVehiculo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Vehiculo.serialVersionUID),
					"#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo}", locFiltroVehiculo);
			FiltroTipoVehiculo locFiltroTipoVehiculo = new FiltroTipoVehiculo();
			locFiltroTipoVehiculo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoVehiculo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoVehiculo.serialVersionUID),
					"#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo}", locFiltroTipoVehiculo);

			FiltroMarca locFiltroMarca = new FiltroMarca();
			locFiltroMarca.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaMarca = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Marca.serialVersionUID), "#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca}",
					locFiltroMarca);

			FiltroModelo locFiltroModelo = new FiltroModelo();
			locFiltroModelo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaModelo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Modelo.serialVersionUID), "#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo}",
					locFiltroModelo);

			FiltroLocalComercial locFiltroLocalComercial = new FiltroLocalComercial();
			locFiltroLocalComercial.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLocalComercial = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LocalComercial.serialVersionUID),
					"#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial}", locFiltroLocalComercial);

			FiltroObligacionTGI locFiltroDocumentoTGI = new FiltroObligacionTGI();
			locFiltroDocumentoTGI.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocumentoTGI = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoTGI.serialVersionUID),
					"#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI}", locFiltroDocumentoTGI);

			FiltroObligacionAutomotor locFiltroDocumentoAutomotor = new FiltroObligacionAutomotor();
			locFiltroDocumentoAutomotor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocEspAutomotor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoAutomotor.serialVersionUID),
					"#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor}", locFiltroDocumentoAutomotor);

			FiltroParcelaCementerio locFiltroParcelaCementerio = new FiltroParcelaCementerio();
			locFiltroParcelaCementerio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaParcelaCementerio = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ParcelaCementerio.serialVersionUID),
					"#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio}", locFiltroParcelaCementerio);
			FiltroTipoSepultura locFiltroTipoSepultura = new FiltroTipoSepultura();
			locFiltroTipoSepultura.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoSepultura = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoSepultura.serialVersionUID),
					"#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura}", locFiltroTipoSepultura);

			FiltroObligacionCementerio locFiltroDocumentoCementerio = new FiltroObligacionCementerio();
			locFiltroDocumentoCementerio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocEspCementerio = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoCementerio.serialVersionUID),
					"#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio}", locFiltroDocumentoCementerio);

			FiltroServicioOSP locFiltroServioOSP = new FiltroServicioOSP();
			locFiltroServioOSP.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaServiciosOSP = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ServicioOSP.serialVersionUID),
					"#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP}", locFiltroServioOSP);

			FiltroInspector locFiltroInspector = new FiltroInspector();
			locFiltroInspector.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaInspector = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Inspector.serialVersionUID),
					"#{habilitaciones$grpSHPS$ABMInspector$AdminInspector}", locFiltroInspector);

			FiltroTipoParametroConstante locFiltroParametroConst = new FiltroTipoParametroConstante();
			locFiltroParametroConst.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoParametroConstante = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoParametroConstante.serialVersionUID),
					"#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante}", locFiltroParametroConst);

			FiltroPlantillaDocumentoTasaMenor locFiltroPlantillaObligacionTasaMenor = new FiltroPlantillaDocumentoTasaMenor();
			locFiltroPlantillaObligacionTasaMenor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPlantillaObligacionTasaMenor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PlantillaDocumentoTasaMenor.serialVersionUID),
					"#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor}", locFiltroPlantillaObligacionTasaMenor);

			FiltroTipoParametroGrupoZona locTipoParametroGrupoZona = new FiltroTipoParametroGrupoZona();
			locTipoParametroGrupoZona.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoParametroGrupoZona = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoParametroGrupoZona.serialVersionUID),
					"#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$AdminTipoParametroGrupoZona}", locTipoParametroGrupoZona);

			FiltroTipoParametroGrilla locTipoParametroGrilla = new FiltroTipoParametroGrilla();
			locTipoParametroGrilla.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoParametroGrilla = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoParametroGrilla.serialVersionUID),
					"#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$AdminTipoParametroGrilla}", locTipoParametroGrilla);
			
			FiltroObligacionArrendamiento locFiltroArrendamiento = new FiltroObligacionArrendamiento();
			locFiltroArrendamiento.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocumentoArrendamiento = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoArrendamiento.serialVersionUID),
					"#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento}", locFiltroArrendamiento);

		} catch(Exception ex) {
			// Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}
	}
	
	private PaginatedTable tablaDocumentoArrendamiento;
	
	public PaginatedTable getTablaDocumentoArrendamiento() {
		return tablaDocumentoArrendamiento;
	}

	public void setTablaDocumentoArrendamiento(
			PaginatedTable tablaDocumentoArrendamiento) {
		this.tablaDocumentoArrendamiento = tablaDocumentoArrendamiento;
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Se llama a este m�todo al agregar este bean al �mbito de la sesi�n. Normalmente, esto ocurre como resultado de la evaluaci�n de una expresi�n de enlace
	 * de valores o de m�todos, que utiliza la funci�n de bean administrado para crear una instancia de este bean y almacenarla en el �mbito de la sesi�n.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para inicializar y almacenar en cach� los valores o recursos necesarios para el ciclo de duraci�n de una sesi�n de usuario
	 * en particular.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// <editor-fold defaultstate="collapsed"
		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("CommunicationHabilitacionesBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		tablePhaseListenerPermisosSinFirmarPorObligacion.keepSelected(true);
		tablePhaseListenerParametroConstante.keepSelected(true);
		tablePhaseListenerParametroDinamico.keepSelected(true);
		tablePhaseListenerParametroSHPS.keepSelected(true);
		tablePhaseListenerParametroGrupoZona.keepSelected(true);
		tablePhaseListenerParametroOSP.keepSelected(true);
		tablePhaseListenerParametroObra.keepSelected(true);
		tablePhaseListenerParametroPFO.keepSelected(true);
		tablePhaseListenerParametroParcelario.keepSelected(true);
		tablePhaseListenerParametroPersona.keepSelected(true);
		tablePhaseListenerParametroTGI.keepSelected(true);
		tablePhaseListenerParametroVencimiento.keepSelected(true);
		tablePhaseListenerMarca.keepSelected(true);
	}

	/**
	 * <p>
	 * Se llama a este m�todo cuando la sesi�n que lo contiene est� apunto de configurarse en modo pasivo. Normalmente, esto ocurre en un contenedor de servlet
	 * distribuido cuando la sesi�n est� apunto de transferirse a otra instancia de contenedor, despu�s de la cual se llamar� al m�todo <code>activate()</code>
	 * para indicar que la transferencia se ha completado.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para liberar las referencias a datos o recursos de sesi�n que no pueden serializarse con la propia sesi�n.
	 * </p>
	 */
	@Override
	public void passivate() {
	}

	/**
	 * <p>
	 * Se llama a este m�todo cuando la sesi�n que lo contiene se reactiva.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para volver a adquirir las referencias a datos o recursos de la sesi�n que no pudieron serializarse con la propia sesi�n.
	 * </p>
	 */
	@Override
	public void activate() {
	}

	/**
	 * <p>
	 * Se llama a este m�todo al eliminar este bean del �mbito de la sesi�n. Normalmente, esto ocurre cuando se supera el tiempo de espera de la sesi�n o la
	 * aplicaci�n la finaliza.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para limpiar los recursos asignados durante la ejecuci�n del m�todo <code>init()</code> o m�s adelante durante el ciclo de
	 * vida de la aplicaci�n.
	 * </p>
	 */
	@Override
	public void destroy() {
	}
	
	private SystemArrendamiento remoteSystemArrendamientos= null;

	public SystemArrendamiento getRemoteSystemArrendamientos() {
		this.remoteSystemArrendamientos.setLlave(getSessionBean1().getLlave());
		return this.remoteSystemArrendamientos;
	}

	private SystemPlantillaObligaciones remoteSystemPlantillaObligaciones = null;

	public SystemPlantillaObligaciones getRemoteSystemPlantillaObligaciones() {
		try {
			/*
			 * try{ if (this.remoteSystemPlantillaObligaciones==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemPlantillaObligacionesHome.JNDI_NAME); SystemPlantillaObligacionesHome locSystemPlantillaObligacionesHome =
			 * (SystemPlantillaObligacionesHome) PortableRemoteObject.narrow(obj, SystemPlantillaObligacionesHome.class); this.remoteSystemPlantillaObligaciones
			 * = locSystemPlantillaObligacionesHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemPlantillaObligaciones.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemPlantillaObligaciones;
	}

	private SystemDocumentoAutomotor remoteSystemDocumentoAutomotor = null;

	public SystemDocumentoAutomotor getRemoteSystemDocumentoAutomotor() {
		try {
			this.remoteSystemDocumentoAutomotor.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemDocumentoAutomotor;
	}

	public void setRemoteSystemDocumentoAutomotor(SystemDocumentoAutomotor pRemoteSystemDocumentoAutomotor) {
		try {
			this.remoteSystemDocumentoAutomotor.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemDocumentoAutomotor = pRemoteSystemDocumentoAutomotor;
	}

	public void setRemoteSystemPlantillaObligaciones(SystemPlantillaObligaciones pRemoteSystemPlantillaObligaciones) {
		try {
			this.remoteSystemPlantillaObligaciones.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemPlantillaObligaciones = pRemoteSystemPlantillaObligaciones;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemObligacion">
	/**
	 * Definicion de la interfaz remota SystemObligacion, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemObligacion remoteSystemObligacion = null;

	public SystemObligacion getRemoteSystemObligacion() {
		try {
			/*
			 * try{ if (this.remoteSystemObligacion==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemObligacionHome.JNDI_NAME);
			 * SystemObligacionHome locSystemObligacionHome = (SystemObligacionHome) PortableRemoteObject.narrow(obj, SystemObligacionHome.class);
			 * this.remoteSystemObligacion = locSystemObligacionHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemObligacion.setLlave(this.getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemObligacion;
	}

	public void setRemoteSystemObligacion(SystemObligacion pRemoteSystemObligacion) {
		try {
			this.remoteSystemObligacion.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemObligacion = pRemoteSystemObligacion;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemBromatologia">
	/**
	 * Definicion de la interfaz remota SystemBromatologia, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemBromatologia remoteSystemBromatologia = null;

	public SystemBromatologia getRemoteSystemBromatologia() {
		try {
			/*
			 * try{ if (this.remoteSystemBromatologia==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemBromatologiaHome.JNDI_NAME); SystemBromatologiaHome locSystemBromatologiaHome = (SystemBromatologiaHome)
			 * PortableRemoteObject.narrow(obj, SystemBromatologiaHome.class); this.remoteSystemBromatologia = locSystemBromatologiaHome.create(); }
			 * }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemBromatologia.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemBromatologia;
	}

	public void setRemoteSystemBromatologia(SystemBromatologia pRemoteSystemBromatologia) {
		try {
			this.remoteSystemBromatologia.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemBromatologia = pRemoteSystemBromatologia;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemAlicuota">
	/**
	 * Definicion de la interfaz remota SystemAlicuota, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemAlicuota remoteSystemAlicuota = null;

	public SystemAlicuota getRemoteSystemAlicuota() {
		try {
			this.remoteSystemAlicuota.setLlave(this.getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemAlicuota;
	}

	public void setRemoteSystemAlicuota(SystemAlicuota pRemoteSystemAlicuota) {
		try {
			this.remoteSystemAlicuota.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemAlicuota = pRemoteSystemAlicuota;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemInspectores">
	/**
	 * Definicion de la interfaz remota SystemInspectores, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemInspectores remoteSystemInspectores = null;

	public SystemInspectores getRemoteSystemInspectores() {
		try {
			/*
			 * try{ if (this.remoteSystemInspectores==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemInspectoresHome.JNDI_NAME);
			 * SystemInspectoresHome locSystemInspectoresHome = (SystemInspectoresHome) PortableRemoteObject.narrow(obj, SystemInspectoresHome.class);
			 * this.remoteSystemInspectores = locSystemInspectoresHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemInspectores.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemInspectores;
	}

	public void setRemoteSystemInspectores(SystemInspectores pRemoteSystemInspectores) {
		try {
			this.remoteSystemInspectores.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemInspectores = pRemoteSystemInspectores;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemPlanFinanciacionObra">
	/**
	 * Definicion de la interfaz remota SystemPlanFinanciacionObra, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemPlanFinanciacionObra remoteSystemPlanFinanciacionObra = null;

	public SystemPlanFinanciacionObra getRemoteSystemPlanFinanciacionObra() {
		try {
			/*
			 * try{ if (this.remoteSystemPlanFinanciacionObra==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemPlanFinanciacionObraHome.JNDI_NAME); SystemPlanFinanciacionObraHome locSystemPlanFinanciacionObraHome =
			 * (SystemPlanFinanciacionObraHome) PortableRemoteObject.narrow(obj, SystemPlanFinanciacionObraHome.class); this.remoteSystemPlanFinanciacionObra =
			 * locSystemPlanFinanciacionObraHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemPlanFinanciacionObra.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemPlanFinanciacionObra;
	}

	public void setRemoteSystemPlanFinanciacionObra(SystemPlanFinanciacionObra pRemoteSystemPlanFinanciacionObra) {
		try {
			this.remoteSystemPlanFinanciacionObra.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemPlanFinanciacionObra = pRemoteSystemPlanFinanciacionObra;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemDocumentoTGI">
	/**
	 * Definicion de la interfaz remota SystemDocumentoTGI, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemDocumentoTGI remoteSystemDocumentoTGI = null;

	public SystemDocumentoTGI getRemoteSystemDocumentoTGI() {
		try {
			/*
			 * try{ if (this.remoteSystemDocumentoTGI==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemDocumentoTGIHome.JNDI_NAME); SystemDocumentoTGIHome locSystemDocumentoTGIHome = (SystemDocumentoTGIHome)
			 * PortableRemoteObject.narrow(obj, SystemDocumentoTGIHome.class); this.remoteSystemDocumentoTGI = locSystemDocumentoTGIHome.create(); }
			 * }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemDocumentoTGI.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemDocumentoTGI;
	}

	public void setRemoteSystemDocumentoTGI(SystemDocumentoTGI pRemoteSystemDocumentoTGI) {
		try {
			this.remoteSystemDocumentoTGI.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemDocumentoTGI = pRemoteSystemDocumentoTGI;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemDocumentoOSP">
	/**
	 * Definicion de la interfaz remota SystemDocumentoOSP, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemDocumentoOSP remoteSystemDocumentoOSP = null;

	public SystemDocumentoOSP getRemoteSystemDocumentoOSP() {
		try {
			this.remoteSystemDocumentoOSP.setLlave(this.getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return remoteSystemDocumentoOSP;
	}

	private muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	public void setRemoteSystemDocumentoOSP(SystemDocumentoOSP pRemoteSystemDocumentoOSP) {
		try {
			this.remoteSystemDocumentoOSP.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemDocumentoOSP = pRemoteSystemDocumentoOSP;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemTipoTasa">
	/**
	 * Definicion de la interfaz remota SystemTipoTasa, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemTipoTasa remoteSystemTipoTasa = null;

	public SystemTipoTasa getRemoteSystemTipoTasa() {
		try {
			/*
			 * try{ if (this.remoteSystemTipoTasa==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemTipoTasaHome.JNDI_NAME);
			 * SystemTipoTasaHome locSystemTipoTasaHome = (SystemTipoTasaHome) PortableRemoteObject.narrow(obj, SystemTipoTasaHome.class);
			 * this.remoteSystemTipoTasa = locSystemTipoTasaHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemTipoTasa.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemTipoTasa;
	}

	public void setRemoteSystemTipoTasa(SystemTipoTasa pRemoteSystemTipoTasa) {
		try {
			this.remoteSystemTipoTasa.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemTipoTasa = pRemoteSystemTipoTasa;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemDocumentoTasaMenor">
	/**
	 * Definicion de la interfaz remota SystemDocumentoTasaMenor, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemDocumentoTasaMenor remoteSystemDocumentoTasaMenor = null;

	public SystemDocumentoTasaMenor getRemoteSystemDocumentoTasaMenor() {
		/*
		 * try{ if (this.remoteSystemTipoTasa==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemTipoTasaHome.JNDI_NAME);
		 * SystemTipoTasaHome locSystemTipoTasaHome = (SystemTipoTasaHome) PortableRemoteObject.narrow(obj, SystemTipoTasaHome.class); this.remoteSystemTipoTasa
		 * = locSystemTipoTasaHome.create(); } }catch(Exception e){ e.printStackTrace(); }
		 */
		this.remoteSystemDocumentoTasaMenor.setLlave(getSessionBean1().getLlave());
		return this.remoteSystemDocumentoTasaMenor;
	}

	public void setRemoteSystemDocumentoTasaMenor(SystemDocumentoTasaMenor pRemoteSystemDocumentoTasaMenor) {
		this.remoteSystemDocumentoTasaMenor.setLlave(getSessionBean1().getLlave());
		this.remoteSystemDocumentoTasaMenor = pRemoteSystemDocumentoTasaMenor;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="SystemReportesHabilitaciones">
	/**
	 * Definicion de la interfaz remota SystemReportesHabilitaciones, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemReportesHabilitaciones remoteSystemReportesHabilitaciones = null;

	public SystemReportesHabilitaciones getRemoteSystemReportesHabilitaciones() {
		try {
			/*
			 * try{ if (this.remoteSystemTipoTasa==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemTipoTasaHome.JNDI_NAME);
			 * SystemTipoTasaHome locSystemTipoTasaHome = (SystemTipoTasaHome) PortableRemoteObject.narrow(obj, SystemTipoTasaHome.class);
			 * this.remoteSystemTipoTasa = locSystemTipoTasaHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemReportesHabilitaciones.setLlave(getSessionBean1().getLlave());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemReportesHabilitaciones;
	}

	public void setRemoteSystemReportesHabilitaciones(SystemReportesHabilitaciones pRemoteSystemReportesHabilitaciones) {
		try {
			this.remoteSystemReportesHabilitaciones.setLlave(getSessionBean1().getLlave());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		this.remoteSystemReportesHabilitaciones = pRemoteSystemReportesHabilitaciones;
	}

	// </editor-fold>
	// @EJB
	private SystemExencionObligacion remoteSystemExencion = null;

	public SystemExencionObligacion getRemoteSystemExencion() {
		try {
			/*
			 * try{ if (this.remoteSystemExencion==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemExencionObligacionHome.JNDI_NAME); SystemExencionObligacionHome locSystemExencionHome = (SystemExencionObligacionHome)
			 * PortableRemoteObject.narrow(obj, SystemExencionObligacionHome.class); this.remoteSystemExencion = locSystemExencionHome.create(); }
			 * }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemExencion.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemExencion;
	}

	public void setRemoteSystemExencion(SystemExencionObligacion pRemoteSystemExencion) {
		try {
			this.remoteSystemExencion.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemExencion = pRemoteSystemExencion;
	}

	private SystemDocumentoCementerio remoteSystemDocumentoCementerio = null;

	public SystemDocumentoCementerio getRemoteSystemDocumentoCementerio() {
		try {
			this.remoteSystemDocumentoCementerio.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemDocumentoCementerio;
	}

	public void setRemoteSystemDocumentoCementerio(SystemDocumentoCementerio pRemoteSystemDocumentoCementerio) {
		try {
			this.remoteSystemDocumentoCementerio.setLlave(getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		this.remoteSystemDocumentoCementerio = pRemoteSystemDocumentoCementerio;
	}

	//
	// ArrayLists
	//
	/**
	 * Conserva el valor de la propiedad listaPropietarios.
	 */
	private List listaPropietarios;

	/**
	 * Getter para propiedad listaPropietarios.
	 * 
	 * @return Valor de la propiedad listaPropietarios.
	 */
	public List getListaPropietarios() {
		return listaPropietarios;
	}

	/**
	 * Setter para propiedad listaPropietarios.
	 * 
	 */
	public void setListaPropietarios(List listaPropietarios) {
		this.listaPropietarios = listaPropietarios;
	}

	private List listaPropietariosTasaMenor;

	public List getListaPropietariosTasaMenor() {
		return listaPropietariosTasaMenor;
	}

	public void setListaPropietariosTasaMenor(List listaPropietariosTasaMenor) {
		this.listaPropietariosTasaMenor = listaPropietariosTasaMenor;
	}

	private TableSelectPhaseListener tablePhaseListenerObligacionesOSP = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerObligacionesOSP.
	 * 
	 * @return Valor de la propiedad tablePhaseListenerObligacionesOSP.
	 */
	public TableSelectPhaseListener getTablePhaseListenerObligacionesOSP() {

		return this.tablePhaseListenerObligacionesOSP;
	}

	/**
	 * Setter para propiedad tablePhaseListenerObligacionesOSP.
	 * 
	 * @param tablePhaseListenerObligacionesOSP
	 *            Nuevo valor de la propiedad tablePhaseListenerObligacionesOSP.
	 */
	public void setTablePhaseListenerObligacionesOSP(TableSelectPhaseListener tablePhaseListenerObligacionesOSP) {

		this.tablePhaseListenerObligacionesOSP = tablePhaseListenerObligacionesOSP;
	}

	//
	// ArrayLists
	//
	/**
	 * Conserva el valor de la propiedad listaObligacionesOSP.
	 */
	private ArrayList listaObligacionesOSP;

	public ArrayList getListaObligacionesOSP() {
		return listaObligacionesOSP;
	}

	public void setListaObligacionesOSP(ArrayList listaObligacionesOSP) {
		this.listaObligacionesOSP = listaObligacionesOSP;
	}

	//
	// ArrayLists
	//
	/**
	 * Conserva el valor de la propiedad listaObligacionesOSP.
	 */
	private ArrayList listaObligacionesOSPExistentes;

	public ArrayList getListaObligacionesOSPExistentes() {
		return listaObligacionesOSPExistentes;
	}

	public void setListaObligacionesOSPExistentes(ArrayList listaObligacionesOSPExistentes) {
		this.listaObligacionesOSPExistentes = listaObligacionesOSPExistentes;
	}

	//
	// ArrayLists
	//
	/**
	 * Conserva el valor de la propiedad listaPropietarios.
	 */
	private List listaPropietariosParcela;

	/**
	 * Getter para propiedad listaPropietarios.
	 * 
	 * @return Valor de la propiedad listaPropietarios.
	 */
	public List getListaPropietariosParcela() {
		return listaPropietariosParcela;
	}

	/**
	 * Setter para propiedad listaPropietarios.
	 * 
	 */
	public void setListaPropietariosParcela(List listaPropietariosParcela) {
		this.listaPropietariosParcela = listaPropietariosParcela;
	}

	/**
	 * Conserva el valor de la propiedad listaAtributosDinamicosTasaMenor.
	 */
	private ArrayList listaAtributosDinamicosTasaMenor;

	public ArrayList getListaAtributosDinamicosTasaMenor() {
		return listaAtributosDinamicosTasaMenor;
	}

	public void setListaAtributosDinamicosTasaMenor(ArrayList listaAtributosDinamicosTasaMenor) {
		this.listaAtributosDinamicosTasaMenor = listaAtributosDinamicosTasaMenor;
	}

	/**
	 * Conserva el valor de la propiedad listaAtributosDinamicosSHPS.
	 */
	private ArrayList listaAtributosDinamicosSHPS;

	public ArrayList getListaAtributosDinamicosSHPS() {
		return listaAtributosDinamicosSHPS;
	}

	public void setListaAtributosDinamicosSHPS(ArrayList listaAtributosDinamicosSHPS) {
		this.listaAtributosDinamicosSHPS = listaAtributosDinamicosSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaMarcas
	 */
	private List listaMarcas;

	public List getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	private List listaModelos;

	public List getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List listaModelos) {
		this.listaModelos = listaModelos;
	}

	/**
	 * Conserva el valor de la propiedad listaAtributosDinamicosSHPS.
	 */
	private ArrayList listaAtributosDinamicosOSP;

	public ArrayList getListaAtributosDinamicosOSP() {
		return listaAtributosDinamicosOSP;
	}

	public void setListaAtributosDinamicosOSP(ArrayList listaAtributosDinamicosOSP) {
		this.listaAtributosDinamicosOSP = listaAtributosDinamicosOSP;
	}
	
	private List listaAtributosDinamicosArrendamiento;
	
	public List getListaAtributosDinamicosArrendamiento() {
		return listaAtributosDinamicosArrendamiento;
	}

	public void setListaAtributosDinamicosArrendamiento(
			List listaAtributosDinamicosArrendamiento) {
		this.listaAtributosDinamicosArrendamiento = listaAtributosDinamicosArrendamiento;
	}

	/**
	 * Conserva el valor de la propiedad listaAtributosDinamicosSHPS.
	 */
	private ArrayList listaAtributosDinamicosTGI;

	public ArrayList getListaAtributosDinamicosTGI() {
		return listaAtributosDinamicosTGI;
	}

	public void setListaAtributosDinamicosTGI(ArrayList listaAtributosDinamicosTGI) {
		this.listaAtributosDinamicosTGI = listaAtributosDinamicosTGI;
	}

	/**
	 * Conserva el valor de la propiedad listaAtributosDinamicosSHPS.
	 */
	private ArrayList listaAtributosDinamicosPFO;

	public ArrayList getListaAtributosDinamicosPFO() {
		return listaAtributosDinamicosPFO;
	}

	public void setListaAtributosDinamicosPFO(ArrayList listaAtributosDinamicosPFO) {
		this.listaAtributosDinamicosPFO = listaAtributosDinamicosPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaPlantillasObligacion.
	 */
	private ArrayList listaPlantillasObligacion;

	/**
	 * Getter para propiedad listaPlantillasObligacion.
	 * 
	 * @return Valor de la propiedad listaPlantillasObligacion.
	 */
	public ArrayList getListaPlantillasObligacion() {
		return listaPlantillasObligacion;
	}

	/**
	 * Setter para propiedad listaPlantillasObligacion.
	 * 
	 * @param listaVehiculos
	 *            Nuevo valor de la propiedad listaPlantillasObligacion.
	 */
	public void setListaPlantillasObligacion(ArrayList listaPlantillasObligacion) {
		this.listaPlantillasObligacion = listaPlantillasObligacion;
	}

	/**
	 * Conserva el valor de la propiedad listaVehiculos.
	 */
	private List listaVehiculos;

	/**
	 * Getter para propiedad listaVehiculos.
	 * 
	 * @return Valor de la propiedad listaVehiculos.
	 */
	public List getListaVehiculos() {

		return this.listaVehiculos;
	}

	/**
	 * Setter para propiedad listaVehiculos.
	 * 
	 * @param listaVehiculos
	 *            Nuevo valor de la propiedad listaVehiculos.
	 */
	public void setListaVehiculos(List listaVehiculos) {

		this.listaVehiculos = listaVehiculos;
	}

	/**
	 * Conserva el valor de la propiedad listaLocalesComerciales.
	 */
	private ArrayList listaLocalesComerciales;

	/**
	 * Getter para propiedad listaLocalesComerciales.
	 * 
	 * @return Valor de la propiedad listaLocalesComerciales.
	 */
	public ArrayList getListaLocalesComerciales() {

		return this.listaLocalesComerciales;
	}

	/**
	 * Setter para propiedad listaLocalesComerciales.
	 * 
	 * @param listaLocalesComerciales
	 *            Nuevo valor de la propiedad listaLocalesComerciales.
	 */
	public void setListaLocalesComerciales(ArrayList listaLocalesComerciales) {

		this.listaLocalesComerciales = listaLocalesComerciales;
	}

	/**
	 * Conserva el valor de la propiedad listaLibretasSanitarias.
	 */
	private ArrayList listaLibretasSanitarias;
	private ArrayList listaLibrerasSanitariasObligaciones;

	/**
	 * Getter para propiedad listaLibretasSanitarias.
	 * 
	 * @return Valor de la propiedad listaLibretasSanitarias.
	 */
	public ArrayList getListaLibretasSanitarias() {

		return this.listaLibretasSanitarias;
	}

	/**
	 * Setter para propiedad listaLibretasSanitarias.
	 * 
	 * @param listaLibretasSanitarias
	 *            Nuevo valor de la propiedad listaLibretasSanitarias.
	 */
	public void setListaLibretasSanitarias(ArrayList listaLibretasSanitarias) {

		this.listaLibretasSanitarias = listaLibretasSanitarias;
	}

	public ArrayList getListaLibrerasSanitariasObligaciones() {
		return listaLibrerasSanitariasObligaciones;
	}

	public void setListaLibrerasSanitariasObligaciones(ArrayList listaLibrerasSanitariasObligaciones) {
		this.listaLibrerasSanitariasObligaciones = listaLibrerasSanitariasObligaciones;
	}

	/**
	 * Conserva el valor de la propiedad listaRenovacionesLibretaSanitaria.
	 */
	private ArrayList listaRenovacionesLibretaSanitaria;

	/**
	 * Getter para propiedad listaRenovacionesLibretaSanitaria.
	 * 
	 * @return Valor de la propiedad listaRenovacionesLibretaSanitaria.
	 */
	public ArrayList getListaRenovacionesLibretaSanitaria() {

		return this.listaRenovacionesLibretaSanitaria;
	}

	/**
	 * Setter para propiedad listaRenovacionesLibretaSanitaria.
	 * 
	 * @param listaRenovacionesLibretaSanitaria
	 *            Nuevo valor de la propiedad listaRenovacionesLibretaSanitaria.
	 */
	public void setListaRenovacionesLibretaSanitaria(ArrayList listaRenovacionesLibretaSanitaria) {

		this.listaRenovacionesLibretaSanitaria = listaRenovacionesLibretaSanitaria;
	}

	/**
	 * Conserva el valor de la propiedad listaConstanciasVacunacionesLibretaSanitaria.
	 */
	private ArrayList listaConstanciasVacunacionesLibretaSanitaria;

	/**
	 * Getter para propiedad listaConstanciasVacunacionesLibretaSanitaria.
	 * 
	 * @return Valor de la propiedad listaConstanciasVacunacionesLibretaSanitaria.
	 */
	public ArrayList getListaConstanciasVacunacionesLibretaSanitaria() {

		return this.listaConstanciasVacunacionesLibretaSanitaria;
	}

	/**
	 * Setter para propiedad listaConstanciasVacunacionesLibretaSanitaria.
	 * 
	 * @param listaConstanciasVacunacionesLibretaSanitaria
	 *            Nuevo valor de la propiedad listaConstanciasVacunacionesLibretaSanitaria.
	 */
	public void setListaConstanciasVacunacionesLibretaSanitaria(ArrayList listaConstanciasVacunacionesLibretaSanitaria) {

		this.listaConstanciasVacunacionesLibretaSanitaria = listaConstanciasVacunacionesLibretaSanitaria;
	}

	/**
	 * Conserva el valor de la propiedad listaInhabilitacionesTemporariasLibretaSanitaria.
	 */
	private ArrayList listaInhabilitacionesTemporariasLibretaSanitaria;

	/**
	 * Getter para propiedad listaInhabilitacionesTemporariasLibretaSanitaria.
	 * 
	 * @return Valor de la propiedad listaInhabilitacionesTemporariasLibretaSanitaria.
	 */
	public ArrayList getListaInhabilitacionesTemporariasLibretaSanitaria() {

		return this.listaInhabilitacionesTemporariasLibretaSanitaria;
	}

	/**
	 * Setter para propiedad listaInhabilitacionesTemporariasLibretaSanitaria.
	 * 
	 * @param listaInhabilitacionesTemporariasLibretaSanitaria
	 *            Nuevo valor de la propiedad listaInhabilitacionesTemporariasLibretaSanitaria.
	 */
	public void setListaInhabilitacionesTemporariasLibretaSanitaria(ArrayList listaInhabilitacionesTemporariasLibretaSanitaria) {

		this.listaInhabilitacionesTemporariasLibretaSanitaria = listaInhabilitacionesTemporariasLibretaSanitaria;
	}

	/**
	 * Conserva el valor de la propiedad listaLocalesComercialesSHPS.
	 */
	private ArrayList listaLocalesComercialesSHPS;

	/**
	 * Getter para propiedad listaLocalesComercialesSHPS.
	 * 
	 * @return Valor de la propiedad listaLocalesComercialesSHPS.
	 */
	public ArrayList getListaLocalesComercialesSHPS() {

		return this.listaLocalesComercialesSHPS;
	}

	/**
	 * Setter para propiedad listaLocalesComercialesSHPS.
	 * 
	 * @param listaLocalesComercialesSHPS
	 *            Nuevo valor de la propiedad listaLocalesComercialesSHPS.
	 */
	public void setListaLocalesComercialesSHPS(ArrayList listaLocalesComercialesSHPS) {

		this.listaLocalesComercialesSHPS = listaLocalesComercialesSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaTransportesVehicularesSHPS.
	 */
	private ArrayList listaTransportesVehicularesSHPS;

	/**
	 * Getter para propiedad listaTransportesVehicularesSHPS.
	 * 
	 * @return Valor de la propiedad listaTransportesVehicularesSHPS.
	 */
	public ArrayList getListaTransportesVehicularesSHPS() {

		return this.listaTransportesVehicularesSHPS;
	}

	/**
	 * Setter para propiedad listaTransportesVehicularesSHPS.
	 * 
	 * @param listaTransportesVehicularesSHPS
	 *            Nuevo valor de la propiedad listaTransportesVehicularesSHPS.
	 */
	public void setListaTransportesVehicularesSHPS(ArrayList listaTransportesVehicularesSHPS) {

		this.listaTransportesVehicularesSHPS = listaTransportesVehicularesSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaRubrosSHPS.
	 */
	private ArrayList listaRubrosSHPS;

	/**
	 * Getter para propiedad listaRubrosSHPS.
	 * 
	 * @return Valor de la propiedad listaRubrosSHPS.
	 */
	public ArrayList getListaRubrosSHPS() {

		return this.listaRubrosSHPS;
	}

	/**
	 * Setter para propiedad listaRubrosSHPS.
	 * 
	 * @param listaRubrosSHPS
	 *            Nuevo valor de la propiedad listaRubrosSHPS.
	 */
	public void setListaRubrosSHPS(ArrayList listaRubrosSHPS) {

		this.listaRubrosSHPS = listaRubrosSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaRegAlicuotasSHPS.
	 */
	private ArrayList listaRegAlicuotasSHPS;

	/**
	 * Getter para propiedad listaRegAlicuotasSHPS.
	 * 
	 * @return Valor de la propiedad listaRegAlicuotasSHPS.
	 */
	public ArrayList getListaRegAlicuotasSHPS() {

		return this.listaRegAlicuotasSHPS;
	}

	/**
	 * Setter para propiedad listaRegAlicuotasSHPS.
	 * 
	 * @param listaRegAlicuotasSHPS
	 *            Nuevo valor de la propiedad listaRegAlicuotasSHPS.
	 */
	public void setListaRegAlicuotasSHPS(ArrayList listaRegAlicuotasSHPS) {

		this.listaRegAlicuotasSHPS = listaRegAlicuotasSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaDeclaracionesJuradasSHPS.
	 */
	private ArrayList listaDeclaracionesJuradasSHPS;

	/**
	 * Getter para propiedad listaDeclaracionesJuradasSHPS.
	 * 
	 * @return Valor de la propiedad listaDeclaracionesJuradasSHPS.
	 */
	public ArrayList getListaDeclaracionesJuradasSHPS() {

		return this.listaDeclaracionesJuradasSHPS;
	}

	/**
	 * Setter para propiedad listaDeclaracionesJuradasSHPS.
	 * 
	 * @param listaDeclaracionesJuradasSHPS
	 *            Nuevo valor de la propiedad listaDeclaracionesJuradasSHPS.
	 */
	public void setListaDeclaracionesJuradasSHPS(ArrayList listaDeclaracionesJuradasSHPS) {

		this.listaDeclaracionesJuradasSHPS = listaDeclaracionesJuradasSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaInspectores.
	 */
	private List listaInspectores;

	/**
	 * Getter para propiedad listaInspectores.
	 * 
	 * @return Valor de la propiedad listaInspectores.
	 */
	public List getListaInspectores() {

		return this.listaInspectores;
	}

	/**
	 * Setter para propiedad listaInspectores.
	 * 
	 * @param listaInspectores
	 *            Nuevo valor de la propiedad listaInspectores.
	 */
	public void setListaInspectores(List listaInspectores) {

		this.listaInspectores = listaInspectores;
	}

	/**
	 * Conserva el valor de la propiedad listaTransportesVehiculares.
	 */
	private ArrayList listaTransportesVehiculares;

	/**
	 * Getter para propiedad listaTransportesVehiculares.
	 * 
	 * @return Valor de la propiedad listaTransportesVehiculares.
	 */
	public ArrayList getListaTransportesVehiculares() {

		return this.listaTransportesVehiculares;
	}

	/**
	 * Setter para propiedad listaTransportesVehiculares.
	 * 
	 * @param listaTransportesVehiculares
	 *            Nuevo valor de la propiedad listaTransportesVehiculares.
	 */
	public void setListaTransportesVehiculares(ArrayList listaTransportesVehiculares) {

		this.listaTransportesVehiculares = listaTransportesVehiculares;
	}

	/**
	 * Conserva el valor de la propiedad listaInspeccionesTransporteVehicular.
	 */
	private ArrayList listaInspeccionesTransporteVehicular;

	/**
	 * Getter para propiedad listaInspeccionesTransporteVehicular.
	 * 
	 * @return Valor de la propiedad listaInspeccionesTransporteVehicular.
	 */
	public ArrayList getListaInspeccionesTransporteVehicular() {

		return this.listaInspeccionesTransporteVehicular;
	}

	/**
	 * Setter para propiedad listaInspeccionesTransporteVehicular.
	 * 
	 * @param listaInspeccionesTransporteVehicular
	 *            Nuevo valor de la propiedad listaInspeccionesTransporteVehicular.
	 */
	public void setListaInspeccionesTransporteVehicular(ArrayList listaInspeccionesTransporteVehicular) {

		this.listaInspeccionesTransporteVehicular = listaInspeccionesTransporteVehicular;
	}

	/**
	 * Conserva el valor de la propiedad listaInspeccionesLocalComercial.
	 */
	private ArrayList listaInspeccionesLocalComercial;

	/**
	 * Getter para propiedad listaInspeccionesLocalComercial.
	 * 
	 * @return Valor de la propiedad listaInspeccionesLocalComercial.
	 */
	public ArrayList getListaInspeccionesLocalComercial() {

		return this.listaInspeccionesLocalComercial;
	}

	/**
	 * Setter para propiedad listaInspeccionesLocalComercial.
	 * 
	 * @param listaInspeccionesLocalComercial
	 *            Nuevo valor de la propiedad listaInspeccionesLocalComercial.
	 */
	public void setListaInspeccionesLocalComercial(ArrayList listaInspeccionesLocalComercial) {

		this.listaInspeccionesLocalComercial = listaInspeccionesLocalComercial;
	}

	/**
	 * Conserva el valor de la propiedad listaLogModificacionesSHPS.
	 */
	private List listaLogModificacionesSHPS;

	/**
	 * Getter para propiedad listaLogModificacionesSHPS.
	 * 
	 * @return Valor de la propiedad listaLogModificacionesSHPS.
	 */
	public List getListaLogModificacionesSHPS() {

		return this.listaLogModificacionesSHPS;
	}

	/**
	 * Setter para propiedad listaLogModificacionesSHPS.
	 * 
	 * @param listaLogModificacionesSHPS
	 *            Nuevo valor de la propiedad listaLogModificacionesSHPS.
	 */
	public void setListaLogModificacionesSHPS(List listaLogModificacionesSHPS) {

		this.listaLogModificacionesSHPS = listaLogModificacionesSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaDocEspSHPS.
	 */
	private List listaDocEspTasaMenor;

	/**
	 * Getter para propiedad listaDocEspTasaMenor.
	 * 
	 * @return Valor de la propiedad listaDocEspTasaMenor.
	 */
	public List getListaDocEspTasaMenor() {

		return this.listaDocEspTasaMenor;
	}

	/**
	 * Setter para propiedad listaDocEspTasaMenor.
	 * 
	 * @param listaDocEspTasaMenor
	 *            Nuevo valor de la propiedad listaDocEspTasaMenor.
	 */
	public void setListaDocEspTasaMenor(List listaDocEspTasaMenor) {

		this.listaDocEspTasaMenor = listaDocEspTasaMenor;
	}

	/**
	 * Conserva el valor de la propiedad listaDocEspTasaMenor.
	 */
	private List listaDocEspSHPS;

	/**
	 * Getter para propiedad listaDocEspSHPS.
	 * 
	 * @return Valor de la propiedad listaDocEspSHPS.
	 */
	public List getListaDocEspSHPS() {

		return this.listaDocEspSHPS;
	}

	/**
	 * Setter para propiedad listaDocEspSHPS.
	 * 
	 * @param listaDocEspSHPS
	 *            Nuevo valor de la propiedad listaDocEspSHPS.
	 */
	public void setListaDocEspSHPS(List listaDocEspSHPS) {

		this.listaDocEspSHPS = listaDocEspSHPS;
	}

	private List listaObligacionesSHPSImprimirDDJJ;
	
	public List getListaObligacionesSHPSImprimirDDJJ() {
		return listaObligacionesSHPSImprimirDDJJ;
	}

	public void setListaObligacionesSHPSImprimirDDJJ(
			List listaObligacionesSHPSImprimirDDJJ) {
		this.listaObligacionesSHPSImprimirDDJJ = listaObligacionesSHPSImprimirDDJJ;
	}

	/**
	 * Conserva el valor de la propiedad listaDocEspPFO.
	 */
	private ArrayList listaDocEspPFO;

	/**
	 * Getter para propiedad listaDocEspPFO.
	 * 
	 * @return Valor de la propiedad listaDocEspPFO.
	 */
	public ArrayList getListaDocEspPFO() {

		return this.listaDocEspPFO;
	}

	/**
	 * Setter para propiedad listaDocEspPFO.
	 * 
	 * @param listaDocEspPFO
	 *            Nuevo valor de la propiedad listaDocEspPFO.
	 */
	public void setListaDocEspPFO(ArrayList listaDocEspPFO) {

		this.listaDocEspPFO = listaDocEspPFO;
	}
	
	private List listaDocEspArrendamiento;
	
	public List getListaDocEspArrendamiento() {
		return listaDocEspArrendamiento;
	}

	public void setListaDocEspArrendamiento(List listaDocEspArrendamiento) {
		this.listaDocEspArrendamiento = listaDocEspArrendamiento;
	}

	private ArrayList listaDocEspTGI;

	public ArrayList getListaDocEspTGI() {
		return this.listaDocEspTGI;
	}

	public void setListaDocEspTGI(ArrayList listaDocEspTGI) {
		this.listaDocEspTGI = listaDocEspTGI;
	}

	/**
	 * Conserva el valor de la propiedad listaDocEspOSP.
	 */
	private List listaDocEspOSP;

	/**
	 * Getter para propiedad listaDocEspOSP.
	 * 
	 * @return Valor de la propiedad listaDocEspOSP.
	 */
	public List getListaDocEspOSP() {

		return this.listaDocEspOSP;
	}

	/**
	 * Setter para propiedad listaDocEspOSP.
	 * 
	 * @param listaDocEspOSP
	 *            Nuevo valor de la propiedad listaDocEspOSP.
	 */
	public void setListaDocEspOSP(List listaDocEspOSP) {

		this.listaDocEspOSP = listaDocEspOSP;
	}

	/**
	 * Conserva el valor de la propiedad listaPlanesCuentaObra.
	 */
	private ArrayList listaPlanesCuentaObra;

	/**
	 * Getter para propiedad listaPlanesCuentaObra.
	 * 
	 * @return Valor de la propiedad listaPlanesCuentaObra.
	 */
	public ArrayList getListaPlanesCuentaObra() {

		return this.listaPlanesCuentaObra;
	}

	/**
	 * Setter para propiedad listaPlanesCuentaObra.
	 * 
	 * @param listaPlanesCuentaObra
	 *            Nuevo valor de la propiedad listaPlanesCuentaObra.
	 */
	public void setListaPlanesCuentaObra(ArrayList listaPlanesCuentaObra) {

		this.listaPlanesCuentaObra = listaPlanesCuentaObra;
	}

	/**
	 * Conserva el valor de la propiedad listaObras.
	 */
	private ArrayList listaObras;

	/**
	 * Getter para propiedad listaObras.
	 * 
	 * @return Valor de la propiedad listaObras.
	 */
	public ArrayList getListaObras() {

		return this.listaObras;
	}

	/**
	 * Setter para propiedad listaObras.
	 * 
	 * @param listaObras
	 *            Nuevo valor de la propiedad listaObras.
	 */
	public void setListaObras(ArrayList listaObras) {

		this.listaObras = listaObras;
	}

	/**
	 * Conserva el valor de la propiedad listaCuadras.
	 */
	private ArrayList listaCuadras;

	/**
	 * Getter para propiedad listaCuadras.
	 * 
	 * @return Valor de la propiedad listaCuadras.
	 */
	public ArrayList getListaCuadras() {

		return this.listaCuadras;
	}

	/**
	 * Setter para propiedad listaCuadras.
	 * 
	 * @param listaCuadras
	 *            Nuevo valor de la propiedad listaCuadras.
	 */
	public void setListaCuadras(ArrayList listaCuadras) {

		this.listaCuadras = listaCuadras;
	}

	/**
	 * Conserva el valor de la propiedad listaPlanesCuentaPorObra.
	 */
	private ArrayList listaPlanesCuentaPorObra;

	/**
	 * Getter para propiedad listaPlanesCuentaPorObra.
	 * 
	 * @return Valor de la propiedad listaPlanesCuentaPorObra.
	 */
	public ArrayList getListaPlanesCuentaPorObra() {

		return this.listaPlanesCuentaPorObra;
	}

	/**
	 * Setter para propiedad listaPlanesCuentaPorObra.
	 * 
	 * @param listaPlanesCuentaPorObra
	 *            Nuevo valor de la propiedad listaPlanesCuentaPorObra.
	 */
	public void setListaPlanesCuentaPorObra(ArrayList listaPlanesCuentaPorObra) {

		this.listaPlanesCuentaPorObra = listaPlanesCuentaPorObra;
	}

	/**
	 * Conserva el valor de la propiedad listaServiciosOSP.
	 */
	private List listaServiciosOSP;

	/**
	 * Getter para propiedad listaServiciosOSP.
	 * 
	 * @return Valor de la propiedad listaServiciosOSP.
	 */
	public List getListaServiciosOSP() {

		return this.listaServiciosOSP;
	}

	/**
	 * Setter para propiedad listaServiciosOSP.
	 * 
	 * @param listaServiciosOSP
	 *            Nuevo valor de la propiedad listaServiciosOSP.
	 */
	public void setListaServiciosOSP(List listaServiciosOSP) {

		this.listaServiciosOSP = listaServiciosOSP;
	}

	/**
	 * Conserva el valor de la propiedad listaFirmasPendientes.
	 */
	private ArrayList listaPermisosSinFirmar;

	/**
	 * Getter para propiedad listaPermisosSinFirmar.
	 * 
	 * @return Valor de la propiedad listaPermisosSinFirmar.
	 */
	public ArrayList getListaPermisosSinFirmar() {

		return this.listaPermisosSinFirmar;
	}

	/**
	 * Setter para propiedad listaPermisosSinFirmar.
	 * 
	 * @param listaPermisosSinFirmar
	 *            Nuevo valor de la propiedad listaPermisosSinFirmar.
	 */
	public void setListaPermisosSinFirmar(ArrayList listaPermisosSinFirmar) {

		this.listaPermisosSinFirmar = listaPermisosSinFirmar;
	}

	/**
	 * Conserva el valor de la propiedad listaPermisosSinFirmarOSP.
	 */
	private ArrayList listaPermisosSinFirmarPorObligacion;

	/**
	 * Getter para propiedad listaPermisosSinFirmarPorObligacion.
	 * 
	 * @return Valor de la propiedad listaPermisosSinFirmarPorObligacion.
	 */
	public ArrayList getListaPermisosSinFirmarPorObligacion() {

		return this.listaPermisosSinFirmarPorObligacion;
	}

	/**
	 * Setter para propiedad listaPermisosSinFirmarPorObligacion.
	 * 
	 * @param listaPermisosSinFirmarPorObligacion
	 *            Nuevo valor de la propiedad listaPermisosSinFirmarPorObligacion.
	 */
	public void setListaPermisosSinFirmarPorObligacion(ArrayList listaPermisosSinFirmarPorObligacion) {

		this.listaPermisosSinFirmarPorObligacion = listaPermisosSinFirmarPorObligacion;
	}

	private List listaPropietariosVehiculo;

	public List getListaPropietariosVehiculo() {
		return listaPropietariosVehiculo;
	}

	public void setListaPropietariosVehiculo(List listaPropietariosVehiculo) {
		this.listaPropietariosVehiculo = listaPropietariosVehiculo;
	}

	/**
	 * Conserva el valor de la propiedad tablePhaseListenerPermisosSinFirmarPorObligacion.
	 */
	private TableSelectPhaseListener tablePhaseListenerPermisosSinFirmarPorObligacion = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerPermisosSinFirmarPorObligacion.
	 * 
	 * @return Valor de la propiedad tablePhaseListenerPermisosSinFirmarPorObligacion.
	 */
	public TableSelectPhaseListener getTablePhaseListenerPermisosSinFirmarPorObligacion() {

		return this.tablePhaseListenerPermisosSinFirmarPorObligacion;
	}

	/**
	 * Setter para propiedad tablePhaseListenerPermisosSinFirmarPorObligacion.
	 * 
	 * @param tablePhaseListenerPermisosSinFirmarPorObligacion
	 *            Nuevo valor de la propiedad tablePhaseListenerPermisosSinFirmarPorObligacion.
	 */
	public void setTablePhaseListenerPermisosSinFirmarPorObligacion(TableSelectPhaseListener tablePhaseListenerPermisosSinFirmarPorObligacion) {

		this.tablePhaseListenerPermisosSinFirmarPorObligacion = tablePhaseListenerPermisosSinFirmarPorObligacion;
	}

	private TableSelectPhaseListener tablePhaseListenerMarca = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerMarca() {
		return tablePhaseListenerMarca;
	}

	public void setTablePhaseListenerMarca(TableSelectPhaseListener tableSelectPhaseListenerMarca) {
		this.tablePhaseListenerMarca = tableSelectPhaseListenerMarca;
	}

	private TableSelectPhaseListener tablePhaseListenerModelo = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerModelo() {
		return tablePhaseListenerModelo;
	}

	public void setTablePhaseListenerModelo(TableSelectPhaseListener tablePhaseListenerModelo) {
		this.tablePhaseListenerModelo = tablePhaseListenerModelo;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroConstante = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroConstante() {
		return this.tablePhaseListenerParametroConstante;
	}

	public void setTablePhaseListenerParametroConstante(TableSelectPhaseListener tablePhaseListenerParametroConstante) {
		this.tablePhaseListenerParametroConstante = tablePhaseListenerParametroConstante;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroDinamico = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroDinamico() {
		return this.tablePhaseListenerParametroDinamico;
	}

	public void setTablePhaseListenerParametroDinamico(TableSelectPhaseListener tablePhaseListenerParametroDinamico) {
		this.tablePhaseListenerParametroDinamico = tablePhaseListenerParametroDinamico;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroSHPS = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroSHPS() {
		return tablePhaseListenerParametroSHPS;
	}

	public void setTablePhaseListenerParametroSHPS(TableSelectPhaseListener tablePhaseListenerParametroSHPS) {
		this.tablePhaseListenerParametroSHPS = tablePhaseListenerParametroSHPS;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroGrupoZona = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroGrupoZona() {
		return tablePhaseListenerParametroGrupoZona;
	}

	public void setTablePhaseListenerParametroGrupoZona(TableSelectPhaseListener tablePhaseListenerParametroGrupoZona) {
		this.tablePhaseListenerParametroGrupoZona = tablePhaseListenerParametroGrupoZona;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroOSP = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroOSP() {
		return tablePhaseListenerParametroOSP;
	}

	public void setTablePhaseListenerParametroOSP(TableSelectPhaseListener tablePhaseListenerParametroOSP) {
		this.tablePhaseListenerParametroOSP = tablePhaseListenerParametroOSP;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroObra = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroObra() {
		return tablePhaseListenerParametroObra;
	}

	public void setTablePhaseListenerParametroObra(TableSelectPhaseListener tablePhaseListenerParametroObra) {
		this.tablePhaseListenerParametroObra = tablePhaseListenerParametroObra;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroPFO = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroPFO() {
		return tablePhaseListenerParametroPFO;
	}

	public void setTablePhaseListenerParametroPFO(TableSelectPhaseListener tablePhaseListenerParametroPFO) {
		this.tablePhaseListenerParametroPFO = tablePhaseListenerParametroPFO;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroParcelario = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroParcelario() {
		return tablePhaseListenerParametroParcelario;
	}

	public void setTablePhaseListenerParametroParcelario(TableSelectPhaseListener tablePhaseListenerParametroParcelario) {
		this.tablePhaseListenerParametroParcelario = tablePhaseListenerParametroParcelario;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroServiciosOSP = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroServiciosOSP() {
		return tablePhaseListenerParametroServiciosOSP;
	}

	public void setTablePhaseListenerParametroServiciosOSP(TableSelectPhaseListener tablePhaseListenerParametroServiciosOSP) {
		this.tablePhaseListenerParametroServiciosOSP = tablePhaseListenerParametroServiciosOSP;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroPersona = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroPersona() {
		return tablePhaseListenerParametroPersona;
	}

	public void setTablePhaseListenerParametroPersona(TableSelectPhaseListener tablePhaseListenerParametroPersona) {
		this.tablePhaseListenerParametroPersona = tablePhaseListenerParametroPersona;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroTGI = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroTGI() {
		return tablePhaseListenerParametroTGI;
	}

	public void setTablePhaseListenerParametroTGI(TableSelectPhaseListener tablePhaseListenerParametroTGI) {
		this.tablePhaseListenerParametroTGI = tablePhaseListenerParametroTGI;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroVencimiento = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroVencimiento() {
		return tablePhaseListenerParametroVencimiento;
	}

	public void setTablePhaseListenerParametroVencimiento(TableSelectPhaseListener tablePhaseListenerParametroVencimiento) {
		this.tablePhaseListenerParametroVencimiento = tablePhaseListenerParametroVencimiento;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroCementerio = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroCementerio() {
		return tablePhaseListenerParametroCementerio;
	}

	public void setTablePhaseListenerParametroCementerio(TableSelectPhaseListener tablePhaseListenerParametroCementerio) {
		this.tablePhaseListenerParametroCementerio = tablePhaseListenerParametroCementerio;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroAutomotor = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroAutomotor() {
		return tablePhaseListenerParametroAutomotor;
	}

	public void setTablePhaseListenerParametroAutomotor(TableSelectPhaseListener tablePhaseListenerParametroAutomotor) {
		this.tablePhaseListenerParametroAutomotor = tablePhaseListenerParametroAutomotor;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroVehiculo = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroVehiculo() {
		return tablePhaseListenerParametroVehiculo;
	}

	public void setTablePhaseListenerParametroVehiculo(TableSelectPhaseListener tablePhaseListenerParametroVehiculo) {
		this.tablePhaseListenerParametroVehiculo = tablePhaseListenerParametroVehiculo;
	}

	private TableSelectPhaseListener tablePhaseListenerParametroDeuda = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerParametroDeuda() {
		return tablePhaseListenerParametroDeuda;
	}

	public void setTablePhaseListenerParametroDeuda(TableSelectPhaseListener tablePhaseListenerParametroDeuda) {
		this.tablePhaseListenerParametroDeuda = tablePhaseListenerParametroDeuda;
	}

	/**
	 * Conserva el valor de la propiedad mapaTipoTasa.
	 */
	private Map<String, TipoTasa> mapaTipoTasa = new HashMap<String, TipoTasa>();

	/**
	 * Getter para propiedad mapaTipoTasa.
	 * 
	 * @return Valor de la propiedad mapaTipoTasa.
	 */
	public Map<String, TipoTasa> getMapaTipoTasa() {
		try {
			List lista = this.getRemoteSystemTipoTasa().findListaTiposTasa(new FiltroTipoTasa()).getListaResultados();
			for(Object object : lista) {
				TipoTasa elemento = (TipoTasa) object;
				this.mapaTipoTasa.put(elemento.getNombre(), elemento);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return this.mapaTipoTasa;
	}

	/**
	 * Setter para propiedad mapaTipoTasa.
	 * 
	 * @param mapaTipoTasa
	 *            Nuevo valor de la propiedad mapaTipoTasa.
	 */
	public void setMapaTipoTasa(Map<String, TipoTasa> mapaTipoTasa) {

		this.mapaTipoTasa = mapaTipoTasa;
	}

	private Map<String, TipoObligacion> mapaTipoObligacion = null;

	public Map<String, TipoObligacion> getMapaTipoObligacion() {
		if(mapaTipoObligacion == null) {
			try {
				mapaTipoObligacion = new HashMap<String, TipoObligacion>();
				List lista = this.getRemoteSystemTipoTasa().findListaTipoObligacion(null, null);
				for(Object object : lista) {
					TipoObligacion elemento = (TipoObligacion) object;
					this.mapaTipoObligacion.put(elemento.getNombre(), elemento);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoObligacion;
	}

	public void setMapaTipoObligacion(Map<String, TipoObligacion> pMapa) {
		this.mapaTipoObligacion = pMapa;
	}

	private Map<String, PlantillaDocumentoTasaMenor> mapaTipoObligacionTasaMenor = null;

	public Map<String, PlantillaDocumentoTasaMenor> getMapaTipoObligacionTasaMenor() {
		if(mapaTipoObligacionTasaMenor == null) {
			try {
				FiltroPlantillaDocumentoTasaMenor locFiltro = new FiltroPlantillaDocumentoTasaMenor();
				mapaTipoObligacionTasaMenor = new HashMap<String, PlantillaDocumentoTasaMenor>();
				locFiltro = this.getRemoteSystemDocumentoTasaMenor().findListaPlantillaDocumentoTasaMenor(locFiltro);
				List lista = locFiltro.getListaResultados();
				for(Object object : lista) {
					PlantillaDocumentoTasaMenor elemento = (PlantillaDocumentoTasaMenor) object;
					this.mapaTipoObligacionTasaMenor.put(elemento.getNombre(), elemento);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoObligacionTasaMenor;
	}

	public void setMapaTipoObligacionTasaMenor(Map<String, PlantillaDocumentoTasaMenor> pMapa) {
		this.mapaTipoObligacionTasaMenor = pMapa;
	}

	/*
	 * private Map<String, PlantillaObligacion> mapaPlantillaObligacionOSP = null;
	 * 
	 * public Map<String, PlantillaObligacion> getMapaPlantillaObligacionOSP() { if (mapaPlantillaObligacionOSP == null) { try { mapaPlantillaObligacionOSP =
	 * new HashMap<String, PlantillaObligacion>(); FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
	 * 
	 * TipoObligacion tipoObligacion = null; tipoObligacion = this.getMapaTipoObligacion().get("OYSP"); locFiltro.setTipoObligacion(tipoObligacion);
	 * List<PlantillaObligacion> lista = this .getRemoteSystemPlantillaObligaciones() .findListaPlantillaObligaciones(locFiltro) .getListaResultados();
	 * 
	 * for (PlantillaObligacion cadaPlantilla : lista) { mapaPlantillaObligacionOSP.put(cadaPlantilla.getNombre(), cadaPlantilla); } } catch (Exception e) {
	 * e.printStackTrace(); } } return mapaPlantillaObligacionOSP; }
	 */

	/*
	 * private Map<String, PlantillaObligacion> mapaPlantillaObligacionSHPS = null;
	 * 
	 * public Map<String, PlantillaObligacion> getMapaPlantillaObligacionSHPS() { if (mapaPlantillaObligacionSHPS == null) { try { mapaPlantillaObligacionSHPS =
	 * new HashMap<String, PlantillaObligacion>(); FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
	 * 
	 * TipoObligacion tipoObligacion = null; tipoObligacion = this.getMapaTipoObligacion().get("SHPS"); locFiltro.setTipoObligacion(tipoObligacion);
	 * List<PlantillaObligacion> lista = this .getRemoteSystemPlantillaObligaciones() .findListaPlantillaObligaciones(locFiltro) .getListaResultados();
	 * 
	 * for (PlantillaObligacion cadaPlantilla : lista) { mapaPlantillaObligacionSHPS.put(cadaPlantilla.getNombre(), cadaPlantilla); } } catch (Exception e) {
	 * e.printStackTrace(); } } return mapaPlantillaObligacionSHPS; }
	 */

	/*
	 * private Map<String, PlantillaObligacion> mapaPlantillaObligacionTGI = null;
	 * 
	 * public Map<String, PlantillaObligacion> getMapaPlantillaObligacionTGI() { if (mapaPlantillaObligacionTGI == null) { try { mapaPlantillaObligacionTGI =
	 * new HashMap<String, PlantillaObligacion>(); FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
	 * 
	 * TipoObligacion tipoObligacion = null; tipoObligacion = this.getMapaTipoObligacion().get("TGI"); locFiltro.setTipoObligacion(tipoObligacion);
	 * List<PlantillaObligacion> lista = this .getRemoteSystemPlantillaObligaciones() .findListaPlantillaObligaciones(locFiltro) .getListaResultados();
	 * 
	 * for (PlantillaObligacion cadaPlantilla : lista) { mapaPlantillaObligacionTGI.put(cadaPlantilla.getNombre(), cadaPlantilla); } } catch (Exception e) {
	 * e.printStackTrace(); } } return mapaPlantillaObligacionTGI; }
	 */

	/*
	 * private Map<String, PlantillaObligacion> mapaPlantillaObligacionAutomotor = null;
	 * 
	 * public Map<String, PlantillaObligacion> getMapaPlantillaObligacionAutomotor() { if (mapaPlantillaObligacionAutomotor == null) { try {
	 * mapaPlantillaObligacionAutomotor = new HashMap<String, PlantillaObligacion>(); FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
	 * 
	 * TipoObligacion tipoObligacion = null; tipoObligacion = this.getMapaTipoObligacion().get("AUTOMOTOR"); locFiltro.setTipoObligacion(tipoObligacion);
	 * List<PlantillaObligacion> lista = this .getRemoteSystemPlantillaObligaciones() .findListaPlantillaObligaciones(locFiltro) .getListaResultados();
	 * 
	 * for (PlantillaObligacion cadaPlantilla : lista) { mapaPlantillaObligacionAutomotor.put( cadaPlantilla.getNombre(), cadaPlantilla); } } catch (Exception
	 * e) { e.printStackTrace(); } } return mapaPlantillaObligacionAutomotor; }
	 */

	/*
	 * private Map<String, PlantillaObligacion> mapaPlantillaObligacionCementerio = null;
	 * 
	 * public Map<String, PlantillaObligacion> getMapaPlantillaObligacionCementerio() { if (mapaPlantillaObligacionCementerio == null) { try {
	 * mapaPlantillaObligacionCementerio = new HashMap<String, PlantillaObligacion>(); FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
	 * 
	 * TipoObligacion tipoObligacion = null; tipoObligacion = this.getMapaTipoObligacion().get("CEMENTERIO"); locFiltro.setTipoObligacion(tipoObligacion);
	 * List<PlantillaObligacion> lista = this .getRemoteSystemPlantillaObligaciones() .findListaPlantillaObligaciones(locFiltro) .getListaResultados();
	 * 
	 * for (PlantillaObligacion cadaPlantilla : lista) { mapaPlantillaObligacionCementerio.put( cadaPlantilla.getNombre(), cadaPlantilla); } } catch (Exception
	 * e) { e.printStackTrace(); } } return mapaPlantillaObligacionCementerio; }
	 */

	/**
	 * Conserva el valor de la propiedad listaPlanesCuentaPFO.
	 */
	private ArrayList listaPlanesCuentaPFO;

	/**
	 * Getter para propiedad listaPlanesCuentaPFO.
	 * 
	 * @return Valor de la propiedad listaPlanesCuentaPFO.
	 */
	public ArrayList getListaPlanesCuentaPFO() {

		return this.listaPlanesCuentaPFO;
	}

	/**
	 * Setter para propiedad listaPlanesCuentaPFO.
	 * 
	 * @param listaPlanesCuentaPFO
	 *            Nuevo valor de la propiedad listaPlanesCuentaPFO.
	 */
	public void setListaPlanesCuentaPFO(ArrayList listaPlanesCuentaPFO) {

		this.listaPlanesCuentaPFO = listaPlanesCuentaPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaCuadrasAfectadasPFO.
	 */
	private ArrayList listaCuadrasAfectadasPFO;

	/**
	 * Getter para propiedad listaCuadrasAfectadasPFO.
	 * 
	 * @return Valor de la propiedad listaCuadrasAfectadasPFO.
	 */
	public ArrayList getListaCuadrasAfectadasPFO() {

		return this.listaCuadrasAfectadasPFO;
	}

	/**
	 * Setter para propiedad listaCuadrasAfectadasPFO.
	 * 
	 * @param listaCuadrasAfectadasPFO
	 *            Nuevo valor de la propiedad listaCuadrasAfectadasPFO.
	 */
	public void setListaCuadrasAfectadasPFO(ArrayList listaCuadrasAfectadasPFO) {

		this.listaCuadrasAfectadasPFO = listaCuadrasAfectadasPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaLogModificacionesPFO.
	 */
	private ArrayList listaLogModificacionesPFO;

	/**
	 * Getter para propiedad listaLogModificacionesPFO.
	 * 
	 * @return Valor de la propiedad listaLogModificacionesPFO.
	 */
	public ArrayList getListaLogModificacionesPFO() {

		return this.listaLogModificacionesPFO;
	}

	/**
	 * Setter para propiedad listaLogModificacionesPFO.
	 * 
	 * @param listaLogModificacionesPFO
	 *            Nuevo valor de la propiedad listaLogModificacionesPFO.
	 */
	public void setListaLogModificacionesPFO(ArrayList listaLogModificacionesPFO) {

		this.listaLogModificacionesPFO = listaLogModificacionesPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaObligacionPrehabilitadas.
	 */
	private ArrayList listaObligacionPrehabilitadas;

	/**
	 * Getter para propiedad listaObligacionPrehabilitadas.
	 * 
	 * @return Valor de la propiedad listaObligacionPrehabilitadas.
	 */
	public ArrayList getListaObligacionPrehabilitadas() {

		return this.listaObligacionPrehabilitadas;
	}

	/**
	 * Setter para propiedad listaObligacionPrehabilitadas.
	 * 
	 * @param listaObligacionPrehabilitadas
	 *            Nuevo valor de la propiedad listaObligacionPrehabilitadas.
	 */
	public void setListaObligacionPrehabilitadas(ArrayList listaObligacionPrehabilitadas) {

		this.listaObligacionPrehabilitadas = listaObligacionPrehabilitadas;
	}

	/**
	 * Conserva el valor de la propiedad listaClausurasSHPS.
	 */
	private ArrayList listaClausurasSHPS;

	/**
	 * Getter para propiedad listaClausurasSHPS.
	 * 
	 * @return Valor de la propiedad listaClausurasSHPS.
	 */
	public ArrayList getListaClausurasSHPS() {

		return this.listaClausurasSHPS;
	}

	/**
	 * Setter para propiedad listaClausurasSHPS.
	 * 
	 * @param listaClausurasSHPS
	 *            Nuevo valor de la propiedad listaClausurasSHPS.
	 */
	public void setListaClausurasSHPS(ArrayList listaClausurasSHPS) {

		this.listaClausurasSHPS = listaClausurasSHPS;
	}

	/**
	 * Conserva la lista de exenciones de la obligacion.
	 */
	private ArrayList listaExenciones;

	/**
	 * Getter para propiedad listaExenciones.
	 * 
	 * @return Valor de la propiedad listaExenciones.
	 */
	public ArrayList getListaExenciones() {

		return this.listaExenciones;
	}

	/**
	 * Setter para propiedad listaExenciones.
	 * 
	 * @param listaExenciones
	 *            Nuevo valor de la propiedad listaExenciones.
	 */
	public void setListaExenciones(ArrayList listaExenciones) {

		this.listaExenciones = listaExenciones;
	}

	/**
	 * Conserva el valor de la propiedad listaClasurasSinFirmar.
	 */
	private ArrayList listaClasurasSinFirmar;

	/**
	 * Getter para propiedad listaClasurasSinFirmar.
	 * 
	 * @return Valor de la propiedad listaClasurasSinFirmar.
	 */
	public ArrayList getListaClasurasSinFirmar() {

		return this.listaClasurasSinFirmar;
	}

	/**
	 * Setter para propiedad listaClasurasSinFirmar.
	 * 
	 * @param listaClasurasSinFirmar
	 *            Nuevo valor de la propiedad listaClasurasSinFirmar.
	 */
	public void setListaClasurasSinFirmar(ArrayList listaClasurasSinFirmar) {

		this.listaClasurasSinFirmar = listaClasurasSinFirmar;
	}

	/**
	 * Conserva el valor de la propiedad listaModificadores.
	 */
	private ArrayList listaModificadores;

	/**
	 * Getter para propiedad listaModificadores.
	 * 
	 * @return Valor de la propiedad listaModificadores.
	 */
	public ArrayList getListaModificadores() {

		return this.listaModificadores;
	}

	/**
	 * Setter para propiedad listaModificadores.
	 * 
	 * @param listaModificadores
	 *            Nuevo valor de la propiedad listaModificadores.
	 */
	public void setListaModificadores(ArrayList listaModificadores) {

		this.listaModificadores = listaModificadores;
	}

	/**
	 * Conserva el valor de la propiedad listaIntereses.
	 */
	private ArrayList listaIntereses;

	/**
	 * Getter para propiedad Intereses.
	 * 
	 * @return Valor de la propiedad listaIntereses.
	 */
	public ArrayList getListaIntereses() {

		return this.listaIntereses;
	}

	/**
	 * Setter para propiedad listaIntereses.
	 * 
	 * @param listaIntereses
	 *            Nuevo valor de la propiedad listaIntereses.
	 */
	public void setListaIntereses(ArrayList listaIntereses) {

		this.listaIntereses = listaIntereses;
	}

	/**
	 * Conserva el valor de la propiedad listaRecargos.
	 */
	private ArrayList listaRecargos;

	/**
	 * Getter para propiedad Recargos.
	 * 
	 * @return Valor de la propiedad listaRecargos.
	 */
	public ArrayList getListaRecargos() {
		return this.listaRecargos;
	}

	/**
	 * Setter para propiedad listaRecargos.
	 * 
	 * @param listaIntereses
	 *            Nuevo valor de la propiedad listaRecargos.
	 */
	public void setListaRecargos(ArrayList listaRecargos) {
		this.listaRecargos = listaRecargos;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposTasa.
	 */
	private List listaTiposTasa;

	/**
	 * Getter para propiedad listaTiposTasa.
	 * 
	 * @return Valor de la propiedad listaTiposTasa.
	 */
	public List getListaTiposTasa() {

		return this.listaTiposTasa;
	}

	/**
	 * Setter para propiedad listaTiposTasa.
	 * 
	 * @param listaTiposTasa
	 *            Nuevo valor de la propiedad listaTiposTasa.
	 */
	public void setListaTiposTasa(List listaTiposTasa) {

		this.listaTiposTasa = listaTiposTasa;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametro.
	 */
	private ArrayList listaTiposParametro;

	/**
	 * Getter para propiedad listaTiposParametro.
	 * 
	 * @return Valor de la propiedad listaTiposParametro.
	 */
	public ArrayList getListaTiposParametro() {

		return this.listaTiposParametro;
	}

	/**
	 * Setter para propiedad listaTiposParametro.
	 * 
	 * @param listaTiposParametro
	 *            Nuevo valor de la propiedad listaTiposParametro.
	 */
	public void setListaTiposParametro(ArrayList listaTiposParametro) {

		this.listaTiposParametro = listaTiposParametro;
	}

	private ArrayList listaParametroAlicuotaFormula;

	/**
	 * Getter para propiedad listaParametroAlicuotaFormula.
	 * 
	 * @return Valor de la propiedad listaParametroAlicuotaFormula.
	 */
	public ArrayList getListaParametroAlicuotaFormula() {

		return this.listaParametroAlicuotaFormula;
	}

	/**
	 * Setter para propiedad listaParametroAlicuotaFormula.
	 * 
	 * @param listaParametroAlicuotaFormula
	 *            Nuevo valor de la propiedad listaParametroAlicuotaFormula.
	 */
	public void setListaParametroAlicuotaFormula(ArrayList listaParametroAlicuotaFormula) {

		this.listaParametroAlicuotaFormula = listaParametroAlicuotaFormula;
	}

	private ArrayList listaParametroAlicuotaNuevos;

	/**
	 * Getter para propiedad listaParametroAlicuotaNuevos.
	 * 
	 * @return Valor de la propiedad listaParametroAlicuotaNuevos.
	 */
	public ArrayList getListaParametroAlicuotaNuevos() {

		return this.listaParametroAlicuotaNuevos;
	}

	/**
	 * Setter para propiedad listaParametroAlicuotaNuevos.
	 * 
	 * @param listaParametroAlicuotaNuevos
	 *            Nuevo valor de la propiedad listaParametroAlicuotaNuevos.
	 */
	public void setListaParametroAlicuotaNuevos(ArrayList listaParametroAlicuotaNuevos) {

		this.listaParametroAlicuotaNuevos = listaParametroAlicuotaNuevos;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroFormulaAlicuota.
	 */
	private ArrayList listaTiposParametroFormulaAlicuota;

	/**
	 * Getter para propiedad listaTiposParametroFormulaAlicuota.
	 * 
	 * @return Valor de la propiedad listaTiposParametroFormulaAlicuota.
	 */
	public ArrayList getListaTiposParametroFormulaAlicuota() {

		return this.listaTiposParametroFormulaAlicuota;
	}

	/**
	 * Setter para propiedad listaTiposParametroFormulaAlicuota.
	 * 
	 * @param listaTiposParametroFormulaAlicuota
	 *            Nuevo valor de la propiedad listaTiposParametroFormulaAlicuota.
	 */
	public void setListaTiposParametroFormulaAlicuota(ArrayList listaTiposParametroFormulaAlicuota) {

		this.listaTiposParametroFormulaAlicuota = listaTiposParametroFormulaAlicuota;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroConstante.
	 */
	private List listaTiposParametroConstante;

	/**
	 * Getter para propiedad listaTiposParametroConstante.
	 * 
	 * @return Valor de la propiedad listaTiposParametroConstante.
	 */
	public List getListaTiposParametroConstante() {

		return this.listaTiposParametroConstante;
	}

	/**
	 * Setter para propiedad listaTiposParametroConstante.
	 * 
	 * @param listaTiposParametroConstante
	 *            Nuevo valor de la propiedad listaTiposParametroConstante.
	 */
	public void setListaTiposParametroConstante(List listaTiposParametroConstante) {
		this.listaTiposParametroConstante = listaTiposParametroConstante;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroParcelario.
	 */
	private ArrayList listaTiposParametroParcelario;

	/**
	 * Getter para propiedad listaTiposParametroParcelario.
	 * 
	 * @return Valor de la propiedad listaTiposParametroParcelario.
	 */
	public ArrayList getListaTiposParametroParcelario() {

		return this.listaTiposParametroParcelario;
	}

	/**
	 * Setter para propiedad listaTiposParametroParcelario.
	 * 
	 * @param listaTiposParametroParcelario
	 *            Nuevo valor de la propiedad listaTiposParametroParcelario.
	 */
	public void setListaTiposParametroParcelario(ArrayList listaTiposParametroParcelario) {

		this.listaTiposParametroParcelario = listaTiposParametroParcelario;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroAlicuota.
	 */
	private ArrayList listaTiposParametroAlicuota;

	/**
	 * Getter para propiedad listaTiposParametroAlicuota.
	 * 
	 * @return Valor de la propiedad listaTiposParametroAlicuota.
	 */
	public ArrayList getListaTiposParametroAlicuota() {

		return this.listaTiposParametroAlicuota;
	}

	/**
	 * Setter para propiedad listaTiposParametroAlicuota.
	 * 
	 * @param listaTiposParametroAlicuota
	 *            Nuevo valor de la propiedad listaTiposParametroAlicuota.
	 */
	public void setListaTiposParametroAlicuota(ArrayList listaTiposParametroAlicuota) {

		this.listaTiposParametroAlicuota = listaTiposParametroAlicuota;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroGrupoZona.
	 */
	private List listaTiposParametroGrupoZona;

	/**
	 * Getter para propiedad listaTiposParametroGrupoZona.
	 * 
	 * @return Valor de la propiedad listaTiposParametroGrupoZona.
	 */
	public List getListaTiposParametroGrupoZona() {

		return this.listaTiposParametroGrupoZona;
	}

	/**
	 * Setter para propiedad listaTiposParametroGrupoZona.
	 * 
	 * @param listaTiposParametroGrupoZona
	 *            Nuevo valor de la propiedad listaTiposParametroGrupoZona.
	 */
	public void setListaTiposParametroGrupoZona(List listaTiposParametroGrupoZona) {

		this.listaTiposParametroGrupoZona = listaTiposParametroGrupoZona;
	}

	/**
	 * Conserva el valor de la propiedad listaTipoParametroGrilla.
	 */
	private List listaTipoParametroGrilla;

	/**
	 * Getter para propiedad listaTipoParametroGrilla.
	 * 
	 * @return Valor de la propiedad listaTipoParametroGrilla.
	 */
	public List getListaTipoParametroGrilla() {

		return this.listaTipoParametroGrilla;
	}

	/**
	 * Setter para propiedad listaTipoParametroGrilla.
	 * 
	 * @param listaTipoParametroGrilla
	 *            Nuevo valor de la propiedad listaTipoParametroGrilla.
	 */
	public void setListaTipoParametroGrilla(List listaTipoParametroGrilla) {

		this.listaTipoParametroGrilla = listaTipoParametroGrilla;
	}

	/**
	 * Conserva el valor de la propiedad listaZonasTipoParamGrupoZona.
	 */
	private List listaZonasTipoParamGrupoZona;

	/**
	 * Getter para propiedad listaZonasTipoParamGrupoZona.
	 * 
	 * @return Valor de la propiedad listaZonasTipoParamGrupoZona.
	 */
	public List getListaZonasTipoParamGrupoZona() {

		return this.listaZonasTipoParamGrupoZona;
	}

	/**
	 * Setter para propiedad listaZonasTipoParamGrupoZona.
	 * 
	 * @param listaZonasTipoParamGrupoZona
	 *            Nuevo valor de la propiedad listaZonasTipoParamGrupoZona.
	 */
	public void setListaZonasTipoParamGrupoZona(List listaZonasTipoParamGrupoZona) {

		this.listaZonasTipoParamGrupoZona = listaZonasTipoParamGrupoZona;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroObra.
	 */
	private ArrayList listaTiposParametroObra;

	/**
	 * Getter para propiedad listaTiposParametroObra.
	 * 
	 * @return Valor de la propiedad listaTiposParametroObra.
	 */
	public ArrayList getListaTiposParametroObra() {

		return this.listaTiposParametroObra;
	}

	/**
	 * Setter para propiedad listaTiposParametroObra.
	 * 
	 * @param listaTiposParametroObra
	 *            Nuevo valor de la propiedad listaTiposParametroObra.
	 */
	public void setListaTiposParametroObra(ArrayList listaTiposParametroObra) {

		this.listaTiposParametroObra = listaTiposParametroObra;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposModificador.
	 */
	private ArrayList listaTiposModificador;

	/**
	 * Getter para propiedad listaTiposModificador.
	 * 
	 * @return Valor de la propiedad listaTiposModificador.
	 */
	public ArrayList getListaTiposModificador() {

		return this.listaTiposModificador;
	}

	/**
	 * Setter para propiedad listaTiposModificador.
	 * 
	 * @param listaTiposModificador
	 *            Nuevo valor de la propiedad listaTiposModificador.
	 */
	public void setListaTiposModificador(ArrayList listaTiposModificador) {

		this.listaTiposModificador = listaTiposModificador;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroPersona.
	 */
	private ArrayList listaTiposParametroPersona;

	/**
	 * Getter para propiedad listaTiposParametroPersona.
	 * 
	 * @return Valor de la propiedad listaTiposParametroPersona.
	 */
	public ArrayList getListaTiposParametroPersona() {

		return this.listaTiposParametroPersona;
	}

	/**
	 * Setter para propiedad listaTiposParametroPersona.
	 * 
	 * @param listaTiposParametroPersona
	 *            Nuevo valor de la propiedad listaTiposParametroPersona.
	 */
	public void setListaTiposParametroPersona(ArrayList listaTiposParametroPersona) {

		this.listaTiposParametroPersona = listaTiposParametroPersona;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroOSP.
	 */
	private ArrayList listaTiposParametroOSP;

	/**
	 * Getter para propiedad listaTiposParametroOSP.
	 * 
	 * @return Valor de la propiedad listaTiposParametroOSP.
	 */
	public ArrayList getListaTiposParametroOSP() {

		return this.listaTiposParametroOSP;
	}

	/**
	 * Setter para propiedad listaTiposParametroOSP.
	 * 
	 * @param listaTiposParametroOSP
	 *            Nuevo valor de la propiedad listaTiposParametroOSP.
	 */
	public void setListaTiposParametroOSP(ArrayList listaTiposParametroOSP) {

		this.listaTiposParametroOSP = listaTiposParametroOSP;
	}

	private List listaTiposParametroSHPS;

	public List getListaTiposParametroSHPS() {
		return listaTiposParametroSHPS;
	}

	public void setListaTiposParametroSHPS(List listaTiposParametroSHPS) {
		this.listaTiposParametroSHPS = listaTiposParametroSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroSHPS.
	 */
	private ArrayList listaTiposParametroDinamico;

	/**
	 * Getter para propiedad listaTiposParametroSHPS.
	 * 
	 * @return Valor de la propiedad listaTiposParametroSHPS.
	 */
	public ArrayList getListaTiposParametroDinamico() {

		return this.listaTiposParametroDinamico;
	}

	/**
	 * Setter para propiedad listaTiposParametroSHPS.
	 * 
	 * @param listaTiposParametroSHPS
	 *            Nuevo valor de la propiedad listaTiposParametroSHPS.
	 */
	public void setListaTiposParametroDinamico(ArrayList listaTiposParametroDinamico) {

		this.listaTiposParametroDinamico = listaTiposParametroDinamico;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroTipoModificador.
	 */
	private ArrayList listaTiposParametroTipoModificador;

	/**
	 * Getter para propiedad listaTiposParametroTipoModificador.
	 * 
	 * @return Valor de la propiedad listaTiposParametroTipoModificador.
	 */
	public ArrayList getListaTiposParametroTipoModificador() {

		return this.listaTiposParametroTipoModificador;
	}

	/**
	 * Setter para propiedad listaTiposParametroTipoModificador.
	 * 
	 * @param listaTiposParametroTipoModificador
	 *            Nuevo valor de la propiedad listaTiposParametroTipoModificador.
	 */
	public void setListaTiposParametroTipoModificador(ArrayList listaTiposParametroTipoModificador) {

		this.listaTiposParametroTipoModificador = listaTiposParametroTipoModificador;
	}

	/**
	 * Conserva el valor de la propiedad listaConceptoPorMora.
	 */
	private ArrayList listaConceptoPorMora;

	/**
	 * Getter para propiedad listaConceptoPorMora.
	 * 
	 * @return Valor de la propiedad listaConceptoPorMora.
	 */
	public ArrayList getListaConceptoPorMora() {

		return this.listaConceptoPorMora;
	}

	/**
	 * Setter para propiedad listaConceptoPorMora.
	 * 
	 * @param listaConceptoPorMora
	 *            Nuevo valor de la propiedad listaConceptoPorMora.
	 */
	public void setListaConceptoPorMora(ArrayList listaConceptoPorMora) {

		this.listaConceptoPorMora = listaConceptoPorMora;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroTipoVencimiento.
	 */
	private ArrayList listaTiposParametroTipoVencimiento;

	/**
	 * Getter para propiedad listaTiposParametroTipoVencimiento.
	 * 
	 * @return Valor de la propiedad listaTiposParametroTipoVencimiento.
	 */
	public ArrayList getListaTiposParametroTipoVencimiento() {

		return this.listaTiposParametroTipoVencimiento;
	}

	/**
	 * Setter para propiedad listaTiposParametroTipoVencimiento.
	 * 
	 * @param listaTiposParametroTipoVencimiento
	 *            Nuevo valor de la propiedad listaTiposParametroTipoVencimiento.
	 */
	public void setListaTiposParametroTipoVencimiento(ArrayList listaTiposParametroTipoVencimiento) {

		this.listaTiposParametroTipoVencimiento = listaTiposParametroTipoVencimiento;
	}

	/**
	 * Conserva el valor de la propiedad listaVencimientos.
	 */
	private ArrayList listaVencimientos;

	/**
	 * Getter para propiedad listaVencimientos.
	 * 
	 * @return Valor de la propiedad listaVencimientos.
	 */
	public ArrayList getListaVencimientos() {

		return this.listaVencimientos;
	}

	/**
	 * Setter para propiedad listaVencimientos.
	 * 
	 * @param listaVencimientos
	 *            Nuevo valor de la propiedad listaVencimientos.
	 */
	public void setListaVencimientos(ArrayList listaVencimientos) {

		this.listaVencimientos = listaVencimientos;
	}

	/**
	 * Conserva el valor de la propiedad listaParametrosValores.
	 */
	private ArrayList listaParametrosValores;

	/**
	 * Getter para propiedad listaParametrosValores.
	 * 
	 * @return Valor de la propiedad listaParametrosValores.
	 */
	public ArrayList getListaParametrosValores() {

		return this.listaParametrosValores;
	}

	/**
	 * Setter para propiedad listaParametrosValores.
	 * 
	 * @param listaParametrosValores
	 *            Nuevo valor de la propiedad listaParametrosValores.
	 */
	public void setListaParametrosValores(ArrayList listaParametrosValores) {

		this.listaParametrosValores = listaParametrosValores;
	}

	/**
	 * Conserva el valor de la propiedad listaParametrosValoresFormulaAlicuota.
	 */
	private ArrayList listaParametrosValoresFormulaAlicuota;

	/**
	 * Getter para propiedad listaParametrosValoresFormulaAlicuota.
	 * 
	 * @return Valor de la propiedad listaParametrosValoresFormulaAlicuota.
	 */
	public ArrayList getListaParametrosValoresFormulaAlicuota() {

		return this.listaParametrosValoresFormulaAlicuota;
	}

	/**
	 * Setter para propiedad listaParametrosValoresFormulaAlicuota.
	 * 
	 * @param listaParametrosValoresFormulaAlicuota
	 *            Nuevo valor de la propiedad listaParametrosValoresFormulaAlicuota.
	 */
	public void setListaParametrosValoresFormulaAlicuota(ArrayList listaParametrosValoresFormulaAlicuota) {

		this.listaParametrosValoresFormulaAlicuota = listaParametrosValoresFormulaAlicuota;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroPFO.
	 */
	private ArrayList listaTiposParametroPFO;

	/**
	 * Getter para propiedad listaTiposParametroPFO.
	 * 
	 * @return Valor de la propiedad listaTiposParametroPFO.
	 */
	public ArrayList getListaTiposParametroPFO() {

		return this.listaTiposParametroPFO;
	}

	/**
	 * Setter para propiedad listaTiposParametroPFO.
	 * 
	 * @param listaTiposParametroPFO
	 *            Nuevo valor de la propiedad listaTiposParametroPFO.
	 */
	public void setListaTiposParametroPFO(ArrayList listaTiposParametroPFO) {

		this.listaTiposParametroPFO = listaTiposParametroPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroTGI.
	 */
	private ArrayList listaTiposParametroTGI;

	/**
	 * Getter para propiedad listaTiposParametroTGI.
	 * 
	 * @return Valor de la propiedad listaTiposParametroTGI.
	 */
	public ArrayList getListaTiposParametroTGI() {

		return this.listaTiposParametroTGI;
	}

	/**
	 * Setter para propiedad listaTiposParametroTGI.
	 * 
	 * @param listaTiposParametroTGI
	 *            Nuevo valor de la propiedad listaTiposParametroTGI.
	 */
	public void setListaTiposParametroTGI(ArrayList listaTiposParametroTGI) {

		this.listaTiposParametroTGI = listaTiposParametroTGI;
	}

	/**
	 * Conserva el valor de la propiedad listaTiposParametroVencimiento.
	 */
	private ArrayList listaTiposParametroVencimiento;

	/**
	 * Getter para propiedad listaTiposParametroVencimiento.
	 * 
	 * @return Valor de la propiedad listaTiposParametroVencimiento.
	 */
	public ArrayList getListaTiposParametroVencimiento() {

		return this.listaTiposParametroVencimiento;
	}

	/**
	 * Setter para propiedad listaTiposParametroVencimiento.
	 * 
	 * @param listaTiposParametroVencimiento
	 *            Nuevo valor de la propiedad listaTiposParametroVencimiento.
	 */
	public void setListaTiposParametroVencimiento(ArrayList listaTiposParametroVencimiento) {

		this.listaTiposParametroVencimiento = listaTiposParametroVencimiento;
	}

	/**
	 * Conserva el valor de la propiedad listaConsumosBasicos.
	 */
	private ArrayList listaConsumosBasicos;

	/**
	 * Getter para propiedad listaConsumosBasicos.
	 * 
	 * @return Valor de la propiedad listaConsumosBasicos.
	 */
	public ArrayList getListaConsumosBasicos() {

		return this.listaConsumosBasicos;
	}

	/**
	 * Setter para propiedad listaConsumosBasicos.
	 * 
	 * @param listaConsumosBasicos
	 *            Nuevo valor de la propiedad listaConsumosBasicos.
	 */
	public void setListaConsumosBasicos(ArrayList listaConsumosBasicos) {

		this.listaConsumosBasicos = listaConsumosBasicos;
	}

	private List listaPlan = new ArrayList();

	public List getListaPlan() {
		return listaPlan;
	}

	public void setListaPlan(List listaPlan) {
		this.listaPlan = listaPlan;
	}

	private List listaValuacionAcara = new ArrayList();

	public List getListaValuacionAcara() {
		return listaValuacionAcara;
	}

	public void setListaValuacionAcara(List listaValuacionAcara) {
		this.listaValuacionAcara = listaValuacionAcara;
	}

	private List listaTiposVehiculo = new ArrayList();

	public List getListaTiposVehiculo() {
		return listaTiposVehiculo;
	}

	public void setListaTiposVehiculo(List listaTiposVehiculo) {
		this.listaTiposVehiculo = listaTiposVehiculo;
	}

	private List listaVariablesSimples = new ArrayList();

	public List getListaVariablesSimples() {
		return listaVariablesSimples;
	}

	public void setListaVariablesSimples(List listaVariablesSimples) {
		this.listaVariablesSimples = listaVariablesSimples;
	}

	private List listaVariablesSimplesAlicuota = new ArrayList();

	public List getListaVariablesSimplesAlicuota() {
		return listaVariablesSimplesAlicuota;
	}

	public void setListaVariablesSimplesAlicuota(List listaVariablesSimplesAlicuota) {
		this.listaVariablesSimplesAlicuota = listaVariablesSimplesAlicuota;
	}

	private List listaVariablesCompuestas = new ArrayList();

	public List getListaVariablesCompuestas() {
		return listaVariablesCompuestas;
	}

	public void setListaVariablesCompuestas(List listaVariablesCompuestas) {
		this.listaVariablesCompuestas = listaVariablesCompuestas;
	}

	private ArrayList listaTiposParametroServiciosOSP;

	public ArrayList getListaTiposParametroServiciosOSP() {
		return listaTiposParametroServiciosOSP;
	}

	public void setListaTiposParametroServiciosOSP(ArrayList listaTiposParametroServiciosOSP) {
		this.listaTiposParametroServiciosOSP = listaTiposParametroServiciosOSP;
	}

	private final TableSelectPhaseListener tablePhaseListenerPlan = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerPlan() {
		return tablePhaseListenerPlan;
	}

	private TableSelectPhaseListener tablePhaseListenerValuacionAcara = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerValuacionAcara() {
		return tablePhaseListenerValuacionAcara;
	}

	public void setTablePhaseListenerValuacionAcara(TableSelectPhaseListener tablePhaseListenerValuacionAcara) {
		this.tablePhaseListenerValuacionAcara = tablePhaseListenerValuacionAcara;
	}

	private TableSelectPhaseListener tablePhaseListenerTipoVehiculo = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerTipoVehiculo() {
		return tablePhaseListenerTipoVehiculo;
	}

	public void setTablePhaseListenerTipoVehiculo(TableSelectPhaseListener tablePhaseListenerTipoVehiculo) {
		this.tablePhaseListenerTipoVehiculo = tablePhaseListenerTipoVehiculo;
	}

	private PaginatedTable tablaServiciosOSP;

	public PaginatedTable getTablaServiciosOSP() {
		return tablaServiciosOSP;
	}

	public void setTablaServiciosOSP(PaginatedTable tablaServiciosOSP) {
		this.tablaServiciosOSP = tablaServiciosOSP;
	}

	private PaginatedTable tablaRubros;

	public PaginatedTable getTablaRubros() {
		return tablaRubros;
	}

	public void setTablaRubros(PaginatedTable tablaRubros) {
		this.tablaRubros = tablaRubros;
	}

	private PaginatedTable tablaTipoTasa;

	public PaginatedTable getTablaTipoTasa() {
		return tablaTipoTasa;
	}

	public void setTablaTipoTasa(PaginatedTable tablaTipoTasa) {
		this.tablaTipoTasa = tablaTipoTasa;
	}

	private PaginatedTable tablaConsumoBasico;

	public PaginatedTable getTablaConsumoBasico() {
		return tablaConsumoBasico;
	}

	public void setTablaConsumoBasico(PaginatedTable tablaConsumoBasico) {
		this.tablaConsumoBasico = tablaConsumoBasico;
	}

	private PaginatedTable tablaDocEspSHPS;

	public PaginatedTable getTablaDocEspSHPS() {
		return tablaDocEspSHPS;
	}

	public void setTablaDocEspSHPS(PaginatedTable tablaDocEspSHPS) {
		this.tablaDocEspSHPS = tablaDocEspSHPS;
	}

	private PaginatedTable tablaDocEspTasaMenor;

	public PaginatedTable getTablaDocEspTasaMenor() {
		return tablaDocEspTasaMenor;
	}

	public void setTablaDocEspTasaMenor(PaginatedTable tablaDocEspTasaMenor) {
		this.tablaDocEspTasaMenor = tablaDocEspTasaMenor;
	}

	private PaginatedTable tablaPlantillaObligacion;

	public PaginatedTable getTablaPlantillaObligacion() {
		return tablaPlantillaObligacion;
	}

	public void setTablaPlantillaObligacion(PaginatedTable tablaPlantillaObligacion) {
		this.tablaPlantillaObligacion = tablaPlantillaObligacion;
	}

	private PaginatedTable tablaDocEspOSP;

	public PaginatedTable getTablaDocEspOSP() {
		return tablaDocEspOSP;
	}

	public void setTablaDocEspOSP(PaginatedTable tablaDocEspOSP) {
		this.tablaDocEspOSP = tablaDocEspOSP;
	}

	private PaginatedTable tablaObra;

	public PaginatedTable getTablaObra() {
		return tablaObra;
	}

	public void setTablaObra(PaginatedTable tablaObra) {
		this.tablaObra = tablaObra;
	}

	private PaginatedTable tablaTransporteVehicular;

	public PaginatedTable getTablaTransporteVehicular() {
		return tablaTransporteVehicular;
	}

	private PaginatedTable tablaLibretaSanitaria;

	public PaginatedTable getTablaLibretaSanitaria() {
		return tablaLibretaSanitaria;
	}

	public void setTablaLibretaSanitaria(PaginatedTable tablaLibretaSanitaria) {
		this.tablaLibretaSanitaria = tablaLibretaSanitaria;
	}

	public void setTablaTransporteVehicular(PaginatedTable tablaTransporteVehicular) {
		this.tablaTransporteVehicular = tablaTransporteVehicular;
	}

	private PaginatedTable tablaVehiculo;

	public PaginatedTable getTablaVehiculo() {
		return tablaVehiculo;
	}

	public void setTablaVehiculo(PaginatedTable tablaVehiculo) {
		this.tablaVehiculo = tablaVehiculo;
	}

	private PaginatedTable tablaPlan;

	public PaginatedTable getTablaPlan() {
		return tablaPlan;
	}

	public void setTablaPlan(PaginatedTable tablaPlan) {
		this.tablaPlan = tablaPlan;
	}

	private PaginatedTable tablaValuacionAcara;

	public PaginatedTable getTablaValuacionAcara() {
		return tablaValuacionAcara;
	}

	public void setTablaValuacionAcara(PaginatedTable tablaValuacionAcara) {
		this.tablaValuacionAcara = tablaValuacionAcara;
	}

	private PaginatedTable tablaTipoVehiculo;

	public PaginatedTable getTablaTipoVehiculo() {
		return tablaTipoVehiculo;
	}

	public void setTablaTipoVehiculo(PaginatedTable tablaTipoVehiculo) {
		this.tablaTipoVehiculo = tablaTipoVehiculo;
	}

	private PaginatedTable tablaModelo;

	public PaginatedTable getTablaModelo() {
		return tablaModelo;
	}

	public void setTablaModelo(PaginatedTable tablaModelo) {
		this.tablaModelo = tablaModelo;
	}

	private PaginatedTable tablaMarca;

	public PaginatedTable getTablaMarca() {
		return tablaMarca;
	}

	public void setTablaMarca(PaginatedTable tablaMarca) {
		this.tablaMarca = tablaMarca;
	}

	private PaginatedTable tablaLocalComercial;

	public PaginatedTable getTablaLocalComercial() {
		return tablaLocalComercial;
	}

	public void setTablaLocalComercial(PaginatedTable tablaLocalComercial) {
		this.tablaLocalComercial = tablaLocalComercial;
	}

	private PaginatedTable tablaDocumentoTGI;

	public PaginatedTable getTablaDocumentoTGI() {
		return tablaDocumentoTGI;
	}

	public void setTablaDocumentoTGI(PaginatedTable tablaDocumentoTGI) {
		this.tablaDocumentoTGI = tablaDocumentoTGI;
	}

	private PaginatedTable tablaParcelaCementerio;

	public PaginatedTable getTablaParcelaCementerio() {
		return tablaParcelaCementerio;
	}

	public void setTablaParcelaCementerio(PaginatedTable tablaParcelaCementerio) {
		this.tablaParcelaCementerio = tablaParcelaCementerio;
	}

	private PaginatedTable tablaInspector;

	public PaginatedTable getTablaInspector() {
		return tablaInspector;
	}

	public void setTablaInspector(PaginatedTable tablaInspector) {
		this.tablaInspector = tablaInspector;
	}

	private PaginatedTable tablaDocEspCementerio;

	public PaginatedTable getTablaDocEspCementerio() {
		return tablaDocEspCementerio;
	}

	public void setTablaDocEspCementerio(PaginatedTable tablaDocEspCementerio) {
		this.tablaDocEspCementerio = tablaDocEspCementerio;
	}

	private PaginatedTable tablaPlantillaObligacionTasaMenor;

	public PaginatedTable getTablaPlantillaObligacionTasaMenor() {
		return tablaPlantillaObligacionTasaMenor;
	}

	public void setTablaPlantillaObligacionTasaMenor(PaginatedTable tablaPlantillaObligacionTasaMenor) {
		this.tablaPlantillaObligacionTasaMenor = tablaPlantillaObligacionTasaMenor;
	}

	private PaginatedTable tablaTipoParametroGrupoZona;

	public PaginatedTable getTablaTipoParametroGrupoZona() {
		return tablaTipoParametroGrupoZona;
	}

	public void setTablaTipoParametroGrupoZona(PaginatedTable tablaTipoParametroGrupoZona) {
		this.tablaTipoParametroGrupoZona = tablaTipoParametroGrupoZona;
	}

	private PaginatedTable tablaTipoParametroGrilla;

	public PaginatedTable getTablaTipoParametroGrilla() {
		return tablaTipoParametroGrilla;
	}

	public void setTablaTipoParametroGrilla(PaginatedTable tablaTipoParametroGrilla) {
		this.tablaTipoParametroGrilla = tablaTipoParametroGrilla;
	}

	private List listaTipoSepultura = new ArrayList();

	public List getListaTipoSepultura() {
		return listaTipoSepultura;
	}

	public void setListaTipoSepultura(List listaTipoSepultura) {
		this.listaTipoSepultura = listaTipoSepultura;
	}

	private PaginatedTable tablaTipoSepultura;

	public PaginatedTable getTablaTipoSepultura() {
		return tablaTipoSepultura;
	}

	public void setTablaTipoSepultura(PaginatedTable tablaTipoSepultura) {
		this.tablaTipoSepultura = tablaTipoSepultura;
	}

	private PaginatedTable tablaTipoParametroConstante;

	public PaginatedTable getTablaTipoParametroConstante() {
		return tablaTipoParametroConstante;
	}

	public void setTablaTipoParametroConstante(PaginatedTable tablaTipoParametroConstante) {
		this.tablaTipoParametroConstante = tablaTipoParametroConstante;
	}

	private List<VariableFormulaSimple> listaVariablesFormulaSimples = new ArrayList<VariableFormulaSimple>();

	public List<VariableFormulaSimple> getListaVariablesFormulaSimples() {
		return listaVariablesFormulaSimples;
	}

	public void setListaVariablesFormulaSimples(List<VariableFormulaSimple> listaVariablesFormulaSimples) {
		this.listaVariablesFormulaSimples = listaVariablesFormulaSimples;
	}

	private List<VariableFormulaSimple> listaVariablesSimpleAlicuota = new ArrayList<VariableFormulaSimple>();

	public List<VariableFormulaSimple> getListaVariablesSimpleAlicuota() {
		return listaVariablesSimpleAlicuota;
	}

	public void setListaVariablesSimpleAlicuota(List<VariableFormulaSimple> listaVariablesSimpleAlicuota) {
		this.listaVariablesSimpleAlicuota = listaVariablesSimpleAlicuota;
	}

	private List<VariableFormulaCompuesta> listaVariablesCompuestaAlicuota = new ArrayList<VariableFormulaCompuesta>();

	public List<VariableFormulaCompuesta> getListaVariablesCompuestaAlicuota() {
		return listaVariablesCompuestaAlicuota;
	}

	private List<VariableFormula> listaVariablesFormulaModificador = new ArrayList<VariableFormula>();

	public List<VariableFormula> getListaVariablesFormulaModificador() {
		return listaVariablesFormulaModificador;
	}

	public void setListaVariablesFormulaModificador(List<VariableFormula> listaVariablesFormulaModificador) {
		this.listaVariablesFormulaModificador = listaVariablesFormulaModificador;
	}

	private List listaDocEspAutomotor = new ArrayList();
	private PaginatedTable tablaDocEspAutomotor;

	public List getListaDocEspAutomotor() {
		return listaDocEspAutomotor;
	}

	public void setListaDocEspAutomotor(List listaDocEspAutomotor) {
		this.listaDocEspAutomotor = listaDocEspAutomotor;
	}

	public PaginatedTable getTablaDocEspAutomotor() {
		return tablaDocEspAutomotor;
	}

	public void setTablaDocEspAutomotor(PaginatedTable tablaDocEspAutomotor) {
		this.tablaDocEspAutomotor = tablaDocEspAutomotor;
	}

	public void setListaVariablesCompuestaAlicuota(List<VariableFormulaCompuesta> listaVariablesCompuestaAlicuota) {
		this.listaVariablesCompuestaAlicuota = listaVariablesCompuestaAlicuota;
	}

	private List listaAtributosDinamicosAutomotor;

	public List getListaAtributosDinamicosAutomotor() {
		return listaAtributosDinamicosAutomotor;
	}

	public void setListaAtributosDinamicosAutomotor(List listaAtributosDinamicosAutomotor) {
		this.listaAtributosDinamicosAutomotor = listaAtributosDinamicosAutomotor;
	}

	List<RegistroPropietario> listaRegistroPropietarioVehiculo;

	public List<RegistroPropietario> getListaRegistroPropietarioVehiculo() {
		return listaRegistroPropietarioVehiculo;
	}

	public void setListaRegistroPropietarioVehiculo(List<RegistroPropietario> listaRegistroPropietarioVehiculo) {
		this.listaRegistroPropietarioVehiculo = listaRegistroPropietarioVehiculo;
	}

	private List listaObligacionesParcela = null;

	public List getListaObligacionesParcela() {
		return listaObligacionesParcela;
	}

	public void setListaObligacionesParcela(List listaObligacionesParcela) {
		this.listaObligacionesParcela = listaObligacionesParcela;
	}

	private List listaObligacionesAutomotor = null;

	public List getListaObligacionesAutomotor() {
		return listaObligacionesAutomotor;
	}

	public void setListaObligacionesAutomotor(List listaObligacionesAutomotor) {
		this.listaObligacionesAutomotor = listaObligacionesAutomotor;
	}

	private List<ParcelaCementerio> listaParcelaCementerio;

	public List<ParcelaCementerio> getListaParcelaCementerio() {
		return listaParcelaCementerio;
	}

	public void setListaParcelaCementerio(List<ParcelaCementerio> listaParcelaCementerio) {
		this.listaParcelaCementerio = listaParcelaCementerio;
	}

	private List<Difunto> listaDifuntos;

	public List<Difunto> getListaDifuntos() {
		return listaDifuntos;
	}

	public void setListaDifuntos(List<Difunto> listaDifuntos) {
		this.listaDifuntos = listaDifuntos;
	}

	List<RegistroPropietario> listaRegistroPropietarioParcelaCementerio;

	public List<RegistroPropietario> getListaRegistroPropietarioParcelaCementerio() {
		return listaRegistroPropietarioParcelaCementerio;
	}

	public void setListaRegistroPropietarioParcelaCementerio(List<RegistroPropietario> listaRegistroPropietarioParcelaCementerio) {
		this.listaRegistroPropietarioParcelaCementerio = listaRegistroPropietarioParcelaCementerio;
	}

	private List listaDocEspCementerio;

	public List getListaDocEspCementerio() {
		return listaDocEspCementerio;
	}

	public void setListaDocEspCementerio(List listaDocEspCementerio) {
		this.listaDocEspCementerio = listaDocEspCementerio;
	}

	private List listaParcelasCementerio;

	public List getListaParcelasCementerio() {
		return listaParcelasCementerio;
	}

	public void setListaParcelasCementerio(List listaParcelasCementerio) {
		this.listaParcelasCementerio = listaParcelasCementerio;
	}

	private List listaAtributosDinamicosCementerio;

	public List getListaAtributosDinamicosCementerio() {
		return listaAtributosDinamicosCementerio;
	}

	public void setListaAtributosDinamicosCementerio(List listaAtributosDinamicosCementerio) {
		this.listaAtributosDinamicosCementerio = listaAtributosDinamicosCementerio;
	}

	private List listaAtributosDinamicosParcelaCementerio;

	public List getListaAtributosDinamicosParcelaCementerio() {
		return listaAtributosDinamicosParcelaCementerio;
	}

	public void setListaAtributosDinamicosParcelaCementerio(List listaAtributosDinamicosParcelaCementerio) {
		this.listaAtributosDinamicosParcelaCementerio = listaAtributosDinamicosParcelaCementerio;
	}

	private List listaAtributosDinamicosDifunto;

	public List getListaAtributosDinamicosDifunto() {
		return listaAtributosDinamicosDifunto;
	}

	public void setListaAtributosDinamicosDifunto(List listaAtributosDinamicosDifunto) {
		this.listaAtributosDinamicosDifunto = listaAtributosDinamicosDifunto;
	}

	private List listaAtributosDinamicosTipoSepultura;

	public List getListaAtributosDinamicosTipoSepultura() {
		return listaAtributosDinamicosTipoSepultura;
	}

	public void setListaAtributosDinamicosTipoSepultura(List listaAtributosDinamicosTipoSepultura) {
		this.listaAtributosDinamicosTipoSepultura = listaAtributosDinamicosTipoSepultura;
	}

	private List<AsocServicioOsp> listaServiciosOSPObligacionOSP;

	public List<AsocServicioOsp> getListaServiciosOSPObligacionOSP() {
		return listaServiciosOSPObligacionOSP;
	}

	public void setListaServiciosOSPObligacionOSP(List<AsocServicioOsp> listaServiciosOSPObligacionOSP) {
		this.listaServiciosOSPObligacionOSP = listaServiciosOSPObligacionOSP;
	}

	private List listaTiposParametroCementerio;

	public List getListaTiposParametroCementerio() {
		return listaTiposParametroCementerio;
	}

	public void setListaTiposParametroCementerio(List listaTiposParametroCementerio) {
		this.listaTiposParametroCementerio = listaTiposParametroCementerio;
	}

	private List listaTiposParametroAutomotor;

	public List getListaTiposParametroAutomotor() {
		return listaTiposParametroAutomotor;
	}

	public void setListaTiposParametroAutomotor(List listaTiposParametroAutomotor) {
		this.listaTiposParametroAutomotor = listaTiposParametroAutomotor;
	}

	private List listaTiposParametroVehiculo;

	public List getListaTiposParametroVehiculo() {
		return listaTiposParametroVehiculo;
	}

	public void setListaTiposParametroVehiculo(List listaTiposParametroVehiculo) {
		this.listaTiposParametroVehiculo = listaTiposParametroVehiculo;
	}

	private List listaTiposParametroDeuda;

	public List getListaTiposParametroDeuda() {
		return listaTiposParametroDeuda;
	}

	public void setListaTiposParametroDeuda(List listaTiposParametroDeuda) {
		this.listaTiposParametroDeuda = listaTiposParametroDeuda;
	}

	private List listaLogsAuditoriaDocEspOsp;

	public List getListaLogsAuditoriaDocEspOsp() {
		return listaLogsAuditoriaDocEspOsp;
	}

	public void setListaLogsAuditoriaDocEspOsp(List listaLogsAuditoriaDocEspOsp) {
		this.listaLogsAuditoriaDocEspOsp = listaLogsAuditoriaDocEspOsp;
	}

	private List listaLogsAuditoriaDocEspTgi;

	public List getListaLogsAuditoriaDocEspTgi() {
		return listaLogsAuditoriaDocEspTgi;
	}

	public void setListaLogsAuditoriaDocEspTgi(List listaLogsAuditoriaDocEspTgi) {
		this.listaLogsAuditoriaDocEspTgi = listaLogsAuditoriaDocEspTgi;
	}

	private List listaLogsAuditoriaDocEspShps;

	public List getListaLogsAuditoriaDocEspShps() {
		return listaLogsAuditoriaDocEspShps;
	}

	public void setListaLogsAuditoriaDocEspShps(List listaLogsAuditoriaDocEspShps) {
		this.listaLogsAuditoriaDocEspShps = listaLogsAuditoriaDocEspShps;
	}

	private List listaServicioOSP;

	public List getListaServicioOSP() {
		return listaServicioOSP;
	}

	public void setListaServicioOSP(List listaServicioOSP) {
		this.listaServicioOSP = listaServicioOSP;
	}

	private List listaLineaTipoParametroConstante;

	public List getListaLineaTipoParametroConstante() {
		return listaLineaTipoParametroConstante;
	}

	public void setListaLineaTipoParametroConstante(List listaLineaTipoParametroConstante) {
		this.listaLineaTipoParametroConstante = listaLineaTipoParametroConstante;
	}

	private List listaVariablesTipoParametroGrilla;

	public List getListaVariablesTipoParametroGrilla() {
		return listaVariablesTipoParametroGrilla;
	}

	public void setListaVariablesTipoParametroGrilla(List listaVariablesTipoParametroGrilla) {
		this.listaVariablesTipoParametroGrilla = listaVariablesTipoParametroGrilla;
	}

	private List listaVariablesTipoParametroGrillaFila;

	public List getListaVariablesTipoParametroGrillaFila() {
		return listaVariablesTipoParametroGrillaFila;
	}

	public void setListaVariablesTipoParametroGrillaFila(List listaVariablesTipoParametroGrillaFila) {
		this.listaVariablesTipoParametroGrillaFila = listaVariablesTipoParametroGrillaFila;
	}

	private List listaVariablesTipoParametroGrillaFilaValores;

	public List getListaVariablesTipoParametroGrillaFilaValores() {
		return listaVariablesTipoParametroGrillaFilaValores;
	}

	public void setListaVariablesTipoParametroGrillaFilaValores(List listaVariablesTipoParametroGrillaFilaValores) {
		this.listaVariablesTipoParametroGrillaFilaValores = listaVariablesTipoParametroGrillaFilaValores;
	}

	public List<Tasa> listaDocsGeneradoresDeuda;

	public List<Tasa> getListaDocsGeneradoresDeuda() {
		return listaDocsGeneradoresDeuda;
	}

	public void setListaDocsGeneradoresDeuda(List<Tasa> listaDocsGeneradoresDeuda) {
		this.listaDocsGeneradoresDeuda = listaDocsGeneradoresDeuda;
	}

	private Map<String, Modelo> mapaModelo = null;

	public Map<String, Modelo> getMapaModelo() {
		if(mapaModelo == null) {
			try {
				mapaModelo = new LinkedHashMap<String, Modelo>();

				FiltroModelo locFiltro = new FiltroModelo();

				List<Modelo> lista = this.getRemoteSystemDocumentoAutomotor().findListaModelo(locFiltro).getListaResultados();

				Collections.sort(lista, new Comparator<Modelo>() {
					@Override
					public int compare(Modelo o1, Modelo o2) {

						// para comparar sin acentos
						String modelo1 = Util.reemplazarAcentos(o1.getNombre());
						String modelo2 = Util.reemplazarAcentos(o2.getNombre());

						return modelo1.compareToIgnoreCase(modelo2);
					}
				});

				for(Modelo cadaModelo : lista) {
					mapaModelo.put(cadaModelo.getNombre() + " - " + cadaModelo.getMarca().getNombre(), cadaModelo);

				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaModelo;
	}

	private Map<String, TipoVehiculo> mapaTipoVehiculo = null;

	public Map<String, TipoVehiculo> getMapaTipoVehiculo() {
		if(mapaTipoVehiculo == null) {
			try {
				mapaTipoVehiculo = new LinkedHashMap<String, TipoVehiculo>();

				FiltroTipoVehiculo locFiltro = new FiltroTipoVehiculo();

				List<TipoVehiculo> lista = this.getRemoteSystemDocumentoAutomotor().findListaTipoVehiculo(locFiltro).getListaResultados();

				Collections.sort(lista, new Comparator<TipoVehiculo>() {
					@Override
					public int compare(TipoVehiculo o1, TipoVehiculo o2) {

						// para comparar sin acentos
						String tipo1 = Util.reemplazarAcentos(o1.getNombre());
						String tipo2 = Util.reemplazarAcentos(o2.getNombre());

						return tipo1.compareToIgnoreCase(tipo2);
					}
				});

				for(TipoVehiculo cadaTipo : lista) {
					mapaTipoVehiculo.put(cadaTipo.getNombre().toString(), cadaTipo);

				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoVehiculo;
	}

	private Map<String, Marca> mapaMarca = null;

	public Map<String, Marca> getMapaMarca() {
		if(mapaMarca == null) {
			try {
				mapaMarca = new LinkedHashMap<String, Marca>();

				FiltroMarca locFiltro = new FiltroMarca();

				List<Marca> lista = this.getRemoteSystemDocumentoAutomotor().findListaMarca(locFiltro).getListaResultados();

				Collections.sort(lista, new Comparator<Marca>() {
					@Override
					public int compare(Marca o1, Marca o2) {
						// para comparar sin acentos
						String marca1 = Util.reemplazarAcentos(o1.getNombre());
						String marca2 = Util.reemplazarAcentos(o2.getNombre());

						return marca1.compareToIgnoreCase(marca2);
					}
				});

				for(Marca cadaMarca : lista) {
					mapaMarca.put(cadaMarca.getNombre().toString(), cadaMarca);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaMarca;
	}

	private Map<String, Plan> mapaPlan;

	public void setMapaPlan(Map<String, Plan> mapaPlan) {
		this.mapaPlan = mapaPlan;
	}

	public Map<String, Plan> getMapaPlan() {
		return mapaPlan;
	}

}
