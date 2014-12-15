package com.trascender.habilitaciones.recurso.persistent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Periodicidad;

@Entity
@DiscriminatorValue(value="CAL_MUN")
public class CalendarioMunicipal extends Calendario implements EntidadTrascender{

	public static final long serialVersionUID = 2925734202060002961L;

	public CalendarioMunicipal(){	}

	public CalendarioMunicipal(String pNombre,
			Integer pAnio,
			Periodicidad pPeriodicidad,
			Calendar pFechaInicio, 
			Periodicidad pPeriodicidadDeLasCuotas,
			Integer pCuotasPorPeriodo){

		super(pNombre, pAnio, pPeriodicidad, pFechaInicio);

		Set<Periodo> locListaPeriodos = new HashSet<Periodo>();

		for(Periodo cadaPeriodo : this.getListaPeriodos()){
			PeriodoLiquidacion locPeriodo = new PeriodoLiquidacion();
			locPeriodo.setFechaInicio(cadaPeriodo.getFechaInicio());
			locPeriodo.setFechaFin(cadaPeriodo.getFechaFin());
			locPeriodo.setNombre(cadaPeriodo.getNombre());
			locPeriodo.setNumero(cadaPeriodo.getNumero());
			locPeriodo.setPeriodicidad(pPeriodicidadDeLasCuotas);
			locPeriodo.setCalendario(this);
			SimpleDateFormat format = new SimpleDateFormat();
			format.applyPattern("dd/MM/yyyy");

			locPeriodo.setPorcentajePeriodo( (double)locPeriodo.getFechaInicio().get(Calendar.DAY_OF_YEAR) 
					/ (double)locPeriodo.getFechaFin().get(Calendar.DAY_OF_YEAR)	);

			if(pCuotasPorPeriodo != null){
				Calendar locFechaInicio = (Calendar) locPeriodo.getFechaInicio().clone();
				Calendar locFechaFin = Calendar.getInstance();
				for(int i=0; i< pCuotasPorPeriodo; i++){
					CuotaLiquidacion cadaCuota = new CuotaLiquidacion();
					cadaCuota.setNumero(i+1);
					cadaCuota.setPeriodo(locPeriodo);

					//						cadaCuota.setFechaInicio((Calendar) locFechaInicio.clone());

					locFechaFin.setTime((Date) locFechaInicio.getTime().clone());
					locFechaFin.add(Calendar.DAY_OF_MONTH, pPeriodicidadDeLasCuotas.getDias() - 1);
					locFechaFin.add(Calendar.MONTH, pPeriodicidadDeLasCuotas.getMeses());
					locFechaFin.add(Calendar.YEAR, pPeriodicidadDeLasCuotas.getAños());
					//							cadaCuota.setFechaFin((Calendar) locFechaFin.clone());

					//					cadaCuota.setFechaFin(locFechaFin);

					//					cadaCuota.setNombre(cadaCuota.toString());

					//					locFechaInicio.setTime((Date) cadaCuota.getFechaFin().getTime().clone());
					locFechaInicio.add(Calendar.DAY_OF_MONTH, 1);


					locPeriodo.addCuotaLiquidacion(cadaCuota);
				}
				locListaPeriodos.add(locPeriodo);
			}
		}

		this.setListaPeriodos(locListaPeriodos);
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PLAN", nullable=false)
	private Plan plan;

	public TipoObligacion getTipoObligacion() {
		return ((this.plan != null)?this.plan.getTipoObligacion():null);
	}

	/**
	 * Devuelve la primer Cuota del Primer Periodo.<br>
	 * Se entiende que todo calendario debe tener al menos 1 periodo y cada periodo al menos 1 cuota.
	 * @return
	 * @throws Exception 
	 */
	@Transient
	public CuotaLiquidacion getPrimerCuota() throws Exception{
		return this.findPeriodo(null, 1).getCuotaLiquidacion(1);
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public PeriodoLiquidacion findPeriodo(Calendar pFecha, Integer pNroPeriodo) {
		// TODO Auto-generated method stub
		return (PeriodoLiquidacion) super.findPeriodo(pFecha, pNroPeriodo);
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
		return this.getIdCalendario();
	}

	public String getNombrePropiedadId() {
		return "idCalendario";
	}

	public boolean isAuditable() {
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CalendarioMunicipal locClon = (CalendarioMunicipal) super.clone();

		Set<Periodo> locListaPeriodos = new HashSet<Periodo>();
		for (Periodo cadaPeriodo : locClon.getListaPeriodos()){
			Periodo locPeriodo = cadaPeriodo.clone();
			locPeriodo.setCalendario(locClon);
			locListaPeriodos.add(locPeriodo);
		}
		locClon.setListaPeriodos(locListaPeriodos);
		return locClon;
	}

}
