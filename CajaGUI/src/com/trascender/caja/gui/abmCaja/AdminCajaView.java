package com.trascender.caja.gui.abmCaja;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;
/**
 * @author adrian
 */
public class AdminCajaView extends AdminView {

	private static final long serialVersionUID = 5809404784245511735L;

	private CajaBusquedaModel busquedaModel; 
	private CajaTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JButton btnRestaurar;
	
	private final String NOMBRE_RECURSO = "AdminCaja";
	
	public AdminCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	public AdminCajaView(JDialog owner) {
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
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblAbreviatura"));
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesCaja.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public CajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}
	public void setBusquedaModel(CajaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}
	public JComboBox getCbEstado() {
		return cbEstado;
	}
	public void setCbEstado(JComboBox cbEstado) {
		this.cbEstado = cbEstado;
	}
	public CajaTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(CajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}
	public JButton getBtnRestaurar() {
		return btnRestaurar;
	}
	public void setBtnRestaurar(JButton btnRestaurar) {
		this.btnRestaurar = btnRestaurar;
	}

}
