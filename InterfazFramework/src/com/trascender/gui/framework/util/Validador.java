package com.trascender.gui.framework.util;

import java.awt.Color;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.exception.GuiException;

public class Validador {

	private static final String PUNTO_DECIMAL_STR = ".";
	private static final char PUNTO_DECIMAL_CHR = PUNTO_DECIMAL_STR.charAt(0);

	private static final String SIGNO_NEGATIVO_STR = "-";
	private static final char SIGNO_NEGATIVO_CHR = SIGNO_NEGATIVO_STR.charAt(0);

	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/**
	 * Compara los valores String.
	 * 
	 * @param val1
	 * @param val2
	 * @return <code>true</code> si son iguales.
	 */
	public static boolean compare(String val1, String val2) {
		boolean ok = true;
		if (val1 != null && !val1.equals("") && val2 != null
				&& !val2.equals("")) {
			ok = false;
			if (val1.equals(val2))
				ok = true;
		}
		return ok;
	}

	/**
	 * Valida el formato de una ip.
	 * 
	 * @param ip
	 *            String de la ip a validar.
	 * @return <b>false</b>, si el formato no es válido.
	 */
	public static boolean isValidIp(String ip) {
		// TODO:
		boolean ok = true;
		if (ip != null && ip != "") {
			ok = false;
			try {
				StringTokenizer st = new StringTokenizer(ip, ".");
				while (st.hasMoreElements()) {
					String elem = st.nextElement().toString();
					int dir = Integer.valueOf((String) st.nextElement());
					if ((Validador.isValidInteger(elem))
							&& (Validador.validarSiPertenece(dir, 0, 255)))
						ok = true;
				}
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null,
				// "Error: No ha ingresado correctamente la dirección Ip");
				ok = false;
				return ok;
			}
		}
		return ok;
	}

	/**
	 * Método para validar el mínimo y el máximo de un entero.
	 * 
	 * @param entero
	 *            a validar.
	 * @param min
	 *            Mínimo del entero que debe poseer.
	 * @param max
	 *            Máximo del entero que debe poseer.
	 * @return <b>false</b>, si el entero es menor a min o si el entero es mayor
	 *         a max.
	 */
	public static boolean validarSiPertenece(int nro, int min, int max) {
		boolean ok = false;
		if (nro >= min) {
			if (nro <= max)
				ok = true;
		}
		return ok;
	}

	/**
	 * Valida que el numero sea Cero.
	 * 
	 * @param numero
	 * @return <b>true</b>, si el numero es cero.
	 */

	public static boolean isCero(Float numero) {
		boolean ok = false;
		if (numero > 0) {
			ok = true;
		} else {
			ok = false;
		}
		return ok;
	}

	/**
	 * Valida un CUIT/CUIL.
	 * 
	 * @param cuim
	 *            CUIT/CUIL a validar.
	 * @return <code>true</code> si es un CUIT/CUIL válido.
	 */
	public static boolean isValidCUIM(String cuim) {
		boolean ok = true;
		if (cuim != null && !cuim.equals("")) {
			ok = false;
			String regex = "\\d\\d[-]\\d\\d\\d\\d\\d\\d\\d\\d[-]\\d";
			if (Pattern.matches(regex, cuim)) {
				if (ValidadorCuit.validar(cuim))
					ok = true;
			}
		}
		return ok;
	}

	/**
	 * Valida si el String contiene un valor convertible a Integer.
	 * 
	 * @param numero
	 * @return <code>true</code> si es un String convertible a Integer (no a
	 *         Float).
	 */
	public static boolean isValidInteger(String numero) {
		boolean ok = true;
		if (numero != null && !numero.equals("")) {
			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
			if (posSigno > 0)
				return false;

			for (int i = (posSigno + 1); i < numero.length(); i++) {
				if (!Character.isDigit(numero.charAt(i)))
					return false;
			}
		}
		return ok;
	}

