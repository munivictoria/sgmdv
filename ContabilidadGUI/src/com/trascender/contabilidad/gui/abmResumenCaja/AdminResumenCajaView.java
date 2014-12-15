package com.trascender.contabilidad.gui.abmResumenCaja;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.abmAsientoContable.abm.PanelResultados;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminResumenCajaView extends AdminView {

	private static final long serialVersionUID = 4110548182126207930L;
	
	private ResumenCajaTableModel tableModel;
	private ResumenCajaBusquedaModel busquedaModel;

	private JLabel lblFecha;
	private JFormattedTextField tfFecha;
	private JLabel lblCajero;
	private JTextField tfCajero;
	private PnlBotonesSeleccion pnlBotonesSeleccionCajero;
	private JLabel lblCaja;
	private JTextField tfCaja;
	private PnlBotonesSeleccion pnlBotonesSeleccionCaja;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private PanelResultados pnlResultados;
	
	private final String NOMBRE_RECURSO = "AdminResumenCaja";
	private MaskFormatter formatter;
	
	public AdminResumenCajaView(JDialog owner) {
		super(owner);
		this.init();
	}

	public AdminResumenCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		
		int numFila = -1;
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFecha = new JFormattedTextField(this.formatter);
		this.tfFecha.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfFecha);
		
		numFila++;
		this.lblCajero = new TLabel();
		this.lblCajero.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCajero"));
		this.lblCajero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCajero);
		
		this.tfCajero = new JTextField();
		this.tfCajero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCajero.setEditable(false);
		this.getPnlBusqueda().add(this.tfCajero);
		
		this.pnlBotonesSeleccionCajero = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCajero.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCajero);
		
		numFila++;
		this.lblCaja = new TLabel();
		this.lblCaja.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCaja"));
		this.lblCaja.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCaja);
		
		this.tfCaja = new JTextField();
		this.tfCaja.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCaja.setEditable(false);
		this.getPnlBusqueda().add(this.tfCaja);
		
		this.pnlBotonesSeleccionCaja = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCaja.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCaja);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		pnlResultados = new PanelResultados();
		this.getPnlCuerpo().add(pnlResultados,BorderLayout.SOUTH);
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
		
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public ResumenCajaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ResumenCajaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ResumenCajaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ResumenCajaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCaja() {
		return pnlBotonesSeleccionCaja;
	}

	public void setPnlBotonesSeleccionCaja(
			PnlBotonesSeleccion pnlBotonesSeleccionCaja) {
		this.pnlBotonesSeleccionCaja = pnlBotonesSeleccionCaja;
	}

	public JTextField getTfCaja() {
		return tfCaja;
	}

	public void setTfCaja(JTextField tfCaja) {
		this.tfCaja = tfCaja;
	}

	public JFormattedTextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(JFormattedTextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JTextField getTfCajero() {
		return tfCajero;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCajero() {
		return pnlBotonesSeleccionCajero;
	}

}
