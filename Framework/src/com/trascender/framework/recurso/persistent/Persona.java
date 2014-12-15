package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

/**
 * Representación abstracta de una persona
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PERSONA")
public abstract class Persona implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_persona")
	@SequenceGenerator(name="generador_persona", sequenceName = "gen_id_persona",allocationSize = 1)
	@Column(name = "ID_PERSONA")
	private long idPersona=-1;
	
	@Column(nullable=false)
	private String cuim;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_DOMICILIO", nullable=false)
	private Domicilio domicilio = new Domicilio();
	
	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.ACTIVO;
	
	private String telefono;
	private String celular;
	private String email;
	
	@Column(name="CANTIDAD_PROPIEDADES", nullable=false)
	private Integer cantidadPropiedades=0;
	
	@Column(name="CANTIDAD_VEHICULOS", nullable=false)
	private Integer cantidadVehiculos=0;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_DOMICILIO_POSTAL", nullable=false)
	private Domicilio domicilioPostal = new Domicilio();
	
	public abstract void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico);
	
	public abstract List<AtributoDinamico<?>> getListaAtributosDinamicos();
	
	public abstract void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos);
	
	public abstract String getToStringCompleto();
	/**
	 * Estados posibles {ELIMINADO, ACTIVO}
	 * @author jsantacruz
	 */
	public enum Estado{ELIMINADO,ACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
	
	};
	

	public String getCelular() {
		return celular;
	}

	public void setCelular(String pCelular) {
		celular = pCelular;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String pEmail) {
		email = pEmail;
	}
	
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String pTelefono) {
		telefono = pTelefono;
	}

	/**
	 * 
	 * @return Clave única de identificación municipal
	 * @hibernate.property not-null = "true"
	 */
	public String getCuim() {
		return cuim;
	}
	
	protected void setCuim(String pCuim) {
		cuim = pCuim;
	}
	
	/**
	 * @return número de identificación único de la persona
	 */
	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long pIdPersona) {
		idPersona = pIdPersona;
	}

	/**
	 * Devuelve el domicilio FISICO de la persona
	 */	
	public Domicilio getDomicilio() {
		if (this.domicilio==null){
			return null;
		}
		return domicilio;
	}

	public void setDomicilio(Domicilio pDomicilio) {
		domicilio = pDomicilio;
	}

	/**
	 * 
	 * @return retorna el estado de la persona
	 */
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}
	
	/**
	 * Esto viola completamente el paradigma de objetos
	 * @return
	 */
//	public String getDenominacion(){
//		if (this instanceof PersonaFisica){
//			return ((PersonaFisica)this).getNombreCompleto();
//		} else {
//			return ((PersonaJuridica) this).getRazonSocial();
//		}
//	}
	
	//Esto es lo que va papa.
	public abstract String getDenominacion();


	@Override
	public boolean equals(Object pObj) {
		if (this==pObj) {
			return true;
		}
		else if (!(pObj instanceof Persona)) {
			return false;
		}
		Persona locPersona=(Persona)pObj;
		if (this.getIdPersona()==locPersona.getIdPersona()){
			return true;
		}
		else{
			return false;
		}
	}

	
	public Integer getCantidadPropiedades() {
		if (this.cantidadPropiedades == null){
			this.cantidadPropiedades = 0;
		}
		return cantidadPropiedades;
	}

	public void setCantidadPropiedades(Integer pCantidadPropiedades) {
		cantidadPropiedades = pCantidadPropiedades;
	}
	
	public Integer getCantidadVehiculos() {
		return cantidadVehiculos;
	}

	public void setCantidadVehiculos(Integer pCantidadVehiculos) {
		cantidadVehiculos = pCantidadVehiculos;
	}

	/**
	 * Se entiende, es el domicilio al cual la persona quiere recibir facturas, documentos, etc.
	 */
	public Domicilio getDomicilioPostal() {
		return domicilioPostal;
	}

	public void setDomicilioPostal(Domicilio pDomicilioPostal) {
		domicilioPostal = pDomicilioPostal;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdPersona());
		}
	}
}