/*
 * AdminEstadoCuenta.java
 *
 * Created on 29 de agosto de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.compras.EstadoCuentaCorriente;

import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.recurso.transients.CuentaCorriente;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Periodicidad;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminEstadoCuentaCorriente extends AdminPageBean {

	protected Button btnSeleccionarProveedor = new Button();
	protected HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();
	protected Button btnImprimir = new Button();

	protected Label lblProveedor = new Label();
	protected Label lblSaldo = new Label();

	protected StaticText stMovimientosIngreso = new StaticText();
	protected StaticText stMovimientosEgreso = new StaticText();
	protected StaticText stNumeroMovIngreso = new StaticText();
	protected StaticText stNumeroMovEgreso = new StaticText();
	protected StaticText stFechaMovIngreso = new StaticText();
	protected StaticText stFechaMovEgreso = new StaticText();
	protected StaticText stFactura = new StaticText();
	protected StaticText stOrdenPago = new StaticText();
	protected StaticText stImporteIngreso = new StaticText();
	protected StaticText stImporteEgreso = new StaticText();
	protected StaticText stSaldo = new StaticText();

	protected TableColumn tcNumeroMovIngreso = new TableColumn();
	protected TableColumn tcNumeroMovEgreso = new TableColumn();
	protected TableColumn tcFechaMovIngreso = new TableColumn();
	protected TableColumn tcFechaMovEgreso = new TableColumn();
	protected TableColumn tcFactura = new TableColumn();
	protected TableColumn tcOrdenPago = new TableColumn();
	protected TableColumn tcImporteIngreso = new TableColumn();
	protected TableColumn tcImporteEgreso = new TableColumn();

	protected ObjectListDataProvider ldpMovIngreso = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpMovEgreso = new ObjectListDataProvider();

	protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	protected TextField tfProveedor = new TextField();

	protected Table table2 = new Table();

	protected TableRowGroup tableRowGroup2 = new TableRowGroup();

	protected NumberConverter numberConverter1 = new NumberConverter();

	// ***GETTERS & SETTERS***

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public void setBtnImprimir(Button b) {
		this.btnImprimir = b;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button btnSeleccionarProveedor) {
		this.btnSeleccionarProveedor = btnSeleccionarProveedor;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Label getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(Label lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public StaticText getStSaldo() {
		return stSaldo;
	}

	public void setStSaldo(StaticText stSaldo) {
		this.stSaldo = stSaldo;
	}

	public StaticText getStImporteEgreso() {
		return stImporteEgreso;
	}

	public void setStImporteEgreso(StaticText stImporteEgreso) {
		this.stImporteEgreso = stImporteEgreso;
	}

	public StaticText getStImporteIngreso() {
		return stImporteIngreso;
	}

	public void setStImporteIngreso(StaticText stImporteIngreso) {
		this.stImporteIngreso = stImporteIngreso;
	}

	public StaticText getStFactura() {
		return stFactura;
	}

	public void setStFactura(StaticText stFactura) {
		this.stFactura = stFactura;
	}

	public StaticText getStOrdenPago() {
		return stOrdenPago;
	}

	public void setStOrdenPago(StaticText stOrdenPago) {
		this.stOrdenPago = stOrdenPago;
	}

	public StaticText getStNumeroMovEgreso() {
		return stNumeroMovEgreso;
	}

	public void setStNumeroMovEgreso(StaticText stNumeroMovEgreso) {
		this.stNumeroMovEgreso = stNumeroMovEgreso;
	}

	public StaticText getStNumeroMovIngreso() {
		return stNumeroMovIngreso;
	}

	public void setStNumeroMovIngreso(StaticText stNumeroMovIngreso) {
		this.stNumeroMovIngreso = stNumeroMovIngreso;
	}

	public StaticText getStFechaMovEgreso() {
		return stFechaMovEgreso;
	}

	public void setStFechaMovEgreso(StaticText stFechaMovEgreso) {
		this.stFechaMovEgreso = stFechaMovEgreso;
	}

	public StaticText getStFechaMovIngreso() {
		return stFechaMovIngreso;
	}

	public void setStFechaMovIngreso(StaticText stFechaMovIngreso) {
		this.stFechaMovIngreso = stFechaMovIngreso;
	}

	public StaticText getStMovimientosEgreso() {
		return stMovimientosEgreso;
	}

	public void setStMovimientosEgreso(StaticText stMovimientosEgreso) {
		this.stMovimientosEgreso = stMovimientosEgreso;
	}

	public StaticText getStMovimientosIngreso() {
		return stMovimientosIngreso;
	}

	public void setStMovimientosIngreso(StaticText stMovimientosIngreso) {
		this.stMovimientosIngreso = stMovimientosIngreso;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tfProveedor) {
		this.tfProveedor = tfProveedor;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table t) {
		this.table2 = t;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup trg) {
		this.tableRowGroup2 = trg;
	}

	public ObjectListDataProvider getLdpMovEgreso() {
		return ldpMovEgreso;
	}

	public void setLdpMovEgreso(ObjectListDataProvider ldpMovEgreso) {
		this.ldpMovEgreso = ldpMovEgreso;
	}

	public ObjectListDataProvider getLdpMovIngreso() {
		return ldpMovIngreso;
	}

	public void setLdpMovIngreso(ObjectListDataProvider ldpMovIngreso) {
		this.ldpMovIngreso = ldpMovIngreso;
	}

	public TableColumn getTcImporteEgreso() {
		return tcImporteEgreso;
	}

	public void setTcImporteEgreso(TableColumn tcImporteEgreso) {
		this.tcImporteEgreso = tcImporteEgreso;
	}

	public TableColumn getTcImporteIngreso() {
		return tcImporteIngreso;
	}

	public void setTcImporteIngreso(TableColumn tcImporteIngreso) {
		this.tcImporteIngreso = tcImporteIngreso;
	}

	public TableColumn getTcFactura() {
		return tcFactura;
	}

	public void setTcFactura(TableColumn tcFactura) {
		this.tcFactura = tcFactura;
	}

	public TableColumn getTcOrdenPago() {
		return tcOrdenPago;
	}

	public void setTcOrdenPago(TableColumn tcOrdenPago) {
		this.tcOrdenPago = tcOrdenPago;
	}

	public TableColumn getTcFechaMovEgreso() {
		return tcFechaMovEgreso;
	}

	public void setTcFechaMovEgreso(TableColumn tcFechaMovEgreso) {
		this.tcFechaMovEgreso = tcFechaMovEgreso;
	}

	public TableColumn getTcFechaMovIngreso() {
		return tcFechaMovIngreso;
	}

	public void setTcFechaMovIngreso(TableColumn tcFechaMovIngreso) {
		this.tcFechaMovIngreso = tcFechaMovIngreso;
	}

	public TableColumn getTcNumeroMovEgreso() {
		return tcNumeroMovEgreso;
	}

	public void setTcNumeroMovEgreso(TableColumn tcNumeroMovEgreso) {
		this.tcNumeroMovEgreso = tcNumeroMovEgreso;
	}

	public TableColumn getTcNumeroMovIngreso() {
		return tcNumeroMovIngreso;
	}

	public void setTcNumeroMovIngreso(TableColumn tcNumeroMovIngreso) {
		this.tcNumeroMovIngreso = tcNumeroMovIngreso;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	// ***METHODS***

	public AdminEstadoCuentaCorriente() {
	}

	@Override
	protected void _init() throws Exception {

		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(), "cap");
		numberConverter1.setPattern("$ #,##0.00"); // #0.000

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null);// proveedor
		ep.getObjetos().add(ind++, new Double(0.0));// total adeudado
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);

		this.getElementoPila().getObjetos().set(0, proveedor);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
		Double totalAdeudado = (Double) this.obtenerObjetoDelElementoPila(1, Double.class);

		if(totalAdeudado != null) {
			this.getStSaldo().setText(totalAdeudado.toString());
		} else {
			this.getStSaldo().setText("0.0");
		}

		if(proveedor != null) {
			this.getTfProveedor().setText(proveedor.toString());
		}
	}

	@Override
	protected void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		int ind = 0;
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		CuentaCorriente cuentaCorriente = new CuentaCorriente();
		// double totalAdeudado = 0.0;
		boolean err = false;

		if(proveedor == null) {
			warn("Debe seleccionar un Proveedor para realizar la b\372squeda.");
			err = true;
		}

		if(!err) {
			try {
				this.getCommunicationCajaBean().getRemoteSystemAdministracionEgresos().setLlave(this.getSessionBean1().getLlave());
				cuentaCorriente = (CuentaCorriente) this.getCommunicationCajaBean().getRemoteSystemAdministracionEgresos().generarCuentaCorriente(proveedor);
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_AdminEstadoCuentaCorriente: " + ex);
				error(getNombrePagina() + " - Administrar Estado de Cuenta Corriente: " + ex.getMessage());
				ex.getStackTrace();
			}

			// ingreso
			this.getObjectListDataProvider().setList(cuentaCorriente.getListaMovimientosIngreso());
			this.setListaDelCommunication(cuentaCorriente.getListaMovimientosIngreso());

			// egreso
			this.getObjectListDataProvider2().setList(cuentaCorriente.getListaMovimientosEgreso());
			this.setListaDelCommunication2(cuentaCorriente.getListaMovimientosEgreso());

			//
			// for (int i = 0; i < this.getListaDelCommunication().size(); i++)
			// {
			// EstadoCuentaContribuyente locEstadoCuentaContribuyente =
			// (EstadoCuentaContribuyente)
			// this.getListaDelCommunication().get(i);
			// totalAdeudado +=
			// locEstadoCuentaContribuyente.getTotalAdeudado().doubleValue();
			//
			// }
			this.getStSaldo().setText(Conversor.getStringDeDouble(cuentaCorriente.getSaldo()));
			this.getElementoPila().getObjetos().set(1, cuentaCorriente.getSaldo());
		} else {
			this.getObjectListDataProvider().setList(null);
			this.setListaDelCommunication(null);
			this.getObjectListDataProvider2().setList(null);
			this.setListaDelCommunication2(null);
		}

	}

	@Override
	protected void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfProveedor().setText(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpMovIngreso();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaMovIngresos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaMovIngresos(lista);
	}

	protected ObjectListDataProvider getObjectListDataProvider2() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpMovEgreso();
	}

	protected List getListaDelCommunication2() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaMovEgresos();
	}

	protected void setListaDelCommunication2(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaMovEgresos(lista);
	}

	protected int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	public String btnImprimir_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfProveedor());
			this.getElementoPila().getObjetos().set(0, null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		// case name where null will return to the same page.
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected String getNombrePagina() {
		return "Estado de Cuenta Corriente";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminEstadoCuentaCorriente";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado instanceof Proveedor) {
			Proveedor proveedor = (Proveedor) seleccionado;
			this.getElementoPila().getObjetos().set(0, proveedor);
		}
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMEstadoCuenta$AdminEstadoCuenta}";
	}

	@Override
	public long getSerialVersionUID() {
		return CuentaCorriente.serialVersionUID;
	}
}