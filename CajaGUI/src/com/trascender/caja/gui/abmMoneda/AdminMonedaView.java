package com.trascender.caja.gui.abmMoneda;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;


public class AdminMonedaView extends AdminView {

	private static final long serialVersionUID = -8079287201140193843L;
	
	private MonedaBusquedaModel busquedaModel;
	private MonedaTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	
	private final String NOMBRE_RECURSO = "AdminMoneda";

	public AdminMonedaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminMonedaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public MonedaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(MonedaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public MonedaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MonedaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

}
