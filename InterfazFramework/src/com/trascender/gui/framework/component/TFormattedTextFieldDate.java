package com.trascender.gui.framework.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;

public class TFormattedTextFieldDate extends JFormattedTextField {
	
	private static final long serialVersionUID = 3542096919926404745L;

	private final String FORMATO_FECHA = "dd/MM/yyyy";
	
	
//	public TFormattedTextFieldDate() {
//		MaskFormatter mask = null;
//		DefaultFormatterFactory dateMaskFormat = null;
//		try {
//			mask = new MaskFormatter(Messages.getString("Application.dateMask"));
//			mask.setAllowsInvalid(false);
//			mask.setOverwriteMode(true);
//			//mask.setPlaceholderCharacter('_');
//			dateMaskFormat = new DefaultFormatterFactory('');
//		} catch (java.text.ParseException e) {
//			System.err.println("Formato de fecha no válido para la máscara: " +
//					e.getMessage());
//			e.printStackTrace();
//		}
//		this.setFormatterFactory(formatter);
//	}
//	
//
//	private void setFormatterFactory(MaskFormatter formatter) {
//		// TODO Auto-generated method stub
//		
//	}

	public Date getDate() {
		String fecha = this.getText();
		Date laFecha = null;
		if (fecha != null && fecha != "") {
			try {
				Date dateSimple = new SimpleDateFormat(FORMATO_FECHA).parse(fecha);
				laFecha = dateSimple;
			} catch(Exception e) {
				laFecha = null;
			}
		}
		return laFecha;
	}
}
