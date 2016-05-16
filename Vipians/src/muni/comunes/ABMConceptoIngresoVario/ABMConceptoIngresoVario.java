/*
 * ABMConceptoIngresoVario.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.comunes.ABMConceptoIngresoVario;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.RelaConceptoIngresoVarioCuenta;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMConceptoIngresoVario extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationCuentas() != null) {
			this.getObjectListDataProviderCuentas().setList(this.getListaDelCommunicationCuentas());
		}
		if(this.getListaDelCommunicationRoles() != null) {
			this.getObjectListDataProviderRoles().setList(this.getListaDelCommunicationRoles());
		}
		if(this.getListaDelCommunicationUsuarios() != null) {
			this.getObjectListDataProviderUsuarios().setList(this.getListaDelCommunicationUsuarios());
		}
	}

	private Label lblCuentas = new Label();
	private Label lblRoles = new Label();
	private Label lblUsuarios = new Label();
	private Table tablaCuentas = new Table();
	private Table tablaRoles = new Table();
	private Table tablaUsuarios = new Table();
	private TableRowGroup trgCuentas = new TableRowGroup();
	private TableRowGroup trgRoles = new TableRowGroup();
	private TableRowGroup trgUsuarios = new TableRowGroup();
	private TableColumn tcRbCuentas = new TableColumn();
	private TableColumn tcRbRoles = new TableColumn();
	private TableColumn tcRbUsuarios = new TableColumn();
	private RadioButton rbCuentas = new RadioButton();
	private RadioButton rbRoles = new RadioButton();
	private RadioButton rbUsuarios = new RadioButton();
	private ObjectListDataProvider ldpCuentas = new ObjectListDataProvider();
	private ObjectListDataProvider ldpRoles = new ObjectListDataProvider();
	private ObjectListDataProvider ldpUsuarios = new ObjectListDataProvider();
	private PanelGroup pgCuentas = new PanelGroup();
	private PanelGroup pgRoles = new PanelGroup();
	private PanelGroup pgUsuarios = new PanelGroup();
	private Button btnAgregarCuenta = new Button();
	private HtmlAjaxCommandButton btnQuitarCuenta = new HtmlAjaxCommandButton();
	private Button btnAgregarRol = new Button();
	private HtmlAjaxCommandButton btnQuitarRol = new HtmlAjaxCommandButton();
	private Button btnAgregarUsuario = new Button();
	private HtmlAjaxCommandButton btnQuitarUsuario = new HtmlAjaxCommandButton();
	private TextField tfNombre = new TextField();
	private TableColumn tcCodigoImputacion = new TableColumn();
	private StaticText stCodigoImputacion = new StaticText();
	private TableColumn tcNombreCuenta = new TableColumn();
	private StaticText stNombreCuenta = new StaticText();
	private TableColumn tcNombreRol = new TableColumn();
	private StaticText stNombreRol = new StaticText();
	private TableColumn tcFirma = new TableColumn();
	private StaticText stFirma = new StaticText();
	private TableColumn tcNombreUsuario = new TableColumn();
	private StaticText stNombreUsuario = new StaticText();
	private TableColumn tcPersonaFisica = new TableColumn();
	private StaticText stPersonaFisica = new StaticText();
	private TableColumn tcEstado = new TableColumn();
	private StaticText stEstado = new StaticText();
	private TableColumn tcObligatoria = new TableColumn();
	private Checkbox ckbObligatoria = new Checkbox();
	private TextField tfMontoPorDefecto = new TextField();
	private TableColumn tcMontoPorDefecto = new TableColumn();
	private TextField tfFormulaDeCalculo = new TextField();
	private Button btnSeleccionarFormulaDeCalculo = new Button();
	private HtmlAjaxCommandButton btnLimpiarFormulaDeCalculo = new HtmlAjaxCommandButton();
	
	public Button getBtnSeleccionarFormulaDeCalculo() {
		return btnSeleccionarFormulaDeCalculo;
	}

	public void setBtnSeleccionarFormulaDeCalculo(
			Button btnSeleccionarFormulaDeCalculo) {
		this.btnSeleccionarFormulaDeCalculo = btnSeleccionarFormulaDeCalculo;
	}

	public HtmlAjaxCommandButton getBtnLimpiarFormulaDeCalculo() {
		return btnLimpiarFormulaDeCalculo;
	}

	public void setBtnLimpiarFormulaDeCalculo(
			HtmlAjaxCommandButton btnLimpiarFormulaDeCalculo) {
		this.btnLimpiarFormulaDeCalculo = btnLimpiarFormulaDeCalculo;
	}

	public TextField getTfFormulaDeCalculo() {
		return tfFormulaDeCalculo;
	}

	public void setTfFormulaDeCalculo(TextField tfFormulaDeCalculo) {
		this.tfFormulaDeCalculo = tfFormulaDeCalculo;
	}

	public TextField getTfMontoPorDefecto() {
		return tfMontoPorDefecto;
	}

	public void setTfMontoPorDefecto(TextField tfMontoPorDefecto) {
		this.tfMontoPorDefecto = tfMontoPorDefecto;
	}

	public TableColumn getTcMontoPorDefecto() {
		return tcMontoPorDefecto;
	}

	public void setTcMontoPorDefecto(TableColumn tcMontoPorDefecto) {
		this.tcMontoPorDefecto = tcMontoPorDefecto;
	}

	public TableColumn getTcObligatoria() {
		return tcObligatoria;
	}

	public void setTcObligatoria(TableColumn tcObligatoria) {
		this.tcObligatoria = tcObligatoria;
	}

	public Checkbox getCkbObligatoria() {
		return ckbObligatoria;
	}

	public void setCkbObligatoria(Checkbox ckbObligatoria) {
		this.ckbObligatoria = ckbObligatoria;
	}

	public TableColumn getTcNombreRol() {
		return tcNombreRol;
	}

	public void setTcNombreRol(TableColumn tcNombreRol) {
		this.tcNombreRol = tcNombreRol;
	}

	public StaticText getStNombreRol() {
		return stNombreRol;
	}

	public void setStNombreRol(StaticText stNombreRol) {
		this.stNombreRol = stNombreRol;
	}

	public TableColumn getTcFirma() {
		return tcFirma;
	}

	public void setTcFirma(TableColumn tcFirma) {
		this.tcFirma = tcFirma;
	}

	public StaticText getStFirma() {
		return stFirma;
	}

	public void setStFirma(StaticText stFirma) {
		this.stFirma = stFirma;
	}

	public TableColumn getTcNombreUsuario() {
		return tcNombreUsuario;
	}

	public void setTcNombreUsuario(TableColumn tcNombreUsuario) {
		this.tcNombreUsuario = tcNombreUsuario;
	}

	public StaticText getStNombreUsuario() {
		return stNombreUsuario;
	}

	public void setStNombreUsuario(StaticText stNombreUsuario) {
		this.stNombreUsuario = stNombreUsuario;
	}

	public TableColumn getTcPersonaFisica() {
		return tcPersonaFisica;
	}

	public void setTcPersonaFisica(TableColumn tcPersonaFisica) {
		this.tcPersonaFisica = tcPersonaFisica;
	}

	public StaticText getStPersonaFisica() {
		return stPersonaFisica;
	}

	public void setStPersonaFisica(StaticText stPersonaFisica) {
		this.stPersonaFisica = stPersonaFisica;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public TableColumn getTcCodigoImputacion() {
		return tcCodigoImputacion;
	}

	public void setTcCodigoImputacion(TableColumn tcCodigoImputacion) {
		this.tcCodigoImputacion = tcCodigoImputacion;
	}

	public StaticText getStCodigoImputacion() {
		return stCodigoImputacion;
	}

	public void setStCodigoImputacion(StaticText stCodigoImputacion) {
		this.stCodigoImputacion = stCodigoImputacion;
	}

	public TableColumn getTcNombreCuenta() {
		return tcNombreCuenta;
	}

	public void setTcNombreCuenta(TableColumn tcNombreCuenta) {
		this.tcNombreCuenta = tcNombreCuenta;
	}

	public StaticText getStNombreCuenta() {
		return stNombreCuenta;
	}

	public void setStNombreCuenta(StaticText stNombreCuenta) {
		this.stNombreCuenta = stNombreCuenta;
	}

	public Button getBtnAgregarCuenta() {
		return btnAgregarCuenta;
	}

	public void setBtnAgregarCuenta(Button btnAgregarCuenta) {
		this.btnAgregarCuenta = btnAgregarCuenta;
	}

	public Button getBtnAgregarRol() {
		return btnAgregarRol;
	}

	public void setBtnAgregarRol(Button btnAgregarRol) {
		this.btnAgregarRol = btnAgregarRol;
	}

	public Button getBtnAgregarUsuario() {
		return btnAgregarUsuario;
	}

	public void setBtnAgregarUsuario(Button btnAgregarUsuario) {
		this.btnAgregarUsuario = btnAgregarUsuario;
	}

	public HtmlAjaxCommandButton getBtnQuitarCuenta() {
		return btnQuitarCuenta;
	}

	public void setBtnQuitarCuenta(HtmlAjaxCommandButton btnQuitarCuenta) {
		this.btnQuitarCuenta = btnQuitarCuenta;
	}

	public HtmlAjaxCommandButton getBtnQuitarRol() {
		return btnQuitarRol;
	}

	public void setBtnQuitarRol(HtmlAjaxCommandButton btnQuitarRol) {
		this.btnQuitarRol = btnQuitarRol;
	}

	public HtmlAjaxCommandButton getBtnQuitarUsuario() {
		return btnQuitarUsuario;
	}

	public void setBtnQuitarUsuario(HtmlAjaxCommandButton btnQuitarUsuario) {
		this.btnQuitarUsuario = btnQuitarUsuario;
	}

	public Label getLblCuentas() {
		return lblCuentas;
	}

	public void setLblCuentas(Label lblCuentas) {
		this.lblCuentas = lblCuentas;
	}

	public Label getLblRoles() {
		return lblRoles;
	}

	public void setLblRoles(Label lblRoles) {
		this.lblRoles = lblRoles;
	}

	public Label getLblUsuarios() {
		return lblUsuarios;
	}

	public void setLblUsuarios(Label lblUsuarios) {
		this.lblUsuarios = lblUsuarios;
	}

	public Table getTablaCuentas() {
		return tablaCuentas;
	}

	public void setTablaCuentas(Table tablaCuentas) {
		this.tablaCuentas = tablaCuentas;
	}

	public Table getTablaRoles() {
		return tablaRoles;
	}

	public void setTablaRoles(Table tablaRoles) {
		this.tablaRoles = tablaRoles;
	}

	public Table getTablaUsuarios() {
		return tablaUsuarios;
	}

	public void setTablaUsuarios(Table tablaUsuarios) {
		this.tablaUsuarios = tablaUsuarios;
	}

	public TableRowGroup getTrgCuentas() {
		return trgCuentas;
	}

	public void setTrgCuentas(TableRowGroup trgCuentas) {
		this.trgCuentas = trgCuentas;
	}

	public TableRowGroup getTrgRoles() {
		return trgRoles;
	}

	public void setTrgRoles(TableRowGroup trgRoles) {
		this.trgRoles = trgRoles;
	}

	public TableRowGroup getTrgUsuarios() {
		return trgUsuarios;
	}

	public void setTrgUsuarios(TableRowGroup trgUsuarios) {
		this.trgUsuarios = trgUsuarios;
	}

	public TableColumn getTcRbCuentas() {
		return tcRbCuentas;
	}

	public void setTcRbCuentas(TableColumn tcRbCuentas) {
		this.tcRbCuentas = tcRbCuentas;
	}

	public TableColumn getTcRbRoles() {
		return tcRbRoles;
	}

	public void setTcRbRoles(TableColumn tcRbRoles) {
		this.tcRbRoles = tcRbRoles;
	}

	public TableColumn getTcRbUsuarios() {
		return tcRbUsuarios;
	}

	public void setTcRbUsuarios(TableColumn tcRbUsuarios) {
		this.tcRbUsuarios = tcRbUsuarios;
	}

	public RadioButton getRbCuentas() {
		return rbCuentas;
	}

	public void setRbCuentas(RadioButton rbCuentas) {
		this.rbCuentas = rbCuentas;
	}

	public RadioButton getRbRoles() {
		return rbRoles;
	}

	public void setRbRoles(RadioButton rbRoles) {
		this.rbRoles = rbRoles;
	}

	public RadioButton getRbUsuarios() {
		return rbUsuarios;
	}

	public void setRbUsuarios(RadioButton rbUsuarios) {
		this.rbUsuarios = rbUsuarios;
	}

	public ObjectListDataProvider getLdpCuentas() {
		return ldpCuentas;
	}

	public void setLdpCuentas(ObjectListDataProvider ldpCuentas) {
		this.ldpCuentas = ldpCuentas;
	}

	public ObjectListDataProvider getLdpRoles() {
		return ldpRoles;
	}

	public void setLdpRoles(ObjectListDataProvider ldpRoles) {
		this.ldpRoles = ldpRoles;
	}

	public ObjectListDataProvider getLdpUsuarios() {
		return ldpUsuarios;
	}

	public void setLdpUsuarios(ObjectListDataProvider ldpUsuarios) {
		this.ldpUsuarios = ldpUsuarios;
	}

	public PanelGroup getPgCuentas() {
		return pgCuentas;
	}

	public void setPgCuentas(PanelGroup pgCuentas) {
		this.pgCuentas = pgCuentas;
	}

	public PanelGroup getPgRoles() {
		return pgRoles;
	}

	public void setPgRoles(PanelGroup pgRoles) {
		this.pgRoles = pgRoles;
	}

	public PanelGroup getPgUsuarios() {
		return pgUsuarios;
	}

	public void setPgUsuarios(PanelGroup pgUsuarios) {
		this.pgUsuarios = pgUsuarios;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();

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

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	private Object lastSelectedCuentas = null;

	public Object getRBSelectedCuentas() {
		String sv = (String) rbCuentas.getSelectedValue();
		return sv.equals(lastSelectedCuentas) ? sv : null;
	}

	public void setRBSelectedCuentas(Object selected) {
		if(selected != null) {
			lastSelectedCuentas = selected;
		}
	}

	public String getCurrentRowCuentas() {
		return trgCuentas.getRowKey().getRowId();
	}

	public void setCurrentRowCuentas(int row) {
	}

	public RowKey getSeleccionadoCuentas() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupCuentas");
			rk = this.getLdpCuentas().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private Object lastSelectedRoles = null;

	public Object getRBSelectedRoles() {
		String sv = (String) rbRoles.getSelectedValue();
		return sv.equals(lastSelectedRoles) ? sv : null;
	}

	public void setRBSelectedRoles(Object selected) {
		if(selected != null) {
			lastSelectedRoles = selected;
		}
	}

	public String getCurrentRowRoles() {
		return trgRoles.getRowKey().getRowId();
	}

	public void setCurrentRowRoles(int row) {
	}

	public RowKey getSeleccionadoRoles() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupRoles");
			rk = this.getLdpRoles().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private Object lastSelectedUsuarios = null;

	public Object getRBSelectedUsuarios() {
		String sv = (String) rbUsuarios.getSelectedValue();
		return sv.equals(lastSelectedUsuarios) ? sv : null;
	}

	public void setRBSelectedUsuarios(Object selected) {
		if(selected != null) {
			lastSelectedUsuarios = selected;
		}
	}

	public String getCurrentRowUsuarios() {
		return trgUsuarios.getRowKey().getRowId();
	}

	public void setCurrentRowUsuarios(int row) {
	}

	public RowKey getSeleccionadoUsuarios() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupUsuarios");
			rk = this.getLdpUsuarios().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private List<Cuenta> getListaDelCommunicationCuentas() {
		return this.getCommunicationCajaBean().getListaCuentasConceptoIngresoVario();
	}

	private void setListaDelCommunicationCuentas(List<Cuenta> lista) {
		this.getCommunicationCajaBean().setListaCuentasConceptoIngresoVario(lista);
	}

	private List<Rol> getListaDelCommunicationRoles() {
		return this.getCommunicationCajaBean().getListaRolesConceptoIngresoVario();
	}

	private void setListaDelCommunicationRoles(List<Rol> lista) {
		this.getCommunicationCajaBean().setListaRolesConceptoIngresoVario(lista);
	}

	private List<Usuario> getListaDelCommunicationUsuarios() {
		return this.getCommunicationCajaBean().getListaUsuariosConceptoIngresoVario();
	}

	private void setListaDelCommunicationUsuarios(List<Usuario> lista) {
		this.getCommunicationCajaBean().setListaUsuariosConceptoIngresoVario(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderCuentas() {
		return this.getLdpCuentas();
	}

	private ObjectListDataProvider getObjectListDataProviderRoles() {
		return this.getLdpRoles();
	}

	private ObjectListDataProvider getObjectListDataProviderUsuarios() {
		return this.getLdpUsuarios();
	}

	public ABMConceptoIngresoVario() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ConceptoIngresoVario());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		ConceptoIngresoVario conceptoIngreso = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);

		Object nombre = this.getTfNombre().getText();
		Object descripcion = this.getTaDescripcion().getText();

		if(nombre != null && nombre != "")
			conceptoIngreso.setNombre(nombre.toString());
		else
			conceptoIngreso.setNombre(null);
		if(descripcion != null && descripcion != "")
			conceptoIngreso.setDescripcion(descripcion.toString());
		else
			conceptoIngreso.setDescripcion(null);

		this.getLdpCuentas().commitChanges();

		this.getElementoPila().getObjetos().set(0, conceptoIngreso);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		ConceptoIngresoVario conceptoIngreso = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);

		this.getTfNombre().setText(conceptoIngreso.getNombre());
		this.getTaDescripcion().setText(conceptoIngreso.getDescripcion());
		
		if (conceptoIngreso.getTipoTasa() != null) {
			this.setTextFieldValue(tfFormulaDeCalculo, conceptoIngreso.getTipoTasa().getNombre());
		}

		this.getObjectListDataProviderCuentas().setList(conceptoIngreso.getListaRelaConceptoIngresoVarioCuenta());
		this.setListaDelCommunicationCuentas(this.getObjectListDataProviderCuentas().getList());

		this.getObjectListDataProviderRoles().setList(conceptoIngreso.getListaRoles());
		this.setListaDelCommunicationRoles(this.getObjectListDataProviderRoles().getList());

		this.getObjectListDataProviderUsuarios().setList(conceptoIngreso.getListaUsuarios());
		this.setListaDelCommunicationUsuarios(this.getObjectListDataProviderUsuarios().getList());
	}

	public String btnAgregarCuenta_action() {
		return navegarParaSeleccionar("AdminCuenta");
	}

	public String btnQuitarCuenta_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoCuentas();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderCuentas().getObjects()[index];
					ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);
					locConcepto.getListaRelaConceptoIngresoVarioCuenta().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnSeleccionarFormulaDeCalculo_action() {
		return navegarParaSeleccionar("AdminTipoTasa");
	}
	
	public String btnLimpiarFormulaDeCalculo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfFormulaDeCalculo());

			ConceptoIngresoVario concepto = this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);
			concepto.setTipoTasa(null);

			this.getElementoPila().getObjetos().set(0, concepto);
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarRol_action() {
		return navegarParaSeleccionar("AdminRol");
	}

	public String btnQuitarRol_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoRoles();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderRoles().getObjects()[index];
					ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);
					locConcepto.getListaRoles().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnQuitarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoUsuarios();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderUsuarios().getObjects()[index];
					ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);
					locConcepto.getListaUsuarios().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);

		if(pObject instanceof Cuenta) {
			Cuenta locCuenta = (Cuenta) pObject;

			boolean encontrado = false;
			for(RelaConceptoIngresoVarioCuenta cadaRela : locConcepto.getListaRelaConceptoIngresoVarioCuenta()) {
				if(cadaRela.getCuenta().equals(locCuenta)) {
					encontrado = true;
				}
			}

			if(!encontrado) {
				RelaConceptoIngresoVarioCuenta nuevaRela = new RelaConceptoIngresoVarioCuenta();
				nuevaRela.setConceptoIngresoVario(locConcepto);
				nuevaRela.setCuenta(locCuenta);
				locConcepto.getListaRelaConceptoIngresoVarioCuenta().add(nuevaRela);
			}
		}

		if(pObject instanceof Rol) {
			Rol locRol = (Rol) pObject;
			locConcepto.getListaRoles().add(locRol);
		}else if(pObject instanceof Usuario) {
			Usuario locUsuario = (Usuario) pObject;
			locConcepto.getListaUsuarios().add(locUsuario);
		} else if(pObject instanceof TipoTasa) {
			TipoTasa locTipoTasa = (TipoTasa) pObject;
			locConcepto.setTipoTasa(locTipoTasa);
		}

		this.getElementoPila().getObjetos().set(0, locConcepto);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ConceptoIngresoVario conceptoIngreso = (ConceptoIngresoVario) pObject;
		this.getElementoPila().getObjetos().set(0, conceptoIngreso);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMConceptoIngresoVario";
	}

	@Override
	public String getNombreBean() {
		return "#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConceptoIngresoVario.serialVersionUID;
	}
}