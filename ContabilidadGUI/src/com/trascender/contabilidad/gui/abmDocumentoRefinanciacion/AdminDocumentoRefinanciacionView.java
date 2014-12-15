package com.trascender.contabilidad.gui.abmDocumentoRefinanciacion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccionPersona;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminDocumentoRefinanciacionView extends AdminView {

	private static final long serialVersionUID = -3766420037659754304L;

	private DocumentoRefinanciacionBusquedaModel busquedaModel;
	private DocumentoRefinanciacionTableModel tableModel;
	
	private JLabel lblNumeroRefinanciacion;
	private JTextField tfNumeroRefinanciacion;
	private JLabel lblContribuyente;
	private JTextField tfContribuyente;
	private PnlBotonesSeleccionPersona pnlBotonesSeleccionPersona;
	
	private final String NOMBRE_RECURSO = "AdminDocumentoRefinanciacion";
	
	public AdminDocumentoRefinanciacionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminDocumentoRefinanciacionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroRefinanciacion = new TLabel();
		this.lblNumeroRefinanciacion.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumeroRefinanciacion"));
		this.lblNumeroRefinanciacion.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumeroRefinanciacion);
		
		this.tfNumeroRefinanciacion = new JTextField();
		this.tfNumeroRefinanciacion.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNumeroRefinanciacion);
		
		numFila++;
		this.lblContribuyente = new TLabel();
		this.lblContribuyente.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblContribuyente"));
		this.lblContribuyente.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblContribuyente);
		
		this.tfContribuyente = new JTextField();
		this.tfContribuyente.setEditable(false);
		this.tfContribuyente.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfContribuyente);
		
		this.pnlBotonesSeleccionPersona = new PnlBotonesSeleccionPersona();
		this.pnlBotonesSeleccionPersona.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionPersona);
		
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


	public DocumentoRefinanciacionBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}


	public void setBusquedaModel(DocumentoRefinanciacionBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}


	public DocumentoRefinanciacionTableModel getTableModel() {
		return tableModel;
	}


	public void setTableModel(DocumentoRefinanciacionTableModel tableModel) {
		this.tableModel = tableModel;
	}


	public JLabel getLblNumeroRefinanciacion() {
		return lblNumeroRefinanciacion;
	}


	public JTextField getTfNumeroRefinanciacion() {
		return tfNumeroRefinanciacion;
	}


	public JLabel getLblContribuyente() {
		return lblContribuyente;
	}


	public JTextField getTfContribuyente() {
		return tfContribuyente;
	}


	public PnlBotonesSeleccionPersona getPnlBotonesSeleccionPersona() {
		return pnlBotonesSeleccionPersona;
	}

}
