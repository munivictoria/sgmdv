package com.trascender.caja.gui.cobro;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.component.TTextField;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public class DetalleCobroSelladoAdministrativoView extends ABMView {

	private static final long serialVersionUID = 1368253719162322951L;

	private TLabel lblCodigoBarras;
	private JTextField tfCodigoBarras;
	private TLabel lblConcepto;
	private JTextField tfConcepto;
	private TLabel lblFechaEmision;
	private JFormattedTextField tfFechaEmision;
	private TLabel lblEstado;
	private JTextField tfEstado;
	private TLabel lblMonto;
	private TFormattedTextFieldImporte tfMonto;
	private TLabel lblObservaciones;
	private JTextArea taObservaciones;
	
	private MaskFormatter formatter;
	
	private final String NOMBRE_RECURSO = "DetalleCobroSellado";
	
	public DetalleCobroSelladoAdministrativoView(JDialog owner) {
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
		this.lblFechaEmision = new TLabel();
		this.lblFechaEmision.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblFechaEmision"));
		this.lblFechaEmision.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblFechaEmision);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.tfFechaEmision = new JFormattedTextField(this.formatter);
		this.tfFechaEmision.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfFechaEmision);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblEstado);
		
		this.tfEstado = new TTextField();
		this.tfEstado.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfEstado);
		
		numFila++;
		this.lblMonto = new TLabel();
		this.lblMonto.setText(MessagesCaja.getString(NOMBRE_RECURSO+".lblMonto"));
		this.lblMonto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblMonto);
		
		this.tfMonto = new TFormattedTextFieldImporte();
		this.tfMonto.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfMonto);
		
		numFila++;
		this.lblObservaciones = new TLabel();
		this.lblObservaciones.setText(MessagesCaja.getString(NOMBRE_RECURSO + ".lblObservaciones"));
		this.lblObservaciones.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblObservaciones);
		
		this.taObservaciones = new JTextArea();
		this.taObservaciones.setLineWrap(true);
		this.taObservaciones.setWrapStyleWord(true);
		JScrollPane spObservaciones = new JScrollPane(this.taObservaciones);
		spObservaciones.setBounds(Util.getBoundsColumnaInputTextArea(numFila));
		this.getPnlCuerpo().add(spObservaciones);
		numFila++;
		
		this.getPnlPie().getBtnAceptar().setVisible(false);
		this.setTextoBtnCerrar();
		
		setTamanioPosicionVentana(numFila +1); 
	}
	
	public void setTextoBtnCerrar(){
		this.getPnlPie().getBtnCancelar().setText(MessagesCaja.getString("Application.btnCerrar"));
		this.getPnlPie().getBtnCancelar().setMnemonic(MessagesCaja.getChar("Application.btnCerrarMnemonic"));
		this.getPnlPie().getBtnCancelar().setToolTipText(MessagesCaja.getString("Application.btnCerrarToolTip"));
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesCaja.getString(NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}
	@Override
	public void setTextoBtnAceptar() {
		
	}

	@Override
	public void setTituloVentana() {
		this.getPnlCabecera().getLblTitulo().setText(MessagesCaja.getString(NOMBRE_RECURSO+".titulo"));	
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public JTextField getTfCodigoBarras() {
		return tfCodigoBarras;
	}

	public JTextField getTfConcepto() {
		return tfConcepto;
	}

	public JFormattedTextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public JTextField getTfEstado() {
		return tfEstado;
	}

	public TFormattedTextFieldImporte getTfMonto() {
		return tfMonto;
	}

	public JTextArea getTaObservaciones() {
		return taObservaciones;
	}

}
