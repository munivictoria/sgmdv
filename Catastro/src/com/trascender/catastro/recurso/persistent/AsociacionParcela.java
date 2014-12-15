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
@Table(name = "ASOC_PARCELA")
@PrimaryKeyJoinColumn(name = "ID_RELA_ZON_ASOC_PAR")
public class AsociacionParcela extends AsociacionParcelaBridge {

	private static final long serialVersionUID = 5491996899190724959L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARCELA")
	private Parcela parcela;
	
	
	@Override
	public List<Parcela> getListaParcelas() {
		List<Parcela> locListaParcelas = new ArrayList<Parcela>();
		locListaParcelas.add(parcela);
		return locListaParcelas;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	@Override
	public String getTipo() {
		return "Parcela";
	}
	
	@Override
	public String getNombre() {
		return this.getParcela().toString();
	}
	@Override
	public String toString() {
		return "Zona: " + this.getZona() + ". Parcela: " + this.parcela.getNroParcela();
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj.getClass() != AsociacionParcela.class){
//			return false;
//		}
//		
//		AsociacionParcela locAsociacionParcela = (AsociacionParcela)obj;
//		
//		if(locAsociacionParcela.getIdAsociacionParcela() != this.getIdAsociacionParcela()){
//			return false;
//		}
//							
//		//Si son iguales los ids, pero son iguales a -1 significa que pueden ser iguales, pero no están persistidas las zonas
//		//si no es nula la parcela las comparo
//		if (this.getParcela()!=null){
//			return this.getParcela().equals(locAsociacionParcela.getParcela());
//		}else if (locAsociacionParcela.getParcela()==null){					
//			return false; 
//		}		
//		//En caso que los ids no sean -1 significa que ambas son la misma zona	
//		return false;
//	}
}
