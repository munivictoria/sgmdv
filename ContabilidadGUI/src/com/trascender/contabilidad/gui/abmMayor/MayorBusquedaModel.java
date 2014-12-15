package com.trascender.contabilidad.gui.abmMayor;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class MayorBusquedaModel extends TAbstractBusquedaModel<Mayor>{

	private Cuenta cuenta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mayor> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<Mayor> locList = locSystem.findListaMayor(this.getCuenta());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setCuenta(null);
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
