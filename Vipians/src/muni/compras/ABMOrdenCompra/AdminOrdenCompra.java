/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.compras.ABMOrdenCompra;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.compras.recurso.filtros.FiltroOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminOrdenCompra extends AdminPageBean {

	protected Button btnImprimirReporte = new Button();
	protected HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();
	protected Button btnFirmarOrdenCompra = new Button();
	protected Button btnSeleccionarProveedor = new Button();
	protected Button btnFinalizarOrdenCompra = new Button();
	protected Button btnTransferirOrdenCompra = new Button();

	protected StaticText stFechaEmision = new StaticText();
	protected StaticText stProveedor = new StaticText();
	protected StaticText stEstado = new StaticText();
	protected StaticText stValorTotal = new StaticText();
	protected StaticText stFechaDesde = new StaticText();
	protected StaticText stFechaHasta = new StaticText();
	protected StaticText stNumero = new StaticText();
	protected StaticText stTotal = new StaticText();
	protected StaticText stSeparador5 = new StaticText();
	protected StaticText stSeparador6 = new StaticText();
	protected StaticText stNumeroSolicitud = new StaticText();
	protected StaticText stArea = new StaticText();
	protected StaticText stBienAsociado = new StaticText();
	protected StaticText stCantidad = new StaticText();
	protected StaticText stPrecio = new StaticText();

	protected Label lblFechaDesde = new Label();
	protected Label lblEstado = new Label();
	protected Label lblTotal = new Label();
	protected Label lblFechaHasta = new Label();
	protected Label lblProveedor = new Label();
	protected Label lblNumero = new Label();
	protected Label lblSecretaria = new Label();
	protected Label lblArea = new Label();

	protected TableColumn tcFechaEmision = new TableColumn();
	protected TableColumn tcProveedor = new TableColumn();
	protected TableColumn tcEstado = new TableColumn();
	protected TableColumn tcPrecio = new TableColumn();
	protected TableColumn tcNumero = new TableColumn();
	protected TableColumn tableColumn7 = new TableColumn();
	protected TableColumn tcNumeroSolicitud = new TableColumn();
	protected TableColumn tcArea = new TableColumn();
	protected TableColumn tcBienAsociado = new TableColumn();
	protected TableColumn tcCantidad = new TableColumn();

	protected TextField tfFechaDesde = new TextField();
	protected TextField tfProveedor = new TextField();
	protected TextField tfFechaHasta = new TextField();
	protected TextField tfCantidadBien = new TextField();
	protected TextField tfNumero = new TextField();

	protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	protected ObjectListDataProvider ldpOrdenCompra = new ObjectListDataProvider();
	protected DropDown ddEstado = new DropDown();
	protected SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	protected DropDown ddSecretaria = new DropDown();
	protected DropDown ddArea = new DropDown();
	protected SingleSelectOptionsList ddSecretariaOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	protected NumberConverter numberConverter1 = new NumberConverter();

	protected Label lblBien = new Label();
	protected TextField tfBien = new TextField();
	protected Button btnSeleccionarBien = new Button();
	protected HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();

	// ***GETTERS & SETTERS

	public Label getLblTotal() {
		return lblTotal;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public Label getLblBien() {
		return lblBien;
	}

	public void setLblBien(Label lblBien) {
		this.lblBien = lblBien;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tfBien) {
		this.tfBien = tfBien;
	}

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button btnSeleccionarBien) {
		this.btnSeleccionarBien = btnSeleccionarBien;
	}

	public Label getLblSecretaria() {
		return lblSecretaria;
	}

	public void setLblSecretaria(Label lblSecretaria) {
		this.lblSecretaria = lblSecretaria;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public DropDown getDdSecretaria() {
		return ddSecretaria;
	}

	public void setDdSecretaria(DropDown ddSecretaria) {
		this.ddSecretaria = ddSecretaria;
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdSecretariaOptions() {
		return ddSecretariaOptions;
	}

	public void setDdSecretariaOptions(SingleSelectOptionsList ddSecretariaOptions) {
		this.ddSecretariaOptions = ddSecretariaOptions;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStFechaDesde() {
		return stFechaDesde;
	}

	public void setStFechaDesde(StaticText stFechaDesde) {
		this.stFechaDesde = stFechaDesde;
	}

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
	}

	public StaticText getStFechaHasta() {
		return stFechaHasta;
	}

	public void setStFechaHasta(StaticText stFechaHasta) {
		this.stFechaHasta = stFechaHasta;
	}

	public StaticText getStNumero() {
		return stNumero;
	}

	public void setStNumero(StaticText stNumero) {
		this.stNumero = stNumero;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStSeparador6() {
		return stSeparador6;
	}

	public void setStSeparador6(StaticText stSeparador6) {
		this.stSeparador6 = stSeparador6;
	}

	public StaticText getStValorTotal() {
		return stValorTotal;
	}

	public void setStValorTotal(StaticText stValorTotal) {
		this.stValorTotal = stValorTotal;
	}

	public StaticText getStArea() {
		return stArea;
	}

	public void setStArea(StaticText stArea) {
		this.stArea = stArea;
	}

	public StaticText getStPrecio() {
		return stPrecio;
	}

	public void setStPrecio(StaticText stPrecio) {
		this.stPrecio = stPrecio;
	}

	public StaticText getStBienAsociado() {
		return stBienAsociado;
	}

	public void setStBienAsociado(StaticText stBienAsociado) {
		this.stBienAsociado = stBienAsociado;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public StaticText getStNumeroSolicitud() {
		return stNumeroSolicitud;
	}

	public void setStNumeroSolicitud(StaticText stNumeroSolicitud) {
		this.stNumeroSolicitud = stNumeroSolicitud;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTcArea() {
		return tcArea;
	}

	public void setTcArea(TableColumn tcArea) {
		this.tcArea = tcArea;
	}

	public TableColumn getTcBienAsociado() {
		return tcBienAsociado;
	}

	public void setTcBienAsociado(TableColumn tcBienAsociado) {
		this.tcBienAsociado = tcBienAsociado;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcNumeroSolicitud() {
		return tcNumeroSolicitud;
	}

	public void setTcNumeroSolicitud(TableColumn tcNumeroSolicitud) {
		this.tcNumeroSolicitud = tcNumeroSolicitud;
	}

	public TableColumn getTcFechaEmision() {
		return tcFechaEmision;
	}

	public void setTcFechaEmision(TableColumn tcFechaEmision) {
		this.tcFechaEmision = tcFechaEmision;
	}

	public TableColumn getTcNumero() {
		return tcNumero;
	}

	public void setTcNumero(TableColumn tcNumero) {
		this.tcNumero = tcNumero;
	}

	public TableColumn getTcPrecio() {
		return tcPrecio;
	}

	public void setTcPrecio(TableColumn tcPrecio) {
		this.tcPrecio = tcPrecio;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public Button getBtnFinalizarOrdenCompra() {
		return btnFinalizarOrdenCompra;
	}

	public void setBtnFinalizarOrdenCompra(Button btnFinalizarOrdenCompra) {
		this.btnFinalizarOrdenCompra = btnFinalizarOrdenCompra;
	}

	public ObjectListDataProvider getLdpOrdenCompra() {
		return ldpOrdenCompra;
	}

	public void setLdpOrdenCompra(ObjectListDataProvider oldp) {
		this.ldpOrdenCompra = oldp;
	}

	public Label getLblFechaDesde() {
		return lblFechaDesde;
	}

	public void setLblFechaDesde(Label l) {
		this.lblFechaDesde = l;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tf) {
		this.tfFechaDesde = tf;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label l) {
		this.lblEstado = l;
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

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label l) {
		this.lblProveedor = l;
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

	public TextField getTfCantidadBien() {
		return tfCantidadBien;
	}

	public void setTfCantidadBien(TextField tfCantidadBien) {
		this.tfCantidadBien = tfCantidadBien;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public Label getLblFechaHasta() {
		return lblFechaHasta;
	}

	public void setLblFechaHasta(Label l) {
		this.lblFechaHasta = l;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tf) {
		this.tfFechaHasta = tf;
	}

	public Button getBtnFirmarOrdenCompra() {
		return btnFirmarOrdenCompra;
	}

	public void setBtnFirmarOrdenCompra(Button b) {
		this.btnFirmarOrdenCompra = b;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	public Button getBtnTransferirOrdenCompra() {
		return btnTransferirOrdenCompra;
	}

	public void setBtnTransferirOrdenCompra(Button btnTransferirOrdenCompra) {
		this.btnTransferirOrdenCompra = btnTransferirOrdenCompra;
	}

	// ***METHODS***
	public AdminOrdenCompra() {
	}

	@Override
	protected void _init() throws Exception {
		// ddEstadoDefaultOptions.setOptions(new
		// com.sun.rave.web.ui.model.Option[] {new
		// com.sun.rave.web.ui.model.Option("CREADA", "CREADA"), new
		// com.sun.rave.web.ui.model.Option("CANCELADA", "CANCELADA"), new
		// com.sun.rave.web.ui.model.Option("CURSADA", "CURSADA"), new
		// com.sun.rave.web.ui.model.Option("RECIBIDA", "RECIBIDA")});
		// ddEstadoDefaultOptions.setSelectedValue("CREADA");
		Option[] op = null;
		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(OrdenCompra.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(OrdenCompra.Estado.APROBADA)));
		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");

		Set<String> locListaSecretarias = this.getCommunicationComprasBean().getMapaSecretariasArea().keySet();
		Option[] opSecretarias = new Option[locListaSecretarias.size() + 1];
		int i = 0;
		opSecretarias[i++] = new Option("", "");
		for(String cadaSecretaria : locListaSecretarias) {
			opSecretarias[i++] = new Option(cadaSecretaria, cadaSecretaria);
		}
		this.ddSecretariaOptions.setOptions(opSecretarias);

		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroOrdenCompra locFiltro = getFiltro();
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroOrdenCompra locFiltro = getFiltro();

		borrarListIdAuxBienes(this.getTfBien(), locFiltro.getBien());
		locFiltro.setListaIdBienes(this.getSessionBean1().getListaIdBienes());

		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), OrdenCompra.Estado.class));
		locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));
		locFiltro.setNumero(this.getTextFieldValueInteger(getTfNumero()));
		locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasSolSum()));
		locFiltro.setSecretaria(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroOrdenCompra locFiltro = this.getFiltro();

		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
		if(locFiltro.getProveedor() != null) {
			this.getTfProveedor().setText(locFiltro.getProveedor().getRazonSocial());
		}
		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(locFiltro.getEstado());
		}

		if(locFiltro.getNumero() != null) {
			this.getTfNumero().setText(locFiltro.getNumero());
		}
		if(locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
		if(locFiltro.getSecretaria() != null) {
			this.getDdSecretaria().setSelected(locFiltro.getSecretaria().toString());
		}
		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
		if(locFiltro.getBien() != null) {
			this.getTfBien().setText(locFiltro.getBien().toString());
		} else {
			this.getTfBien().setText(null);
		}
		
		if(locFiltro.getListaResultados() != null){
		double monto = 0.0;
		for(Iterator<OrdenCompra> it = locFiltro.getListaResultados().iterator(); it.hasNext();) {
			OrdenCompra fila = it.next();
			monto += fila.getTotal().doubleValue();
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
		FiltroOrdenCompra filtro = this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().findOrdenCompra((FiltroOrdenCompra) pFiltro);

		double monto = 0.0;

		for(Iterator<OrdenCompra> it = filtro.getListaResultados().iterator(); it.hasNext();) {
			OrdenCompra fila = it.next();
			monto += fila.getTotal().doubleValue();
		}

		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		return filtro;
	}

	public void actionListenerDropSecretaria(ActionEvent event) {
		Set<String> locListaAreas = this.getCommunicationComprasBean()
				.getMapaAreasSolSum(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum())).keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroOrdenCompra locFiltro = getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setFechaHasta(null);
		locFiltro.setNumero(null);
		locFiltro.setProveedor(null);
		locFiltro.setArea(null);
		locFiltro.setSecretaria(null);
		locFiltro.setBien(null);
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(OrdenCompra.Estado.APROBADA)));
		this.getDdArea().setSelected("");
		this.getDdSecretaria().setSelected("");
		this.getTfFechaHasta().setText("");
		this.getTfProveedor().setText("");
		this.getTfNumero().setText("");
		this.getTfBien().setText(null);
		this.getStTotal().setText("");
		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		
		this.getSessionBean1().getListaIdBienes().clear();
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaOrdenCompra();
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpOrdenCompra();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaOrdenesCompra();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaOrdenesCompra(lista);
	}

	public String btnModificar_action() {
		OrdenCompra locOrdenCompra = (OrdenCompra) getObjetoSeleccionado();

		if(locOrdenCompra != null && !locOrdenCompra.getEstado().equals(OrdenCompra.Estado.APROBADA)) {
			warn("No se puede modificar una orden de compra que no est\341 en estado ABIERTA.");
			return null;
		}
		return toAbm(new OrdenCompraModel().new ModificarController());
	}

	public String btnSeleccionarBien_action() {
		return navegarParaSeleccionar("AdminBien");
	}

	public String btnLimpiarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfBien());
			FiltroOrdenCompra locFiltro = getFiltro();
			locFiltro.setBien(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		boolean errorEnReporte = false;

		if(ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					OrdenCompra locOrdenCompra = (OrdenCompra) obj;
					if(locOrdenCompra.getEstado() != OrdenCompra.Estado.APROBADA) {
						warn("La Orden de Compra debe estar APROBADA para imprimirse");
						errorEnReporte = true;
					} else {
						this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());
						JasperPrint jp = this.getCommunicationComprasBean().getRemoteSystemReportesCompras().getReporteOrdenCompra(locOrdenCompra.getIdOrdenCompra());

						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_OrdenCompra");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
					}
				} else {
					errorEnReporte = true;
				}
			} catch(Exception e) {
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
				errorEnReporte = true;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		if(errorEnReporte) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
		}
		return retorno;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(getTfProveedor());
			FiltroOrdenCompra locFiltro = getFiltro();
			locFiltro.setProveedor(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		this.btnImprimirReporte.setDisabled(false);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().findOrdenCompraByID(locOrdenCompra.getIdOrdenCompra());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Ordenes de Compra";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminOrdenCompra";
	}

	public String btnAgregar_action() {
		return toAbm(new OrdenCompraModel().new AgregarController());
	}

	public String btnFinalizar_action() {

		OrdenCompra locOrdenCompra = (OrdenCompra) getObjetoSeleccionado();

		if(locOrdenCompra != null && (locOrdenCompra.getEstado().equals(OrdenCompra.Estado.ANULADA) 
				|| locOrdenCompra.getEstado().equals(OrdenCompra.Estado.RESCINDIDA))) {
			warn("La Orden de Compra ya se encuentra en estado ANULADA o RESCINDIDA");
			return null;
		}
		
		if (locOrdenCompra != null && locOrdenCompra.getEstado().equals(OrdenCompra.Estado.CUMPLIDA)) {
			warn("La Orden de Compra se encuentra CUMPLIDA");
			return null;
		}
		return toAbm(new OrdenCompraModel().new FinalizarController());
	}

	public String btnConsultar_action() {
		return toAbm(new OrdenCompraModel().new ConsultarController());
	}

	public String btnFirmarOrdenCompra_action() {
		OrdenCompra locOrdenCompra = (OrdenCompra) getObjetoSeleccionado();
		
		if (locOrdenCompra == null) {
			warn("Seleccione una Orden de Compra");
			return null;
		}

		if(!locOrdenCompra.getEstado().equals(OrdenCompra.Estado.NUEVA)) {
			warn("El Estado de la Orden de Compra debe ser NUEVA");
			return null;
		}
		Usuario locUsuario = this.getSessionBean1().getUsuario();
		for(AutorizacionOrdenCompra cadaAutorizacion : locOrdenCompra.getListaFirmaPermisos()) {
			if(cadaAutorizacion.getFirmaPermiso().getUsuario().equals(locUsuario)) {
				warn("Usted ya firmó esta Orden de Compra");
				return null;
			}
		}

		return toAbm(new OrdenCompraModel().new FirmarController());
	}

	public String btnTransferirOrdenCompra_action() {
		OrdenCompra locOrdenCompra = (OrdenCompra) getObjetoSeleccionado();
		if (locOrdenCompra == null) {
			warn("Seleccione una Orden de Compra.");
			return null;
		}
		if(locOrdenCompra.getEstado().equals(OrdenCompra.Estado.NUEVA) || locOrdenCompra.getEstado().equals(OrdenCompra.Estado.APROBADA)) {
			return toAbm(new OrdenCompraModel().new TransferirController());
		}else{
			warn("La Orden de Compra solo se puede transferir si se encuentra en estado NUEVA o APROBADA");
			return null;
		}
		
	}

	public String btnGenerarPagos_action() {
		OrdenCompra locOrdenCompra = (OrdenCompra) getObjetoSeleccionado();
		if(locOrdenCompra != null && locOrdenCompra.getEstado().equals(OrdenCompra.Estado.CUMPLIDA)) {
			warn("La Orden de Compra ya se encuentra en estado CUMPLIDA");
			return null;
		}
		return toAbm(new OrdenCompraModel().new GenerarPagosController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroOrdenCompra locFiltro = getFiltro();
		if(pObject instanceof Proveedor) {
			if(pObject != null) {
				locFiltro.setProveedor((Proveedor) pObject);
			}
		}
		if(pObject instanceof Bien) {
			Bien bien = (Bien) pObject;

			locFiltro.setBien(bien);
		}
	}

	public void setBienAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroOrdenCompra locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Bien locBien = null;

		try {
			locBien = this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locFiltro.setBien(locBien);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMOrdenCompra$AdminOrdenCompra}";
	}

	@Override
	public long getSerialVersionUID() {
		return OrdenCompra.serialVersionUID;
	}
}