package com.trascender.framework.recurso.persistent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;


/**
 * @hibernate.class table = "PERSONA_JURIDICA"
 */
@Entity
@Table(name="PERSONA_JURIDICA")
public class PersonaJuridica extends Persona implements EntidadTrascender {

	public static final long serialVersionUID = 4264401969927094878L;
	
	public enum TipoSocietario{
		AGRUPACIONES_DE_COLABORACION_EMPRESARIA, ASOCIACION_CON_PARTICIPACION_ESTATAL_MAYORITARIA, 
		CONDOMINIO, CONSORCIO_DE_PROPIETARIOS, CONSORCIOS_DE_COOPERACION, 
		COOPERADORA, COOPERATIVA, COOPERATIVA_EFECTORA,	DIRECCION_ADMINISTRATIVA_ESTATAL, 
		ECONOMIA_MIXTA, EMPRESA_DEL_ESTADO,	ENTIDADES_DE_DERECHO_PUBLICO_NO_ESTATAL, FIDEICOMISO,
		FIDEICOMISO_FINANCIERO, FONDO_COMUN_DE_INVERSION, FUNDACION, 
		INSTITUTO_DE_VIDA_CONSAGRADA, MUTUAL, ORGANISMO_PUBLICO, ORGANISMO_PUBLICO_INTERNACIONAL, 
		OTRAS_ENTIDADES_CIVILES, OTRAS_SOCIEDADES, SOCIEDAD_ANONIMA, SOCIEDAD_BINACIONAL_FUERA_DE_JURISDICCION, 
		SOCIEDAD_COLECTIVA, SOCIEDAD_DE_CAPITAL_E_INDUSTRIA, SOCIEDAD_DE_GARANTIA_RECIPROCA, 
		SOCIEDAD_DE_HECHO, SOCIEDAD_DE_RESPONSABILIDAD_LIMITADA, SOCIEDAD_EN_COMANDITA_POR_ACCIONES, 
		SOCIEDAD_EN_COMANDITA_SIMPLE, SOCIEDAD_EN_FORMACION, SUCURSAL_EMPRESA_EXTRANJERA, UNION_TRANSITORIA;
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	public enum OrganismoEmisor{
		INSPECCION_GENERAL_DE_JUSTICIA, REGISTRO_PUBLICO_DE_COMERCIO, INSTITUTO_NACIONAL_DE_ACCION_COOPERATIVA_Y_MUTUAL, 
		OTROS_ORGANISMOS_DE_CONTROL, INSPECCION_GENERAL_DE_PERSONAS_JURIDICAS, DIRECCION_DE_LAS_PERSONAS_JURIDICAS;
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Column(name="RAZON_SOCIAL", nullable=false)
	private String razonSocial;
	
	@Column(name="NUMERO_SOCIEDAD")
	private String numeroSociedad;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "personaJuridica", orphanRemoval = true)
	private Set<Socio> listaSocios;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_SOCIETARIO")
	private TipoSocietario tipoSocietario;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ORGANISMO_EMISOR")
	private OrganismoEmisor organismoEmisor;
	
	private String nacionalidad;
	
	@Column(name = "NOMBRE_FANTASIA")
	private String nombreFantasia;
	
	@Column(name = "NUMERO_IIBB")
	private String numeroIngresosBrutos;
	
	@Column(name = "NUMERO_CONVENIO_MULTILATERAL")
	private String numeroConvenioMultilateral;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdPersona());
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
	
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String pNombreFantasia) {
		nombreFantasia = pNombreFantasia;
	}

	public String getNumeroIngresosBrutos() {
		return numeroIngresosBrutos;
	}

	public void setNumeroIngresosBrutos(String pNumeroIngresosBrutos) {
		numeroIngresosBrutos = pNumeroIngresosBrutos;
	}

	public String getNumeroConvenioMultilateral() {
		return numeroConvenioMultilateral;
	}

	public void setNumeroConvenioMultilateral(String pNumeroConvenioMultilateral) {
		numeroConvenioMultilateral = pNumeroConvenioMultilateral;
	}

	public String getNacionalidad() {
		return Util.capitalize(nacionalidad);
	}

	public void setNacionalidad(String pNacionalidad) {
		nacionalidad = pNacionalidad;
	}

	public long getIdPersonaJuridica(){
		return this.getIdPersona();
	}
	
	public void setIdPersonaJuridica(long pIdPersonaJuridica){
		this.setIdPersona(pIdPersonaJuridica);
	}
	
	public void setCuit(String pCuil){
		this.setCuim(pCuil);
	}
	
	public String getCuit(){
		return this.getCuim();
	}

	public String getRazonSocial() {
		if(razonSocial != null){
			return Util.capitalize(razonSocial.trim());
		}
		return razonSocial;
	}

	public void setRazonSocial(String pRazonSocial) {
			razonSocial = (pRazonSocial != null)?Util.capitalize(pRazonSocial.trim()):null;
	}

	
	public String getNumeroSociedad() {
		return numeroSociedad;
	}

	public void setNumeroSociedad(String pNumeroSociedad) {
		numeroSociedad = pNumeroSociedad;
	}

	public Set<Socio> getListaSocios() {
		return listaSocios;
	}

	public void setListaSocios(Set<Socio> pListaSocios) {
		listaSocios = pListaSocios;
	}

	public TipoSocietario getTipoSocietario() {
		return tipoSocietario;
	}

	public void setTipoSocietario(TipoSocietario pTipoSocietario) {
		tipoSocietario = pTipoSocietario;
	}

	public OrganismoEmisor getOrganismoEmisor() {
		return organismoEmisor;
	}

	public void setOrganismoEmisor(OrganismoEmisor pOrganismoEmisor) {
		organismoEmisor = pOrganismoEmisor;
	}

	public String toString() {	
		return (this.razonSocial==null)?"":Util.capitalize(this.razonSocial)+" "+((this.getCuim()!=null)?("["+this.getCuim()+"]"):"");
	}

	@Override
	public String getToStringCompleto() {
		return this.toString();
	}

	@Override
	public String getDenominacion() {
		return this.getRazonSocial();
	}
	
	// ********************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getIdEntidad() {
		return this.getIdPersona();
	}

	public String getNombrePropiedadId() {
		return "idPersona";
	}

	public boolean isAuditable() {
		return true;
	}
}
