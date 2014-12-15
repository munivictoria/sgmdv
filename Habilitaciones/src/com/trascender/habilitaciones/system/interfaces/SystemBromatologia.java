
package com.trascender.habilitaciones.system.interfaces;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.filtros.FiltroLibretaSanitaria;
import com.trascender.habilitaciones.recurso.filtros.FiltroLocalComercial;
import com.trascender.habilitaciones.recurso.filtros.FiltroTransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;

/**
 * Remote interface for SystemBromatologia.
 */
@Remote
public interface SystemBromatologia {

	public static final String JNDI_NAME = "ejb/SystemBromatologia/remote";

	public void addLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws TrascenderException, RemoteException;

	public void updateLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws TrascenderException, RemoteException;

	public FiltroLibretaSanitaria findListaLibretasSanitarias(FiltroLibretaSanitaria pFiltro) throws TrascenderException, RemoteException;

	public void deleteLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws TrascenderException, RemoteException;

	/**
	 * Agrega una inspeccion
	 * 
	 * @param pInspeccion
	 * @throws TrascenderException
	 */
	public void addInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException, RemoteException;

	/**
	 * Actualiza una inspeccion
	 * 
	 * @param pInspeccion
	 * @throws TrascenderException
	 */
	public void updateInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException, RemoteException;

	public void deleteInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException, RemoteException;

	/**
	 * Recupera un listado de inspecciones vehiculares
	 * 
	 * @return
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public List findListaInspeccionesVehiculares(Date pFechaDesde, Date pFechaHasta, TransporteVehicular pTransporteVehicular, InspeccionSHPS.Estado pEstado)
			throws TrascenderException, RemoteException;

	/**
	 * Recupera una lista de inspecciones comerciales
	 * 
	 * @return
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pLocalComercial
	 * @param pEstado
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public List findListaInspeccionesComerciales(Date pFechaDesde, Date pFechaHasta, LocalComercial pLocalComercial, InspeccionSHPS.Estado pEstado) throws TrascenderException,
			RemoteException;

	public void addLocalComercial(LocalComercial pLocalComercial) throws TrascenderException, RemoteException;

	public void updateLocalComercial(LocalComercial pLocalComercial) throws TrascenderException, RemoteException;

	public void deleteLocalComercial(LocalComercial pLocalComercial) throws TrascenderException, RemoteException;

	public FiltroLocalComercial findListaLocalesComerciales(FiltroLocalComercial pFiltro) throws TrascenderException, RemoteException;

	public void addTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException, RemoteException;

	public void updateTranporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException, RemoteException;

	public void deleteTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException, java.rmi.RemoteException;

	/**
	 * Crea un nuevo transporte vehicular para el mismo vehículo, con una nueva fecha de alta
	 * 
	 * @param pTransporteVehicular
	 * @throws TrascenderException
	 */
	public TransporteVehicular restoreTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException, RemoteException;

	/**
	 * Recupera un listado de transportes vehiculares
	 * 
	 * @param pNumeroInscripcion
	 *            numero de inscripcion de los transportes vehiculares
	 * @param pVehiculo
	 *            vehiculo al que pertecen los transportes vehiculares
	 * @return
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroTransporteVehicular findListaTransportesVehiculares(FiltroTransporteVehicular pFiltro) throws TrascenderException, RemoteException;

	public LibretaSanitaria getLibretaSanitariaPorId(long pId) throws TrascenderException, RemoteException;

	/**
	 * Obtiene un documento habilitante a partir de la obligación
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 */
	public DocumentoSHPS getDocumentoHabilitanteSHPS(Obligacion pObligacion) throws Exception, RemoteException;

	/**
	 * Agrega una clausura a un documentoSHPS
	 * 
	 * @param pClausuraSHPS
	 *            clausura asociada
	 * @return
	 * @throws Exception
	 */
	public ClausuraSHPS addClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws TrascenderException, RemoteException;

	/**
	 * Actualiza los datos de una clausura
	 * 
	 * @param pClausuraSHPS
	 * @return
	 * @throws TrascenderException
	 */
	public ClausuraSHPS updateClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws TrascenderException, RemoteException;

	/**
	 * Recupera un listado de clausuras
	 * 
	 * @param pFechaAltaDesde
	 *            fecha de alta desde la cual filtrar el listado
	 * @param pFechaAltaHasta
	 *            fecha de alta hasta la cual filtrar el listado
	 * @param pFechaBajaDesde
	 *            fecha de baja desde la cual filtrar el listado
	 * @param pFechaBajaHasta
	 *            fecha de baja hasta la cual filtrar el listado
	 * @param isActiva
	 *            si la clausura se encuentra activa o no
	 * @param pDocumentoSHPS
	 *            documento al que pertenecen las clausuras
	 * @return Listado de clausuras
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List findListaClausurasSHPS(Date pFechaAltaDesde, Date pFechaAltaHasta, Date pFechaBajaDesde, Date pFechaBajaHasta, Boolean isActiva, DocumentoSHPS pDocumentoSHPS)
			throws TrascenderException, RemoteException;

	/**
	 * Recupera un tranporte vehicular según el id
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 */
	public TransporteVehicular getTransporteVehicularPorId(long pId) throws TrascenderException, RemoteException;

	/**
	 * Recupera un local comercial por el número de identificación único
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 */
	public LocalComercial getLocalComercialPorId(long pId) throws TrascenderException, RemoteException;

	/**
	 * Actualiza un documentoSHPS
	 * 
	 * @param pDocumentoSHPS
	 * @throws TrascenderException
	 */
	public void updateDocumentoSHPS(DocumentoSHPS pDocumentoSHPS) throws TrascenderException, RemoteException;

	public Integer getNroInscripcionDocEspSHPS();

	/**
	 * Recupera un listado de Documentos SHPS
	 * 
	 * @param pPersona
	 * @return lista de Documentos SHPS
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public List findListaDocumentosSHPS(Persona pPersona) throws TrascenderException, RemoteException;

	/**
	 * Firma una clausura para habilitar nuevamente la obligacion
	 * 
	 * @param pClausura
	 * @param pComentario
	 * @return
	 * @throws TrascenderException
	 */
	public FirmaPermiso firmarClausura(ClausuraSHPS pClausura, String pComentario) throws TrascenderException, RemoteException;

	/**
	 * Recupera el listado de clauras que pueden ser levantadas por el usuario actual
	 * 
	 * @return
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public List findListaClausurasPorUsuario() throws TrascenderException, RemoteException;

	public void setLlave(long pLlave) throws RemoteException;

}
