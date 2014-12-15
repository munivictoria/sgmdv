package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Calendario.EstadoCalendario;
import com.trascender.framework.util.Periodicidad;

/**
 * Esta clase reemplaza al enum TipoObligacion, porque el usuario
 * podra dar de alta tipos de obligaciones por su cuenta.
 *
 */
@Entity
@Table(name = "TIPO_OBLIGACION")
public class TipoObligacion implements Serializable{

	public static final long serialVersionUID = 269064934710786479L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_obligacion")
	@SequenceGenerator(name = "gen_id_tipo_obligacion", sequenceName = "gen_id_tipo_obligacion",allocationSize = 1)
	@Column(name="ID_TIPO_OBLIGACION")
	private long idTipoObligacion = -1;
	
	private String nombre;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="RELA_TIPO_OB_CALENDARIO", joinColumns=@JoinColumn(name="ID_TIPO_OBLIGACION"), 
//												inverseJoinColumns=@JoinColumn(name="ID_CALENDARIO"))
//	private Set<CalendarioMunicipal> listaCalendarioMunicipal = new HashSet<CalendarioMunicipal>();
	
//	/**
//	 * Al agregar un calendario nuevo desactiva el anterior e inserta el nuevo ACTIVADO
//	 * @param pCalendario
//	 * @return
//	 */
//	public boolean addCalendarioMunicipal(CalendarioMunicipal pCalendario){
//		for(CalendarioMunicipal cadaCalendario : this.listaCalendarioMunicipal){
//			if(cadaCalendario.getPeriodicidad().equals(pCalendario.getPeriodicidad()) && 
//					cadaCalendario.getAnio().equals(pCalendario.getAnio()))
//			cadaCalendario.setEstado(EstadoCalendario.INACTIVO);
//		}
//		
//		pCalendario.setEstado(EstadoCalendario.ACTIVO);
//		this.listaCalendarioMunicipal.add(pCalendario);
//		
//		return true;
//	}
	
//	/**
//	 * @param pPeriodicidad
//	 * @return
//	 */
//	public Calendario getCalendarioActivo(Periodicidad pPeriodicidad, Integer pAnio){
//		for(Calendario cadaCalendario : this.listaCalendarioMunicipal){
//			if(cadaCalendario.getPeriodicidad().equals(pPeriodicidad) 
//					&& cadaCalendario.getAnio().equals(pAnio)
//					&& cadaCalendario.getEstado().equals(EstadoCalendario.ACTIVO)){
//				return cadaCalendario;
//			}
//		}
//		
//		return null;
//	}
	
	public long getIdTipoObligacion() {
		return idTipoObligacion;
	}

	public void setIdTipoObligacion(long idTipoObligacion) {
		this.idTipoObligacion = idTipoObligacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
//	public Set<CalendarioMunicipal> getListaCalendarioMunicipal() {
//		return listaCalendarioMunicipal;
//	}
//
//	public void setListaCalendarioMunicipal(
//			Set<CalendarioMunicipal> listaCalendarioMunicipal) {
//		this.listaCalendarioMunicipal = listaCalendarioMunicipal;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoObligacion other = (TipoObligacion) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return nombre;
	}
}
