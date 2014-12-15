package com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.ImportarPlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar.PlanDeCuentaTableModelConsultar;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMImportarPlanDeCuentaView extends ABMView {

	private static final long serialVersionUID = 825064966127693311L;
	
	private ImportarPlanDeCuentaABMModel abmModel;
	private PlanDeCuentaTableModelConsultar tableModel;
	
	//Seccion Cuerpo
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	
	private JLabel lblDescripcion;
	private JTextField tfDescripcion;
	private JLabel lblFechaAlta;
	private JFormattedTextField tfFechaAlta;
	
	private MaskFormatter formatter;
	
	private final String NOMBRE_RECURSO = "ABMImportarPlanDeCuenta";
	
	public ABMImportarPlanDeCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ABMImportarPlanDeCuentaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		
		int numFila = -1;
		
		numFila++;
		this.lblDescripcion = new TLabel();
		lblDescripcion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblDescripcion"));
		lblDescripcion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(lblDescripcion);
		
		this.tfDescripcion = new JTextField();
		tfDescripcion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlAtributos().add(tfDescripcion);
		
		numFila++;
		this.lblFechaAlta = new TLabel();
		lblFechaAlta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblFechaAlta"));
		lblFechaAlta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(lblFechaAlta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaAlta = new JFormattedTextField(this.formatter);
		tfFechaAlta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlAtributos().add(this.tfFechaAlta);
		
		this.getPnlAtributos().setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				numFila*ConstantesSeparacion.INCREMENTO_Y+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y*2));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
				
		setTamanioPosicionVentana(numFila+1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
	}
	
	public ImportarPlanDeCuentaABMModel getAbmModel() {
		return abmModel;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public JLabel getLblFechaAlta() {
		return lblFechaAlta;
	}

	public JFormattedTextField getTfFechaAlta() {
		return tfFechaAlta;
	}

	public PlanDeCuentaTableModelConsultar getTableModel() {
		return tableModel;
	}

	public void setTableModel(PlanDeCuentaTableModelConsultar tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	public void setAbmModel(ImportarPlanDeCuentaABMModel abmModel) {
		this.abmModel = abmModel;
	}
	
}
