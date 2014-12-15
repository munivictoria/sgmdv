package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@Table(name = "CUADRA")
public class Cuadra implements Serializable, EntidadTrascender {
	/**
	 * 
	 */
	public static final long serialVersionUID = 9118210359747742005L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_cuadra")
	@SequenceGenerator(name ="gen_id_cuadra", sequenceName = "gen_id_cuadra", allocationSize = 1)
	@Column(name = "ID_CUADRA")
	private long idCuadra=-1;
	
	@Column(name = "NUMERACION_DESDE")
	private Integer numeracionDesde;
	
	@Column(name = "NUMERACION_HASTA")
	private Integer numeracionHasta;
	
	@Column(name = "TIPO_NUMERACION")
	private Character tipoNumeracion;
	
	@Column(name = "METROS_LINEALES")
	private Double metrosLineales;
	
	@ManyToOne
	@JoinColumn(name = "ID_CALLE_EMPIEZA_EN")
	private Calle calleComienza;
	
	@ManyToOne
	@JoinColumn(name = "ID_CALLE_TERMINA_EN")
	private Calle calleFinaliza;
	
	@ManyToOne
	@JoinColumn(name = "ID_CALLE")
	private Calle calle;
	
	private boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "ID_MANZANA", insertable =true, updatable = true)
	private Manzana manzana;
	
	@OneToMany(mappedBy = "cuadra")
	private List<ParcelaPorCuadra> listaParcelasPorCuadra=new ArrayList<ParcelaPorCuadra>();
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OneToMany(mappedBy = "cuadra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AsociacionParcelaCuadra> listaAsociacionParcelaCuadra = new ArrayList<AsociacionParcelaCuadra>();
	
	public void addZona(Zona pZona){
		AsociacionParcelaCuadra locAsociacion = new AsociacionParcelaCuadra();
		locAsociacion.setZona(pZona);
		locAsociacion.setCuadra(this);
		this.listaAsociacionParcelaCuadra.add(locAsociacion);
	}
	
	public void removeZona(AsociacionParcelaCuadra pAsociacion){
		this.listaAsociacionParcelaCuadra.remove(pAsociacion);
	}

	public List<AsociacionParcelaCuadra> getListaAsociacionParcelaCuadra() {
		return listaAsociacionParcelaCuadra;
	}

	public void setListaAsociacionParcelaCuadra(
			List<AsociacionParcelaCuadra> listaAsociacionParcelaCuadra) {
		this.listaAsociacionParcelaCuadra = listaAsociacionParcelaCuadra;
	}
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idCuadra);
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
	
	public List<ParcelaPorCuadra> getListaParcelasPorCuadra() {
		return listaParcelasPorCuadra;
	}
	public void setListaParcelasPorCuadra(List<ParcelaPorCuadra> pListaParcelasPorCuadra) {
		this.listaParcelasPorCuadra = pListaParcelasPorCuadra;
	}
	
	public Manzana getManzana() {
		return manzana;
	}
	
	public void setManzana(Manzana manzana) {
		this.manzana = manzana;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Calle getCalleComienza() {
		return calleComienza;
	}

	public void setCalleComienza(Calle calleComienza) {
		this.calleComienza = calleComienza;
	}

	public Calle getCalleFinaliza() {
		return calleFinaliza;
	}

	public void setCalleFinaliza(Calle calleFinaliza) {
		this.calleFinaliza = calleFinaliza;
	}

	public Calle getCalle() {
		return calle;
	}

	public void setCalle(Calle calle) {
		this.calle = calle;
	}

	public long getIdCuadra() {
		return idCuadra;
	}

	public void setIdCuadra(long idCuadra) {
		this.idCuadra = idCuadra;
	}

	public Double getMetrosLineales() {
		return metrosLineales;
	}

	public void setMetrosLineales(Double metrosLineales) {
		this.metrosLineales = metrosLineales;
	}

	public Integer getNumeracionDesde() {
		return numeracionDesde;
	}

	public void setNumeracionDesde(Integer numeracionDesde) {
		this.numeracionDesde = numeracionDesde;
	}

	public Integer getNumeracionHasta() {
		return numeracionHasta;
	}

	public void setNumeracionHasta(Integer numeracionHasta) {
		this.numeracionHasta = numeracionHasta;
	}

	public Character getTipoNumeracion() {
		return tipoNumeracion;
	}

	public void setTipoNumeracion(Character tipoNumeracion) {
		if (tipoNumeracion != null) {
			this.tipoNumeracion = Character.toUpperCase(tipoNumeracion);
		} else {
			this.tipoNumeracion = null;
		}
	}

	@Override
	public String toString() {
		if ((this.getCalle() != null) && (this.getTipoNumeracion() != null)
				&& (this.getNumeracionDesde() != null)
				&& (this.getNumeracionHasta() != null)) {
			return this.getCalle()
					+ " ["
					+ this.getNumeracionDesde()
					+ " - "
					+ this.getNumeracionHasta()
					+ "]"
					+ ((this.getTipoNumeracion().equals('P')) ? " Par"
							: " Impar");
		}
		return "";
	}

	public String getNombre() {
		return this.toString();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCuadra ^ (idCuadra >>> 32));
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
		final Cuadra other = (Cuadra) obj;
		if (idCuadra != other.idCuadra)
			return false;
		return true;
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : listaAtributosDinamicos){
			cadaAtributo.setIdEntidad(this.idCuadra);
		}
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
			return this.idCuadra;
		}

		public String getNombrePropiedadId() {
			return "idCuadra";
		}

		public boolean isAuditable() {
			return true;
		}
}