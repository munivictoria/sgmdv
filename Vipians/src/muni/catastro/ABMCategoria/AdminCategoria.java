/*
 * AdminCategoria.java
 *
 * Created on 26 de octubre de 2006, 09:43
 * Copyright Trascender
 */
package muni.catastro.ABMCategoria;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminCategoria extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.mostrarCantidadRegistros();
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private HtmlAjaxCommandButton btnLimpiarTipoConstruccion = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarTipoConstruccion() {
		return btnLimpiarTipoConstruccion;
	}

	public void setBtnLimpiarTipoConstruccion(HtmlAjaxCommandButton btnLimpiarTipoConstruccion) {
		this.btnLimpiarTipoConstruccion = btnLimpiarTipoConstruccion;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfTipoConstruccion = new TextField();

	public TextField getTfTipoConstruccion() {
		return tfTipoConstruccion;
	}

	public void setTfTipoConstruccion(TextField tf) {
		this.tfTipoConstruccion = tf;
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

	private ObjectListDataProvider ldpCategoria = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCategoria() {
		return ldpCategoria;
	}

	public void setLdpCategoria(ObjectListDataProvider oldp) {
		this.ldpCategoria = oldp;
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

	private Button btnSeleccionarTipoConstruccion = new Button();

	public Button getBtnSeleccionarTipoConstruccion() {
		return btnSeleccionarTipoConstruccion;
	}

	public void setBtnSeleccionarTipoConstruccion(Button b) {
		this.btnSeleccionarTipoConstruccion = b;
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

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tf) {
		this.tfCodigo = tf;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
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

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminCategoria() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCategoria locFiltro = getFiltro();

		locFiltro.setCodigoCategoria(this.getTextFieldValueInteger(getTfCodigo()));
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCategoria locFiltro = getFiltro();

		if (locFiltro.getCodigoCategoria() != null) {
			this.getTfCodigo().setText(locFiltro.getCodigoCategoria().toString());
		}
		this.getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getTipoConstruccion() != null) {
			this.getTfTipoConstruccion().setText(locFiltro.getTipoConstruccion().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findListaCategorias((FiltroCategoria) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCategoria locFiltro = getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setCodigoCategoria(null);
		locFiltro.setTipoConstruccion(null);

		this.getTfNombre().setText("");
		this.getTfCodigo().setText("");
		this.getTfTipoConstruccion().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCategoria();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCategorias();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaCategorias((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new CategoriaModel().new AgregarCategoriaController());
	}

	public String btnModificar_action() {
		return toAbm(new CategoriaModel().new ModificarCategoriaController());
	}

	public String btnEliminar_action() {
		return toAbm(new CategoriaModel().new EliminarCategoriaController());
	}

	public String btnSeleccionarTipoConstruccion_action() {
		return navegarParaSeleccionar("AdminTipoConstruccion");
	}

	/**
	 * Mines: boton consultar..
	 */
	public String btnConsultar_action() {
		return toAbm(new CategoriaModel().new ConsultarCategoriaController());
	}

	public void mostrarCantidadRegistros() {
		this.getStCantidadRegistros().setText(Integer.toString(this.getListaDelCommunication().size()));
	}

	private Categoria categoriaABuscar;

	public Categoria getCategoriaABuscar() {
		if (this.categoriaABuscar == null) {
			this.categoriaABuscar = new Categoria();
		}
		return categoriaABuscar;
	}

	public void setCategoriaABuscar(Categoria categoriaABuscar) {
		this.categoriaABuscar = categoriaABuscar;
	}

	private TipoConstruccion tipoConstruccionSeleccionado;

	public TipoConstruccion getTipoConstruccionSeleccionado() {
		if (this.tipoConstruccionSeleccionado == null) {
			this.tipoConstruccionSeleccionado = new TipoConstruccion();
		}
		return tipoConstruccionSeleccionado;
	}

	public void setTipoConstruccionSeleccionado(TipoConstruccion tipoConstruccionSeleccionado) {
		this.tipoConstruccionSeleccionado = tipoConstruccionSeleccionado;
	}

	public String btnLimpiarTipoConstruccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(getTfTipoConstruccion());
			FiltroCategoria locFiltro = getFiltro();
			locFiltro.setTipoConstruccion(null);

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
		Categoria locCategoria = (Categoria) pObject;
		getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
		locCategoria = getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().getCategoriaPorId(locCategoria.getIdCategoria());
		return locCategoria;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Categorias";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCategoria";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaCategoria();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof TipoConstruccion) {
			FiltroCategoria locFiltro = getFiltro();
			if (pObject != null) {
				TipoConstruccion tipoConstruccion = (TipoConstruccion) pObject;
				locFiltro.setTipoConstruccion(tipoConstruccion);
			}
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Categoria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCategoria$AdminCategoria}";
	}
}