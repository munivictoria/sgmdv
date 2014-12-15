package com.trascender.caja.gui.abmMovimientoCajaChica;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminMovimientoCajaChicaView extends AdminView {

	private static final long serialVersionUID = -5179690897816190131L;
	
	private MovimientoCajaChicaBusquedaModel busquedaModel;
	private MovimientoCajaChicaTableModel tableModel;
	
	private JLabel lblFechaDesdeHasta;
	private JFormattedTextField tfFechaDesde;
	private JFormattedTextField tfFechaHasta;
	private JLabel lblConcepto;
	private JTextField tfConcepto;
	private PnlBotonesSeleccion pnlBotonesSeleccionConcepto;
	private JLabel lblCajaChica;
	private JTextField tfCajaChica;
	private PnlBotonesSeleccion pnlBotonesSeleccionCajaChica;
	
	private final String NOMBRE_RECURSO = "AdminMovimientoCajaChica";
	private MaskFormatter formatter;
	
	public AdminMovimientoCajaChicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminMovimientoCajaChicaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFechaDesdeHasta = new TLabel();
		this.lblFechaDesdeHasta.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblFechaDesdeHasta"));
		this.lblFechaDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesdeHasta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaDesde);
		
		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);
		
		numFila++;
		this.lblConcepto = new TLabel();
		this.lblConcepto.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblConcepto"));
		this.lblConcepto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblConcepto);
		
		this.tfConcepto = new JTextField();
		this.tfConcepto.setEditable(false);
		this.tfConcepto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfConcepto);
		
		this.pnlBotonesSeleccionConcepto = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionConcepto.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionConcepto);
		
		numFila++;
		this.lblCajaChica = new TLabel();
		this.lblCajaChica.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblCajaChica"));
		this.lblCajaChica.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCajaChica);
		
		this.tfCajaChica = new JTextField();
		this.tfCajaChica.setEditable(false);
		this.tfCajaChica.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCajaChica);
		
		this.pnlBotonesSeleccionCajaChica = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCajaChica.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCajaChica);
				
		// PANEL CON BOTONES BUSCAR+REINICIAR
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesCaja.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public MovimientoCajaChicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(MovimientoCajaChicaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCajaChica() {
		return pnlBotonesSeleccionCajaChica;
	}

	public void setPnlBotonesSeleccionCajaChica(
			PnlBotonesSeleccion pnlBotonesSeleccionCajaChica) {
		this.pnlBotonesSeleccionCajaChica = pnlBotonesSeleccionCajaChica;
	}

	public MovimientoCajaChicaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MovimientoCajaChicaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionConcepto() {
		return pnlBotonesSeleccionConcepto;
	}

	public void setPnlBotonesSeleccionConcepto(
			PnlBotonesSeleccion pnlBotonesSeleccionConcepto) {
		this.pnlBotonesSeleccionConcepto = pnlBotonesSeleccionConcepto;
	}

	public JTextField getTfCajaChica() {
		return tfCajaChica;
	}

	public void setTfCajaChica(JTextField tfCajaChica) {
		this.tfCajaChica = tfCajaChica;
	}

	public JTextField getTfConcepto() {
		return tfConcepto;
	}

	public void setTfConcepto(JTextField tfConcepto) {
		this.tfConcepto = tfConcepto;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(JFormattedTextField tfFechaDesde) {
		this.tfFechaDesde = tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(JFormattedTextField tfFechaHasta) {
		this.tfFechaHasta = tfFechaHasta;
	}

}
