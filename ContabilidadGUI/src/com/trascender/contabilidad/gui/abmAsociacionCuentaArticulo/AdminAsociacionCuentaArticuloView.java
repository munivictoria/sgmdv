package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaArticuloView extends AdminView{
	
	private AsociacionCuentaArticuloTableModel tableModel;
	private AsociacionCuentaArticuloBusquedaModel busquedaModel;
	
	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblArticulo;
	private JTextField tfArticulo;
	private PnlBotonesSeleccion pnlBotonesSeleccionArticulo;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaArticulo";
	
	public AdminAsociacionCuentaArticuloView(JFrame frame){
		super(frame);
		this.init();
	}
	
	public AdminAsociacionCuentaArticuloView(JDialog dialog){
		super(dialog);
		this.init();
	}
	
	private void init(){
		int numFila = -1;
		
		numFila++;
		this.lblAnioPeriodo = new TLabel();
		this.lblAnioPeriodo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblAnioPeriodo"));
		this.lblAnioPeriodo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblAnioPeriodo);
		
		this.tfAnioPeriodo = new JTextField();
		this.tfAnioPeriodo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfAnioPeriodo);
		
		numFila++;
		this.lblArticulo = new TLabel();
		this.lblArticulo.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblArticulo"));
		this.lblArticulo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblArticulo);
		
		this.tfArticulo = new JTextField();
		this.tfArticulo.setEditable(false);
		this.tfArticulo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfArticulo);
		
		this.pnlBotonesSeleccionArticulo = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionArticulo.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionArticulo);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	public AsociacionCuentaArticuloTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionCuentaArticuloTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	public AsociacionCuentaArticuloBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(AsociacionCuentaArticuloBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public JTextField getTfArticulo() {
		return tfArticulo;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionArticulo() {
		return pnlBotonesSeleccionArticulo;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}
	
}
