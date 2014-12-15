package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "LINEA_TIPO_PARAMETRO_CONSTANTE")
public class LineaTipoParametroConstante implements Serializable{
	private static final long serialVersionUID = -450876910378212239L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_tipo_parametro_constante")
	@SequenceGenerator(name = "gen_id_linea_tipo_parametro_constante", sequenceName = "gen_id_linea_tipo_parametro_constante",allocationSize = 1)
	@Column(name="ID_LINEA_TIPO_PARAMETRO_CONSTANTE")
	private long idLineaTipoParametroConstante = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PARAMETRO_CONSTANTE")
	private TipoParametroConstante tipoParametroConstante;
	
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	private Double valor;
	
	public long getIdLineaTipoParametroConstante() {
		return idLineaTipoParametroConstante;
	}
	public void setIdLineaTipoParametroConstante(long idLineaTipoParametroConstante) {
		this.idLineaTipoParametroConstante = idLineaTipoParametroConstante;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public TipoParametroConstante getTipoParametroConstante() {
		return tipoParametroConstante;
	}
	public void setTipoParametroConstante(
			TipoParametroConstante tipoParametroConstante) {
		this.tipoParametroConstante = tipoParametroConstante;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaTipoParametroConstante ^ (idLineaTipoParametroConstante >>> 32));
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
		LineaTipoParametroConstante other = (LineaTipoParametroConstante) obj;
		if (this.idLineaTipoParametroConstante == -1 && other.idLineaTipoParametroConstante == -1){
			return this == other;
		}
		if (idLineaTipoParametroConstante != other.idLineaTipoParametroConstante)
			return false;
		return true;
	}
	
}
