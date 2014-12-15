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
@Table(name = "ASOC_PARCELA_CUADRA")
@PrimaryKeyJoinColumn(name = "ID_RELA_ZON_ASOC_PAR")
public class AsociacionParcelaCuadra extends AsociacionParcelaBridge{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5402564891618495000L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUADRA")
	private Cuadra cuadra;
	
	
	@Override
	public List<Parcela> getListaParcelas() {
		List<Parcela> locRetorno = null;
		if (this.cuadra != null){
			locRetorno = new ArrayList<Parcela>();
			for (ParcelaPorCuadra cadaParcelaPorCuadra: this.cuadra.getListaParcelasPorCuadra()){
				locRetorno.add(cadaParcelaPorCuadra.getParcela());
			}
		}
		return locRetorno;
	}


	public Cuadra getCuadra() {
		return cuadra;
	}
	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}

	
	@Override
	public String getTipo() {
		return "Cuadra";
	}
	
	@Override
	public String getNombre() {
		return this.getCuadra().toString();
	}
	
	@Override
	public String toString() {
		return "Zona: " + this.getZona() + ". Cuadra: " + this.cuadra;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj.getClass() != AsociacionParcelaCuadra.class){
//			return false;
//		}
//		
//		AsociacionParcelaCuadra locAsociacionParcelaCuadra = (AsociacionParcelaCuadra)obj;
//		if(locAsociacionParcelaCuadra.getIdAsociacionParcela() != this.getIdAsociacionParcela()){
//			return false;
//		}
//							
//		//Si son iguales los ids, pero son iguales a -1 significa que pueden ser iguales, pero no están persistidas las zonas
//		//si no es nula la parcela las comparo
//		if (this.getCuadra()!=null){
//			return this.getCuadra().equals(locAsociacionParcelaCuadra.getCuadra());
//		}else if (locAsociacionParcelaCuadra.getCuadra()==null){					
//			return false; 
//		}
//		
//		//En caso que los ids no sean -1 significa que ambas son la misma zona	
//		return false;
//	}
}
