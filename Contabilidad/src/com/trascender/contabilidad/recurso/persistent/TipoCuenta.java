/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_CUENTA")
public class TipoCuenta implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 5035671181150785533L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_cuenta")
	@SequenceGenerator(name = "gen_id_tipo_cuenta", sequenceName = "gen_id_tipo_cuenta",allocationSize = 1)
	@Column(name="ID_TIPO_CUENTA")
	private long idTipoCuenta =-1;
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Abreviatura abreviatura;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "OPERA_ASIENTOS")
	private Opera operaAsientos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "OPERA_MOVIMIENTOS_CAJA")
	private Opera operaMovimientosCaja;
	public enum Abreviatura{A, P, I, E, PN, OR;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	public enum Opera {ACREDITA, DEBITA, AMBOS;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@OneToMany
	@JoinTable(name="RELA_CUENTA_TIPO_CUENTA", joinColumns=@JoinColumn(name="ID_TIPO_CUENTA", nullable = false),
									inverseJoinColumns=@JoinColumn(name="ID_CUENTA", nullable = false))
	private Set<Cuenta> listaCuenta = new HashSet<Cuenta>();
	
	@OneToMany
    @JoinTable(name="RELA_TIPO_CUENTA_TIPO_CUENTA", joinColumns=@JoinColumn(name="ID_TIPO_CUENTA", nullable = false),
    								inverseJoinColumns=@JoinColumn(name="ID_TIPO_CUENTA_EXCLUIDO", nullable = false))
	private List<TipoCuenta> listaTipoCuentaExcluidos = new ArrayList<TipoCuenta>();
	
	public Set<Cuenta> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(Set<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

	public Abreviatura getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(Abreviatura abreviatura) {
		this.abreviatura = abreviatura;
	}

	public long getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(long idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Opera getOperaAsientos() {
		return operaAsientos;
	}

	public void setOperaAsientos(Opera operaAsientos) {
		this.operaAsientos = operaAsientos;
	}

	public Opera getOperaMovimientosCaja() {
		return operaMovimientosCaja;
	}

	public void setOperaMovimientosCaja(Opera operaMovimientosCaja) {
		this.operaMovimientosCaja = operaMovimientosCaja;
	}
	
	public List<TipoCuenta> getListaTipoCuentaExcluidos() {
		return listaTipoCuentaExcluidos;
	}

	public void setListaTipoCuentaExcluidos(
			List<TipoCuenta> listaTipoCuentaExcluidos) {
		this.listaTipoCuentaExcluidos = listaTipoCuentaExcluidos;
	}

	@Override
	public String toString(){
		return this.nombre+" ("+this.abreviatura+")";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idTipoCuenta == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoCuenta ^ (idTipoCuenta >>> 32));
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
		final TipoCuenta other = (TipoCuenta) obj;
		if (idTipoCuenta != other.idTipoCuenta) {
			return false;
		}
		return true;
	}
}
