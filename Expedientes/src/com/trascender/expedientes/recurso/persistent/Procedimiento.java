/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.framework.recurso.persistent.Numerador;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name = "EXP_PROCEDIMIENTO")
public class Procedimiento extends NodoProcedimiento implements Serializable {

	public static final long serialVersionUID = -4906745053107266465L;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;

	@Column(name = "NOMBRE")
	protected String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_NUMERADOR")
	protected Numerador numerador;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	public List<FaseProcedimiento> getListaFasesProcedimiento() {
		List<FaseProcedimiento> lista = new ArrayList<FaseProcedimiento>();
		for(NodoProcedimiento faseP : getListaNodosHijos()) {
			if(faseP.getEstado().equals(EstadoPlantilla.ACTIVO))
			lista.add((FaseProcedimiento) faseP);
		}

		return lista;
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Numerador getNumerador() {
		return numerador;
	}

	public void setNumerador(Numerador numerador) {
		this.numerador = numerador;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final NodoProcedimiento other = (NodoProcedimiento) obj;
		if(idNodoProcedimiento != other.idNodoProcedimiento)
			return false;

		return true;
	}

}