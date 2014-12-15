package com.trascender.contabilidad.recurso.persistent;

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

@Entity
@Table(name="REPORTE_CONTABLE")
public class ReporteContable implements Serializable{
	
	public static final long serialVersionUID = -3628286635475265493L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_reporte_contable")
	@SequenceGenerator(name = "gen_id_reporte_contable", sequenceName = "gen_id_reporte_contable",allocationSize = 1)
	@Column(name="ID_REPORTE_CONTABLE")
	private long idReporteContable=-1;
	
	private String nombre;
	
	@Column(name="NOMBRE_ARCHIVO_JASPER")
	private String nombreArchivoJasper;
	
	@OneToMany(mappedBy = "reporteContable", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value="nombre")
	private List<ParametroReporteContable> listaParametroReporte = new ArrayList<ParametroReporteContable>();
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	@OneToMany
	@JoinTable(name="RELA_REPORTE_CONTABLE_USUARIO", joinColumns=@JoinColumn(name="ID_REPORTE_CONTABLE", nullable = false),
			inverseJoinColumns=@JoinColumn(name="ID_USUARIO", nullable = false))
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	public enum Estado{
		ACTIVO,INACTIVO
	}

	public long getIdReporteContable() {
		return idReporteContable;
	}

	public void setIdReporteContable(long idReporteContable) {
		this.idReporteContable = idReporteContable;
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

	public List<ParametroReporteContable> getListaParametroReporte() {
		return listaParametroReporte;
	}

	public void setListaParametroReporte(
			List<ParametroReporteContable> listaParametroReporte) {
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
		result = prime * result
				+ (int) (idReporteContable ^ (idReporteContable >>> 32));
		return result;
	}

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
		ReporteContable other = (ReporteContable) obj;
		if (idReporteContable != other.idReporteContable) {
			return false;
		}
		return true;
	}
	
}
