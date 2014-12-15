package com.trascender.contabilidad.gui.abmProveedor;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminProveedorView extends AdminView {
	
	private static final long serialVersionUID = -6596268282093703467L;
	
	private ProveedorBusquedaModel busquedaModel;
	private ProveedorTableModel tableModel;
	
	private JLabel lblRazonSocial;
	private JTextField tfRazonSocial;
	private JLabel lblCodigo;
	private JTextField tfCodigo;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminProveedor";
	
	
	public AdminProveedorView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminProveedorView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblRazonSocial = new TLabel();
		this.lblRazonSocial.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblRazonSocial"));
		this.lblRazonSocial.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblRazonSocial);
		
		this.tfRazonSocial = new JTextField();
		this.tfRazonSocial.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfRazonSocial);
		
		numFila++;
		this.lblCodigo = new TLabel();
		this.lblCodigo.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCodigo"));
		this.lblCodigo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCodigo);
		
		this.tfCodigo = new JTextField();
		this.tfCodigo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCodigo);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public ProveedorBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ProveedorBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ProveedorTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ProveedorTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JLabel getLblRazonSocial() {
		return lblRazonSocial;
	}

	public JTextField getTfRazonSocial() {
		return tfRazonSocial;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTfCodigo() {
		return tfCodigo;
	}
	
}
