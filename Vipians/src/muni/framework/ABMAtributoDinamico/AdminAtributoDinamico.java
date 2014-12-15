/*
 * AdminAtributoDinamico.java
 *
 * Created on 1 de septiembre de 2006, 14:11
 * Copyright Trascender
 */
package muni.framework.ABMAtributoDinamico;

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
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This8 class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminAtributoDinamico extends AdminPageBean {

	private Button btnSeleccionarRecurso = new Button();
	private HtmlAjaxCommandButton btnLimpiarRecurso = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarRecurso() {
		return btnLimpiarRecurso;
	}

	public void setBtnLimpiarRecurso(HtmlAjaxCommandButton btnLimpiarRecurso) {
		this.btnLimpiarRecurso = btnLimpiarRecurso;
	}

	public Button getBtnSeleccionarRecurso() {
		return btnSeleccionarRecurso;
	}

	public void setBtnSeleccionarRecurso(Button btnSeleccionarRecurso) {
		this.btnSeleccionarRecurso = btnSeleccionarRecurso;
	}

	// ----------------------------Label----------------------------------------
	private Label label1 = new Label();
	private Label label2 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	// -------------------------------------------------------------------------
	// ----------------------------StaticText-----------------------------------
	private StaticText staticText1 = new StaticText();
	private StaticText staticText2 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText staticText3) {
		this.staticText3 = staticText3;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	// ----------------------------TextField------------------------------------
	private TextField tfNombre = new TextField();
	private TextField tfRecurso = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TextField getTfRecurso() {
		return tfRecurso;
	}

	public void setTfRecurso(TextField tfRecurso) {
		this.tfRecurso = tfRecurso;
	}

	// ----------------------------Tabla----------------------------------------
	private ObjectListDataProvider ldpAtributosDinamicos = new ObjectListDataProvider();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();

	public ObjectListDataProvider getLdpAtributosDinamicos() {
		return ldpAtributosDinamicos;
	}

	public void setLdpAtributosDinamicos(ObjectListDataProvider ldpAtributosDinamicos) {
		this.ldpAtributosDinamicos = ldpAtributosDinamicos;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	// Marcos: Nuevo ver
	public boolean isDatosVacios() {
		boolean retorno = false;

		PersonaFisica personaFisica = (PersonaFisica) this.getElementoPila().getObjetos().get(0);

		if (personaFisica.getNombre().equals("") && personaFisica.getApellido().equals("") && personaFisica.getNumeroDocumento().equals("") && personaFisica.getCuil().equals("")) {
			retorno = true;
		}
		return retorno;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroPlantillaAtributosDinamicos locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroPlantillaAtributosDinamicos locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getRecurso() != null) {
			this.getTfRecurso().setText(locFiltro.getRecurso().getNombre());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().findListaPlantillaAtritbutosDinamicos((FiltroPlantillaAtributosDinamicos) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroPlantillaAtributosDinamicos locFiltro = getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setRecurso(null);
		
		this.getTfNombre().setText("");
		this.getTfRecurso().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpAtributosDinamicos();
	}

	@Override
	protected List getListaDelCommunication() {
		return (ArrayList) this.getComunicationBean().getListaAtributosDinamicos();
	}

	public String btnAgregar_action() {
		return toAbm(new AtributoDinamicoModel().new AgregarAtributoDinamicoController());
	}

	public String btnModificar_action() {
		return toAbm(new AtributoDinamicoModel().new ModificarAtributoDinamicoController());
	}

	public String btnEliminar_action() {
		return toAbm(new AtributoDinamicoModel().new EliminarAtributoDinamicoController());
	}

	public String btnConsultar_action() {
		return toAbm(new AtributoDinamicoModel().new ConsultarAtributoDinamicoController());
	}

	public String btnSeleccionarRecurso_action() {
		return navegarParaSeleccionar("SeleccionarRecurso");
	}

	public String btnLimpiarRecurso_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(getTfRecurso());
			FiltroPlantillaAtributosDinamicos locFiltro = getFiltro();
			locFiltro.setRecurso(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaAtributosDinamicos((ArrayList) lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) pObject;
		this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().getPlantillaAtributoDinamicoPorId(locPlantilla.getIdPlantillaAtributoDinamico());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Atributos Dinámicos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminAtributoDinamico";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaAtributoDinamico();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroPlantillaAtributosDinamicos locFiltro = getFiltro();
		if (pObject instanceof Recurso) {
			Recurso locRecurso = (Recurso) pObject;

			locFiltro.setRecurso(locRecurso);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return PlantillaAtributoDinamico.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMAtributoDinamico$AdminAtributoDinamico}";
	}
}