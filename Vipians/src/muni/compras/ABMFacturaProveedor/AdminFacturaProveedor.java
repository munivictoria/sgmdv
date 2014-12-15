/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.compras.ABMFacturaProveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ActionEvent;

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
import com.trascender.compras.recurso.filtros.FiltroFacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class AdminFacturaProveedor extends AdminPageBean {

	protected Button btnLimpiarProveedor = new Button();
	protected Button btnSeleccionarProveedor = new Button();
	protected Button btnImprimirReporte = new Button();

	protected StaticText stFechaDeEmision = new StaticText();
	protected StaticText stProveedor = new StaticText();
	protected StaticText stEstado = new StaticText();
	protected StaticText stTotal2 = new StaticText();
	protected StaticText stTipoFactura = new StaticText();
	protected StaticText stFechaDesde = new StaticText();
	protected StaticText stFechaHasta = new StaticText();
	protected StaticText stSeparador5 = new StaticText();
	protected StaticText stTotal = new StaticText();

	protected TableColumn tcFechaEmision = new TableColumn();
	protected TableColumn tcProveedor = new TableColumn();
	protected TableColumn tcEstado = new TableColumn();
	protected TableColumn tcMonto = new TableColumn();
	protected TableColumn tcTipoFactura = new TableColumn();

	protected Label lblFechaDesde = new Label();
	protected Label lblEstado = new Label();
	protected Label lblTotal = new Label();
	protected Label lblProveedor = new Label();
	protected Label lblFechaHasta = new Label();
	protected Label lblArea = new Label();
	protected Label lblSecretaria = new Label();
	protected Label lblNro = new Label();

	protected TextField tfFechaDesde = new TextField();
	protected TextField tfProveedor = new TextField();
	protected TextField tfFechaHasta = new TextField();
	protected TextField tfNro = new TextField();

	protected DropDown ddEstado = new DropDown();
	protected DropDown ddSecretaria = new DropDown();
	protected DropDown ddArea = new DropDown();

	protected SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddSecretariaOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();

	protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	protected ObjectListDataProvider ldpFactura = new ObjectListDataProvider();

	protected NumberConverter numberConverter1 = new NumberConverter();

	protected Label lblBien = new Label();
	protected TextField tfBien = new TextField();
	protected Button btnSeleccionarBien = new Button();
	protected Button btnLimpiarBien = new Button();
	
	// ***GETTERS & SETTERS
	
	public Label getLblEstado() {
		return lblEstado;
	}

	public Label getLblNro() {
		return lblNro;
	}

	public void setLblNro(Label lblNro) {
		this.lblNro = lblNro;
	}

	public TextField getTfNro() {
		return tfNro;
	}

	public void setTfNro(TextField tfNro) {
		this.tfNro = tfNro;
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

	public Button getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(Button btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public Label getLblSecretaria() {
		return lblSecretaria;
	}

	public void setLblSecretaria(Label lblSecretaria) {
		this.lblSecretaria = lblSecretaria;
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

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblFechaDesde() {
		return lblFechaDesde;
	}

	public void setLblFechaDesde(Label lblFechaDesde) {
		this.lblFechaDesde = lblFechaDesde;
	}

	public Label getLblFechaHasta() {
		return lblFechaHasta;
	}

	public void setLblFechaHasta(Label lblFechaHasta) {
		this.lblFechaHasta = lblFechaHasta;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcFechaEmision() {
		return tcFechaEmision;
	}

	public void setTcFechaEmision(TableColumn tcFechaEmision) {
		this.tcFechaEmision = tcFechaEmision;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public TableColumn getTcTipoFactura() {
		return tcTipoFactura;
	}

	public void setTcTipoFactura(TableColumn tcTipoFactura) {
		this.tcTipoFactura = tcTipoFactura;
	}

	public StaticText getStFechaDesde() {
		return stFechaDesde;
	}

	public void setStFechaDesde(StaticText stFechaDesde) {
		this.stFechaDesde = stFechaDesde;
	}

	public StaticText getStFechaHasta() {
		return stFechaHasta;
	}

	public void setStFechaHasta(StaticText stFechaHasta) {
		this.stFechaHasta = stFechaHasta;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStTipoFactura() {
		return stTipoFactura;
	}

	public void setStTipoFactura(StaticText stTipoFactura) {
		this.stTipoFactura = stTipoFactura;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStTotal2() {
		return stTotal2;
	}

	public void setStTotal2(StaticText stTotal2) {
		this.stTotal2 = stTotal2;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public StaticText getStFechaDeEmision() {
		return stFechaDeEmision;
	}

	public void setStFechaDeEmision(StaticText stFechaDeEmision) {
		this.stFechaDeEmision = stFechaDeEmision;
	}

	public Button getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(Button btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public ObjectListDataProvider getLdpFactura() {
		return ldpFactura;
	}

	public void setLdpFactura(ObjectListDataProvider oldp) {
		this.ldpFactura = oldp;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tf) {
		this.tfFechaDesde = tf;
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

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaFacturaProveedor();
	}

	// ***METHODS***

	@Override
	protected void _init() throws Exception {

		this.setListaDelCommunicationAtributosDinamicos(null);
		
		// ddEstadoDefaultOptions.setOptions(new
		// com.sun.rave.web.ui.model.Option[] {new
		// com.sun.rave.web.ui.model.Option("CREADA", "CREADA"), new
		// com.sun.rave.web.ui.model.Option("PAGADA", "PAGADA")});
		// ddEstadoDefaultOptions.setSelectedValue("CREADA");
		Option[] op = null;

		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaProveedor.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);

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
		for (String cadaSecretaria : locListaSecretarias) {
			opSecretarias[i++] = new Option(cadaSecretaria, cadaSecretaria);
		}
		this.ddSecretariaOptions.setOptions(opSecretarias);

		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}
	
	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public AdminFacturaProveedor() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroFacturaProveedor locFiltro = getFiltro();
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(FacturaProveedor.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroFacturaProveedor locFiltro = this.getFiltro();

		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), FacturaProveedor.Estado.class));
		locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));
		locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasSolSum()));
		locFiltro.setSecretaria(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum()));
		locFiltro.setNumero(this.getTextFieldValue(getTfNro()));
		
		if (locFiltro.getListaAtributoDinamico() != null) {
			locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FacturaProveedor auxFacturaProveedor = null;

		double monto = 0.0;
		List lista;

		FiltroFacturaProveedor locFiltro = this.getFiltro();
		
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{framework$ABMFacturaProveedor$ABMFacturaProveedor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());

		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
		if (locFiltro.getProveedor() != null) {
			this.getTfProveedor().setText(locFiltro.getProveedor().getRazonSocial());
		}
		if (locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
		if (locFiltro.getSecretaria() != null) {
			this.getDdSecretaria().setSelected(locFiltro.getSecretaria().toString());
		}
		if (locFiltro.getBien() != null) {
			this.getTfBien().setText(locFiltro.getBien().toString());
		} else {
			this.getTfBien().setText(null);
		}
		if (locFiltro.getNumero() != null){
			this.getTfNro().setText(locFiltro.getNumero());
		}else{
			this.getTfNro().setText(null);
		}

		if (this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {
			lista = this.getListaDelCommunication();
			for (int i = 0; i < lista.size(); i++) {
				auxFacturaProveedor = (FacturaProveedor) lista.get(i);
				monto += auxFacturaProveedor.getTotal().doubleValue();
			}
		}

		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		if (locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().setLlave(this.getSessionBean1().getLlave());
		FiltroFacturaProveedor locFiltro = getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().findListaFacturaProveedor((FiltroFacturaProveedor) pFiltro);

		double monto = 0.0;
		if (!locFiltro.getListaResultados().isEmpty()) {
			for (FacturaProveedor cadaFactura : locFiltro.getListaResultados()) {
				monto += cadaFactura.getTotal().doubleValue();
			}
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		return locFiltro;
	}

	public void actionListenerDropSecretaria(ActionEvent event) {
		Set<String> locListaAreas = this.getCommunicationComprasBean()
				.getMapaAreasSolSum((Secretaria) this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum())).keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroFacturaProveedor locFiltro = this.getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setFechaHasta(null);
		locFiltro.setProveedor(null);
		locFiltro.setArea(null);
		locFiltro.setSecretaria(null);
		locFiltro.setBien(null);
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());
		locFiltro.setNumero(null);
		
		panelAtributoDinamico.limpiarCampos();

		this.getTfNro().setText("");
		this.getDdEstado().setSelected(null);
		this.getTfFechaDesde().setText("");
		this.getTfFechaHasta().setText("");
		this.getTfProveedor().setText("");
		this.getDdArea().setSelected("");
		this.getDdSecretaria().setSelected("");
		this.getTfBien().setText(null);
		this.getStTotal().setText("");
		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpFactura();
	}

	@Override
	public List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaFacturas();
	}

	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaFacturas(lista);
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
				log(getCasoNavegacion() + "_AceptarError:", ex);
				error(getNombrePagina() + " - Aceptar: " + ex.getMessage());
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
	
	private List getListaDelCommunicationAtributosDinamicos() {
		return this.getCommunicationComprasBean().getListaAtributosDinamicosFacturaProveedor();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getCommunicationComprasBean().setListaAtributosDinamicosFacturaProveedor(lista);
	}
	
	public String btnSeleccionarBien_action() {
		return navegarParaSeleccionar("AdminBien");
	}
	
	public String btnLimpiarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(this.getTfBien());
			FiltroFacturaProveedor locFiltro = getFiltro();
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
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(getTfProveedor());
			FiltroFacturaProveedor locFiltro = this.getFiltro();
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
		FacturaProveedor locFactura = (FacturaProveedor) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().findFacturaProveedorByID(locFactura.getIdFactura());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Facturas de Proveedor";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminFacturaProveedor";
	}

	public String btnAgregar_action() {
		return toAbm(new FacturaProveedorModel().new AgregarController());
	}

	public String btnModificar_action() {

		FacturaProveedor locFacturaProveedor = (FacturaProveedor) this.getObjetoSeleccionado();

		if (locFacturaProveedor != null && locFacturaProveedor.getEstado().equals(Factura.Estado.CANCELADA)) {
			warn("No se puede modificar facturas con estado CANCELADA");
			return null;
		}

		return toAbm(new FacturaProveedorModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new FacturaProveedorModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new FacturaProveedorModel().new ConsultarController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroFacturaProveedor locFiltro = this.getFiltro();
		if (pObject instanceof Proveedor) {
			locFiltro.setProveedor((Proveedor) pObject);
		}
		if (pObject instanceof Bien) {
			Bien bien = (Bien) pObject;

			locFiltro.setBien(bien);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaProveedor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaProveedor$AdminFacturaProveedor}";
	}
}