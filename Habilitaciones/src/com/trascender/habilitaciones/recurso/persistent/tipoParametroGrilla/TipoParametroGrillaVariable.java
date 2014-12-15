package com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_PARAMETRO_GRILLA_VARIABLE")
public class TipoParametroGrillaVariable implements Serializable {
	private static final long serialVersionUID = 4726444717749125107L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="GEN_ID_TIPO_PARAMETRO_GRILLA_VARIABLE")
	@SequenceGenerator(name = "GEN_ID_TIPO_PARAMETRO_GRILLA_VARIABLE", sequenceName = "GEN_ID_TIPO_PARAMETRO_GRILLA_VARIABLE",allocationSize = 1)
	@Column(name = "ID_TIPO_PARAMETRO_GRILLA_VARIABLE")
	private long idTipoParametroGrillaVariable = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PARAMETRO_GRILLA")
	private TipoParametroGrilla grilla;
	
	private String nombre;
	
	private String descripcion;
	
	public TipoParametroGrillaVariable(){
	}
	
	public TipoParametroGrillaVariable(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public long getIdTipoParametroGrillaVariable() {
		return idTipoParametroGrillaVariable;
	}

	public void setIdTipoParametroGrillaVariable(long idTipoParametroGrillaVariable) {
		this.idTipoParametroGrillaVariable = idTipoParametroGrillaVariable;
	}

	public TipoParametroGrilla getGrilla() {
		return grilla;
	}

	public void setGrilla(TipoParametroGrilla grilla) {
		this.grilla = grilla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}