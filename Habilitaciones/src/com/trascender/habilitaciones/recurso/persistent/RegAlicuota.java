package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;

@Entity
@Table(name = "REG_ALICUOTA")
@DiscriminatorColumn(name="TIPO_ALICUOTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RegAlicuota implements Serializable{
	
	public enum Estado{ACTIVO,INACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};

	public enum TipoRegAlicuota{
			FIJO_ANUAL(false,Periodicidad.ANUAL), 
			FIJO_MENSUAL(false,Periodicidad.MENSUAL), 
			PORCENTUAL_MENSUAL(true,Periodicidad.MENSUAL);
			
			//EL CONSTRUCTOR ES PRIVADO
			TipoRegAlicuota(boolean pPorcentual,Periodicidad pTipoPeriodicidad){
				this.porcentual=pPorcentual;
				this.periodicidad=pTipoPeriodicidad;
			}
			
			private boolean porcentual;
			private Periodicidad periodicidad;
			
			
			public Periodicidad getPeriodicidad() {
				return periodicidad;
			}
			public boolean isPorcentual() {
				return porcentual;
			}
	
			@Override
			public String toString() {
				return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
			}
	}
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -4583789689164067337L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_reg_alicuota")
	@SequenceGenerator(name = "gen_id_reg_alicuota", sequenceName = "gen_id_reg_alicuota", allocationSize = 1)
	@Column(name = "ID_REG_ALICUOTA")
	private long idTipoAlicuota=-1;
	
	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.ACTIVO;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_OBLIGACION")
	private TipoObligacion tipoObligacion;
	
	private String nombre;
	private String codigo;
	private Double minimo;
	private boolean porcentual;
	
	@Enumerated(EnumType.STRING)
	private Periodicidad periodicidad;
	
	private Double valor;
	
	public RegAlicuota(){
		super();
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public RegAlicuota(TipoRegAlicuota pTipoRegAlicuota){
		this.setPorcentual(pTipoRegAlicuota.isPorcentual());
		this.setPeriodicidad(pTipoRegAlicuota.getPeriodicidad());
	}

	public RegAlicuota(TipoObligacion pTipoObligacion) {
		this.setTipoObligacion(pTipoObligacion);
	}
	public void setTipoRegAlicuota(TipoRegAlicuota pTipoRegAlicuota){
		if(pTipoRegAlicuota != null){
			this.setPorcentual(pTipoRegAlicuota.isPorcentual());
			this.setPeriodicidad(pTipoRegAlicuota.getPeriodicidad());
		}
	}
	
	
	public TipoRegAlicuota getTipoRegAlicuota(){
		String valor;
		if (this.isPorcentual()){
			valor="PORCENTUAL";
		}
		else{
			valor="FIJO";
		}
		if (this.getPeriodicidad()!=null){
			valor+="_"+this.getPeriodicidad().toString();
			try{
				return TipoRegAlicuota.valueOf(Util.getEnumNameFromString(valor));
			}
			catch(IllegalArgumentException e){
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Es el pago mínimo //You don't say...
	 */
	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}

	public boolean isPorcentual() {
		return porcentual;
	}

	public void setPorcentual(boolean porcentual) {
		this.porcentual = porcentual;
	}

	
	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public long getIdTipoAlicuota() {
		return idTipoAlicuota;
	}
	public void setIdTipoAlicuota(long idTipoAlicuota) {
		this.idTipoAlicuota = idTipoAlicuota;
	}
	
	@Override
	public String toString() {
		StringBuilder locString=new StringBuilder("");
		if (this.getCodigo()!=null){
			locString.append(this.getCodigo());
			locString.append(" - ");
		}
		
		if (this.getNombre()!=null){
			locString.append(this.getNombre());
		}
		return locString.toString().trim();
	}
	
	public String getSimbolo(){
		return (this.isPorcentual())?"%":"$";
	}
	
	/**
	 * La sobreescriben las hijas, despues se va a pasar esta clase a abstracta.
	 * @return
	 */
	public List<AtributoDinamico<?>> getListaAtributosDinamicos(){
		return null;
	}
	
	@Override
	public int hashCode() {
		if (this.idTipoAlicuota==-1)
			return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoAlicuota ^ (idTipoAlicuota >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass()) //Comentado, se usa el If de abajo
//			return false;
		if (!(obj instanceof RegAlicuota)) //Para poder usar el equals sobre un objeto Javassist, que siempre extiende a TipoAlicuota.
			return false;
		final RegAlicuota other = (RegAlicuota) obj;
		if (this.getIdTipoAlicuota() != other.getIdTipoAlicuota())//No el field directamente, porque si es javassist, es nulo, el valor está en el metodo.
			return false;
		return true;
	}
}
