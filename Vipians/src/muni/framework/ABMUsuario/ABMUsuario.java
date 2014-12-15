/*
 * ABMUsuario.java
 *
 * Created on 10 de octubre de 2006, 08:25
 * Copyright Trascender
 */
package muni.framework.ABMUsuario;

import java.util.ArrayList;
import java.util.HashSet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;
import org.ajax4jsf.renderers.ajax.CommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PasswordField;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.DefaultTableDataProvider;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
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
public class ABMUsuario extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if (this.getListaDelCommunicationArea() != null) {
			this.getObjectListDataProviderArea().setList(this.getListaDelCommunicationArea());
		}
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private PasswordField pfConfirmPassword1 = new PasswordField();

	public PasswordField getPfConfirmPassword1() {
		return pfConfirmPassword1;
	}

	public void setPfConfirmPassword1(PasswordField pfConfirmPassword1) {
		this.pfConfirmPassword1 = pfConfirmPassword1;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();
	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private ArrayList rolesArray = new ArrayList();

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Button btnSeleccionarPersona = new Button();

	public Button getBtnSeleccionarPersona() {
		return btnSeleccionarPersona;
	}

	public void setBtnSeleccionarPersona(Button b) {
		this.btnSeleccionarPersona = b;
	}

	private PasswordField pfPassword1 = new PasswordField();

	public PasswordField getPfPassword1() {
		return pfPassword1;
	}

	public void setPfPassword1(PasswordField pf) {
		this.pfPassword1 = pf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private ObjectListDataProvider ldpRolesUsuario = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRolesUsuario() {
		return ldpRolesUsuario;
	}

	public void setLdpRolesUsuario(ObjectListDataProvider oldp) {
		this.ldpRolesUsuario = oldp;
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

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnAgregar = new Button();

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	private DefaultTableDataProvider defaultTableDataProvider = new DefaultTableDataProvider();

	public DefaultTableDataProvider getDefaultTableDataProvider() {
		return defaultTableDataProvider;
	}

	public void setDefaultTableDataProvider(DefaultTableDataProvider dtdp) {
		this.defaultTableDataProvider = dtdp;
	}
	
	private Label labelArea = new Label();
	private Table table2 = new Table();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private TableColumn tableColumnArea1 = new TableColumn();
	private TableColumn tableColumnArea2 = new TableColumn();
	private TableColumn tableColumnArea3 = new TableColumn();
	private TableColumn tableColumnArea4 = new TableColumn();
	private RadioButton radioButtonArea1 = new RadioButton();
	private StaticText staticTextArea1 = new StaticText();
	private StaticText staticTextArea2 = new StaticText();
	private StaticText staticTextArea3 = new StaticText();
	private StaticText staticTextArea4 = new StaticText();
	private PanelGroup groupPanel2 = new PanelGroup();
	private Button btnAgregarArea = new Button();
	private HtmlAjaxCommandButton btnQuitarArea = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarTodasAreas = new HtmlAjaxCommandButton();
	private ObjectListDataProvider ldpAreasUsuarios = new ObjectListDataProvider();
	private ArrayList areasArray = new ArrayList();
	
	
	
	public Label getLabelArea() {
		return labelArea;
	}

	public void setLabelArea(Label labelArea) {
		this.labelArea = labelArea;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public TableColumn getTableColumnArea1() {
		return tableColumnArea1;
	}

	public void setTableColumnArea1(TableColumn tableColumnArea1) {
		this.tableColumnArea1 = tableColumnArea1;
	}

	public TableColumn getTableColumnArea2() {
		return tableColumnArea2;
	}

	public void setTableColumnArea2(TableColumn tableColumnArea2) {
		this.tableColumnArea2 = tableColumnArea2;
	}

	public TableColumn getTableColumnArea3() {
		return tableColumnArea3;
	}

	public void setTableColumnArea3(TableColumn tableColumnArea3) {
		this.tableColumnArea3 = tableColumnArea3;
	}

	public TableColumn getTableColumnArea4() {
		return tableColumnArea4;
	}

	public void setTableColumnArea4(TableColumn tableColumnArea4) {
		this.tableColumnArea4 = tableColumnArea4;
	}

	public RadioButton getRadioButtonArea1() {
		return radioButtonArea1;
	}

	public void setRadioButtonArea1(RadioButton radioButtonArea1) {
		this.radioButtonArea1 = radioButtonArea1;
	}

	public StaticText getStaticTextArea1() {
		return staticTextArea1;
	}

	public void setStaticTextArea1(StaticText staticTextArea1) {
		this.staticTextArea1 = staticTextArea1;
	}

	public StaticText getStaticTextArea2() {
		return staticTextArea2;
	}

	public void setStaticTextArea2(StaticText staticTextArea2) {
		this.staticTextArea2 = staticTextArea2;
	}

	public StaticText getStaticTextArea3() {
		return staticTextArea3;
	}

	public void setStaticTextArea3(StaticText staticTextArea3) {
		this.staticTextArea3 = staticTextArea3;
	}

	public StaticText getStaticTextArea4() {
		return staticTextArea4;
	}

	public void setStaticTextArea4(StaticText staticTextArea4) {
		this.staticTextArea4 = staticTextArea4;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public Button getBtnAgregarArea() {
		return btnAgregarArea;
	}

	public void setBtnAgregarArea(Button btnAgregarArea) {
		this.btnAgregarArea = btnAgregarArea;
	}

	public HtmlAjaxCommandButton getBtnQuitarArea() {
		return btnQuitarArea;
	}

	public void setBtnQuitarArea(HtmlAjaxCommandButton btnQuitarArea) {
		this.btnQuitarArea = btnQuitarArea;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodasAreas() {
		return btnQuitarTodasAreas;
	}

	public void setBtnQuitarTodasAreas(HtmlAjaxCommandButton btnQuitarTodasAreas) {
		this.btnQuitarTodasAreas = btnQuitarTodasAreas;
	}

	public ObjectListDataProvider getLdpAreasUsuarios() {
		return ldpAreasUsuarios;
	}

	public void setLdpAreasUsuarios(ObjectListDataProvider ldpAreasUsuarios) {
		this.ldpAreasUsuarios = ldpAreasUsuarios;
	}
	
	private ObjectListDataProvider getObjectListDataProviderArea() {
		return getLdpAreasUsuarios();
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMUsuario() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Usuario());
		ep.getObjetos().add(ind++, new PersonaFisica());
		ep.getObjetos().add(ind++, this.getRolesArray());
		ep.getObjetos().add(ind++, new String());
		ep.getObjetos().add(ind++, this.getAreasArray());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Usuario usuario = (Usuario) this.obtenerObjetoDelElementoPila(0, Usuario.class);
		PersonaFisica p = (PersonaFisica) this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);
		ArrayList roles = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		String confirPass = (String) this.obtenerObjetoDelElementoPila(3, String.class);
		ArrayList areas = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

		usuario.setUser(this.getTextFieldValue(getTfNombre()));
		Object passw1 = this.getPfPassword1().getText();
		if (passw1 != null && passw1 != "")
			usuario.setPassword(passw1.toString());
		else
			usuario.setPassword(null);
		Object confirPass1 = this.getPfConfirmPassword1().getText();
		if (confirPass1 != null && confirPass1 != "")
			confirPass = confirPass1.toString();
		else
			confirPass = null;

		if (p.getIdPersonaFisica() == -1)
			p = null;
		usuario.setPersonaFisica(p);

		this.setListaDelCommunication((ArrayList) this.getObjectListDataProvider().getList());

		roles = (ArrayList) this.getObjectListDataProvider().getList();
		if (roles.size() == 0)
			roles = null;
		if (roles != null)
			usuario.setListaRoles(new HashSet(roles));
		else
			usuario.setListaRoles(null);
		
		this.setListaDelCommunicationArea((ArrayList) this.getLdpAreasUsuarios().getList());
		areas = (ArrayList) this.getLdpAreasUsuarios().getList();
		if (areas.size() == 0)
			areas = null;
		if (areas != null)
			usuario.setListaAreas(areas);
		else
			usuario.setListaAreas(null);

		this.getElementoPila().getObjetos().set(0, usuario);
		this.getElementoPila().getObjetos().set(1, p);
		this.getElementoPila().getObjetos().set(2, roles);
		this.getElementoPila().getObjetos().set(3, confirPass);
		this.getElementoPila().getObjetos().set(4, areas);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Usuario usuario = (Usuario) this.obtenerObjetoDelElementoPila(ind++, Usuario.class);
		PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
		ArrayList roles = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		String confirPass = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
		ArrayList areas = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

		this.getTfNombre().setText(usuario.getUser());
		this.getPfConfirmPassword1().setText(confirPass);
		this.getPfPassword1().setText(usuario.getPassword());
		this.getTfPersona().setText(persona.toString());

		this.getObjectListDataProvider().setList(roles);
		this.setListaDelCommunication(roles);
		
		this.getObjectListDataProviderArea().setList(areas);
		this.setListaDelCommunicationArea(areas);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpRolesUsuario();
	}

	private ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaRolesUsuario();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getComunicationBean().setListaRolesUsuario(lista);
	}
	
	private ArrayList getListaDelCommunicationArea(){
		return (ArrayList) this.getComunicationBean().getListaAreasUsuarios();
	}
	
	private void setListaDelCommunicationArea(ArrayList lista){
		this.getComunicationBean().setListaAreasUsuarios(lista);
	}

	public ArrayList getAreasArray() {
		return areasArray;
	}

	public void setAreasArray(ArrayList areasArray) {
		this.areasArray = areasArray;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRowArea(){
		return tableRowGroup2.getRowKey().getRowId();
	}
	
	public void setCurrentRowArea(int row){
		
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
	
	public Object getRBSelectedArea() {
		String sv = (String) radioButtonArea1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelectedArea(Object selected) {
		if (selected != null) {
			lastSelected = selected;
		}
	}

	public String getPrincipalRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setPrincipalRow(int row) {
	}

	private Object lastPrincipalSelected = null;

	public Object getRBPrincipalSelected() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastPrincipalSelected) ? sv : null;
	}

	public void setRBPrincipalSelected(Object selected) {
		if (selected != null) {
			lastPrincipalSelected = selected;
		}
	}

	public RowKey getPrincipalSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
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
			if (posicionGlobal.longValue() > cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas);
			}
			;

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public void seleccionarFilaPrincipal(Object principal) {
		int cantFilas = this.getObjectListDataProvider().getList().size();
		Rol enTabla = null;
		for (int i = 0; i < cantFilas; i++) {
			enTabla = (Rol) this.getObjectListDataProvider().getObjects()[i];
			if (enTabla.getIdRol() == ((Rol) principal).getIdRol()) {
				lastPrincipalSelected = new Long(i).toString();
			}
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
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public String btnSeleccionarPersona_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnAgregar_action() {
		return navegarParaSeleccionar("AdminRol");
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunication().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public ArrayList getRolesArray() {
		return rolesArray;
	}

	public void setRolesArray(ArrayList rolesArray) {
		this.rolesArray = rolesArray;
	}


	
	@Override
	protected String getCasoNavegacion() {
		return "ABMUsuario";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof PersonaFisica) {
			if (pObject != null) {
				PersonaFisica persona = (PersonaFisica) pObject;
				this.getElementoPila().getObjetos().set(1, persona);
			}
		}
		if (pObject instanceof Rol) {
			if (pObject != null) {
				Rol nuevoRol = (Rol) pObject;
				ArrayList roles = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
				Rol deLaTabla = null;
				boolean esta = false;
				for (int i = 0; i < roles.size(); i++) {
					deLaTabla = (Rol) roles.get(i);
					if (deLaTabla.getIdRol() == nuevoRol.getIdRol()) {
						esta = true;
					}
				}
				if (!esta)
					roles.add(nuevoRol);
				else
					warn("El Rol que intenta agregar ya se encuentra en la lista.");
				this.getElementoPila().getObjetos().set(2, roles);
			}
		}
		
		if(pObject instanceof Area){
			if(pObject != null){
				Area nuevaArea = (Area) pObject;
				ArrayList areas = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
				Area deLaTabla = null;
				boolean esta = false;
				for(int i = 0; i < areas.size(); i++){
					deLaTabla = (Area) areas.get(i);
					if(deLaTabla.getIdArea() == nuevaArea.getIdArea())
						esta = true;
				}
				if(!esta)
					areas.add(nuevaArea);
				else warn("El Area que intenta agrega ya se encuentra en la lista.");
				this.getElementoPila().getObjetos().set(4, areas);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Usuario usuario = (Usuario) this.getRequestBean1().getObjetoABM();
		ArrayList roles = new ArrayList(usuario.getListaRoles());
		PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);
		Long idPersona = new Long(usuario.getIdPersonaFisica());
		ArrayList areas = new ArrayList(usuario.getListaAreas());

		try {
			this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
			persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(idPersona.longValue());
		} catch (Exception ex) {
			log("ModificarUsuario" + "_BuscarPersonaPorIdError:", ex);
			error("Modificar Usuario" + " - No se pudo recuperar la Persona F\355sica vinculada al Usuario: " + ex.getMessage());
		}

		this.setRolesArray(roles);
		this.setAreasArray(areas);

		this.getElementoPila().getObjetos().set(0, usuario);
		this.getElementoPila().getObjetos().set(1, persona);
		this.getElementoPila().getObjetos().set(2, roles);
		this.getElementoPila().getObjetos().set(3, usuario.getPassword());
		this.getElementoPila().getObjetos().set(4, areas);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Usuario.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMUsuario$ABMUsuario}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Usuario locUsuario = this.obtenerObjetoDelElementoPila(0, Usuario.class);
		this.getTablaLogs().getLdpLogs().setList(locUsuario.getListaLogsAuditoria());
	}
	
	public String btnAgregarArea_action(){
		return navegarParaSeleccionar("AdminArea");
	}
	
	public String btnQuitarArea_action(){
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderArea().getObjects()[index];
					this.getListaDelCommunicationArea().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnQuitarTodasAreas_action(){
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.getListaDelCommunicationArea().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
}