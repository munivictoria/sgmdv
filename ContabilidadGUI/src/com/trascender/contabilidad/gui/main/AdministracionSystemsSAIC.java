package com.trascender.contabilidad.gui.main;

import javax.naming.Context;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AdministracionSystems;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;

public class AdministracionSystemsSAIC extends AdministracionSystems {

	private SystemLiquidacionTasa systemLiquidacionTasa;
	
	@Override
	public void setLlave(long llave) throws Exception {
		super.setLlave(llave);
		if (this.systemLiquidacionTasa != null) this.systemLiquidacionTasa.setLlave(super.getLlave());
	}
	
	

	public SystemLiquidacionTasa getSystemLiquidacionTasa() throws TrascenderException {
		try{
			if (this.systemLiquidacionTasa == null) {
				Context ctx = super.getInitialContext();
				this.systemLiquidacionTasa = (SystemLiquidacionTasa) ctx.lookup(SystemLiquidacionTasa.JNDI_NAME);
				this.systemLiquidacionTasa.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return systemLiquidacionTasa;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
}
