/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.persistent.reporteDinamico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;

@Entity
@Table(name = "REPORTE")
public class Reporte implements Serializable {

	public static final long serialVersionUID = -3628286635475265493L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_reporte")
	@SequenceGenerator(name = "gen_id_reporte", sequenceName = "gen_id_reporte", allocationSize = 1)
	@Column(name = "ID_REPORTE")
	private long idReporte = -1;

	@Column(name = "ID_RECURSO")
	private Long idRecurso;

	@Enumerated(EnumType.STRING)
	private SeleccionaEntidad seleccionaEntidad;

	private String nombre;

	@Column(name = "NOMBRE_ARCHIVO_JASPER")
	private String nombreArchivoJasper;

	@OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "orden")
	private List<ParametroReporte> listaParametroReporte = new ArrayList<ParametroReporte>();

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;

	@OneToMany
	@JoinTable(name = "RELA_REPORTE_USUARIO", joinColumns = @JoinColumn(name = "ID_REPORTE", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_USUARIO", nullable = false))
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();

	@Enumerated(EnumType.STRING)
	private Tipo tipo = Tipo.PDF;

	public enum Tipo {
		PDF, EXCEL
	}

	public enum Estado {
		ACTIVO, INACTIVO
	}

	public enum SeleccionaEntidad {
		UNA, VARIAS
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public SeleccionaEntidad getSeleccionaEntidad() {
		return seleccionaEntidad;
	}

	public void setSeleccionaEntidad(SeleccionaEntidad pSeleccionaEntidad) {
		seleccionaEntidad = pSeleccionaEntidad;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(long pIdReporte) {
		idReporte = pIdReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreArchivoJasper() {
		return nombreArchivoJasper;
	}

	public void setNombreArchivoJasper(String nombreArchivoJasper) {
		this.nombreArchivoJasper = nombreArchivoJasper;
	}

	public List<ParametroReporte> getListaParametroReporte() {
		return listaParametroReporte;
	}

	public void setListaParametroReporte(List<ParametroReporte> listaParametroReporte) {
		this.listaParametroReporte = listaParametroReporte;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idReporte ^ (idReporte >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Reporte other = (Reporte) obj;
		if(idReporte != other.idReporte) {
			return false;
		}
		return true;
	}

	public ParametroReporte getParametroPorId(Long pIdParametroReporte) {
		for(ParametroReporte cadaParametro : listaParametroReporte) {
			if(pIdParametroReporte.equals(cadaParametro.getIdParametroReporte())) {
				return cadaParametro;
			}
		}
		return null;
	}

	public ParametroReporte getParametroPorNombreAtributo(String pNombreAtributoParametro) {
		for(ParametroReporte cadaParametro : listaParametroReporte) {
			if(pNombreAtributoParametro.equals(cadaParametro.getNombreAtributo())) {
				return cadaParametro;
			}
		}
		
		return null;
	}
	
	public String getNombreRecurso() {
		if(idRecurso == null) {
			return null;
		}
		
		return SecurityMgr.getInstance().getNombreRecurso(idRecurso);
	}

}