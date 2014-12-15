/*
 * AdminPersonaJuridica.java
 *
 * Created on 13 de octubre de 2006, 11:14
 * Copyright Trascender SRL
 */
package muni.framework.ABMPersonaJuridica;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroPersonaJuridica;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
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
public class AdminPersonaJuridica extends AdminPageBean {

	private Label lblNombreFantasia = new Label();
	private TextField tfNombreFantasia = new TextField();

	@Override
	protected void _init() throws Exception {
		this.setListaDelCommunicationAtributosDinamicos(null);
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PersonaJuridica.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(PersonaJuridica.Estado.ACTIVO)));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(PersonaJuridica.Estado.ACTIVO)));
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	private DropDown ddEstado = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	private Label lblEstado = new Label();
	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Button btnActivarPersona = new Button();
	private HtmlAjaxCommandButton btnLimpiarTitular = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarTitular() {
		return btnLimpiarTitular;
	}

	public void setBtnLimpiarTitular(HtmlAjaxCommandButton btnLimpiarTitular) {
		this.btnLimpiarTitular = btnLimpiarTitular;
	}

	public Button getBtnActivarPersona() {
		return btnActivarPersona;
	}

	public void setBtnActivarPersona(Button btnActivarPersona) {
		this.btnActivarPersona = btnActivarPersona;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfTitular = new TextField();

	public TextField getTfTitular() {
		return tfTitular;
	}

	public void setTfTitular(TextField tf) {
		this.tfTitular = tf;
	}

	private ObjectListDataProvider ldpPersonaJuridica = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPersonaJuridica() {
		return ldpPersonaJuridica;
	}

	public void setLdpPersonaJuridica(ObjectListDataProvider oldp) {
		this.ldpPersonaJuridica = oldp;
	}

	private Button btnSeleccionarTitular = new Button();

	public Button getBtnSeleccionarTitular() {
		return btnSeleccionarTitular;
	}

	public void setBtnSeleccionarTitular(Button b) {
		this.btnSeleccionarTitular = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfRazonSocial = new TextField();

	public TextField getTfRazonSocial() {
		return tfRazonSocial;
	}

	public void setTfRazonSocial(TextField tf) {
		this.tfRazonSocial = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfCuit = new TextField();

	public TextField getTfCuit() {
		return tfCuit;
	}

	public void setTfCuit(TextField tf) {
		this.tfCuit = tf;
	}

	public Label getLblNombreFantasia() {
		return lblNombreFantasia;
	}

	public void setLblNombreFantasia(Label lblNombreFantasia) {
		this.lblNombreFantasia = lblNombreFantasia;
	}

	public TextField getTfNombreFantasia() {
		return tfNombreFantasia;
	}

	public void setTfNombreFantasia(TextField tfNombreFantasia) {
		this.tfNombreFantasia = tfNombreFantasia;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminPersonaJuridica() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroPersonaJuridica locFiltro = this.getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			if (this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PersonaJuridica.serialVersionUID, null, null) != null) {
				atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PersonaJuridica.serialVersionUID, null, null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroPersonaJuridica locFiltro = this.getFiltro();

		locFiltro.setCuit(this.getTextFieldValue(getTfCuit()));
		locFiltro.setRazonSocial(this.getTextFieldValue(getTfRazonSocial()));
		locFiltro.setNombreFantasia(this.getTextFieldValue(getTfNombreFantasia()));

		Object estado = this.getDdEstado().getSelected();
		if ((estado != null) && (estado.toString().length() > 0)) {
			if (estado.equals("ACTIVO")) {
				locFiltro.setEstado(PersonaJuridica.Estado.ACTIVO);
			}
			if (estado.equals("ELIMINADO")) {
				locFiltro.setEstado(PersonaJuridica.Estado.ELIMINADO);
			}
		} else {
			locFiltro.setEstado(null);
		}

		if (locFiltro.getListaAtributoDinamico() != null) {
			locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroPersonaJuridica locFiltro = this.getFiltro();
		// String tipoSeleccion = null;
		// if (this.getRequestBean1().getTipoSeleccion() != null) {
		// tipoSeleccion = this.getRequestBean1().getTipoSeleccion();
		// this.getElementoPila().getObjetos().set(2, tipoSeleccion);
		// }

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{framework$ABMPersonaFisica$ABMPersonaJuridica}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());

		// atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(3,
		// ArrayList.class);

		this.getTfCuit().setText(locFiltro.getCuit());
		this.getTfRazonSocial().setText(locFiltro.getRazonSocial());
		this.getTfTitular().setText(locFiltro.getTitular());
		this.getTfNombreFantasia().setText(locFiltro.getNombreFantasia());

		if (locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
			this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}

		this.setListaDelCommunicationAtributosDinamicos((ArrayList) locFiltro.getListaAtributoDinamico());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemPersonaJuridica().findPersonaJuridica((FiltroPersonaJuridica) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroPersonaJuridica locFiltro = this.getFiltro();
		locFiltro.setCuit(null);
		locFiltro.setRazonSocial(null);
		locFiltro.setNombreFantasia(null);
		locFiltro.setEstado(null);
		locFiltro.setTitular(null);

		panelAtributoDinamico.limpiarCampos();

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfCuit().setText("");
		this.getTfRazonSocial().setText("");
		this.getTfNombreFantasia().setText("");
		this.getTfTitular().setText("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(PersonaJuridica.Estado.ACTIVO)));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(PersonaJuridica.Estado.ACTIVO)));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpPersonaJuridica();
	}

	@Override
	protected List getListaDelCommunication() {
		return (ArrayList) this.getComunicationBean().getListaPersonasJuridicas();
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationBean().getListaAtributosDinamicosPersonasJuridicas();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationBean().setListaAtributosDinamicosPersonasJuridicas(lista);
	}

	public String btnSeleccionarTitular_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnAgregar_action() {
		return toAbm(new PersonaJuridicaModel().new AgregarController());
	}

	public String btnModificar_action() {
		PersonaJuridica persona = (PersonaJuridica) getObjetoSeleccionado();
		if (persona.getEstado().equals(PersonaJuridica.Estado.ELIMINADO)) {
			warn("No se puede modificar una Persona Jurídica en estado ELIMINADO.");
			return null;
		}
		return toAbm(new PersonaJuridicaModel().new ModificarController());
	}

	public String btnEliminar_action() {
		PersonaJuridica persona = (PersonaJuridica) getObjetoSeleccionado();
		if (persona.getEstado().equals(PersonaJuridica.Estado.ELIMINADO)) {
			warn("La Persona Jurídica ya se encuentra eliminada.");
			return null;
		}
		return toAbm(new PersonaJuridicaModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new PersonaJuridicaModel().new ConsultarController());
	}

	public String btnActivarPersona_action() {
		PersonaJuridica persona = (PersonaJuridica) getObjetoSeleccionado();
		if (persona.getEstado().equals(PersonaJuridica.Estado.ACTIVO)) {
			warn("La persona seleccionada ya se encuentra activa.");
			return null;
		}
		return toAbm(new PersonaJuridicaModel().new ActivarController());
	}

	public String btnLimpiarTitular_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(getTfTitular());
			FiltroPersonaJuridica locFiltro = getFiltro();
			locFiltro.setTitular(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaPersonasJuridicas((ArrayList) lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return this.getComunicationBean().getTspPersonasJuridicas();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		PersonaJuridica pPersona = (PersonaJuridica) pObject;
		this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
		pPersona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(pPersona.getIdPersona());
		return pPersona;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Personas Jur\355dicas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminPersonaJuridica";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaPersonaJuridica();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		PersonaFisica titular = (PersonaFisica) pObject;
		FiltroPersonaJuridica locFiltro = getFiltro();
		locFiltro.setTitular(titular);
	}
	
	@Override
	public long getSerialVersionUID() {
		return PersonaJuridica.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPersonaJuridica$AdminPersonaJuridica}";
	}
}