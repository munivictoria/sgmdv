package com.trascender.caja.gui.cobro;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.component.TTextField;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public class DetalleCobroView extends ABMView{

	private static final long serialVersionUID = -3082350242953244634L;
	
	private TLabel lblCodigoBarras;
	private JTextField tfCodigoBarras;
	private TLabel lblConcepto;
	private JTextField tfConcepto;
	private TLabel lblTasa;
	private TFormattedTextFieldImporte tfTasa;
	private TLabel lblFechaVencimiento;
	private JFormattedTextField tfFechaVencimiento;
	private TLabel lblEstado;
	private JTextField tfEstado;
	private TLabel lblMontoExento;
	private TFormattedTextFieldImporte tfMontoExento;
	private TLabel lblInteres;
	private TFormattedTextFieldImporte tfInteres;
	private TLabel lblRecargo;
	private TFormattedTextFieldImporte tfRecargo;
	private TLabel lblMulta;
	private TFormattedTextFieldImporte tfMulta;
	private TLabel lblTotal;
	private TFormattedTextFieldImporte tfTotal;
	
	private MaskFormatter formatter;
	
	private final String NOMBRE_RECURSO = "DetalleCobro";

	public DetalleCobroView(JDialog owner){
		super(owner);
		this.init();
	}
	
	public void init(){
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.getPnlCuerpo().setLayout(null);
		
		int numFila = -1;
		
		numFila++;
		this.lblCodigoBarras = new TLabel();
		this.lblCodigoBarras.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblCodigoBarras"));
		this.lblCodigoBarras.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCodigoBarras);
		
		this.tfCodigoBarras = new JTextField();
		this.tfCodigoBarras.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCodigoBarras);
		
		numFila++;
		this.lblConcepto = new TLabel();
		this.lblConcepto.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblConcepto"));
		this.lblConcepto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblConcepto);
		
		this.tfConcepto = new JTextField();
		this.tfConcepto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfConcepto);
		
		numFila++;
		this.lblFechaVencimiento = new TLabel();
		this.lblFechaVencimiento.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblFechaVencimiento"));
		this.lblFechaVencimiento.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFechaVencimiento);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaVencimiento = new JFormattedTextField(this.formatter);
		this.tfFechaVencimiento.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFechaVencimiento);
		
		numFila++;
		this.lblTasa = new TLabel();
		this.lblTasa.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblTasa"));
		this.lblTasa.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTasa);
		
		this.tfTasa = new TFormattedTextFieldImporte();
		this.tfTasa.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		
		this.getPnlCuerpo().add(this.tfTasa);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblEstado);
		
		this.tfEstado = new TTextField();
		this.tfEstado.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfEstado);
		
		numFila++;
		this.lblMontoExento = new TLabel();
		this.lblMontoExento.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblMontoExento"));
		this.lblMontoExento.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblMontoExento);
		
		this.tfMontoExento = new TFormattedTextFieldImporte();
		this.tfMontoExento.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfMontoExento);
		
		
		numFila++;
		this.lblInteres = new TLabel();
		this.lblInteres.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblInteres"));
		this.lblInteres.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblInteres);
		
		this.tfInteres = new TFormattedTextFieldImporte();
		this.tfInteres.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfInteres);
		
		numFila++;
		this.lblRecargo = new TLabel();
		this.lblRecargo.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblRecargo"));
		this.lblRecargo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(lblRecargo);
		
		this.tfRecargo= new TFormattedTextFieldImporte();
		this.tfRecargo.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfRecargo);
		
		numFila++;
		this.lblMulta = new TLabel();
		this.lblMulta.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblMulta"));
		this.lblMulta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(lblMulta);
		
		this.tfMulta = new TFormattedTextFieldImporte();
		this.tfMulta.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfMulta);
		
		numFila++;
		this.lblTotal = new TLabel();
		this.lblTotal.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblTotal"));
		this.lblTotal.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTotal);
		
		this.tfTotal = new TFormattedTextFieldImporte();
		this.tfTotal.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfTotal);
		
		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.setTextoBtnCerrar();
		
		setTamanioPosicionVentana(numFila +1); 
	}
	
	@Override
	public void setTituloVentana() {
		this.getPnlCabecera().getLblTitulo().setText(MessagesCaja.getString(NOMBRE_RECURSO+".titulo"));	
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setTextoBtnAceptar() {
		
	}
	
	public void setTextoBtnCerrar(){
		this.getPnlPie().getBtnCancelar().setText(MessagesCaja.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCancelar().setMnemonic(MessagesCaja.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCancelar().setToolTipText(MessagesCaja.getString("Application.btnCerrarToolTip"));
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public JTextField getTfCodigoBarras() {
		return tfCodigoBarras;
	}

	public TFormattedTextFieldImporte getTfTasa() {
		return tfTasa;
	}

	public JTextField getTfConcepto() {
		return tfConcepto;
	}

	public JFormattedTextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public JTextField getTfEstado() {
		return tfEstado;
	}

	public TFormattedTextFieldImporte getTfInteres() {
		return tfInteres;
	}

	public TFormattedTextFieldImporte getTfRecargo() {
		return tfRecargo;
	}

	public TFormattedTextFieldImporte getTfMulta() {
		return tfMulta;
	}

	public TFormattedTextFieldImporte getTfTotal() {
		return tfTotal;
	}

	public TFormattedTextFieldImporte getTfMontoExento() {
		return tfMontoExento;
	}
}
