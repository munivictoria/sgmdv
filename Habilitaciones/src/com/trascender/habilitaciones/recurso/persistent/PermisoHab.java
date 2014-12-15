package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Rol;

@Entity
@Table(name = "PERMISO_HAB")
@PrimaryKeyJoinColumn(name = "ID_DOC_HABILITANTE")
public class PermisoHab extends DocHabilitante {
	
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 4299493475317207503L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firma;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false)
	private Rol rol;
	
	public PermisoHab(){
		this.setEstado(Estado.CREADO);
	}
	
	public FirmaPermiso getFirma() {
		return firma;
	}
	public void setFirma(FirmaPermiso firma){
		this.firma=firma;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	@Override
	public boolean habilitar() {
		if (this.getFirma()!=null){
			return super.habilitar();
		}
		else{
			return false;
		}
	}
	
}