	/**
	 * Valida si el String contiene un valor convertible a Float.
	 * 
	 * @param numero
	 * @return <code>true</code> si es un String convertible a Float.
	 */
	public static boolean isValidFloat(String numero) {
		boolean ok = true;
		Double tipoNumero = null;
		if (numero != null && !numero.equals("")) {
			try {
				tipoNumero = Double.parseDouble(numero);
				ok = true;
			} catch (Exception e) {
				ok = false;
			}
//			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
//			if (posSigno > 0)
//				return false;
//
//			int first = numero.indexOf(PUNTO_DECIMAL_STR);
//			int last = numero.lastIndexOf(PUNTO_DECIMAL_STR);
//			if (first != last)
//				return false;
//
//			for (int i = (posSigno + 1); i < numero.length(); i++) {
//				if (!Character.isDigit(numero.charAt(i))
//						&& numero.charAt(i) != PUNTO_DECIMAL_CHR
//						&& numero.charAt(i) != SIGNO_NEGATIVO_CHR)
//					return false;
//			}
		}
		return ok;
	}

	/**
	 * Valida si el String no comienza con un signo negativo.
	 * 
	 * @param numero
	 * @return <code>true</code> si es el String no comienza con un signo
	 *         negativo.
	 */
	public static boolean isPositiveNumber(String numero) {
		boolean ok = true;
		if (numero != null && !numero.equals("")) {
			int posSigno = numero.indexOf(SIGNO_NEGATIVO_STR);
			if (posSigno >= 0)
				return false;

		}
		return ok;
	}

	/**
	 * Valida el largo de una cadena.
	 * 
	 * @param cadena
	 *            String a validar.
	 * @param min
	 *            Mínimo de caracteres que debe poseer la cadena.
	 * @param max
	 *            Máximo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena no se encuentra entre min
	 *         y max.
	 */
	public static boolean lengthBetween(String cadena, int min, int max) {
		boolean ok = true;
		if (cadena != null && !cadena.equals("")) {
			ok = false;
			if (cadena.length() >= min && cadena.length() <= max)
				ok = true;
		}
		return ok;
	}

	/**
	 * Metodo para validar el largo minimo de una cadena.
	 * 
	 * @param cadena
	 *            String a validar.
	 * @param min
	 *            Minimo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena es menor a min.
	 */
	public static boolean minLength(String cadena, int min) {
		boolean ok = true;
		if (cadena != null && !cadena.equals("")) {
			ok = false;
			if (cadena.length() >= min)
				ok = true;
		}
		return ok;
	}

	/**
	 * Metodo para validar el largo maximo de una cadena.
	 * 
	 * @param cadena
	 *            String a validar.
	 * @param max
	 *            Maximo de caracteres que debe poseer la cadena.
	 * @return <b>false</b>, si el largo de la cadena es mayor a max.
	 */
	public static boolean maxLength(String cadena, int max) {
		boolean ok = true;
		if (cadena != null && !cadena.equals("")) {
			ok = false;
			if (cadena.length() < max)
				ok = true;
		}
		return ok;
	}

	/**
	 * Valida una fecha con un DateFormat.
	 * 
	 * @param fecha
	 *            String de la fecha a validar.
	 * @param formato
	 *            DateFormat que deberia tener la fecha.
	 * @return <b>false</b>, si el formato no es valido.
	 */
	public static boolean isValidDate(String fecha, String formato) {
		boolean ok = true;
		if (fecha != null && !fecha.equals("")) {
			ok = false;
			try {
				Date dateSimple = new SimpleDateFormat(formato).parse(fecha);
				Format formatter = new SimpleDateFormat(formato);
				if (fecha.equals(formatter.format(dateSimple)))
					ok = true;
			} catch (ParseException e) {
				return false;
			}
		}
		return ok;
	}

	/**
	 * Valida una fecha con un DateFormat con el formato <code>dd/MM/yyyy</code>
	 * .
	 * 
	 * @param fecha
	 *            String de la fecha a validar.
	 * @return <b>false</b>, si el formato no es valido.
	 */
	public static boolean isValidDate(String fecha) {
		return Validador.isValidDate(fecha, Validador.FORMATO_FECHA);
	}

