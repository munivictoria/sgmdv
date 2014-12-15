package com.trascender.compras.recurso.persistent.suministros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;


@Entity
@Table(name = "FACTURA_SERVICIO")
@PrimaryKeyJoinColumn(name = "ID_FACTURA")
public class FacturaServicio extends Factura{

	public static final long serialVersionUID = 6805696876632999237L;
	
	@ManyToOne
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien pBien) {
		this.bien = pBien;
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
