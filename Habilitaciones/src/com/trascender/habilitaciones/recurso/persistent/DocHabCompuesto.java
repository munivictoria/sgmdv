package com.trascender.habilitaciones.recurso.persistent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * 
 * @author Mariano Lusardi
 * 
 * @hibernate.joined-subclass table = "DOC_HAB_COMPUESTO"
 * @hibernate.joined-subclass-key column = "ID_DOC_HABILITANTE"
 */
@Entity
@Table(name = "DOC_HAB_COMPUESTO")
@PrimaryKeyJoinColumn(name = "ID_DOC_HABILITANTE")
public class DocHabCompuesto extends DocHabilitante{
	
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -3219719506878525854L;
	
	private String descripcion;
	
	@OneToMany(mappedBy = "padre", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocHabilitante> listaDocumentosHabilitantes=new HashSet<DocHabilitante>();

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Set<DocHabilitante> getListaDocumentosHabilitantes() {
		return listaDocumentosHabilitantes;
	}
	
	public void setListaDocumentosHabilitantes(
			Set<DocHabilitante> listaDocumentosHabilitantes) {
		this.listaDocumentosHabilitantes = listaDocumentosHabilitantes;
	}
	
	@Override
	public boolean habilitar() {
		boolean hijosHabilitados=true;
		
		//recorro todos los hijos para ver si fueron habilitados
		Iterator it=this.getListaDocumentosHabilitantes().iterator();
		while ((it.hasNext())&&(hijosHabilitados)){
			DocHabilitante locDocHabilitante=(DocHabilitante)it.next();
		
			hijosHabilitados=locDocHabilitante.getEstado().equals(Estado.HABILITADO);
		}
		
		if (hijosHabilitados){
			return super.habilitar();
		}
		else{
			return false;
		}
	}
	
	/**
	 * Termina el documento habilitante compuesto, para ello termina todos los hijos
	 */
	@Override
	public boolean terminar() {
		boolean terminado=true;
		for (DocHabilitante locDocHabilitante:this.getListaDocumentosHabilitantes()){
			terminado=terminado&&locDocHabilitante.terminar();
		}
		if (terminado){
			terminado = super.terminar();
		}
		return terminado;
	}
	
	
}
