package com.trascender.contabilidad.gui.abmTicketCaja;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminTicketcajaView extends AdminView {

	private static final long serialVersionUID = 9004515458656215198L;

	private TicketCajaBusquedaModel busquedaModel;
	private TicketCajaTableModel tableModel;
	
	private JLabel lblFechaDesdeHasta;
	private JFormattedTextField ftfFechaDesde;
//	private JLabel lblFechaHasta;
	private JFormattedTextField ftfFechaHasta;
	private JLabel lblNumero;
	private JTextField tfNumero;
	private JLabel lblUsuario;
	private JTextField tfUsuario;
	private PnlBotonesSeleccion pnlBotonesSeleccionUsuario;
	private JLabel lblCaja;
	private JTextField tfCaja;
	private PnlBotonesSeleccion pnlBotonesSeleccionCaja;
	
	private final String NOMBRE_RECURSO = "AdminTicketCaja";
	private MaskFormatter formatter;
	
	public AdminTicketcajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminTicketcajaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
			
		numFila++;
		this.lblFechaDesdeHasta = new TLabel();
		this.lblFechaDesdeHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaDesdeHasta"));
		this.lblFechaDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesdeHasta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFechaDesde = new JFormattedTextField(this.formatter);
		this.ftfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaDesde);
		
		this.ftfFechaHasta = new JFormattedTextField(this.formatter);
		this.ftfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.ftfFechaHasta);
		
			
		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfNumero);
		
//		numFila++;
//		this.lblUsuario = new TLabel();
//		this.lblUsuario.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblUsuario"));
//		this.lblUsuario.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblUsuario);
//		
//		this.tfUsuario = new JTextField();
//		this.tfUsuario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlBusqueda().add(this.tfUsuario);
//		
//		this.pnlBotonesSeleccionUsuario = new PnlBotonesSeleccion();
//		this.pnlBotonesSeleccionUsuario.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
//		this.getPnlBusqueda().add(this.pnlBotonesSeleccionUsuario);
//		
//		numFila++;
//		this.lblCaja = new TLabel();
//		this.lblCaja.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCaja"));
//		this.lblCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblCaja);
//		
//		this.tfCaja = new JTextField();
//		this.tfCaja.setEditable(false);
//		this.tfCaja.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlBusqueda().add(this.tfCaja);
//		
//		this.pnlBotonesSeleccionCaja = new PnlBotonesSeleccion();
//		this.pnlBotonesSeleccionCaja.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
//		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCaja);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		//this.getPnlPie().getBtnConsultar().setVisible(true);
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo= MessagesContabilidad.getString(NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public TicketCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(TicketCajaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public TicketCajaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TicketCajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblFechaDesdeHasta() {
		return lblFechaDesdeHasta;
	}

	public JFormattedTextField getFtfFechaDesde() {
		return ftfFechaDesde;
	}

	public JFormattedTextField getFtfFechaHasta() {
		return ftfFechaHasta;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionUsuario() {
		return pnlBotonesSeleccionUsuario;
	}

	public JLabel getLblCaja() {
		return lblCaja;
	}

	public JTextField getTfCaja() {
		return tfCaja;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCaja() {
		return pnlBotonesSeleccionCaja;
	}
	
}
