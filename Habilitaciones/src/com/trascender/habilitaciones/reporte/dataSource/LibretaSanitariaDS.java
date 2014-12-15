package com.trascender.habilitaciones.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.shps.ConstanciaVacunacion;
import com.trascender.habilitaciones.recurso.persistent.shps.InhabilitacionTemporariaLS;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.RenovacionLibretaSanitaria;

public class LibretaSanitariaDS extends TrascenderDataSource{
	
	private LibretaSanitaria libretaSanitaria;
	private Map <String, Object> parametros;
	private int lineaActual = -1;
	
	public LibretaSanitariaDS(final LibretaSanitaria pLibretaSanitaria){
		this.libretaSanitaria = pLibretaSanitaria;
		parametros = new HashMap<String, Object>();
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		ConstanciaVacunacionDS vacunacionDS = new ConstanciaVacunacionDS(new ArrayList<ConstanciaVacunacion>(pLibretaSanitaria.getListaConstanciasVacunaciones()));
		parametros.put("PAR_VACUNACIONES_DS", vacunacionDS);
		RenovacionDS renovacionDS = new RenovacionDS(new ArrayList<RenovacionLibretaSanitaria>(pLibretaSanitaria.getListaRenovaciones()));
		parametros.put("PAR_RENOVACION_DS", renovacionDS);
		InhabilitacionesTemporalesDS inhabilitacionDS = new InhabilitacionesTemporalesDS(new ArrayList<InhabilitacionTemporariaLS>(pLibretaSanitaria.getListaInhabilitacionesTemporarias()));
		parametros.put("PAR_INHABILITACION_DS", inhabilitacionDS);
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("F_NRO_LIBRETA_SANITARIA")){
			valor = libretaSanitaria.getNumeroLibretaSanitaria();
		} else if (field.getName().equals("F_ACTIVO")){
			valor = libretaSanitaria.isActivo() ? "SI" : "NO";
		} else if (field.getName().equals("F_PERSONA")){
			valor = libretaSanitaria.getNombrePersonaFisica();
		}
		return valor;
	}
	
	public boolean next() throws JRException {
		return ++lineaActual < 1;
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "Reporte_Libreta_Sanitaria.jasper";
	}
	
}

class RenovacionDS implements JRDataSource{
	
	List<RenovacionLibretaSanitaria> listaRenovaciones;
	private int lineaActual = -1;
	
	public RenovacionDS(List<RenovacionLibretaSanitaria> pListaRenovaciones){
		this.listaRenovaciones = pListaRenovaciones;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("F_FECHA_RENOVACION")){
			valor = getLineaActual().getFechaDesde();
		} else if (field.getName().equals("F_FECHA_VIGENCIA")){
			valor = getLineaActual().getFechaVigencia();
		}
		return valor;
	}
	
	private RenovacionLibretaSanitaria getLineaActual(){
		return listaRenovaciones.get(lineaActual);
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaRenovaciones.size();
	}
	
}

class InhabilitacionesTemporalesDS implements JRDataSource{
	
	private int lineaActual = -1;
	private List<InhabilitacionTemporariaLS> listaInhabilitaciones;
	
	public InhabilitacionesTemporalesDS(List<InhabilitacionTemporariaLS> pListaInhabilitaciones){
		this.listaInhabilitaciones = pListaInhabilitaciones;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("F_FECHA_INHABILITACION")){
			valor = getLineaActual().getFechaInhabilitacion();
		} else if (field.getName().equals("F_DIAGNOSTICO")) {
			valor = getLineaActual().getDiagnostico();
		} else if (field.getName().equals("F_PRUEBA_CONFIRMATORIA")){
			valor = getLineaActual().getPruebaConfirmatoria();
		} else if (field.getName().equals("F_FECHA_REINTEGRO")){
			valor = getLineaActual().getFechaReintegro();
		}
		return valor;
	}
	
	private InhabilitacionTemporariaLS getLineaActual(){
		return listaInhabilitaciones.get(lineaActual);
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaInhabilitaciones.size();
	}
	
	
}
	
class ConstanciaVacunacionDS implements JRDataSource{
	
	private int lineaActual = -1;
	
	List<ConstanciaVacunacion> listaConstancias;
	
	public ConstanciaVacunacionDS(List<ConstanciaVacunacion> pListaConstancias){
		this.listaConstancias = pListaConstancias;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object valor = null;
		if (field.getName().equals("F_FECHA_VACUNACION")){
			valor = getLineaActual().getFechaVacunacion();
		} else if (field.getName().equals("F_VACUNA")){
			valor = getLineaActual().getVacuna();
		} else if (field.getName().equals("F_VALIDEZ")) {
			valor = getLineaActual().getFechaValidez();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaConstancias.size();
	}
	
	private ConstanciaVacunacion getLineaActual(){
		return listaConstancias.get(lineaActual);
	}

		
}


