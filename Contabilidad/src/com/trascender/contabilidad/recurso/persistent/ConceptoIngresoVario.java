/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "CONCEPTO_INGRESO_VARIO")
public class ConceptoIngresoVario implements Serializable{

	public static final long serialVersionUID = 7593356562754082785L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_concepto_ingreso_vario")
	@SequenceGenerator(name = "gen_id_concepto_ingreso_vario", sequenceName = "gen_id_concepto_ingreso_vario",allocationSize = 1)
	@Column(name="ID_CONCEPTO_INGRESO_VARIO")
	private long idConceptoIngresoVario = -1;
	private String nombre;
	
	@Column(name = "VALOR_POR_DEFECTO")
	private Double valorPorDefecto = 0D;
	private String descripcion;
	
	@OneToMany(mappedBy = "conceptoIngresoVario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<IngresoVario> listaIngresoVario;
	
	@OneToMany(mappedBy = "conceptoIngresoVario", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<RelaConceptoIngresoVarioCuenta> listaRelaConceptoIngresoVarioCuenta = new ArrayList<RelaConceptoIngresoVarioCuenta>();
	
	@OneToMany
	@JoinTable(name="RELA_CONCEPTO_INGRESO_VARIO_ROL", joinColumns=@JoinColumn(name="ID_CONCEPTO_INGRESO_VARIO", nullable = false), 
	inverseJoinColumns=@JoinColumn(name="ID_ROL", nullable = false))
	private List<Rol> listaRoles = new ArrayList<Rol>();
	
	@OneToMany
	@JoinTable(name="RELA_CONCEPTO_INGRESO_VARIO_USUARIO", joinColumns=@JoinColumn(name="ID_CONCEPTO_INGRESO_VARIO", nullable = false), 
	inverseJoinColumns=@JoinColumn(name="ID_USUARIO", nullable = false))
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public long getIdConceptoIngresoVario() {
		return idConceptoIngresoVario;
	}

	public void setIdConceptoIngresoVario(long idConceptoIngresoVario) {
		this.idConceptoIngresoVario = idConceptoIngresoVario;
	}

	public Set<IngresoVario> getListaIngresoVario() {
		return listaIngresoVario;
	}

	public void setListaIngresoVario(
			Set<IngresoVario> listaIngresoVario) {
		this.listaIngresoVario = listaIngresoVario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValorPorDefecto() {
		return valorPorDefecto;
	}

	public void setValorPorDefecto(Double valorPorDefecto) {
		this.valorPorDefecto = valorPorDefecto;
	}

	public List<RelaConceptoIngresoVarioCuenta> getListaRelaConceptoIngresoVarioCuenta() {
		return listaRelaConceptoIngresoVarioCuenta;
	}

	public void setListaRelaConceptoIngresoVarioCuenta(
			List<RelaConceptoIngresoVarioCuenta> listaRelaConceptoIngresoVarioCuenta) {
		this.listaRelaConceptoIngresoVarioCuenta = listaRelaConceptoIngresoVarioCuenta;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public void calcularValorPorDefecto() {
		Double valor = 0D;
		for (RelaConceptoIngresoVarioCuenta cadaRelacion : this.listaRelaConceptoIngresoVarioCuenta) {
			valor += cadaRelacion.getMontoPorDefecto();
		}
		this.valorPorDefecto = valor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idConceptoIngresoVario == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idConceptoIngresoVario ^ (idConceptoIngresoVario >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ConceptoIngresoVario other = (ConceptoIngresoVario) obj;
		if (idConceptoIngresoVario != other.idConceptoIngresoVario) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		if (this == null) {
			return "";
		}
		String linea = "";
		if (this.nombre!= null){
			 linea = this.nombre+" ($ "+ this.valorPorDefecto+")";
		}
		return linea;
	}
	

}
