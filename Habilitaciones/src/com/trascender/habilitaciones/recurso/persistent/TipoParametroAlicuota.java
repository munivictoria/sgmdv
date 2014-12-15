package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

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


@Entity
@Table(name = "TIPO_PARAMETRO_ALICUOTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class TipoParametroAlicuota implements Serializable{
	private static final long serialVersionUID = 4224356256813159146L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_param_alicuota")
	@SequenceGenerator(name = "gen_id_tipo_param_alicuota", sequenceName = "gen_id_tipo_param_alicuota",allocationSize = 1)
	@Column(name="id_tipo_param_alicuota")
	private long idTipoParametroAlicuota = -1;

	@Column(name = "NOMBRE")
	private String nombreVariable;

	@Transient
	private CuotaLiquidacion cuotaLiquidacion;

	public enum TIPOS_PARAMETRO{ALICUOTA_SHPS, PARCELA_CEMENTERIO, TIPO_SEPULTURA, SERVICIOS_OSP;
	@Override
	public String toString() {
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}};

	public abstract Object getValor(AsocRegAlicuota pAsocRegAlicuota);
	
	public abstract void setNombreAtributo(String pNombreAtributo);

	public CuotaLiquidacion getCuotaLiquidacion() {
		return cuotaLiquidacion;
	}

	public void setCuotaLiquidacion(CuotaLiquidacion cuotaLiquidacion) {
		this.cuotaLiquidacion = cuotaLiquidacion;
	}

	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String pNombreVariable) {
		this.nombreVariable = pNombreVariable;
	}
	public long getIdTipoParametroAlicuota() {
		return idTipoParametroAlicuota;
	}
	public void setIdTipoParametroAlicuota(long idTipoParametroAlicuota) {
		this.idTipoParametroAlicuota = idTipoParametroAlicuota;
	}
	@Override
	public String toString(){
		return this.nombreVariable;
	}

}
