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
@Table(name = "BALANCE")
public class Balance implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 8410968549182974910L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_balance")
	@SequenceGenerator(name = "gen_id_balance", sequenceName = "gen_id_balance",allocationSize = 1)
	@Column(name="ID_BALANCE")
	private long idBalance =1;
	private String nombre;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_BALANCE")
	private TipoBalance tipoBalance;
	
	@OneToMany(mappedBy = "balance", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CuentaHistoricaBalance> listaCuentaHistoricoBalance = new HashSet<CuentaHistoricaBalance>();
	
	@ManyToMany
	@JoinTable(name = "RELA_BALANCE_MAYOR", joinColumns=@JoinColumn(name = "ID_BALANCE"), inverseJoinColumns=@JoinColumn(name = "ID_MAYOR"))
	private Set<Mayor> listaMayor = new HashSet<Mayor>();
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getIdBalance() {
		return idBalance;
	}
	public void setIdBalance(long idBalance) {
		this.idBalance = idBalance;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoBalance getTipoBalance() {
		return tipoBalance;
	}
	public void setTipoBalance(TipoBalance tipoBalance) {
		this.tipoBalance = tipoBalance;
	}
	
	public Set<CuentaHistoricaBalance> getListaCuentaHistoricoBalance() {
		return listaCuentaHistoricoBalance;
	}
	public void setListaCuentaHistoricoBalance(Set<CuentaHistoricaBalance> listaCuentaHistoricoBalance) {
		this.listaCuentaHistoricoBalance = listaCuentaHistoricoBalance;
	}
	public Set<Mayor> getListaMayor() {
		return listaMayor;
	}
	public void setListaMayor(Set<Mayor> listaMayor) {
		this.listaMayor = listaMayor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idBalance == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idBalance ^ (idBalance >>> 32));
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
		final Balance other = (Balance) obj;
		if (idBalance != other.idBalance) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		DateFormat formato=DateFormat.getDateInstance(DateFormat.SHORT);
		return this.nombre+" ("+formato.format(this.fecha)+")";
	}

}
