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
 * Sin uno ni efecto desde 14/12/2012
 * el agrupamiento se hace a partir de los codigos ciiu.
 */
@Deprecated
@Entity
@Table(name = "GRUPO_PROVEEDOR")
public class GrupoProveedor implements Serializable {

	public static final long serialVersionUID = 5839159912714702644L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_grupo_proveedor")
	@SequenceGenerator(name = "gen_id_grupo_proveedor", sequenceName = "gen_id_grupo_proveedor",allocationSize = 1)
	@Column(name="ID_GRUPO_PROVEEDOR")
	private long idGrupoProveedor=-1;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_FK_GRUPO_PROVEEDOR")
	private GrupoProveedor grupo;
	
	@Transient
	private List<GrupoProveedor> listaSubGrupoProveedores = null;
	
	@Transient
	private List<Proveedor> listaProveedores = null;
	
	public GrupoProveedor(){
		this.listaProveedores = new ArrayList<Proveedor>();
		this.listaSubGrupoProveedores = new ArrayList<GrupoProveedor>();
	}
	
	public GrupoProveedor getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoProveedor grupo) {
		this.grupo = grupo;
	}
	public long getIdGrupoProveedor() {
		return idGrupoProveedor;
	}
	public void setIdGrupoProveedor(long idGrupoProveedor) {
		this.idGrupoProveedor = idGrupoProveedor;
	}
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}
	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}	
	
	public void addSubGrupoProveedores(GrupoProveedor pGrupoProveedores){
		pGrupoProveedores.setGrupo(this);
		this.listaSubGrupoProveedores.add(pGrupoProveedores);
	}	
	public List getListaSubGrupoProveedores(){
		return this.listaSubGrupoProveedores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public GrupoProveedor getGrupoProveedorHijo(int i){
		return this.listaSubGrupoProveedores.get(i);
	}
	
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
