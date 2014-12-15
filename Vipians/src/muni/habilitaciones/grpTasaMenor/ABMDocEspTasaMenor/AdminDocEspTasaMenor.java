/*
 * AdminDocEspTasaMenor.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpTasaMenor.ABMDocEspTasaMenor;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminDocEspTasaMenor extends AdminPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Administraci\363n de Obligaciones Tasas Menores";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminDocEspTasaMenor";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = true;
	// CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
	private final String lnkAgregar = "AgregarDocEspTasaMenor";
	private final String lnkModificar = "ModificarDocEspTasaMenor";
	private final String lnkEliminar = "EliminarDocEspTasaMenor";

	private Label lblTipoObligacion = new Label();
	private DropDown ddTipoObligacion = new DropDown();
	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

	public Label getLblTipoObligacion() {
		return lblTipoObligacion;
	}

	public void setLblTipoObligacion(Label lblTipoObligacion) {
		this.lblTipoObligacion = lblTipoObligacion;
	}

	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown ddTipoObligacion) {
		this.ddTipoObligacion = ddTipoObligacion;
	}

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ddTipoObligacionDefaultOptions) {
		this.ddTipoObligacionDefaultOptions = ddTipoObligacionDefaultOptions;
	}

	private ObjectListDataProvider ldpDocEspTasaMenor = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspTasaMenor() {
		return ldpDocEspTasaMenor;
	}

	public void setLdpDocEspTasaMenor(ObjectListDataProvider ldpDocEspTasaMenor) {
		this.ldpDocEspTasaMenor = ldpDocEspTasaMenor;
	}

	private TextField tfPlantillaObligacion = new TextField();

	public TextField getTfPlantillaObligacion() {
		return tfPlantillaObligacion;
	}

	public void setTfPlantillaObligacion(TextField tf) {
		this.tfPlantillaObligacion = tf;
	}

	private Button idImprimirReporte = new Button();

	public Button getIdImprimirReporte() {
		return idImprimirReporte;
	}

	public void setIdImprimirReporte(Button b) {
		this.idImprimirReporte = b;
	}

	private DropDown ddEstado = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	private DropDown ddPlantillaObligacion = new DropDown();
	private SingleSelectOptionsList ddPlantillaObligacionOptions = new SingleSelectOptionsList();

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

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private PanelGroup gpSeleccion = new PanelGroup();

	public PanelGroup getGpSeleccion() {
		return gpSeleccion;
	}

	public void setGpSeleccion(PanelGroup pg) {
		this.gpSeleccion = pg;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
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

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	public AdminDocEspTasaMenor() {
	}

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		Set<String> locListaPlantillasObligaciones = getApplicationBean1().getMapaPlantillaObligacionTasaMenor().keySet();

		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size() + 1];
		int i = 0;
		opPlantillasObligaciones[i++] = new Option("", "");
		for(String cadaPlantilla : locListaPlantillasObligaciones) {
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacionTasaMenor().keySet().toArray(), "cap");
		ddTipoObligacionDefaultOptions.setOptions(op);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaObligacion());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroObligacionTasaMenor locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());
		borrarListIdAuxParcelas(this.getTfParcelaSeleccionada(), locFiltro.getParcela());
		locFiltro.setListaIdParcelas(this.getSessionBean1().getListaIdParcelas());
		
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();
		PlantillaObligacion plantillaObligacion = (PlantillaObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
		String numeroRegistroEP;

		locFiltro.setPlantillaDocumentoTasaMenor(this.getDDObjectValue(getDdTipoObligacion(), getCommunicationHabilitacionesBean().getMapaTipoObligacionTasaMenor()));

		Object numeroRegistro = this.getTfNroParcela().getText();

		if(numeroRegistro != null && numeroRegistro != "") {
			numeroRegistroEP = /* Conversor.getIntegerDeString( */numeroRegistro.toString()/* ) */;
		} else {
			numeroRegistroEP = null;
		}
		this.getSessionBean1().setNroParcela(numeroRegistroEP);
		this.getSessionBean1().setPersonaSeleccionada(persona);
		this.getSessionBean1().setParcelaSeleccionada(parcela);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, plantillaObligacion);
		locFiltro.setParcela(parcela);
		locFiltro.setPersona(persona);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		FiltroObligacionTasaMenor locFiltro = getFiltro();
		Persona persona = null;
		Parcela parcela = null;
		PlantillaObligacion plantillaObligacion = null;
		String numeroRegistroEP = null;

		int ind = 0;
		persona = this.getSessionBean1().getPersonaSeleccionada();
		parcela = this.getSessionBean1().getParcelaSeleccionada();
		plantillaObligacion = (PlantillaObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
		numeroRegistroEP = this.getSessionBean1().getNroParcela();

		if(locFiltro.getPlantillaDocumentoTasaMenor() != null) {
			this.getDdTipoObligacion().setSelected(locFiltro.getPlantillaDocumentoTasaMenor().toString());
		}
		if(persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}
		if(parcela != null) {
			this.getTfParcelaSeleccionada().setText(parcela.toString());
		}
		this.getTfPlantillaObligacion().setText(plantillaObligacion.toString());
		if(numeroRegistroEP != null) {
			this.getTfNroParcela().setText(numeroRegistroEP.toString());
		}
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setNroParcela(null);
		// CAMBIAR: Limpiar los textField y los dropDown

		FiltroObligacionTasaMenor locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setNroParcela(null);
		locFiltro.setParcela(null);

		this.getTfPersonaSeleccionada().setText(null);
		this.getTfPlantillaObligacion().setText(null);
		this.getTfNroParcela().setText(null);
		this.getTfParcelaSeleccionada().setText(null);
		this.getDdTipoObligacion().setSelected("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpDocEspTasaMenor();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaDocEspTasaMenor();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaDocEspTasaMenor(lista);
	}

	public String btnAgregar_action() {
		PlantillaObligacion plantilla = (PlantillaObligacion) this.getDDObjectValue(this.getDdPlantillaObligacion(), getApplicationBean1().getMapaPlantillaObligacionTasaMenor());

		if(plantilla == null || plantilla.getIdPlantillaObligacion() == -1) {
			warn("Debe seleccionar una Plantilla de Obligaci\363n para poder agregar una nueva Obligaci\363n.");
			return null;
		}

		// determina si la plantilla de obligacion corresponde a una Tasa Menor
		try {
			if(!this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().esTipoObligacionTasaMenor(plantilla.getTipoObligacion())) {
				warn("Debe seleccionar una Plantilla de Obligaci\363n correspondiente a una Tasa Menor para poder agregar una nueva Obligaci\363n.");
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.getRequestBean1().setObjetoABM(plantilla);
		return toAbm(new DocEspTasaMenorModel().new AgregarDocEspTasaMenorController());
	}

	public String btnModificar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion obligacion = (Obligacion) obj;
					if(obligacion.getEstado().equals(Estado.ANULADO)) {
						warn("Las obligaciones ANULADAS no pueden modificarse");
						return null;
					}
					// paso la obligacion
					this.getRequestBean1().setObjetoABM(obj);
					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_ModificarError:", ex);
				error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = toAbm(new DocEspTasaMenorModel().new ModificarDocEspTasaMenorController());
				;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnEliminar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion obligacion = (Obligacion) obj;
					if(obligacion.getEstado().equals(Estado.ANULADO)) {
						warn("Las obligaciones ANULADAS no pueden volver a anularse");
						return null;

					}
					// Caso especial de las obligaciones.
					Object objetoABM = obligacion.getDocumentoEspecializado();

//					getRequestBean1().setObjetoABM(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_EliminarError:", ex);
				error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = toAbm(new DocEspTasaMenorModel().new EliminarDocEspTasaMenorController());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnImprimirReporte_action() {
		return null;
	}

	public String btnConsultar_action() {
		return toAbm(new DocEspTasaMenorModel().new ConsultarDocEspTasaMenorController());
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Obligacion locObligacion = (Obligacion) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
		locObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(locObligacion.getIdObligacion());
		return locObligacion;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesTasaMenor((FiltroObligacionTasaMenor) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaDocEspTasaMenor();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
		}
		if(pObject instanceof Parcela) {
			Parcela parcela = (Parcela) pObject;
			this.getSessionBean1().setParcelaSeleccionada(parcela);
		}

		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;
			this.getElementoPila().getObjetos().set(0, plantillaObligacion);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Obligaciones Tasas Menores";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspTasaMenor";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoTasaMenor.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor}";
	}

	public void setParcelaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionTasaMenor locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Parcela parcela = null;

		try {
			parcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(TrascenderException e) {
			e.printStackTrace();
		}

		locFiltro.setParcela(parcela);
		this.getSessionBean1().setParcelaSeleccionada(parcela);
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionTasaMenor locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(TrascenderException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(locPersona);
		this.getSessionBean1().setPersonaSeleccionada(locPersona);
	}
}