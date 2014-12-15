package com.trascender.caja.gui.cobro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlTablaCompleto;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;
/**
 * @author adrian
 */
public class CobroView extends ABMView {

	private static final long serialVersionUID = 5551867054961579722L;
	private CobroTableModel tableModel;
	private PnlABMAtributos pnlAtributos;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlBtnTabla;	
	private JLabel lblCodigoLiquidacion;
	private JTextField tfCodigoLiquidacion;
	private JButton btnAgregarLiquidacion;
	private JLabel lblMonto;
	private JTextField tfMonto;
	private JLabel lblTotalPagado;
	private JLabel lblVuelto;
	private JTextField tfVuelto;
	private JTextField tfTotalPagado;
	private JButton btnQuitar;
	private JButton btnComponerPago;
	private PnlTablaCompleto pnlTablaPagos;
	private JButton btnPagoCheque;
	private JButton btnPagoDeposito;
	private final String NOMBRE_RECURSO = "Cobro";
	
	public CobroView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public CobroView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.getPnlCuerpo().setLayout(new BorderLayout());
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlTabla = new PnlTabla();
		this.pnlBtnTabla = new PnlVerticalBotones();
		int numFila = -1;
		
		numFila++;
		this.lblCodigoLiquidacion = new TLabel();
		this.lblCodigoLiquidacion.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblCodigoLiquidacion"));
		this.lblCodigoLiquidacion.setBounds(new Rectangle(ConstantesPosicion.COLUMN_LBL_POS_INI_X,
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				120, 
				ConstantesTamanio.LBL_HEIGHT));
		this.pnlAtributos.add(this.lblCodigoLiquidacion);
		
		this.tfCodigoLiquidacion = new JTextField();
		this.tfCodigoLiquidacion.setBounds(new Rectangle(ConstantesPosicion.COLUMN_INPUT_POS_INI_X-110, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.TF_WIDTH, 
				ConstantesTamanio.BTN_HEIGHT));
		this.pnlAtributos.add(this.tfCodigoLiquidacion);
		
		this.btnAgregarLiquidacion = new JButton();
		
		this.btnAgregarLiquidacion.setText(MessagesCaja.getString("Application.btnAgregar"));
		this.btnAgregarLiquidacion.setBounds(new Rectangle (ConstantesPosicion.COLUMN_INPUT_POS_INI_X -110+ ConstantesTamanio.TF_WIDTH + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y, 
				ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnAgregarLiquidacion.setMnemonic('g');
		
		this.pnlAtributos.add(this.btnAgregarLiquidacion);
		this.pnlAtributos.setPreferredSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesSeparacion.INCREMENTO_Y*2+numFila));
		
		int numFilaPnlVertical = -1;
		
		numFilaPnlVertical++;
		numFilaPnlVertical++;
		numFilaPnlVertical++;
		this.btnQuitar = new JButton();
		this.btnQuitar.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".btnQuitar"));
		this.btnQuitar.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.btnQuitar);
		
		numFilaPnlVertical++;
		this.btnComponerPago = new JButton();
		this.btnComponerPago.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".btnComponerPago"));
		this.btnComponerPago.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.btnComponerPago);
		
		numFilaPnlVertical++;
		this.lblMonto = new JLabel();
		this.lblMonto.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblMonto"));
		this.lblMonto.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.lblMonto);
		
		numFilaPnlVertical++;
		this.tfMonto = new JTextField();
		this.tfMonto.setEnabled(true);
		this.tfMonto.setEditable(false);
		this.tfMonto.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.tfMonto);
		
		numFilaPnlVertical++;
		this.lblTotalPagado = new JLabel();
		this.lblTotalPagado.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblTotalPagado"));
		this.lblTotalPagado.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.lblTotalPagado);
		
		numFilaPnlVertical++;
		this.tfTotalPagado = new JTextField();
		this.tfTotalPagado.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));
		this.pnlBtnTabla.add(this.tfTotalPagado);
		
		numFilaPnlVertical++;
		this.lblVuelto = new JLabel();//
		this.lblVuelto.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblVuelto"));//
		this.lblVuelto.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));//
		this.pnlBtnTabla.add(this.lblVuelto);//
		
		numFilaPnlVertical++;//
		this.tfVuelto = new JTextField();//
		this.tfVuelto.setEnabled(true);//
		this.tfVuelto.setEditable(false);
		this.tfVuelto.setBounds(Util.getBoundsBotonesPnlVertical(numFilaPnlVertical));//
		this.pnlBtnTabla.add(this.tfVuelto);//
		
		this.pnlBtnTabla.getBtnAgregar().setVisible(true);
//		this.pnlBtnTabla.getBtnAgregar().setVisible(false);
		this.pnlBtnTabla.getBtnEliminar().setVisible(false);
		this.pnlBtnTabla.getBtnModificar().setVisible(false);
		this.pnlBtnTabla.getBtnQuitarTodos().setVisible(false);
		
		this.getPnlCuerpo().add(this.pnlAtributos, BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.pnlTabla, BorderLayout.CENTER);
