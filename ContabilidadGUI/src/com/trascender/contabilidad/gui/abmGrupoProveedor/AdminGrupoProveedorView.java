package com.trascender.contabilidad.gui.abmGrupoProveedor;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class AdminGrupoProveedorView extends AdminView {

	private static final long serialVersionUID = -8431568343394638545L;
	
	private GrupoProveedorBusquedaModel busquedaModel;
	private GrupoProveedorTableModel tableModel;
	
	private JLabel lblDescripcionBusqueda;
	
	private final String NOMBRE_RECURSO = "AdminGrupoProveedor";
	
	public AdminGrupoProveedorView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminGrupoProveedorView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		numFila++;
		this.lblDescripcionBusqueda = new JLabel();
		this.lblDescripcionBusqueda.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblDescripcionBusqueda"));
		this.lblDescripcionBusqueda.setBounds(ConstantesPosicion.COLUMN_LBL_POS_INI_X,
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.ADMIN_WINDOW_WIDTH, 
				ConstantesTamanio.LBL_HEIGHT);
		this.getPnlBusqueda().add(this.lblDescripcionBusqueda);
		
		numFila++;
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

	public JLabel getLblDescripcionBusqueda() {
		return lblDescripcionBusqueda;
	}

	public GrupoProveedorBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(GrupoProveedorBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public GrupoProveedorTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(GrupoProveedorTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}
	
}
