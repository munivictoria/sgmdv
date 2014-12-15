/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.catastro.ABMManzana;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.catastro.recurso.filtros.FiltroManzana;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminManzana extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		Set<String> locListaCalles = this.getComunicationCatastroBean().getMapaCalles().keySet();
		Option[] opCalles = new Option[locListaCalles.size() + 1];
		int i = 0;
		opCalles[i++] = new Option("", "");
		for (String cadaCalle : locListaCalles) {
			opCalles[i++] = new Option(cadaCalle, cadaCalle);
		}
		this.ddCalleOptions.setOptions(opCalles);

		this.habilitarBtnExportar();
	}

	private  Label lblCuadra = new Label();
	private Label label1 = new Label();
	private  TextField tfCuadra = new TextField();

	private DropDown ddCalle = new DropDown();
	private SingleSelectOptionsList ddCalleOptions = new SingleSelectOptionsList();

	private Button btnSeleccionarCuadra = new Button();
	private Button btnSeleccionarCalle = new Button();
	private HtmlAjaxCommandButton btnLimpiarCuadra = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public DropDown getDdCalle() {
		return ddCalle;
	}

	public void setDdCalle(DropDown ddCalle) {
		this.ddCalle = ddCalle;
	}

	public SingleSelectOptionsList getDdCalleOptions() {
		return ddCalleOptions;
	}

	public void setDdCalleOptions(SingleSelectOptionsList ddCalleOptions) {
		this.ddCalleOptions = ddCalleOptions;
	}

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button btnSeleccionarCalle) {
		this.btnSeleccionarCalle = btnSeleccionarCalle;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public Label getLblCuadra() {
		return lblCuadra;
	}

	public void setLblCuadra(Label lblCuadra) {
		this.lblCuadra = lblCuadra;
	}

	public TextField getTfCuadra() {
		return tfCuadra;
	}

	public void setTfCuadra(TextField tfCuadra) {
		this.tfCuadra = tfCuadra;
	}

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button btnSeleccionarCuadra) {
		this.btnSeleccionarCuadra = btnSeleccionarCuadra;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCuadra() {
		return btnLimpiarCuadra;
	}

	public void setBtnLimpiarCuadra(HtmlAjaxCommandButton btnLimpiarCuadra) {
		this.btnLimpiarCuadra = btnLimpiarCuadra;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpManzana = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpManzana() {
		return ldpManzana;
	}

	public void setLdpManzana(ObjectListDataProvider oldp) {
		this.ldpManzana = oldp;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	private Label lblNumero = new Label();

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	private TextField tfNumero = new TextField();

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminManzana() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {

		FiltroManzana locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Manzana.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroManzana locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setNumero(this.getTextFieldValueInteger(getTfNumero()));
		locFiltro.setCalle(this.getDDObjectValue(this.getDdCalle(), this.getComunicationCatastroBean().getMapaCalles()));

		if (locFiltro.getListaAtributoDinamico() != null) {
			locFiltro.setListaAtributoDinamico(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroManzana locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfNumero().setText(locFiltro.getNumero());
		if (locFiltro.getCuadra() != null){
			this.getTfCuadra().setText(locFiltro.getCuadra().toString());
		}
		if (locFiltro.getCalle() != null){
			this.getDdCalle().setSelected(locFiltro.getCalle().toString());
		}
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{catastro$ABMManzana$ABMManzana}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().findListaManzanas((FiltroManzana) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroManzana locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setNumero(null);
		locFiltro.setCalle(null);
		locFiltro.setCuadra(null);

		this.getTfNombre().setText("");
		this.getTfNumero().setText("");
		this.getTfCuadra().setText("");
		this.getDdCalle().setSelected("");
		panelAtributoDinamico.limpiarCampos();
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpManzana();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaManzanas();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaManzanas((ArrayList) lista);
	}

	private TableSelectPhaseListener tablePhaseListener;

	public String btnAgregar_action() {
		return toAbm(new ManzanaModel().new AgregarManzanaController());
	}

	public String btnModificar_action() {
		return toAbm(new ManzanaModel().new ModificarManzanaController());
	}

	public String btnEliminar_action() {
		return toAbm(new ManzanaModel().new EliminarManzanaController());
	}

	public String btnConsultar_action() {
		return toAbm(new ManzanaModel().new ConsultarManzanaController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return getComunicationCatastroBean().getTablePhaseListenerManzana();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Manzana locManzana = (Manzana) pObject;
		getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
		locManzana = getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getManzanaPorId(locManzana.getIdManzana());
		return locManzana;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Manzanas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminManzana";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaManzana();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroManzana locFiltro = getFiltro();
		if (pObject instanceof Cuadra) {
			Cuadra cuadra = (Cuadra) pObject;
			locFiltro.setCuadra(cuadra);
		}
		if (pObject instanceof Calle){
			Calle calle = (Calle) pObject;
			locFiltro.setCalle(calle);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Manzana.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMManzana$AdminManzana}";
	}

	public String btnSeleccionarCuadra_action() {
		return navegarParaSeleccionar("AdminCuadra");
	}

	public String btnSeleccionarCalle_action() {
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalle.setSelected(null);
			FiltroManzana locFiltro = getFiltro();
			locFiltro.setCalle(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.tfCuadra.setText("");
			FiltroManzana locFiltro = getFiltro();
			locFiltro.setCuadra(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
}