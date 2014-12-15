/*
 * ABMRol.java
 *
 * Created on 23 de octubre de 2006, 13:23
 * Copyright Trascender SRL
 */
package muni.framework.ABMRol;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMRol extends ABMPageBean {

	private final FacesContext context = FacesContext.getCurrentInstance();
	private final ELContext elContext = context.getELContext();
	private final ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

	private PanelGroup panelBotonesDinamicos;

	public PanelGroup getPanelBotonesDinamicos() {
		return panelBotonesDinamicos;
	}

	public void setPanelBotonesDinamicos(PanelGroup pPanel) {
		panelBotonesDinamicos = pPanel;
	}

	private String textoBotonSeleccionado = "Texto Prueba";

	public String getTextoBotonSeleccionado() {
		return textoBotonSeleccionado;
	}

	public void setTextoBotonSeleccionado(String pTexto) {
		textoBotonSeleccionado = pTexto;
	}

	@Override
	protected void _init() throws Exception {
		this.getObjectListDataProvider().setList(getListaDelCommunication());
	}

	private HiddenField hidIdBotonera = new HiddenField();

	public HiddenField getHidIdBotonera() {
		return hidIdBotonera;
	}

	public void setHidIdBotonera(HiddenField hf) {
		this.hidIdBotonera = hf;
	}

	public void setRecursosSegunModulo_action(ActionEvent evento) {
		this.guardarEstadoObjetosUsados();
		String pNombreModulo = this.getHidIdBotonera().getValue().toString().trim();
		this.setRecursosSegunModulo(pNombreModulo);
	}

	private void setRecursosSegunModulo(String pValor) {
		this.tableColumn2.setHeaderText("Módulo " + pValor);
		String encabezado = this.tableColumn2.getHeaderText();
		this.getElementoPila().getObjetos().set(2, encabezado);
		String clave = this.devolverNombreModulo(pValor);
		Map<String, List<Permiso>> locMapaPermisos = this.obtenerObjetoDelElementoPila(1, Map.class);
		this.setListaDelCommunication((ArrayList) locMapaPermisos.get(clave));
		this.getObjectListDataProvider().setList(getListaDelCommunication());
	}

	private void crearBotoneraDinamica(Map<String, List<Permiso>> pMapaPermiso) {
		Iterator iterador = pMapaPermiso.keySet().iterator();
		if (panelBotonesDinamicos == null) {
			panelBotonesDinamicos = new PanelGroup();
		}
		for (int i = 0; i < pMapaPermiso.size(); i++) {
			if (i == 5) {
				StaticText espacio = new StaticText();
				String lineas = "";
				for (int j = 0; j < 130; j++) {
					lineas = lineas + " - ";
				}
				//				espacio.setText(lineas);
				espacio.setStyle("height: 1px; FONT-SIZE: 7pt;");
				panelBotonesDinamicos.getChildren().add(espacio);
			}

			String locLlaveMapa = (String) iterador.next();
			//			if (!locLlaveMapa.equals("CUE")) {
			Button boton = new Button();
			boton.setStyle("width: 148px;margin-bottom: 5px;");
			boton.setText(setearTextoBoton(locLlaveMapa));
			boton.setId("boton_" + i);

			boton.setOnClick("capturarClickBoton(this)");
			boton.addActionListener(this.getActionListener("#{framework$ABMRol$ABMRol.setRecursosSegunModulo_action(event)}"));
			panelBotonesDinamicos.getChildren().add(boton);
			//			}
		}

	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		Class[] args = new Class[] {};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);

		return listener;
	}

	private Map<String, List<Permiso>> armarListaDePermisosPorModulo(Rol rol) throws TrascenderFrameworkException, RemoteException, TrascenderException {
		Map<String, List<Permiso>> locMapaPermisos = new HashMap<String, List<Permiso>>();
		this.getComunicationBean().getRemoteSystemRol().setLlave(this.getSessionBean1().getLlave());
		List<Permiso> listaPermisos = this.getComunicationBean().getRemoteSystemRol().getListaPermisosPorRol(rol);
		List<Grupo> listaGrupos = this.getComunicationBean().getRemoteSystemUsuario().getListaGruposRecursos();

		labelForPermisos: for (Permiso cadaPermiso : listaPermisos) {
			for (Grupo cadaGrupo : listaGrupos) {

				for (Recurso cadaRecurso : cadaGrupo.getListaRecursos()) {
					if (cadaRecurso.getIdRecurso() == cadaPermiso.getIdRecurso()) {
						String locInicialModulo = cadaGrupo.getNombre().substring(0, 3);
						if (!locMapaPermisos.containsKey(locInicialModulo)) {
							List<Permiso> locLista = new ArrayList<Permiso>();
							locLista.add(cadaPermiso);
							locMapaPermisos.put(locInicialModulo, locLista);
						} else {
							List permisosYaAsignados = locMapaPermisos.get(locInicialModulo);
							permisosYaAsignados.add(cadaPermiso);
						}
						continue labelForPermisos;
					}
				}
			}
		}
		for (List cadaLista : locMapaPermisos.values()) {
			Collections.sort(cadaLista);
		}

		return locMapaPermisos;
	}

	private String devolverNombreModulo(String pTextoBoton) {
		String locRet = "";
		if (pTextoBoton.equals("Framework")) {
			locRet = "FRM";
			return locRet;
		} else if (pTextoBoton.equals("Catastro")) {
			locRet = "CAT";
			return locRet;
		} else if (pTextoBoton.equals("Habilitaciones")) {
			locRet = "HAB";
			return locRet;
		} else if (pTextoBoton.equals("Compras y Suministros")) {
			locRet = "COM";
			return locRet;
		} else if (pTextoBoton.equals("Depósitos")) {
			locRet = "DEP";
			return locRet;
		} else if (pTextoBoton.equals("SAIC")) {
			locRet = "SAI";
			return locRet;
		} else if (pTextoBoton.equals("Caja")) {
			locRet = "CAJ";
			return locRet;
		} else if (pTextoBoton.equals("Expedientes")) {
			locRet = "EXP";
			return locRet;
		} else if (pTextoBoton.equals("Acción Social")) {
			locRet = "ACC";
			return locRet;
		} else if (pTextoBoton.equals("Gestión ciudadana")) {
			locRet = "MGC";
			return locRet;
		}else if(pTextoBoton.equals("Contabilidad")){
			locRet = "CUE";
			return locRet;
		}

		return locRet;
	}

	private String setearTextoBoton(String pNombreModulo) {
		String locRet = "";
		if (pNombreModulo.equals("FRM")) {
			locRet = "Framework";
			return locRet;
		} else if (pNombreModulo.equals("CAT")) {
			locRet = "Catastro";
			return locRet;
		} else if (pNombreModulo.equals("HAB")) {
			locRet = "Habilitaciones";
			return locRet;
		} else if (pNombreModulo.equals("COM")) {
			locRet = "Compras y Suministros";
			return locRet;
		} else if (pNombreModulo.equals("DEP")) {
			locRet = "Depósitos";
			return locRet;
		} else if (pNombreModulo.equals("SAI")) {
			locRet = "SAIC";
			return locRet;
		} else if (pNombreModulo.equals("CAJ")) {
			locRet = "Caja";
			return locRet;
		} else if (pNombreModulo.equals("EXP")) {
			locRet = "Expedientes";
			return locRet;
		} else if (pNombreModulo.equals("ACC")) {
			locRet = "Acción Social";
			return locRet;
		} else if (pNombreModulo.equals("MGC")) {
			locRet = "Gestión ciudadana";
			return locRet;
		}else if(pNombreModulo.equals("CUE")){
			locRet = "Contabilidad";
			return locRet;
		}

		return locRet;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label lblNombre = new Label();

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label l) {
		this.lblNombre = l;
	}

	private Checkbox cbTodos = new Checkbox();
	private Checkbox cbBuscar = new Checkbox();
	private Checkbox cbAgregar = new Checkbox();
	private Checkbox cbEditar = new Checkbox();
	private Checkbox cbEliminar = new Checkbox();
	private Checkbox cbAuditar = new Checkbox();

	public Checkbox getCbAuditar() {
		return cbAuditar;
	}

	public void setCbAuditar(Checkbox cbAuditar) {
		this.cbAuditar = cbAuditar;
	}

	public Checkbox getCbAgregar() {
		return cbAgregar;
	}

	public void setCbAgregar(Checkbox cbAgregar) {
		this.cbAgregar = cbAgregar;
	}

	public Checkbox getCbBuscar() {
		return cbBuscar;
	}

	public void setCbBuscar(Checkbox cbBuscar) {
		this.cbBuscar = cbBuscar;
	}

	public Checkbox getCbEditar() {
		return cbEditar;
	}

	public void setCbEditar(Checkbox cbEditar) {
		this.cbEditar = cbEditar;
	}

	public Checkbox getCbEliminar() {
		return cbEliminar;
	}

	public void setCbEliminar(Checkbox cbEliminar) {
		this.cbEliminar = cbEliminar;
	}

	public Checkbox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(Checkbox cbTodos) {
		this.cbTodos = cbTodos;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private ObjectListDataProvider ldpPermisos = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPermisos() {
		return ldpPermisos;
	}

	public void setLdpPermisos(ObjectListDataProvider oldp) {
		this.ldpPermisos = oldp;
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

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tc) {
		this.tableColumn8 = tc;
	}

	private TableColumn tableColumnTodo = new TableColumn();

	public TableColumn getTableColumnTodo() {
		return tableColumnTodo;
	}

	public void setTableColumnTodo(TableColumn tc) {
		this.tableColumnTodo = tc;
	}

	private Checkbox checkbox5 = new Checkbox();

	public Checkbox getCheckbox5() {
		return checkbox5;
	}

	public void setCheckbox5(Checkbox c) {
		this.checkbox5 = c;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if (selected != null) {
			lastSelected = selected;
		}
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMRol() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Rol());
		ep.getObjetos().add(ind++, new HashMap());
		ep.getObjetos().add(ind++, new String());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Rol rol = this.obtenerObjetoDelElementoPila(ind++, Rol.class);
		Map<String, List<Permiso>> locMapaPermiso = this.obtenerObjetoDelElementoPila(ind++, Map.class);

		rol.setNombre(this.getTextFieldValue(getTfNombre()));

		this.getObjectListDataProvider().commitChanges();
		ArrayList<Permiso> permisos = (ArrayList) this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(permisos);
		String locLlave = this.tableColumn2.getHeaderText();
		String nombreModulo = new String(locLlave);
		locLlave = locLlave.substring((locLlave.indexOf(" ") + 1));
		locLlave = this.devolverNombreModulo(locLlave);
		locMapaPermiso.put(locLlave, permisos);

		for (List<Permiso> cadaListaPermiso : locMapaPermiso.values()) {
			for (Permiso cadaPermisoPantalla : cadaListaPermiso) {
				rol.addPermiso(cadaPermisoPantalla);
			}
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, rol);
		this.getElementoPila().getObjetos().set(ind++, locMapaPermiso);
		this.getElementoPila().getObjetos().set(ind++, nombreModulo);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		int ind = 0;
		Rol rol = this.obtenerObjetoDelElementoPila(ind++, Rol.class);
		Map<String, List<Permiso>> locMapaPermiso = this.obtenerObjetoDelElementoPila(ind++, Map.class);
		// La primera se inicializa el mapa.
		if (locMapaPermiso.isEmpty()) {
			try {
				locMapaPermiso = this.armarListaDePermisosPorModulo(rol);
				this.getElementoPila().getObjetos().set(1, locMapaPermiso);
				this.setRecursosSegunModulo("Framework");
				this.getObjectListDataProvider().setList(this.getListaDelCommunication());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.panelBotonesDinamicos = null;
		this.crearBotoneraDinamica(locMapaPermiso);
		panelBotonesDinamicos.setStyle("width:705px");

		this.tableColumn2.setHeaderText(this.obtenerObjetoDelElementoPila(2, String.class).toString());

		this.getTfNombre().setText(rol.getNombre());
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpPermisos();
	}

	private ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaPermisos();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getComunicationBean().setListaPermisos(lista);
	}

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

		if (rk != null) {
			while (!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if (!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if (!encontrado)
					inicioPagina += cantRegistrosPorPagina;
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

		if (cantFilas > 0) {
			// si hay al menos una fila
			if (posicionGlobal.longValue() >= cantFilas) {
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
		} catch (Exception ex) {

		}
		return rk;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRol";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Rol rol = (Rol) pObject;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, rol);
	}

	@Override
	public long getSerialVersionUID() {
		return Rol.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMRol$ABMRol}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Rol locRol = this.obtenerObjetoDelElementoPila(0, Rol.class);
		this.getTablaLogs().getLdpLogs().setList(locRol.getListaLogsAuditoria());
	}
}