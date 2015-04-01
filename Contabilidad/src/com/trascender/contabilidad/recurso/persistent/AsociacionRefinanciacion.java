package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.saic.recurso.persistent.ParametroAsociacion;

@Entity
@Table(name = "ASOCIACION_REFINANCIACION")
public class AsociacionRefinanciacion implements Serializable {

	public static final long serialVersionUID = -2353630652808409783L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_asoc_refinanciacion")
	@SequenceGenerator(name = "gen_id_asoc_refinanciacion", sequenceName = "gen_id_asoc_refinanciacion",allocationSize = 1)
	@Column(name="ID_ASOCIACION_REFINANCIACION")
	private long idAsociacionRefinanciacion = -1;
	
//	@ManyToOne
//	@JoinColumn(name = "ID_PERIODO")
//	private Periodo periodo;
	
	@OneToMany(mappedBy = "asociacionRefinanciacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CuentaRefinanciacion> listaCuentaRefinanciacion = new HashSet<CuentaRefinanciacion>();
	
	public long getIdAsociacionRefinanciacion() {
		return idAsociacionRefinanciacion;
	}
	
	public void setIdAsociacionRefinanciacion(long idAsociacionRefinanciacion) {
		this.idAsociacionRefinanciacion = idAsociacionRefinanciacion;
	}
	
//	public Periodo getPeriodo() {
//		return periodo;
//	}
//	
//	public void setPeriodo(Periodo periodo) {
//		this.periodo = periodo;
//	}
	
	
	public Set<CuentaRefinanciacion> getListaCuentaRefinanciacion() {
		return listaCuentaRefinanciacion;
	}

	public void setListaCuentaRefinanciacion(
			Set<CuentaRefinanciacion> listaCuentaRefinanciacion) {
		this.listaCuentaRefinanciacion = listaCuentaRefinanciacion;
	}

	public Set<ParametroAsociacion> getListaParametrosAsociacion() {
		Set<ParametroAsociacion> locListaRetorno = new HashSet<ParametroAsociacion>();
		
		for (CuentaRefinanciacion unaCuentaRefinanciacion : this.listaCuentaRefinanciacion) {
			locListaRetorno.add(unaCuentaRefinanciacion.getParametroAsociacion());
		}
		
		return locListaRetorno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idAsociacionRefinanciacion ^ (idAsociacionRefinanciacion >>> 32));
		result = prime
				* result
				+ ((listaCuentaRefinanciacion == null) ? 0
						: listaCuentaRefinanciacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AsociacionRefinanciacion other = (AsociacionRefinanciacion) obj;
		if (idAsociacionRefinanciacion != other.idAsociacionRefinanciacion) {
			return false;
		}
		if (listaCuentaRefinanciacion == null) {
			if (other.listaCuentaRefinanciacion != null) {
				return false;
			}
		} else if (!listaCuentaRefinanciacion
				.equals(other.listaCuentaRefinanciacion)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AsociacionRefinanciacion [idAsociacionRefinanciacion="
				+ idAsociacionRefinanciacion + ", listaCuentaRefinanciacion="
				+ listaCuentaRefinanciacion + "]";
	}
}
