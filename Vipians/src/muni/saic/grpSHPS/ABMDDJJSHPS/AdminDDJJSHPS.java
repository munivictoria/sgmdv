/*
 * AdminDDJJSHPS.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.saic.grpSHPS.ABMDDJJSHPS;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;

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
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.filtros.FiltroDDJJSHPS;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminDDJJSHPS extends AdminPageBean {

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Administraci\363n de Declaraciones Juradas de SHPS";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminDDJJSHPS";

	protected void _init() throws Exception {
		FiltroDDJJSHPS locFiltro = this.getFiltro();
		locFiltro.setAnio(Integer.valueOf(getAnioCorriente()));

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());

		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter1.setTimeStyle("short");

		btnImprimirReporte.setDisabled(true);
	}

	public void eventoSeleccionAnio(ValueChangeEvent event) {
		String opcion = this.getDdAnios().getSelected().toString();
		seleccionarAnio(opcion, true);
	}

	public void eventoSeleccionCalendario(ValueChangeEvent event) {
		String opcion = this.getDdCalendarios().getSelected().toString();
		seleccionarCalendario(opcion, true);
	}

	public void eventoSeleccionPeriodo(ValueChangeEvent event) {
		String opcion = this.getDdPeriodos().getSelected().toString();
		seleccionarPeriodo(opcion, true);
	}

	private void seleccionarAnio(String pAnio, boolean actualizar) {
		this.ddAnios.setSelected(pAnio);
		if(actualizar) {
			actualizarOpcionesDDCalendario(pAnio);
		}
	}

	private void seleccionarCalendario(String pCalendario, boolean actualizar) {
		this.ddCalendarios.setSelected(pCalendario);
		if(actualizar) {
			this.actualizarOpcionesDDPeriodo(pCalendario);
		}
	}

	private void seleccionarPeriodo(String pPeriodo, boolean actualizar) {
		this.ddPeriodos.setSelected(pPeriodo);
		if(actualizar) {
			this.actualizarOpcionesDDCuotas(pPeriodo);
		}
	}

	private void seleccionarCuota(String pCuota) {
		this.ddCuotas.setSelected(pCuota);
	}

	private void actualizarOpcionesDDCalendario(String pAnio) {
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosSHPS(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet().iterator().next();
		}
	}

	private Label lblPeriodo = new Label();
	private Label lblAnio = new Label();
	private DropDown ddAnios = new DropDown();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private Label lblCalendarios = new Label();
	private DropDown ddCalendarios = new DropDown();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private Label lblPeriodos = new Label();
	private DropDown ddPeriodos = new DropDown();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private Label lblCuotas = new Label();
	private DropDown ddCuotas = new DropDown();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();

	public Label getLblCuotas() {
		return lblCuotas;
	}

	public void setLblCuotas(Label lblCuotas) {
		this.lblCuotas = lblCuotas;
	}

	public DropDown getDdCuotas() {
		return ddCuotas;
	}

	public void setDdCuotas(DropDown ddCuotas) {
		this.ddCuotas = ddCuotas;
	}

	public SingleSelectOptionsList getDdCuotasOptions() {
		return ddCuotasOptions;
	}

	public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
		this.ddCuotasOptions = ddCuotasOptions;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public DropDown getDdAnios() {
		return ddAnios;
	}

	public void setDdAnios(DropDown ddAnios) {
		this.ddAnios = ddAnios;
	}

	public SingleSelectOptionsList getDdAniosOptions() {
		return ddAniosOptions;
	}

	public void setDdAniosOptions(SingleSelectOptionsList ddAniosOptions) {
		this.ddAniosOptions = ddAniosOptions;
	}

	public Label getLblCalendarios() {
		return lblCalendarios;
	}

	public void setLblCalendarios(Label lblCalendarios) {
		this.lblCalendarios = lblCalendarios;
	}

	public DropDown getDdCalendarios() {
		return ddCalendarios;
	}

	public void setDdCalendarios(DropDown ddCalendarios) {
		this.ddCalendarios = ddCalendarios;
	}

	public SingleSelectOptionsList getDdCalendariosOptions() {
		return ddCalendariosOptions;
	}

	public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
		this.ddCalendariosOptions = ddCalendariosOptions;
	}

	public Label getLblPeriodos() {
		return lblPeriodos;
	}

	public void setLblPeriodos(Label lblPeriodos) {
		this.lblPeriodos = lblPeriodos;
	}

	public DropDown getDdPeriodos() {
		return ddPeriodos;
	}

	public void setDdPeriodos(DropDown ddPeriodos) {
		this.ddPeriodos = ddPeriodos;
	}

	public SingleSelectOptionsList getDdPeriodosOptions() {
		return ddPeriodosOptions;
	}

	public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
		this.ddPeriodosOptions = ddPeriodosOptions;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label lblNroInscripcion = new Label();
	private TextField tfNroInscripcion = new TextField();

	public Label getLblNroInscripcion() {
		return lblNroInscripcion;
	}

	public void setLblNroInscripcion(Label lblNroInscripcion) {
		this.lblNroInscripcion = lblNroInscripcion;
	}

	public TextField getTfNroInscripcion() {
		return tfNroInscripcion;
	}

	public void setTfNroInscripcion(TextField tfNroInscripcion) {
		this.tfNroInscripcion = tfNroInscripcion;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	private Button btnImprimir = new Button();

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
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

	private ObjectListDataProvider ldpDeclaracionJuradaSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDeclaracionJuradaSHPS() {
		return ldpDeclaracionJuradaSHPS;
	}

	public void setLdpDeclaracionJuradaSHPS(ObjectListDataProvider oldp) {
		this.ldpDeclaracionJuradaSHPS = oldp;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public AdminDDJJSHPS() {
	}

	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	protected void guardarEstadoObjetosUsados() {
		FiltroDDJJSHPS locFiltro = getFiltro();

		borrarListIdAuxPersonas(this.tfPersona, locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());

		locFiltro.setNroInscripcion(this.getTextFieldValue(this.getTfNroInscripcion()));

		locFiltro.setAnio(this.getDDObjectValue(getDdAnios(), this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS()));
		if(locFiltro.getAnio() != null) {
			locFiltro.setCalendario(this.getDDObjectValue(getDdCalendarios(), this.getCommunicationSAICBean().getMapaCalendariosSHPS(locFiltro.getAnio().toString())));
		}
		if(locFiltro.getCalendario() != null) {
			locFiltro.setPeriodo(this.getDDObjectValue(getDdPeriodos(), this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(locFiltro.getCalendario().getNombre())));
		}
		if(locFiltro.getPeriodo() != null) {
			locFiltro.setCuota(this.getDDObjectValue(getDdCuotas(), this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(locFiltro.getPeriodo().toString())));
		}
	}

	protected void mostrarEstadoObjetosUsados() {
		FiltroDDJJSHPS locFiltro = getFiltro();

		if(locFiltro.getPersona() != null) {
			this.getTfPersona().setText(locFiltro.getPersona().toString());
		}

		this.getTfNroInscripcion().setText(locFiltro.getNroInscripcion());

		if(locFiltro.getAnio() != null) {
			seleccionarAnio(locFiltro.getAnio().toString(), true);
		}
		if(locFiltro.getCalendario() != null && !locFiltro.getCalendario().getNombre().isEmpty()) {
			seleccionarCalendario(locFiltro.getCalendario().getNombre(), true);
		}
		if(locFiltro.getPeriodo() != null && !locFiltro.getPeriodo().toString().isEmpty()) {
			seleccionarPeriodo(locFiltro.getPeriodo().toString(), true);
		}
		if(locFiltro.getCuota() != null && !locFiltro.getCuota().toString().isEmpty()) {
			seleccionarCuota(locFiltro.getCuota().toString());
		}
	}

	protected void limpiarObjetosUsados() {
		FiltroDDJJSHPS locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setNroInscripcion(null);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);

		this.getTfPersona().setText(null);
		this.getTfNroInscripcion().setText(null);
		this.seleccionarAnio(this.getAnioCorriente().toString(), true);
		this.getDdPeriodos().setSelected(null);
		this.getDdCuotas().setSelected(null);

		this.getSessionBean1().getListaIdPersonas().clear();
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDeclaracionJuradaSHPS();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaDeclaracionesJuradasSHPS();
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnAgregar_action() {
		return toAbm(new DDJJSHPSModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new DDJJSHPSModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new DDJJSHPSModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new DDJJSHPSModel().new ConsultarController());
	}

	public String btnImprimir_action() {
		return toAbm(new ImprimirDDJJSHPSModel().new ImprimirDDJJSHPSController());
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			FiltroDDJJSHPS locFiltro = this.getFiltro();
			this.getTfPersona().setText("");
			locFiltro.setPersona(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				Persona persona = null;

				persona = this.getSessionBean1().getPersonaSeleccionada();// pf
				// if (locPersona != null && locPersona.getIdPersona() != -1) {
				// persona = locPersona;
				// }
				// locPersona = (Persona)
				// this.getElementoPila().getObjetos().get(1);//pj
				// if (locPersona != null && locPersona.getIdPersona() != -1) {
				// persona = locPersona;
				// }

				if(persona != null && persona.getIdPersona() != -1) {
					this.getSessionBean1().setObjetoImpresion(persona);

				} else {
					error(NOMBRE_PAGINA + " - Imprimir: Debe seleccionar una Persona Física o Jurídica.");
				}
			} catch(Exception e) {
				log(CASO_NAVEGACION + "_ImprimirError: ", e);
				error(NOMBRE_PAGINA + " - Imprimir: " + e.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// this.guardarOrdenamiento();
			// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			// this.getElementoPila().setPosicionGlobal(pos.longValue());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private String llenarDD(SingleSelectOptionsList ddOpciones, Set<String> llaves) {
		Option[] opciones;
		String opcion = "";
		if(llaves == null || llaves.isEmpty()) {
			opciones = new Option[0];
		} else if(llaves.size() > 1) {
			opciones = new Option[llaves.size() + 1];
			opciones[0] = new Option("");
			int i = 1;
			for(String cadaLlave : llaves) {
				opciones[i++] = new Option(cadaLlave);
			}
		} else {
			opciones = new Option[llaves.size()];
			opcion = llaves.iterator().next();
			opciones[0] = new Option(opcion);
		}
		ddOpciones.setOptions(opciones);
		return opcion;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaDeclaracionesJuradasSHPS(lista);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaDDJJSHPS();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		DeclaracionJuradaSHPS locDDJJSHPS = (DeclaracionJuradaSHPS) pObject;
		this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
		locDDJJSHPS = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getDDJJSHPSporId(locDDJJSHPS.getIdRegistroValuado());
		return locDDJJSHPS;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
		FiltroDDJJSHPS filtro = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getListaDDJJSHPS((FiltroDDJJSHPS) pFiltro);
		return filtro;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroDDJJSHPS locFiltro = getFiltro();
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			locFiltro.setPersona(persona);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Declaraciones Juradas de SHPS";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDDJJSHPS";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroDDJJSHPS locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(locPersona);
		this.getSessionBean1().setPersonaSeleccionada(locPersona);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS}";
	}

	@Override
	public long getSerialVersionUID() {
		return DeclaracionJuradaSHPS.serialVersionUID;
	}
}