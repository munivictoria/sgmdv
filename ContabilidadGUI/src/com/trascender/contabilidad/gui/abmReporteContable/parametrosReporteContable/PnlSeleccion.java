package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmTipoBalance.AdminTipoBalance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;

public abstract class PnlSeleccion extends JPanel{

	private static final long serialVersionUID = 8497774566744019169L;
	
	private JTextField textField = new JTextField();
	private PnlBotonesSeleccion panelBotones = new PnlBotonesSeleccion();
	private ParametrosReporteContable ventana;
	
	protected abstract Long getIdObjetoSeleccionado();
	protected abstract void setObjetoSeleccionado(Object pObjeto);
	
	public PnlSeleccion(ParametrosReporteContable pVentana) {
		this.ventana = pVentana;
		this.getPanelBotones().getBtnSeleccionar().addActionListener(new BtnSeleccionarListener(this));
		this.getPanelBotones().getBtnLimpiar().addActionListener(new BtnLimpiarListener(this));
	}
	
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public PnlBotonesSeleccion getPanelBotones() {
		return panelBotones;
	}

	public void setPanelBotones(PnlBotonesSeleccion panelBotones) {
		this.panelBotones = panelBotones;
	}
	
	public ParametrosReporteContable getVentana() {
		return ventana;
	}

	public void setVentana(ParametrosReporteContable ventana) {
		this.ventana = ventana;
	}

	protected abstract void seleccionarObjeto() throws Exception;
	
	
	class BtnSeleccionarListener implements ActionListener {
		private PnlSeleccion locPanel;
		
		public BtnSeleccionarListener(PnlSeleccion pPanel) {
			this.locPanel = pPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				this.locPanel.seleccionarObjeto();
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	class BtnLimpiarListener implements ActionListener {
		
		private PnlSeleccion locPanel;
		
		public BtnLimpiarListener(PnlSeleccion pPanel) {
			this.locPanel = pPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.locPanel.textField.setText("");
			this.locPanel.setObjetoSeleccionado(null);
		}
	}
}
