package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;
//import com.trascender.framework.util.Auditable;
//import com.trascender.framework.util.LogAuditoria;



/**
 * @hibernate.class table = "DOC_HAB_ESPECIALIZADO" 
 * @hibernate.discriminator column = "TIPO_DOC_HAB_ESPECIALIZADO"
 */

@Entity
@Table(name = "DOC_HAB_ESPECIALIZADO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_DOC_HAB_ESPECIALIZADO")
public abstract class DocHabilitanteEspecializado implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -8553502914982912154L;

	public enum Estado{ACTIVO,INACTIVO;
	@Override
	public String toString() {
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}
	};

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_doc_hab_especializado")
	@SequenceGenerator(name = "gen_id_doc_hab_especializado", sequenceName = "gen_id_doc_hab_especializado", allocationSize = 1)
	@Column(name = "ID_DOC_HAB_ESPECIALIZADO")
	private long idDocHabilitanteEspecializado=-1;

	@OneToMany(mappedBy = "docHabilitanteEspecializado")
	private Set<RegistroValuado> listaRegistrosValuados=new HashSet<RegistroValuado>();

	private String nombre;

	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.ACTIVO;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "FECHA_INICIO_ACTIVIDAD")
	private Date fechaInicioActividad;

	@Column(name = "FECHA_CESE_ACTIVIDAD")
	private Date fechaCeseActividad;

	@ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OBLIGACION")
	private Obligacion obligacion;

	@ManyToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOMICILIO_POSTAL")
	private Domicilio domicilio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARCELA")
	private Parcela parcela;

	@NoAuditable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_TASA")
	private TipoTasa tipoTasa;

	@OneToMany(mappedBy="docHabilitanteEspecializado", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<LogModificacionDocEsp> listaLogsModificaciones = new ArrayList<LogModificacionDocEsp>();

	@OneToMany(mappedBy="docHabilitanteEspecializado", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	private List<AsocRegAlicuota> listaAsocRegAlicuota = new ArrayList<AsocRegAlicuota>();

	/*@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogAuditorias = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogAuditorias() {
		return listaLogAuditorias;
	}

	public void setListaLogAuditorias(List<LogAuditoria> listaLogAuditorias) {
		this.listaLogAuditorias = listaLogAuditorias;
	}*/

	public abstract void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico);

	public abstract List<AtributoDinamico<?>> getListaAtributosDinamicos();

	public abstract void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos);

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Set<RegAlicuota> getListaRegAlicuotas() {
		Set<RegAlicuota> listaRegAlicuotas = new HashSet<RegAlicuota>();
		for(AsocRegAlicuota cadaAsoc : this.listaAsocRegAlicuota){
			listaRegAlicuotas.add(cadaAsoc.getRegistroAlicuota());
		}
		return listaRegAlicuotas;
	}

	public void setListaRegAlicuotas(Set<RegAlicuota> listaRegAlicuotas) {

	}

	public Obligacion getObligacion() {
		return obligacion;
	}

	public void setObligacion(Obligacion obligacion) {
		this.obligacion = obligacion;
	}

	public Date getFechaCeseActividad() {
		return fechaCeseActividad;
	}

	public void setFechaCeseActividad(Date fechaCeseActividad) {
		this.fechaCeseActividad = fechaCeseActividad;
	}

	public Date getFechaInicioActividad() {
		return fechaInicioActividad;
	}

	public void setFechaInicioActividad(Date fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdDocHabilitanteEspecializado() {
		return idDocHabilitanteEspecializado;
	}

	public void setIdDocHabilitanteEspecializado(long idDocHabilitanteEspecializado) {
		this.idDocHabilitanteEspecializado = idDocHabilitanteEspecializado;
	}

	public List<LogModificacionDocEsp> getListaLogsModificaciones() {
		return listaLogsModificaciones;
	}

	public void setListaLogsModificaciones(
			List<LogModificacionDocEsp> listaLogsModificaciones) {
		this.listaLogsModificaciones = listaLogsModificaciones;
	}

	//	@Transient
	//	public abstract RegAlicuota getRegistroAlicuota();

	public List<AsocRegAlicuota> getListaAsocRegAlicuota() {
		return listaAsocRegAlicuota;
	}

	public void setListaAsocRegAlicuota(List<AsocRegAlicuota> listaAsocRegAlicuota) {
		this.listaAsocRegAlicuota = listaAsocRegAlicuota;
		for(AsocRegAlicuota cadaAsoc : this.listaAsocRegAlicuota){
			cadaAsoc.setDocHabilitanteEspecializado(this);
		}
	}

	@Override
	public int hashCode() {
		if (this.getIdDocHabilitanteEspecializado()==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idDocHabilitanteEspecializado ^ (idDocHabilitanteEspecializado >>> 32));
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
		final DocHabilitanteEspecializado other = (DocHabilitanteEspecializado) obj;
		if (idDocHabilitanteEspecializado != other.idDocHabilitanteEspecializado)
			return false;
		return true;
	}

	public Set<RegistroValuado> getListaRegistrosValuados() {
		return listaRegistrosValuados;
	}

	public void setListaRegistrosValuados(
			Set<RegistroValuado> listaRegistrosValuados) {
		this.listaRegistrosValuados = listaRegistrosValuados;
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}
	public void setTipoTasa(TipoTasa tipoTasa) {
		if (tipoTasa!=null){
			if (!tipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)){
				this.tipoTasa = tipoTasa;
			}
		}
		else{
			this.tipoTasa = null;
		}
	}

	/**
	 * Obtiene el registro valuado de un periodo
	 * @param periodoLiquidacion
	 * @return
	 */
	public List<RegistroValuado> getRegistroValuado(CuotaLiquidacion pCuota) {
		List<RegistroValuado> locListaResultado = new ArrayList<RegistroValuado>();
		for (RegistroValuado cadaRegistroValuado : this.getListaRegistrosValuados()){
			if (cadaRegistroValuado.getCuotaLiquidacion().equals(pCuota)){
				locListaResultado.add(cadaRegistroValuado);
			}
		}
		return locListaResultado;
	}

	public RegistroValuado getRegistroValuado(CuotaLiquidacion pCuota, RegAlicuota pRegAlicuota){
		for (RegistroValuado cadaRegistroValuado : this.getListaRegistrosValuados()){
			if (cadaRegistroValuado.getCuotaLiquidacion().equals(pCuota) && cadaRegistroValuado.getRegAlicuota().equals(pRegAlicuota)){
				return cadaRegistroValuado;
			}
		}
		return null;
	}

	public List<RegistroValuado> getListaRegistrosValuados(CuotaLiquidacion pCuota){
		List<RegistroValuado> locListaResultado = new ArrayList<RegistroValuado>();
		for (RegistroValuado cadaRegistroValuado : this.getListaRegistrosValuados()){
			if (cadaRegistroValuado.getCuotaLiquidacion().equals(pCuota)){
				locListaResultado.add(cadaRegistroValuado);
			}
		}
		return locListaResultado;
	}

	@Transient
	public RegAlicuota getRegistroAlicuota() {
		return !this.getListaRegAlicuotas().isEmpty() ? this.getListaRegAlicuotas().iterator().next() : null;
	}

	public AsocRegAlicuota getAsocRegAlicuotaPorAlicuota(RegAlicuota pRegAlicuota) {
		for (AsocRegAlicuota cadaAsoc : this.listaAsocRegAlicuota){
			if (cadaAsoc.getRegistroAlicuota().equals(pRegAlicuota)) {
				return cadaAsoc;
			}
		}
		return null;
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(idDocHabilitanteEspecializado);
		}
	}

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public void setComentarioAuditoria(String pComentario) {
		this.comentarioAuditoria = pComentario;
	}

	public void setLlaveUsuarioAuditoria(long pLlave) {
		this.llaveUsuarioAuditoria = pLlave;
	}

	public long getLlaveUsuarioAuditoria() {
		return this.llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return this.comentarioAuditoria;
	}

	public abstract List<LogAuditoria> getListaLogsAuditoria();

	public abstract void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria);

	public long getIdEntidad() {
		return this.idDocHabilitanteEspecializado;
	}

	public String getNombrePropiedadId() {
		return "idDocHabilitanteEspecializado";
	}

	public boolean isAuditable() {
		return true;
	}

}
