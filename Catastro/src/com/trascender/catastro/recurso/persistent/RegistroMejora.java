package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion.EstadoCoeficiente;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "REGISTRO_MEJORA")
public class RegistroMejora implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -4184040529333327426L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_registro_mejora")
	@SequenceGenerator(name ="gen_id_registro_mejora", sequenceName = "gen_id_registro_mejora", allocationSize = 1)
	@Column(name = "ID_REGISTRO_MEJORA")
	private long idRegistroMejora=-1;
	
	private Double superficie;
	
	@Column(name = "ANIO_CONSTRUCCION")
	private Integer anioConstruccion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_MEJORA")
	private CoeficienteDepreciacion.EstadoCoeficiente estadoMejora = EstadoCoeficiente.BUENO;
	
	@ManyToOne
	@JoinColumn(name = "ID_PARCELA")
	private Parcela parcela;
	
	@ManyToOne(cascade = CascadeType.ALL)/////////
	@JoinColumn(name = "ID_DECLARACION_JURADA")
	private DeclaracionJurada declaracionJurada;
	
	private boolean activo=true;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : listaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdRegistroMejora());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(idRegistroMejora);
		}
	}
	/**
	 * 
	 * @hibernate.many-to-one cascade = "none" column = "ID_CATEGORIA" not-null = "true" 
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * 
	 * @hibernate.property column = "ACTIVO"
	 */
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	/**
	 * 
	 * @hibernate.property column = "ANIO_CONSTRUCCION"
	 */
	public Integer getAnioConstruccion() {
		return anioConstruccion;
	}
	public void setAnioConstruccion(Integer anioConstruccion) {
		this.anioConstruccion = anioConstruccion;
	}
	
	/**
	 * 
	 * @hibernate.many-to-one column = "ID_DECLARACION_JURADA" cascade = "none" not-null = "false"
	 */
	public DeclaracionJurada getDeclaracionJurada() {
		return declaracionJurada;
	}
	public void setDeclaracionJurada(DeclaracionJurada declaracionJurada) {
		this.declaracionJurada = declaracionJurada;
	}
	
	/**
	 * 
	 * @hibernate.property   
	 * 	type = "com.trascender.catastro.util.enumerations.EstadoCoeficienteDepreciacion"
	 * column = "ESTADO_MEJORA"
	 * not-null = "true"
	 */
	public CoeficienteDepreciacion.EstadoCoeficiente getEstadoMejora() {
		return estadoMejora;
	}
	public void setEstadoMejora(CoeficienteDepreciacion.EstadoCoeficiente estadoMejora) {
		this.estadoMejora = estadoMejora;
	}
	
	/**
	 * 
	 * @hibernate.id column = "ID_REGISTRO_MEJORA" generator-class = "increment" unsaved-value = "-1" 
	 */
	public long getIdRegistroMejora() {
		return idRegistroMejora;
	}
	public void setIdRegistroMejora(long idRegistroMejora) {
		this.idRegistroMejora = idRegistroMejora;
	}
	
	/**
	 * 
	 * @hibernate.many-to-one cascade = "none" column = "ID_PARCELA" not-null = "true"   
	 */
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	
	/**
	 * 
	 * @hibernate.property
	 */
	public Double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}
	
	@Override
	public String toString() {
		return this.getAnioConstruccion()+" - "+((this.declaracionJurada!=null)?this.declaracionJurada.getNumero():"") ;
	}
	
	@Override
	public int hashCode() {
		if (this.getIdRegistroMejora() == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRegistroMejora ^ (idRegistroMejora >>> 32));
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
		if(this.getIdRegistroMejora() == -1){
			return (super.equals(obj) && (System.identityHashCode(this) == System.identityHashCode(obj)));
		}
		final RegistroMejora other = (RegistroMejora) obj;		
		if (idRegistroMejora != other.idRegistroMejora)
			return false;
				
		return true;
	}
	
	//*********************************************************************************************************************************************************************************/
		// AUDITORIA
	
		@OrderBy(value="fecha")
		@Where(clause = "id_recurso = " + serialVersionUID)
		@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
		private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

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
		public List<LogAuditoria> getListaLogsAuditoria() {
			return listaLogsAuditoria;
		}
		public void setListaLogsAuditoria(List<LogAuditoria> listaLogsAuditoria) {
			this.listaLogsAuditoria = listaLogsAuditoria;
		}
		public long getIdEntidad() {
			return this.idRegistroMejora;
		}
		public long getSerialVersionUID() {
			return serialVersionUID;
		}
		public String getNombrePropiedadId() {
			return "idRegistroMejora";
		}
		public boolean isAuditable() {
			return true;
		}
		
}
