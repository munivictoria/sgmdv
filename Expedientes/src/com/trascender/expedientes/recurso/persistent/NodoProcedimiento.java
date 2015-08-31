/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.expedientes.enums.EstadoPlantilla;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "EXP_NODOPROCEDIMIENTO")
public abstract class NodoProcedimiento implements Serializable {

	private static final long serialVersionUID = -6850165600605227290L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_nodoProcedimiento")
	@SequenceGenerator(name = "gen_id_exp_nodoProcedimiento", sequenceName = "gen_id_exp_nodoProcedimiento", allocationSize = 1)
	@Column(name = "ID_NODOPROCEDIMEINTO")
	protected long idNodoProcedimiento = -1l;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "ID_PLAZO")
	protected PlazoProcedimiento plazo;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "ID_RESPONSABLE")
	protected Responsable responsable;

	@Column(name = "ORDEN")
	protected Integer orden;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nodoPadre", orphanRemoval = true)
	protected List<NodoProcedimiento> listaNodosHijos = new ArrayList<NodoProcedimiento>();

	@ManyToOne
	@JoinColumn(name = "ID_NODOPADRE")
	protected NodoProcedimiento nodoPadre;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;
	
	public long getIdNodoProcedimiento() {
		return idNodoProcedimiento;
	}

	public void setIdNodoProcedimiento(long idNodoProcedimiento) {
		this.idNodoProcedimiento = idNodoProcedimiento;
	}

	public PlazoProcedimiento getPlazo() {
		return plazo;
	}

	public void setPlazo(PlazoProcedimiento plazo) {
		this.plazo = plazo;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public List<NodoProcedimiento> getListaNodosHijos() {
		try {
			Collections.sort(listaNodosHijos, comparatorOrden);
		} catch(NullPointerException npe) {
			System.out.println(npe.getMessage() + " Nodo de Procedimiento sin orden asignado ");
			// npe.printStackTrace();
		} catch(Exception e) {
			// e.printStackTrace();
		}
		return listaNodosHijos;
	}

	public void setListaNodosHijos(List<NodoProcedimiento> listaNodosHijos) {
		this.listaNodosHijos = listaNodosHijos;
	}

	public NodoProcedimiento getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(NodoProcedimiento nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	private static Comparator<NodoProcedimiento> comparatorOrden = new Comparator<NodoProcedimiento>() {

		@Override
		public int compare(NodoProcedimiento nodo1, NodoProcedimiento nodo2) {
			return nodo1.getOrden().compareTo(nodo2.getOrden());
		}
	};

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final NodoProcedimiento other = (NodoProcedimiento) obj;
		if(this.idNodoProcedimiento == -1 || other.idNodoProcedimiento == -1) {
			return this == other;
		}
		if(idNodoProcedimiento != other.idNodoProcedimiento)
			return false;
		
		return true;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

}