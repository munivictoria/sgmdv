package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.monitor.alarm.Alarm;

import com.trascender.framework.recurso.persistent.FirmaPermiso;

@Entity
@Table(name = "PERMISO_TIPO_TASA")
public class PermisoTipoTasa implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 512376552446993830L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_permiso_tipo_tasa")
	@SequenceGenerator(name = "gen_id_permiso_tipo_tasa", sequenceName = "gen_id_permiso_tipo_tasa", allocationSize = 1)
	@Column(name = "ID_PERMISO_TIPO_TASA")
	private long idPermisoTipoTasa=-1;
	
//	@ManyToOne
//	@JoinColumn(name = "ID_TIPO_TASA", nullable = false)
//	private TipoTasa tipoTasa;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB", nullable = false)
	private FirmaPermiso firma;
	
	
	public long getIdPermisoTipoTasa() {
		return idPermisoTipoTasa;
	}
	public void setIdPermisoTipoTasa(long idPermisoTipoTasa) {
		this.idPermisoTipoTasa = idPermisoTipoTasa;
	}
	public FirmaPermiso getFirma() {
		return firma;
	}
	public void setFirma(FirmaPermiso firma) {
		this.firma = firma;
	}
	
//	public TipoTasa getTipoTasa() {
//		return tipoTasa;
//	}
//	public void setTipoTasa(TipoTasa tipoTasa) {
//		this.tipoTasa = tipoTasa;
//	}
	
	public boolean isHabilitado(){
		return (this.getFirma()!=null);
	}
}
