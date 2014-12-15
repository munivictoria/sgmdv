package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlABMCabecera extends JPanel {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private JLabel lblTitulo;
	private JLabel lblDescripcion;
	private JLabel lblImagen;

	public PnlABMCabecera() {
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(0,ConstantesTamanio.PNL_CABECERA_HEIGHT));
		
		Border bordeHeader = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK), 
				BorderFactory.createEmptyBorder(
						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER,
						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER,
						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER,
						ConstantesTamanio.PNL_CABECERA_EMPTY_BORDER));
		this.setBorder(bordeHeader);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbcPnlCabecera = new GridBagConstraints();
		gbcPnlCabecera.weightx= GridBagConstraints.FIRST_LINE_START;
		gbcPnlCabecera.gridx=0;
		gbcPnlCabecera.gridy=0;
		gbcPnlCabecera.anchor = GridBagConstraints.NORTHWEST;
		
		this.lblTitulo = new JLabel();
		this.lblTitulo.setText(null);
		this.lblTitulo.setFont(Constantes.FUENTE_TITULO);
		this.add(this.lblTitulo,gbcPnlCabecera);
		
		this.lblDescripcion = new JLabel();
		gbcPnlCabecera.anchor = GridBagConstraints.NORTHWEST;
		gbcPnlCabecera.gridx=0;
		gbcPnlCabecera.gridy=1;
		this.lblDescripcion.setText(null);
		this.add(this.lblDescripcion,gbcPnlCabecera);
		
		this.lblImagen = new JLabel();
		gbcPnlCabecera.anchor = GridBagConstraints.EAST;
		gbcPnlCabecera.gridx=1;
		gbcPnlCabecera.gridy=0;
		gbcPnlCabecera.fill = GridBagConstraints.VERTICAL;
		this.lblImagen.setText(null);
		this.add(this.lblImagen,gbcPnlCabecera);
		
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}
	
	public JLabel getLblImagen() {
		return lblImagen;
	}

}
