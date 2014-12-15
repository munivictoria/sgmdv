package com.trascender.gui.framework.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TDoubleRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 837007545099054811L;

	NumberFormat formato = NumberFormat.getNumberInstance(Locale.ITALIAN);

	@Override
	protected void setValue(Object value) {
		if (value != null) {
			if (value instanceof Number) {
				if (formato instanceof DecimalFormat) {
					((DecimalFormat) formato).setDecimalSeparatorAlwaysShown(true);
					((DecimalFormat) formato).setMaximumFractionDigits(2);
					((DecimalFormat) formato).setMinimumFractionDigits(2);
					
				}
				this.setText(formato.format(value));
				this.setHorizontalAlignment(SwingConstants.RIGHT);
			}

		} else {
			this.setText("");
		}

	}
}
