package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EXP_EVENTO")
public class Evento implements Serializable {

	private static final long serialVersionUID = 7902387045828724570L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_evento")
	@SequenceGenerator(name = "gen_id_exp_evento", sequenceName = "gen_id_exp_evento", allocationSize = 1)
	@Column(name = "ID_EVENTO")
	private long idEvento = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;


	private boolean activo = true;


	public long getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
