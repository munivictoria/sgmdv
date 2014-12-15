package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.Util;

@MappedSuperclass
public abstract class Plano implements Serializable{

	private static final long serialVersionUID = -48784831645251357L;

	/**
	 *Tipos: ORIGINAL,
		COPIA,
		MODIFICADO;
	 */
	public enum Tipo{
		ORIGINAL,
		COPIA,
		MODIFICADO;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	/**
	 * Tipos: APROBADO,
		NO_APROBADO,
		EN_TRAMITE;
	 */
	public enum Estado{
		APROBADO,
		NO_APROBADO,
		EN_TRAMITE;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	@Column
	private String plano;

	private String folio;

	private String tomo;

	@Enumerated(EnumType.STRING)
	private Tipo tipo = Tipo.ORIGINAL;

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.EN_TRAMITE;

	@Column(name="NRO_EXPEDIENTE")
	private String nroExpediente;

	@Column(name="NRO_REGISTRO")
	private String nroRegistro;

	@Column(name = "FECHA_INSCRIPCION")
	private Date fechaInscripcion;

	private String comentario;

	//	@ManyToOne(optional=false)
	//	@JoinColumn(name = "ID_PARCELA")
	@Transient
	private Parcela parcela;

	public abstract void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico);

	public abstract List<AtributoDinamico<?>> getListaAtributosDinamicos();

	public abstract void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos);

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) throws Exception {
		if(parcela instanceof SubParcela){
			throw new Exception("Un plano de mensura solo puede poseer parcelas.");
		}

		this.parcela = parcela;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTomo() {
		return tomo;
	}

	public void setTomo(String tomo) {
		this.tomo = tomo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNroExpediente() {
		return nroExpediente;
	}

	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

}
