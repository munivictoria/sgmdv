
package com.trascender.framework.util;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import ar.trascender.util.ReflectionUtils;

import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;

/**
 * Algoritmos �tiles para todo el sistema
 * 
 * @author Ariel Trevisan, Juan Pablo Burioni, Mariano Lusari
 *
 */
public class Util {

	public static boolean isFechaBetween(Calendar pFecha, Calendar pFechaMenor, Calendar pFechaMayor) {
		if(pFecha.after(pFechaMenor) && pFecha.before(pFechaMayor)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isFechaBetweenNoTima(Date pFecha, Date pFechaMenor, Date pFechaMayor) {
		return compareDate(pFecha, pFechaMenor) > -1 && compareDate(pFecha, pFechaMayor) < 1;
	}
	
	/**
	 * Si la fecha es Sabado o Domingo, la lleva al dia lunes.
	 * @param pDate
	 * @return
	 */
	public static synchronized Date llevarFechaALunes(Date pDate) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(pDate);
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		if (diaSemana == Calendar.SATURDAY)
			calendario.add(Calendar.DAY_OF_MONTH, 2);
		if (diaSemana == Calendar.SUNDAY)
			calendario.add(Calendar.DAY_OF_MONTH, 1);
		return calendario.getTime();
	}

	/**
	 * 
	 * @param pFecha1
	 * @param pFecha2
	 * @return True si la Fecha 1 es mayor a la fecha 2.
	 */
	public static boolean isFechaAfterNoTima(Date pFecha1, Date pFecha2) {
		return compareDate(pFecha1, pFecha2) > 0;
	}

	public static boolean isFechaEqualsNoTima(Date pFecha1, Date pFecha2) {
		return compareDate(pFecha1, pFecha2) == 0;
	}

	public static int compareDate(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		if(c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
			return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		if(c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
			return c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		return c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Convierte una cadena a may�sculas todas las primeras letras de todas las palabras y min�sculas el resto
	 * 
	 * @param cadena
	 *            que se desea capitalizar
	 * @return cadena tipo t�tulo<br>
	 *         ej: esto es una cadena<br>
	 *         salida: Esto Es Una Cadena
	 * 
	 */
	public static String capitalize(String cadena) {
		if(cadena != null) {
			cadena = cadena.toLowerCase();
			char ch;
			char prevCh;
			int i;
			prevCh = '.';
			String salida = "";
			for(i = 0; i < cadena.length(); i++) {
				ch = cadena.charAt(i);
				if(Character.isLetter(ch) && !Character.isLetter(prevCh))
					salida += Character.toUpperCase(ch);
				else
					salida += ch;
				prevCh = ch;
			}
			return salida;
		}
		return null;
	}

	/**
	 * Convierte una cadena a may�sculas la primera letra (Explicito) de la cadena, y luego separa las palabras a partir de las demas letras en mayusculas
	 * 
	 * @param cadena
	 *            que se desea capitalizar y separar
	 * @return cadena tipo t�tulo<br>
	 *         ej: atributoDinamico<br>
	 *         salida: Atributo Dinamico
	 * 
	 */
	public static String capitalizeFirstAndSeparateWords(String cadena) {
		if(cadena != null) {
			String salida = (cadena.charAt(0) + "").toUpperCase();
			for(int i = 1; i < cadena.length(); i++) {
				char ch = cadena.charAt(i);
				salida += Character.isUpperCase(ch) ? " " + ch : ch;
			}
			return salida;
		}
		return null;
	}

	public static String capitalizeEnumName(String cadena) {
		return Util.capitalize(cadena).replaceAll("_", " ");
	}

	public static String getEnumNameFromString(String cadena) {
		return cadena.replaceAll(" ", "_").toUpperCase().trim();
	}

	// ------------------------------------------------------------ UTILES

	/**
	 * @param pTamanio
	 * @param pCadena
	 * @return
	 */
	public static String formatString(int pTamanio, String pCadena) {
		String retorno = "";
		int locTamanio = pCadena.length();
		int agregar = pTamanio - locTamanio;
		if(agregar > 0) {
			for(int i = 0; i < agregar; i++) {
				retorno += "0";
			}
		}
		return retorno + pCadena;
	}

	public static String getParteDecimalAsString(Float pValor) {
		int locParteEntera = pValor.intValue();
		pValor -= locParteEntera;
		pValor *= 1000;
		return String.valueOf(pValor.intValue());
	}

	public static String getParteDecimalAsString(Double pValor) {
		int locParteEntera = pValor.intValue();
		pValor -= locParteEntera;
		pValor *= 1000;
		return String.valueOf(pValor.intValue());
	}

	public static String getString(Date pDate, String pFormato) {
		String strFecha = "";
		try {
			strFecha = (new SimpleDateFormat(pFormato)).format(pDate);
		} catch(Exception ex) {
		}

		return strFecha;
	}

	public static String getString(Date pDate) {
		return getString(pDate, "dd/MM/yyyy");
	}

	public static Date getFechaActualFormatoSimple() {
		return new Date(Calendar.getInstance().get(Calendar.YEAR) - 1900, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
	}

	/**
	 * Calcula los meses de dierencia entre dos fechas
	 * 
	 * @param pFecha1
	 *            fecha de mayor antigüedad
	 * @param pFecha2
	 *            fecha de menor antigüedad
	 * @return
	 * @throws Exception
	 */
	public static int getMesesDiferencia(Date pFecha1, Date pFecha2) throws Exception {
		Calendar locCalendar1 = Calendar.getInstance();
		Calendar locCalendar2 = Calendar.getInstance();
		locCalendar1.setTime(pFecha1);
		locCalendar2.setTime(pFecha2);
		return Util.getMesesDiferencia(locCalendar1, locCalendar2);
	}

	/**
	 * Calcula los meses de diferencia entre dos fechas
	 * 
	 * @param pFecha1
	 *            fecha de mayor antigüedad
	 * @param pFecha2
	 *            fecha de menor antigüedad
	 * @return
	 * @throws Exception
	 */
	public static int getMesesDiferencia(Calendar pFecha1, Calendar pFecha2) throws Exception {
		if(pFecha2.before(pFecha1)) {
			throw new TrascenderFrameworkException(500);
		}
		int difAnios = pFecha2.get(Calendar.YEAR) - pFecha1.get(Calendar.YEAR);
		int difMeses = pFecha2.get(Calendar.MONTH) - pFecha1.get(Calendar.MONTH);
		int difDias = pFecha2.get(Calendar.DAY_OF_MONTH) - pFecha1.get(Calendar.DAY_OF_MONTH);

		if(difDias < 0) {
			difMeses--;
		}

		if(difAnios == 0) {
			return difMeses;
		}
		difMeses += difAnios * 12;
		return difMeses;
	}

	/**
	 * 
	 * @param pFecha1
	 *            fecha inicial
	 * @param pFecha2
	 *            fecha final
	 * @return
	 * @throws Exception
	 */
	public static int getDiasDiferencia(Date pFecha1, Date pFecha2) throws TrascenderFrameworkException {
		if(pFecha2.before(pFecha1)) {
			throw new TrascenderFrameworkException(500);
		}
		return new Long(Math.abs(pFecha2.getTime() - pFecha1.getTime()) / 86400000L).intValue();
	}

	/**
	 * Verifica que una cadena no se encuentre vacía ni sea nula
	 * 
	 * @param pNombre
	 * @return
	 */
	public static boolean verificarCadenaLlena(String pNombre) {
		if(pNombre == null) {
			return false;
		}
		if(pNombre.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static synchronized String getStringDeLista(Collection coleccion, String separador) {
		String resultado = new String();
		for(Iterator iterator = coleccion.iterator(); iterator.hasNext();) {
			Object cadaLinea = iterator.next();
			resultado += cadaLinea.toString();
			if(iterator.hasNext())
				resultado += separador;
		}
		return resultado;
	}

	public static String formatNumero(Double pNumero) {

		NumberFormat locFormato = NumberFormat.getNumberInstance();
		locFormato.setMaximumFractionDigits(2);
		locFormato.setMinimumFractionDigits(2);
		locFormato.setCurrency(Currency.getInstance(Locale.US));
		return locFormato.format(pNumero);
	}

	public static Date getFechaYHora(Date fecha) {
		Date laFecha = null;
		if(fecha != null) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dateSimple = dateFormat.format(fecha.getTime());
				Date dateS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateSimple);
				laFecha = dateS;
			} catch(Exception e) {
				laFecha = null;
			}
		}
		return laFecha;
	}

	public static String getStringFechaYHora(Date fecha) {
		if(fecha != null) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				return dateFormat.format(fecha.getTime());
			} catch(Exception e) {
			}
		}
		return "";
	}

	public static Object getFormatIfNull(Object cadena) {
		if(cadena != null) {
			return cadena;
		} else {
			return "---";
		}
	}

	public static Throwable getRootCause(Throwable pThrowable) {
		if(pThrowable.getCause() == null) {
			return pThrowable;
		} else {
			return getRootCause(pThrowable.getCause());
		}
	}

	public static String reemplazarAcentos(String pCadena) {
		if(pCadena == null) {
			return null;
		}
		String[] aBuscar = new String[] {"Á", "É", "Í", "Ó", "Ú", "á", "é", "í", "ó", "ú"};
		String[] aReemplazar = new String[] {"A", "E", "I", "O", "U", "a", "e", "i", "o", "u"};
		String locCadenaResultado = new String(pCadena);
		for(int i = 0; i < aBuscar.length; i++) {
			locCadenaResultado = locCadenaResultado.replace(aBuscar[i], aReemplazar[i]);
		}
		return locCadenaResultado;
	}

	public static synchronized double redondear(double valor, int digitos) {
		BigDecimal bd = new BigDecimal(valor);
		bd = bd.setScale(digitos, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static synchronized float redondear(float valor, int digitos) {
		BigDecimal bd = new BigDecimal(valor);
		bd = bd.setScale(digitos, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	/**
	 * Devuelve true si el String "buscar" es igual a alguno de los valores que se definen en el arreglo "valores"
	 * 
	 * @param buscar
	 *            String
	 * @param valores
	 *            String[]
	 * @return true o false
	 */
	public static synchronized boolean estaEn(String buscar, String... valores) {
		for(String cadaValor : valores) {
			if(buscar.equalsIgnoreCase(cadaValor)) {
				return true;
			}
		}
		return false;
	}

	public static synchronized <T> List<T> castearLista(List pLista) {
		List<T> listaCasteada = new ArrayList<T>();
		for(Object cadaObjeto : pLista) {
			listaCasteada.add((T) cadaObjeto);
		}

		return listaCasteada;
	}
	
	public static <T, E> List<T> getListaPropiedad(Collection<E> pLista, String propiedad) {
		if (pLista == null) return null;
		List<T> listaRetorno = new ArrayList<T>();
		try {
			if (pLista.isEmpty()) return listaRetorno;
			Method geter = null;
			for (E objeto : pLista) {
				if (objeto == null) continue;
				if (geter == null) {
					geter = ReflectionUtils.getGeter(objeto.getClass(), propiedad);
				}
				listaRetorno.add((T) geter.invoke(objeto));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public static synchronized File crearArchivoTemporal(String pNombre, String pExtension) {
		return crearArchivo(null, pNombre, pExtension);
	}

	public static synchronized File crearArchivo(String pDirectorio, String pNombre, String pExtension) {
		if(pDirectorio == null) {
			pDirectorio = System.getProperty("java.io.tmpdir");
		}
		String nombreArchivo = (pNombre != null ? pNombre : "") + (pExtension != null ? "." + pExtension : "");

		return new File(pDirectorio, nombreArchivo);
	}

	/*
	 * Devuelve la cadena de reemplazo para el toString(), de los recursos en la configuracion de recurso.
	 */
	public static synchronized String returnToString(Object pClase, Long pSerialVersion) {
		for(Long cadaKey : SecurityMgr.getInstance().getMapaConfiguracionesRecurso().keySet()) {
			ConfiguracionRecurso config = SecurityMgr.getInstance().getMapaConfiguracionesRecurso().get(cadaKey);

			if(config.getIdRecurso().equals(pSerialVersion) && (config.getToString() != null && config.getToString().length() > 0)) {
				String resultado = "";
				String[] arrayToString = config.getToString().split(" ");

				for(int i = 0; i < arrayToString.length; i++) {
					String str = arrayToString[i];
					int first = str.indexOf("$");
					int last = str.lastIndexOf("$");

					// System.out.println("IF($Nombre$ :: Mi nombre es $Nombre$ :: No tengo nombre)");
					// System.out.println("Mi nombre es $Nombre$");
					// System.out.println("No tengo nombre"); '$'$Sueldo$ 1 10

					if((first != -1 && last != -1) && first != last) {

						if(first != 0 || last + 1 != str.length()) {
							if(str.charAt(first - 1) == '\'' && str.charAt(first + 1) == '\'' && str.charAt(first + 2) == '$') {
								str = str.replace("'$'", "$");
								last -= 2;
							}
						}

						String aux = str.substring(first + 1, last);
						int count = aux.replaceAll("[^$]", "").length();
						if(count > 0) {
							String strVarios = "";
							String relleno = "";
							int k;
							if(count > 2) {
								count--;
							}
							for(k = 0; k < count; k++) {
								try {
									int existe = aux.indexOf("$");
									String valor = (existe != -1) ? aux.substring(0, aux.indexOf("$")) : aux;
									Method metodo = ReflectionUtils.getGeter(pClase.getClass(), valor);
									Object strAux = metodo.invoke(pClase);

									if(existe != -1) {
										aux = aux.substring(aux.indexOf("$") + 1);
										relleno = aux.substring(0, aux.indexOf("$"));
									} else {
										relleno = "";
									}

									strVarios = strVarios + (strAux != null ? strAux.toString() : "''") + relleno;
									aux = aux.substring(relleno.length() + 1);
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
							str = str.substring(0, str.indexOf("$")) + strVarios + str.substring(last + 1);
						} else {
							try {
								Method metodo = ReflectionUtils.getGeter(pClase.getClass(), aux);
								Object valor = metodo.invoke(pClase);
								
								String relleno = "";
								int v = str.indexOf("$");
								if(str.charAt(v) == '$' && str.charAt(v+1) == '$') {
									relleno = str.substring(0, first);
								}
								str = relleno + str.substring(0, str.indexOf("$")) + (valor != null ? valor.toString() : "''") + str.substring(last + 1);
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
					} else {
						int signo = str.indexOf("'$'");
						if(signo != -1) {
							str = str.replace("'$'", "$");
						}
					}
					resultado = resultado + " " + str;
				}

				return resultado.trim();
			}
		}

		return null;
	}
	
	public static long factorial(long n) {
		if(n <= 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
}