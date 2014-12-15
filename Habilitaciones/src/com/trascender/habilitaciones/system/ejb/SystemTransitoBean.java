package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessTransitoLocal;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.persistent.transito.BaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TablaBaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.system.interfaces.SystemTransito;

@Stateful(name="ejb/SystemTransito")
public class SystemTransitoBean implements SystemTransito{
	private long llave;
	
	@EJB
	private BusinessTransitoLocal businessTransito;
	
	public void setLlave(long pLlave) throws RemoteException {
		this.llave = pLlave;
	}

	public Double getValorBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.getValorBaseImponible(tablaBaseImponible, pVehiculo);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(825);
		}
	}

	public boolean getIsExentoBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.getIsExentoBaseImponible(tablaBaseImponible, pVehiculo);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(826);
		}
	}

	public TablaBaseImponible addTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TablaBaseImponible.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessTransito.addTablaBaseImponible(pTablaBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(827);
		}
	}

	public TablaBaseImponible updateTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TablaBaseImponible.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessTransito.updateTablaBaseImponible(pTablaBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(828);
		}
	}

	public boolean deleteTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TablaBaseImponible.serialVersionUID, Permiso.Accion.DELETE)) {
				return this.businessTransito.deleteTablaBaseImponible(pTablaBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(829);
		}
	}

	public TablaBaseImponible getTablaBaseImponibleById(Long pIdTablaBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TablaBaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.getTablaBaseImponibleById(pIdTablaBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(831);
		}
	}

	public Set<TablaBaseImponible> findListaTablaBaseImponible(String pNombre, Boolean pActivo) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TablaBaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.findListaTablaBaseImponible(pNombre, pActivo);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(830);
		}
	}

	public BaseImponible addBaseImponible(BaseImponible pBaseImponible)	throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessTransito.addBaseImponible(pBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(820);
		}
	}

	public BaseImponible updateBaseImponible(BaseImponible pBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessTransito.updateBaseImponible(pBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(821);
		}
	}

	public boolean deleteBaseImponible(BaseImponible pBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.DELETE)) {
				return this.businessTransito.deleteBaseImponible(pBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(822);
		}
	}

	public BaseImponible getBaseImponibleById(Long pIdBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.getBaseImponibleById(pIdBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(824);
		}
	}

	public Set<BaseImponible> findListaBaseImponible(Marca pmarca,
			Modelo pModelo, 
			TipoVehiculo pTipoVehiculo,
			TablaBaseImponible pTablaBaseImponible) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BaseImponible.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTransito.findListaBaseImponible(pmarca, pModelo, pTipoVehiculo, pTablaBaseImponible);
			} else {
				throw new TransitoException(799);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new TransitoException(823);
		}
	}

}
