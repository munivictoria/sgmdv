package com.trascender.framework.system.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.inject.Inject;

import com.trascender.framework.business.ejb.BusinessAuxiliarAuditoriaBean;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemAuxiliarAuditoria;
import com.trascender.framework.util.EntidadTrascender;

@Stateful(name = "ejb/SystemAuxiliarAuditoria")
public class SystemAuxiliarAuditoriaBean implements SystemAuxiliarAuditoria{

	@Inject
	private BusinessAuxiliarAuditoriaBean locBusiness = null;
	
	public SystemAuxiliarAuditoriaBean(){
		super();
	}
	
	public long getLlave() {
		return this.locBusiness.getLlave();
	}

	public void setLlave(long pLlave) {
		this.locBusiness.setLlave(pLlave);
	}

	public String getComentario() {
		return this.locBusiness.getComentario();
	}

	public void setComentario(String pComentario) {
		this.locBusiness.setComentario(pComentario);
	}
	
}
