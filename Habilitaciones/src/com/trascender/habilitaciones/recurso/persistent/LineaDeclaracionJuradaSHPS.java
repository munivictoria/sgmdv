
package com.trascender.habilitaciones.recurso.persistent;

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

import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;

/**
 * 
 * @author nico
 * 
 */

@Entity
@Table(name = "LINEA_DDJJ_SHPS")
public class LineaDeclaracionJuradaSHPS implements Serializable {

	private static final long serialVersionUID = -9036308217819814046L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_linea_ddjj_shps")
	@SequenceGenerator(name = "gen_id_linea_ddjj_shps", sequenceName = "gen_id_linea_ddjj_shps", allocationSize = 1)
	@Column(name = "ID_LINEA_DDJJ_SHPS")
	private Long idLineaDDJJSHPS = -1l;

	@ManyToOne
	@JoinColumn(name = "ID_RUBRO")
	private Rubro rubro;

	@Column(name = "IMPORTE")
	private Double importe;

	@ManyToOne
	@JoinColumn(name = "ID_DECLARACION_JURADA_SHPS")
	private DeclaracionJuradaSHPS declaracionJuradaSHPS;

	public Long getIdLineaDDJJSHPS() {
		return idLineaDDJJSHPS;
	}

	public void setIdLineaDDJJSHPS(Long idLineaDDJJSHPS) {
		this.idLineaDDJJSHPS = idLineaDDJJSHPS;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public DeclaracionJuradaSHPS getDeclaracionJuradaSHPS() {
		return declaracionJuradaSHPS;
	}

	public void setDeclaracionJuradaSHPS(DeclaracionJuradaSHPS declaracionJuradaSHPS) {
		this.declaracionJuradaSHPS = declaracionJuradaSHPS;
	}
	
	public String getNroInscripcion() {
		return this.getDeclaracionJuradaSHPS().getDocHabilitanteEspecializado().getObligacion().getNumeroInscripcion();
	}
}