package com.trascender.framework.recurso.persistent.validacionDinamica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;

@Entity
@DiscriminatorValue(value="VALOR_ENTIDAD")
public class ValorEntidad extends ValorValidacion {

	private static final long serialVersionUID = 4953394811966557630L;

	@Column(name="ID_RECURSO", nullable=false)
	private Long idRecurso;
	
	@Column(name="VALOR_SERIAL_VER_RECURSO", nullable=false)
	private Long serialVersionRecurso;
	
	@Override
	public boolean comparar(Object pObjeto) {
		System.out.println("Serial version: " + this.getSerialVersionFromObject(pObjeto));
		System.out.println("ID Objeto: " + this.getIdFromObjeto(pObjeto, pObjeto.getClass()));
		if(this.serialVersionRecurso.equals(this.getSerialVersionFromObject(pObjeto)) 
				&& this.idRecurso.equals(this.getIdFromObjeto(pObjeto, pObjeto.getClass()))){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Long getValor() {
		return this.idRecurso;
	}

	@Override
	public void setValor(Object pValor) {
		this.idRecurso = (Long)pValor;
	}
	
	

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public Long getSerialVersionRecurso() {
		return serialVersionRecurso;
	}

	public void setSerialVersionRecurso(Long pSerialVersionRecurso) {
		serialVersionRecurso = pSerialVersionRecurso;
	}

	@Override
	public boolean comparar(Object pObjeto, Operadores pTipoOperacion) {
		switch (pTipoOperacion) {
			case IGUAL: return this.comparar(pObjeto);
			case DISTINTO: return !this.comparar(pObjeto);
			default: return false;
		}
	}

	private Long getIdFromObjeto(Object pObjeto, Class pClase) {
		try {
			System.out.println(pClase.getSimpleName());
			return (Long) pObjeto.getClass().getDeclaredMethod(("getId"+ pObjeto.getClass().getSimpleName()), null).invoke(pObjeto, null);
		}catch (NoSuchMethodException locE) {
			try {
				return getIdFromObjeto(pObjeto, pClase.getSuperclass());
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}
		return  -11l;
	}

	private Long getSerialVersionFromObject(Object pObjeto){
		try {
			return pObjeto.getClass().getDeclaredField("serialVersionUID").getLong(pObjeto);
		} catch (Exception e) {
			System.out.println("NO se encontro el Serial Version del objeto");
		}
		return -11l;
	}
}
