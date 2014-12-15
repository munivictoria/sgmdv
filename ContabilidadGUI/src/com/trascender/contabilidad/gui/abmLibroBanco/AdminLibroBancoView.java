package com.trascender.contabilidad.gui.abmLibroBanco;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminLibroBancoView extends AdminView{

	private static final long serialVersionUID = 5513255615940373091L;

	private LibroBancoBusquedaModel busquedaModel;
	private LibroBancoTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblCuentaBancaria;
	private JTextField tfCuentaBancaria;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	
	private final String NOMBRE_RECURSO = "AdminLibroBanco";
	
	public AdminLibroBancoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminLibroBancoView(JDialog owner) {
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
		this.lblCuentaBancaria = new TLabel();
		this.lblCuentaBancaria.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuentaBancaria"));
		this.lblCuentaBancaria.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuentaBancaria);

		this.tfCuentaBancaria = new JTextField();
		this.tfCuentaBancaria.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuentaBancaria);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccion);
		
//		 PANEL CON BOTONES BUSCAR+REINICIAR
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
		
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public LibroBancoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(LibroBancoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public LibroBancoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LibroBancoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public JTextField getTfCuentaBancaria() {
		return tfCuentaBancaria;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}
}
