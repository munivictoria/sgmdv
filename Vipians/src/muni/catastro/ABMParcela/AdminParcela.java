/*
 * AdminParcela.java
 *
 * Created on 30 de octubre de 2006, 12:03
 * Copyright Trascender
 */

package muni.catastro.ABMParcela;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.NomenclaturaCatastral;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Plano;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminParcela extends AdminPageBean {

	protected TableColumn tcCalle = new TableColumn();
	protected TableColumn tcAltura = new TableColumn();
	protected TableColumn tcNumeroParcela = new TableColumn();
	protected TableColumn tableColumn2 = new TableColumn();
	protected TableColumn tableColumn9 = new TableColumn();
	protected TableColumn tableColumn10 = new TableColumn();

	protected StaticText stCalle = new StaticText();
	protected StaticText stAltura = new StaticText();
	protected StaticText stNumeroParcela = new StaticText();
	protected StaticText staticText3 = new StaticText();
	protected StaticText staticText4 = new StaticText();
	protected StaticText staticText9 = new StaticText();
	protected StaticText staticText10 = new StaticText();
	protected StaticText staticText11 = new StaticText();
	protected StaticText staticText2 = new StaticText();
	protected StaticText staticText6 = new StaticText();
	protected StaticText staticText8 = new StaticText();
	protected StaticText staticText1 = new StaticText();
	protected StaticText staticText5 = new StaticText();
	protected StaticText staticText7 = new StaticText();

	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoOptions = new SingleSelectOptionsList();

	private SingleSelectOptionsList ddCalleOptions = new SingleSelectOptionsList();
	private DropDown ddCalle = new DropDown();

	private SingleSelectOptionsList ddCalleComienzaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleComienza = new DropDown();

	private SingleSelectOptionsList ddCalleFinalizaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleFinaliza = new DropDown();

	private SingleSelectOptionsList ddZonaOptions = new SingleSelectOptionsList();
	private DropDown ddZona = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoOptions() {
		return ddEstadoOptions;
	}

	public void setDdEstadoOptions(SingleSelectOptionsList ddEstadoOptions) {
		this.ddEstadoOptions = ddEstadoOptions;
	}

	public SingleSelectOptionsList getDdZonaOptions() {
		return ddZonaOptions;
	}

	public void setDdZonaOptions(SingleSelectOptionsList ddZonaOptions) {
		this.ddZonaOptions = ddZonaOptions;
	}

	public DropDown getDdZona() {
		return ddZona;
	}

	public void setDdZona(DropDown ddZona) {
		this.ddZona = ddZona;
	}

	public SingleSelectOptionsList getDdCalleOptions() {
		return ddCalleOptions;
	}

	public void setDdCalleOptions(SingleSelectOptionsList ddCalleOptions) {
		this.ddCalleOptions = ddCalleOptions;
	}

	public DropDown getDdCalleComienza() {
		return ddCalleComienza;
	}

	public void setDdCalleComienza(DropDown ddCalleComienza) {
		this.ddCalleComienza = ddCalleComienza;
	}

	public SingleSelectOptionsList getDdCalleComienzaOptions() {
		return ddCalleComienzaOptions;
	}

	public void setDdCalleComienzaOptions(SingleSelectOptionsList ddCalleComienzaOptions) {
		this.ddCalleComienzaOptions = ddCalleComienzaOptions;
	}

	public DropDown getDdCalleFinaliza() {
		return ddCalleFinaliza;
	}

	public void setDdCalleFinaliza(DropDown ddCalleFinaliza) {
		this.ddCalleFinaliza = ddCalleFinaliza;
	}

	public SingleSelectOptionsList getDdCalleFinalizaOptions() {
		return ddCalleFinalizaOptions;
	}

	public void setDdCalleFinalizaOptions(SingleSelectOptionsList ddCalleFinalizaOptions) {
		this.ddCalleFinalizaOptions = ddCalleFinalizaOptions;
	}

	private DropDown ddEstadoPlanoMensura = new DropDown();
	private SingleSelectOptionsList ddEstadoPlanoMensuraOptions = new SingleSelectOptionsList();

	public DropDown getDdEstadoPlanoMensura() {
		return ddEstadoPlanoMensura;
	}

	public void setDdEstadoPlanoMensura(DropDown ddEstadoPlanoMensura) {
		this.ddEstadoPlanoMensura = ddEstadoPlanoMensura;
	}

	public SingleSelectOptionsList getDdEstadoPlanoMensuraOptions() {
		return ddEstadoPlanoMensuraOptions;
	}

	public void setDdEstadoPlanoMensuraOptions(SingleSelectOptionsList ddEstadoPlanoMensuraOptions) {
		this.ddEstadoPlanoMensuraOptions = ddEstadoPlanoMensuraOptions;
	}

	private DropDown ddEstadoPlanoConstruccion = new DropDown();
	private SingleSelectOptionsList ddEstadoPlanoConstruccionOptions = new SingleSelectOptionsList();

	public DropDown getDdEstadoPlanoConstruccion() {
		return ddEstadoPlanoConstruccion;
	}

	public void setDdEstadoPlanoConstruccion(DropDown ddEstadoPlanoConstruccion) {
		this.ddEstadoPlanoConstruccion = ddEstadoPlanoConstruccion;
	}

	public SingleSelectOptionsList getDdEstadoPlanoConstruccionOptions() {
		return ddEstadoPlanoConstruccionOptions;
	}

	public void setDdEstadoPlanoConstruccionOptions(SingleSelectOptionsList ddEstadoPlanoConstruccionOptions) {
		this.ddEstadoPlanoConstruccionOptions = ddEstadoPlanoConstruccionOptions;
	}

	public StaticText getStAltura() {
		return stAltura;
	}

	public void setStAltura(StaticText stAltura) {
		this.stAltura = stAltura;
	}

	public StaticText getStCalle() {
		return stCalle;
	}

	public void setStCalle(StaticText stCalle) {
		this.stCalle = stCalle;
	}

	public TableColumn getTcAltura() {
		return tcAltura;
	}

	public void setTcAltura(TableColumn tcAltura) {
		this.tcAltura = tcAltura;
	}

	public TableColumn getTcCalle() {
		return tcCalle;
	}

	public void setTcCalle(TableColumn tcCalle) {
		this.tcCalle = tcCalle;
	}

	public TableColumn getTcNumeroParcela() {
		return tcNumeroParcela;
	}

	public void setTcNumeroParcela(TableColumn tcNumeroParcela) {
		this.tcNumeroParcela = tcNumeroParcela;
	}

	public StaticText getStNumeroParcela() {
		return stNumeroParcela;
	}

	public void setStNumeroParcela(StaticText stNumeroParcela) {
		this.stNumeroParcela = stNumeroParcela;
	}

	@Override
	protected void _init() throws Exception {
		Option[] opTipoBusqueda = new Option[5];
		Option opcion = new Option("Campos b\341sicos");
		opTipoBusqueda[0] = opcion;
		Option opcion2 = new Option("Campos avanzados");
		opTipoBusqueda[1] = opcion2;
		Option opcion3 = new Option("Plano de Mensura");
		opTipoBusqueda[2] = opcion3;
		Option opcion4 = new Option("Plano de Construcción");
		opTipoBusqueda[3] = opcion4;
		Option opcion5 = new Option("Titulo Propiedad");
		opTipoBusqueda[4] = opcion5;
		ddTipoBusquedaDefaultOptions.setOptions(opTipoBusqueda);

		this.cargarComboCallesComienzaCuadras(null);
		this.cargarComboCallesFinalizaCuadras(null, null);

		Set<String> locListaCalles = this.getComunicationCatastroBean().getMapaCalles().keySet();
		Option[] opCalles = new Option[locListaCalles.size() + 1];
		int i = 0;
		opCalles[i++] = new Option("", "");
		for(String cadaCalle : locListaCalles) {
			opCalles[i++] = new Option(cadaCalle, cadaCalle);
		}
		ddCalleOptions.setOptions(opCalles);

		Set<String> locListaZona = this.getComunicationCatastroBean().getMapaZona().keySet();
		Option[] opZona = new Option[locListaZona.size() + 1];
		int j = 0;
		opZona[j++] = new Option("", "");
		for(String cadaZona : locListaZona) {
			opZona[j++] = new Option(cadaZona, cadaZona);
		}
		ddZonaOptions.setOptions(opZona);

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Parcela.Estado.values(), "cap");
		ddEstadoOptions.setOptions(opEstado);

		Option[] opEstadoPlanoMensura = null;
		opEstadoPlanoMensura = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PlanoMensura.Estado.values(), "cap");
		ddEstadoPlanoMensuraOptions.setOptions(opEstadoPlanoMensura);

		Option[] opEstadoPlanoConstruccion = null;
		opEstadoPlanoConstruccion = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PlanoConstruccion.Estado.values(), "cap");
		ddEstadoPlanoConstruccionOptions.setOptions(opEstadoPlanoConstruccion);

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		// ((Parcela)ldpParcela.getList().get(0)).getAltura

		this.habilitarBtnExportar();
	}

	protected PanelAtributoDinamico panelAtributoDinamico;
	protected PanelAtributoDinamico panelAtributoDinamicoPlanoMensura = new PanelAtributoDinamico();
	protected PanelAtributoDinamico panelAtributoDinamicoPlanoConstruccion = new PanelAtributoDinamico();
	protected PanelAtributoDinamico panelAtributoDinamicoTituloPropiedad = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamicoTituloPropiedad() {
		return panelAtributoDinamicoTituloPropiedad;
	}

	public void setPanelAtributoDinamicoTituloPropiedad(PanelAtributoDinamico panelAtributoDinamicoTituloPropiedad) {
		this.panelAtributoDinamicoTituloPropiedad = panelAtributoDinamicoTituloPropiedad;
	}

	public PanelAtributoDinamico getPanelAtributoDinamicoPlanoConstruccion() {
		return panelAtributoDinamicoPlanoConstruccion;
	}

	public void setPanelAtributoDinamicoPlanoConstruccion(PanelAtributoDinamico panelAtributoDinamicoPlanoConstruccion) {
		this.panelAtributoDinamicoPlanoConstruccion = panelAtributoDinamicoPlanoConstruccion;
	}

	public PanelAtributoDinamico getPanelAtributoDinamicoPlanoMensura() {
		return panelAtributoDinamicoPlanoMensura;
	}

	public void setPanelAtributoDinamicoPlanoMensura(PanelAtributoDinamico panelAtributoDinamicoPlanoMensura) {
		this.panelAtributoDinamicoPlanoMensura = panelAtributoDinamicoPlanoMensura;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	private Label lbEstado = new Label();

	public Label getLbEstado() {
		return lbEstado;
	}

	public void setLbEstado(Label lbEstado) {
		this.lbEstado = lbEstado;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label20 = new Label();

	public Label getLabel20() {
		return label20;
	}

	public void setLabel20(Label label20) {
		this.label20 = label20;
	}

	private Label label21 = new Label();

	public Label getLabel21() {
		return label21;
	}

	public void setLabel21(Label label21) {
		this.label21 = label21;
	}

	private Label label22 = new Label();

	public Label getLabel22() {
		return label22;
	}

	public void setLabel22(Label label22) {
		this.label22 = label22;
	}

	private Label label23 = new Label();

	public Label getLabel23() {
		return label23;
	}

	public void setLabel23(Label label23) {
		this.label23 = label23;
	}

	private Label label24 = new Label();

	public Label getLabel24() {
		return label24;
	}

	public void setLabel24(Label label24) {
		this.label24 = label24;
	}

	private Label label27 = new Label();

	public Label getLabel27() {
		return label27;
	}

	public void setLabel27(Label label27) {
		this.label27 = label27;
	}

	private Label label28 = new Label();

	public Label getLabel28() {
		return label28;
	}

	public void setLabel28(Label label28) {
		this.label28 = label28;
	}

	private Label label30 = new Label();

	public Label getLabel30() {
		return label30;
	}

	public void setLabel30(Label label30) {
		this.label30 = label30;
	}

	private Label label31 = new Label();

	public Label getLabel31() {
		return label31;
	}

	public void setLabel31(Label label31) {
		this.label31 = label31;
	}

	private Label label32 = new Label();

	public Label getLabel32() {
		return label32;
	}

	public void setLabel32(Label label32) {
		this.label32 = label32;
	}

	private Label label33 = new Label();

	public Label getLabel33() {
		return label33;
	}

	public void setLabel33(Label label33) {
		this.label33 = label33;
	}

	private Label label34 = new Label();

	public Label getLabel34() {
		return label34;
	}

	public void setLabel34(Label label34) {
		this.label34 = label34;
	}

	private Label label35 = new Label();

	public Label getLabel35() {
		return label35;
	}

	public void setLabel35(Label label35) {
		this.label35 = label35;
	}

	private Label label36 = new Label();

	public Label getLabel36() {
		return label36;
	}

	public void setLabel36(Label label36) {
		this.label36 = label36;
	}

	private Label label37 = new Label();

	public Label getLabel37() {
		return label37;
	}

	public void setLabel37(Label label37) {
		this.label37 = label37;
	}

	public Label label38 = new Label();
	public Label label39 = new Label();
	public Label label40 = new Label();
	public Label label41 = new Label();
	public Label label42 = new Label();
	public Label lbFechaPlanoMensura = new Label();

	public Label getLabel38() {
		return label38;
	}

	public void setLabel38(Label label38) {
		this.label38 = label38;
	}

	public Label getLabel39() {
		return label39;
	}

	public void setLabel39(Label label39) {
		this.label39 = label39;
	}

	public Label getLabel40() {
		return label40;
	}

	public void setLabel40(Label label40) {
		this.label40 = label40;
	}

	public Label getLabel41() {
		return label41;
	}

	public void setLabel41(Label label41) {
		this.label41 = label41;
	}

	public Label getLabel42() {
		return label42;
	}

	public void setLabel42(Label label42) {
		this.label42 = label42;
	}

	public Label getLbFechaPlanoMensura() {
		return lbFechaPlanoMensura;
	}

	public void setLbFechaPlanoMensura(Label lbFechaPlanoMensura) {
		this.lbFechaPlanoMensura = lbFechaPlanoMensura;
	}

	private Label label43 = new Label();
	private Label label44 = new Label();
	private Label label45 = new Label();
	private Label label46 = new Label();
	private Label label47 = new Label();
	private Label lbFechaPlanoConstruccion = new Label();

	public Label getLabel43() {
		return label43;
	}

	public void setLabel43(Label label43) {
		this.label43 = label43;
	}

	public Label getLabel44() {
		return label44;
	}

	public void setLabel44(Label label44) {
		this.label44 = label44;
	}

	public Label getLabel45() {
		return label45;
	}

	public void setLabel45(Label label45) {
		this.label45 = label45;
	}

	public Label getLabel46() {
		return label46;
	}

	public void setLabel46(Label label46) {
		this.label46 = label46;
	}

	public Label getLabel47() {
		return label47;
	}

	public void setLabel47(Label label47) {
		this.label47 = label47;
	}

	public Label getLbFechaPlanoConstruccion() {
		return lbFechaPlanoConstruccion;
	}

	public void setLbFechaPlanoConstruccion(Label lbFechaPlanoConstruccion) {
		this.lbFechaPlanoConstruccion = lbFechaPlanoConstruccion;
	}

	private HtmlAjaxCommandButton btnLimpiarCuadra = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarManzana = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCuadra() {
		return btnLimpiarCuadra;
	}

	public void setBtnLimpiarCuadra(HtmlAjaxCommandButton btnLimpiarCuadra) {
		this.btnLimpiarCuadra = btnLimpiarCuadra;
	}

	public HtmlAjaxCommandButton getBtnLimpiarManzana() {
		return btnLimpiarManzana;
	}

	public void setBtnLimpiarManzana(HtmlAjaxCommandButton btnLimpiarManzana) {
		this.btnLimpiarManzana = btnLimpiarManzana;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Button btnSubdividirSubparcela = new Button();

	public Button getBtnSubdividirSubparcela() {
		return btnSubdividirSubparcela;
	}

	public void setBtnSubdividirSubparcela(Button btnSubdividirSubparcela) {
		this.btnSubdividirSubparcela = btnSubdividirSubparcela;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpParcela = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpParcela() {
		return ldpParcela;
	}

	public void setLdpParcela(ObjectListDataProvider oldp) {
		this.ldpParcela = oldp;
	}

	private RadioButtonGroup rbgTipoBusqueda = new RadioButtonGroup();

	public RadioButtonGroup getRbgTipoBusqueda() {
		return rbgTipoBusqueda;
	}

	public void setRbgTipoBusqueda(RadioButtonGroup rbgTipoBusqueda) {
		this.rbgTipoBusqueda = rbgTipoBusqueda;
	}

	private SingleSelectOptionsList ddTipoBusquedaDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoBusquedaDefaultOptions() {
		return ddTipoBusquedaDefaultOptions;
	}

	public void setDdTipoBusquedaDefaultOptions(SingleSelectOptionsList ddTipoBusquedaDefaultOptions) {
		this.ddTipoBusquedaDefaultOptions = ddTipoBusquedaDefaultOptions;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TextField tfCuadra = new TextField();

	public TextField getTfCuadra() {
		return tfCuadra;
	}

	public void setTfCuadra(TextField tf) {
		this.tfCuadra = tf;
	}

	private TextField tfDpto = new TextField();

	public TextField getTfDpto() {
		return tfDpto;
	}

	public void setTfDpto(TextField tfDpto) {
		this.tfDpto = tfDpto;
	}

	private TextField tfPedania = new TextField();

	public TextField getTfPedania() {
		return tfPedania;
	}

	public void setTfPedania(TextField tfPedania) {
		this.tfPedania = tfPedania;
	}

	private TextField tfCircunscripcion = new TextField();

	public TextField getTfCircunscripcion() {
		return tfCircunscripcion;
	}

	public void setTfCircunscripcion(TextField tfCircunscripcion) {
		this.tfCircunscripcion = tfCircunscripcion;
	}

	private TextField tfDistrito = new TextField();

	public TextField getTfDistrito() {
		return tfDistrito;
	}

	public void setTfDistrito(TextField tfDistrito) {
		this.tfDistrito = tfDistrito;
	}

	private TextField tfSubDistrito = new TextField();

	public TextField getTfSubDistrito() {
		return tfSubDistrito;
	}

	public void setTfSubDistrito(TextField tfSubDistrito) {
		this.tfSubDistrito = tfSubDistrito;
	}

	private TextField tfSeccionPoligono = new TextField();

	public TextField getTfSeccionPoligono() {
		return tfSeccionPoligono;
	}

	public void setTfSeccionPoligono(TextField tfSeccionPoligono) {
		this.tfSeccionPoligono = tfSeccionPoligono;
	}

	private TextField tfManzana = new TextField();

	public TextField getTfManzana() {
		return tfManzana;
	}

	public void setTfManzana(TextField tfManzana) {
		this.tfManzana = tfManzana;
	}

	private TextField tfQuinta = new TextField();

	public TextField getTfQuinta() {
		return tfQuinta;
	}

	public void setTfQuinta(TextField tfQuinta) {
		this.tfQuinta = tfQuinta;
	}

	private TextField tfChacraFraccion = new TextField();

	public TextField getTfChacraFraccion() {
		return tfChacraFraccion;
	}

	public void setTfChacraFraccion(TextField tfChacraFraccion) {
		this.tfChacraFraccion = tfChacraFraccion;
	}

	private TextField tfLote = new TextField();

	public TextField getTfLote() {
		return tfLote;
	}

	public void setTfLote(TextField tfLote) {
		this.tfLote = tfLote;
	}

	private TextField tfNroPlanoMensura = new TextField();
	private TextField tfFolioPlanoMensura = new TextField();
	private TextField tfTomoPlanoMensura = new TextField();
	private TextField tfFechaPlanoMensura = new TextField();

	public TextField getTfFechaPlanoMensura() {
		return tfFechaPlanoMensura;
	}

	public void setTfFechaPlanoMensura(TextField tfFechaPlanoMensura) {
		this.tfFechaPlanoMensura = tfFechaPlanoMensura;
	}

	public TextField getTfFolioPlanoMensura() {
		return tfFolioPlanoMensura;
	}

	public void setTfFolioPlanoMensura(TextField tfFolioPlanoMensura) {
		this.tfFolioPlanoMensura = tfFolioPlanoMensura;
	}

	public TextField getTfNroPlanoMensura() {
		return tfNroPlanoMensura;
	}

	public void setTfNroPlanoMensura(TextField tfNroPlanoMensura) {
		this.tfNroPlanoMensura = tfNroPlanoMensura;
	}

	public TextField getTfTomoPlanoMensura() {
		return tfTomoPlanoMensura;
	}

	public void setTfTomoPlanoMensura(TextField tfTomoPlanoMensura) {
		this.tfTomoPlanoMensura = tfTomoPlanoMensura;
	}

	private TextField tfNroPlanoConstruccion = new TextField();
	private TextField tfFolioPlanoConstruccion = new TextField();
	private TextField tfTomoPlanoConstruccion = new TextField();
	private TextField tfFechaPlanoConstruccion = new TextField();

	public TextField getTfFechaPlanoConstruccion() {
		return tfFechaPlanoConstruccion;
	}

	public void setTfFechaPlanoConstruccion(TextField tfFechaPlanoConstruccion) {
		this.tfFechaPlanoConstruccion = tfFechaPlanoConstruccion;
	}

	public TextField getTfFolioPlanoConstruccion() {
		return tfFolioPlanoConstruccion;
	}

	public void setTfFolioPlanoConstruccion(TextField tfFolioPlanoConstruccion) {
		this.tfFolioPlanoConstruccion = tfFolioPlanoConstruccion;
	}

	public TextField getTfNroPlanoConstruccion() {
		return tfNroPlanoConstruccion;
	}

	public void setTfNroPlanoConstruccion(TextField tfNroPlanoConstruccion) {
		this.tfNroPlanoConstruccion = tfNroPlanoConstruccion;
	}

	public TextField getTfTomoPlanoConstruccion() {
		return tfTomoPlanoConstruccion;
	}

	public void setTfTomoPlanoConstruccion(TextField tfTomoPlanoConstruccion) {
		this.tfTomoPlanoConstruccion = tfTomoPlanoConstruccion;
	}

	private Button btnSeleccionarCuadra = new Button();

	private Button btnVolantesCatastrales = new Button();

	public Button getBtnVolantesCatastrales() {
		return btnVolantesCatastrales;
	}

	public void setBtnVolantesCatastrales(Button btnVolantesCatastrales) {
		this.btnVolantesCatastrales = btnVolantesCatastrales;
	}

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button b) {
		this.btnSeleccionarCuadra = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Button btnSeleccionarManzana = new Button();

	public Button getBtnSeleccionarManzana() {
		return btnSeleccionarManzana;
	}

	public void setBtnSeleccionarManzana(Button b) {
		this.btnSeleccionarManzana = b;
	}

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tableColumn10) {
		this.tableColumn10 = tableColumn10;
	}

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private Label label29 = new Label();

	public Label getLabel29() {
		return label29;
	}

	public void setLabel29(Label label29) {
		this.label29 = label29;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfNumeroRegistro = new TextField();
	private TextField tfNumeroMatricula = new TextField();
	private TextField tfNumeroCuenta = new TextField();

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public TextField getTfNumeroMatricula() {
		return tfNumeroMatricula;
	}

	public void setTfNumeroMatricula(TextField tfNumeroMatricula) {
		this.tfNumeroMatricula = tfNumeroMatricula;
	}

	public TextField getTfNumeroRegistro() {
		return tfNumeroRegistro;
	}

	public void setTfNumeroRegistro(TextField tf) {
		this.tfNumeroRegistro = tf;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label labelZona = new Label();
	private HtmlAjaxCommandButton btnLimpiarZona = new HtmlAjaxCommandButton();
	private Button btnSeleccionarZona = new Button();

	public Label getLabelZona() {
		return labelZona;
	}

	public void setLabelZona(Label labelZona) {
		this.labelZona = labelZona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarZona() {
		return btnLimpiarZona;
	}

	public void setBtnLimpiarZona(HtmlAjaxCommandButton btnLimpiarZona) {
		this.btnLimpiarZona = btnLimpiarZona;
	}

	public Button getBtnSeleccionarZona() {
		return btnSeleccionarZona;
	}

	public void setBtnSeleccionarZona(Button btnSeleccionarZona) {
		this.btnSeleccionarZona = btnSeleccionarZona;
	}

	private Label labelCalle = new Label();
	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();
	private Button btnSeleccionarCalle = new Button();
	private Label labelDomicilio = new Label();
	private TextField tfDomicilio = new TextField();

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public Label getLabelCalle() {
		return labelCalle;
	}

	public void setLabelCalle(Label labelCalle) {
		this.labelCalle = labelCalle;
	}

	public Label getLabelDomicilio() {
		return labelDomicilio;
	}

	public void setLabelDomicilio(Label labelDomicilio) {
		this.labelDomicilio = labelDomicilio;
	}

	public DropDown getDdCalle() {
		return ddCalle;
	}

	public void setDdCalle(DropDown ddCalle) {
		this.ddCalle = ddCalle;
	}

	public TextField getTfDomicilio() {
		return tfDomicilio;
	}

	public void setTfDomicilio(TextField tfDomicilio) {
		this.tfDomicilio = tfDomicilio;
	}

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button btnSeleccionarCalle) {
		this.btnSeleccionarCalle = btnSeleccionarCalle;
	}

	private TextField tfNroPartidaProvincial = new TextField();

	public TextField getTfNroPartidaProvincial() {
		return tfNroPartidaProvincial;
	}

	public void setTfNroPartidaProvincial(TextField tf) {
		this.tfNroPartidaProvincial = tf;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Label label7 = new Label();

	private Label label8 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label label7) {
		this.label7 = label7;
	}

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label label8) {
		this.label8 = label8;
	}

	private TextField tfTituloPropiedad = new TextField();

	public TextField getTfTituloPropiedad() {
		return tfTituloPropiedad;
	}

	public void setTfTituloPropiedad(TextField tf) {
		this.tfTituloPropiedad = tf;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	private Button btnSeleccionarTituloPropiedad = new Button();

	public Button getBtnSeleccionarTituloPropiedad() {
		return btnSeleccionarTituloPropiedad;
	}

	public void setBtnSeleccionarTituloPropiedad(Button b) {
		this.btnSeleccionarTituloPropiedad = b;
	}

	private HtmlAjaxCommandButton btnLimpiarTituloPropiedad = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarTituloPropiedad() {
		return btnLimpiarTituloPropiedad;
	}

	public void setBtnLimpiarTituloPropiedad(HtmlAjaxCommandButton btnLimpiarTituloPropiedad) {
		this.btnLimpiarTituloPropiedad = btnLimpiarTituloPropiedad;
	}

	protected Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminParcela() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Object());// 0 Tipo Busqueda

		FiltroParcela locFiltro = getFiltro();
		List atributosDinamicos = null;
		List atributosDinamicosPlanoMensura = null;
		List atributosDinamicosPlanoConstruccion = null;
		List atributosDinamicosTituloPropiedad = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Parcela.serialVersionUID, null, true);
			atributosDinamicosPlanoMensura = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PlanoMensura.serialVersionUID, null, true);
			atributosDinamicosPlanoConstruccion = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PlanoConstruccion.serialVersionUID, null, true);
			atributosDinamicosTituloPropiedad = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(TituloPropiedadParcelario.serialVersionUID, null, true);
		} catch(Exception e) {

		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);
		locFiltro.setListaAtributosDinamicosPlanoMensura(atributosDinamicosPlanoMensura);
		locFiltro.setListaAtributosDinamicosPlanoConstruccion(atributosDinamicosPlanoConstruccion);
		locFiltro.setListaAtributosDinamicosTituloPropiedad(atributosDinamicosTituloPropiedad);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroParcela locFiltro = getFiltro();
		String tipoBusqueda = this.getRbgTipoBusqueda().getSelected().toString();

		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());

		// Nomenclatura Catastral
		NomenclaturaCatastral nomenclaturaCatastral = new NomenclaturaCatastral();
		nomenclaturaCatastral.setDepartamento(this.getTextFieldValue(getTfDpto()));
		nomenclaturaCatastral.setPedania(this.getTextFieldValue(getTfPedania()));
		nomenclaturaCatastral.setCircunscripcion(this.getTextFieldValue(getTfCircunscripcion()));
		nomenclaturaCatastral.setDistrito(this.getTextFieldValue(getTfDistrito()));
		nomenclaturaCatastral.setSubDistrito(this.getTextFieldValue(getTfSubDistrito()));
		nomenclaturaCatastral.setSeccion(this.getTextFieldValue(getTfSeccionPoligono()));
		nomenclaturaCatastral.setQuinta(this.getTextFieldValue(getTfQuinta()));
		nomenclaturaCatastral.setChacra(this.getTextFieldValue(getTfChacraFraccion()));
		nomenclaturaCatastral.setLote(this.getTextFieldValue(getTfLote()));
		nomenclaturaCatastral.setNroParcela(this.getTextFieldValue(getTfNroParcela()));
		locFiltro.setNomenClaturaCatastral(nomenclaturaCatastral);
		locFiltro.setNumeroDomicilio(this.getTextFieldValue(getTfDomicilio()));
		//
		locFiltro.setNroPartidaProvincial(this.getTextFieldValue(getTfNroPartidaProvincial()));
		locFiltro.setNroRegistro(this.getTextFieldValue(getTfNumeroRegistro()));
		locFiltro.setNroMatricula(this.getTextFieldValue(getTfNumeroMatricula()));
		locFiltro.setNroCuenta(this.getTextFieldValueInteger(getTfNumeroCuenta()));

		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Parcela.Estado.class));

		// Plano Mensura
		locFiltro.setPlanoMensura(this.getTextFieldValue(getTfNroPlanoMensura()));
		locFiltro.setFolioMensura(this.getTextFieldValue(getTfFolioPlanoMensura()));
		locFiltro.setTomoMensura(this.getTextFieldValue(getTfTomoPlanoMensura()));
		locFiltro.setFechaInscripcionMensura(this.getTextFieldValueDate(getTfFechaPlanoMensura()));
		locFiltro.setEstadoMensura(this.getDDEnumValue(getDdEstadoPlanoMensura(), Plano.Estado.class));
		//
		// Plano Construccion
		locFiltro.setPlanoConstruccion(this.getTextFieldValue(getTfNroPlanoConstruccion()));
		locFiltro.setFolioConstruccion(this.getTextFieldValue(getTfFolioPlanoConstruccion()));
		locFiltro.setTomoConstruccion(this.getTextFieldValue(getTfTomoPlanoConstruccion()));
		locFiltro.setFechaInscripcionConstruccion(this.getTextFieldValueDate(getTfFechaPlanoConstruccion()));
		locFiltro.setEstadoConstruccion(this.getDDEnumValue(getDdEstadoPlanoConstruccion(), Plano.Estado.class));
		//
		// Calle
		locFiltro.setCalle(this.getDDObjectValue(getDdCalle(), getComunicationCatastroBean().getMapaCalles()));
		locFiltro.setCalleComienza(this.getDDObjectValue(getDdCalleComienza(), getComunicationCatastroBean().getMapaCalles()));
		locFiltro.setCalleFinaliza(this.getDDObjectValue(getDdCalleFinaliza(), getComunicationCatastroBean().getMapaCalles()));
		//
		// zona
		locFiltro.setZona(this.getDDObjectValue(getDdZona(), getComunicationCatastroBean().getMapaZona()));

		if(locFiltro.getListaAtributosDinamicos() != null) {
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}
		if(locFiltro.getListaAtributosDinamicosPlanoMensura() != null) {
			locFiltro.setListaAtributosDinamicosPlanoMensura(panelAtributoDinamicoPlanoMensura.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicosPlanoMensura()));
		}
		if(locFiltro.getListaAtributosDinamicosPlanoConstruccion() != null) {
			locFiltro.setListaAtributosDinamicosPlanoConstruccion(panelAtributoDinamicoPlanoConstruccion.obtenerListaAtributosDinamicos(locFiltro
					.getListaAtributosDinamicosPlanoConstruccion()));
		}
		if(locFiltro.getListaAtributosDinamicosTituloPropiedad() != null) {
			locFiltro.setListaAtributosDinamicosTituloPropiedad(panelAtributoDinamicoTituloPropiedad.obtenerListaAtributosDinamicos(locFiltro
					.getListaAtributosDinamicosTituloPropiedad()));
		}

		this.getElementoPila().getObjetos().set(0, tipoBusqueda);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroParcela locFiltro = getFiltro();

		Object tipoBusqueda = this.obtenerObjetoDelElementoPila(0, Object.class);

		this.ddCalleComienzaOptions.setOptions(new Option[0]);
		this.ddCalleFinalizaOptions.setOptions(new Option[0]);

		if(locFiltro.getZona() != null) {
			this.getDdZona().setSelected(locFiltro.getZona().toString());
		}
		if(locFiltro.getCalle() != null) {
			this.cargarComboCallesComienzaCuadras(locFiltro.getCalle());
			if(locFiltro.getCalleComienza() != null) {
				this.cargarComboCallesFinalizaCuadras(locFiltro.getCalle(), locFiltro.getCalleComienza());
				this.getDdCalleComienza().setSelected(locFiltro.getCalleComienza().toString());
			}
			if(locFiltro.getCalleFinaliza() != null) {
				this.getDdCalleFinaliza().setSelected(locFiltro.getCalleFinaliza().toString());
			}
		}
		if(locFiltro.getCuadra() != null) {
			this.getTfCuadra().setText(locFiltro.getCuadra().toString());
		}
		if(locFiltro.getManzana() != null) {
			this.getTfManzana().setText(locFiltro.getManzana().toString());
		}
		if(locFiltro.getPersona() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getPersona().toString());
		}

		if(locFiltro.getCalle() != null) {
			this.getDdCalle().setSelected(locFiltro.getCalle().toString());
		}
		if(locFiltro.getNumeroDomicilio() != null) {
			this.getTfDomicilio().setText(locFiltro.getNumeroDomicilio());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Parcela.Estado.ACTIVO : locFiltro.getEstado())));

		if(locFiltro.getNomenClaturaCatastral() != null) {
			NomenclaturaCatastral nomenclaturaCatastral = locFiltro.getNomenClaturaCatastral();
			if(nomenclaturaCatastral.getDepartamento() != null) {
				this.getTfDpto().setText(nomenclaturaCatastral.getDepartamento().toString());
			}
			if(nomenclaturaCatastral.getPedania() != null) {
				this.getTfPedania().setText(nomenclaturaCatastral.getPedania().toString());
			}
			if(nomenclaturaCatastral.getCircunscripcion() != null) {
				this.getTfCircunscripcion().setText(nomenclaturaCatastral.getCircunscripcion().toString());
			}
			if(nomenclaturaCatastral.getDistrito() != null) {
				this.getTfDistrito().setText(nomenclaturaCatastral.getDistrito().toString());
			}
			if(nomenclaturaCatastral.getSubDistrito() != null) {
				this.getTfSubDistrito().setText(nomenclaturaCatastral.getSubDistrito().toString());
			}
			if(nomenclaturaCatastral.getSeccion() != null) {
				this.getTfSeccionPoligono().setText(nomenclaturaCatastral.getSeccion().toString());
			}
			if(nomenclaturaCatastral.getQuinta() != null) {
				this.getTfQuinta().setText(nomenclaturaCatastral.getQuinta().toString());
			}
			if(nomenclaturaCatastral.getChacra() != null) {
				this.getTfChacraFraccion().setText(nomenclaturaCatastral.getChacra().toString());
			}
			if(nomenclaturaCatastral.getLote() != null) {
				this.getTfLote().setText(nomenclaturaCatastral.getLote().toString());
			}
			if(nomenclaturaCatastral.getNroParcela() != null) {
				this.getTfNroParcela().setText(nomenclaturaCatastral.getNroParcela().toString());
			}
		}

		if(locFiltro.getNroPartidaProvincial() != null) {
			this.getTfNroPartidaProvincial().setText(locFiltro.getNroPartidaProvincial().toString());
		}
		if(locFiltro.getNroRegistro() != null) {
			this.getTfNumeroRegistro().setText(locFiltro.getNroRegistro().toString());
		}
		if(locFiltro.getNroMatricula() != null) {
			this.getTfNumeroMatricula().setText(locFiltro.getNroMatricula().toString());
		}
		if(locFiltro.getNroCuenta() != null) {
			this.getTfNumeroCuenta().setText(locFiltro.getNroCuenta().toString());
		}
		this.rbgTipoBusqueda.setSelected(tipoBusqueda.toString());

		String nombreAtributo = "";
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{catastro$ABMParcela$AdminParcela}");
		nombreAtributo = "PlanoMensura";
		panelAtributoDinamicoPlanoMensura = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicosPlanoMensura(), "#{catastro$ABMParcela$AdminParcela}", nombreAtributo);
		nombreAtributo = "PlanoConstruccion";
		panelAtributoDinamicoPlanoConstruccion = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicosPlanoConstruccion(), "#{catastro$ABMParcela$AdminParcela}",
				nombreAtributo);
		nombreAtributo = "TituloPropiedad";
		panelAtributoDinamicoTituloPropiedad = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicosTituloPropiedad(), "#{catastro$ABMParcela$AdminParcela}", nombreAtributo);

		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
		panelAtributoDinamicoPlanoMensura.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicosPlanoMensura());
		panelAtributoDinamicoPlanoConstruccion.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicosPlanoConstruccion());
		panelAtributoDinamicoTituloPropiedad.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicosTituloPropiedad());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
		FiltroParcela filtro = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().findListaParcelas((FiltroParcela) pFiltro);
		return filtro;
	}

	@Override
	protected void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroParcela locFiltro = getFiltro();
		locFiltro.setNomenClaturaCatastral(null);
		locFiltro.setNumeroDomicilio(null);
		locFiltro.setNroPartidaProvincial(null);
		locFiltro.setNroRegistro(null);
		locFiltro.setNroMatricula(null);
		locFiltro.setNroCuenta(null);
		locFiltro.setPlanoMensura(null);
		locFiltro.setFolioMensura(null);
		locFiltro.setTomoMensura(null);
		locFiltro.setFechaInscripcionMensura(null);
		locFiltro.setEstadoMensura(null);
		locFiltro.setPlanoConstruccion(null);
		locFiltro.setFolioConstruccion(null);
		locFiltro.setTomoConstruccion(null);
		locFiltro.setFechaInscripcionConstruccion(null);
		locFiltro.setEstadoConstruccion(null);
		locFiltro.setCalle(null);
		locFiltro.setCalleComienza(null);
		locFiltro.setCalleFinaliza(null);
		locFiltro.setListaAtributosDinamicos(null);
		locFiltro.setListaAtributosDinamicosPlanoMensura(null);
		locFiltro.setListaAtributosDinamicosPlanoConstruccion(null);
		locFiltro.setListaAtributosDinamicosTituloPropiedad(null);
		locFiltro.setCuadra(null);
		locFiltro.setManzana(null);
		locFiltro.setPersona(null);
		locFiltro.setZona(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();

		this.getTfCuadra().setText("");
		this.getTfManzana().setText("");
		this.getTfNumeroRegistro().setText("");
		this.getTfNroParcela().setText("");
		this.getTfNroPartidaProvincial().setText("");
		this.getTfPersonaSeleccionada().setText("");
		this.getTfDpto().setText("");
		this.getTfPedania().setText("");
		this.getTfCircunscripcion().setText("");
		this.getTfDistrito().setText("");
		this.getTfSubDistrito().setText("");
		this.getTfSeccionPoligono().setText("");
		this.getTfQuinta().setText("");
		this.getTfChacraFraccion().setText("");
		this.getTfLote().setText("");
		this.getTfNumeroMatricula().setText("");
		this.getTfNumeroCuenta().setText("");
		this.rbgTipoBusqueda.setSelected("Campos b\341sicos");
		this.getDdCalle().setSelected(null);
		this.getTfDomicilio().setText("");
		this.getPanelAtributoDinamico().limpiarCampos();
		this.getPanelAtributoDinamicoPlanoConstruccion().limpiarCampos();
		this.getPanelAtributoDinamicoPlanoMensura().limpiarCampos();
		this.getPanelAtributoDinamicoTituloPropiedad().limpiarCampos();
		this.ddCalleComienzaOptions.setOptions(null);
		this.ddCalleFinalizaOptions.setOptions(null);
		this.getDdZona().setSelected(null);

		this.getTfNroPlanoMensura().setText("");
		this.getTfFolioPlanoMensura().setText("");
		this.getTfTomoPlanoMensura().setText("");
		this.getTfFechaPlanoMensura().setText("");
		this.ddEstadoPlanoMensura.setSelected(null);
		this.getTfNroPlanoConstruccion().setText("");
		this.getTfFolioPlanoConstruccion().setText("");
		this.getTfTomoPlanoConstruccion().setText("");
		this.getTfFechaPlanoConstruccion().setText("");
		this.ddEstadoPlanoConstruccion.setSelected(null);
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Parcela.Estado.ACTIVO)));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpParcela();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaParcelas();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaParcelas((ArrayList) lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getComunicationCatastroBean().getTablePhaseListenerParcelas();
	}

	public String btnAgregar_action() {
		Parcela parcela = new Parcela();
		NomenclaturaCatastral nomenclaturaCatastral = parcela.getNomenclaturaCatastral();
		nomenclaturaCatastral.setNroParcela(this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getSugerenciaNumeroParcela().toString());
		this.getRequestBean1().setObjetoABM(parcela);

		return toAbm(new ParcelaModel().new AgregarParcelaController());
	}

	public String btnModificar_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				RowKey rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Parcela locParcela = (Parcela) obj;

					if(locParcela != null && locParcela.getEstado() != Parcela.Estado.ELIMINADO) {
						return toAbm(new ParcelaModel().new ModificarParcelaController());
					} else {
						warn("No se puede modificar una parcela eliminada.");
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public String btnEliminar_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				RowKey rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Parcela locParcela = (Parcela) obj;

					if(locParcela != null && locParcela.getEstado() != Parcela.Estado.ELIMINADO) {
						return toAbm(new ParcelaModel().new EliminarParcelaController());
					} else {
						warn("La parcela ya está eliminada.");
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public String btnSeleccionarCuadra_action() {
		return navegarParaSeleccionar("AdminCuadra");
	}

	public String btnSeleccionarManzana_action() {
		return navegarParaSeleccionar("AdminManzana");
	}

	public String btnSeleccionarTituloPropiedad_action() {
		return navegarParaSeleccionar("AdminTituloPropiedad");
	}

	public String btnConsultar_action() {
		return toAbm(new ParcelaModel().new ConsultarParcelaController());
	}

	public String btnSubdividirSubparcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Parcela parcela = (Parcela) obj;
					this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());

					getRequestBean1().setObjetoABM(parcela);

					this.setRowKeySeleccionado(this.getSeleccionado());
				} else {
					warn("Debe seleccionar una parcela para realizar subdivisiones.");
					return null;
				}
			} catch(Exception ex) {
				log("AdminParcela" + "_SubdividirSubparcelaError:", ex);
				error("Administraci\363n de Parcelas" + " - Subdividir Subparcela: " + ex.getMessage());
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				this.getRequestBean1().setAccion(Constantes.ACCION_GOTO_ELIMINAR);
				retorno = "AdministrarSubdivisiones";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnVolantesCatastrales_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Parcela parcela = null;

		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();
				VolanteCatastral nuevoVolante = null;
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					parcela = (Parcela) obj;

					if(parcela != null && parcela.getEstado() != Parcela.Estado.ELIMINADO) {
						this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
						nuevoVolante = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().generarVolanteCatastral(parcela);
						info("El Volante Catastral se agreg\363 exitosamente.");
					} else {
						warn("No se puede generar un Volante Catastral de una parcela eliminada.");
						return "";
					}
				}
			} catch(Exception ex) {
				log("ModificarParcela" + "_AgregarVolanteCatastralError:", ex);
				error("Modificar Parcela" + " - Agregar Volante Catastral: " + ex.getMessage());
			}
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfCuadra());
			FiltroParcela locFiltro = getFiltro();
			locFiltro.setCuadra(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarManzana_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfManzana());
			FiltroParcela locFiltro = getFiltro();
			locFiltro.setManzana(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.ddCalle.setSelected(null);

			this.ddCalleComienzaOptions.setOptions(new Option[0]);
			this.ddCalleFinalizaOptions.setOptions(new Option[0]);
			this.limpiarCalleComienzoFin(true, true);
			FiltroParcela locFiltro = getFiltro();
			locFiltro.setCalle(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.ddZona.setSelected(null);

			FiltroParcela locFiltro = getFiltro();
			locFiltro.setZona(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCalle_action() {
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnSeleccionarZona_action() {
		return navegarParaSeleccionar("AdminZona");
	}

	private void limpiarCalleComienzoFin(boolean pLimpiarComienzo, boolean pLimpiarFin) {
		if(pLimpiarComienzo) {
			if(this.getComunicationCatastroBean().getListaCallesComienzoDinamica() != null) {
				this.getComunicationCatastroBean().getListaCallesComienzoDinamica().clear();
			}
		}

		if(pLimpiarFin) {
			if(this.getComunicationCatastroBean().getListaCallesFinDinamica() != null) {
				this.getComunicationCatastroBean().getListaCallesFinDinamica().clear();
			}
		}
	}

	private void cargarComboCallesComienzaCuadras(Calle pCalle) {
		List<String> locListaCallesComienzan = this.getComunicationCatastroBean().getListaCuadrasCalleComienzo(pCalle);
		Option[] opCuadras = new Option[locListaCallesComienzan.size() + 1];
		int i = 0;
		opCuadras[i++] = new Option("", "");
		for(String cadaCalle : locListaCallesComienzan) {
			opCuadras[i++] = new Option(cadaCalle, cadaCalle);
		}

		this.ddCalleComienzaOptions.setOptions(opCuadras);
		this.ddCalleComienza.setSelected("");
	}

	private void cargarComboCallesFinalizaCuadras(Calle pCalle, Calle pCalleComienza) {
		List<String> locListaCallesFin = this.getComunicationCatastroBean().getListaCuadrasCalleFin(pCalle, pCalleComienza);
		Option[] opCuadras = new Option[locListaCallesFin.size() + 1];
		int i = 0;
		opCuadras[i++] = new Option("", "");
		for(String cadaCalle : locListaCallesFin) {
			opCuadras[i++] = new Option(cadaCalle, cadaCalle);
		}

		this.ddCalleFinalizaOptions.setOptions(opCuadras);
		this.ddCalleFinaliza.setSelected("");
	}

	private Calle getCallePorNombre(String pCalle) {
		return this.getComunicationCatastroBean().getMapaCalles().get(pCalle);
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		this.limpiarCalleComienzoFin(true, true);

		if(this.ddCalle.getSelected().toString().equals("")) {
			this.ddCalleComienzaOptions.setOptions(new Option[0]);
		} else {
			Calle locCalleSeleccionada = this.getCallePorNombre(this.ddCalle.getSelected().toString());
			this.cargarComboCallesComienzaCuadras(locCalleSeleccionada);
		}

		this.ddCalleFinalizaOptions.setOptions(new Option[0]);
	}

	public void valueChangeEventDdCalleComienza(ValueChangeEvent event) {
		this.limpiarCalleComienzoFin(false, true);

		if(this.ddCalleComienza.getSelected().toString().equals("")) {
			this.ddCalleFinalizaOptions.setOptions(new Option[0]);
		} else {
			Calle locCalleSeleccionada = this.getCallePorNombre(this.ddCalle.getSelected().toString());
			Calle locCalleSeleccionadaComienza = this.getCallePorNombre(this.ddCalleComienza.getSelected().toString());

			// this.cargarComboCallesComienzaCuadras(locCalleSeleccionada);
			this.cargarComboCallesFinalizaCuadras(locCalleSeleccionada, locCalleSeleccionadaComienza);
		}
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Parcela locParcela = (Parcela) pObject;
		getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
		locParcela = getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(locParcela.getIdParcela());
		return locParcela;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Parcelas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminParcela";
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return getComunicationCatastroBean().getTablePhaseListenerParcelas();
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaParcela();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroParcela locFiltro = getFiltro();
		if(pObject instanceof Cuadra) {
			if(pObject != null) {
				Cuadra cuadra = (Cuadra) pObject;
				locFiltro.setCuadra(cuadra);
			}
		} else if(pObject instanceof Manzana) {
			if(pObject != null) {
				Manzana manzana = (Manzana) pObject;
				locFiltro.setManzana(manzana);
			}
		} else if(pObject instanceof Persona) {
			if(pObject != null) {
				Persona propietario = (Persona) pObject;
				locFiltro.setPersona(propietario);
				this.getSessionBean1().setPersonaSeleccionada(propietario);
			}
		} else if(pObject instanceof Calle) {
			if(pObject != null) {
				Calle calle = (Calle) pObject;
				locFiltro.setCalle(calle);

				this.limpiarCalleComienzoFin(true, true);
				locFiltro.setCalleComienza(null);
				locFiltro.setCalleFinaliza(null);
			}
		} else if(pObject instanceof Zona) {
			if(pObject != null) {
				Zona zona = (Zona) pObject;
				locFiltro.setZona(zona);
			}
		}
	}

	@Override
	protected Option[] armarOpcionesUsuarios() {
		Map<String, Usuario> locMapaUsuarios = this.getComunicationBean().getMapaUsuariosLogs(this.getSerialVersionUID());
		locMapaUsuarios.putAll(this.getComunicationBean().getMapaUsuariosLogs(TituloPropiedadParcelario.serialVersionUID));
		locMapaUsuarios.putAll(this.getComunicationBean().getMapaUsuariosLogs(PlanoConstruccion.serialVersionUID));
		locMapaUsuarios.putAll(this.getComunicationBean().getMapaUsuariosLogs(PlanoMensura.serialVersionUID));

		Option[] opUsuarios = new Option[locMapaUsuarios.keySet().size() + 1];
		opUsuarios[0] = new Option("", "");
		int i = 1;
		for(String cadaUsuario : locMapaUsuarios.keySet()) {
			opUsuarios[i++] = new Option(cadaUsuario, cadaUsuario);
		}
		return opUsuarios;
	}

	@Override
	protected Option[] armarOpcionesPropiedades() {
		List<String> locListaPropiedadesParcela = this.getComunicationBean().getListaNombresPropiedadesLogs(this.getSerialVersionUID());
		List<String> locListaPropiedadesTituloPropiedad = this.getComunicationBean().getListaNombresPropiedadesLogs(TituloPropiedadParcelario.serialVersionUID);
		List<String> locListaPropiedadesPlanoMensura = this.getComunicationBean().getListaNombresPropiedadesLogs(PlanoMensura.serialVersionUID);
		List<String> locListaPropiedadesPlanoConstruccion = this.getComunicationBean().getListaNombresPropiedadesLogs(PlanoConstruccion.serialVersionUID);
		List<String> locListaPropiedadesRegistroMejora = this.getComunicationBean().getListaNombresPropiedadesLogs(RegistroMejora.serialVersionUID);

		int cantidad = locListaPropiedadesParcela.size() + 
				locListaPropiedadesTituloPropiedad.size() + 
				locListaPropiedadesPlanoMensura.size() + 
				locListaPropiedadesPlanoConstruccion.size() +
				locListaPropiedadesRegistroMejora.size();

		Option[] opPropiedades = new Option[cantidad + 5];
		opPropiedades[0] = new Option("Parcela", "Parcela");
		opPropiedades[1] = new Option("Título Propiedad", "Título Propiedad");
		opPropiedades[2] = new Option("Plano Mensura", "Plano Mensura");
		opPropiedades[3] = new Option("Plano Construcción", "Plano Construcción");
		opPropiedades[4] = new Option("Registro Mejora", "Registro Mejora");
		int i = 5;
		for(String cadaPropiedad : locListaPropiedadesParcela) {
			opPropiedades[i++] = new Option("Parcela -> " + cadaPropiedad, "Parcela -> " + cadaPropiedad);
		}
		for(String cadaPropiedad : locListaPropiedadesTituloPropiedad) {
			opPropiedades[i++] = new Option("Título Propiedad -> " + cadaPropiedad, "Título Propiedad -> " + cadaPropiedad);
		}
		for(String cadaPropiedad : locListaPropiedadesPlanoMensura) {
			opPropiedades[i++] = new Option("Plano Mensura -> " + cadaPropiedad, "Plano Mensura -> " + cadaPropiedad);
		}
		for(String cadaPropiedad : locListaPropiedadesPlanoConstruccion) {
			opPropiedades[i++] = new Option("Plano Construcción -> " + cadaPropiedad, "Plano Construcción -> " + cadaPropiedad);
		}
		for(String cadaPropiedad : locListaPropiedadesRegistroMejora) {
			opPropiedades[i++] = new Option("Registro Mejora -> " + cadaPropiedad, "Registro Mejora -> " + cadaPropiedad);
		}
		return opPropiedades;
	}

	@Override
	public long getSerialVersionUID() {
		return Parcela.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMParcela$AdminParcela}";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroParcela locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;

		try {
			propietario = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}

	public String btnImprimirReporte_action() throws Exception {
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
					Parcela locParcela = (Parcela) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().getReporteInformacionParcelaria(locParcela.getIdParcela());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Parcela");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.ERRROR_EN_REPORTE, true);
		}
		return retorno;
	}
	
	/**
	 * Sobreescrito porque se debe poder ver el boton modificar si el usuario puede modificar
	 * Titulos de propiedad, Planos de Mensura, Planos de Construccion, Registros de Mejora, etc.
	 */
	@Override
	protected void deshabilitarBotonesAccion() throws RemoteException, Exception {
		int cont = 0;
		if(!SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Accion.INSERT)) {
			this.getBtnAgregar().setRendered(false);
			cont++;
		}
		if(!SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), this.getSerialVersionUID(), Accion.UPDATE)
				&& !SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Accion.UPDATE)
				&& !SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Accion.UPDATE)
				&& !SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Accion.UPDATE)
				&& !SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Accion.UPDATE)) {
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
}