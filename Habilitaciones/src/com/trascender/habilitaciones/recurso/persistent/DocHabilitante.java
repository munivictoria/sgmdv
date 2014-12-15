package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Documento que habilita una obligación de un contribuyente
 * @author Mariano Lusardi
 * @hibernate.class table = "DOC_HABILITANTE"
 *
 */
@Entity
@Table(name="DOC_HABILITANTE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DocHabilitante implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_doc_habilitante")
	@SequenceGenerator(name = "gen_id_doc_habilitante", sequenceName = "gen_id_doc_habilitante", allocationSize = 1)
	@Column(name = "ID_DOC_HABILITANTE")
	private long idDocHabilitante=-1;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_OBLIGACION")
	private Obligacion obligacion;
	
	@Column(name = "FECHA_HORA_CREACION")
	private Date fechaHoraCreacion;
	
	public enum Estado{CREADO,HABILITADO,TERMINADO,SUSPENDIDO,CANCELADO;
	
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
	};
	
	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.CREADO;
	
	@ManyToOne
	@JoinColumn(name = "ID_PADRE")
	private DocHabilitante padre;
	
	public DocHabilitante getPadre() {
		return padre;
	}
	public void setPadre(DocHabilitante pPadre) {
		padre = pPadre;
	}
	public long getIdDocHabilitante() {
		return idDocHabilitante;
	}
	public void setIdDocHabilitante(long pIdDocHabilitante) {
		idDocHabilitante = pIdDocHabilitante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	/**
	 * 
	 * @return obligación a la cual pertenece este documento habilitante
	 */
	public Obligacion getObligacion() {
		return obligacion;
	}
	public void setObligacion(Obligacion pObligacion) {
		obligacion = pObligacion;
	}
	
	public String toString() {	
		return (this.nombre==null)?"":this.nombre;
	}
	
	public Date getFechaHoraCreacion() {
		return this.fechaHoraCreacion;
	}
	public void setFechaHoraCreacion(Date pFechaCreacion) {
		this.fechaHoraCreacion = pFechaCreacion;
	}
	
	public Estado getEstado() {
		return estado;
	}
	protected void setEstado(Estado pEstado) {
		estado = pEstado;
	}	
	
	
	/**
	 * Habilita el documento habilitante requiere que el documento se encuentre en estado suspendido o creado
	 * en caso que posea un padre, habilita el padre recursivamente, en caso contrario prehabilita la obligacion
	 * @return true=logró la habilitación, false=no pudo habilitar (faltar requisitos)
	 */
	public boolean habilitar(){
		boolean retorno=false;
		if ((this.getEstado().equals(Estado.CREADO)) || (this.getEstado().equals(Estado.SUSPENDIDO))){
			this.setEstado(Estado.HABILITADO);
			
			if (this.getPadre()!=null){
					this.getPadre().habilitar();
			}
			else{
				this.getObligacion().prehabilitar();
			}
			retorno=true;
		}
		return retorno;
	}
	
	/**
	 * Termina el documento habilitante
	 * @return
	 */
	public boolean terminar(){
		if ((this.getEstado().equals(Estado.HABILITADO))||(this.getEstado().equals(Estado.SUSPENDIDO))){
			this.setEstado(Estado.TERMINADO);
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * Suspende temporalmente la obligaci�n, requiere que se encuentre en estado habilitado. En caso de poder suspenderla,
	 * suspende los documentos padre, hasta llegar a la obligaci�n, que la inhabilita
	 * @return true=suspendi�, false=no suspendi�
	 */
	public boolean suspender(){
		if (this.getEstado().equals(Estado.HABILITADO)) {
			this.setEstado(Estado.SUSPENDIDO);
			
			if (this.getPadre()!=null){
				this.getPadre().suspender();
			}
			else{
				this.getObligacion().inhabilitar();
			}
			
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * Cancela la obligación, requiere que se encuentre en estado habilitado, luego cancela el padre
	 * recursivamente hasta la obligación
	 * @return true= canceló, false= no canceló
	 * @throws Exception 
	 */
	public boolean cancelar() throws Exception{
		if ((this.getEstado().equals(Estado.HABILITADO))||(this.getEstado().equals(Estado.CREADO))){
			this.setEstado(Estado.CANCELADO);
			if (this.getPadre()==null){
				this.getObligacion().anular();
			}
			else{
				this.getPadre().cancelar();
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean equals(Object pObj) {
		if (this==pObj){
			return true;
		}
		if (!(pObj instanceof DocHabilitante)){
			return false;
		}
		else{
			DocHabilitante locDocHabilitante=(DocHabilitante)pObj;
			if (this.getIdDocHabilitante()!=-1){
				return (locDocHabilitante.getIdDocHabilitante()==this.getIdDocHabilitante())?true:false;
			}
			else{
				return ((
						this.getClass().getName().equals(pObj.getClass().getName()))
					  &&this.getNombre().equals(((DocHabilitante)pObj).getNombre()))?
							true:
							false;
			}
		}
	}
	
	
	@Override
	public int hashCode() {
		return (this.getClass().getName()+String.valueOf(this.getIdDocHabilitante())).hashCode();
	}
	
}
