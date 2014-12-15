package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMReporteContableView extends ABMView {

	private static final long serialVersionUID = 2357869292470648034L;

	private ReporteContableABMModel abmModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblNombreArchivoJasper;
	private JTextField tfNombreArchivoJasper;
	private JLabel lblTablaParametros;
	private JLabel lblTablaUsuarios;
	private PnlTabla pnlTablaParametrosReporte;
	private PnlTabla pnlTablaUsuarios;
	private PnlABMAtributos pnlAtributos;
	private UsuarioTableModel tableModelUsuario;
	private ParametroReporteContableTableModel tableModelParametros;
	private JPanel pnlTablas;
	private JPanel pnlContenedorParametros;
	private JPanel pnlContenedorUsuarios;
	private PnlVerticalBotones pnlBtnTablaParametros;
	private PnlVerticalBotones pnlBtnTablaUsuarios;
	
	private ParametroTextFieldCellEditor cellEditorTextField;
	private ParametroComboBoxCellEditor cellEditorComboBox;
	private ParametroCheckBoxCellEditor cellEditorCheckBox;
	
	private static final String NOMBRE_RECURSO = "ABMReporteContable";
	
	public ABMReporteContableView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMReporteContableView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTablaParametrosReporte = new PnlTabla();
		this.pnlTablaUsuarios = new PnlTabla();
		
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombre);
		
		numFila++;
		this.lblNombreArchivoJasper = new TLabel();
		this.lblNombreArchivoJasper.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombreArchivoJasper"));
		this.lblNombreArchivoJasper.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNombreArchivoJasper);
		
		this.tfNombreArchivoJasper = new JTextField();
		this.tfNombreArchivoJasper.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNombreArchivoJasper);
		
		this.pnlTablas = new JPanel();
		this.pnlTablas.setLayout(new BoxLayout(this.pnlTablas, BoxLayout.Y_AXIS));
		
		this.pnlContenedorParametros = new JPanel();
		this.pnlContenedorParametros.setLayout(new BorderLayout());
		this.lblTablaParametros = new JLabel();
		this.lblTablaParametros.setText("Parámetros");
		this.pnlTablaParametrosReporte = new PnlTabla();
		this.pnlTablaParametrosReporte.setPreferredSize(new Dimension(100,100));
		
		this.pnlBtnTablaParametros = new PnlVerticalBotones();
		this.getPnlContenedorParametros().add(this.lblTablaParametros, BorderLayout.NORTH);
		this.getPnlContenedorParametros().add(this.pnlBtnTablaParametros, BorderLayout.EAST);
		this.getPnlContenedorParametros().add(this.pnlTablaParametrosReporte, BorderLayout.CENTER);
		
		this.getPnlTablas().add(this.getPnlContenedorParametros());
	
		this.pnlContenedorUsuarios = new JPanel();
		this.pnlContenedorUsuarios.setLayout(new BorderLayout());
//		
		this.pnlContenedorUsuarios.setBorder(BorderFactory.createEmptyBorder(
				ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER, 0, 0, 0));
		this.lblTablaUsuarios = new JLabel();
		this.lblTablaUsuarios.setText("Usuarios");
		this.pnlTablaUsuarios = new PnlTabla();
		this.pnlTablaUsuarios.setPreferredSize(new Dimension(100,100));
		
		this.pnlBtnTablaUsuarios = new PnlVerticalBotones();
		this.getPnlContenedorUsuarios().add(this.lblTablaUsuarios, BorderLayout.NORTH);
		this.getPnlContenedorUsuarios().add(this.pnlBtnTablaUsuarios, BorderLayout.EAST);
		this.getPnlContenedorUsuarios().add(this.pnlTablaUsuarios, BorderLayout.CENTER);
	
		this.getPnlTablas().add(this.getPnlContenedorUsuarios());
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.getPnlTablas(), BorderLayout.CENTER);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
//		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL + 
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,600));
		this.setLocationRelativeTo(null);		
	}

	public ReporteContableABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(ReporteContableABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JLabel getLblNombreArchivoJasper() {
		return lblNombreArchivoJasper;
	}

	public JTextField getTfNombreArchivoJasper() {
		return tfNombreArchivoJasper;
	}

	public PnlTabla getPnlTablaParametrosReporte() {
		return pnlTablaParametrosReporte;
	}

	public PnlTabla getPnlTablaUsuarios() {
		return pnlTablaUsuarios;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public UsuarioTableModel getTableModelUsuario() {
		return tableModelUsuario;
	}

	public ParametroReporteContableTableModel getTableModelParametros() {
		return tableModelParametros;
	}

	public void setTableModelUsuario(UsuarioTableModel tableModelUsuario) {
		this.tableModelUsuario = tableModelUsuario;
		this.getPnlTablaUsuarios().getTblDatos().setModel(tableModelUsuario);
	}

	public void setTableModelParametros(ParametroReporteContableTableModel tableModelParametros) {
		this.tableModelParametros = tableModelParametros;
		this.getPnlTablaParametrosReporte().getTblDatos().setModel(tableModelParametros);
	}

	public JLabel getLblTablaParametros() {
		return lblTablaParametros;
	}

	public JLabel getLblTablaUsuarios() {
		return lblTablaUsuarios;
	}

	public JPanel getPnlContenedorParametros() {
		return pnlContenedorParametros;
	}

	public JPanel getPnlContenedorUsuarios() {
		return pnlContenedorUsuarios;
	}

	public JPanel getPnlTablas() {
		return pnlTablas;
	}

	public PnlVerticalBotones getPnlBtnTablaParametros() {
		return pnlBtnTablaParametros;
	}

	public PnlVerticalBotones getPnlBtnTablaUsuarios() {
		return pnlBtnTablaUsuarios;
	}
	
	public ParametroTextFieldCellEditor getCellEditorTextField() {
		return cellEditorTextField;
	}

	public void setCellEditorTextField(ParametroTextFieldCellEditor cellEditor) {
		this.cellEditorTextField = cellEditor;
		
		TableColumnModel cmNombre = this.getPnlTablaParametrosReporte().getTblDatos().getColumnModel();
		TableColumn tcNombre = cmNombre.getColumn(0);
		tcNombre.setCellEditor(cellEditor);
	}
	
	public ParametroComboBoxCellEditor getCellEditorComboBox() {
		return cellEditorComboBox;
	}

	public void setCellEditorComboBox(ParametroComboBoxCellEditor cellEditor) {
		this.cellEditorComboBox = cellEditor;
		
		TableColumnModel cmTipo = this.getPnlTablaParametrosReporte().getTblDatos().getColumnModel();
		TableColumn tcTipo = cmTipo.getColumn(1);
		tcTipo.setCellEditor(cellEditor);
	}
	
	public ParametroCheckBoxCellEditor getCellEditorCheckBox() {
		return cellEditorCheckBox;
	}

	public void setCellEditorCheckBox(ParametroCheckBoxCellEditor cellEditor) {
		this.cellEditorCheckBox = cellEditor;
		
		TableColumnModel cmRequerido = this.getPnlTablaParametrosReporte().getTblDatos().getColumnModel();
		TableColumn tcRequerido = cmRequerido.getColumn(2);
		tcRequerido.setCellEditor(cellEditor);
	}
}
