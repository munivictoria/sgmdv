/*
 * AdminDocEspOSP.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpOSP.ABMDocEspOSP;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminDocEspOSP extends AdminPageBean {

	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private Label lblExenciones = new Label();
	private DropDown ddExenciones = new DropDown();
	private SingleSelectOptionsList ddExencionesDefaultOptions = new SingleSelectOptionsList();

	public DropDown getDdExenciones() {
		return ddExenciones;
	}

	public void setDdExenciones(DropDown ddExenciones) {
		this.ddExenciones = ddExenciones;
	}

	public SingleSelectOptionsList getDdExencionesDefaultOptions() {
		return ddExencionesDefaultOptions;
	}

	public void setDdExencionesDefaultOptions(SingleSelectOptionsList ddExencionesDefaultOptions) {
		this.ddExencionesDefaultOptions = ddExencionesDefaultOptions;
	}

	public Label getLblExenciones() {
		return lblExenciones;
	}

	public void setLblExenciones(Label lblExenciones) {
		this.lblExenciones = lblExenciones;
	}

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

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	protected PanelAtributoDinamico panelAtributoDinamico;

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
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

	private ObjectListDataProvider ldpDocEspOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspOSP() {
		return ldpDocEspOSP;
	}

	public void setLdpDocEspOSP(ObjectListDataProvider oldp) {
		this.ldpDocEspOSP = oldp;
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

	private Button btnSeleccionarSubparcela = new Button();

	public Button getBtnSeleccionarSubparcela() {
		return btnSeleccionarSubparcela;
	}

	public void setBtnSeleccionarSubparcela(Button btnSeleccionarSubparcela) {
		this.btnSeleccionarSubparcela = btnSeleccionarSubparcela;
	}

	private TextArea textArea1 = new TextArea();

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
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

	// private Button btnSeleccionarPlantillaObligacion = new Button();
	//
	// public Button getBtnSeleccionarPlantillaObligacion() {
	// return btnSeleccionarPlantillaObligacion;
	// }
	//
	// public void setBtnSeleccionarPlantillaObligacion(Button b) {
	// this.btnSeleccionarPlantillaObligacion = b;
	// }

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

	// private Button btnLimpiarPlantillaObligacion = new Button();
	//
	// public Button getBtnLimpiarPlantillaObligacion() {
	// return btnLimpiarPlantillaObligacion;
	// }
	//
	// public void setBtnLimpiarPlantillaObligacion(Button b) {
	// this.btnLimpiarPlantillaObligacion = b;
	// }

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private Label lblNroRegistro = new Label();

	public Label getLblNroRegistro() {
		return lblNroRegistro;
	}

	public void setLblNroRegistro(Label l) {
		this.lblNroRegistro = l;
	}

	private Label lbNroCuenta = new Label();

	public Label getLbNroCuenta() {
		return lbNroCuenta;
	}

	public void setLbNroCuenta(Label l) {
		this.lbNroCuenta = l;
	}

	private TextField tfNumeroRegistro = new TextField();

	public TextField getTfNumeroRegistro() {
		return tfNumeroRegistro;
	}

	public void setTfNumeroRegistro(TextField tf) {
		this.tfNumeroRegistro = tf;
	}

	private TextField tfNumeroCuenta = new TextField();

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tf) {
		this.tfNumeroCuenta = tf;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
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

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		ddExencionesDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] { new com.sun.rave.web.ui.model.Option("", ""),
				new com.sun.rave.web.ui.model.Option("true", "Posee"), new com.sun.rave.web.ui.model.Option("false", "No posee") });
		ddExencionesDefaultOptions.setSelectedValue("");

		Set<String> locListaPlantillasObligaciones = this.getApplicationBean1().getMapaPlantillaObligacionOSP().keySet();
		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size() + 1];
		int i = 0;
		opPlantillasObligaciones[i++] = new Option("", "");
		for (String cadaPlantilla : locListaPlantillasObligaciones) {
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminDocEspOSP() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaObligacion());

		FiltroObligacionOSP locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoOSP.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
		Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();

		Object exenciones = this.getDdExenciones().getSelected();

		FiltroObligacionOSP locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());

		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Obligacion.Estado.class));

		if (this.ddPlantillaObligacion.getSelected() != null && this.ddPlantillaObligacion.getSelected() != "") {
			plantillaObligacion = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		} else {
			plantillaObligacion = null;
		}

		locFiltro.setPoseeExenciones(new Boolean((String) exenciones));
		locFiltro.setNumeroCuenta(getTextFieldValueInteger(this.getTfNumeroCuenta()));
		locFiltro.setParcela(parcela);
		locFiltro.setPersona(persona);

		if (locFiltro.getListaAtributosDinamicos() != null) {
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, plantillaObligacion);
	}

	private PlantillaObligacion getPlantillaPorNombre(String pPlantilla) {
		return this.getApplicationBean1().getMapaPlantillaObligacionOSP().get(pPlantilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = null;
		PlantillaObligacion plantillaObligacion = null;
		Parcela parcela = null;

		FiltroObligacionOSP locFiltro = this.getFiltro();

		plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
		persona = this.getSessionBean1().getPersonaSeleccionada();
		parcela = this.getSessionBean1().getParcelaSeleccionada();

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());

		if (persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}

		if (plantillaObligacion != null && plantillaObligacion.getIdPlantillaObligacion() != -1) {
			this.getDdPlantillaObligacion().setSelected(plantillaObligacion.toString());
		}

		if (parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcelaSeleccionada().setText(parcela.toString());
		}
		if (locFiltro.getNumeroCuenta() != null) {
			this.getTfNumeroCuenta().setText(locFiltro.getNumeroCuenta().toString());
		}
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Obligacion.Estado.CREADO : locFiltro.getEstado())));

	}

	// </editor-fold>

	public String btnSeleccionarSubparcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		// int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()
			// - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setRefrescarTabla(true);
			this.getRequestBean1().setTipoSeleccion("SUBPARCELA");

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminSubparcela";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	// public String btnSeleccionarPlantillaObligacion_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// // CAMBIAR: guardo la posicion donde debo guardar el objeto que
	// seleccione
	// int posicionObjetoSeleccionado = 0;
	//
	// if (ultimo) {
	//
	// try {
	// RowKey rk = this.getSeleccionado();
	// if (rk != null) {
	// this.setRowKeySeleccionado(this.getSeleccionado());
	// }
	// } catch (Exception ex) {
	// // CAMBIAR:
	// log(CASO_NAVEGACION + "_SeleccionarPlantillaObligacionError:", ex);
	// error(NOMBRE_PAGINA + " - Seleccionar Plantilla de Obligaci\363n: " +
	// ex.getMessage());
	// }
	//
	// this.getRequestBean1().setObjetoSeleccion(this.getCommunicationHabilitacionesBean().getMapaTipoTasa().get("OYSP"));
	// this.guardarEstadoObjetosUsados();
	// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() -
	// 1, new Integer(posicionObjetoSeleccionado));
	// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	// this.guardarOrdenamiento();
	// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
	// this.getElementoPila().setPosicionGlobal(pos.longValue());
	//
	// // CAMBIAR: Caso de navegacion de la pagina de administracion
	// correspondiente.
	// retorno = "AdminPlantillaObligacion";
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	//
	// return retorno;
	// }

	// public String btnLimpiarPlantillaObligacion_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	// if (ultimo) {
	// // CAMBIAR: Especificar objeto
	// this.limpiarObjeto(1, this.getTfPlantillaObligacion());
	// this.guardarEstadoObjetosUsados();
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDocEspOSP();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaDocEspOSP();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaDocEspOSP(lista);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroObligacionOSP locObligacionOSP = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP((FiltroObligacionOSP) pFiltro);
		return locObligacionOSP;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Documentos Especiales OSP";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspOSP";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaDocEspOSP();
	}

	public String btnAgregar_action() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		noVacios[0] = this.getDdPlantillaObligacion();
		nomNoVacios[0] = "Plantilla de Obligaci\363n del Tipo OSP";

		v.noSonVacios(noVacios, nomNoVacios);

		if (v.getErrores().size() > 0) {
			for (int i = 0; i < v.getErrores().size(); i++) {
				warn(v.getErrores().toArray()[i].toString());
			}
			return null;
		}
		PlantillaObligacion locPlantilla = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		try {
			locPlantilla = this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().getPlantillaObligacion(locPlantilla.getIdPlantillaObligacion());
		} catch (Exception ex) {
			Logger.getLogger(AdminDocEspOSP.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.getRequestBean1().setObjetoABM(locPlantilla);
		return toAbm(new DocEspOSPModel().new AgregarDocEspOSPController());
	}

	public String btnModificar_action() {
		return toAbm(new DocEspOSPModel().new ModificarDocEspOSPController());
	}

	public String btnConsultar_action() {
		return toAbm(new DocEspOSPModel().new ConsultarDocEspOSPController());
	}

	public String btnEliminar_action() {
		return toAbm(new DocEspOSPModel().new EliminarDocEspOSPController());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroObligacionOSP locFiltro = this.getFiltro();
		locFiltro.setNumeroCuenta(null);
		locFiltro.setParcela(null);
		locFiltro.setPersona(null);
		locFiltro.setPoseeExenciones(false);
		locFiltro.setEstado(Obligacion.Estado.CREADO);

		this.getSessionBean1().setParcelaSeleccionada(null);
		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Obligacion.Estado.CREADO)));
		this.getTfNumeroCuenta().setText("");
		this.getTfParcelaSeleccionada().setText("");
		this.getTfPersonaSeleccionada().setText("");
		this.getDdPlantillaObligacion().setSelected("");
		this.ddPlantillaObligacion.setSelected(null);
		this.ddExenciones.setSelected(null);
		this.getPanelAtributoDinamico().limpiarCampos();
		
	}

	public String btnImprimirReporte_action() {
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Persona persona = null;
		Parcela parcela = null;
		SubParcela subparcela = null;

		Object seleccionado = pObject;
		if (seleccionado instanceof Persona) {
			persona = (Persona) seleccionado;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			this.getRequestBean1().setObjetoSeleccion(null);
			this.limpiarTabla();
		}
		if (this.getRequestBean1().getTipoSeleccion() != null && this.getRequestBean1().getTipoSeleccion().equals("SUBPARCELA")) {
			System.out.println("es SUBPARCELA");
			subparcela = (SubParcela) seleccionado;
			this.getSessionBean1().setParcelaSeleccionada(parcela);
		} else {
			if (seleccionado instanceof Parcela) {
				System.out.println("es PARCELA");
				parcela = (Parcela) seleccionado;
				this.getSessionBean1().setParcelaSeleccionada(parcela);
			}
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoOSP.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionOSP locFiltro = this.getFiltro();
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
