/*
 * ABMPlantillaPlanDePago.java
 *
 * Created on 24 de octubre de 2006, 12:53
 * Copyright Trascender SRL
 */
package muni.saic.ABMPlantillaPlanDePago;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
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
	}

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
		PlantillaPlanDePago PlantillaPlanDePago = (PlantillaPlanDePago) this.obtenerObjetoDelElementoPila(ind++, PlantillaPlanDePago.class);

		PlantillaPlanDePago.setNombre(this.getTextFieldValue(getTfNombre()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, PlantillaPlanDePago);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		int ind = 0;
		PlantillaPlanDePago plantillaPlanDePago = (PlantillaPlanDePago) this.obtenerObjetoDelElementoPila(ind++, PlantillaPlanDePago.class);
		this.getTfNombre().setText(plantillaPlanDePago.getNombre());
		
		this.setTextFieldValueDouble(tfMontoCondonacionImporte, plantillaPlanDePago.getMontoCondonacionImporte());
		this.setTextFieldValueDouble(tfMontoCondonacionInteres, plantillaPlanDePago.getMontoCondonacionIntereses());
		this.setCheckBoxValue(cbCondonacionImportePorcentual, plantillaPlanDePago.getCondonacionImportePorcentual());
		this.setCheckBoxValue(cbCondonacionInteresPorcentual, plantillaPlanDePago.getCondonacionInteresPorcentual());
		
		this.setTextFieldValueInteger(this.tfCantidadCuotas, plantillaPlanDePago.getCantidadCuotas());
		this.setTextFieldValueInteger(tfDiaVencimiento, plantillaPlanDePago.getDiaVencimiento());
		
		this.setTextFieldValueDouble(tfInteresPunitorio, plantillaPlanDePago.getInteresPunitorio());
		this.setTextFieldValueDouble(tfTasaNominalAnual, plantillaPlanDePago.getTasaNominalAnual());
		
		this.setTextFieldValueInteger(tfCantidadCuotasCese, plantillaPlanDePago.getCantidadCuotasCese());
		this.setTextFieldValueInteger(tfCantidadDiasCese, plantillaPlanDePago.getCantidadDiasCese());
		
		
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPlantillaPlanDePago";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PlantillaPlanDePago PlantillaPlanDePago = (PlantillaPlanDePago) pObject;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, PlantillaPlanDePago);
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
}