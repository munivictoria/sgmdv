package com.trascender.compras.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.Contratacion.Estado;
import com.trascender.compras.recurso.persistent.suministros.Contratacion.Tipo;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroContratacion extends FiltroAbstracto<Contratacion> {
	public FiltroContratacion() {
	}

	public FiltroContratacion(Date fechaPublicacion, Integer numero, String objeto, Tipo tipo, Estado estado, Secretaria secretaria, Area area, Bien bien) {
		this.fechaPublicacion = fechaPublicacion;
		this.numero = numero;
		this.objeto = objeto;
		this.tipo = tipo;
		this.estado = estado;
		this.secretaria = secretaria;
		this.area = area;
		this.bien = bien;
	}

	private static final long serialVersionUID = -5683911214343240462L;

	private Date fechaPublicacion;
	private Integer numero;
	private String objeto;
	private Contratacion.Tipo tipo;
	private Contratacion.Estado estado;
	private Secretaria secretaria;
	private Area area;
	private Bien bien;
	private List<Long> listaIdBienes;

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Contratacion.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Contratacion.Tipo tipo) {
		this.tipo = tipo;
	}

	public Contratacion.Estado getEstado() {
		return estado;
	}

	public void setEstado(Contratacion.Estado estado) {
		this.estado = estado;
	}

	public List<Long> getListaIdBienes() {
		return listaIdBienes;
	}

	public void setListaIdBienes(List<Long> listaIdBienes) {
		this.listaIdBienes = listaIdBienes;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
