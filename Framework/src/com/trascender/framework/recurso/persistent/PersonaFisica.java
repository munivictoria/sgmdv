
package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * Representa una persona física
 * 
 * @hibernate.class table = "PERSONA_FISICA"
 */

@Entity
@Table(name = "PERSONA_FISICA")
public class PersonaFisica extends Persona implements Serializable, EntidadTrascender {

	public static final String[] listaAtributosConfigurables = {"nombre", "apellido"};
	public static final long serialVersionUID = 6133400549322171158L;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@Column(name = "NUMERO_DOCUMENTO", nullable = false)
	private String numeroDocumento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	private Integer edad;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CIVIL")
	private EstadoCivil estadoCivil;

	@Column
	private boolean jubilado = false;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.getIdPersona());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for(AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos) {
			if(cadaAtributo.getValor() != null) {
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	/**
	 * Posibles estos Civiles {SOLTERO,CASADO,DIVORCIADO,VIUDO,SEPARADO,NO_INFORMADO}
	 * 
	 * @author jsantacruz
	 */
	public enum EstadoCivil {
		SOLTERO, CASADO, DIVORCIADO, VIUDO, SEPARADO, NO_INFORMADO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};

	/**
	 * Sexos posibles {MASCULINO, FEMENINO}
	 * 
	 * @author jsantacruz
	 */
	public enum Sexo {
		MASCULINO, FEMENINO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};

	/**
	 * Tipos de documentos posibles {DNI,LC,LE,CI,PS,EN_TRAMITE}
	 * 
	 * @author jsantacruz
	 */
	public enum TipoDocumento {
		DNI, LC, LE, CI, PS, EN_TRAMITE
	};

	/**
	 * 
	 * @hibernate.property column = "JUBILADO"
	 */
	public boolean isJubilado() {
		return jubilado;
	}

	public void setJubilado(boolean pJubilado) {
		jubilado = pJubilado;
	}

	/**
	 * @return estado civil de la persona
	 * @hibernate.property not-null = "false" column = "ESTADO_CIVIL" type = "com.trascender.framework.util.enumerations.PersonaFisicaEstadoCivil"
	 * 
	 */
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil pEstadoCivil) {
		estadoCivil = pEstadoCivil;
	}

	/**
	 * 
	 * @return tipo de documento que posee la persona física
	 * @hibernate.property column = "TIPO_DOCUMENTO" not-null = "true" type = "com.trascender.framework.util.enumerations.PersonaFisicaTipoDocumento"
	 * 
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento pTipoDocumento) {
		tipoDocumento = pTipoDocumento;
	}

	/**
	 * @return apellido de esta persona
	 * @hibernate.property not-null = "true"
	 */
	public String getApellido() {
		return Util.capitalize(apellido);
	}

	public void setApellido(String pApellido) {
		if(pApellido != null) {
			apellido = Util.capitalize(pApellido.trim());
		} else {
			apellido = null;
		}
	}

	/**
	 * @return cuil de la persona
	 */
	public String getCuil() {
		return this.getCuim();
	}

	public void setCuil(String pCuil) {
		this.setCuim(pCuil);
	}

	/**
	 * @return edad de la persona referenciada
	 */
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer pEdad) {
		edad = pEdad;
	}

	/**
	 * @return fecha de nacimiento de la persona referenciada
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date pFechaNacimiento) {
		fechaNacimiento = pFechaNacimiento;
	}

	/**
	 * @return nombre de la persona
	 */
	public String getNombre() {
		return Util.capitalize(nombre);
	}

	public void setNombre(String pNombre) {
		if(pNombre != null) {
			nombre = Util.capitalize(pNombre.trim());
		} else {
			nombre = null;
		}
	}

	/**
	 * @return número de documento de la persona
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String pNumeroDocumento) {
		if(pNumeroDocumento != null) {
			numeroDocumento = pNumeroDocumento.trim();
		} else {
			numeroDocumento = null;
		}
	}

	/**
	 * @return sexo de la persona
	 */
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo pSexo) {
		sexo = pSexo;
	}

	public long getIdPersonaFisica() {
		return this.getIdPersona();
	}

	public void setIdPersonaFisica(long idPersonaFisica) {
		this.setIdPersona(idPersonaFisica);
	}

	public String getNombreCompleto() {
		return this.getApellido() + ", " + this.getNombre();
	}

	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		return (this.apellido == null) ? "" : Util.capitalize(this.apellido) + ", " + Util.capitalize(this.nombre) + " "
				+ ((this.getCuil() != null) ? ("[" + this.getCuil() + "]") : "");
	}

	@Override
	public String getToStringCompleto() {
		return this.toString();
	}

	@Override
	public String getDenominacion() {
		return this.getNombreCompleto();
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
