package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.BorderLayout;
import java.text.NumberFormat;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;

public class ConsultarPresupuesto extends ABMPresupuesto{

	private ConsultarPresupuestoView view;
	private PresupuestoABMModel abmModel = new PresupuestoABMModel();
	private LineaPresupuestoTableModel tableModel;
	
	public ConsultarPresupuesto(JDialog owner) throws Exception {
		this.view = new ConsultarPresupuestoView(owner);
		this.init();
	}
	
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
		this.editableAll();
	}

	private void setListeners() {
	
	}
	
	private void editableAll() {
		this.getView().getTfAnioPeriodo().setEditable(false);
		this.getView().getTfDigesto().setEditable(false);
		this.getView().getTfNombre().setEditable(false);
		this.getView().getCbEstado().setEnabled(false);
		this.getView().getCbTipo().setEnabled(false);
	}
	
	private void disabledAll() {
		this.getView().getPnlBtnTabla().setEnabled(false);
		this.getView().getPnlTabla().getTblDatos().setEnabled(false);
	}
	
	private void visibleAll() {
		this.getView().getPnlBotonesSeleccionDigesto().setVisible(false);
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlBtnTabla().setVisible(false);
	}
	
	/*
	 * Setea el modelo de tablas según el tipo del elemento seleccionado
	 */
	public void instanciarTableModel(Tipo tipoPresupuesto) throws Exception {
		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
			this.tableModel = new LineaPresupuestoGastosConsultaABMTableModel();
		}
		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
			this.tableModel = new LineaPresupuestoRecursoConsultaABMTableModel();
		}
		this.getView().setTableModel(this.getTableModel());
	}
	
	/*
	 * Llama a los métodos para calcular los resultados de gastos y recursos y los agrega a los labels
	 * correspondiente en los paneles
	 */
	public void CalcularResultados(Tipo tipoPresupuesto){
		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
			PanelResultadosGastos pnlResultado = new PanelResultadosGastos();
			pnlResultado.getLblTotalMontoPresupuestado().setText(this.TotalGastosPresupuesto());
			pnlResultado.getLblTotalMontoComprometido().setText(this.TotalGastosComprometido());
			pnlResultado.getLblTotalMontoPagado().setText(this.TotalGastosPagado());
			this.getView().getPnlCuerpo().add(pnlResultado, BorderLayout.SOUTH);
			
		}
		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
			PanelResultadosRecursos pnlResultado = new PanelResultadosRecursos();
			pnlResultado.getLblTotalMontoEstimado().setText(this.TotalRecursosEstimado());
			pnlResultado.getLblTotalMontoRecaudado().setText(this.TotalRecursosRecaudado());
			this.getView().getPnlCuerpo().add(pnlResultado, BorderLayout.SOUTH);
		}
	}
	
	/*
	 * Calcula el total de gastos de presupuesto para mostrarlo en la consulta
	 */
	private String TotalGastosPresupuesto(){
		Double locTotal = new Double(0);
		for(LineaPresupuesto cadaLineaPresupuesto: this.getAbmModel().getLineaPresupuesto()){
		
			LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos)cadaLineaPresupuesto;
			if(locLineaPresupuestoGastos.getMontoPresupuestado()!=null){
				locTotal+=locLineaPresupuestoGastos.getMontoPresupuestado();
			}
		}
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 

	/*
	 * Calcula el total de gastos comprometido para mostrarlo en la consulta
	 */
	private String TotalGastosComprometido(){
		Double locTotal = new Double(0);
		for(LineaPresupuesto  cadaLineaPresupuesto: this.getAbmModel().getLineaPresupuesto()){
			
			LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos)cadaLineaPresupuesto;
			if(locLineaPresupuestoGastos.getMontoComprometido()!=null){
				locTotal+=locLineaPresupuestoGastos.getMontoComprometido();

			}
		}
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 
	
	/*
	 * Calcula el total de gastos pagados para mostrarlo en la consulta
	 */
	private String TotalGastosPagado(){
		Double locTotal = new Double(0);
		for(LineaPresupuesto  cadaLineaPresupuesto: this.getAbmModel().getLineaPresupuesto()){
			
			LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos)cadaLineaPresupuesto;
			if(locLineaPresupuestoGastos.getMontoPagado()!=null){
			locTotal+=locLineaPresupuestoGastos.getMontoPagado();
			}
		}
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 
	
	/*
	 * Calcula el total de recursos estimados para mostrarlo en la consulta
	 */
	private String TotalRecursosEstimado(){
		Double locTotal = new Double(0);
		for(LineaPresupuesto  cadaLineaPresupuesto: this.getAbmModel().getLineaPresupuesto()){
			
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos)cadaLineaPresupuesto;
			if(locLineaPresupuestoRecursos.getMontoEstimado()!=null){
			locTotal+=locLineaPresupuestoRecursos.getMontoEstimado();
			}
		}
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 

	/**
	 * Calcula el total de recursos recaudados para mostrarlo en la consulta
	 * @return
	 */
	private String TotalRecursosRecaudado(){
		Double locTotal = new Double(0);
		for(LineaPresupuesto  cadaLineaPresupuesto: this.getAbmModel().getLineaPresupuesto()){
			
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos)cadaLineaPresupuesto;
			if(locLineaPresupuestoRecursos.getMontoRecaudado()!=null){
			locTotal+=locLineaPresupuestoRecursos.getMontoRecaudado();
			}
		}
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		dispFormat.setGroupingUsed(true);
		
		return dispFormat.format(locTotal);
	} 
	
	
	@Override
	public PresupuestoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaPresupuestoTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public ConsultarPresupuestoView getView() {
		return this.view;
	}

}