package com.trascender.habilitaciones.recurso.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EXENCION_OBLIGACION")
@PrimaryKeyJoinColumn(name = "ID_EXENCION")
public class ExencionObligacion extends Exencion{

	public static final long serialVersionUID = 4669550025902361544L;
	
	@OneToMany
	@JoinTable(name="RELA_EXENCION_OBLIGACION", joinColumns=@JoinColumn(name="ID_EXENCION"),
	inverseJoinColumns=@JoinColumn(name="ID_OBLIGACION"))
	private List<Obligacion> listaObligacion = new ArrayList<Obligacion>();
	
	@OneToMany(mappedBy="exencionObligacion", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<CondicionAplicacionExencion> listaCondicionAplicacion = new ArrayList<CondicionAplicacionExencion>();
	
	@OneToMany(mappedBy="exencionObligacion", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<CondicionAplicacionExencionNumeroCuota> listaCondicionAplicacionNumeroCuota = new ArrayList<CondicionAplicacionExencionNumeroCuota>();
	
	private boolean siempre = false;
	
	public List<Obligacion> getListaObligacion() {
		return listaObligacion;
	}

	public void setListaObligacion(List<Obligacion> listaObligacion) {
		this.listaObligacion = listaObligacion;
	}

	public List<CondicionAplicacionExencion> getListaCondicionAplicacion() {
		return listaCondicionAplicacion;
	}

	public void setListaCondicionAplicacion(
			List<CondicionAplicacionExencion> listaCondicionAplicacion) {
		this.listaCondicionAplicacion = listaCondicionAplicacion;
	}
	
	public List<CondicionAplicacionExencionNumeroCuota> getListaCondicionAplicacionNumeroCuota() {
		return listaCondicionAplicacionNumeroCuota;
	}

	public void setListaCondicionAplicacionNumeroCuota(
			List<CondicionAplicacionExencionNumeroCuota> listaCondicionAplicacionNumeroCuota) {
		this.listaCondicionAplicacionNumeroCuota = listaCondicionAplicacionNumeroCuota;
	}

	public boolean isSiempre() {
		return siempre;
	}

	public void setSiempre(boolean siempre) {
		this.siempre = siempre;
	}

	@Override
	public String toString(){
		return this.getNombre() + " [%"+this.getValor()+"] ["+this.getEstado().toString()+"]";
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
