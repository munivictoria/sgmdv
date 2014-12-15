package com.trascender.catastro.recurso.persistent;

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

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "RELA_PARCELA_POR_CUADRA")
public class ParcelaPorCuadra implements Serializable, AuditoriaIndirecta {

	private static final long serialVersionUID = 2530099039740176436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_rela_parcela_por_cuadra")
	@SequenceGenerator(name ="gen_id_rela_parcela_por_cuadra", sequenceName = "gen_id_rela_parcela_por_cuadra", allocationSize = 1)
	@Column(name = "ID_RELA_PARCELA_POR_CUADRA")
	private long idParcelaPorCuadra=-1;

	@ManyToOne
	@JoinColumn(name = "ID_PARCELA")
	private Parcela parcela;

	@ManyToOne
	@JoinColumn(name = "ID_CUADRA")
	private Cuadra cuadra;

	@Column(name = "METROS_POR_CUADRA")
	private Double metrosPorCuadra = 0d;

	public Double getMetrosPorCuadra() {
		return metrosPorCuadra;
	}

	public void setMetrosPorCuadra(Double metrosPorCuadra) {
		this.metrosPorCuadra = metrosPorCuadra;
	}

	public Cuadra getCuadra() {
		return cuadra;
	}

	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public long getIdParcelaPorCuadra() {
		return idParcelaPorCuadra;
	}

	public void setIdParcelaPorCuadra(long idParcelaPorCuadra) {
		this.idParcelaPorCuadra = idParcelaPorCuadra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idParcelaPorCuadra ^ (idParcelaPorCuadra >>> 32));
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
		ParcelaPorCuadra other = (ParcelaPorCuadra) obj;
		if (cuadra == null) {
			if (other.cuadra != null)
				return false;
		} else if (!cuadra.equals(other.cuadra))
			return false;
		if (idParcelaPorCuadra != other.idParcelaPorCuadra)
			return false;
		if (parcela == null) {
			if (other.parcela != null)
				return false;
		} else if (!parcela.equals(other.parcela))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Metros en " + this.cuadra + ": " + this.metrosPorCuadra;
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.parcela;
	}

	public String getNombrePropiedad() {
		return "Frente en " + this.cuadra;
	}

	public boolean concatenaNombre() {
		return true;
	}

}
