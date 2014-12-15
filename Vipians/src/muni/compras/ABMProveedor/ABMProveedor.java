/*
 * ABMProveedor.java
 *
 * Created on 24 de noviembre de 2006, 14:19
 * Copyright Trascender SRL
 */
package muni.compras.ABMProveedor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMProveedor extends ABMPageBean {

	private StaticText stCodigosCiiu = new StaticText();
	private StaticText stCodigo = new StaticText();
	private StaticText stDescripcionCodigo = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stNombre = new StaticText();
	private StaticText tsDescripcionTipoBien = new StaticText();
	private StaticText stTipoBien = new StaticText();

	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tcCodigo = new TableColumn();
	private TableColumn tcDescripcionCodigo = new TableColumn();

	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcDescripcionTipoBien = new TableColumn();

	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private TableRowGroup tableRowGroup3 = new TableRowGroup();

	private Table table2 = new Table();
	private Table table3 = new Table();

	private PanelGroup groupPanel2 = new PanelGroup();
	private PanelGroup groupPanel3 = new PanelGroup();

	private Button btnAgregar2 = new Button();
	 protected HtmlAjaxCommandButton btnQuitar2 = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodos2 = new HtmlAjaxCommandButton();
	private Button btnSeleccionarTipoBien = new Button();
	private Button btnLimpiarTipoBien = new Button();
	private Button btnAgregar = new Button();
	 protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	 private HtmlAjaxCommandButton btnLimpiarRazonSocial = new HtmlAjaxCommandButton();

	private ObjectListDataProvider ldpCodigosCiiu = new ObjectListDataProvider();
	private ObjectListDataProvider ldpTipoBien = new ObjectListDataProvider();

	private Checkbox checkbox1 = new Checkbox();
	private Checkbox checkbox2 = new Checkbox();

	private TableSelectPhaseListener tablePhaseListener;
	private TableSelectPhaseListener tablePhaseListener2;

	private Label lblTipo = new Label();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	private DropDown ddTipoBien = new DropDown();
	private SingleSelectOptionsList ddTipoBienDefaultOptions = new SingleSelectOptionsList();

	
	public HtmlAjaxCommandButton getBtnLimpiarRazonSocial() {
		return btnLimpiarRazonSocial;
	}

	public void setBtnLimpiarRazonSocial(HtmlAjaxCommandButton btnLimpiarRazonSocial) {
		this.btnLimpiarRazonSocial = btnLimpiarRazonSocial;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public TableSelectPhaseListener getTablePhaseListener2() {
		return tablePhaseListener2;
	}

	public void setTablePhaseListener2(TableSelectPhaseListener tablePhaseListener2) {
		this.tablePhaseListener2 = tablePhaseListener2;
	}

	public StaticText getStTipoBien() {
		return stTipoBien;
	}

	public void setStTipoBien(StaticText stTipoBien) {
		this.stTipoBien = stTipoBien;
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public Button getBtnLimpiarTipoBien() {
		return btnLimpiarTipoBien;
	}

	public void setBtnLimpiarTipoBien(Button btnLimpiarTipoBien) {
		this.btnLimpiarTipoBien = btnLimpiarTipoBien;
	}

	public Button getBtnSeleccionarTipoBien() {
		return btnSeleccionarTipoBien;
	}

	public void setBtnSeleccionarTipoBien(Button btnSeleccionarTipoBien) {
		this.btnSeleccionarTipoBien = btnSeleccionarTipoBien;
	}

	public ObjectListDataProvider getLdpTipoBien() {
		return ldpTipoBien;
	}

	public void setLdpTipoBien(ObjectListDataProvider ldpTipoBien) {
		this.ldpTipoBien = ldpTipoBien;
	}

	public Checkbox getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(Checkbox checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public DropDown getDdTipoBien() {
		return ddTipoBien;
	}

	public void setDdTipoBien(DropDown ddTipoBien) {
		this.ddTipoBien = ddTipoBien;
	}

	public SingleSelectOptionsList getDdTipoBienDefaultOptions() {
		return ddTipoBienDefaultOptions;
	}

	public void setDdTipoBienDefaultOptions(SingleSelectOptionsList ddTipoBienDefaultOptions) {
		this.ddTipoBienDefaultOptions = ddTipoBienDefaultOptions;
	}

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public TableColumn getTcDescripcionTipoBien() {
		return tcDescripcionTipoBien;
	}

	public void setTcDescripcionTipoBien(TableColumn tcDescripcionTipoBien) {
		this.tcDescripcionTipoBien = tcDescripcionTipoBien;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public StaticText getTsDescripcionTipoBien() {
		return tsDescripcionTipoBien;
	}

	public void setTsDescripcionTipoBien(StaticText tsDescripcionTipoBien) {
		this.tsDescripcionTipoBien = tsDescripcionTipoBien;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox checkbox1) {
		this.checkbox1 = checkbox1;
	}

	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup2.getRowKey();
		if (rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	public Object getSelected() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	public void setSelectedTipoBien(Object object) {
		RowKey rowKey = tableRowGroup3.getRowKey();
		if (rowKey != null) {
			tablePhaseListener2.setSelected(rowKey, object);
		}
	}

	public Object getSelectedTipoBien() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return tablePhaseListener2.getSelected(rowKey);
	}

	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public Object getSelectedValueTipoBien() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public Button getBtnAgregar2() {
		return btnAgregar2;
	}

	public void setBtnAgregar2(Button btnAgregar2) {
		this.btnAgregar2 = btnAgregar2;
	}

	public HtmlAjaxCommandButton getBtnQuitar2() {
		return btnQuitar2;
	}

	public void setBtnQuitar2(HtmlAjaxCommandButton btnQuitar2) {
		this.btnQuitar2 = btnQuitar2;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos2() {
		return btnQuitarTodos2;
	}

	public void setBtnQuitarTodos2(HtmlAjaxCommandButton btnQuitarTodos2) {
		this.btnQuitarTodos2 = btnQuitarTodos2;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public ObjectListDataProvider getLdpCodigosCiiu() {
		return ldpCodigosCiiu;
	}

	public void setLdpCodigosCiiu(ObjectListDataProvider ldpCodigosCiiu) {
		this.ldpCodigosCiiu = ldpCodigosCiiu;
	}

	public StaticText getStCodigo() {
		return stCodigo;
	}

	public void setStCodigo(StaticText stCodigo) {
		this.stCodigo = stCodigo;
	}

	public StaticText getStCodigosCiiu() {
		return stCodigosCiiu;
	}

	public void setStCodigosCiiu(StaticText stCodigosCiiu) {
		this.stCodigosCiiu = stCodigosCiiu;
	}

	public StaticText getStDescripcionCodigo() {
		return stDescripcionCodigo;
	}

	public void setStDescripcionCodigo(StaticText stDescripcionCodigo) {
		this.stDescripcionCodigo = stDescripcionCodigo;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTcCodigo() {
		return tcCodigo;
	}

	public void setTcCodigo(TableColumn tcCodigo) {
		this.tcCodigo = tcCodigo;
	}

	public TableColumn getTcDescripcionCodigo() {
		return tcDescripcionCodigo;
	}

	public void setTcDescripcionCodigo(TableColumn tcDescripcionCodigo) {
		this.tcDescripcionCodigo = tcDescripcionCodigo;
	}

	private Button btnSeleccionarDomicilioProveedor = new Button();

	public Button getBtnSeleccionarDomicilioProveedor() {
		return btnSeleccionarDomicilioProveedor;
	}

	public void setBtnSeleccionarDomicilioProveedor(Button btnSeleccionarDomicilioProveedor) {
		this.btnSeleccionarDomicilioProveedor = btnSeleccionarDomicilioProveedor;
	}

	private Label lblProveedorLocal = new Label();

	public Label getLblProveedorLocal() {
		return lblProveedorLocal;
	}

	public void setLblProveedorLocal(Label lblProveedorLocal) {
		this.lblProveedorLocal = lblProveedorLocal;
	}

	private TextField tfProveedorLocal = new TextField();

	public TextField getTfProveedorLocal() {
		return tfProveedorLocal;
	}

	public void setTfProveedorLocal(TextField tf) {
		this.tfProveedorLocal = tf;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	private Label lblDomicilioFiscal = new Label();

	public Label getLblDomicilioFiscal() {
		return lblDomicilioFiscal;
	}

	public void setLblDomicilioFiscal(Label lblDomicilioFiscal) {
		this.lblDomicilioFiscal = lblDomicilioFiscal;
	}

	private Button btnSeleccionarDomicilio = new Button();

	public Button getBtnSeleccionarDomicilio() {
		return btnSeleccionarDomicilio;
	}

	public void setBtnSeleccionarDomicilio(Button b) {
		this.btnSeleccionarDomicilio = b;
	}

	private Label lblContrato = new Label();

	public Label getLblContrato() {
		return lblContrato;
	}

	public void setLblContrato(Label lblContrato) {
		this.lblContrato = lblContrato;
	}

	private TextField tfContacto = new TextField();

	public TextField getTfContacto() {
		return tfContacto;
	}

	public void setTfContacto(TextField tf) {
		this.tfContacto = tf;
	}

	private Label lblTelefono = new Label();

	public Label getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(Label lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	private TextField tfTelefono = new TextField();

	public TextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(TextField tf) {
		this.tfTelefono = tf;
	}

	private Label lblEmail = new Label();

	public Label getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(Label lblEmail) {
		this.lblEmail = lblEmail;
	}

	private TextField tfEmail = new TextField();

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tf) {
		this.tfEmail = tf;
	}

	private Label lblCodigo = new Label();

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private StaticText stSeparador1 = new StaticText();

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	private Object RBSelected = new Object();

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que
		// corresponda
		return this.getCommunicationComprasBean().getTablePhaseListenerCodigoCiiu();
	}

	private TableSelectPhaseListener getTableSelectPhaseListener2() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que
		// corresponda
		return this.getCommunicationComprasBean().getTablePhaseListenerTipoBien();
	}

	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListener.isSelected(rowKey);
	}

	public boolean isCurrentRowSelectedTipoBien() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return tablePhaseListener2.isSelected(rowKey);
	}

	private String currentRow = new String();

	public void setCurrentRow(String s) {
		this.currentRow = s;
	}

	private StaticText stDomicilio = new StaticText();

	public StaticText getStDomicilio() {
		return stDomicilio;
	}

	public void setStDomicilio(StaticText st) {
		this.stDomicilio = st;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Proveedor());
		ep.getObjetos().add(ind++, null); // Persona
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new ArrayList()); // 3 Lista Codigos Ciiu
		ep.getObjetos().add(ind++, new ArrayList()); // 4 lista Tipo Bien
		ep.getObjetos().add(ind++, new ArrayList()); // 5 AtributosDinamicos
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		List codigosCiiu = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		List tipoBien = (List) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);

		proveedor.setContacto(this.getTextFieldValue(getTfContacto()));
		proveedor.setTelefono(this.getTextFieldValue(getTfTelefono()));
		proveedor.setEmail(this.getTextFieldValue(getTfEmail()));
		proveedor.setCodigo(this.getTextFieldValue(getTfCodigo()));
		proveedor.setTipo(this.getDDEnumValue(getDdTipo(), Proveedor.Tipo.class));

		if (domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		if (persona != null && persona.getIdPersona() == -1) {
			persona = null;
		}
		if (!tipoBien.isEmpty()) {
			proveedor.setListaTipoBien(tipoBien);
		}
		if (!codigosCiiu.isEmpty()) {
			proveedor.setListaCodigosCiiu(codigosCiiu);
		}
		proveedor.setDomicilio(domicilio);
		proveedor.setProveedorLocal((Persona) persona);

		this.getObjectListDataProviderCodigosCiiu().commitChanges();
		codigosCiiu = this.getObjectListDataProviderCodigosCiiu().getList();
		proveedor.setListaCodigosCiiu(codigosCiiu);

		this.getObjectListDataProviderTipoBien().commitChanges();
		tipoBien = this.getObjectListDataProviderTipoBien().getList();
		proveedor.setListaTipoBien(tipoBien);

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		proveedor.setListaAtributosDinamicos(atributosDinamicos);
		
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, proveedor);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, codigosCiiu);
		this.getElementoPila().getObjetos().set(ind++, tipoBien);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Proveedor proveedor = null;
		Localidad localidad = null;
		Persona persona = null;
		Domicilio domicilio = null;

		List codigosCiiu = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		List tipoBien = (List) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();

			if (respuesta instanceof Domicilio) {
				domicilio = (Domicilio) respuesta;
				this.getElementoPila().getObjetos().set(2, domicilio);
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}
		
		proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
		
		if (proveedor.getListaAtributosDinamicos() != null) {
			try {
				ArrayList atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(Proveedor.serialVersionUID, proveedor.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(5, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
		domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
		codigosCiiu = (List) this.obtenerObjetoDelElementoPila(3, List.class);
		tipoBien = (List) this.obtenerObjetoDelElementoPila(4, List.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, getNombreBean());
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.getTfContacto().setText(proveedor.getContacto());
		this.getTfTelefono().setText(proveedor.getTelefono());
		this.getTfEmail().setText(proveedor.getEmail());
		this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(proveedor.getTipo())));
		this.getDdTipoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(proveedor.getTipo())));

		if (proveedor != null && proveedor.getCodigo() != null) {
			this.getTfCodigo().setText(proveedor.getCodigo());
		} else {
			this.getTfCodigo().setText("");

		}

		if (persona != null) {
			this.getTfProveedorLocal().setText(persona.toString());
		}
		this.getStDomicilio().setText(domicilio.toString());

		this.getObjectListDataProviderCodigosCiiu().setList(codigosCiiu);
		this.setListaDelCommunicationCodCiiu(codigosCiiu);

		this.getObjectListDataProviderTipoBien().setList(tipoBien);
		this.setListaDelCommunicationTipoBien(tipoBien);
		
		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	protected List getListaDelCommunicationAtributosDinamicos() {
		return this.getCommunicationComprasBean().getListaAtributosDinamicosProveedor();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getCommunicationComprasBean().setListaAtributosDinamicosProveedor(lista);
	}
	
	private ObjectListDataProvider getObjectListDataProviderCodigosCiiu() {
		return this.getLdpCodigosCiiu();
	}

	private ObjectListDataProvider getObjectListDataProviderTipoBien() {
		return this.getLdpTipoBien();
	}

	public List getListaDelCommunicationCodCiiu() {
		return this.getCommunicationComprasBean().getListaCodigosCiiuProveedor();
	}

	public List getListaDelCommunicationTipoBien() {
		return this.getCommunicationComprasBean().getListaTipoBienesProveedor();
	}

	private void setListaDelCommunicationTipoBien(List lista) {
		this.getCommunicationComprasBean().setListaTipoBienesProveedor(lista);
	}

	private void setListaDelCommunicationCodCiiu(List lista) {
		this.getCommunicationComprasBean().setListaCodigosCiiuProveedor(lista);
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public void setRBSelected(Object selected) {
		if (selected != null) {
			lastSelected = selected;
		}
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar la fila recientemente seleccionada">
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public RowKey getCodCiiuSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderCodigosCiiu().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	public String btnAgregarCodigosCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminCodigoCiiu";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarCodigosCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey[] selectedRowKeys = this.getTableRowGroup2().getSelectedRowKeys();
				List locListaSeleccionados = new ArrayList();
				for (int i = 0; i < selectedRowKeys.length; i++) {
					String rowId = selectedRowKeys[i].getRowId();
					RowKey rowKey = this.getObjectListDataProviderCodigosCiiu().getRowKey(rowId);
					Object obj = this.getObjectListDataProviderCodigosCiiu().getObjects()[getNroFila(rowKey.toString())];
					locListaSeleccionados.add(obj);
				}
				this.getListaDelCommunicationCodCiiu().removeAll(locListaSeleccionados);
				tablePhaseListener.clear();

			} catch (Exception e) {
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosCodigosCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationCodCiiu().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica", 1);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica", 1);
	}

	public String btnSeleccionarGrupoProveedor_action() {
		return navegarParaSeleccionar("AdminGrupoProveedor", 1);
	}

	public String btnSeleccionarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if (localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilio);
				retorno = "ModificarDomicilio";
			} else {
				retorno = "AgregarDomicilio";
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarDomicilioProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
			Domicilio domicilio = null;
			if (persona != null) {
				domicilio = persona.getDomicilioPostal();
			}

			this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarTipoBien_action() {
		return navegarParaSeleccionar("AdminTipoBien");
	}

	public String btnLimpiarTipoBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, ddTipoBien);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMProveedor";
	}

	@Override
	protected void _init() {
		
		getTable2().setClearSortButton(true);
		getTable3().setClearSortButton(true);
		
		this.getObjectListDataProviderCodigosCiiu().setList(this.getListaDelCommunicationCodCiiu());
		tablePhaseListener = this.getTableSelectPhaseListener();

		this.getObjectListDataProviderTipoBien().setList(this.getListaDelCommunicationTipoBien());
		tablePhaseListener2 = this.getTableSelectPhaseListener2();

		Option[] opTipo;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Proveedor.Tipo.values(), "may");
		ddTipoDefaultOptions.setOptions(opTipo);

		Set<String> locListaTipoBien = getApplicationBean1().getMapaTipoBien().keySet();

		Option[] opTipoBien = new Option[locListaTipoBien.size() + 1];
		int j = 0;
		opTipoBien[j++] = new Option("", "");
		for (String cadaTipo : locListaTipoBien) {
			opTipoBien[j++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoBienDefaultOptions.setOptions(opTipoBien);
	}

	public String btnAgregar_action() {
		this.guardarEstadoObjetosUsados();

		TipoBien locTipoBien = getApplicationBean1().getMapaTipoBien().get(getDdTipoBien().getSelected().toString());
		agregarTipoBienAProveedor(locTipoBien);

		return "";

	}

	public String btnQuitar_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey[] selectedRowKeys = this.getTableRowGroup3().getSelectedRowKeys();
				List locListaSeleccionados = new ArrayList();
				for (int i = 0; i < selectedRowKeys.length; i++) {
					String rowId = selectedRowKeys[i].getRowId();
					RowKey rowKey = this.getObjectListDataProviderTipoBien().getRowKey(rowId);
					Object obj = this.getObjectListDataProviderTipoBien().getObjects()[getNroFila(rowKey.toString())];
					locListaSeleccionados.add(obj);
				}
				this.getObjectListDataProviderTipoBien().getList().removeAll(locListaSeleccionados);
				this.getListaDelCommunicationTipoBien().removeAll(locListaSeleccionados);

			} catch (Exception e) {
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;

	}

	public String btnQuitarTodos_action() {
		this.guardarEstadoObjetosUsados();
		Proveedor locProveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0);
		locProveedor.setListaTipoBien(new ArrayList<TipoBien>());
		this.getObjectListDataProviderTipoBien().getList().clear();

		return "";
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProviderTipoBien().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public void agregarTipoBienAProveedor(TipoBien tipoBien) {
		System.out.println("EJECUTO EL METODO AGREGARTIPOBIENAPROVEEDOR");
		Proveedor locProveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0);

		if (tipoBien != null) {
			if (locProveedor.getListaTipoBien() == null) {
				locProveedor.setListaTipoBien(new ArrayList<TipoBien>());
			}
			if (!locProveedor.getListaTipoBien().contains(tipoBien)) {
				locProveedor.getListaTipoBien().add((TipoBien) tipoBien);

				this.getObjectListDataProviderTipoBien().setList(locProveedor.getListaTipoBien());
			} else {
				warn("El Tipo Bien seleccionado ya se encuentra en la lista");
			}
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getRequestBean1().setObjetoSeleccion(null);
			
			try{
				if(persona instanceof PersonaJuridica){
					persona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(persona.getIdPersona());
				} else{
					persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(persona.getIdPersona());
				}
			} catch(Exception e){
				e.printStackTrace();
			}

			Proveedor proveedor = (Proveedor) this.getElementoPila().getObjetos().get(0);
			this.getElementoPila().getObjetos().set(0, proveedor);

			Domicilio domicilio = persona.getDomicilioPostal();
			this.getElementoPila().getObjetos().set(1, persona);
			this.getElementoPila().getObjetos().set(2, domicilio);
		}

		if (pObject instanceof CodigoCiiu) {
			List codigosCiiu = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
			if (!((List) this.getElementoPila().getObjetos().get(3)).contains(pObject)) {
				codigosCiiu.add((CodigoCiiu) pObject);
				this.getElementoPila().getObjetos().set(3, codigosCiiu);
			} else {
				warn("El Código Ciiu seleccionado ya se encuentra en la lista");
			}
			this.getRequestBean1().setObjetoSeleccion(null);
		}

		if (pObject instanceof TipoBien) {
			System.out.println("entro al if de instanceof tipoBien");
			agregarTipoBienAProveedor((TipoBien) pObject);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Proveedor proveedor = (Proveedor) pObject;
		Domicilio domicilio = proveedor.getDomicilio();
		Persona persona = proveedor.getProveedorLocal();
		List codigosCiiu = proveedor.getListaCodigosCiiu();
		List tipoBien = proveedor.getListaTipoBien();
		ArrayList atributosDinamicos = null;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, proveedor);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, codigosCiiu);
		this.getElementoPila().getObjetos().set(ind++, tipoBien);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Proveedor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMProveedor$ABMProveedor}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Proveedor locProveedor = this.obtenerObjetoDelElementoPila(0, Proveedor.class);
		this.getTablaLogs().getLdpLogs().setList(locProveedor.getListaLogsAuditoria());
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, locPersona);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(1);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}
	
	public String btnLimpiarRazonSocial_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getTfProveedorLocal());
			Proveedor locProveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0);
			locProveedor.setProveedorLocal(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
}