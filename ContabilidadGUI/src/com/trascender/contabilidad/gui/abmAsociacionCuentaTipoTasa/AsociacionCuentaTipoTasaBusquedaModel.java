package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AsociacionCuentaTipoTasaBusquedaModel extends TAbstractBusquedaModel<CuentaTipoTasa> {

	private TipoTasa tipoTasa;
	private Cuenta cuenta;
	private Integer anio;

	@Override
	@SuppressWarnings("unchecked")
	public List<CuentaTipoTasa> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = 
				ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<CuentaTipoTasa> locLista = locSystem.findListaCuentaTipoTasa(this.getCuenta(), this.getTipoTasa(), this.getAnio());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setTipoTasa(null);
		this.setCuenta(null);
		this.setAnio(null);
		this.fireActualizarDatos();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}


}
