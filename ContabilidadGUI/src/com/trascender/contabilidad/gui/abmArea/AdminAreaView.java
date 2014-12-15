package com.trascender.contabilidad.gui.abmArea;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAreaView extends AdminView {

	private static final long serialVersionUID = -5758558595127529049L;
	
	private AreaBusquedaModel busquedaModel;
	private AreaTableModel tableModel;

	private JLabel lblNombre;
	private JTextField tfNombre;
	
	private final String NOMBRE_RECURSO = "AdminArea";
	
	public AdminAreaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminAreaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public AreaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(AreaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AreaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AreaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}
	
}
