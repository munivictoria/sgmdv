package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Table(name = "ZONA")
@Cacheable
public class Zona implements Serializable, EntidadTrascender {

	public enum Estado {
		ACTIVO, INACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super
					.toString());
		}

	}

	public static final long serialVersionUID = 6345473134015288299L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_zona")
	@SequenceGenerator(name ="gen_id_zona", sequenceName = "gen_id_zona", allocationSize = 1)
	@Column(name = "ID_ZONA")
	private long idZona = -1;
	private String nombre;
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	@OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AsociacionParcelaBridge> listaAsociacionParcela;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ZONIFICACION")
	private Zonificacion zonificacion;
	
	private Integer prioridad;
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	/**
	 * @hibernate.property
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @hibernate.property type =
	 *                     "com.trascender.catastro.util.enumerations.EstadoZona"
	 */
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @hibernate.id column = "ID_ZONA" generator-class = "increment"
	 *               unsaved-value = "-1"
	 */
	public long getIdZona() {
		return idZona;
	}

	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AsociacionParcelaBridge> getListaAsociacionParcela() {
		return listaAsociacionParcela;
	}

	public void setListaAsociacionParcela(
			List<AsociacionParcelaBridge> listaAsociacionParcela) {
		this.listaAsociacionParcela = listaAsociacionParcela;
	}

	public Zonificacion getZonificacion() {
		return zonificacion;
	}

	public void setZonificacion(Zonificacion zonificacion) {
		this.zonificacion = zonificacion;
	}

	public void add(AsociacionParcelaBridge pAsociacion) throws Exception {
		if (this.getListaAsociacionParcela() == null) {
			this.listaAsociacionParcela = new ArrayList<AsociacionParcelaBridge>();
		}

		if (this.getListaAsociacionParcela() != null 
				//&& !this.getListaAsociacionParcela().isEmpty() 
				&& !this.getListaAsociacionParcela().contains(pAsociacion)) {
			// Valido que la asociación no haya estado asociada a otra zona
			if ((pAsociacion.getZona() != null) && (!pAsociacion.equals(this))) {
				pAsociacion.getZona().getListaAsociacionParcela().remove(pAsociacion);
			}
			
			this.getListaAsociacionParcela().add(pAsociacion);
			pAsociacion.setZona(this);
			
		}
	}

	/**
	 * @param pAsociacion 
	 * @return True = Si la parcela ya esxiste; False = Si la parcela no esta en la lista
	 * @throws Exception 
	 */
	private boolean validarUnicidadDeParcela(AsociacionParcelaBridge pAsociacion) {
		boolean locEncontrado = false;
		try {
			
			System.out.println("Entro");
			for (AsociacionParcelaBridge locAsociacionParcela : this.listaAsociacionParcela) {
				System.out.println("Entro 1");
				for (Parcela cadaParcela : locAsociacionParcela.getListaParcelas()) {
					System.out.println("Entro 2");
					for (Parcela cadaParcelaAsociacion : pAsociacion.getListaParcelas()) {
						System.out.println("Entro 3");
						if (cadaParcela.equals(cadaParcelaAsociacion)) {
							locEncontrado = true;
						}

						if (locEncontrado) {
							break;
						}
					}

					if (locEncontrado) {
						break;
					}
				}

				if (locEncontrado) {
					break;
				}
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return locEncontrado;
		
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public String toString() {
		return (this.nombre != null) ? this.nombre : "Sin nombre";
	}

	@Override
	public int hashCode() {
		if (this.getIdZona() == -1) {
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idZona ^ (idZona >>> 32));
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
		final Zona other = (Zona) obj;
		if (idZona != other.idZona)
			return false;
		return true;
	}
	
	//*********************************************************************************************************************************************************************************/
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
			return this.idZona;
		}

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public String getNombrePropiedadId() {
			return "idBien";
		}

		public boolean isAuditable() {
			return true;
		}
}
