package com.trascender.contabilidad.gui.abmPersonaJuridica;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminPersonaJuridicaView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private PersonaJuridicaBusquedaModel busquedaModel;
	private PersonaJuridicaTableModel tableModel;
	
	private JLabel lblCuit;
	private JTextField tfCuit;
	private JLabel lblRazonSocial;
	private JTextField tfRazonSocial;
	private JLabel lblTitular;
	private JTextField tfTitular;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	private final String NOMBRE_RECURSO = "AdminPersonaJuridica";
	
	public AdminPersonaJuridicaView(JFrame owner) {
		super(owner);
		this.init();
	}

	public AdminPersonaJuridicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCuit = new TLabel();
		this.lblCuit.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuit"));
		this.lblCuit.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuit);
		
		this.tfCuit = new JTextField();
		this.tfCuit.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuit);
		
		numFila++;
		this.lblRazonSocial = new TLabel();
		this.lblRazonSocial.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblRazonSocial"));
		this.lblRazonSocial.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblRazonSocial);
		
		this.tfRazonSocial = new JTextField();
		this.tfRazonSocial.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfRazonSocial);
		
		numFila++;
		this.lblTitular = new TLabel();
		this.lblTitular.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTitular"));
		this.lblTitular.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTitular);
		
		this.tfTitular = new JTextField();
		this.tfTitular.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTitular);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccion);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
	}
	
	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
		
	}

	public PersonaJuridicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(PersonaJuridicaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public PersonaJuridicaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(PersonaJuridicaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblCuit() {
		return lblCuit;
	}

	public JLabel getLblRazonSocial() {
		return lblRazonSocial;
	}

	public JLabel getLblTitular() {
		return lblTitular;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}

	public JTextField getTfCuit() {
		return tfCuit;
	}

	public JTextField getTfRazonSocial() {
		return tfRazonSocial;
	}

	public JTextField getTfTitular() {
		return tfTitular;
	}

}
