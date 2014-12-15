/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOLETA_DEPOSITO")
public class BoletaDeposito implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1684217289733755535L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_boleta_deposito")
	@SequenceGenerator(name = "gen_id_boleta_deposito", sequenceName = "gen_id_boleta_deposito",allocationSize = 1)
	@Column(name="ID_BOLETA_DEPOSITO")
	private long idBoletaDeposito = -1;
	private Date fecha;
	
	@Column(name = "NUMERO_BOLETA")
	private String numeroBoleta;
	private Double importe;
	private String observaciones;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_BANCARIA")
	private CuentaBancaria cuentaBancaria;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_LIBRO_BANCO")
	private LineaLibroBanco lineaLibroBanco;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RELA_DEPOSITO_MOV_EGRESO", joinColumns=@JoinColumn(name = "ID_BOLETA_DEPOSITO"), inverseJoinColumns=@JoinColumn(name = "ID_MOVIMIENTO_CAJA"))
	private Set<MovimientoCajaEgreso> listaMovimientoCajaEgreso = new HashSet<MovimientoCajaEgreso>();
	
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public LineaLibroBanco getLineaLibroBanco() {
		return lineaLibroBanco;
	}
	public void setLineaLibroBanco(LineaLibroBanco lineaLibroBanco) {
		this.lineaLibroBanco = lineaLibroBanco;
	}
	
	@Override
	public String toString(){
		String linea = "Boleta Nº "+this.getNumeroBoleta()+". $ "+this.getImporte()+" - "+this.fecha+" ("+this.cuentaBancaria+")";
		return linea;
	}
	public long getIdBoletaDeposito() {
		return idBoletaDeposito;
	}
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public Double getImporte() {
		return importe;
	}
	public Set<MovimientoCajaEgreso> getListaMovimientoCajaEgreso() {
		return listaMovimientoCajaEgreso;
	}
	public void setIdBoletaDeposito(long idBoletaDeposito) {
		this.idBoletaDeposito = idBoletaDeposito;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public void setListaMovimientoCajaEgreso(
			Set<MovimientoCajaEgreso> listaMovimientoCajaEgreso) {
		this.listaMovimientoCajaEgreso = listaMovimientoCajaEgreso;
	}
	
}
