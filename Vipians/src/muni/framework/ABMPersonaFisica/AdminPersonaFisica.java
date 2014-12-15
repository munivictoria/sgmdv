/*
 * AdminPersonaFisica.java
 *
 * Created on 1 de septiembre de 2006, 14:11
 * Copyright Trascender
 */
package muni.framework.ABMPersonaFisica;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

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
import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This8 class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminPersonaFisica extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		// CAMBIAR: Utilizar el EJBCliet adecuado.
		this.setListaDelCommunicationAtributosDinamicos(null);

		Option[] op = null;
		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoDocumento.values(), "may");
		ddTipoDocumentoDefaultOptions.setOptions(op);

		Option[] opEstado = null;

		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PersonaFisica.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(PersonaFisica.Estado.ACTIVO)));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(PersonaFisica.Estado.ACTIVO)));
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaPersonaFisica();
	}

	// ----------------------------Button---------------------------------------
	private Button btnImprimir = new Button();
	private Button btnActivar = new Button();

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
	}

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	// ----------------------------Dropdown-------------------------------------
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private DropDown ddTipoDocumento = new DropDown();
	private SingleSelectOptionsList ddTipoDocumentoDefaultOptions = new SingleSelectOptionsList();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public DropDown getDdTipoDocumento() {
		return ddTipoDocumento;
	}

	public void setDdTipoDocumento(DropDown ddTipoDocumento) {
		this.ddTipoDocumento = ddTipoDocumento;
	}

	public SingleSelectOptionsList getDdTipoDocumentoDefaultOptions() {
		return ddTipoDocumentoDefaultOptions;
	}

	public void setDdTipoDocumentoDefaultOptions(SingleSelectOptionsList ddTipoDocumentoDefaultOptions) {
		this.ddTipoDocumentoDefaultOptions = ddTipoDocumentoDefaultOptions;
	}

	// -------------------------------------------------------------------------
	// ----------------------------Label----------------------------------------
	private Label lblEstado = new Label();
	private Label label1 = new Label();
	private Label label3 = new Label();
	private Label label4 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
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

	// -------------------------------------------------------------------------
	// ----------------------------TextArea-------------------------------------
	// -------------------------------------------------------------------------
	// ----------------------------TextField------------------------------------
	private TextField tfCuil = new TextField();
	private TextField tfApellido = new TextField();
	private TextField tfNombre = new TextField();

	public TextField getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(TextField tfApellido) {
		this.tfApellido = tfApellido;
	}

	public TextField getTfCuil() {
		return tfCuil;
	}

	public void setTfCuil(TextField tfCuil) {
		this.tfCuil = tfCuil;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	// -------------------------------------------------------------------------
	// ----------------------------Tabla----------------------------------------
	private ObjectListDataProvider ldpPersonaFisica = new ObjectListDataProvider();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();

	public ObjectListDataProvider getLdpPersonaFisica() {
		return ldpPersonaFisica;
	}

	public void setLdpPersonaFisica(ObjectListDataProvider ldpPersonaFisica) {
		this.ldpPersonaFisica = ldpPersonaFisica;
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

	// -------------------------------------------------------------------------
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
		FiltroPersonaFisica locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PersonaFisica.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroPersonaFisica locFiltro = getFiltro();

		locFiltro.setCuil(this.getTextFieldValue(getTfCuil()));
		locFiltro.setTipoDocumento(this.getDDEnumValue(getDdTipoDocumento(), TipoDocumento.class));
		locFiltro.setNumeroDocumento(this.getTextFieldValue(getTfNroDocumento()));
		this.getSessionBean1().setNroDocumento(locFiltro.getNumeroDocumento());
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), PersonaFisica.Estado.class));
		locFiltro.setApellido(this.getTextFieldValue(getTfApellido()));
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));

		if (locFiltro.getListaAtributoDinamico() != null) {
			locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroPersonaFisica locFiltro = getFiltro();

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{framework$ABMPersonaFisica$AdminPersonaFisica}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());

		// no requerido en este caso.
		// persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(0,
		// PersonaFisica.class);

		this.getTfCuil().setText(locFiltro.getCuil());
		ddTipoDocumento.setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoDocumento())));
		ddTipoDocumentoDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoDocumento())));

		this.getTfNroDocumento().setText(this.getSessionBean1().getNroDocumento());
		this.getTfApellido().setText(locFiltro.getApellido());
		this.getTfNombre().setText(locFiltro.getNombre());

		if (locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
			this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
		// this.setListaDelCommunicationAtributosDinamicos((ArrayList)
		// locFiltro.getListaAtributoDinamico());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemPersonaFisica().findPersonaFisica((FiltroPersonaFisica) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroPersonaFisica locFiltro = getFiltro();
		locFiltro.setCuil(null);
		locFiltro.setTipoDocumento(null);
		locFiltro.setNumeroDocumento(null);
		this.getSessionBean1().setNroDocumento(null);
		locFiltro.setEstado(null);
		locFiltro.setApellido(null);
		locFiltro.setNombre(null);

		panelAtributoDinamico.limpiarCampos();

		this.getTfCuil().setText(null);
		ddTipoDocumento.setSelected(null);
		ddTipoDocumentoDefaultOptions.setSelectedValue(null);
		this.getTfNroDocumento().setText(null);
		this.getTfNombre().setText(null);
		this.getTfApellido().setText(null);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(PersonaFisica.Estado.ACTIVO)));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(PersonaFisica.Estado.ACTIVO)));

	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpPersonaFisica();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaPersonasFisicas();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaPersonasFisicas((ArrayList) lista);
	}

	private List getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationBean().getListaAtributosDinamicosPersonasFisicas();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationBean().setListaAtributosDinamicosPersonasFisicas(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new PersonaFisicaModel().new AgregarPersonaFisicaController());
	}

	public String btnModificar_action() {
		return toAbm(new PersonaFisicaModel().new ModificarPersonaFisicaController());
	}

	public String btnEliminar_action() {
		PersonaFisica persona = (PersonaFisica) getObjetoSeleccionado();
		if (persona != null && persona.getEstado().equals(PersonaFisica.Estado.ELIMINADO)) {
			warn("La persona seleccionada ya se encuentra eliminada.");
			return null;
		}
		return toAbm(new PersonaFisicaModel().new EliminarPersonaFisicaController());
	}

	public String btnConsultar_action() {
		return toAbm(new PersonaFisicaModel().new ConsultarPersonaFisicaController());
	}

	public String btnActivar_action() {
		PersonaFisica persona = (PersonaFisica) getObjetoSeleccionado();
		if (persona != null && persona.getEstado().equals(PersonaFisica.Estado.ACTIVO)) {
			warn("La persona seleccionada ya se encuentra activa.");
			return null;
		}
		return toAbm(new PersonaFisicaModel().new ActivarPersonaFisicaController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		PersonaFisica locPersonaF = (PersonaFisica) pObject;
		getComunicationBean().getRemoteSystemPersonaFisica().setLlave(getSessionBean1().getLlave());
		locPersonaF = getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(locPersonaF.getIdPersona());
		return locPersonaF;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Personas Fisicas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminPersonaFisica";
	}

	private String nombrePersona;

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	private Option[] listaPersonas;

	public Option[] getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(Option[] listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public void eventoBuscarPersona(ActionEvent evento) {
		System.out.println("Nombre persona: " + nombrePersona);
		if (nombrePersona.length() > 3) {
			FiltroPersonaFisica locFiltro = new FiltroPersonaFisica();
			locFiltro.setApellido(nombrePersona);
			try {
				this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
				locFiltro = this.getComunicationBean().getRemoteSystemPersonaFisica().findPersonaFisica(locFiltro);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			this.listaPersonas = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(locFiltro.getListaResultados().toArray(), "may");
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return PersonaFisica.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPersonaFisica$AdminPersonaFisica}";
	}
}