	private static boolean validacionCollections(Collection att,
			Collection<JLabel> lbl) {
		if (att == null || lbl == null) {
			return false;
		}
		if (att.size() != lbl.size()) {
			return false;
		}
		return true;
	}

	private static boolean validacionCollections(Collection att,
			Collection<JLabel> lbl, Collection otro) {
		if (att == null || lbl == null || otro == null) {
			return false;
		}
		if ((att.size() != lbl.size()) || (att.size() != otro.size())) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que una fecha no sea mas alta que la fecha actual.
	 * 
	 * @param fecha
	 *            String de la fecha a validar.
	 * @param formato
	 *            DateFormat que deberia tener la fecha.
	 * @return <b>false</b>, si el formato no es valido.
	 */
	public static boolean isValidDate(String pFecha, Date pFechaActual) {
		boolean ok = false;
		if (pFecha != null && !pFecha.equals("")) {
			Date locFecha = Conversor.getDate(pFecha);
			if (locFecha.before(pFechaActual)) {
				ok = true;
				return ok;
			}
		}
		return ok;

	}

	/**
	 * valida que la fecha ingresada no sea mayor a la actual.
	 * 
	 * @param fecha
	 *            Objetos que no pueden ser <code>null</code>.
	 * @param lblFecha
	 *            Etiquetas del Objetos que no pueden ser <code>null</code>.
	 * @return Mensajes de error.
	 * @throws Exception
	 */
	public static List<String> validarFechaNoMayorALaActual(String pFecha,
			JLabel lblFecha) {
		List<String> error = new ArrayList<String>();

		Date locFecha = Conversor.getDate(pFecha);
		if (locFecha != null) {
			if (locFecha.after(Calendar.getInstance().getTime())) {
				String textEtiqueta = lblFecha.getText().replaceAll(
						TLabel.AGREGADO, "");
				lblFecha.setForeground(Color.BLUE);
				error.add("El campo " + textEtiqueta
						+ " no puede ser posterior a la fecha actual.");
			}
		}
		return error;
	}

	/**
	 * valida si el arbol está vacio.
	 * 
	 * @param pListaNodos
	 *            Objetos que no pueden ser <code>null</code>.
	 * @return Mensajes de error.
	 */
	@SuppressWarnings("unchecked")
	public static List<String> validarArbol(java.util.Set pListaNodos) {
		List<String> error = new ArrayList<String>();
		
		if (pListaNodos.isEmpty()){
				error.add("El arbol está vacío");
		}
		return error;
	}

	/**
	 * Valida si los atributos de una lista son nulos.
	 * 
	 * @param attNulos
	 *            Objetos que no pueden ser <code>null</code>.
	 * @param lblNulos
	 *            Etiquetas de los Objetos que no pueden ser <code>null</code>.
	 * @return Lista de mensajes de error.
	 * @throws Exception
	 */
	public static List<String> validarNulos(List<Object> attNulos,
			List<JLabel> lblNulos) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attNulos, lblNulos)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attNulos.size(); i++) {
			lblNulos.get(i).setForeground(Color.BLACK);
			if (attNulos.get(i) == null
					|| attNulos.get(i).toString().trim().equals("")) {
				String textEtiqueta = lblNulos.get(i).getText().replaceAll(
						TLabel.AGREGADO, "");
				lblNulos.get(i).setForeground(Color.RED);
				listaErrores.add("El campo '" + textEtiqueta
						+ "' no puede quedar vacío.");
			}
		}
		return listaErrores;
	}

	/**
	 * Valida si los atributos son números enteros
	 * 
	 * @param attEnteros
	 * @param lblEnteros
	 * @return
	 */
	public static List<String> validarEnteros(List<Object> attEnteros,
			List<JLabel> lblEnteros) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attEnteros, lblEnteros)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attEnteros.size(); i++) {
			if (attEnteros.get(i) != null
					&& !attEnteros.get(i).toString().trim().equals("")) {
				lblEnteros.get(i).setForeground(Color.BLACK);
				if (!Validador.isValidInteger(attEnteros.get(i).toString())) {
					String textEtiqueta = lblEnteros.get(i).getText()
							.replaceAll(TLabel.AGREGADO, "");
					lblEnteros.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener un número entero.");
				}
			}
		}

		return listaErrores;
	}

	/**
	 *Valida si los atributos son números decimales
	 * 
	 * @param attDecimales
	 * @param lblDecimales
	 * @return
	 */
	public static List<String> validarDecimales(List<Object> attDecimales, List<JLabel> lblDecimales) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attDecimales, lblDecimales)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attDecimales.size(); i++) {
			if (attDecimales.get(i) != null	&& !attDecimales.get(i).toString().trim().equals("")) {
				lblDecimales.get(i).setForeground(Color.BLACK);
				if (!Validador.isValidFloat(attDecimales.get(i).toString())) {
					String textEtiqueta = lblDecimales.get(i).getText().replaceAll(TLabel.AGREGADO, "");
					lblDecimales.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta + "' debe contener un número decimal.");
				}
			}
		}
		return listaErrores;
	}

	/**
	 * Valida importes para el debe y el haber
	 * 
	 * @param attDebe
	 * @param lblDebe
	 * @param attHaber
	 * @param lblHaber
	 * @return
	 */
	public static List<String> validarImportesDebeHaber(List<Object> attDebe,
			List<JLabel> lblDebe, List<Object> attHaber, List<JLabel> lblHaber)
			throws GuiException {
		
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attDebe, lblDebe) && !validacionCollections(attHaber, lblHaber)) {
			throw new GuiException(7);
		}
		
		if (attDebe.size() == attHaber.size()) {
			for (int i = 0; i < attDebe.size(); i++) {
				lblDebe.get(i).setForeground(Color.BLACK);
				if (attDebe.get(i) != null && !attDebe.get(i).toString().trim().equals("")) {
					lblDebe.get(i).setForeground(Color.BLUE);
				}
			}
			for (int j = 0; j < attHaber.size(); j++) {
				lblHaber.get(j).setForeground(Color.BLACK);
				if (attHaber.get(j) != null && !attHaber.get(j).toString().trim().equals("")) {
					lblHaber.get(j).setForeground(Color.BLUE);
				}
			}

			listaErrores.add("Debe ingresar un importe en el debe o en el haber.");
		}

		return listaErrores;
	}

	/**
	 * 
	 * @param attFechas
	 * @param lblFechas
	 * @return
	 */
	public static List<String> validarFechas(List<String> attFechas,
			List<JLabel> lblFechas) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attFechas, lblFechas)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attFechas.size(); i++) {

			if (attFechas.get(i) != null && !attFechas.get(i).trim().equals("")) {
				lblFechas.get(i).setForeground(Color.BLACK);
				if (!Validador.isValidDate(attFechas.get(i))) {
					String textEtiqueta = lblFechas.get(i).getText()
							.replaceAll(TLabel.AGREGADO, "");
					lblFechas.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener una fecha válida.");
				}
			}
		}

		return listaErrores;
	}

	/**
	 * Valida que la fecha sea valida.
	 * 
	 * @param attFechas
	 * @param lblFechas
	 * @return
	 */
	public static List<String> validarDia(List<String> attFechas,List<JLabel> lblFechas) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attFechas, lblFechas)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attFechas.size(); i++) {

			if (attFechas.get(i) != null && !attFechas.get(i).trim().equals("")) {
				lblFechas.get(i).setForeground(Color.BLACK);
				if (!Validador.isValidDate(attFechas.get(i))) {
					String textEtiqueta = lblFechas.get(i).getText().replaceAll(TLabel.AGREGADO, "");
					lblFechas.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta + "' debe contener un día válido.");
				}
			}
		}

		return listaErrores;
	}
	
	/**
	 * Valida que el número ingresado sea un mes valido.
	 * 
	 * @param attFechas
	 * @param lblFechas
	 * @return
	 */
	public static List<String> validarMes(List<String> attMes,List<JLabel> lblMes) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attMes, lblMes)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attMes.size(); i++) {

			if (attMes.get(i) != null && !attMes.get(i).trim().equals("")) {
				lblMes.get(i).setForeground(Color.BLACK);
				if (!Validador.validarSiPertenece(Conversor.getInteger(attMes.get(i)), 1, 12)) {
					String textEtiqueta = lblMes.get(i).getText().replaceAll(TLabel.AGREGADO, "");
					lblMes.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta + "' debe contener un número de mes válido.");
				}
			}
		}

		return listaErrores;
	}
	
	/**
	 * Valida que los atributos tengan una longitud exacta
	 * 
	 * @param attDatos
	 *            lista de objetos tipo String.
	 * @param lblDatos
	 *            lista de objetos tipo JLabel para cambiar el aspecto si
	 *            resultara mal la validacion.
	 * @param longExacta
	 *            lista de longitudes que deben tener los atributos.
	 * @return
	 * @throws GuiException
	 */
	public static List<String> validarLongitudExacta(List<Object> attDatos,
			List<JLabel> lblDatos, List<Integer> longExacta)
			throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attDatos, lblDatos, longExacta)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < lblDatos.size(); i++) {
			if (attDatos.get(i) != null
					&& !attDatos.get(i).toString().trim().equals("")) {
				lblDatos.get(i).setForeground(Color.BLACK);
				if (attDatos.get(i).toString().length() != longExacta.get(i)) {
					String textEtiqueta = lblDatos.get(i).getText().replaceAll(
							TLabel.AGREGADO, "");
					lblDatos.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener una longitud de "
							+ longExacta.get(i) + " caracter(es).");
				}
			}
		}
		return listaErrores;
	}

	/**
	 *  Valida que los atributos tengan una longitud Minima
	 * 
	 * @param attDatos
	 * @param lblDatos
	 * @param longMin
	 * @return
	 * @throws GuiException
	 */
	public static List<String> validarLongitudMinima(List<Object> attDatos,
			List<JLabel> lblDatos, List<Integer> longMin) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attDatos, lblDatos, longMin)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attDatos.size(); i++) {
			if (attDatos.get(i) != null
					&& !attDatos.get(i).toString().trim().equals("")) {
				lblDatos.get(i).setForeground(Color.BLACK);
				if (attDatos.get(i).toString().length() < longMin.get(i)) {
					String textEtiqueta = lblDatos.get(i).getText().replaceAll(
							TLabel.AGREGADO, "");
					lblDatos.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener una longitud mínima de "
							+ longMin.get(i) + " caracteres.");
				}
			}
		}
		return listaErrores;
	}

	/**
	 * 
	 * @param attMin
	 * @param lblMin
	 * @param cantMin
	 * @return
	 * @throws GuiException
	 */
	public static List<String> validarCantidadMinima(List<Integer> attMin,
			List<JLabel> lblMin, List<Integer> cantMin) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attMin, lblMin, cantMin)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attMin.size(); i++) {
			if (attMin.get(i) != null
					&& !attMin.get(i).toString().trim().equals("")) {
				lblMin.get(i).setForeground(Color.BLACK);
				if (attMin.get(i).intValue() < cantMin.get(i).intValue()) {
					String textEtiqueta = lblMin.get(i).getText().replaceAll(
							TLabel.AGREGADO, "");
					lblMin.get(i).setForeground(Color.BLUE);
					listaErrores.add("'" + textEtiqueta
							+ "' debe contener al menos " + cantMin.get(i)
							+ " elemento(s).");
				}
			}
		}
		return listaErrores;
	}

	/**
	 * 
	 * @param attPositivos
	 * @param lblPositivos
	 * @return
	 * @throws GuiException
	 */
	public static List<String> validarPositivos(List<String> attPositivos,
			List<JLabel> lblPositivos) throws GuiException {
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attPositivos, lblPositivos)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attPositivos.size(); i++) {
			if (attPositivos.get(i) != null
					&& !attPositivos.get(i).trim().equals("")) {
				lblPositivos.get(i).setForeground(Color.BLACK);
				if (!Validador.isPositiveNumber(attPositivos.get(i))) {
					String textEtiqueta = lblPositivos.get(i).getText()
							.replaceAll(TLabel.AGREGADO, "");
					lblPositivos.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener un valor positivo.");
				}
			}
		}

		return listaErrores;
	}

	/**
	 * 
	 * @param attIp
	 * @param lblIp
	 * @return
	 */
	public static List<String> validarIp(List<Object> attIp, List<JLabel> lblIp)
			throws GuiException {
		// TODO:
		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attIp, lblIp)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attIp.size(); i++) {
			if (attIp.get(i) != null
					&& !attIp.get(i).toString().trim().equals("")) {
				lblIp.get(i).setForeground(Color.BLACK);
				if (!Validador.isValidIp(attIp.get(i).toString())) {
					String textEtiqueta = lblIp.get(i).getText().replaceAll(
							TLabel.AGREGADO, "");
					lblIp.get(i).setForeground(Color.BLUE);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' debe contener un número de IP correcto.");
				}
			}
		}
		return listaErrores;
	}
	
	/**
	 * Valida que el parámetro no sea cero
	 * 
	 * @param attIp
	 * @param lblIp
	 * @return
	 */
	public static List<String> validarSiEsCero(List<Float> attNumero,
			List<JLabel> lblNumero) throws GuiException {

		List<String> listaErrores = new ArrayList<String>();

		if (!validacionCollections(attNumero, lblNumero)) {
			throw new GuiException(7);
		}

		for (int i = 0; i < attNumero.size(); i++) {
			if (attNumero.get(i) != null) {
				lblNumero.get(i).setForeground(Color.BLACK);
				if (!Validador.isCero(attNumero.get(i))) {
					String textEtiqueta = lblNumero.get(i).getText()
							.replaceAll(TLabel.AGREGADO, "");
					lblNumero.get(i).setForeground(Color.RED);
					listaErrores.add("El campo '" + textEtiqueta
							+ "' no debe ser cero.");
				}
			}
		}
		return listaErrores;
	}
}

