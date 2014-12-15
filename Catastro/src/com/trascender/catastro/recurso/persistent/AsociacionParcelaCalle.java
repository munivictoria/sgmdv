package com.trascender.catastro.recurso.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ASOC_PARCELA_CALLE")
@PrimaryKeyJoinColumn(name = "ID_RELA_ZON_ASOC_PAR")
public class AsociacionParcelaCalle extends AsociacionParcelaBridge {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4847250434842523464L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CALLE")
	private Calle calle;
	
	@Override
	public List<Parcela> getListaParcelas() {
		if (this.calle!=null){
			List<Parcela> locListaParcelas = new ArrayList<Parcela>();
			for (Cuadra cadaCuadra : this.calle.getListaCuadras()){
				for (ParcelaPorCuadra cadaParcelaPorCuadra:cadaCuadra.getListaParcelasPorCuadra()){
					locListaParcelas.add(cadaParcelaPorCuadra.getParcela());
				}
			}
			return locListaParcelas;
		}
		return null; 
	}
	
	public Calle getCalle() {
		return calle;
	}
	public void setCalle(Calle calle) {
		this.calle = calle;
	}

	@Override
	public String getTipo() {
		return "Calle";
	}
	
	@Override
	public String getNombre() {
		return this.getCalle().toString();
	}

	@Override
	public String toString() {
		return "Zona: " + this.getZona() + ". Calle: " + this.calle;
	}
	
	

//	@Override
//	public boolean equals(Object obj) {
//		
//		if(obj.getClass() != AsociacionParcelaCalle.class){
//			return false;
//		}
//		
//		AsociacionParcelaCalle locAsociacionParcelaCalle = (AsociacionParcelaCalle)obj;
//		
//		if(locAsociacionParcelaCalle.getIdAsociacionParcela() != this.getIdAsociacionParcela()){
//			return false;
//		}
//							
//		//Si son iguales los ids, pero son iguales a -1 significa que pueden ser iguales, pero no están persistidas las zonas
//		//si no es nula la parcela las comparo
//		if (this.getCalle()!=null){
//			return this.getCalle().equals(locAsociacionParcelaCalle.getCalle());
//		}else if (locAsociacionParcelaCalle.getCalle()==null){					
//			return false; 
//		}		
//		//En caso que los ids no sean -1 significa que ambas son la misma zona	
//		return false;
//	}
}
