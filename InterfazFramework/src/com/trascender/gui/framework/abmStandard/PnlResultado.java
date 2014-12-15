package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesTamanio;
 
// JOSE
public class PnlResultado extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private TLabel lblDescripcion;
	private JLabel lblNumero;
	private JPanel pnlLinea;
	private JPanel pnlDivisor; 
	
	
	public PnlResultado() {
		this.init();
	}
		
	private void init() {
		this.setPreferredSize(new Dimension(ConstantesTamanio.LBL_WIDTH * 2, ConstantesTamanio.LBL_HEIGHT+10));
//		Border bordePanel = BorderFactory.createCompoundBorder(
//				BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY), 
//				BorderFactory.createEmptyBorder(
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER-7,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER-7));
		
		Border bordePanel = BorderFactory.createEmptyBorder(5, 2, 0, 2);
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER - 5,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER - 7,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER - 5,
//						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER - 7);
		
		this.setBorder(bordePanel);
		
		this.setLayout(new BorderLayout());
		
		Font locFont1 = new Font("Vardana", Font.BOLD, 12);
		
		this.lblDescripcion = new TLabel();
		this.lblDescripcion.setText("Totales");
		this.lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT); 
		this.lblDescripcion.setFont(locFont1);
		this.add(this.lblDescripcion, BorderLayout.CENTER);
		
		this.lblNumero = new JLabel();
		this.lblNumero.setFont(locFont1);
		this.lblNumero.setPreferredSize(new Dimension(ConstantesTamanio.TF_FECHA_WIDTH, ConstantesTamanio.LBL_HEIGHT));
//		this.lblNumero.setText("0");
		this.lblNumero.setHorizontalAlignment(JLabel.RIGHT);
		this.add(this.lblNumero, BorderLayout.EAST);
		
		pnlLinea = new JPanel(null);
		pnlLinea.setPreferredSize(new Dimension(ConstantesTamanio.TF_FECHA_WIDTH, 1));
		Border linea = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY);
		pnlLinea.setBorder(linea);

		pnlDivisor = new JPanel(new BorderLayout());
		pnlDivisor.add(pnlLinea, BorderLayout.EAST);
		this.add(pnlDivisor, BorderLayout.SOUTH);
		
	}

	public TLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JPanel getPnlLinea() {
		return pnlLinea;
	}
	
}
