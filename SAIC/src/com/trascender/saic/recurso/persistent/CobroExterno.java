package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="COBRO_EXTERNO")
public class CobroExterno implements Serializable{
	
	public static final long serialVersionUID = 668591012051154624L;
	
	@Id
	@SequenceGenerator(name = "gen_id_cobro_externo", sequenceName = "gen_id_cobro_externo", allocationSize = 1)
	@GeneratedValue(generator = "gen_id_cobro_externo",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID_COBRO_EXTERNO")
	private long idCobroExterno = -1;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name = "ID_LIQUIDACION")
	private LiquidacionTasa liquidacionTasa;
	
	@Column(name="IMPORTE_PAGADO")
	private Double importePagado;
	
	@Column(name="FECHA_PAGO")
	private Date fechaPago;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ENTIDAD_RECAUDADORA")
	private EntidadRecaudadora entidadRecaudadora;
	
	public enum EntidadRecaudadora{
		PAGOFACIL, LINK;
		
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	public long getIdCobroExterno() {
		return idCobroExterno;
	}

	public void setIdCobroExterno(long idCobroExterno) {
		this.idCobroExterno = idCobroExterno;
	}

	public LiquidacionTasa getLiquidacionTasa() {
		return liquidacionTasa;
	}

	public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
		this.liquidacionTasa = liquidacionTasa;
	}

	public Double getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(Double importePagado) {
		this.importePagado = importePagado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public EntidadRecaudadora getEntidadRecaudadora() {
		return entidadRecaudadora;
	}

	public void setEntidadRecaudadora(EntidadRecaudadora entidadRecaudadora) {
		this.entidadRecaudadora = entidadRecaudadora;
	}
}
