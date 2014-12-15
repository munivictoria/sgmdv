/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;

@Entity
@Table(name = "CUENTA_BANCARIA")
public class CuentaBancaria implements Serializable{

	/**
	 * 
	 */
	public  static final long serialVersionUID = 6717900040898704365L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_cuenta_bancaria")
	@SequenceGenerator(name = "gen_id_cuenta_bancaria", sequenceName = "gen_id_cuenta_bancaria",allocationSize = 1)
	@Column(name="ID_CUENTA_BANCARIA")
	private long idCuentaBancaria =-1;
	
	@Column(name = "TIPO_CUENTA")
	private String tipoCuenta;
	private String numero;
	private boolean propia;
	//Relacion con otros objetos 
	
	@OneToMany(mappedBy = "cuentaBancaria", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set <BoletaDeposito> boletasDeposito = new HashSet<BoletaDeposito>();
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona titularCuentaBancaria;
	
	@ManyToOne
	@JoinColumn(name = "ID_BANCO")
	private Banco banco;
	
	@OneToMany(mappedBy = "cuentaBancaria", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LibroBanco> librosBanco = new HashSet<LibroBanco>();
	
	@OneToMany(mappedBy = "cuentaBancaria", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MovimientoBancario> listaMovimientoBancario = new HashSet<MovimientoBancario>();
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Set<BoletaDeposito> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(Set<BoletaDeposito> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public long getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(long idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isPropia() {
		return propia;
	}

	public void setPropia(boolean propia) {
		this.propia = propia;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public Set<LibroBanco> getLibrosBanco() {
		return librosBanco;
	}

	public void setLibrosBanco(Set<LibroBanco> librosBanco) {
		this.librosBanco = librosBanco;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.idCuentaBancaria == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCuentaBancaria ^ (idCuentaBancaria >>> 32));
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
		final CuentaBancaria other = (CuentaBancaria) obj;
		if (idCuentaBancaria != other.idCuentaBancaria) {
			return false;
		}
		return true;
	}

	public Persona getTitularCuentaBancaria() {
		return titularCuentaBancaria;
	}
	public void setTitularCuentaBancaria(Persona titularCuentaBancaria) {
		this.titularCuentaBancaria = titularCuentaBancaria;
	}
	
	@Override
	public String toString(){
		String linea = this.numero;
		if (this.banco != null){
			linea += " - "+this.banco;
		}
		return linea;
	}
	
	public Set<MovimientoBancario> getListaMovimientoBancario() {
		return listaMovimientoBancario;
	}
	public void setListaMovimientoBancario(
			Set<MovimientoBancario> listaMovimientoBancario) {
		this.listaMovimientoBancario = listaMovimientoBancario;
	}
	
}
