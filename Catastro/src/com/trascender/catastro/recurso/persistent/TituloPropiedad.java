
package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "TITULO_PROPIEDAD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC_TIPO", discriminatorType = DiscriminatorType.STRING)
public abstract class TituloPropiedad implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = 100632172111490217L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_titulo_propiedad")
	@SequenceGenerator(name = "gen_id_titulo_propiedad", sequenceName = "gen_id_titulo_propiedad", allocationSize = 1)
	@Column(name = "ID_TITULO_PROPIEDAD")
	private long idTituloPropiedad = -1;

	private String titulo;

	@Column(name = "FECHA_INSCRIPCION")
	private Date fechaInscripcion;

	@OneToMany(mappedBy = "tituloPropiedad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<RegistroPropietario> listaRegistrosPropietarios = new ArrayList<RegistroPropietario>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<RegistroPropietario> getListaRegistrosPropietarios() {
		return listaRegistrosPropietarios;
	}

	public void setListaRegistrosPropietarios(List<RegistroPropietario> listaRegistrosPropietarios) {
		this.listaRegistrosPropietarios = listaRegistrosPropietarios;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public long getIdTituloPropiedad() {
		return idTituloPropiedad;
	}

	public void setIdTituloPropiedad(long idTituloPropiedad) {
		this.idTituloPropiedad = idTituloPropiedad;
	}

	// @Override
	// public boolean equals(Object obj) {
	// if (!(obj instanceof TituloPropiedad))
	// return false;
	// if (obj == this)
	// return true;
	// if (((TituloPropiedad) obj).getIdTituloPropiedad() == this
	// .getIdTituloPropiedad())
	// return true;
	// return super.equals(obj);
	// }

	@Override
	public String toString() {
		DateFormat locDF = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return "Fecha Inscripcion: " + locDF.format(this.fechaInscripcion);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTituloPropiedad ^ (idTituloPropiedad >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		TituloPropiedad other = (TituloPropiedad) obj;
		if(idTituloPropiedad != other.idTituloPropiedad)
			return false;
		return true;
	}

	public void setEncargadosListaPropietarios(RegistroPropietario pRegistroPropietario) {
		for(RegistroPropietario cadaRegProp : this.getListaRegistrosPropietarios()) {
			cadaRegProp.setEncargadoDeObligaciones(cadaRegProp.equals(pRegistroPropietario));
		}
	}
	
	public RegistroPropietario getRegistroPropietarioEncargadoObligaciones(){
		for(RegistroPropietario cadaRegistro : this.getListaRegistrosPropietarios()){
			if(cadaRegistro.getEncargadoDeObligaciones()){
				return cadaRegistro;
			}
		}
		return null;
	}
	
	public String getStringListaPropietarios(){
		StringBuilder locCadenaRetorno = new StringBuilder();
		
		if(this.getRegistroPropietarioEncargadoObligaciones() != null) {
			locCadenaRetorno.append(this.getRegistroPropietarioEncargadoObligaciones().getPersona().toString());
		}
		
		if(this.getListaRegistrosPropietarios().size() > 1){
			locCadenaRetorno.append("\n");
		}
		
		for (Iterator<RegistroPropietario> iterator = 
				this.getListaRegistrosPropietarios().iterator(); iterator.hasNext();) {
			RegistroPropietario cadaRegProp = iterator.next();
			if(cadaRegProp.equals(this.getRegistroPropietarioEncargadoObligaciones())){
				continue;
			}
			locCadenaRetorno.append(cadaRegProp.getPersona().toString());
			if (iterator.hasNext()) locCadenaRetorno.append("\n");
		}
		return locCadenaRetorno.toString();
	}
	
	public void setStringListaPropietarios(String pSb){};
}