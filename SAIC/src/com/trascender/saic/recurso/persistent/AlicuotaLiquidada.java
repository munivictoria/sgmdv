package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;


@Entity
@Table(name = "ALICUOTA_LIQUIDADA")
public class AlicuotaLiquidada implements Serializable {
	private static final long serialVersionUID = -6511964469084575524L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_alicuota_liquidada")
	@SequenceGenerator(name = "gen_id_alicuota_liquidada", sequenceName = "gen_id_alicuota_liquidada",allocationSize = 1)
	@Column(name="ID_ALICUOTA_LIQUIDADA")
	private long idAlicuotaLiquidada = -1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REG_ALICUOTA")
	private RegAlicuota regAlicuota;
	
	@OneToMany(mappedBy = "alicuotaLiquidada", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ParametroValuadoAlicuota> listaParametrosValuados = new ArrayList<ParametroValuadoAlicuota>();
	
	private Double valor;

	public long getIdAlicuotaLiquidada() {
		return idAlicuotaLiquidada;
	}

	public void setIdAlicuotaLiquidada(long idAlicuotaLiquidada) {
		this.idAlicuotaLiquidada = idAlicuotaLiquidada;
	}

	public RegAlicuota getRegAlicuota() {
		return regAlicuota;
	}

	public void setRegAlicuota(RegAlicuota regAlicuota) {
		this.regAlicuota = regAlicuota;
	}

	public void addParametroValuado(ParametroValuadoAlicuota pParametroValuado){
		pParametroValuado.setAlicuotaLiquidada(this);
		this.listaParametrosValuados.add(pParametroValuado);
	}

	public List<ParametroValuadoAlicuota> getListaParametrosValuados() {
		return listaParametrosValuados;
	}

	public void setListaParametrosValuados(
			List<ParametroValuadoAlicuota> listaParametrosValuados) {
		this.listaParametrosValuados = listaParametrosValuados;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String toString(){
		return regAlicuota.getNombre()+", $" +valor; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((regAlicuota == null)? 0 : regAlicuota.hashCode());
		for (ParametroValuadoAlicuota cadaParametroValuado : this.listaParametrosValuados){
			result = prime * result	+ cadaParametroValuado.hashCode();
		}
		result = prime * result	+ (valor != null ? valor.hashCode() : 0);
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
		AlicuotaLiquidada other = (AlicuotaLiquidada) obj;
		if (idAlicuotaLiquidada != other.idAlicuotaLiquidada)
			return false;
		return true;
	}
	
	

}
