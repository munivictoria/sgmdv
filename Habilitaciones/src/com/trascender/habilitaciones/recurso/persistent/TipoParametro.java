package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.recurso.transients.Periodo;

@Entity
@Table(name = "TIPO_PARAMETRO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class TipoParametro implements Serializable, Cloneable{

	private static final long serialVersionUID = 8767584828582300196L;

	/**
	 * Tipos posibles{SHPS,OSP,CONSTANTE,OBRA,PARCELARIO,GRUPO_ZONA,PERSONA,VENCIMIENTO,INTERES, CEMENTERIO}
	 */
	public enum TIPOS_PARAMETRO{TGI,SHPS,OSP,CONSTANTE,OBRA,PARCELARIO,GRUPO_ZONA,PERSONA,VENCIMIENTO,INTERES, CEMENTERIO, AUTOMOTOR, VEHICULO, DEUDA, GRILLA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tipo_parametro")
	@SequenceGenerator(name = "gen_id_tipo_parametro", sequenceName = "gen_id_tipo_parametro", allocationSize = 1)
	@Column(name = "ID_TIPO_PARAMETRO")
	private Long idTipoParametro=-1L;
	
	@Column(name = "NOMBRE_VARIABLE")
	private String nombreVariable;
	
	private boolean activo=true;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@Column(name = "BOOLE")
	private boolean booleano=false;
	
	public abstract Object getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado) throws Exception;

	@Transient
	private Periodo periodoLiquidacion;
	
	@Transient
	private CuotaLiquidacion cuotaLiquidacion;

	
	public CuotaLiquidacion getCuotaLiquidacion() {
		return cuotaLiquidacion;
	}
	public void setCuotaLiquidacion(CuotaLiquidacion cuotaLiquidacion) {
		this.cuotaLiquidacion = cuotaLiquidacion;
	}
	public Long getIdTipoParametro() {
		return idTipoParametro;
	}
	public void setIdTipoParametro(Long idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}
	
	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Override
	public TipoParametro clone() throws CloneNotSupportedException {
		//DEJO QUE SEAN CLONABLES PARA PODER MANTENER MÁS FACILMENTE EL HISTÓRICO
		TipoParametro locTipoParametro=(TipoParametro)super.clone();
		locTipoParametro.setIdTipoParametro(null);
		
//		if (locTipoParametro.getFechaAlta()!=null){
//			locTipoParametro.setFechaAlta((Date)locTipoParametro.getFechaAlta().clone());
//		}
		Calendar locCalendar = Calendar.getInstance();
		locTipoParametro.setFechaAlta(locCalendar.getTime());
		
		if (locTipoParametro.getFechaBaja()!=null){
			locTipoParametro.setFechaBaja((Date)locTipoParametro.getFechaBaja().clone());
		}
		return locTipoParametro;
	}

	public boolean isBooleano() {
		return booleano;
	}
	public void setBooleano(boolean booleano) {
		this.booleano = booleano;
	}

	@Override
	public String toString() {
		return (this.getNombreVariable()!=null)?this.getNombreVariable():"";
	}
	
	/**
	 * El período de liquidación solo sirve para ser utilizado mientras se calculan las tasas.
	 */
	public Periodo getPeriodoLiquidacion() {
		return periodoLiquidacion;
	}
	public void setPeriodoLiquidacion(Periodo periodoLiquidacion) {
		this.periodoLiquidacion = periodoLiquidacion;
	}
	
	public abstract void setNombreAtributo(String pNombreAtributo);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + (booleano ? 1231 : 1237);
		result = prime * result
				+ ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result
				+ ((fechaBaja == null) ? 0 : fechaBaja.hashCode());
		result = prime * result
				+ ((nombreVariable == null) ? 0 : nombreVariable.hashCode());
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
		TipoParametro other = (TipoParametro) obj;
		if (activo != other.activo)
			return false;
		if (booleano != other.booleano)
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (fechaBaja == null) {
			if (other.fechaBaja != null)
				return false;
		} else if (!fechaBaja.equals(other.fechaBaja))
			return false;
		if (nombreVariable == null) {
			if (other.nombreVariable != null)
				return false;
		} else if (!nombreVariable.equals(other.nombreVariable))
			return false;
		return true;
	}
}
