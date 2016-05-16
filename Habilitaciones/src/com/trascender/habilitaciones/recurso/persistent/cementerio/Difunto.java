package com.trascender.habilitaciones.recurso.persistent.cementerio;

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
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
public class Difunto implements Serializable{

	public static final long serialVersionUID = -4217529186044292892L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_id_difunto")
	@SequenceGenerator(name="gen_id_difunto", sequenceName="gen_id_difunto", allocationSize=1)
	@Column(name="ID_DIFUNTO")
	private long idDifunto = -1;
	
	@Column(name="FECHA_DECESO")
	private Date fechaDeceso;
	
	private String causa;
	
	private boolean inmunoinfecciosa;
	
	private boolean reducido;
	
	private boolean cremado;
	
	@ManyToOne
	@JoinColumn(name="ID_PARCELA_CEMENTERIO")
	private ParcelaCementerio parcelaCementerio;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private PersonaFisica persona;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	private String difunto;
	
	public String getDifunto() {
		return difunto;
	}

	public void setDifunto(String difunto) {
		this.difunto = difunto;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdDifunto());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	public long getIdDifunto() {
		return idDifunto;
	}

	public void setIdDifunto(long idDifunto) {
		this.idDifunto = idDifunto;
	}

	public Date getFechaDeceso() {
		return fechaDeceso;
	}

	public void setFechaDeceso(Date fechaDeceso) {
		this.fechaDeceso = fechaDeceso;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	public boolean isInmunoinfecciosa() {
		return inmunoinfecciosa;
	}

	public void setInmunoinfecciosa(boolean inmunoinfecciosa) {
		this.inmunoinfecciosa = inmunoinfecciosa;
	}

	public boolean isReducido() {
		return reducido;
	}

	public void setReducido(boolean reducido) {
		this.reducido = reducido;
	}

	public boolean isCremado() {
		return cremado;
	}

	public void setCremado(boolean cremado) {
		this.cremado = cremado;
	}

	public ParcelaCementerio getParcelaCementerio() {
		return parcelaCementerio;
	}

	public void setParcelaCementerio(ParcelaCementerio parcelaCementerio) {
		this.parcelaCementerio = parcelaCementerio;
	}

	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdDifunto());
		}
	}
}
