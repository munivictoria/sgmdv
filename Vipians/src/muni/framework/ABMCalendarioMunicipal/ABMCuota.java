/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMCalendarioMunicipal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.convert.DateTimeConverter;

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
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMCuota extends ABMPageBean {

	private Table table1 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpVencimientos = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcFechaVencimiento = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stVencimientos = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarVencimiento = new Button();
	protected HtmlAjaxCommandButton btnQuitarVencimiento = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosVencimiento = new HtmlAjaxCommandButton();
	private MessageGroup messageGroup1 = new MessageGroup();
	private TextField tfNombre = new TextField();
	private TextField tfNumero = new TextField();
	private TextField tfFechaVencimiento = new TextField();
	private Label lblNombre = new Label();
	private Label lblNumero = new Label();
	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public TableColumn getTcFechaVencimiento() {
		return tcFechaVencimiento;
	}

	public void setTcFechaVencimiento(TableColumn tcFechaVencimiento) {
		this.tcFechaVencimiento = tcFechaVencimiento;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public Button getBtnAgregarVencimiento() {
		return btnAgregarVencimiento;
	}

	public void setBtnAgregarVencimiento(Button btnAgregarVencimiento) {
		this.btnAgregarVencimiento = btnAgregarVencimiento;
	}

	public HtmlAjaxCommandButton getBtnQuitarVencimiento() {
		return btnQuitarVencimiento;
	}

	public void setBtnQuitarVencimiento(HtmlAjaxCommandButton btnQuitarVencimiento) {
		this.btnQuitarVencimiento = btnQuitarVencimiento;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosVencimiento() {
		return btnQuitarTodosVencimiento;
	}

	public void setBtnQuitarTodosVencimiento(HtmlAjaxCommandButton btnQuitarTodosVencimiento) {
		this.btnQuitarTodosVencimiento = btnQuitarTodosVencimiento;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public ObjectListDataProvider getLdpVencimientos() {
		return ldpVencimientos;
	}

	public void setLdpVencimientos(ObjectListDataProvider ldpVencimientos) {
		this.ldpVencimientos = ldpVencimientos;
	}

	public StaticText getStVencimientos() {
		return stVencimientos;
	}

	public void setStVencimientos(StaticText stVencimientos) {
		this.stVencimientos = stVencimientos;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
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
			rk = this.getLdpVencimientos().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public ObjectListDataProvider getObjectListDataProviderVencimientos() {
		return this.getLdpVencimientos();
	}

	protected SortedSet<Calendar> getListaDelCommunicationVencimientos() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getComunicationBean().getListaVencimientos();
	}

	protected void setListaDelCommunicationVencimientos(SortedSet<Calendar> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getComunicationBean().setListaVencimientos(lista);
	}

	@Override
	protected void _init() throws Exception {		
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(table1, tableRowGroup1);
		
		if(this.getListaDelCommunicationVencimientos() != null) {
			this.getObjectListDataProviderVencimientos().setList(new ArrayList(this.getListaDelCommunicationVencimientos()));
		}
	}
	
	@Override
	protected void _prerender() throws Exception {		
		this.getObjectListDataProviderVencimientos().commitChanges();
		this.setListaDelCommunicationVencimientos(new TreeSet(this.getObjectListDataProviderVencimientos().getList()));
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(0, CuotaLiquidacion.class);
		SortedSet<Calendar> listaVencimientos = (SortedSet<Calendar>) this.obtenerObjetoDelElementoPila(1, TreeSet.class);

		cuota.setNombre(this.getTextFieldValue(getTfNombre()));
		cuota.setNumero(this.getTextFieldValueInteger(getTfNumero()));

		this.getObjectListDataProviderVencimientos().commitChanges();
		listaVencimientos = new TreeSet(this.getObjectListDataProviderVencimientos().getList());
		this.setListaDelCommunicationVencimientos(listaVencimientos);
		cuota.setListaVencimientos(new ArrayList(listaVencimientos));

		this.getElementoPila().getObjetos().set(0, cuota);
		this.getElementoPila().getObjetos().set(1, listaVencimientos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(0, CuotaLiquidacion.class);
		SortedSet<Calendar> listaVencimientos = (SortedSet<Calendar>) this.obtenerObjetoDelElementoPila(1, TreeSet.class);

		if(cuota.getNombre() != null) {
			this.getTfNombre().setText(cuota.getNombre());
		} else {
			this.getTfNombre().setText(null);
		}

		if(cuota.getNumero() != null && cuota.getNumero() > 0) {
			this.getTfNumero().setText(cuota.getNumero());
		} else {
			this.getTfNumero().setText(null);
		}

		this.setListaDelCommunicationVencimientos(listaVencimientos);
		this.getObjectListDataProviderVencimientos().setList(new ArrayList(listaVencimientos));
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCuota";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new CuotaLiquidacion());
		ep.getObjetos().add(ind++, new TreeSet<Calendar>());// 1 lista vencimientos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	public void btnAgregarVencimiento_action() {
		this.getObjectListDataProviderVencimientos().commitChanges();
		this.guardarEstadoObjetosUsados();
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(0);
		SortedSet<Calendar> listaVencimientos = (SortedSet<Calendar>) this.obtenerObjetoDelElementoPila(1);

		Calendar locVencimiento = new GregorianCalendar();

		listaVencimientos.add(locVencimiento);

		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderVencimientos().getObjects()[index];
					this.getListaDelCommunicationVencimientos().remove(obj);
				}
			} catch(Exception ex) {
			}
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationVencimientos().clear();
				this.getElementoPila().getObjetos().set(1, null);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		CuotaLiquidacion cuota = (CuotaLiquidacion) pObject;
		SortedSet<Calendar> listaVencimientos = new TreeSet(cuota.getListaVencimientos());
		this.getElementoPila().getObjetos().set(0, cuota);
		this.getElementoPila().getObjetos().set(1, listaVencimientos);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMCalendarioMunicipal$ABMCuota}";
	}

	@Override
	public long getSerialVersionUID() {
		return CuotaLiquidacion.serialVersionUID;
	}
}