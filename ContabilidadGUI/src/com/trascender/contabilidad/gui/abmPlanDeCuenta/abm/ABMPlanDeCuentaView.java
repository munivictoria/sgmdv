package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTreeModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMArbol;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMPlanDeCuentaView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private PlanDeCuentaABMModel abmModel;
	
	//Seccion Cuerpo
	private PnlABMAtributos pnlAtributos;
	private PnlVerticalBotones pnlVerticalBotonesArbol;
	private PnlABMArbol pnlABMArbol;
	
	private JLabel lblDescripcion;
	private JTextField tfDescripcion;
	private JLabel lblFechaAlta;
	private JFormattedTextField tfFechaAlta;
	
	private MaskFormatter formatter;
	
	private final String NOMBRE_RECURSO = "ABMPlanDeCuenta";
	
	public ABMPlanDeCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMPlanDeCuentaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlABMArbol = new PnlABMArbol();
		this.pnlVerticalBotonesArbol = new PnlVerticalBotones();
		
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
		this.getPnlCuerpo().add(this.pnlABMArbol, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlVerticalBotonesArbol, BorderLayout.EAST);
				
		setTamanioPosicionVentana(numFila+1);
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(700,600));
		this.setLocationRelativeTo(null);
	}
	
	
	public CuentaTreeModel getTreeModel() {
		return (CuentaTreeModel)this.pnlABMArbol.getTreeModel();
	}
	public void setTreeModel(CuentaTreeModel treeModel) {
		this.pnlABMArbol.setTreeModel(treeModel);
	}

	public PlanDeCuentaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(PlanDeCuentaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JLabel getLblFechaAlta() {
		return lblFechaAlta;
	}

	public PnlABMArbol getPnlABMArbol() {
		return pnlABMArbol;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlVerticalBotones getPnlVerticalBotonesArbol() {
		return pnlVerticalBotonesArbol;
	}

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public JFormattedTextField getTfFechaAlta() {
		return tfFechaAlta;
	}

}
