package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlAyuda;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsientoContableView extends ABMView {

	private static final long serialVersionUID = -6169687977251897532L;
	
	private AsientoContableABMModel abmModel;
	private LineaAsientoContableTableModel tableModel;

	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBtnTabla;
	
	private JLabel lblNumeroAsiento;
	private JTextField tfNumeroAsiento;
	private JLabel lblLibroDiario;
	private JTextField tfLibroDiario;
	private PnlBotonesSeleccion pnlBotonesSeleccionLibroDiario;
	private JLabel lblFolioLibroDiario;
	private JComboBox cbFolioLibroDiario;
	private JLabel lblObservaciones;
	private JTextArea taObservaciones;
	private JLabel lblFecha;
	private JFormattedTextField ftfFecha;
	private JLabel lblTipoSubdiarioCaja;
	private JComboBox cbTipoSubdiarioCaja;
	private JButton btnGenerarAsientoContable;
	
	private PnlAyuda pnlAyudaDevengamiento;
	
	private JButton btnCargarAsientoContable;
	private PanelResultados pnlResultados;

	private final String NOMBRE_RECURSO = "ABMAsientoContable";
	private MaskFormatter formatter;
	
	public ABMAsientoContableView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsientoContableView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		this.pnlBtnTabla = new PnlVerticalBotones();
		
		int numFila = -1;
		
		numFila++;
		this.lblNumeroAsiento = new TLabel();
		this.lblNumeroAsiento.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroAsiento"));
		this.lblNumeroAsiento.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblNumeroAsiento);
		
		this.tfNumeroAsiento = new JTextField();
		this.tfNumeroAsiento.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfNumeroAsiento);
		
		numFila++;
		this.lblLibroDiario = new TLabel();
		this.lblLibroDiario.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblLibroDiario"));
		this.lblLibroDiario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblLibroDiario);
		
		this.tfLibroDiario = new JTextField();
		this.tfLibroDiario.setEditable(false);
		this.tfLibroDiario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfLibroDiario);
		
		this.pnlBotonesSeleccionLibroDiario = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionLibroDiario.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBotonesSeleccionLibroDiario);
		
		numFila++;
		this.lblFolioLibroDiario = new TLabel();
		this.lblFolioLibroDiario.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFolioLibroDiario"));
		this.lblFolioLibroDiario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFolioLibroDiario);
		
		this.cbFolioLibroDiario = new JComboBox();
		this.cbFolioLibroDiario.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbFolioLibroDiario);
		
		numFila++;
		this.lblObservaciones = new TLabel();
		this.lblObservaciones.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblObservaciones"));
		this.lblObservaciones.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblObservaciones);
		
		this.taObservaciones = new JTextArea();
		this.taObservaciones.setLineWrap(true);
		this.taObservaciones.setWrapStyleWord(true);
		JScrollPane spObservaciones = new JScrollPane(this.taObservaciones);
		spObservaciones.setBounds(Util.getBoundsColumnaInputTextArea(numFila));
		this.pnlAtributos.add(spObservaciones);
		numFila++;
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFecha = new JFormattedTextField(this.formatter);
		this.ftfFecha.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.ftfFecha);
		
		numFila++;
		this.lblTipoSubdiarioCaja = new TLabel();
		this.lblTipoSubdiarioCaja.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipoSubdiarioCaja"));
		this.lblTipoSubdiarioCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblTipoSubdiarioCaja);
		
		this.cbTipoSubdiarioCaja = new JComboBox();
		this.cbTipoSubdiarioCaja.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.pnlAtributos.add(this.cbTipoSubdiarioCaja);
		
		numFila++;
		this.btnGenerarAsientoContable = new JButton();
		this.btnGenerarAsientoContable.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".btnGenerarAsientoTexto"));
		this.btnGenerarAsientoContable.setToolTipText(MessagesContabilidad.getString(NOMBRE_RECURSO+".btnGenerarAsientoTextoToolTip"));
		this.btnGenerarAsientoContable.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH * 2,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnGenerarAsientoContable);
		
		this.pnlAyudaDevengamiento = new PnlAyuda();
		this.pnlAyudaDevengamiento.setBounds(new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X + ConstantesTamanio.TF_WIDTH + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH * 2,
				ConstantesTamanio.PNL_BOTONES_SELECCION_HEIGHT));
		this.pnlAtributos.add(this.pnlAyudaDevengamiento);
		
		numFila++;
		this.btnCargarAsientoContable = new JButton();
		this.btnCargarAsientoContable.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".btnCargarAsientoTexto"));
		this.btnCargarAsientoContable.setToolTipText(MessagesContabilidad.getString(NOMBRE_RECURSO+".btnCargarAsientoTextoToolTip"));
		this.btnCargarAsientoContable.setBounds(
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.BTN_WIDTH*2,
				ConstantesTamanio.BTN_HEIGHT);
		this.pnlAtributos.add(this.btnCargarAsientoContable);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.TF_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y+
				ConstantesTamanio.BTN_HEIGHT));
		
		this.getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		pnlResultados = new PanelResultados();

		
		this.getPnlTabla().getTblDatos().getTableHeader().setReorderingAllowed(false) ;
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
	    this.getPnlCuerpo().add(this.getPnlTabla(), BorderLayout.CENTER);
		this.getPnlCuerpo().add(pnlResultados,BorderLayout.SOUTH);
		
		this.getPnlCuerpo().add(this.pnlBtnTabla, BorderLayout.EAST);

		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
//				ConstantesPosicion.COLUMN_LBL_POS_INI_X+ConstantesTamanio.TF_WIDTH+ConstantesSeparacion.INCREMENTO_Y+
//				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH
				750
				,600));
		this.setLocationRelativeTo(null);
	}

	public PanelResultados getPnlResultados() {
		return pnlResultados;
	}

	public void setPnlResultados(PanelResultados pnlResultados) {
		this.pnlResultados = pnlResultados;
	}

	public LineaAsientoContableTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LineaAsientoContableTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JButton getBtnGenerarAsientoContable() {
		return btnGenerarAsientoContable;
	}

	public JComboBox getCbTipoSubdiarioCaja() {
		return cbTipoSubdiarioCaja;
	}

	public JFormattedTextField getFtfFecha() {
		return ftfFecha;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getLblNumeroAsiento() {
		return lblNumeroAsiento;
	}

	public JLabel getLblTipoSubdiarioCaja() {
		return lblTipoSubdiarioCaja;
	}

	public JTextField getTfNumeroAsiento() {
		return tfNumeroAsiento;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public AsientoContableABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsientoContableABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JComboBox getCbFolioLibroDiario() {
		return cbFolioLibroDiario;
	}

	public JLabel getLblLibroDiario() {
		return lblLibroDiario;
	}

	public JLabel getLblObservaciones() {
		return lblObservaciones;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionLibroDiario() {
		return pnlBotonesSeleccionLibroDiario;
	}

	public JTextArea getTaObservaciones() {
		return taObservaciones;
	}

	public JTextField getTfLibroDiario() {
		return tfLibroDiario;
	}

	public JLabel getLblFolioLibroDiario() {
		return lblFolioLibroDiario;
	}

	public JButton getBtnCargarAsientoContable() {
		return btnCargarAsientoContable;
	}

	public PnlVerticalBotones getPnlBtnTabla() {
		return pnlBtnTabla;
	}

	public PnlAyuda getPnlAyudaDevengamiento() {
		return pnlAyudaDevengamiento;
	}

}
