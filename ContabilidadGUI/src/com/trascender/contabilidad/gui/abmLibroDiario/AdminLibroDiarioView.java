package com.trascender.contabilidad.gui.abmLibroDiario;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminLibroDiarioView extends AdminView {
	
	private static final long serialVersionUID = 4874189931883208017L;
	
	private LibroDiarioBusquedaModel busquedaModel;
	private LibroDiarioTableModel tableModel;
	
	private JLabel lblCodigoAutorizacion;
	private JTextField tfCodigoAutorizacion;
	private JLabel lblNumero;
	private JTextField tfNumero;
	
	private final String NOMBRE_RECURSO = "AdminLibroDiario";
	
	public AdminLibroDiarioView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminLibroDiarioView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCodigoAutorizacion = new TLabel();
		this.lblCodigoAutorizacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCodigoAutorizacion"));
		this.lblCodigoAutorizacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCodigoAutorizacion);
		
		this.tfCodigoAutorizacion = new JTextField();
		this.tfCodigoAutorizacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCodigoAutorizacion);
		
		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumero"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNumero);
		
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
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public LibroDiarioBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(LibroDiarioBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public LibroDiarioTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LibroDiarioTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JLabel getLblCodigoAutorizacion() {
		return lblCodigoAutorizacion;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JTextField getTfCodigoAutorizacion() {
		return tfCodigoAutorizacion;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

}
