package com.trascender.contabilidad.gui.abmDebitoBancario;

import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminDebitoBancarioView extends AdminView {

	private static final long serialVersionUID = 1L;
	
	private DebitoBancarioBusquedaModel busquedaModel;
	private DebitoBancarioTableModel tableModel;
	
	private JLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmisionDesde;
	private JFormattedTextField tfFechaEmisionHasta;
	
	private JLabel lblImporteDesde;
	private TFormattedTextFieldImporte tfImporteDesde;
	private JLabel lblImporteHasta;
	private TFormattedTextFieldImporte tfImporteHasta;
	
	private MaskFormatter formatter;
	private static final String NOMBRE_RECURSO = "AdminDebitoBancario";
	
	public AdminDebitoBancarioView(JFrame owner){
		super(owner);
		this.init();
	}
	
	public AdminDebitoBancarioView(JDialog owner){
		super(owner);
		this.init();
	}
	
	private void init(){
		int numFila = -1;
		
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

	public void setTableModel(DebitoBancarioTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public DebitoBancarioTableModel getTableModel() {
		return tableModel;
	}
	
	public DebitoBancarioBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(DebitoBancarioBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}
	
	public JFormattedTextField getTfFechaEmisionDesde() {
		return tfFechaEmisionDesde;
	}

	public void setTfFechaEmisionDesde(JFormattedTextField tfFechaEmisionDesde) {
		this.tfFechaEmisionDesde = tfFechaEmisionDesde;
	}

	public JFormattedTextField getTfFechaEmisionHasta() {
		return tfFechaEmisionHasta;
	}

	public void setTfFechaEmisionHasta(JFormattedTextField tfFechaEmisionHasta) {
		this.tfFechaEmisionHasta = tfFechaEmisionHasta;
	}

	public TFormattedTextFieldImporte getTfImporteDesde() {
		return tfImporteDesde;
	}

	public void setTfImporteDesde(TFormattedTextFieldImporte tfImporteDesde) {
		this.tfImporteDesde = tfImporteDesde;
	}

	public TFormattedTextFieldImporte getTfImporteHasta() {
		return tfImporteHasta;
	}

	public void setTfImporteHasta(TFormattedTextFieldImporte tfImporteHasta) {
		this.tfImporteHasta = tfImporteHasta;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JLabel getLblImporteDesde() {
		return lblImporteDesde;
	}

	public JLabel getLblImporteHasta() {
		return lblImporteHasta;
	}

	public void setLblFechaEmision(JLabel lblFechaEmision) {
		this.lblFechaEmision = lblFechaEmision;
	}

	public void setLblImporteDesde(JLabel lblImporteDesde) {
		this.lblImporteDesde = lblImporteDesde;
	}

	public void setLblImporteHasta(JLabel lblImporteHasta) {
		this.lblImporteHasta = lblImporteHasta;
	}

	
}
