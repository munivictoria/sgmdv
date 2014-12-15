package com.trascender.gui.framework.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class Conversor {
	
	private static final String FORMATO_FECHA = "dd/MM/yyyy"; 
	private static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	
    /**
     * 
     * @param pParametro Un String cualquiera.
     * @return <code>null</code> si el parámetro es un String vacío.
     */
	public static String getNullSiVacio(String pParametro) {
		if (pParametro != null && pParametro.trim().equals("")) {
			return null;
		}
		else {
			return pParametro;
		}
	}
	
	/**
	 * 
	 * @param pParametro Un objeto cualquiera.
	 * @return String vacío si el objeto es <code>null</code>, <code>pParametro.toString()</code> si no es <code>null</code>.
	 */
	public static String getVacioSiNull(Object pParametro) {
		if (pParametro == null) {
			return "";
		}
		else {
			return pParametro.toString();
		}
	}
	
	public static String getString(Object pObjeto) {
		if (pObjeto != null) {
			return pObjeto.toString();
		}
		else {
			return "";
		}
	}
	
	public static String getString(Date pDate, String pFormato) {
        String strFecha = null;
        try {
            strFecha = (new SimpleDateFormat(pFormato)).format(pDate);
        }
        catch (Exception ex) {}
        
        return strFecha;
	}
	
	public static String getString(Double pDouble) {
		String strDouble = "";
        DecimalFormat formato = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.US);
//        forma
        try {
//            strDouble = new DecimalFormat().format(pDouble);
        	strDouble = new DecimalFormat().format(pDouble);
        }
        catch (Exception ex) {}
        
        return strDouble;
	}
	
	public static String getString(Date pDate) {
		return Conversor.getString(pDate, Conversor.FORMATO_FECHA);
	}
	
	public static String getStringHora(Date pDate){
		try{
			return new SimpleDateFormat("HH:mm").format(pDate);
		} catch (Exception e){
			return "";
		}
	}
	
	public static Integer getInteger(String pNumero) {
        Integer elNumero = null;
        if (pNumero != null && !pNumero.equals("")) {
	        try {
	            boolean esInteger = Validador.isValidInteger(pNumero);
	            if (esInteger) elNumero = new Integer(pNumero);
	        } catch(Exception e) {
	            elNumero = null;
	        }
        }
        return elNumero;
	}
	
	/**
	 * Obtiene un valor Float
	 * @param numero
	 * @return Un número de tipo Float o NULL si no es un valor valido. 
	 */
    public static Float getFloat(String numero) {
        Float elNumero = null;
        if (numero != null && !numero.equals("")) {
	        try {
	            boolean esFloat = Validador.isValidFloat(numero);
	            if (esFloat) elNumero = new Float(numero);
	        } catch(Exception e) {
	            elNumero = null;
	        }
        }
        return elNumero;
    }
    
    
    /**
     * Obtiene un valor Double
     * @param numero cadena de texto a convertir
     * @return
     */
    public static Double getDouble(String numero) {
        Double elNumero = null;
        if (numero != null && !numero.equals("")) {
	        try {
	            boolean esDouble = Validador.isValidFloat(numero);
	            if (esDouble) elNumero = new Double(numero);
	        } catch(Exception e) {
	            elNumero = null;
	        }
        }
        return elNumero;
    }
    
    public static Double getDouble(Object pObjeto) {
    	Double elNumero = null;
    	if (pObjeto instanceof Number) {
			if (pObjeto != null) {
				Number numero = (Number) pObjeto;

				if (numero != null && !numero.equals("")) {
					try {
						boolean esDouble = Validador.isValidFloat(numero
								.toString());
						if (esDouble)
							elNumero = new Double(numero.doubleValue());
					} catch (Exception e) {
						elNumero = null;
					}
				}
			}
		}
		
		 return elNumero;
	}

    
    /**
     * Obtiene un Long desde una cadena de texto
     * @param pNumero número a convertir
     * @return Long con el número correspondiente
     */
	public static Long getLong(String pNumero) {
	    Long elNumero = null;
        if (pNumero != null && !pNumero.equals("")) {
	        try {
	            boolean esValido = Validador.isValidInteger(pNumero);
	            if (esValido) {
	            	elNumero = new Long(pNumero);
	            }
	        } catch(Exception e) {
	        	e.printStackTrace();
	            elNumero = null;
	        }
        }
        return elNumero;
	}
	
	public static Date getDateConHora(String fecha, String hora){
		Date locFecha = null;
		if (fecha != null && fecha != "" && hora != null && hora.trim() != ":"){
			try{
				locFecha = new SimpleDateFormat(FORMATO_FECHA_HORA).parse(fecha + " " + hora);
			} catch (Exception e){
			}
		}
		return locFecha;
	}
	

	public static Date getDate(String fecha) {
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
	
	public static Date getDate(Calendar fecha) {
		Date laFecha = null;
		if (fecha != null) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
				String dateSimple = dateFormat.format(fecha.getTime());
				Date dateS = new SimpleDateFormat(FORMATO_FECHA).parse(dateSimple);
				laFecha = dateS;
			} catch(Exception e) {
				laFecha = null;
			}
		}
		return laFecha;
	}
	
	public static String getDia(String dia) {
		String diaCompleto = null;
		
		if (dia != null && dia != "") {
			try {
				if (getInteger(dia) <= 9) {
					diaCompleto = '0' + Conversor.getString(dia);
				}
				else {
					diaCompleto = Conversor.getString(dia);
				}
			} catch(Exception e) {
				diaCompleto = null;
			}
		}
		return diaCompleto;
	}
	
	public static String getDia(int dia) {
		String diaCompleto = null;

		try {
			if (dia <= 9) {
				diaCompleto = '0' + Conversor.getString(dia);
			}
			else {
				diaCompleto = Conversor.getString(dia);
			}
		} catch(Exception e) {
			diaCompleto = null;
		}
	
		return diaCompleto;
	}
}
