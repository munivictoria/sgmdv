package com.trascender.contabilidad.gui.abmBanco;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminBancoView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private BancoBusquedaModel busquedaModel;
	private BancoTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblSucursal;
	private JTextField tfSucursal;
	
	private static final String NOMBRE_RECURSO = "AdminBanco";
	
	public AdminBancoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblSucursal = new TLabel();
		this.lblSucursal.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblSucursal"));
		this.lblSucursal.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblSucursal);
		
		this.tfSucursal = new JTextField();
		this.tfSucursal.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfSucursal);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
		this.getPnlPie().getBtnConsultar().setVisible(true);
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

	public BancoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(BancoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public BancoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(BancoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfSucursal() {
		return tfSucursal;
	}

}
