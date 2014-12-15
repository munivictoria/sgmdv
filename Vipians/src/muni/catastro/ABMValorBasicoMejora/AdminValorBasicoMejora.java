/*
 * AdminValorBasicoMejora.java
 *
 * Created on 27 de octubre de 2006, 14:52
 * Copyright Trascender
 */
package muni.catastro.ABMValorBasicoMejora;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
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
public class AdminValorBasicoMejora extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
		// numberConverter1.setPattern("$ #,###,##0.000");
		numberConverter1.setPattern("$ #,##0.00");
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private HtmlAjaxCommandButton btnLimpiarCategoria = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCategoria() {
		return btnLimpiarCategoria;
	}

	public void setBtnLimpiarCategoria(HtmlAjaxCommandButton btnLimpiarCategoria) {
		this.btnLimpiarCategoria = btnLimpiarCategoria;
	}

	private ValorBasicoMejora valorBasicoABuscar = null;
	private Categoria categoriaSeleccionado = null;

	public ValorBasicoMejora getValorBasicoABuscar() {
		return valorBasicoABuscar;
	}

	public void setValorBasicoABuscar(ValorBasicoMejora valorBasicoABuscar) {
		this.valorBasicoABuscar = valorBasicoABuscar;
	}

	public Categoria getCategoriaSeleccionado() {
		return categoriaSeleccionado;
	}

	public void setCategoriaSeleccionado(Categoria categoriaSeleccionado) {
		this.categoriaSeleccionado = categoriaSeleccionado;
	}

	private TextField tfCategoria = new TextField();

	public TextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(TextField tf) {
		this.tfCategoria = tf;
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

	private ObjectListDataProvider ldpValorBasicoMejora = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpValorBasicoMejora() {
		return ldpValorBasicoMejora;
	}

	public void setLdpValorBasicoMejora(ObjectListDataProvider oldp) {
		this.ldpValorBasicoMejora = oldp;
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

	private Button btnSeleccionarCategoria = new Button();

	public Button getBtnSeleccionarCategoria() {
		return btnSeleccionarCategoria;
	}

	public void setBtnSeleccionarCategoria(Button b) {
		this.btnSeleccionarCategoria = b;
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
	public AdminValorBasicoMejora() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroValorBasicoMejora locFiltro = getFiltro();

		if (locFiltro.getCategoria() != null) {
			this.getTfCategoria().setText(locFiltro.getCategoria().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findListaValoresBasicosMejora((FiltroValorBasicoMejora) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroValorBasicoMejora locFiltro = getFiltro();
		locFiltro.setCategoria(null);
		
		this.getTfCategoria().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpValorBasicoMejora();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaValoresBasicosMejora();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaValoresBasicosMejora((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ValorBasicoMejoraModel().new AgregarValorBasicoMejoraController());
	}

	public String btnModificar_action() {
		return toAbm(new ValorBasicoMejoraModel().new ModificarValorBasicoMejoraController());
	}

	public String btnEliminar_action() {
		return toAbm(new ValorBasicoMejoraModel().new EliminarValorBasicoMejoraController());
	}

	public String btnSeleccionarCategoria_action() {
		return navegarParaSeleccionar("AdminCategoria");
	}

	public String btnLimpiarCategoria_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(getTfCategoria());
			FiltroValorBasicoMejora locFiltro = getFiltro();
			locFiltro.setCategoria(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultar_action() {
		return toAbm(new ValorBasicoMejoraModel().new ConsultarValorBasicoMejoraController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ValorBasicoMejora locValorBasicoMejora = (ValorBasicoMejora) pObject;
		getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
		locValorBasicoMejora = getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().getValorBasicoMejoraPorId(locValorBasicoMejora.getIdValorBasicoMejora());
		return locValorBasicoMejora;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Valores Basicos de Mejora";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminValorBasicoMejora";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaValorBasicoMejora();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Categoria) {
				Categoria categoria = (Categoria) pObject;
				FiltroValorBasicoMejora locFiltro = getFiltro();
				locFiltro.setCategoria(categoria);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return ValorBasicoMejora.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora}";
	}
}