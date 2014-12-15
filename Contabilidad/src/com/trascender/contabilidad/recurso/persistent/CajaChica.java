package com.trascender.contabilidad.recurso.persistent;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "CAJA_CHICA")
public class CajaChica  implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 2620259495788032417L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_caja_chica")
	@SequenceGenerator(name = "gen_id_caja_chica", sequenceName = "gen_id_caja_chica",allocationSize = 1)
	@Column(name="ID_CAJA_CHICA")
	private long idCajaChica=-1;
	
	private String nombre;
	
	@Column(name = "IMPORTE_REPOSICION")
	private double importeReposicion;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "cajaChica", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaChica> movimientos;
	
	public long getIdCajaChica() {
		return idCajaChica;
	}
	
	public void setIdCajaChica(long idCajaChica) {
		this.idCajaChica = idCajaChica;
	}

	public double getImporteReposicion() {
		return importeReposicion;
	}

	public void setImporteReposicion(double importeReposicion) {
		this.importeReposicion = importeReposicion;
	}

	public Set<MovimientoCajaChica> getMovimientos() {
		return movimientos;
	}
	/**
	 * @param movimientos the movimientos to set
	 */
	public void setMovimientos(Set<MovimientoCajaChica> movimientos) {
		this.movimientos = movimientos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idCajaChica == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCajaChica ^ (idCajaChica >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		final CajaChica other = (CajaChica) obj;
		if (idCajaChica != other.idCajaChica) {
			return false;
		}
		return true;
	}
	@Override
	public String toString(){
		return this.nombre;
	}
}
