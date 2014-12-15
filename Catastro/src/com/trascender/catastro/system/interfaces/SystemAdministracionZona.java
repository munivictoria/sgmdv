package com.trascender.catastro.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
@Remote
public interface SystemAdministracionZona {
	
	public static final String JNDI_NAME="ejb/SystemAdministracionZona/remote";
	
	public List<AsociacionParcelaBridge> getListaAsociaciones() throws Exception, RemoteException;
	public void agregarAsociacionesParcela(List<Parcela> pListaParcelas)  throws Exception,RemoteException;
	public void agregarAsociacionesCalle(List<Calle> pListaCalles) throws Exception, RemoteException;
	public void agregarAsociacionManzana(List<Manzana> pListaManzanas) throws Exception, RemoteException;
	public void agregarAsociacionCuadra(List<Cuadra> pListaCuadras) throws Exception, RemoteException;
	public void guardar() throws Exception, RemoteException;
	public void eliminar() throws Exception, RemoteException;
	public void setZona(Zona pZona) throws Exception;
	
	/**
	 * @deprecated
	 * @throws RemoteException
	 */
	public void mostrarDatos() throws RemoteException;
	
	public String getDescripcion() throws RemoteException;
	public String getNombre()throws RemoteException;
	public Integer getPrioridad() throws RemoteException;
	public void setDescripcion(String descripcion) throws RemoteException;
	public void setNombre(String nombre)throws RemoteException;
	public void setPrioridad(Integer prioridad) throws RemoteException;
	public void setZonificacion(Zonificacion zonificacion)throws RemoteException;
	public Zonificacion getZonificacion() throws RemoteException;
	public void removeAsociacion(AsociacionParcelaBridge pAsociacion) throws RemoteException;
}
