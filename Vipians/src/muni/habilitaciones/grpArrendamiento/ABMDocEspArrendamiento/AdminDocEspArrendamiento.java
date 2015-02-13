/*
 * AdminDocEspArrendamiento.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpArrendamiento.ABMDocEspArrendamiento;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import muni.habilitaciones.grpArrendamiento.ABMDocEspArrendamiento.DocEspArrendamientoModel.ConsultarDocEspController;

import com.sun.data.provider.RowKey;
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
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;
/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminDocEspArrendamiento extends AdminPageBean {


	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Modificar Obligaci\363n: Arrendamiento";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ModificarDocEspArrendamiento";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;    // </editor-fold>    
	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		ddExencionesDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[]{new com.sun.rave.web.ui.model.Option("", ""), new com.sun.rave.web.ui.model.Option("true", "Posee"), new com.sun.rave.web.ui.model.Option("false", "No posee")});
		ddExencionesDefaultOptions.setSelectedValue("");

		Set<String> locListaPlantillasObligaciones = getApplicationBean1().getMapaPlantillaObligacionArrendamiento().keySet();

		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size()];
		int i = 0;
		for (String cadaPlantilla : locListaPlantillasObligaciones){
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);
	}

	protected PanelAtributoDinamico panelAtributoDinamico;
	private Label lblExenciones = new Label();
	private DropDown ddExenciones = new DropDown();
	private SingleSelectOptionsList ddExencionesDefaultOptions = new SingleSelectOptionsList();
	private Button btnReactivar = new Button();
	
	public Button getBtnReactivar() {
		return btnReactivar;
	}

	public void setBtnReactivar(Button btnReactivar) {
		this.btnReactivar = btnReactivar;
	}

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

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public Button getbtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setbtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
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

	private ObjectListDataProvider ldpDocEspArrendamiento = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspArrendamiento() {
		return ldpDocEspArrendamiento;
	}

	public void setLdpDocEspArrendamiento(ObjectListDataProvider oldp) {
		this.ldpDocEspArrendamiento = oldp;
	}
	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
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

	private Button btnSeleccionarPlantillaObligacion = new Button();

	public Button getBtnSeleccionarPlantillaObligacion() {
		return btnSeleccionarPlantillaObligacion;
	}

	public void setBtnSeleccionarPlantillaObligacion(Button b) {
		this.btnSeleccionarPlantillaObligacion = b;
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
	private Button btnLimpiarPlantillaObligacion = new Button();

	public Button getBtnLimpiarPlantillaObligacion() {
		return btnLimpiarPlantillaObligacion;
	}

	public void setBtnLimpiarPlantillaObligacion(Button b) {
		this.btnLimpiarPlantillaObligacion = b;
	}
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
	private Label label1 = new Label();
	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}
	private TextField tfNumeroRegistro = new TextField();

	public TextField getTfNumeroRegistro() {
		return tfNumeroRegistro;
	}

	public void setTfNumeroRegistro(TextField tf) {
		this.tfNumeroRegistro = tf;
	}
	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
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
	private Button idImprimirReporte = new Button();
	private Button btnImprimirReporte = new Button();


	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	public Button getIdImprimirReporte() {
		return idImprimirReporte;
	}

	public void setIdImprimirReporte(Button b) {
		this.idImprimirReporte = b;
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
	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	private Button btnDardeAButton = new Button();

	public Button getBtnDardeAButton() {
		return btnDardeAButton;
	}

	public void setBtnDardeAButton(Button btnDardeAButton) {
		this.btnDardeAButton = btnDardeAButton;
	}

	// </editor-fold>

	/**
	 * <p>Construir una instancia de bean de p?gina.</p>
	 */
	public AdminDocEspArrendamiento() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
		List atributosDinamicos = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoArrendamiento.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);

		ep.getObjetos().add(ind++, new PlantillaObligacion()); // 0
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
		Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();

		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getPersona());
		locFiltro.setListaIdPersona(this.getSessionBean1().getListaIdPersonas());
		borrarListIdAuxParcelas(this.getTfParcelaSeleccionada(), locFiltro.getParcela());
		locFiltro.setListaIdParcela(this.getSessionBean1().getListaIdParcelas());

		Object exenciones = this.getDdExenciones().getSelected();

		locFiltro.setNroParcela(getTextFieldValue(this.getTfNroParcela()));
		this.getSessionBean1().setNroParcela(getTextFieldValue(this.getTfNroParcela()));

		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Obligacion.Estado.class));

		if (this.ddPlantillaObligacion.getSelected() != null && this.ddPlantillaObligacion.getSelected() != "") {
			plantillaObligacion = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		} else {
			plantillaObligacion = null;
		}

		if(persona != null && persona.getIdPersona() != -1){
			locFiltro.setPersona(persona);
		}else{
			locFiltro.setPersona(null);
		}

		if(parcela != null && parcela.getIdParcela() != -1){
			locFiltro.setParcela(parcela);
		}else{
			locFiltro.setParcela(null);
		}

		if(locFiltro.getListaAtributosDinamicos() != null){
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}
	}
	private PlantillaObligacion getPlantillaPorNombre(String pPlantilla){
		return getApplicationBean1().getMapaPlantillaObligacionArrendamiento().get(pPlantilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = null;
		PlantillaObligacion plantillaObligacion = null;
		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
		//        Integer numeroRegistroEP = null;
		//        Parcela parcela = null;
		//        List atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		//        String exenciones;

		persona = this.getSessionBean1().getPersonaSeleccionada();
		plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
		locFiltro.setParcela(this.getSessionBean1().getParcelaSeleccionada());

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());

		if(this.getSessionBean1().getNroParcela() != null){
			this.getTfNroParcela().setText(this.getSessionBean1().getNroParcela());
		}

		if (persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		}

		/*if (numeroRegistroEP != null) {
            this.getTfNumeroRegistro().setText(numeroRegistroEP.toString());
        }*/
		if(locFiltro.getParcela() != null && locFiltro.getParcela().getIdParcela() != -1){
			this.getTfParcelaSeleccionada().setText(locFiltro.getParcela().toString());
		}
		if(plantillaObligacion!=null && plantillaObligacion.getIdPlantillaObligacion()!=-1){
			this.getDdPlantillaObligacion().setSelected(plantillaObligacion.toString());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Obligacion.Estado.CREADO : locFiltro.getEstado())));
		
		System.out.println("ESTADO DE LA OBLIGACION: "+plantillaObligacion.toString());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
		locFiltro.setEstado(Obligacion.Estado.CREADO);
		locFiltro.setNroParcela(null);
		locFiltro.setPersona(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setParcelaSeleccionada(null);
		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersonaSeleccionada().setText(null);
		this.ddPlantillaObligacion.setSelected(null);
		this.getPanelAtributoDinamico().limpiarCampos();
		this.getTfNroParcela().setText(null);
		this.getTfParcelaSeleccionada().setText(null);
		this.getPanelAtributoDinamico().limpiarCampos();
		this.getDdExenciones().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Obligacion.Estado.CREADO)));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
		return this.getLdpDocEspArrendamiento();
	}

	@Override
	public List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaDocEspArrendamiento();
	}
	
	public String btnAgregar_action() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = this.getDdPlantillaObligacion();
		nomNoVacios[pos++] = "Plantilla de Obligaci\363n del Tipo Arrendamiento";
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
			ex.printStackTrace();
		}
		this.getRequestBean1().setObjetoABM(locPlantilla);
		return toAbm(new DocEspArrendamientoModel().new AgregarDocEspArrendamientoController());
	}

	public String btnModificar_action() {
		Obligacion locObligacion = (Obligacion) getPaginatedTable().getObjetoSeleccionado();
		if (locObligacion.getEstado() != Obligacion.Estado.CREADO) {
			warn("Solo se pueden modificar Obligaciones CREADAS.");
			return null;
		}
		return toAbm(new DocEspArrendamientoModel().new ModificarDocEspArrendamientoController());
	}

	public String btnDardeAlta_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		DocumentoArrendamiento documentoArrendamiento = null;
		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();

				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion obligacion = (Obligacion) obj;

					if (!obligacion.getEstado().equals(Estado.PENDIENTE_DE_ALTA)) {
						System.out.println("estado de la oblig: " + obligacion.getEstado());
						warn("Las obligaciones deben estar en estado Pendiente de Alta.");
						return null;
					}
					try {
						this.getCommunicationHabilitacionesBean().getRemoteSystemArrendamientos().setLlave(this.getSessionBean1().getLlave());
						documentoArrendamiento = this.getCommunicationHabilitacionesBean().getRemoteSystemArrendamientos().getDocumentoArrendamiento(obligacion);
					} catch (Exception ex) {
						log(CASO_NAVEGACION + "_getDocumentoHabilitanteArrendamiento:", ex);
						error(NOMBRE_PAGINA + " - No se pudo obtener el Documento Arrendamiento: " + ex.getMessage());
						ex.printStackTrace();
						return null;
					}

					if (documentoArrendamiento.getParcela().getTituloPropiedad() == null) {
						warn("La parcela de la Obligaci\363n seleccionada no posee T\355tulo de Propiedad.");
						return null;
					}
					// paso la obligacion                    
					this.getRequestBean1().setObjetoABM(obligacion);
					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch (Exception ex) {
				log(CASO_NAVEGACION + "_ModificarError:", ex);
				error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if (rk != null) {
				retorno = "DardeAltaDocEspArrendamiento";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnReactivar_action() {
		Obligacion locObligacion = (Obligacion) getObjetoSeleccionado();
		if (!locObligacion.getEstado().equals(Obligacion.Estado.ANULADO)) {
			warn("Solo se pueden Reactivar Obligaciones ANULADAS.");
			return null;
		}
		return toAbm(new DocEspArrendamientoModel().new RectivarDocEspArrendamientoController());
	}

	public String btnEliminar_action() {
		Obligacion locObligacion = (Obligacion) getObjetoSeleccionado();
		if (!locObligacion.getEstado().equals(Obligacion.Estado.CREADO)) {
			warn("Solo se pueden eliminar Obligaciones CREADAS.");
			return null;
		}
		return toAbm(new DocEspArrendamientoModel(). new EliminarDocEspArrendamientoController());
	}

	public String idImprimirReporte_action() {
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	public String btnConsultar_action() {
		return toAbm(new DocEspArrendamientoModel().new ConsultarDocEspController());
	}

	public String btnSeleccionarDomicilioParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 4;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Parcela parcela = this.obtenerObjetoDelElementoPila(3, Parcela.class);
			if (parcela.getIdParcela() != -1) {
				Domicilio domicilio = parcela.getDomicilioParcelario();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("Seleccione una Parcela para obtener el Domicilio Parcelario.");
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaDocEspArrendamiento((ArrayList)lista);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroObligacionArrendamiento locFiltroObligacionArrendamiento = (FiltroObligacionArrendamiento) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesArrendamiento(locFiltroObligacionArrendamiento);
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Obligaciones Arrendamiento";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspArrendamiento";
	}
	@Override
	public PaginatedTable getPaginatedTable(){
		return this.getCommunicationHabilitacionesBean().getTablaDocumentoArrendamiento();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

		if (pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			this.limpiarTabla();
			this.getRequestBean1().setObjetoSeleccion(null);
		}

		if (pObject instanceof Parcela) {
			Parcela parcela = (Parcela) pObject;
			this.getSessionBean1().setParcelaSeleccionada(parcela);
			this.limpiarTabla();
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		boolean errorEnReporte = false;

		if (ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion  locObligacion = (Obligacion) obj;
					DocumentoArrendamiento docArrendamiento = (DocumentoArrendamiento) locObligacion.getDocumentoEspecializado();

					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());

					//					JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteLiquidacionDeudaServiciosMunicipales(docArrendamiento);

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Liquidacion_Deuda_Servicios_Municipales");
					//					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				} else {
					errorEnReporte = true;
				}
			} catch (Exception e) {
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
				errorEnReporte = true;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		if (errorEnReporte) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
		}
		return retorno;
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoArrendamiento.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
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
	
	public void setParcelaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroObligacionArrendamiento locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;
		
		try {
			locParcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (TrascenderException e) {
			e.printStackTrace();
		}
		
		locFiltro.setParcela(locParcela);
		this.getSessionBean1().setParcelaSeleccionada(locParcela);
	}
}
