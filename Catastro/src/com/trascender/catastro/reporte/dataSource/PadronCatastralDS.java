package com.trascender.catastro.reporte.dataSource;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Planta.Edificacion;
import com.trascender.catastro.recurso.persistent.TipoPlanta;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;


public class PadronCatastralDS extends TrascenderDataSource {

	private int linea_Actual = -1;
	private ArrayList <Parcela>listaLineas = new ArrayList<Parcela>();
	private Map <String, Object>parametros;
	private SystemAdministracionZonificacion systemZonificacion;
	private JasperReport reporte;
	private boolean incluyeMejoras = false;
	
	public PadronCatastralDS(List<Parcela> plistaParcelas, SystemAdministracionZonificacion systemZonificacion, boolean pIncluyeMejoras) throws Exception{
		parametros = new HashMap<String, Object> ();
				
		incluyeMejoras = pIncluyeMejoras;
		
		for(Parcela cadaParcela : plistaParcelas){
			listaLineas.add(cadaParcela);
		}
		this.systemZonificacion = systemZonificacion;
		URL urlSubreporteMejoras = this.getClass().getResource("/com/trascender/catastro/reporte/compilado/Reporte_Padron_Catastral_Mejoras_Subreporte.jasper");
		reporte = (JasperReport) JRLoader.loadObject(urlSubreporteMejoras);
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = new Object();
		
		if("F_NUM_REGISTRO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getNroRegistro();
		}else if("F_NUM_MANZANA".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getManzana().getNroManzana();
		}else if("F_NUM_PARCELA".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getNomenclaturaCatastral().getNroParcela();
		}else if("F_PARTIDA_PROVINCIAL".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getNroPartidaProvincial());
		}else if("F_RAZONSOCIAL_CONTRIBUYENTE".equals(arg0.getName())){
			Persona locPersona = listaLineas.get(linea_Actual).getTituloPropiedad().getListaRegistrosPropietarios().get(0).getPersona(); 
			if(locPersona instanceof PersonaFisica)
				locValor = Util.getFormatIfNull(((PersonaFisica)locPersona).getApellido() + ", " + ((PersonaFisica)locPersona).getNombre());
			else
				locValor = Util.getFormatIfNull(((PersonaJuridica)locPersona).getRazonSocial());
		}else if("F_DOMICILIO_PARCELARIO".equals(arg0.getName())){
			locValor = (listaLineas.get(linea_Actual).getDomicilioParcelario().getDomicilioCompleto());
		}else if("F_CODIGO_CALLE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(linea_Actual).getDomicilioParcelario().getRelacionCalle().getCodigo());
		}else if("F_METROS_FRENTE".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getMetrosFrente();
		}else if("F_ZONA_RENTAS".equals(arg0.getName())){
			try {
				locValor = Util.getFormatIfNull(systemZonificacion.getListaZonasFromParcela(listaLineas.get(linea_Actual)).get(0).getNombre());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TrascenderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("F_ES_BALDIO".equals(arg0.getName())){ 
			if(listaLineas.get(linea_Actual).getPlanta().getTipoEdificacion().equals(Edificacion.BALDIO))
			locValor = "SI";
			else
				locValor = "NO";
		}else if("F_RADIO_CENTRO".equals(arg0.getName())){
			if(listaLineas.get(linea_Actual).getPlanta().getTipoPlanta().equals(TipoPlanta.Tipo.URBANA))
				locValor = "SI";
				else
				locValor = "NO";
		}else if("F_AÑO_ALTA_REGISTRO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getTituloPropiedad().getFechaInscripcion();
		}else if("F_SUPERFICIE_TERRENO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getSuperficie();
		}else if("F_SUPERFICIE_MEJORAS".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getSuperficieMejoras();
		}else if("F_AVALUO_TERRENO".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getAvaluoTerreno();
		}else if("F_AVALUO_MEJORAS".equals(arg0.getName())){
			locValor = listaLineas.get(linea_Actual).getAvaluoPorMejoras();
		}else if(("F_MEJORAS_DATASOURCE".equals(arg0.getName())) && (incluyeMejoras)){
			MejorasDS locMejorasDS = new MejorasDS(listaLineas.get(linea_Actual).getListaRegistrosMejora());
			locValor=locMejorasDS;
		}else if(("F_MEJORAS_SUBREPORTE".equals(arg0.getName())) && (incluyeMejoras)){
			locValor=reporte;
		}else{
			locValor = null;
		}
		return locValor;
	}
	
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++linea_Actual < listaLineas.size();
	}

	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Padron_Catastral.jasper";
	}

}
