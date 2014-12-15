package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMPresupuestoView extends ABMView{

	private static final long serialVersionUID = 7557164258261981220L;
	
	private PresupuestoABMModel abmModel;
	private LineaPresupuestoTableModel tableModel;
	private LineaPresupuestoCellEditor cellEditor;

	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBtnTabla;
	
	private JLabel lblTipo;
	private JComboBox cbTipo;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JLabel lblDigesto;
	private JTextField tfDigesto;
	private PnlBotonesSeleccion pnlBotonesSeleccionDigesto;
	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	
	private final String NOMBRE_RECURSO = "ABMPresupuesto";
	
	public ABMPresupuestoView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ABMPresupuestoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		this.pnlBtnTabla = new PnlVerticalBotones();
		
		int numFila = -1;
		
		//---Sección Búsqueda
		numFila++;
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbTipo);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);
	
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbEstado);
		
		numFila++;
		this.lblDigesto = new TLabel();
		this.lblDigesto.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblDigesto"));
		this.lblDigesto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblDigesto);
		
		this.tfDigesto = new JTextField();
		this.tfDigesto.setEditable(false);
		this.tfDigesto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfDigesto);
		
		this.pnlBotonesSeleccionDigesto = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionDigesto.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBotonesSeleccionDigesto);
		
		numFila++;
		this.lblAnioPeriodo= new TLabel();
		this.lblAnioPeriodo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblAnioPeriodo"));
		this.lblAnioPeriodo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblAnioPeriodo);
		
		this.tfAnioPeriodo = new JTextField();
		this.tfAnioPeriodo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.tfAnioPeriodo);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
		this.getPnlCuerpo().add(this.pnlBtnTabla, BorderLayout.EAST);
		
		this.setTamanioPosicionVentana(numFila);
		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X+ConstantesTamanio.TF_WIDTH+ConstantesSeparacion.INCREMENTO_Y+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +Double.valueOf(this.getPnlBotonesSeleccionDigesto().getSize().getWidth()).intValue(), 
				600));
		this.setLocationRelativeTo(null);
	}

	public PresupuestoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(PresupuestoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public LineaPresupuestoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LineaPresupuestoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}
	
	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}

	public JLabel getLblDigesto() {
		return lblDigesto;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionDigesto() {
		return pnlBotonesSeleccionDigesto;
	}

	public PnlVerticalBotones getPnlBtnTabla() {
		return pnlBtnTabla;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public JTextField getTfDigesto() {
		return tfDigesto;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public LineaPresupuestoCellEditor getCellEditor() {
		return cellEditor;
	}

	public void setCellEditor(LineaPresupuestoCellEditor cellEditor) {
		this.cellEditor = cellEditor;
		
		TableColumnModel cmValor = this.getPnlTabla().getTblDatos().getColumnModel();
		TableColumn tcValor = cmValor.getColumn(LineaPresupuestoTableModel.COLUMNA_VALOR_MODIFICABLE);
		tcValor.setCellEditor(cellEditor);
		
	}

}
