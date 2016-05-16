/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.comunes.ABMIngresoVario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMActualizarDeudaIngresoVario extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getLdpIngresosVarios().setList(this.getListaDelCommunication());
		}

		this.getDtcFecha().setPattern("dd/MM/yyyy");
		this.getDtcFecha().setTimeZone(TimeZone.getDefault());
		this.getNcMonto().setPattern("$ #,##0.00");
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTbIngresosVarios(), this.getTbrgIngresosVarios());
	}

	private Label lbFechaVencimiento = new Label();
	private Label lbAplicarIntereses = new Label();
	private Label lbIngresosVarios = new Label();
	private TextField tfFechaVencimiento = new TextField();
	private Checkbox cbAplicarIntereses = new Checkbox();
	private Table tbIngresosVarios = new Table();
	private TableRowGroup tbrgIngresosVarios = new TableRowGroup();
	private ObjectListDataProvider ldpIngresosVarios = new ObjectListDataProvider();
	private MessageGroup messageGroup1 = new MessageGroup();
	private Button btnActualizarDeuda = new Button();
	private DateTimeConverter dtcFecha = new DateTimeConverter();
	private NumberConverter ncMonto = new NumberConverter();
	private Checkbox checkBoxSeleccion = new Checkbox();
	private TableColumn tcCheckBox = new TableColumn();
	private Set listaSeleccionados = new HashSet();

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private StaticText stPersona = new StaticText();
	private StaticText stConcepto = new StaticText();
	private StaticText stMonto = new StaticText();
	private StaticText stVencimiento = new StaticText();
	private StaticText stEstado = new StaticText();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	public StaticText getStConcepto() {
		return stConcepto;
	}

	public void setStConcepto(StaticText stConcepto) {
		this.stConcepto = stConcepto;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStVencimiento() {
		return stVencimiento;
	}

	public void setStVencimiento(StaticText stVencimiento) {
		this.stVencimiento = stVencimiento;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public Set getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

	public Checkbox getCheckBoxSeleccion() {
		return checkBoxSeleccion;
	}

	public void setCheckBoxSeleccion(Checkbox checkBoxSeleccion) {
		this.checkBoxSeleccion = checkBoxSeleccion;
	}

	public TableColumn getTcCheckBox() {
		return tcCheckBox;
	}

	public void setTcCheckBox(TableColumn tcCheckBox) {
		this.tcCheckBox = tcCheckBox;
	}

	public NumberConverter getNcMonto() {
		return ncMonto;
	}

	public void setNcMonto(NumberConverter ncMonto) {
		this.ncMonto = ncMonto;
	}

	public DateTimeConverter getDtcFecha() {
		return dtcFecha;
	}

	public void setDtcFecha(DateTimeConverter dtcFecha) {
		this.dtcFecha = dtcFecha;
	}

	public Button getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public void setBtnActualizarDeuda(Button btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public Label getLbAplicarIntereses() {
		return lbAplicarIntereses;
	}

	public void setLbAplicarIntereses(Label lbAplicarIntereses) {
		this.lbAplicarIntereses = lbAplicarIntereses;
	}

	public Label getlbIngresosVarios() {
		return lbIngresosVarios;
	}

	public void setlbIngresosVarios(Label lbIngresosVarios) {
		this.lbIngresosVarios = lbIngresosVarios;
	}

	public Label getLbFechaVencimiento() {
		return lbFechaVencimiento;
	}

	public void setLbFechaVencimiento(Label lbFechaVencimiento) {
		this.lbFechaVencimiento = lbFechaVencimiento;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public Checkbox getCbAplicarIntereses() {
		return cbAplicarIntereses;
	}

	public void setCbAplicarIntereses(Checkbox cbAplicarIntereses) {
		this.cbAplicarIntereses = cbAplicarIntereses;
	}

	public Label getLbIngresosVarios() {
		return lbIngresosVarios;
	}

	public void setLbIngresosVarios(Label lbIngresosVarios) {
		this.lbIngresosVarios = lbIngresosVarios;
	}

	public Table getTbIngresosVarios() {
		return tbIngresosVarios;
	}

	public void setTbIngresosVarios(Table tbIngresosVarios) {
		this.tbIngresosVarios = tbIngresosVarios;
	}

	public TableRowGroup getTbrgIngresosVarios() {
		return tbrgIngresosVarios;
	}

	public void setTbrgIngresosVarios(TableRowGroup tbrgIngresosVarios) {
		this.tbrgIngresosVarios = tbrgIngresosVarios;
	}

	public ObjectListDataProvider getLdpIngresosVarios() {
		return ldpIngresosVarios;
	}

	public void setLdpIngresosVarios(ObjectListDataProvider ldpIngresosVarios) {
		this.ldpIngresosVarios = ldpIngresosVarios;
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaIngresosVariosActualizarDeuda();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaIngresosVariosActualizarDeuda(lista);
	}

	public String getCurrentRow1() {
		return tbrgIngresosVarios.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ArrayList()); // 0 lista ingresos varios seleccionados
		ep.getObjetos().add(ind++, null); // 1 fecha nuevo vencimiento
		ep.getObjetos().add(ind++, null); // 2 aplicar intereses
		ep.getObjetos().add(ind++, new ArrayList()); // 3 seleccionadas

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		List<IngresoVario> listaIngresosVarios = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		Boolean aplicarIntereses = this.obtenerObjetoDelElementoPila(ind++, Boolean.class);

		fechaVencimiento = Conversor.getFechaCortaDeString(this.getTextFieldValue(this.tfFechaVencimiento));
		aplicarIntereses = this.getCbAplicarIntereses().isChecked();

		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario(listaSeleccionados);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, listaIngresosVarios);
		this.getElementoPila().getObjetos().set(ind++, fechaVencimiento);
		this.getElementoPila().getObjetos().set(ind++, aplicarIntereses);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		List<IngresoVario> listaIngresosVarios = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		Boolean aplicarIntereses = this.obtenerObjetoDelElementoPila(ind++, Boolean.class);

		this.getTfFechaVencimiento().setText(Conversor.getStringDeFechaCorta(fechaVencimiento));
		this.getCbAplicarIntereses().setSelected(aplicarIntereses);
		this.getLdpIngresosVarios().setList(listaIngresosVarios);
	}

	public void setSelected(Object object) {
		Object objectSeleccionado = null;

		if(object == null) {
			objectSeleccionado = this.getLdpIngresosVarios().getObjects()[Integer.parseInt(this.getTbrgIngresosVarios().getRowKey().getRowId())];
			if(objectSeleccionado != null && this.getListaSeleccionados().contains(objectSeleccionado)) {
				this.getListaSeleccionados().remove(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario().remove(objectSeleccionado);
			}
		} else if(this.getLdpIngresosVarios() != null && this.getLdpIngresosVarios().getObjects().length > 0) {
			objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(object.toString()));
			if(objectSeleccionado != null && !this.getListaSeleccionados().contains(objectSeleccionado)) {
				this.getListaSeleccionados().add(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario().add(objectSeleccionado);
			}
		}
	}

	public Object getSelected() {
		String sv = getCheckBoxSeleccion().getSelectedValue().toString();
		Object objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(sv));

		if(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario().contains(objectSeleccionado)) {
			return sv;
		}

		return null;
	}

	public String getSelectedValue() {
		RowKey rowKey = tbrgIngresosVarios.getRowKey();
		if(rowKey != null) {
		}

		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	@SuppressWarnings("unused")
	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getCommunicationSAICBean().getTablePhaseListenerActualizarDeudaIngresoVario();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<IngresoVario> listaIngresosVarios = (List) pObject;
		
		this.setListaDelCommunication(listaIngresosVarios);
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario(new HashSet(listaIngresosVarios));
		this.listaSeleccionados.addAll(listaIngresosVarios);
		this.getLdpIngresosVarios().setList(listaIngresosVarios);
		
		this.getElementoPila().getObjetos().set(0, listaIngresosVarios);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMActualizarDeudaIngresoVario";
	}

	@Override
	public String getNombreBean() {
		return "#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}

}