package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "RELA_ZONIF_ASOC_PARCELA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AsociacionParcelaBridge implements Serializable, EntidadTrascender {
	
	private static final long serialVersionUID = 1320641719669412824L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_rela_zon_asoc_par")
	@SequenceGenerator(name = "gen_id_rela_zon_asoc_par", sequenceName = "gen_id_rela_zon_asoc_par",allocationSize = 1)
	@Column(name="ID_RELA_ZON_ASOC_PAR")
	private long idAsociacionParcela=-1;
	
	public abstract List<Parcela> getListaParcelas();
	
	@ManyToOne
	@JoinColumn(name = "ID_ZONA")
	private Zona zona;
	
	public long getIdAsociacionParcela() {
		return idAsociacionParcela;
	}
	
	public void setIdAsociacionParcela(long idAsociacionParcela) {
		this.idAsociacionParcela = idAsociacionParcela;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	/**
	 * Un texto descriptivo del tipo de asociación
	 * puede ser utilizado para las tablas 
	 * @return
	 */
	public abstract String getTipo();
	public abstract String getNombre();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idAsociacionParcela ^ (idAsociacionParcela >>> 32));
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
		AsociacionParcelaBridge other = (AsociacionParcelaBridge) obj;
		if (idAsociacionParcela == -1 && other.idAsociacionParcela == -1)
			return this == other;
		if (idAsociacionParcela != other.idAsociacionParcela)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return zona != null ? zona.toString() : "Sin zona";
	}
	
	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	public long getLlaveUsuarioAuditoria() {
		return 0;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
	}

	public String getComentarioAuditoria() {
		return null;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
	}

	public long getIdEntidad() {
		return this.idAsociacionParcela;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idAsociacionParcela";
	}

	public boolean isAuditable() {
		return true;
	}
}
