package com.trascender.contabilidad.gui.abmOrdenPago.abm;

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

import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoABMModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccionMovimientoBancario;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TFormattedTextFieldDate;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMOrdenPagoView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private OrdenPagoABMModel abmModel;
	private OrdenPagoTableModel tableModel;
	
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTablaFactura;
	private PnlTabla pnlTablaCheque;
	private PnlTabla pnlTablaDebito;
	private PnlVerticalBotones pnlBotonesTablaFactura;
	private PnlVerticalBotones pnlBotonesTblCheque;
	private PnlVerticalBotones pnlBotonesTblDebito;

	
	private JLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmision;
	private JLabel lblFechaPago;
	private JFormattedTextField tfFechaPago;
	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBtnSeleccionProveedor;
	private JLabel lblMovimientoBancario;
	private PnlBotonesSeleccionMovimientoBancario pnlBtnSeleccionMovimientoBancario;
	private JLabel lblRetencion;
	private JTextField tfRetencion;
	private PnlBotonesSeleccion pnlBtnSeleccionRetencion;
	
	
	private final String NOMBRE_RECURSO = "ABMOrdenPago";
	private MaskFormatter formatter;

	public ABMOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMOrdenPagoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTablaFactura = new PnlTabla();
		this.pnlBotonesTablaFactura = new PnlVerticalBotones();
	
		JPanel pnlTablass = new JPanel();
		pnlTablass.setLayout(new BoxLayout(pnlTablass,BoxLayout.LINE_AXIS)); //Contenedor de las dos tablas con los botones

		JLabel locLblTituloFactura= new JLabel("Factura");
		locLblTituloFactura.setFont(new Font(locLblTituloFactura.getFont().getFontName(),Font.BOLD,12));
		locLblTituloFactura.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		
		JPanel locPnlTablaFactura = new JPanel(); //Contenedor de la tabla de Factura con sus botones
		locPnlTablaFactura.setLayout(new BoxLayout(locPnlTablaFactura, BoxLayout.LINE_AXIS));
		locPnlTablaFactura.add(this.pnlTablaFactura);
		locPnlTablaFactura.add(this.pnlBotonesTablaFactura);
		this.pnlBotonesTablaFactura.setPreferredSize(new Dimension(190,600));

		JPanel locPnlContenedorTblFactura = new JPanel();
		locPnlContenedorTblFactura.setLayout(new BoxLayout(locPnlContenedorTblFactura, BoxLayout.PAGE_AXIS));
		locPnlContenedorTblFactura.add(locLblTituloFactura);
		locPnlContenedorTblFactura.add(locPnlTablaFactura);
		
		
		JLabel locLblTituloCheque = new JLabel("Cheque");
		locLblTituloCheque.setFont(new Font(locLblTituloCheque.getFont().getFontName(),Font.BOLD,12));
		locLblTituloCheque.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				
		JPanel locPnlTablaCheque = new JPanel(); //Contenedor de la tabla Cheque con sus botones
		locPnlTablaCheque.setLayout(new BoxLayout(locPnlTablaCheque, BoxLayout.LINE_AXIS));
		this.pnlTablaCheque = new PnlTabla();
		locPnlTablaCheque.add(this.pnlTablaCheque);
		this.pnlBotonesTblCheque = new PnlVerticalBotones();
		this.pnlBotonesTblCheque.getBtnAgregar().setText("Cheque");
		this.pnlBotonesTblCheque.getBtnModificar().setVisible(false);
		this.pnlBotonesTblCheque.setPreferredSize(new Dimension(190,600));
		locPnlTablaCheque.add(this.pnlBotonesTblCheque);

		
		JLabel locLblTituloDebito= new JLabel("Débito Bancario");
		locLblTituloDebito.setFont(new Font(locLblTituloDebito.getFont().getFontName(),Font.BOLD,12));
		locLblTituloDebito.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		
		
		JPanel locPnlTablaDebito = new JPanel(); //Contenedor de la tabla Debito con sus botones
		locPnlTablaDebito.setLayout(new BoxLayout(locPnlTablaDebito, BoxLayout.LINE_AXIS));
		this.pnlTablaDebito = new PnlTabla();
		locPnlTablaDebito.add(this.pnlTablaDebito);
		this.pnlBotonesTblDebito = new PnlVerticalBotones();
		this.pnlBotonesTblDebito.getBtnAgregar().setText("Débito");
		this.pnlBotonesTblDebito.getBtnModificar().setVisible(false);
		this.pnlBotonesTblDebito.setPreferredSize(new Dimension(190,600));
		locPnlTablaDebito.add(this.pnlBotonesTblDebito);


		JPanel locPnlContenedorTblsMovimientosBancarios = new JPanel(); //Contenedor de las dos tablas(Paneles) de movimientos bancarios
		locPnlContenedorTblsMovimientosBancarios.setLayout(new BoxLayout(locPnlContenedorTblsMovimientosBancarios, BoxLayout.Y_AXIS));
		locPnlContenedorTblsMovimientosBancarios.add(locLblTituloCheque);
		locPnlContenedorTblsMovimientosBancarios.add(locPnlTablaCheque);
		locPnlContenedorTblsMovimientosBancarios.add(locLblTituloDebito);
		locPnlContenedorTblsMovimientosBancarios.add(locPnlTablaDebito);
		
		
		pnlTablass.add(locPnlContenedorTblFactura);
		pnlTablass.add(locPnlContenedorTblsMovimientosBancarios);
	
		//-------------------------------------------------------------------------------------------------------------
		
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
		this.lblProveedor = new TLabel();
		this.lblProveedor.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblProveedor"));
		this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblProveedor);
	
		this.tfProveedor = new TFormattedTextFieldDate();
		this.tfProveedor.setEditable(false);
		this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfProveedor);
		
		this.pnlBtnSeleccionProveedor =  new PnlBotonesSeleccion();
		this.pnlBtnSeleccionProveedor.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlAtributos.add(this.pnlBtnSeleccionProveedor);
		
		numFila++;
		this.lblRetencion = new TLabel();
		this.lblRetencion.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblComprobanteRetencion"));
		this.lblRetencion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblRetencion);
	
		this.tfRetencion = new TFormattedTextFieldDate();
		this.tfRetencion.setEditable(false);
		this.tfRetencion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.pnlAtributos.add(this.tfRetencion);
		
		this.pnlBtnSeleccionRetencion =  new PnlBotonesSeleccion();
		this.pnlBtnSeleccionRetencion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.pnlBtnSeleccionRetencion.getBtnLimpiar().setVisible(false);
		this.pnlAtributos.add(this.pnlBtnSeleccionRetencion);
		
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+ConstantesTamanio.CBX_HEIGHT*numFila+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(pnlTablass, BorderLayout.CENTER);
		
		//Deshabilitado hasta terminarlo
		this.getPnlBotonesTablaFactura().getBtnModificar().setVisible(false);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
