package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.trascender.gui.framework.component.TLabel;

public class PanelResultadosGastos extends JPanel{

	private static final long serialVersionUID = 1L;

	private TLabel lblTitulo;
	private JLabel lblTotalMontoPresupuestado;
	private JLabel lblTotalMontoComprometido;
	private JLabel lblTotalMontoPagado;
	
	public PanelResultadosGastos() {
		this.init();
	}
	
	private void init() {
		this.setLayout(new GridLayout(1,0));
		Font locFont1 = new Font( "Vardana", Font.BOLD, 12 );
		
		this.lblTitulo = new TLabel();
		this.lblTitulo.setText(" Total ");
		this.lblTitulo.setHorizontalAlignment(SwingConstants.LEFT); 
		this.lblTitulo.setFont(locFont1);
	
		this.lblTotalMontoPresupuestado = new JLabel();
		this.lblTotalMontoPresupuestado.setFont(locFont1);
		this.lblTotalMontoPresupuestado.setHorizontalAlignment(JLabel.RIGHT);
		
		this.lblTotalMontoComprometido = new JLabel();
		this.lblTotalMontoComprometido.setFont(locFont1);
		this.lblTotalMontoComprometido.setHorizontalAlignment(JLabel.RIGHT);
		
		this.lblTotalMontoPagado = new JLabel();
		this.lblTotalMontoPagado.setFont(locFont1);
		this.lblTotalMontoPagado.setHorizontalAlignment(JLabel.RIGHT);
	
		this.add(lblTitulo);
		this.add(lblTotalMontoPresupuestado);
		this.add(lblTotalMontoComprometido);
		this.add(lblTotalMontoPagado);
	}

	public JLabel getLblTotalMontoPresupuestado() {
		return lblTotalMontoPresupuestado;
	}

	public void setLblTotalMontoPresupuestado(JLabel lblTotalMontoPresupuestado) {
		this.lblTotalMontoPresupuestado = lblTotalMontoPresupuestado;
	}

	public JLabel getLblTotalMontoComprometido() {
		return lblTotalMontoComprometido;
	}

	public void setLblTotalMontoComprometido(JLabel lblTotalMontoComprometido) {
		this.lblTotalMontoComprometido = lblTotalMontoComprometido;
	}

	public JLabel getLblTotalMontoPagado() {
		return lblTotalMontoPagado;
	}

	public void setLblTotalMontoPagado(TLabel lblTotalMontoPagado) {
		this.lblTotalMontoPagado = lblTotalMontoPagado;
	}

}
