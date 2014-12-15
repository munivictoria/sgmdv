package com.trascender.compras.recurso.persistent.suministros;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value="LIN_FAC_BIEN")
public class LineaFacturaBien extends LineaFactura{

	private static final long serialVersionUID = 4110820275840747495L;
	
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
	public String getNombre() {
		return this.bien.getNombre();
	}
}
