package com.trascender.contabilidad.recurso.transients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;

public class CuentaCorriente implements Serializable{
	
	public static final long serialVersionUID = 352240196729848392L;
	
	private Double saldo = 0.00D;
	private String numero;
	private Date fecha;

	private Proveedor proveedor;
	
	private List<MovCuentaCorriente> listaMovimientos = new ArrayList<MovCuentaCorriente>();
	
	public void addMovimientoCuentaCorriente(MovCuentaCorriente pMovimiento){
		pMovimiento.setCuentaCorriente(this);
		listaMovimientos.add(pMovimiento);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public void setSaldo(){
		Double locSaldo = new Double(0);
		for (MovCuentaCorriente cadaMovimiento : listaMovimientos){
			if (cadaMovimiento instanceof MovCuentaCorrienteEgreso){
				locSaldo = locSaldo - cadaMovimiento.getImporte();
			} else {
				locSaldo = locSaldo + cadaMovimiento.getImporte();
			}
		}
		this.saldo = locSaldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<MovCuentaCorriente> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<MovCuentaCorriente> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	
	public List<MovCuentaCorrienteEgreso> getListaMovimientosEgreso(){
		List<MovCuentaCorrienteEgreso> locLista = new ArrayList<MovCuentaCorrienteEgreso>();
		for (MovCuentaCorriente cadaMovimiento : listaMovimientos){
			if (cadaMovimiento instanceof MovCuentaCorrienteEgreso) {
				locLista.add((MovCuentaCorrienteEgreso) cadaMovimiento);
			}
		}
		return locLista;
	}
	
	public List<MovCuentaCorrienteIngreso> getListaMovimientosIngreso(){
		List<MovCuentaCorrienteIngreso> locLista = new ArrayList<MovCuentaCorrienteIngreso>();
		for (MovCuentaCorriente cadaMovimiento : listaMovimientos){
			if (cadaMovimiento instanceof MovCuentaCorrienteIngreso) {
				locLista.add((MovCuentaCorrienteIngreso) cadaMovimiento);
			}
		}
		return locLista;
	}

	
	@Override
	public String toString(){
		return "Cuenta corriente del proveedor: "+proveedor+", saldo"+saldo;
	}
	
}
