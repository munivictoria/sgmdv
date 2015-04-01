/*
 * ABMPlantillaPlanDePago.java
 *
 * Created on 24 de octubre de 2006, 12:53
 * Copyright Trascender SRL
 */
package muni.saic.ABMPlantillaPlanDePago;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.RelaConceptoIngresoVarioCuenta;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMPlantillaPlanDePago extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationParametrosAsociacion() != null) {
			this.ldpCuentas.setList(this.getListaDelCommunicationParametrosAsociacion());
		}
	}

	private ObjectListDataProvider ldpCuentas = new ObjectListDataProvider();
	private TextField tfNombre = new TextField();
	private TextField tfMontoCondonacionImporte = new TextField();
	private TextField tfMontoCondonacionInteres = new TextField();
	private Checkbox cbCondonacionImportePorcentual = new Checkbox();
	private Checkbox cbCondonacionInteresPorcentual = new Checkbox();
	private TextField tfCantidadCuotas = new TextField();
	private TextField tfTasaNominalAnual = new TextField();
	private TextField tfInteresPunitorio = new TextField();
	private TextField tfDiaVencimiento = new TextField();
	private TextField tfCantidadDiasCese = new TextField();
	private TextField tfCantidadCuotasCese = new TextField();
	private Table tablaCuentas = new Table();
	private TableRowGroup trgCuentas = new TableRowGroup();
	private TableColumn tcRbCuentas = new TableColumn();
	private RadioButton rbCuentas = new RadioButton();
	private TableColumn tcCodigoImputacion = new TableColumn();
	private TableColumn tcNombreCuenta = new TableColumn();
	private TableColumn tcPorcentaje = new TableColumn();
	private TextField tfPorcentaje = new TextField();
	private PanelGroup pgCuentas = new PanelGroup();
	private Button btnAgregarCuenta = new Button();
	private HtmlAjaxCommandButton btnQuitarCuenta = new HtmlAjaxCommandButton();
	
	public PanelGroup getPgCuentas() {
		return pgCuentas;
	}

	public void setPgCuentas(PanelGroup pgCuentas) {
		this.pgCuentas = pgCuentas;
	}

	public Button getBtnAgregarCuenta() {
		return btnAgregarCuenta;
	}

	public void setBtnAgregarCuenta(Button btnAgregarCuenta) {
		this.btnAgregarCuenta = btnAgregarCuenta;
	}

	public HtmlAjaxCommandButton getBtnQuitarCuenta() {
		return btnQuitarCuenta;
	}

	public void setBtnQuitarCuenta(HtmlAjaxCommandButton btnQuitarCuenta) {
		this.btnQuitarCuenta = btnQuitarCuenta;
	}

	public TableColumn getTcPorcentaje() {
		return tcPorcentaje;
	}

	public void setTcPorcentaje(TableColumn tcPorcentaje) {
		this.tcPorcentaje = tcPorcentaje;
	}

	public TextField getTfPorcentaje() {
		return tfPorcentaje;
	}

	public void setTfPorcentaje(TextField tfPorcentaje) {
		this.tfPorcentaje = tfPorcentaje;
	}

	public TableColumn getTcCodigoImputacion() {
		return tcCodigoImputacion;
	}

	public void setTcCodigoImputacion(TableColumn tcCodigoImputacion) {
		this.tcCodigoImputacion = tcCodigoImputacion;
	}

	public TableColumn getTcNombreCuenta() {
		return tcNombreCuenta;
	}

	public void setTcNombreCuenta(TableColumn tcNombreCuenta) {
		this.tcNombreCuenta = tcNombreCuenta;
	}

	public RadioButton getRbCuentas() {
		return rbCuentas;
	}

	public void setRbCuentas(RadioButton rbCuentas) {
		this.rbCuentas = rbCuentas;
	}

	public TableColumn getTcRbCuentas() {
		return tcRbCuentas;
	}

	public void setTcRbCuentas(TableColumn tcRbCuentas) {
		this.tcRbCuentas = tcRbCuentas;
	}

	public ObjectListDataProvider getLdpCuentas() {
		return ldpCuentas;
	}

	public void setLdpCuentas(ObjectListDataProvider ldpCuentas) {
		this.ldpCuentas = ldpCuentas;
	}

	public Table getTablaCuentas() {
		return tablaCuentas;
	}

	public void setTablaCuentas(Table tablaCuentas) {
		this.tablaCuentas = tablaCuentas;
	}

	public TableRowGroup getTrgCuentas() {
		return trgCuentas;
	}

	public void setTrgCuentas(TableRowGroup trgCuentas) {
		this.trgCuentas = trgCuentas;
	}

	public TextField getTfMontoCondonacionImporte() {
		return tfMontoCondonacionImporte;
	}

	public void setTfMontoCondonacionImporte(TextField tfMontoCondonacionImporte) {
		this.tfMontoCondonacionImporte = tfMontoCondonacionImporte;
	}
	public TextField getTfMontoCondonacionInteres() {
		return tfMontoCondonacionInteres;
	}

	public void setTfMontoCondonacionInteres(TextField tfMontoCondonacionInteres) {
		this.tfMontoCondonacionInteres = tfMontoCondonacionInteres;
	}

	public Checkbox getCbCondonacionImportePorcentual() {
		return cbCondonacionImportePorcentual;
	}

	public void setCbCondonacionImportePorcentual(
			Checkbox cbCondonacionImportePorcentual) {
		this.cbCondonacionImportePorcentual = cbCondonacionImportePorcentual;
	}

	public Checkbox getCbCondonacionInteresPorcentual() {
		return cbCondonacionInteresPorcentual;
	}

	public void setCbCondonacionInteresPorcentual(
			Checkbox cbCondonacionInteresPorcentual) {
		this.cbCondonacionInteresPorcentual = cbCondonacionInteresPorcentual;
	}

	public TextField getTfCantidadCuotas() {
		return tfCantidadCuotas;
	}

	public void setTfCantidadCuotas(TextField tfCantidadCuotas) {
		this.tfCantidadCuotas = tfCantidadCuotas;
	}

	public TextField getTfTasaNominalAnual() {
		return tfTasaNominalAnual;
	}

	public void setTfTasaNominalAnual(TextField tfTasaNominalAnual) {
		this.tfTasaNominalAnual = tfTasaNominalAnual;
	}

	public TextField getTfInteresPunitorio() {
		return tfInteresPunitorio;
	}

	public void setTfInteresPunitorio(TextField tfInteresPunitorio) {
		this.tfInteresPunitorio = tfInteresPunitorio;
	}

	public TextField getTfDiaVencimiento() {
		return tfDiaVencimiento;
	}

	public void setTfDiaVencimiento(TextField tfDiaVencimiento) {
		this.tfDiaVencimiento = tfDiaVencimiento;
	}

	public TextField getTfCantidadDiasCese() {
		return tfCantidadDiasCese;
	}

	public void setTfCantidadDiasCese(TextField tfCantidadDiasCese) {
		this.tfCantidadDiasCese = tfCantidadDiasCese;
	}

	public TextField getTfCantidadCuotasCese() {
		return tfCantidadCuotasCese;
	}

	public void setTfCantidadCuotasCese(TextField tfCantidadCuotasCese) {
		this.tfCantidadCuotasCese = tfCantidadCuotasCese;
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

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMPlantillaPlanDePago() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaPlanDePago());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		PlantillaPlanDePago plantillaPlanDePago = (PlantillaPlanDePago) this.obtenerObjetoDelElementoPila(ind++, PlantillaPlanDePago.class);

		plantillaPlanDePago.setNombre(this.getTextFieldValue(getTfNombre()));
		plantillaPlanDePago.setMontoCondonacionImporte(getTextFieldValueDouble(tfMontoCondonacionImporte));
		plantillaPlanDePago.setMontoCondonacionIntereses(getTextFieldValueDouble(tfMontoCondonacionInteres));
		plantillaPlanDePago.setCondonacionImportePorcentual(getCbCondonacionImportePorcentual().isChecked());
		plantillaPlanDePago.setCondonacionInteresPorcentual(getCbCondonacionInteresPorcentual().isChecked());
		plantillaPlanDePago.setCantidadCuotas(getTextFieldValueInteger(tfCantidadCuotas));
		plantillaPlanDePago.setDiaVencimiento(getTextFieldValueInteger(tfDiaVencimiento));
		plantillaPlanDePago.setInteresPunitorio(getTextFieldValueDouble(tfInteresPunitorio));
		plantillaPlanDePago.setTasaNominalAnual(getTextFieldValueDouble(tfTasaNominalAnual));
		plantillaPlanDePago.setCantidadCuotasCese(getTextFieldValueInteger(tfCantidadCuotasCese));
		plantillaPlanDePago.setCantidadDiasCese(getTextFieldValueInteger(tfCantidadDiasCese));

		this.getLdpCuentas().commitChanges();
		plantillaPlanDePago.setListaParametrosAsociacion(this.getLdpCuentas().getList());

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, plantillaPlanDePago);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		PlantillaPlanDePago plantillaPlanDePago = (PlantillaPlanDePago) this.obtenerObjetoDelElementoPila(ind++, PlantillaPlanDePago.class);
		this.getTfNombre().setText(plantillaPlanDePago.getNombre());
		
		this.setTextFieldValueDouble(tfMontoCondonacionImporte, plantillaPlanDePago.getMontoCondonacionImporte());
		this.setTextFieldValueDouble(tfMontoCondonacionInteres, plantillaPlanDePago.getMontoCondonacionIntereses());
		this.setCheckBoxValue(cbCondonacionImportePorcentual, plantillaPlanDePago.getCondonacionImportePorcentual());
		this.setCheckBoxValue(cbCondonacionInteresPorcentual, plantillaPlanDePago.getCondonacionInteresPorcentual());
		
		this.setTextFieldValueInteger(tfCantidadCuotas, plantillaPlanDePago.getCantidadCuotas());
		this.setTextFieldValueInteger(tfDiaVencimiento, plantillaPlanDePago.getDiaVencimiento());
		
		this.setTextFieldValueDouble(tfInteresPunitorio, plantillaPlanDePago.getInteresPunitorio());
		this.setTextFieldValueDouble(tfTasaNominalAnual, plantillaPlanDePago.getTasaNominalAnual());
		
		this.setTextFieldValueInteger(tfCantidadCuotasCese, plantillaPlanDePago.getCantidadCuotasCese());
		this.setTextFieldValueInteger(tfCantidadDiasCese, plantillaPlanDePago.getCantidadDiasCese());
		
		this.getLdpCuentas().setList(plantillaPlanDePago.getListaParametrosAsociacion());
		this.setListaDelCommunicationParametrosAsociacion(getLdpCuentas().getList());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPlantillaPlanDePago";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		PlantillaPlanDePago plantilla = (PlantillaPlanDePago) obtenerObjetoDelElementoPila(0);
		if(pObject instanceof Cuenta) {
			Cuenta locCuenta = (Cuenta) pObject;

			boolean encontrado = false;
			for(ParametroAsociacion cadaParametro : plantilla.getListaParametrosAsociacion()) {
				if(cadaParametro.getCuenta().getIdCuenta() == locCuenta.getIdCuenta()) {
					encontrado = true;
				}
			}

			if(!encontrado) {
				ParametroAsociacion locParametro = new ParametroAsociacion();
				locParametro.setCuenta(locCuenta.getCuentaRfr());
				plantilla.getListaParametrosAsociacion().add(locParametro);
			}
		}
		this.getElementoPila().getObjetos().set(0, plantilla);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PlantillaPlanDePago plantillaPlanDePago = (PlantillaPlanDePago) pObject;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, plantillaPlanDePago);
	}
	
	@Override
	public long getSerialVersionUID() {
		return PlantillaPlanDePago.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PlantillaPlanDePago locPlantillaPlanDePago = this.obtenerObjetoDelElementoPila(0, PlantillaPlanDePago.class);
		this.getTablaLogs().getLdpLogs().setList(locPlantillaPlanDePago.getListaLogsAuditoria());
	}
	
	private List<ParametroAsociacion> getListaDelCommunicationParametrosAsociacion() {
		return this.getCommunicationSAICBean().getListaParametrosAsociacionPlantilla();
	}

	private void setListaDelCommunicationParametrosAsociacion(List<ParametroAsociacion> lista) {
		this.getCommunicationSAICBean().setListaParametrosAsociacionPlantilla(lista);
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
	
	public String btnAgregarCuenta_action() {
		return navegarParaSeleccionar("AdminCuenta");
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
					Object obj = this.getLdpCuentas().getObjects()[index];
					PlantillaPlanDePago locPlantilla = this.obtenerObjetoDelElementoPila(0, PlantillaPlanDePago.class);
					locPlantilla.getListaParametrosAsociacion().remove(obj);
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

}