/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.framework.ABMReporte;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.OpcionParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMReporte extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] opciones = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(Reporte.Tipo.values(), "cap");
		ddTipoDefaultOptions.setOptions(opciones);

		Option[] seleccionaEntidad = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Reporte.SeleccionaEntidad.values(), "cap");
		ddSeleccionaEntidadDefaultOptions.setOptions(seleccionaEntidad);

		if(this.getListaDelCommunicationUsuarios() != null) {
			this.getObjectListDataProviderUsuarios().setList(this.getListaDelCommunicationUsuarios());
		}
		if(this.getListaDelCommunicationParametro() != null) {
			this.getLdpParametros().setList(this.getListaDelCommunicationParametro());
		}
		if(this.getListaDelCommunicationOpcionesParametro() != null) {
			this.getLdpOpciones().setList(getListaDelCommunicationOpcionesParametro());
		}

		Option[] opTipoParametro = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(ParametroReporte.Tipo.values(), "cap");
		ddTipoParametroDefaultOptions.setOptions(opTipoParametro);
	}

	private List<Usuario> getListaDelCommunicationUsuarios() {
		return this.getCommunicationSAICBean().getListaUsuariosReporte();
	}

	private void setListaDelCommunicationUsuarios(List<Usuario> lista) {
		this.getCommunicationSAICBean().setListaUsuariosReporte(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderUsuarios() {
		return this.getLdpUsuarios();
	}

	private List<ParametroReporte> getListaDelCommunicationParametro() {
		return this.getComunicationBean().getListaParametrosReporte();
	}

	private void setListaDelCommunicationParametro(List<ParametroReporte> lista) {
		this.getComunicationBean().setListaParametrosReporte(lista);
	}

	private List<OpcionParametroReporte> getListaDelCommunicationOpcionesParametro() {
		return this.getComunicationBean().getListaOpcionParametroReporte();
	}

	private void setListaDelCommunicationOpcionesParametro(List<OpcionParametroReporte> lista) {
		this.getComunicationBean().setListaOpcionParametroReporte(lista);
	}

	private TextField tfNombre = new TextField();
	private TextField tfNombreAtributo = new TextField();
	private TextField tfOrden = new TextField();
	private TextField tfNombreJasper = new TextField();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();
	private Table tablaUsuarios = new Table();
	private TableRowGroup trgUsuarios = new TableRowGroup();
	private ObjectListDataProvider ldpUsuarios = new ObjectListDataProvider();
	private StaticText stNombreUsuario = new StaticText();
	private ObjectListDataProvider ldpOpciones = new ObjectListDataProvider();
	private Table tablaOpcionesParametro = new Table();
	private TableRowGroup trgOpcionesParametro = new TableRowGroup();
	private RadioButton rbOpcion = new RadioButton();

	private TextField tfRecurso = new TextField();
	private Button btnBuscarRecurso = new Button();
	private HtmlAjaxCommandButton btnLimpiarRecurso = new HtmlAjaxCommandButton();
	private DropDown ddSeleccionaEntidad = new DropDown();
	private SingleSelectOptionsList ddSeleccionaEntidadDefaultOptions = new SingleSelectOptionsList();

	public TextField getTfNombreAtributo() {
		return tfNombreAtributo;
	}

	public void setTfNombreAtributo(TextField tfNombreAtributo) {
		this.tfNombreAtributo = tfNombreAtributo;
	}

	public TextField getTfOrden() {
		return tfOrden;
	}

	public void setTfOrden(TextField tfOrden) {
		this.tfOrden = tfOrden;
	}

	public TableColumn getTableColumnNombreAtributo() {
		return tableColumnNombreAtributo;
	}

	public void setTableColumnNombreAtributo(TableColumn tableColumnNombreAtributo) {
		this.tableColumnNombreAtributo = tableColumnNombreAtributo;
	}

	public TableColumn getTableColumnOrden() {
		return tableColumnOrden;
	}

	public void setTableColumnOrden(TableColumn tableColumnOrden) {
		this.tableColumnOrden = tableColumnOrden;
	}

	public TextField getTfRecurso() {
		return tfRecurso;
	}

	public void setTfRecurso(TextField tfRecurso) {
		this.tfRecurso = tfRecurso;
	}

	public Button getBtnBuscarRecurso() {
		return btnBuscarRecurso;
	}

	public void setBtnBuscarRecurso(Button btnBuscarRecurso) {
		this.btnBuscarRecurso = btnBuscarRecurso;
	}

	public HtmlAjaxCommandButton getBtnLimpiarRecurso() {
		return btnLimpiarRecurso;
	}

	public void setBtnLimpiarRecurso(HtmlAjaxCommandButton btnLimpiarRecurso) {
		this.btnLimpiarRecurso = btnLimpiarRecurso;
	}

	public DropDown getDdSeleccionaEntidad() {
		return ddSeleccionaEntidad;
	}

	public void setDdSeleccionaEntidad(DropDown ddSeleccionaEntidad) {
		this.ddSeleccionaEntidad = ddSeleccionaEntidad;
	}

	public SingleSelectOptionsList getDdSeleccionaEntidadDefaultOptions() {
		return ddSeleccionaEntidadDefaultOptions;
	}

	public void setDdSeleccionaEntidadDefaultOptions(SingleSelectOptionsList ddSeleccionaEntidadDefaultOptions) {
		this.ddSeleccionaEntidadDefaultOptions = ddSeleccionaEntidadDefaultOptions;
	}

	public ObjectListDataProvider getLdpOpciones() {
		return ldpOpciones;
	}

	public void setLdpOpciones(ObjectListDataProvider ldpOpciones) {
		this.ldpOpciones = ldpOpciones;
	}

	public RadioButton getRbOpcion() {
		return rbOpcion;
	}

	public void setRbOpcion(RadioButton rbOpcion) {
		this.rbOpcion = rbOpcion;
	}

	public Table getTablaOpcionesParametro() {
		return tablaOpcionesParametro;
	}

	public void setTablaOpcionesParametro(Table tablaOpcionesParametro) {
		this.tablaOpcionesParametro = tablaOpcionesParametro;
	}

	public TableRowGroup getTrgOpcionesParametro() {
		return trgOpcionesParametro;
	}

	public void setTrgOpcionesParametro(TableRowGroup trgOpcionesParametro) {
		this.trgOpcionesParametro = trgOpcionesParametro;
	}

	public StaticText getStNombreUsuario() {
		return stNombreUsuario;
	}

	public void setStNombreUsuario(StaticText stNombreUsuario) {
		this.stNombreUsuario = stNombreUsuario;
	}

	public TableRowGroup getTrgUsuarios() {
		return trgUsuarios;
	}

	public void setTrgUsuarios(TableRowGroup trgUsuarios) {
		this.trgUsuarios = trgUsuarios;
	}

	public ObjectListDataProvider getLdpUsuarios() {
		return ldpUsuarios;
	}

	public void setLdpUsuarios(ObjectListDataProvider ldpUsuarios) {
		this.ldpUsuarios = ldpUsuarios;
	}

	public Table getTablaUsuarios() {
		return tablaUsuarios;
	}

	public void setTablaUsuarios(Table tablaUsuarios) {
		this.tablaUsuarios = tablaUsuarios;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	public TextField getTfNombreJasper() {
		return tfNombreJasper;
	}

	public void setTfNombreJasper(TextField tfNombreJasper) {
		this.tfNombreJasper = tfNombreJasper;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private TableColumn tcRbUsuarios = new TableColumn();

	public TableColumn getTcRbUsuarios() {
		return tcRbUsuarios;
	}

	public void setTcRbUsuarios(TableColumn tcRbUsuarios) {
		this.tcRbUsuarios = tcRbUsuarios;
	}

	private RadioButton rbUsuarios = new RadioButton();
	private TableColumn tcNombreUsuario = new TableColumn();
	private TextField tfNombreParametro = new TextField();
	private TableColumn tcPersonaFisica = new TableColumn();
	private StaticText stPersonaFisica = new StaticText();
	private TableColumn tcEstado = new TableColumn();
	private StaticText stEstado = new StaticText();
	private PanelGroup pgUsuarios = new PanelGroup();
	private Button btnAgregarUsuario = new Button();
	private HtmlAjaxCommandButton btnQuitarUsuario = new HtmlAjaxCommandButton();

	public PanelGroup getPgUsuarios() {
		return pgUsuarios;
	}

	public void setPgUsuarios(PanelGroup pgUsuarios) {
		this.pgUsuarios = pgUsuarios;
	}

	public Button getBtnAgregarUsuario() {
		return btnAgregarUsuario;
	}

	public void setBtnAgregarUsuario(Button btnAgregarUsuario) {
		this.btnAgregarUsuario = btnAgregarUsuario;
	}

	public HtmlAjaxCommandButton getBtnQuitarUsuario() {
		return btnQuitarUsuario;
	}

	public void setBtnQuitarUsuario(HtmlAjaxCommandButton btnQuitarUsuario) {
		this.btnQuitarUsuario = btnQuitarUsuario;
	}

	public TableColumn getTcNombreUsuario() {
		return tcNombreUsuario;
	}

	public void setTcNombreUsuario(TableColumn tcNombreUsuario) {
		this.tcNombreUsuario = tcNombreUsuario;
	}

	public TextField getTfNombreParametro() {
		return tfNombreParametro;
	}

	public void setTfNombreParametro(TextField tfNombreParametro) {
		this.tfNombreParametro = tfNombreParametro;
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

	public RadioButton getRbUsuarios() {
		return rbUsuarios;
	}

	public void setRbUsuarios(RadioButton rbUsuarios) {
		this.rbUsuarios = rbUsuarios;
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

	// tabla parametros
	private TableRowGroup tableRowGroupParametros = new TableRowGroup();
	private ObjectListDataProvider ldpParametros = new ObjectListDataProvider();
	private TableColumn tableColumnRB = new TableColumn();
	private TableColumn tableColumnNombre = new TableColumn();
	private TableColumn tableColumnNombreAtributo = new TableColumn();
	private TableColumn tableColumnOrden = new TableColumn();
	private RadioButton radioButtonParametro = new RadioButton();
	private Object lastSelectedParametro = null;
	private Object lastSelectedOpcion = null;
	private StaticText staticTextNombre = new StaticText();
	private PanelGroup groupPanelParametro = new PanelGroup();
	private Button btnAgregarParametro = new Button();
	private HtmlAjaxCommandButton btnQuitarParametro = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarTodosParametro = new HtmlAjaxCommandButton();
	private StaticText staticText4 = new StaticText();
	private Table tableParametros = new Table();
	private Label lblParametros = new Label();
	private TableColumn tableColumnTipoParametro = new TableColumn();
	private DropDown ddTipoParametro = new DropDown();
	private SingleSelectOptionsList ddTipoParametroDefaultOptions = new SingleSelectOptionsList();
	private TableColumn tableColumnRequerido = new TableColumn();
	private Checkbox cbRequerido = new Checkbox();
	private TableColumn tableColumnRecurso = new TableColumn();
	private HtmlAjaxCommandButton btnSeleccionarRecurso = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLeerParametros = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnModificarOpciones = new HtmlAjaxCommandButton();
	private TableColumn tableColumnNombreRecurso = new TableColumn();
	private StaticText stNombreRecurso = new StaticText();

	public HtmlAjaxCommandButton getBtnLeerParametros() {
		return btnLeerParametros;
	}

	public void setBtnLeerParametros(HtmlAjaxCommandButton btnLeerParametros) {
		this.btnLeerParametros = btnLeerParametros;
	}

	public HtmlAjaxCommandButton getBtnModificarOpciones() {
		return btnModificarOpciones;
	}

	public void setBtnModificarOpciones(HtmlAjaxCommandButton btnModificarOpciones) {
		this.btnModificarOpciones = btnModificarOpciones;
	}

	public TableColumn getTableColumnNombreRecurso() {
		return tableColumnNombreRecurso;
	}

	public void setTableColumnNombreRecurso(TableColumn tableColumnNombreRecurso) {
		this.tableColumnNombreRecurso = tableColumnNombreRecurso;
	}

	public StaticText getStNombreRecurso() {
		return stNombreRecurso;
	}

	public void setStNombreRecurso(StaticText stNombreRecurso) {
		this.stNombreRecurso = stNombreRecurso;
	}

	public TableColumn getTableColumnRecurso() {
		return tableColumnRecurso;
	}

	public void setTableColumnRecurso(TableColumn tableColumnRecurso) {
		this.tableColumnRecurso = tableColumnRecurso;
	}

	public HtmlAjaxCommandButton getBtnSeleccionarRecurso() {
		return btnSeleccionarRecurso;
	}

	public void setBtnSeleccionarRecurso(HtmlAjaxCommandButton btnSeleccionarRecurso) {
		this.btnSeleccionarRecurso = btnSeleccionarRecurso;
	}

	public TableColumn getTableColumnRequerido() {
		return tableColumnRequerido;
	}

	public void setTableColumnRequerido(TableColumn tableColumnRequerido) {
		this.tableColumnRequerido = tableColumnRequerido;
	}

	public Checkbox getCbRequerido() {
		return cbRequerido;
	}

	public void setCbRequerido(Checkbox cbRequerido) {
		this.cbRequerido = cbRequerido;
	}

	public TableColumn getTableColumnTipoParametro() {
		return tableColumnTipoParametro;
	}

	public void setTableColumnTipoParametro(TableColumn tableColumnTipoParametro) {
		this.tableColumnTipoParametro = tableColumnTipoParametro;
	}

	public DropDown getDdTipoParametro() {
		return ddTipoParametro;
	}

	public void setDdTipoParametro(DropDown ddTipoParametro) {
		this.ddTipoParametro = ddTipoParametro;
	}

	public SingleSelectOptionsList getDdTipoParametroDefaultOptions() {
		return ddTipoParametroDefaultOptions;
	}

	public void setDdTipoParametroDefaultOptions(SingleSelectOptionsList ddTipoParametroDefaultOptions) {
		this.ddTipoParametroDefaultOptions = ddTipoParametroDefaultOptions;
	}

	public Table getTableParametros() {
		return tableParametros;
	}

	public void setTableParametros(Table tableParametros) {
		this.tableParametros = tableParametros;
	}

	public Label getLblParametros() {
		return lblParametros;
	}

	public void setLblParametros(Label lblParametros) {
		this.lblParametros = lblParametros;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public Button getBtnAgregarParametro() {
		return btnAgregarParametro;
	}

	public void setBtnAgregarParametro(Button btnAgregarParametro) {
		this.btnAgregarParametro = btnAgregarParametro;
	}

	public HtmlAjaxCommandButton getBtnQuitarParametro() {
		return btnQuitarParametro;
	}

	public void setBtnQuitarParametro(HtmlAjaxCommandButton btnQuitarParametro) {
		this.btnQuitarParametro = btnQuitarParametro;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosParametro() {
		return btnQuitarTodosParametro;
	}

	public void setBtnQuitarTodosParametro(HtmlAjaxCommandButton btnQuitarTodosParametro) {
		this.btnQuitarTodosParametro = btnQuitarTodosParametro;
	}

	public TableColumn getTableColumnNombre() {
		return tableColumnNombre;
	}

	public void setTableColumnNombre(TableColumn tableColumnNombre) {
		this.tableColumnNombre = tableColumnNombre;
	}

	public Object getLastSelectedUsuarios() {
		return lastSelectedUsuarios;
	}

	public void setLastSelectedUsuarios(Object lastSelectedUsuarios) {
		this.lastSelectedUsuarios = lastSelectedUsuarios;
	}

	public TableRowGroup getTableRowGroupParametros() {
		return tableRowGroupParametros;
	}

	public void setTableRowGroupParametros(TableRowGroup tableRowGroupParametros) {
		this.tableRowGroupParametros = tableRowGroupParametros;
	}

	public ObjectListDataProvider getLdpParametros() {
		return ldpParametros;
	}

	public void setLdpParametros(ObjectListDataProvider ldpParametros) {
		this.ldpParametros = ldpParametros;
	}

	public TableColumn getTableColumnRB() {
		return tableColumnRB;
	}

	public void setTableColumnRB(TableColumn tableColumnRB) {
		this.tableColumnRB = tableColumnRB;
	}

	public RadioButton getRadioButtonParametro() {
		return radioButtonParametro;
	}

	public void setRadioButtonParametro(RadioButton radioButtonParametro) {
		this.radioButtonParametro = radioButtonParametro;
	}

	public Object getLastSelectedOpcion() {
		return lastSelectedOpcion;
	}

	public void setLastSelectedOpcion(Object lastSelectedOpcion) {
		this.lastSelectedOpcion = lastSelectedOpcion;
	}

	public Object getLastSelectedParametro() {
		return lastSelectedParametro;
	}

	public void setLastSelectedParametro(Object lastSelectedParametro) {
		this.lastSelectedParametro = lastSelectedParametro;
	}

	public StaticText getStaticTextNombre() {
		return staticTextNombre;
	}

	public void setStaticTextNombre(StaticText staticTextNombre) {
		this.staticTextNombre = staticTextNombre;
	}

	public PanelGroup getGroupPanelParametro() {
		return groupPanelParametro;
	}

	public void setGroupPanelParametro(PanelGroup groupPanelParametro) {
		this.groupPanelParametro = groupPanelParametro;
	}

	public String getCurrentRowParametros() {
		return tableRowGroupParametros.getRowKey().getRowId();
	}

	public void setCurrentRowParametros(int row) {
	}

	public String getCurrentRowOpcion() {
		return trgOpcionesParametro.getRowKey().getRowId();
	}

	public void setCurrentRowOpcion(int row) {
	}

	public RowKey getSeleccionadoParametro() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpParametros().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	public RowKey getSeleccionadoOpcion() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("grupoOpciones");
			rk = this.getLdpOpciones().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	public Object getRBSelectedParametro() {
		String sv = (String) radioButtonParametro.getSelectedValue();
		return sv.equals(lastSelectedParametro) ? sv : null;
	}

	public void setRBSelectedParametro(Object selected) {
		if(selected != null) {
			lastSelectedParametro = selected;
		}
	}

	public Object getRBSelectedOpcion() {
		String sv = (String) rbOpcion.getSelectedValue();
		return sv.equals(lastSelectedOpcion) ? sv : null;
	}

	public void setRBSelectedOpcion(Object selected) {
		if(selected != null) {
			lastSelectedOpcion = selected;
		}
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMReporte() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Reporte());
		ep.getObjetos().add(ind++, new ParametroReporte());
		ep.getObjetos().add(ind++, new Integer(0));

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Reporte reporte = (Reporte) this.obtenerObjetoDelElementoPila(ind++, Reporte.class);

		reporte.setNombre(this.getTextFieldValue(getTfNombre()));
		reporte.setNombreArchivoJasper(this.getTextFieldValue(tfNombreJasper));
		reporte.setTipo(getDDEnumValue(ddTipo, Reporte.Tipo.class));
		reporte.setSeleccionaEntidad(getDDEnumValue(ddSeleccionaEntidad, Reporte.SeleccionaEntidad.class));

		this.getLdpParametros().commitChanges();
		reporte.setListaParametroReporte(this.getLdpParametros().getList());
		setListaDelCommunicationParametro(reporte.getListaParametroReporte());

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, reporte);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Reporte reporte = (Reporte) this.obtenerObjetoDelElementoPila(ind++, Reporte.class);
		this.setTextFieldValue(tfNombre, reporte.getNombre());
		this.setTextFieldValue(tfNombreJasper, reporte.getNombreArchivoJasper());
		this.setDDEnumValue(ddTipo, reporte.getTipo());
		this.setDDEnumValue(ddSeleccionaEntidad, reporte.getSeleccionaEntidad());

		if(reporte.getIdRecurso() != null && reporte.getIdRecurso() != -1) {
			this.getTfRecurso().setText(reporte.getNombreRecurso());
		}

		this.getObjectListDataProviderUsuarios().setList(reporte.getListaUsuario());
		this.setListaDelCommunicationUsuarios(this.getObjectListDataProviderUsuarios().getList());

		this.getLdpParametros().setList(reporte.getListaParametroReporte());
		this.setListaDelCommunicationParametro(reporte.getListaParametroReporte());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMReporte";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Reporte reporte = (Reporte) this.obtenerObjetoDelElementoPila(0, Reporte.class);

		if(pObject instanceof Usuario) {
			Usuario locUsuario = (Usuario) pObject;
			reporte.getListaUsuario().add(locUsuario);
		}

		if(pObject instanceof Recurso) {
			Recurso locRecurso = (Recurso) pObject;

			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			switch(posicion) {
				case 1: {
					reporte.setIdRecurso(locRecurso.getIdRecurso());

					break;
				}
				case 2: {
					ParametroReporte parametroReporte = (ParametroReporte) this.obtenerObjetoDelElementoPila(1, ParametroReporte.class);
					Integer index = (Integer) this.obtenerObjetoDelElementoPila(2, Integer.class);

					parametroReporte.setIdRecurso(locRecurso.getIdRecurso());
					reporte.getListaParametroReporte().set(index, parametroReporte);

					break;
				}
			}
		}
		this.getElementoPila().getObjetos().set(0, reporte);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Reporte reporte = (Reporte) pObject;
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, reporte);
	}

	@Override
	public long getSerialVersionUID() {
		return Reporte.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMReporte$ABMReporte}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		getApplicationBean1().aplicarPropiedadesTablaAdmin(tableParametros, tableRowGroupParametros);
		getApplicationBean1().aplicarPropiedadesTablaAdmin(tablaUsuarios, trgUsuarios);
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
					Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);
					locReporte.getListaUsuario().remove(obj);
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

	public String btnAgregarParametro_action() {
		Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);

		ParametroReporte locParametro = new ParametroReporte();
		locParametro.setOrden(locReporte.getListaParametroReporte().size() + 1);

		locReporte.getListaParametroReporte().add(locParametro);

		this.getLdpParametros().setList(locReporte.getListaParametroReporte());
		guardarEstadoObjetosUsados();

		return "";
	}

	public String btnQuitarParametro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoParametro();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getLdpParametros().getObjects()[index];
					Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);
					locReporte.getListaParametroReporte().remove(obj);
					setListaDelCommunicationParametro(locReporte.getListaParametroReporte());
					getLdpParametros().setList(getListaDelCommunicationParametro());
					this.getElementoPila().getObjetos().set(0, locReporte);
				}
			} catch(Exception ex) {
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosParametro_action() {
		this.guardarEstadoObjetosUsados();
		Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);
		locReporte.setListaParametroReporte(new ArrayList<ParametroReporte>());
		this.getLdpParametros().getList().clear();

		return "";
	}

	public String btnBuscarRecurso_action() {
		return navegarParaSeleccionar("SeleccionarRecurso", 1);
	}

	public String btnLimpiarRecurso_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfRecurso().setText(null);
			Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);
			locReporte.setIdRecurso(null);
			this.getElementoPila().getObjetos().set(0, locReporte);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarRecurso_action() {
		guardarEstadoObjetosUsados();
		RowKey rk = null;
		rk = this.getSeleccionadoParametro();

		if(rk != null) {
			int index = getNroFila(rk.toString());
			Object obj = this.getLdpParametros().getObjects()[index];
			ParametroReporte parametro = (ParametroReporte) obj;
			this.getElementoPila().getObjetos().set(1, parametro);
			this.getElementoPila().getObjetos().set(2, index);

			if((parametro.getTipo() == null) || !parametro.getTipo().equals(ParametroReporte.Tipo.RECURSO)) {
				warn("Debe seleccionar un parametro del tipo RECURSO.");
			} else {
				return navegarParaSeleccionar("SeleccionarRecurso", 2);
			}
		}
		return null;
	}

	public String btnAgregarOpcion_action() {
		this.getLdpOpciones().commitChanges();
		OpcionParametroReporte locOpcion = new OpcionParametroReporte();
		this.getListaDelCommunicationOpcionesParametro().add(locOpcion);
		this.getLdpOpciones().setList(this.getListaDelCommunicationOpcionesParametro());

		return null;
	}

	public String btnLeerParametros_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			guardarEstadoObjetosUsados();
			String nombreJasper = getTextFieldValue(tfNombreJasper);
			if (nombreJasper != null) {
				String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
				try {
					JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(rutaReportes + nombreJasper));
					Reporte locReporte = this.obtenerObjetoDelElementoPila(0, Reporte.class);
					for (JRParameter cadaParametro : reporte.getParameters()) {
						if (cadaParametro.isSystemDefined()
								|| cadaParametro.getName().equals("P_USUARIO")
								|| cadaParametro.getName().equals("P_ID_USUARIO")) {
							continue;
						}
						ParametroReporte locParametro = locReporte.getParametroPorNombreAtributo(cadaParametro.getName());
						if (locParametro == null) {
							locParametro = new ParametroReporte();
							locParametro.setNombreAtributo(cadaParametro.getName());
							locParametro.setOrden(locReporte.getListaParametroReporte().size() + 1);
							locReporte.getListaParametroReporte().add(locParametro);
						}
						locParametro.setTipo(getTipoSegunClase(cadaParametro.getValueClassName()));
					}
					this.getLdpParametros().setList(locReporte.getListaParametroReporte());
					guardarEstadoObjetosUsados();
				} catch (JRException e) {
					e.printStackTrace();
					error("No se encontro un archivo Jasper con el nombre ingresado.");
					tfNombreJasper.setValid(false);
				}
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	private ParametroReporte.Tipo getTipoSegunClase(String clase) {
		if (clase.equals(Long.class.getName()) || clase.equals(Integer.class.getName()))
			return ParametroReporte.Tipo.NUMÉRICO;
		if (clase.equals(Double.class.getName()) || clase.equals(Float.class.getName()))
			return ParametroReporte.Tipo.DECIMAL;
		if (clase.equals(String.class.getName()))
			return ParametroReporte.Tipo.CADENA;
		if (clase.equals(Date.class.getName()))
			return ParametroReporte.Tipo.FECHA;
		if (clase.equals(Boolean.class.getName()))
			return ParametroReporte.Tipo.BOOLEANO;
		if (clase.contains("trascender"))
			return ParametroReporte.Tipo.RECURSO;
		return null;
	}

	public String btnQuitarOpcion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getLdpOpciones().commitChanges();
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoOpcion();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// Object obj = this.getLdpOpciones().getObjects()[index];
					this.getListaDelCommunicationOpcionesParametro().remove(index);
				}
			} catch(Exception ex) {
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnGuardarOpciones_action() {
		RowKey rk = null;
		rk = this.getSeleccionadoParametro();

		if(rk != null) {
			int index = getNroFila(rk.toString());
			Object obj = this.getLdpParametros().getObjects()[index];
			ParametroReporte parametro = (ParametroReporte) obj;
			this.getLdpOpciones().commitChanges();
			parametro.setListaOpciones(this.getLdpOpciones().getList());
		}

		return null;
	}

	public String btnModificarOpciones_action() {
		guardarEstadoObjetosUsados();
		RowKey rk = null;
		rk = this.getSeleccionadoParametro();

		if(rk != null) {
			int index = getNroFila(rk.toString());
			Object obj = this.getLdpParametros().getObjects()[index];
			ParametroReporte parametro = (ParametroReporte) obj;

			if((parametro.getTipo() == null)
					|| (!parametro.getTipo().equals(ParametroReporte.Tipo.LISTADO_SIMPLE) && !parametro.getTipo().equals(ParametroReporte.Tipo.LISTADO_MULTIPLE))) {
				warn("Debe seleccionar un parametro del tipo LISTADO.");
			} else {
				this.setListaDelCommunicationOpcionesParametro(new ArrayList<OpcionParametroReporte>(parametro.getListaOpciones()));
				this.getLdpOpciones().setList(getListaDelCommunicationOpcionesParametro());
			}
		}

		return null;
	}

}