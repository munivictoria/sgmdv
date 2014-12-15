package com.trascender.contabilidad.gui.abmBoletaDeposito;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminBoletaDepositoView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;
	
	private BoletaDepositoBusquedaModel busquedaModel;
	private BoletaDepositoTableModel tableModel;
	
	private JLabel lblNumeroBoleta;
	private JTextField tfNumeroBoleta;
	private JLabel lblImporteDesde;
	private TFormattedTextFieldImporte tfImporteDesde;
	private JLabel lblImporteHasta;
	private TFormattedTextFieldImporte tfImporteHasta;
	private JLabel lblFecha;
	private JFormattedTextField tfFechaDesde;
	private JFormattedTextField tfFechaHasta;
	
	private static String NOMBRE_RECURSO = "AdminBoletaDeposito";
	private MaskFormatter formatter;

	public AdminBoletaDepositoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminBoletaDepositoView(JDialog owner) {
		super(owner);
		this.init();
		
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroBoleta = new TLabel();
		this.lblNumeroBoleta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroBoleta"));
		this.lblNumeroBoleta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumeroBoleta);
		
		this.tfNumeroBoleta = new JTextField();
		this.tfNumeroBoleta.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfNumeroBoleta);
		
		numFila++;
		this.lblImporteDesde = new TLabel();
		this.lblImporteDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporteDesde"));
		this.lblImporteDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblImporteDesde);
		
		this.tfImporteDesde = new TFormattedTextFieldImporte();
		this.tfImporteDesde.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfImporteDesde);
		
		numFila++;
		this.lblImporteHasta = new TLabel();
		this.lblImporteHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporteHasta"));
		this.lblImporteHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblImporteHasta);
		
		this.tfImporteHasta = new TFormattedTextFieldImporte();
		this.tfImporteHasta.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfImporteHasta);
		
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
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaDesde);

		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);
		
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
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo= MessagesContabilidad.getString(NOMBRE_RECURSO + ".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public BoletaDepositoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(BoletaDepositoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public BoletaDepositoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(BoletaDepositoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(this.tableModel);
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}


	public JLabel getLblImporteDesde() {
		return lblImporteDesde;
	}

	public JLabel getLblImporteHasta() {
		return lblImporteHasta;
	}

	public JLabel getLblNumeroBoleta() {
		return lblNumeroBoleta;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public TFormattedTextFieldImporte getTfImporteDesde() {
		return tfImporteDesde;
	}

	public TFormattedTextFieldImporte getTfImporteHasta() {
		return tfImporteHasta;
	}

	public JTextField getTfNumeroBoleta() {
		return tfNumeroBoleta;
	}
}
