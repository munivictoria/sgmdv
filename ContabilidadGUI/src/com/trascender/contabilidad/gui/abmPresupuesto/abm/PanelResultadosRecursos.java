package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.trascender.gui.framework.component.TLabel;

public class PanelResultadosRecursos extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private TLabel lblTitulo;
	private JLabel lblTotalMontoEstimado;
	private JLabel lblTotalMontoRecaudado;

	public PanelResultadosRecursos() {
		this.init();
	}
	
	private void init() {
		this.setLayout(new GridLayout(1,0));
		Font locFont1 = new Font( "Vardana", Font.BOLD, 12 );
		
		this.lblTitulo = new TLabel();
		this.lblTitulo.setText(" Total ");
		this.lblTitulo.setHorizontalAlignment(SwingConstants.LEFT); 
		this.lblTitulo.setFont(locFont1);

		this.lblTotalMontoEstimado = new JLabel();
		this.lblTotalMontoEstimado.setFont(locFont1);
		this.lblTotalMontoEstimado.setHorizontalAlignment(JLabel.RIGHT);  
		
		this.lblTotalMontoRecaudado = new JLabel();
		this.lblTotalMontoRecaudado.setFont(locFont1);
		this.lblTotalMontoRecaudado.setHorizontalAlignment(JLabel.RIGHT); 
		
		this.add(lblTitulo);
		this.add(lblTotalMontoEstimado);
		this.add(lblTotalMontoRecaudado);
	}

	public JLabel getLblTotalMontoEstimado() {
		return lblTotalMontoEstimado;
	}

	public void setLblTotalMontoEstimado(JLabel lblTotalMontoEstimado) {
		this.lblTotalMontoEstimado = lblTotalMontoEstimado;
	}								

	public JLabel getLblTotalMontoRecaudado() {
		return lblTotalMontoRecaudado;
	}

	public void setLblTotalMontoRecaudado(JLabel lblTotalMontoRecaudado) {
		this.lblTotalMontoRecaudado = lblTotalMontoRecaudado;
	}
}
