package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCESO_DIRECTO")
public class AccesoDirecto implements Serializable{
	private static final long serialVersionUID = -716924532413280256L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_acceso_directo")
	@SequenceGenerator(name = "gen_id_acceso_directo", sequenceName = "gen_id_acceso_directo", allocationSize = 1)
	@Column(name = "ID_ACCESO_DIRECTO")
	private long idAccesoDirecto = -1;

	@ManyToOne
	@JoinColumn(name = "ID_CONFIGURACION")
	private ConfiguracionAccesosDirectos configuracion;
	
	@Column(name = "ID_RECURSO")
	private long idRecurso;

	public long getIdAccesoDirecto() {
		return idAccesoDirecto;
	}

	public void setIdAccesoDirecto(long pIdAccesoDirecto) {
		idAccesoDirecto = pIdAccesoDirecto;
	}

	public ConfiguracionAccesosDirectos getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionAccesosDirectos pConfiguracion) {
		configuracion = pConfiguracion;
	}

	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long pIdRecurso) {
		idRecurso = pIdRecurso;
	}
	
}
