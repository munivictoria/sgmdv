package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class LineaSubdiarioCajaABMModel extends TAbstractABMModel<LineaSubdiarioCaja> {
	
	private String mes;
	private String anio;
	
	@Override
	public void agregar() throws Exception {
		
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		
	}

	public Cuenta getCuenta() {
		return this.objetoABM.getCuenta();
	}

	public void setCuenta(Cuenta cuenta) {
		this.objetoABM.setCuenta(cuenta);
	}

	public Integer getDia() {
		return this.objetoABM.getDia();
	}

	public void setDia(Integer dia) {
		this.objetoABM.setDia(dia);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}
	
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
}
