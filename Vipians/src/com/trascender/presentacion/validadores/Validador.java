/*
 * Validador.java
 *
 * Created on 12 de octubre de 2006, 07:50
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.trascender.presentacion.validadores;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.trascender.presentacion.conversores.Conversor;

/**
 * Validadores para la Presentacion
 *
 * @author Juan Pablo & Ariel
 */
public class Validador {

	private static final char PUNTO_DECIMAL_CHR = '.';
	private static final String PUNTO_DECIMAL_STR = ".";
	private static final char SIGNO_NEGATIVO_CHR = '-';
	private static final String SIGNO_NEGATIVO_STR = "-";
	private static final String CARACTER_LI = "+ ";
	private static final char SEPARADOR_DE_MILES_CHR = ',';
	private ArrayList errores;

	public Validador() {
		this.errores = new ArrayList();
	}

	public ArrayList getErrores() {
		return this.errores;
	}

	public void setErrores(ArrayList errores) {
		this.errores = errores;
	}
	//
	// METODOS PRIVADOS (eran). lol.
	//

	public boolean sonIguales(String val1, String val2) {
		boolean ok = true;
		if (val1 != null && val1 != "" && val2 != null && val2 != "") {
			ok = false;
			if (val1.equals(val2)) {
				ok = true;
			}
		}
		return ok;
	}

	public boolean cuitValido(String cuit) {
		boolean ok = true;
		if (cuit != null && cuit != "") {
			ok = false;
			String regex = "\\d\\d[-]\\d\\d\\d\\d\\d\\d\\d\\d[-]\\d";
			if (Pattern.matches(regex, cuit)) {
				if (Cuit.validar(cuit)) {
					ok = true;
				}
			}
		}
		return ok;
	}

	public boolean esEntero(String numero) {
		boolean ok = true;
		if (numero != null && numero != "") {

			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
			if (posSigno >= 0) {
				return false;
			}
			for (int i = (posSigno + 1); i < numero.length(); i++) {
				if (!Character.isDigit(numero.charAt(i))) {
					return false;
				}
			}
		}
		return ok;
	}

