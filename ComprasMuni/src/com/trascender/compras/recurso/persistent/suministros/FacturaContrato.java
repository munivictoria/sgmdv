package com.trascender.compras.recurso.persistent.suministros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name = "FACTURA_CONTRATO")
@PrimaryKeyJoinColumn(name = "ID_FACTURA")
public class FacturaContrato extends Factura{

	/**
	 * 
	 */
	public static final long serialVersionUID = 8184919211753129530L;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTRATO")
	private Contrato contrato;
	
	public Contrato getContrato() {
		return contrato;
	}

	/**
	 * @param pContrato the Contrato to set.
	 */
	public void setContrato(Contrato pContrato) {
		this.contrato = pContrato;
	}

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		// TODO Auto-generated method stub
		
	}

//	public Set<LineaFactura> getListaLineaFacturaContrato() {
//		return listaLineaFacturaContrato;
//	}
//
//	public void setListaLineaFacturaContrato(
//			Set<LineaFactura> pListaLineaFacturaContrato) {
//		this.listaLineaFacturaContrato = pListaLineaFacturaContrato;
//	}

	
	
	
}