final class ValidadorCuit {
	// ariel - contador para cortar las llamadas a calcular().
	private static int calls;

	private static int dniStc;
	private static int xyStc;
	private static int digitoStc;

	/**
	 * Metodo estatico para generar un CUIT/CUIL.
	 * 
	 * @param dniInt
	 *            DNI como int
	 * 
	 * @param xyChar
	 *            Sexo de la persona como char. Masculino: m - Femenino: f -
	 *            Para Personas Juridicas: cualquier otro caracter
	 * 
	 * @return El CUIT/CUIL como String
	 **/

	public static String generar(int dniInt, char xyChar)

	{
		if (xyChar == 'F' || xyChar == 'f')
			xyStc = 27;
		else if (xyChar == 'M' || xyChar == 'm')
			xyStc = 20;
		else
			xyStc = 30;

		dniStc = dniInt;

		calcular();
		return formatear();
	}

	/**
	 * Metodo estatico para generar un CUIT/CUIL.
	 * 
	 * @param dniInt
	 *            DNI como int
	 * @param xyInt
	 *            El prefijo del CUIT/CUIL como int
	 * 
	 * 
	 * @return El CUIT/CUIL como String
	 **/

	public static String generar(int dniInt, int xyInt) {
		xyStc = xyInt;
		dniStc = dniInt;
		calcular();
		return formatear();

	}

