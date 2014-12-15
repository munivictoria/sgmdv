package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoCementerioLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroParcelaCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoCementerio;

@Stateful(name="ejb/SystemDocumentoCementerio")
public class SystemDocumentoCementerioBean implements SystemDocumentoCementerio{

	public static final long serialVersionUID = -9214868622276957593L;
	private long llave;
	
	@EJB
	private BusinessDocumentoCementerioLocal businessDocumentoCementerio;
	
	public DocumentoCementerio addDocumentoCementerio(
			DocumentoCementerio pDocumentoCementerio) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoCementerio.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoCementerio.addDocumentoCementerio(pDocumentoCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(445);
		}
	}

	public DocumentoCementerio updateDocumentoCementerio(
			DocumentoCementerio pDocumentoCementerio) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoCementerio.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoCementerio.updateDocumentoCementerio(pDocumentoCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(446);
		} 
	}

	public void deleteDocumentoCementerio(
			DocumentoCementerio pDocumentoCementerio) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoCementerio.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoCementerio.deleteDocumentoCementerio(pDocumentoCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(447);
		}
	}

	public FiltroObligacionCementerio findListaObligacionesCementerio(
			FiltroObligacionCementerio pFiltro) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoCementerio.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.findListaObligacionesCementerio(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(448);
		}
	}

	public DocumentoCementerio getDocumentoCementerio(Obligacion pObligacion)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoCementerio.serialVersionUID,Permiso.Accion.SELECT)){
				DocumentoCementerio locDoc = this.businessDocumentoCementerio.getDocumentoCementerio(pObligacion);
				return locDoc;
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(449);
		}
	}

	public TipoSepultura addTipoSepultura(TipoSepultura pTipoSepultura)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoSepultura.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoCementerio.addTipoSepultura(pTipoSepultura);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(455);
		}
	}

	public TipoSepultura updateTipoSepultura(TipoSepultura pTipoSepultura)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoSepultura.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoCementerio.updateTipoSepultura(pTipoSepultura);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(456);
		} 
	}

	public void deleteTipoSepultura(TipoSepultura pTipoSepultura)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoSepultura.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoCementerio.deleteTipoSepultura(pTipoSepultura);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(457);
		}
	}

	public FiltroTipoSepultura findListaTipoSepultura(
			FiltroTipoSepultura pFiltro) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoSepultura.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.findListaTipoSepultura(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(458);
		}
	}

	public TipoSepultura getTipoSepulturaById(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoSepultura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.getTipoSepulturaById(pId);
			}
			else{
				throw new TransitoException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(459);
		}
	}

	public ParcelaCementerio addParcelaCementerio(
			ParcelaCementerio pParcelaCementerio) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ParcelaCementerio.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoCementerio.addParcelaCementerio(pParcelaCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(450);
		}
	}

	public ParcelaCementerio updateParcelaCementerio(
			ParcelaCementerio pParcelaCementerio) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ParcelaCementerio.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoCementerio.updateParcelaCementerio(pParcelaCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(451);
		} 
	}

	public void deleteParcelaCementerio(ParcelaCementerio pParcelaCementerio)
			throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ParcelaCementerio.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoCementerio.deleteParcelaCementerio(pParcelaCementerio);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(452);
		}
	}

	public FiltroParcelaCementerio findListaParcelaCementerio(
			FiltroParcelaCementerio pFiltro) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ParcelaCementerio.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.findListaParcelaCementerio(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(453);
		}
	}

	public ParcelaCementerio getParcelaCementerioById(long pId)
			throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, ParcelaCementerio.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.getParcelaCementerioById(pId);
			}
			else{
				throw new TransitoException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(454);
		}
	}

	public void setLlave(long pLlave) throws RemoteException {
		this.llave=pLlave;
	}

}
