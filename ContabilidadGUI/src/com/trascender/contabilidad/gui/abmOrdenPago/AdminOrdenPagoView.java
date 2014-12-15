package com.trascender.contabilidad.gui.abmOrdenPago;

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
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminOrdenPagoView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;

	private OrdenPagoBusquedaModel busquedaModel;
	private OrdenPagoTableModel tableModel;
	
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
	
	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminOrdenPago";
	private MaskFormatter formatter;
	
	public AdminOrdenPagoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminOrdenPagoView(JDialog owner) {
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
		this.lblProveedor = new TLabel();
		this.lblProveedor.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblProveedor"));
		this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblProveedor);
		
		this.tfProveedor = new JTextField();
		this.tfProveedor.setEditable(false);
		this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfProveedor);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccion);
		
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

	public OrdenPagoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(OrdenPagoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public OrdenPagoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(OrdenPagoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public void setLblFechaEmision(JLabel lblFechaEmision) {
		this.lblFechaEmision = lblFechaEmision;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public void setLblFechaPago(JLabel lblFechaPago) {
		this.lblFechaPago = lblFechaPago;
	}

	public JLabel getLblImporteDesde() {
		return lblImporteDesde;
	}

	public void setLblImporteDesde(JLabel lblImporteDesde) {
		this.lblImporteDesde = lblImporteDesde;
	}

	public JLabel getLblImporteHasta() {
		return lblImporteHasta;
	}

	public void setLblImporteHasta(JLabel lblImporteHasta) {
		this.lblImporteHasta = lblImporteHasta;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(JLabel lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public void setPnlBotonesSeleccion(PnlBotonesSeleccion pnlBotonesSeleccion) {
		this.pnlBotonesSeleccion = pnlBotonesSeleccion;
	}

	public JFormattedTextField getTfFechaEmisionDesde() {
		return tfFechaEmisionDesde;
	}

	public void setTfFechaEmisionDesde(JFormattedTextField tfFechaEmisionDesde) {
		this.tfFechaEmisionDesde = tfFechaEmisionDesde;
	}

	public JFormattedTextField getTfFechaEmisionHasta() {
		return tfFechaEmisionHasta;
	}

	public void setTfFechaEmisionHasta(JFormattedTextField tfFechaEmisionHasta) {
		this.tfFechaEmisionHasta = tfFechaEmisionHasta;
	}

	public JFormattedTextField getTfFechaPagoDesde() {
		return tfFechaPagoDesde;
	}

	public void setTfFechaPagoDesde(JFormattedTextField tfFechaPagoDesde) {
		this.tfFechaPagoDesde = tfFechaPagoDesde;
	}

	public JFormattedTextField getTfFechaPagoHasta() {
		return tfFechaPagoHasta;
	}

	public void setTfFechaPagoHasta(JFormattedTextField tfFechaPagoHasta) {
		this.tfFechaPagoHasta = tfFechaPagoHasta;
	}

	public TFormattedTextFieldImporte getTfImporteDesde() {
		return tfImporteDesde;
	}

	public void setTfImporteDesde(TFormattedTextFieldImporte tfImporteDesde) {
		this.tfImporteDesde = tfImporteDesde;
	}

	public TFormattedTextFieldImporte getTfImporteHasta() {
		return tfImporteHasta;
	}

	public void setTfImporteHasta(TFormattedTextFieldImporte tfImporteHasta) {
		this.tfImporteHasta = tfImporteHasta;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(JTextField tfProveedor) {
		this.tfProveedor = tfProveedor;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

}

