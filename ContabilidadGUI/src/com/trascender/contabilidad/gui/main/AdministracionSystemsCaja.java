package com.trascender.contabilidad.gui.main;

import javax.naming.Context;

import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AdministracionSystems;
import com.trascender.gui.framework.main.AppManager;

public class AdministracionSystemsCaja extends AdministracionSystems {

	private SystemAdministracionIngresos systemAdministracionIngresos;
	
	@Override
	public void setLlave(long pLlave) throws Exception {
		super.setLlave(pLlave);
		if (this.systemAdministracionIngresos != null) this.systemAdministracionIngresos.setLlave(super.getLlave());
	}
	
	public SystemAdministracionIngresos getSystemAdministracionIngresos() throws TrascenderException {
		try {
			if (this.systemAdministracionIngresos == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionIngresos = (SystemAdministracionIngresos) ctx.lookup(SystemAdministracionIngresos.JNDI_NAME);
				this.systemAdministracionIngresos.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionIngresos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);
		}
		
	}
	
}
