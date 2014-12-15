package com.trascender.contabilidad.gui.abmMayor;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminMayorView extends AdminView {
	
	private static final long serialVersionUID = 585926972371437556L;

	private MayorBusquedaModel busquedaModel;
	private MayorTableModel tableModel;
	
//	private JLabel lblFechaDesde;
//	private TFormattedTextFieldDate ftfFechaDesde;
//	private JLabel lblFechaHasta;
//	private TFormattedTextFieldDate ftfFechaHasta;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccion;
	
	private static final String NOMBRE_RECURSO = "AdminMayor";
	
	public AdminMayorView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminMayorView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
//		numFila++;
//		this.lblFechaDesde = new TLabel();
//		this.lblFechaDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaDesde"));
//		this.lblFechaDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblFechaDesde);
//		
//		this.ftfFechaDesde = new TFormattedTextFieldDate();
//		this.ftfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
//		this.getPnlBusqueda().add(this.ftfFechaDesde);
//		
//		numFila++;
//		this.lblFechaHasta = new TLabel();
//		this.lblFechaHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaHasta"));
//		this.lblFechaHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
//		this.getPnlBusqueda().add(this.lblFechaHasta);
//		
//		this.ftfFechaHasta = new TFormattedTextFieldDate();
//		this.ftfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
//		this.getPnlBusqueda().add(this.ftfFechaHasta);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuenta);
		
		this.pnlBotonesSeleccion = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccion.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccion);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
		this.getPnlPie().getBtnConsultar().setVisible(true);
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

	public MayorBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(MayorBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public MayorTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MayorTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}
//
//	public TFormattedTextFieldDate getFtfFechaDesde() {
//		return ftfFechaDesde;
//	}
//
//	public TFormattedTextFieldDate getFtfFechaHasta() {
//		return ftfFechaHasta;
//	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

//	public JLabel getLblFechaDesde() {
//		return lblFechaDesde;
//	}
//
//	public JLabel getLblFechaHasta() {
//		return lblFechaHasta;
//	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccion() {
		return pnlBotonesSeleccion;
	}


}
