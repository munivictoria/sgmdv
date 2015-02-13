package com.trascender.habilitaciones.system.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoArrendamientoLocal;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.system.interfaces.SystemArrendamiento;

@Stateless(name = "ejb/SystemArrendamiento")
public class SystemArrendamientoBean implements SystemArrendamiento{
	
	private long llave;
	public void setLlave(long llave) {
		this.llave = llave;
	}
	
	@EJB
	private BusinessDocumentoArrendamientoLocal businessArrendamiento;

	@Override
	public void addDocumentoArrendamiento(DocumentoArrendamiento pDocumento)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoArrendamiento.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessArrendamiento.addDocumentoArrendamiento(pDocumento);
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

	@Override
	public void updateDocumentoArrendamiento(DocumentoArrendamiento pDocumento)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoArrendamiento.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessArrendamiento.updateDocumentoArrendamiento(pDocumento);
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

	@Override
	public void deleteDocumentoArrendamiento(DocumentoArrendamiento pDocumento)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoArrendamiento.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessArrendamiento.deleteDocumentoArrendamiento(pDocumento);
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

	@Override
	public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(
			FiltroObligacionArrendamiento pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoArrendamiento.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessArrendamiento.findListaObligacionesArrendamiento(pFiltro);
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

	@Override
	public DocumentoArrendamiento getDocumentoArrendamiento(
			Obligacion pObligacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoArrendamiento.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessArrendamiento.getDocumentoArrendamiento(pObligacion);
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

}
