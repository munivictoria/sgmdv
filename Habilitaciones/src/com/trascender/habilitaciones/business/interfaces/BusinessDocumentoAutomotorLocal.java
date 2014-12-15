
package com.trascender.habilitaciones.business.interfaces;

import java.io.File;
import java.util.List;

import javax.ejb.Local;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroTituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Local
public interface BusinessDocumentoAutomotorLocal {

	public static final String JNDI_NAME = "ejb/BusinessDocumentoAutomotorLocal/local";

	/**
	 * Agrega un vehículo al sistema
	 * 
	 * @param pVehiculo
	 *            vehiculo que se desea agregar
	 * @return vehiculo agregado (con datos acutizados)
	 * @throws Exception
	 */
	public Vehiculo addVehiculo(Vehiculo pVehiculo) throws java.lang.Exception;

	/**
	 * Actualiza los datos de un vehículo
	 * 
	 * @param pVehiculo
	 *            vehículo que se desea actualizar
	 * @return
	 * @throws Exception
	 */
	public Vehiculo updateVehiculo(Vehiculo pVehiculo) throws java.lang.Exception;

	/**
	 * Elimina un vehículo que no se encuentre registrado como TransporteVehicular
	 * 
	 * @param pVehiculo
	 *            vehiculo que se desea agregar
	 * @throws Exception
	 */
	public void deleteVehiculo(Vehiculo pVehiculo) throws java.lang.Exception;
	
	   /**
	    * Recupera un vehículo según el número de identificación única
	    * @param pId número de identificación del vehículo
	    * @return vehículo asociado
	    * @throws Exception
	    */
	   public Vehiculo getVehiculoPorId( long pId ) throws java.lang.Exception;
	   
	   public FiltroVehiculo findListaVehiculos(FiltroVehiculo pFiltro);
	   
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
	   public boolean deleteTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception;
	   public TipoVehiculo getTipoVehiculoById(Long pId) throws Exception;
	   public FiltroTipoVehiculo findListaTipoVehiculo(FiltroTipoVehiculo pFiltro) throws Exception;
	   
	   public DocumentoAutomotor addDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception;
	   public DocumentoAutomotor updateDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception;
	   public void deleteDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception;
	   public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws Exception;
	   public DocumentoAutomotor getDocumentoAutomotor(Obligacion pObligacion) throws Exception;
	   
	   public void addTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception;
	   public void updateTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception;
	   public FiltroTituloPropiedadAutomotor findListaTitulosPropiedadAutomotor(FiltroTituloPropiedadAutomotor pFiltro) throws Exception;
	   public TituloPropiedadAutomotor getTituloPropiedadAutomotorById(Long pId) throws Exception;
	   
	   public ValuacionAcara addValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;
	   public ValuacionAcara updateValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;
	   public void deleteValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception;
	   public ValuacionAcara getValuacionAcaraById(Long pId) throws Exception;
	   public FiltroValuacionAcara findListaValuacionesAcara(FiltroValuacionAcara pFiltro) throws Exception;
	   public void procesarArchivoValuacionAcara(File pFile) throws Exception;
	
	public List<AuxIdEntidad> findListaAuxIdModeloVehiculo(String cadena);
}
