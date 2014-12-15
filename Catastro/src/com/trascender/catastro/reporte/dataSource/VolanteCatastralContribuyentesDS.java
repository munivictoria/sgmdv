package com.trascender.catastro.reporte.dataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class VolanteCatastralContribuyentesDS implements JRDataSource{

	private int linea_Actual = -1;
	private ArrayList<RegistroPropietario> listaLineasContribuyentes = new ArrayList<RegistroPropietario>();
	
	public VolanteCatastralContribuyentesDS (List <RegistroPropietario> listaContribuyentes){
		for (RegistroPropietario cadaPropietario : listaContribuyentes){
			listaLineasContribuyentes.add(cadaPropietario);
		}
	}
	
	public Object getFieldValue (JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if ("F_APELLIDO_NOMBRE".equals(arg0.getName())){
			if(listaLineasContribuyentes.get(linea_Actual).getPersona() instanceof PersonaFisica){
				locValor = Util.getFormatIfNull((((PersonaFisica) listaLineasContribuyentes.get(linea_Actual).getPersona()).getNombre()));
			}else if(listaLineasContribuyentes.get(linea_Actual).getPersona() instanceof PersonaJuridica){
				locValor = Util.getFormatIfNull(((PersonaJuridica) listaLineasContribuyentes.get(linea_Actual).getPersona()).getRazonSocial());
			}
			
		}else if("F_DOMICILIO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineasContribuyentes.get(linea_Actual).getPersona().getDomicilio().getDomicilioCompleto());
		}else if("F_CODIGO_POSTAL".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineasContribuyentes.get(linea_Actual).getPersona().getDomicilio().getCodigoPostal());
		}else if("F_LOCALIDAD".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineasContribuyentes.get(linea_Actual).getPersona().getDomicilio().getLocalidad().getNombre());
		}
		
		return locValor;
	}
	public boolean next() throws JRException {
		return ++linea_Actual < listaLineasContribuyentes.size();
	}

}
