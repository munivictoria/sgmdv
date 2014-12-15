package com.trascender.catastro.recurso.persistent;

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
@Table(name = "VOLANTE_CATASTRAL")
public class VolanteCatastral implements Serializable {

	public static final long serialVersionUID = -847647608163976899L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_volante_catastral")
	@SequenceGenerator(name ="gen_id_volante_catastral", sequenceName = "gen_id_volante_catastral", allocationSize = 1)
	@Column(name = "ID_VOLANTE_CATASTRAL")
	private long idVolanteCatastral=-1;
	
	private Date fecha;
	
	@Column(name = "NRO_VOLANTE_CATASTRAL")
	private Integer nroVolanteCatastral;
	
	private boolean radio;
	
	@Column(name = "AVALUO_TOTAL_TERRENO")
	private Double avaluoTotalTerreno;
	
	@Column(name = "AVALUO_TOTAL_MEJORAS")
	private Double avaluoTotalMejoras;
	
	@ManyToOne
	@JoinColumn(name = "ID_PARCELA")
	private Parcela parcela;
	
	public Double getAvaluoTotalMejoras() {
		return avaluoTotalMejoras;
	}

	public void setAvaluoTotalMejoras(Double avaluoTotalMejoras) {
		this.avaluoTotalMejoras = avaluoTotalMejoras;
	}

	public Double getAvaluoTotalTerreno() {
		return avaluoTotalTerreno;
	}

	public void setAvaluoTotalTerreno(Double avaluoTotalTerreno) {
		this.avaluoTotalTerreno = avaluoTotalTerreno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getIdVolanteCatastral() {
		return idVolanteCatastral;
	}

	public void setIdVolanteCatastral(long idVolanteCatastral) {
		this.idVolanteCatastral = idVolanteCatastral;
	}

	public Integer getNroVolanteCatastral() {
		return nroVolanteCatastral;
	}

	public void setNroVolanteCatastral(Integer nroVolanteCatastral) {
		this.nroVolanteCatastral = nroVolanteCatastral;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public boolean getRadio() {
		return radio;
	}

	public void setRadio(boolean radio) {
		this.radio = radio;
	}

	@Override
	public String toString() {
		return this.getNroVolanteCatastral().toString();
	}		
}
