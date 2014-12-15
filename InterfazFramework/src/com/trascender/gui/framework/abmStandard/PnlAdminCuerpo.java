package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PnlAdminCuerpo extends JPanel {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	public PnlAdminCuerpo() {
		this.init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		
		// ariel: POSICION EN EL CONTENT_PANE
//		this.getContentPane().add(this.pnlCuerpo, BorderLayout.CENTER);
	}
}
