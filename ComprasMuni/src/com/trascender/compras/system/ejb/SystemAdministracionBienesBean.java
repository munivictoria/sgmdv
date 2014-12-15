
package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessBienLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionBienes")
public class SystemAdministracionBienesBean implements SystemAdministracionBienes {

	/**
	 * 
	 */
	@EJB
	private BusinessBienLocal locBien;
	private static final long serialVersionUID = -3316974684761173303L;
	private long llave;

	public SystemAdministracionBienesBean() {
		super();

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
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien addGrupoBienes(
	// com.trascender.compras.recurso.persistent.suministros.GrupoBien pGrupoBien)
	// throws com.trascender.framework.exception.TrascenderException {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// GrupoBien.serialVersionUID,
	// Permiso.Accion.INSERT)) {
	// return this.locBien.addGrupoBien(pGrupoBien);
	// } else {
	// throw new TrascenderComprasException(700); //Permiso
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// throw new TrascenderComprasException(300); //Error en INSERT
	// }
	// }

	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public java.util.List findListaGrupoBienesPorNodo(
	// com.trascender.compras.recurso.persistent.suministros.GrupoBien pNodo)
	// throws java.lang.Exception {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// GrupoBien.serialVersionUID,
	// Permiso.Accion.SELECT)) {
	// return this.locBien.findListaGrupoBienesPorNodo(pNodo);
	// } else {
	// throw new TrascenderComprasException(701); //Permiso
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// throw new TrascenderComprasException(301);
	// }
	// }
	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public GrupoBien updateGrupoBienes(GrupoBien pNodo) throws Exception {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// GrupoBien.serialVersionUID,
	// Permiso.Accion.UPDATE)) {
	// return this.locBien.updateGrupoBien(pNodo);
	// } else {
	// throw new TrascenderComprasException(702);
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// throw new TrascenderComprasException(302);
	// }
	// }
	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public void deleteGrupoBienes(GrupoBien pNodo) throws Exception {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// GrupoBien.serialVersionUID,
	// Permiso.Accion.DELETE)) {
	// this.locBien.deleteGrupoBien(pNodo);
	// } else {
	// throw new TrascenderComprasException(703);
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// throw new TrascenderComprasException(303);
	// }
	// }

	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// findGrupoBienesPorNombre(String pNombre) throws Exception {
	// try{
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,GrupoBien.serialVersionUID,Permiso.Accion.SELECT))
	// return this.locBien.findGrupoBienesPorNombre(pNombre);
	// else
	// throw new TrascenderComprasException(701);
	// }catch(TrascenderException e){
	// throw e;
	// }catch(Exception e){
	// throw new TrascenderComprasException(301);
	// }
	// }
	// /**
	// * Business method
	// * @ejb.interface-method view-type = "remote"
	// */
	// public List findListaGrupoBienes() throws Exception {
	// try {
	// if (SecurityMgr.getInstance().getPermiso(
	// this.llave,
	// GrupoBien.serialVersionUID,
	// Permiso.Accion.SELECT)) {
	// return this.locBien.getListadoGrupoBienes();
	// } else {
	// throw new TrascenderComprasException(701);
	// }
	// } catch (TrascenderException e) {
	// throw e;
	// } catch (Exception e) {
	// throw new TrascenderComprasException(301);
	// }
	// }
	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien addBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Bien.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locBien.addBien(pBien);
			} else
				throw new TrascenderComprasException(720);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(320);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroBien findListadoBienes(FiltroBien filtro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Bien.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.getListadoBienes(filtro);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(321);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien updateBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws Exception {
		try {
			// PRE: El proveedor debe estar ACTIVO
			if(pBien.getEstado() == Bien.Estado.INACTIVO)
				throw new TrascenderComprasException(325);
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Bien.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locBien.updateBien(pBien);
			} else
				throw new TrascenderComprasException(722); // Permiso
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(322);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pBienProvisto
	 * @return
	 * @throws Exception
	 */
	public BienProvisto updateBienProvisto(BienProvisto pBienProvisto) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Bien.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locBien.updateBienProvisto(pBienProvisto);
			} else
				throw new TrascenderComprasException(722);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(322);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Bien.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locBien.deleteBien(pBien);
			} else
				throw new TrascenderComprasException(723);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(323);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien restoreBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Bien.serialVersionUID, Permiso.Accion.UPDATE)) {
				if(pBien.getEstado() == Bien.Estado.INACTIVO)
					return this.locBien.restoreBien(pBien);
				else
					return pBien;
			} else
				throw new TrascenderComprasException(724);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(324);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien findBienByID(long pIdBien) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Bien.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.findBienByID(pIdBien);
			} else
				throw new TrascenderComprasException(0);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(0);
		}
	}

	public List<Bien> validarNombreBien(Bien pBien) throws Exception {
		try {
			return this.locBien.validarNombreBien(pBien);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(0);
		}
	}

	// Marina

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Unidad addUnidad(com.trascender.compras.recurso.persistent.suministros.Unidad pUnidad) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Unidad.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locBien.addUnidad(pUnidad);
			} else
				throw new TrascenderComprasException(720);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(200);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Unidad updateUnidad(com.trascender.compras.recurso.persistent.suministros.Unidad pUnidad) throws Exception {
		try {
			// if (pUnidad.getEstado() == Bien.Estado.INACTIVO)
			// throw new TrascenderComprasException(325);
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Unidad.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locBien.updateUnidad(pUnidad);
			} else
				throw new TrascenderComprasException(722); // Permiso
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(201);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteUnidad(com.trascender.compras.recurso.persistent.suministros.Unidad pUnidad) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, com.trascender.compras.recurso.persistent.suministros.Unidad.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locBien.deleteUnidad(pUnidad);
			} else
				throw new TrascenderComprasException(723);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(202);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Unidad getUnidadByID(Long pIdUnidad) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Unidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.getUnidadByID(pIdUnidad);
			} else
				throw new TrascenderComprasException(0);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(203);
		}
	}

	public TipoBien getTipoBienByID(Long pIdTipoBien) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TipoBien.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.getTipoBienByID(pIdTipoBien);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(321);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="remote"
	 * @param pDescripcion
	 * @return
	 * @throws Exception
	 */
	public FiltroUnidad findListaUnidad(FiltroUnidad filtro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Unidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.findListaUnidad(filtro);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(204);
		}
	}

	/**
	 * Recupera todas las unidades sin requerir el permiso necesario, si comprueba que haya una llave.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Unidad> findListaUnidad() throws Exception {
		try {
			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				return this.locBien.findListaUnidad(new FiltroUnidad()).getListaResultados();
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(204);
		}
	}

	public FiltroTipoBien findListaTipoBien(FiltroTipoBien filtro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Unidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.findListaTipoBien(filtro);
			} else {
				throw new TrascenderComprasException(721);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(321);
		}
	}

	/**
	 * Recupera todos los Tipos Bien sin requerir el permiso, si valida la llave.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TipoBien> findListaTipoBien() throws Exception {
		try {
			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				return this.locBien.findListaTipoBien(new FiltroTipoBien()).getListaResultados();
			} else {
				throw new TrascenderComprasException(721);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(321);
		}
	}

	public FiltroBien findListaBien(FiltroBien filtro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Unidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.findListaBien(filtro);
			} else {
				throw new TrascenderComprasException(721);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(326);
		}
	}

	public List<AuxIdEntidad> findListaAuxIdBien(String cadena) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Bien.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locBien.findListaAuxIdBien(cadena);
			} else {
				throw new TrascenderComprasException(721);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(321);
		}
	}

	public TipoBien addTipoBien(TipoBien pTipoBien) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TipoBien.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locBien.addTipoBien(pTipoBien);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(204);
		}
	}

	public void updateTipoBien(TipoBien pTipoBien) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TipoBien.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.locBien.updateTipoBien(pTipoBien);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(204);
		}
	}

	public void deleteTipoBien(TipoBien pTipoBien) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TipoBien.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locBien.deleteTipoBien(pTipoBien);
			} else
				throw new TrascenderComprasException(721);
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			throw new TrascenderComprasException(204);
		}
	}
}