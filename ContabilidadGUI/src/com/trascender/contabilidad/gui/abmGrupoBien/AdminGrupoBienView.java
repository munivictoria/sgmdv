package com.trascender.contabilidad.gui.abmGrupoBien;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class AdminGrupoBienView extends AdminView {

	private static final long serialVersionUID = -4902285021608721580L;
	
	private GrupoBienBusquedaModel busquedaModel;
	private GrupoBienTableModel tableModel;
	
	private JLabel lblDescripcionBusqueda;
	
	private final String NOMBRE_RECURSO = "AdminGrupoBien";
	
	public AdminGrupoBienView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminGrupoBienView(JDialog owner) {
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

	public GrupoBienBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(GrupoBienBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public GrupoBienTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(GrupoBienTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblDescripcionBusqueda() {
		return lblDescripcionBusqueda;
	}

}
