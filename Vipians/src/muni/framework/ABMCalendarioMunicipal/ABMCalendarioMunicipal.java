/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCalendarioMunicipal;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
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
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMCalendarioMunicipal extends ABMPageBean {

	private TextField tfNombre = new TextField();
	private TextField tfAnio = new TextField();
	private StaticText stNombrePeriodo = new StaticText();
	private StaticText stNumeroPeriodo = new StaticText();
	private StaticText stFechaInicioPeriodo = new StaticText();
	private StaticText stFechaFinPeriodo = new StaticText();
	private Label lblNombre = new Label();
	private Label lblTipoObligacion = new Label();
	private Label lblAnio = new Label();
	private Label lblPlan = new Label();
	private DropDown ddTipoObligacion = new DropDown();
	private DropDown ddPlanes = new DropDown();
	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPlanDefaultOptions = new SingleSelectOptionsList();
	private Table table1 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpPeriodos = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcNombrePeriodo = new TableColumn();
	private TableColumn tcNumeroPeriodo = new TableColumn();
	private TableColumn tcFechaInicioPeriodo = new TableColumn();
	private TableColumn tcFechaFinPeriodo = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stPeriodos = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarPeriodo = new Button();
	private Button btnModificarPeriodo = new Button();
	protected HtmlAjaxCommandButton btnQuitarPeriodo = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosPeriodo = new HtmlAjaxCommandButton();
	private Button btnConsultarPeriodo = new Button();

	// ////////////////////////////////////////////////////////////

	public Button getBtnConsultarPeriodo() {
		return btnConsultarPeriodo;
	}

	public Label getLblPlan() {
		return lblPlan;
	}

	public void setLblPlan(Label lblPlan) {
		this.lblPlan = lblPlan;
	}

	public DropDown getDdPlanes() {
		return ddPlanes;
	}

	public void setDdPlanes(DropDown ddPlanes) {
		this.ddPlanes = ddPlanes;
	}

	public SingleSelectOptionsList getDdPlanDefaultOptions() {
		return ddPlanDefaultOptions;
	}

	public void setDdPlanDefaultOptions(SingleSelectOptionsList ddPlanDefaultOptions) {
		this.ddPlanDefaultOptions = ddPlanDefaultOptions;
	}

	public void setBtnConsultarPeriodo(Button btnConsultarPeriodo) {
		this.btnConsultarPeriodo = btnConsultarPeriodo;
	}

	public Button getBtnModificarPeriodo() {
		return btnModificarPeriodo;
	}

	public void setBtnModificarPeriodo(Button btnModificarPeriodo) {
		this.btnModificarPeriodo = btnModificarPeriodo;
	}

	public StaticText getStFechaFinPeriodo() {
		return stFechaFinPeriodo;
	}

	public void setStFechaFinPeriodo(StaticText stFechaFinPeriodo) {
		this.stFechaFinPeriodo = stFechaFinPeriodo;
	}

	public StaticText getStFechaInicioPeriodo() {
		return stFechaInicioPeriodo;
	}

	public void setStFechaInicioPeriodo(StaticText stFechaInicioPeriodo) {
		this.stFechaInicioPeriodo = stFechaInicioPeriodo;
	}

	public StaticText getStNombrePeriodo() {
		return stNombrePeriodo;
	}

	public void setStNombrePeriodo(StaticText stNombrePeriodo) {
		this.stNombrePeriodo = stNombrePeriodo;
	}

	public StaticText getStNumeroPeriodo() {
		return stNumeroPeriodo;
	}

	public void setStNumeroPeriodo(StaticText stNumeroPeriodo) {
		this.stNumeroPeriodo = stNumeroPeriodo;
	}

	public TableColumn getTcFechaFinPeriodo() {
		return tcFechaFinPeriodo;
	}

	public void setTcFechaFinPeriodo(TableColumn tcFechaFinPeriodo) {
		this.tcFechaFinPeriodo = tcFechaFinPeriodo;
	}

	public TableColumn getTcFechaInicioPeriodo() {
		return tcFechaInicioPeriodo;
	}

	public void setTcFechaInicioPeriodo(TableColumn tcFechaInicioPeriodo) {
		this.tcFechaInicioPeriodo = tcFechaInicioPeriodo;
	}

	public TableColumn getTcNombrePeriodo() {
		return tcNombrePeriodo;
	}

	public void setTcNombrePeriodo(TableColumn tcNombrePeriodo) {
		this.tcNombrePeriodo = tcNombrePeriodo;
	}

	public TableColumn getTcNumeroPeriodo() {
		return tcNumeroPeriodo;
	}

	public void setTcNumeroPeriodo(TableColumn tcNumeroPeriodo) {
		this.tcNumeroPeriodo = tcNumeroPeriodo;
	}

	public Button getBtnAgregarPeriodo() {
		return btnAgregarPeriodo;
	}

	public void setBtnAgregarPeriodo(Button btnAgregarPeriodo) {
		this.btnAgregarPeriodo = btnAgregarPeriodo;
	}

	public HtmlAjaxCommandButton getBtnQuitarPeriodo() {
		return btnQuitarPeriodo;
	}

	public void setBtnQuitarPeriodo(HtmlAjaxCommandButton btnQuitarPeriodo) {
		this.btnQuitarPeriodo = btnQuitarPeriodo;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosPeriodo() {
		return btnQuitarTodosPeriodo;
	}

	public void setBtnQuitarTodosPeriodo(HtmlAjaxCommandButton btnQuitarTodosPeriodo) {
		this.btnQuitarTodosPeriodo = btnQuitarTodosPeriodo;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStPeriodos() {
		return stPeriodos;
	}

	public void setStPeriodos(StaticText stPeriodos) {
		this.stPeriodos = stPeriodos;
	}

	public ObjectListDataProvider getLdpPeriodos() {
		return ldpPeriodos;
	}

	public void setLdpPeriodos(ObjectListDataProvider ldpPeriodos) {
		this.ldpPeriodos = ldpPeriodos;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public Label getLblTipoObligacion() {
		return lblTipoObligacion;
	}

	public void setLblTipoObligacion(Label lblTipoObligacion) {
		this.lblTipoObligacion = lblTipoObligacion;
	}

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoObligacionDefaultOptions = ssol;
	}

	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown dd) {
		this.ddTipoObligacion = dd;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
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

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpPeriodos().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	public ObjectListDataProvider getObjectListDataProviderPeriodos() {
		return this.getLdpPeriodos();
	}

	protected SortedSet<Periodo> getListaDelCommunicationPeriodos() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getComunicationBean().getListaPeriodos();
	}

	protected void setListaDelCommunicationPeriodos(SortedSet<Periodo> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getComunicationBean().setListaPeriodos(lista);
	}

	@Override
	protected void _init() throws Exception {

		if (this.getListaDelCommunicationPeriodos() != null) {
			this.getObjectListDataProviderPeriodos().setList(new ArrayList(this.getListaDelCommunicationPeriodos()));
		}

		armarDropDownTipoObligacion();
		armarDropDownPlan();
	}

	private void armarDropDownTipoObligacion() {
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "");
		ddTipoObligacionDefaultOptions.setOptions(op);
	}

	private void armarDropDownPlan() {
		System.out.println("ejecuto armarDropDownPlan");
		if (this.getCommunicationHabilitacionesBean().getMapaPlan() != null && !this.getCommunicationHabilitacionesBean().getMapaPlan().isEmpty()) {
			Option[] op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaPlan().keySet().toArray(), "");
			ddPlanDefaultOptions.setOptions(op2);
		} else {
			ddPlanDefaultOptions.setOptions(new Option[] {});
		}
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		System.out.println("ejecuto valuechange");
		String toStringTipoObligacion = (String) this.getDdTipoObligacion().getSelected();
		TipoObligacion tipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(toStringTipoObligacion);
		if (tipoObligacion != null) {
			armarMapaPlan(tipoObligacion);
		} else {
			this.getCommunicationHabilitacionesBean().setMapaPlan(null);
			this.armarDropDownPlan();
		}
	}

	public void armarMapaPlan(TipoObligacion tipoObligacion) {
		System.out.println("ejecuto armarMapaPlan");
		FiltroPlan locFiltro = new FiltroPlan();
		locFiltro.setTipoObligacion(tipoObligacion);

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
			List<Plan> locListaPlan = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaPlan(locFiltro).getListaResultados();
			Map<String, Plan> locMapa = new LinkedHashMap<String, Plan>();
			for (Plan cadaPlan : locListaPlan) {
				locMapa.put(cadaPlan.getNombre().toString(), cadaPlan);
			}
			this.getCommunicationHabilitacionesBean().setMapaPlan(locMapa);
			Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(locMapa.keySet().toArray(), "");
			ddPlanDefaultOptions.setOptions(op);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMCalendarioMunicipal() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		ep.getObjetos().add(ind++, new CalendarioMunicipal());
		// periodos
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		CalendarioMunicipal calendarioMunicipal = this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class);

		calendarioMunicipal.setNombre(this.getTextFieldValue(getTfNombre()));
		calendarioMunicipal.setAnio(this.getTextFieldValueInteger(getTfAnio()));
		calendarioMunicipal.setPlan(this.getDDObjectValue(this.getDdPlanes(), getCommunicationHabilitacionesBean().getMapaPlan()));

		this.getObjectListDataProviderPeriodos().commitChanges();

		this.getElementoPila().getObjetos().set(0, calendarioMunicipal);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
		}

		int ind = 0;
		CalendarioMunicipal calendarioMunicipal = this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);

		this.getTfNombre().setText(calendarioMunicipal.getNombre());

		if (calendarioMunicipal.getAnio() != null) {
			this.getTfAnio().setText(calendarioMunicipal.getAnio().toString());
		}

		if (calendarioMunicipal.getPlan() != null) {
			this.getDdTipoObligacion().setSelected(calendarioMunicipal.getPlan().getTipoObligacion().getNombre());
			this.getDdPlanes().setSelected(calendarioMunicipal.getPlan().getNombre());
		}

		this.setListaDelCommunicationPeriodos(new TreeSet(calendarioMunicipal.getListaPeriodos()));
		this.getObjectListDataProviderPeriodos().setList(new ArrayList(calendarioMunicipal.getListaPeriodos()));

	}

	public String btnAgregarPeriodo_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.getObjectListDataProviderPeriodos().commitChanges();
		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(0);

			PeriodoLiquidacion locPeriodo = new PeriodoLiquidacion();
			locPeriodo.setCalendario(calendario);
			locPeriodo.setNombre("");
			locPeriodo.setFechaInicio((Date) null);// [!]
			locPeriodo.setFechaFin((Date) null);// [!]
			locPeriodo.setListaCuotas(null);
			locPeriodo.setNumero(0);

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			// this.getRequestBean1().setObjetoSeleccion();
			this.getRequestBean1().setObjetoABM(locPeriodo);
			this.getRequestBean1().setAbmController(new PeriodoModel().new AgregarPeriodoController());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "ABMPeriodo";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarPeriodo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpPeriodos().getObjects()[index];
					PeriodoLiquidacion periodo = (PeriodoLiquidacion) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class));
					this.getRequestBean1().setObjetoABM(periodo);
					this.getRequestBean1().setAbmController(new PeriodoModel().new ModificarPeriodoController());
					retorno = "ABMPeriodo";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultarPeriodo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpPeriodos().getObjects()[index];
					PeriodoLiquidacion periodo = (PeriodoLiquidacion) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class));
					this.getRequestBean1().setObjetoABM(periodo);
					this.getRequestBean1().setAbmController(new PeriodoModel().new ConsultarPeriodoController());
					retorno = "ABMPeriodo";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarPeriodo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if (ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderPeriodos().getObjects()[index];
					this.getListaDelCommunicationPeriodos().remove(obj);
					this.getObjectListDataProviderPeriodos().setList(new ArrayList(this.getListaDelCommunicationPeriodos()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosPeriodo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationPeriodos().clear();
				this.getElementoPila().getObjetos().set(1, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCalendarioMunicipal";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof TipoObligacion) {
			CalendarioMunicipal calendarioMunicipal = this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class);
			this.getElementoPila().getObjetos().set(0, calendarioMunicipal);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
		if (pObject instanceof PeriodoLiquidacion) {
			PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pObject;
			CalendarioMunicipal calendarioMunicipal = this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class);
			calendarioMunicipal.getListaPeriodos().add(locPeriodo);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		CalendarioMunicipal objetoABM = (CalendarioMunicipal) pObject;

		this.getElementoPila().getObjetos().set(0, objetoABM);
		armarMapaPlan(objetoABM.getPlan().getTipoObligacion());

	}

	@Override
	public long getSerialVersionUID() {
		return CalendarioMunicipal.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		CalendarioMunicipal locCalendario = this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class);
		this.getTablaLogs().getLdpLogs().setList(locCalendario.getListaLogsAuditoria());
	}
}