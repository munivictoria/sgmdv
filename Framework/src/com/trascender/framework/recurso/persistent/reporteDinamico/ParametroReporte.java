/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.persistent.reporteDinamico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "PARAMETRO_REPORTE")
public class ParametroReporte implements Serializable {

	public static final long serialVersionUID = 6155966271951148400L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_parametro_reporte")
	@SequenceGenerator(name = "gen_id_parametro_reporte", sequenceName = "gen_id_parametro_reporte", allocationSize = 1)
	@Column(name = "ID_PARAMETRO_REPORTE")
	private long idParametroReporte = -1;

	private String nombre;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Column(name = "nombre_atributo")
	private String nombreAtributo;

	private Integer orden;

	@ManyToOne
	@JoinColumn(name = "ID_REPORTE")
	private Reporte reporte;

	private boolean requerido = false;

	public enum Tipo {
		CADENA, CADENA_LARGA, NUMÉRICO, DECIMAL, BOOLEANO, FECHA, LISTADO_SIMPLE, LISTADO_MULTIPLE, IMAGEN, RECURSO, FIRMA_ELECTRONICA;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	@Column(name = "ID_RECURSO")
	private Long idRecurso;

	@OneToMany(mappedBy = "parametroReporte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OpcionParametroReporte> listaOpciones = new ArrayList<OpcionParametroReporte>();

	public List<OpcionParametroReporte> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<OpcionParametroReporte> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long idRecurso) {
		this.idRecurso = idRecurso;
	}

	public long getIdParametroReporte() {
		return idParametroReporte;
	}

	public void setIdParametroReporte(long pIdParametroReporte) {
		idParametroReporte = pIdParametroReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte pReporte) {
		reporte = pReporte;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String pNombreAtributo) {
		nombreAtributo = pNombreAtributo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer pOrden) {
		orden = pOrden;
	}

	public String getNombreRecurso() {
		if(getIdRecurso() == null) {
			return null;
		}

		return SecurityMgr.getInstance().getNombreRecurso(this.getIdRecurso());
	}

	public String getStringOpcionesORecurso() {
		if(this.tipo == Tipo.RECURSO) {
			return getNombreRecurso();
		} else if(this.tipo == Tipo.LISTADO_SIMPLE || this.tipo == Tipo.LISTADO_MULTIPLE) {
			return Util.getStringDeLista(listaOpciones, ", ");
		} else {
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idParametroReporte ^ (idParametroReporte >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		ParametroReporte other = (ParametroReporte) obj;
		if(other.idParametroReporte == -1 || this.idParametroReporte == -1) {
			return other == this;
		}
		if(idParametroReporte != other.idParametroReporte) {
			return false;
		}
		return true;
	}

}