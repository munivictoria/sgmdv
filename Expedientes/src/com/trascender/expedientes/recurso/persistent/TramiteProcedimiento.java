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
@Table(name = "EXP_TRAMITEPROCEDIMIENTO")
public class TramiteProcedimiento extends NodoProcedimiento implements Serializable {

	public static final long serialVersionUID = -1110143766787118464L;

	@ManyToOne
	@JoinColumn(name = "ID_TRAMITECATALOGO")
	private TramiteCatalogo tramiteCatalogo;

	public TramiteProcedimiento() {

	}

	public TramiteProcedimiento(TramiteCatalogo pTramiteCatalogo, NodoProcedimiento pPadre) {
		this.tramiteCatalogo = pTramiteCatalogo;
		this.nodoPadre = pPadre;
		for(DocumentoCatalogo dc : pTramiteCatalogo.getListaDocumentosCatalogos()) {
			this.getListaNodosHijos().add(new DocumentoProcedimiento(dc, this));
		}
	}

	public TramiteCatalogo getTramiteCatalogo() {
		return tramiteCatalogo;
	}

	public void setTramiteCatalogo(TramiteCatalogo tramiteCatalogo) {
		this.tramiteCatalogo = tramiteCatalogo;
	}

	public List<DocumentoProcedimiento> getListaDocumentosProcedimiento() {
		List<DocumentoProcedimiento> lista = new ArrayList<DocumentoProcedimiento>();
		for(NodoProcedimiento documentoP : getListaNodosHijos()) {
			if(documentoP.getEstado().equals(EstadoPlantilla.ACTIVO))
			lista.add((DocumentoProcedimiento) documentoP);
		}
		
		return lista;
	}

}