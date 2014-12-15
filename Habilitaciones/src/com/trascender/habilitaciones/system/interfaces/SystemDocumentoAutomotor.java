
package com.trascender.habilitaciones.system.interfaces;

import java.io.File;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroTituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Remote
public interface SystemDocumentoAutomotor {

	public static final String JNDI_NAME = "ejb/SystemDocumentoAutomotor/remote";

	/**
	 * Agregar un vehículo
	 * 
	 * @param pVehiculo
	 * @throws TrascenderException
	 */
	public void addVehiculo(Vehiculo pVehiculo) throws com.trascender.framework.exception.TrascenderException;

	public void updateVehiculo(Vehiculo pVehiculo) throws com.trascender.framework.exception.TrascenderException;

	/**
	 * ELimina un vehículo del registro
	 * 
	 * @param pVehiculo
	 *            vehículo que se desea eliminar
	 * @throws TrascenderException
	 */
	public void deleteVehiculo(Vehiculo pVehiculo) throws com.trascender.framework.exception.TrascenderException;

	public FiltroVehiculo findListaVehiculo(FiltroVehiculo pFiltro) throws com.trascender.framework.exception.TrascenderException;

	public Vehiculo getVehiculoPorId(long pId) throws com.trascender.framework.exception.TrascenderException;

	public Modelo addModelo(Modelo pModelo) throws Exception;

	public Modelo updateModelo(Modelo pModelo) throws Exception;

	public boolean deleteModelo(Modelo pModelo) throws Exception;

	public Modelo getModeloById(Long pId) throws Exception;

	public FiltroModelo findListaModelo(FiltroModelo pFiltro) throws Exception;

	public Marca addMarca(Marca pMarca) throws Exception;

	public Marca updateMarca(Marca pMarca) throws Exception;

	public boolean deleteMarca(Marca pMarca) throws Exception;

	public Marca getMarcaById(Long pId) throws Exception;

	public FiltroMarca findListaMarca(FiltroMarca pFiltro) throws Exception;

	public TipoVehiculo addTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception;

	public TipoVehiculo updateTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception;

	public void deleteTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception;

	public TipoVehiculo getTipoVehiculoById(Long pId) throws Exception;

	public FiltroTipoVehiculo findListaTipoVehiculo(FiltroTipoVehiculo pFiltro) throws Exception;

	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor addDocumentoAutomotor(
			com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception;

	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor updateDocumentoAutomotor(
			com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception;

	public void deleteDocumentoAutomotor(com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception;

	public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws TrascenderException;

	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor getDocumentoAutomotor(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion)
			throws TrascenderException;

	public void addTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception;

	public void updateTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception;

	public FiltroTituloPropiedadAutomotor findListaTituloPropiedadAutomotor(FiltroTituloPropiedadAutomotor pFiltro) throws TrascenderException;

	public TituloPropiedadAutomotor getTituloPropiedadAutomotorPorId(long pId) throws TrascenderException;

	public ValuacionAcara addValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;

	public ValuacionAcara updateValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;

	public void deleteValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;

	public ValuacionAcara getValuacionAcaraById(Long pId) throws Exception;

	public FiltroValuacionAcara findListaValuacionesAcara(FiltroValuacionAcara pFiltro) throws Exception;

	public void procesarArchivoValuacionAcara(File pFile) throws Exception;

	public void setLlave(long pLlave) throws java.rmi.RemoteException;
	
	public List<AuxIdEntidad> findListaAuxIdModeloVehiculo(String cadena) throws com.trascender.framework.exception.TrascenderException;
}