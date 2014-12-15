/*
 * Conversor.java
 *
 * Created on 25 de octubre de 2006, 08:35
 */
package com.trascender.presentacion.conversores;

import com.trascender.presentacion.validadores.Validador;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Conversores para la presentacion.
 *
 * @author Ariel
 */
public class Conversor {

    private static final String FORMATO_FECHA_CORTA = "dd/MM/yyyy";
    private static final String FORMATO_FECHA_HORA = "dd/MM/yyyy-hh:mm";

    public Conversor() {
    }

    public static Date getFechaHoraDeString(String fecha) {
        return Conversor.getFechaFormatoDeString(fecha, FORMATO_FECHA_HORA);
    }

    // OBTENER FECHAS
    public static Date getFechaCortaDeString(String fecha) {
        return Conversor.getFechaFormatoDeString(fecha, FORMATO_FECHA_CORTA);
    }

    public static Date getFechaFormatoDeString(String fecha, String formato) {
        Date laFecha = null;
        if (fecha != null && fecha != "") {
            try {
                Date dateSimple = new SimpleDateFormat(formato).parse(fecha);
                Format formatter = new SimpleDateFormat(formato);
                laFecha = dateSimple;
//                if (fecha.equals(formatter.format(dateSimple))) {
//                    laFecha = dateSimple;
//                }
            } catch (Exception e) {
                laFecha = null;
            }
        }
        return laFecha;
    }

    // OBTENER STRING
    public static String getStringDeObject(Object objetoTipoString) {
        String strObjeto = null;
        try {
            strObjeto = String.valueOf(objetoTipoString);
        } catch (Exception ex) {
        }

        return strObjeto;
    }

    public static String getStringDeFechaCorta(Date fecha) {
        return Conversor.getStringDeFechaFormato(fecha, FORMATO_FECHA_CORTA);
    }

    public static String getStringDeFechaFormato(Date fecha, String formato) {
        String strFecha = null;
        try {
            strFecha = (new SimpleDateFormat(formato)).format(fecha);
        } catch (Exception ex) {
        }

        return strFecha;
    }

    // OBTENER NUMERICOS
    public static Integer getIntegerDeString(String numero) {
        Integer elNumero = null;
        try {
            boolean esInteger = new Validador().esEntero(numero);
            if (esInteger) {
                elNumero = new Integer(numero);
            }
        } catch (Exception e) {
            elNumero = null;
        }
        return elNumero;
    }

    public static Float getFloatDeString(String numero) {
        Float elNumero = null;
        try {
            boolean esFloat = new Validador().esFlotante(numero);
            if (esFloat) {
                elNumero = new Float(numero);
            }
        } catch (Exception e) {
            elNumero = null;
        }
        return elNumero;
    }

//    public static BigDecimal getBigDecimalDeString(String numero){
//        BigDecimal elNumero = null;
//        try{
//            boolean esBigDecimal = new Validador().esFlotante(numero);
//            if(esBigDecimal) elNumero = new BigDecimal(numero);
//        }catch(Exception ex){
//            elNumero = null;
//        }
//        return elNumero;
//
//    }
    public static Double getDoubleDeString(String numero) {
        Double elNumero = null;

        try {
            boolean esDouble = new Validador().esFlotante(numero);
            if (esDouble) {
                //NumberFormat locNumberFormat = NumberFormat.getInstance(Locale.US);
                //locNumberFormat.setRoundingMode(RoundingMode.UNNECESSARY);

                BigDecimal locBigDecimal = new BigDecimal(numero);

                elNumero = Double.valueOf(locBigDecimal.doubleValue());

            }
        } catch (Exception e) {
            elNumero = null;
        }
        return elNumero;
    }

    public static String getStringDeDouble(Double numero) {
        String strNumero = null;
        NumberFormat locNumberFormat = NumberFormat.getNumberInstance(Locale.US);
        try {
            locNumberFormat.setMaximumIntegerDigits(9);
            locNumberFormat.setRoundingMode(RoundingMode.UNNECESSARY);
            //nF.igits(9);
            //  strNumero = locNumberFormat.format(numero);
            BigDecimal locBigDecimal = new BigDecimal(numero.toString());
            strNumero = locBigDecimal.toString();
            // locNumberFormat.parse(strNumero);

        } catch (Exception ex) {
        }
        return strNumero;//locNumberFormat.format(numero.floatValue());
    }
}