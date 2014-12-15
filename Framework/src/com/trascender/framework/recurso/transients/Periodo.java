package com.trascender.framework.recurso.transients;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERIODO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC")
@DiscriminatorValue(value="PER")
public class Periodo implements Serializable, Comparable<Periodo>, Cloneable{

	public static final long serialVersionUID = 5399459143645351036L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_periodo")
	@SequenceGenerator(name = "gen_id_periodo", sequenceName = "gen_id_periodo", allocationSize = 1)
	@Column(name = "ID_PERIODO")
	private long idPeriodo=-1;

	@Column(name = "FECHA_INICIO")
	private Calendar fechaInicio;

	@Column(name = "FECHA_FIN")
	private Calendar fechaFin;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "NOMBRE_REDUCIDO")
	private String nombreReducido;

	@Column(name = "NUMERO")
	private Integer numero=0;

	@ManyToOne
	@JoinColumn(name="ID_CALENDARIO")
	private Calendario calendario;

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public long getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(long pIdPeriodo) {
		idPeriodo = pIdPeriodo;
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Calendar pFechaFin) {
		fechaFin = pFechaFin;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar pFechaInicio) {
		fechaInicio = pFechaInicio;
	}

	public void setFechaInicio(Date pFechaInicio) {
		if(pFechaInicio != null){
			Calendar locFecha = Calendar.getInstance();
			locFecha.setTime(pFechaInicio);
			this.fechaInicio = locFecha;
		}
	}


	public void setFechaFin(Date pFechaFin) {
		if(pFechaFin != null){
			Calendar locFecha = Calendar.getInstance();
			locFecha.setTime(pFechaFin);
			this.fechaFin = locFecha;
		}
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombrePeriodo) {
		nombre = pNombrePeriodo;
	}

	public String getNombreReducido() {
		return nombreReducido;
	}

	public void setNombreReducido(String pNombreReducido) {
		nombreReducido = pNombreReducido;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer pNumero) {
		numero = pNumero;
	}

	public String getFechaInicioAsString(){
		if(fechaInicio == null){
			return "";
		}
		SimpleDateFormat locFormat = new SimpleDateFormat("dd/MM/yyyy");

		return locFormat.format(this.getFechaInicio());
	}

	public String getFechaFinAsString(){
		if(this.fechaFin == null){
			return "";
		}
		SimpleDateFormat locFormat = new SimpleDateFormat("dd/MM/yyyy");

		return locFormat.format(this.getFechaFin());
	}

	@Override
	public String toString() {
		return (this.getNombreReducido()!=null)?this.getNombreReducido():this.getNombre();
	}
	
	public int compareTo(Periodo pPeriodo){
		int intCalendario = this.getCalendario().compareTo(pPeriodo.getCalendario());
		if (intCalendario != 0) return intCalendario;
		return this.numero.compareTo(pPeriodo.numero);
	}

	@Override
	public Periodo clone() throws CloneNotSupportedException {
		Periodo locPeriodo = (Periodo) super.clone();
		//		locPeriodo.getCalendario().setIdCalendario(-1l);
		locPeriodo.setIdPeriodo(-1);
		return locPeriodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPeriodo ^ (idPeriodo >>> 32));
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
		Periodo other = (Periodo) obj;
		if (other.idPeriodo == -1 || this.idPeriodo == -1) {
			return this == obj;
		}
		if (idPeriodo != other.idPeriodo)
			return false;
		return true;
	}

}
