package com.trascender.contabilidad.gui.abmArticulo;

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

public class AdminArticuloView extends AdminView{
	
	private JLabel lblCodigo;
	private JTextField tfCodigo;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblArea;
	private JTextField tfArea;
	private PnlBotonesSeleccion pnlBotonesSeleccionArea;
	private JLabel lblEstadoContable;
	private JComboBox cbEstadoContable;
	
	private final String NOMBRE_RECURSO = "AdminArticulo";
	
	private ArticuloTableModel tableModel;
	private ArticuloBusquedaModel busquedaModel;
	
	public AdminArticuloView(JFrame frame){
		super(frame);
		this.init();
	}
	
	public AdminArticuloView(JDialog dialog){
		super(dialog);
		this.init();
	}
	
	public void init(){
		int numFila = -1;
		
		numFila++;
		this.lblCodigo = new TLabel();
		this.lblCodigo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCodigo"));
		this.lblCodigo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCodigo);
		
		this.tfCodigo = new JTextField();
		this.tfCodigo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfCodigo);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblArea = new TLabel();
		this.lblArea.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblArea"));
		this.lblArea.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblArea);
		
		this.tfArea = new JTextField();
		this.tfArea.setEditable(false);
		this.tfArea.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfArea);
		
		this.pnlBotonesSeleccionArea = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionArea.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionArea);
		
		numFila++;
		this.lblEstadoContable = new TLabel();
		this.lblEstadoContable.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblEstadoContable"));
		this.lblEstadoContable.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstadoContable);
		
		this.cbEstadoContable = new JComboBox();
		this.cbEstadoContable.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstadoContable);
		
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

	public JTextField getTfCodigo() {
		return tfCodigo;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfArea() {
		return tfArea;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionArea() {
		return pnlBotonesSeleccionArea;
	}

	public ArticuloTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ArticuloTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	public ArticuloBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ArticuloBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public JComboBox getCbEstadoContable() {
		return cbEstadoContable;
	}
	
}
