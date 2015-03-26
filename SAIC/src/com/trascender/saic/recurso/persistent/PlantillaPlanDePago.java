package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "PLANTILLA_PLAN_DE_PAGO")
public class PlantillaPlanDePago implements EntidadTrascender, Serializable{
	public static final long serialVersionUID = 5511484881779040110L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_plantilla_plan_pago")
	@SequenceGenerator(name = "gen_id_plantilla_plan_pago", sequenceName = "gen_id_plantilla_plan_pago", allocationSize = 1)
	@Column(name = "ID_PLANTILLA_PLAN_DE_PAGO")
	private long idPlantillaPlanDePago = -1;
	
	private String nombre;

	@Column(name = "MONTO_CONDONACION_IMPORTE")
	private Double montoCondonacionImporte;
	@Column(name = "MONTO_CONDONACION_INTERESES")
	private Double montoCondonacionIntereses;
	@Column(name = "CONDONACION_IMPORTE_PORCENTUAL")
	private boolean condonacionImportePorcentual = true;
	@Column(name = "CONDONACION_INTERES_PORCENTUAL")
	private boolean condonacionInteresPorcentual = true;

	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas;
	
	@Column(name = "TASA_NOMINAL_ANUAL")
	private Double tasaNominalAnual;
	@Column(name = "INTERES_PUNITORIO")
	private Double interesPunitorio;
	@Column(name = "DIA_VENCIMIENTO")
	private Integer diaVencimiento;
	
	@Column(name = "CANTIDAD_DIAS_CESE")
	private Integer cantidadDiasCese;
	@Column(name = "CANTIDAD_CUOTAS_CESE")
	private Integer cantidadCuotasCese;
	

	public long getIdPlantillaPlanDePago() {
		return idPlantillaPlanDePago;
	}

	public void setIdPlantillaPlanDePago(long idPlantillaPlanDePago) {
		this.idPlantillaPlanDePago = idPlantillaPlanDePago;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMontoCondonacionImporte() {
		return montoCondonacionImporte;
	}

	public void setMontoCondonacionImporte(Double montoCondonacionImporte) {
		this.montoCondonacionImporte = montoCondonacionImporte;
	}

	public Double getMontoCondonacionIntereses() {
		return montoCondonacionIntereses;
	}

	public void setMontoCondonacionIntereses(Double montoCondonacionIntereses) {
		this.montoCondonacionIntereses = montoCondonacionIntereses;
	}

	public boolean isCondonacionImportePorcentual() {
		return condonacionImportePorcentual;
	}

	public void setCondonacionImportePorcentual(boolean condonacionImportePorcentual) {
		this.condonacionImportePorcentual = condonacionImportePorcentual;
	}

	public boolean isCondonacionInteresPorcentual() {
		return condonacionInteresPorcentual;
	}

	public void setCondonacionInteresPorcentual(boolean condonacionInteresPorcentual) {
		this.condonacionInteresPorcentual = condonacionInteresPorcentual;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public Double getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	public void setTasaNominalAnual(Double tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	public Double getInteresPunitorio() {
		return interesPunitorio;
	}

	public void setInteresPunitorio(Double interesPunitorio) {
		this.interesPunitorio = interesPunitorio;
	}

	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}

	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}

	public Integer getCantidadDiasCese() {
		return cantidadDiasCese;
	}

	public void setCantidadDiasCese(Integer cantidadDiasCese) {
		this.cantidadDiasCese = cantidadDiasCese;
	}

	public Integer getCantidadCuotasCese() {
		return cantidadCuotasCese;
	}

	public void setCantidadCuotasCese(Integer cantidadCuotasCese) {
		this.cantidadCuotasCese = cantidadCuotasCese;
	}

	@Override
	public long getIdEntidad() {
		return this.idPlantillaPlanDePago;
	}

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String getNombrePropiedadId() {
		return "idPlantillaPlanDePago";
	}

	@Override
	public boolean isAuditable() {
		return true;
	}

	@Transient
	private String comentarioAuditoria;
	@Transient
	private long llaveUsuarioAuditoria;
	@Override
	public void setComentarioAuditoria(String pComentario) {
		this.comentarioAuditoria = pComentario;
	}

	@Override
	public void setLlaveUsuarioAuditoria(long pLlave) {
		this.llaveUsuarioAuditoria = pLlave;
	}

	@Override
	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	@Override
	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}
	
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

}
