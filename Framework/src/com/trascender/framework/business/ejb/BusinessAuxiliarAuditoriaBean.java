package com.trascender.framework.business.ejb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("BusinessAuxiliarAuditoriaBean")
public class BusinessAuxiliarAuditoriaBean implements Serializable{
	private static final long serialVersionUID = -8983960017094394614L;

	private long llave;
	private String comentario;

	//	@PersistenceContext(name = "Vipians")
	//	private EntityManager entityManager;

	public long getLlave() {
		return llave;
	}
	public void setLlave(long pLlave) {
		llave = pLlave;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String pComentario) {
		comentario = pComentario;
	}
}
