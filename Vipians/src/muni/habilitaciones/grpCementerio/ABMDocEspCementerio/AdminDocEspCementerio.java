package muni.habilitaciones.grpCementerio.ABMDocEspCementerio;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;

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
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

public class AdminDocEspCementerio extends AdminPageBean{

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		Set<String> locListaPlantillasObligaciones = getApplicationBean1().getMapaPlantillaObligacionCementerio().keySet();

		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size() + 1];
		int i = 0;
		opPlantillasObligaciones[i++] = new Option("", "");
		for (String cadaPlantilla : locListaPlantillasObligaciones){
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);
	}

	private Label lblParcelaCementerio = new Label();
	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private TextField tfParcelaCementerio = new TextField();
	private ObjectListDataProvider ldpDocEspCementerio = new ObjectListDataProvider();
	private PanelGroup gpSeleccion = new PanelGroup();
	private Label label3 = new Label();
	private StaticText staticText5 = new StaticText();
	private DropDown ddPlantillaObligacion = new DropDown();
	private SingleSelectOptionsList ddPlantillaObligacionOptions = new SingleSelectOptionsList();
	protected PanelAtributoDinamico panelAtributoDinamico;
	private Button btnSeleccionarParcelaCementerio = new Button();
	private Button btnLimpiarParcelaCementerio = new Button();
	private TextField tfNumeroCuenta = new TextField();
	
	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public Button getBtnSeleccionarParcelaCementerio() {
		return btnSeleccionarParcelaCementerio;
	}

	public void setBtnSeleccionarParcelaCementerio(
			Button btnSeleccionarParcelaCementerio) {
		this.btnSeleccionarParcelaCementerio = btnSeleccionarParcelaCementerio;
	}

	public Button getBtnLimpiarParcelaCementerio() {
		return btnLimpiarParcelaCementerio;
	}

	public void setBtnLimpiarParcelaCementerio(Button btnLimpiarParcelaCementerio) {
		this.btnLimpiarParcelaCementerio = btnLimpiarParcelaCementerio;
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

	public Label getLblParcelaCementerio() {
		return lblParcelaCementerio;
	}

	public void setLblParcelaCementerio(Label lblParcelaCementerio) {
		this.lblParcelaCementerio = lblParcelaCementerio;
	}

	public TextField getTfParcelaCementerio() {
		return tfParcelaCementerio;
	}

	public void setTfParcelaCementerio(TextField tfParcelaCementerio) {
		this.tfParcelaCementerio = tfParcelaCementerio;
	}

	public ObjectListDataProvider getLdpDocEspCementerio() {
		return ldpDocEspCementerio;
	}

	public void setLdpDocEspCementerio(ObjectListDataProvider ldpDocEspCementerio) {
		this.ldpDocEspCementerio = ldpDocEspCementerio;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDocEspCementerio();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaDocEspCementerio();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaDocEspCementerio(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {

		this.getPanelAtributoDinamico().limpiarCampos();

		FiltroObligacionCementerio locFiltro = this.getFiltro();
		locFiltro.setEstado(Obligacion.Estado.CREADO);
		locFiltro.setParcelaCementerio(null);
		locFiltro.setPersona(null);
		locFiltro.setNumeroCuenta(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();
		this.getTfPersonaSeleccionada().setText(null);
		this.getTfParcelaCementerio().setText(null);
		this.ddPlantillaObligacion.setSelected(null);
		this.getTfNumeroCuenta().setText(null);
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Obligacion.Estado.CREADO)));
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroObligacionCementerio locFiltroObligacionCementerio = (FiltroObligacionCementerio) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesCementerio(locFiltroObligacionCementerio);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
	
		FiltroObligacionCementerio locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());
	
		if (this.ddPlantillaObligacion.getSelected() != null && this.ddPlantillaObligacion.getSelected() != "") {
			plantillaObligacion = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		} else {
			plantillaObligacion = null;
		}
		
		locFiltro.setNumeroCuenta(this.getTextFieldValueInteger(tfNumeroCuenta));
	
		if(persona != null && persona.getIdPersona() != -1){
			locFiltro.setPersona(persona);
		}else{
			locFiltro.setPersona(null);
		}
	
		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Obligacion.Estado.class));
	
		if(locFiltro.getListaAtributosDinamicos() != null){
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}
	
		this.getElementoPila().getObjetos().set(0, plantillaObligacion);
		//        this.getElementoPila().getObjetos().set(1, obligacion);
		//        this.getElementoPila().getObjetos().set(3, atributosDinamicos);
	}

	private PlantillaObligacion getPlantillaPorNombre(String pPlantilla){
		return getApplicationBean1().getMapaPlantillaObligacionCementerio().get(pPlantilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = null;
		PlantillaObligacion plantillaObligacion = null;

		FiltroObligacionCementerio locFiltro = this.getFiltro();

		persona = this.getSessionBean1().getPersonaSeleccionada();
		plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
		//numeroRegistroEP = (Integer) this.obtenerObjetoDelElementoPila(2, Integer.class);

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());

		if (persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Obligacion.Estado.CREADO : locFiltro.getEstado())));

		if(plantillaObligacion!=null && plantillaObligacion.getIdPlantillaObligacion()!=-1){
			this.getDdPlantillaObligacion().setSelected(plantillaObligacion.toString());
		}

		if(locFiltro.getParcelaCementerio() != null){
			this.getTfParcelaCementerio().setText(locFiltro.getParcelaCementerio());
		}
		
		this.setTextFieldValueInteger(tfNumeroCuenta, locFiltro.getNumeroCuenta());
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Obligaciones: Cementerio";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspCementerio";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaObligacion());

		FiltroObligacionCementerio locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoCementerio.serialVersionUID, null, true); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);
		return ep;
	}

	@Override
	public PaginatedTable getPaginatedTable(){
		return this.getCommunicationHabilitacionesBean().getTablaDocEspCementerio();
	}

	public String btnSeleccionarParcelaCementerio_action() {
		return navegarParaSeleccionar("AdminParcelaCementerio");
	}

	public String btnLimpiarParcelaCementerio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.getTfParcelaCementerio().setText("");
			((FiltroObligacionCementerio)this.getFiltro()).setParcelaCementerio(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = this.getDdPlantillaObligacion();
		nomNoVacios[pos++] = "Plantilla de Obligaci\363n del Tipo Cementerio";
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
		return toAbm(new DocEspCementerioModel().new AgregarDocEspCementerioController());
	}

	public String btnModificar_action(){
		return toAbm(new DocEspCementerioModel().new ModificarDocEspCementerioController());
	}

	public String btnConsultar_action(){
		return toAbm(new DocEspCementerioModel().new ConsultarDocEspCementerioController());
	}

	public String btnEliminar_action(){
		return toAbm(new DocEspCementerioModel().new EliminarDocEspCementerioController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroObligacionCementerio locFiltro = this.getFiltro();

		if (pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			this.limpiarTabla();
		}

		if(pObject instanceof ParcelaCementerio){
			ParcelaCementerio locParcelaCem = (ParcelaCementerio) pObject;
			locFiltro.setParcelaCementerio(locParcelaCem);
		}	
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoCementerio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionCementerio locFiltro = this.getFiltro();
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
