package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionABMModel;
import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionTableModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMParametroRetencionView extends ABMView {

	private static final long serialVersionUID = -8364151261849845401L;
	
	private ParametroRetencionABMModel abmModel;
	private ParametroRetencionTableModel tableModel;
	
	private TLabel lblNombre;
	private JTextField tfNombre;
	private TLabel lblAlicuota;
	private JTextField tfAlicuota;
	private TLabel lblDeducirIVA;
	private JCheckBox chDeducirIVA;
	private TLabel lblImporteMinimo;
	private TFormattedTextFieldImporte tfImporteMinimo;
	private TLabel lblPorcentaje;
	private JTextField tfPorcentaje;
	
	private final String NOMBRE_RECURSO = "ABMParametroRetencion";
			
	public ABMParametroRetencionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMParametroRetencionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;

		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNombre);

		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNombre);
		
		numFila++;
		this.lblAlicuota = new TLabel();
		this.lblAlicuota.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblAlicuota"));
		this.lblAlicuota.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblAlicuota);

		this.tfAlicuota = new JTextField();
		this.tfAlicuota.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfAlicuota);
		
		numFila++;
		this.lblDeducirIVA = new TLabel();
		this.lblDeducirIVA.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblDeducirIVA"));
		this.lblDeducirIVA.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblDeducirIVA);
		
		this.chDeducirIVA = new JCheckBox();
		this.chDeducirIVA.setBounds(Util.getBoundsColumnaCheckBox(numFila));
		this.getPnlCuerpo().add(this.chDeducirIVA);
		
		numFila++;
		this.lblImporteMinimo = new TLabel();
		this.lblImporteMinimo.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblImporteMinimo"));
		this.lblImporteMinimo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporteMinimo);
		
		this.tfImporteMinimo = new TFormattedTextFieldImporte();
		this.tfImporteMinimo.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporteMinimo);
		
		numFila++;
		this.lblPorcentaje = new TLabel();
		this.lblPorcentaje.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblPorcentaje"));
		this.lblPorcentaje.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblPorcentaje);
		
		this.tfPorcentaje = new JTextField();
		this.tfPorcentaje.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfPorcentaje);
		
		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public ParametroRetencionABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(ParametroRetencionABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public ParametroRetencionTableModel getTableModel() {
		return tableModel;
	}

	public void setTablemodel(ParametroRetencionTableModel tableModel) {
		this.tableModel = tableModel;
//		this.getPnlCuerpo().getTblDatos().setModel(tablemodel);
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfAlicuota() {
		return tfAlicuota;
	}

	public JCheckBox getChDeducirIVA() {
		return chDeducirIVA;
	}

	public TFormattedTextFieldImporte getTfImporteMinimo() {
		return tfImporteMinimo;
	}

	public JTextField getTfPorcentaje() {
		return tfPorcentaje;
	}

	public TLabel getLblNombre() {
		return lblNombre;
	}

	public TLabel getLblAlicuota() {
		return lblAlicuota;
	}

	public TLabel getLblDeducirIVA() {
		return lblDeducirIVA;
	}

	public TLabel getLblImporteMinimo() {
		return lblImporteMinimo;
	}

	public TLabel getLblPorcentaje() {
		return lblPorcentaje;
	}

}
