package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_BALANCE")
public class TipoBalance implements Serializable{

	public static final long serialVersionUID = 7464910046400757976L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_balance")
	@SequenceGenerator(name = "gen_id_tipo_balance", sequenceName = "gen_id_tipo_balance",allocationSize = 1)
	@Column(name="ID_TIPO_BALANCE")
	private long idTipoBalance = -1;
	private String nombre;
	
	@Column(name = "FECHA_CREACION")
	private Date fecha;
	//Relacion con otros objetos
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "RELA_CUENTA_TIPO_BALANCE", joinColumns=@JoinColumn(name = "ID_TIPO_BALANCE"), inverseJoinColumns=@JoinColumn(name = "ID_CUENTA"))
	private Set<Cuenta> listaCuentas = new HashSet<Cuenta>();
	
	@OneToMany(mappedBy = "tipoBalance", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Balance> listaBalances = new HashSet<Balance>();
	
	public long getIdTipoBalance() {
		return idTipoBalance;
	}

	public void setIdTipoBalance(long idTipoBalance) {
		this.idTipoBalance = idTipoBalance;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fechaCreacion) {
		this.fecha = fechaCreacion;
	}

	public Set<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(Set<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public Set<Balance> getListaBalances() {
		return listaBalances;
	}

	public void setListaBalances(Set<Balance> listaBalances) {
		this.listaBalances = listaBalances;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.idTipoBalance ==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoBalance ^ (idTipoBalance >>> 32));
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
		final TipoBalance other = (TipoBalance) obj;
		if (idTipoBalance != other.idTipoBalance) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return this.nombre+" ("+locDateFormat.format(this.fecha)+")";
	}

}
