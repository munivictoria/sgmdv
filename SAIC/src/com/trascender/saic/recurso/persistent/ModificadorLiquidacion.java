package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

@Entity
@Table(name = "MODIFICADOR_LIQUIDACION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_MODIFICADOR", discriminatorType = DiscriminatorType.STRING)
public abstract class ModificadorLiquidacion implements Serializable, Cloneable {

	public static final long serialVersionUID = 5008745878514244114L;

	@Id
	@Column(name = "ID_MODIFICADOR_LIQUIDACION")
	@SequenceGenerator(name = "gen_id_modificador_liquidacion", allocationSize = 1, sequenceName = "gen_id_modificador_liquidacion")
	@GeneratedValue(generator = "gen_id_modificador_liquidacion", strategy = GenerationType.SEQUENCE)
	protected long idModificadorLiquidacion = -1;

	protected String nombre;

	@Column(name = "VALOR", nullable = false)
	protected Double valorModificador;

	@Transient
	private LiquidacionTasa liquidacionTasa;
	
	public abstract boolean isSobreSaldoNeto();
	
	public abstract TipoModificador.EnumTipoModificador getEnumTipoModificador();
	
	public ModificadorLiquidacion clone() throws CloneNotSupportedException{
		return (ModificadorLiquidacion) super.clone();
	}
	
	public long getIdModificadorLiquidacion() {
		return idModificadorLiquidacion;
	}

	public void setIdModificadorLiquidacion(long idModificadorLiquidacion) {
		this.idModificadorLiquidacion = idModificadorLiquidacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Es el valor en $ del moficador para esta liquidación específica
	 * 
	 * @hibernate.property not-null = "true" column = "VALOR"
	 */
	public Double getValorModificador() {
		return valorModificador;
	}

	public void setValorModificador(Double valorModificador) {
		this.valorModificador = valorModificador;
	}

	public LiquidacionTasa getLiquidacionTasa() {
		return liquidacionTasa;
	}

	public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
		this.liquidacionTasa = liquidacionTasa;
	}

}
