package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "EXP_DOCUMENTOPROCEDIMIENTO")
public class DocumentoProcedimiento extends NodoProcedimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1483336453715663320L;

	@ManyToOne
	@JoinColumn(name = "ID_DOCUEMNTOCATALOGO")
	private DocumentoCatalogo documentoCatalogo = new DocumentoCatalogo();

	public DocumentoCatalogo getDocumentoCatalogo() {
		return documentoCatalogo;
	}

	public void setDocumentoCatalogo(DocumentoCatalogo documentoCatalogo) {
		this.documentoCatalogo = documentoCatalogo;
	}

	public DocumentoProcedimiento() {

	}

	public DocumentoProcedimiento(DocumentoCatalogo pDocumentoCatalogo, NodoProcedimiento nodoPadre) {
        this.nodoPadre = nodoPadre;
		this.documentoCatalogo = pDocumentoCatalogo;
		
	}

}