	public void esCharacter(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			if (((UIInput) componentes[i]) != null) {
				if ((((UIInput) componentes[i]).getValue() != null) && (!((UIInput) componentes[i]).getValue().equals(""))) {
					if (!(((UIInput) componentes[i]).getValue().toString()).matches("[a-zA-Z]*")) {
						((UIInput) componentes[i]).setValid(false);
						mensaje = "El campo '" + nombresCampos[i] + "' no es v\341lido";
						this.getErrores().add(mensaje);
					}

				}
			}
		}
		return;
	}

	//para validar q un valor sea entero positivo usar esNumero,
	//de usar sonEnteros sera nec antes validar con esPositivo,
	//sino tira error cuando modificas, en la linea 357 cast long.. a string..
	public void esNumero(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			if ((((UIInput) componentes[i]).getValue() != null) && (!((UIInput) componentes[i]).getValue().equals(""))) {
				if (!(((UIInput) componentes[i]).getValue().toString()).matches("[0-9]*")) {
					((UIInput) componentes[i]).setValid(false);
					mensaje = "El campo '" + nombresCampos[i] + "' no es v\341lido.";
					this.getErrores().add(mensaje);
				}

			}
		}
		return;
	}

	public boolean esFlotante(String numero) {
		boolean ok = true;
		if (numero != null && numero != "") {

			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
			if (posSigno > 0) {
				return false;
			}
			int first = numero.indexOf(PUNTO_DECIMAL_STR);
			int last = numero.lastIndexOf(PUNTO_DECIMAL_STR);
			if (first == 0 && numero.length() == 1) {
				//System.out.println("-----ENTRO EN ESTA CONDICION----");
				return false;
			}
			if (first != last) {
				return false;
			}
			for (int i = (posSigno + 1); i < numero.length(); i++) {
				if (!Character.isDigit(numero.charAt(i))
						&& numero.charAt(i) != PUNTO_DECIMAL_CHR
						&& numero.charAt(i) != SIGNO_NEGATIVO_CHR //&&
						//numero.charAt(i) != SEPARADOR_DE_MILES_CHR
						) {
					return false;
				}
			}
			//
		}
		return ok;
	}

	public boolean esPositivo(String numero) {
		boolean ok = true;
		if (numero != null && numero != "") {

			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
			if (posSigno >= 0) {
				return false;
			}
		}
		return ok;
	}

	/**
	 * Metodo para validar el largo de una cadena.
	 *
	 * @param cadena String a validar.
	 * @param min Minimo de caracteres que debe poseer la cadena.
	 * @param max Maximo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena no se encuentra entre min
	 * y max.
	 */
	public boolean longitudEntre(String cadena, int min, int max) {
		boolean ok = true;
		if (cadena != null && cadena != "") {
			ok = false;
			if (cadena.length() >= min && cadena.length() <= max) {
				ok = true;
			}
		}
		return ok;
	}

	/**
	 * Metodo para validar el largo minimo de una cadena.
	 *
	 * @param cadena String a validar.
	 * @param min Minimo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena es menor a min.
	 */
	public static boolean longitudMinima(String cadena, int min) {
		boolean ok = true;
		if (cadena != null && cadena != "") {
			ok = false;
			if (cadena.length() >= min) {
				ok = true;
			}
		}
		return ok;
	}

	/**
	 * Metodo para validar el largo maximo de una cadena.
	 *
	 * @param cadena String a validar.
	 * @param max Maximo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena es mayor a max.
	 */
	public boolean longitudMaxima(String cadena, int max) {
		boolean ok = true;
		if (cadena != null && cadena != "") {
			ok = false;
			if (cadena.length() < max) {
				ok = true;
			}
		}
		return ok;
	}

	/**
	 * Metodo para validar el formato de una fecha.
	 *
	 * @param fecha String de la fecha a validar.
	 * @param formato Formato que deberia tener la fecha.
	 * @return <b>false</b>, si el formato no es valido.
	 */
	public boolean fechaValida(String fecha, String formato) {
		boolean ok = true;
		//String datePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";
		if (fecha != null && fecha != "") {
			ok = false;
			try {
				Date dateSimple = new SimpleDateFormat(formato).parse(fecha);
				Format formatter = new SimpleDateFormat(formato);
				if (fecha.equals(formatter.format(dateSimple))) {
					ok = true;
				}
			} catch (ParseException e) {
				return false;
			}
		}
		return ok;
	}

	/**
	 * Metodo para validar el fecha contra fecha actual.
	 *
	 * @param fecha Date de la fecha a validar.
	 * @return <b>false</b>, si la fecha es mayor a la fecha actual.
	 */
	public boolean fechaNoMayorAFechaActual(Date fecha, String nombre) {
		boolean ok = true;
		//String datePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";
		if (fecha != null) {
			ok = false;
			try {

				Calendar calendar = Calendar.getInstance();

				if (fecha.before(calendar.getTime())) {
					ok = true;
				} else {
					String mensaje = null;
					mensaje = "La '" + nombre + "' no puede ser posterior a la fecha actual.";
					this.getErrores().add(mensaje);
				}
			} catch (Exception e) {
				return false;
			}
		}
		return ok;
	}

	//Las fechas deben venir de menor a mayor
	//Ej: si una fecha es necesario que sea menor que otras 2, debe venir 1ero en el arreglo
	public void fechaEsMenorQue(UIComponent[] componentes, String[] nombresCampos) {
		boolean ok = true;
		//String datePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";

		int largo = componentes.length;

		Date[] fechas = new Date[largo];
		for (int i = 0; i < largo; i++) {
			fechas[i] = Conversor.getFechaCortaDeString(((UIInput) componentes[i]).getValue().toString());
		}

		//        Date fecha = Conversor.getFechaCortaDeString(((UIInput) componentes[0]).getValue().toString());
		//        Date fecha2 = Conversor.getFechaCortaDeString(((UIInput) componentes[1]).getValue().toString());

		for (int i = 0; i < largo - 1; i++) {
			for (int j = i + 1; j < largo; j++) {
				if (fechas[i] != null && fechas[j] != null) {
					try {
						if (!fechas[i].before(fechas[j])) {
							StringBuffer mensaje = new StringBuffer();
							mensaje.append("La '");
							mensaje.append(nombresCampos[i]);
							mensaje.append("' debe ser anterior a '");
							mensaje.append(nombresCampos[j]);
							mensaje.append("'.");
							//                    mensaje = "La 'Fecha Fin' debe ser posterior a 'Fecha Inicio'.";
							this.getErrores().add(mensaje);
							((UIInput) componentes[i]).setValid(false);
							((UIInput) componentes[j]).setValid(false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		//        if (fecha != null && fecha2 != null) {
		//            ok = false;
		//            try {
		//
		//                //Calendar calendar = Calendar.getInstance();
		//
		//                if (fecha.before(fecha2)) {
		//                    ok = true;
		//                } else {
		////                    String mensaje = null;
		//                    StringBuffer mensaje = new StringBuffer();
		//                    mensaje.append("La '");
		//                    mensaje.append(nombresCampos[0]);
		//                    mensaje.append("' debe ser posterior a ");
		//                    mensaje.append(nombresCampos[1]);
		//                    mensaje.append("'.");
		////                    mensaje = "La 'Fecha Fin' debe ser posterior a 'Fecha Inicio'.";
		//                    this.getErrores().add(mensaje);
		//                    ((UIInput) componentes[0]).setValid(false);
		//                    ((UIInput) componentes[1]).setValid(false);
		//                }
		//            } catch (Exception e) {
		//                //return false;
		//            }
		//        }
		// return ok;
	}

	//
	// METODOS PUBLICOS
	//
	public void noSonVacios(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			UIComponent locUIComponent = componentes[i];
			if (!validarValor(locUIComponent)) {
				UIInput locInput = (UIInput) locUIComponent;
				locInput.setValid(false);
				mensaje = "El campo '" + nombresCampos[i] + "' es requerido.";
				this.getErrores().add(mensaje);
			}
		}        
	}

	public boolean validarValor(UIComponent pUIIComponent){
		if (pUIIComponent instanceof TextField){
			return validarValor((TextField)pUIIComponent);
		} else if (pUIIComponent instanceof TextArea){
			return validarValor((TextArea) pUIIComponent);
		} else if (pUIIComponent instanceof DropDown){
			return validarValor((DropDown)pUIIComponent);
		} else if (pUIIComponent instanceof Upload){
			return validarValor((Upload)pUIIComponent);
		} else {
			//Tratamos de castear a UIInput
			return validarValor((UIInput) pUIIComponent);
		}
	}

	public boolean validarValor(Upload pUpload){
		return pUpload.getUploadedFile().getOriginalName() != null 
				&& !pUpload.getUploadedFile().getOriginalName().equals("");
	}

	public boolean validarValor(UIInput pUIInput){
		return pUIInput.getValue() != null && !pUIInput.getValue().equals("");
	}

	public boolean validarValor(DropDown pDropDown){
		return pDropDown.getSelected() != null && !pDropDown.getSelected().equals("");
	}

	public boolean validarValor(TextField pTextField){
		return pTextField.getValue() != null && !pTextField.getValue().equals("");
	}

	public boolean validarValor(TextArea pTextArea){
		return pTextArea.getValue() != null && !pTextArea.getValue().equals("");
	}

	public void cumplenLargo(UIComponent[] componentes, String[] nombresCampos,
			Integer[] minimos, Integer[] maximos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (minimos[i] != null && maximos[i] != null) {
				if (!longitudEntre(valor, minimos[i].intValue(), maximos[i].intValue())) {
					((UIInput) componentes[i]).setValid(false);
					mensaje = "La longitud del campo '" + nombresCampos[i]
							+ "' debe estar comprendida entre " + minimos[i] + " y "
							+ maximos[i] + ".";
					this.getErrores().add(mensaje);
				}
			} else {
				if (minimos[i] != null && maximos[i] == null) {
					if (!longitudMinima(valor, minimos[i].intValue())) {
						((UIInput) componentes[i]).setValid(false);
						mensaje = "La longitud del campo '" + nombresCampos[i]
								+ "' debe ser mayor o igual a " + minimos[i] + ".";
						this.getErrores().add(mensaje);
					}
				} else {
					if (minimos[i] == null && maximos[i] != null) {
						if (!longitudMaxima(valor, maximos[i].intValue())) {
							((UIInput) componentes[i]).setValid(false);
							mensaje = "La longitud del campo '" + nombresCampos[i]
									+ "' debe ser menor o igual a " + maximos[i] + ".";
							this.getErrores().add(mensaje);
						}
					}
				}
			}
		}
		return;
	}

	public void formatoFechaValido(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIOutput) componentes[i]).getValue();
			if (!fechaValida(valor, "dd/MM/yyyy")
					&& !fechaValida(valor, "dd-MM-yyyy")
					&& !fechaValida(valor, "dd.MM.yyyy")) {
				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' no contiene una fecha v\341lida.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}

	public void formatoFechaHoraValido(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (!fechaValida(valor, "dd/MM/yyyy-HH:mm") /*
			 * && !fechaValida(valor, "dd-MM-yyyy") &&
			 * !fechaValida(valor, "dd.MM.yyyy")
			 */) {
				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' no contiene una fecha y hora v\341lida.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}

	public void formatoCuitValido(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (!cuitValido(valor)) {
				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' no contiene un CUIT/CUIL v\341lido.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}
	// sonEnteros: valida que no contenga letras ni números negativos

	public void sonEnteros(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (!esEntero(valor)) {
				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' no contiene un n\372mero v\341lido.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}

	public void sonFlotantes(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (!esFlotante(valor)) {

				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' no contiene un n\372mero decimal v\341lido.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}

	public void sonIguales(UIComponent[] componentes1, UIComponent[] componentes2,
			String[] nombresCampos1, String[] nombresCampos2) {
		String mensaje = null;
		for (int i = 0; i < componentes1.length; i++) {
			String valor1 = (String) ((UIInput) componentes1[i]).getValue();
			String valor2 = (String) ((UIInput) componentes2[i]).getValue();
			if (!sonIguales(valor1, valor2)) {
				((UIInput) componentes1[i]).setValid(false);
				((UIInput) componentes2[i]).setValid(false);
				mensaje = "Los campos '" + nombresCampos1[i]
						+ "' y '" + nombresCampos2[i] + "' deben ser iguales.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}

	public void sonPositivos(UIComponent[] componentes, String[] nombresCampos) {
		String mensaje = null;
		for (int i = 0; i < componentes.length; i++) {
			String valor = (String) ((UIInput) componentes[i]).getValue();
			if (!esPositivo(valor)) {
				((UIInput) componentes[i]).setValid(false);
				mensaje = "El campo '" + nombresCampos[i]
						+ "' debe contener un valor positivo.";
				this.getErrores().add(mensaje);
			}
		}
		return;
	}
}