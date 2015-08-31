/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trascender.expedientes.enums.EstadoPlantilla;

@Entity
@Table(name = "EXP_FASEPROCEDIMIENTO")
public class FaseProcedimiento extends NodoProcedimiento implements Serializable {

	public static final long serialVersionUID = 2612303959407718152L;

	@ManyToOne
	@JoinColumn(name = "ID_FASECATALOGO")
	private FaseCatalogo faseCatalogo;

	public FaseProcedimiento() {
	}

	public FaseProcedimiento(FaseCatalogo pFaseCatalogo, NodoProcedimiento nodoPadre) {
		this.faseCatalogo = pFaseCatalogo;
		this.nodoPadre = nodoPadre;
		this.orden = this.nodoPadre.getListaNodosHijos().size();
		for(TramiteCatalogo tCatalogo : pFaseCatalogo.getListaTramitesCatalogos()) {
			TramiteProcedimiento locTp = new TramiteProcedimiento(tCatalogo, this);
			locTp.setOrden((this.listaNodosHijos.size()));
			this.listaNodosHijos.add(locTp);
		}
		for(FaseCatalogo fase : this.faseCatalogo.getListaFasesEspeciales()) {
			this.listaNodosHijos.add(new FaseProcedimiento(fase, this));
		}
	}

	public FaseCatalogo getFaseCatalogo() {
		return faseCatalogo;
	}

	public void setFaseCatalogo(FaseCatalogo faseCatalogo) {
		this.faseCatalogo = faseCatalogo;
	}

	public List<TramiteProcedimiento> getListaTramitesProcedimientos() {
		List<TramiteProcedimiento> lista = new ArrayList<TramiteProcedimiento>();
		for(NodoProcedimiento tramiteP : getListaNodosHijos()) {
			if(tramiteP instanceof TramiteProcedimiento) {
				if(tramiteP.getEstado().equals(EstadoPlantilla.ACTIVO))
				lista.add((TramiteProcedimiento) tramiteP);
			}
		}
		
		return lista;
	}

	public List<FaseProcedimiento> getListaFasesEspeciales() {
		List<FaseProcedimiento> lista = new ArrayList<FaseProcedimiento>();
		for(NodoProcedimiento cadaNodo : getListaNodosHijos()) {
			if(cadaNodo instanceof FaseProcedimiento) {
				lista.add((FaseProcedimiento) cadaNodo);
			}
		}
		
		return lista;
	}

	public List<NodoProcedimiento> getListaTramitesYFasesProcedimiento() {
		return this.listaNodosHijos;
	}
	
}