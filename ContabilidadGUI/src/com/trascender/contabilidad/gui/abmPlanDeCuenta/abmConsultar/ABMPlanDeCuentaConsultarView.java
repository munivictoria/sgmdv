package com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlTabla;

public abstract class ABMPlanDeCuentaConsultarView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private PlanDeCuentaABMModel abmModel;
	private PlanDeCuentaTableModelConsultar tableModel;
	
	//Seccion Cuerpo
	private PnlTabla pnlABMTabla;
	
	public PlanDeCuentaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(PlanDeCuentaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	
	public ABMPlanDeCuentaConsultarView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMPlanDeCuentaConsultarView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlABMTabla = new PnlTabla();
		this.getPnlCuerpo().add(this.pnlABMTabla, BorderLayout.CENTER);
		
		//numFila++;
		
//		this.getPnlAtributos().setPreferredSize(new Dimension(
//				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
//				numFila*ConstantesSeparacion.INCREMENTO_Y+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y*2));
		
		
		int numFila = -1;		
		setTamanioPosicionVentana(numFila+1);
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
	}

	public PnlTabla getPnlABMTabla() {
		return pnlABMTabla;
	}

	public PlanDeCuentaTableModelConsultar getTableModel() {
		return tableModel;
	}

	public void setTableModel(PlanDeCuentaTableModelConsultar tableModel) {
		this.tableModel = tableModel;
		this.getPnlABMTabla().getTblDatos().setModel(this.tableModel);
	}

}
