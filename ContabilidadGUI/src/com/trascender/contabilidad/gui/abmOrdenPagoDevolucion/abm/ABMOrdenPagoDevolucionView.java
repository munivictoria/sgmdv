package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmOrdenPagoDevolucion.OrdenPagoDevolucionABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccionPersona;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TFormattedTextFieldDate;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMOrdenPagoDevolucionView extends ABMView {

	private static final long serialVersionUID = -8646486702682179506L;

	private OrdenPagoDevolucionABMModel abmModel;
//	private OrdenPagoDevolucionTableModel tableModel;
	
	private PnlABMAtributos pnlAtributos;
		private JLabel lblFechaEmision;
		private JFormattedTextField tfFechaEmision;
		private JLabel lblFechaPago;
		private JFormattedTextField tfFechaPago;
		private JLabel lblPersona;
		private JTextField tfPersona;
		private PnlBotonesSeleccionPersona pnlBtnSeleccionPersona;
	
	private JPanel pnlContenedorTablas;
		private JPanel locPnlContenedorTblOrdenPagoDev;
			private JLabel lblTituloLineaOrdenPagoDev;
			private JPanel pnlContenedorLineaOPD;
				private PnlTabla pnlTablaLineaOrdenPagoDev;
				private PnlVerticalBotones pnlBotonesTblLineaOrdenPagoDev;
		
		private JPanel locPnlContenedorTblsMovimientosBancarios;
			private JLabel lblTituloCheque;
			private	JPanel pnlContenedorCheque;
				private PnlTabla pnlTablaCheque;
				private PnlVerticalBotones pnlBotonesTblCheque;
			
			private JLabel lblTituloDebito;
			private	JPanel pnlContenedorDebito;
				private PnlTabla pnlTablaDebito;
				private PnlVerticalBotones pnlBotonesTblDebito;
	
	private final String NOMBRE_RECURSO = "ABMOrdenPagoDevolucion";
	private MaskFormatter formatter;
	
	public ABMOrdenPagoDevolucionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMOrdenPagoDevolucionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		
		// ------------------ Aca se arma el panel para los atributos -------------------
		
		int numFila = -1;
		
		numFila++;
		this.lblFechaEmision = new TLabel();
		this.lblFechaEmision.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaEmision"));
		this.lblFechaEmision.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaEmision);
	
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaEmision = new JFormattedTextField(this.formatter);
		this.tfFechaEmision.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.tfFechaEmision);
		
		numFila++;
		this.lblFechaPago = new TLabel();
		this.lblFechaPago.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaPago"));
		this.lblFechaPago.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaPago);
	
		this.tfFechaPago = new JFormattedTextField(this.formatter);
		this.tfFechaPago.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.pnlAtributos.add(this.tfFechaPago);
		
		numFila++;
		this.lblPersona = new TLabel();
		this.lblPersona.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblPersona"));
		this.lblPersona.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblPersona);
	
		this.tfPersona = new TFormattedTextFieldDate();
		this.tfPersona.setEditable(false);
		this.tfPersona.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfPersona);
		
		this.pnlBtnSeleccionPersona =  new PnlBotonesSeleccionPersona();
		this.pnlBtnSeleccionPersona.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
		this.pnlAtributos.add(this.pnlBtnSeleccionPersona);
		
			
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		
		// ------------------ Aca se arma el panel para las tablas varias -------------------
		
		this.pnlContenedorTablas = new JPanel();
		this.pnlContenedorTablas.setLayout(new BoxLayout(this.pnlContenedorTablas,BoxLayout.LINE_AXIS)); //Contenedor de las dos tablas con los botones
				
			this.locPnlContenedorTblOrdenPagoDev = new JPanel();
			this.locPnlContenedorTblOrdenPagoDev.setLayout(new BoxLayout(this.locPnlContenedorTblOrdenPagoDev, BoxLayout.PAGE_AXIS));
	
				this.lblTituloLineaOrdenPagoDev= new JLabel("Líneas Orden de Pago por Devolución");
				this.lblTituloLineaOrdenPagoDev.setFont(new Font(this.lblTituloLineaOrdenPagoDev.getFont().getFontName(),Font.BOLD,12));
				
				this.pnlContenedorLineaOPD = new JPanel(); //Contenedor de la tabla de lineas de ordenes de pago por devolucion con sus botones
				this.pnlContenedorLineaOPD.setLayout(new BorderLayout());
				
					this.pnlTablaLineaOrdenPagoDev = new PnlTabla();
					this.pnlBotonesTblLineaOrdenPagoDev = new PnlVerticalBotones();	
					this.pnlBotonesTblLineaOrdenPagoDev.getBtnModificar().setVisible(false);
					this.pnlBotonesTblLineaOrdenPagoDev.getBtnQuitarTodos().setVisible(false);
				
				this.pnlContenedorLineaOPD.add(this.pnlTablaLineaOrdenPagoDev, BorderLayout.CENTER);
				this.pnlContenedorLineaOPD.add(this.pnlBotonesTblLineaOrdenPagoDev, BorderLayout.EAST);
			
			this.locPnlContenedorTblOrdenPagoDev.add(this.lblTituloLineaOrdenPagoDev);
			this.locPnlContenedorTblOrdenPagoDev.add(this.pnlContenedorLineaOPD);
			
	//*********		
			
			this.locPnlContenedorTblsMovimientosBancarios = new JPanel(); //Contenedor de las dos tablas(Paneles) de movimientos bancarios
			this.locPnlContenedorTblsMovimientosBancarios.setLayout(new BoxLayout(locPnlContenedorTblsMovimientosBancarios, BoxLayout.Y_AXIS));
			
				this.lblTituloCheque = new JLabel("Cheque");
				this.lblTituloCheque.setFont(new Font(this.lblTituloCheque.getFont().getFontName(),Font.BOLD,12));
				
				this.pnlContenedorCheque = new JPanel(); //Contenedor de la tabla Cheque con sus botones
				this.pnlContenedorCheque.setLayout(new BorderLayout());
					this.pnlTablaCheque = new PnlTabla();
					this.pnlBotonesTblCheque = new PnlVerticalBotones();
					this.pnlBotonesTblCheque.getBtnAgregar().setText("Cheque");
					this.pnlBotonesTblCheque.getBtnModificar().setVisible(false);
				this.pnlContenedorCheque.add(this.pnlTablaCheque, BorderLayout.CENTER);
				this.pnlContenedorCheque.add(this.pnlBotonesTblCheque, BorderLayout.EAST);
				
				this.lblTituloDebito= new JLabel("Débito Bancario");
				this.lblTituloDebito.setFont(new Font(this.lblTituloDebito.getFont().getFontName(),Font.BOLD,12));
				
				this.pnlContenedorDebito = new JPanel(); //Contenedor de la tabla Debito con sus botones
				this.pnlContenedorDebito.setLayout(new BorderLayout());
					this.pnlTablaDebito = new PnlTabla();
					this.pnlBotonesTblDebito = new PnlVerticalBotones();
					this.pnlBotonesTblDebito.getBtnAgregar().setText("Débito");
					this.pnlBotonesTblDebito.getBtnModificar().setVisible(false);
				this.pnlContenedorDebito.add(this.pnlTablaDebito, BorderLayout.CENTER);
				this.pnlContenedorDebito.add(this.pnlBotonesTblDebito, BorderLayout.EAST);
		
			this.locPnlContenedorTblsMovimientosBancarios.add(this.lblTituloCheque);
			this.locPnlContenedorTblsMovimientosBancarios.add(this.pnlContenedorCheque);
			this.locPnlContenedorTblsMovimientosBancarios.add(this.lblTituloDebito);
			this.locPnlContenedorTblsMovimientosBancarios.add(this.pnlContenedorDebito);
		
		
		this.pnlContenedorTablas.add(this.locPnlContenedorTblOrdenPagoDev);
		this.pnlContenedorTablas.add(this.locPnlContenedorTblsMovimientosBancarios);
	
		//-------------------- Aca se arma el cuerpo final del la ventana ------------------
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlContenedorTablas, BorderLayout.CENTER);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(950, 600));
		this.setLocationRelativeTo(null);
	}
	
	public OrdenPagoDevolucionABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(OrdenPagoDevolucionABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JFormattedTextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public JFormattedTextField getTfFechaPago() {
		return tfFechaPago;
	}

	public JLabel getLblPersona() {
		return lblPersona;
	}

	public JTextField getTfPersona() {
		return tfPersona;
	}

	public PnlBotonesSeleccionPersona getPnlBtnSeleccionPersona() {
		return pnlBtnSeleccionPersona;
	}

	public JPanel getPnlContenedorTablas() {
		return pnlContenedorTablas;
	}

	public JPanel getLocPnlContenedorTblOrdenPagoDev() {
		return locPnlContenedorTblOrdenPagoDev;
	}

	public JLabel getLblTituloLineaOrdenPagoDev() {
		return lblTituloLineaOrdenPagoDev;
	}

	public JPanel getPnlContenedorLineaOPD() {
		return pnlContenedorLineaOPD;
	}

	public PnlTabla getPnlTablaLineaOrdenPagoDev() {
		return pnlTablaLineaOrdenPagoDev;
	}

	public PnlVerticalBotones getPnlBotonesTblLineaOrdenPagoDev() {
		return pnlBotonesTblLineaOrdenPagoDev;
	}

	public JPanel getLocPnlContenedorTblsMovimientosBancarios() {
		return locPnlContenedorTblsMovimientosBancarios;
	}

	public JLabel getLblTituloCheque() {
		return lblTituloCheque;
	}

	public JPanel getPnlContenedorCheque() {
		return pnlContenedorCheque;
	}

	public PnlTabla getPnlTablaCheque() {
		return pnlTablaCheque;
	}

	public PnlVerticalBotones getPnlBotonesTblCheque() {
		return pnlBotonesTblCheque;
	}

	public JLabel getLblTituloDebito() {
		return lblTituloDebito;
	}

	public JPanel getPnlContenedorDebito() {
		return pnlContenedorDebito;
	}

	public PnlTabla getPnlTablaDebito() {
		return pnlTablaDebito;
	}

	public PnlVerticalBotones getPnlBotonesTblDebito() {
		return pnlBotonesTblDebito;
	}
	
}