	/**
	 * Metodo estatico para validar un numero de CUIT/CUIL.
	 * 
	 * 
	 * @param cuit
	 *            N\186 de CUIT/CUIL como String
	 * 
	 * @return Boolean: true si el CUIT/CUIL es correcto, false en caso
	 *         contrario
	 * 
	 **/

	public static boolean validar(String cuit) {
		String xyStr, dniStr, digitoStr;
		int digitoTmp;
		int n = cuit.lastIndexOf("-");
		xyStr = cuit.substring(0, 2);

		dniStr = cuit.substring(cuit.indexOf("-") + 1, n);
		digitoStr = cuit.substring(n + 1, n + 2);
		if (xyStr.length() != 2 || dniStr.length() > 8
				|| digitoStr.length() != 1)
			return false;

		try {
			xyStc = Integer.parseInt(xyStr);
			dniStc = Integer.parseInt(dniStr);
			digitoTmp = Integer.parseInt(digitoStr);
		} catch (NumberFormatException e) {

			return false;
		}

		if (xyStc != 20 && xyStc != 23 && xyStc != 24 && xyStc != 27
				&& xyStc != 30 && xyStc != 33 && xyStc != 34)
			return false;

		calcular();

		if (digitoStc == digitoTmp && xyStc == Integer.parseInt(xyStr))
			return true;

		return false;

	}

