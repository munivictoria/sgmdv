package com.trascender.framework.recurso.filtros;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Rol.Estado;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroRol extends FiltroAbstracto<Rol>{

	public FiltroRol() {
	}
	public FiltroRol(String pNombre, Date pDesde, Date pHasta,
			Boolean pFirma, Estado pEstado) {
		nombre = pNombre;
		desde = pDesde;
		hasta = pHasta;
		firma = pFirma;
		estado = pEstado;
	}
	private static final long serialVersionUID = -6430553660923094690L;
	
	
	private String nombre;
	private Date desde;
	private Date hasta;
	private Boolean firma;
	private Rol.Estado estado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date pDesde) {
		desde = pDesde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date pHasta) {
		hasta = pHasta;
	}
	public Boolean getFirma() {
		return firma;
	}
	public void setFirma(Boolean pFirma) {
		firma = pFirma;
	}
	public com.trascender.framework.recurso.persistent.Rol.Estado getEstado() {
		return estado;
	}
	public void setEstado(
			com.trascender.framework.recurso.persistent.Rol.Estado pEstado) {
		estado = pEstado;
	}
	
}
