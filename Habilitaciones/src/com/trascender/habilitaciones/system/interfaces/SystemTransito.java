package com.trascender.habilitaciones.system.interfaces;

import java.util.Set;

import javax.ejb.Remote;

import com.trascender.habilitaciones.recurso.persistent.transito.BaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TablaBaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Remote
public interface SystemTransito {
	public static final String JNDI_NAME = "ejb/SystemTransito/remote";
	
	  public void setLlave( long pLlave )
      throws java.rmi.RemoteException;
	  
	  public Double getValorBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) throws Exception;
	  public boolean getIsExentoBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) throws Exception;
		  
		public TablaBaseImponible addTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception;
		public TablaBaseImponible updateTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception;
		public boolean deleteTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception;
		public TablaBaseImponible getTablaBaseImponibleById(Long pIdTablaBaseImponible) throws Exception;
		public Set<TablaBaseImponible> findListaTablaBaseImponible(String pNombre, Boolean pActivo) throws Exception;
		
		public BaseImponible addBaseImponible(BaseImponible pBaseImponible) throws Exception;
		public BaseImponible updateBaseImponible(BaseImponible pBaseImponible) throws Exception;
		public boolean deleteBaseImponible(BaseImponible pBaseImponible) throws Exception;
		public BaseImponible getBaseImponibleById(Long pIdBaseImponible) throws Exception;
		public Set<BaseImponible> findListaBaseImponible(Marca pmarca, Modelo pModelo, TipoVehiculo pTipoVehiculo, TablaBaseImponible pTablaBaseImponible) throws Exception;
}
