package com.trascender.catastro.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemAdministracionZonificacion{
	
	public static final String JNDI_NAME="ejb/SystemAdministracionZonificacion/remote";
	
	public FiltroZonificacion findListaZonificacion(FiltroZonificacion pFiltro) throws TrascenderException, RemoteException;
	
	//---------------Métodos de ABM
	public void addZonificacion(Zonificacion pZonificacion) throws TrascenderException, RemoteException;
	public Zonificacion getZonificacionPorId(Long pIdZonificacion) throws TrascenderException;
	public void updateZonificacion(Zonificacion pZonificacion) throws TrascenderException, RemoteException;
	public void removeZonificacion(Zonificacion pZonificacion) throws TrascenderException, RemoteException;
	public FiltroZona findListaZonas(FiltroZona pFiltro) throws TrascenderException, RemoteException;
	public void setLlave(long llave) throws RemoteException;
	public List<Zona> getListaZonasFromParcela(Parcela pParcela) throws TrascenderException,RemoteException;
	public List<Zona> getListaZonasFromParcelaSinLimitar(Parcela pParcela) throws TrascenderException,RemoteException;
	public Zona getZonaById(Long pIdZona) throws Exception;
	
	/**
	 * Agrega una zona
	 * @param pZona zona a agregar
	 * @throws TrascenderException
	 */
	public Zona addZona(Zona pZona) throws TrascenderException, RemoteException;

	/**
	 * Actualiza una zona
	 * @param pZona zona que se desea actualizar
	 * @throws TrascenderException
	 */
	public Zona updateZona(Zona pZona)	throws TrascenderException, RemoteException;

	/**
	 * Elimina una zona
	 * @param pZona zona a eliminar
	 * @throws TrascenderException
	 */
	public void deleteZona(Zona pZona ) throws TrascenderException, RemoteException;

}
