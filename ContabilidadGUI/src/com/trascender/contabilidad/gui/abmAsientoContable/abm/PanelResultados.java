package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.trascender.gui.framework.component.TLabel;

public class PanelResultados extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private TLabel lblTitulo;
	private JLabel lblTotalDebe;
	private JLabel lblTotalHaber;
	private SpringLayout layoutPrueba;
	private TextField locAcomodador; // Arreglar esto ya!
	
	public PanelResultados() {
		this.init();
	}
		
	private void init() {
		this.setLayout(new GridLayout(1,0));
		Font locFont1 = new Font( "Vardana", Font.BOLD, 12 );
			
		this.lblTitulo = new TLabel();
		this.lblTitulo.setText(" Total ");
		this.lblTitulo.setHorizontalAlignment(SwingConstants.LEFT); 
		this.lblTitulo.setFont(locFont1);
		
		this.lblTotalDebe = new JLabel();
		this.lblTotalDebe.setFont(locFont1);
		this.lblTotalDebe.setText("0,00");
		this.lblTotalDebe.setHorizontalAlignment(JLabel.RIGHT);
			
		this.lblTotalHaber = new JLabel();
		this.lblTotalHaber.setFont(locFont1);
		this.lblTotalHaber.setText("0,00");
		this.lblTotalHaber.setHorizontalAlignment(JLabel.RIGHT);
		
        locAcomodador = new TextField();
	    locAcomodador.setVisible(false);
		
		this.add(lblTitulo);
		this.add(lblTotalDebe);
		this.add(lblTotalHaber);
		this.add(locAcomodador);
	}

	public TextField getLocAcomodador() {
		return locAcomodador;
	}

	public void setLocAcomodador(TextField locAcomodador) {
		this.locAcomodador = locAcomodador;
	}

	public SpringLayout getLayoutPrueba() {
		return layoutPrueba;
	}

	public void setLayoutPrueba(SpringLayout layoutPrueba) {
		this.layoutPrueba = layoutPrueba;
	}

	public JLabel getLblTotalDebe() {
		return lblTotalDebe;
	}

	public void setLblTotalDebe(JLabel lblTotalDebe) {
		this.lblTotalDebe = lblTotalDebe;
	}

	public JLabel getLblTotalHaber() {
		return lblTotalHaber;
	}

	public void setLblTotalHaber(JLabel lblTotalHaber) {
		this.lblTotalHaber = lblTotalHaber;
	}
}
