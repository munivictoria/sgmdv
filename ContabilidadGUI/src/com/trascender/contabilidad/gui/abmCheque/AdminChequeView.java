package com.trascender.contabilidad.gui.abmCheque;

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

public class AdminChequeView extends AdminView {

	private static final long serialVersionUID = 5513255615940373091L;

	private ChequeBusquedaModel busquedaModel;
	private ChequeTableModel tableModel;
	
	private JLabel lblNumeroCheque;
	private JTextField tfNumeroCheque;
	private JLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmisionDesde;
	private JFormattedTextField tfFechaEmisionHasta;
	private JLabel lblFechaPago;
	private JFormattedTextField tfFechaPagoDesde;
	private JFormattedTextField tfFechaPagoHasta;
	private JLabel lblImporteDesde;
	private TFormattedTextFieldImporte tfImporteDesde;
	private JLabel lblImporteHasta;
	private TFormattedTextFieldImporte tfImporteHasta;
	
	private MaskFormatter formatter;
	private static final String NOMBRE_RECURSO = "AdminCheque";
	
	public AdminChequeView(JFrame owner) {
		super(owner);
		this.init();
	}

	public AdminChequeView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNumeroCheque = new TLabel();
		this.lblNumeroCheque.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNumeroCheque"));
		this.lblNumeroCheque.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNumeroCheque);
		
		this.tfNumeroCheque = new JTextField();
		this.tfNumeroCheque.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlBusqueda().add(this.tfNumeroCheque);
		
		numFila++;
		this.lblFechaEmision = new TLabel();
		this.lblFechaEmision.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaEmision"));
		this.lblFechaEmision.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaEmision);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaEmisionDesde = new JFormattedTextField(this.formatter);
		this.tfFechaEmisionDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaEmisionDesde);
		
		this.tfFechaEmisionHasta = new JFormattedTextField(this.formatter);
		this.tfFechaEmisionHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaEmisionHasta);
		
		numFila++;
		this.lblFechaPago = new TLabel();
		this.lblFechaPago.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblFechaPago"));
		this.lblFechaPago.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaPago);
		
		this.tfFechaPagoDesde = new JFormattedTextField(this.formatter);
		this.tfFechaPagoDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaPagoDesde);
		
		this.tfFechaPagoHasta = new JFormattedTextField(this.formatter);
		this.tfFechaPagoHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaPagoHasta);
		
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

	public ChequeBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ChequeBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ChequeTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ChequeTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public JLabel getLblImporteDesde() {
		return lblImporteDesde;
	}

	public JLabel getLblImporteHasta() {
		return lblImporteHasta;
	}

	public JLabel getLblNumeroCheque() {
		return lblNumeroCheque;
	}

	public JFormattedTextField getTfFechaEmisionDesde() {
		return tfFechaEmisionDesde;
	}

	public JFormattedTextField getTfFechaEmisionHasta() {
		return tfFechaEmisionHasta;
	}

	public JFormattedTextField getTfFechaPagoHasta() {
		return tfFechaPagoHasta;
	}

	public JFormattedTextField getTfFechaPagoDesde() {
		return tfFechaPagoDesde;
	}

	public TFormattedTextFieldImporte getTfImporteDesde() {
		return tfImporteDesde;
	}

	public TFormattedTextFieldImporte getTfImporteHasta() {
		return tfImporteHasta;
	}

	public JTextField getTfNumeroCheque() {
		return tfNumeroCheque;
	}

}
