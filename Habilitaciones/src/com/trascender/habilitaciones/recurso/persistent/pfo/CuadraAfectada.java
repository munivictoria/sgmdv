package com.trascender.habilitaciones.recurso.persistent.pfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.catastro.recurso.persistent.Cuadra;

@Entity
@Table(name = "RELA_CUADRAS_AFECTADAS")
public class CuadraAfectada implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -5303461842730875104L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_rela_cuadras_afectadas")
	@SequenceGenerator(name = "gen_id_rela_cuadras_afectadas", sequenceName = "gen_id_rela_cuadras_afectadas", allocationSize = 1)
	@Column(name = "ID_RELA_CUADRAS_AFECTADAS")
	private long idCuadraAfectada=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUADRA", nullable = false)
	private Cuadra cuadra;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO", nullable = false)
	private DocumentoPlanObra documentoPlanObra;
	
	@Column(name = "METROS_AFECTADOS")
	private Double metros;
	
	public Double getMetros() {
		return metros;
	}
	public void setMetros(Double metros) {
		this.metros = metros;
	}
	public long getIdCuadraAfectada() {
		return idCuadraAfectada;
	}
	public void setIdCuadraAfectada(long idCuadraAfectada) {
		this.idCuadraAfectada = idCuadraAfectada;
	}
	
	public Cuadra getCuadra() {
		return cuadra;
	}
	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}
	public DocumentoPlanObra getDocumentoPlanObra() {
		return documentoPlanObra;
	}
	public void setDocumentoPlanObra(DocumentoPlanObra documentoPlanObra) {
		this.documentoPlanObra = documentoPlanObra;
	}
	
	@Override
	public String toString() {
		return ((this.getCuadra()!=null)?(this.getCuadra()+" - "):"")+((this.getMetros()!=null)?this.getMetros():"");
	}
	
}
