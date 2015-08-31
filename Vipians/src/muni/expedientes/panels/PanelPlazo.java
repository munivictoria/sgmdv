/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.PlazoProcedimiento;
import com.trascender.presentacion.conversores.Conversor;

public class PanelPlazo {

	private Checkbox chDiasCorridos = new Checkbox();
	private TextField tfCantidadDias = new TextField();
	private TextField tfCantidadExtensiones = new TextField();
	private Label lblCantidadDias = new Label();

	public TextField getTfCantidadDias() {
		return tfCantidadDias;
	}

	public void setTfCantidadDias(TextField tfCantidadDias) {
		this.tfCantidadDias = tfCantidadDias;
	}

	public Label getLblCantidadDias() {
		return lblCantidadDias;
	}

	public void setLblCantidadDias(Label lblCantidadDias) {
		this.lblCantidadDias = lblCantidadDias;
	}

	public Checkbox getChDiasCorridos() {
		return chDiasCorridos;
	}

	public void setChDiasCorridos(Checkbox chDiasCorridos) {
		this.chDiasCorridos = chDiasCorridos;
	}

	public TextField getTfCantidadExtensiones() {
		return tfCantidadExtensiones;
	}

	public void setTfCantidadExtensiones(TextField tfCantidadExtensiones) {
		this.tfCantidadExtensiones = tfCantidadExtensiones;
	}

	private Integer getTextFieldValueInteger(TextField pTextField) {
		Integer retorno = null;
		if(pTextField.getText() != null && !pTextField.getText().toString().trim().isEmpty()) {
			retorno = Conversor.getIntegerDeString(pTextField.getText().toString().trim());
		}
		return retorno;
	}

	public PlazoProcedimiento guardarDatos(PlazoProcedimiento plazo) {
		if(plazo != null) {
			plazo.setDias(getTextFieldValueInteger(tfCantidadDias));
			plazo.setCantidadExtensiones(getTextFieldValueInteger(tfCantidadExtensiones));
			plazo.setDiasCorridos(chDiasCorridos.isChecked());
		} else if(!emptyFields()) {
			plazo = new PlazoProcedimiento();
			plazo.setDias(getTextFieldValueInteger(tfCantidadDias));
			plazo.setCantidadExtensiones(getTextFieldValueInteger(tfCantidadExtensiones));
			plazo.setDiasCorridos(chDiasCorridos.isChecked());
		}
		
		return plazo;
	}

	public void mostrarDatos(PlazoProcedimiento plazo) {
		if(plazo != null) {
			tfCantidadDias.setText(plazo.getDias());
			chDiasCorridos.setValue(plazo.isDiasCorridos());
			tfCantidadExtensiones.setText(plazo.getCantidadExtensiones());
		} else {
			tfCantidadDias.setText(null);
			chDiasCorridos.setValue(null);
			tfCantidadExtensiones.setText(null);
		}
	}

	public boolean emptyFields() {
		return tfCantidadDias.getText() == null || tfCantidadDias.getText().equals("");
	}

}