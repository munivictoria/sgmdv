/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;

@Entity
@Table(name = "PRESUPUESTO")
public class Presupuesto implements Serializable{

	public  static final long serialVersionUID = 4517847123936395257L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_presupuesto")
	@SequenceGenerator(name = "gen_id_presupuesto", sequenceName = "gen_id_presupuesto",allocationSize = 1)
	@Column(name="ID_PRESUPUESTO")
	private long idPresupuesto = -1;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private String nombre;
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	public enum Tipo{GASTOS, RECURSOS;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	public enum Estado {ACTIVO, INACTIVO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	//Relacion con otros objetos
	
//	@ManyToOne
//	@JoinColumn(name = "ID_PERIODO")
//	private Periodo periodo;
	
	private Integer anio;
	
	@OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaPresupuesto> lineaPresupuesto = new HashSet<LineaPresupuesto>();
	
	@ManyToOne
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal;
	
	@OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HistoricoPresupuesto> historicos= new HashSet<HistoricoPresupuesto>();
	
	public long getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(long idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
//	public Periodo getPeriodo() {
//		return periodo;
//	}
//
//	public void setPeriodo(Periodo periodo) {
//		this.periodo = periodo;
//	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Set<LineaPresupuesto> getLineaPresupuesto() {
		return lineaPresupuesto;
	}

	public void setLineaPresupuesto(Set<LineaPresupuesto> lineaPresupuesto) {
		this.lineaPresupuesto = lineaPresupuesto;
	}

	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}

	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}

	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idPresupuesto ==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idPresupuesto ^ (idPresupuesto >>> 32));
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
		final Presupuesto other = (Presupuesto) obj;
		if (idPresupuesto != other.idPresupuesto) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		DateFormat formato=DateFormat.getDateInstance(DateFormat.SHORT);
		if(this.fecha != null){
			formato.format(this.fecha);
		}
		return this.getIdPresupuesto() + " - " + this.nombre+" - "+this.estado.toString()+" - "+this.tipo.toString()+" (" + this.fecha +" )";
	 
	}

	public Set<HistoricoPresupuesto> getHistoricos() {
		return historicos;
	}
	public void setHistoricos(Set<HistoricoPresupuesto> historicos) {
		this.historicos = historicos;
	}

}
