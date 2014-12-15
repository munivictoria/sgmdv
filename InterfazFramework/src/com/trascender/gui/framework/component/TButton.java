package com.trascender.gui.framework.component;

import java.awt.Font;

import javax.swing.JButton;

import com.trascender.gui.framework.recursos.Messages;

public class TButton extends JButton {

	private static final long serialVersionUID = -108391063932035840L;

	private static final Font font = new Font(
			Messages.getString("Application.btnFontName"),
			Integer.valueOf(Messages.getString("Application.btnFontStyle")), 
			Integer.valueOf(Messages.getString("Application.btnFontSize")));

	public TButton() {
		super();
		this.setFont(TButton.font);
	}
	
}
