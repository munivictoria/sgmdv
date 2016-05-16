package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;


public class IngresoVarioDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private IngresoVario locIngresoVario;
	String locCodigoBarra = "";
	
	public IngresoVarioDS(IngresoVario pIngresoVario, Usuario pUser) throws Exception{ 
		parametros = new HashMap<String, Object> ();
		locIngresoVario = pIngresoVario;
		
        locCodigoBarra = rellenarCodigoBarra(String.valueOf(locIngresoVario.getIdRegistroDeuda()));
				
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NUMERO", locIngresoVario.getNumero());
		parametros.put("PAR_CONCEPTO", locIngresoVario.getConceptoIngresoVario().getNombre());
		parametros.put("PAR_OBS_INGRESO_VARIO", locIngresoVario.getObservaciones());
		parametros.put("PAR_USUARIO", pUser);
		
		Persona locPersona = locIngresoVario.getPersona();
		if(locPersona instanceof PersonaFisica) {
			parametros.put("PAR_PERSONA", Util.getFormatIfNull(((PersonaFisica)locPersona).getDenominacion())+" [" + locPersona.getCuim()+"]");
		} else {
			parametros.put("PAR_PERSONA", Util.getFormatIfNull(((PersonaJuridica)locPersona).getRazonSocial())+" [" + locPersona.getCuim()+"]");
		}
		
//		parametros.put("PAR_FIRMA", Util.getFormatIfNull(((PersonaFisica)locPersona).getDenominacion()));
		parametros.put("PAR_VALOR", locIngresoVario.getValor());		
		parametros.put("PAR_FECHA_EMISION", locIngresoVario.getFechaEmision());
		parametros.put("PAR_FECHA_IMPRESION", new Date());
//		parametros.put("PAR_OBS", locIngresoVario.getObservaciones());
		parametros.put("PAR_CODIGOBARRA_INGRESO_VARIO", "2"+ Util.formatString(18, String.valueOf(locIngresoVario.getIdRegistroDeuda())));
		parametros.put("PAR_IMPUTACIONES_DS", new ImputacionesIngresoVarioDS(locIngresoVario));
		parametros.put("PAR_INGRESO_VARIO", locIngresoVario);
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor =  new Object();
		
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < 1;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Ingreso_Vario.jasper";
	}
	
    public String rellenarCodigoBarra(String pCadena) {
        String retorno = "";
        int locTamanio = pCadena.length();
        int agregar = 18 - locTamanio;
        retorno += "2";
        if (agregar > 0) {
            for (int i = 0; i < agregar; i++) {
                retorno += "0";
            }
        }
        return retorno + pCadena;
    }

}

class ImputacionesIngresoVarioDS implements JRDataSource{
	private int lActual = -1;
	private final List<ImputacionIngresoVario> locListaImputacion;

	public ImputacionesIngresoVarioDS(IngresoVario pIngresoVario){
		this.locListaImputacion = new ArrayList<ImputacionIngresoVario>(pIngresoVario.getListaImputacionIngresos());
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;

		if (field.getName().equals("F_NUMERO_CUENTA")){
			valor = locListaImputacion.get(lActual).getCuenta().getCodigoImputacionCompleto();
		} else if(field.getName().equals("F_NOMBRE_CUENTA")){
			valor = locListaImputacion.get(lActual).getCuenta().getNombre();
		} else if(field.getName().equals("F_VALOR_IMPUTACION")){
			valor = locListaImputacion.get(lActual).getMonto();
		}
		
		return valor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lActual < locListaImputacion.size();
	}		

}
