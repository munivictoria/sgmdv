/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trascender.presentacion.abstracts;

import jasper.ConstantesReportes;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.ValueChangeEvent;

import muni.framework.ABMReporte.EjecutarReporteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;

/**
 * 
 * @author Fernando Gareis
 */
public abstract class AdminPageBean extends TrascenderAbstractPageBean {

	protected Label lblEncontrados = new Label();
	protected Label lblPersonaSeleccionada = new Label();
	protected Label lblNroDocumento = new Label();
	protected Label lblParcelaSeleccionada = new Label();
	protected Label lblNroParcela = new Label();
	protected StaticText stCantidadRegistros = new StaticText();
	private NumberConverter ncRegistrosEncontrados = new NumberConverter();
	protected StaticText stTitulo = new StaticText();
	protected TextField tfPersonaSeleccionada = new TextField();
	protected TextField tfNroDocumento = new TextField();
	protected TextField tfParcelaSeleccionada = new TextField();
	protected TextField tfNroParcela = new TextField();

	protected Button btnAgregar = new Button();
	protected Button btnModificar = new Button();
	protected Button btnEliminar = new Button();
	protected Button btnExportar = new Button();
	protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();
	protected Button btnCancelar = new Button();
	protected Button btnSeleccionar = new Button();
	protected Button btnConsultar = new Button();
	protected Button btnEjecutar = new Button();
	protected HtmlAjaxCommandButton btnCheckearStock = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarPersonaFisica = new Button();
	protected Button btnSeleccionarPersonaJuridica = new Button();
	protected HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarParcela = new Button();
	protected HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	protected Table table1 = new Table();
	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected PanelGroup pgParametros = new PanelGroup();
	protected TableColumn tableColumn1 = new TableColumn();
	protected RadioButton radioButton1 = new RadioButton();
	protected Checkbox checkbox1 = new Checkbox();
	protected TableColumn tcCheckbox = new TableColumn();

	protected Script scriptFinal1 = new Script();

	protected StaticText stSeparador1 = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected StaticText stSeparador3 = new StaticText();
	protected StaticText stSeparador4 = new StaticText();
	protected StaticText stSeparadorSeleccionar = new StaticText();
	protected StaticText stSeparadorAccion = new StaticText();

	protected TableSelectPhaseListener tspl = new TableSelectPhaseListener();
	
	protected DropDown ddReportes = new DropDown();

	// private Checkbox ckbBuscarPorLogs = new Checkbox();

	// meter un mapa (communication) con el serial version (key) y la tabla (object) y recuperarla aca

	// public Checkbox getCkbBuscarPorLogs() {
	// return ckbBuscarPorLogs;
	// }
	//
	// public void setCkbBuscarPorLogs(Checkbox ckbBuscarPorLogs) {
	// this.ckbBuscarPorLogs = ckbBuscarPorLogs;
	// }
	
	public TableSelectPhaseListener getTspl() {
		return tspl;
	}

	public DropDown getDdReportes() {
		return ddReportes;
	}

	public void setDdReportes(DropDown ddReportes) {
		this.ddReportes = ddReportes;
	}

	public Button getBtnEjecutar() {
		return btnEjecutar;
	}

	public void setBtnEjecutar(Button btnEjecutar) {
		this.btnEjecutar = btnEjecutar;
	}

