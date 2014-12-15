package com.trascender.contabilidad.recurso.filtros;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCuenta extends FiltroAbstracto<Cuenta> {
	
	private static final long serialVersionUID = -3374680870346848575L;
	
	private String nombre;
	private String abreviatura;
	private String codigoImputacion;
	private PlanDeCuenta planDeCuenta;
	private TipoCuenta tipoCuenta;
	private Boolean aceptaAsientosContables;
	private Area area;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getCodigoImputacion() {
		return codigoImputacion;
	}
	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}
	public PlanDeCuenta getPlanDeCuenta() {
		return planDeCuenta;
	}
	public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
		this.planDeCuenta = planDeCuenta;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Boolean getAceptaAsientosContables() {
		return aceptaAsientosContables;
	}
	public void setAceptaAsientosContables(Boolean aceptaAsientosContables) {
		this.aceptaAsientosContables = aceptaAsientosContables;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
}
