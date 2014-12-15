
package com.trascender.habilitaciones.recurso.persistent.osp;

import java.io.Serializable;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;

@Entity
@SecondaryTable(name = "SERVICIO_OSP", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID_REG_ALICUOTA"))
@DiscriminatorValue(value = "SERVICIO_OSP")
public class ServicioOSP extends RegAlicuota implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -8050545963617946384L;

	@Column(name = "COEFICIENTE_VALOR_TERRENO", table = "SERVICIO_OSP")
	private Double coeficienteValorTerreno;

	@Column(name = "COEFICIENTE_VALOR_EDIFICADO", table = "SERVICIO_OSP")
	private Double coeficienteValorEdificado;

	@Column(name = "VALOR_POR_EXCEDENTE", table = "SERVICIO_OSP")
	private Double valorPorExcedente = 0d;

	@Column(name = "BASE_CONSUMO", table = "SERVICIO_OSP")
	private Double baseConsumo = 0d;

	@Column(table = "SERVICIO_OSP")
	private boolean medido;

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIDAD_MEDIDA", table = "SERVICIO_OSP")
	private UnidadMedida unidadMedida;

	@Column(name = "COEFICIENTE_CODIGO_SERVICIO", table = "SERVICIO_OSP")
	private Double coeficienteCodigoServicio;

	@Column(name = "VOLCADO_EFLUENTES_INDUSTRIALES", table = "SERVICIO_OSP")
	private boolean volcadoEfluentesIndustriales;

	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	/**
	 * Unidades de medida de un servicio
	 */
	public enum UnidadMedida {
		LITROS("Litros", "lt"), METROS_CUBICOS("Metros Cúbicos", "m3"), MILILITROS("Mililitros", "ml");

		private String nombre;
		private String abreviatura;

		private UnidadMedida(String pNombre, String pAbreviatura) {
			this.nombre = pNombre;
			this.abreviatura = pAbreviatura;
		}

		public String getNombre() {
			return this.nombre;
		}

		public String getAbreviatura() {
			return abreviatura;
		}

		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	public ServicioOSP() {
		super();
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getBaseConsumo() {
		return baseConsumo;
	}

	public void setBaseConsumo(Double pBaseConsumo) {
		this.baseConsumo = pBaseConsumo;
	}

	public Double getCoeficienteCodigoServicio() {
		return coeficienteCodigoServicio;
	}

	public void setCoeficienteCodigoServicio(Double pCoeficienteCodigoServicio) {
		coeficienteCodigoServicio = pCoeficienteCodigoServicio;
	}

	public Double getValorPorExcedente() {
		return valorPorExcedente;
	}

	public void setValorPorExcedente(Double pValorPorExcedente) {
		this.valorPorExcedente = pValorPorExcedente;
	}

	public boolean isMedido() {
		return medido;
	}

	public void setMedido(boolean medido) {
		this.medido = medido;
	}

	public Double getCoeficienteValorEdificado() {
		return coeficienteValorEdificado;
	}

	public void setCoeficienteValorEdificado(Double pCoeficienteValorEdificado) {
		this.coeficienteValorEdificado = pCoeficienteValorEdificado;
	}

	public Double getCoeficienteValorTerreno() {
		return coeficienteValorTerreno;
	}

	public void setCoeficienteValorTerreno(Double pCoeficienteValorTerreno) {
		this.coeficienteValorTerreno = pCoeficienteValorTerreno;
	}

	public boolean isVolcadoEfluentesIndustriales() {
		return volcadoEfluentesIndustriales;
	}

	public void setVolcadoEfluentesIndustriales(boolean pVolcadoEfluentesIndustriales) {
		this.volcadoEfluentesIndustriales = pVolcadoEfluentesIndustriales;
	}

	@Override
	public String toString() {
		return this.getCodigo() + " [" + this.getNombre() + "]";
	}

	// Atributos Dinamicos

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		pAtributoDinamico.setIdEntidad(this.getIdTipoAlicuota());
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

	// *********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

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

	public long getIdEntidad() {
		return this.getIdTipoAlicuota();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTipoAlicuota";
	}

	public boolean isAuditable() {
		return true;
	}
}