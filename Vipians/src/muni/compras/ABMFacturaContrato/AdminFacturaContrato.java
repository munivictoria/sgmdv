/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.compras.ABMFacturaContrato;

import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import muni.compras.ABMFacturaSubsidio.ABMFacturaSubsidio;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroFacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminFacturaContrato extends AdminPageBean {

	private Button btnLimpiarProveedor = new Button();
	private Button btnLimpiarContrato = new Button();
	private Button btnSeleccionarContrato = new Button();
	private Button btnSeleccionarProveedor = new Button();
	private Button btnImprimirReporte = new Button();

	private StaticText stFechaEmision = new StaticText();
	private StaticText stProveedor = new StaticText();
	private StaticText stEstado = new StaticText();
	private StaticText stTotal = new StaticText();
	private StaticText stTotal2 = new StaticText();
	private StaticText stTipoFactura = new StaticText();
	private StaticText stSeparador5 = new StaticText();

	private TableColumn tcFechaEmision = new TableColumn();
	private TableColumn tcProveedor = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private TableColumn tcTipoFactura = new TableColumn();

	private Label lblFechaDesde = new Label();
	private Label lblContrato = new Label();
	private Label lblEstado = new Label();
	private Label lblProveedor = new Label();
	private Label lblTotal = new Label();
	private Label lblFechaHasta = new Label();

	private TextField tfContrato = new TextField();
	private TextField tfFechaDesde = new TextField();
	private TextField tfProveedor = new TextField();
	private TextField tfFechaHasta = new TextField();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	private ObjectListDataProvider ldpFactura = new ObjectListDataProvider();

	private DropDown ddEstado = new DropDown();

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	private NumberConverter numberConverter1 = new NumberConverter();

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaContrato.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);
		
		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yyyy");
		dateTimeConverter1.setTimeStyle("full");
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStTotal2() {
		return stTotal2;
	}

	public void setStTotal2(StaticText stTotal2) {
		this.stTotal2 = stTotal2;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public StaticText getStTipoFactura() {
		return stTipoFactura;
	}

	public void setStTipoFactura(StaticText stTipoFactura) {
		this.stTipoFactura = stTipoFactura;
	}

	public TableColumn getTcTipoFactura() {
		return tcTipoFactura;
	}

	public void setTcTipoFactura(TableColumn tcTipoFactura) {
		this.tcTipoFactura = tcTipoFactura;
	}

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
	}

	public TableColumn getTcFechaEmision() {
		return tcFechaEmision;
	}

	public void setTcFechaEmision(TableColumn tcFechaEmision) {
		this.tcFechaEmision = tcFechaEmision;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Label getLblFechaHasta() {
		return lblFechaHasta;
	}

	public void setLblFechaHasta(Label lblFechaHasta) {
		this.lblFechaHasta = lblFechaHasta;
	}

	public Label getLblFechaDesde() {
		return lblFechaDesde;
	}

	public void setLblFechaDesde(Label lblFechaDesde) {
		this.lblFechaDesde = lblFechaDesde;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	public Button getBtnLimpiarContrato() {
		return btnLimpiarContrato;
	}

	public void setBtnLimpiarContrato(Button b) {
		this.btnLimpiarContrato = b;
	}

	public Button getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(Button b) {
		this.btnLimpiarProveedor = b;
	}

	public ObjectListDataProvider getLdpFactura() {
		return ldpFactura;
	}

	public void setLdpFactura(ObjectListDataProvider oldp) {
		this.ldpFactura = oldp;
	}

	public Label getLblContrato() {
		return lblContrato;
	}

	public void setLblContrato(Label lblCotrato) {
		this.lblContrato = lblCotrato;
	}

	public TextField getTfContrato() {
		return tfContrato;
	}

	public void setTfContrato(TextField tfContrato) {
		this.tfContrato = tfContrato;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tf) {
		this.tfFechaDesde = tf;
	}

	public Button getBtnSeleccionarContrato() {
		return btnSeleccionarContrato;
	}

	public void setBtnSeleccionarContrato(Button btnSeleccionarContrato) {
		this.btnSeleccionarContrato = btnSeleccionarContrato;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown dd) {
		this.ddEstado = dd;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddEstadoDefaultOptions = ssol;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tf) {
		this.tfProveedor = tf;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button b) {
		this.btnSeleccionarProveedor = b;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText st) {
		this.stTotal = st;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tf) {
		this.tfFechaHasta = tf;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	public AdminFacturaContrato() {
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaFacturaContrato();
	}

	public String btnAgregar_action() {
		return toAbm(new FacturaContratoModel().new AgregarController());
	}

	public String btnEliminar_action() {
		return toAbm(new FacturaContratoModel().new EliminarController());
	}

	public String btnModificar_action() {
		return toAbm(new FacturaContratoModel().new ModificarController());
	}

	public String btnConsultar_action() {
		return toAbm(new FacturaContratoModel().new ConsultarController());
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroFacturaContrato locFiltro = this.getFiltro();

		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), FacturaContrato.Estado.class));
		locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroFacturaContrato locFiltro = this.getFiltro();
		
		FacturaContrato auxFacturaContrato = null;
		double monto = 0.0;
		List lista;

		this.getTfProveedor().setText(locFiltro.getProveedor());
	    this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
	    this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
	    this.getTfContrato().setText(locFiltro.getContrato());     

		
		if (this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {
			lista = this.getListaDelCommunication();
			for (int i = 0; i < lista.size(); i++) {
				auxFacturaContrato = (FacturaContrato) lista.get(i);
				monto += auxFacturaContrato.getTotal().doubleValue();
			}
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroFacturaContrato locFiltro = this.getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setFechaDesde(null);
		locFiltro.setFechaHasta(null);
		locFiltro.setContrato(null);
		locFiltro.setProveedor(null);
		
		this.getDdEstado().setSelected(null);
		this.getTfFechaDesde().setText("");
		this.getTfFechaHasta().setText("");
		this.getTfProveedor().setText("");
		this.getTfContrato().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpFactura();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaFacturaCompras();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaFacturaCompras(lista);
	}

	public String btnAceptarOrdenCompra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoABM(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch (Exception ex) {
				log(this.getCasoNavegacion() + "_AceptarError:", ex);
				error(this.getNombrePagina() + " - Aceptar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if (rk != null) {
				retorno = "AceptarOrdenCompra";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	public String btnImprimirReporte_action() {
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	protected ABMFacturaSubsidio getcompras$ABMFacturaSubsidio$AgregarFacturaSubsidio() {
		return (ABMFacturaSubsidio) getBean("compras$ABMFacturaSubsidio$AgregarFacturaSubsidio");
	}

	public String btnSeleccionarContrato_action() {
		return navegarParaSeleccionar("AdminContrato");
	}

	public String btnLimpiarContrato_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getTfContrato());
			FiltroFacturaContrato locFiltro = this.getFiltro();
			locFiltro.setContrato(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getTfProveedor());
			FiltroFacturaContrato locFiltro = this.getFiltro();
			locFiltro.setProveedor(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		FacturaContrato locFacturaContrato = (FacturaContrato) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().setLlave(this.getSessionBean1().getLlave());
		locFacturaContrato = this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato()
				.getFacturaContratoPorId(locFacturaContrato.getIdFactura());
		return locFacturaContrato;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Facturas de Contrato";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminFacturaContrato";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().setLlave(this.getSessionBean1().getLlave());
		FiltroFacturaContrato locFiltro = getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().findListaFacturasContrato((FiltroFacturaContrato) pFiltro);

		double monto = 0.0;
		if (!locFiltro.getListaResultados().isEmpty()) {
			for (FacturaContrato cadaFactura : locFiltro.getListaResultados()) {
				monto += cadaFactura.getTotal().doubleValue();
			}
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		return locFiltro;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroFacturaContrato locFiltro = this.getFiltro();
		if (pObject instanceof Proveedor) {
			locFiltro.setProveedor((Proveedor) pObject);
		}
		if (pObject instanceof Contrato) {
			Contrato contrato = (Contrato) pObject;

			locFiltro.setContrato(contrato);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaContrato.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaContrato$AdminFacturaContrato}";
	}
}