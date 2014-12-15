package com.trascender.contabilidad.gui.abmCuenta;

import java.rmi.RemoteException;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.filtros.FiltroCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;

public class CuentaBusquedaModel extends TAbstractBusquedaModel<Cuenta> {

	private String nombre;
	private String abreviatura;
	private String codigoImputacion;
	private TipoCuenta tipoCuenta;
	private PlanDeCuenta planDeCuenta;
	private Boolean aceptaAsientosContables;
	private Area area;

	@Override
	public List<Cuenta> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		FiltroCuenta locFiltro = new FiltroCuenta();
		locFiltro.setNombre(this.getNombre());
		locFiltro.setAbreviatura(this.getAbreviatura());
		locFiltro.setCodigoImputacion(this.getCodigoImputacion());
		locFiltro.setPlanDeCuenta(this.getPlanDeCuenta());
		locFiltro.setTipoCuenta(this.getTipoCuenta());
		locFiltro.setAceptaAsientosContables(this.aceptaAsientosContables);
		locFiltro.setArea(this.getArea());
		List<Cuenta> locLista = locSystem.findListaCuenta(locFiltro).getListaResultados();


		//		System.out.println("Antes de ordenar ---> " + locLista.toString());
		//		Collections.sort(locLista, new Comparator<Cuenta>(){
		//			public int compare(Cuenta o1, Cuenta o2) {
		//				return o1.getCodigoImputacion().compareTo(o2.getCodigoImputacion());
		//			}
		//		});
		//		System.out.println("Después de ordenar ---> " + locLista.toString());


		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setAbreviatura(null);
		this.setNombre(null);
		this.setCodigoImputacion(null);
		this.setTipoCuenta(null);
		this.setPlanDeCuenta(null);
		this.setArea(null);
		
		this.fireActualizarDatos();
	}

	public List<Area> getListaAreas(){
		List<Area> locListaRetorno = null;
		try {
			locListaRetorno = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad().findArea(new FiltroArea()).getListaResultados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locListaRetorno;
	}
	
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = Conversor.getNullSiVacio(abreviatura);
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = Conversor.getNullSiVacio(codigoImputacion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = Conversor.getNullSiVacio(nombre);
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