//		this.getPnlCuerpo().add(this.pnlBtnTabla, BorderLayout.EAST);
		
		
		btnPagoDeposito = new JButton();
		btnPagoDeposito.setText("Depósito");
		
		btnPagoCheque = new JButton();
		btnPagoCheque.setText("Cheque");

		pnlTablaPagos = new PnlTablaCompleto(btnPagoCheque, btnPagoDeposito);
		pnlTablaPagos.setBorder(null);
		pnlTablaPagos.setPreferredSize(new Dimension(400, 50));
		pnlTablaPagos.getPnlVerticalBotones().getBtnAgregar().setText("Compensación");
		pnlTablaPagos.getPnlVerticalBotones().getBtnModificar().setText("Efectivo");
		
		pnlTablaPagos.getLblTitulo().setText("Agregar pago por:");
		pnlTablaPagos.setVisible(false);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.add(this.pnlBtnTabla, BorderLayout.CENTER);
		panelAux.add(this.pnlTablaPagos, BorderLayout.EAST);
		
		this.getPnlCuerpo().add(panelAux, BorderLayout.EAST);
		
		
		this.getPnlPie().getBtnImprimir().setVisible(true);
		this.setTextoBtnImprimir();
		this.setTextoBtnDetalle();
		
		setTamanioPosicionVentana(65);		
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
//		this.setSize(new Dimension(
//				ConstantesPosicion.COLUMN_LBL_POS_INI_X*10+ConstantesTamanio.TF_WIDTH+ConstantesPosicion.COLUMN_INPUT_POS_INI_X + ConstantesTamanio.BTN_WIDTH,
//				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilasComponentes*
//				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y + 300));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(Math.round(dimension.width * (pCantidadFilasComponentes / 100f)) , 
				Math.round(dimension.height * (pCantidadFilasComponentes / 100f)));
		this.setLocationRelativeTo(null);
	}

	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlPie().setBackground(color);
		this.getPnlCabecera().setBackground(color);
		
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString("Cobro.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesCaja.getString("Application.btnCobrar"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesCaja.getChar(("Application.btnCobrarMnemonic")));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesCaja.getString("Application.btnCobrarToolTip"));		
	}
	
	public void setTextoBtnDetalle() {
		this.getPnlBtnTabla().getBtnAgregar().setText(MessagesCaja.getString("Application.btnDetalle"));
		this.getPnlBtnTabla().getBtnAgregar().setMnemonic(MessagesCaja.getChar(("Application.btnDetalleMnemonic")));
		this.getPnlBtnTabla().getBtnAgregar().setToolTipText(MessagesCaja.getString("Application.btnDetalleToolTip"));		
	}
	
	public PnlVerticalBotones getPnlBtnTabla() {
		return pnlBtnTabla;
	}

	public void setTextoBtnImprimir() { //Este boton se usa para reiniciar el cobro
		this.getPnlPie().getBtnImprimir().setText(MessagesCaja.getString("Cobro.btnImprimir"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString("Cobro.titulo");
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
		this.setTitle(locTitulo);
	}

	public JTextField getTfCodigoLiquidacion() {
		return tfCodigoLiquidacion;
	}

	public void setTfCodigoLiquidacion(JTextField tfCodigoLiquidacion) {
		this.tfCodigoLiquidacion = tfCodigoLiquidacion;
	}

	public JTextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(JTextField tfMonton) {
		this.tfMonto = tfMonton;
	}

	public JTextField getTfTotalPagado() {
		return tfTotalPagado;
	}

	public void setTfTotalPagado(JTextField tfTotalPagado) {
		this.tfTotalPagado = tfTotalPagado;
	}

	public JTextField getTfVuelto() {
		return tfVuelto;
	}

	public void setTfVuelto(JTextField tfVuelto) {
		this.tfVuelto = tfVuelto;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public void setPnlTabla(PnlTabla pnlTabla) {
		this.pnlTabla = pnlTabla;
	}

	public JButton getBtnAgregarLiquidacion() {
		return btnAgregarLiquidacion;
	}

	public void setBtnAgregarLiquidacion(JButton btnAgregarLiquidacion) {
		this.btnAgregarLiquidacion = btnAgregarLiquidacion;
	}

	public CobroTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(CobroTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public PnlTablaCompleto getPnlTablaPagos() {
		return pnlTablaPagos;
	}

	public JButton getBtnComponerPago() {
		return btnComponerPago;
	}

	public JButton getBtnPagoCheque() {
		return btnPagoCheque;
	}

	//Por que sonrie Yohana?
	public JButton getBtnPagoDeposito() {
		return btnPagoDeposito;
	}
	
}
