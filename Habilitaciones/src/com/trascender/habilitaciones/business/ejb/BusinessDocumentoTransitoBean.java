package com.trascender.habilitaciones.business.ejb;

import javax.ejb.Stateless;

import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTransitoLocal;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Stateless(name = "ejb/BusinessDocumentoTransitoLocal")
public class BusinessDocumentoTransitoBean implements BusinessDocumentoTransitoLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763353609194906324L;
//	static{
//		Grupo grupo=new Grupo();
//		grupo.setId(serialVersionUID);
//		grupo.setNombre("HAB|Adm. Tránsito");
//		
//		Recurso vehiculo=new Recurso();
//		vehiculo.setIdRecurso(Vehiculo.serialVersionUID);
//		vehiculo.setNombre("Vehículo");
//		grupo.getListaRecursos().add(vehiculo);
//		
//		SecurityMgr.getInstance().addGrupo(grupo);
//	}
	
	
	
	public BusinessDocumentoTransitoBean() {
	}

	
}


