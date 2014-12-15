package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FICHA_SOCIAL")
public class FichaSocial implements Serializable{

	public static final long serialVersionUID = 8164856817073295495L;

	@Id
	@SequenceGenerator(allocationSize = 1, name="gen_id_ficha_social", sequenceName="gen_id_ficha_social")
	@GeneratedValue(generator="gen_id_ficha_social", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_FICHA_SOCIAL")
	private long idFichaSocial = -1;
	
	private Date fecha = new Date();
	private Integer numero;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="ID_BENEFICIARIO")
	private Beneficiario titular;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true,fetch=FetchType.LAZY )
	@JoinTable(name="RELA_BENEF_FICHA_SOC", joinColumns={@JoinColumn(name="ID_FICHA_SOCIAL")}, inverseJoinColumns=@JoinColumn(name="ID_BENEFICIARIO"))
	private Set<Beneficiario> grupoFamiliar = new HashSet<Beneficiario>();
	
	@Embedded
	private AspectoHabitacional aspectoHabitacional;
	@Embedded
	private AspectoEconomico aspectoEconomico;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="fichaSocial", fetch=FetchType.LAZY)
	private Set<SolicitudBeneficio> listaSolicitudBeneficio = new HashSet<SolicitudBeneficio>();
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void addFamiliar(Beneficiario pBeneficario) {
		this.grupoFamiliar.add(pBeneficario);
	}
	
	public void addSolicitudBeneficio(SolicitudBeneficio pSolicitudBeneficio){
		pSolicitudBeneficio.setFichaSocial(this);
		this.listaSolicitudBeneficio.add(pSolicitudBeneficio);
	}
	
	public long getIdFichaSocial() {
		return idFichaSocial;
	}
	public void setIdFichaSocial(long idFichaSocial) {
		this.idFichaSocial = idFichaSocial;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Beneficiario getTitular() {
		return titular;
	}
	public void setTitular(Beneficiario titular) {
		this.titular = titular;
	}
	public AspectoHabitacional getAspectoHabitacional() {
		return aspectoHabitacional;
	}
	public void setAspectoHabitacional(AspectoHabitacional aspectoHabitacional) {
		this.aspectoHabitacional = aspectoHabitacional;
	}
	public AspectoEconomico getAspectoEconomico() {
		return aspectoEconomico;
	}
	public void setAspectoEconomico(AspectoEconomico aspectoEconomico) {
		this.aspectoEconomico = aspectoEconomico;
	}
	public Set<Beneficiario> getGrupoFamiliar() {
		return grupoFamiliar;
	}
	public void setGrupoFamiliar(Set<Beneficiario> grupoFamiliar) {
		this.grupoFamiliar = grupoFamiliar;
	}
	public Set<SolicitudBeneficio> getListaSolicitudBeneficio() {
		return listaSolicitudBeneficio;
	}
	public void setListaSolicitudBeneficio(
			Set<SolicitudBeneficio> listaSolicitudBeneficio) {
		this.listaSolicitudBeneficio = listaSolicitudBeneficio;
	}
	
	@Override
	public String toString(){
		return "Titular: "+titular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idFichaSocial ^ (idFichaSocial >>> 32));
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
		FichaSocial other = (FichaSocial) obj;
		if (idFichaSocial != other.idFichaSocial)
			return false;
		return true;
	}
	
}
