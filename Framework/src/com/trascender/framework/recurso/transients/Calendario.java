package com.trascender.framework.recurso.transients;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;

@Entity
@Table(name="CALENDARIO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC")
@DiscriminatorValue(value="CAL")
public class Calendario implements Serializable, Cloneable, Comparable<Calendario> {

	public static final long serialVersionUID = -4620417591246191763L;

	/**.
	 * @version 1.0 - Es posible que el calendario requiera mas valores que ACTIVO E INACTIVO.
	 * Estados posibles: ACTIVO, INACTIVO
	 */
	public enum EstadoCalendario{
		ACTIVO,
		INACTIVO;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	@Id
	@GeneratedValue(generator="gen_id_calendario", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize=1, name="gen_id_calendario", sequenceName="gen_id_calendario")
	@Column(name="ID_CALENDARIO")
	private Long idCalendario = -1l;

	private Integer anio;

	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoCalendario estado = EstadoCalendario.INACTIVO;

	@OrderBy(value="numero")
	@OneToMany(mappedBy="calendario", cascade=CascadeType.ALL)
	private Set<Periodo> listaPeriodos = new HashSet<Periodo>();

	@Enumerated(EnumType.STRING)
	private Periodicidad periodicidad;

	public Calendario(){}

	/**
	 * Ademas de setear los valores genera los periodos anuales para cada tipo de periodicidad.
	 * @param pNombre
	 * @param pAnio
	 * @param pPeriodicidad
	 * @param pFechaInicio Por defecto toma el primer dia del mes 1 del anio pasado por parametro.
	 */
	public Calendario(String pNombre, Integer pAnio, Periodicidad pPeriodicidad, Calendar pFechaInicio){
		this.nombre = pNombre;
		this.anio = pAnio;
		this.periodicidad = pPeriodicidad;

		Calendar locFechaInicio = Calendar.getInstance();
		locFechaInicio.set(pAnio, 0, 1);
		if(pFechaInicio != null){
			locFechaInicio.setTime((Date) pFechaInicio.getTime().clone());
		}

		Calendar locFechaFin  = Calendar.getInstance();
		locFechaFin.set(pAnio, 11, 31);

		SimpleDateFormat locDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		int i = 0;
		Periodo locPeriodo = null;
		do{
			locPeriodo = new Periodo();
			locPeriodo.setNumero( (i)+1);
			locPeriodo.setFechaInicio((Calendar) locFechaInicio.clone());

			locFechaFin.setTime((Date) locFechaInicio.getTime().clone());
			locFechaFin.add(Calendar.DAY_OF_MONTH, pPeriodicidad.getDias() - 1);
			locFechaFin.add(Calendar.MONTH, pPeriodicidad.getMeses());
			locFechaFin.add(Calendar.YEAR, pPeriodicidad.getAños());
			locPeriodo.setFechaFin((Calendar) locFechaFin.clone());

			locPeriodo.setNombre("Periodo "+ locPeriodo.getNumero() +" del " + locDateFormat.format(locFechaInicio.getTime()) +
					" Al " + locDateFormat.format(locFechaFin.getTime()));

			locFechaInicio.setTime((Date) locPeriodo.getFechaFin().getTime().clone());
			locFechaInicio.add(Calendar.DAY_OF_MONTH, 1);

			this.addPeriodo(locPeriodo);
			i++;
		}while(i < Periodicidad.getCantidadPeriodosPorAnio(pPeriodicidad));
	}


	public void addPeriodo(Periodo pPeriodo){
		if(!listaPeriodos.contains(pPeriodo)){
			pPeriodo.setCalendario(this);
			this.listaPeriodos.add(pPeriodo);
		}
	}

	/**
	 * Busca un periodo segun los parametros dados
	 * @param pFecha
	 * @param pNroPeriodo
	 * @return El period o null si no se encuentra o ambos parametros son nulos.
	 */
	public Periodo findPeriodo(Calendar pFecha, Integer pNroPeriodo){
		for(Periodo cadaPeriodo : this.listaPeriodos){
			boolean soloFecha = pNroPeriodo == null && pFecha != null;
			boolean soloNumero = pFecha == null && pNroPeriodo != null;

			//				if((soloFecha && soloNumero)){
			//					return null;
			//				}

			if(soloFecha && Util.isFechaBetween(pFecha, cadaPeriodo.getFechaInicio(), cadaPeriodo.getFechaFin())){
				return cadaPeriodo;
			}

			if(soloNumero && cadaPeriodo.getNumero().equals(pNroPeriodo)){
				return cadaPeriodo;
			}

			if(pFecha != null && Util.isFechaBetween(pFecha, cadaPeriodo.getFechaInicio(), cadaPeriodo.getFechaFin())
					&& cadaPeriodo.getNumero().equals(pNroPeriodo)){
				return cadaPeriodo;
			}
		}

		return null;
	}

	public Periodicidad getPeriodicidad(){
		return this.periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad){
		this.periodicidad = periodicidad;
	}

	public Long getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(Long pIdCalendario) {
		idCalendario = pIdCalendario;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer pAnio) {
		anio = pAnio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public Set<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(Set<Periodo> pListaPeriodos) {
		listaPeriodos = pListaPeriodos;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Calendario locClon = (Calendario) super.clone();
		locClon.setIdCalendario(-1l);

		return locClon;
	}

	public EstadoCalendario getEstado() {
		return estado;
	}

	public void setEstado(EstadoCalendario pEstado) {
		estado = pEstado;
	}

	private int getUltimaCuotaPeriodoAnterior(int pNumeroPeriodo) {
		Periodo locPeriodo = this.findPeriodo(null, pNumeroPeriodo);

		return 0;
	}

	@Override
	public String toString() {
		return this.nombre + " ["+ this.anio + "]";
	}

	public int compareTo(Calendario pCalendario) {
		return this.anio.compareTo(pCalendario.anio);
	}

}
