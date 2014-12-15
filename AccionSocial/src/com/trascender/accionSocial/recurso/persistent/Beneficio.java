package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.Util;

@Entity
@Table(name="BENEFICIO")
public class Beneficio implements Serializable{
	
	/**
	 * Tipos Beneficio: PLAN, INSUMO
	 */
	public enum TipoBeneficio{
		PLAN, INSUMO;
		
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}
	
	public static final long serialVersionUID = -1244804885624045904L;
	
	@Id
	@GeneratedValue(generator="gen_id_beneficio",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name="gen_id_beneficio", sequenceName="gen_id_beneficio")
	@Column(name="ID_BENEFICIO")
	private long idBeneficio = -1;
	
	private String nombre;
	private String descripcion;
	private Double monto;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_BENEFICIO")
	private TipoBeneficio tipoBeneficio;
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(long idBeneficio) {
		this.idBeneficio = idBeneficio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public TipoBeneficio getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}
	
	public String toString(){
		return "Nombre: " + this.nombre+", Tipo: " + this.tipoBeneficio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBeneficio ^ (idBeneficio >>> 32));
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
		Beneficio other = (Beneficio) obj;
		if (idBeneficio != other.idBeneficio)
			return false;
		return true;
	}
	
}
