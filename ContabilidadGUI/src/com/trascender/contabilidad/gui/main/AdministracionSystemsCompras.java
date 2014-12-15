package com.trascender.contabilidad.gui.main;

import javax.naming.Context;

import com.trascender.compras.system.interfaces.SystemAdministracionFactura;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaContrato;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaProveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaServicio;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaSubsidio;
import com.trascender.compras.system.interfaces.SystemStock;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AdministracionSystems;
import com.trascender.gui.framework.main.AppManager;

public class AdministracionSystemsCompras extends AdministracionSystems {
	
	private SystemAdministracionFactura systemAdministracionFactura;
	private SystemAdministracionFacturaContrato systemAdministracionFacturaContrato;
	private SystemAdministracionFacturaProveedor systemAdministracionFacturaProveedor;
	private SystemAdministracionFacturaServicio systemAdministracionFacturaServicio;
	private SystemAdministracionFacturaSubsidio systemAdministracionFacturaSubsidio;
	private SystemStock systemStock;
	
	
	@Override
	public void setLlave(long llave) throws Exception {
		super.setLlave(llave);
		if (this.systemAdministracionFactura != null) this.systemAdministracionFactura.setLlave(super.getLlave());
		if (this.systemAdministracionFacturaContrato != null) this.systemAdministracionFacturaContrato.setLlave(super.getLlave());
		if (this.systemAdministracionFacturaProveedor != null) this.systemAdministracionFacturaProveedor.setLlave(super.getLlave());
		if (this.systemAdministracionFacturaServicio != null) this.systemAdministracionFacturaServicio.setLlave(super.getLlave());
		if (this.systemAdministracionFacturaSubsidio != null) this.systemAdministracionFacturaSubsidio.setLlave(super.getLlave());
		if (this.systemStock != null) this.systemStock.setLlave(super.getLlave());
	}
	
	

	public SystemAdministracionFactura getSystemAdministracionFactura() throws TrascenderException {
		try{
			if (this.systemAdministracionFactura == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionFactura = (SystemAdministracionFactura) ctx.lookup(SystemAdministracionFactura.JNDI_NAME);
				this.systemAdministracionFactura.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return systemAdministracionFactura;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}



	public SystemAdministracionFacturaContrato getSystemAdministracionFacturaContrato() throws TrascenderException {
		try{
			if (this.systemAdministracionFacturaContrato == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionFacturaContrato = (SystemAdministracionFacturaContrato) ctx.lookup(SystemAdministracionFacturaContrato.JNDI_NAME);
				this.systemAdministracionFacturaContrato.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return systemAdministracionFacturaContrato;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionFacturaProveedor getSystemAdministracionFacturaProveedor() throws TrascenderException {
		try{
			if (this.systemAdministracionFacturaProveedor == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionFacturaProveedor = (SystemAdministracionFacturaProveedor) ctx.lookup(SystemAdministracionFacturaProveedor.JNDI_NAME);
				this.systemAdministracionFacturaProveedor.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return systemAdministracionFacturaProveedor;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionFacturaServicio getSystemAdministracionFacturaServicio() throws TrascenderException {
		try{
			if (this.systemAdministracionFacturaServicio == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionFacturaServicio = (SystemAdministracionFacturaServicio) ctx.lookup(SystemAdministracionFacturaServicio.JNDI_NAME);
				this.systemAdministracionFacturaServicio.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionFacturaServicio;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemAdministracionFacturaSubsidio getSystemAdministracionFacturaSubsidio() throws TrascenderException {
		try{
			if (this.systemAdministracionFacturaSubsidio == null) {
				Context ctx = super.getInitialContext();
				this.systemAdministracionFacturaSubsidio = (SystemAdministracionFacturaSubsidio) ctx.lookup(SystemAdministracionFacturaSubsidio.JNDI_NAME);
				this.systemAdministracionFacturaSubsidio.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemAdministracionFacturaSubsidio;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	public SystemStock getSystemStock() throws TrascenderException{
		try{
			if (this.systemStock == null){
				Context ctx = super.getInitialContext();
				this.systemStock = (SystemStock) ctx.lookup(SystemStock.JNDI_NAME);
				this.systemStock.setLlave(AppManager.getInstance().getLlaveUsuarioConectado());
			}
			return this.systemStock;
		} catch (Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}
	
	

}
