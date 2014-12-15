package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Domicilio de una persona
 */
@Entity()
@Table(name="DOMICILIO")
public class Domicilio implements Serializable, Cloneable{
	public static final long serialVersionUID = 6374687589489035536L;

	@Id
	@GeneratedValue(generator="gen_id_domicilio")
	@SequenceGenerator(name="gen_id_domicilio",sequenceName="gen_id_domicilio",allocationSize = 1)
	@Column(name="ID_DOMICILIO")
	private Long idDomicilio=-1L;

	private String calle;
	private String numero;
	private String manzana;
	private String torre;
	private String escalera;
	private String piso;
	private String departamento;
	private String barrio;
	private String sector;

	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;

	@ManyToOne
	@JoinColumn(name = "ID_CALLE")
	private RfrCalle relacionCalle;

	@ManyToOne
	@JoinColumn(name = "ID_CALLE_COMIENZA")
	private RfrCalle relacionCalleComienza;

	@ManyToOne
	@JoinColumn(name = "ID_CALLE_FINALIZA")
	private RfrCalle relacionCalleFinaliza;

	@ManyToOne
	@JoinColumn(name="ID_LOCALIDAD")
	private Localidad localidad;


	public RfrCalle getRelacionCalleComienza() {
		return relacionCalleComienza;
	}
	public void setRelacionCalleComienza(RfrCalle pRelacionCalleComienza) {
		relacionCalleComienza = pRelacionCalleComienza;
	}
	public RfrCalle getRelacionCalleFinaliza() {
		return relacionCalleFinaliza;
	}
	public void setRelacionCalleFinaliza(RfrCalle pRelacionCalleFinaliza) {
		relacionCalleFinaliza = pRelacionCalleFinaliza;
	}
	/**
	 * 
	 * @return localidad a la que pertenece el domicilio
	 * @hibernate.many-to-one cascade = "save-update" 
	 * column = "ID_LOCALIDAD" not-null = "true"  
	 */
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad pLocalidad) {
		localidad = pLocalidad;
	}
	/**
	 * @return domicilio de catastro
	 * @hibernate.many-to-one 
	 * class = "com.trascender.framework.recurso.persistent.RfrCalle"
	 * column = "ID_CALLE" 
	 * cascade = "none" not-null = "false"
	 */
	public RfrCalle getRelacionCalle() {
		return relacionCalle;
	}
	public void setRelacionCalle(RfrCalle pAbstractCalle) {
		relacionCalle = pAbstractCalle;
	}



	/**
	 * 
	 * @return barrio del domicilio
	 * @hibernate.property not-null = "false" 
	 */
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String pBarrio) {
		barrio = pBarrio;
	}

	/**
	 * 
	 * @return calle del domicilio
	 * @hibernate.property not-null = "false"
	 */
	public String getCalle() {
		if (this.getRelacionCalle()==null){
			return this.calle;
		}
		else{
			return this.getRelacionCalle().toString();
		}
	}

	public void setCalle(String pCalle) {
		calle = pCalle;
	}

	/**
	 * 
	 * @return departamento del domicilio
	 * @hibernate.property not-null = "false"
	 */
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String pDepartamento) {
		departamento = pDepartamento;
	}

	/**
	 * 
	 * @return escalera, en caso que la posea, del domicilio
	 * @hibernate.property not-null = "false"
	 */
	public String getEscalera() {
		return escalera;
	}
	public void setEscalera(String pEscalera) {
		escalera = pEscalera;
	}

	/**
	 * 
	 * @return número de identificación del domicilio
	 * @hibernate.id generator-class = "increment" column = "ID_DOMICILIO"
	 * unsaved-value = "-1"
	 */
	public long getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(long pIdDomicilio) {
		idDomicilio = pIdDomicilio;
	}

	/**
	 * 
	 * @return manzana a la que pertenece el domicilio
	 * @hibernate.property  not-null = "false"
	 */
	public String getManzana() {
		return manzana;
	}
	public void setManzana(String pManzana) {
		manzana = pManzana;
	}
	
	public String getAltura(){
		String resultado = "";
		if (this.getNumero() != null && !this.getNumero().trim().isEmpty()){
			resultado += this.getNumero();
		} else if (this.relacionCalleComienza != null && relacionCalleFinaliza == null){
			resultado += (relacionCalleComienza.toString().toLowerCase().startsWith("i") ? "e " : "y ")
					+ relacionCalleComienza;
		} else if (this.relacionCalleComienza != null && relacionCalleFinaliza != null){
			resultado += "entre " + relacionCalleComienza 
					+ (relacionCalleFinaliza.toString().toLowerCase().startsWith("i") ? " e " : " y ") 
					+ relacionCalleFinaliza;
		}
		return resultado;
	}
	
	/**
	 * 
	 * @return número de la casa
	 * @hibernate.property not-null = "false"
	 */
	public String getNumero() {
		if(numero != null){
			return numero;
		}
		else{
			return "";
		}
	}
	public void setNumero(String pNumero) {
		numero = pNumero;
	}

	/**
	 * 
	 * @return piso del domicilio
	 * @hibernate.property not-null = "false"
	 * 
	 */
	public String getPiso() {
		return piso;
	}
	public void setPiso(String pPiso) {
		piso = pPiso;
	}

	/**
	 * @return sector al que pertenece el domicilio
	 * @hibernate.property not-null = "false"
	 * 
	 */
	public String getSector() {
		return sector;
	}
	public void setSector(String pSector) {
		sector = pSector;
	}

	/**
	 * 
	 * @return torre del domicilio
	 * @hibernate.property not-null = "false"
	 */
	public String getTorre() {
		return torre;
	}
	public void setTorre(String pTorre) {
		torre = pTorre;
	}

	@Override
	public String toString() {
		String resultado = (this.getCalle()!=null)?this.getCalle():"";
		resultado += " ";
		resultado += this.getAltura();
		return resultado;
	}

	public String getDomicilioCompleto(){
		String cadena= this.toString();
		cadena+=((this.getBarrio()!=null)?("<br/>Barrio: "+this.getBarrio()):"");
		return cadena;
	}
	/**
	 * 
	 * @hibernate.property column = "CODIGO_POSTAL"
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String pCodigoPostal) {
		codigoPostal = pCodigoPostal;
	}

	@Override
	public Domicilio clone() throws CloneNotSupportedException {
		Domicilio locDomicilio = new Domicilio();
		locDomicilio.setBarrio(this.barrio);
		locDomicilio.setCalle(this.calle);
		locDomicilio.setCodigoPostal(this.codigoPostal);
		locDomicilio.setDepartamento(this.departamento);
		locDomicilio.setEscalera(this.escalera);
		locDomicilio.setLocalidad(this.localidad);
		locDomicilio.setManzana(this.manzana);
		locDomicilio.setNumero(this.numero);
		locDomicilio.setPiso(this.piso);
		locDomicilio.setRelacionCalle(this.relacionCalle);
		locDomicilio.setRelacionCalleComienza(this.relacionCalleComienza);
		locDomicilio.setRelacionCalleFinaliza(this.relacionCalleFinaliza);
		locDomicilio.setSector(this.sector);
		locDomicilio.setTorre(this.torre);

		return locDomicilio;
	}
}
