package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PARAMETRO_REPORTE_CONTABLE")
public class ParametroReporteContable implements Serializable{
	
	public static final long serialVersionUID = 6155966271951148400L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_parametro_reporte_contable")
	@SequenceGenerator(name = "gen_id_parametro_reporte_contable", sequenceName = "gen_id_parametro_reporte_contable",allocationSize = 1)
	@Column(name="ID_PARAMETRO_REPORTE_CONTABLE")
	private long idParametroReporteContable=-1;
	
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name = "ID_REPORTE_CONTABLE")
	private ReporteContable reporteContable;
	
	private boolean requerido = false;
	
	public enum Tipo {
		FECHA, NUMÉRICO, CADENA, PLANTILLA_BALANCE, CUENTA, ÁREA, SECRETARÍA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super
					.toString());
		}
	}

	public long getIdParametroReporteContable() {
		return idParametroReporteContable;
	}

	public void setIdParametroReporteContable(long idParametroReporteContable) {
		this.idParametroReporteContable = idParametroReporteContable;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public ReporteContable getReporteContable() {
		return reporteContable;
	}

	public void setReporteContable(ReporteContable reporteContable) {
		this.reporteContable = reporteContable;
	}
	
	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idParametroReporteContable ^ (idParametroReporteContable >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ParametroReporteContable other = (ParametroReporteContable) obj;
		if (idParametroReporteContable != other.idParametroReporteContable) {
			return false;
		}
		return true;
	}
	
}
