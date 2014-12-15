package com.trascender.contabilidad.gui.abmFactura.abm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmFactura.FacturaContratoABMModel;
import com.trascender.contabilidad.gui.abmFactura.LineaFacturaTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlTablaCompleto;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public class ModificarFacturaContratoView extends ABMView {

	private static final long serialVersionUID = 1834171150343699166L;

	private FacturaContratoABMModel abmModel;
	private LineaFacturaTableModel tableModel;

	private JPanel pnlContenedor;
		private JPanel pnlAtributos;
			private JLabel lblContrato;
			private JTextField tfContrato;
			private JLabel lblProveedor;
			private JTextField tfProveedor;
			private JLabel lblFecha;
			private JFormattedTextField tfFecha;
			private JLabel lblNumeroFactura;
			private JTextField tfNumeroFactura;
			private JLabel lblTipoFactura;
			private JComboBox cmbTipoFactura;
			private JLabel lblEstado;
			private JComboBox cmbEstado;
		
		private JPanel PnlContenedorTablas;
			private PnlTablaCompleto pnlTablaRemito;
			private PnlTablaCompleto pnlTablaLineaFP;
		
		private JPanel pnlTotal;
			private JLabel lblTotal;
			private TFormattedTextFieldImporte tfTotal;
	
	private final String NOMBRE_RECURSO ="ModificarFacturaContrato";
	private MaskFormatter formatter;
	
	public ModificarFacturaContratoView(JDialog owner) throws Exception {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTituloVentana();
		this.init();
	}
	
	public ModificarFacturaContratoView(JFrame owner) throws Exception {
		this.setImagenCabecera();
		this.setColorFondo();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTituloVentana();
		this.init();
	}
	
	private void init() {
		this.pnlContenedor = new JPanel();
		this.pnlContenedor.setLayout(new BorderLayout());
		this.pnlContenedor.setPreferredSize(new Dimension(200,300));
			this.pnlAtributos = new JPanel();
			this.pnlAtributos.setLayout(null);
				int numFila = -1;
			
				numFila++;
				this.lblContrato = new TLabel();
				this.lblContrato.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblContrato"));
				this.lblContrato.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlAtributos.add(this.lblContrato);
				
				this.tfContrato = new JTextField();
				this.tfContrato.setEditable(false);
				this.tfContrato.setBounds(Util.getBoundsColumnaInputTextField(numFila));
				this.pnlAtributos.add(this.tfContrato);
				
				numFila++;
				this.lblProveedor = new TLabel();
				this.lblProveedor.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblProveedor"));
				this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlAtributos.add(this.lblProveedor);
				
				this.tfProveedor = new JTextField();
				this.tfProveedor.setEditable(false);
				this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
				this.pnlAtributos.add(this.tfProveedor);
				
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
				
				this.tfFecha = new JFormattedTextField(this.formatter);
				this.tfFecha.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
				this.tfFecha.setEditable(false);
				this.pnlAtributos.add(this.tfFecha);
				
				numFila++;
				this.lblNumeroFactura = new TLabel();
				this.lblNumeroFactura.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroFactura"));
				this.lblNumeroFactura.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlAtributos.add(this.lblNumeroFactura);
		
				this.tfNumeroFactura = new JTextField();
		//		this.tfImporte.setHorizontalAlignment(JTextField.RIGHT);
				this.tfNumeroFactura.setBounds(Util.getBoundsColumnaInputTextField(numFila));
				this.tfNumeroFactura.setEditable(false);
				this.pnlAtributos.add(this.tfNumeroFactura);
		
				numFila++;
				this.lblTipoFactura = new TLabel();
				this.lblTipoFactura.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTipoFactura"));
				this.lblTipoFactura.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlAtributos.add(this.lblTipoFactura);
				
				this.cmbTipoFactura = new JComboBox();
				this.cmbTipoFactura.setEnabled(false);
				this.cmbTipoFactura.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
				this.pnlAtributos.add(this.cmbTipoFactura);
				
				numFila++;
				this.lblEstado = new TLabel();
				this.lblEstado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblEstado"));
				this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlAtributos.add(this.lblEstado);
				
				this.cmbEstado = new JComboBox();
				this.cmbEstado.setEnabled(false);
				this.cmbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
				this.pnlAtributos.add(this.cmbEstado);
				
				numFila++;
			this.pnlAtributos.setPreferredSize(new Dimension(ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
					ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
					ConstantesTamanio.TF_WIDTH +
					ConstantesSeparacion.SEPARADOR_HORIZONTAL,
					numFila * ConstantesSeparacion.INCREMENTO_Y +
					ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		
			this.PnlContenedorTablas = new JPanel();
			this.PnlContenedorTablas.setLayout(new BoxLayout(this.PnlContenedorTablas, BoxLayout.Y_AXIS));
				
//				this.pnlTablaRemito = new PnlTablaCompleto();
//				this.pnlTablaRemito.getLblTitulo().setText("Remitos");
//				this.pnlTablaRemito.getPnlVerticalBotones().setVisible(false);
//				this.pnlTablaRemito.setPreferredSize(new Dimension(200,200));
				
				this.pnlTablaLineaFP = new PnlTablaCompleto();
				this.pnlTablaLineaFP.getLblTitulo().setText("Lista de Facturas del Proveedor");
				this.pnlTablaLineaFP.setPreferredSize(new Dimension(200,200));
				
//			this.PnlContenedorTablas.add(this.pnlTablaRemito);
			this.PnlContenedorTablas.add(this.pnlTablaLineaFP);
			
			this.pnlTotal = new JPanel();
			this.pnlTotal.setLayout(null);
			numFila = -1;
				numFila++;
				this.lblTotal = new TLabel();
				this.lblTotal.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTotal"));
				this.lblTotal.setBounds(Util.getBoundsColumnaLabel(numFila));
				this.pnlTotal.add(this.lblTotal);
				
				this.tfTotal = new TFormattedTextFieldImporte();
				this.tfTotal.setEditable(false);
				this.tfTotal.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
				this.pnlTotal.add(this.tfTotal);
			
				numFila++;
			this.pnlTotal.setPreferredSize(new Dimension(new Dimension(ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
					ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
					ConstantesTamanio.TF_WIDTH +
					ConstantesSeparacion.SEPARADOR_HORIZONTAL,
					numFila * ConstantesSeparacion.INCREMENTO_Y +
					ConstantesPosicion.COLUMN_LBL_POS_INI_Y)));
		
		
		this.pnlContenedor.add(this.pnlAtributos, BorderLayout.NORTH);
		this.pnlContenedor.add(this.PnlContenedorTablas, BorderLayout.CENTER);
		this.pnlContenedor.add(this.pnlTotal, BorderLayout.SOUTH);
		
		this.getPnlCuerpo().setLayout(new BorderLayout());
		this.getPnlCuerpo().add(this.pnlContenedor, BorderLayout.CENTER);
		this.setTamanioPosicionVentana(numFila + 1);
	}	
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_MODIFICAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("ModificarFacturaContrato.descripcion"));
	}

	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(new Dimension(600, 750));
		this.setLocationRelativeTo(null);
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(Messages.getString("Application.btnModificarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(Messages.getString("Application.btnModificarTextoMnemonic").charAt(0));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("ModificarFacturaContrato.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_MODIFICAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}

	public FacturaContratoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(FacturaContratoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public LineaFacturaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LineaFacturaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTablaLineaFP().getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JPanel getPnlContenedor() {
		return pnlContenedor;
	}

	public JPanel getPnlContenedorTablas() {
		return PnlContenedorTablas;
	}

	public PnlTablaCompleto getPnlTablaLineaFP() {
		return pnlTablaLineaFP;
	}
	
	public JPanel getPnlAtributos() {
		return pnlAtributos;
	}

	public JTextField getTfContrato() {
		return tfContrato;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public JFormattedTextField getTfFecha() {
		return tfFecha;
	}

	public JTextField getTfNumeroFactura() {
		return tfNumeroFactura;
	}

	public JComboBox getCmbTipoFactura() {
		return cmbTipoFactura;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public PnlTablaCompleto getPnlTablaRemito() {
		return pnlTablaRemito;
	}

	public JPanel getPnlTotal() {
		return pnlTotal;
	}

	public TFormattedTextFieldImporte getTfTotal() {
		return tfTotal;
	}
	
}
