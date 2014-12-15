
package com.trascender.catastro.system.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.filtros.FiltroDeclaracionJurada;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.filtros.FiltroPlanoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.filtros.FiltroSubParcela;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.AuxIdEntidad;

/**
 * Remote interface for SystemInformacionParcelaria.
 */
@Remote
public interface SystemInformacionParcelaria {
	public static final String JNDI_NAME = "ejb/SystemInformacionParcelaria/remote";

	/**
	 * Agrega una parcela
	 * 
	 * @param pParcela
	 *            parcela que agrega
	 */
	public Parcela addParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	public void deleteParcela(Parcela pParcela) throws TrascenderException;
	
	/**
	 * Actualiza los datos de una parcela
	 * 
	 * @param pParcela
	 *            parcela a actualizar
	 */
	public com.trascender.catastro.recurso.persistent.Parcela updateParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera una lista de parcelas por cuadra
	 * 
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroParcela findListaParcelas(FiltroParcela pFiltro) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera una parcela por el n�mero de identificaci�n �nico
	 * 
	 * @param pIdParcela
	 *            id de la parcela a recuperar
	 * @throws TrascenderException
	 * @return Parcela parcela que posee ese id o nulo en caso que no exista
	 */
	public com.trascender.catastro.recurso.persistent.Parcela getParcelaPorId(long pIdParcela) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera una lista de volantes catastrales
	 * 
	 * @param pNumVolanteCatastral
	 *            n�mero o c�digo del volante catastral
	 * @param pParcela
	 *            parcela a la que pertenecen los volantes catastrales
	 * @throws TrascenderException
	 * @return Lista con los volantes catastrales filtrados por los valores no nulos de los par�metros
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findVolanteCatastral(java.lang.Integer pNumVolanteCatastral, com.trascender.catastro.recurso.persistent.Parcela pParcela)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera una lista de planos de mensura
	 * 
	 * @param pNumero
	 *            n�mero del plano de mensura
	 * @param pFechaInscripcion
	 *            fecha de inscripci�n del plano de mensura
	 * @param pParcela
	 *            parcela a la que pertenece el plano de mensura
	 * @return Lista de planos de mensura
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoMensura findListaPlanosMensura(FiltroPlanoMensura pFiltro) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	// @SuppressWarnings("unchecked")
	// public FiltroParcela findListaPlanosMensura( FiltroParcela pFiltro) throws com.trascender.framework.exception.TrascenderException,
	// java.rmi.RemoteException;

	/**
	 * Recupera una lista de declaraciones juradas
	 * 
	 * @param pCodigoDDJJ
	 *            c�digo de la declaraci�n jurada de mejoras
	 * @param pParcela
	 *            parcela a la que pertenece la declaraci�n jurada
	 * @throws TrascenderException
	 * @return lista de declaraciones juradas
	 */
	@SuppressWarnings("unchecked")
	public FiltroDeclaracionJurada findDeclaracionJurada(FiltroDeclaracionJurada filtro) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera una lista de registros de mejora
	 * 
	 * @param pParcela
	 *            parcela a la que pertenecen los registros de mejora
	 * @throws TrascenderException
	 * @return lista de registros de mejora
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findRegistroMejora(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	/**
	 * Setea la llave del usuario a manejar
	 * 
	 * @param pLlave
	 *            llave del usuario
	 */
	public void setLlave(long pLlave) throws java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral addVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral updateVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public void deleteVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral) throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Agrega un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a agregar
	 * @return plano de mensura actualizado
	 * @throws TrascenderException
	 */
	public com.trascender.catastro.recurso.persistent.PlanoMensura addPlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Actualiza los datos de un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a actualizar
	 * @return pPlanoMensura plano de mensura actualizado
	 * @throws TrascenderException
	 */
	public com.trascender.catastro.recurso.persistent.PlanoMensura updatePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Elimina un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a eliminar
	 * @throws TrascenderException
	 */
	public void deletePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	public com.trascender.catastro.recurso.persistent.PlanoConstruccion addPlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	public com.trascender.catastro.recurso.persistent.PlanoConstruccion updatePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	public void deletePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	/**
	 * Recupera un listado de planos de construcci�n
	 * 
	 * @param pNumero
	 *            n�mero de plano de construcci�n
	 * @param pParcela
	 *            parcela a la que pertenecen los planos de construcci�n
	 * @return Listado de planos de construccion
	 * @throws TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoConstruccion findListaPlanosConstruccion(FiltroPlanoConstruccion pFiltro) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	/**
	 * Recupera un listado de cuadras por parcela con todas las cuadras de la manzana con los respectivos metros de frente de cada una, requiere permiso sobre
	 * la parcela (NO SOBRE LAS CUADRAS)
	 * 
	 * @param pParcela
	 *            parcela relacionada
	 * @return listado de ParcelaPorCuadra
	 */
	@SuppressWarnings("unchecked")
	public java.util.List getListaCuadrasPorParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	/**
	 * Recalcula el aval�o por mejoras de una parcela y lo actualiza en la base de datos
	 * 
	 * @param pParcela
	 *            parcela a actualizar
	 * @return parcela actualizada con el nuevo aval�o
	 * @throws TrascenderException
	 */
	public java.lang.Double calcularAvaluoMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	public com.trascender.catastro.recurso.persistent.VolanteCatastral generarVolanteCatastral(com.trascender.catastro.recurso.persistent.Parcela pParcela)
			throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

	public java.lang.Double getSuperficieMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws com.trascender.framework.exception.TrascenderException,
			java.rmi.RemoteException;

	/**
	 * Mensura una sub-parcela es el proceso de convertir a Parcela una SubParcela
	 * 
	 * @param pSubParcela
	 * @param pPlanoMensura
	 * @throws Exception
	 */
	public Parcela mensurarSubParcela(SubParcela pSubParcela, PlanoMensura pPlanoMensura) throws TrascenderException;

	/**
	 * Remueve una subParcela unificando el resto de las sub-parcelas restantes
	 * 
	 * @param pParcela
	 * @param pSubParcela
	 * @param pSubParcelasActualizadas
	 * @throws Exception
	 */
	public void unionSubParcelaria(Parcela pParcela, SubParcela pSubParcela) throws TrascenderException;

	/**
	 * Sub-divide una parcela en 2 o mas sub-parcelas
	 * 
	 * @param pParcela
	 * @param pListaSubParcelas
	 * @throws Exception
	 */
	public void subParcelarParcela(Parcela pParcela, List<? extends SubParcela> pListaSubParcelas) throws TrascenderException;

	/**
	 * 
	 * @param pParcelaPadre
	 * @param pTitular
	 * @return
	 * @throws TrascenderException
	 */
	public FiltroSubParcela findListaSubParcela(FiltroSubParcela pFiltro) throws TrascenderException;

	public PlanoMensura getPlanoMensuraPorId(long id) throws Exception;

	public String getSugerenciaNumeroParcela();
	
	public Long getSugerenciaNumeroRegistro();

	public List<AuxIdEntidad> findListaAuxIdParcela(String cadena) throws com.trascender.framework.exception.TrascenderException;
}