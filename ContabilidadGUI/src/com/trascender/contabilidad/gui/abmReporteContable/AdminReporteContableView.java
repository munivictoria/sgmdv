package com.trascender.contabilidad.gui.abmReporteContable;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminReporteContableView extends AdminView{

	private static final long serialVersionUID = -4593163998121109231L;
	
	private ReporteContableBusquedaModel busquedaModel;
	private ReporteContableTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;

	private static final String NOMBRE_RECURSO = "AdminReporteContable";
	
	public AdminReporteContableView(JDialog owner) {
		super(owner);
		this.init();
	}

	public AdminReporteContableView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		
		int numFila = -1;
		
		// COMPLETAR ACA
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
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
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
		this.getPnlPie().getBtnConsultar().setVisible(true);
	}
	
	@Override
	public void setTituloVentana() {
		String locTitulo= MessagesContabilidad.getString(NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	public void setBusquedaModel(ReporteContableBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public void setTableModel(ReporteContableTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public ReporteContableBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public ReporteContableTableModel getTableModel() {
		return tableModel;
	}
	
	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	public JLabel getLblNombre() {
		return lblNombre;
	}
	
}
