package com.trascender.contabilidad.gui.abmTipoTasa;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminTipoTasaView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private TipoTasaBusquedaModel busquedaModel;
	private TipoTasaTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblTipoObligacion;
	private JComboBox cbTipoObligacion;
	
	private static final String NOMBRE_RECURSO ="AdminTipoTasa";
	
	public AdminTipoTasaView(JFrame pFrame) {
		super(pFrame);
		init();
	}
	
	public AdminTipoTasaView(JDialog pDialog) {
		super(pDialog);
		init();
	}

	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblTipoObligacion = new TLabel();
		this.lblTipoObligacion.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipoObligacion"));
		this.lblTipoObligacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoObligacion);
		
		this.cbTipoObligacion = new JComboBox();
		this.cbTipoObligacion.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbTipoObligacion);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AdminTipoTasa.descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("AdminTipoTasa.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);

	}

	public TipoTasaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(TipoTasaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
		this.actualizarBusquedaModel();
	}

	public void actualizarBusquedaModel() {
		this.getTfNombre().setText(this.busquedaModel.getNombre());
		this.getCbTipoObligacion().setSelectedItem(this.busquedaModel.getTipoObligacion());
	}

	public TipoTasaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TipoTasaTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JComboBox getCbTipoObligacion() {
		return cbTipoObligacion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblTipoObligacion() {
		return lblTipoObligacion;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

}
