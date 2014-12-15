package com.trascender.contabilidad.gui.main;

import javax.naming.Context;

import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConciliacionBancaria;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AdministracionSystems;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

public class AdministracionSystemsContabilidad extends AdministracionSystems {

	private SystemAdministracionConsultaContable systemAdministracionConsultaContable;
	private SystemRegistroValuado systemRegistroValuado;
	private SystemMunicipalidad systemMunicipalidad;
	private SystemTipoTasa systemTipoTasa;
	private SystemAdministracionProveedores systemAdministracionProveedores;
	private SystemAdministracionBienes systemAdministracionBienes;
	private SystemPersonaJuridica systemPersonaJuridica;
	private SystemAdministracionOrdenCompra systemAdministracionOrdenCompra; 
	private SystemAdministracionConciliacionBancaria systemAdministracionConciliacionBancaria;
	private SystemAdministracionEgresos systemAdministracionEgresos;
	private SystemAdministracionIngresos systemAdministracionIngresos;
	private SystemAdministracionSolicitudSuministro systemAdministracionSolicitudSuministro;
	private SystemReportesContabilidad systemReportesContabilidad;
	
	@Override
	public void setLlave(long pLlave) throws Exception {
		super.setLlave(pLlave);
		if (this.systemAdministracionConsultaContable != null) this.systemAdministracionConsultaContable.setLlave(super.getLlave());
		if (this.systemRegistroValuado != null) this.systemRegistroValuado.setLlave(super.getLlave());
		if (this.systemMunicipalidad != null) this.systemMunicipalidad.setLlave(super.getLlave());
		if (this.systemAdministracionProveedores != null) this.systemAdministracionProveedores.setLlave(super.getLlave());
		if (this.systemAdministracionBienes != null) this.systemAdministracionBienes.setLlave(super.getLlave());
		if (this.systemPersonaJuridica != null) this.systemPersonaJuridica.setLlave(super.getLlave());
		if (this.systemAdministracionOrdenCompra != null) this.systemAdministracionOrdenCompra.setLlave(super.getLlave());
		if (this.systemAdministracionConciliacionBancaria != null) this.systemAdministracionConciliacionBancaria.setLlave(super.getLlave());
		if (this.systemAdministracionEgresos != null) this.systemAdministracionEgresos.setLlave(super.getLlave());
		if (this.systemAdministracionIngresos != null) this.systemAdministracionIngresos.setLlave(super.getLlave());
		if (this.systemAdministracionSolicitudSuministro != null) this.systemAdministracionSolicitudSuministro.setLlave(super.getLlave());
		if (this.systemTipoTasa!= null) this.systemTipoTasa.setLlave(super.getLlave());
		if (this.systemReportesContabilidad != null) this.systemReportesContabilidad.setLlave(super.getLlave());
		
		
	}
	
	/**
	 * Obtiene la interfaz SystemAdministracionConsultaContable para la invocación de los
	 * métodos de negocio.
	 * @return Una instancia de SystemAdministracionConsultaContable.
	 * @throws TrascenderException
	 */
	public SystemAdministracionConsultaContable getSystemAdministracionConsultaContable() throws TrascenderException {
		try{
			if (this.systemAdministracionConsultaContable == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionConsultaContable = (SystemAdministracionConsultaContable) ctx.lookup(SystemAdministracionConsultaContable.JNDI_NAME);
				this.systemAdministracionConsultaContable.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionConsultaContable;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemRegistroValuado getSystemRegistroValuado() throws TrascenderException {
		try {
			if (this.systemRegistroValuado == null) {
				Context ctx = super.getInitialContext();
				this.systemRegistroValuado = (SystemRegistroValuado) ctx.lookup(SystemRegistroValuado.JNDI_NAME);
				this.systemRegistroValuado.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemRegistroValuado;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemMunicipalidad getSystemMunicipalidad() throws TrascenderException {
		try {
			if (this.systemMunicipalidad == null) {
				Context ctx = super.getInitialContext();
				this.systemMunicipalidad = (SystemMunicipalidad) ctx.lookup(SystemMunicipalidad.JNDI_NAME);
				this.systemMunicipalidad.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemMunicipalidad;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemTipoTasa getSystemTipoTasa() throws TrascenderException {
		try {
			if (this.systemTipoTasa == null) {
				Context ctx = super.getInitialContext();
				this.systemTipoTasa = (SystemTipoTasa) ctx.lookup(SystemTipoTasa.JNDI_NAME);
				this.systemTipoTasa.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemTipoTasa;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemAdministracionProveedores getSystemAdministracionProveedores() throws TrascenderException {
		try {
			if (this.systemAdministracionProveedores == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionProveedores = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);
				this.systemAdministracionProveedores.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionProveedores;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemAdministracionBienes getSystemAdministracionBienes() throws TrascenderException {
		try {
			if (this.systemAdministracionBienes == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionBienes = (SystemAdministracionBienes) ctx.lookup(SystemAdministracionBienes.JNDI_NAME);
				this.systemAdministracionBienes.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionBienes;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemPersonaJuridica getSystemPersonaJuridica() throws TrascenderException {
		try {
			if (this.systemPersonaJuridica == null) {
				Context ctx = super.getInitialContext();
				this.systemPersonaJuridica = (SystemPersonaJuridica) ctx.lookup(SystemPersonaJuridica.JNDI_NAME);
				this.systemPersonaJuridica.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemPersonaJuridica;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemAdministracionOrdenCompra getSystemAdministracionOrdenCompra() throws TrascenderException {
		try {
			if (this.systemAdministracionOrdenCompra == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionOrdenCompra = (SystemAdministracionOrdenCompra) ctx.lookup(SystemAdministracionOrdenCompra.JNDI_NAME);
				this.systemAdministracionOrdenCompra.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionOrdenCompra;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemAdministracionConciliacionBancaria getSystemAdministracionConciliacionBancaria() throws TrascenderException {
		try {
			if (this.systemAdministracionConciliacionBancaria == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionConciliacionBancaria = (SystemAdministracionConciliacionBancaria) ctx.lookup(SystemAdministracionConciliacionBancaria.JNDI_NAME);
				this.systemAdministracionConciliacionBancaria.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionConciliacionBancaria;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionEgresos getSystemAdministracionEgresos() throws TrascenderException {
		try {
			if (this.systemAdministracionEgresos == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionEgresos = (SystemAdministracionEgresos) ctx.lookup(SystemAdministracionEgresos.JNDI_NAME);
				this.systemAdministracionEgresos.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionEgresos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemReportesContabilidad getSystemReportesContabilidad() throws TrascenderException {
		try {
			if (this.systemReportesContabilidad == null) {
				Context ctx = super.getInitialContext();
				this.systemReportesContabilidad = (SystemReportesContabilidad) ctx.lookup(SystemReportesContabilidad.JNDI_NAME);
				this.systemReportesContabilidad.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemReportesContabilidad;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionIngresos getSystemAdministracionIngresos() throws TrascenderException {
		try {
			if (this.systemAdministracionIngresos == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionIngresos = (SystemAdministracionIngresos) ctx.lookup(SystemAdministracionIngresos.JNDI_NAME);
				this.systemAdministracionIngresos.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionIngresos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionSolicitudSuministro getSystemAdministracionSolicitudSuministro() throws TrascenderException {
		try {
			if (this.systemAdministracionSolicitudSuministro == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionSolicitudSuministro = (SystemAdministracionSolicitudSuministro) ctx.lookup(SystemAdministracionSolicitudSuministro.JNDI_NAME);
				this.systemAdministracionSolicitudSuministro.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionSolicitudSuministro;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(2);
		}
	}

	
	
}
