package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

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
@Table(name = "ARQUEO_CAJA")
public class ArqueoCaja implements Serializable{

	public static final long serialVersionUID = -7357093966033316787L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_arqueo_caja")
	@SequenceGenerator(name = "gen_id_arqueo_caja", sequenceName = "gen_id_arqueo_caja",allocationSize = 1)
	@Column(name="ID_ARQUEO_CAJA")
	private long idArqueoCaja=-1;
	private Integer cantidad;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_PLANILLA_DIARIA_CAJA")
	private PlanillaDiariaCaja planillaDiariaCaja;
	
	@ManyToOne
	@JoinColumn(name = "ID_MONEDA")
	private Moneda moneda;
	
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public long getIdArqueoCaja() {
		return idArqueoCaja;
	}
	public void setIdArqueoCaja(long idArqueoCaja) {
		this.idArqueoCaja = idArqueoCaja;
	}
	public PlanillaDiariaCaja getPlanillaDiariaCaja() {
		return planillaDiariaCaja;
	}
	public void setPlanillaDiariaCaja(PlanillaDiariaCaja planillaDiariaCaja) {
		this.planillaDiariaCaja = planillaDiariaCaja;
	}

}
