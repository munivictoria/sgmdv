/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MAYOR")
public class Mayor implements Serializable{

	public static final long serialVersionUID = 2475318865474262488L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_mayor")
	@SequenceGenerator(name = "gen_id_mayor", sequenceName = "gen_id_mayor",allocationSize = 1)
	@Column(name="ID_MAYOR")
	private long idMayor =-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToMany
	@JoinTable(name = "RELA_BALANCE_MAYOR", joinColumns=@JoinColumn(name = "ID_MAYOR"), inverseJoinColumns=@JoinColumn(name = "ID_BALANCE"))
	private Set<Balance> listaBalance = new HashSet<Balance>();
	
	@OneToMany(mappedBy = "mayor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaMayor> listaLineaMayor = new HashSet<LineaMayor>();
	
	private Integer anio;
	
	private Integer mes;

	public enum Tipo {
		A, B, C;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super
					.toString());
		}
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}	
	public long getIdMayor() {
		return idMayor;
	}
	
	public void setIdMayor(long idMayor) {
		this.idMayor = idMayor;
	}
	
	public Set<Balance> getListaBalance() {
		return listaBalance;
	}
	
	public void setListaBalance(Set<Balance> listaBalance) {
		this.listaBalance = listaBalance;
	}
	
	public Set<LineaMayor> getListaLineaMayor() {
		return listaLineaMayor;
	}
	
	public void setListaLineaMayor(Set<LineaMayor> listaLineaMayor) {
		this.listaLineaMayor = listaLineaMayor;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idMayor == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idMayor ^ (idMayor >>> 32));
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
		final Mayor other = (Mayor) obj;
		if (idMayor != other.idMayor) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString (){
		return this.cuenta+" - Tipo "+this.tipo+" - Año "+this.anio+ " - Mes "+this.mes;
	}
	
}
