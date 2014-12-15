/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ImprimirLiquidaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroCancelacionManual;

/**
 * 
 * @author Fer Luca
 */
public class ABMRegistroCancelacionManual extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		this.getDateConverter1().setPattern("dd/MM/yyyy");
		this.getDateConverter1().setTimeZone(TimeZone.getDefault());
		this.getNumberConverter1().setPattern("$ #,##0.00");
	}

	private MessageGroup messageGroup1 = new MessageGroup();
	private Label lblComentario = new Label();
	private StaticText stSeleccionados = new StaticText();
	private TextArea taComentario = new TextArea();
	private Table table1 = new Table();
	private ObjectListDataProvider ldpLiquidaciones = new ObjectListDataProvider();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableColumn tcPeriodo = new TableColumn();
	private TableColumn tcObligacion = new TableColumn();
	private TableColumn tcVencimiento = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private TableColumn tcTipoDeuda = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private StaticText stPeriodo = new StaticText();
	private StaticText stVencimiento = new StaticText();
	private StaticText stMonto = new StaticText();
	private StaticText stTipoDeuda = new StaticText();
	private StaticText stEstado = new StaticText();
	private NumberConverter numberConverter1 = new NumberConverter();
	private DateTimeConverter dateConverter1 = new DateTimeConverter();
	private TextArea taObligacion = new TextArea();

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	public DateTimeConverter getDateConverter1() {
		return dateConverter1;
	}

	public void setDateConverter1(DateTimeConverter dateConverter1) {
		this.dateConverter1 = dateConverter1;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStPeriodo() {
		return stPeriodo;
	}

	public void setStPeriodo(StaticText stPeriodo) {
		this.stPeriodo = stPeriodo;
	}

	public StaticText getStTipoDeuda() {
		return stTipoDeuda;
	}

	public void setStTipoDeuda(StaticText stTipoDeuda) {
		this.stTipoDeuda = stTipoDeuda;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public TableColumn getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TableColumn tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public TableColumn getTcTipoDeuda() {
		return tcTipoDeuda;
	}

	public void setTcTipoDeuda(TableColumn tcTipoDeuda) {
		this.tcTipoDeuda = tcTipoDeuda;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpLiquidaciones() {
		return ldpLiquidaciones;
	}

	public void setLdpLiquidaciones(ObjectListDataProvider ldpLiquidaciones) {
		this.ldpLiquidaciones = ldpLiquidaciones;
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLiquidaciones();
	}

	public StaticText getStSeleccionados() {
		return stSeleccionados;
	}

	public void setStSeleccionados(StaticText stSeleccionados) {
		this.stSeleccionados = stSeleccionados;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup messageGroup1) {
		this.messageGroup1 = messageGroup1;
	}

	public TableColumn getTcObligacion() {
		return tcObligacion;
	}

	public void setTcObligacion(TableColumn tcObligacion) {
		this.tcObligacion = tcObligacion;
	}

	public TableColumn getTcVencimiento() {
		return tcVencimiento;
	}

	public void setTcVencimiento(TableColumn tcVencimiento) {
		this.tcVencimiento = tcVencimiento;
	}

	public StaticText getStVencimiento() {
		return stVencimiento;
	}

	public void setStVencimiento(StaticText stVencimiento) {
		this.stVencimiento = stVencimiento;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		RegistroCancelacionManual registroCancelacion = this.obtenerObjetoDelElementoPila(0, RegistroCancelacionManual.class);
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(1, ArrayList.class);

		Object comentario = this.getTaComentario().getText();

		if(comentario != null && comentario != "")
			registroCancelacion.setComentario(comentario.toString());
		else
			registroCancelacion.setComentario(null);

		this.getElementoPila().getObjetos().set(0, registroCancelacion);
		this.getElementoPila().getObjetos().set(1, listaLiquidaciones);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		RegistroCancelacionManual registroCancelacion = null;
		List<LiquidacionTasa> listaLiquidaciones = null;

		int ind = 0;
		registroCancelacion = this.obtenerObjetoDelElementoPila(ind++, RegistroCancelacionManual.class);
		listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getTaComentario().setText(registroCancelacion.getComentario());

		this.getObjectListDataProvider().setList(listaLiquidaciones);
	}

	@Override
	protected String getNombrePagina() {
		return "Registro de Cancelaci\363n Manual";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRegistroCancelacionManual";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new RegistroCancelacionManual()); // 0
		ep.getObjetos().add(ind++, new ArrayList()); // 1 lista liquidaciones seleccionadas

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;
		this.getElementoPila().getObjetos().set(1, listaLiquidaciones);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		RegistroCancelacionManual registroCancelacion = (RegistroCancelacionManual) pObject;
		this.getElementoPila().getObjetos().set(0, registroCancelacion);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual}";
	}

	@Override
	public long getSerialVersionUID() {
		return RegistroCancelacionManual.serialVersionUID;
	}
}