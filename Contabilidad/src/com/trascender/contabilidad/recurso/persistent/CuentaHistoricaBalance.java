/**
 * 
 */
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
import javax.persistence.Transient;

@Entity
@Table(name = "CUENTA_HISTORICA_BALANCE")
public class CuentaHistoricaBalance implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -9004584810506000536L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_cuenta_historica_balance")
	@SequenceGenerator(name = "gen_id_cuenta_historica_balance", sequenceName = "gen_id_cuenta_historica_balance",allocationSize = 1)
	@Column(name="ID_CUENTA_HISTORICA_BALANCE")
	private long idCuentaHistoricoBalance = -1;
	private Double valor = 0d;
	private String nombre;
	private String abreviatura;
	
	@Column(name = "CODIGO_IMPUTACION")
	private String codigoImputacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_BALANCE")
	private Balance balance;
	
	@Transient
	private CuentaHistoricaBalance cuentaHistoricaBalanceAnterior;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_HISTORICA_BALANCE_ANT")
	private CuentaHistoricaBalance cuentaHistoricaBalancePosterior;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	@Column(name = "IMPORTE_PRESUPUESTADO")
	private Double importePresupuestado = 0d;
	
	private Double acumulado = 0d;
	
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public CuentaHistoricaBalance getCuentaHistoricaBalanceAnterior() {
		return cuentaHistoricaBalanceAnterior;
	}

	public CuentaHistoricaBalance getCuentaHistoricaBalancePosterior() {
		return cuentaHistoricaBalancePosterior;
	}

	public void setCuentaHistoricaBalancePosterior(
			CuentaHistoricaBalance cuentaHistoricaBalancePosterior) {
		this.cuentaHistoricaBalancePosterior = cuentaHistoricaBalancePosterior;
	}

	public long getIdCuentaHistoricoBalance() {
		return idCuentaHistoricoBalance;
	}

	public void setIdCuentaHistoricoBalance(long idCuentaHistoricoBalance) {
		this.idCuentaHistoricoBalance = idCuentaHistoricoBalance;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public  Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idCuentaHistoricoBalance == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCuentaHistoricoBalance ^ (idCuentaHistoricoBalance >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CuentaHistoricaBalance other = (CuentaHistoricaBalance) obj;
		if (idCuentaHistoricoBalance != other.idCuentaHistoricoBalance) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return this.codigoImputacion+" - "+this.nombre+" ($ "+this.valor+")"; 
	}
	
	public Double getImportePresupuestado() {
		return importePresupuestado;
	}
	public void setImportePresupuestado(Double importePresupuestado) {
		this.importePresupuestado = importePresupuestado;
	}
	public Double getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(Double acumulado) {
		this.acumulado = acumulado;
	}
	
}
