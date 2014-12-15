package com.trascender.contabilidad.gui.abmPresupuesto;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminPresupuestoView extends AdminView {


	private static final long serialVersionUID = 5513255615940373091L;

	private PresupuestoBusquedaModel busquedaModel;
	private PresupuestoTableModel tableModel;
	
	private JLabel lblTipoPresupuesto;
	private JComboBox cbTipoPresupuesto;
	private JLabel lblFecha;
	private JFormattedTextField ftfFechaDesde;
	private JFormattedTextField ftfFechaHasta;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminPresupuesto";
	private MaskFormatter formatter;
	
	public AdminPresupuestoView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public AdminPresupuestoView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoPresupuesto = new TLabel();
		this.lblTipoPresupuesto.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblTipo"));
		this.lblTipoPresupuesto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoPresupuesto);
		
		this.cbTipoPresupuesto = new JComboBox();
		this.cbTipoPresupuesto.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbTipoPresupuesto);

		numFila++;
		this.lblFecha= new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.ftfFechaDesde = new JFormattedTextField(this.formatter);
		this.ftfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.ftfFechaDesde);

		this.ftfFechaHasta = new JFormattedTextField(this.formatter);
		this.ftfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.ftfFechaHasta);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		//Panel con botones Buscar y Reiniciar
		numFila ++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.getPnlPie().getBtnConsultar().setVisible(true);
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public PresupuestoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(PresupuestoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public PresupuestoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(PresupuestoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JComboBox getCbTipoPresupuesto() {
		return cbTipoPresupuesto;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JLabel getLblTipoPresupuesto() {
		return lblTipoPresupuesto;
	}

	public JFormattedTextField getFtfFechaDesde() {
		return ftfFechaDesde;
	}

	public JFormattedTextField getFtfFechaHasta() {
		return ftfFechaHasta;
	}

	
}
