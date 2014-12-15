package com.trascender.contabilidad.gui.abmFolioLibroDiario;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminFolioLibroDiarioView extends AdminView {
	
	private static final long serialVersionUID = 1012234079714231085L;
	
	private FolioLibroDiarioBusquedaModel busquedaModel;
	private FolioLibroDiarioTableModel tableModel;
	
	private JLabel lblNumero;
	private JTextField tfNumero;
	private JLabel lblLibroDiario;
	private JTextField tfLibroDiario;
	private PnlBotonesSeleccion pnlBotonesSeleccionLibroDiario;
	
	private final String NOMBRE_RECURSO = "AdminFolioLibroDiario";
	
	public AdminFolioLibroDiarioView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminFolioLibroDiarioView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumero"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNumero);
		
		numFila++;
		this.lblLibroDiario = new TLabel();
		this.lblLibroDiario.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblLibroDiario"));
		this.lblLibroDiario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblLibroDiario);
		
		this.tfLibroDiario = new JTextField();
		this.tfLibroDiario.setEditable(false);
		this.tfLibroDiario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfLibroDiario);
		
		this.pnlBotonesSeleccionLibroDiario = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionLibroDiario.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(pnlBotonesSeleccionLibroDiario);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO +".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}
	
	public FolioLibroDiarioBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public JLabel getLblLibroDiario() {
		return lblLibroDiario;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public FolioLibroDiarioTableModel getTableModel() {
		return tableModel;
	}

	public JTextField getTfLibroDiario() {
		return tfLibroDiario;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public void setBusquedaModel(FolioLibroDiarioBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public void setTableModel(FolioLibroDiarioTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionLibroDiario() {
		return pnlBotonesSeleccionLibroDiario;
	}

}
