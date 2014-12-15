package com.trascender.contabilidad.gui.ayuda;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.model.TAbstractABMModel;

@SuppressWarnings("unchecked")
public class AcercaDe  extends ABMController {

	private AcercaDeView view;
	
	public AcercaDe(JDialog owner) {
		this.view = new AcercaDeView(owner);
		this.init();
	}
	
	public AcercaDe(JFrame owner) {
		this.view = new AcercaDeView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getTaProgamadores().setEditable(false);
		this.getView().getSpProgramadores().setVisible(false);
	}
	
	private void setListeners() {
		this.getView().getPnlCabecera().getLblImagen().addMouseListener(new LblImagenListener(this));
	}
	
	@Override
	protected void actualizarABMModel() {
	}

	@Override
	protected void actualizarView() {
	}

	@Override
	protected TAbstractABMModel getAbmModel() {
		return null;
	}

	@Override
	protected AcercaDeView getView() {
		return view;
	}

}

class LblImagenListener extends MouseAdapter {
	private AcercaDe controller;
	public LblImagenListener(AcercaDe controller) {
		this.controller = controller;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 ) {
			if (this.controller.getView().getSpProgramadores().isVisible()) {
				this.controller.getView().getSpProgramadores().setVisible(false);
			} else {
				this.controller.getView().getSpProgramadores().setVisible(true);
			}
		}
	}
}