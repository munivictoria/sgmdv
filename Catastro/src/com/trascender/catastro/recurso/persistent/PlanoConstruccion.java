package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "PLANO_CONSTRUCCION") 
public class PlanoConstruccion extends Plano implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = 2480759541574778008L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_plano_construccion")
	@SequenceGenerator(name ="gen_id_plano_construccion", sequenceName = "gen_id_plano_construccion", allocationSize = 1)
	@Column(name = "ID_PLANO_CONSTRUCCION")
	private long idPlanoConstruccion=-1;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="planoConstruccion")
	private List<FirmantePlanoConstruccion> listaFirmantePlanoConstruccion = new ArrayList<FirmantePlanoConstruccion>();

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idPlanoConstruccion);
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ESTADISTICAS_INDEC")
	private EstadisticasIndec estadisticasIndec;
	
	@Column(name = "SUPERFICIE_CUBIERTA_PLANTA_BAJA")
	private Double superficieCubiertaPlantaBaja;
	
	@Column(name = "SUPERFICIE_CUBIERTA_PLANTA_ALTA")
	private Double superficieCubiertaPlantaAlta;
	
	@Column(name = "SUPERFICIE_SEMICUBIERTA_PLANTA_BAJA")
	private Double superficieSemiCubiertaPlantaBaja;
	
	@Column(name = "SUPERFICIE_SEMICUBIERTA_PLANTA_ALTA")
	private Double superficieSemiCubiertaPlantaAlta;
	
	@Column(name = "NRO_PLANTA")
	private Integer nroPlantas;

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	@Override
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	public EstadisticasIndec getEstadisticasIndec() {
		return estadisticasIndec;
	}

	public void setEstadisticasIndec(EstadisticasIndec estadisticasIndec) {
		this.estadisticasIndec = estadisticasIndec;
	}

	public Double getSuperficieCubiertaPlantaBaja() {
		return superficieCubiertaPlantaBaja;
	}

	public void setSuperficieCubiertaPlantaBaja(Double superficieCubiertaPlantaBaja) {
		this.superficieCubiertaPlantaBaja = superficieCubiertaPlantaBaja;
	}

	public Double getSuperficieCubiertaPlantaAlta() {
		return superficieCubiertaPlantaAlta;
	}

	public void setSuperficieCubiertaPlantaAlta(Double superficieCubiertaPlantaAlta) {
		this.superficieCubiertaPlantaAlta = superficieCubiertaPlantaAlta;
	}

	public Double getSuperficieSemiCubiertaPlantaBaja() {
		return superficieSemiCubiertaPlantaBaja;
	}

	public void setSuperficieSemiCubiertaPlantaBaja(
			Double superficieSemiCubiertaPlantaBaja) {
		this.superficieSemiCubiertaPlantaBaja = superficieSemiCubiertaPlantaBaja;
	}

	public Double getSuperficieSemiCubiertaPlantaAlta() {
		return superficieSemiCubiertaPlantaAlta;
	}

	public void setSuperficieSemiCubiertaPlantaAlta(
			Double superficieSemiCubiertaPlantaAlta) {
		this.superficieSemiCubiertaPlantaAlta = superficieSemiCubiertaPlantaAlta;
	}

	public Integer getNroPlantas() {
		return nroPlantas;
	}

	public void setNroPlantas(Integer nroPlantas) {
		this.nroPlantas = nroPlantas;
	}

	public long getIdPlanoConstruccion() {
		return idPlanoConstruccion;
	}

	public void setIdPlanoConstruccion(long idPlanoConstruccion) {
		this.idPlanoConstruccion = idPlanoConstruccion;
	}

	public List<FirmantePlanoConstruccion> getListaFirmantePlanoConstruccion() {
		return listaFirmantePlanoConstruccion;
	}

	public void setListaFirmantePlanoConstruccion(
			List<FirmantePlanoConstruccion> listaFirmantePlanoConstruccion) {
		this.listaFirmantePlanoConstruccion = listaFirmantePlanoConstruccion;
	}

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	@Override
	public String toString() {
		SimpleDateFormat locDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "[PlanoConstruccion: " + this.getPlano() + /*this.getFechaInscripcion().getTime() != null? (" Fecha Inscripción: " +
			locDateFormat.format(this.getFechaInscripcion())) : "" +*/ " Parcela " + this.getParcela() + "]";
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(idPlanoConstruccion);
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
		return this.getIdPlanoConstruccion();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idPlanoConstruccion";
	}

	public boolean isAuditable() {
		return true;
	}
}
