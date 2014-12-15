package com.trascender.contabilidad.gui.abmSolicitudSuministros;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminSolicitudSuministroView extends AdminView {

	private static final long serialVersionUID = 6255149621741417407L;
	
	private SolicitudSuministroBusquedaModel busquedaModel;
	private SolicitudSuministroTableModel tableModel;

	private TLabel lblProducto;
	private JTextField tfproducto;
	private PnlBotonesSeleccion pnlBotonesSeleccionProducto;
	private TLabel lblArea;
	private JTextField tfArea;
	private PnlBotonesSeleccion pnlBotonesSeleccionArea;
	private TLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminSolicitudSuministro";
	
	public AdminSolicitudSuministroView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminSolicitudSuministroView(JFrame owner) {
		super(owner);
		this.init();
		this.getPnlPie().getBtnConsultar().setVisible(true);
	}
	
	private void init() {
		int numFila = -1;

		numFila++;
		this.lblProducto = new TLabel();
		this.lblProducto.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblProducto"));
		this.lblProducto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblProducto);
		
		this.tfproducto = new JTextField();
		this.tfproducto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfproducto.setEditable(false);
		this.getPnlBusqueda().add(this.tfproducto);
		
		this.pnlBotonesSeleccionProducto = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionProducto.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionProducto);
		
		numFila++;
		this.lblArea = new TLabel();
		this.lblArea.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblArea"));
		this.lblArea.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblArea);
		
		this.tfArea = new JTextField();
		this.tfArea.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfArea.setEditable(false);
		this.getPnlBusqueda().add(this.tfArea);
		
		this.pnlBotonesSeleccionArea = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionArea.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionArea);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		//Panel con botones Buscar y Reiniciar
		numFila ++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public SolicitudSuministroBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(SolicitudSuministroBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public SolicitudSuministroTableModel getTableModel() {
		return tableModel;
	}
	
	public void setTableModel(SolicitudSuministroTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JTextField getTfproducto() {
		return tfproducto;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionProducto() {
		return pnlBotonesSeleccionProducto;
	}

	public JTextField getTfArea() {
		return tfArea;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionArea() {
		return pnlBotonesSeleccionArea;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}
	
}
