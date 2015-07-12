
package com.trascender.framework.recurso.persistent.reporteDinamico;

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

@Entity
@Table(name = "OPCION_PARAMETRO_REPORTE")
public class OpcionParametroReporte implements Serializable {

	private static final long serialVersionUID = 4652943535414954461L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_opcion_rp")
	@SequenceGenerator(name = "gen_id_opcion_rp", sequenceName = "gen_id_opcion_rp", allocationSize = 1)
	@Column(name = "ID_OPCION_RP")
	private long idOpcionParametroReporte = -1;

	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_PARAMETRO_REPORTE")
	private ParametroReporte parametroReporte;

	public long getIdOpcionParametroReporte() {
		return idOpcionParametroReporte;
	}

	public void setIdOpcionParametroReporte(long pIdOpcionParametroReporte) {
		idOpcionParametroReporte = pIdOpcionParametroReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ParametroReporte getParametroReporte() {
		return parametroReporte;
	}

	public void setParametroReporte(ParametroReporte pParametroReporte) {
		parametroReporte = pParametroReporte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idOpcionParametroReporte ^ (idOpcionParametroReporte >>> 32));

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
		OpcionParametroReporte other = (OpcionParametroReporte) obj;
		if(idOpcionParametroReporte != other.idOpcionParametroReporte) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}