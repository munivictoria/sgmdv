
package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroDeclaracionJurada;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.filtros.FiltroPlanoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.filtros.FiltroSubParcela;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemInformacionParcelaria" display-name="Name for SystemInformacionParcelaria" description="Description for SystemInformacionParcelaria"
 *           jndi-name="ejb/SystemInformacionParcelaria" type="Stateless" view-type="remote"
 */
@Stateful(name = "ejb/SystemInformacionParcelaria")
public class SystemInformacionParcelariaBean implements SystemInformacionParcelaria {

	private static final long serialVersionUID = -8661064891308468580L;

	private long llave;

	@EJB
	private BusinessRegistroParcelarioLocal registroParcelario;

	public SystemInformacionParcelariaBean() {
		super();
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	/**
	 * Agrega una parcela
	 * 
	 * @param pParcela
	 *            parcela que agrega
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Parcela addParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.INSERT)
					|| SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedad.serialVersionUID, Permiso.Accion.INSERT)
					|| SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.INSERT)
					|| SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.registroParcelario.addParcela(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(410);
		}
	}
	
	public void deleteParcela(Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.DELETE)) {
				this.registroParcelario.deleteParcela(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(700);
		}
	}

	/**
	 * Actualiza los datos de una parcela
	 * 
	 * @param pParcela
	 *            parcela a actualizar
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Parcela updateParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.UPDATE)
					|| SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedad.serialVersionUID, Permiso.Accion.UPDATE)
					|| SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.UPDATE)
					|| SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.registroParcelario.updateParcela(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(411);
		}
	}

	/**
	 * Recupera una lista de parcelas por cuadra
	 * 
	 * @param pCuadra
	 *            cuadra a la que pertenecen las parcelas
	 * @param pManzana
	 *            manzana a la que pertenecen las parcelas
	 * @param pNumeroRegistro
	 *            n�mero de registro de la parcela
	 * @param pNumeroParcela
	 *            n�mero de parcela (�nico en la manzana)
	 * @param pNroPartidaProvincial
	 *            n�mero de partida provincial
	 * @return Listado de parcelas
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroParcela findListaParcelas(FiltroParcela filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaParcelas(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(413);
		}
	}

	public FiltroSubParcela findListaSubParcela(FiltroSubParcela filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaSubParcela(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(413);
		}
	}

	/**
	 * Recupera una parcela por el n�mero de identificaci�n �nico
	 * 
	 * @param pIdParcela
	 *            id de la parcela a recuperar
	 * @throws TrascenderException
	 * @return Parcela parcela que posee ese id o nulo en caso que no exista
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Parcela getParcelaPorId(long pIdParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.getParcelaPorId(pIdParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(414);
		}
	}

	/**
	 * Recupera una lista de volantes catastrales
	 * 
	 * @param pNumVolanteCatastral
	 *            n�mero o c�digo del volante catastral
	 * @param pParcela
	 *            parcela a la que pertenecen los volantes catastrales
	 * @throws TrascenderException
	 * @return Lista con los volantes catastrales filtrados por los valores no nulos de los par�metros
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public List findVolanteCatastral(Integer pNumVolanteCatastral, com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, VolanteCatastral.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaVolanteCatastral(pNumVolanteCatastral, pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(415);
		}
	}

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
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoMensura findListaPlanosMensura(FiltroPlanoMensura filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaPlanoMensura(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(416);
		}
	}

	// @SuppressWarnings("unchecked")
	// public FiltroParcela findListaPlanosMensura(FiltroParcela filtro)
	// throws TrascenderException {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// PlanoMensura.serialVersionUID,
	// Permiso.Accion.SELECT)) {
	// return this.registroParcelario.findListaPlanoMensura(filtro);
	// } else {
	// throw new CatastroException(791);
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new CatastroException(416);
	// }
	// }

	/**
	 * Recupera una lista de declaraciones juradas
	 * 
	 * @param pCodigoDDJJ
	 *            c�digo de la declaraci�n jurada de mejoras
	 * @param pParcela
	 *            parcela a la que pertenece la declaraci�n jurada
	 * @throws TrascenderException
	 * @return lista de declaraciones juradas
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroDeclaracionJurada findDeclaracionJurada(FiltroDeclaracionJurada filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DeclaracionJurada.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaDeclaracionJurada(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(417);
		}
	}

	/**
	 * Recupera una lista de registros de mejora
	 * 
	 * @param pParcela
	 *            parcela a la que pertenecen los registros de mejora
	 * @throws TrascenderException
	 * @return lista de registros de mejora
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findRegistroMejora(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegistroMejora.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaRegistroMejora(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(418);
		}
	}

	/**
	 * Recupera una lista de planos de contrucci�n
	 * 
	 * @param pCodigo
	 *            c�digo del plano de contrucci�n
	 * @param pParcela
	 *            parcela a la que pertenece el plano de contrucci�n
	 * @throws TrascenderException
	 * @return Lista de planos de construcci�n
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoConstruccion findPlanoConstruccion(FiltroPlanoConstruccion filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaPlanoConstruccion(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(419);
		}
	}

	/**
	 * Setea la llave del usuario a manejar
	 * 
	 * @param pLlave
	 *            llave del usuario
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral addVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.registroParcelario.addVolanteCatastral(pVolanteCatastral);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(430);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral updateVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.registroParcelario.updateVolanteCatastral(pVolanteCatastral);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(431);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.registroParcelario.deleteVolanteCatastral(pVolanteCatastral);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(432);
		}
	}

	/**
	 * Agrega un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a agregar
	 * @return plano de mensura actualizado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.PlanoMensura addPlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.registroParcelario.addPlanoMensura(pPlanoMensura);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(433);
		}
	}

	/**
	 * Actualiza los datos de un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a actualizar
	 * @return pPlanoMensura plano de mensura actualizado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.PlanoMensura updatePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.registroParcelario.updatePlanoMensura(pPlanoMensura);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(434);
		}
	}

	/**
	 * Elimina un plano de mensura
	 * 
	 * @param pPlanoMensura
	 *            plano de mensura a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deletePlanoMensura(com.trascender.catastro.recurso.persistent.PlanoMensura pPlanoMensura) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Permiso.Accion.DELETE)) {
				this.registroParcelario.deletePlanoMensura(pPlanoMensura);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(435);
		}
	}

	/**
	 * 
	 * @param pPlanoConstruccion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public com.trascender.catastro.recurso.persistent.PlanoConstruccion addPlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.registroParcelario.addPlanoConstruccion(pPlanoConstruccion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(436);
		}
	}

	/**
	 * 
	 * @param pPlanoConstruccion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.PlanoConstruccion updatePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.registroParcelario.updatePlanoConstruccion(pPlanoConstruccion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(437);
		}
	}

	/**
	 * 
	 * @param pPlanoConstruccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deletePlanoConstruccion(com.trascender.catastro.recurso.persistent.PlanoConstruccion pPlanoConstruccion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.DELETE)) {
				this.registroParcelario.deletePlanoConstruccion(pPlanoConstruccion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(438);
		}
	}

	/**
	 * Recupera un listado de planos de construcci�n
	 * 
	 * @param pNumero
	 *            n�mero de plano de construcci�n
	 * @param pParcela
	 *            parcela a la que pertenecen los planos de construcci�n
	 * @return Listado de planos de construccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroPlanoConstruccion findListaPlanosConstruccion(FiltroPlanoConstruccion filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoConstruccion.serialVersionUID, Permiso.Accion.SELECT)) {
				return registroParcelario.findListaPlanoConstruccion(filtro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(439);
		}
	}

	/**
	 * Recupera un listado de cuadras por parcela con todas las cuadras de la manzana con los respectivos metros de frente de cada una, requiere permiso sobre
	 * la parcela (NO SOBRE LAS CUADRAS)
	 * 
	 * @param pParcela
	 *            parcela relacionada
	 * @return listado de ParcelaPorCuadra
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public Set<ParcelaPorCuadra> getListaCuadrasPorParcela(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.getListaCuadrasPorParcela(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(440);
		}
	}

	/**
	 * Recalcula el aval�o por mejoras de una parcela y lo actualiza en la base de datos
	 * 
	 * @param pParcela
	 *            parcela a actualizar
	 * @return parcela actualizada con el nuevo aval�o
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public Double calcularAvaluoMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.calcularAvaluoMejoras(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(441);
		}
	}

	/**
	 * 
	 * @param pParcela
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral generarVolanteCatastral(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, VolanteCatastral.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.registroParcelario.generarVolanteCatastral(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(443);
		}
	}

	/**
	 * 
	 * @param pParcela
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public Double getSuperficieMejoras(com.trascender.catastro.recurso.persistent.Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.getSuperficieMejoras(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(444);
		}
	}

	// /**
	// * Mensura una sub-parcela es el proceso de convertir a Parcela una SubParcela
	// * @param pSubParcela
	// * @param pPlanoMensura
	// * @throws TrascenderException
	// * @throws Exception
	// */
	// public void mensurarSubParcela(SubParcela pSubParcela, PlanoMensura pPlanoMensura) throws TrascenderException{
	// try{
	// if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Accion.INSERT)){
	// System.out.println("DEL SYSTEM *********" + pPlanoMensura + " - " + pPlanoMensura.getFechaInscripcion());
	// registroParcelario.mensurarSubParcela(pSubParcela, pPlanoMensura);
	// }else{
	// throw new CatastroException(791);
	// }
	// }catch (TrascenderException e) {
	// e.printStackTrace();
	// throw e;
	// }catch (Exception e) {
	// e.printStackTrace();
	// throw new CatastroException(-1);
	// }
	// }

	/**
	 * Remueve una subParcela unificando el resto de las sub-parcelas restantes
	 * 
	 * @param pParcela
	 * @param pSubParcela
	 * @param pSubParcelasActualizadas
	 * @throws Exception
	 */
	public void unionSubParcelaria(Parcela pParcela, SubParcela pSubParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Accion.INSERT)) {
				registroParcelario.unionSubParcelaria(pParcela, pSubParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(-1);
		}
	}

