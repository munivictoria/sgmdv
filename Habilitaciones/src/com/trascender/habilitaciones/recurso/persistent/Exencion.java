package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;

/**
 * @autor Maximiliano Fontanini
 */
@Entity
@Table(name = "EXENCION")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Exencion implements Serializable{
	
	public static final long serialVersionUID = 4484942235869476333L;

	public enum Estado{ 
		VIGENTE, TERMINADA;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_exencion")
	@SequenceGenerator(name = "gen_id_exencion", sequenceName = "gen_id_exencion", allocationSize = 1)
	@Column(name = "ID_EXENCION")
	private long idExencion = -1l;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(nullable = false, name = "VALOR")
	private Double valor;
	
	private boolean fijo=false;
	
	@ManyToOne 
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private Estado estado=Estado.VIGENTE;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERIODICIDAD_CUOTAS", nullable = false)
	private Periodicidad periodicidadCuotas=Periodicidad.ANUAL;
	

	@Column(name = "MOTIVO")
	private String motivo;
		
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELA_EXENCION_FIRMA_PERMISO", 
			joinColumns=@JoinColumn(name = "ID_EXENCION"), 
			inverseJoinColumns=@JoinColumn(name = "ID_FIRMA_PERMISO_HAB"))
	private List<FirmaPermiso> listaFirmas = new ArrayList<FirmaPermiso>();
	
	public long getIdExencion() {
		return idExencion;
	}
	public void setIdExencion(long pIdExencion) {
		this.idExencion = pIdExencion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public boolean isFijo() {
		return fijo;
	}
	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado pEstado) {
		this.estado = pEstado;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String pMotivo) {
		this.motivo = pMotivo;
	}

	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}
	public void setDigestoMunicipal(DigestoMunicipal pDigestoMunicipal) {
		this.digestoMunicipal = pDigestoMunicipal;
	}

	public List<FirmaPermiso> getListaFirmas() {
		return listaFirmas;
	}
	public void setListaFirmas(List<FirmaPermiso> pListaFirmas) {
		this.listaFirmas = pListaFirmas;
	}

	public Periodicidad getPeriodicidadCuotas() {
		return periodicidadCuotas;
	}
	public void setPeriodicidadCuotas(Periodicidad pPeriodicidadCuotas) {
		this.periodicidadCuotas = pPeriodicidadCuotas;
	}

	@Override
	public String toString(){
		return this.getNombre();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((digestoMunicipal == null) ? 0 : digestoMunicipal.hashCode());
		result = prime
				* result
				+ ((periodicidadCuotas == null) ? 0 : periodicidadCuotas
						.hashCode());
		result = prime * result
				+ ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exencion other = (Exencion) obj;
		if (digestoMunicipal == null) {
			if (other.digestoMunicipal != null)
				return false;
		} else if (!digestoMunicipal.equals(other.digestoMunicipal))
			return false;
		if (periodicidadCuotas == null) {
			if (other.periodicidadCuotas != null)
				return false;
		} else if (!periodicidadCuotas.equals(other.periodicidadCuotas))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
}
