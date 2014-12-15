package com.trascender.framework.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Persona.Estado;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroPersonaFisica extends FiltroAbstracto<PersonaFisica>{
	public FiltroPersonaFisica() {
	}
	public FiltroPersonaFisica(String pCuil, String pNombre, String pApellido,
			Domicilio pDomicilioPostal, Date pFechaNacimiento,
			TipoDocumento pTipoDocumento, String pNumeroDocumento,
			Integer pEdad, String pSexo, String pTelefono, String pCelular,
			String pEmail, Estado pEstado,
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		cuil = pCuil;
		nombre = pNombre;
		apellido = pApellido;
		domicilioPostal = pDomicilioPostal;
		fechaNacimiento = pFechaNacimiento;
		tipoDocumento = pTipoDocumento;
		numeroDocumento = pNumeroDocumento;
		edad = pEdad;
		sexo = pSexo;
		telefono = pTelefono;
		celular = pCelular;
		email = pEmail;
		estado = pEstado;
		listaAtributoDinamico = pListaAtributoDinamico;
	}
	private static final long serialVersionUID = -2071969888538580666L;
	
	private String cuil;
	private String nombre;
	private String apellido;
	private Domicilio domicilioPostal;
	private Date fechaNacimiento;
	private PersonaFisica.TipoDocumento tipoDocumento;
	private String numeroDocumento;
	private Integer edad;
	private String sexo;
	private String telefono;
	private String celular;
	private String email;
	private Persona.Estado estado;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String pCuil) {
		cuil = pCuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String pApellido) {
		apellido = pApellido;
	}
	public Domicilio getDomicilioPostal() {
		return domicilioPostal;
	}
	public void setDomicilioPostal(Domicilio pDomicilioPostal) {
		domicilioPostal = pDomicilioPostal;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date pFechaNacimiento) {
		fechaNacimiento = pFechaNacimiento;
	}
	public PersonaFisica.TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(PersonaFisica.TipoDocumento pTipoDocumento) {
		tipoDocumento = pTipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String pNumeroDocumento) {
		numeroDocumento = pNumeroDocumento;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer pEdad) {
		edad = pEdad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String pSexo) {
		sexo = pSexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String pTelefono) {
		telefono = pTelefono;
	}
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
	public com.trascender.framework.recurso.persistent.Persona.Estado getEstado() {
		return estado;
	}
	public void setEstado(
			com.trascender.framework.recurso.persistent.Persona.Estado pEstado) {
		estado = pEstado;
	}
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		listaAtributoDinamico = pListaAtributoDinamico;
	}
	
}
