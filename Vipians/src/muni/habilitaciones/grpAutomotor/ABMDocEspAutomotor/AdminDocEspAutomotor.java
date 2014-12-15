package muni.habilitaciones.grpAutomotor.ABMDocEspAutomotor;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

public class AdminDocEspAutomotor extends AdminPageBean {

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		Set<String> locListaPlantillasObligaciones = getApplicationBean1().getMapaPlantillaObligacionAutomotor().keySet();

		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size() + 1];
		int i = 0;
		opPlantillasObligaciones[i++] = new Option("", "");
		for (String cadaPlantilla : locListaPlantillasObligaciones) {
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);
	}

	private Label lblPatente = new Label();
	private Label lblEstado = new Label();

	private Label lblVehiculo = new Label();
	private TextField tfVehiculo = new TextField();
	private Button btnSeleccionarVehiculo = new Button();
	
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private TextField tfPatente = new TextField();
	private ObjectListDataProvider ldpDocEspAutomotor = new ObjectListDataProvider();
	private PanelGroup gpSeleccion = new PanelGroup();
	private Label label3 = new Label();
	private StaticText staticText5 = new StaticText();
	private DropDown ddPlantillaObligacion = new DropDown();
	private SingleSelectOptionsList ddPlantillaObligacionOptions = new SingleSelectOptionsList();
	protected PanelAtributoDinamico panelAtributoDinamico;
	private Label lblNroCuenta = new Label();
	private TextField tfNumeroCuenta = new TextField();

	private HtmlAjaxCommandButton btnLimpiarVehiculo = new HtmlAjaxCommandButton();
	
	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public Label getLblNroCuenta() {
		return lblNroCuenta;
	}

	public void setLblNroCuenta(Label lblNroCuenta) {
		this.lblNroCuenta = lblNroCuenta;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public PanelGroup getGpSeleccion() {
		return gpSeleccion;
	}

	public void setGpSeleccion(PanelGroup gpSeleccion) {
		this.gpSeleccion = gpSeleccion;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public DropDown getDdPlantillaObligacion() {
		return ddPlantillaObligacion;
	}

	public void setDdPlantillaObligacion(DropDown ddPlantillaObligacion) {
		this.ddPlantillaObligacion = ddPlantillaObligacion;
	}

	public SingleSelectOptionsList getDdPlantillaObligacionOptions() {
		return ddPlantillaObligacionOptions;
	}

	public void setDdPlantillaObligacionOptions(SingleSelectOptionsList ddPlantillaObligacionOptions) {
		this.ddPlantillaObligacionOptions = ddPlantillaObligacionOptions;
	}

	public Label getLblPatente() {
		return lblPatente;
	}

	public void setLblPatente(Label lblPatente) {
		this.lblPatente = lblPatente;
	}

	public TextField getTfPatente() {
		return tfPatente;
	}

	public void setTfPatente(TextField tfPatente) {
		this.tfPatente = tfPatente;
	}

	public ObjectListDataProvider getLdpDocEspAutomotor() {
		return ldpDocEspAutomotor;
	}

	public void setLdpDocEspAutomotor(ObjectListDataProvider ldpDocEspAutomotor) {
		this.ldpDocEspAutomotor = ldpDocEspAutomotor;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDocEspAutomotor();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaDocEspAutomotor();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaDocEspAutomotor(lista);
	}

	public Label getLblVehiculo() {
		return lblVehiculo;
	}

	public void setLblVehiculo(Label lblVehiculo) {
		this.lblVehiculo = lblVehiculo;
	}

	public TextField getTfVehiculo() {
		return tfVehiculo;
	}

	public void setTfVehiculo(TextField tfVehiculo) {
		this.tfVehiculo = tfVehiculo;
	}

	public Button getBtnSeleccionarVehiculo() {
		return btnSeleccionarVehiculo;
	}

	public void setBtnSeleccionarVehiculo(Button btnSeleccionarVehiculo) {
		this.btnSeleccionarVehiculo = btnSeleccionarVehiculo;
	}

	public HtmlAjaxCommandButton getBtnLimpiarVehiculo() {
		return btnLimpiarVehiculo;
	}

	public void setBtnLimpiarVehiculo(HtmlAjaxCommandButton btnLimpiarVehiculo) {
		this.btnLimpiarVehiculo = btnLimpiarVehiculo;
	}

	@Override
	protected void limpiarObjetosUsados() {

		this.getPanelAtributoDinamico().limpiarCampos();

		FiltroObligacionAutomotor locFiltro = this.getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setListaAtributosDinamicos(this.getPanelAtributoDinamico().getListaAtributosDinamicos());
		locFiltro.setPatente("");
		locFiltro.setPersona(null);
		locFiltro.setNumeroCuenta(null);
		locFiltro.setVehiculo(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();
		
		this.getTfPersonaSeleccionada().setText(null);
		this.getTfPatente().setText(null);
		this.getTfNumeroCuenta().setText(null);
		this.ddPlantillaObligacion.setSelected(null);
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Obligacion.Estado.CREADO)));
		this.tfVehiculo.setText(null);
		this.stCantidadRegistros.setText("");
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroObligacionAutomotor locFiltroObligacionAutomotor = (FiltroObligacionAutomotor) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesAutomotor(locFiltroObligacionAutomotor);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroObligacionAutomotor locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());

		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		locFiltro.setNumeroCuenta(getTextFieldValueInteger(this.getTfNumeroCuenta()));
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);

		// Obligacion.Estado estado = null;
		// Object estadoSelected = this.getDdEstado().getSelected();
		// Object patente = this.getTfPatente().getText();

		locFiltro.setPatente(getTextFieldValue(this.getTfPatente()));

		if (this.ddPlantillaObligacion.getSelected() != null && this.ddPlantillaObligacion.getSelected() != "") {
			plantillaObligacion = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		} else {
			plantillaObligacion = null;
		}

		if (persona != null && persona.getIdPersona() != -1) {
			locFiltro.setPersona(persona);
		} else {
			locFiltro.setPersona(null);
		}

		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Obligacion.Estado.class));

		if (locFiltro.getListaAtributosDinamicos() != null) {
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}

		this.getElementoPila().getObjetos().set(0, plantillaObligacion);
		// this.getElementoPila().getObjetos().set(1, obligacion);
		// this.getElementoPila().getObjetos().set(3, atributosDinamicos);
	}

	private PlantillaObligacion getPlantillaPorNombre(String pPlantilla) {
		return getApplicationBean1().getMapaPlantillaObligacionAutomotor().get(pPlantilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = null;
		FiltroObligacionAutomotor locFiltro = this.getFiltro();

		persona = this.getSessionBean1().getPersonaSeleccionada();

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());

		if (persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}
		
		Vehiculo vehiculo = locFiltro.getVehiculo();
		if(vehiculo != null)
			this.getTfVehiculo().setText(vehiculo);
		
		if (locFiltro.getPatente() != null) {
			this.getTfPatente().setText(locFiltro.getPatente());
		}

		if (locFiltro.getNumeroCuenta() != null) {
			this.getTfNumeroCuenta().setText(locFiltro.getNumeroCuenta());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Obligacion.Estado.CREADO : locFiltro.getEstado())));
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Obligaciones: Automotor";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspAutomotor";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaObligacion());

		FiltroObligacionAutomotor locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoAutomotor.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);

		return ep;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaDocEspAutomotor();
	}

	public String btnAgregar_action() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = this.getDdPlantillaObligacion();
		nomNoVacios[pos++] = "Plantilla de Obligaci\363n del Tipo Automotor";
		v.noSonVacios(noVacios, nomNoVacios);
		if (v.getErrores().size() > 0) {
			for (int i = 0; i < v.getErrores().size(); i++) {
				warn(v.getErrores().toArray()[i].toString());
			}
			return null;
		}

		PlantillaObligacion locPlantilla = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		try {
			System.out.println("locPlantilla: " + locPlantilla);
			locPlantilla = this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().getPlantillaObligacion(locPlantilla.getIdPlantillaObligacion());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.getRequestBean1().setObjetoABM(locPlantilla);
		return toAbm(new DocEspAutomotorModel().new AgregarDocEspAutomotorController());
	}

	public String btnModificar_action() {
		return toAbm(new DocEspAutomotorModel().new ModificarDocEspAutomotorController());
	}

	public String btnConsultar_action() {
		return toAbm(new DocEspAutomotorModel().new ConsultarDocEspAutomotorController());
	}

	public String btnEliminar_action() {
		return toAbm(new DocEspAutomotorModel().new EliminarDocEspAutomotorController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Persona persona = null;

		if (pObject instanceof Persona) {
			persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			this.limpiarTabla();
		}
		
		Vehiculo vehiculo = null;
		if(pObject instanceof Vehiculo){
			vehiculo = (Vehiculo) pObject;
			FiltroObligacionAutomotor locFiltro = this.getFiltro();
			locFiltro.setVehiculo(vehiculo);
		}

	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoAutomotor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor}";
	}
	
	//Sumar Vehiculo al filtro de busqueda
	public String btnSeleccionarVehiculo_action() {
		return navegarParaSeleccionar("AdminVehiculo");
	}
	
	public void btnLimpiarVehiculo_action(){
		this.tfVehiculo.setText(null);
		FiltroObligacionAutomotor locFiltro = this.getFiltro();
		locFiltro.setVehiculo(null);
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionAutomotor locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;
		
		try {
			propietario = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setPersona(propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}
}
