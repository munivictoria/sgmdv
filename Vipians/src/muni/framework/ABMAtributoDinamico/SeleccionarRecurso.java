/*
 * SeleccionarRecurso.java
 *
 * Created on 23 de octubre de 2006, 13:23
 * Copyright Trascender SRL
 */

package muni.framework.ABMAtributoDinamico;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class SeleccionarRecurso extends AbstractPageBean {

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a campos ocultos.
	private Long idPagina = null;
	private Long idSubSesion = null;
	private final TabSet tabSetPestaniasDinamicas = new TabSet();
	private final FacesContext context = FacesContext.getCurrentInstance();

	private final ELContext elContext = context.getELContext();
	private final ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

	public TabSet getTabSetPestaniasDinamicas() {
		return tabSetPestaniasDinamicas;
	}

	public Long getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
	}

	public Long getIdSubSesion() {
		return idSubSesion;
	}

	public void setIdSubSesion(Long idSubSesion) {
		this.idSubSesion = idSubSesion;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		// String sv = (String) radioButton1.getSelectedValue();
		// return sv.equals(lastSelected) ? sv : null;
		return lastSelected;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	private PanelGroup panelBotonesDinamicos;

	public PanelGroup getPanelBotonesDinamicos() {
		return panelBotonesDinamicos;
	}

	public void setPanelBotonesDinamicos(PanelGroup pPanel) {
		panelBotonesDinamicos = pPanel;
	}

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Seleccionar Recurso";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "SeleccionarRecurso";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */

	private String textoBotonSeleccionado = "Texto Prueba";

	public String getTextoBotonSeleccionado() {
		return textoBotonSeleccionado;
	}

	public void setTextoBotonSeleccionado(String pTexto) {
		textoBotonSeleccionado = pTexto;
	}

	Map<String, List> locMapaRecursos = new HashMap<String, List>();

	private void _init() throws Exception {
		try {
			// BOTONES DINAMICOS
			locMapaRecursos = this.armarListaDeRecursosPorModulo();
			this.crearBotoneraDinamica(locMapaRecursos);
			panelBotonesDinamicos.setStyle("width:705px");

			this.tableColumn2.setHeaderText("Recursos Recursos");
			this.tableColumn2.setStyle("FONT-SIZE:12pt;");

			if(this.getComunicationBean().getListaRecursos() == null) {
				this.setRecursosSegunModulo("Recursos");
			}

			this.getObjectListDataProvider().setList(this.getComunicationBean().getListaRecursos());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setRecursosSegunModulo_action(ActionEvent evento) {
		String pNombreModulo = this.getHidIdBotonera().getValue().toString().trim();
		this.setRecursosSegunModulo(pNombreModulo);
		this.getObjectListDataProvider().setList(this.getComunicationBean().getListaRecursos());
	}

	private void setRecursosSegunModulo(String pValor) {
		this.tableColumn2.setHeaderText("Recursos" + " " + pValor);
		this.getComunicationBean().setListaRecursos((ArrayList) locMapaRecursos.get(this.devolverNombreModulo(pValor)));
	}

	private void setStyleBotonSeleccionado(Button pBoton) {
		pBoton.setStyle("BORDER: rgb(128,128,128) 1px dashed; FONT-SIZE: 11pt;");
	}

	private void crearBotoneraDinamica(Map<String, List> pMapaRecurso) {
		Iterator iterador = pMapaRecurso.keySet().iterator();
		if(panelBotonesDinamicos == null) {
			panelBotonesDinamicos = new PanelGroup();
		}

		for(int i = 0; i < pMapaRecurso.size(); i++) {
			if(i == 5) {
				StaticText espacio = new StaticText();
				String lineas = "";
				for(int j = 0; j < 123; j++) {
					lineas = lineas + " - ";
				}
				espacio.setText(lineas);
				espacio.setStyle("height: 1px; FONT-SIZE: 7pt;");
				panelBotonesDinamicos.getChildren().add(espacio);
			}

			String locLlaveMapa = (String) iterador.next();
			if(!locLlaveMapa.equals("CUE")) {
				Button boton = new Button();
				boton.setStyle("width: 142px");
				boton.setText(setearTextoBoton(locLlaveMapa));
				boton.setId("boton_" + i);

				boton.setOnClick("capturarClickBoton(this)");
				boton.addActionListener(this.getActionListener("#{framework$ABMAtributoDinamico$SeleccionarRecurso.setRecursosSegunModulo_action(event)}"));
				panelBotonesDinamicos.getChildren().add(boton);
			}
		}

	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		Class[] args = new Class[] {};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);

		return listener;
	}

	private Map<String, List> armarListaDeRecursosPorModulo() throws TrascenderFrameworkException, RemoteException {
		List<Grupo> locListaGrupos = new ArrayList<Grupo>(SecurityMgr.getInstance().getListaGrupos());
		for(Grupo cadaGrupo : locListaGrupos) {
			String locInicialModulo = cadaGrupo.getNombre().substring(0, 3);
			if(!locMapaRecursos.containsKey(locInicialModulo)) {
				List<Recurso> locLista = new ArrayList(cadaGrupo.getListaRecursos());
				locMapaRecursos.put(locInicialModulo, locLista);
			} else {
				List recursoYaAsignados = locMapaRecursos.get(locInicialModulo);
				recursoYaAsignados.addAll(cadaGrupo.getListaRecursos());
			}
		}
		for (String cadaLlave : locMapaRecursos.keySet()) {
			List<Recurso> locLista = locMapaRecursos.get(cadaLlave);
			Collections.sort(locLista, new Comparator<Recurso>() {
				@Override
				public int compare(Recurso o1, Recurso o2) {
					return Util.reemplazarAcentos(o1.getNombre()).compareTo(Util.reemplazarAcentos(o2.getNombre()));
				}
			});
		}
		return locMapaRecursos;
	}

	private String devolverNombreModulo(String pTextoBoton) {
		String locRet = "";
		if(pTextoBoton.equals("Recursos")) {
			locRet = "FRM";
			return locRet;
		} else if(pTextoBoton.equals("Catastro")) {
			locRet = "CAT";
			return locRet;
		} else if(pTextoBoton.equals("Habilitaciones")) {
			locRet = "HAB";
			return locRet;
		} else if(pTextoBoton.equals("Compras y Suministros")) {
			locRet = "COM";
			return locRet;
		} else if(pTextoBoton.equals("Depósitos")) {
			locRet = "DEP";
			return locRet;
		} else if(pTextoBoton.equals("SAIC")) {
			locRet = "SAI";
			return locRet;
		} else if(pTextoBoton.equals("Caja")) {
			locRet = "CAJ";
			return locRet;
		} else if(pTextoBoton.equals("Mesa de Entrada")) {
			locRet = "MES";
			return locRet;
		} else if(pTextoBoton.equals("Acción Social")) {
			locRet = "ACC";
			return locRet;
		} else if(pTextoBoton.equals("Gestión Ciudadana")) {
			locRet = "MGC";
			return locRet;
		} else if(pTextoBoton.equals("Expedientes")) {
			locRet = "EXP";
			return locRet;
		}

		return locRet;
	}

	private String setearTextoBoton(String pNombreModulo) {
		String locRet = "";
		if(pNombreModulo.equals("FRM")) {
			locRet = "Recursos";
			return locRet;
		} else if(pNombreModulo.equals("CAT")) {
			locRet = "Catastro";
			return locRet;
		} else if(pNombreModulo.equals("HAB")) {
			locRet = "Habilitaciones";
			return locRet;
		} else if(pNombreModulo.equals("COM")) {
			locRet = "Compras y Suministros";
			return locRet;
		} else if(pNombreModulo.equals("DEP")) {
			locRet = "Depósitos";
			return locRet;
		} else if(pNombreModulo.equals("SAI")) {
			locRet = "SAIC";
			return locRet;
		} else if(pNombreModulo.equals("CAJ")) {
			locRet = "Caja";
			return locRet;
		} else if(pNombreModulo.equals("MES")) {
			locRet = "Mesa de Entrada";
			return locRet;
		} else if(pNombreModulo.equals("ACC")) {
			locRet = "Acción Social";
			return locRet;
		} else if(pNombreModulo.equals("MGC")) {
			locRet = "Gestión Ciudadana";
			return locRet;
		} else if(pNombreModulo.equals("EXP")) {
			locRet = "Expedientes";
		}

		return locRet;
	}

	private Page page1 = new Page();

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page p) {
		this.page1 = p;
	}

	private Html html1 = new Html();

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html h) {
		this.html1 = h;
	}

	private Head head1 = new Head();

	public Head getHead1() {
		return head1;
	}

	public void setHead1(Head h) {
		this.head1 = h;
	}

	private Link link1 = new Link();

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	private Body body1 = new Body();

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body b) {
		this.body1 = b;
	}

	private Form form1 = new Form();

	public Form getForm1() {
		return form1;
	}

	public void setForm1(Form f) {
		this.form1 = f;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	private Button btnGuardar = new Button();

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button b) {
		this.btnGuardar = b;
	}

	private Button btnCancelar = new Button();

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	private StaticText stSeparador = new StaticText();

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText st) {
		this.stSeparador = st;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private HiddenField hidIdPagina = new HiddenField();

	public HiddenField getHidIdPagina() {
		return hidIdPagina;
	}

	public void setHidIdPagina(HiddenField hf) {
		this.hidIdPagina = hf;
	}

	private HiddenField hidIdSubSesion = new HiddenField();

	public HiddenField getHidIdSubSesion() {
		return hidIdSubSesion;
	}

	public void setHidIdSubSesion(HiddenField hf) {
		this.hidIdSubSesion = hf;
	}

	private HiddenField hidIdBotonera = new HiddenField();

	public HiddenField getHidIdBotonera() {
		return hidIdBotonera;
	}

	public void setHidIdBotonera(HiddenField hf) {
		this.hidIdBotonera = hf;
	}

	private Script scriptValidador = new Script();

	public Script getScriptValidador() {
		return scriptValidador;
	}

	public void setScriptValidador(Script scriptValidador) {
		this.scriptValidador = scriptValidador;
	}

	private ObjectListDataProvider ldpRecursos = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRecursos() {
		return ldpRecursos;
	}

	public void setLdpRecursos(ObjectListDataProvider ldpRecursos) {
		this.ldpRecursos = ldpRecursos;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
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

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private Checkbox checkbox1 = new Checkbox();

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private Checkbox checkbox2 = new Checkbox();

	public Checkbox getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(Checkbox c) {
		this.checkbox2 = c;
	}

	private Checkbox checkbox3 = new Checkbox();

	public Checkbox getCheckbox3() {
		return checkbox3;
	}

	public void setCheckbox3(Checkbox c) {
		this.checkbox3 = c;
	}

	private Checkbox checkbox4 = new Checkbox();

	public Checkbox getCheckbox4() {
		return checkbox4;
	}

	public void setCheckbox4(Checkbox c) {
		this.checkbox4 = c;
	}

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tc) {
		this.tableColumn8 = tc;
	}

	private Checkbox checkbox5 = new Checkbox();

	public Checkbox getCheckbox5() {
		return checkbox5;
	}

	public void setCheckbox5(Checkbox c) {
		this.checkbox5 = c;
	}

	private Button btnSeleccionarArea = new Button();

	public Button getBtnSeleccionarArea() {
		return btnSeleccionarArea;
	}

	public void setBtnSeleccionarArea(Button b) {
		this.btnSeleccionarArea = b;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public SeleccionarRecurso() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("SeleccionarRecurso Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	@Override
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */

	@Override
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;
		boolean cargarTabla = false;

		if(this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// 1. Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();

				cargarTabla = true;
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();

			cargarTabla = true;
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if(!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		if(cargarTabla) {
			try {
				this.refrescarTabla();
			} catch(Exception ex) {
				this.limpiarTabla();
			}
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
		// this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Rol());
		ep.getObjetos().add(ind++, new Area());
		ep.getObjetos().add(ind++, new ArrayList());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
//		int ind = 0;
		/*
		 * Rol rol = (Rol) this.obtenerObjetoDelElementoPila(ind++, Rol.class); Area area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		 * ArrayList permisos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		 * 
		 * if (nombre != null && nombre != "") rol.setNombre(nombre.toString()); else rol.setNombre(null); if (firma != null && firma != "") rol.setFirma(
		 * ((Boolean)firma).booleanValue()); else rol.setFirma(false);
		 * 
		 * if (area.getIdArea() == -1) area = null; rol.setArea(area);
		 * 
		 * this.getObjectListDataProvider().commitChanges(); permisos = (ArrayList) this.getObjectListDataProvider().getList();
		 * this.setListaDelCommunication(permisos); rol.setListaPermisos(new HashSet(permisos));
		 * 
		 * ind = 0; this.getElementoPila().getObjetos().set(ind++, rol); this.getElementoPila().getObjetos().set(ind++, area);
		 * this.getElementoPila().getObjetos().set(ind++, permisos);
		 */
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
//		int ind = 0;

		/*
		 * Rol rol = null; Area area = null; ArrayList permisos = null;
		 * 
		 * this.acomodarSeleccionado();
		 * 
		 * if (this.getRequestBean1().getObjetoABM() != null) { rol = (Rol) this.getRequestBean1().getObjetoABM(); area = rol.getArea(); permisos = (ArrayList)
		 * this.getObjectListDataProvider().getList();
		 * 
		 * ind = 0; this.getElementoPila().getObjetos().set(ind++, rol); this.getElementoPila().getObjetos().set(ind++, area);
		 * this.getElementoPila().getObjetos().set(ind++, permisos); }
		 * 
		 * ind = 0; rol = (Rol) this.obtenerObjetoDelElementoPila(ind++, Rol.class); area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		 * permisos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		 * 
		 * this.getTfNombre().setText(rol.getNombre()); this.getTfArea().setText(area.toString()); this.getCbFirma().setValue(new Boolean(rol.isFirma()));
		 * 
		 * this.setListaDelCommunication(permisos); this.getObjectListDataProvider().setList(permisos);
		 */

//		List listaGrupo = new ArrayList();
//		try {
//			this.getComunicationBean().getRemoteSystemUsuario().setLlave(this.getSessionBean1().getLlave());
//			System.out.println("grupos de recursos = " + this.getComunicationBean().getRemoteSystemUsuario().getListaGruposRecursos().size());
//			listaGrupo.addAll(this.getComunicationBean().getRemoteSystemUsuario().getListaGruposRecursos());
//			System.out.println("lista de grupos = " + listaGrupo.size());
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//
//		List listaRecursos = new ArrayList();
//		if(listaGrupo != null && listaGrupo.size() > 0) {
//			for(Iterator it = listaGrupo.iterator(); it.hasNext();) {
//				Object object = it.next();
//				Grupo grupo = (Grupo) object;
//				System.out.println("nombre = " + grupo.getNombre() + ", lista recursos = " + grupo.getListaRecursos().size());
//				listaRecursos.addAll(grupo.getListaRecursos());
//			}
//		}
//		System.out.println("lista de recursos = " + listaRecursos.size());

		// this.setListaDelCommunication((ArrayList) listaRecursos);
		// this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpRecursos();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getComunicationBean().getListaRecursos();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getComunicationBean().setListaRecursos(lista);
	}

	private void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		/*
		 * Rol rol = (Rol) this.obtenerObjetoDelElementoPila(0, Rol.class); if (rol != null && rol.getIdRol() == -1) rol = null;
		 */
		// CAMBIAR: Utilizar el EJBClient adecuado.
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos estaticos de la pagina">
	private void crearElementoPila() {
		ElementoPila ep = new ElementoPila();
		ep.setCasoNavegacion(CASO_NAVEGACION);
		ep.setIdPagina(this.getIdPagina());
		ep.setIdSubSesion(this.getIdSubSesion());
		ep.setNombrePagina(NOMBRE_PAGINA);

		ep = this.agregarObjetosAElementoPila(ep);

		this.getSessionBean1().getMgrPilas().addElemento(ep);
	}

	private String prepararParaVolver(String pAccionRetorno) {
		this.getRequestBean1().setAccion(pAccionRetorno);
		String retorno = null;
		ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());

		if(locElementoAnterior != null) {
			this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());

			this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
			this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
			this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
			retorno = locElementoAnterior.getCasoNavegacion();
		} else {
			retorno = CASO_NAV_POST_CADUCIDAD;
		}

		return retorno;
	}

	private String prepararCaducidad() {
		// redireccionar a pagina con mensaje de caducidad
		this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
		return CASO_NAV_CADUCIDAD;
	}

	private boolean ultimoElementoPilaDeSubSesion() {
		return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
	}

	private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
		Object objeto = null;
		try {
			objeto = this.getElementoPila().getObjetos().get(posicion);
			if(objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch(Exception ex) {
		}
		return objeto;
	}

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if(campo != null) {
				campo.setText(null);
			}
		} catch(Exception ex) {
		}
	}

	// desc="Metodos para seleccionar RaddioButton">

	private int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	// desc="Metodos para seleccionar la fila recientemente seleccionada">
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			;

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnGuardar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoSeleccion(obj);

					this.setRowKeySeleccionado(rk);
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_SeleccionarError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
			}

			if(rk != null) {
				ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
				if(locElementoAnterior == null) {
					return null;
				}
				retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	/*
	 * public String btnGuardar_action() { String retorno = null; boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	 * 
	 * if (ultimo) {
	 * 
	 * try { this.guardarEstadoObjetosUsados();
	 * 
	 * // CAMBIAR: Validar los campos necesarios. Validador v = new Validador(); UIComponent[] noVacios = new UIComponent[1]; String[] nomNoVacios = new
	 * String[1];
	 * 
	 * int pos = 0; noVacios[pos] = this.getTfNombre(); nomNoVacios[pos++] = "Nombre";
	 * 
	 * v.noSonVacios(noVacios, nomNoVacios);
	 * 
	 * if (v.getErrores().size() > 0) { error("Existen Errores:"); for (int i = 0; i < v.getErrores().size(); i++) {
	 * warn(v.getErrores().toArray()[i].toString()); } return null; }
	 * 
	 * Rol rol = (Rol) this.obtenerObjetoDelElementoPila(0, Rol.class);
	 * 
	 * // CAMBIAR: Utilizar el EJBClient adecuado. this.getComunicationBean().getRemoteSystemRol ().setLlave(this.getSessionBean1().getLlave());
	 * this.getComunicationBean().getRemoteSystemRol().addRol(rol);
	 * 
	 * this.getRequestBean1().setRespuestaABM(rol);
	 * 
	 * this.setListaDelCommunication(null);
	 * 
	 * info("El Rol se agreg\363 exitosamente."); retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
	 * 
	 * } catch (Exception ex) { if (ex instanceof TrascenderException) { int codigoError = ((TrascenderException)ex).getCodeTrascenderException(); if
	 * (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext(). getRequestPathInfo(), codigoError)) retorno = null; else retorno =
	 * this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR); } log(CASO_NAVEGACION+"_GuardarError:", ex); error(NOMBRE_PAGINA+" - Guardar: " +
	 * ex.getMessage()); } } else { retorno = this.prepararCaducidad(); } return retorno; }
	 */

	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.setListaDelCommunication(null);

			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
}
