package com.trascender.habilitaciones.recurso.persistent.tasaMenor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;

@Entity
@Table(name = "PLANTILLA_DOC_TASA_MENOR")
public class PlantillaDocumentoTasaMenor implements Serializable, EntidadTrascender{
	
	public static final long serialVersionUID = -3793437662352943422L;

	/**
	 * Estados: <br>
	 * <li>ACTIVA <br>
	 * <li>INACTIVA <br>
	 */
	public enum EstadoPlantillaDocTasaMenor{
		ACTIVA,
		INACTIVA;
		
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_plantilla_doc_tasa_menor")
	@SequenceGenerator(name = "gen_id_plantilla_doc_tasa_menor", sequenceName = "gen_id_plantilla_doc_tasa_menor",allocationSize = 1)
	@Column(name="id_plantilla_doc_tasa_menor")
	private long idDocumentoTasaMenor = -1;
	
	private String nombre;

	@Column(name="asociacion_a_parcela")
	private boolean asociacionAParcela;
	
	@Column(name="persona_propietaria")
	private boolean personaPropietaria;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private EstadoPlantillaDocTasaMenor estado = EstadoPlantillaDocTasaMenor.ACTIVA;
	
	/**
	 * Normalmente cada PlantillaAtributoDinamico apunta a que Clase pertence, pero no sirve para este caso
	 * porque cada instancia de PlantillaDocumentoTasaMenor tiene asociada sus propias
	 * PlantillasAtributosDinamicos
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELA_PLAN_DOC_PLAN_ATRIB", joinColumns = @JoinColumn(name = "ID_PLANTILLA_DOCUMENTO"), inverseJoinColumns = @JoinColumn(name = "ID_PLANTILLA_ATRIBUTO"))
	private List<PlantillaAtributoDinamico> listaPlantillasAtributos = new ArrayList<PlantillaAtributoDinamico>();
	
	/**
	 * Esta lista mantiene la relacion entre las Plantillas de atributos dinámicos y los Registros Valuados pertenecientes
	 * a esta Tasa Menor. 
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELA_PLAN_DOC_DINAM_VALUADO", joinColumns = @JoinColumn(name = "ID_PLANTILLA_DOCUMENTO"), inverseJoinColumns = @JoinColumn(name = "ID_PLANTILLA_ATRIBUTO"))
	private List<PlantillaAtributoDinamico> listaPlantillasRegistroValuado = new ArrayList<PlantillaAtributoDinamico>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_OBLIGACION")
	private TipoObligacion tipoObligacion = new TipoObligacion();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public void addPlantillaAtributoRegistroValuado(PlantillaAtributoDinamico pPlantilla){
		listaPlantillasRegistroValuado.add(pPlantilla);
	}
	
	public List<PlantillaAtributoDinamico> getListaPlantillasRegistroValuado() {
		return listaPlantillasRegistroValuado;
	}

	public void setListaPlantillasRegistroValuado(
			List<PlantillaAtributoDinamico> listaPlantillasRegistroValuado) {
		this.listaPlantillasRegistroValuado = listaPlantillasRegistroValuado;
	}

	public void addPlantillaAtributoDinamico(PlantillaAtributoDinamico pPlantilla){
		listaPlantillasAtributos.add(pPlantilla);
	}
	
	public List<PlantillaAtributoDinamico> getListaPlantillasAtributos() {
		return listaPlantillasAtributos;
	}

	public void setListaPlantillasAtributos(
			List<PlantillaAtributoDinamico> listaPlantillasAtributos) {
		this.listaPlantillasAtributos = listaPlantillasAtributos;
	}

	public long getIdDocumentoTasaMenor() {
		return idDocumentoTasaMenor;
	}

	public void setIdDocumentoTasaMenor(long idDocumentoTasaMenor) {
		this.idDocumentoTasaMenor = idDocumentoTasaMenor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString(){
		return nombre;
	}
	
	public boolean isAsociacionAParcela() {
		return asociacionAParcela;
	}

	public void setAsociacionAParcela(boolean asociacionAParcela) {
		this.asociacionAParcela = asociacionAParcela;
	}

	public boolean isPersonaPropietaria() {
		return personaPropietaria;
	}

	public void setPersonaPropietaria(boolean personaPropietaria) {
		this.personaPropietaria = personaPropietaria;
	}

	public DocumentoTasaMenor generarDocumentoTasaMenor() throws Exception{
		DocumentoTasaMenor locDocumento = new DocumentoTasaMenor();
		locDocumento.setPlantillaDocumentoTasaMenor(this);
		//Solo para el refreso en la base, no los genera realmente
		locDocumento.generarAtributosDinamicos();
		return locDocumento;
	}
	
	public RegistroValuadoTasaMenor generarRegistroValuado(){
		RegistroValuadoTasaMenor locRegistroValuado = new RegistroValuadoTasaMenor();
		locRegistroValuado.setPlantillaDocTasaMenor(this);
		locRegistroValuado.generarAtributosDinamicos();
		return locRegistroValuado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idDocumentoTasaMenor ^ (idDocumentoTasaMenor >>> 32));
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
		PlantillaDocumentoTasaMenor other = (PlantillaDocumentoTasaMenor) obj;
		if (idDocumentoTasaMenor != other.idDocumentoTasaMenor)
			return false;
		return true;
	}
	
	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}

	public EstadoPlantillaDocTasaMenor getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantillaDocTasaMenor estado) {
		this.estado = estado;
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
		return this.idDocumentoTasaMenor;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idDocumentoTasaMenor";
	}

	public boolean isAuditable() {
		return true;
	}
}
