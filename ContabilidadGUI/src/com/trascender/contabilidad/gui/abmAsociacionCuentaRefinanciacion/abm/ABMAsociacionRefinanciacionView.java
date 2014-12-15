package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.abm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.AsociacionRefinanciacionBusquedaModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.RefinanciacionTableModel;
import com.trascender.contabilidad.gui.abmRetencion.RetencionABMModel;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.abmStandard.PnlTabla;
import com.trascender.gui.framework.abmStandard.PnlVerticalBotones;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public abstract class ABMAsociacionRefinanciacionView extends ABMView {

	private static final long serialVersionUID = -1504150480095525837L;
	
	private RefinanciacionTableModel refinanciacionTableModel;
	private AsociacionRefinanciacionBusquedaModel cuentaRefinanciacionBusquedaModel;
	private ParametroAsociacionTableModel parametroAsociacionTableModel;
	
	private PnlABMAtributos pnlAtributos;
	
	private JPanel pnlTablas;
	private JPanel pnlContenedorRefinanciaciones;
		private JLabel lblTablaRefinanciaciones;
		private PnlTabla pnlTablaRefinanciaciones;
	private JPanel pnlContenedorParametroAsociacion;
		private JLabel lblTablaParametrosAsociacion;
		private PnlTabla pnlTablaParametrosAsociacion;
		private PnlVerticalBotones pnlBtnTablaParametroAsociacion;
		private JButton btnBuscarDocumentosRefinanciacion;
	
	private RetencionABMModel abmModel;
	
	private final String NOMBRE_RECURSO = "ABMAsociacionRefinanciacion";
	
	public ABMAsociacionRefinanciacionView(JDialog owner) {
		super(owner);
		this.init();
	}

	public ABMAsociacionRefinanciacionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		this.pnlAtributos.setLayout(null);
		
			numFila++;
		this.pnlTablas = new JPanel();
		this.pnlTablas.setLayout(new BoxLayout(this.pnlTablas, BoxLayout.Y_AXIS));
		
			this.pnlContenedorRefinanciaciones = new JPanel();
			this.pnlContenedorRefinanciaciones.setLayout(new BorderLayout());
			//this.pnlContenedorOrdenesPago.setBorder(BorderFactory.createTitledBorder("Ordenes de Pago"));
				this.lblTablaRefinanciaciones = new JLabel();
				this.lblTablaRefinanciaciones.setText("Documentos de Refinanciación Asociados");
				this.pnlTablaRefinanciaciones = new PnlTabla();
				this.pnlTablaRefinanciaciones.setPreferredSize(new Dimension(100,100));
			
			this.getPnlContenedorRefinanciaciones().add(this.lblTablaRefinanciaciones, BorderLayout.NORTH);
			this.getPnlContenedorRefinanciaciones().add(this.pnlTablaRefinanciaciones, BorderLayout.CENTER);
			
		this.getPnlTablas().add(this.getPnlContenedorRefinanciaciones());
		
			this.pnlContenedorParametroAsociacion = new JPanel();
			this.pnlContenedorParametroAsociacion.setLayout(new BorderLayout());
//			
			this.pnlContenedorParametroAsociacion.setBorder(BorderFactory.createEmptyBorder(
					ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER, 0, 0, 0));
				this.lblTablaParametrosAsociacion = new JLabel();
				this.lblTablaParametrosAsociacion.setText("Parámetros de Asociación");
				this.lblTablaParametrosAsociacion.setBorder(BorderFactory.createEmptyBorder(0,5,10,0));
				this.lblTablaParametrosAsociacion.setFont(new Font("Verdana",0,12));
				this.pnlTablaParametrosAsociacion = new PnlTabla();
				this.pnlTablaParametrosAsociacion.setPreferredSize(new Dimension(100,100));
			
				this.pnlBtnTablaParametroAsociacion = new PnlVerticalBotones();
			this.getPnlContenedorParametroAsociacion().add(this.lblTablaParametrosAsociacion, BorderLayout.NORTH);
			this.getPnlContenedorParametroAsociacion().add(this.pnlBtnTablaParametroAsociacion, BorderLayout.EAST);
			this.getPnlContenedorParametroAsociacion().add(this.pnlTablaParametrosAsociacion, BorderLayout.CENTER);
		
		this.getPnlTablas().add(this.getPnlContenedorParametroAsociacion());
		
		this.pnlAtributos.setPreferredSize(new Dimension(ConstantesPosicion.COLUMN_LBL_POS_INI_X * 2 +
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X +
				ConstantesTamanio.TF_WIDTH +
				ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				numFila * ConstantesSeparacion.INCREMENTO_Y +
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

	public JPanel getPnlContenedorRefinanciaciones() {
		return pnlContenedorRefinanciaciones;
	}

	public JLabel getLblTablaRefinanciaciones() {
		return lblTablaRefinanciaciones;
	}

	public PnlTabla getPnlTablaRefinanciaciones() {
		return pnlTablaRefinanciaciones;
	}

	public JPanel getPnlContenedorParametroAsociacion() {
		return pnlContenedorParametroAsociacion;
	}

	public JLabel getLblTablaParametrosAsociacion() {
		return lblTablaParametrosAsociacion;
	}

	public PnlTabla getPnlTablaParametrosAsociacion() {
		return pnlTablaParametrosAsociacion;
	}

	public PnlVerticalBotones getPnlBtnTablaParametroAsociacion() {
		return pnlBtnTablaParametroAsociacion;
	}

	public RetencionABMModel getAbmModel() {
		return abmModel;
	}

	public PnlABMAtributos getPnlAtributos() {
		return pnlAtributos;
	}

	public JPanel getPnlTablas() {
		return pnlTablas;
	}

	public RefinanciacionTableModel getRefinanciacionTableModel() {
		return refinanciacionTableModel;
	}

	public void setRefinanciacionTableModel(
			RefinanciacionTableModel refinanciacionTableModel) {
		this.refinanciacionTableModel = refinanciacionTableModel;
		this.getPnlTablaRefinanciaciones().getTblDatos().setModel(refinanciacionTableModel);
	}

	public ParametroAsociacionTableModel getParametroAsociacionTableModel() {
		return parametroAsociacionTableModel;
	}

	public void setParametroAsociacionTableModel(
			ParametroAsociacionTableModel parametroAsociacionTableModel) {
		this.parametroAsociacionTableModel = parametroAsociacionTableModel;
		this.getPnlTablaParametrosAsociacion().getTblDatos().setModel(parametroAsociacionTableModel);
	}

	public AsociacionRefinanciacionBusquedaModel getCuentaRefinanciacionBusquedaModel() {
		return cuentaRefinanciacionBusquedaModel;
	}

	public JButton getBtnBuscarDocumentosRefinanciacion() {
		return btnBuscarDocumentosRefinanciacion;
	}
}