package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name="ASOC_REG_ALICUOTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_ASOC_REG_ALICUOTA")
public class AsocRegAlicuota implements Serializable{

	public static final long serialVersionUID = 3331386226531582659L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_asoc_reg_alicuota")
	@SequenceGenerator(name = "gen_id_asoc_reg_alicuota", sequenceName = "gen_id_asoc_reg_alicuota", allocationSize = 1)
	@Column(name = "ID_ASOC_REG_ALICUOTA")
	private long idAsocRegAlicuota = -1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO")
	private DocHabilitanteEspecializado docHabilitanteEspecializado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_REG_ALICUOTA")
	private RegAlicuota registroAlicuota;

	public long getIdAsocRegAlicuota() {
		return idAsocRegAlicuota;
	}

	public void setIdAsocRegAlicuota(long idAsocRegAlicuota) {
		this.idAsocRegAlicuota = idAsocRegAlicuota;
	}

	public DocHabilitanteEspecializado getDocHabilitanteEspecializado() {
		return docHabilitanteEspecializado;
	}

	public void setDocHabilitanteEspecializado(
			DocHabilitanteEspecializado docHabilitanteEspecializado) {
		this.docHabilitanteEspecializado = docHabilitanteEspecializado;
	}

	public RegAlicuota getRegistroAlicuota() {
		return registroAlicuota;
	}

	public void setRegistroAlicuota(RegAlicuota registroAlicuota) {
		this.registroAlicuota = registroAlicuota;
	}

	@Override
	public String toString() {
		return this.registroAlicuota.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idAsocRegAlicuota ^ (idAsocRegAlicuota >>> 32));
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
		AsocRegAlicuota other = (AsocRegAlicuota) obj;
		if (idAsocRegAlicuota != other.idAsocRegAlicuota)
			return false;
		return true;
	}
	
	public Integer getNumeroCuenta() {
		return this.getDocHabilitanteEspecializado().getObligacion().getNumeroCuenta();
	}
	
	//Para las clases hijas.
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return null;
	}
	
	public Object getAtributoDinamicoPorNombre(String pNombre) {
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			if (cadaAtributo.getNombre().replace(" ", "_").toUpperCase().equals(pNombre)){
				return cadaAtributo.getValor();
			}
		}
		return null;
	}
}
