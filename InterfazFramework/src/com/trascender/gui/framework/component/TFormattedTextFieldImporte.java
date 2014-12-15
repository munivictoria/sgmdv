package com.trascender.gui.framework.component;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class TFormattedTextFieldImporte extends JFormattedTextField {

	private static final long serialVersionUID = 5596768864273092954L;
	
	public TFormattedTextFieldImporte(){
		// Creamos el campo
		JFormattedTextField formatterNumero = new JFormattedTextField();
		
		// Formato de visualización
		NumberFormat dispFormat = NumberFormat.getNumberInstance();
		dispFormat.setMaximumFractionDigits(2);
		dispFormat.setMinimumFractionDigits(2);
		
		// Formato de edición: inglés (separador decimal: el punto)
		NumberFormat editFormat = NumberFormat.getNumberInstance(Locale.US);
		
		// Para la edición, Si queremos separadores de millares
		editFormat.setGroupingUsed(true);
		
		// Creamos los formateadores de números
		NumberFormatter dnFormat = new NumberFormatter(dispFormat);
		NumberFormatter enFormat = new NumberFormatter(editFormat);
		
		// Creamos la factoría de formateadores especificando los
		// formateadores por defecto, de visualización y de edición
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);
		
		// El formateador de edición admite caracteres incorrectos
		enFormat.setAllowsInvalid(true);
		
		// Asignamos la factoría al campo
		formatterNumero.setFormatterFactory(currFactory);

		this.setFormatterFactory(currFactory);
		
		// Alineamos el importe a la derecha
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setValue(0.00);
	}

}
