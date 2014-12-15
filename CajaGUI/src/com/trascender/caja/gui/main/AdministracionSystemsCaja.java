package com.trascender.caja.gui.main;

import javax.naming.Context;

import com.trascender.contabilidad.system.interfaces.SystemAdministracionCajaChica;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AdministracionSystems;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;

public class AdministracionSystemsCaja extends AdministracionSystems{
	
	private SystemAdministracionIngresos systemAdministracionIngresos;
	private SystemAdministracionCajaChica systemAdministracionCajaChica;
	private SystemAdministracionPlanillaDiaria systemAdministracionPlanillaDiaria;
	private SystemPersonaFisica systemPersonaFisica;
	private SystemPersonaJuridica systemPersonaJuridica;
	private SystemLiquidacionTasa systemLiquidacionTasa;
	private SystemParametro systemParametro;
	
	@Override
	public void setLlave(long pLlave) throws Exception {
		super.setLlave(pLlave);
		if (this.systemAdministracionIngresos != null) this.systemAdministracionIngresos.setLlave(super.getLlave());
		if (this.systemAdministracionCajaChica != null) this.systemAdministracionCajaChica.setLlave(super.getLlave());
		if (this.systemAdministracionPlanillaDiaria != null) this.systemAdministracionPlanillaDiaria.setLlave(super.getLlave());
		if (this.systemPersonaFisica != null) this.systemPersonaFisica.setLlave(super.getLlave());
		if (this.systemPersonaJuridica != null) this.systemPersonaJuridica.setLlave(super.getLlave());
		if (this.systemLiquidacionTasa != null) this.systemLiquidacionTasa.setLlave(super.getLlave());
		if (this.systemParametro != null) this.systemParametro.setLlave(super.getLlave());
	}
	
	public SystemAdministracionIngresos getSystemAdministracionIngresos() throws TrascenderException {
		try {
			if (this.systemAdministracionIngresos == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionIngresos = (SystemAdministracionIngresos) ctx.lookup(systemAdministracionIngresos.JNDI_NAME);
				this.systemAdministracionIngresos.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionIngresos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);
		}
		
	}
	
	public SystemAdministracionCajaChica getSystemAdministracionCajaChica() throws TrascenderException {
		try {
			if (this.systemAdministracionCajaChica == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionCajaChica = (SystemAdministracionCajaChica) ctx.lookup(SystemAdministracionCajaChica.JNDI_NAME);
				this.systemAdministracionCajaChica.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionCajaChica;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemAdministracionPlanillaDiaria getSystemAdministracionPlanillaDiaria() throws TrascenderException {
		try {
			if (systemAdministracionPlanillaDiaria == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionPlanillaDiaria = (SystemAdministracionPlanillaDiaria) ctx.lookup(SystemAdministracionPlanillaDiaria.JNDI_NAME);
				this.systemAdministracionPlanillaDiaria.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionPlanillaDiaria;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemPersonaFisica getSystemPersonaFisica() throws TrascenderException {
		try {
			if (systemPersonaFisica == null) {
				Context ctx = super.getInitialContext();
				this.systemPersonaFisica = (SystemPersonaFisica) ctx.lookup(systemPersonaFisica.JNDI_NAME);
				this.systemPersonaFisica.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemPersonaFisica;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);		}
	}

	public SystemPersonaJuridica getSystemPersonaJuridica() throws TrascenderException {
		try {
			if (systemPersonaJuridica == null) {
				Context ctx = super.getInitialContext();
				this.systemPersonaJuridica = (SystemPersonaJuridica) ctx.lookup(SystemPersonaJuridica.JNDI_NAME);
				this.systemPersonaJuridica.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemPersonaJuridica;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);		
		}
		
	}
	
	public SystemLiquidacionTasa getSystemLiquidacionTasa() throws TrascenderException {
		try {
			if (systemLiquidacionTasa == null) {
				Context ctx = super.getInitialContext();
				this.systemLiquidacionTasa = (SystemLiquidacionTasa) ctx.lookup(SystemLiquidacionTasa.JNDI_NAME);
				this.systemLiquidacionTasa.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemLiquidacionTasa;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(2);		
		}
	}

	public SystemParametro getSystemParametro() throws TrascenderException{
		try{
			if (systemParametro == null){
				Context ctx = super.getInitialContext();
				this.systemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);
				this.systemParametro.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return systemParametro;
		} catch (Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
}
