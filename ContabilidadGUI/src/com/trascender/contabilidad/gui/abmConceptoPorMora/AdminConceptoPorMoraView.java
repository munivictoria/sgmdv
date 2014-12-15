package com.trascender.contabilidad.gui.abmConceptoPorMora;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminConceptoPorMoraView extends AdminView {

	private static final long serialVersionUID = -3069554682253013754L;
	
	private ConceptoPorMoraBusquedaModel busquedaModel;
	private ConceptoPorMoraTableModel tableModel;
	
	private JLabel lblTipoTasa;
	private JTextField tfTipoTasa;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoTasa;

	private final String NOMBRE_RECURSO = "AdminConceptoPorMora";
	
	public AdminConceptoPorMoraView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminConceptoPorMoraView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoTasa = new TLabel();
		this.lblTipoTasa.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoTasa"));
		this.lblTipoTasa.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoTasa);
		
		this.tfTipoTasa = new JTextField();
		this.tfTipoTasa.setEditable(false);
		this.tfTipoTasa.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoTasa);
		
		this.pnlBotonesSeleccionTipoTasa = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoTasa.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoTasa);
		
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
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

	public ConceptoPorMoraBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ConceptoPorMoraBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ConceptoPorMoraTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ConceptoPorMoraTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblTipoTasa() {
		return lblTipoTasa;
	}

	public JTextField getTfTipoTasa() {
		return tfTipoTasa;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoTasa() {
		return pnlBotonesSeleccionTipoTasa;
	}

}
