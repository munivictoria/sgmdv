/*
 * AdminDocEspSHPS.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMDocEspSHPS;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

public class AdminDocEspSHPS extends AdminPageBean {

	private Label label1 = new Label();

	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private Label lblExenciones = new Label();
	private DropDown ddExenciones = new DropDown();
	private SingleSelectOptionsList ddExencionesDefaultOptions = new SingleSelectOptionsList();
	private Label lblCuim = new Label();
	private TextField tfCuim = new TextField();
	private Label label17 = new Label();
	private TextField tfContador = new TextField();

	public Label getLabel17() {
		return label17;
	}

	public void setLabel17(Label label17) {
		this.label17 = label17;
	}

	public TextField getTfContador() {
		return tfContador;
	}

	public void setTfContador(TextField tfContador) {
		this.tfContador = tfContador;
	}

	public Label getLblCuim() {
		return lblCuim;
	}

	public void setLblCuim(Label lblCuim) {
		this.lblCuim = lblCuim;
	}

	public TextField getTfCuim() {
		return tfCuim;
	}

	public void setTfCuim(TextField tfCuim) {
		this.tfCuim = tfCuim;
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

	private ObjectListDataProvider ldpDocEspSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspSHPS() {
		return ldpDocEspSHPS;
	}

	public void setLdpDocEspSHPS(ObjectListDataProvider oldp) {
		this.ldpDocEspSHPS = oldp;
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
	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}
	private TextField tfNumeroInscripcion = new TextField();

	public TextField getTfNumeroInscripcion() {
		return tfNumeroInscripcion;
	}

	public void setTfNumeroInscripcion(TextField tf) {
		this.tfNumeroInscripcion = tf;
	}
	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}
	private Button btnImprimirReporte = new Button();
	private Button btnSeleccionarContador = new Button();
	private Button btnSeleccionarPersonaJuridicaContador = new Button();
	private HtmlAjaxCommandButton btnLimpiarContador = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarContador() {
		return btnLimpiarContador;
	}

	public void setBtnLimpiarContador(HtmlAjaxCommandButton btnLimpiarContador) {
		this.btnLimpiarContador = btnLimpiarContador;
	}

	public Button getBtnSeleccionarContador() {
		return btnSeleccionarContador;
	}

	public void setBtnSeleccionarContador(Button btnSeleccionarContador) {
		this.btnSeleccionarContador = btnSeleccionarContador;
	}

	public Button getBtnSeleccionarPersonaJuridicaContador() {
		return btnSeleccionarPersonaJuridicaContador;
	}

	public void setBtnSeleccionarPersonaJuridicaContador(
			Button btnSeleccionarPersonaJuridicaContador) {
		this.btnSeleccionarPersonaJuridicaContador = btnSeleccionarPersonaJuridicaContador;
	}

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
	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
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

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	@Override
	protected void _init() throws Exception {

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Obligacion.Estado.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		ddExencionesDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[]{new com.sun.rave.web.ui.model.Option("", ""), new com.sun.rave.web.ui.model.Option("true", "Posee"), new com.sun.rave.web.ui.model.Option("false", "No posee")});
		ddExencionesDefaultOptions.setSelectedValue("");

		Set<String> locListaPlantillasObligaciones = getApplicationBean1().getMapaPlantillaObligacionSHPS().keySet();

		Option[] opPlantillasObligaciones = new Option[locListaPlantillasObligaciones.size() + 1];
		int i = 0;
		opPlantillasObligaciones[i++] = new Option("", "");
		for (String cadaPlantilla : locListaPlantillasObligaciones){
			opPlantillasObligaciones[i++] = new Option(cadaPlantilla, cadaPlantilla);
		}
		this.ddPlantillaObligacionOptions.setOptions(opPlantillasObligaciones);
	}

	// </editor-fold>
	/**
	 * <p>Construir una instancia de bean de p?gina.</p>
	 */
	public AdminDocEspSHPS() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		//CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new PlantillaObligacion());
		ep.getObjetos().add(ind++, null);

		FiltroObligacionSHPS locFiltro = this.getFiltro();
		List atributosDinamicos = null;
		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro()
					.getAtributosPorRecurso(DocumentoSHPS.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);

		Object exenciones = this.getDdExenciones().getSelected();

		FiltroObligacionSHPS locFiltro = this.getFiltro();

		locFiltro.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Obligacion.Estado.class));
		locFiltro.setCuim(getTextFieldValue(this.getTfCuim()));

		if (this.ddPlantillaObligacion.getSelected() != null && this.ddPlantillaObligacion.getSelected() != "") {
			plantillaObligacion = this.getPlantillaPorNombre(this.ddPlantillaObligacion.getSelected().toString());
		} else {
			plantillaObligacion = null;
		}
		locFiltro.setPoseeExenciones(new Boolean((String)exenciones));

		if (locFiltro.getContador() != null){
			locFiltro.setContador(locFiltro.getContador());
		}else{
			locFiltro.setPersona(persona);
		}

		if(locFiltro.getListaAtributosDinamicos() != null){
			locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
		}
		this.getElementoPila().getObjetos().set(0, plantillaObligacion);
	}

	private PlantillaObligacion getPlantillaPorNombre(String pPlantilla){
		return getApplicationBean1().getMapaPlantillaObligacionSHPS().get(pPlantilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);

		FiltroObligacionSHPS locFiltro = this.getFiltro();

		if (persona != null) {
			this.getTfPersonaSeleccionada().setText(persona.toString());
		} else {
			this.getTfPersonaSeleccionada().setText("");
		}
		if ( locFiltro.getContador() != null) {
			this.getTfContador().setText(locFiltro.getContador().toString());
		} else {
			this.getTfContador().setText("");
		}
		if(plantillaObligacion!=null && plantillaObligacion.getIdPlantillaObligacion()!=-1){
			this.getDdPlantillaObligacion().setSelected(plantillaObligacion.toString());
		}
		if (locFiltro.getCuim() != null) {
			this.getTfCuim().setText(locFiltro.getCuim());
		}
		
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Obligacion.Estado.CREADO : locFiltro.getEstado())));

		this.getDdExenciones().setSelected(locFiltro.getPoseeExenciones());
		this.getTfNumeroInscripcion().setText(locFiltro.getNumeroInscripcion());

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(),"#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
	}


	@Override
	protected void limpiarObjetosUsados() {
		FiltroObligacionSHPS locFiltro = this.getFiltro();
		locFiltro.setCuim(null);
		locFiltro.setEstado(Obligacion.Estado.CREADO);
		locFiltro.setNumeroInscripcion(null);
		locFiltro.setPersona(null);
		locFiltro.setContador(null);
		locFiltro.setPoseeExenciones(false);

		this.getSessionBean1().setPersonaSeleccionada(null);

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersonaSeleccionada().setText(null);
		this.getTfContador().setText(null);
		this.getTfCuim().setText("");
		this.getDdExenciones().setSelected(null);
		this.getTfNumeroInscripcion().setText(null);
		this.ddPlantillaObligacion.setSelected(null);
		this.getPanelAtributoDinamico().limpiarCampos();
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Obligacion.Estado.CREADO)));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
		return this.getLdpDocEspSHPS();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaDocEspSHPS();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaDocEspSHPS(lista);
	}

	//    public String btnSeleccionarPlantillaObligacion_action() {
	//        String retorno = null;
	//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	//        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
	//        int posicionObjetoSeleccionado = 0;
	//
	//        if (ultimo) {
	//
	//            try {
	//                RowKey rk = this.getSeleccionado();
	//                if (rk != null) {
	//                    this.setRowKeySeleccionado(this.getSeleccionado());
	//                }
	//            } catch (Exception ex) {
	//                // CAMBIAR:
	//                log(getCasoNavegacion() + "_SeleccionarPlantillaObligacionError:", ex);
	//                error(getNombrePagina()+ " - Seleccionar Plantilla de Obligaci\363n: " + ex.getMessage());
	//            }
	//
	//            this.getRequestBean1().setObjetoSeleccion(this.getCommunicationHabilitacionesBean().getMapaTipoTasa().get("SHPS"));
	//
	//            this.guardarEstadoObjetosUsados();
	//            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
	//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	//            this.guardarOrdenamiento();
	//            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
	//            this.getElementoPila().setPosicionGlobal(pos.longValue());
	//
	//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
	//            retorno = "AdminPlantillaObligacion";
	//        } else {
	//            retorno = this.prepararCaducidad();
	//        }
	//
	//        return retorno;
	//    }

	//    public String btnLimpiarPlantillaObligacion_action() {
	//        String retorno = null;
	//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//        if (ultimo) {
	//            // CAMBIAR: Especificar objeto
	//            this.limpiarObjeto(0, this.getTfPlantillaObligacion());
	//            this.guardarEstadoObjetosUsados();
	//        } else {
	//            retorno = this.prepararCaducidad();
	//        }
	//        return retorno;
	//    }

	public String btnAgregar_action() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		noVacios[0] = this.getDdPlantillaObligacion();
		nomNoVacios[0] =  "Plantilla de Obligaci\363n del Tipo SHPS";

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
			Logger.getLogger(AdminDocEspSHPS.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.getRequestBean1().setObjetoABM(locPlantilla);
		return toAbm(new DocEspSHPSModel().new AgregarController());
	}

	public String btnModificar_action() {
		Obligacion obligacion = (Obligacion) getObjetoSeleccionado();
		if(obligacion != null){
			if (obligacion.getEstado().equals(Estado.ANULADO)) {
				warn("Las obligaciones ANULADAS no pueden modificarse");
				return null;
			}
			return toAbm(new DocEspSHPSModel().new ModificarController());
		}
		return null;
	}

	public String btnEliminar_action() {
		Obligacion obligacion = (Obligacion) getObjetoSeleccionado();
		if(obligacion != null){
			if (obligacion.getEstado().equals(Estado.ANULADO)) {
				warn("Las obligaciones ANULADAS no pueden volver a anularse");
				return null;
			}
			return toAbm(new DocEspSHPSModel().new EliminarController());
		}
		return null;
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				System.out.println("----RK " + rk);
				if (rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					PlantillaObligacion locPlantillaObligacion = (PlantillaObligacion) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().setLlave(this.getSessionBean1().getLlave());
					// JasperPrint jp = this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().getReporteLibretaSanitaria(locLibretaSanitaria.getIdLibretaSanitaria());
					// FALTA QUE SE PASE DE BIRT
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ObligacionSaludHigieneProfilaxisSeguridad");
					//                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultar_action() {
		return toAbm(new DocEspSHPSModel().new ConsultarController());
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		return this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesSHPS((FiltroObligacionSHPS)pFiltro);
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Obligaciones SHPS";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDocEspSHPS";
	}

	@Override
	public PaginatedTable getPaginatedTable(){
		return this.getCommunicationHabilitacionesBean().getTablaDocEspSHPS();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Persona persona = null;
		PlantillaObligacion plantillaObligacion = null;
		FiltroObligacionSHPS locFiltro = this.getFiltro();
		Integer posicion = (Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1);

		if (pObject instanceof Persona) {
			if (posicion != null && posicion.equals(1)){
				persona = (Persona) pObject;
				locFiltro.setContador(persona);
				this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, null);
			}else{
				persona = (Persona) pObject;
				locFiltro.setPersona(persona);
				this.getSessionBean1().setPersonaSeleccionada(persona);
			}
		}
		if (pObject instanceof PlantillaObligacion) {
			plantillaObligacion = (PlantillaObligacion) pObject;
			this.getElementoPila().getObjetos().set(0, plantillaObligacion);
		}
	}

	public String btnSeleccionarContador_action() {
		return navegarParaSeleccionar("AdminPersonaFisica", 1);
	}

	public String btnSeleccionarPersonaJuridicaContador_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica", 1);
	}

	protected String navegarParaSeleccionar(String pCasoNavegacion, int pPosicionEnPila) {
		String retorno;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(pPosicionEnPila));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = pCasoNavegacion;
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarContador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfContador());
			FiltroObligacionSHPS locFiltro = this.getFiltro();
			locFiltro.setContador(null);
			this.guardarEstadoObjetosUsados();
			// this.ocultarLibretaSanitaria(null);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	public void limpiarObjeto(int posicion, TextField campo) {
		this.getElementoPila().getObjetos().set(posicion, null);
		this.limpiarObjeto(campo);
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoSHPS.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSesion) { // aunque no se usa el ID de subsession
		FiltroObligacionSHPS locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona persona = null;
		
		try {
			persona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setPersona(persona);
		this.getSessionBean1().setPersonaSeleccionada(persona);
	}
	
	public void setContadorAutocompletar(String pId, String pIdSubSesion) { // aunque no se usa el ID de subsession
		FiltroObligacionSHPS locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona contador = null;
		
		try {
			contador = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setContador(contador);
	}
}