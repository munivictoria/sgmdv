package com.trascender.framework.util;


public interface EntidadTrascender {

	public long getIdEntidad();
	public long getSerialVersionUID();
	public String getNombrePropiedadId();
	public boolean isAuditable();
	public void setComentarioAuditoria(String pComentario);
	public void setLlaveUsuarioAuditoria(long pLlave);
	public long getLlaveUsuarioAuditoria();
	public String getComentarioAuditoria();
}
