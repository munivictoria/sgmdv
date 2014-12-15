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
@Table(name = "ASOC_PARCELA_MANZANA")
@PrimaryKeyJoinColumn(name = "ID_RELA_ZON_ASOC_PAR")
public class AsociacionParcelaManzana extends AsociacionParcelaBridge{

	
	private static final long serialVersionUID = 4037397401452976506L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MANZANA")
	private Manzana manzana;
	
	@Override
	public List<Parcela> getListaParcelas() {
		if (this.manzana!=null){
			List<Parcela> locListaParcelas = new ArrayList<Parcela>();
			for (Cuadra cadaCuadra : this.manzana.getListaCuadrasDelimitantes()){
				for (ParcelaPorCuadra cadaParcelaPorCuadra: cadaCuadra.getListaParcelasPorCuadra()){
					locListaParcelas.add(cadaParcelaPorCuadra.getParcela());
				}
			}
			return locListaParcelas;
		}
		
		return null;
	}

	public Manzana getManzana() {
		return manzana;
	}

	public void setManzana(Manzana manzana) {
		this.manzana = manzana;
	}
	
	@Override
	public String getTipo() {
		return "Manzana";
	}

	@Override
	public String getNombre() {
		return this.getManzana().toString();
	}
	
	@Override
	public String toString() {
		return "Zona: " + this.getZona() + ". Manzana: " + this.manzana;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj.getClass() != AsociacionParcelaManzana.class){
//			return false;
//		}
//		
//		AsociacionParcelaManzana locAsociacionParcelaManzana = (AsociacionParcelaManzana)obj;
//		if(locAsociacionParcelaManzana.getIdAsociacionParcela() != this.getIdAsociacionParcela()){
//			return false;
//		}
//			
//				//Si son iguales los ids, pero son iguales a -1 significa que pueden ser iguales, pero no están persistidas las zonas
//				//si no es nula la parcela las comparo
//				if (this.getManzana() != null){
//					return this.getManzana().equals(locAsociacionParcelaManzana.getManzana());
//				}
//				
//				if(locAsociacionParcelaManzana.getManzana() == null){
//					return false;
//				}
//				
////		En caso que los ids no sean -1 significa que ambas son la misma zona
//		return false;
//	}
}
