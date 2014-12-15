package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlTablaCompleto;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaSolicitudSuministroView extends ABMView {

	private static final long serialVersionUID = -4878787278381801536L;
	
	AsociacionCuentaSolicitudSuministroABMModel abmModel;

	private JPanel pnlAtributos;
		private JLabel lblSolicitudSuministro;
		private JTextField tfSolicitudSuministro;
		private JLabel lblArea;
		private JTextField tfArea;
		private JLabel lblEstado;
		private JTextField tfEsatado;
		private JLabel lblFechaEmsion;
		private JFormattedTextField tfFechaEmision;
		private JLabel lblUsuario;
		private JTextField tfUsuario;
		
	private JPanel PnlContenedorTablas;
		private PnlTablaCompleto pnlTablaFirma;
		private PnlTablaCompleto pnlTablaLineaSS;
	
//	private JLabel lblProducto;
//	private JTextField tfProducto;
//	private JLabel lblCantidad;
//	private JTextField tfCantidad;
//	private JLabel lblValor;
//	private JTextField tfValor;
//	private JLabel lblCuenta;
//	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "ABMAsociacionCuentaSolicitudSuministro";
	private MaskFormatter formatter;
	
	public ABMAsociacionCuentaSolicitudSuministroView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsociacionCuentaSolicitudSuministroView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new JPanel();
		this.pnlAtributos.setLayout(null);
		
		int numFila= -1;
		
		numFila++;
		this.lblSolicitudSuministro = new TLabel();
		this.lblSolicitudSuministro.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblSolicitudSuministro"));
		this.lblSolicitudSuministro.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblSolicitudSuministro);
		
		this.tfSolicitudSuministro = new JTextField();
		this.tfSolicitudSuministro.setEditable(false);
		this.tfSolicitudSuministro.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlAtributos().add(this.tfSolicitudSuministro);
		
		numFila++;
		this.lblArea = new TLabel();
		this.lblArea.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblArea"));
		this.lblArea.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblArea);
		
		this.tfArea = new JTextField();
		this.tfArea.setEditable(false);
		this.tfArea.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfArea);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblEstado);
		
		this.tfEsatado = new JTextField();
		this.tfEsatado.setEditable(false);
		this.tfEsatado.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfEsatado);
		
		numFila++;
		this.lblFechaEmsion = new TLabel();
		this.lblFechaEmsion.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaEmision"));
		this.lblFechaEmsion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaEmsion);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaEmision = new JFormattedTextField(this.formatter);
		this.tfFechaEmision.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.tfFechaEmision.setEditable(false);
		this.pnlAtributos.add(this.tfFechaEmision);
		
//		numFila++;
//		this.lblProducto = new TLabel();
//		this.lblProducto.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblProducto"));
//		this.lblProducto.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlCuerpo().add(this.lblProducto);
//		
//		this.tfProducto = new JTextField();
//		this.tfProducto.setEditable(false);
//		this.tfProducto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlCuerpo().add(this.tfProducto);
//		
//		numFila++;
//		this.lblCantidad = new TLabel();
//		this.lblCantidad.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCantidad"));
//		this.lblCantidad.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlCuerpo().add(this.lblCantidad);
//		
//		this.tfCantidad = new JTextField();
//		this.tfCantidad.setEditable(false);
//		this.tfCantidad.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlCuerpo().add(this.tfCantidad);
//
//		numFila++;
//		this.lblValor = new TLabel();
//		this.lblValor.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblValor"));
//		this.lblValor.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlCuerpo().add(this.lblValor);
//		
//		this.tfValor = new JTextField();
//		this.tfValor.setEditable(false);
//		this.tfValor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlCuerpo().add(this.tfValor);
		
		numFila++;
		this.lblUsuario = new TLabel();
		this.lblUsuario.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblUsuario"));
		this.lblUsuario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlAtributos().add(this.lblUsuario);
		
		this.tfUsuario = new JTextField();
		this.tfUsuario.setEditable(false);
		this.tfUsuario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlAtributos().add(this.tfUsuario);
		
		this.pnlAtributos.setPreferredSize(new Dimension(ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				numFila * ConstantesSeparacion.INCREMENTO_Y +
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
				
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		
		this.PnlContenedorTablas = new JPanel();
		this.PnlContenedorTablas.setLayout(new BoxLayout(this.PnlContenedorTablas, BoxLayout.Y_AXIS));
			
			this.pnlTablaFirma = new PnlTablaCompleto();
			this.pnlTablaFirma.getLblTitulo().setText("Firmas");
			this.pnlTablaFirma.getPnlVerticalBotones().setVisible(false);
			this.pnlTablaFirma.setPreferredSize(new Dimension(200,200));
			
			this.pnlTablaLineaSS = new PnlTablaCompleto();
			this.pnlTablaLineaSS.getLblTitulo().setText("Líneas de la Solicitud de Suministros");
			this.pnlTablaLineaSS.setPreferredSize(new Dimension(200,200));
			
		this.PnlContenedorTablas.add(this.pnlTablaFirma);
		this.PnlContenedorTablas.add(this.pnlTablaLineaSS);
		
		this.getPnlCuerpo().add(this.PnlContenedorTablas, BorderLayout.CENTER);
		
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setColorFondo();
		this.setTamanioPosicionVentana(numFila+1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
				ConstantesTamanio.TF_WIDTH+
				ConstantesSeparacion.SEPARADOR_HORIZONTAL+
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				700));
		this.setLocationRelativeTo(null);
	}

	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaSolicitudSuministroABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JPanel getPnlAtributos() {
		return pnlAtributos;
	}

	public JPanel getPnlContenedorTablas() {
		return PnlContenedorTablas;
	}

	public PnlTablaCompleto getPnlTablaFirma() {
		return pnlTablaFirma;
	}

	public PnlTablaCompleto getPnlTablaLineaSS() {
		return pnlTablaLineaSS;
	}

	public JLabel getLblSolicitudSuministro() {
		return lblSolicitudSuministro;
	}

	public JTextField getTfSolicitudSuministro() {
		return tfSolicitudSuministro;
	}

//	public JTextField getTfProducto() {
//		return tfProducto;
//	}
//
//	public JLabel getLblCantidad() {
//		return lblCantidad;
//	}
//
//	public JTextField getTfCantidad() {
//		return tfCantidad;
//	}
//
//	public JLabel getLblValor() {
//		return lblValor;
//	}
//
//	public JTextField getTfValor() {
//		return tfValor;
//	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public JLabel getLblCuenta() {
		return lblArea;
	}

	public JTextField getTfCuenta() {
		return tfArea;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public JTextField getTfArea() {
		return tfArea;
	}

	public JTextField getTfEsatado() {
		return tfEsatado;
	}

	public JFormattedTextField getTfFechaEmision() {
		return tfFechaEmision;
	}
	
	
	
}