//				ConstantesPosicion.COLUMN_LBL_POS_INI_X+ConstantesTamanio.TF_WIDTH+ConstantesSeparacion.INCREMENTO_Y+
//				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				950,
				600));
		this.setLocationRelativeTo(null);
	}

	public OrdenPagoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(OrdenPagoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTablaFactura().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblMovimientoBancario() {
		return lblMovimientoBancario;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlVerticalBotones getPnlBotonesTablaFactura() {
		return pnlBotonesTablaFactura;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionProveedor() {
		return pnlBtnSeleccionProveedor;
	}

	public PnlTabla getPnlTablaFactura() {
		return pnlTablaFactura;
	}

	public JFormattedTextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public JFormattedTextField getTfFechaPago() {
		return tfFechaPago;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public OrdenPagoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(OrdenPagoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblRetencion() {
		return lblRetencion;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionRetencion() {
		return pnlBtnSeleccionRetencion;
	}

	public JTextField getTfRetencion() {
		return tfRetencion;
	}

	public PnlBotonesSeleccionMovimientoBancario getPnlBtnSeleccionMovimientoBancario() {
		return pnlBtnSeleccionMovimientoBancario;
	}

	public void setPnlBtnSeleccionMovimientoBancario(
			PnlBotonesSeleccionMovimientoBancario pnlBtnSeleccionMovimientoBancario) {
		this.pnlBtnSeleccionMovimientoBancario = pnlBtnSeleccionMovimientoBancario;
	}
	
	public PnlTabla getPnlTablaCheque() {
		return pnlTablaCheque;
	}

	public void setPnlTablaCheque(PnlTabla pnlTablaCheque) {
		this.pnlTablaCheque = pnlTablaCheque;
	}

	public PnlTabla getPnlTablaDebito() {
		return pnlTablaDebito;
	}

	public void setPnlTablaDebito(PnlTabla pnlTablaDebito) {
		this.pnlTablaDebito = pnlTablaDebito;
	}

	public PnlVerticalBotones getPnlBotonesTblCheque() {
		return pnlBotonesTblCheque;
	}

	public void setPnlBotonesTblCheque(PnlVerticalBotones pnlBotonesTblCheque) {
		this.pnlBotonesTblCheque = pnlBotonesTblCheque;
	}

	public PnlVerticalBotones getPnlBotonesTblDebito() {
		return pnlBotonesTblDebito;
	}

	public void setPnlBotonesTblDebito(PnlVerticalBotones pnlBotonesTblDebito) {
		this.pnlBotonesTblDebito = pnlBotonesTblDebito;
	}

}
