/*
 * ABMLicitacion.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */

package muni.compras.ABMLicitacion;

import jasper.ConstantesReportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.IIOException;

import muni.Zip;
import muni.compras.ABMActaApertura.OfertaLicitacionModel;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaPresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.RepresentanteActaApertura;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMLicitacion extends ABMPageBean {

	protected HtmlAjaxCommandButton btnQuitarLinea = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosProveedor = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosLinea = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarProveedor = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarOfertaContratacion = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosOfertaContratacion = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarRepresentante = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosRepresentante = new HtmlAjaxCommandButton();
	protected TextField tfNumero = new TextField();
	protected TextField tfPlazoMantenimientoOferta = new TextField();
	protected TextField tfExtensionMantenimientoOferta = new TextField();
	protected TextField tfFechaEntrega = new TextField();
	protected TextField tfTitulo = new TextField();
	protected TextField tfPresupuestoOficial = new TextField();
	protected TextArea taObjeto = new TextArea();
	protected TextField tfProveedor = new TextField();
	protected TextField tfFechaOferta = new TextField();
	protected TextField tfImporte = new TextField();
	protected TextField tfGarantia = new TextField();
	protected TextField tfPlazo = new TextField();
	protected TextField tfFechaPublicacion = new TextField();
	protected TextField tfFechaCierre = new TextField();
	protected TextField tfHoraCierre = new TextField();
	protected TextField tfMinutoCierre = new TextField();
	protected TextField tfFechaAperturaSobres = new TextField();
	protected TextField tfHoraAperturaSobres = new TextField();
	protected TextField tfMinutoAperturaSobres = new TextField();
	protected TextField tfDigestoMunicipalLicitacion = new TextField();
	protected TextField tfDigestoMunicipalLicitacion2 = new TextField();
	protected TextField tfCargoRepresentante = new TextField();
	protected TextField tfLugar = new TextField();
	protected TextField tfFechaApertura = new TextField();
	protected TextField tfPrecioUnitarioReferencial = new TextField();
	protected TextField tfProveedorAdjudicacion = new TextField();
	private TextField tfCantidad = new TextField();
	protected TextArea taRegistroEscrito = new TextArea();
	protected Button btnSeleccionarOferta = new Button();
	protected Button btnLimpiarOferta = new Button();
	protected Button btnSeleccionarDigestoMunicipalLicitacion = new Button();
	protected HtmlAjaxCommandButton btnLimpiarDigestoMunicipalLicitacion = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarProveedor = new Button();
	protected HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();
	protected Button btnAgregarLinea = new Button();
	protected Button btnAgregarDesdeSolicitud = new Button();
	protected Button btnModificarRenglon = new Button();
	protected Button btnAgregarProveedor = new Button();
	protected Button btnAgregarOfertaContratacion = new Button();
	protected Button btnModificarOfertaContratacion = new Button();
	protected Button btnConsultarOfertaContratacion = new Button();
	protected Button btnAgregarRepresentante = new Button();
	protected Button btnPresupuesto = new Button();
	protected Button btnSeleccionarDigestoMunicipalLicitacion2 = new Button();
	protected HtmlAjaxCommandButton btnLimpiarDigestoMunicipalLicitacion2 = new HtmlAjaxCommandButton();
	protected Label lblFechaEntrega = new Label();
	protected Label lblLineasContratacion = new Label();
	protected Label lblPresupuestoOficial = new Label();
	protected Label lblNumero = new Label();
	protected Label lblObjeto = new Label();
	protected Label lblOferta = new Label();
	protected Label lblTipoLicitacion = new Label();
	protected Label lblTipoContratacion = new Label();
	protected Label lblEstadoLicitacion = new Label();
	protected Label lblComentarios = new Label();
	protected Label lblFechaPublicacion = new Label();
	protected Label lblFechaCierre = new Label();
	protected Label lblHoraCierre = new Label();
	protected Label lblFechaAperturaSobres = new Label();
	protected Label lblHoraAperturaSobres = new Label();
	protected Label lblLugar = new Label();
	protected Label lblFechaApertura = new Label();
	protected Label lblRegistroEscrito = new Label();
	protected Label lblLineasContratacionAdj = new Label();
	protected Label lblDigestoMunicipalLicitacion2 = new Label();
	protected Label lblTotal = new Label();
	protected Label lblPlazoMantenimientoOferta = new Label();
	protected Label lblExtensionMantenimientoOferta = new Label();
	protected Label lblDigestoMunicipalLicitacion = new Label();
	protected Label lblProveedorAdjudicacion = new Label();
	protected Label lblAdjudicarTodas = new Label();
	protected StaticText stNombre = new StaticText();
	protected StaticText stFechaPublicacion = new StaticText();
	protected StaticText stFechaCierre = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected StaticText stSeparador3 = new StaticText();
	protected StaticText stSeparador4 = new StaticText();
	protected StaticText stSeparador5 = new StaticText();
	protected StaticText stFechaAperturaSobres = new StaticText();
	protected StaticText stTotal = new StaticText();
	protected StaticText stDescripcion = new StaticText();
	protected StaticText stCantidadAdj = new StaticText();
	protected StaticText stUnidad = new StaticText();
	protected StaticText stProveedores = new StaticText();
	protected StaticText stRazonSocial = new StaticText();
	protected StaticText stCuit = new StaticText();
	protected StaticText stOfertasContratacion = new StaticText();
	protected StaticText stProveedor = new StaticText();
	protected StaticText stPlazo = new StaticText();
	protected StaticText stTotalPresupuestado = new StaticText();
	protected StaticText stRepresentantes = new StaticText();
	protected StaticText stPersona = new StaticText();
	protected StaticText stBien = new StaticText();
	protected StaticText stBienAdj = new StaticText();
	protected StaticText stPrecioTotal = new StaticText();
	protected StaticText stPrecioTotalAdj = new StaticText();
	protected StaticText stPrecioUnitarioReferencialAdj = new StaticText();
	protected TableColumn tableColumn1 = new TableColumn();
	protected TableColumn tableColumn2 = new TableColumn();
	protected TableColumn tableColumn3 = new TableColumn();
	protected TableColumn tableColumn4 = new TableColumn();
	protected TableColumn tcNombre = new TableColumn();
	protected TableColumn tcDescripcion = new TableColumn();
	protected TableColumn tcCantidad = new TableColumn();
	protected TableColumn tcCantidadAdj = new TableColumn();
	protected TableColumn tcUnidad = new TableColumn();
	protected TableColumn tcRazonSocial = new TableColumn();
	protected TableColumn tcCuit = new TableColumn();
	protected TableColumn tcProveedor = new TableColumn();
	protected TableColumn tcPlazo = new TableColumn();
	protected TableColumn tcTotalPresupuestado = new TableColumn();
	protected TableColumn tcPersona = new TableColumn();
	protected TableColumn tcCargo = new TableColumn();
	protected TableColumn tcBien = new TableColumn();
	protected TableColumn tcBienAdj = new TableColumn();
	protected TableColumn tcPrecioUnitReferencial = new TableColumn();
	protected TableColumn tcPrecioUnitReferencialAdj = new TableColumn();
	protected TableColumn tcPrecioTotal = new TableColumn();
	protected TableColumn tcPrecioTotalAdj = new TableColumn();
	protected TableColumn tcOfertasContratacion = new TableColumn();
	protected TextArea taComentarios = new TextArea();
	protected TextArea taComentariosOferta = new TextArea();
	protected static long idRenglonLicitacion = -1;
	protected ObjectListDataProvider ldpLineasContratacion = new ObjectListDataProvider();
	private ObjectListDataProvider ldpProveedores = new ObjectListDataProvider();
	private ObjectListDataProvider ldpOfertasContratacion = new ObjectListDataProvider();
	private ObjectListDataProvider ldpRepresentantesActaApertura = new ObjectListDataProvider();
	protected Table table1 = new Table();
	protected Table table2 = new Table();
	protected Table table3 = new Table();
	protected Table table4 = new Table();
	protected Table table5 = new Table();
	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected TableRowGroup tableRowGroup2 = new TableRowGroup();
	protected TableRowGroup tableRowGroup3 = new TableRowGroup();
	protected TableRowGroup tableRowGroup4 = new TableRowGroup();
	protected TableRowGroup tableRowGroup5 = new TableRowGroup();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected PanelGroup groupPanel2 = new PanelGroup();
	protected PanelGroup groupPanel3 = new PanelGroup();
	protected PanelGroup groupPanel4 = new PanelGroup();
	protected PanelGroup gpAdjudicarProveedor = new PanelGroup();
	protected RadioButton radioButton1 = new RadioButton();
	protected RadioButton radioButton2 = new RadioButton();
	protected RadioButton radioButton3 = new RadioButton();
	protected RadioButton radioButton4 = new RadioButton();
	protected DropDown ddTipoLicitacion = new DropDown();
	protected DropDown ddOfertasContratacion = new DropDown();
	protected DropDown ddAdjudicarTodas = new DropDown();
	protected SingleSelectOptionsList ddTipoLicitacionDefaultOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddOfertasContratacionDefaultOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddProveedoresDefaultOptions = new SingleSelectOptionsList();
	protected TabSet tabSet1 = new TabSet();
	protected Object lastSelected = null;
	private Object lastSelected2 = null;
	private Object lastSelected3 = null;
	private Object lastSelected4 = null;
	protected RowKey rowKeySeleccionado = null;
	private HiddenField hiddenField = new HiddenField();

	// ***GETTERS & SETTERS

	public Label getLblFechaEntrega() {
		return lblFechaEntrega;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public HtmlAjaxCommandButton getBtnLimpiarDigestoMunicipalLicitacion() {
		return btnLimpiarDigestoMunicipalLicitacion;
	}

	public void setBtnLimpiarDigestoMunicipalLicitacion(HtmlAjaxCommandButton btnLimpiarDigestoMunicipalLicitacion) {
		this.btnLimpiarDigestoMunicipalLicitacion = btnLimpiarDigestoMunicipalLicitacion;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public HtmlAjaxCommandButton getBtnLimpiarDigestoMunicipalLicitacion2() {
		return btnLimpiarDigestoMunicipalLicitacion2;
	}

	public void setBtnLimpiarDigestoMunicipalLicitacion2(HtmlAjaxCommandButton btnLimpiarDigestoMunicipalLicitacion2) {
		this.btnLimpiarDigestoMunicipalLicitacion2 = btnLimpiarDigestoMunicipalLicitacion2;
	}

	public void setLblFechaEntrega(Label lblFechaEntrega) {
		this.lblFechaEntrega = lblFechaEntrega;
	}

	public TextField getTfFechaEntrega() {
		return tfFechaEntrega;
	}

	public void setTfFechaEntrega(TextField tfFechaEntrega) {
		this.tfFechaEntrega = tfFechaEntrega;
	}

	public StaticText getStTotalPresupuestado() {
		return stTotalPresupuestado;
	}

	public void setStTotalPresupuestado(StaticText stTotalPresupuestado) {
		this.stTotalPresupuestado = stTotalPresupuestado;
	}

	public TableColumn getTcTotalPresupuestado() {
		return tcTotalPresupuestado;
	}

	public void setTcTotalPresupuestado(TableColumn tcTotalPresupuestado) {
		this.tcTotalPresupuestado = tcTotalPresupuestado;
	}

	public Label getLblHoraAperturaSobres() {
		return lblHoraAperturaSobres;
	}

	public void setLblHoraAperturaSobres(Label lblHoraAperturaSobres) {
		this.lblHoraAperturaSobres = lblHoraAperturaSobres;
	}

	public TextField getTfHoraAperturaSobres() {
		return tfHoraAperturaSobres;
	}

	public void setTfHoraAperturaSobres(TextField tfHoraAperturaSobres) {
		this.tfHoraAperturaSobres = tfHoraAperturaSobres;
	}

	public TextField getTfMinutoAperturaSobres() {
		return tfMinutoAperturaSobres;
	}

	public void setTfMinutoAperturaSobres(TextField tfMinutoAperturaSobres) {
		this.tfMinutoAperturaSobres = tfMinutoAperturaSobres;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public DropDown getDdAdjudicarTodas() {
		return ddAdjudicarTodas;
	}

	public void setDdAdjudicarTodas(DropDown ddAdjudicarTodas) {
		this.ddAdjudicarTodas = ddAdjudicarTodas;
	}

	public SingleSelectOptionsList getDdProveedoresDefaultOptions() {
		if(this.getListaDelCommunicationOfertaContratacion() != null && !this.getListaDelCommunicationOfertaContratacion().isEmpty()) {
			List locListaOfertas = this.getListaDelCommunicationOfertaContratacion();
			Set<Proveedor> locListaProveedores = new HashSet<Proveedor>();
			for(Object cadaObject : locListaOfertas) {
				OfertaContratacion cadaOferta = (OfertaContratacion) cadaObject;
				locListaProveedores.add(cadaOferta.getProveedor());
			}

			Option[] opProveedores = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(locListaProveedores.toArray(), "");

			ddProveedoresDefaultOptions.setOptions(opProveedores);
		}
		return ddProveedoresDefaultOptions;
	}

	public void setDdProveedoresDefaultOptions(SingleSelectOptionsList ddProveedoresDefaultOptions) {
		this.ddProveedoresDefaultOptions = ddProveedoresDefaultOptions;
	}

	public Label getLblHoraCierre() {
		return lblHoraCierre;
	}

	public void setLblHoraCierre(Label lblHoraCierre) {
		this.lblHoraCierre = lblHoraCierre;
	}

	public TextField getTfHoraCierre() {
		return tfHoraCierre;
	}

	public void setTfHoraCierre(TextField tfHoraCierre) {
		this.tfHoraCierre = tfHoraCierre;
	}

	public TextField getTfMinutoCierre() {
		return tfMinutoCierre;
	}

	public void setTfMinutoCierre(TextField tfMinutoCierre) {
		this.tfMinutoCierre = tfMinutoCierre;
	}

	public Label getLblAdjudicarTodas() {
		return lblAdjudicarTodas;
	}

	public void setLblAdjudicarTodas(Label lblAdjudicarTodas) {
		this.lblAdjudicarTodas = lblAdjudicarTodas;
	}

	public Label getLblDigestoMunicipalLicitacion2() {
		return lblDigestoMunicipalLicitacion2;
	}

	public void setLblDigestoMunicipalLicitacion2(Label lblDigestoMunicipalLicitacion2) {
		this.lblDigestoMunicipalLicitacion2 = lblDigestoMunicipalLicitacion2;
	}

	public TextField getTfDigestoMunicipalLicitacion2() {
		return tfDigestoMunicipalLicitacion2;
	}

	public void setTfDigestoMunicipalLicitacion2(TextField tfDigestoMunicipalLicitacion2) {
		this.tfDigestoMunicipalLicitacion2 = tfDigestoMunicipalLicitacion2;
	}

	public Button getBtnSeleccionarDigestoMunicipalLicitacion2() {
		return btnSeleccionarDigestoMunicipalLicitacion2;
	}

	public void setBtnSeleccionarDigestoMunicipalLicitacion2(Button btnSeleccionarDigestoMunicipalLicitacion2) {
		this.btnSeleccionarDigestoMunicipalLicitacion2 = btnSeleccionarDigestoMunicipalLicitacion2;
	}

	public Button getBtnPresupuesto() {
		return btnPresupuesto;
	}

	public void setBtnPresupuesto(Button btnPresupuesto) {
		this.btnPresupuesto = btnPresupuesto;
	}

	public Label getLblProveedorAdjudicacion() {
		return lblProveedorAdjudicacion;
	}

	public void setLblProveedorAdjudicacion(Label lblProveedorAdjudicacion) {
		this.lblProveedorAdjudicacion = lblProveedorAdjudicacion;
	}

	public TextField getTfProveedorAdjudicacion() {
		return tfProveedorAdjudicacion;
	}

	public void setTfProveedorAdjudicacion(TextField tfProveedorAdjudicacion) {
		this.tfProveedorAdjudicacion = tfProveedorAdjudicacion;
	}

	public HiddenField getHiddenField() {
		return hiddenField;
	}

	public void setHiddenField(HiddenField hiddenField) {
		this.hiddenField = hiddenField;
	}

	public boolean isHayOfertas() {
		return this.getListaDelCommunicationOfertaContratacion() != null && !this.getListaDelCommunicationOfertaContratacion().isEmpty();
	}

	public Option[] getOpcionesAdjudicables() {
		System.out.println("**** GETOPCIONESADJUDICABLES");
		Option[] locOpciones = null;
		if(this.getListaDelCommunicationOfertaContratacion() != null) {
			Bien locBien = (Bien) this.hiddenField.getValue();

			List<OfertaContratacion> listaOfertas = this.getListaDelCommunicationOfertaContratacion();
			Set<LineaOfertaContratacion> locListaLineasOferta = new HashSet<LineaOfertaContratacion>();

			for(OfertaContratacion cadaOferta : listaOfertas) {
				System.out.println("******* cadaOferta");
				for(LineaOfertaContratacion lineaOC : cadaOferta.getListaLineasOfertasContratacion()) {
					if(lineaOC.getBien().equals(locBien)) {
						System.out.println("lineaOC: " + lineaOC);
						boolean b = locListaLineasOferta.add(lineaOC);
						System.out.println("**** ID de la linea " + lineaOC.getIdLineaOfertaContratacion());
						System.out.println(" *** SE AGREGO?: " + b);
						// se agrega la primer oferta nomas. las demas no entran
						// al set xq al
						// ser todas nuevas y no estar persistidas tienen el
						// mismo id (-1),
						// y el equals de LineaOfertaContratacion usa el id para
						// comprararlas entre sí.
					}
				}
			}
			for(int i = 0; i < locListaLineasOferta.toArray().length; i++) {
				System.out.println("***** locListaLineasOferta.toArray()[" + i + "]: " + locListaLineasOferta.toArray()[i]);
			}
			locOpciones = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(locListaLineasOferta.toArray(), "");

			for(int i = 0; i < locOpciones.length; i++) {
				System.out.println("**** locOpciones: " + locOpciones[i]);
			}

		}
		return locOpciones;
	}

	public void setSelected(Object pSelected) {
		if(this.getDdAdjudicarTodas().getSelected().toString().trim().isEmpty()) {
			// es una seleccion de oferta normal, no a través del dropdown
			// adjudicar todas
			Bien locBien = (Bien) hiddenField.getValue();
			if(locBien != null) {
				if(pSelected != null && pSelected.toString().trim().isEmpty()) {
					// Se seleccionó vacio
					this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().remove(locBien);
				} else {
					for(Object o : this.getListaDelCommunicationOfertaContratacion()) {
						OfertaContratacion oferta = (OfertaContratacion) o;
						for(LineaOfertaContratacion cadaLineaOferta : oferta.getListaLineasOfertasContratacion()) {
							if(locBien.toString().equals(cadaLineaOferta.getBien().toString()) && cadaLineaOferta.toString().equals(pSelected.toString())) {
								this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().put(locBien, cadaLineaOferta);
								return;
							}
						}
					}
				}
			}
		}
	}

	public Object getSelected() {
		return this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().get(hiddenField.getValue());
	}

	public PanelGroup getGpAdjudicarProveedor() {
		return gpAdjudicarProveedor;
	}

	public void setGpAdjudicarProveedor(PanelGroup gpAdjudicarProveedor) {
		this.gpAdjudicarProveedor = gpAdjudicarProveedor;
	}

	public Label getLblLineasContratacionAdj() {
		return lblLineasContratacionAdj;
	}

	public void setLblLineasContratacionAdj(Label lblLineasContratacionAdj) {
		this.lblLineasContratacionAdj = lblLineasContratacionAdj;
	}

	public StaticText getStBienAdj() {
		return stBienAdj;
	}

	public void setStBienAdj(StaticText stBienAdj) {
		this.stBienAdj = stBienAdj;
	}

	public StaticText getStCantidadAdj() {
		return stCantidadAdj;
	}

	public void setStCantidadAdj(StaticText stCantidadAdj) {
		this.stCantidadAdj = stCantidadAdj;
	}

	public StaticText getStPrecioTotalAdj() {
		return stPrecioTotalAdj;
	}

	public void setStPrecioTotalAdj(StaticText stPrecioTotalAdj) {
		this.stPrecioTotalAdj = stPrecioTotalAdj;
	}

	public StaticText getStPrecioUnitarioReferencialAdj() {
		return stPrecioUnitarioReferencialAdj;
	}

	public void setStPrecioUnitarioReferencialAdj(StaticText stPrecioUnitarioReferencialAdj) {
		this.stPrecioUnitarioReferencialAdj = stPrecioUnitarioReferencialAdj;
	}

	public Table getTable5() {
		return table5;
	}

	public void setTable5(Table table5) {
		this.table5 = table5;
	}

	public TableRowGroup getTableRowGroup5() {
		return tableRowGroup5;
	}

	public void setTableRowGroup5(TableRowGroup tableRowGroup5) {
		this.tableRowGroup5 = tableRowGroup5;
	}

	public TableColumn getTcBienAdj() {
		return tcBienAdj;
	}

	public void setTcBienAdj(TableColumn tcBienAdj) {
		this.tcBienAdj = tcBienAdj;
	}

	public TableColumn getTcCantidadAdj() {
		return tcCantidadAdj;
	}

	public void setTcCantidadAdj(TableColumn tcCantidadAdj) {
		this.tcCantidadAdj = tcCantidadAdj;
	}

	public TableColumn getTcPrecioTotalAdj() {
		return tcPrecioTotalAdj;
	}

	public void setTcPrecioTotalAdj(TableColumn tcPrecioTotalAdj) {
		this.tcPrecioTotalAdj = tcPrecioTotalAdj;
	}

	public TableColumn getTcPrecioUnitReferencialAdj() {
		return tcPrecioUnitReferencialAdj;
	}

	public void setTcPrecioUnitReferencialAdj(TableColumn tcPrecioUnitReferencialAdj) {
		this.tcPrecioUnitReferencialAdj = tcPrecioUnitReferencialAdj;
	}

	public TableColumn getTcOfertasContratacion() {
		// tcOfertasContratacion.setRendered(this.isHayOfertas());
		return tcOfertasContratacion;
	}

	public void setTcOfertasContratacion(TableColumn tcOfertasContratacion) {
		this.tcOfertasContratacion = tcOfertasContratacion;
	}

	public DropDown getDdOfertasContratacion() {
		return ddOfertasContratacion;
	}

	public void setDdOfertasContratacion(DropDown ddOfertasContratacion) {
		this.ddOfertasContratacion = ddOfertasContratacion;
	}

	public SingleSelectOptionsList getDdOfertasContratacionDefaultOptions() {
		return ddOfertasContratacionDefaultOptions;
	}

	public void setDdOfertasContratacionDefaultOptions(SingleSelectOptionsList ddOfertasContratacionDefaultOptions) {
		this.ddOfertasContratacionDefaultOptions = ddOfertasContratacionDefaultOptions;
	}

	public ObjectListDataProvider getLdpRepresentantesActaApertura() {
		return ldpRepresentantesActaApertura;
	}

	public void setLdpRepresentantesActaApertura(ObjectListDataProvider ldpRepresentantesActaApertura) {
		this.ldpRepresentantesActaApertura = ldpRepresentantesActaApertura;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public StaticText getStPrecioTotal() {
		return stPrecioTotal;
	}

	public void setStPrecioTotal(StaticText stPrecioTotal) {
		this.stPrecioTotal = stPrecioTotal;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public TableColumn getTcPrecioTotal() {
		return tcPrecioTotal;
	}

	public void setTcPrecioTotal(TableColumn tcPrecioTotal) {
		this.tcPrecioTotal = tcPrecioTotal;
	}

	public TableColumn getTcPrecioUnitReferencial() {
		return tcPrecioUnitReferencial;
	}

	public void setTcPrecioUnitReferencial(TableColumn tcPrecioUnitReferencial) {
		this.tcPrecioUnitReferencial = tcPrecioUnitReferencial;
	}

	public TextField getTfPrecioUnitarioReferencial() {
		return tfPrecioUnitarioReferencial;
	}

	public void setTfPrecioUnitarioReferencial(TextField tfPrecioUnitarioReferencial) {
		this.tfPrecioUnitarioReferencial = tfPrecioUnitarioReferencial;
	}

	public Label getLblFechaApertura() {
		return lblFechaApertura;
	}

	public void setLblFechaApertura(Label lblFechaApertura) {
		this.lblFechaApertura = lblFechaApertura;
	}

	public Label getLblLugar() {
		return lblLugar;
	}

	public void setLblLugar(Label lblLugar) {
		this.lblLugar = lblLugar;
	}

	public Label getLblRegistroEscrito() {
		return lblRegistroEscrito;
	}

	public void setLblRegistroEscrito(Label lblRegistroEscrito) {
		this.lblRegistroEscrito = lblRegistroEscrito;
	}

	public TextArea getTaRegistroEscrito() {
		return taRegistroEscrito;
	}

	public void setTaRegistroEscrito(TextArea taRegistroEscrito) {
		this.taRegistroEscrito = taRegistroEscrito;
	}

	public TextField getTfFechaApertura() {
		return tfFechaApertura;
	}

	public void setTfFechaApertura(TextField tfFechaApertura) {
		this.tfFechaApertura = tfFechaApertura;
	}

	public TextField getTfLugar() {
		return tfLugar;
	}

	public void setTfLugar(TextField tfLugar) {
		this.tfLugar = tfLugar;
	}

	public TextField getTfCargoRepresentante() {
		return tfCargoRepresentante;
	}

	public void setTfCargoRepresentante(TextField tfCargoRepresentante) {
		this.tfCargoRepresentante = tfCargoRepresentante;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	public TableColumn getTcCargo() {
		return tcCargo;
	}

	public void setTcCargo(TableColumn tcCargo) {
		this.tcCargo = tcCargo;
	}

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public Button getBtnAgregarRepresentante() {
		return btnAgregarRepresentante;
	}

	public void setBtnAgregarRepresentante(Button btnAgregarRepresentante) {
		this.btnAgregarRepresentante = btnAgregarRepresentante;
	}

	public HtmlAjaxCommandButton getBtnQuitarRepresentante() {
		return btnQuitarRepresentante;
	}

	public void setBtnQuitarRepresentante(HtmlAjaxCommandButton btnQuitarRepresentante) {
		this.btnQuitarRepresentante = btnQuitarRepresentante;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosRepresentante() {
		return btnQuitarTodosRepresentante;
	}

	public void setBtnQuitarTodosRepresentante(HtmlAjaxCommandButton btnQuitarTodosRepresentante) {
		this.btnQuitarTodosRepresentante = btnQuitarTodosRepresentante;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public Table getTable4() {
		return table4;
	}

	public void setTable4(Table table4) {
		this.table4 = table4;
	}

	public PanelGroup getGroupPanel4() {
		return groupPanel4;
	}

	public void setGroupPanel4(PanelGroup groupPanel4) {
		this.groupPanel4 = groupPanel4;
	}

	public Object getLastSelected3() {
		return lastSelected3;
	}

	public void setLastSelected3(Object lastSelected3) {
		this.lastSelected3 = lastSelected3;
	}

	public Object getLastSelected4() {
		return lastSelected4;
	}

	public void setLastSelected4(Object lastSelected4) {
		this.lastSelected4 = lastSelected4;
	}

	public RadioButton getRadioButton4() {
		return radioButton4;
	}

	public void setRadioButton4(RadioButton radioButton4) {
		this.radioButton4 = radioButton4;
	}

	public TableRowGroup getTableRowGroup4() {
		return tableRowGroup4;
	}

	public void setTableRowGroup4(TableRowGroup tableRowGroup4) {
		this.tableRowGroup4 = tableRowGroup4;
	}

	public StaticText getStRepresentantes() {
		return stRepresentantes;
	}

	public void setStRepresentantes(StaticText stRepresentantes) {
		this.stRepresentantes = stRepresentantes;
	}

	public HtmlAjaxCommandButton getBtnQuitarOfertaContratacion() {
		return btnQuitarOfertaContratacion;
	}

	public void setBtnQuitarOfertaContratacion(HtmlAjaxCommandButton btnQuitarOfertaContratacion) {
		this.btnQuitarOfertaContratacion = btnQuitarOfertaContratacion;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosOfertaContratacion() {
		return btnQuitarTodosOfertaContratacion;
	}

	public void setBtnQuitarTodosOfertaContratacion(HtmlAjaxCommandButton btnQuitarTodosOfertaContratacion) {
		this.btnQuitarTodosOfertaContratacion = btnQuitarTodosOfertaContratacion;
	}

	public StaticText getStSeparador4() {
		return stSeparador4;
	}

	public void setStSeparador4(StaticText stSeparador4) {
		this.stSeparador4 = stSeparador4;
	}

	public Button getBtnAgregarOfertaContratacion() {
		return btnAgregarOfertaContratacion;
	}

	public void setBtnAgregarOfertaContratacion(Button btnAgregarOfertaContratacion) {
		this.btnAgregarOfertaContratacion = btnAgregarOfertaContratacion;
	}

	public Button getBtnConsultarOfertaContratacion() {
		return btnConsultarOfertaContratacion;
	}

	public void setBtnConsultarOfertaContratacion(Button btnConsultarOfertaContratacion) {
		this.btnConsultarOfertaContratacion = btnConsultarOfertaContratacion;
	}

	public Button getBtnModificarOfertaContratacion() {
		return btnModificarOfertaContratacion;
	}

	public void setBtnModificarOfertaContratacion(Button btnModificarOfertaContratacion) {
		this.btnModificarOfertaContratacion = btnModificarOfertaContratacion;
	}

	public TableColumn getTcPlazo() {
		return tcPlazo;
	}

	public void setTcPlazo(TableColumn tcPlazo) {
		this.tcPlazo = tcPlazo;
	}

	public ObjectListDataProvider getLdpOfertasContratacion() {
		return ldpOfertasContratacion;
	}

	public void setLdpOfertasContratacion(ObjectListDataProvider ldpOfertasContratacion) {
		this.ldpOfertasContratacion = ldpOfertasContratacion;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton radioButton3) {
		this.radioButton3 = radioButton3;
	}

	public StaticText getStOfertasContratacion() {
		return stOfertasContratacion;
	}

	public void setStOfertasContratacion(StaticText stOfertasContratacion) {
		this.stOfertasContratacion = stOfertasContratacion;
	}

	public StaticText getStPlazo() {
		return stPlazo;
	}

	public void setStPlazo(StaticText stPlazo) {
		this.stPlazo = stPlazo;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public Button getBtnAgregarProveedor() {
		return btnAgregarProveedor;
	}

	public void setBtnAgregarProveedor(Button btnAgregarProveedor) {
		this.btnAgregarProveedor = btnAgregarProveedor;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosProveedor() {
		return btnQuitarTodosProveedor;
	}

	public void setBtnQuitarTodosProveedor(HtmlAjaxCommandButton btnQuitarTodosProveedor) {
		this.btnQuitarTodosProveedor = btnQuitarTodosProveedor;
	}

	public HtmlAjaxCommandButton getBtnQuitarProveedor() {
		return btnQuitarProveedor;
	}

	public void setBtnQuitarProveedor(HtmlAjaxCommandButton btnQuitarProveedor) {
		this.btnQuitarProveedor = btnQuitarProveedor;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public StaticText getStCuit() {
		return stCuit;
	}

	public void setStCuit(StaticText stCuit) {
		this.stCuit = stCuit;
	}

	public StaticText getStRazonSocial() {
		return stRazonSocial;
	}

	public void setStRazonSocial(StaticText stRazonSocial) {
		this.stRazonSocial = stRazonSocial;
	}

	public TableColumn getTcCuit() {
		return tcCuit;
	}

	public void setTcCuit(TableColumn tcCuit) {
		this.tcCuit = tcCuit;
	}

	public TableColumn getTcRazonSocial() {
		return tcRazonSocial;
	}

	public void setTcRazonSocial(TableColumn tcRazonSocial) {
		this.tcRazonSocial = tcRazonSocial;
	}

	public Object getLastSelected2() {
		return lastSelected2;
	}

	public void setLastSelected2(Object lastSelected2) {
		this.lastSelected2 = lastSelected2;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public ObjectListDataProvider getLdpProveedores() {
		return ldpProveedores;
	}

	public void setLdpProveedores(ObjectListDataProvider ldpProveedores) {
		this.ldpProveedores = ldpProveedores;
	}

	public Button getBtnModificarRenglon() {
		return btnModificarRenglon;
	}

	public void setBtnModificarRenglon(Button btnModificarRenglon) {
		this.btnModificarRenglon = btnModificarRenglon;
	}

	public Button getBtnAgregarDesdeSolicitud() {
		return btnAgregarDesdeSolicitud;
	}

	public void setBtnAgregarDesdeSolicitud(Button btnAgregarDesdeSolicitud) {
		this.btnAgregarDesdeSolicitud = btnAgregarDesdeSolicitud;
	}

	public static long getIdRenglonLicitacion() {
		return idRenglonLicitacion;
	}

	public static void setIdRenglonLicitacion(long idRenglonLicitacion) {
		ABMLicitacion.idRenglonLicitacion = idRenglonLicitacion;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public Label getLblComentarios() {
		return lblComentarios;
	}

	public void setLblComentarios(Label lblComentarios) {
		this.lblComentarios = lblComentarios;
	}

	public Label getLblDigestoMunicipalLicitacion() {
		return lblDigestoMunicipalLicitacion;
	}

	public void setLblDigestoMunicipalLicitacion(Label lblDigestoMunicipalLicitacion) {
		this.lblDigestoMunicipalLicitacion = lblDigestoMunicipalLicitacion;
	}

	public Label getLblEstadoLicitacion() {
		return lblEstadoLicitacion;
	}

	public void setLblEstadoLicitacion(Label lblEstadoLicitacion) {
		this.lblEstadoLicitacion = lblEstadoLicitacion;
	}

	public Label getLblExtensionMantenimientoOferta() {
		return lblExtensionMantenimientoOferta;
	}

	public void setLblExtensionMantenimientoOferta(Label lblExtensionMantenimientoOferta) {
		this.lblExtensionMantenimientoOferta = lblExtensionMantenimientoOferta;
	}

	public Label getLblFechaAperturaSobres() {
		return lblFechaAperturaSobres;
	}

	public void setLblFechaAperturaSobres(Label lblFechaAperturaSobres) {
		this.lblFechaAperturaSobres = lblFechaAperturaSobres;
	}

	public Label getLblFechaCierre() {
		return lblFechaCierre;
	}

	public void setLblFechaCierre(Label lblFechaCierre) {
		this.lblFechaCierre = lblFechaCierre;
	}

	public Label getLblFechaPublicacion() {
		return lblFechaPublicacion;
	}

	public void setLblFechaPublicacion(Label lblFechaPublicacion) {
		this.lblFechaPublicacion = lblFechaPublicacion;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public Label getLblObjeto() {
		return lblObjeto;
	}

	public void setLblObjeto(Label lblObjeto) {
		this.lblObjeto = lblObjeto;
	}

	public Label getLblOferta() {
		return lblOferta;
	}

	public void setLblOferta(Label lblOferta) {
		this.lblOferta = lblOferta;
	}

	public Label getLblPlazoMantenimientoOferta() {
		return lblPlazoMantenimientoOferta;
	}

	public void setLblPlazoMantenimientoOferta(Label lblPlazoMantenimientoOferta) {
		this.lblPlazoMantenimientoOferta = lblPlazoMantenimientoOferta;
	}

	public Label getLblPresupuestoOficial() {
		return lblPresupuestoOficial;
	}

	public void setLblPresupuestoOficial(Label lblPresupuestoOficial) {
		this.lblPresupuestoOficial = lblPresupuestoOficial;
	}

	public Label getLblLineasContratacion() {
		return lblLineasContratacion;
	}

	public void setLblLineasContratacion(Label lblLineasContratacion) {
		this.lblLineasContratacion = lblLineasContratacion;
	}

	public Label getLblTipoContratacion() {
		return lblTipoContratacion;
	}

	public void setLblTipoContratacion(Label lblTipoContratacion) {
		this.lblTipoContratacion = lblTipoContratacion;
	}

	public Label getLblTipoLicitacion() {
		return lblTipoLicitacion;
	}

	public void setLblTipoLicitacion(Label lblTipoLicitacion) {
		this.lblTipoLicitacion = lblTipoLicitacion;
	}

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	public StaticText getStFechaAperturaSobres() {
		return stFechaAperturaSobres;
	}

	public void setStFechaAperturaSobres(StaticText stFechaAperturaSobres) {
		this.stFechaAperturaSobres = stFechaAperturaSobres;
	}

	public StaticText getStFechaCierre() {
		return stFechaCierre;
	}

	public void setStFechaCierre(StaticText stFechaCierre) {
		this.stFechaCierre = stFechaCierre;
	}

	public StaticText getStFechaPublicacion() {
		return stFechaPublicacion;
	}

	public void setStFechaPublicacion(StaticText stFechaPublicacion) {
		this.stFechaPublicacion = stFechaPublicacion;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStUnidad() {
		return stUnidad;
	}

	public void setStUnidad(StaticText stUnidad) {
		this.stUnidad = stUnidad;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public TableColumn getTcUnidad() {
		return tcUnidad;
	}

	public void setTcUnidad(TableColumn tcUnidad) {
		this.tcUnidad = tcUnidad;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tf) {
		this.tfNumero = tf;
	}

	public TextField getTfPlazoMantenimientoOferta() {
		return tfPlazoMantenimientoOferta;
	}

	public void setTfPlazoMantenimientoOferta(TextField tfPlazoMantenimientoOferta) {
		this.tfPlazoMantenimientoOferta = tfPlazoMantenimientoOferta;
	}

	public TextField getTfExtensionMantenimientoOferta() {
		return tfExtensionMantenimientoOferta;
	}

	public void setTfExtensionMantenimientoOferta(TextField tfExtensionMantenimientoOferta) {
		this.tfExtensionMantenimientoOferta = tfExtensionMantenimientoOferta;
	}

	public TextField getTfTitulo() {
		return tfTitulo;
	}

	public void setTfTitulo(TextField tf) {
		this.tfTitulo = tf;
	}

	public TextField getTfPresupuestoOficial() {
		return tfPresupuestoOficial;
	}

	public void setTfPresupuestoOficial(TextField tfPresupuestoOficial) {
		this.tfPresupuestoOficial = tfPresupuestoOficial;
	}

	public TextArea getTfObjeto() {
		return taObjeto;
	}

	public void setTfObjeto(TextArea tfObjeto) {
		this.taObjeto = tfObjeto;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tfProveedor) {
		this.tfProveedor = tfProveedor;
	}

	public TextField getTfFechaOferta() {
		return tfFechaOferta;
	}

	public void setTfFechaOferta(TextField tfFechaOferta) {
		this.tfFechaOferta = tfFechaOferta;
	}

	public TextField getTfImporte() {
		return tfImporte;
	}

	public void setTfImporte(TextField tfImporte) {
		this.tfImporte = tfImporte;
	}

	public TextField getTfGarantia() {
		return tfGarantia;
	}

	public void setTfGarantia(TextField tfGarantia) {
		this.tfGarantia = tfGarantia;
	}

	public TextField getTfPlazo() {
		return tfPlazo;
	}

	public void setTfPlazo(TextField tfPlazo) {
		this.tfPlazo = tfPlazo;
	}

	public TextField getTfFechaPublicacion() {
		return tfFechaPublicacion;
	}

	public void setTfFechaPublicacion(TextField tfFechaPublicacion) {
		this.tfFechaPublicacion = tfFechaPublicacion;
	}

	public TextField getTfFechaCierre() {
		return tfFechaCierre;
	}

	public void setTfFechaCierre(TextField tfFechaCierre) {
		this.tfFechaCierre = tfFechaCierre;
	}

	public TextField getTfFechaAperturaSobres() {
		return tfFechaAperturaSobres;
	}

	public void setTfFechaAperturaSobres(TextField tfFechaAperturaSobres) {
		this.tfFechaAperturaSobres = tfFechaAperturaSobres;
	}

	public Button getBtnSeleccionarOferta() {
		return btnSeleccionarOferta;
	}

	public void setBtnSeleccionarOferta(Button btnSeleccionarOferta) {
		this.btnSeleccionarOferta = btnSeleccionarOferta;
	}

	public Button getBtnLimpiarOferta() {
		return btnLimpiarOferta;
	}

	public void setBtnLimpiarOferta(Button btnLimpiarOferta) {
		this.btnLimpiarOferta = btnLimpiarOferta;
	}

	public TextField getTfDigestoMunicipalLicitacion() {
		return tfDigestoMunicipalLicitacion;
	}

	public void setTfDigestoMunicipalLicitacion(TextField tfDigestoMunicipalLicitacion) {
		this.tfDigestoMunicipalLicitacion = tfDigestoMunicipalLicitacion;
	}

	public Button getBtnSeleccionarDigestoMunicipalLicitacion() {
		return btnSeleccionarDigestoMunicipalLicitacion;
	}

	public void setBtnSeleccionarDigestoMunicipalLicitacion(Button btnSeleccionarDigestoMunicipalLicitacion) {
		this.btnSeleccionarDigestoMunicipalLicitacion = btnSeleccionarDigestoMunicipalLicitacion;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button btnSeleccionarProveedor) {
		this.btnSeleccionarProveedor = btnSeleccionarProveedor;
	}

	public TextArea getTaComentarios() {
		return taComentarios;
	}

	public void setTaComentarios(TextArea taComentarios) {
		this.taComentarios = taComentarios;
	}

	public TextArea getTaComentariosOferta() {
		return taComentariosOferta;
	}

	public void setTaComentariosOferta(TextArea taComentariosOferta) {
		this.taComentariosOferta = taComentariosOferta;
	}

	public ObjectListDataProvider getLdpLineasContratacion() {
		return ldpLineasContratacion;
	}

	public void setLdpLineasContratacion(ObjectListDataProvider ldpLineasContratacion) {
		this.ldpLineasContratacion = ldpLineasContratacion;
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

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public Button getBtnAgregarLinea() {
		return btnAgregarLinea;
	}

	public void setBtnAgregarLinea(Button btnAgregarLinea) {
		this.btnAgregarLinea = btnAgregarLinea;
	}

	public HtmlAjaxCommandButton getBtnQuitarLinea() {
		return btnQuitarLinea;
	}

	public void setBtnQuitarLinea(HtmlAjaxCommandButton btnQuitarLinea) {
		this.btnQuitarLinea = btnQuitarLinea;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosLinea() {
		return btnQuitarTodosLinea;
	}

	public void setBtnQuitarTodosLinea(HtmlAjaxCommandButton btnQuitarTodosLinea) {
		this.btnQuitarTodosLinea = btnQuitarTodosLinea;
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

	public DropDown getDdTipoLicitacion() {
		return ddTipoLicitacion;
	}

	public void setDdTipoLicitacion(DropDown ddTipoLicitacion) {
		this.ddTipoLicitacion = ddTipoLicitacion;
	}

	public SingleSelectOptionsList getDdTipoLicitacionDefaultOptions() {
		return ddTipoLicitacionDefaultOptions;
	}

	public void setDdTipoLicitacionDefaultOptions(SingleSelectOptionsList ddTipoLicitacionDefaultOptions) {
		this.ddTipoLicitacionDefaultOptions = ddTipoLicitacionDefaultOptions;
	}

	/*
	 * private DropDown ddEstadoLicitacion = new DropDown(); private SingleSelectOptionsList ddEstadoLicitacionDefaultOptions = new SingleSelectOptionsList();
	 * 
	 * public DropDown getDdEstadoLicitacion() { return ddEstadoLicitacion; }
	 * 
	 * public void setDdEstadoLicitacion(DropDown ddEstadoLicitacion) { this.ddEstadoLicitacion = ddEstadoLicitacion; }
	 * 
	 * public SingleSelectOptionsList getDdEstadoLicitacionDefaultOptions() { return ddEstadoLicitacionDefaultOptions; }
	 * 
	 * public void setDdEstadoLicitacionDefaultOptions(SingleSelectOptionsList ddEstadoLicitacionDefaultOptions) { this.ddEstadoLicitacionDefaultOptions =
	 * ddEstadoLicitacionDefaultOptions; }
	 */

	public TabSet getTabSet1() {
		return tabSet1;
	}

	public void setTabSet1(TabSet tabSet1) {
		this.tabSet1 = tabSet1;
	}

	public StaticText getStProveedores() {
		return stProveedores;
	}

	public void setStProveedores(StaticText stProveedores) {
		this.stProveedores = stProveedores;
	}

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if(selected != null) {
			lastSelected3 = selected;
		}
	}

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	public Object getRBSelected4() {
		String sv = (String) radioButton4.getSelectedValue();
		return sv.equals(lastSelected4) ? sv : null;
	}

	public void setRBSelected4(Object selected) {
		if(selected != null) {
			lastSelected4 = selected;
		}
	}

	public String getCurrentRow4() {
		return tableRowGroup4.getRowKey().getRowId();
	}

	public void setCurrentRow4(int row) {
	}

	public String getCurrentRow5() {
		return tableRowGroup5.getRowKey().getRowId();
	}

	public void setCurrentRow5(int row) {
	}

	protected ObjectListDataProvider getObjectListDataProviderProveedores() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpProveedores();
	}

	protected List getListaDelCommunicationProveedores() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaProveedoresContratacion();
	}

	protected void setListaDelCommunicationProveedores(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaProveedoresContratacion(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderOfertasContratacion() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpOfertasContratacion();
	}

	protected List getListaDelCommunicationOfertaContratacion() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaOfertasLicitaciones();
	}

	protected void setListaDelCommunicationOfertaContratacion(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaOfertasLicitaciones(lista);
	}

	/*
	 * protected List getListaDelCommunicationLineasContratacion() { // CAMBIAR: Utilizar la Lista del Comunication que corresponda return
	 * this.getCommunicationComprasBean().getListaLineasContratacion(); }
	 */

	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaLineasContratacion(lista);
	}

	// ***METHODS***
	public ABMLicitacion() {
	}

	@Override
	protected void _init() throws Exception {

		getTable1().setClearSortButton(true);
		getTable2().setClearSortButton(true);
		getTable3().setClearSortButton(true);
		getTable4().setClearSortButton(true);
		getTable5().setClearSortButton(true);

		Option[] opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Tipo.values(), "may");
		ddTipoLicitacionDefaultOptions.setOptions(opTipo);

		/*
		 * Option[] opEstado = null; opEstado = this.getApplicationBean1().getMgrDropDown ().armarArrayOptions(Licitacion.Estado.values(), "may");
		 * ddEstadoLicitacionDefaultOptions.setOptions(opEstado);
		 */
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunicationProveedores() != null) {
			this.getObjectListDataProviderProveedores().setList(this.getListaDelCommunicationProveedores());
		}
		if(this.getListaDelCommunicationOfertaContratacion() != null) {
			this.getObjectListDataProviderOfertasContratacion().setList(this.getListaDelCommunicationOfertaContratacion());
		}

		this.getObjectListDataProviderRepresentantesActaApertura().setList(this.getListaDelCommunicationRepresentantesActaApertura());
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		// Como este metodo se ejecuta la primera vez que se entra a la pagina,
		// limpiamos el mapa de adjucicaciones que puede tener elementos
		// anteriores.
		this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().clear();

		int ind = 0;
		ep.getObjetos().add(ind++, new Contratacion()); // 0
		ep.getObjetos().add(ind++, new ArrayList());// 1 Lista de Lineas
													// Contratacion
		ep.getObjetos().add(ind++, new ArrayList()); // 2 Lista de Proveedores
		ep.getObjetos().add(ind++, new DigestoMunicipal()); // 3 Digesto
		ep.getObjetos().add(ind++, new ArrayList()); // 4 Lista Representantes
														// ActaApertura
		ep.getObjetos().add(ind++, new ArrayList()); // 5 Lista Ofertas
														// Contratacion
		ep.getObjetos().add(ind++, new HashMap<Bien, LineaOfertaContratacion>()); // 6
																					// Mapa
																					// para
																					// Selected
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, null);// 7 Id de la pestaña seleccionada
		ep.getObjetos().add(ind++, null);// 8, para adjudicar un proveedor de
											// forma directa.
		ep.getObjetos().add(ind++, new DigestoMunicipal());// 9 Digesto cuarta
															// pestaña
		ep.getObjetos().add(ind++, false);// 10 Boolean para saber cual de los 2
											// digestos selecciono.
		ep.getObjetos().add(ind++, new Integer(0)); // X
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Contratacion contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class); // 0
		List listaLineas = this.obtenerObjetoDelElementoPila(1, ArrayList.class); // 1
		List listaProveedores = this.obtenerObjetoDelElementoPila(2, ArrayList.class); // 2
		DigestoMunicipal digestoContratacion = this.obtenerObjetoDelElementoPila(3, DigestoMunicipal.class);// 3
		List listaRepresentantesActa = this.obtenerObjetoDelElementoPila(4, ArrayList.class); // 4
		List listaOfertasContratacion = this.obtenerObjetoDelElementoPila(5, ArrayList.class); // 5

		DigestoMunicipal digestoContratacionAdjudicacion = this.obtenerObjetoDelElementoPila(9, DigestoMunicipal.class);// 9

		Contratacion.Tipo tipoLicitacion = null;

		this.getObjectListDataProvider().commitChanges();
		this.setListaDelCommunication(listaLineas);
		contratacion.setListaLineasContratacion(listaLineas);

		contratacion.setListaProveedoresAutorizados(listaProveedores);
		this.getObjectListDataProviderProveedores().commitChanges();
		this.setListaDelCommunicationProveedores(listaProveedores);

		contratacion.setListaOfertasContratacion(listaOfertasContratacion);
		this.getObjectListDataProviderOfertasContratacion().commitChanges();
		this.setListaDelCommunicationOfertaContratacion(listaOfertasContratacion);

		// Licitacion.Estado estadoLicitacion = null;

		if(digestoContratacion.getIdDigestoMunicipal() == -1) {
			digestoContratacion = null;
		}
		contratacion.setDigestoMunicipal(digestoContratacion);

		if(digestoContratacionAdjudicacion.getIdDigestoMunicipal() == -1) {
			digestoContratacionAdjudicacion = null;
		}
		if(contratacion.getAdjudicacion() != null) {
			contratacion.getAdjudicacion().setDigestoMunicipal(digestoContratacionAdjudicacion);
		}

		Object objeto = taObjeto.getText();
		Object presupuestoOficial = tfPresupuestoOficial.getText();
		Object tipoLicitacionSelected = ddTipoLicitacion.getSelected();
		Object plazoMantenimientoOferta = tfPlazoMantenimientoOferta.getText();
		Object extensionMantenimientoOferta = tfExtensionMantenimientoOferta.getText();
		Object fechaEntrega = tfFechaEntrega.getText();
		// Object estadoLicitacionSelected = ddEstadoLicitacion.getSelected();
		Object comentarios = taComentarios.getText();
		Object fechaPublicacion = tfFechaPublicacion.getText();
		Object fechaCierre = tfFechaCierre.getText();
		Object horaCierre = this.getTfHoraCierre().getText();
		Object minutoCierre = this.getTfMinutoCierre().getText();
		Object fechaAperturaSobres = tfFechaAperturaSobres.getText();
		Object horaAperturaSobres = this.getTfHoraAperturaSobres().getText();
		Object minutoAperturaSobre = this.getTfMinutoAperturaSobres().getText();

		/*
		 * Object fechaOferta = tfFechaOferta.getText(); Object importe = tfImporte.getText(); Object garantia = tfGarantia.getText(); Object comentariosOferta
		 * = taComentariosOferta.getText(); Object plazo = tfPlazo.getText();
		 */

		try {
			contratacion.setNumero(this.getTextFieldValueInteger(getTfNumero()));
		} catch(Exception e) {
		}

		if(objeto != null && objeto != "") {
			contratacion.setObjeto(objeto.toString());
		} else {
			contratacion.setObjeto(null);
		}
		if(presupuestoOficial != null && presupuestoOficial != "") {
			contratacion.setPresupuestoOficial(Conversor.getDoubleDeString(presupuestoOficial.toString()));
		} else {
			contratacion.setPresupuestoOficial(null);
		}
		System.out.println("tipoLicitacionSelected: " + tipoLicitacionSelected);
		if(tipoLicitacionSelected != null && tipoLicitacionSelected.toString().length() > 0 && !tipoLicitacionSelected.toString().equals("NULL")) {
			tipoLicitacion = Contratacion.Tipo.valueOf(tipoLicitacionSelected.toString());
		} else {
			tipoLicitacion = null;
		}
		if(tipoLicitacion != null) {
			contratacion.setTipo(tipoLicitacion);
		} else {
			contratacion.setTipo(null);
		}
		if(plazoMantenimientoOferta != null && plazoMantenimientoOferta != "") {
			contratacion.setPlazoOferta(plazoMantenimientoOferta.toString());
		} else {
			contratacion.setPlazoOferta(null);
		}
		if(extensionMantenimientoOferta != null && extensionMantenimientoOferta != "") {
			contratacion.setExtensionOferta(extensionMantenimientoOferta.toString());
		} else {
			contratacion.setExtensionOferta(null);
		}
		if(fechaEntrega != null && fechaEntrega != "") {
			contratacion.setFechaEntrega(Conversor.getFechaCortaDeString(fechaEntrega.toString()));
		} else {
			contratacion.setFechaEntrega(null);
		}

		/*
		 * if ((estadoLicitacionSelected != null) && (estadoLicitacionSelected.toString().length() > 0)) { estadoLicitacion =
		 * Licitacion.Estado.valueOf(estadoLicitacionSelected.toString()); } else { estadoLicitacion = null; } if (estadoLicitacion != null) {
		 * licitacion.setEstado(estadoLicitacion); } else { licitacion.setEstado(null); }
		 */

		if(comentarios != null && comentarios != "") {
			contratacion.setComentarios(comentarios.toString());
		} else {
			contratacion.setComentarios(null);
		}
		if(fechaPublicacion != null && fechaPublicacion != "") {
			contratacion.setFechaPublicacion(Conversor.getFechaCortaDeString(fechaPublicacion.toString()));
		} else {
			contratacion.setFechaPublicacion(null);
		}
		if(fechaCierre != null && fechaCierre != "") {
			contratacion.setFechaCierre(Conversor.getFechaCortaDeString(fechaCierre.toString()));
		} else {
			contratacion.setFechaCierre(null);
		}

		// Si no se puso fecha, tampoco se pone hora
		if(horaCierre != null && !horaCierre.toString().isEmpty() && contratacion.getFechaCierre() != null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(contratacion.getFechaCierre());
			cal.set(java.util.Calendar.HOUR_OF_DAY, Integer.valueOf(horaCierre.toString()));
			contratacion.setFechaCierre(cal.getTime());
		}

		// Si no se puso fecha, tampoco se pone hora
		if(minutoCierre != null && !minutoCierre.toString().isEmpty() && contratacion.getFechaCierre() != null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(contratacion.getFechaCierre());
			cal.set(java.util.Calendar.MINUTE, Integer.valueOf(minutoCierre.toString()));
			contratacion.setFechaCierre(cal.getTime());
		}

		if(fechaAperturaSobres != null && fechaAperturaSobres != "") {
			contratacion.setFechaAperturaSobres(Conversor.getFechaCortaDeString(fechaAperturaSobres.toString()));
		} else {
			contratacion.setFechaAperturaSobres(null);
		}

		// Si no se puso fecha, tampoco se pone hora
		if(horaAperturaSobres != null && !horaAperturaSobres.toString().isEmpty() && contratacion.getFechaAperturaSobres() != null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(contratacion.getFechaAperturaSobres());
			cal.set(java.util.Calendar.HOUR_OF_DAY, Integer.valueOf(horaAperturaSobres.toString()));
			contratacion.setFechaAperturaSobres(cal.getTime());
		}

		// Si no se puso fecha, tampoco se pone hora
		if(minutoAperturaSobre != null && !minutoAperturaSobre.toString().isEmpty() && contratacion.getFechaAperturaSobres() != null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(contratacion.getFechaAperturaSobres());
			cal.set(java.util.Calendar.MINUTE, Integer.valueOf(minutoCierre.toString()));
			contratacion.setFechaAperturaSobres(cal.getTime());
		}

		Object lugar = this.getTfLugar().getText();
		Object fechaApertura = this.getTfFechaApertura().getText();
		Object regEscrito = this.getTaRegistroEscrito().getText();

		if((lugar != null && lugar != "") || (fechaApertura != null && fechaApertura != "") || (regEscrito != null && regEscrito != "") || (!listaRepresentantesActa.isEmpty())) {

			if(contratacion.getActaApertura() == null) {
				contratacion.setActaApertura(new ActaApertura());
			}
		}

		if(contratacion.getActaApertura() != null) {
			if(lugar != null) {
				contratacion.getActaApertura().setLugar(lugar.toString());
			}
			if(regEscrito != null) {
				contratacion.getActaApertura().setRegistroEscrito(regEscrito.toString());
			}

			for(Object o : listaRepresentantesActa) {
				RepresentanteActaApertura r = (RepresentanteActaApertura) o;
				r.setActaApertura(contratacion.getActaApertura());
			}

			contratacion.getActaApertura().setListaRepresentantes(listaRepresentantesActa);
			this.getObjectListDataProviderRepresentantesActaApertura().commitChanges();
			this.setListaDelCommunicationRepresentantesActaApertura(listaRepresentantesActa);
		}

		for(Object o : listaLineas) {
			LineaContratacion cadaLinea = (LineaContratacion) o;
			cadaLinea.setLineaOfertaContratacionAdjudicada(this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().get(cadaLinea.getBien()));
		}

		// Se guarda la pestaña actual seleccionada
		String idTabActual = this.getTabSet1().getSelected();

		// ind = 0;
		this.getElementoPila().getObjetos().set(0, contratacion);
		this.getElementoPila().getObjetos().set(1, listaLineas);
		this.getElementoPila().getObjetos().set(2, listaProveedores);
		this.getElementoPila().getObjetos().set(3, digestoContratacion);
		this.getElementoPila().getObjetos().set(4, listaRepresentantesActa);
		this.getElementoPila().getObjetos().set(5, listaOfertasContratacion);
		this.getElementoPila().getObjetos().set(7, idTabActual);
		this.getElementoPila().getObjetos().set(9, digestoContratacionAdjudicacion);

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Contratacion contratacion = null;

		List listaLineas = this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		List listaProveedores = this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		DigestoMunicipal digestoContratacion = this.obtenerObjetoDelElementoPila(3, DigestoMunicipal.class);
		List listaRepresentantesActa = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		List<OfertaContratacion> listaOfertasContratacion = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		// Adjudicacion adjudicacion = null;
		LineaContratacion lineaContratacion = null;
		DigestoMunicipal digesto = null;
		// OfertaLicitacion oferta = null;
		// DigestoMunicipal digestoAdjudicacion = null;
		SolicitudSuministro solSuministro = null;
		DigestoMunicipal digestoContratacionAdjudicacion = this.obtenerObjetoDelElementoPila(9, DigestoMunicipal.class);

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
			List listaSeleccionados = this.getRequestBean1().getObjetosSeleccionMultiple();

			if(listaSeleccionados.get(0) instanceof LineaSolicitudSuministro) {
				contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);

				boolean mostrarWarn = false;
				for(Object cadaObject : this.getRequestBean1().getObjetosSeleccionMultiple()) {
					LineaSolicitudSuministro cadaLineaSolSum = (LineaSolicitudSuministro) cadaObject;
					contratacion.addLineaSolicitudSuministro(cadaLineaSolSum);

					for(LineaPresupuestoSolicitudSuministro cadaLineaPresupuesto : cadaLineaSolSum.getListaLineasPresupuestoSolicitudSuministro()) {
						OfertaContratacion locOferta = contratacion.getOfertaPorProveedor(cadaLineaPresupuesto.getPresupuestoSolicitud().getProveedor());
						if(locOferta != null) {
							System.out.println("**** HAY OFERTA ****");
							LineaOfertaContratacion locLineaOferta = locOferta.getLineaOfertaPorBien(cadaLineaPresupuesto.getBien());

							if(locLineaOferta != null && !locLineaOferta.getPrecioUnitario().equals(cadaLineaPresupuesto.getPrecioUnitario())) {
								System.out.println("**** HAY LINEA ****");
								// Hay q pensar una forma de permitirle al usuario elegir que precio quiere.
								locLineaOferta.setPrecioUnitario(cadaLineaPresupuesto.getPrecioUnitario());
								mostrarWarn = true;
							} else if(locLineaOferta == null) {
								System.out.println("**** NO HAY LINEA ****");
								LineaOfertaContratacion nuevaLinea = new LineaOfertaContratacion();
								nuevaLinea.setOfertaContratacion(locOferta);
								nuevaLinea.setPrecioUnitario(cadaLineaPresupuesto.getPrecioUnitario());
								nuevaLinea.setPrecioTotal(cadaLineaPresupuesto.getPrecioTotal());
								// nuevaLinea.setLineaContratacion(cadaLineaSolSum.getLineaContratacion()); ARREGLAR ESTO
								nuevaLinea.setLineaContratacion(cadaLineaSolSum.getListaLineaContratacion().get(0)); // ???
								locOferta.getListaLineasOfertasContratacion().add(nuevaLinea);
							}
						} else {
							System.out.println("**** NI OFERTA NI LINEA ****");
							OfertaContratacion nuevaOferta = new OfertaContratacion();
							nuevaOferta.setContratacion(contratacion);
							nuevaOferta.setPlazo(cadaLineaPresupuesto.getPresupuestoSolicitud().getPlazoMantenimiento());
							nuevaOferta.setProveedor(cadaLineaPresupuesto.getPresupuestoSolicitud().getProveedor());
							LineaOfertaContratacion nuevaLinea = new LineaOfertaContratacion();
							nuevaLinea.setOfertaContratacion(nuevaOferta);
							nuevaLinea.setPrecioUnitario(cadaLineaPresupuesto.getPrecioUnitario());
							nuevaLinea.setPrecioTotal(cadaLineaPresupuesto.getPrecioTotal());
							// nuevaLinea.setLineaContratacion(cadaLineaSolSum.getLineaContratacion()); ARREGLAR ESTO
							nuevaLinea.setLineaContratacion(cadaLineaSolSum.getListaLineaContratacion().get(0)); // ???
							nuevaOferta.getListaLineasOfertasContratacion().add(nuevaLinea);

							listaOfertasContratacion.add(nuevaOferta);
							this.getElementoPila().getObjetos().set(5, listaOfertasContratacion);
						}
					}
				}

				if(mostrarWarn)
					warn("Se ha sobreescrito el precio unitario de las Líneas de Ofertas de los Proveedores por el de sus respectivos Presupuestos de las Líneas de Solicitud seleccionadas.");

				this.getElementoPila().getObjetos().set(1, contratacion.getListaLineasContratacion());

			} else if(listaSeleccionados.get(0) instanceof Proveedor) {
				// Si no habia un 8, borramos el proveedor anterior
				this.getElementoPila().getObjetos().set(getCantidadObjetosUsados() - 1, new Integer(0));
				this.getElementoPila().getObjetos().set(8, null);

				boolean hayRepetidos = false;
				for(Object cadaObject : listaSeleccionados) {
					Proveedor cadaProveedor = (Proveedor) cadaObject;

					try {
						cadaProveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(cadaProveedor.getIdProveedor());
					} catch(Exception e) {
						e.printStackTrace();
					}

					if(!listaProveedores.contains(cadaProveedor)) {
						listaProveedores.add(cadaProveedor);
					} else {
						hayRepetidos = true;
					}
				}

				if(hayRepetidos) {
					warn("Uno o más Proveedores seleccionados ya se encuentran en la lista");
				}
				this.getElementoPila().getObjetos().set(2, listaProveedores);
			}
		}

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object seleccionado = this.getRequestBean1().getRespuestaABM();

			if(seleccionado instanceof OfertaContratacion) {
				OfertaContratacion ofertaContratacion = (OfertaContratacion) seleccionado;

				contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);

				boolean yaExiste = false;

				/*
				 * for (Object o : contratacion.getListaOfertasContratacion()) { yaExiste = false; OfertaContratacion oferta = (OfertaContratacion) o;
				 * 
				 * if (ofertaContratacion.equals(oferta)) { listaOfertasContratacion.remove(oferta);
				 * 
				 * } }
				 */

				if(contratacion.getListaOfertasContratacion().contains(ofertaContratacion)) {
					listaOfertasContratacion.remove(ofertaContratacion);
				}
				contratacion.getListaOfertasContratacion().add(ofertaContratacion);
				this.getElementoPila().getObjetos().set(5, contratacion.getListaOfertasContratacion());
			}

		}

		contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);
		listaLineas = this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		listaProveedores = this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		digestoContratacion = this.obtenerObjetoDelElementoPila(3, DigestoMunicipal.class);
		listaRepresentantesActa = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		listaOfertasContratacion = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(8);
		digestoContratacionAdjudicacion = this.obtenerObjetoDelElementoPila(9, DigestoMunicipal.class);

		this.getTfNumero().setText(contratacion.getNumero());
		this.getTfObjeto().setText(contratacion.getObjeto());
		this.getTfPresupuestoOficial().setText(Conversor.getStringDeDouble(contratacion.getPresupuestoOficial()));

		this.getDdTipoLicitacion().setSelected(Util.getEnumNameFromString(String.valueOf(contratacion.getTipo())));
		this.getTfPlazoMantenimientoOferta().setText(contratacion.getPlazoOferta());
		this.getTfExtensionMantenimientoOferta().setText(contratacion.getExtensionOferta());
		this.getTaComentarios().setText(contratacion.getComentarios());
		this.getTfFechaPublicacion().setText(Conversor.getStringDeFechaCorta(contratacion.getFechaPublicacion()));
		this.getTfFechaCierre().setText(Conversor.getStringDeFechaCorta(contratacion.getFechaCierre()));
		this.getTfFechaAperturaSobres().setText(Conversor.getStringDeFechaCorta(contratacion.getFechaAperturaSobres()));
		this.getTfFechaEntrega().setText(Conversor.getStringDeFechaCorta(contratacion.getFechaEntrega()));

		Double totalLineasContratacion = 0d;
		for(LineaContratacion cadaLinea : contratacion.getListaLineasContratacion()) {
			totalLineasContratacion = totalLineasContratacion + cadaLinea.getMontoTotalReferencial();
		}
		this.getStTotal().setText(totalLineasContratacion);

		if(contratacion.getFechaCierre() != null) {
			java.util.Calendar calFechaCierre = java.util.Calendar.getInstance();
			calFechaCierre.setTime(contratacion.getFechaCierre());
			int hora = calFechaCierre.get(java.util.Calendar.HOUR_OF_DAY);
			int minutos = calFechaCierre.get(java.util.Calendar.MINUTE);
			this.getTfHoraCierre().setText(hora != 0 ? hora : "");
			this.getTfMinutoCierre().setText(hora != 0 ? minutos : "");
		}

		if(contratacion.getFechaAperturaSobres() != null) {
			java.util.Calendar calFechaApertura = java.util.Calendar.getInstance();
			calFechaApertura.setTime(contratacion.getFechaAperturaSobres());
			int hora = calFechaApertura.get(java.util.Calendar.HOUR_OF_DAY);
			int minutos = calFechaApertura.get(java.util.Calendar.MINUTE);
			this.getTfHoraAperturaSobres().setText(hora != 0 ? hora : "");
			this.getTfMinutoAperturaSobres().setText(hora != 0 ? minutos : "");
		}

		if(digestoContratacion != null && digestoContratacion.getIdDigestoMunicipal() != -1) {
			this.getTfDigestoMunicipalLicitacion().setText(digestoContratacion);
		}
		if(digestoContratacionAdjudicacion != null && digestoContratacionAdjudicacion.getIdDigestoMunicipal() != -1) {
			this.getTfDigestoMunicipalLicitacion2().setText(digestoContratacionAdjudicacion);
		}

		if(contratacion.getActaApertura() != null) {

			this.getTfLugar().setText(contratacion.getActaApertura().getLugar());
			this.getTaRegistroEscrito().setText(contratacion.getActaApertura().getRegistroEscrito());
		}

		if(proveedor != null) {
			this.getTfProveedorAdjudicacion().setText(proveedor.toString());
		}

		this.getObjectListDataProvider().setList(listaLineas);
		this.setListaDelCommunication(listaLineas);

		this.getObjectListDataProviderProveedores().setList(listaProveedores);
		this.setListaDelCommunicationProveedores(listaProveedores);

		this.getObjectListDataProviderRepresentantesActaApertura().setList(listaRepresentantesActa);
		this.setListaDelCommunicationRepresentantesActaApertura(listaRepresentantesActa);

		this.getObjectListDataProviderOfertasContratacion().setList(listaOfertasContratacion);
		this.setListaDelCommunicationOfertaContratacion(listaOfertasContratacion);

		// Se setea la ultima pestaña seleccionada
		String idTab = (String) this.obtenerObjetoDelElementoPila(7);
		if(idTab != null) {
			this.getTabSet1().setSelected(idTab);
		}

		if((listaProveedores != null && !listaProveedores.isEmpty()) || (listaOfertasContratacion != null && !listaOfertasContratacion.isEmpty())) {
			this.getGpAdjudicarProveedor().setRendered(false);
		}

		this.getCommunicationComprasBean().setProveedorSeleccionado(null);
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLineasContratacion();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaLineasContratacion();
	}

	protected void setListaDelCommunication(ArrayList lista) {
		this.getCommunicationComprasBean().setListaLineasContratacion(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderRepresentantesActaApertura() {
		return this.getLdpRepresentantesActaApertura();
	}

	protected List getListaDelCommunicationRepresentantesActaApertura() {
		return this.getCommunicationComprasBean().getListaRepresentantesActaApertura();
	}

	protected void setListaDelCommunicationRepresentantesActaApertura(List lista) {
		this.getCommunicationComprasBean().setListaRepresentantesActaApertura(lista);
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
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
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderProveedores().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			rk = this.getObjectListDataProviderOfertasContratacion().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado4() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup4");
			rk = this.getObjectListDataProviderRepresentantesActaApertura().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	/*
	 * public String btnSeleccionarDigestoMunicipalAdjudicacion_action() { String retorno = null; boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	 * 
	 * if (ultimo) {
	 * 
	 * this.guardarEstadoObjetosUsados(); this.getRequestBean1().setTipoSeleccion("DIGESTO_ADJUDICACION");
	 * this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	 * 
	 * // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente. retorno = "AdminDigestoMunicipal"; } else { retorno =
	 * this.prepararCaducidad(); } return retorno;
	 * 
	 * }
	 */
	/*
	 * public String btnLimpiarDigestoMunicipalAdjudicacion_action() { String retorno = null; boolean ultimo = this.ultimoElementoPilaDeSubSesion(); if (ultimo)
	 * { // CAMBIAR: Especificar objeto this.limpiarObjeto(5, DigestoMunicipal.class, this.getTfDigestoMunicipalAdjudicacion());
	 * this.guardarEstadoObjetosUsados(); } else { retorno = this.prepararCaducidad(); } return retorno; }
	 */

	public String btnSeleccionarDigestoMunicipalLicitacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			if(this.getTabSet1().getSelected().equals("two")) {
				this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, 3);
				this.getElementoPila().getObjetos().set(10, false);
			} else {
				this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, 9);
				this.getElementoPila().getObjetos().set(10, true);
			}
			// this.getRequestBean1().setTipoSeleccion("DIGESTO_LICITACION");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminDigestoMunicipal";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;

	}

	public String btnLimpiarDigestoMunicipalLicitacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			if(this.getTabSet1().getSelected().equals("two")) {
				this.limpiarObjeto(3, DigestoMunicipal.class, this.getTfDigestoMunicipalLicitacion());
			} else {
				this.limpiarObjeto(9, DigestoMunicipal.class, this.getTfDigestoMunicipalLicitacion2());
			}
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarOferta_action() {
		return navegarParaSeleccionar("AdminOfertaLicitacion");
	}

	/*
	 * public String btnLimpiarOferta_action() { String retorno = null; boolean ultimo = this.ultimoElementoPilaDeSubSesion(); if (ultimo) { // CAMBIAR:
	 * Especificar objeto this.limpiarObjeto(4, OfertaLicitacion.class, this.getTfOferta()); this.guardarEstadoObjetosUsados(); } else { retorno =
	 * this.prepararCaducidad(); } return retorno; }
	 */

	public String btnAgregarLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();
		// Proveedor proveedor = (Proveedor)
		// this.obtenerObjetoDelElementoPila(1, Proveedor.class);

		if(ultimo) {
			List listaProveedores = (List) this.obtenerObjetoDelElementoPila(2);
			this.getRequestBean1().setObjetoABM(listaProveedores);
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.getCommunicationComprasBean().setListaLineasSolSumContratacion(new ArrayList());
			retorno = "AgregarLineaContratacion";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					LineaContratacion locLinea = (LineaContratacion) obj;
					Contratacion locContratacion = (Contratacion) this.obtenerObjetoDelElementoPila(0);
					boolean tieneOferta = false;

					if(!locContratacion.getListaOfertasContratacion().isEmpty()) {

						for(OfertaContratacion cadaOferta : locContratacion.getListaOfertasContratacion()) {

							for(LineaOfertaContratacion cadaLineaOferta : cadaOferta.getListaLineasOfertasContratacion()) {

								if(cadaLineaOferta.getLineaContratacion().equals(locLinea)) {
									tieneOferta = true;
									break;
								}
							}
						}
					}

					if(!tieneOferta) {
						this.getListaDelCommunication().remove(obj);
					} else {
						warn("No se puede quitar una Línea que posee Ofertas/Presupuestos");
					}

				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				Contratacion locContratacion = (Contratacion) this.obtenerObjetoDelElementoPila(0);

				if(this.getListaDelCommunicationOfertaContratacion() != null && this.getListaDelCommunicationOfertaContratacion().isEmpty()
						&& locContratacion.getListaOfertasContratacion().isEmpty()) {
					this.getListaDelCommunication().clear();
				} else {
					warn("No se puede quitar Líneas que posean Ofertas/Presupuestos en la Contrataci\363n");
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		if(ultimo) {

			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminProveedor";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				System.out.println("RK: " + rk);
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderProveedores().getObjects()[index];
					Proveedor locProveedor = (Proveedor) obj;
					Contratacion locContratacion = (Contratacion) this.obtenerObjetoDelElementoPila(0);
					boolean tieneOferta = false;

					if(!locContratacion.getListaOfertasContratacion().isEmpty()) {
						for(OfertaContratacion cadaOferta : locContratacion.getListaOfertasContratacion()) {

							if(cadaOferta.getProveedor().equals(locProveedor)) {
								tieneOferta = true;
								break;
							}
						}
					}

					if(!tieneOferta) {
						System.out.println("Proveedor seleccionado: " + obj);
						this.getListaDelCommunicationProveedores().remove(obj);
						this.getObjectListDataProviderProveedores().setList(this.getListaDelCommunicationProveedores());
					} else {
						warn("No se puede quitar Proveedores que posean Ofertas/Presupuestos en la Contrataci\363n");
					}

					if(this.getListaDelCommunicationProveedores().isEmpty()) {
						this.getCommunicationComprasBean().setMapaProveedores(null);
						this.getGpAdjudicarProveedor().setRendered(true);
					}
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				Contratacion locContratacion = (Contratacion) this.obtenerObjetoDelElementoPila(0);

				if(this.getListaDelCommunicationOfertaContratacion() != null && this.getListaDelCommunicationOfertaContratacion().isEmpty()
						&& locContratacion.getListaOfertasContratacion().isEmpty()) {

					this.getListaDelCommunicationProveedores().clear();
					this.getElementoPila().getObjetos().set(2, null);
					this.getCommunicationComprasBean().setMapaProveedores(null);
					this.getGpAdjudicarProveedor().setRendered(true);
				} else {
					warn("No se puede quitar Proveedores que posean Ofertas/Presupuestos en la Contrataci\363n");
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

	public String btnAgregarOfertaContratacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		Contratacion contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);

		if(ultimo) {
			OfertaContratacion ofertaContratacion = new OfertaContratacion();
			for(LineaContratacion r : contratacion.getListaLineasContratacion()) {
				LineaOfertaContratacion orl = new LineaOfertaContratacion();
				orl.setPrecioUnitario(0.00);
				orl.setLineaContratacion(r);
				ofertaContratacion.getListaLineasOfertasContratacion().add(orl);
			}
			if(this.getCommunicationComprasBean().getMapaProveedores() != null) {
				this.getCommunicationComprasBean().getMapaProveedores().clear();
			}
			ofertaContratacion.setContratacion(contratacion);
			this.getRequestBean1().setObjetoABM(ofertaContratacion);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new OfertaLicitacionModel().new AgregarController());

			retorno = "ABMOfertaLicitacion";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarOfertaContratacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					OfertaContratacion ofertaContratacion = (OfertaContratacion) this.getObjectListDataProviderOfertasContratacion().getObjects()[index];

					Contratacion contratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);

					if(ofertaContratacion.getListaLineasOfertasContratacion().size() < contratacion.getListaLineasContratacion().size()) {
						System.out.println("la lista de lineas de la oferta es menor a la de lineas de contratacion");
						for(LineaContratacion cadaLineaContratacion : contratacion.getListaLineasContratacion()) {
							boolean tieneOferta = false;

							for(LineaOfertaContratacion cadaLinea : ofertaContratacion.getListaLineasOfertasContratacion()) {
								if(cadaLinea.getLineaContratacion().getBien().equals(cadaLineaContratacion.getBien())) {
									tieneOferta = true;
								}
							}

							if(!tieneOferta) {
								System.out.println("NO TIENE OFERTA: " + cadaLineaContratacion.toString());
								LineaOfertaContratacion nuevaLineaOferta = new LineaOfertaContratacion();
								nuevaLineaOferta.setOfertaContratacion(ofertaContratacion);
								nuevaLineaOferta.setPrecioUnitario(0.00);
								nuevaLineaOferta.setLineaContratacion(cadaLineaContratacion);
								ofertaContratacion.getListaLineasOfertasContratacion().add(nuevaLineaOferta);
							}
						}

					}
					this.getRequestBean1().setObjetoABM(ofertaContratacion);
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setAbmController(new OfertaLicitacionModel().new ModificarController());

					retorno = "ABMOfertaLicitacion";
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

	public String btnConsultarOfertaContratacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.getObjectListDataProviderOfertasContratacion().getObjects()[index];

					this.getRequestBean1().setObjetoABM(ofertaLicitacion);
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setAbmController(new OfertaLicitacionModel().new ConsultarController());

					retorno = "ABMOfertaLicitacion";

				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarOfertaContratacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderOfertasContratacion().getObjects()[index];
					this.getListaDelCommunicationOfertaContratacion().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosOfertaContratacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationOfertaContratacion().clear();
				this.getElementoPila().getObjetos().set(3, null);// ************
																	// MODIFICAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

	public String btnAgregarRepresentante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarRepresentante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado4();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderRepresentantesActaApertura().getObjects()[index];
					this.getListaDelCommunicationRepresentantesActaApertura().remove(obj);
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

	public String btnQuitarTodosRepresentante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationRepresentantesActaApertura().clear();
				this.getElementoPila().getObjetos().set(4, null);// **********MODIFICAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	public String btnLimpiarProveedor_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(8, Proveedor.class, this.getTfProveedorAdjudicacion());
			this.getElementoPila().getObjetos().set(getCantidadObjetosUsados() - 1, 0);
			this.getElementoPila().getObjetos().set(8, null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMLicitacion";
	}

	private void agregarLineaSolicitudSuministro(List pListaRenglonLicitacion, LineaSolicitudSuministro pLineaSolicitud) {

		boolean encontrado = false;
		for(Object cadaLinea : pListaRenglonLicitacion) {

			LineaContratacion linea = (LineaContratacion) cadaLinea;

			if(linea.getBien().getNombre().equals(pLineaSolicitud.getBien().getNombre())) {
				linea.setCantidad(linea.getCantidad() + pLineaSolicitud.getCantidad());
				encontrado = true;
			}
		}

		if(!encontrado) {

			LineaContratacion renglonCon = new LineaContratacion();
			renglonCon.setBien(pLineaSolicitud.getBien());
			renglonCon.setCantidad(pLineaSolicitud.getCantidad());

			pListaRenglonLicitacion.add(renglonCon);
		}
	}

	@Override
	public void destroy() {
		try {
			this.guardarEstadoObjetosUsados();
		} catch(Exception e) {

		}
		super.destroy();
	}

	public String btnPresupuesto_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		boolean errorEnReporte = false;
		if(ultimo) {
			try {
				Object obj = this.obtenerObjetoDelElementoPila(0);
				Contratacion locContratacion = (Contratacion) obj;
				// this.getSessionBean1().setObjetoImpresion(obj);
				this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());

				// List<JasperPrint> listaJaspers = new ArrayList<JasperPrint>();
				Map<String, JasperPrint> mapaJaspers =  this.getCommunicationComprasBean().getRemoteSystemReportesCompras().getReportePresupuesto(locContratacion);

				File carpeta = new File(System.getProperty("java.io.tmpdir", null), getSessionBean1().getUsuario().getUser());
				if(!carpeta.exists() && !carpeta.mkdir()) {
					throw new IIOException("Error al crear el directorio temporal." + carpeta);
				}
				for(File archivo : carpeta.listFiles()) {
					archivo.delete();
				}

				List<File> listaArchivos = new ArrayList<File>();
				for (String cadaLlave : mapaJaspers.keySet()) {
					JasperPrint cadaReporte = mapaJaspers.get(cadaLlave);
					
					File file = Util.crearArchivo(carpeta.getPath(), cadaLlave, "pdf");
					FileOutputStream fos = new FileOutputStream(file);

					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, cadaReporte);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
					exporter.exportReport();

					listaArchivos.add(file);
				}

				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss");
				File archivoZip = new File(carpeta.getPath() + File.separator + "Reportes_Presupuestos_" + sdf.format(fecha) + ".zip");

				Zip locZip = new Zip();
				locZip.setCarpetaTemporal(carpeta.getPath());
				locZip.comprimir(listaArchivos, archivoZip);

				FileInputStream fis = new FileInputStream(archivoZip);
				byte[] bFile = new byte[(int) archivoZip.length()];
				fis.read(bFile);

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bytesDocumentoDescargable", bFile);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivoOriginal", archivoZip.getName());
				fis.close();
			} catch(Exception e) {
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
				errorEnReporte = true;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		if(errorEnReporte) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.ERRROR_EN_REPORTE, true);
		}
		return retorno;
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		seleccionarDdsAdjudicacion();
	}

	public void seleccionarDdsAdjudicacion() {
		if(this.getDdAdjudicarTodas().getSelected() != null && this.getDdAdjudicarTodas().getSelected() != "") {

			String toStringProveedor = this.getDdAdjudicarTodas().getSelected().toString();
			for(Object o : this.getListaDelCommunicationOfertaContratacion()) {
				OfertaContratacion oferta = (OfertaContratacion) o;
				if(oferta.getProveedor().toString().equals(toStringProveedor)) {
					for(LineaOfertaContratacion lineaOferta : oferta.getListaLineasOfertasContratacion()) {
						this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().put(lineaOferta.getBien(), lineaOferta);
					}
					break;
				}
			}
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof DigestoMunicipal) {
			DigestoMunicipal digesto = null;
			DigestoMunicipal digestoContratacion = this.obtenerObjetoDelElementoPila(3, DigestoMunicipal.class);
			if(!(Boolean) this.obtenerObjetoDelElementoPila(10)) {
				System.out.println("1");
				digesto = (DigestoMunicipal) pObject;
				digestoContratacion = digesto;
				this.getElementoPila().getObjetos().set(3, digestoContratacion);
			} else {
				System.out.println("2");
				digesto = (DigestoMunicipal) pObject;
				digestoContratacion = digesto;
				this.getElementoPila().getObjetos().set(9, digestoContratacion);
			}
		}

		if(pObject instanceof Proveedor) {
			List listaProveedores = this.obtenerObjetoDelElementoPila(2, ArrayList.class);
			Proveedor proveedor = null;
			try {
				proveedor = (Proveedor) pObject;
				proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(proveedor.getIdProveedor());
			} catch(Exception e) {
				e.printStackTrace();
			}
			// Si hay un 8 en el ultimo elemento de la pila, es un proveedo
			// para adjudicacion directa
			Integer indice = this.obtenerObjetoDelElementoPila(getCantidadObjetosUsados() - 1, Integer.class);
			if(indice.equals(8)) {
				this.getElementoPila().getObjetos().set(8, proveedor);
			} else {
				// Si no habia un 8, borramos el proveedor anterior
				this.getElementoPila().getObjetos().set(getCantidadObjetosUsados() - 1, new Integer(0));
				this.getElementoPila().getObjetos().set(8, null);
				if(!listaProveedores.contains(pObject)) {// ||
															// listaProveedores.contains(proveedor)){
					System.out.println(listaProveedores.contains(pObject));
					System.out.println(listaProveedores.contains(proveedor));
					listaProveedores.add(proveedor);
					this.getElementoPila().getObjetos().set(2, listaProveedores);
				} else {
					warn("El Proveedor seleccionado ya se encuentra en la lista");
				}
			}
		}

		if(pObject instanceof PersonaFisica) {
			List listaRepresentantesActa = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
			try {
				PersonaFisica personaFisica = (PersonaFisica) pObject;
				RepresentanteActaApertura representante = new RepresentanteActaApertura();
				representante.setPersona(personaFisica);
				representante.setCargo((String) this.getTfCargoRepresentante().getText());

				boolean yaExiste = false;

				for(Object r : listaRepresentantesActa) {
					RepresentanteActaApertura rep = (RepresentanteActaApertura) r;

					if(rep.getPersona().getIdPersona() == personaFisica.getIdPersona()) {
						yaExiste = true;
					}
				}

				if(!yaExiste) {

					listaRepresentantesActa.add(representante);
					this.getElementoPila().getObjetos().set(4, listaRepresentantesActa);
				} else {
					warn("La Persona seleccionada ya se encuentra en la lista");
				}

				this.getRequestBean1().setObjetoSeleccion(null);

			} catch(Exception ex) {
				log(" Error Description", ex);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Contratacion contratacion = (Contratacion) pObject;

		DigestoMunicipal locDigestoContratacion = contratacion.getDigestoMunicipal();

		List listaLineas = contratacion.getListaLineasContratacion();
		List listaProveedores = contratacion.getListaProveedoresAutorizados();
		List listaOfertasContratacion = contratacion.getListaOfertasContratacion();
		DigestoMunicipal locDigestoContratacionAdjudicacion = null;
		List listaRepresentantesActa = null;

		if(contratacion.getAdjudicacion() != null) {
			locDigestoContratacionAdjudicacion = contratacion.getAdjudicacion().getDigestoMunicipal();
		}

		for(Object cadaObject : listaLineas) {
			LineaContratacion cadaLinea = (LineaContratacion) cadaObject;
			if(cadaLinea.getLineaOfertaContratacionAdjudicada() != null) {
				this.getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().put(cadaLinea.getBien(), cadaLinea.getLineaOfertaContratacionAdjudicada());
			}
		}

		if(contratacion.getActaApertura() != null && contratacion.getActaApertura().getIdActaApertura() != -1) {
			listaRepresentantesActa = contratacion.getActaApertura().getListaRepresentantes();
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, contratacion);
		this.getElementoPila().getObjetos().set(ind++, listaLineas);
		this.getElementoPila().getObjetos().set(ind++, listaProveedores);
		this.getElementoPila().getObjetos().set(ind++, locDigestoContratacion);
		this.getElementoPila().getObjetos().set(ind++, listaRepresentantesActa);
		this.getElementoPila().getObjetos().set(ind++, listaOfertasContratacion);
		this.getElementoPila().getObjetos().set(9, locDigestoContratacionAdjudicacion);
	}

	@Override
	public long getSerialVersionUID() {
		return Contratacion.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMLicitacion$ABMLicitacion}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Contratacion locContratacion = this.obtenerObjetoDelElementoPila(0, Contratacion.class);
		this.getTablaLogs().getLdpLogs().setList(locContratacion.getListaLogsAuditoria());
	}

	public boolean getReadOnlyTfCantidad() {
		LineaContratacion locLineaContratacion = (LineaContratacion) this.getLdpLineasContratacion().getList().get(Integer.parseInt(this.getCurrentRow()));
		return locLineaContratacion.getListaLineasSolicitudSuministro().size() > 1 ? true : false;
	}

	public void setReadOnlyTfCantidad(boolean s) {
	}

	public void validarCantidadLinea(ActionEvent e) {
		this.getObjectListDataProvider().commitChanges();
		List<LineaContratacion> locListaLineas = this.getObjectListDataProvider().getList();

		for(LineaContratacion cadaLinea : locListaLineas) {
			if(cadaLinea.getListaLineasSolicitudSuministro().size() < 2 && cadaLinea.getCantidad() > cadaLinea.getListaLineasSolicitudSuministro().get(0).getCantidad()) {
				error("La cantidad ingresada (" + cadaLinea.getCantidad() + ") para la Línea " + cadaLinea.getBien() + " excede la cantidad especificada en la Solicitud de Suministro");
				cadaLinea.setCantidad(0d);
				cadaLinea.setCantidad(cadaLinea.getListaLineasSolicitudSuministro().get(0).getCantidad()
						- cadaLinea.getListaLineasSolicitudSuministro().get(0).getCantidadEntregasPrevias());
				break;
			}
		}
	}
}