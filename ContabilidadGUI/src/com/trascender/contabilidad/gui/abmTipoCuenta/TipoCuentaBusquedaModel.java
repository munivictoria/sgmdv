package com.trascender.contabilidad.gui.abmTipoCuenta;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;

public class TipoCuentaBusquedaModel extends TAbstractBusquedaModel<TipoCuenta> {
	
	private String nombre;
	private Abreviatura abreviatura;
	private Opera operaAsientos;
	private Opera operaMovimientosCaja;

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoCuenta> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<TipoCuenta> locLista = locSystem.findListaTipoCuenta(this.getNombre(), this.getAbreviatura(), this.getOperaMovimientosCaja(), this.getOperaAsientos());
		return locLista;
	}
	
	@Override
	public void reiniciar() {
		this.setAbreviatura(null);
		this.setNombre(null);
		this.setOperaAsientos(null);
		this.setOperaMovimientosCaja(null);
		
		this.fireActualizarDatos();
	}

	public Abreviatura getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(Abreviatura abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = Conversor.getNullSiVacio(nombre);
	}

	public Opera getOperaAsientos() {
		return operaAsientos;
	}
	public void setOperaAsientos(Opera operaAsientos) {
		this.operaAsientos = operaAsientos;
	}

	public Opera getOperaMovimientosCaja() {
		return operaMovimientosCaja;
	}
	public void setOperaMovimientosCaja(Opera operaMovimientosCaja) {
		this.operaMovimientosCaja = operaMovimientosCaja;
	}

}
