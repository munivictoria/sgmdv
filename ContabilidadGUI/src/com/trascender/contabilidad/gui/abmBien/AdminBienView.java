package com.trascender.contabilidad.gui.abmBien;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminBienView extends AdminView {

	private static final long serialVersionUID = 6927150849176140713L;
	
	private BienBusquedaModel busquedaModel;
	private BienTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblGrupoBien;
	private JTextField tfGrupoBien;
	private PnlBotonesSeleccion pnlBotonesSeleccionGrupoBien;
	
	private final String NOMBRE_RECURSO = "AdminBien";
	
	
	public AdminBienView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminBienView(JDialog owner) {
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
		this.lblGrupoBien = new TLabel();
		this.lblGrupoBien.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblGrupoBien"));
		this.lblGrupoBien.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblGrupoBien);
		
		this.tfGrupoBien = new JTextField();
		this.tfGrupoBien.setEditable(false);
		this.tfGrupoBien.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfGrupoBien);
		
		this.pnlBotonesSeleccionGrupoBien = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionGrupoBien.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionGrupoBien);
		
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

	public BienBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(BienBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public BienTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(BienTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblGrupoBien() {
		return lblGrupoBien;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionGrupoBien() {
		return pnlBotonesSeleccionGrupoBien;
	}

	public JTextField getTfGrupoBien() {
		return tfGrupoBien;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	

}
