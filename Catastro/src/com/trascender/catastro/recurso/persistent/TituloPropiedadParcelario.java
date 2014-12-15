package com.trascender.catastro.recurso.persistent;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;

@Entity
@DiscriminatorValue("PARCELARIO")
public class TituloPropiedadParcelario extends TituloPropiedad{

	public static final long serialVersionUID = -2087529643864407717L;

	/**
	 *COMPARATIVA,
		SEGREGACION,
		CESION,
		DONACION,
		LIQ_SOC_GANANCIALES,
		VENTA_JUDICIAL,
		PART_HEREDITARIA,
		PERMUTA;
	 */
	public enum TipoTransaccionCatastral{
		COMPRAVENTA,
		SEGREGACION,
		CESION,
		DONACION,
		AGRUPACION,
		LIQ_SOC_GANANCIALES,
		VENTA_JUDICIAL,
		PART_HEREDITARIA,
		PERMUTA;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	@Column(name = "NRO_TOMO")
	private Integer nroTomo;

	@Column(name = "NRO_FOLIO")
	private Integer nroFolio;

	@Column
	private Integer asiento;

	@Column(name="AREA_REGISTRO")
	private String areaRegistro;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_TRANSACCION_CATASTRAL", nullable=false)
	private TipoTransaccionCatastral tipoTransaccionCatastral;

	//	@ManyToOne
	//	@JoinColumn(name = "ID_PARCELA")
	@Transient
	private Parcela parcela;

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Integer getNroTomo() {
		return nroTomo;
	}

	public void setNroTomo(Integer nroTomo) {
		this.nroTomo = nroTomo;
	}

	public Integer getNroFolio() {
		return nroFolio;
	}

	public void setNroFolio(Integer nroFolio) {
		this.nroFolio = nroFolio;
	}

	public Integer getAsiento() {
		return asiento;
	}

	public void setAsiento(Integer asiento) {
		this.asiento = asiento;
	}

	public String getAreaRegistro() {
		return areaRegistro;
	}

	public void setAreaRegistro(String areaRegistro) {
		this.areaRegistro = areaRegistro;
	}

	public TipoTransaccionCatastral getTipoTransaccionCatastral() {
		return tipoTransaccionCatastral;
	}

	public void setTipoTransaccionCatastral(
			TipoTransaccionCatastral tipoTransaccionCatastral) {
		this.tipoTransaccionCatastral = tipoTransaccionCatastral;
	}

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	@Override
	public String toString() {
		return this.toStringCompleto();
	}

	public String toStringCompleto() {
		DateFormat locDF = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return super.getFechaInscripcion() != null ? "Fecha Inscripcion: " + locDF.format(super.getFechaInscripcion()) : ""
				+ this.nroTomo != null ? " Tomo/Folio: " + this.nroTomo : ""
						+ this.nroFolio != null ? "/" + this.nroFolio : "";
	}

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdTituloPropiedad());
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

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;


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

	public long getIdEntidad() {
		return this.getIdTituloPropiedad();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTituloPropiedad";
	}

	public boolean isAuditable() {
		return true;
	}
}
