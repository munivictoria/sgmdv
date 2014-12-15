package com.trascender.saic.recurso.references;

import java.util.Date;
import java.util.TreeSet;

import org.apache.commons.codec.language.RefinedSoundex;

import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.persistent.VencimientoLiquidacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

public class LiquidacionTasaRef {

	private Character estadoChar;
	public EstadoRegistroDeuda estado;
	private Boolean tieneRegCancelacion = false;
	private Date fechaEmision;
	private TreeSet<VencimientoLiquidacion> listaVencimientos;
	
	public Character getEstadoChar() {
		return estadoChar;
	}
	public void setEstadoChar(Character estadoChar) {
		this.estadoChar = estadoChar;
	}
	public EstadoRegistroDeuda getEstado() {
		return estado;
	}
	public void setEstado(EstadoRegistroDeuda estado) {
		this.estado = estado;
	}
	public Boolean tieneRegCancelacion() {
		return tieneRegCancelacion;
	}
	public void tieneRegCancelacion(Boolean tieneRegCancelacion) {
		this.tieneRegCancelacion = tieneRegCancelacion;
	}

	public LiquidacionTasaRef(){}
	
	
	public LiquidacionTasaRef(EstadoRegistroDeuda estado,
			Boolean tieneRegCancelacion, Date fechaEmision, TreeSet<VencimientoLiquidacion> listaVencimientos) {
		this.estado = estado;
		this.tieneRegCancelacion = tieneRegCancelacion;
		this.fechaEmision = fechaEmision;
		this.listaVencimientos = listaVencimientos;
		this.cargarEstadoChar();
	}
	public LiquidacionTasaRef(EstadoRegistroDeuda estado,
			Boolean tieneRegCancelacion, Date fechaEmision, VencimientoLiquidacion vencimiento) {
		this.estado = estado;
		this.tieneRegCancelacion = tieneRegCancelacion;
		this.fechaEmision = fechaEmision;
		this.listaVencimientos = new TreeSet<VencimientoLiquidacion>();
		this.listaVencimientos.add(vencimiento);
		this.cargarEstadoChar();
		
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fecha) {
		this.fechaEmision = fecha;
	}
	public TreeSet<VencimientoLiquidacion> getListaVencimientos() {
		return listaVencimientos;
	}
	public void setListaVencimientos(TreeSet<VencimientoLiquidacion> listaVencimientos) {
		this.listaVencimientos = listaVencimientos;
	}
	@Override
	public String toString() {
		return "LiquidacionTasaRef [estadoChar=" + estadoChar + ", estado="
				+ estado + ", tieneRegCancelacion=" + tieneRegCancelacion
				+ ", fechaEmision=" + fechaEmision + ", listaVencimientos="
				+ listaVencimientos + "]";
	}
	
	private void cargarEstadoChar() {
		if(this.estado.equals(EstadoRegistroDeuda.ANULADA) 
				|| this.estado.equals(EstadoRegistroDeuda.CANCELADA) 
				|| this.estado.equals(EstadoRegistroDeuda.VENCIDA) 
				|| this.estado.equals(EstadoRegistroDeuda.VIGENTE)){
			if(this.tieneRegCancelacion() == null){//si no tiene reg de cancelacion, es vigente
				estado = EstadoRegistroDeuda.VIGENTE;
			}

			if(estado.equals(EstadoRegistroDeuda.VIGENTE)){
				Date locFechaActual = Util.getFechaActualFormatoSimple();

				if(this.getListaVencimientos()!=null && !this.getListaVencimientos().isEmpty() ){//si es vigente y tiene una fecha de vencimiento, comprueba si no esta venciada
					Date locFechaVencimiento = this.getListaVencimientos().last().getFecha();
					estado = ( (locFechaVencimiento.after(locFechaActual) || locFechaActual.getTime() == locFechaVencimiento.getTime())?EstadoRegistroDeuda.VIGENTE:EstadoRegistroDeuda.VENCIDA);
				}
				else{// si tiene una fecha de vencimiento, el estado es vencido
					estado = EstadoRegistroDeuda.VENCIDA;
				}
			}
			//si el estado de la obligacion asociada es anulada, el registro es anulado.
//			if(this.getDocGeneradorDeuda() != null && this.getDocGeneradorDeuda().getObligacion().getEstado().equals(Obligacion.Estado.ANULADO)){
//				estado = EstadoRegistroDeuda.ANULADA;
//			}
		}
		
		if(this.estado.equals(EstadoRegistroDeuda.PAGADA)
				|| this.estado.equals(EstadoRegistroDeuda.PAGADA_ANUAL)
				|| this.estado.equals(EstadoRegistroDeuda.PAGADA_BIMESTRAL)
				|| this.estado.equals(EstadoRegistroDeuda.PAGADA_EN_TERCIOS)
				|| this.estado.equals(EstadoRegistroDeuda.CANCELADA)){
		
		this.estadoChar = 'A';
		}
		else
			if(this.estado.equals(EstadoRegistroDeuda.REFINANCIADA)
					|| this.estado.equals(EstadoRegistroDeuda.REFINANCIADA_BIMESTRAL)
					|| this.estado.equals(EstadoRegistroDeuda.RELIQUIDADA)){
				this.estadoChar = 'R';
			}
			else
				if(this.estado.equals(EstadoRegistroDeuda.VIGENTE)){
					this.estadoChar = 'V';
				}
				else if(estado.equals(EstadoRegistroDeuda.VENCIDA))
						this.estadoChar = 'D';
				
				else estadoChar = 'X'; //por defecto, si hay un estado que no se esta teniendo en cuenta
	}
	
}
