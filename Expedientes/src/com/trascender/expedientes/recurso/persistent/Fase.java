/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "EXP_FASE")
public class Fase extends NodoExpediente implements Serializable {

	private static final long serialVersionUID = 4145739101531829917L;

	@Column(name = "ACTIVA")
	private Boolean activa = false;

	public enum Estado {
		CERRADO, ABIERTO, FINALIZADO, VENCIDO;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CERRADO;

	public Fase() {
	}

	@ManyToOne
	@JoinColumn(name = "ID_FASE_A_VOLVER")
	private Fase faseAVolver;

	public Fase getFaseAVolver() {
		return faseAVolver;
	}

	public void setFaseAVolver(Fase faseAVolver) {
		this.faseAVolver = faseAVolver;
	}

	public Fase(FaseProcedimiento pFProcedimiento, NodoExpediente pExpediente) {
		this.setNodoProcedimiento(pFProcedimiento);
		this.setNodoPadre(pExpediente);
		this.setOrden(pFProcedimiento.getOrden());
		for(TramiteProcedimiento locTProcedimiento : pFProcedimiento.getListaTramitesProcedimientos()) {
			Tramite t = new Tramite(locTProcedimiento, this);
			this.listaNodosExpedientes.add(t);
		}
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		String retorno = " Fase: " + this.getPlantilla();
		
		return retorno;
	}

	@Override
	public Object getPlantilla() {
		return ((FaseProcedimiento) getNodoProcedimiento()).getFaseCatalogo();
	}

	@Override
	public boolean tieneVencimientos(List<DiaFeriado> diasFeriados) {
		if(this.nodoPadre.isVencido(diasFeriados)) {
			return true;
		}
		if(this.isVencido(diasFeriados)) {
			return true;
		} else {
			for(NodoExpediente tramite : listaNodosExpedientes) {
				if(tramite.isVencido(diasFeriados)) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean isVencido(List<DiaFeriado> diasFeriados) {
		if(Estado.VENCIDO.equals(estado)) {
			return true;
		}
		
		return super.isVencido(diasFeriados);
	}

}