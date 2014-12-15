package com.trascender.contabilidad.gui.abmRetencion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminRetencionView extends AdminView {

	private static final long serialVersionUID = 4695105816628890200L;

	private RetencionBusquedaModel busquedaModel;
	private RetencionTableModel tableModel;
	
	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBotonesSeleccionProveedor;
	private JLabel lblMes;
	private JTextField tfMes;
	private JLabel lblAnio;
	private JTextField tfAnio;

	private final String NOMBRE_RECURSO = "AdminRetencion";
	
	
	public AdminRetencionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminRetencionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblProveedor = new TLabel();
		this.lblProveedor.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblProveedor"));
		this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblProveedor);
		
		this.tfProveedor = new JTextField();
		this.tfProveedor.setEditable(false);
		this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfProveedor);
		
		this.pnlBotonesSeleccionProveedor = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionProveedor.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionProveedor);
		
		numFila++;
		this.lblMes = new TLabel();
		this.lblMes.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblMes"));
		this.lblMes.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblMes);
		
		this.tfMes = new JTextField();
		this.tfMes.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfMes);
		
		numFila++;
		this.lblAnio = new TLabel();
		this.lblAnio.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblAnio"));
		this.lblAnio.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblAnio);
		
		this.tfAnio = new JTextField();
		this.tfAnio.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfAnio);

		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
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

	public RetencionBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(RetencionBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public RetencionTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(RetencionTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionProveedor() {
		return pnlBotonesSeleccionProveedor;
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

}