	public void setTspl(TableSelectPhaseListener tspl) {
		this.tspl = tspl;
	}

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button btnSeleccionarParcela) {
		this.btnSeleccionarParcela = btnSeleccionarParcela;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public Label getLblParcelaSeleccionada() {
		return lblParcelaSeleccionada;
	}

	public void setLblParcelaSeleccionada(Label lblParcelaSeleccionada) {
		this.lblParcelaSeleccionada = lblParcelaSeleccionada;
	}

	public Label getLblPersonaSeleccionada() {
		return lblPersonaSeleccionada;
	}

	public void setLblPersonaSeleccionada(Label lblPersonaSeleccionada) {
		this.lblPersonaSeleccionada = lblPersonaSeleccionada;
	}

	public Label getLblNroDocumento() {
		return lblNroDocumento;
	}

	public void setLblNroDocumento(Label lblNroDocumento) {
		this.lblNroDocumento = lblNroDocumento;
	}

	public Label getLblNroParcela() {
		return lblNroParcela;
	}

	public void setLblNroParcela(Label lblNroParcela) {
		this.lblNroParcela = lblNroParcela;
	}

	public TextField getTfNroDocumento() {
		return tfNroDocumento;
	}

	public void setTfNroDocumento(TextField tfNroDocumento) {
		this.tfNroDocumento = tfNroDocumento;
	}

	public TextField getTfNroParcela() {
		return tfNroParcela;
	}

	public void setTfNroParcela(TextField tfNroParcela) {
		this.tfNroParcela = tfNroParcela;
	}

	public TextField getTfParcelaSeleccionada() {
		return tfParcelaSeleccionada;
	}

	public void setTfParcelaSeleccionada(TextField tfParcelaSeleccionada) {
		this.tfParcelaSeleccionada = tfParcelaSeleccionada;
	}

	public TextField getTfPersonaSeleccionada() {
		return tfPersonaSeleccionada;
	}

	public void setTfPersonaSeleccionada(TextField tfPersonaSeleccionada) {
		this.tfPersonaSeleccionada = tfPersonaSeleccionada;
	}

	public TableColumn getTcCheckbox() {
		return tcCheckbox;
	}

	public void setTcCheckbox(TableColumn tcCheckbox) {
		this.tcCheckbox = tcCheckbox;
	}

	private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();

	protected TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	protected void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public StaticText getStSeparador4() {
		return stSeparador4;
	}

	public void setStSeparador4(StaticText stSeparador4) {
		this.stSeparador4 = stSeparador4;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public StaticText getStSeparadorSeleccionar() {
		stSeparadorSeleccionar.setText(" | ");
		return stSeparadorSeleccionar;
	}

	public void setStSeparadorSeleccionar(StaticText stSeparadorSeleccionar) {
		this.stSeparadorSeleccionar = stSeparadorSeleccionar;
	}

	public StaticText getStSeparadorAccion() {
		stSeparadorAccion.setText(" | ");
		return stSeparadorAccion;
	}

	public void setStSeparadorAccion(StaticText stSeparadorAccion) {
		this.stSeparadorAccion = stSeparadorAccion;
	}

	public StaticText getStCantidadRegistros() {
		return stCantidadRegistros;
	}

	public void setStCantidadRegistros(StaticText st) {
		this.stCantidadRegistros = st;
	}

	public NumberConverter getNcRegistrosEncontrados() {
		return ncRegistrosEncontrados;
	}

	public void setNcRegistrosEncontrados(NumberConverter ncRegistrosEncontrados) {
		this.ncRegistrosEncontrados = ncRegistrosEncontrados;
	}

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	public Label getLblEncontrados() {
		return lblEncontrados;
	}

	public void setLblEncontrados(Label l) {
		this.lblEncontrados = l;
	}

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	public HtmlAjaxCommandButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public HtmlAjaxCommandButton getBtnCheckearStock() {
		return btnCheckearStock;
	}

	public void setBtnCheckearStock(HtmlAjaxCommandButton btnCheckearStock) {
		this.btnCheckearStock = btnCheckearStock;
	}

	public HtmlAjaxCommandButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
		this.btnReiniciar = btnReiniciar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Button b) {
		this.btnEliminar = b;
	}

	public Button getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(Button b) {
		this.btnModificar = b;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	public Button getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(Button b) {
		this.btnSeleccionar = b;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public Script getScriptFinal1() {
		return scriptFinal1;
	}

	public void setScriptFinal1(Script s) {
		this.scriptFinal1 = s;
	}

	public Button getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(Button b) {
		this.btnConsultar = b;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	// ****************************************************//
	public abstract ObjectListDataProvider getObjectListDataProvider();

	protected abstract List getListaDelCommunication();

	protected abstract void setListaDelCommunication(List lista);

	// protected abstract TableSelectPhaseListener getTablePhaseListener();

	protected abstract void limpiarObjetosUsados();

	protected abstract Object getObjectPorId(Object pObject) throws Exception;

	protected abstract FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception;

	/***********************************************************/
	/**
	 * A pasar a abstracto dentro de poco
	 * 
	 * @return
	 */

	// public abstract PaginatedTable getPaginatedTable();

	public PaginatedTable getPaginatedTable() {
		return null;
	}

	// Se deja vacio a proposito, para que no sobreescriba la actual.
	public void setPaginatedTable(PaginatedTable customTable) {
	}

	protected <T extends FiltroAbstracto> T getFiltro() {
		return (T) this.getPaginatedTable().getFiltro();
	}

	protected void refrescarTabla() throws Exception {
		
		Object objetoSeleccionado = (!this.getElementoPila().isSeleccionMultiple()) ? this.getObjetoSeleccionado() : null;
		if(objetoSeleccionado != null) {
			this.getPaginatedTable().setObjetoSeleccionado(objetoSeleccionado);
		}

		FiltroAbstracto locFiltro = this.getPaginatedTable().getFiltro();

		locFiltro.setConfiguracionRecurso(this.getSessionBean1().getConfiguracionRecurso(this.getSerialVersionUID()));

		locFiltro = this.buscar(locFiltro);
		this.getPaginatedTable().setFiltro(locFiltro);
		this.mostrarCantidadRegistrosEncontrados();
		this.setListaDelCommunication(locFiltro.getListaResultados());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	}

	public void limpiarOrdenamiento(ActionEvent event) {
		this.getPaginatedTable().resetearOrdenamientos();
		try {
			this.refrescarTabla();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ordenarPor(ActionEvent actionEvent) {
		String orden = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("criterioOrdenamiento");
		this.getPaginatedTable().setearOrden(orden);
		Object objetoSeleccionado = this.getObjetoSeleccionado();
		if(objetoSeleccionado != null) {
			this.getPaginatedTable().setObjetoSeleccionado(objetoSeleccionado);
		}
		try {
			this.refrescarTabla();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void goPage(ActionEvent actionEvent) {
		PanelGroup pg = (PanelGroup) actionEvent.getComponent().getParent();
		TextField tf = (TextField) pg.getChildren().get(2);

		Integer pag = Integer.parseInt(tf.getValue().toString());
		this.getPaginatedTable().irPagina(pag);
		try {
			this.refrescarTabla();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goBackPage(ActionEvent actionEvent) {
		this.getPaginatedTable().restarUnaPagina();
		try {
			this.refrescarTabla();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goNextPage(ActionEvent actionEvent) {
		this.getPaginatedTable().sumarUnaPagina();
		try {
			this.refrescarTabla();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goFirstPage(ActionEvent actionEvent) {
		this.getPaginatedTable().irPrimerPagina();
		try {
			this.refrescarTabla();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goLastPage(ActionEvent actionEvent) {
		this.getPaginatedTable().irUltimaPagina();
		try {
			this.refrescarTabla();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeDinamicReport_action(ActionEvent actionEvent) {
		String strIdReporte = this.getDdReportes().getSelected().toString();

		try {
			if(strIdReporte != "") {
				this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
				Reporte locReporte = this.getComunicationBean().getRemoteSystemParametro().getReporteByID(Long.parseLong(strIdReporte));

				if(locReporte != null) {
					List listaParaReporte = new ArrayList();
					boolean selecciona = false;
					if(locReporte.getSeleccionaEntidad() != null) {
						if(locReporte.getSeleccionaEntidad().equals(Reporte.SeleccionaEntidad.VARIAS)) {
							selecciona = true;
							listaParaReporte.addAll(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
						} else if(locReporte.getSeleccionaEntidad().equals(Reporte.SeleccionaEntidad.UNA)) {
							selecciona = true;
							Object obj = getObjetoSeleccionado();

							listaParaReporte.add(obj);
						}
					}

					if(selecciona && listaParaReporte.size() < 1) {
						warn("Debe seleccionar al menos un registro.");

						return;
					}

					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(locReporte);
					this.getRequestBean1().setAbmController(new EjecutarReporteModel().new EjecutarController());
					this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList(listaParaReporte));

					FacesContext context = FacesContext.getCurrentInstance();
					NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
					navigationHandler.handleNavigation(context, null, "EjecutarReporte");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valueChangeReportes(ValueChangeEvent valueChangeEvent) {
		String strIdReporte = this.getDdReportes().getSelected().toString();

		try {
			if(strIdReporte != "") {
				this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
				Reporte locReporte = this.getComunicationBean().getRemoteSystemParametro().getReporteByID(Long.parseLong(strIdReporte));

				if(locReporte != null) {
					boolean selectionMul = false;

					if(locReporte.getSeleccionaEntidad() != null && locReporte.getSeleccionaEntidad().equals(Reporte.SeleccionaEntidad.VARIAS)) {
						selectionMul = true;
						// ver como hacer esto...
					}

					this.getElementoPila().setSeleccionMultiple(selectionMul);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarCantidadRegistrosEncontrados() {
		if(this.getListaDelCommunication() != null) {
			this.getLblEncontrados().setVisible(true);
			
			ncRegistrosEncontrados.setPattern("#,##0");
			this.getStCantidadRegistros().setConverter(ncRegistrosEncontrados);
			
			this.getStCantidadRegistros().setText(this.getPaginatedTable() != null ? this.getFiltro().getCantidadFilasTotales() : this.getListaDelCommunication().size());
			if(!this.getListaDelCommunication().isEmpty()) {
				this.setRBSelected("0");
			}
		} else {
			this.getStCantidadRegistros().setText(0);
		}
	}

	protected Object getObjetoSeleccionado() {
		Object obj = null;
		if(this.getElementoPila().isSeleccionMultiple()) {
			System.out.println(this.getPaginatedTable().getSeleccionadosSeleccionMultiple().size());
			if(this.getPaginatedTable().getSeleccionadosSeleccionMultiple() == null || this.getPaginatedTable().getSeleccionadosSeleccionMultiple().size() < 1) {
				warn("Seleccione un elemento");
				return null;
			}
			obj = this.getPaginatedTable().getSeleccionadosSeleccionMultiple().get(0);
		} else {
			RowKey rk = this.getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				obj = this.getObjectListDataProvider().getObjects()[index];
				this.setRowKeySeleccionado(rk);
			}
		}
		
		return obj;
	}

	protected String toAbm(ABMController pController) {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Object obj = null;
		if(ultimo) {
			if(pController.seleccionarObjeto()) {
				obj = getObjetoSeleccionado();
				
				try {
					obj = this.getObjectPorId(obj);
				} catch(Exception e) {
					e.printStackTrace();
					log(getCasoNavegacion() + "toAbmException:", e);
					error(getNombrePagina() + " - toAbm: " + e.getMessage());
				}
				this.getRequestBean1().setObjetoABM(obj);
				if(this.getPaginatedTable() != null) {
					this.getPaginatedTable().setObjetoSeleccionado(obj);
				}
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());

			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// Si se necesita un objeto seleccionado pero el usuario no
			// selecciono nada,
			// no se navega.
			if(!pController.seleccionarObjeto() || (pController.seleccionarObjeto() && obj != null)) {
				this.getRequestBean1().setAbmController(pController);
				retorno = pController.getModel().getReglaNavegacion();
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnReiniciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				if(this.getPaginatedTable() != null) {
					this.getPaginatedTable().resetearEstado();
					this.getPaginatedTable().getSeleccionadosSeleccionMultiple().clear();
				}
				this.limpiarObjetosUsados();
				// this.guardarEstadoObjetosUsados();
				// this.refrescarTabla();
				this.getStCantidadRegistros().setText("");
				this.limpiarTabla();
				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
				this.getElementoPila().setPosicionGlobal(pos.longValue());
				this.getRequestBean1().setAccion(Constantes.ACCION_REINICIAR);
				this.mostrarCantidadRegistrosEncontrados();
				this.getTablaBusquedaLogs().getCkbBuscarPorLogs().setSelected(false);
				this.getFiltro().setBuscarPorLogs(false);
				this.getFiltro().setListaBusquedaPorLogs(new ArrayList<BusquedaPorLog>());
				if(this.getSerialVersionUID() != 0)
					this.getTablaBusquedaLogs().getLdpBusquedaLogs().setList(this.getFiltro().getListaBusquedaPorLogs());
				this.getPaginatedTable().resetearOrdenamientos();
			} catch(Exception ex) {
				this.limpiarTabla();
				log(getCasoNavegacion() + "_ReiniciarError:", ex);
				error(getNombrePagina() + " - Reiniciar: " + ex.getMessage());
				this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		this.getFiltro().setCantidadFilasTotales(0l);
		
		return retorno;
	}

	public String btnBuscar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				if(this.getSerialVersionUID() != 0 && !this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().isEmpty())
					this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
				this.guardarEstadoObjetosUsados();
				if(!validarBuscar()) {
					return null;
				}
				if(this.getPaginatedTable() != null) {
					this.getPaginatedTable().resetearEstado();
					if(this.getPaginatedTable().getSeleccionadosSeleccionMultiple() != null) {
						this.getPaginatedTable().getSeleccionadosSeleccionMultiple().clear();
					}
				}
				this.refrescarTabla();
				this.guardarOrdenamiento();

				if(this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {
					Object primerObjeto = this.getListaDelCommunication().get(0);
					if(this.getPaginatedTable() != null) {
						this.getPaginatedTable().setObjetoSeleccionado(primerObjeto);
					}
				}

				this.getRequestBean1().setAccion(Constantes.ACCION_BUSCAR);
				this.mostrarCantidadRegistrosEncontrados();
			} catch(Exception ex) {
				this.limpiarTabla();
				log(getCasoNavegacion() + "_BuscarError:", ex);
				error(getNombrePagina() + " - Buscar: " + ex.getMessage());
				this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	protected boolean validarBuscar() {
		return true;
	}

	public String btnSeleccionar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			if(this.getElementoPila().isSeleccionMultiple()) {
				try {
					// Inicializo el Array de objetos seleccionados
					if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
						this.getRequestBean1().getObjetosSeleccionMultiple().clear();
					} else {
						this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
					}

					// ACAAAAA NO ENTRA AL FOR PORQ selectedRowKeys = 0 !!!!!
					// WTF
					this.getRequestBean1().getObjetosSeleccionMultiple().addAll(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
					this.getPaginatedTable().getSeleccionadosSeleccionMultiple().clear();

				} catch(Exception ex) {
					log(getCasoNavegacion() + "_SeleccionarMultiplesObjetosError:", ex);
					error(getNombrePagina() + " - Seleccionar M\372ltiples Objetos: " + ex.getMessage());
				}

				if(!this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) {
					retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
				} else {
				}
				// retorno =
				// this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);

			} else {
				try {
					rk = this.getSeleccionado();
					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						if(!validarSeleccionar(obj)) {
							return null;
						}
						getRequestBean1().setObjetoSeleccion(obj);
						this.setRowKeySeleccionado(this.getSeleccionado());
						if(this.getPaginatedTable() != null) {
							this.getPaginatedTable().setObjetoSeleccionado(obj);
						}
					}

				} catch(Exception ex) {
					log(getCasoNavegacion() + "_SeleccionarError:", ex);
					error(getNombrePagina() + " - Seleccionar: " + ex.getMessage());
				}

				if(rk != null) {
					ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
					if(locElementoAnterior == null) {
						return null;
					}
					retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
				}
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	protected boolean validarSeleccionar(Object obj) {
		return true;
	}

	public String btnCancelar_action() {
		String retorno;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.setListaDelCommunication(null);
			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
			if(getPaginatedTable() != null) {
				this.getPaginatedTable().resetearOrdenamientos();
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		// this.getElementoPila().setObjetoSeleccionado(this.getSeleccionado());

		return retorno;
	}

	public String getCurrentRow() {
		// Arreglo temporal para las tablas que aun no implementan la tabla
		// paginada.
		if(this.getPaginatedTable() != null) {
			return this.getPaginatedTable().getTableRowGroup().getRowKey().getRowId();
		} else {
			return this.tableRowGroup1.getRowKey().getRowId();
		}
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		if(this.getPaginatedTable() != null) {
			String sv = getPaginatedTable().getRbSeleccion().getSelectedValue().toString();
			Object objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];
			return objectSeleccionado.equals(lastSelected) ? sv : null;
		} else {
			String sv = (String) radioButton1.getSelectedValue();
			return sv.equals(lastSelected) ? sv : null;
		}

	}

	public void setRBSelected(Object selected) {
		Object objectSeleccionado = null;
		if(this.getObjectListDataProvider() != null && this.getObjectListDataProvider().getObjects().length > 0 && selected != null) {
			objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt((String) selected)];
		}

		if(objectSeleccionado != null) {
			lastSelected = selected;
		}
	}

	/**
	 * // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
	 * 
	 * @param pCadena
	 * @return
	 */
	@Override
	protected int getNroFila(String pCadena) {
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		if(this.getPaginatedTable() != null) {
			this.getElementoPila().setOrdenamiento(this.getPaginatedTable().getTableRowGroup().getTableDataSorter().getSortCriteria());
		}
	}

	public void setearOrdenamiento() {
		if(this.getPaginatedTable() != null) {
			this.getPaginatedTable().getTableRowGroup().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
		}
	}

	/**
	 * Recupera la posicion global del elemento seleccionado. Global pues tiene en cuenta todas las paginas del Admin
	 * 
	 * @param rk
	 * @return
	 */
	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getPaginatedTable() != null ? this.getPaginatedTable().getFiltro().getCantidadPorPagina() : 15l;
		TableRowGroup locTrg = this.getPaginatedTable() != null ? this.getPaginatedTable().getTableRowGroup() : this.getTableRowGroup1();
		long cantRegistros = locTrg.getRowCount();
		boolean encontrado = false;
		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				locTrg.setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = locTrg.getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = getPaginatedTable().getTableRowGroup().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		// Un arreglo para las paginas que no implementan aun la tabla paginada.
		if(this.getPaginatedTable() != null) {
			// if(posicionGlobal <
			// getPaginatedTable().getTableRowGroup().getSortedRowKeys().length){
			if(posicionGlobal >= 0 && this.getObjectListDataProvider().getObjects().length > 0) {
				RowKey rk = getPaginatedTable().getTableRowGroup().getSortedRowKeys()[posicionGlobal.intValue()];
				lastSelected = this.getObjectListDataProvider().getObject(rk);
			}
		} else {
			lastSelected = posicionGlobal.toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long cantRegistrosPorPagina = this.getPaginatedTable().getTableRowGroup().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	/**
	 * Setter for selected
	 * 
	 * @param object
	 *            Value to set the property to Bind this to the checkbox's selected property If the object's value matches selectedValue then the checkbox is
	 *            considered to be selected.
	 */

	public boolean isCurrentRowSelected() {
		TableRowGroup locTrg = this.getPaginatedTable() != null ? this.getPaginatedTable().getTableRowGroup() : this.getTableRowGroup1();
		RowKey rowKey = locTrg.getRowKey();
		return this.getTablePhaseListener().isSelected(rowKey);
	}

	/**
	 * Getter for currentRowSelected
	 * 
	 * @return Boolean true if the checkbox in the current row is selected Bind this property to the row's selected property
	 */

	protected void limpiarTabla() {
		// CAMBIAR: Utilizar el ListDataProvider adecuado.
		if(this.getObjectListDataProvider().getList() != null) {
			this.getObjectListDataProvider().getList().clear();
		}
	}

	public TreeNode getNodoSeleccionado(Tree tr, String idCompleto) {
		TreeNode selectedNode = null;
		if(idCompleto != null) {
			String idNodo = this.getIdSinPrefijo(idCompleto, tr.getId());
			selectedNode = (TreeNode) tr.findComponent(idNodo);
		}
		return selectedNode;
	}

	protected String getIdSinPrefijo(String idCompleto, String idComponente) {
		String retorno = null;
		if(idCompleto != null && idCompleto.length() > 0) {
			retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
		}
		return retorno;
	}

	public String btnExportar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			try {
				TableRowGroup trg;
				List locListaResultados;
				if(this.getPaginatedTable() != null) {
					trg = this.getPaginatedTable().getTableRowGroup();
					FiltroAbstracto filtro = this.getPaginatedTable().getFiltro();

					// Se guardan la cantidad de paginas y el numero de las
					// misma en la paginacion del filtro para su posterior re
					// asignacion
					int auxCantidad = filtro.getCantidadPorPagina();
					int auxNumeroPagina = filtro.getNumeroPagina();

					// Se establece como parametros de busqueda valores nulos
					// para garantizar que busca todos los datos
					filtro.setCantidadPorPagina(null);
					filtro.setNumeroPagina(null);

					// se guarda en una variable los datos de la busqueda
					// completa
					FiltroAbstracto auxFiltro = this.buscar(filtro);
					locListaResultados = auxFiltro.getListaResultados();

					// Se retorna los valores de filtrado al filtro
					filtro.setCantidadPorPagina(auxCantidad);
					filtro.setNumeroPagina(auxNumeroPagina);
				} else {
					trg = this.getTableRowGroup1();
					locListaResultados = this.getListaDelCommunication();
				}

				// JasperPrint jp = ImpresionReporteDinamico.imprimirLista(locListaResultados, trg, "Reporte Din\341mico");
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte");
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

				String formatoExportar = this.getDdFormatoExportar().getSelected() != null ? this.getDdFormatoExportar().getSelected().toString() : "";
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(ConstantesReportes.FORMATO_REPORTE, formatoExportar.equals("PDF") ? ConstantesReportes.PDF : ConstantesReportes.XLSX);
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(locListaResultados, trg, "Reporte Din\341mico");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	// Metodo para habilitar el boton exportar cuando corresponda
	public void habilitarBtnExportar() {
		this.btnExportar.setDisabled(false);
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
	@Override
	public void init() {
		super.init();
		// this.habilitarBtnExportar();
		try {
			_init();

			if(this.getListaDelCommunication() != null) {
				this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			}
			
			if (this.getTablaBusquedaLogs().getCkbBuscarPorLogs().isChecked()) {
				armarOpcionesTablaBusqueda();
			}
			if(this.getPaginatedTable() != null && this.getSessionBean1().getMapaDeListasReportesDelUsuario() != null) {
				List<Reporte> listaReportes = this.getSessionBean1().getMapaDeListasReportesDelUsuario().get(getSerialVersionUID());

				if(listaReportes != null && listaReportes.size() > 0) {
					this.getPaginatedTable().setListaReportes(this.getSessionBean1().getMapaDeListasReportesDelUsuario().get(getSerialVersionUID()));
					this.getPaginatedTable().cargarDropDownReportes();
				} else {
					// no renderizamos el panelgroup
					this.getPaginatedTable().getDdReportes().getParent().setRendered(false);
				}
			}
		} catch(Exception e) {
			log(getCasoNavegacion() + " Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}

	}

	private void armarOpcionesTablaBusqueda() {
		if(this.getSerialVersionUID() != 0 && this.getPaginatedTable() != null) {

			if(!this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().isEmpty()) {
				this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
			}

			this.getTablaBusquedaLogs().getLdpBusquedaLogs().setList(this.getFiltro().getListaBusquedaPorLogs());

			Option[] opPropiedades = armarOpcionesPropiedades();
			getTablaBusquedaLogs().getDdNombrePropiedadBusquedaLogsOptions().setOptions(opPropiedades);

			Option[] opUsuarios = armarOpcionesUsuarios();
			this.getTablaBusquedaLogs().getDdUsuarioBusquedaLogsOptions().setOptions(opUsuarios);

			Option[] opEventos = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(BusquedaPorLog.Evento.values(), "cap");
			this.getTablaBusquedaLogs().getDdEventoBusquedaLogsOptions().setOptions(opEventos);

			this.getTablaBusquedaLogs().getDateTimeConverter1().setPattern("dd/MM/yyyy");
			this.getTablaBusquedaLogs().getDateTimeConverter1().setTimeZone(TimeZone.getDefault());
		}
	}

	protected Option[] armarOpcionesUsuarios() {
		Map<String, Usuario> locMapaUsuarios = this.getComunicationBean().getMapaUsuariosLogs(this.getSerialVersionUID());

		Option[] opUsuarios = new Option[locMapaUsuarios.keySet().size() + 1];
		opUsuarios[0] = new Option("", "");
		int i = 1;
		for(String cadaUsuario : locMapaUsuarios.keySet()) {
			opUsuarios[i++] = new Option(cadaUsuario, cadaUsuario);
		}
		return opUsuarios;
	}

	protected Option[] armarOpcionesPropiedades() {
		List<String> locListaPropiedades = this.getComunicationBean().getListaNombresPropiedadesLogs(this.getSerialVersionUID());

		Option[] opPropiedades = new Option[locListaPropiedades.size() + 1];
		opPropiedades[0] = new Option("", "");
		int i = 1;
		for(String cadaPropiedad : locListaPropiedades) {
			opPropiedades[i++] = new Option(cadaPropiedad, cadaPropiedad);
		}
		return opPropiedades;
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		// Nada por ahora
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	@Override
	public void prerender() {
		super.prerender();

		if(this.getSerialVersionUID() != 0) {
			this.getFiltro().setListaBusquedaPorLogs(this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList());
			this.getTablaBusquedaLogs().getCkbBuscarPorLogs().setValue(this.getFiltro().isBuscarPorLogs());
		}

		this.setTipoSeleccion();
		this.habilitarBtnExportar();
		this.mostrarCantidadRegistrosEncontrados();
		// this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(),
		// this.getTableRowGroup1());

		if(this.getObjectListDataProvider().getList() != null && !this.getObjectListDataProvider().getList().isEmpty()) {
			/*
			 * Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal()); this.seleccionarFila(filaSeleccionada);
			 */
			if(this.getPaginatedTable() != null) {
				lastSelected = this.getPaginatedTable().getObjetoSeleccionado();
			}

		}
		try {
			this._prerender();
			this.ocultarCkbBusquedaPorLogs();
		} catch(Exception e) {
			log(this.getCasoNavegacion() + " prerender Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}

		// Si es la pagina inicial, no renderiza el boton de Seleccionar ni el separador...
		this.getBtnSeleccionar().setValueBinding("rendered", _getValueBinding(armarExpressionEnBean("renderizaSiNoEsPaginaInicial")));
		this.getStSeparadorSeleccionar().setValueBinding("rendered", _getValueBinding(armarExpressionEnBean("renderizaSiNoEsPaginaInicial")));
		// Si no se tiene el permiso adecuado, se le oculta los botones de Agregar, Modificar y Eliminar...
		try {
			deshabilitarBotonesAccion();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void deshabilitarBotonesAccion() throws RemoteException, Exception {
		int cont = 0;
		if(!SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Accion.INSERT)) {
			this.getBtnAgregar().setRendered(false);
			cont++;
		}
		if(!SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Accion.UPDATE)) {
			this.getBtnModificar().setRendered(false);
			cont++;
		}
		if(!SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Accion.DELETE)) {
			this.getBtnEliminar().setRendered(false);
			cont++;
		}
		// Si no se renderizan los 3 componentes, tampoco se renderiza el stSeparador...
		if(cont == 3) {
			this.getStSeparadorAccion().setRendered(false);
		}
	}

	private ValueBinding _getValueBinding(String pPropiedad) {
		return FacesContext.getCurrentInstance().getApplication().createValueBinding(pPropiedad);
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(this.getNombreBean());
		sb.insert(this.getNombreBean().length() - 1, "." + propiedad);
		return sb.toString();
	}

	public Boolean getRenderizaSiNoEsPaginaInicial() {
		return !this.getSessionBean1().getMgrPilas().getPaginaInicial();
	}

	private void setTipoSeleccion() {
		if(this.getElementoPila().isSeleccionMultiple()) {
			this.getPaginatedTable().getTcSeleccionMultiple().setRendered(true);
			this.getPaginatedTable().getTcSeleccion().setRendered(false);
//			this.getBtnEliminar().setRendered(false);
//			this.getBtnModificar().setRendered(false);
		} else if(this.getPaginatedTable() != null) {
			this.getPaginatedTable().getTcSeleccion().setRendered(true);
			this.getPaginatedTable().getTcSeleccionMultiple().setRendered(false);
//			this.getBtnEliminar().setRendered(true);
//			this.getBtnModificar().setRendered(true);
		}
	}

	@Override
	protected boolean puedeSerPaginaInicial() {
		return true;
	}

	@Override
	protected void haciaAtrasTransaccion() {
		ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
		if(ep.getOrdenamiento() != null) {
			this.setearOrdenamiento();
			this.getElementoPila().setOrdenamiento(null);
		}

		if(this.getListaDelCommunication() != null && this.getListaDelCommunication().size() > 0 && this.getRequestBean1().isRefrescarTabla()) {
			try {
				this.refrescarTabla();
			} catch(Exception ex) {
				this.limpiarTabla();
			}
		}
		try {
			this._prerender();
		} catch(Exception e) {
			log(this.getCasoNavegacion() + " Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
	}

	public String getTextoSeparador() {
		return "  |  ";
	}

	public Object getSelected() {
		if(this.getPaginatedTable() != null) {
			String sv = getPaginatedTable().getCkbSeleccion().getSelectedValue().toString();
			Object objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];
			if(this.getPaginatedTable().getSeleccionadosSeleccionMultiple().contains(objectSeleccionado)) {
				return sv;
			}
		}
		return null;
	}

	public void setSelected(Object selected) {
		Object objectSeleccionado = null;

		if(selected == null) {
			objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(this.getPaginatedTable().getTableRowGroup().getRowKey().getRowId())];
			if(objectSeleccionado != null && this.getPaginatedTable().getSeleccionadosSeleccionMultiple().contains(objectSeleccionado)) {
				this.getPaginatedTable().getSeleccionadosSeleccionMultiple().remove(objectSeleccionado);
			}
		} else if(this.getObjectListDataProvider() != null && this.getObjectListDataProvider().getObjects().length > 0) {
			objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt((String) selected)];

			if(objectSeleccionado != null && !this.getPaginatedTable().getSeleccionadosSeleccionMultiple().contains(objectSeleccionado)) {
				this.getPaginatedTable().getSeleccionadosSeleccionMultiple().add(objectSeleccionado);
			}
		}
	}

	public String getSelectedValue() {
		TableRowGroup locTrg = this.getPaginatedTable() != null ? this.getPaginatedTable().getTableRowGroup() : this.getTableRowGroup1();
		RowKey rowKey = locTrg.getRowKey();
		return rowKey.getRowId();
	}

	public void setSelectedValue(int row) {
		Object objectSeleccionado = this.getObjectListDataProvider().getObjects()[row];
		this.getPaginatedTable().getSeleccionadosSeleccionMultiple().remove(objectSeleccionado);
	}

	public String btnSeleccionarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		// int posicionObjetoSeleccionado = 0;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(getCasoNavegacion() + "_SeleccionarPersonaFisicaError:", ex);
				error(getNombrePagina() + " - Seleccionar Persona F\355sica: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()
			// - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaFisica";
			this.limpiarTabla();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfPersonaSeleccionada().setText("");
			this.getSessionBean1().setPersonaSeleccionada(null);
			this.limpiarPersonaEnFiltro();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private void limpiarPersonaEnFiltro() {
		try {
			FiltroAbstracto locFiltro = this.getFiltro();
			Method seterPersona = locFiltro.getClass().getMethod("setPersona", Persona.class);
			if(seterPersona != null) {
				seterPersona.invoke(locFiltro, new Object[] {null});
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void limpiarParcelaEnFiltro() {
		try {
			FiltroAbstracto locFiltro = this.getFiltro();
			Method seterParcela = locFiltro.getClass().getMethod("setParcela", Parcela.class);
			if(seterParcela != null) {
				seterParcela.invoke(locFiltro, new Object[] {null});
			}
		} catch(Exception e) {
		}
	}

	public String btnSeleccionarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		// int posicionObjetoSeleccionado = 0;

		if(ultimo) {

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(getCasoNavegacion() + "_SeleccionarPropietarioError:", ex);
				error(getNombrePagina() + " - Seleccionar Propietario: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()
			// - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		// int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(getCasoNavegacion() + "_SeleccionarParcelaError:", ex);
				error(getNombrePagina() + " - Seleccionar Parcela: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()
			// - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminParcela";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfParcelaSeleccionada().setText("");
			this.getSessionBean1().setParcelaSeleccionada(null);
			this.limpiarParcelaEnFiltro();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	protected String navegarParaSeleccionar(String pCasoNavegacion) {

		String retorno;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione

		if(ultimo) {

			// try {
			// RowKey rk = this.getSeleccionado();
			// if (rk != null) {
			// this.setRowKeySeleccionado(this.getSeleccionado());
			// }
			// } catch (Exception ex) {
			// log(getCasoNavegacion() + "_NavegarA"+pCasoNavegacion, ex);
			// error(getNombrePagina() + " - NavegarA: "+pCasoNavegacion +
			// ex.getMessage());
			// }

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());
			retorno = pCasoNavegacion;
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnAgregarBusqueda_action(ActionEvent actionEvent) {
		if(!this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().isEmpty())
			this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
		this.guardarEstadoObjetosUsados();
		List<BusquedaPorLog> listaLogs = this.getFiltro().getListaBusquedaPorLogs();

		BusquedaPorLog nuevaBusqueda = new BusquedaPorLog();

		listaLogs.add(nuevaBusqueda);
		this.getTablaBusquedaLogs().getLdpBusquedaLogs().setList(listaLogs);
		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarBusqueda_action(ActionEvent actionEvent) {
		if(!this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().isEmpty())
			this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getTablaBusquedaLogs().getSeleccionadoBusquedaLogs();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getTablaBusquedaLogs().getLdpBusquedaLogs().getObjects()[index];
					this.getFiltro().getListaBusquedaPorLogs().remove(obj);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosBusqueda_action(ActionEvent actionEvent) {
		if(!this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().isEmpty())
			this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.getTablaBusquedaLogs().getLdpBusquedaLogs().commitChanges();
				this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().clear();
				this.getFiltro().getListaBusquedaPorLogs().clear();
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	private void ocultarCkbBusquedaPorLogs() {
		try {
			if(this.getSerialVersionUID() != 0 && SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Permiso.Accion.AUDITH)) {
				this.getTablaBusquedaLogs().getCkbBuscarPorLogs().setRendered(true);
			} else {
				this.getTablaBusquedaLogs().getCkbBuscarPorLogs().setRendered(false);
			}
		} catch(RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void valueChangeEventBusquedaLogs(ActionEvent event) {
		if(this.getTablaBusquedaLogs().getCkbBuscarPorLogs().isChecked()) {
			this.getFiltro().setBuscarPorLogs(true);
		} else {
			this.getFiltro().setBuscarPorLogs(false);
			this.getTablaBusquedaLogs().getLdpBusquedaLogs().getList().clear();
			this.getFiltro().getListaBusquedaPorLogs().clear();
		}
	}

	public TablaBusquedaPorLogs getTablaBusquedaLogs() {
		TablaBusquedaPorLogs locTabla = this.getComunicationBean().getMapaTablasLogs().get(this.getSerialVersionUID());
		if(locTabla == null) {
			locTabla = new TablaBusquedaPorLogs(this.getNombreBean());
			this.getComunicationBean().getMapaTablasLogs().put(this.getSerialVersionUID(), locTabla);
		}
		return locTabla;
	}

	public void setTablaBusquedaLogs(TablaBusquedaPorLogs tablaBusquedaLogs) {

	}

	public String getEstiloTabla() {
		return this.getFiltro().isBuscarPorLogs() ? "" : "display:none";
	}

	public boolean getRendered() {
		return this.getFiltro().isBuscarPorLogs();
	}

	protected void borrarListIdAuxPersonas(TextField tf, Persona pPersona) {
		if(tf.getText() == null || tf.getText().toString().length() < 3 || pPersona != null && pPersona.getIdPersona() != -1) {
			this.getSessionBean1().getListaIdPersonas().clear();
		}
	}

	protected void borrarListIdAuxParcelas(TextField tf, Parcela pParcela) {
		if(tf.getText() == null || tf.getText().toString().length() < 3 || pParcela != null && pParcela.getIdParcela() != -1) {
			this.getSessionBean1().getListaIdParcelas().clear();
		}
	}

	protected void borrarListIdAuxBienes(TextField tf, Bien pBien) {
		if(tf.getText() == null || tf.getText().toString().length() < 3 || pBien != null && pBien.getIdBien() != -1) {
			this.getSessionBean1().getListaIdBienes().clear();
		}
	}

	protected void borrarListIdAuxModelosVehiculo(TextField tf, Modelo pModelo) {
		if(tf.getText() == null || tf.getText().toString().length() < 3 || pModelo != null && pModelo.getIdModelo() != -1) {
			this.getSessionBean1().getListaIdModelosVehiculo().clear();
		}
	}

	protected void borrarListIdAuxCodigosCiiu(TextField tf, CodigoCiiu pCodigoCiiu) {
		if(tf.getText() == null || tf.getText().toString().length() < 3 || pCodigoCiiu != null && pCodigoCiiu.getIdCodigoCiiu() != -1) {
			this.getSessionBean1().getListaIdCodigoCiiu().clear();
		}
	}
	
	private MethodExpressionActionListener getActionListener(String pValor) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);
		return listener;
	}
	
	private MethodExpressionActionListener getActionListenerEnBean(String pValor) {
		return this.getActionListener(this.armarExpressionEnBean(pValor));
	}

	public Hyperlink getHpAgregarPaginaAccesoDirecto() {
		Hyperlink hp = new Hyperlink();
		hp.setText("Agregar esta página como acceso directo");
		hp.addActionListener(getActionListenerEnBean("agregarPaginaComoAccesoDirecto"));
		return hp;
	}
	
	public void setHpAgregarPaginaAccesoDirecto(Hyperlink hp){}
	
	public void agregarPaginaComoAccesoDirecto(ActionEvent evento) {
		Long idRecurso = getSerialVersionUID();
		if (idRecurso != null && idRecurso != 0) {
			try {
				this.getComunicationBean().getRemoteSystemParametro().addAccesoDirecto(idRecurso);
				this.getSessionBean1().levantarConfiguracionAccesoDirecto();
				info("Página agregada como acceso directo");
			} catch (TrascenderFrameworkException e) {
				e.printStackTrace();
				error(e.getMessage());
			}
		}
	}
}