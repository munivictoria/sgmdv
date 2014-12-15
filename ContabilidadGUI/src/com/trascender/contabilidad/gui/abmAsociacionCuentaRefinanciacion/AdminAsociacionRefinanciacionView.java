package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionRefinanciacionView  extends AdminView {

	private static final long serialVersionUID = 2276609390196696586L;

	private AsociacionRefinanciacionBusquedaModel busquedaModel;
	private AsociacionRefinanciacionTableModel tableModel;
	
//	private JLabel lblDocumentoRefinanciacion;
//	private JTextField tfDocumentoRefinanciacion;
//	private PnlBotonesSeleccion pnlBotonesSeleccionDocumentoRefinanciacion;

	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaRefinanciacion";
	
	public AdminAsociacionRefinanciacionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionRefinanciacionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
//		numFila++;
//		this.lblDocumentoRefinanciacion = new TLabel();
//		this.lblDocumentoRefinanciacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblDocumentoRefinanciacion"));
//		this.lblDocumentoRefinanciacion.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblDocumentoRefinanciacion);
//		
//		this.tfDocumentoRefinanciacion = new JTextField();
//		this.tfDocumentoRefinanciacion.setEditable(false);
//		this.tfDocumentoRefinanciacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
//		this.getPnlBusqueda().add(this.tfDocumentoRefinanciacion);
//		
//		this.pnlBotonesSeleccionDocumentoRefinanciacion = new PnlBotonesSeleccion();
//		this.pnlBotonesSeleccionDocumentoRefinanciacion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
//		this.getPnlBusqueda().add(this.pnlBotonesSeleccionDocumentoRefinanciacion);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
		this.getPnlPie().getBtnModificar().setEnabled(false);
		this.getPnlPie().getBtnConsultar().setVisible(true);
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public AsociacionRefinanciacionBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			AsociacionRefinanciacionBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsociacionRefinanciacionTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionRefinanciacionTableModel tableModel) {
		this.tableModel = tableModel;
	}

//	public JLabel getLblDocumentoRefinanciacion() {
//		return lblDocumentoRefinanciacion;
//	}
//
//	public JTextField getTfDocumentoRefinanciacion() {
//		return tfDocumentoRefinanciacion;
//	}
//
//	public PnlBotonesSeleccion getPnlBotonesSeleccionDocumentoRefinanciacion() {
//		return pnlBotonesSeleccionDocumentoRefinanciacion;
//	}
}