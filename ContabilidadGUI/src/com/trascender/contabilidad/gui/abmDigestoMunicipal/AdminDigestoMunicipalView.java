package com.trascender.contabilidad.gui.abmDigestoMunicipal;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminDigestoMunicipalView extends AdminView {
	
	private static final long serialVersionUID = 1961094417115169426L;
	
	private DigestoMunicipalBusquedaModel busquedaModel;
	private DigestoMunicipalTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	
	private final String NOMBRE_RECURSO = "AdminDigestoMunicipal";
	
	public AdminDigestoMunicipalView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminDigestoMunicipalView(JDialog owner) {
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
		this.lblTipo = new TLabel();
		this.lblTipo.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipo"));
		this.lblTipo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipo);
		
		this.cbTipo = new JComboBox();
		this.cbTipo.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbTipo);
		
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

	public DigestoMunicipalBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(DigestoMunicipalBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public DigestoMunicipalTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DigestoMunicipalTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	
}
