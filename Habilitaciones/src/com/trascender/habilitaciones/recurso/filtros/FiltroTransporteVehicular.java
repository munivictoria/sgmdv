package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

public class FiltroTransporteVehicular extends FiltroAbstracto<TransporteVehicular>{
	private static final long serialVersionUID = -2940654922316604268L;
	
	private DocumentoSHPS documentoEspecializado;
	private String numeroInscripcion;
	private Vehiculo vehiculo;
	private Boolean activo;
	
	public DocumentoSHPS getDocumentoEspecializado() {
		return documentoEspecializado;
	}
	public void setDocumentoEspecializado(DocumentoSHPS documentoEspecializado) {
		this.documentoEspecializado = documentoEspecializado;
	}
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}
