/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Sin efecto ni uso desde 14/12/2012
 */
@Deprecated
@Entity
@Table(name = "GRUPO_BIEN")
public class GrupoBien implements Serializable {
	public static final long serialVersionUID = -7291998984102510146L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_grupo_bien")
	@SequenceGenerator(name = "gen_id_grupo_bien", sequenceName = "gen_id_grupo_bien",allocationSize = 1)
	@Column(name="ID_GRUPO_BIEN")
	private long idGrupoBien=-1;
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_FK_GRUPO_BIEN")
	private GrupoBien padre = null;
	
	@Transient
	private List<GrupoBien> listaSubGrupoBienes = null;
	
	@Transient
	private List<Bien> listaBienes = null;
	
	private String descripcion;
	
	public GrupoBien(){
		this.listaBienes = new ArrayList<Bien>();
		this.listaSubGrupoBienes = new ArrayList<GrupoBien>();
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public long getIdGrupoBien() {
		return idGrupoBien;
	}

	public void setIdGrupoBien(long idGrupoBien) {
		this.idGrupoBien = idGrupoBien;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public GrupoBien getPadre() {
		return padre;
	}

	public void setPadre(GrupoBien padre) {
		this.padre = padre;
	}

	public void addSubGrupoBienes(GrupoBien pGrupoBien){
		pGrupoBien.setPadre(this);
		this.listaSubGrupoBienes.add(pGrupoBien);
	}
	
	public void addBien(Bien pBien){
//		pBien.setGrupo(this);
		this.listaBienes.add(pBien);
	}

	
	public List<Bien> getListaBienes() {
		return listaBienes;
	}

	public List<GrupoBien> getListaSubGrupoBienes() {
		return listaSubGrupoBienes;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