	/**
	 * Sub-divide una parcela en 2 o mas sub-parcelas
	 * 
	 * @param pParcela
	 * @param pListaSubParcelas
	 * @throws Exception
	 */
	public void subParcelarParcela(Parcela pParcela, List<? extends SubParcela> pListaSubParcelas) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Accion.INSERT)) {
				registroParcelario.subParcelarParcela(pParcela, pListaSubParcelas);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(-1);
		}
	}

	/**
	 * Mensura una sub-parcela es el proceso de convertir a Parcela una SubParcela
	 * 
	 * @param pSubParcela
	 * @param pPlanoMensura
	 * @throws TrascenderException
	 * @throws Exception
	 */
	public Parcela mensurarSubParcela(SubParcela pSubParcela, PlanoMensura pPlanoMensura) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Accion.INSERT)) {
				return registroParcelario.mensurarSubParcela(pSubParcela, pPlanoMensura);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(-1);
		}
	}

	public PlanoMensura getPlanoMensuraPorId(long id) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlanoMensura.serialVersionUID, Accion.SELECT)) {
				return this.registroParcelario.getPlanoMensuraPorId(id);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(-1);
		}
	}

	public String getSugerenciaNumeroParcela() {
		return this.registroParcelario.getSugerenciaNumeroParcela();
	}
	
	public Long getSugerenciaNumeroRegistro() {
		return this.registroParcelario.getSugerenciaNumeroRegistro();
	}

	public List<AuxIdEntidad> findListaAuxIdParcela(String cadena) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Parcela.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.registroParcelario.findListaAuxIdParcela(cadena);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(413);
		}
	}
}