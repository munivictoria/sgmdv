package com.trascender.framework.system.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.EntidadTrascender;

@Remote
public interface SystemAuxiliarAuditoria {

	public static final String JNDI_NAME = "ejb/SystemAuxiliarAuditoria/remote";
	
	public long getLlave();

	public void setLlave(long pLlave);

	public String getComentario();

	public void setComentario(String pComentario);
	
}
