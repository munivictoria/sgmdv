package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccionPersona;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminOrdenPagoDevolucionView extends AdminView {

	private static final long serialVersionUID = 118955374203961512L;
	
	private OrdenPagoDevolucionBusquedaModel busquedaModel;
	private OrdenPagoDevolucionTableModel tableModel;
	
	private JLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmisionDesde;
	private JFormattedTextField tfFechaEmisionHasta;
	
	private JLabel lblFechaPago;
	private JFormattedTextField tfFechaPagoDesde;
	private JFormattedTextField tfFechaPagoHasta;
	
	private JLabel lblImporteDesde;
	private TFormattedTextFieldImporte tfImporteDesde;
	
	private JLabel lblImporteHasta;
	private TFormattedTextFieldImporte tfImporteHasta;
	
	private JLabel lblPersona;
	private JTextField tfPersona;
	private PnlBotonesSeleccionPersona pnlBotonesSeleccionPersona;
	
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminOrdenPagoDevolucion";
	private MaskFormatter formatter;
	
	public AdminOrdenPagoDevolucionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminOrdenPagoDevolucionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
			
		numFila++;
		this.lblFechaEmision = new TLabel();
		this.lblFechaEmision.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaEmision"));
		this.lblFechaEmision.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaEmision);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaEmisionDesde = new JFormattedTextField(this.formatter);
		this.tfFechaEmisionDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaEmisionDesde);
		
		this.tfFechaEmisionHasta = new JFormattedTextField(this.formatter);
		this.tfFechaEmisionHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaEmisionHasta);
		
		numFila++;
		this.lblFechaPago = new TLabel();
		this.lblFechaPago.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaPago"));
		this.lblFechaPago.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaPago);
		
		this.tfFechaPagoDesde = new JFormattedTextField(this.formatter);
		this.tfFechaPagoDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaPagoDesde);
		
		this.tfFechaPagoHasta = new JFormattedTextField(this.formatter);
		this.tfFechaPagoHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaPagoHasta);
		
		numFila++;
		this.lblImporteDesde = new TLabel();
		this.lblImporteDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporteDesde"));
		this.lblImporteDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblImporteDesde);
		
		this.tfImporteDesde = new TFormattedTextFieldImporte();
		this.tfImporteDesde.setHorizontalAlignment(JTextField.RIGHT);		
		this.tfImporteDesde.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfImporteDesde);
		
		numFila++;
		this.lblImporteHasta = new TLabel();
		this.lblImporteHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporteHasta"));
		this.lblImporteHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblImporteHasta);
		
		this.tfImporteHasta = new TFormattedTextFieldImporte();
		this.tfImporteHasta.setHorizontalAlignment(JTextField.RIGHT);
		this.tfImporteHasta.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfImporteHasta);
		
		numFila++;
		this.lblPersona = new TLabel();
		this.lblPersona.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblPersona"));
		this.lblPersona.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblPersona);
		
		this.tfPersona = new JTextField();
		this.tfPersona.setEditable(false);
		this.tfPersona.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfPersona);
		
		this.pnlBotonesSeleccionPersona = new PnlBotonesSeleccionPersona();
		this.pnlBotonesSeleccionPersona.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionPersona);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.getPnlPie().getBtnConsultar().setVisible(true);
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public OrdenPagoDevolucionBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(OrdenPagoDevolucionBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public OrdenPagoDevolucionTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(OrdenPagoDevolucionTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JFormattedTextField getTfFechaEmisionDesde() {
		return tfFechaEmisionDesde;
	}

	public JFormattedTextField getTfFechaEmisionHasta() {
		return tfFechaEmisionHasta;
	}

	public JFormattedTextField getTfFechaPagoDesde() {
		return tfFechaPagoDesde;
	}

	public JFormattedTextField getTfFechaPagoHasta() {
		return tfFechaPagoHasta;
	}

	public TFormattedTextFieldImporte getTfImporteDesde() {
		return tfImporteDesde;
	}

	public TFormattedTextFieldImporte getTfImporteHasta() {
		return tfImporteHasta;
	}

	public JTextField getTfPersona() {
		return tfPersona;
	}

	public PnlBotonesSeleccionPersona getPnlBotonesSeleccionPersona() {
		return pnlBotonesSeleccionPersona;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public JLabel getLblImporteDesde() {
		return lblImporteDesde;
	}

	public JLabel getLblImporteHasta() {
		return lblImporteHasta;
	}

	public JLabel getLblPersona() {
		return lblPersona;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}
	
}
