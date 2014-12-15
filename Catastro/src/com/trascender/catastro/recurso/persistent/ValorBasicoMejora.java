package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@Table(name = "VALOR_BASICO_MEJORA")
public class ValorBasicoMejora implements Serializable, EntidadTrascender {

	/**
	 * 
	 */
	public static final long serialVersionUID = 5663951740353022604L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_valor_basico_mejora")
	@SequenceGenerator(name ="gen_id_valor_basico_mejora", sequenceName = "gen_id_valor_basico_mejora", allocationSize = 1)
	@Column(name = "ID_VALOR_BASICO_MEJORA")
	private long idValorBasicoMejora=-1;
	private Double valor;
	
	@Column(name = "ANIO_VIGENTE")
	private Integer anioVigente;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return a�o de vigencia del valor b�sico por mejora
	 */
	public Integer getAnioVigente() {
		return anioVigente;
	}

	public void setAnioVigente(Integer anioVigente) {
		this.anioVigente = anioVigente;
	}

	public long getIdValorBasicoMejora() {
		return idValorBasicoMejora;
	}

	public void setIdValorBasicoMejora(long idValorBasicoMejora) {
		this.idValorBasicoMejora = idValorBasicoMejora;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return this.getAnioVigente() + " - " + this.getCategoria().toString();
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
			return this.idValorBasicoMejora;
		}

		public String getNombrePropiedadId() {
			return "idValorBasicoMejora";
		}

		public boolean isAuditable() {
			return true;
		}
}