package com.trascender.framework.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.framework.util.EntidadTrascender;

@Local
public interface BusinessAuxiliarAuditoriaLocal {

	public final static String JNDI_NAME = "ejb/BusinessAuxiliarAuditoria";
	
	public long getLlave();

	public void setLlave(long pLlave);

	public String getComentario();

	public void setComentario(String pComentario);
	
}
