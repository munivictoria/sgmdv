package com.trascender.habilitaciones.recurso.filtros;

import java.io.Serializable;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra.TipoObra;

public class FiltroObra extends FiltroAbstracto<Obra> implements Serializable {

	public static final long serialVersionUID = 6223349349945302388L;
	
	
	public FiltroObra() {}
	
	public FiltroObra(String pDescripcion, TipoObra pTipoObra, Cuadra pCuadra, String pNumero, Obra pObra) {
		this.descripcion = pDescripcion;
		this.tipoObra = pTipoObra;
		this.cuadra = pCuadra;
		this.numero = pNumero;
		this.obra = pObra;
	}
	
	private String descripcion;
	private Obra.TipoObra tipoObra;
	private Cuadra cuadra;
	private String numero; 
	private Obra obra;


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Obra.TipoObra getTipoObra() {
		return tipoObra;
	}

	public void setTipoObra(Obra.TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}

	public Cuadra getCuadra() {
		return cuadra;
	}

	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
	

}
