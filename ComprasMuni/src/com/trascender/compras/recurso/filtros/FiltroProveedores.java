package com.trascender.compras.recurso.filtros;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.Proveedor.Estado;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroProveedores extends FiltroAbstracto<Proveedor>{

	private static final long serialVersionUID = -311799889631535652L;

	private String razonSocial;
	private Proveedor.Estado estado;
	private String codigo;
	private Proveedor.Tipo tipo;
	private TipoBien tipoBien;
	private CodigoCiiu codigoCiiu;
	private Persona persona;
	private List<Long> listaIdPersonas;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	
	public FiltroProveedores() {
	}
	public FiltroProveedores(String razonSocial, //GrupoProveedor grupoProveedor, 
			Estado estado, Bien bien, TipoBien tipoBien, Persona persona,List<AtributoDinamico<?>> pListaAtributoDinamico) {
//		this.grupoProveedor = grupoProveedor;
		this.razonSocial = razonSocial;
		this.estado = estado;
		this.tipoBien = tipoBien;
		this.persona = persona;
		this.listaAtributoDinamico = pListaAtributoDinamico;
	}
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> listaAtributoDinamico) {
		this.listaAtributoDinamico = listaAtributoDinamico;
	}
	public Proveedor.Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Proveedor.Tipo tipo) {
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Proveedor.Estado getEstado() {
		return estado;
	}
	public void setEstado(Proveedor.Estado estado) {
		this.estado = estado;
	}
	public TipoBien getTipoBien() {
		return tipoBien;
	}
	public void setTipoBien(TipoBien tipoBien) {
		this.tipoBien = tipoBien;
	}
	public CodigoCiiu getCodigoCiiu() {
		return codigoCiiu;
	}
	public void setCodigoCiiu(CodigoCiiu codigoCiiu) {
		this.codigoCiiu = codigoCiiu;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	
}