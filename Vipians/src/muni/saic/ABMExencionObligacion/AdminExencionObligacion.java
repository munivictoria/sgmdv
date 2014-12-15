/*
 * AdminExencion.java
 */

package muni.saic.ABMExencionObligacion;

import java.util.List;

import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminExencionObligacion extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
	}

	private Label lblAnio = new Label();
	private Label lblObligacion = new Label();
	private Label lblPersona = new Label();
	private TextField tfAnio = new TextField();
	private TextField tfObligacion = new TextField();
	private TextField tfPersona = new TextField();
	private Button btnSeleccionarObligacionTGI = new Button();
	private Button btnSeleccionarObligacionSHPS = new Button();
	private Button btnSeleccionarObligacionOSP = new Button();
	private Button btnSeleccionarObligacionPFO = new Button();
	private Button btnSeleccionarObligacionCementerio = new Button();
	private Button btnSeleccionarObligacionAutomotor = new Button();
	private HtmlAjaxCommandButton btnLimpiarObligacion = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarObligacion() {
		return btnLimpiarObligacion;
	}

	public void setBtnLimpiarObligacion(HtmlAjaxCommandButton btnLimpiarObligacion) {
		this.btnLimpiarObligacion = btnLimpiarObligacion;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public TextField getTfObligacion() {
		return tfObligacion;
	}

	public void setTfObligacion(TextField tfObligacion) {
		this.tfObligacion = tfObligacion;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public Button getBtnSeleccionarObligacionTGI() {
		return btnSeleccionarObligacionTGI;
	}

	public void setBtnSeleccionarObligacionTGI(Button btnSeleccionarObligacionTGI) {
		this.btnSeleccionarObligacionTGI = btnSeleccionarObligacionTGI;
	}

	public Button getBtnSeleccionarObligacionSHPS() {
		return btnSeleccionarObligacionSHPS;
	}

	public void setBtnSeleccionarObligacionSHPS(Button btnSeleccionarObligacionSHPS) {
		this.btnSeleccionarObligacionSHPS = btnSeleccionarObligacionSHPS;
	}

	public Button getBtnSeleccionarObligacionOSP() {
		return btnSeleccionarObligacionOSP;
	}

	public void setBtnSeleccionarObligacionOSP(Button btnSeleccionarObligacionOSP) {
		this.btnSeleccionarObligacionOSP = btnSeleccionarObligacionOSP;
	}

	public Button getBtnSeleccionarObligacionPFO() {
		return btnSeleccionarObligacionPFO;
	}

	public void setBtnSeleccionarObligacionPFO(Button btnSeleccionarObligacionPFO) {
		this.btnSeleccionarObligacionPFO = btnSeleccionarObligacionPFO;
	}

	public Button getBtnSeleccionarObligacionCementerio() {
		return btnSeleccionarObligacionCementerio;
	}

	public void setBtnSeleccionarObligacionCementerio(Button btnSeleccionarObligacionCementerio) {
		this.btnSeleccionarObligacionCementerio = btnSeleccionarObligacionCementerio;
	}

	public Button getBtnSeleccionarObligacionAutomotor() {
		return btnSeleccionarObligacionAutomotor;
	}

	public void setBtnSeleccionarObligacionAutomotor(Button btnSeleccionarObligacionAutomotor) {
		this.btnSeleccionarObligacionAutomotor = btnSeleccionarObligacionAutomotor;
	}

	private StaticText stNombre = new StaticText();

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText st) {
		this.stNombre = st;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Button btnTerminar = new Button();

	public Button getBtnTerminar() {
		return btnTerminar;
	}

	public void setBtnTerminar(Button b) {
		this.btnTerminar = b;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private ObjectListDataProvider ldpExencionO = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpExencionO() {
		return ldpExencionO;
	}

	public void setLdpExencionO(ObjectListDataProvider oldp) {
		this.ldpExencionO = oldp;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TableColumn tcNombre = new TableColumn();

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tc) {
		this.tcNombre = tc;
	}

	private TableColumn tcPorcentaje = new TableColumn();

	public TableColumn getTcPorcentaje() {
		return tcPorcentaje;
	}

	public void setTcPorcentaje(TableColumn tc) {
		this.tcPorcentaje = tc;
	}

	private TableColumn tcNumeroCuota = new TableColumn();

	public TableColumn getTcNumeroCuota() {
		return tcNumeroCuota;
	}

	public void setTcNumeroCuota(TableColumn tcNumeroCuota) {
		this.tcNumeroCuota = tcNumeroCuota;
	}

	private TableColumn tcPeriodo = new TableColumn();

	public TableColumn getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TableColumn tc) {
		this.tcPeriodo = tc;
	}

	private StaticText stPorcentaje = new StaticText();

	public StaticText getStPorcentaje() {
		return stPorcentaje;
	}

	public void setStPorcentaje(StaticText stPorcentaje) {
		this.stPorcentaje = stPorcentaje;
	}

	private TableColumn tcEstado = new TableColumn();

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tc) {
		this.tcEstado = tc;
	}

	private StaticText stPeriodo = new StaticText();
	private StaticText stEstado = new StaticText();
	private StaticText stTipoDeuda = new StaticText();

	public StaticText getStTipoDeuda() {
		return stTipoDeuda;
	}

	public void setStTipoDeuda(StaticText stTipoDeuda) {
		this.stTipoDeuda = stTipoDeuda;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStPeriodo() {
		return stPeriodo;
	}

	public void setStPeriodo(StaticText st) {
		this.stPeriodo = st;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private HtmlPanelGrid grpCargando = new HtmlPanelGrid();

	public HtmlPanelGrid getGrpCargando() {
		return grpCargando;
	}

	public void setGrpCargando(HtmlPanelGrid hpg) {
		this.grpCargando = hpg;
	}

	private ImageComponent image1 = new ImageComponent();

	public ImageComponent getImage1() {
		return image1;
	}

	public void setImage1(ImageComponent ic) {
		this.image1 = ic;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private PanelGroup gpExencion1 = new PanelGroup();

	public PanelGroup getGpExencion1() {
		return gpExencion1;
	}

	public void setGpExencion1(PanelGroup pg) {
		this.gpExencion1 = pg;
	}

	private StaticText stMonto = new StaticText();

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText st) {
		this.stMonto = st;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public AdminExencionObligacion() {
	}

	@Override
	public void _prerender() {

	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroExencionObligacion locFiltro = this.getFiltro();

		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		locFiltro.setPersona(persona);

		locFiltro.setAnio(this.getTextFieldValueInteger(this.getTfAnio()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroExencionObligacion locFiltro = this.getFiltro();

		Persona persona = this.getSessionBean1().getPersonaSeleccionada();

		if(persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}

		if(locFiltro.getObligacion() != null) {
			this.getTfObligacion().setText(locFiltro.getObligacion().toString());
		}

		if(locFiltro.getAnio() != null) {
			this.getTfAnio().setText(locFiltro.getAnio().toString());
		}
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroExencionObligacion locFiltro = this.getFiltro();
		this.getSessionBean1().setPersonaSeleccionada(null);
		locFiltro.setPersona(null);
		locFiltro.setObligacion(null);
		locFiltro.setAnio(null);

		this.getTfAnio().setText("");
		this.getTfObligacion().setText("");
		this.getTfPersonaSeleccionada().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpExencionO();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaExencionObligacion();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaExencionObligacion(lista);

	}

	public String btnAgregar_action() {
		return toAbm(new ExencionObligacionModel().new AgregarExencionObligacionController());
	}

	public String btnModificar_action() {
		ExencionObligacion locExencion = (ExencionObligacion) this.getObjetoSeleccionado();
		if(locExencion.getEstado().equals(ExencionObligacion.Estado.TERMINADA)) {
			warn("Las exenciones en estado Terminada no pueden ser modificadas");
			return null;
		}
		return toAbm(new ExencionObligacionModel().new ModificarExencionObligacionController());
	}

	public String btnTerminar_action() {
		ExencionObligacion locExencion = (ExencionObligacion) this.getObjetoSeleccionado();
		if(locExencion.getEstado().equals(ExencionObligacion.Estado.TERMINADA)) {
			warn("No se pueden terminar exenciones que se encuentren en Estado Terminada.");
			return null;
		}
		return toAbm(new ExencionObligacionModel().new TerminarExencionObligacionController());
	}

	public String btnConsultar_action() {
		return toAbm(new ExencionObligacionModel().new ConsultarExencionObligacionController());
	}

	public String btnLimpiarObligacion_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			FiltroExencionObligacion locFiltro = this.getFiltro();
			locFiltro.setObligacion(null);
			this.getTfObligacion().setText(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionOSP_action() {
		return navegarParaSeleccionar("AdminDocEspOSP");
	}

	public String btnSeleccionarObligacionTGI_action() {
		return navegarParaSeleccionar("AdminDocEspTGI");
	}

	public String btnSeleccionarObligacionSHPS_action() {
		return navegarParaSeleccionar("AdminDocEspSHPS");
	}

	public String btnSeleccionarObligacionPFO_action() {
		return navegarParaSeleccionar("AdminDocEspPFO");
	}

	public String btnSeleccionarObligacionAutomotor_action() {
		return navegarParaSeleccionar("AdminDocEspAutomotor");
	}

	public String btnSeleccionarObligacionCementerio_action() {
		return navegarParaSeleccionar("AdminDocEspCementerio");
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ExencionObligacion locExencionObligacion = (ExencionObligacion) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
		locExencionObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemExencion().getExencionObligacionByID(locExencionObligacion.getIdExencion());
		return locExencionObligacion;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemExencion().findListaExencionesObligacion((FiltroExencionObligacion) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaExencionObligacion();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroExencionObligacion locFiltro = this.getFiltro();

		if(pObject instanceof Obligacion) {
			Obligacion locObligacion = (Obligacion) pObject;
			locFiltro.setObligacion(locObligacion);
			this.getTfObligacion().setText(null);
		}
		if(pObject instanceof Persona) {
			this.getSessionBean1().setPersonaSeleccionada((Persona) pObject);
		}
		this.limpiarTabla();
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Exenciones de Obligaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminExencionObligacion";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMExencionObligacion$AdminExencionObligacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return ExencionObligacion.serialVersionUID;
	}
}