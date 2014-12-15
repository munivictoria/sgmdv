package com.trascender.contabilidad.gui.abmRetencion.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoBusquedaModel;
import com.trascender.contabilidad.gui.abmOrdenPago.OrdenPagoTableModel;
import com.trascender.contabilidad.gui.abmRetencion.LineaRetencionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
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

public abstract class ABMRetencionView extends ABMView {

	private static final long serialVersionUID = -1504150480095525837L;
	
	private OrdenPagoTableModel ordenPagoTableModel;
	private OrdenPagoBusquedaModel ordenPagoBusquedaModel;
	private LineaRetencionTableModel lineaRetencionTableModel;
	
	
	private JLabel lblMes;
	private JTextField tfMes;
	private JLabel lblAnio;
	private JTextField tfAnio;
	private PnlABMAtributos pnlAtributos;
	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBotonesSeleccionProveedor;
	private JButton btnBuscarOrdenesDePagos;
	
	private JPanel pnlTablas;
	private JPanel pnlContenedorOrdenesPago;
		private JLabel lblTablaOrdenesPago;
		private PnlTabla pnlTablaOrdenesPago;
	private JPanel pnlContenedorParametroRetencion;
		private JLabel lblTablaParametrosRetencion;
		private PnlTabla pnlTablaParametrosRetencion;
		private PnlVerticalBotones pnlBtnTablaParametroRetencion;
	
	private RetencionABMModel abmModel;
	
	private final String NOMBRE_RECURSO = "ABMRetencion";
	
	public ABMRetencionView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ABMRetencionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlAtributos.setLayout(null);
		
			numFila++;
			this.lblMes = new TLabel();
			this.lblMes.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblMes"));
			this.lblMes.setBounds(Util.getBoundsColumnaLabel(numFila));
			this.getPnlAtributos().add(this.lblMes);
			
			this.tfMes = new JTextField();
			this.tfMes.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
			this.getPnlAtributos().add(this.tfMes);
			
			numFila++;
			this.lblAnio = new TLabel();
			this.lblAnio.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblAnio"));
			this.lblAnio.setBounds(Util.getBoundsColumnaLabel(numFila));
			this.getPnlAtributos().add(this.lblAnio);
			
			this.tfAnio = new JTextField();
			this.tfAnio.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
			this.getPnlAtributos().add(this.tfAnio);
		

			numFila++;
			this.lblProveedor = new TLabel();
			this.lblProveedor.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblProveedor"));
			this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
			this.getPnlAtributos().add(this.lblProveedor);
			
			this.tfProveedor = new JTextField();
			this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
			this.tfProveedor.setEditable(false);
			this.getPnlAtributos().add(this.tfProveedor);
			
			this.pnlBotonesSeleccionProveedor = new PnlBotonesSeleccion();
			this.pnlBotonesSeleccionProveedor.setBounds(Util.getBoundsColumnaSeleccion(numFila));
			this.getPnlAtributos().add(this.pnlBotonesSeleccionProveedor);
			
			numFila++;
			this.btnBuscarOrdenesDePagos = new JButton();
			this.btnBuscarOrdenesDePagos.setText("Buscar Ordenes de Pago");
			this.btnBuscarOrdenesDePagos.setToolTipText("Busca las Ordenes de Pago existente para los datos ingresados.");
			this.btnBuscarOrdenesDePagos.setBounds(
					ConstantesPosicion.COLUMN_INPUT_POS_INI_X,
					ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
					ConstantesTamanio.BTN_WIDTH * 2,
					ConstantesTamanio.BTN_HEIGHT);
			this.getPnlAtributos().add(this.btnBuscarOrdenesDePagos);
			
		
		this.pnlTablas = new JPanel();
		this.pnlTablas.setLayout(new BoxLayout(this.pnlTablas, BoxLayout.Y_AXIS));
		
			this.pnlContenedorOrdenesPago = new JPanel();
			this.pnlContenedorOrdenesPago.setLayout(new BorderLayout());
			//this.pnlContenedorOrdenesPago.setBorder(BorderFactory.createTitledBorder("Ordenes de Pago"));
				this.lblTablaOrdenesPago = new JLabel();
				this.lblTablaOrdenesPago.setText("Ordenes de Pago");
				this.pnlTablaOrdenesPago = new PnlTabla();
				this.pnlTablaOrdenesPago.setPreferredSize(new Dimension(100,100));
			
