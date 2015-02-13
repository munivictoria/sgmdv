/*
 * Nav1.java
 *
 * Created on 22 de agosto de 2006, 11:10
 * Copyright Trascender SRL
 */

package muni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.accionSocial.recurso.persistent.SolicitudBeneficio;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.transients.CuentaCorriente;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.RegistroValuadoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.navegacion.Enlace;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class Nav1 extends AbstractPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
	}

	private Page page1 = new Page();

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page p) {
		this.page1 = p;
	}

	private Html html1 = new Html();

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html h) {
		this.html1 = h;
	}

	private Head head1 = new Head();

	public Head getHead1() {
		return head1;
	}

	public void setHead1(Head h) {
		this.head1 = h;
	}

	private Body body1 = new Body();

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body b) {
		this.body1 = b;
	}

	private Form form1 = new Form();

	public Form getForm1() {
		return form1;
	}

	public void setForm1(Form f) {
		this.form1 = f;
	}

	private Tree trRecursos = new Tree();

	public Tree getTrRecursos() {
		return trRecursos;
	}

	public void setTrRecursos(Tree t) {
		this.trRecursos = t;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Link link1 = new Link();

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	// </editor-fold>

	@SuppressWarnings("rawtypes")
	HashMap procesos = new HashMap();
	@SuppressWarnings("rawtypes")
	HashMap metaGrupos = new HashMap();
	// CAMBIAR: aumentar el numero a medida que se agreguen modulos
	@SuppressWarnings("rawtypes")
	List[] listasChildrenMetaGrupos = new List[10];// Mines...cambiaste de 6 a 7
	TreeNode[] nodosMetaGrupos = new TreeNode[10];

	// private final String IMG_GRUPO = "/resources/imagenes/arboles/grupo.png";
	private final String IMG_REC = "/resources/imagenes/arboles/recurso.png";
	private final String IMG_MAIN = "/resources/imagenes/arboles/blog_home.png";

	private final String FRM = "Recursos";
	private final String CAT = "Catastro";
	private final String HAB = "Habilitaciones";
	private final String COM = "Compras y Contrataciones";
	private final String DEP = "Depositos";
	private final String SAI = "SAIC";
	private final String CAJ = "Caja";
	private final String EXP = "Expedientes";
	private final String ACC = "Accion Social";
	private final String MGC = "Gestion Ciudadana";// Módulo Gestion Ciudadana

	Enlace[] linksRecursos = {
			// Framework
			new Enlace(Area.serialVersionUID, "/faces/framework/ABMArea/AdminArea.jsp"),
			new Enlace(Calendario.serialVersionUID, "/faces/framework/ABMCalendarioMunicipal/AdminCalendarioMunicipal.jsp"),
			new Enlace(CodigoCiiu.serialVersionUID, "/faces/framework/ABMCodigoCiiu/AdminCodigoCiiu.jsp"),
			new Enlace(ConfiguracionRecurso.serialVersionUID, "/faces/framework/ABMConfiguracionRecurso/AdminConfiguracionRecurso.jsp"),
			new Enlace(Contrato.serialVersionUID, "/faces/framework/ABMContrato/AdminContrato.jsp"),
			new Enlace(DiaFeriado.serialVersionUID, "/faces/framework/ABMDiaFeriado/AdminDiaFeriado.jsp"),
			new Enlace(Localidad.serialVersionUID, "/faces/framework/ABMLocalidad/AdminLocalidad.jsp"),
			new Enlace(Municipalidad.serialVersionUID, "/faces/framework/ABMMunicipalidad/AdminMunicipalidad.jsp"),
			new Enlace(Pais.serialVersionUID, "/faces/framework/ABMPais/AdminPais.jsp"),
			new Enlace(Provincia.serialVersionUID, "/faces/framework/ABMProvincia/AdminProvincia.jsp"),
			new Enlace(PersonaFisica.serialVersionUID, "/faces/framework/ABMPersonaFisica/AdminPersonaFisica.jsp"),
			new Enlace(PersonaJuridica.serialVersionUID, "/faces/framework/ABMPersonaJuridica/AdminPersonaJuridica.jsp"),
			new Enlace(Rol.serialVersionUID, "/faces/framework/ABMRol/AdminRol.jsp"),
			new Enlace(Secretaria.serialVersionUID, "/faces/framework/ABMSecretaria/AdminSecretaria.jsp"),
			new Enlace(Usuario.serialVersionUID, "/faces/framework/ABMUsuario/AdminUsuario.jsp"),
			new Enlace(DigestoMunicipal.serialVersionUID, "/faces/framework/ABMDigestoMunicipal/AdminDigestoMunicipal.jsp"),
			new Enlace(PlantillaAtributoDinamico.serialVersionUID, "/faces/framework/ABMAtributoDinamico/AdminAtributoDinamico.jsp"),
			new Enlace(ProcesoDB.serialVersionUID, "/faces/framework/ABMProcesoDB/AdminProcesoDB.jsp"),

			// Catastro
			new Enlace(Calle.serialVersionUID, "/faces/catastro/ABMCalle/AdminCalle.jsp"),
			new Enlace(Categoria.serialVersionUID, "/faces/catastro/ABMCategoria/AdminCategoria.jsp"),
			new Enlace(CoeficienteDepreciacion.serialVersionUID, "/faces/catastro/ABMCoeficienteDepreciacion/AdminCoeficienteDepreciacion.jsp"),
			new Enlace(Cuadra.serialVersionUID, "/faces/catastro/ABMCuadra/AdminCuadra.jsp"),
			// new Enlace(DeclaracionJurada.serialVersionUID, "/faces/catastro/ABMDeclaracionJurada/AdminDeclaracionJurada.jsp"),
			new Enlace(Manzana.serialVersionUID, "/faces/catastro/ABMManzana/AdminManzana.jsp"),
			new Enlace(Parcela.serialVersionUID, "/faces/catastro/ABMParcela/AdminParcela.jsp"),
			new Enlace(SubParcela.serialVersionUID, "/faces/catastro/ABMSubparcela/AdminSubparcela.jsp"),
			new Enlace(RegistroPropietario.serialVersionUID, "/faces/catastro/ABMRegistroPropietario/AdminRegistroPropietario.jsp"),
			new Enlace(Servicio.serialVersionUID, "/faces/catastro/ABMServicio/AdminServicio.jsp"),
			new Enlace(TipoCalle.serialVersionUID, "/faces/catastro/ABMTipoCalle/AdminTipoCalle.jsp"),
			new Enlace(TipoConstruccion.serialVersionUID, "/faces/catastro/ABMTipoConstruccion/AdminTipoConstruccion.jsp"),
			new Enlace(ValorBasicoMejora.serialVersionUID, "/faces/catastro/ABMValorBasicoMejora/AdminValorBasicoMejora.jsp"),
			new Enlace(Zona.serialVersionUID, "/faces/catastro/ABMZona/AdminZona.jsp"),
			new Enlace(Zonificacion.serialVersionUID, "/faces/catastro/ABMZonificacion/AdminZonificacion.jsp"),

			// Habilitaciones: Comunes
			new Enlace(PlantillaObligacion.serialVersionUID, "/faces/habilitaciones/ABMPlantillaObligacion/AdminPlantillaObligacion.jsp"),
			new Enlace(PlantillaDocumentoTasaMenor.serialVersionUID, "/faces/habilitaciones/ABMPlantillaObligacionTasaMenor/AdminPlantillaObligacionTasaMenor.jsp"),
			new Enlace(DocumentoTasaMenor.serialVersionUID, "/faces/habilitaciones/grpTasaMenor/ABMDocEspTasaMenor/AdminDocEspTasaMenor.jsp"),
			new Enlace(PermisoHab.serialVersionUID, "/faces/habilitaciones/ABMFirmaPendiente/AdminPermisoSinFirma.jsp"),

			// Habilitaciones: Bromatologia
			new Enlace(TransporteVehicular.serialVersionUID, "/faces/habilitaciones/grpBromatologia/ABMTransporteVehicular/AdminTransporteVehicular.jsp"),

			// Habilitaciones: OSP
			new Enlace(DocumentoOSP.serialVersionUID, "/faces/habilitaciones/grpOSP/ABMDocEspOSP/AdminDocEspOSP.jsp"),
			new Enlace(ServicioOSP.serialVersionUID, "/faces/habilitaciones/grpOSP/ABMServicioOSP/AdminServicioOSP.jsp"),
			new Enlace(ConsumoBasico.serialVersionUID, "/faces/habilitaciones/grpOSP/ABMConsumoBasico/AdminConsumoBasico.jsp"),

			// Habilitaciones: PFO
			new Enlace(DocumentoPlanObra.serialVersionUID, "/faces/habilitaciones/grpPlanFinanciacionObra/ABMDocEspPFO/AdminDocEspPFO.jsp"),
			new Enlace(Obra.serialVersionUID, "/faces/habilitaciones/grpPlanFinanciacionObra/ABMObra/AdminObra.jsp"),
			new Enlace(PlanCuentaObra.serialVersionUID, "/faces/habilitaciones/grpPlanFinanciacionObra/ABMPlanCuentaObra/AdminPlanCuentaObra.jsp"),

			// Habilitaciones: SHPS
			new Enlace(ClausuraSHPS.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMClausuraSinFirma/AdminClausuraSinFirma.jsp"),
			new Enlace(DocumentoSHPS.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMDocEspSHPS/AdminDocEspSHPS.jsp"),
			new Enlace(Inspector.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMInspector/AdminInspector.jsp"),
			new Enlace(LibretaSanitaria.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMLibretaSanitaria/AdminLibretaSanitaria.jsp"),
			new Enlace(LocalComercial.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMLocalComercial/AdminLocalComercial.jsp"),
			new Enlace(Rubro.serialVersionUID, "/faces/habilitaciones/grpSHPS/ABMRegAlicuotaSHPS/AdminRegAlicuotaSHPS.jsp"),

			// Habilitaciones: TGI
			new Enlace(DocumentoTGI.serialVersionUID, "/faces/habilitaciones/grpTGI/ABMDocEspTGI/AdminDocEspTGI.jsp"),
			
			//Habilitaciones: Arrendamiento
			
			new Enlace(DocumentoArrendamiento.serialVersionUID, "/faces/habilitaciones/grpArrendamiento/ABMDocEspArrendamiento/AdminDocEspArrendamiento.jsp"),

			// Habilitaciones: Automotor
			new Enlace(Vehiculo.serialVersionUID, "/faces/habilitaciones/grpAutomotor/ABMVehiculo/AdminVehiculo.jsp"),
			new Enlace(Modelo.serialVersionUID, "/faces/habilitaciones/grpAutomotor/ABMModelo/AdminModelo.jsp"),
			new Enlace(Marca.serialVersionUID, "/faces/habilitaciones/grpAutomotor/ABMMarca/AdminMarca.jsp"),
			new Enlace(TipoVehiculo.serialVersionUID, "faces/habilitaciones/grpAutomotor/ABMTipoVehiculo/AdminTipoVehiculo.jsp"),
			new Enlace(DocumentoAutomotor.serialVersionUID, "faces/habilitaciones/grpAutomotor/ABMDocEspAutomotor/AdminDocEspAutomotor.jsp"),
			new Enlace(ValuacionAcara.serialVersionUID, "faces/habilitaciones/grpAutomotor/ABMValuacionAcara/AdminValuacionAcara.jsp"),

			// Habilitaciones: TipoTasa y TipoParametro
			new Enlace(TipoTasa.serialVersionUID, "/faces/habilitaciones/ABMTipoTasa/AdminTipoTasa.jsp"),
			new Enlace(TipoParametroConstante.serialVersionUID, "/faces/habilitaciones/grpTipoParametro/ABMTipoParametroConstante/AdminTipoParametroConstante.jsp"),
			new Enlace(TipoParametroGrilla.serialVersionUID, "/faces/habilitaciones/grpTipoParametro/ABMTipoParametroGrilla/AdminTipoParametroGrilla.jsp"),
			new Enlace(TipoParametroGrupoZona.serialVersionUID, "/faces/habilitaciones/grpTipoParametro/ABMTipoParametroGrupoZona/AdminTipoParametroGrupoZona.jsp"),
			new Enlace(Plan.serialVersionUID, "/faces/habilitaciones/grpTipoParametro/ABMPlan/AdminPlan.jsp"),

			// Habilitaciones: TipoTasa
			new Enlace(TipoTasa.serialVersionUID, "/faces/habilitaciones/ABMTipoTasa/AdminTipoTasa.jsp"),

			// Habilitaciones: Cementerio
			new Enlace(DocumentoCementerio.serialVersionUID, "/faces/habilitaciones/grpCementerio/ABMDocEspCementerio/AdminDocEspCementerio.jsp"),
			new Enlace(ParcelaCementerio.serialVersionUID, "/faces/habilitaciones/grpCementerio/ABMParcelaCementerio/AdminParcelaCementerio.jsp"),
			new Enlace(TipoSepultura.serialVersionUID, "/faces/habilitaciones/grpCementerio/ABMTipoSepultura/AdminTipoSepultura.jsp"),

			// SAIC
			new Enlace(DeclaracionJuradaSHPS.serialVersionUID, "/faces/saic/grpSHPS/ABMDDJJSHPS/AdminDDJJSHPS.jsp"),
			new Enlace(ValorMedidor.serialVersionUID, "/faces/saic/grpOSP/ABMValorMedidor/AdminValorMedidor.jsp"),
			new Enlace(LiquidacionTasa.codigoTGI, "/faces/saic/grpTGI/ABMLiquidacionTGI/AdminLiquidacionTGI.jsp"),
			new Enlace(LiquidacionTasa.codigoOSP, "/faces/saic/grpOSP/ABMLiquidacionOSP/AdminLiquidacionOSP.jsp"),
			new Enlace(LiquidacionTasa.codigoSHPS, "/faces/saic/grpSHPS/ABMLiquidacionSHPS/AdminLiquidacionSHPS.jsp"),
			new Enlace(LiquidacionTasa.codigoArrendamiento, "/faces/saic/grpArrendamiento/ABMLiquidacionArrendamiento/AdminLiquidacionArrendamiento.jsp"),
			new Enlace(LiquidacionTasa.codigoPFO, "/faces/saic/grpPFO/ABMLiquidacionPFO/AdminLiquidacionPFO.jsp"),
			new Enlace(LiquidacionTasa.codigoAutomotor, "/faces/saic/grpAutomotor/ABMLiquidacionAutomotor/AdminLiquidacionAutomotor.jsp"),
			new Enlace(LiquidacionTasa.codigoCementerio, "/faces/saic/grpCementerio/ABMLiquidacionCementerio/AdminLiquidacionCementerio.jsp"),
			new Enlace(LiquidacionTasa.codigoTasaMenor, "/faces/saic/grpTasaMenor/ABMLiquidacionTasaMenor/AdminLiquidacionTasaMenor.jsp"),
			new Enlace(LiquidacionTasa.codigoEstadoCuenta, "/faces/saic/ABMEstadoCuenta/AdminEstadoCuenta.jsp"),
			new Enlace(RegistroValuadoTasaMenor.serialVersionUID, "/faces/saic/grpTasaMenor/ABMRegistroValuadoTasaMenor/AdminRegistroValuadoTasaMenor.jsp"),
			new Enlace(LiquidacionTasa.codigoTasasUnificadas, "/faces/saic/ImprimirLiquidaciones/AdminImprimirLiquidaciones.jsp"),
			new Enlace(LogLiquidacion.serialVersionUID, "/faces/saic/LogLiquidaciones/AdminLogLiquidaciones.jsp"),
			new Enlace(ExencionRegistroDeuda.serialVersionUID, "/faces/saic/ABMExencionRegistroDeuda/AdminExencionRegistroDeuda.jsp"),
			new Enlace(ExencionObligacion.serialVersionUID, "/faces/saic/ABMExencionObligacion/AdminExencionObligacion.jsp"),
			new Enlace(AuditoriaTributaria.serialVersionUID, "/faces/saic/ABMAuditoriaTributaria/AdminAuditoriaTributaria.jsp"),
			new Enlace(CobroExterno.serialVersionUID, "/faces/saic/ABMCobroExterno/AdminCobroExterno.jsp"),

			// Excepciones
			new Enlace(DocumentoRefinanciacion.serialVersionUID, "/faces/excepciones/ABMRefinanciacion/AdminRefinanciacion.jsp"),

			// Expedientes
			new Enlace(Expediente.serialVersionUID, "/faces/expedientes/ABMExpediente/AdminExpediente.jsp"),
			new Enlace(FaseCatalogo.serialVersionUID, "/faces/expedientes/ABMFaseCatalogo/AdminFaseCatalogo.jsp"),
			new Enlace(FaseProcedimiento.serialVersionUID, "/faces/expedientes/ABMFaseProcedimiento/AdminFaseProcedimiento.jsp"),
			new Enlace(Procedimiento.serialVersionUID, "/faces/expedientes/ABMProcedimiento/AdminProcedimiento.jsp"),
			new Enlace(TramiteCatalogo.serialVersionUID, "/faces/expedientes/ABMTramiteCatalogo/AdminTramiteCatalogo.jsp"),
			new Enlace(TramiteProcedimiento.serialVersionUID, "/faces/expedientes/ABMTramiteProcedimiento/AdminTramiteProcedimiento.jsp"),
			new Enlace(DocumentoCatalogo.serialVersionUID, "/faces/expedientes/ABMDocumentoCatalogo/AdminDocumentoCatalogo.jsp"),
			new Enlace(EstadoTramite.serialVersionUID, "/faces/expedientes/ABMEstadoTramite/AdminEstadoTramite.jsp"),

			// Compras
			new Enlace(SolicitudSuministro.serialVersionUID, "/faces/compras/ABMSolicitudSuministro/AdminSolicitudSuministro.jsp"),
			new Enlace(AutorizacionSolicitudSuministro.serialVersionUID, "/faces/compras/ABMAutorizacionSolicitudSuministro/AdminAutorizacionSolicitudSuministro.jsp"),
			new Enlace(ActaApertura.serialVersionUID, "/faces/compras/ABMActaApertura/AdminActaApertura.jsp"),
			new Enlace(Proveedor.serialVersionUID, "/faces/compras/ABMProveedor/AdminProveedor.jsp"),
			new Enlace(Bien.serialVersionUID, "/faces/compras/ABMBien/AdminBien.jsp"),
			new Enlace(Unidad.serialVersionUID, "/faces/compras/ABMUnidad/AdminUnidad.jsp"),
			// new Enlace(CondicionCompra.serialVersionUID, "/faces/compras/ABMCondicionCompra/AdminCondicionCompra.jsp"),
			new Enlace(OrdenCompra.serialVersionUID, "/faces/compras/ABMOrdenCompra/AdminOrdenCompra.jsp"),
			new Enlace(UsuarioAutorizadorOrdenCompra.serialVersionUID, "/faces/compras/ABMUsuarioFirmante/AdminUsuarioFirmante.jsp"),
			new Enlace(FacturaContrato.serialVersionUID, "/faces/compras/ABMFacturaContrato/AdminFacturaContrato.jsp"),
			new Enlace(FacturaProveedor.serialVersionUID, "/faces/compras/ABMFacturaProveedor/AdminFacturaProveedor.jsp"),
			new Enlace(FacturaSubsidio.serialVersionUID, "/faces/compras/ABMFacturaSubsidio/AdminFacturaSubsidio.jsp"),
			new Enlace(FacturaServicio.serialVersionUID, "/faces/compras/ABMFacturaServicio/AdminFacturaServicio.jsp"),
			new Enlace(Contratacion.serialVersionUID, "/faces/compras/ABMLicitacion/AdminLicitacion.jsp"),
			new Enlace(OfertaContratacion.serialVersionUID, "/faces/compras/ABMOfertaLicitacion/AdminOfertaLicitacion.jsp"),
			new Enlace(CuentaCorriente.serialVersionUID, "/faces/compras/EstadoCuentaCorriente/AdminEstadoCuentaCorriente.jsp"),
			new Enlace(EstadoSolicitudSuministro.serialVersionUID, "/faces/compras/ABMEstadoSolicitudSuministro/AdminEstadoSolicitudSuministro.jsp"),
			new Enlace(TipoBien.serialVersionUID, "/faces/compras/ABMTipoBien/AdminTipoBien.jsp"),
			new Enlace(LiquidacionCompra.serialVersionUID, "/faces/compras/ABMLiquidacionCompra/AdminLiquidacionCompra.jsp"),

			// Inventario
			new Enlace(Deposito.serialVersionUID, "/faces/inventario/ABMDeposito/AdminDeposito.jsp"),
			new Enlace(Stock.serialVersionUID, "/faces/inventario/ABMStock/AdminStock.jsp"),
			new Enlace(MovimientoDeMercaderia.serialVersionUID, "/faces/inventario/ABMMovimientoDeMercaderia/AdminMovimientoDeMercaderia.jsp"),
			new Enlace(Articulo.serialVersionUID, "/faces/inventario/ABMArticulo/AdminArticulo.jsp"),

			// Caja
			new Enlace(ConceptoIngresoVario.serialVersionUID, "/faces/comunes/ABMConceptoIngresoVario/AdminConceptoIngresoVario.jsp"),
			new Enlace(IngresoVario.serialVersionUID, "/faces/comunes/ABMIngresoVario/AdminIngresoVario.jsp"),

			// Acción Social
			new Enlace(Beneficio.serialVersionUID, "/faces/accionsocial/ABMBeneficio/AdminBeneficio.jsp"),
			new Enlace(ObraSocial.serialVersionUID, "/faces/accionsocial/ABMObraSocial/AdminObraSocial.jsp"),
			new Enlace(Beneficiario.serialVersionUID, "/faces/accionsocial/ABMBeneficiario/AdminBeneficiario.jsp"),
			new Enlace(FichaSocial.serialVersionUID, "/faces/accionsocial/ABMFichaSocial/AdminFichaSocial.jsp"),
			new Enlace(SolicitudBeneficio.serialVersionUID, "/faces/accionsocial/ABMSolicitudBeneficio/AdminSolicitudBeneficio.jsp"),};

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public Nav1() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationCajaBean getCommunicationCajaBean() {
		return (CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationComprasBean getCommunicationComprasBean() {
		return (CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationSAICBean getCommunicationSAICBean() {
		return (CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ámbito.
	 * </p>
	 */
	protected ComunicationCatastroBean getComunicationCatastroBean() {
		return (ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	protected CommunicationTransitoBean getCommunicationTransito() {
		return (CommunicationTransitoBean) getBean("CommunicationTransitoBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected ComunicationBean getComunicationBean() {
		return (ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected RequestBean1 getRequestBean1() {
		return (RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected SessionBean1 getSessionBean1() {
		return (SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();

		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("Nav1 Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		ArrayList grupos = null;
		Map permisos = null;
		int p = 0;

		procesos.put("FRM", FRM);
		procesos.put("CAT", CAT);
		procesos.put("HAB", HAB);
		procesos.put("COM", COM);
		procesos.put("DEP", DEP);
		procesos.put("SAI", SAI);
		procesos.put("CAJ", CAJ);
		procesos.put("EXP", EXP);
		procesos.put("ACC", ACC);
		procesos.put("MGC", MGC);

		metaGrupos.put("FRM", new Integer(p++));
		metaGrupos.put("CAT", new Integer(p++));
		metaGrupos.put("HAB", new Integer(p++));
		metaGrupos.put("COM", new Integer(p++));
		metaGrupos.put("DEP", new Integer(p++));
		metaGrupos.put("SAI", new Integer(p++));
		metaGrupos.put("CAJ", new Integer(p++));
		metaGrupos.put("EXP", new Integer(p++));
		metaGrupos.put("ACC", new Integer(p++));
		metaGrupos.put("MGC", new Integer(p++));

		for(p = 0; p < procesos.size(); p++)
			listasChildrenMetaGrupos[p] = new ArrayList();
		for(p = 0; p < procesos.size(); p++)
			nodosMetaGrupos[p] = new TreeNode();

		p = 0;
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(FRM));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(CAT));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(HAB));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(COM));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(DEP));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(SAI));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(CAJ));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(EXP));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(ACC));
		nodosMetaGrupos[p++].setId(Util.getEnumNameFromString(MGC));

		p = 0;
		nodosMetaGrupos[p++].setText(FRM);
		nodosMetaGrupos[p++].setText(CAT);
		nodosMetaGrupos[p++].setText(HAB);
		nodosMetaGrupos[p++].setText(COM);
		nodosMetaGrupos[p++].setText(DEP);
		nodosMetaGrupos[p++].setText(SAI);
		nodosMetaGrupos[p++].setText(CAJ);
		nodosMetaGrupos[p++].setText(EXP);
		nodosMetaGrupos[p++].setText(ACC);
		nodosMetaGrupos[p++].setText(MGC);

		try {
			// Permisos del usuario logueado.
			permisos = this.getSessionBean1().getUsuario().getPermisos();

			// Todos los grupos/recursos
			this.getComunicationBean().getRemoteSystemUsuario().setLlave(this.getSessionBean1().getLlave());
			grupos = new ArrayList(this.getComunicationBean().getRemoteSystemUsuario().getListaPermisos());

			this.setTrRecursos(this.armarArbolEstatico(grupos, permisos));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setTrRecursos(null);
			error(ex.getMessage());
		}
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	public void prerender() {

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 * 
	 */
	public void destroy() {
	}

	public String getLinkRecurso(long serialVersionUIDRecurso) {

		String link = null;

		for(int i = 0; i < this.linksRecursos.length; i++) {
			Enlace enlace = linksRecursos[i];
			if(enlace.getIdRecurso() == serialVersionUIDRecurso)
				link = enlace.getLink();
		}

		return link;
	}

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	private Tree armarArbolEstatico(ArrayList grupos, Map permisos) throws Exception {

		Collections.sort(grupos, Collections.reverseOrder());

		String nomUser = this.getSessionBean1().getUsuario().getUser();
		String superUsuario = this.getApplicationBean1().getSuperUsuario();
		if(!nomUser.equals(superUsuario)) {
			if(permisos == null || permisos.size() <= 0) {
				throw new Exception("El Usuario no Posee Permisos.");
			}
		}

		Tree tree = new Tree();

		{
			TreeNode tnTemp = new TreeNode();
			tnTemp.setId("ppal");
			tnTemp.setText("Principal");
			tnTemp.setTarget("main");
			tnTemp.setUrl("/faces/Main.jsp");
			tnTemp.setImageURL(IMG_MAIN);

			tree.getChildren().add(tnTemp);
		}

		// for (int i = 0; i < procesos.size(); i++) {
		// //metaGrupos.put(procesos.keySet().iterator().next(), new Integer(i));
		//
		// this.nodosMetaGrupos[i].setId(procesos.get())
		// }

		List treeChildren = tree.getChildren();

		TreeNode tnProceso = null;
		List tnProcesoChildren = null;

		TreeNode tnGrupo = null;
		List tnGrupoChildren = null;

		TreeNode tnRecurso = null;

		Permiso permiso = null;

		// GRUPOS
		for(int i = 0; i < grupos.size(); i++) {
			Grupo grupo = (Grupo) grupos.get(i);

			tnGrupo = new TreeNode();
			tnGrupo.setId("g" + i);// grupo.getNombre().replace(" ", "").replace("/", ""));
			tnGrupo.setText(grupo.getNombre().substring(grupo.getNombre().indexOf('|') + 1));
			// tnGrupo.setImageURL(IMG_GRUPO);

			tnGrupoChildren = new ArrayList();
			ArrayList recursosTodos = grupo.getListaRecursos();

			int num = 0;
			for(int k = 0; k < recursosTodos.size(); k++) {
				Recurso recurso = (Recurso) recursosTodos.get(k);
				Object p = permisos.get(new Long(recurso.getIdRecurso()));

				if(p != null)
					permiso = (Permiso) p;
				else
					permiso = null;
				if(nomUser.equals(superUsuario))
					permiso = new Permiso();

				String url = this.getLinkRecurso(recurso.getIdRecurso());
				if(permiso != null && url != null) {
					tnRecurso = new TreeNode();
					tnRecurso.setId("r" + new Long(i).toString() + new Long(k).toString());
					tnRecurso.setText(recurso.getNombre());

					// lo siguiente estaba comentado
					tnRecurso.setTarget("main");
					tnRecurso.setUrl(url);
					tnRecurso.setImageURL(IMG_REC);
					// comentado hasta aca

					// lo siguiente no estaba comentado
					// HtmlActionParameter actionParam = new HtmlActionParameter();
					// actionParam.setName("paginaNavegacion");
					// actionParam.setValue(url.substring(7, (url.length())));
					// tnRecurso.getChildren().add(actionParam);
					//
					// HtmlAjaxCommandLink cl = new HtmlAjaxCommandLink();
					// cl.setId("id" + num);
					// num++;
					// cl.setValue(recurso.getNombre());
					// cl.setReRender("main");
					// cl.getChildren().add(actionParam);
					// cl.addActionListener(getActionListener("#{SessionBean1.actualizarPagina(evento)}"));
					// tnRecurso.getFacets().put(TreeNode.CONTENT_FACET_KEY, cl);
					// no comentado hasta aca

					// Map<String,UIComponent> mapaComponentes = tnRecurso.getFacets();
					// for (String llave : mapaComponentes.keySet()){
					// System.out.println(llave + ": " + mapaComponentes.get(llave));
					// }
					//
					tnGrupoChildren.add(tnRecurso);
				}
			}
			if(tnGrupoChildren.size() > 0) {
				tnGrupo.getChildren().addAll(tnGrupoChildren);
				agregarGrupoAMetaGrupo(grupo.getNombre().substring(0, 3), tnGrupo);
				// treeChildren.add(tnGrupo);
			}

		}

		for(int ii = 0; ii < this.procesos.size(); ii++) {
			if(!this.listasChildrenMetaGrupos[ii].isEmpty()) {
				this.nodosMetaGrupos[ii].getChildren().addAll(listasChildrenMetaGrupos[ii]);
				treeChildren.add(this.nodosMetaGrupos[ii]);

			}
		}

		return tree;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void agregarGrupoAMetaGrupo(String idMetaGrupo, TreeNode grupo) {
		int posicion = new Integer(this.metaGrupos.get(idMetaGrupo).toString()).intValue();

		boolean locGrupoExencionEncontrado = false;
		int i = 0;
		while(i < this.listasChildrenMetaGrupos.length && !locGrupoExencionEncontrado) {
			ArrayList locListaChildren = (ArrayList) this.listasChildrenMetaGrupos[i];
			int j = 0;
			while(j < locListaChildren.size() && !locGrupoExencionEncontrado) {
				TreeNode locTreeNode = (TreeNode) locListaChildren.get(j);
				if(locTreeNode.getText().equals(grupo.getText())) {
					grupo.getChildren().addAll(locTreeNode.getChildren());
					this.listasChildrenMetaGrupos[i].remove(locTreeNode);
					this.listasChildrenMetaGrupos[i].add(grupo);
					locGrupoExencionEncontrado = true;
				}
				j++;
			}
			i++;
		}

		if(!locGrupoExencionEncontrado) {
			this.listasChildrenMetaGrupos[posicion].add(grupo);
		}
	}

	@SuppressWarnings({"unused", "rawtypes"})
	private MethodExpressionActionListener getActionListener(String pValor) {
		System.out.println("GETACTIONLISTENER");
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);
		return listener;
	}
}