package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaLineaFacturaView extends AdminView {

	private static final long serialVersionUID = -6341144399858301981L;
	
	private AsociacionCuentaLineaFacturaTableModel tableModel;
	private AsociacionCuentaLineaFacturaBusquedaModel busquedaModel;

	private JLabel lblFactura;
	private JTextField tfFactura;
	private PnlBotonesSeleccion pnlBotonesSeleccionFactura;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaLineaFactura";

	public AdminAsociacionCuentaLineaFacturaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaLineaFacturaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFactura = new TLabel();
		this.lblFactura.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblFactura"));
		this.lblFactura.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFactura);
		
		this.tfFactura = new JTextField();
		this.tfFactura.setEditable(false);
		this.tfFactura.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfFactura);
		
		this.pnlBotonesSeleccionFactura = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionFactura.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionFactura);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
	}
	
	public AsociacionCuentaLineaFacturaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionCuentaLineaFacturaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public AsociacionCuentaLineaFacturaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			AsociacionCuentaLineaFacturaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public JTextField getTfFactura() {
		return tfFactura;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionFactura() {
		return pnlBotonesSeleccionFactura;
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	

}
