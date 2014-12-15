package com.trascender.contabilidad.gui.abmTipoBalance;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminTipoBalanceView extends AdminView{

	private static final long serialVersionUID = 5513255615940373091L;
	
	private TipoBalanceBusquedaModel busquedaModel;
	private TipoBalanceTableModel tableModel;
	
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblFechaDesde;
	private JFormattedTextField tfFechaDesde;
	private JLabel lblFechaHasta;
	private JFormattedTextField tfFechaHasta;
	
	private MaskFormatter formatter;
	
	public AdminTipoBalanceView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	public AdminTipoBalanceView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	private void init(){
		
		String locNombreRecurso = "AdminTipoBalance";
		int numFila=-1;
		
		// Sección Búsqueda
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(locNombreRecurso+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.lblFechaDesde = new TLabel();
		this.lblFechaDesde.setText(MessagesContabilidad.getString(locNombreRecurso+".lblFechaDesde"));
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
		this.lblFechaHasta.setText(MessagesContabilidad.getString(locNombreRecurso+".lblFechaHasta"));
		this.lblFechaHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaHasta);
		
		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);
		
		//Botones Panel Búsqueda
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setDescripcionVentana();
		this.setTituloVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
		
		//Ponerlo en el lugar que corresponde
		this.getPnlPie().getBtnConsultar().setVisible(true);
				
	}
	
	
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("AdminTipoBalance.descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("AdminTipoBalance.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public JFormattedTextField getTfFecha() {
		return tfFechaDesde;
	}

	public void setTfFecha(JFormattedTextField tfFecha) {
		this.tfFechaDesde = tfFecha;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TipoBalanceBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(TipoBalanceBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public TipoBalanceTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TipoBalanceTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(JFormattedTextField tfFechaDesde) {
		this.tfFechaDesde = tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(JFormattedTextField tfFechaHasta) {
		this.tfFechaHasta = tfFechaHasta;
	}

	
}
