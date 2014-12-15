package com.trascender.compras.recurso.persistent.suministros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;

@Entity
@Table(name = "FACTURA_SUBSIDIO")
@PrimaryKeyJoinColumn(name = "ID_FACTURA")
public class FacturaSubsidio extends Factura{

	public static final long serialVersionUID = -7245216087549448566L;
	
	@ManyToOne
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal;

	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}
	
	public void setDigestoMunicipal(DigestoMunicipal pDigestoMunicipal) {
		this.digestoMunicipal = pDigestoMunicipal;
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

}
