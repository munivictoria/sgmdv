package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "REGISTRO_VALUADO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_REGISTRO_VALUADO")
public class RegistroValuado implements Serializable{

	/**
	 * 
	 */
	public enum Estado{CARGADO, LIQUIDADO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	public static final long serialVersionUID = -94796613518309892L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_registro_valuado")
	@SequenceGenerator(name = "gen_id_registro_valuado", sequenceName = "gen_id_registro_valuado", allocationSize = 1)
	@Column(name = "ID_REGISTRO_VALUADO")
	private long idRegistroValuado=-1;
	
	@Column(name = "MONTO_IMPONIBLE")
	private Double montoImponible;
	
	@Transient
	private Double lectura=0D;
	
	private Date fecha = new Date();
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "ID_CUOTA_LIQUIDACION")
	private CuotaLiquidacion cuotaLiquidacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REGISTRO_VALUADO_ANTERIOR")
	private RegistroValuado registroValuadoAnterior;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO")
	private DocHabilitanteEspecializado docHabilitanteEspecializado;
	
	@ManyToOne
	@JoinColumn(name = "ID_REG_ALICUOTA")
	private RegAlicuota regAlicuota;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public RegAlicuota getRegAlicuota() {
		return regAlicuota;
	}
	
	public void setRegAlicuota(RegAlicuota regAlicuota) {
		this.regAlicuota = regAlicuota;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public long getIdRegistroValuado() {
		return idRegistroValuado;
	}
	public void setIdRegistroValuado(long idRegistroValuado) {
		this.idRegistroValuado = idRegistroValuado;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRegistroValuado ^ (idRegistroValuado >>> 32));
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
		final RegistroValuado other = (RegistroValuado) obj;
		if (idRegistroValuado != other.idRegistroValuado)
			return false;
		return true;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Double getMontoImponible() {
		try {
			if (this instanceof ValorMedidor && this.montoImponible == null) {
				this.setMontoImponible(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return montoImponible;
		
	}
	
	public void setMontoImponible(Double pMontoImponible) {
		try{
			if(pMontoImponible != null){
				this.montoImponible = pMontoImponible;
			}
			else{
				if(this instanceof ValorMedidor){
					if(this.getRegistroValuadoAnterior() != null){
						this.montoImponible = this.getLectura().doubleValue() - this.getRegistroValuadoAnterior().getLectura().doubleValue();
						
					}
					else{
						this.montoImponible = this.getLectura().doubleValue();
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Double getLectura() {
		if(lectura != null){
			return lectura;
		}
		return 0D;
	}

	public void setLectura(Double pLectura) {
		this.lectura = pLectura;
	}
	public CuotaLiquidacion getCuotaLiquidacion() {
		return cuotaLiquidacion;
	}

	public void setCuotaLiquidacion(CuotaLiquidacion cuotaLiquidacion) {
		this.cuotaLiquidacion = cuotaLiquidacion;
	}

	public RegistroValuado getRegistroValuadoAnterior() {
		return registroValuadoAnterior;
	}
	public void setRegistroValuadoAnterior(RegistroValuado registroValuadoAnterior) {
		this.registroValuadoAnterior = registroValuadoAnterior;
	}
	
	public DocHabilitanteEspecializado getDocHabilitanteEspecializado() {
		return docHabilitanteEspecializado;
	}
	public void setDocHabilitanteEspecializado(
			DocHabilitanteEspecializado docHabilitanteEspecializado) {
		this.docHabilitanteEspecializado = docHabilitanteEspecializado;
	}
	
	public Double getStringLecturaAnterior(){
		if(this.getRegistroValuadoAnterior() != null){
			return this.getRegistroValuadoAnterior().getLectura();
			
		}
		return 0d;
	}
	public String getStringObligacion(){
		if(this.getDocHabilitanteEspecializado() != null){
			return this.getDocHabilitanteEspecializado().getObligacion().toString();
		}else return null;
	}
	
}