	/**
	 * Metodo estatico que retorna el digito verificador de un CUIT/CUIL.
	 * 
	 * 
	 * @param xyInt
	 *            El prefijo como int
	 * @param dniInt
	 *            El DNI como int
	 * 
	 * @return El digito como int. Si se modifico el prefijo (por 23 o 33)
	 *         retorna 23x o 33x donde x es el digito
	 **/

	public static int digito(int xyInt, int dniInt) {
		xyStc = xyInt;
		dniStc = dniInt;
		calcular();
		if (xyInt == xyStc)
			return digitoStc;
		else
			return (xyStc * 10 + digitoStc);

	}

	/**
	 * Metodo privado q da formato al CUIT como String
	 **/

	private static String formatear() {
		return String.valueOf(xyStc) + "-" + completar(String.valueOf(dniStc))
				+ "-" + String.valueOf(digitoStc);

	}

	/**
	 * Metodo privado q completa con ceros el DNI para q quede con 8 digitos
	 **/

	private static String completar(String dniStr)

	{
		int n = dniStr.length();

		while (n < 8) {
			dniStr = "0" + dniStr;
			n = dniStr.length();
		}

		return dniStr;
	}

	/**
	 * 
	 * Metodo privado q calcula el CUIT
	 **/

	private static void calcular() {
		long tmp1, tmp2;
		long acum = 0;
		int n = 2;
		tmp1 = xyStc * 100000000L + dniStc;

		for (int i = 0; i < 10; i++) {
			tmp2 = tmp1 / 10;
			acum += (tmp1 - tmp2 * 10L) * n;
			tmp1 = tmp2;
			if (n < 7)
				n++;
			else
				n = 2;

		}

		n = (int) (11 - acum % 11);

		if (n == 10) {
			if (xyStc == 20 || xyStc == 27 || xyStc == 24)
				xyStc = 23;
			else
				xyStc = 33;

			/*
			 * No es necesario hacer la llamada recursiva a calcular(), se puede
			 * poner el digito en 9 si el prefijo original era 23 o 33 o poner
			 * el digito en 4 si el prefijo era 27
			 */

			// ariel - if para cortar la llamada infinita.
			calls++;
			if (calls < 50)
				calcular();

		} else {
			if (n == 11)
				digitoStc = 0;

			else
				digitoStc = n;
		}
	}

	/**
	 * Metodo toString().
	 * 
	 * @return Info acerca de la clase
	 **/
	public String toString() {
		return "El principal objetivo de esta clase es implementar metodos"
				+ "para generar y validar un numero de CUIT/CUIL., "
				+ "validar fechas, números, cadenas de caracteres, entre otros\n ";

	}

}
