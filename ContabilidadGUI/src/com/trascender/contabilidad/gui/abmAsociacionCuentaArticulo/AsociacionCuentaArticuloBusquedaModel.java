package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo;

import java.util.List;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AsociacionCuentaArticuloBusquedaModel extends TAbstractBusquedaModel<CuentaArticulo>{
	
	private Articulo articulo;
	private Cuenta cuenta;
	private Integer anio;

	@Override
	public List<CuentaArticulo> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<CuentaArticulo> locList = locSystem.findListaCuentaArticulo(this.getArticulo(), this.getAnio(), this.getCuenta());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setArticulo(null);
		this.setCuenta(null);
		this.setAnio(null);
		fireActualizarDatos();
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
}
