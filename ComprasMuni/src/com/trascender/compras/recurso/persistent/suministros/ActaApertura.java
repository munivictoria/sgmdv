package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name="ACTA_APERTURA")
public class ActaApertura implements Serializable{

	public static final long serialVersionUID = -3479295673860962283L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name="GEN_ID_ACTA_APERTURA", sequenceName="GEN_ID_ACTA_APERTURA")
	@GeneratedValue(generator="GEN_ID_ACTA_APERTURA", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ACTA_APERTURA")
	private Long idActaApertura = -1l;
	
	private String lugar;
	
	@Column(name="REGISTRO_ESCRITO")
	private String registroEscrito;
	
	@ManyToOne
	@JoinColumn(name="ID_CONTRATACION", nullable=false)
	private Contratacion contratacion;
	
	@OneToMany(mappedBy="actaApertura", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<RepresentanteActaApertura> listaRepresentantes = new ArrayList<RepresentanteActaApertura>();
	
	/**
	 * Agrega un representante del acta.
	 * @param pRepresentante
	 * @return
	 */
	public boolean addRepresentante(RepresentanteActaApertura pRepresentante){
		if(pRepresentante != null && !this.listaRepresentantes.contains(pRepresentante)){
			pRepresentante.setActaApertura(this);
			this.listaRepresentantes.add(pRepresentante);
			return true;
		}
		return false;
	}
	
	public Long getIdActaApertura() {
		return idActaApertura;
	}

	public void setIdActaApertura(Long idActaApertura) {
		this.idActaApertura = idActaApertura;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<RepresentanteActaApertura> getListaRepresentantes() {
		return listaRepresentantes;
	}

	public void setListaRepresentantes(
			List<RepresentanteActaApertura> listaRepresentantes) {
		this.listaRepresentantes = listaRepresentantes;
	}

	public Contratacion getContratacion() {
		return contratacion;
	}

	public void setContratacion(Contratacion contratacion) {
		this.contratacion = contratacion;
	}

	public String getRegistroEscrito() {
		return registroEscrito;
	}

	public void setRegistroEscrito(String registroEscrito) {
		this.registroEscrito = registroEscrito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		result = prime * result
				+ ((contratacion == null) ? 0 : contratacion.hashCode());
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
		ActaApertura other = (ActaApertura) obj;
		if (contratacion == null) {
			if (other.contratacion != null)
				return false;
		} else if (!contratacion.equals(other.contratacion))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Apertura licitacion numero: " + this.contratacion.getNumero();
	}
	

}
