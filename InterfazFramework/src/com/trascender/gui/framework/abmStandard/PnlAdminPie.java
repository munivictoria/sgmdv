package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlAdminPie extends JPanel {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private JButton btnSeleccionar;
	private JSeparator separator;
	private JButton btnConsultar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnRestaurar;
	private JButton btnCerrar;
	
	private JButton btnReporte;
	
	public PnlAdminPie() {
		this.init();
	}
	
	private void init() {
		GridBagLayout gblPie = new GridBagLayout();
		this.setLayout(gblPie);

		GridBagConstraints gbcPnlPie = new GridBagConstraints();
		gbcPnlPie.anchor = GridBagConstraints.PAGE_END;
	
		this.setBackground(Constantes.COLOR_ADMIN);
		Border bordePie = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER));
		this.setBorder(bordePie);
		
		// BOTONES (FILA 1)
		
		GridBagConstraints gbcBtns = new GridBagConstraints();
		
		int numColumna = -1; // Numero de Columna para colocar los Botones
		
		// SELECCIONAR
		numColumna++;
		this.btnSeleccionar = new JButton();
		this.btnSeleccionar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnSeleccionar.setText(Messages.getString("Application.btnSeleccionar"));
		this.btnSeleccionar.setMnemonic(Messages.getString("Application.btnSeleccionarMnemonic").charAt(0));
		gbcBtns.anchor = GridBagConstraints.WEST;
		gbcBtns.gridx=numColumna; 
		gbcBtns.gridy=0;
		gbcBtns.weightx = 150;
		this.add(this.btnSeleccionar, gbcBtns);
		
		// Propiedades comunes
		gbcBtns.gridy = 0;
		gbcBtns.weightx = 0;
		
		// SEPARADOR
		numColumna++;
		this.separator = new JSeparator(SwingConstants.VERTICAL);
		this.separator.setMaximumSize(new Dimension(2, 10));
		this.separator.setBorder(new BevelBorder(BevelBorder.LOWERED));
		gbcBtns.gridx = numColumna;
		this.add(this.separator, gbcBtns);
		
		// Propiedades comunes
		gbcBtns.anchor = GridBagConstraints.EAST;
		gbcBtns.insets = new Insets(0,ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN,0,0);
		
		// BOTON AGREGAR
		numColumna++;
		this.btnConsultar = new JButton();
		this.btnConsultar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnConsultar.setText(Messages.getString("Application.btnConsultar"));
		this.btnConsultar.setMnemonic(Messages.getString("Application.btnConsultarMnemonic").charAt(0));
		this.btnConsultar.setVisible(false);
		gbcBtns.gridx=numColumna;
		this.add(btnConsultar, gbcBtns);
		
		// BOTON AGREGAR
		numColumna++;
		this.btnAgregar = new JButton();
		this.btnAgregar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnAgregar.setText(Messages.getString("Application.btnAgregar"));
		this.btnAgregar.setMnemonic(Messages.getString("Application.btnAgregarMnemonic").charAt(0));
		gbcBtns.gridx=numColumna;
		this.add(btnAgregar, gbcBtns);
		
		// BOTON MODIFICAR
		numColumna++;
		this.btnModificar = new JButton();
		btnModificar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		btnModificar.setText(Messages.getString("Application.btnModificar"));
		btnModificar.setMnemonic(Messages.getString("Application.btnModificarMnemonic").charAt(0));
		gbcBtns.gridx=numColumna;
		this.add(btnModificar, gbcBtns);
		
		// BOTON ELIMINAR
		numColumna++;
		this.btnEliminar = new JButton();
		btnEliminar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		btnEliminar.setText(Messages.getString("Application.btnEliminar"));
		btnEliminar.setMnemonic(Messages.getString("Application.btnEliminarMnemonic").charAt(0));
		gbcBtns.gridx=numColumna;
		this.add(btnEliminar, gbcBtns);
		
		// BOTON RESTAURAR
//		numColumna++;
//		this.btnRestaurar = new JButton();
//		btnRestaurar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
//		btnRestaurar.setText(Messages.getString("Application.btnRestaurar"));
//		btnRestaurar.setMnemonic(Messages.getString("Application.btnRestaurarMnemonic").charAt(0));
//		gbcBtns.gridx=numColumna;
//		this.add(btnRestaurar, gbcBtns);
		
		
		// BOTONES (FILA 2) 
		
		// Propiedades comunes
		gbcBtns.gridy=1;
		gbcBtns.insets = new Insets(ConstantesSeparacion.SEPARADOR_VERTICAL_BTN,ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN,0,0);
		
		// BOTON CERRAR
		this.btnCerrar = new JButton();
		gbcBtns.gridx=numColumna;
		btnCerrar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		btnCerrar.setText(Messages.getString("Application.btnCerrar"));
		btnCerrar.setMnemonic(Messages.getString("Application.btnCerrarMnemonic").charAt(0));
		this.add(btnCerrar,gbcBtns);
		numColumna--;
		
		numColumna= 0;
		
//		// BOTON REPORTE
//		this.btnReporte = new JButton();
//		gbcBtns.gridx=numColumna;
//		btnReporte.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
//		btnReporte.setText(Messages.getString("Application.btnReporte"));
//		btnReporte.setMnemonic(Messages.getString("Application.btnReporteMnemonic").charAt(0));
//		gbcBtns.anchor = GridBagConstraints.WEST;
//		gbcBtns.gridx=numColumna; 
//		gbcBtns.weightx = 150;
//		gbcBtns.insets = new Insets(0,0,0,0);
//		this.add(btnReporte,gbcBtns);
//		numColumna--;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnRestaurar() {
		return btnRestaurar;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}
}