			this.getPnlContenedorOrdenesPago().add(this.lblTablaOrdenesPago, BorderLayout.NORTH);
			this.getPnlContenedorOrdenesPago().add(this.pnlTablaOrdenesPago, BorderLayout.CENTER);
			
		this.getPnlTablas().add(this.getPnlContenedorOrdenesPago());
		
			this.pnlContenedorParametroRetencion = new JPanel();
			this.pnlContenedorParametroRetencion.setLayout(new BorderLayout());
//			
			this.pnlContenedorParametroRetencion.setBorder(BorderFactory.createEmptyBorder(
					ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER, 0, 0, 0));
				this.lblTablaParametrosRetencion = new JLabel();
				this.lblTablaParametrosRetencion.setText("Línea de Retención");
				this.pnlTablaParametrosRetencion = new PnlTabla();
				this.pnlTablaParametrosRetencion.setPreferredSize(new Dimension(100,100));
			
				this.pnlBtnTablaParametroRetencion = new PnlVerticalBotones();
			this.getPnlContenedorParametroRetencion().add(this.lblTablaParametrosRetencion, BorderLayout.NORTH);
			this.getPnlContenedorParametroRetencion().add(this.pnlBtnTablaParametroRetencion, BorderLayout.EAST);
			this.getPnlContenedorParametroRetencion().add(this.pnlTablaParametrosRetencion, BorderLayout.CENTER);
		
		this.getPnlTablas().add(this.getPnlContenedorParametroRetencion());
		
		this.pnlAtributos.setPreferredSize(new Dimension(ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				(numFila + 1) * ConstantesSeparacion.INCREMENTO_Y +
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y * 2));
		
		this.getPnlCuerpo().add(this.getPnlAtributos(), BorderLayout.NORTH);
		this.getPnlCuerpo().add(this.getPnlTablas(), BorderLayout.CENTER);
		setTamanioPosicionVentana(numFila + 1);
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL + 
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,600));
		this.setLocationRelativeTo(null);
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionProveedor() {
		return pnlBotonesSeleccionProveedor;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public JTextField getTfMes() {
		return tfMes;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public JTextField getTfAnio() {
		return tfAnio;
	}

	public RetencionABMModel getAbmModel() {
		return abmModel;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public PnlTabla getPnlTablaOrdenesPago() {
		return pnlTablaOrdenesPago;
	}

	public JPanel getPnlTablas() {
		return pnlTablas;
	}

	public JPanel getPnlContenedorOrdenesPago() {
		return pnlContenedorOrdenesPago;
	}

	public JPanel getPnlContenedorParametroRetencion() {
		return pnlContenedorParametroRetencion;
	}

	public OrdenPagoTableModel getOrdenPagoTableModel() {
		return ordenPagoTableModel;
	}

	public void setOrdenPagoTableModel(OrdenPagoTableModel ordenPagoTableModel) {
		this.ordenPagoTableModel = ordenPagoTableModel;
		this.getPnlTablaOrdenesPago().getTblDatos().setModel(ordenPagoTableModel);
	}

	public OrdenPagoBusquedaModel getOrdenPagoBusquedaModel() {
		return ordenPagoBusquedaModel;
	}

	public void setOrdenPagoBusquedaModel(OrdenPagoBusquedaModel ordenPagoBusquedaModel) {
		this.ordenPagoBusquedaModel = ordenPagoBusquedaModel;
	}

	
	public PnlTabla getPnlTablaParametrosRetencion() {
		return pnlTablaParametrosRetencion;
	}

	public PnlVerticalBotones getPnlBtnTablaParametroRetencion() {
		return pnlBtnTablaParametroRetencion;
	}

	public LineaRetencionTableModel getLineaRetencionTableModel() {
		return lineaRetencionTableModel;
	}

	public void setLineaRetencionTableModel(LineaRetencionTableModel lineaRetencionTableModel) {
		this.lineaRetencionTableModel = lineaRetencionTableModel;
		this.getPnlTablaParametrosRetencion().getTblDatos().setModel(lineaRetencionTableModel);
	}

	public JButton getBtnBuscarOrdenesDePagos() {
		return btnBuscarOrdenesDePagos;
	}

}
