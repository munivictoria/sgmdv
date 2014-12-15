package com.trascender.saic.recurso.persistent;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.saic.recurso.interfaces.Pagable;

@Entity
@Table(name = "PAGO_SELLADO")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
public class PagoSellado extends RegistroDeuda implements Pagable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DOC_HABILITANTE", nullable = false)
	private Sellado sellado;

	public PagoSellado() {
		super();
		this.setFechaEmision(Calendar.getInstance().getTime());
	}

	public PagoSellado(Sellado pSellado, DocGeneradorDeuda pDocGeneradorDeuda) {
		this();
		this.sellado = pSellado;
		this.setDocGeneradorDeuda(pDocGeneradorDeuda);

	}

	public PagoSellado(Sellado pSellado) {
		this();
		this.sellado = pSellado;
	}

	public String getNombre() {
		if (this.sellado != null) {
			return this.sellado.getNombre();
		}
		return "";
	}

	public Persona getPersona() {
		if (this.sellado != null) {
			return this.getDocGeneradorDeuda().getObligacion().getPersona();
		}
		return null;
	}

	public Double getValor() {
		if (this.sellado != null) {
			return this.sellado.getValor();
		}
		return 0d;
	}


	public Sellado getSellado() {
		return sellado;
	}

	public void setSellado(Sellado sellado) {
		this.sellado = sellado;
	}

	@Override
	public void setRegistroCancelacion(RegistroCancelacion pRegistroCancelacion) {
		super.setRegistroCancelacion(pRegistroCancelacion);
		if (pRegistroCancelacion != null) {
			boolean locPagado = this.sellado.habilitar();
			this.sellado.setPagado(locPagado);
		}
	}

	public String toString() {
		if (this.getNombre() != null) {
			return this.getNombre()
					+ ((this.getRegistroCancelacion() != null) ? " [PAGADO]"
							: "");
		}
		return "";
	}

	@Override
	public Date getFechaVencimiento() {
		return this.getFechaEmision();
	}

	@Override
	public Double getInteres() {
		return 0d;
	}

	@Override
	public Double getMultas() {
		return 0d;
	}

	@Override
	public CuotaLiquidacion getCuotaLiquidacion(){
		return null;
	}

	@Override
	public Double getRecargo() {
		return 0d;
	}

}
