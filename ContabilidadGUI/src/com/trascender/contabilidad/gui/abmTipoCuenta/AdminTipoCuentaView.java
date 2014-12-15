package com.trascender.contabilidad.gui.abmTipoCuenta;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminTipoCuentaView extends AdminView {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private TipoCuentaBusquedaModel busquedaModel;
	private TipoCuentaTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblAbreviatura;
	private JComboBox cbAbreviatura;
	private JLabel lblOperaAsientos;
	private JComboBox cbOperaAsientos;
	private JLabel lblOperaMovimientosCaja;
	private JComboBox cbOperaMovimientosCaja;
	
	private final String NOMBRE_RECURSO = "AdminTipoCuenta";
	
	public AdminTipoCuentaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminTipoCuentaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblAbreviatura = new TLabel();
		this.lblAbreviatura.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblAbreviatura"));
		this.lblAbreviatura.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblAbreviatura);
		
		this.cbAbreviatura = new JComboBox();
		this.cbAbreviatura.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbAbreviatura);
		
		numFila++;
		this.lblOperaAsientos = new TLabel();
		this.lblOperaAsientos.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOperaAsientos"));
		this.lblOperaAsientos.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblOperaAsientos);
		
		this.cbOperaAsientos = new JComboBox();
		this.cbOperaAsientos.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbOperaAsientos);
		
		numFila++;
		this.lblOperaMovimientosCaja = new TLabel();
		this.lblOperaMovimientosCaja.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblOperaMovimientosCaja"));
		this.lblOperaMovimientosCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblOperaMovimientosCaja);
		
		this.cbOperaMovimientosCaja = new JComboBox();
		this.cbOperaMovimientosCaja.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbOperaMovimientosCaja);
		
		// PANEL CON BOTONES BUSCAR+REINICIAR
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
		this.getPnlPie().getBtnConsultar().setVisible(true);
	}

	public TipoCuentaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(TipoCuentaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
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
	
	public JComboBox getCbAbreviatura() {
		return cbAbreviatura;
	}
	
	public void setCbAbreviatura(JComboBox cbAbreviatura) {
		this.cbAbreviatura = cbAbreviatura;
	}

	public JComboBox getCbOperaAsientos() {
		return cbOperaAsientos;
	}

	public void setCbOperaAsientos(JComboBox cbOperaAsientos) {
		this.cbOperaAsientos = cbOperaAsientos;
	}

	public JComboBox getCbOperaMovimientosCaja() {
		return cbOperaMovimientosCaja;
	}

	public void setCbOperaMovimientosCaja(JComboBox cbOperaMovimientosCaja) {
		this.cbOperaMovimientosCaja = cbOperaMovimientosCaja;
	}

	public JLabel getLblAbreviatura() {
		return lblAbreviatura;
	}

	public void setLblAbreviatura(JLabel lblAbreviatura) {
		this.lblAbreviatura = lblAbreviatura;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblOperaAsientos() {
		return lblOperaAsientos;
	}

	public void setLblOperaAsientos(JLabel lblOperaAsientos) {
		this.lblOperaAsientos = lblOperaAsientos;
	}

	public JLabel getLblOperaMovimientosCaja() {
		return lblOperaMovimientosCaja;
	}

	public void setLblOperaMovimientosCaja(JLabel lblOperaMovimientosCaja) {
		this.lblOperaMovimientosCaja = lblOperaMovimientosCaja;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TipoCuentaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TipoCuentaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

}
