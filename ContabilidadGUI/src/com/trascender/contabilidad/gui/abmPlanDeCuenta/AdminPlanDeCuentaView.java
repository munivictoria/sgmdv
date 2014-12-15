package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminPlanDeCuentaView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;

	private PlanDeCuentaBusquedaModel busquedaModel;
	private PlanDeCuentaTableModel tableModel;

	private JLabel lblDescripcion;
	private JTextField tfDescripcion;
	private JLabel lblFechaDesde;
	private JFormattedTextField tfFechaDesde;
	private JLabel lblFechaHasta;
	private JFormattedTextField tfFechaHasta;

	private MaskFormatter formatter;

	private final String NOMBRE_RECURSO = "AdminPlanDeCuenta";

	public AdminPlanDeCuentaView(JFrame pFrame) {
		super(pFrame);
		this.init();

	}

	public AdminPlanDeCuentaView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}

	private void init() {
		int numFila = -1;

		numFila++;
		this.lblDescripcion = new TLabel();
		this.lblDescripcion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblDescripcion"));
		this.lblDescripcion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblDescripcion);

		this.tfDescripcion = new JTextField();
		this.tfDescripcion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfDescripcion);

		numFila++;
		this.lblFechaDesde = new TLabel();
		this.lblFechaDesde.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblFechaDesde"));
		this.lblFechaDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesde);

		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaDesde);

		numFila++;
		this.lblFechaHasta = new TLabel();
		this.lblFechaHasta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".lblFechaHasta"));
		this.lblFechaHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaHasta);

		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);

		this.getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// PANEL CON BOTONES BUSCAR + REINICIAR
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());

		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}

	public PlanDeCuentaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(PlanDeCuentaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
		// this.actualizarDatosBusqueda();
	}

	// public void actualizarDatosBusqueda() {
	// this.getTfDescripcion().setText(this.busquedaModel.getDescripcion());
	// this.getTfFechaDesde().setText(Conversor.getString(this.busquedaModel.
	// getFechaDesde()));
	// this.getTfFechaHasta().setText(Conversor.getString(this.busquedaModel.
	// getFechaHasta()));
	// }

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(
				MessagesContabilidad.getString(this.NOMBRE_RECURSO
						+ ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO
				+ ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JLabel getLblFechaDesde() {
		return lblFechaDesde;
	}

	public JLabel getLblFechaHAsta() {
		return lblFechaHasta;
	}

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public PlanDeCuentaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(PlanDeCuentaTableModel tableModel) {
		this.tableModel = tableModel;
	}

}
