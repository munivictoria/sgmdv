/*
 * AdminCuenta.java
 *
 * Created on 21 de noviembre de 2006, 11:19
 * Copyright Trascencer
 *
 */

package muni.compras.ABMCuenta;

import java.util.List;

import muni.CommunicationMesaEntradaBean;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.contabilidad.recurso.filtros.FiltroCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminCuenta extends AdminPageBean {

	private StaticText stAbreviatura = new StaticText();
	private StaticText stCodigoImputacion = new StaticText();
	private StaticText stNombre = new StaticText();
	private StaticText stTipoCuenta = new StaticText();
	private StaticText stPlanDeCuenta = new StaticText();

	private TableColumn tcAbreviatura = new TableColumn();
	private TableColumn tcCodigoImputacion = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcTipoCuenta = new TableColumn();
	private TableColumn tcPlanDeCuenta = new TableColumn();

	private Label lblAbreviatura = new Label();
	private Label lblNombre = new Label();
	private Label lblCodigoImputacion = new Label();

	private TextField tfAbreviatura = new TextField();
	private TextField tfNombre = new TextField();
	private TextField tfCodigoImputacion = new TextField();

	private ObjectListDataProvider ldpCuentas = new ObjectListDataProvider();

	public StaticText getStPlanDeCuenta() {
		return stPlanDeCuenta;
	}

	public void setStPlanDeCuenta(StaticText stPlanDeCuenta) {
		this.stPlanDeCuenta = stPlanDeCuenta;
	}

	public StaticText getStTipoCuenta() {
		return stTipoCuenta;
	}

	public void setStTipoCuenta(StaticText stTipoCuenta) {
		this.stTipoCuenta = stTipoCuenta;
	}

	public StaticText getStAbreviatura() {
		return stAbreviatura;
	}

	public void setStAbreviatura(StaticText stAbreviatura) {
		this.stAbreviatura = stAbreviatura;
	}

	public StaticText getStCodigoImputacion() {
		return stCodigoImputacion;
	}

	public void setStCodigoImputacion(StaticText stCodigoImputacion) {
		this.stCodigoImputacion = stCodigoImputacion;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTcPlanDeCuenta() {
		return tcPlanDeCuenta;
	}

	public void setTcPlanDeCuenta(TableColumn tcPlanDeCuenta) {
		this.tcPlanDeCuenta = tcPlanDeCuenta;
	}

	public TableColumn getTcTipoCuenta() {
		return tcTipoCuenta;
	}

	public void setTcTipoCuenta(TableColumn tcTipoCuenta) {
		this.tcTipoCuenta = tcTipoCuenta;
	}

	public TableColumn getTcAbreviatura() {
		return tcAbreviatura;
	}

	public void setTcAbreviatura(TableColumn tcAbreviatura) {
		this.tcAbreviatura = tcAbreviatura;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public TableColumn getTcCodigoImputacion() {
		return tcCodigoImputacion;
	}

	public void setTcCodigoImputacion(TableColumn tcCodigoImputacion) {
		this.tcCodigoImputacion = tcCodigoImputacion;
	}

	public Label getLblAbreviatura() {
		return lblAbreviatura;
	}

	public void setLblAbreviatura(Label lblAbreviatura) {
		this.lblAbreviatura = lblAbreviatura;
	}

	public Label getLblCodigoImputacion() {
		return lblCodigoImputacion;
	}

	public void setLblCodigoImputacion(Label lblCodigoImputacion) {
		this.lblCodigoImputacion = lblCodigoImputacion;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label l) {
		this.lblNombre = l;
	}

	public TextField getTfAbreviatura() {
		return tfAbreviatura;
	}

	public void setTfAbreviatura(TextField tfAbreviatura) {
		this.tfAbreviatura = tfAbreviatura;
	}

	public TextField getTfCodigoImputacion() {
		return tfCodigoImputacion;
	}

	public void setTfCodigoImputacion(TextField tfCodigoImputacion) {
		this.tfCodigoImputacion = tfCodigoImputacion;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	public AdminCuenta() {
	}

	public ObjectListDataProvider getLdpCuentas() {
		return ldpCuentas;
	}

	public void setLdpCuentas(ObjectListDataProvider oldp) {
		this.ldpCuentas = oldp;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCuenta locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setAbreviatura(this.getTextFieldValue(getTfAbreviatura()));
		locFiltro.setCodigoImputacion(this.getTextFieldValue(getTfCodigoImputacion()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCuenta locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfAbreviatura().setText(locFiltro.getAbreviatura());
		this.getTfCodigoImputacion().setText(locFiltro.getCodigoImputacion());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCuenta locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setAbreviatura(null);
		locFiltro.setCodigoImputacion(null);

		this.getTfNombre().setText("");
		this.getTfAbreviatura().setText("");
		this.getTfCodigoImputacion().setText("");
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationContabilidadBean().getListaCuenta();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationContabilidadBean().setListaCuenta(lista);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCuentas();
	}

	/**
	 * Mines: boton Consultar...
	 */
	// public String btnConsultar_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if (ultimo) {
	// RowKey rk = null;
	//
	// // APLICAR LOGICA AQUI...
	// try {
	//
	// rk = this.getSeleccionado();
	//
	// if (rk != null) {
	// int index = getNroFila(rk.toString());
	// // CAMBIAR: Utilizar el ListDataProvider adecuado.
	// Object obj = this.getLdpBienes().getObjects()[index];
	// this.getRequestBean1().setObjetoABM(obj);
	// this.setRowKeySeleccionado(this.getSeleccionado());
	// }
	//
	// } catch (Exception ex) {
	// log(CASO_NAVEGACION + "_ConsultarError:", ex);
	// error(NOMBRE_PAGINA + " - Consultar: " + ex.getMessage());
	// }
	//
	// this.guardarEstadoObjetosUsados();
	// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	//
	// this.guardarOrdenamiento();
	// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
	// this.getElementoPila().setPosicionGlobal(pos.longValue());
	//
	// if (rk != null) {
	// retorno = lnkConsultar;
	// }
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
		return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationContabilidadBean().getTablaCuenta();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Cuenta locCuenta = (Cuenta) pObject;
		this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().setLlave(this.getSessionBean1().getLlave());
		locCuenta = this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
		return locCuenta;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Cuentas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCuenta";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().findListaCuenta((FiltroCuenta) pFiltro);
	}

	public String btnAgregar_action() {
		return null;
	}

	public String btnModificar_action() {
		return null;
	}

	public String btnEliminar_action() {
		return null;
	}

	public String btnConsultar_action() {
		return null;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// CAMBIAR: Crear los if necesarios para cada posible objeto a
		// seleccionar.
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMCuenta$AdminCuenta}";
	}

	@Override
	public long getSerialVersionUID() {
		return Cuenta.serialVersionUID;
	}
}