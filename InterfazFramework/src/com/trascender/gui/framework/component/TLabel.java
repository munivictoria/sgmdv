package com.trascender.gui.framework.component;

import java.awt.Font;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TLabel extends JLabel {

	private static final long serialVersionUID = 1435950300412317020L;

	private static final Font font = new Font("Verdana", 0, 12);

	public static final String AGREGADO = " : ";

	public TLabel() {
		super();
		this.setFont(TLabel.font);
		this.setCommonProperties();
	}

	public TLabel(String text) {
		this();
		this.setText(text);
		this.setCommonProperties();
	}

	public TLabel(Double doble) {
		this();
		this.setCommonPropertiesDouble();
	}

	public TLabel(Icon image) {
		this();
		this.setIcon(image);
		this.setCommonProperties();
	}

	private void setCommonProperties() {
		this.setHorizontalAlignment(JLabel.RIGHT);
	}

	private void setCommonPropertiesDouble() {
		this.setDefaultLocale(Locale.ITALIAN );
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	@Override
	public void setText(String text) {
		super.setText(text + AGREGADO);
	}
}
