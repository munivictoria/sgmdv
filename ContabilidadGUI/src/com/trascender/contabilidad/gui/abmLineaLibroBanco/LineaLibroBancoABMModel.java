package com.trascender.contabilidad.gui.abmLineaLibroBanco;

import java.util.Date;

import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco.Tipo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class LineaLibroBancoABMModel extends TAbstractABMModel<LineaLibroBanco> {

	private Date fechaGeneracion;
	private Double importe;
	private Boolean conciliado;
	private Tipo tipo;
	private String observaciones;
	
	@Override
	public void agregar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Date getFechaGeneracion() {
		return this.objetoABM.getFecha();
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.objetoABM.setFecha(fechaGeneracion);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}

	public Boolean isConciliado() {
		return this.objetoABM.isConciliado();
	}

	public void setConciliado(Boolean conciliado) {
		this.objetoABM.setConciliado(conciliado);
	}

	public Tipo getTipo() {
		return this.objetoABM.getTipo();
	}

	public void setTipo(Tipo tipo) {
		this.objetoABM.setTipo(tipo);
	}

	public String getObservaciones() {
		return this.objetoABM.getObservaciones();
	}

	public void setObservaciones(String observaciones) {
		this.objetoABM.setObservaciones(observaciones);
	}
	
	

}
