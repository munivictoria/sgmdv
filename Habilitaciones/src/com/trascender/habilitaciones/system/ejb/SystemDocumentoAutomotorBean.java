
package com.trascender.habilitaciones.system.ejb;

import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoAutomotorLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroTituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoAutomotor;

@Stateful(name = "ejb/SystemDocumentoAutomotor")
public class SystemDocumentoAutomotorBean implements SystemDocumentoAutomotor {
	private long llave;

	@EJB
	private BusinessDocumentoAutomotorLocal businessDocumentoAutomotor;

	public static final long serialVersionUID = -1981285835812100162L;

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * Agregar un vehículo
	 * 
	 * @param pVehiculo
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addVehiculo(Vehiculo pVehiculo) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Vehiculo.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessDocumentoAutomotor.addVehiculo(pVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(800);
		}
	}

	/**
	 * 
	 * @param pVehiculo
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateVehiculo(Vehiculo pVehiculo) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Vehiculo.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoAutomotor.updateVehiculo(pVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(801);
		}
	}

	/**
	 * ELimina un vehículo del registro
	 * 
	 * @param pVehiculo
	 *            vehículo que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteVehiculo(Vehiculo pVehiculo) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Vehiculo.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoAutomotor.deleteVehiculo(pVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(802);
		}
	}

	/**
	 * 
	 * @param pVehiculo
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroVehiculo findListaVehiculo(FiltroVehiculo pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Vehiculo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaVehiculos(pFiltro);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(804);
		}

	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public Vehiculo getVehiculoPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Vehiculo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getVehiculoPorId(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(803);
		}
	}

	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public Modelo addModelo(Modelo pModelo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoAutomotor.addModelo(pModelo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(805);
		}
	}

	public Modelo updateModelo(Modelo pModelo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoAutomotor.updateModelo(pModelo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(806);
		}
	}

	public boolean deleteModelo(Modelo pModelo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.DELETE)) {
				return this.businessDocumentoAutomotor.deleteModelo(pModelo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(807);
		}
	}

	public Modelo getModeloById(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getModeloById(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(817);
		}
	}

	public FiltroModelo findListaModelo(FiltroModelo pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaModelo(pFiltro);
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

	public List<AuxIdEntidad> findListaAuxIdModeloVehiculo(String cadena) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaAuxIdModeloVehiculo(cadena);
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

	public Marca addMarca(Marca pMarca) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoAutomotor.addMarca(pMarca);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(809);
		}
	}

	public Marca updateMarca(Marca pMarca) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoAutomotor.updateMarca(pMarca);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(810);
		}
	}

	public boolean deleteMarca(Marca pMarca) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.DELETE)) {
				return this.businessDocumentoAutomotor.deleteMarca(pMarca);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(811);
		}
	}

	public Marca getMarcaById(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getMarcaById(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(818);
		}
	}

	public FiltroMarca findListaMarca(FiltroMarca pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaMarca(pFiltro);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(812);
		}
	}

	public TipoVehiculo addTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoAutomotor.addTipoVehiculo(pTipoVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(813);
		}
	}

	public TipoVehiculo updateTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoAutomotor.updateTipoVehiculo(pTipoVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(814);
		}
	}

	public void deleteTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoAutomotor.deleteTipoVehiculo(pTipoVehiculo);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(815);
		}
	}

	public TipoVehiculo getTipoVehiculoById(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getTipoVehiculoById(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(819);
		}
	}

	public FiltroTipoVehiculo findListaTipoVehiculo(FiltroTipoVehiculo pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Modelo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaTipoVehiculo(pFiltro);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(816);
		}
	}

	/**
	 * Agrega un documento Automotor al registro
	 * 
	 * @param pDocumentoAutomotor
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor addDocumentoAutomotor(
			com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoAutomotor.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoAutomotor.addDocumentoAutomotor(pDocumentoAutomotor);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(409);
		}
	}

	/**
	 * Actualiza los datos de un documento automotor
	 * 
	 * @param pDocumentoAutomotor
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor updateDocumentoAutomotor(
			com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoTGI.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoAutomotor.updateDocumentoAutomotor(pDocumentoAutomotor);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(405);
		}
	}

	/**
	 * Elimina un documento automotor
	 * 
	 * @param pDocumentoAutomotor
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteDocumentoAutomotor(com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor pDocumentoAutomotor) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoAutomotor.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoAutomotor.deleteDocumentoAutomotor(pDocumentoAutomotor);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(406);
		}
	}

	/**
	 * Recupera un listado de documentos de automotor
	 * 
	 * @param pFiltro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoAutomotor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaObligacionesAutomotor(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(407);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor getDocumentoAutomotor(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoAutomotor.serialVersionUID, Permiso.Accion.SELECT)) {
				DocumentoAutomotor locDoc = this.businessDocumentoAutomotor.getDocumentoAutomotor(pObligacion);
				return locDoc;
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(408);
		}
	}

	public void addTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedadAutomotor.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessDocumentoAutomotor.addTituloPropiedadAutomotor(pTituloPropiedadAutomotor);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(832);
		}
	}

	public void updateTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedadAutomotor.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoAutomotor.updateTituloPropiedadAutomotor(pTituloPropiedadAutomotor);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(833);
		}
	}

	public FiltroTituloPropiedadAutomotor findListaTituloPropiedadAutomotor(FiltroTituloPropiedadAutomotor pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedadAutomotor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaTitulosPropiedadAutomotor(pFiltro);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(834);
		}

	}

	public TituloPropiedadAutomotor getTituloPropiedadAutomotorPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedadAutomotor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getTituloPropiedadAutomotorById(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(835);
		}
	}

	public ValuacionAcara addValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoAutomotor.addValuacionAcara(pValuacionAcara);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(836);
		}
	}

	public ValuacionAcara updateValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoAutomotor.updateValuacionAcara(pValuacionAcara);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(837);
		}
	}

	public void deleteValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoAutomotor.deleteValuacionAcara(pValuacionAcara);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(838);
		}
	}

	public ValuacionAcara getValuacionAcaraById(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.getValuacionAcaraById(pId);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(839);
		}
	}

	public FiltroValuacionAcara findListaValuacionesAcara(FiltroValuacionAcara pFiltro) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoAutomotor.findListaValuacionesAcara(pFiltro);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(840);
		}
	}

	public void procesarArchivoValuacionAcara(File pFile) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ValuacionAcara.serialVersionUID, Permiso.Accion.AUDITH)) {
				this.businessDocumentoAutomotor.procesarArchivoValuacionAcara(pFile);
			} else {
				throw new TransitoException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TransitoException(840);
		}
	}

}
