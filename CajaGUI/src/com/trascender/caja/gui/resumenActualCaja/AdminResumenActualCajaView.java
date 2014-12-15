package com.trascender.caja.gui.resumenActualCaja;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlContenedorResultados;
import com.trascender.gui.framework.abmStandard.PnlResultado;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminResumenActualCajaView extends AdminView {

	private static final long serialVersionUID = -7812729298369033736L;
	
	private ResumenActualCajaBusquedaModel busquedaModel;
	private ResumenActualCajaTableModel tableModel;
	
	private JLabel lblCaja;
	private JTextField tfCaja;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JLabel lblFechaDesdeHasta;
	private JLabel lblHoraDesdeHasta;
	private JFormattedTextField ftfFechaDesde;
	private JFormattedTextField ftfFechaHasta;
	private JFormattedTextField ftfHoraDesde;
	private JFormattedTextField ftfHoraHasta;
	private MaskFormatter formatterFecha;
	private MaskFormatter formatterHora;
//	private JLabel lblObligacion;
//	private JComboBox cbObligacion;
	//private PnlResultado pnlResultado;
	private PnlContenedorResultados pnlContenedorResultado;
	private PnlResultado pnlResultadoMontoCobrado;
	private PnlResultado pnlResultadoCantidadTickets;
	
	private final String NOMBRE_RECURSO = "AdminResumenActualCaja";

	public AdminResumenActualCajaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminResumenActualCajaView(JFrame owner) {
		super(owner);
		this.init();
	}

	private void init() {

		int numFila = -1;

		numFila++;
		this.lblCaja = new TLabel();
		this.lblCaja.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblCaja"));
		this.lblCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCaja);
		
		this.tfCaja = new JTextField();
		this.tfCaja.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCaja);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		try {
			this.formatterFecha = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			this.formatterHora = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		numFila++;
		this.lblFechaDesdeHasta = new TLabel();
		this.lblFechaDesdeHasta.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblFechaDesdeHasta"));
		this.lblFechaDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesdeHasta);
		
		this.ftfFechaDesde = new JFormattedTextField(this.formatterFecha);
		this.ftfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaDesde);
		
		this.ftfFechaHasta = new JFormattedTextField(this.formatterFecha);
		this.ftfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.ftfFechaHasta);
		
		numFila++;
		this.lblHoraDesdeHasta = new TLabel();
		this.lblHoraDesdeHasta.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblHoraDesdeHasta"));
		this.lblHoraDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblHoraDesdeHasta);
		
		this.ftfHoraDesde = new JFormattedTextField(this.formatterHora);
		this.ftfHoraDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfHoraDesde);
		
		this.ftfHoraHasta = new JFormattedTextField(this.formatterHora);
		this.ftfHoraHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.ftfHoraHasta);
		
		
		
//		numFila++;
//		this.lblObligacion = new TLabel();
//		this.lblObligacion.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblObligacion"));
//		this.lblObligacion.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblObligacion);
//		
//		this.cbObligacion = new JComboBox();
//		this.cbObligacion.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
//		this.getPnlBusqueda().add(this.cbObligacion);

		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.pnlContenedorResultado = new PnlContenedorResultados();
		//Paneles de totales
		this.pnlResultadoMontoCobrado = new PnlResultado();
		this.pnlResultadoMontoCobrado.getLblDescripcion().setText("Total Monto Cobrado");
		this.pnlResultadoMontoCobrado.getLblNumero().setText("0,00");
		this.getPnlContenedorResultado().add(this.pnlResultadoMontoCobrado);
	
		this.pnlResultadoCantidadTickets = new PnlResultado();
		this.pnlResultadoCantidadTickets.getLblDescripcion().setText("Total Tickets Cobrados");
		this.pnlResultadoCantidadTickets.getLblNumero().setText("0");
		this.getPnlContenedorResultado().add(this.pnlResultadoCantidadTickets);
		
		this.getPnlCuerpo().add(this.pnlContenedorResultado, BorderLayout.SOUTH);
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public ResumenActualCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ResumenActualCajaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ResumenActualCajaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ResumenActualCajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public PnlContenedorResultados getPnlContenedorResultado() {
		return pnlContenedorResultado;
	}

	public PnlResultado getPnlResultadoMontoCobrado() {
		return pnlResultadoMontoCobrado;
	}

	public PnlResultado getPnlResultadoCantidadTickets() {
		return pnlResultadoCantidadTickets;
	}

	public JTextField getTfCaja() {
		return tfCaja;
	}

	public JFormattedTextField getFtfFechaDesde() {
		return ftfFechaDesde;
	}

	public JFormattedTextField getFtfFechaHasta() {
		return ftfFechaHasta;
	}

	public JFormattedTextField getFtfHoraDesde() {
		return ftfHoraDesde;
	}

	public JFormattedTextField getFtfHoraHasta() {
		return ftfHoraHasta;
	}
	
//	public JComboBox getCbObligacion() {
//		return cbObligacion;
//	}

}
