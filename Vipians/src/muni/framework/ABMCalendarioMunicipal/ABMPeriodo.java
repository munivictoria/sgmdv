/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMCalendarioMunicipal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMPeriodo extends ABMPageBean {

	private Table table1 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpCuotas = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcNumeroCuota = new TableColumn();
	private TableColumn tcFechaInicioCuota = new TableColumn();
	private TableColumn tcFechaFinCuota = new TableColumn();
	private TableColumn tcFechaVencimiento = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stCuotas = new StaticText();
	private StaticText stFechaInicioCuota = new StaticText();
	private StaticText stFechaFinCuota = new StaticText();
	private StaticText stNombre = new StaticText();
	private StaticText stNumeroCuota = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarCuota = new Button();
	private Button btnModificarCuota = new Button();
	protected HtmlAjaxCommandButton btnQuitarCuota = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosCuota = new HtmlAjaxCommandButton();
	private MessageGroup messageGroup1 = new MessageGroup();
	private TextField tfNombrePeriodo = new TextField();
	private TextField tfNumeroPeriodo = new TextField();
	private TextField tfFechaInicioPeriodo = new TextField();
	private TextField tfFechaVencimiento = new TextField();
	private Label lblNombrePeriodo = new Label();
	private Label lblNumeroPeriodo = new Label();
	private Label lblFechaInicioPeriodo = new Label();

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public StaticText getStNumeroCuota() {
		return stNumeroCuota;
	}

	public void setStNumeroCuota(StaticText stNumeroCuota) {
		this.stNumeroCuota = stNumeroCuota;
	}

	public Button getBtnModificarCuota() {
		return btnModificarCuota;
	}

	public void setBtnModificarCuota(Button btnModificarCuota) {
		this.btnModificarCuota = btnModificarCuota;
	}

	public Label getLblFechaInicioPeriodo() {
		return lblFechaInicioPeriodo;
	}

	public TableColumn getTcFechaFinCuota() {
		return tcFechaFinCuota;
	}

	public void setTcFechaFinCuota(TableColumn tcFechaFinCuota) {
		this.tcFechaFinCuota = tcFechaFinCuota;
	}

	public TableColumn getTcFechaInicioCuota() {
		return tcFechaInicioCuota;
	}

	public void setTcFechaInicioCuota(TableColumn tcFechaInicioCuota) {
		this.tcFechaInicioCuota = tcFechaInicioCuota;
	}

	public TableColumn getTcFechaVencimiento() {
		return tcFechaVencimiento;
	}

	public void setTcFechaVencimiento(TableColumn tcFechaVencimiento) {
		this.tcFechaVencimiento = tcFechaVencimiento;
	}

	public TableColumn getTcNumeroCuota() {
		return tcNumeroCuota;
	}

	public void setTcNumeroCuota(TableColumn tcNumeroCuota) {
		this.tcNumeroCuota = tcNumeroCuota;
	}

	public StaticText getStFechaFinCuota() {
		return stFechaFinCuota;
	}

	public void setStFechaFinCuota(StaticText stFechaFinCuota) {
		this.stFechaFinCuota = stFechaFinCuota;
	}

	public StaticText getStFechaInicioCuota() {
		return stFechaInicioCuota;
	}

	public void setStFechaInicioCuota(StaticText stFechaInicioCuota) {
		this.stFechaInicioCuota = stFechaInicioCuota;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public void setLblFechaInicioPeriodo(Label lblFechaInicioPeriodo) {
		this.lblFechaInicioPeriodo = lblFechaInicioPeriodo;
	}

	public Label getLblNumeroPeriodo() {
		return lblNumeroPeriodo;
	}

	public void setLblNumeroPeriodo(Label lblNumeroPeriodo) {
		this.lblNumeroPeriodo = lblNumeroPeriodo;
	}

	public Label getLblNombrePeriodo() {
		return lblNombrePeriodo;
	}

	public void setLblNombrePeriodo(Label lblNombrePeriodo) {
		this.lblNombrePeriodo = lblNombrePeriodo;
	}

	public TextField getTfNombrePeriodo() {
		return tfNombrePeriodo;
	}

	public void setTfNombrePeriodo(TextField tfNombrePeriodo) {
		this.tfNombrePeriodo = tfNombrePeriodo;
	}

	public TextField getTfFechaInicioPeriodo() {
		return tfFechaInicioPeriodo;
	}

	public void setTfFechaInicioPeriodo(TextField tfFechaInicioPeriodo) {
		this.tfFechaInicioPeriodo = tfFechaInicioPeriodo;
	}

	public TextField getTfNumeroPeriodo() {
		return tfNumeroPeriodo;
	}

	public void setTfNumeroPeriodo(TextField tfNumeroPeriodo) {
		this.tfNumeroPeriodo = tfNumeroPeriodo;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public Button getBtnAgregarCuota() {
		return btnAgregarCuota;
	}

	public void setBtnAgregarCuota(Button btnAgregarCuota) {
		this.btnAgregarCuota = btnAgregarCuota;
	}

	public HtmlAjaxCommandButton getBtnQuitarCuota() {
		return btnQuitarCuota;
	}

	public void setBtnQuitarCuota(HtmlAjaxCommandButton btnQuitarCuota) {
		this.btnQuitarCuota = btnQuitarCuota;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosCuota() {
		return btnQuitarTodosCuota;
	}

	public void setBtnQuitarTodosCuota(HtmlAjaxCommandButton btnQuitarTodosCuota) {
		this.btnQuitarTodosCuota = btnQuitarTodosCuota;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public ObjectListDataProvider getLdpCuotas() {
		return ldpCuotas;
	}

	public void setLdpCuotas(ObjectListDataProvider ldpCuotas) {
		this.ldpCuotas = ldpCuotas;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public StaticText getStCuotas() {
		return stCuotas;
	}

	public void setStCuotas(StaticText stCuotas) {
		this.stCuotas = stCuotas;
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

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
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
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpCuotas().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public ObjectListDataProvider getObjectListDataProviderCuotas() {
		return this.getLdpCuotas();
	}

	protected SortedSet<CuotaLiquidacion> getListaDelCommunicationCuotas() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getComunicationBean().getListaCuotas();
	}

	protected void setListaDelCommunicationCuotas(SortedSet<CuotaLiquidacion> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getComunicationBean().setListaCuotas(lista);
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationCuotas() != null) {
			this.getObjectListDataProviderCuotas().setList(new ArrayList(this.getListaDelCommunicationCuotas()));
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		PeriodoLiquidacion periodo = this.obtenerObjetoDelElementoPila(0, PeriodoLiquidacion.class);
		SortedSet<CuotaLiquidacion> listaCuotas = this.obtenerObjetoDelElementoPila(1, TreeSet.class);

		Calendar fechaInicioCalendar = Calendar.getInstance();
		Calendar fechaFinCalendar = Calendar.getInstance();
		periodo.setNombre(this.getTextFieldValue(getTfNombrePeriodo()));
		periodo.setNumero(this.getTextFieldValueInteger(getTfNumeroPeriodo()));

		if(this.getTextFieldValueDate(getTfFechaInicioPeriodo()) != null) {
			fechaInicioCalendar.setTime(this.getTextFieldValueDate(getTfFechaInicioPeriodo()));
			periodo.setFechaInicio(fechaInicioCalendar);
		}

		periodo.setListaCuotas(this.getListaDelCommunicationCuotas());
		this.getObjectListDataProviderCuotas().commitChanges();

		this.getElementoPila().getObjetos().set(0, periodo);
		this.getElementoPila().getObjetos().set(1, listaCuotas);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		PeriodoLiquidacion periodo = this.obtenerObjetoDelElementoPila(0, PeriodoLiquidacion.class);
		SortedSet<CuotaLiquidacion> listaCuotas = this.obtenerObjetoDelElementoPila(1, TreeSet.class);

		if(periodo.getNombre() != null) {
			this.getTfNombrePeriodo().setText(periodo.getNombre());
		} else {
			this.getTfNombrePeriodo().setText(null);
		}

		if(periodo.getNumero() != null && periodo.getNumero() > 0) {
			this.getTfNumeroPeriodo().setText(periodo.getNumero());
		} else {
			this.getTfNumeroPeriodo().setText(null);
		}

		if(periodo.getFechaInicio() != null) {
			this.getTfFechaInicioPeriodo().setText(Conversor.getStringDeFechaCorta(periodo.getFechaInicio().getTime()));
		} else {
			this.getTfFechaInicioPeriodo().setText(null);
		}

		this.setListaDelCommunicationCuotas(listaCuotas);
		this.getObjectListDataProviderCuotas().setList(new ArrayList(listaCuotas));
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPeriodo";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PeriodoLiquidacion());
		ep.getObjetos().add(ind++, new TreeSet<CuotaLiquidacion>());// 1 lista
		// cuotas

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	public String btnAgregarCuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
			this.getRequestBean1().setAbmController(new CuotaModel().new AgregarCuotaController());
			retorno = "ABMCuota";

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarCuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el List DataProvider adecuado.
					Object obj = this.getObjectListDataProviderCuotas().getObjects()[index];

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(obj);
					this.getRequestBean1().setAbmController(new CuotaModel().new ModificarCuotaController());
					retorno = "ABMCuota";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarCuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderCuotas().getObjects()[index];
					this.getListaDelCommunicationCuotas().remove(obj);
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

	public String btnQuitarTodosCuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationCuotas().clear();
				this.getElementoPila().getObjetos().set(1, null);
			} catch(Exception ex) {
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
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof CuotaLiquidacion) {
			PeriodoLiquidacion periodo = this.obtenerObjetoDelElementoPila(0, PeriodoLiquidacion.class);
			SortedSet<CuotaLiquidacion> listaCuotas = this.obtenerObjetoDelElementoPila(1, TreeSet.class);
			CuotaLiquidacion locCuota = (CuotaLiquidacion) pObject;
			locCuota.setPeriodo(periodo);
			// locCuota.setCalendario(periodo.getCalendario());
			listaCuotas.add(locCuota);
			this.getElementoPila().getObjetos().set(1, listaCuotas);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PeriodoLiquidacion periodo = (PeriodoLiquidacion) pObject;
		SortedSet<CuotaLiquidacion> listaCuotas = null;

		if(periodo.getListaCuotas() != null && !periodo.getListaCuotas().isEmpty()) {
			listaCuotas = new TreeSet(periodo.getListaCuotas());
		} else {
			listaCuotas = new TreeSet<CuotaLiquidacion>();
		}

		this.getElementoPila().getObjetos().set(0, periodo);
		this.getElementoPila().getObjetos().set(1, listaCuotas);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMCalendarioMunicipal$ABMPeriodo}";
	}

	@Override
	public long getSerialVersionUID() {
		return PeriodoLiquidacion.serialVersionUID;
	}
}