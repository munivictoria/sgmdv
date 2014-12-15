/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.framework.ABMContrato;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroContrato;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminContrato extends AdminPageBean {

	protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	protected Button btnImprimirReporte = new Button();

	protected ObjectListDataProvider ldpFactura = new ObjectListDataProvider();

	protected TableColumn tcCodigo = new TableColumn();
	protected TableColumn tcFechaInicio = new TableColumn();
	protected TableColumn tcFechaFin = new TableColumn();
	protected TableColumn tableColumn5 = new TableColumn();
	protected TableColumn tcPersona = new TableColumn();

	protected StaticText stCodigoContrato = new StaticText();
	protected StaticText stFechaInicio = new StaticText();
	protected StaticText stFechaFin = new StaticText();
	protected StaticText stPersona = new StaticText();
	protected StaticText stSeparador5 = new StaticText();

	protected Label lblFechaInicioDesde = new Label();
	protected Label lblFechaFinDesde = new Label();
	protected Label lblFechaFinHasta = new Label();
	protected Label lblFechaInicioHasta = new Label();

	protected TextField tfFechaInicioDesde = new TextField();
	protected TextField tfFechaInicioHasta = new TextField();
	protected TextField tfFechaFinDesde = new TextField();
	protected TextField tfFechaFinHasta = new TextField();

	protected NumberConverter numberConverter1 = new NumberConverter();

	@Override
	protected void _init() throws Exception {
		// Tipo Documento
		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setDateStyle("full");
	}

	public Label getLblFechaInicioDesde() {
		return lblFechaInicioDesde;
	}

	public void setLblFechaInicioDesde(Label lblFechaInicioDesde) {
		this.lblFechaInicioDesde = lblFechaInicioDesde;
	}

	public Label getLblFechaInicioHasta() {
		return lblFechaInicioHasta;
	}

	public void setLblFechaInicioHasta(Label lblFechaInicioHasta) {
		this.lblFechaInicioHasta = lblFechaInicioHasta;
	}

	public Label getLblFechaFinDesde() {
		return lblFechaFinDesde;
	}

	public void setLblFechaFinDesde(Label lblFechaFinDesde) {
		this.lblFechaFinDesde = lblFechaFinDesde;
	}

	public TableColumn getTcCodigo() {
		return tcCodigo;
	}

	public void setTcCodigo(TableColumn tcCodigo) {
		this.tcCodigo = tcCodigo;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	public StaticText getStCodigoContrato() {
		return stCodigoContrato;
	}

	public void setStCodigoContrato(StaticText stCodigoContrato) {
		this.stCodigoContrato = stCodigoContrato;
	}

	public ObjectListDataProvider getLdpFactura() {
		return ldpFactura;
	}

	public void setLdpFactura(ObjectListDataProvider oldp) {
		this.ldpFactura = oldp;
	}

	public StaticText getStFechaInicio() {
		return stFechaInicio;
	}

	public void setStFechaInicio(StaticText stFechaInicio) {
		this.stFechaInicio = stFechaInicio;
	}

	public TableColumn getTcFechaFin() {
		return tcFechaFin;
	}

	public void setTcFechaFin(TableColumn tcFechaFin) {
		this.tcFechaFin = tcFechaFin;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	public TextField getTfFechaInicioDesde() {
		return tfFechaInicioDesde;
	}

	public void setTfFechaInicioDesde(TextField tfFechaInicioDesde) {
		this.tfFechaInicioDesde = tfFechaInicioDesde;
	}

	public TableColumn getTcFechaInicio() {
		return tcFechaInicio;
	}

	public void setTcFechaInicio(TableColumn tcFechaInicio) {
		this.tcFechaInicio = tcFechaInicio;
	}

	public Label getLblFechaFinHasta() {
		return lblFechaFinHasta;
	}

	public void setLblFechaFinHasta(Label lblFechaFinHasta) {
		this.lblFechaFinHasta = lblFechaFinHasta;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public TextField getTfFechaInicioHasta() {
		return tfFechaInicioHasta;
	}

	public void setTfFechaInicioHasta(TextField tfFechaInicioHasta) {
		this.tfFechaInicioHasta = tfFechaInicioHasta;
	}

	public TextField getTfFechaFinDesde() {
		return tfFechaFinDesde;
	}

	public void setTfFechaFinDesde(TextField tfFechaFinDesde) {
		this.tfFechaFinDesde = tfFechaFinDesde;
	}

	public TextField getTfFechaFinHasta() {
		return tfFechaFinHasta;
	}

	public void setTfFechaFinHasta(TextField tfFechaFinHasta) {
		this.tfFechaFinHasta = tfFechaFinHasta;
	}

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	public StaticText getStFechaFin() {
		return stFechaFin;
	}

	public void setStFechaFin(StaticText stFechaFin) {
		this.stFechaFin = stFechaFin;
	}

	public AdminContrato() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroContrato locFiltro = this.getFiltro();

		locFiltro.setFechaInicioDesde(this.getTextFieldValueDate(getTfFechaInicioDesde()));
		locFiltro.setFechaInicioHasta(this.getTextFieldValueDate(getTfFechaInicioHasta()));
		locFiltro.setFechaFinDesde(this.getTextFieldValueDate(getTfFechaFinDesde()));
		locFiltro.setFechaFinHasta(this.getTextFieldValueDate(getTfFechaFinHasta()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroContrato locFiltro = getFiltro();

		this.getTfFechaInicioDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaInicioDesde()));
		this.getTfFechaInicioHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaInicioHasta()));
		this.getTfFechaFinDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaFinDesde()));
		this.getTfFechaFinHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaFinHasta()));

		if(locFiltro.getPersona() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getPersona().toString());
		}
		// this.getTfProveedor().setText(proveedor.getRazonSocial());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemContrato().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemContrato().findListaContratos((FiltroContrato) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroContrato locFiltro = this.getFiltro();

		locFiltro.setFechaInicioDesde(null);
		locFiltro.setFechaInicioHasta(null);
		locFiltro.setFechaFinDesde(null);
		locFiltro.setFechaFinHasta(null);
		locFiltro.setPersona(null);

		this.getTfFechaInicioDesde().setText("");
		this.getTfFechaInicioHasta().setText("");
		this.getTfFechaFinDesde().setText("");
		this.getTfFechaFinHasta().setText("");
		this.getTfPersonaSeleccionada().setText("");
		this.getSessionBean1().setPersonaSeleccionada(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpFactura();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaContratos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaContratos(lista);
	}

	@Override
	protected boolean validarBuscar() {
		Validador v = new Validador();

		UIComponent[] fechas = new UIComponent[4];
		String[] nomfechas = new String[4];

		int pos1 = 0;
		fechas[pos1] = this.getTfFechaInicioDesde();
		nomfechas[pos1++] = "Fecha Inicio Desde";
		fechas[pos1] = this.getTfFechaInicioHasta();
		nomfechas[pos1++] = "Fecha Inicio Hasta";
		fechas[pos1] = this.getTfFechaInicioHasta();
		nomfechas[pos1++] = "Fecha Fin Desde";
		fechas[pos1] = this.getTfFechaInicioHasta();
		nomfechas[pos1++] = "Fecha Fin Hasta";

		v.formatoFechaValido(fechas, nomfechas);

		if(v.getErrores().size() > 0) {
			error("Existen Errores:");
			for(int i = 0; i < v.getErrores().size(); i++) {
				warn(v.getErrores().toArray()[i].toString());
			}
			return false;
		}
		return true;
	}

	public String btnAceptarOrdenCompra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoABM(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(this.getCasoNavegacion() + "_AceptarError:", ex);
				error(this.getNombrePagina() + " - Aceptar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = "AceptarOrdenCompra";
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnImprimirReporte_action() {
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Contrato locContrato = (Contrato) pObject;
		getComunicationBean().getRemoteSystemContrato().setLlave(this.getSessionBean1().getLlave());
		locContrato = this.getComunicationBean().getRemoteSystemContrato().getContratoPorId(locContrato.getIdContrato());
		return locContrato;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Contratos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminContrato";
	}

	public String btnAgregar_action() {
		return toAbm(new ContratoModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new ContratoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new ContratoModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new ContratoModel().new ConsultarController());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaContrato();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			FiltroContrato locFiltro = getFiltro();
			locFiltro.setPersona(persona);

			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMContrato$AdminContrato}";
	}

	@Override
	public long getSerialVersionUID() {
		return Contrato.serialVersionUID;
	}
}