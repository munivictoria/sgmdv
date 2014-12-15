/*
 * AdminCuadra.java
 *
 * Created on 31 de octubre de 2006, 08:25
 * Copyright Trascender SRL
 */
package muni.catastro.ABMCuadra;

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
import com.trascender.catastro.recurso.filtros.FiltroCuadra;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component defions (and initialization code) for all components that you have
 * defined on this page, as well as lifecycle methods and event handlers where
 * you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminCuadra extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			this.mostrarCantidadRegistros();
		}

		this.habilitarBtnExportar();

		ddTipoNumeracionDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] { new com.sun.rave.web.ui.model.Option("", ""),
				new com.sun.rave.web.ui.model.Option("P", "Par"), new com.sun.rave.web.ui.model.Option("I", "Impar") });
		ddTipoNumeracionDefaultOptions.setSelectedValue("");

		Set<String> locListaCalles = this.getComunicationCatastroBean().getMapaCalles().keySet();
		Option[] opCalles = new Option[locListaCalles.size() + 1];
		int i = 0;
		opCalles[i++] = new Option("", "");
		for (String cadaCalle : locListaCalles) {
			opCalles[i++] = new Option(cadaCalle, cadaCalle);
		}
		this.ddCalleOptions.setOptions(opCalles);
		this.ddCalleComienzaOptions.setOptions(opCalles);
		this.ddCalleFinalizaOptions.setOptions(opCalles);
	}

	private Label lblManzana = new Label();
	private Label label1 = new Label();
	private TextField tfManzana = new TextField();

	public TextField getTfManzana() {
		return tfManzana;
	}

	public void setTfManzana(TextField tfManzana) {
		this.tfManzana = tfManzana;
	}

	public Label getLblManzana() {
		return lblManzana;
	}

	public void setLblManzana(Label lblManzana) {
		this.lblManzana = lblManzana;
	}

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

	private SingleSelectOptionsList ddCalleOptions = new SingleSelectOptionsList();
	private DropDown ddCalle = new DropDown();

	public DropDown getDdCalle() {
		return ddCalle;
	}

	public void setDdCalle(DropDown ddCalle) {
		this.ddCalle = ddCalle;
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

	private ObjectListDataProvider ldpCuadra = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCuadra() {
		return ldpCuadra;
	}

	public void setLdpCuadra(ObjectListDataProvider oldp) {
		this.ldpCuadra = oldp;
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

	private Button btnSeleccionarCalle = new Button();

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button b) {
		this.btnSeleccionarCalle = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private SingleSelectOptionsList ddCalleComienzaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleComienza = new DropDown();

	public DropDown getDdCalleComienza() {
		return ddCalleComienza;
	}

	public void setDdCalleComienza(DropDown ddCalleComienza) {
		this.ddCalleComienza = ddCalleComienza;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tc) {
		this.tableColumn8 = tc;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private SingleSelectOptionsList ddCalleFinalizaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleFinaliza = new DropDown();

	public DropDown getDdCalleFinaliza() {
		return ddCalleFinaliza;
	}

	public void setDdCalleFinaliza(DropDown ddCalleFinaliza) {
		this.ddCalleFinaliza = ddCalleFinaliza;
	}

	public SingleSelectOptionsList getDdCalleComienzaOptions() {
		return ddCalleComienzaOptions;
	}

	public void setDdCalleComienzaOptions(SingleSelectOptionsList ddCalleComienzaOptions) {
		this.ddCalleComienzaOptions = ddCalleComienzaOptions;
	}

	public SingleSelectOptionsList getDdCalleFinalizaOptions() {
		return ddCalleFinalizaOptions;
	}

	public void setDdCalleFinalizaOptions(SingleSelectOptionsList ddCalleFinalizaOptions) {
		this.ddCalleFinalizaOptions = ddCalleFinalizaOptions;
	}

	public SingleSelectOptionsList getDdCalleOptions() {
		return ddCalleOptions;
	}

	public void setDdCalleOptions(SingleSelectOptionsList ddCalleOptions) {
		this.ddCalleOptions = ddCalleOptions;
	}

	private TextField tfNumeracionDesde = new TextField();

	public TextField getTfNumeracionDesde() {
		return tfNumeracionDesde;
	}

	public void setTfNumeracionDesde(TextField tf) {
		this.tfNumeracionDesde = tf;
	}

	private TextField tfNumeracionHasta = new TextField();

	public TextField getTfNumeracionHasta() {
		return tfNumeracionHasta;
	}

	public void setTfNumeracionHasta(TextField tf) {
		this.tfNumeracionHasta = tf;
	}

	private Button btnSeleccionarCalleComienza = new Button();
	private Button btnSeleccionarManzana = new Button();

	public Button getBtnSeleccionarManzana() {
		return btnSeleccionarManzana;
	}

	public void setBtnSeleccionarManzana(Button btnSeleccionarManzana) {
		this.btnSeleccionarManzana = btnSeleccionarManzana;
	}

	public Button getBtnSeleccionarCalleComienza() {
		return btnSeleccionarCalleComienza;
	}

	public void setBtnSeleccionarCalleComienza(Button b) {
		this.btnSeleccionarCalleComienza = b;
	}

	private Button btnSeleccionarCalleFinaliza = new Button();

	public Button getBtnSeleccionarCalleFinaliza() {
		return btnSeleccionarCalleFinaliza;
	}

	public void setBtnSeleccionarCalleFinaliza(Button b) {
		this.btnSeleccionarCalleFinaliza = b;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private DropDown ddTipoNumeracion = new DropDown();

	public DropDown getDdTipoNumeracion() {
		return ddTipoNumeracion;
	}

	public void setDdTipoNumeracion(DropDown dd) {
		this.ddTipoNumeracion = dd;
	}

	private SingleSelectOptionsList ddTipoNumeracionDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoNumeracionDefaultOptions() {
		return ddTipoNumeracionDefaultOptions;
	}

	public void setDdTipoNumeracionDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoNumeracionDefaultOptions = ssol;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarComienza = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarTerminaEn = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarManzanaCuadra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarManzanaCuadra() {
		return btnLimpiarManzanaCuadra;
	}

	public void setBtnLimpiarManzanaCuadra(
			HtmlAjaxCommandButton btnLimpiarManzanaCuadra) {
		this.btnLimpiarManzanaCuadra = btnLimpiarManzanaCuadra;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public HtmlAjaxCommandButton getBtnLimpiarComienza() {
		return btnLimpiarComienza;
	}

	public void setBtnLimpiarComienza(HtmlAjaxCommandButton btnLimpiarComienza) {
		this.btnLimpiarComienza = btnLimpiarComienza;
	}

	public HtmlAjaxCommandButton getBtnLimpiarTerminaEn() {
		return btnLimpiarTerminaEn;
	}

	public void setBtnLimpiarTerminaEn(HtmlAjaxCommandButton btnLimpiarTerminaEn) {
		this.btnLimpiarTerminaEn = btnLimpiarTerminaEn;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminCuadra() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Integer(0));// Nro para saber que tipo de
		// calle se selecciono

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCuadra locFiltro = this.getFiltro();

		Object tipoNumeracion = this.getDdTipoNumeracion().getSelected();
		if ((tipoNumeracion != null) && (tipoNumeracion.toString().length() > 0)) {
			locFiltro.setTipoNumeracion(new Character(tipoNumeracion.toString().charAt(0)));
		} else {
			locFiltro.setTipoNumeracion(null);
		}
		locFiltro.setNumeracionDesde(this.getTextFieldValueInteger(getTfNumeracionDesde()));
		locFiltro.setNumeracionHasta(this.getTextFieldValueInteger(getTfNumeracionHasta()));
		locFiltro.setCalle(this.getDDObjectValue(getDdCalle(), this.getComunicationCatastroBean().getMapaCalles()));
		locFiltro.setCalleComienza(this.getDDObjectValue(getDdCalleComienza(), this.getComunicationCatastroBean().getMapaCalles()));
		locFiltro.setCalleFinaliza(this.getDDObjectValue(getDdCalleFinaliza(), this.getComunicationCatastroBean().getMapaCalles()));
		locFiltro.setManzana(locFiltro.getManzana());
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCuadra locFiltro = this.getFiltro();

		if (locFiltro.getCalle() != null) {
			this.getDdCalle().setSelected(locFiltro.getCalle().toString());
		}

		if (locFiltro.getCalleComienza() != null) {
			this.getDdCalleComienza().setSelected(locFiltro.getCalleComienza().toString());
		}

		if (locFiltro.getCalleFinaliza() != null) {
			this.getDdCalleFinaliza().setSelected(locFiltro.getCalleFinaliza().toString());
		}
		if (locFiltro.getManzana() != null) {
			this.getTfManzana().setText(locFiltro.getManzana().toString());
		}

		this.getDdTipoNumeracion().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoNumeracion())));
		this.getDdTipoNumeracionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoNumeracion())));
		if (locFiltro.getNumeracionDesde() != null) {
			this.getTfNumeracionDesde().setText(locFiltro.getNumeracionDesde().toString());
		}
		if (locFiltro.getNumeracionHasta() != null) {
			this.getTfNumeracionHasta().setText(locFiltro.getNumeracionHasta().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().findListaCuadras((FiltroCuadra) pFiltro);
	}

	private Calle getCallePorNombre(String pCalle) {
		return this.getComunicationCatastroBean().getMapaCalles().get(pCalle);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCuadra locFiltro = this.getFiltro();
		locFiltro.setTipoNumeracion(null);
		locFiltro.setNumeracionDesde(null);
		locFiltro.setNumeracionHasta(null);
		locFiltro.setCalle(null);
		locFiltro.setCalleComienza(null);
		locFiltro.setCalleFinaliza(null);
		locFiltro.setManzana(null);

		this.getDdCalle().setSelected("");
		this.ddCalle.setSelected(null);
		this.getDdCalleComienza().setSelected("");
		this.ddCalleComienza.setSelected(null);
		this.getDdCalleFinaliza().setSelected("");
		this.ddCalleFinaliza.setSelected(null);
		this.getDdTipoNumeracion().setSelected("");
		this.getDdTipoNumeracionDefaultOptions().setSelectedValue(null);
		this.getTfNumeracionDesde().setText("");
		this.getTfNumeracionHasta().setText("");
		this.getTfManzana().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCuadra();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCuadras();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaCuadras((ArrayList) lista);
	}

	private TableSelectPhaseListener tablePhaseListener;

	public String btnSeleccionarCalle_action() {
		this.getElementoPila().getObjetos().set(0, 1);
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnSeleccionarCalleComienza_action() {
		this.getElementoPila().getObjetos().set(0, 2);
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnSeleccionarCalleFinaliza_action() {
		this.getElementoPila().getObjetos().set(0, 3);
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnSeleccionarManzana_action() {
		this.getElementoPila().getObjetos().set(0, 4);
		return navegarParaSeleccionar("AdminManzana");
	}

	public String btnAgregar_action() {
		return toAbm(new CuadraModel().new AgregarCuadraController());
	}

	public String btnModificar_action() {
		return toAbm(new CuadraModel().new ModificarCuadraController());
	}

	public String btnEliminar_action() {
		return toAbm(new CuadraModel().new EliminarCuadraController());
	}

	public String btnConsultar_action() {
		return toAbm(new CuadraModel().new ConsultarCuadraController());
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalle.setSelected(null);
			FiltroCuadra locFiltro = getFiltro();
			locFiltro.setCalle(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarComienza_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalleComienza.setSelected(null);
			FiltroCuadra locFiltro = getFiltro();
			locFiltro.setCalleComienza(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void mostrarCantidadRegistros() {
		this.getStCantidadRegistros().setText(Integer.toString(this.getListaDelCommunication().size()));
	}

	public String btnLimpiarTerminaEn_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalleFinaliza.setSelected(null);
			FiltroCuadra locFiltro = getFiltro();
			locFiltro.setCalleFinaliza(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarManzanaCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.getTfManzana().setText("");
			FiltroCuadra locFiltro = getFiltro();
			locFiltro.setManzana(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return getComunicationCatastroBean().getTablePhaseListenerCuadras();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Cuadra locCuadra = (Cuadra) pObject;
		getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
		locCuadra = getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getCuadraPorId(locCuadra.getIdCuadra());
		return locCuadra;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Cuadras";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCuadra";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaCuadra();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroCuadra locFiltro = getFiltro();
		if (pObject instanceof Calle) {
			Calle calle = (Calle) pObject;
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			if (posicion == 1) {
				locFiltro.setCalle(calle);
			}
			if (posicion == 2) {
				locFiltro.setCalleComienza(calle);
			}
			if (posicion == 3) {
				locFiltro.setCalleFinaliza(calle);
			}
		}
		if (pObject instanceof Manzana){
			Manzana manzana = (Manzana) pObject;
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			if (posicion == 4){
				locFiltro.setManzana(manzana);
			}
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Cuadra.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCuadra$AdminCuadra}";
	}
}